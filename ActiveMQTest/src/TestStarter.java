import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import javax.jms.JMSException;

public class TestStarter implements ListenerStarter {

  private AtomicInteger listenerCounter;
  private Listener[] listeners;
  private Thread[] publishers;

  public static void main(String[] args) {
    TestStarter starter = new TestStarter();
    try {
      starter.doTheJob();
    } catch (IOException | JMSException | InterruptedException e) {
      System.out.println("Something went wrong");
      e.printStackTrace();
    }
  }

  private void doTheJob() throws IOException, JMSException, InterruptedException {

    System.out.println("TestStarter started.");

    listenerCounter = new AtomicInteger();
    listeners = new Listener[Configuration.numberOfSubsribers];

    for (int listenerCounter = 1; listenerCounter <= Configuration.numberOfSubsribers; listenerCounter++) {
      // FIXME - start one with filter
      listeners[listenerCounter - 1] = new Listener(Configuration.destination_listener, "Listener" + listenerCounter, "all", this);
      System.out.println("Listener number " + listenerCounter + " started.");
    }

    publishers = new Thread[Configuration.numberOfPublishers];
    for (int publisherCounter = 1; publisherCounter <= Configuration.numberOfPublishers; publisherCounter++) {
      publishers[publisherCounter - 1] = new Thread(new Publisher("" + publisherCounter));
      //      publishers[publisherCounter - 1].start();
      System.out.println("Publisher number " + publisherCounter + " initialized.");
    }

    for (Thread pub : publishers) {
      pub.start();
    }

    checkAndClose();
    System.out.println("TestStarter normally finished.");

  }

  private void checkAndClose() throws InterruptedException, JMSException {
    boolean finished = false;
    Object o = new Object();
    while (!finished) {
      
      synchronized (o) {
        o.wait(1000);
      }

      if (listenerCounter.compareAndSet(Configuration.numberOfSubsribers, Configuration.numberOfSubsribers)) {
        for (Listener listener : listeners) {
          listener.closeConnection();
        }
        finished = true;
        System.out.println("Listeners connections closed.");
      }
    }
  }

  @Override
  public void incremenetListenerFinished() throws JMSException {
    listenerCounter.getAndIncrement();
  }

}
