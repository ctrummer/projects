import javax.jms.JMSException;

public interface ListenerStarter {

	public abstract void incremenetListenerFinished() throws JMSException;

}