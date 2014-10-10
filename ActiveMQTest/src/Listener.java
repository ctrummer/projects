import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
// TODO think about this
// session.createBrowser(queue) instead of session.createConsumer(dest);
import javax.jms.Topic;

import com.sun.messaging.ConnectionFactory;

// TODO receive processing acknowdlege
// reciever vs. browser
// wie verhalten sich topic bzw queues beim ack.
class Listener {
	// public static void main(String[] args) throws JMSException, IOException {
	//
	// Listener listener = new Listener(Configuration.destination_listener,
	// args[1], args[2]);
	// listener.readMessageBulk();
	// }

	private Topic dest;
	private ConnectionFactory factory;
	private Connection connection;
	private Session session;
	private MessageConsumer consumer;
	private String listenerID;
	private OutputWriter out;
	private String filter;
	private AtomicBoolean readyForCLose = new AtomicBoolean();
	private ListenerStarter starter;

	// public Listener(TopicImpl source) throws JMSException {
	// dest = source;
	// filter = "all";
	// initListener();
	// }

	public Listener(String source, String id, String filter, ListenerStarter starter)
			throws JMSException, IOException {
		this.filter = filter;
		this.starter = starter;
		System.out.println("Listener<" + id + "> Source  == " + source);
		dest = new com.sun.messaging.Topic(source); // Configuration.getDestination(source);
		listenerID = id;
		out = new OutputWriter("./log/" + listenerID + ".txt");
		out.writeln("Listener" + listenerID + " started");

		initListener();
		out.writeln("Initialization done ...");

	}

	private void initListener() throws JMSException {
		readyForCLose.set(false);
		factory = new ConnectionFactory();
		connection = factory.createTopicConnection();

		// connection = factory.createConnection(Configuration.user,
		// Configuration.password);
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
		final AtomicInteger shutDownCounter = new AtomicInteger(0);

		final List<TimeMessage> messages = new ArrayList<TimeMessage>();

		consumer.setMessageListener(new MessageListener() {

			@Override
			public void onMessage(Message msg) {
				// System.out.println(Thread.currentThread().getId()
				// + " / received= " + msg);

				try {
					if (msg instanceof TextMessage) {
						if (msg.getStringProperty("shutdown").equalsIgnoreCase(
								"true")) {
							shutDownCounter.getAndIncrement();

							if (shutDownCounter.get() == Configuration.numberOfPublishers
									|| !filter.equals("all")) {

								calculateSimpleResult(messages);
								// connection.close();
								// calculateResults(messages);
								// System.out.println("Listener<" + listenerID
								// + "> Exit!");
								// // ;
								// System.exit(1);
								readyForCLose.set(true);
								starter.incremenetListenerFinished();

							}
						} else {
							messages.add(new TimeMessage(System
									.currentTimeMillis(), (TextMessage) msg));
						}
					} else {
						System.out.println("Unexpected message type: "
								+ msg.getClass());
						System.exit(-1);
					}
				} catch (JMSException e) {
					throw new IllegalStateException(e);
				}
			}
		});

	}

	public void closeConnection() throws JMSException {
		connection.close();
	}

	private void calculateSimpleResult(List<TimeMessage> messages)
			throws JMSException {
		int numberOfMessages = messages.size();
		double startTime = messages.get(0).time;
		double endTime = messages.get(numberOfMessages - 1).time;
		double totalTime = endTime - startTime;
		double averageTime = ((endTime - startTime) * 1000) / numberOfMessages;
		System.out
				.printf("Listener<%10s>: Total Time: %10.2f Message Count: %6d #1000: %10.2f %n",
						listenerID, totalTime, numberOfMessages, averageTime);
		averageRuntime(messages);
	}

	private void averageRuntime(List<TimeMessage> messageList)
			throws JMSException {

		double totalRunTime = 0;
		for (int index = 0; index < messageList.size(); index++) {
			TimeMessage message = messageList.get(index);
			double endTime = message.time;
			double startTime = Long.valueOf(message.message
					.getStringProperty("time"));

			totalRunTime = totalRunTime + (endTime - startTime);
		}
		double averageRunTime = totalRunTime / messageList.size();

		System.out.printf(
				"Listener<%s> Average runtime over all messages: %6.2f %n",
				listenerID, averageRunTime);
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

		averageRuntime(publisher, messageList);

	}

	private void averageRuntime(int publisher, List<TimeMessage> messageList)
			throws JMSException {
		averageRuntime(messageList);

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
