
package com.spielwiese.message.subtypes;

import com.spielwiese.message.IMessageType;
import com.spielwiese.message.MessageType;
import com.spielwiese.message.MessageTypesNew;

public class MessageTypeNameTwice extends MessageTypesNew {

  public static final IMessageType MessageTypeNameTwice = new MessageType(0x20004, "MessageTypeNameTwice");

  static {
    MessageTypesNew.checkMessageTypesConstraints();
  }

}

//---------------------------- Revision History ----------------------------
//$Log$
//
