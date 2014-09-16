import javax.jms.TextMessage;

public class TimeMessage {

	public long time;
	public TextMessage message;

	public TimeMessage(long currentTimeMillis, TextMessage message) {
		time = currentTimeMillis;
		this.message = message;
	}

}
