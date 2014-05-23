
package com.spielwiese.message.subtypes;

import com.spp.custom.common.messaging.boss.IMessageType;
import com.spp.custom.common.messaging.boss.MessageType;
import com.spp.custom.common.messaging.boss.MessageTypesNew;

public class MessageTypeNameNull extends MessageTypesNew {

  public static final IMessageType MessageTypeNameNull = new MessageType(0x200002, null);

  static {
    MessageTypesNew.checkMessageTypesConstraints();
  }
}

//---------------------------- Revision History ----------------------------
//$Log$
//
