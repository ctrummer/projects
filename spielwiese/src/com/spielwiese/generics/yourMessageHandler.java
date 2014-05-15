
package com.spielwiese.generics;

import org.apache.log4j.Logger;

public class yourMessageHandler<t extends Message> extends MessageHandler<t> {

  static Logger logger = Logger.getLogger(yourMessageHandler.class);

  @Override
  public boolean handle(SubMessageA mesg) {
    return false;
  }

  @Override
  public boolean handle(t mesg) {
    return false;
  }

}

//---------------------------- Revision History ----------------------------
//$Log$
//
