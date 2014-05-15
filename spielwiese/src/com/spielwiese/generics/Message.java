
package com.spielwiese.generics;

import org.apache.log4j.Logger;

public abstract class Message {

  static Logger logger = Logger.getLogger(Message.class);

  private String MessageString;

  public String getMessageString() {
    return MessageString;
  }

  public void setMessageString(String messageString) {
    MessageString = messageString;
  }

}

//---------------------------- Revision History ----------------------------
//$Log$
//
