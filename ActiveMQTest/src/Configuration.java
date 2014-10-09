import javax.jms.Destination;

import org.apache.qpid.amqp_1_0.jms.impl.QueueImpl;
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
	public static int numberOfPublishers = 32;
	public static int numberOfSubsribers = 32;

	public static String createMessageText(int messageSize) {
		String messageText = "";
		for (int i = 0; i < messageSize; i++) {
			messageText += DATA.charAt(i % DATA.length());
		}
		return messageText;
	}

	public static Destination getDestination(String destination) {
		Destination dest = null;
		if (destination.startsWith("topic://")) {
			dest = new TopicImpl(destination);
		} else {
			dest = new QueueImpl(destination);
		}
		return dest;
	}
}
