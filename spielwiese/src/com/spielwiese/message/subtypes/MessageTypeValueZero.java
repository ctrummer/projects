
package com.spielwiese.message.subtypes;

import com.spp.custom.common.messaging.boss.IMessageType;
import com.spp.custom.common.messaging.boss.MessageType;
import com.spp.custom.common.messaging.boss.MessageTypesNew;

public class MessageTypeValueZero extends MessageTypesNew {

  public static final IMessageType MessageTypeValueZero = new MessageType(0x0, "MessageTypeValueZero");

  static {
    MessageTypesNew.checkMessageTypesConstraints();
  }

}

//---------------------------- Revision History ----------------------------
//$Log$
//
