
package com.spielwiese.generics.messages;

import org.apache.log4j.Logger;

public abstract class Message implements IMessage {

  static Logger logger = Logger.getLogger(Message.class);

  private String messageName;

  public String getMessageName() {
    return messageName;
  }

  public void setMessageName(String messageName) {
    this.messageName = messageName;
  }

}
