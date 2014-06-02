
package com.spielwiese.message.test;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.spielwiese.message.IMessageType;
import com.spielwiese.message.subtypes.MessageTypeNameEmpty;
import com.spielwiese.message.subtypes.MessageTypeNameNull;
import com.spielwiese.message.subtypes.MessageTypeNameTwice;
import com.spielwiese.message.subtypes.MessageTypeValueZero;

public class MessageTypesNewTest {

  static Logger LOG = Logger.getLogger(MessageTypesNewTest.class);

  @Test(expected = ExceptionInInitializerError.class)
  @SuppressWarnings("unused")
  public void testNameEmpty() {
    IMessageType type = MessageTypeNameEmpty.MessageTypeNameEmpty;
  }

  @Test(expected = ExceptionInInitializerError.class)
  @SuppressWarnings("unused")
  public void testNameNull() {
    IMessageType type = MessageTypeNameNull.MessageTypeNameNull;
  }

  @Test(expected = ExceptionInInitializerError.class)
  @SuppressWarnings("unused")
  public void testNameTwice() {
    IMessageType type = MessageTypeNameTwice.MessageTypeNameTwice;
  }

  @Test(expected = ExceptionInInitializerError.class)
  @SuppressWarnings("unused")
  public void testValueTwice() {
    IMessageType type = MessageTypeValueZero.MessageTypeValueZero;
  }

  // TODO add the good case!!!

}

//---------------------------- Revision History ----------------------------
//$Log$
//
