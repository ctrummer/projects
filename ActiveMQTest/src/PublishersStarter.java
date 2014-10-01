import java.io.IOException;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.qpid.amqp_1_0.jms.impl.ConnectionFactoryImpl;

class PublishersStarter {

	private OutputWriter out;

	public static void main(String[] args) throws Exception {
		PublishersStarter publisher = new PublishersStarter();
		publisher.doTheJob();
		System.out.println("bla bla");

	}

	private void doTheJob() throws IOException, InterruptedException,
			JMSException {
		out = new OutputWriter("./log/PublishersStarter.txt");
		out.writeln("PublisherStarter starts with the job.");
		Connection connection = null;
		try {
			connection = job();
		} catch (JMSException e) {
			out.writeln(e.toString());
		}
		out.writeln("Broadcasted STARTUP-Message to topic for the publishers.");
		out.close();

		Thread.sleep(1000 * 3);
		connection.close();
		System.out.println("PublisherStarter Exit!");
		System.exit(0);
	}

	private Connection job() throws JMSException {
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
				.getDestination(Configuration.destination_starter));
		producer.setDeliveryMode(DeliveryMode.PERSISTENT);

		// send a shutdown message
		producer.send(session.createTextMessage("STARTUP"));
		return connection;
	}

}