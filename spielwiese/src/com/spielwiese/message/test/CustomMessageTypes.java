
package com.spielwiese.message.test;

public class CustomMessageTypes {

  // Commons
  //  public static final IMessageType AuftragsChangeMessage = new MessageType(0x1011, "AuftragsChangeMessage");
  //  public static final IMessageType AuftragsMessage = new MessageType(0x1010, "AuftragsMessage");
  //  public static final IMessageType AuftStartAckMessage = new MessageType(0x1002, "AuftStartAckMessage");

  public static final IMessageType MessageTypeNameEmpty = new MessageType(0x200003, "");
  public static final IMessageType MessageTypeNameNull = new MessageType(0x200002, null);
  public static final IMessageType MessageTypeNameTwice = new MessageType(0x20004, "AuftStartAckMessage");
  public static final IMessageType MessageTypeValueTwice = new MessageType(0x1011, "MessageTypeValueTwice");
  public static final IMessageType MessageTypeValueZero = new MessageType(0x0, "MessageTypeValueZero");

}

//---------------------------- Revision History ----------------------------
//$Log$
//
