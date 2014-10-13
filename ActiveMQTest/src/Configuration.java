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


// Listener ActiveMQ

private void initListener() throws JMSException {
    factory = new ConnectionFactoryImpl(Configuration.host,
            Configuration.port, Configuration.user, Configuration.password);

    connection = factory.createConnection(Configuration.user,
            Configuration.password);
    connection.setClientID(listenerID);
    connection.start();

    session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

    if (filter.equalsIgnoreCase("all")) {
        // consumer = session.createConsumer(dest);
        consumer = session.createDurableSubscriber(dest, listenerID);
    } else {
        // consumer = session.createConsumer(dest, "publisher = '" + filter
        // + "'");
        consumer = session.createDurableSubscriber(dest, listenerID,
                "publisher = '" + filter + "'", false);

    }
}

// Listener HornetQ
private void initListener() throws JMSException {
    TransportConfiguration transportConfiguration = new TransportConfiguration(
            NettyConnectorFactory.class.getName());
    factory = HornetQJMSClient.createConnectionFactoryWithoutHA(
            JMSFactoryType.TOPIC_CF, transportConfiguration);

    // factory = new HornetQConnectionFactory(Configuration.host,
    // Configuration.port, Configuration.user, Configuration.password);

    connection = factory.createConnection(Configuration.user,
            Configuration.password);
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


// Publisher ActieMQ

//Connection
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


// Publisher HornetQ
TransportConfiguration transportConfiguration = new TransportConfiguration(
        NettyConnectorFactory.class.getName());
factory = HornetQJMSClient.createConnectionFactoryWithoutHA(
        JMSFactoryType.TOPIC_CF, transportConfiguration);

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

