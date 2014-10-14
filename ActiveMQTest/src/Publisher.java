import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

class Publisher implements Runnable {

	private String publisherID;
	private Connection connection;
	private String fillUpString;

	public Publisher(String publisherID) {
		this.publisherID = publisherID;
		fillUpString = Configuration
				.createMessageText(Configuration.sizeOfTestMessages);
	}

	private void doTheJob() throws JMSException, InterruptedException,
			IOException {

		System.out
				.println("Publisher " + publisherID + " starts with the job.");
		Session session = null;
		MessageProducer producer = null;
		try {
			connection = Configuration.getConnection();
			connection.start();

			// Session Mode !!!
			session = connection.createSession(false,
					Session.CLIENT_ACKNOWLEDGE);

			// Producer
			producer = session.createProducer(Configuration
					.getDestination(Configuration.destination_listener));
			producer.setDeliveryMode(DeliveryMode.PERSISTENT);

			// send a bulk of messages
			sendMessageBulk(session, producer);

			// send a shutdown message
			sendMessageShutDown(session, producer);
		} finally {
			if (producer != null) {
				producer.close();
			}
			if (session != null) {
				session.close();
			}
			connection.close();
		}

		System.out.println("Publisher<" + publisherID + "> finished!");

	}

	private void sendMessageShutDown(Session session, MessageProducer producer)
			throws JMSException {

		TextMessage msg = session.createTextMessage("SHUTDOWN");
		msg.setStringProperty("publisher", publisherID);
		msg.setStringProperty("time", System.nanoTime() + "");
		msg.setStringProperty("shutdown", "true");
		producer.send(msg);
	}

	private void sendMessageBulk(Session session, MessageProducer producer)
			throws JMSException {

		long startSendingTime = System.nanoTime();

		for (int i = 1; i <= Configuration.numberOfTestMessages; i++) {
			long currentTime = System.nanoTime();

			TextMessage msg = session.createTextMessage("#:" + publisherID
					+ " t:" + currentTime + " s:" + fillUpString);

			msg.setStringProperty("publisher", publisherID);
			msg.setStringProperty("time", currentTime + "");
			msg.setStringProperty("shutdown", "false");

			producer.send(msg);

		}

		long stopSendingTime = System.nanoTime();

		System.out.println("Publisher: "
				+ publisherID
				+ " needed total time for creating and sending "
				+ Configuration.numberOfTestMessages
				+ " messages: "
				+ TimeUnit.NANOSECONDS
						.toMillis((stopSendingTime - startSendingTime)));

	}

	@Override
	public void run() {
		try {
			doTheJob();
		} catch (JMSException | InterruptedException | IOException e) {
			e.printStackTrace();
		}

	}
}