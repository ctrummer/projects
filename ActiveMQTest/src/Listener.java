import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
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

// TODO receive processing acknowdlege
// reciever vs. browser
// wie verhalten sich topic bzw queues beim ack.
class Listener {

	private Topic dest;
	private Connection connection;
	private Session session;
	private String listenerID;
	private String filter;
	private AtomicBoolean isCloseable = new AtomicBoolean();
	private ListenerStarter starter;
	private String topic;

	public Listener(String topic, String id, String filter,
			ListenerStarter starter) throws JMSException, IOException {
		this.topic = topic;
		this.filter = filter;
		this.starter = starter;
		System.out.println("Listener<" + id + "> Source  == " + topic);
		listenerID = id;

		initListener();
		System.out.println("Listener" + listenerID + " started");

	}

	private void initListener() throws JMSException {
		isCloseable.set(false);

		connection = Configuration.getConnection();
		connection.setClientID(listenerID);
		connection.start();

		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		dest = session.createTopic(topic);

		MessageConsumer consumer = Configuration.createConsumer(session,
				listenerID, dest, filter);
		setListener(consumer);
	}

	public void setListener(MessageConsumer consumer) throws JMSException {
		System.out.println("Listener: " + listenerID
				+ " is waiting for messages...");
		final AtomicInteger shutDownCounter = new AtomicInteger(0);

		// Create fully size array so no resize needs to be done
		final List<TimeMessage> messages = new ArrayList<TimeMessage>(
				Configuration.numberOfTestMessages
						* Configuration.numberOfPublishers);

		consumer.setMessageListener(new MessageListener() {

			@Override
			public void onMessage(Message msg) {

				try {
					if (!(msg instanceof TextMessage)) {
						System.out.println("Unexpected message type: "
								+ msg.getClass());
						return;
					}

					if (msg.getStringProperty("shutdown").equalsIgnoreCase(
							"true")) {
						shutDownCounter.getAndIncrement();

						if (shutDownCounter.get() == Configuration.numberOfPublishers
								|| !filter.equals("all")) {

							calculateSimpleResult(messages);

							isCloseable.set(true);
							starter.incremenetListenerFinished();

						}
					} else {
						messages.add(new TimeMessage((TextMessage) msg));
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
		double startTime = messages.get(0).receivedAt;
		double endTime = messages.get(numberOfMessages - 1).receivedAt;
		double totalTime = endTime - startTime;
		double averageTime = ((endTime - startTime) / numberOfMessages) * 1000;
		long averageOverallTime = averageRuntime(messages);
		System.out
				.printf("Listener<%10s>: Total Time: %10d ms \tMessage Count: %6d \t#1000: %10d ms \taverage travel time per message:  %10d ms %n",
						listenerID, TimeUnit.NANOSECONDS.toMillis(Double
								.valueOf(totalTime).longValue()),
						numberOfMessages, TimeUnit.NANOSECONDS.toMillis(Double
								.valueOf(averageTime).longValue()),
						TimeUnit.NANOSECONDS.toMillis(averageOverallTime));
	}

	private long averageRuntime(List<TimeMessage> messageList)
			throws JMSException {

		double totalRunTime = 0;

		for (TimeMessage message : messageList) {
			double endTime = message.receivedAt;
			double startTime = Long.valueOf(message.message
					.getStringProperty("time"));
			totalRunTime = totalRunTime + (endTime - startTime);
		}

		double averageRunTime = totalRunTime / messageList.size();

		return Double.valueOf(averageRunTime).longValue();

	}

}
