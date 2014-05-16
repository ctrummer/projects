
package com.spielwiese.generics.messages;

import org.apache.log4j.Logger;

public class MessageA<s extends String, r extends String, p extends String> extends Message {

  static Logger logger = Logger.getLogger(MessageA.class);

  @Override
  public Object getSender() {
    return null;
  }

  @Override
  public Object getRecaeiver() {
    return null;
  }

  @Override
  public Object getPayload() {
    return null;
  }

  @Override
  public void setSender(Object sender) {
  }

  @Override
  public void setReceiver(Object receiver) {
  }

  @Override
  public void setPayLoad(Object payload) {
  }

}

//---------------------------- Revision History ----------------------------
//$Log$
//
