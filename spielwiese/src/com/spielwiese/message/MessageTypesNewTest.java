
package com.spielwiese.message;

import org.junit.Test;

import com.spp.custom.common.messaging.boss.messagetypes.MessageTypeNameEmpty;
import com.spp.custom.common.messaging.boss.messagetypes.MessageTypeNameNull;
import com.spp.custom.common.messaging.boss.messagetypes.MessageTypeNameTwice;
import com.spp.custom.common.messaging.boss.messagetypes.MessageTypeValueZero;
import com.ssp.common.logging.ILogger;
import com.ssp.common.logging.LogHelper;

public class MessageTypesNewTest {

  private static final ILogger LOG = LogHelper.getLogger();

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
