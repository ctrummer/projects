
package com.spielwiese.message.enumsolution;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Copyright 2011 SSI Schaefer PEEM GmbH. All Rights reserved. <br />
 * <br />
 * $Id: MessageTypes.java,v 1.33 2014/05/12 15:06:59 a.rait Exp $ <br />
 * <br />
 * Enumeration which lists all known BOSS message types and their numerical id. <br />
 * 
 * @author cjo
 * @version $Revision: 1.33 $
 */

public enum MessageTypes {
  @Deprecated
  AklInsertDestination(0x4506),
  @Deprecated
  AklInsertRequest(0x4505),
  @Deprecated
  AKLInternMessage(0x8203),
  @Deprecated
  AklLocationUpdate(0x4507),
  @Deprecated
  AklMovementChange(0x4504),
  @Deprecated
  AklMovementError(0x4503),
  @Deprecated
  AklMovementOrder(0x4501),
  @Deprecated
  AklMovementReply(0x4502),
  @Deprecated
  AklReplenishment(0x4508),
  @Deprecated
  AKLRequestMessage(0x8201),
  @Deprecated
  AKLResponseMessage(0x8202),
  @Deprecated
  AklRestartMsg(0x4500),
  AuftragsChangeMessage(0x1011),
  AuftragsMessage(0x1010),
  AuftStartAckMessage(0x1002),
  AuftStartMessage(0x1001),
  AusBerStatusMessage(0x8402), // used in "Standard"? 
  AusBerStatusMessage_2(0x8404),
  AusBerStatusMessage_3(0x8406),
  AusBerStatusRequestMessage(0x8401), // used in "Standard"?
  AusBerStatusRequestMessage_2(0x8403),
  AusBerStatusRequestMessage_3(0x8405),
  AutpriAckMessage(0x3002),
  AutpriMessage(0x3001),
  BatchTraceMessage(0x2502),
  BBRequestMessage(0x8101),
  BBResponseMessage(0x8102),
  BehaelterAnMessage(0x1102),
  BehaelterRueckMessage(0x1101),
  BoxtraceMessage(0x1203),
  CCReturnChannelStateMessage(0x30038),
  ConfigMessage(0x4),
  ConvertRequestMessage(0x0002),
  ConvertResponseMessage(0x0003),
  DBAnfrageMessage(0x2002),
  DBAnmeldAckMessage(0x2021),
  DBAnmeldMessage(0x2020),
  DBAntwortMessage(0x2001),
  ExekutorMessage(0x7101),
  FileRequestMessage(0x7001),
  FileResponseMessage(0x7002),
  GenericNodeAnnMessage(0x1300),
  GenericNodeRespMessage(0x1301),
  GenericScannerReply(0x10073),
  GUIControlMessage(0x7700),
  HcomFromhostMessage(0x4001),
  HcomTohostMessage(0x4002),
  InfoMessage(0x4713),
  LCTestModeMessage(0x5101),
  LicenseMessage(0x5102),
  LogCallbackRequestMessage(0x5006),
  LogCfgControlMessage(0x5004),
  LogCfgRequestMessage(0x5002),
  LogCfgResponseMessage(0x5003),
  LogConfigurationMessage(0x9),
  TracingConfigurationMessage(0x91),
  LogMessage(0x5001),
  LoopStatusAckMessage(0x6102),
  LoggedUserMessage(0x6112),
  RequestLoggedUserMessage(0x6113),
  RequestPPUserMessage(0x6114),
  MBIDataContainerMsg(0x30054),
  MBISCSOrderResponseMsg(0x30052),
  MBISCSStatusResponseMsg(0x30050),
  MBISMCOrderResponseMsg(0x30053),
  MBISMCStatusResponseMsg(0x30051),
  MCIPCSDispoOrderRequestMsg(0x30021),
  MCIPCSDispoOrderResponseMsg(0x30022),
  MDCAisleMessage(0x31023),
  PdcSessionMessage(0x40001),
  PdcNotificationMessage(0x40002),
  MotMessage(0x1500),
  MotOutMessage(0x1501),
  MovementOrderFinishedMessage(0x11702),
  MovementOrderMessage(0x11501),
  MovementOrderQueryMessage(0x11801),
  MovementOrderStatusMessage(0x11802),
  MovementOrderCancelledMessage(0x11803),
  CheckProductAssignmentMessage(0x11804),
  RequestInventoryMovementMessage(0x11805),
  MfsStationResponseMessage(0x11806),
  MovementOrderResponseMessage(0x11701),
  MultipleDeviceMessage(0x30024),
  NGKPRueckMessage(0x1400),
  NoPendingFullcaseMessage(0x11665),
  NotbetriebNotifyMessage(0x5200),
  PACOASRSTransportRequestMsg(0x30023),
  PACOCCReturnChannelStateMsg(0x30025),
  PACOCCStatusQueryMsg(0x30016),
  PACOCCStatusResponseMsg(0x30017),
  PACOCCTransportRequestMsg(0x30020),
  PACOCCTransportResponseMsg(0x30026),
  PACODeviceLockMsg(0x30005),
  PACOLocationLockMsg(0x30006),
  PACODeviceModeMsg(0x30034),
  PACODeviceModeReplyMsg(0x30035),
  PACOOCReservationSimulationMsg(0x30028),
  PACOOCTransportSimulationMsg(0x30027),
  PACOOrderControllerRemoteControlMsg(0x30013),
  PACOOrderControllerStatusRequestMsg(0x30009),
  PACOOrderControllerStatusResponseMsg(0x30010),
  PACOPCSAdapterStatusRequestMsg(0x30029),
  PACOPCSAdapterStatusResponseMsg(0x30032),
  PACOPickplaceStatusMsg(0x30001),
  PACOPTTStatusRequestMsg(0x30011),
  PACOPTTStatusResponseMsg(0x30012),
  PACOSCStatusRequestMsg(0x30098),
  PACOSCStatusResponseMsg(0x30099),
  PACOCompactionContRequestMessage(0x30100),
  PACOCompactionContResponseMessage(0x30101),
  PACOSCSCleanOutMsg(0x30008),
  PACOSCSStatusRequestMsg(0x30002),
  PACOSCSStatusResponseMsg(0x30003),
  PACOSMCCleanOutMsg(0x30018),
  PACOHARPCleanLaneMsg(0x30042),
  PACOSMCInventoryResponseMsg(0x30031),
  PACOSMCPickingResponseMsg(0x30030),
  PACOSMCResetMsg(0x30019),
  PACOSMCStatusRequestMsg(0x30014),
  PACOSMCStatusResponseMsg(0x30015),
  PACOTubCancelMsg(0x30007),
  PACOTransportCancelMsg(0x30033),
  PACOPseudoToteUpdateMsg(0x30036),
  PACODirectStockModificationStartMsg(0x30037),
  PACOPCSTestGUIMessage(0x30039),
  PACOControlTourMsg(0x30040),
  PACOShippingBufferModeMsg(0x30041),
  PBLAnmeldMessage(0x6001),
  PBLRequestMessage(0x5201),
  PBLResponseMessage(0x5202),
  PBLRueckmeldMessage(0x6002),
  PCXControlMsg(0x9001),
  PematChannelLockMessage(0x1105),
  PickingClientMessage(0x1601),
  PickingMessage(0x1600),
  //  TODO should 0x0 be included or not in this enumeration? 
  //  I think no, but your mileage may vary ;)
  //  To avoid confusing warnings in the logs i added it to this enumeration!
  PlistMessage(0x0),
  PrinterAckMessage(0x3102),
  PrinterRequestMessage(0x8005),
  PrinterResponseMessage(0x8006),
  PrintRequestMessage(0x8001),
  ProdChangeMessage(0x5),
  PTLAddressingMessage(0x5100),
  QuitMessage(0x1),
  RbgMenuMessage(0x5200),
  RequestMessage(0x4711),
  ResponseMessage(0x4712),
  RoutingQueryMessage(0x1983),
  RoutingQueryResponseMessage(0x1984),
  ScanMessage(0x5203),
  SCSGoodsInMessage(0x12601),
  SCSGrScanMsg(0x10082),
  SCSPPLoginMsg(0x10091),
  SCSResetMsg(0x10666),
  SCSStatusLineResponseMsg(0x10081),
  SlotReservationMessage(0x7356),
  SoftPTTPollMessage(0x7355),
  StartServerMessage(0x31001),
  StartServerSteuerMessage(0x31002), // TODO re-added, because of reference in StartServerSteuerMessage.java
  StartServerParameterMsg(0x31003),
  StartServerPseudoToteStartMsg(0x31004),
  StateResponseMessage(0x5005),
  StationReloadMessage(0xCAFE),
  StationsAnMessage(0x1104),
  StationsRueckMessage(0x1103),
  StatistikMessage(0x1201),
  StatusAnfrageMessage(0x1202),
  StopConveyMessage(0x7),
  SubscribeMessage(0x7353),
  SwitchPanelMessage(0x1602),
  StringMessage(0x99999),
  SteuerLampMessage(0x5204),
  // entries for the SystemMessage. 
  // The SystemMessage uses more than one msgType code.
  SystemMessage1(0x10000001),
  SystemMessage10(0x10000010),
  SystemMessage2(0x10000002),
  SystemMessage3(0x10000003),
  SystemMessage4(0x10000004),
  SystemMessage5(0x10000005),
  SystemMessage6(0x10000006),
  SystemMessage7(0x10000007),
  SystemMessage8(0x10000008),
  SystemMessage9(0x10000009),
  SystemMessageA(0x1000000a),
  SystemMessageB(0x1000000b),
  SystemMessageC(0x1000000c),
  SystemMessageD(0x1000000d),
  TermUserInfo(0x1303),
  TimeRequestMessage(0x9001),
  TimeResponseMessage(0x9002),
  // message sent (usually from Steuer to Mfs service) when a TOTE is diverted to a station
  ToteMovementResponseMessage(0x11807),
  UnsubscribeMessage(0x7354),
  VisuControlMessage(0x1250),
  WmsCompactionMessage(0x12401),
  WmsEmptyTuRequestMessage(0x12701),
  @Deprecated
  WmsGoodsInMessage(0x11101),
  WmsInventoryMessage(0x12301),
  WmsManagedEmptyTuRequestMessage(0x12702),
  WmsOrderBoxtraceMessage(0x12703),
  WmsOrderEventMessage(0x12501),
  // message sent to a routing service (mfs_service) to initiate a routing algorithm for an order/TOTE.
  WmsOrderRoutingMessage(0x12003),
  WmsOrderServiceAbortOrder(0x12805),
  WmsOrderServiceAddStationMessage(0x12802),
  WmsOrderServiceCloseStationMessage(0x12804),
  WmsOrderServiceDeleteStationMessage(0x12803),
  WmsOrderSplitRequestMessage(0x12801),
  WmsOrderVerifierToteChangedMessage(0x12705),
  WmsOrderVerifierTraceMessage(0x12704),
  WmsOrderVerifierAnnouncementMessage(0x12706),
  WmsPickMessage(0x11201),
  WmsProductMoveMessage(0x11202),
  WmsSlottingMessage(0x11203),
  WmsReplenishmentCancelMessage(0x11403),
  WmsReplenishmentFulfillmentMessage(0x11402),
  WmsReplenishmentRequestMessage(0x11401),
  WMSSetOrderStateMessage(0x12002),
  WmsStockCorrectionMessage(0x12101),
  WMSToteStartMessage(0x12001),
  WmsTriggerMovementOrderCreationMessage(0x11808),
  WmsTransportUnitNestedMessage(0x11809),
  ToteRoutingMessage(0x12901),
  SupervisorMessage(0x12050);

  /**
   * lookup provides a simple container for reverse lookups so that we are able to check if a
   * numerical message type id is already known in this enumeration.
   */
  private static final Map<Integer, MessageTypes> lookup = new HashMap<Integer, MessageTypes>();

  /**
   * Build reverse lookup container out of the enumeration elements.
   */
  static {
    for (MessageTypes s : EnumSet.allOf(MessageTypes.class)) {
      lookup.put(s.getMsgTypeId(), s);
    }
  }

  /**
   * Returns the MessageType which maps to a given numerical message type code.
   * 
   * @param msgTypeCode The message type code to be used to get the MessageType.
   * @return returns the MessageType
   */
  public static MessageTypes get(int msgTypeCode) {
    return lookup.get(msgTypeCode);
  }

  /**
   * Checks if a MessageType with the given numerical message type code is available in this
   * enumeration.
   * 
   * @param msgTypeCode The message type code to be used for the lookup.
   * @return returns true if the message type is available, false otherwise.
   */
  public static boolean hasMsgType(int msgTypeCode) {
    return lookup.get(msgTypeCode) != null ? true : false;
  }

  private int msgTypeId = 0x0;

  /**
   * Constructs a message type with the given numeric id.
   * 
   * @param msgTypeId The numeric message type id.
   */
  private MessageTypes(final int msgTypeId) {
    this.msgTypeId = msgTypeId;
  }

  /**
   * Returns the numeric id of the message type.
   * 
   * @return The numeric id of the message type.
   */
  public int getMsgTypeId() {
    return this.msgTypeId;
  }

}

//---------------------------- Revision History ----------------------------
//$Log: MessageTypes.java,v $
//Revision 1.33  2014/05/12 15:06:59  a.rait
//feature: patch developments  from BRANCH_ANTGRA
//jira: MFS-827
//
//Revision 1.32  2014/04/10 06:54:05  igoina
//feature: Added SteuerLampMessage
//jira: REQ-684
//committed by: igoina
//
//Revision 1.31  2014/04/07 14:51:57  j.duchemin
//feature: PDC User Notification
//jira: PDC-76
//
//Revision 1.30  2014/01/15 07:55:38  clagro
//improvement: added SoftPTTPollMessage
//jira: MFS-803
//
//Revision 1.29  2014/01/08 14:08:24  n.lauritsch
//refactor: replaced the plistMessage with the Stringmessage, because the msg contains xml
//jira: GUI-231
//
//Revision 1.28  2013/12/02 11:13:00  wsa
//feature: message triggered panel switching
//jira: GUI-231
//
//Revision 1.27  2013/11/19 15:43:39  maf
//cleanup: bringing sequencer for shipping buffer to head
//jira: MFS-743
//committed by: maf
//
//Revision 1.26  2013/11/15 09:40:57  walter
//cleanup: final move/rename
//jira: RD-251
//
//Revision 1.25  2013/10/28 13:08:39  igoina
//feature: Added PACOShippingBufferModeMsg, needed for Shipping Buffer TourRelease GUI
//jira: MFS-738
//committed by: igoina
//
//Revision 1.24  2013/08/13 08:53:15  a.rait
//feature: implementation of compaction controller
//jira: MFS-667
//
//Revision 1.23  2013/05/28 08:15:35  a.rait
//feature:  implementation of StatusController to consolidate paco controller status scripts.
//jira: MFS-601
//
//Revision 1.22  2013/05/17 06:57:57  eherf
//feature: added licensing based on IP to the menu and menuserver
//jira: GUI-201
//committed by: eherf
//
//Revision 1.21  2013/04/05 14:04:38  igoina
//refactor:Renamed PACOStartTourMsg to PACOControlTourMsg
//jira: MFS-525
//committed by:igoina
//
//Revision 1.20  2013/04/02 19:26:05  igoina
//feature: Added PACOStartTourMsg
//jira: MFS-525
//committed by:igoina
//
//Revision 1.19  2013/03/08 13:20:30  p.clement
//feature: added SupervisorMessage message type
//jira: GUI-178
//
//Revision 1.18  2013/02/06 16:36:47  fsp
//cleanup: removed tue architecture changes
//jira: COMMONS-160
//committed by: fsp
//
//Revision 1.17  2013/02/04 18:55:02  fsp
//cleanup: removed from branch natusa, because eclipse added it there and i dont know why, now tortoise should do it
//jira: COMMONS-160
//committed by: fsp
//
//Revision 1.16  2013/02/04 16:26:52  c.joelly
//bugfix: aligned message type names to message classes
//
//Revision 1.15  2013/02/04 15:30:30  fsp
//refactor: renamed
//jira: COMMONS-160
//committed by: fsp
//
//Revision 1.14  2013/01/22 14:24:31  fsp
//feature: 2 Messagetypes added for new Tote Route Messages
//jira: COMMONS-160
//committed by: fsp
//
//Revision 1.13  2012/11/30 08:40:55  maf
//feature: new Messagetype for TestGui to get the necessary information to the PCSAdapter
//jira: 010054H-767
//committed by: maf
//
//Revision 1.12  2012/11/20 14:42:55  c.joelly
//feature: added new message which signals a nesting of an transport unit into another transport unit.
//jira: 010054H-727
//
//Revision 1.11  2012/11/08 17:32:37  marktl
//feature: wip - add possibility to react on BossMessages to change LogLevel and tracing
//jira: REQ-135, COMMONS-150, COMMONS-141
//
//Revision 1.10  2012/09/29 08:16:22  wni
//feature: patched slotting related changes from HERMRI to HEAD
//jira: NEWPCS-550
//
//Revision 1.9  2012/09/27 14:23:43  p.clement
//feature: implemented WmsReplenishmentCancelMessage
//jira: REQ-168
//
//Revision 1.8  2012/09/25 10:22:23  m.wohlmuther
//feature: Introduced a own message type for the RequestPPUserMessage
//jira: MFS-367
//
//Revision 1.7  2012/09/18 11:04:21  m.wohlmuther
//feature: Added user logging for a PTT pickplace and the possibility to avoid picking without an logged in user at the Pickplace GUI.
//jira: MFS-367
//
//Revision 1.6  2012/08/07 10:04:05  marktl
//feature: added new message WmsOrderVerifierAnnouncementMessage / patched from GEHE UNNA Branch to HEAD
//jira: NEWPCS-578
//
//Revision 1.5  2012/07/10 10:41:19  clagro
//bugfix: changed type of CCReturnChannelStateMessage from 30026 to 30038 as
//PACOCCTransportResponseMsg already has 30026
//
//Revision 1.4  2012/06/25 09:51:40  c.joelly
//feature: new messages for mfs_service and its TOTE response and order routing capabilities.
//jira: 011098H-245, 011098H-365
//
//Revision 1.3  2012/06/20 07:28:31  m.wohlmuther
//[feature]: Added message type PACODirectStockModificationStartMsg(0x30037) for starting direct stock modification orders via a boss message instead of JMS_AQ
//jira: MFS-232
//
//Revision 1.2  2012/05/16 04:49:12  wni
//feature: added MfsStationResponseMessage
//jira: NEWPCS-438
//
//Revision 1.1  2012/04/22 06:28:59  wni
//Moved all packages except persistence and wms in commons project into common package.
//Adapted imports of usages and changed references in config files. --> COMMONS-36
//
//Revision 1.40  2012/03/12 17:23:26  c.joelly
//Re-added StartServerSteuerMessage(0x31002), because it is referenced in StartServerSteuerMessage.
//
//Revision 1.39  2012/03/12 13:16:56  m.wohlmuther
//Merged newly introduced messagetypes from BRANCH_VDBOLE.
//See Jira: MFS-88
//
//Revision 1.38  2012/03/07 12:51:59  m.wohlmuther
//Created infrastructure in PACO to receive and process PACODeviceMode messages and send PACODeviceModeReply messages.
//See Jira: MFS-120
//
//Revision 1.37  2012/02/09 06:30:00  wni
//Added RequestInventoryMovementMessage.
//
//Revision 1.36  2012/02/06 09:46:38  gsc
//added new message: PACOTransportCancelMsg (0x30033)
//Committed by: gsc
//
//Revision 1.35  2012/02/04 16:11:15  wni
//Added CheckProductAssignmentMessage.
//
//Revision 1.34  2012/02/02 13:45:05  wni
//Added MovementOrderCancelledMessage.
//
//Revision 1.33  2012/02/02 06:16:47  wni
//Added MovementOrderStatusMessage.
//
//Revision 1.32  2011/12/23 05:46:31  wni
//Refactored Message.java: moved core logic concerning boss write/read to new class BossMessageFactory.
//
//Revision 1.31  2011/12/21 20:12:52  wni
//Made WmsGoodsInMessage deprecated.
//
//Revision 1.30  2011/12/21 13:45:20  wni
//Added WmsOrderVerifierToteChangedMessage.
//
//Revision 1.29  2011/12/21 11:50:18  wni
//Made AKLMessageTypes deprecated.
//
//Revision 1.28  2011/12/19 04:38:55  wni
//Removed unused Galexis special MovementOrderActivationMessage since to-service or related
//business logic does not exist anymore and this message will not be used in future time.
//
//Revision 1.27  2011/12/19 04:36:41  wni
//Removed unused Galexis special WMSDispoRequestMessage since dispo-service or related
//business logic does not exist anymore and this message will not be used in future time.
//
//Revision 1.26  2011/12/19 04:35:28  wni
//Removed unused SDMONT special TransportOrderQueryMessage since related
//business logic does not exist anymore and this message will not be used in future time.
//
//Revision 1.25  2011/12/19 04:27:15  wni
//Removed unused Galexis special WMSBGResponseMessage since BGHandler or related
//business logic does not exist anymore and this message will not be used in future time.
//
//Revision 1.24  2011/12/19 04:25:48  wni
//Removed unused Galexis special WMSSkuMessage since SkuHandler or related
//business logic does not exist anymore and this message will not be used in future time.
//
//Revision 1.23  2011/12/19 03:16:07  wni
//Minor change.
//
//Revision 1.22  2011/12/15 13:27:12  eherf
//added the MBIDataContainerMsg for exchanging IMBIDAOS between different java processes
//Committed by: eherf
//
//Revision 1.21  2011/12/06 13:34:30  wni
//Added MovementOrderFinishedMessage.
//
//Revision 1.20  2011/12/06 12:21:14  eherf
//added messages to simulate SMCand SCS status und order responses
//Committed by: eherf
//
//Revision 1.19  2011/09/20 12:16:56  gsc
//added msgTypes PACOPCSAdapterStatusRequestMsg and PACOPCSAdapterStatusResponseMsg
//Committed by: gsc
//
//Revision 1.18  2011/09/19 07:59:55  c.joelly
//Added new action and message for a replenishment fulfillment feature.
//Committed by: cjo
//
//Revision 1.15.2.3  2011/09/16 14:44:47  c.joelly
//Added handling of transfer groups within the storage object tree. Transfer groups are needed for replenishment, but these are no overstock groups, thus they needs a special treatment. Added a new message so a new movement order can be triggered if a location becomes available on a transfer group after replenishment fulfillment.
//Committed by: cjo
//
//Revision 1.15.2.2  2011/09/15 14:04:30  a.perner
//added GenericScannerReply
//
//Revision 1.15.2.1  2011/09/14 11:47:42  tgoedl
//added PematChannelLockMessage type
//
//Revision 1.15  2011/09/01 07:46:32  maf
//add WmsOrderVerifierTraceMessage type
//
//Revision 1.14  2011/08/09 10:26:28  s.steginska
//add MDCAisleMessage(0x31023)
//
//Revision 1.13  2011/07/25 13:37:32  tgoedl
//added message types PACOOCTransportSimulationMsg and PACOOCReservationSimulationMsg
//
//Revision 1.12  2011/05/17 08:17:56  szw
//added PACOCCTransportResponseMsg
//Committed by: szw
//
//Revision 1.11  2011/04/22 05:28:29  m.wohlmuther
//Added messagetype for WMSSetOrderStateMessage
//
//Revision 1.10  2011/04/13 15:14:42  wni
//Removed WMSOrdersplitReconfigurationMessage.
//
//Revision 1.9  2011/04/13 11:41:17  c.joelly
//Changed numerical message type of WmsProductMoveMessage from 0x11201 to 0x11202 because 0x11201 is already used by WmsPickMessage.
//Committed by: cjo
//
//Revision 1.8  2011/04/13 08:40:20  c.joelly
//Added missing message type codes to MessageTypes enumeration and adapted corresponding messages.
//Committed by: cjo
//
//Revision 1.7  2011/04/13 07:47:00  c.joelly
//Added missing message type codes to MessageTypes enumeration and adapted corresponding messages.
//Committed by: cjo
//
//Revision 1.6  2011/04/12 13:40:34  marktl
//fixed Types of messages, renamed some MessageTypes
//Committed by: amarktl
//
//Revision 1.5  2011/04/08 11:45:38  aleitner
//changed StartServerParameterChangeMsg to StartServerParameterMsg
//
//Revision 1.4  2011/04/08 10:37:18  aleitner
//added StartServerParameterDAO for generic parameter handling
//
//Revision 1.3  2011/04/05 08:23:31  c.joelly
//Added some still missing message type codes.
//Committed by: cjo
//
//Revision 1.2  2011/03/25 10:04:01  igoina
//Commited by:igoina
//Added PACOSMCInventoryResponseMsg
//
//Revision 1.1  2011/03/17 10:32:47  marktl
//moved enum MessageTypes to com.ssp.messaging.boss
//Committed by: amarktl
//
//Revision 1.6  2011/03/14 16:19:34  igoina
//Added PACOSMCPickingResponseMsg (0x30030) needed by SMC picking to send a picking response to paco (used at Novolog project)
//
//Revision 1.5  2011/03/14 14:40:12  eherf
//added the CCReturnChannelStateMessage
//
//Revision 1.4  2011/03/02 13:42:56  c.joelly
//All numerical message type id's are taken from the MessageTypes enumeration to have a central place for the id's.
//Committed by: cjo
//
//Revision 1.3  2011/02/24 09:44:03  c.joelly
//Added quit and system messages to the enumeration to get rid of the warnings during message registration.
//Committed by: cjo
//
//Revision 1.2  2011/02/14 14:26:02  c.joelly
//
//Removed message type code 0x0 from enumeration, because 0x0 should not be a valid message type.
//Committed by: cjo
//
//Revision 1.1  2011/02/14 13:38:05  c.joelly
//
//Introduced new enumeration for message types.
//Intention: easier handling of the numerical message codes and enabling checks during message registration.
//Committed by: cjo
//
//