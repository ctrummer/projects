
package com.spielwiese.message.test;


public class MessageType implements IMessageType {

  //  private static final ILogger LOG = LogHelper.getLogger();

  private Integer messageTypeIntRep;
  private String messageTypeName;

  public MessageType(int messageTypeIntRep, String messageTypeName) {
    super();
    this.messageTypeIntRep = Integer.valueOf(messageTypeIntRep);
    this.messageTypeName = messageTypeName;
  }

  @Override
  public Integer getMessageTypeIntRep() {
    return messageTypeIntRep;
  }

  @Override
  public String getMessageTypeName() {
    return messageTypeName;
  }

}

//---------------------------- Revision History ----------------------------
//$Log$
//
