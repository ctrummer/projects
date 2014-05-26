
package com.spielwiese.message.subtypes;

import com.spielwiese.message.IMessageType;
import com.spielwiese.message.MessageType;
import com.spielwiese.message.MessageTypesNew;

public class MessageTypeNameNull extends MessageTypesNew {

  public static final IMessageType MessageTypeNameNull = new MessageType(0x200002, null);

  static {
    MessageTypesNew.checkMessageTypesConstraints();
  }
}

//---------------------------- Revision History ----------------------------
//$Log$
//
