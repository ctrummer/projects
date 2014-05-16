
package com.ssp.demo.guiceconfigured.core.interfaces;

import javax.annotation.Nonnull;

/**
 * <p>
 * IProcessor provides an interface for a simple order processor.
 * </p>
 * Copyright 2014 SSI Schaefer PEEM GmbH. All rights reserved.
 */

public interface IProcessor {

  /**
   * Processes the order.
   * 
   * @param order The order to process
   * @return The result flag
   */
  boolean process(@Nonnull final IOrder order);
}

//---------------------------- Revision History ----------------------------
//$Log: IProcessor.java,v $
//Revision 1.1  2014/05/07 17:02:17  v.feurer
//[feature]: configurable DI with Google Guice
//
//
