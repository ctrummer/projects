
package com.spielwiese.message;

import java.lang.reflect.Field;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Test;

import com.spielwiese.message.enumsolution.MessageTypes;

public class CustomMessageTypesTest {

  static Logger LOG = Logger.getLogger(CustomMessageTypesTest.class);

  @AfterClass
  public static void tearDownAfterClass() throws Exception {
  }

  @Test
  public void testName() throws Exception {
    MessageTypes tmp = MessageTypes.AklInsertDestination;
    MessageTypes.values();
  }

  static {
    //    checkMessageTypesConstraints();
  }

  protected static final void checkMessageTypesConstraints(Class MessageTypesClass) {
    Field[] declaredFields = MessageTypesClass.getDeclaredFields();
    for (int index = 0; index < declaredFields.length; index++) {
      Field field = declaredFields[index];
      if (IMessageType.class.isAssignableFrom(field.getType())) {
        IMessageType messageType;
        try {
          messageType = (IMessageType) field.get(null);
          if (!checkInLookup(messageType)) {
            addToLookUp(field.getName(), messageType);
          }
        } catch (IllegalAccessException e) {
          LOG.error("Static fields of MessagetType and its sub-types should be accessable - we have to handle the exception.");
          throw new RuntimeException(e);
        }
      }
    }
  }

  private static boolean checkInLookup(IMessageType messageType) {
    return (lookup.get(messageType.getMessageTypeIntRep()).equalsIgnoreCase(messageType.getMessageTypeName()));
  }

  private static final void addToLookUp(String fieldName, IMessageType messageType) {
    if (messageType.getMessageTypeIntRep().intValue() == 0x0) {
      String errMess = "Field " + fieldName + " has zero as representation integer for the message type.";
      IllegalArgumentException exp = new IllegalArgumentException(errMess);
      LOG.error(errMess, exp);
      throw exp;
    }

    if (messageType.getMessageTypeName() == null || messageType.getMessageTypeName().equalsIgnoreCase("")) {
      String errMess = "Field " + fieldName + " have no name for the message type";
      IllegalArgumentException exp = new IllegalArgumentException(errMess);
      LOG.error(errMess, exp);
      throw exp;
    }

    if (lookup.values().contains(messageType.getMessageTypeName())) {
      String errMess = "Field " + fieldName + " message type name allready in use";
      IllegalStateException exp = new IllegalStateException(errMess);
      LOG.error(errMess, exp);
      throw exp;
    }

    if (lookup.get(messageType.getMessageTypeIntRep()) == null) {
      lookup.put(messageType.getMessageTypeIntRep(), messageType.getMessageTypeName());
    } else {
      String errMess = "Field " + fieldName + " message type integer representation allready in use.";
      IllegalStateException exp = new IllegalStateException(errMess);
      LOG.error(errMess, exp);
      throw exp;
    }

  }

}

//---------------------------- Revision History ----------------------------
//$Log$
//
