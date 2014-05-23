
package com.spielwiese.message.subtypes;

import com.spp.custom.common.messaging.boss.IMessageType;
import com.spp.custom.common.messaging.boss.MessageType;
import com.spp.custom.common.messaging.boss.MessageTypesNew;

public class MessageTypeNameEmpty extends MessageTypesNew {

  public static final IMessageType MessageTypeNameEmpty = new MessageType(0x200003, "");

  static {
    checkMessageTypesConstraints();
  }

}

//---------------------------- Revision History ----------------------------
//$Log$
//
