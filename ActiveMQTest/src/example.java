//GrabResolver(name='JBoss releases', root='https://repository.jboss.org/nexus/content/repositories/releases/')
//@Grab(group='org.hornetq', module='hornetq-core', version='2.0.0.GA')
//@Grab(group='org.hornetq', module='hornetq-jms', version='2.0.0.GA')
//@Grab(group='org.hornetq', module='hornetq-logging', version='2.0.0.GA')
//@Grab(group='org.hornetq', module='hornetq-transports', version='2.0.0.GA')
//@Grab(group='org.jboss.netty', module='netty', version='3.1.5.GA')
//@Grab(group='org.jboss.javaee', module='jboss-jms-api', version='1.1.0.GA')
//@Grab(group='org.jboss.logging', module='jboss-logging', version='2.1.0.GA')
//@GrabResolver(name='Maven Central', root='http://repo1.maven.org/')
//@Grab(group='jboss', module='jnpserver', version='4.2.2.GA')
//@GrabConfig(systemClassLoader=true)

import java.util.List;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.hornetq.api.core.TransportConfiguration;
import org.hornetq.api.jms.HornetQJMSClient;
import org.hornetq.core.config.Configuration;
import org.hornetq.core.config.impl.ConfigurationImpl;
import org.hornetq.core.remoting.impl.invm.InVMAcceptorFactory;
import org.hornetq.core.remoting.impl.invm.InVMConnectorFactory;
import org.hornetq.core.server.HornetQServer;
import org.hornetq.core.server.impl.HornetQServerImpl;
import org.hornetq.jms.server.JMSServerManager;
import org.hornetq.jms.server.config.JMSConfiguration;
import org.hornetq.jms.server.config.JMSQueueConfiguration;
import org.hornetq.jms.server.config.impl.JMSConfigurationImpl;
import org.hornetq.jms.server.impl.JMSServerManagerImpl;

public class example {

	public void test() {

		String QUEUE_NAME = "exampleQueue";
		String JMS_QUEUE_NAME = "/queue/${QUEUE_NAME}";

		HornetQServer server = null;
		Connection connection = null;

		try {

			Configuration configuration = new ConfigurationImpl();

			configuration.setPersistenceEnabled(false);
			configuration.setSecurityEnabled(false);
			// configuration.setClustered(false);
			configuration.setClusterUser("blah");
			configuration.setClusterPassword("not-used");
			configuration.setJMXManagementEnabled(false);
			configuration.getAcceptorConfigurations().add(
					new TransportConfiguration(InVMAcceptorFactory.class
							.getName()));

			server = new HornetQServerImpl(configuration);

			JMSConfiguration jmsConfiguration = new JMSConfigurationImpl();

			List<JMSQueueConfiguration> queueConfigurations = jmsConfiguration
					.getQueueConfigurations();

			// queueConfigurations = new QueueConfigurationImpl(QUEUE_NAME,
			// null,
			// false, JMS_QUEUE_NAME);

			JMSServerManager jmsServerManager = new JMSServerManagerImpl(
					server, jmsConfiguration);

			// We're not using JNDI.
			jmsServerManager.setContext(null);
			jmsServerManager.start();

			// -----

			Queue queue = HornetQJMSClient.createQueue("${QUEUE_NAME}");

			TransportConfiguration transportConfiguration = new TransportConfiguration(
					InVMConnectorFactory.class.getName());

			ConnectionFactory cf = HornetQJMSClient.
					.createConnectionFactory(transportConfiguration);

			connection = cf.createConnection();

			Session session = connection.createSession(false,
					Session.AUTO_ACKNOWLEDGE);

			MessageProducer producer = session.createProducer(queue);

			TextMessage message = session
					.createTextMessage("This is a text message");

			System.out.println("Sent message: " + message.getText());

			producer.send(message);

			MessageConsumer messageConsumer = session.createConsumer(queue);

			connection.start();

			TextMessage messageReceived = (TextMessage) messageConsumer
					.receive(5000);

			System.out
					.println("Received message: " + messageReceived.getText());

			connection.close();

		} finally {
			if (connection != null) {
				connection.close();
			}
			if (server != null) {
				server.stop();
			}
		}
	}
}
