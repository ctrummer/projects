
package com.spielwiese.message.subtypes;

import com.spielwiese.message.IMessageType;
import com.spielwiese.message.MessageType;
import com.spielwiese.message.MessageTypesNew;

public class MessageTypeValueZero extends MessageTypesNew {

  public static final IMessageType MessageTypeValueZero = new MessageType(0x0, "MessageTypeValueZero");

  static {
    MessageTypesNew.checkMessageTypesConstraints();
  }

}

//---------------------------- Revision History ----------------------------
//$Log$
//
