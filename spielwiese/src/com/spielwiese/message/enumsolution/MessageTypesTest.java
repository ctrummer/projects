
package com.spielwiese.message.enumsolution;

import junit.framework.TestCase;

import org.junit.Test;

/**
 * Copyright 2011 SSI Schaefer PEEM GmbH. All Rights reserved. <br />
 * <br />
 * Basic tests for the MessageTypes enumeration. <br />
 */
public class MessageTypesTest extends TestCase {

  /** The numerical message codes to be used in these test cases. */
  private static int FAULTY_MESSAGETYPE_CODE = -0x1;
  private static int KNOWN_MESSAGETYPE_CODE = MessageTypes.WmsOrderBoxtraceMessage.getMsgTypeId();
  private static int UNKNOWN_MESSAGETYPE_CODE = 0x99998;

  /**
   * The message type to be used in these test cases. Needs to match the code in
   * KNOWN_MESSAGETYPE_CODE.
   */
  private static MessageTypes KNOWN_MESSAGETYPE = MessageTypes.WmsOrderBoxtraceMessage;

  /** {@inheritDoc} */
  @Override
  protected void setUp() throws Exception {
    super.setUp();
  }

  /** {@inheritDoc} */
  @Override
  protected void tearDown() throws Exception {
    super.tearDown();
  }

  @Test
  public void testTypeIsUnique() throws Exception {
    new BossMessageRegistry();
  }

  /**
   * Test if 0x0 message type can be found in enumeration. 0x0 should not be a final/usable message
   * type.
   */
  public void testFaultyMessageType() {
    assertFalse(MessageTypes.hasMsgType(FAULTY_MESSAGETYPE_CODE));
  }

  /**
   * Test if known message type can be found in enumeration.
   */
  public void testAvailableMessageType() {
    assertTrue(MessageTypes.hasMsgType(KNOWN_MESSAGETYPE_CODE));
  }

  /**
   * Test if unknown message type can be found in enumeration.
   */
  public void testNotAvailableMessageType() {
    assertTrue(!MessageTypes.hasMsgType(UNKNOWN_MESSAGETYPE_CODE));
  }

  public void testGetMessageTypeFromCode() {
    assertEquals(KNOWN_MESSAGETYPE, MessageTypes.get(KNOWN_MESSAGETYPE_CODE));
  }
}

//---------------------------- Revision History ----------------------------
//$Log: MessageTypesTest.java,v $
//Revision 1.1  2014/05/15 11:19:12  tch
//cleanup: Moved boss tests to correct package.
//jira: REQ-142
//
//Revision 1.9  2014/03/05 07:11:19  wni
//cleanup: minor code cleanup to solve open task in jenkins
//jira: none
//
//Revision 1.8  2014/01/22 08:55:36  m.kegele
//cleanup: fixed test, string message has same code as the unknown message specified in the test, changed the code for the unknown type
//jira: GUI-231
//
//Revision 1.7  2012/11/01 12:08:45  wni
//cleanup: minor changes done with regex exchangement
//- remove cvs version field
//- minor changes / additions in javadoc
//jira: COMMONS-149
//
//Revision 1.6  2012/05/21 13:33:47  marktl
//refactor: wip
//jira: COMMONS-82
//Committed by: amarktl
//
//Revision 1.5  2012/04/22 06:32:38  wni
//Moved all packages except persistence and wms in commons project into common package.
//Adapted imports of usages and changed references in config files. --> COMMONS-36
//
//Revision 1.4  2012/04/16 16:34:28  haas
//Changed FAULTY_MESSAGETYPE_CODE to -1 because 0x0 is a valid message type
//
//Revision 1.3  2011/12/24 15:49:12  wni
//Minor cleanup.
//
//Revision 1.2  2011/03/17 12:54:31  c.joelly
//Removed import of MessageTypes due to move of the enumeration.
//Committed by: cjo
//
//Revision 1.1  2011/02/18 11:29:00  c.joelly
//Added basic unit test for MessageTypes enumeration.
//Committed by: cjo
//
//