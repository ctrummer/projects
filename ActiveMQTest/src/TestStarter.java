import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestStarter {

	// private final static String classpath = "./bin;" + //
	// "./lib/geronimo-jms_1.1_spec-1.1.jar;" + //
	// "./lib/qpid-amqp-1-0-client-0.26.jar;" + //
	// "./lib/qpid-amqp-1-0-client-jms-0.26.jar;" + //
	// "./lib/qpid-amqp-1-0-common-0.26.jar;";
	//

	private final static String classpath = "./bin;" + //
			"./lib_hornet/hornetq-commons-2.4.0.Final.jar" + ";" + //
			"./lib_hornet/hornetq-core-client-2.4.0.Final.jar" + ";" + //
			"./lib_hornet/hornetq-jms-client-2.4.0.Final.jar" + ";" + //
			"./lib_hornet/hornetq-jms-examples-common-2.4.0.Final.jar" + ";" + //
			"./lib_hornet/hornetq-journal-2.4.0.Final.jar" + ";" + //
			"./lib_hornet/hornetq-native-2.4.0.Final.jar" + ";" + //
			"./lib_hornet/javax.inject-1.jar" + ";" + //
			"./lib_hornet/jboss-jms-api_2.0_spec-1.0.0.Final.jar" + ";" + //
			"./lib_hornet/jboss-logging-3.1.0.GA.jar" + ";" + //
			"./lib_hornet/jgroups-3.3.4.Final.jar" + ";" + //
			"./lib_hornet/netty-all-4.0.13.Final.jar" + ";";

	public static void main(String[] args) throws IOException,
			InterruptedException {
		OutputWriter out = new OutputWriter("./log/TestStarter.txt");
		out.writeln("TestStarter started.");

		for (int listenerCounter = 1; listenerCounter <= Configuration.numberOfSubsribers; listenerCounter++) {
			if (listenerCounter != Configuration.numberOfSubsribers) {
				createSubVm("java", "-cp", classpath, "Listener",
						Configuration.destination_listener, "Listener"
								+ listenerCounter, "all");
			} else {
				createSubVm("java", "-cp", classpath, "Listener",
						Configuration.destination_listener, "Listener"
								+ listenerCounter, "1");
			}

			out.writeln("Listener number " + listenerCounter + " started.");
		}

		for (int publisherCounter = 1; publisherCounter <= Configuration.numberOfPublishers; publisherCounter++) {
			createSubVm("java", "-cp", classpath, "ListenerPublisher", ""
					+ publisherCounter);
			System.out.println("Publisher number " + publisherCounter
					+ " started.");
		}

		Thread.sleep(10000);

		createSubVm("java", "-cp", classpath, "PublishersStarter");

		out.writeln("PublisherStarter started.");
		out.writeln("TestStarter finished.");
		out.close();
	}

	public static void createSubVm(String... args) throws IOException {
		// ProcessBuilder builder = new ProcessBuilder("java", "-cp", classpath,
		// args[0], args[1]);
		ProcessBuilder builder = new ProcessBuilder(args);
		builder.redirectErrorStream(true);

		final Process p = builder.start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				String line = null;
				final BufferedReader reader = new BufferedReader(
						new InputStreamReader(p.getInputStream()));
				try {
					while ((line = reader.readLine()) != null) {
						System.out.println(line);
					}
					p.waitFor();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					try {
						reader.close();
					} catch (IOException e) {
						/** ignore on close */
					}
				}
			}
		}).start();
	}
}
