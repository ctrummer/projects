
package com.spielwiese.generics;

import org.apache.log4j.Logger;

public abstract class MessageHandler<t extends Message> {

  static Logger logger = Logger.getLogger(MessageHandler.class);

  public abstract boolean handle(t mesg);

}

//---------------------------- Revision History ----------------------------
//$Log$
//
