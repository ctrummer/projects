import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TopicConnection;

import com.sun.messaging.ConnectionFactory;
import com.sun.messaging.Topic;

public class Configuration {
	public static final String user = "admin";
	public static final String password = "admin";
	public static final String host = "localhost";
	public static final int port = 1099;
	public static final String destination_listener = "eventTopic";
	public static final String destination_starter = "startTopic";
	public static final int numberOfTestMessages = 1000;
	public static final int sizeOfTestMessages = 256;

	private static final String DATA = "abcdefghijklmnopqrstuvwxyz";
	public static int numberOfPublishers = 10;
	public static int numberOfSubsribers = 10;

	public static String createMessageText(int messageSize) {
		String messageText = "";
		for (int i = 0; i < messageSize; i++) {
			messageText += DATA.charAt(i % DATA.length());
		}
		return messageText;
	}

	public static Destination getDestination(String destination)
			throws JMSException {
		Destination dest = null;

		dest = new Topic(destination);
		// dest = HornetQJMSClient.createTopic(destination);

		return dest;
	}

	public static Connection getConnection() throws JMSException {
		ConnectionFactory factory = new ConnectionFactory();
		Connection connection = factory.createTopicConnection();
		return connection;
	}

	public static Connection getConnectionForPublisher() throws JMSException {
		ConnectionFactory factory = new ConnectionFactory();
		TopicConnection connection = factory.createTopicConnection();
		return connection;
	}

	public static MessageConsumer createConsumer(Session session,
			String listenerID, javax.jms.Topic dest, String filter)
			throws JMSException {
		MessageConsumer consumer;
		if (filter.equalsIgnoreCase("all")) {
			consumer = session.createDurableConsumer(dest, listenerID);
		} else {
			consumer = session.createDurableConsumer(dest, listenerID,
					"publisher = '" + filter + "'", false);
		}
		return consumer;
	}

}
