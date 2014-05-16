
package com.ssp.demo.guiceconfigured.core.objects.orderpools;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.LinkedList;

import javax.annotation.Nonnull;

import com.ssp.demo.guiceconfigured.core.interfaces.IOrder;
import com.ssp.demo.guiceconfigured.core.interfaces.IOrderPool;

/**
 * <p>
 * LinkedListOrderPool provides an order pool using an LinkedList.
 * </p>
 * <p>
 * LinkedListOrderPool is <b>not</b> immutable and must <b>not</b> be freely exchanged between
 * threads. <br />
 * Calls to methods of LinkedListOrderPool are <b>not</b> thread safe.
 * </p>
 * Copyright 2014 SSI Schaefer PEEM GmbH. All rights reserved.
 */

public final class LinkedListOrderPool implements IOrderPool {

  /** The error message complaining about a value must not be null. */
  private static final String ERR_NOT_NULL = "'%s' must not be null";

  /** The pool. */
  private final LinkedList<IOrder> pool = new LinkedList<IOrder>();

  /** Pointer to the next element to return. */
  private int next = 0;

  /** {@inheritDoc} */
  @Override
  public void add(@Nonnull final IOrder order) {
    checkNotNull(order, String.format(ERR_NOT_NULL, "order"));
    this.pool.add(order);
  }

  /** {@inheritDoc} */
  @Override
  public IOrder getNext() {
    IOrder result = null;
    if (this.next < this.pool.size()) {
      result = this.pool.get(this.next);
      this.next++;
    }
    return result;
  }

  /** {@inheritDoc} */
  @Override
  public int getInitialSize() {
    return -1;
  }

}

//---------------------------- Revision History ----------------------------
//$Log: LinkedListOrderPool.java,v $
//Revision 1.1  2014/05/07 17:02:17  v.feurer
//[feature]: configurable DI with Google Guice
//
//
