
package com.spielwiese.generics.handlers;

import org.apache.log4j.Logger;

import com.spielwiese.generics.messages.MessageA;

public class MessageCHandler<T extends MessageA> extends MessageAHandler<T> {

  static Logger logger = Logger.getLogger(MessageCHandler.class);
}

//---------------------------- Revision History ----------------------------
//$Log$
//
