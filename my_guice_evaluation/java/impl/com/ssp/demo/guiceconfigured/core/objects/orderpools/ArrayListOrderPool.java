
package com.ssp.demo.guiceconfigured.core.objects.orderpools;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;

import javax.annotation.Nonnull;
import javax.inject.Inject;

import org.apache.commons.configuration.Configuration;

import com.ssp.demo.guiceconfigured.core.interfaces.IOrder;
import com.ssp.demo.guiceconfigured.core.interfaces.IOrderPool;

/**
 * <p>
 * ArrayListOrderPool provides an order pool using an ArrayList.
 * </p>
 * <p>
 * ArrayListOrderPool is <b>not</b> immutable and must <b>not</b> be freely exchanged between
 * threads. <br />
 * Calls to methods of ArrayListOrderPool are <b>not</b> thread safe.
 * </p>
 * Copyright 2014 SSI Schaefer PEEM GmbH. All rights reserved.
 */

public final class ArrayListOrderPool implements IOrderPool {

  /** The error message complaining about a value must not be null. */
  private static final String ERR_NOT_NULL = "'%s' must not be null";

  /** The initial pool size. */
  private int initialPoolSize;

  /** The pool. */
  private ArrayList<IOrder> pool;

  /** Pointer to the next element to return. */
  private int next = 0;

  /** The configuration. */
  @Inject
  private Configuration config;

  /** {@inheritDoc} */
  @Override
  public void add(@Nonnull final IOrder order) {
    checkNotNull(order, String.format(ERR_NOT_NULL, "order"));

    if (this.pool == null) {
      this.initialPoolSize = this.config.getInt("pool.initialSize", 10);
      this.pool = new ArrayList<IOrder>(this.initialPoolSize);
    } // not elegant enough, in live systems must find some other solution

    this.pool.add(order);
  }

  /** {@inheritDoc} */
  @Override
  public IOrder getNext() {
    IOrder result = null;
    if (this.pool != null && this.next < this.pool.size()) {
      result = this.pool.get(this.next);
      this.next++;
    }
    return result;
  }

  /** {@inheritDoc} */
  @Override
  public int getInitialSize() {
    return this.initialPoolSize;
  }

}

//---------------------------- Revision History ----------------------------
//$Log: ArrayListOrderPool.java,v $
//Revision 1.1  2014/05/07 17:02:17  v.feurer
//[feature]: configurable DI with Google Guice
//
//
