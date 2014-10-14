import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import org.apache.qpid.amqp_1_0.jms.impl.ConnectionFactoryImpl;
import org.apache.qpid.amqp_1_0.jms.impl.TopicImpl;

public class Configuration {
	public static final String user = "admin";
	public static final String password = "password";
	public static final String host = "localhost";
	public static final int port = 5672;
	public static final String destination_listener = "topic://event";
	public static final String destination_starter = "topic://start";
	public static final int numberOfTestMessages = 1000;
	public static final int sizeOfTestMessages = 256;

	private static final String DATA = "abcdefghijklmnopqrstuvwxyz";
	public static int numberOfPublishers = 128;
	public static int numberOfSubsribers = 128;

	public static String createMessageText(int messageSize) {
		String messageText = "";
		for (int i = 0; i < messageSize; i++) {
			messageText += DATA.charAt(i % DATA.length());
		}
		return messageText;
	}

	public static Destination getDestination(String destination)
			throws JMSException {
		// return getDestinationOpenMQ(destination);
		return getDestinationActiveMQ(destination);
	}

	// private static Destination getDestinationOpenMQ(String destination)
	// throws JMSException {
	// return new Topic(destination);
	// }

	private static Destination getDestinationActiveMQ(String destination)
			throws JMSException {
		return new TopicImpl(destination);
	}

	public static Connection getConnection() throws JMSException {
		// return getConnectionOpenMQ();
		return getConnectionActiveMQ();
	}

	// private static Connection getConnectionOpenMQ() throws JMSException {
	// ConnectionFactory factory = new ConnectionFactory();
	// Connection connection = factory.createTopicConnection();
	// return connection;
	// }

	private static Connection getConnectionActiveMQ() throws JMSException {
		ConnectionFactoryImpl factory = new ConnectionFactoryImpl(
				Configuration.host, Configuration.port, Configuration.user,
				Configuration.password);
		Connection connection = factory.createConnection(Configuration.user,
				Configuration.password);
		return connection;
	}

	public static MessageConsumer createConsumer(Session session,
			String listenerID, javax.jms.Topic dest, String filter)
			throws JMSException {
		// return createConsumerOpenMQ(session, listenerID, dest, filter);
		return createConsumerActiveMQ(session, listenerID, dest, filter);
	}

	// private static MessageConsumer createConsumerOpenMQ(Session session,
	// String listenerID, javax.jms.Topic dest, String filter)
	// throws JMSException {
	// MessageConsumer consumer;
	// if (filter.equalsIgnoreCase("all")) {
	// consumer = session.createDurableConsumer(dest, listenerID);
	// } else {
	// consumer = session.createDurableConsumer(dest, listenerID,
	// "publisher = '" + filter + "'", false);
	// }
	// return consumer;
	// }

	private static MessageConsumer createConsumerActiveMQ(Session session,
			String listenerID, javax.jms.Topic dest, String filter)
			throws JMSException {
		MessageConsumer consumer;
		if (filter.equalsIgnoreCase("all")) {
			consumer = session.createDurableSubscriber(dest, listenerID);
		} else {
			consumer = session.createDurableSubscriber(dest, listenerID,
					"publisher = '" + filter + "'", false);
		}
		return consumer;
	}

}
