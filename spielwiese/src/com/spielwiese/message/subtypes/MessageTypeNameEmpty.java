
package com.spielwiese.message.subtypes;

import com.spielwiese.message.IMessageType;
import com.spielwiese.message.MessageType;
import com.spielwiese.message.MessageTypesNew;

public class MessageTypeNameEmpty extends MessageTypesNew {

  public static final IMessageType MessageTypeNameEmpty = new MessageType(0x200003, "");

  static {
    checkMessageTypesConstraints();
  }

}

//---------------------------- Revision History ----------------------------
//$Log$
//
