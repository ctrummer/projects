import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import org.hornetq.api.core.TransportConfiguration;
import org.hornetq.api.jms.HornetQJMSClient;
import org.hornetq.api.jms.JMSFactoryType;
import org.hornetq.core.remoting.impl.netty.NettyConnectorFactory;
import org.hornetq.jms.client.HornetQConnectionFactory;
import org.hornetq.jms.client.HornetQTopic;

public class Configuration {
	public static final String user = "guest";
	public static final String password = "guest";
	public static final String host = "localhost";
	public static final int port = 1099;
	public static final String destination_listener = "eventTopic";
	public static final String destination_starter = "startTopic";
	public static final int numberOfTestMessages = 1000;
	public static final int sizeOfTestMessages = 256;

	private static final String DATA = "abcdefghijklmnopqrstuvwxyz";
	public static int numberOfPublishers = 4;
	public static int numberOfSubsribers = 4;

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
		// return getDestinationActiveMQ(destination);
		return getDestinationHornetQ(destination);
	}

	private static Destination getDestinationHornetQ(String destination)
			throws JMSException {
		return new HornetQTopic(destination);
	}

	// private static Destination getDestinationOpenMQ(String destination)
	// throws JMSException {
	// return new Topic(destination);
	// }

	// private static Destination getDestinationActiveMQ(String destination)
	// throws JMSException {
	// return new TopicImpl(destination);
	// }

	public static Connection getConnection() throws JMSException {
		return getConnectionHornetQ();
		// return getConnectionOpenMQ();
		// return getConnectionActiveMQ();
	}

	private static Connection getConnectionHornetQ() throws JMSException {
		TransportConfiguration transportConfiguration = new TransportConfiguration(
				NettyConnectorFactory.class.getName());
		HornetQConnectionFactory factory = HornetQJMSClient
				.createConnectionFactoryWithoutHA(JMSFactoryType.TOPIC_CF,
						transportConfiguration);
		return factory.createConnection(Configuration.user,
				Configuration.password);
	}

	// private static Connection getConnectionOpenMQ() throws JMSException {
	// ConnectionFactory factory = new ConnectionFactory();
	// Connection connection = factory.createTopicConnection();
	// return connection;
	// }

	// private static Connection getConnectionActiveMQ() throws JMSException {
	// ConnectionFactoryImpl factory = new ConnectionFactoryImpl(
	// Configuration.host, Configuration.port, Configuration.user,
	// Configuration.password);
	// Connection connection = factory.createConnection(Configuration.user,
	// Configuration.password);
	// return connection;
	// }

	public static MessageConsumer createConsumer(Session session,
			String listenerID, javax.jms.Topic dest, String filter)
			throws JMSException {
		return createConsumerHornetQ(session, listenerID, dest, filter);
		// return createConsumerOpenMQ(session, listenerID, dest, filter);
		// return createConsumerActiveMQ(session, listenerID, dest, filter);
	}

	private static MessageConsumer createConsumerHornetQ(Session session,
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

	// private static MessageConsumer createConsumerActiveMQ(Session session,
	// String listenerID, javax.jms.Topic dest, String filter)
	// throws JMSException {
	// MessageConsumer consumer;
	// if (filter.equalsIgnoreCase("all")) {
	// consumer = session.createDurableSubscriber(dest, listenerID);
	// } else {
	// consumer = session.createDurableSubscriber(dest, listenerID,
	// "publisher = '" + filter + "'", false);
	// }
	// return consumer;
	// }

}
