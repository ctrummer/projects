
package com.spielwiese.message.subtypes;

import com.spp.custom.common.messaging.boss.IMessageType;
import com.spp.custom.common.messaging.boss.MessageType;
import com.spp.custom.common.messaging.boss.MessageTypesNew;

public class MessageTypeValueTwice extends MessageTypesNew {

  public static final IMessageType MessageTypeValueTwice = new MessageType(0x200001, "MessageTypeValueTwice");

  static {
    MessageTypesNew.checkMessageTypesConstraints();
  }

}

//---------------------------- Revision History ----------------------------
//$Log$
//
