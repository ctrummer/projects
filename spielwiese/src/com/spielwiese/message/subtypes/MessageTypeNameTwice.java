
package com.spielwiese.message.subtypes;

import com.spp.custom.common.messaging.boss.IMessageType;
import com.spp.custom.common.messaging.boss.MessageType;
import com.spp.custom.common.messaging.boss.MessageTypesNew;

public class MessageTypeNameTwice extends MessageTypesNew {

  public static final IMessageType MessageTypeNameTwice = new MessageType(0x20004, "MessageTypeNameTwice");

  static {
    MessageTypesNew.checkMessageTypesConstraints();
  }

}

//---------------------------- Revision History ----------------------------
//$Log$
//
