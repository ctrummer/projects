import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.naming.InitialContext;
import javax.naming.NamingException;

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

	private Topic dest;
	private ConnectionFactory factory;
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

		// dest = new HornetQTopic(source); //
		// Configuration.getDestination(source);
		listenerID = id;
		out = new OutputWriter("./log/" + listenerID + ".txt");
		out.writeln("Listener" + listenerID + " started");

		initListener();
		out.writeln("Initialization done ...");

	}

	private void initListener() {
		Connection connection = null;
		InitialContext initialContext = null;
		// getContext - start
		Properties props = new Properties();
		props.put("java.naming.factory.initial",
				"org.jnp.interfaces.NamingContextFactory");
		props.put("java.naming.provider.url", 0);
		props.put("java.naming.factory.url.pkgs",
				"org.jboss.naming:org.jnp.interfaces");
		try {
			initialContext = new InitialContext(props);

			// getContext - end

			Topic topic = (Topic) initialContext.lookup("/topic/event");
			ConnectionFactory cf = (ConnectionFactory) initialContext
					.lookup("/ConnectionFactory");
			connection = cf.createConnection();
			connection.setClientID(listenerID);
			connection.start();

			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			dest = session.createTopic("topic://event");
			if (filter.equalsIgnoreCase("all")) {
				// consumer = session.createConsumer(dest);
				consumer = session.createDurableSubscriber(dest, listenerID);
			} else {
				// consumer = session.createConsumer(dest, "publisher = '" +
				// filter
				// + "'");
				consumer = session.createDurableSubscriber(dest, listenerID,
						"publisher = '" + filter + "'", false);

			}
		} catch (NamingException e) {

			e.printStackTrace();
		} catch (JMSException e) {

			e.printStackTrace();
		} finally {
			// Step 14. Be sure to close our JMS resources!
			if (connection != null) {
				// connection.close();
			}

			// Also the initialContext
			if (initialContext != null) {
				// initialContext.close();
			}
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