
package com.spielwiese.message.test;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.jfree.util.Log;

import com.ssp.common.logging.ILogger;
import com.ssp.common.logging.LogHelper;

public class MessageTypesNew {

  /** The Constant LOG. */
  final static ILogger LOG = LogHelper.getLogger();

  public static final IMessageType CustomMessageType = new MessageType(0x200001, "CustomMessageType");
  //public static final IMessageType MessageValueNull = new MessageType(null, "MessageValueNull");

  public static final IMessageType AuftragsChangeMessage = new MessageType(0x1011, "AuftragsChangeMessage");
  public static final IMessageType AuftragsMessage = new MessageType(0x1010, "AuftragsMessage");
  public static final IMessageType AuftStartAckMessage = new MessageType(0x1002, "AuftStartAckMessage");

  private static final Map<Integer, String> lookup = new HashMap<Integer, String>();

  static {
    checkMessageTypesConstraints();
  }

  protected static final void checkMessageTypesConstraints() {
    Field[] declaredFields = MessageTypesNew.class.getDeclaredFields();
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
          Log.error("Static fields of MessagetType and its sub-types should be accessable - we have to handle the exception.");
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
      Log.error(errMess, exp);
      throw exp;
    }

  }
}

//---------------------------- Revision History ----------------------------
//$Log$
//
