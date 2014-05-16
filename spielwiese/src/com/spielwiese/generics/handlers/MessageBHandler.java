
package com.spielwiese.generics.handlers;

import org.apache.log4j.Logger;

import com.spielwiese.generics.messages.MessageA;
import com.spielwiese.generics.messages.MessageB;

public class MessageBHandler<T extends MessageB> implements IMessageHandler<T> {

  static Logger logger = Logger.getLogger(MessageBHandler.class);

  public boolean handle(MessageA mesg) {
    return false;
  }

  @Override
  public boolean handle(T message) {
    return false;
  }

}
