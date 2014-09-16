import java.io.IOException;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.qpid.amqp_1_0.jms.impl.ConnectionFactoryImpl;

class ListenerPublisher {

	public static void main(String[] args) throws Exception {

		ListenerPublisher publisher = new ListenerPublisher(args[0]);
		publisher.doTheJob();
	}

	private String publisherID;
	private OutputWriter out;

	public ListenerPublisher(String publisherID) {
		this.publisherID = publisherID;
	}

	private void doTheJob() throws JMSException, InterruptedException,
			IOException {

		out = new OutputWriter("./log/" + publisherID + ".txt");
		out.writeln(publisherID + " starts with the job.");

		Listener listener = new Listener(
				Configuration.getDestination(Configuration.destination_starter));

		out.writeln("Listener created");

		listener.waitUntilStartSignal(out);

		// Connection
		ConnectionFactoryImpl factory = new ConnectionFactoryImpl(
				Configuration.host, Configuration.port, Configuration.user,
				Configuration.password);
		Connection connection = factory.createConnection(Configuration.user,
				Configuration.password);
		connection.start();

		// Session Mode !!!
		Session session = connection.createSession(false,
				Session.CLIENT_ACKNOWLEDGE);

		// Producer
		MessageProducer producer = session.createProducer(Configuration
				.getDestination(Configuration.destination_listener));
		producer.setDeliveryMode(DeliveryMode.PERSISTENT);

		// send a bulk of messages
		sendMessageBulk(session, producer);

		// send a shutdown message
		producer.send(session.createTextMessage("SHUTDOWN"));

		Thread.sleep(1000 * 3);
		connection.close();
		System.exit(0);
	}

	private void sendMessageBulk(Session session, MessageProducer producer)
			throws JMSException {
		// System.nanoTime();
		String fillUpString = Configuration
				.createMessageText(Configuration.sizeOfTestMessages);
		long startSendingTime = System.currentTimeMillis();
		for (int i = 1; i <= Configuration.numberOfTestMessages; i++) {
			long currentTime = System.currentTimeMillis();
			TextMessage msg = session.createTextMessage("#:" + publisherID
					+ " t:" + currentTime + " s:" + fillUpString);

			msg.setStringProperty("publisher", publisherID);
			msg.setStringProperty("time", Long.valueOf(currentTime).toString());
			producer.send(msg);

		}
		long stopSendingTime = System.currentTimeMillis();
		System.out.println("Total time for creating and sending "
				+ Configuration.numberOfTestMessages + " messages:"
				+ (stopSendingTime - startSendingTime));

	}

}