
package com.spielwiese.message.subtypes;

import com.spielwiese.message.IMessageType;
import com.spielwiese.message.MessageType;
import com.spielwiese.message.MessageTypesNew;

public class MessageTypeValueTwice extends MessageTypesNew {

  public static final IMessageType MessageTypeValueTwice = new MessageType(0x200001, "MessageTypeValueTwice");

  static {
    MessageTypesNew.checkMessageTypesConstraints();
  }

}

//---------------------------- Revision History ----------------------------
//$Log$
//
