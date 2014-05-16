
package com.ssp.demo.guiceconfigured.core.interfaces;

/**
 * <p>
 * IOrder is a simple interface for an order.
 * </p>
 * Copyright 2014 SSI Schaefer PEEM GmbH. All rights reserved.
 */

public interface IOrder {

  /**
   * @return The type of the order
   */
  String getType();

  /**
   * @return The ID of the order
   */
  String getId();

  /**
   * @return The Description of the order
   */
  String getDescription();

  /**
   * @return The details of the order
   */
  Object getDetails();
}

//---------------------------- Revision History ----------------------------
//$Log: IOrder.java,v $
//Revision 1.1  2014/05/07 17:02:17  v.feurer
//[feature]: configurable DI with Google Guice
//
//
