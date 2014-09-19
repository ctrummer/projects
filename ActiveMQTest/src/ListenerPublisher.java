import java.io.IOException;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.hornetq.jms.client.HornetQTopicConnectionFactory;

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

		Listener listener = new Listener(Configuration.destination_starter,
				publisherID, "all");

		out.writeln("Listener created");

		listener.waitUntilStartSignal(out);

		Connection connection = getConnection();
		Session session = getSession(connection);

		// Producer
		MessageProducer producer = session.createProducer(Configuration
				.getDestination(Configuration.destination_listener));
		producer.setDeliveryMode(DeliveryMode.PERSISTENT);

		// send a bulk of messages
		sendMessageBulk(session, producer);

		// send a shutdown message
		sendMessageShutDown(session, producer);

		Thread.sleep(1000 * 3);
		connection.close();
		System.out.println("Publisher<" + publisherID + "> Exit!");
		System.exit(0);
	}

	private Session getSession(Connection connection) throws JMSException {
		// Session Mode !!!
		Session session = connection.createSession(false,
				Session.CLIENT_ACKNOWLEDGE);
		return session;
	}

	private Connection getConnection() throws JMSException {
		ConnectionFactory factory = new HornetQTopicConnectionFactory();

		// Configuration.host, Configuration.port, Configuration.user,
		// Configuration.password);

		Connection connection = factory.createConnection(Configuration.user,
				Configuration.password);
		connection.start();
		return connection;
	}

	private void sendMessageShutDown(Session session, MessageProducer producer)
			throws JMSException {

		TextMessage msg = session.createTextMessage("SHUTDOWN");
		msg.setStringProperty("publisher", publisherID);
		msg.setStringProperty("time", Long.valueOf(System.currentTimeMillis())
				.toString());
		msg.setStringProperty("shutdown", "true");
		producer.send(msg);
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
			msg.setStringProperty("shutdown", "false");
			producer.send(msg);

		}
		long stopSendingTime = System.currentTimeMillis();
		System.out.println("Publisher: " + publisherID
				+ " needed total time for creating and sending "
				+ Configuration.numberOfTestMessages + " messages: "
				+ (stopSendingTime - startSendingTime));

	}

}