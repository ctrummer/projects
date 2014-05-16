
package com.ssp.demo.guiceconfigured.core.interfaces;

import javax.annotation.Nonnull;

import com.google.inject.Injector;

/**
 * <p>
 * IOrderProcessorEngine provides a simple interface for an order processor engine.
 * </p>
 * Copyright 2014 SSI Schaefer PEEM GmbH. All rights reserved.
 */

public interface IOrderProcessorEngine {

  /**
   * @param injector The injector to use
   */
  void setInjector(@Nonnull final Injector injector);

  /**
   * Loads the orders from the configuration.
   */
  void loadOrders();

  /**
   * Processes the orders.
   */
  void processOrders();

  /**
   * @return The number of orders processed
   */
  int getOrdersProcessed();

  /**
   * @return The number of orders discarded (not processed)
   */
  int getOrdersDiscarded();

  /**
   * @return The number of orders rejected
   */
  int getOrdersRejected();
}

//---------------------------- Revision History ----------------------------
//$Log: IOrderProcessorEngine.java,v $
//Revision 1.1  2014/05/07 17:02:17  v.feurer
//[feature]: configurable DI with Google Guice
//
//
