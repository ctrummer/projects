public class TimeMessage {

	public long time;
	public String message;

	public TimeMessage(long currentTimeMillis, String messageBody) {
		time = currentTimeMillis;
		message = messageBody;
	}

}
