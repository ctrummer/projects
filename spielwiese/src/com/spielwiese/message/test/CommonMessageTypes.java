
package com.spielwiese.message.test;

public class CommonMessageTypes {

  public static final IMessageType AuftragsChangeMessage = new MessageType(0x1011, "AuftragsChangeMessage");
  public static final IMessageType AuftragsMessage = new MessageType(0x1010, "AuftragsMessage");
  public static final IMessageType AuftStartAckMessage = new MessageType(0x1002, "AuftStartAckMessage");

}

//---------------------------- Revision History ----------------------------
//$Log$
//
