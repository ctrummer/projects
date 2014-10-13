import javax.jms.TextMessage;

public class TimeMessage {

  public long receivedAt;
  public TextMessage message;

  protected TimeMessage(TextMessage message) {
    receivedAt = System.nanoTime();
    this.message = message;
  }

}
