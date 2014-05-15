
package com.spielwiese.generics;

import org.apache.log4j.Logger;

public class myMessageHandler extends MessageHandler {

  static Logger logger = Logger.getLogger(myMessageHandler.class);

  @Override
  public boolean handle(Message mesg) {
    return false;
  }

}

//---------------------------- Revision History ----------------------------
//$Log$
//
