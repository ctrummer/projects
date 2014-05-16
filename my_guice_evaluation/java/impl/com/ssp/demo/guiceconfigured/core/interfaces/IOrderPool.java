
package com.ssp.demo.guiceconfigured.core.interfaces;

import javax.annotation.Nonnull;

/**
 * <p>
 * IOrderPool provides a general order pool interface.
 * </p>
 * Copyright 2014 SSI Schaefer PEEM GmbH. All rights reserved.
 */

public interface IOrderPool {

  /**
   * Adds a new object to the order pool.
   * 
   * @param order The new order
   */
  void add(@Nonnull final IOrder order);

  /**
   * Returns the next order from the pool.
   * 
   * @return The next order from the pool or null if empty
   */
  IOrder getNext();

  /**
   * Returns the initial size of the pool.
   * 
   * @return The initial size of the pool
   */
  int getInitialSize();

}

//---------------------------- Revision History ----------------------------
//$Log: IOrderPool.java,v $
//Revision 1.1  2014/05/07 17:02:17  v.feurer
//[feature]: configurable DI with Google Guice
//
//
