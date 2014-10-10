import javax.jms.Destination;
import javax.jms.JMSException;

import com.sun.messaging.Topic;

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
	public static int numberOfPublishers = 16;
	public static int numberOfSubsribers = 32;

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
}
