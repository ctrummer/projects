import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.hornetq.api.core.TransportConfiguration;
import org.hornetq.api.jms.HornetQJMSClient;
import org.hornetq.api.jms.JMSFactoryType;
import org.hornetq.core.remoting.impl.netty.NettyConnectorFactory;
import org.hornetq.jms.client.HornetQConnectionFactory;
import org.hornetq.jms.client.HornetQTopic;
// TODO think about this
// session.createBrowser(queue) instead of session.createConsumer(dest);

// TODO receive processing acknowdlege
// reciever vs. browser
// wie verhalten sich topic bzw queues beim ack.
class Listener {
	public static void main(String[] args) throws JMSException, IOException {

		Listener listener = new Listener(Configuration.destination_listener,
				args[1], args[2]);
		listener.readMessageBulk();
	}

	private HornetQTopic dest;
	private HornetQConnectionFactory factory;
	private Connection connection;
	private Session session;
	private MessageConsumer consumer;
	private String listenerID;
	private OutputWriter out;
	private String filter;

	// public Listener(TopicImpl source) throws JMSException {
	// dest = source;
	// filter = "all";
	// initListener();
	// }

	public Listener(String source, String id, String filter)
			throws JMSException, IOException {
		this.filter = filter;
		System.out.println("Listener<" + id + "> Source  == " + source);
		dest = new HornetQTopic(source); // Configuration.getDestination(source);
		listenerID = id;
		out = new OutputWriter("./log/" + listenerID + ".txt");
		out.writeln("Listener" + listenerID + " started");

		initListener();
		out.writeln("Initialization done ...");

	}

	private void initListener() throws JMSException {
		TransportConfiguration transportConfiguration = new TransportConfiguration(
				NettyConnectorFactory.class.getName());
		factory = HornetQJMSClient.createConnectionFactoryWithoutHA(
				JMSFactoryType.TOPIC_CF, transportConfiguration);

		// factory = new HornetQConnectionFactory(Configuration.host,
		// Configuration.port, Configuration.user, Configuration.password);

		connection = factory.createConnection(Configuration.user,
				Configuration.password);
		connection.setClientID(listenerID);
		connection.start();

		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

		if (filter.equalsIgnoreCase("all")) {
			// consumer = session.createConsumer(dest);
			consumer = session.createDurableConsumer(dest, listenerID);
			// consumer = session.createDurableSubscriber(dest, listenerID);
		} else {
			// consumer = session.createConsumer(dest, "publisher = '" + filter
			// + "'");
			consumer = session.createDurableConsumer(dest, listenerID,
					"publisher = '" + filter + "'", false);
			// consumer = session.createDurableSubscriber(dest, listenerID,
			// "publisher = '" + filter + "'", false);

		}
	}

	public void readMessageBulk() throws JMSException {
		System.out.println("Listener: " + listenerID
				+ " is waiting for messages...");
		int shutDownCounter = 0;
		List<TimeMessage> messages = new ArrayList<TimeMessage>();
		while (true) {
			Message msg = consumer.receive();
			if (msg instanceof TextMessage) {
				if (msg.getStringProperty("shutdown").equalsIgnoreCase("true")) {
					shutDownCounter++;
					if (shutDownCounter == Configuration.numberOfPublishers
							|| !filter.equals("all")) {
						connection.close();
						calculateResults(messages);
						System.out
								.println("Listener<" + listenerID + "> Exit!");
						;
						System.exit(1);
					}
				} else {
					messages.add(new TimeMessage(System.currentTimeMillis(),
							(TextMessage) msg));
				}
			} else {
				System.out
						.println("Unexpected message type: " + msg.getClass());
				System.exit(-1);
			}
		}

	}

	private void calculateResults(List<TimeMessage> allMessages)
			throws NumberFormatException, JMSException {
		List[] splittedLists = new ArrayList[Configuration.numberOfPublishers];
		for (int index = 0; index < Configuration.numberOfPublishers; index++) {
			splittedLists[index] = new ArrayList<TimeMessage>();
		}

		Iterator<TimeMessage> iterator = allMessages.iterator();
		while (iterator.hasNext()) {
			TimeMessage timeMessage = iterator.next();
			int publisherNumber = Integer.valueOf(timeMessage.message
					.getStringProperty("publisher"));
			splittedLists[publisherNumber - 1].add(timeMessage);
		}

		for (int index = 0; index < Configuration.numberOfPublishers; index++) {
			// may be empyt in case of filtering
			if (!splittedLists[index].isEmpty()) {
				caluclateResultsPerPublisher(index, splittedLists[index]);
			}
		}
	}

	private void caluclateResultsPerPublisher(int publisher,
			List<TimeMessage> messageList) throws NumberFormatException,
			JMSException {

		TimeMessage firstMessage = messageList.get(0);
		TimeMessage lastMessage = messageList.get(messageList.size() - 1);
		long totalTime = lastMessage.time - firstMessage.time;
		out.writeln("Listener <" + listenerID + "> ListenerPublisher <"
				+ publisher + "> Total time  == " + totalTime);
		System.out.println("Listener <" + listenerID + "> ListenerPublisher <"
				+ publisher + "> Total time  == " + totalTime);

		int numberOfReceivedMessages = 0;
		long totalRunTime = 0;
		for (int index = 0; index < messageList.size(); index++) {
			TimeMessage message = messageList.get(index);
			long endTime = message.time;
			long startTime = Long.valueOf(message.message
					.getStringProperty("time"));
			numberOfReceivedMessages++;
			totalRunTime = totalRunTime + (endTime - startTime);
		}
		double averageRunTime = (totalRunTime * 1.0)
				/ (numberOfReceivedMessages * 1.0);
		out.writeln("Average time per message for ListenerPubliser" + publisher
				+ " == " + averageRunTime);
		System.out
				.println("Listener <" + listenerID + "> ListenerPublisher <"
						+ publisher + "> Average time per message == "
						+ averageRunTime);

	}

	public void waitUntilStartSignal(OutputWriter out) throws JMSException {
		while (true) {
			Message msg = consumer.receive();
			if (msg instanceof TextMessage) {
				String body = ((TextMessage) msg).getText();
				if ("STARTUP".equals(body)) {
					out.writeln("Startup message received ...");
					connection.close();
					break;
				}
			}
		}
	}

}