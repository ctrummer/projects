import java.io.IOException;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;

import com.sun.messaging.ConnectionFactory;

class PublishersStarter {

	public void doTheJob() throws IOException, InterruptedException,
			JMSException {
		try {
			job();
			System.out.println("PublisherStarter broadcasted start-signal.");
		} catch (JMSException e) {
			System.out.println("PublisherStarter failed.");
			System.out.println(e.toString());
		}
	}

	private void job() throws JMSException {
		ConnectionFactory factory = new ConnectionFactory();
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

		// send a start message
		producer.send(session.createTextMessage("STARTUP"));
		connection.close();
	}
}