
package com.ssp.demo.guiceconfigured.objects.orderpools;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.ssp.demo.guiceconfigured.clients.one.LocalConfigurationClientOne;
import com.ssp.demo.guiceconfigured.core.interfaces.IOrderPool;
import com.ssp.demo.guiceconfigured.core.objects.orders.SimpleOrder;

/**
 * <p>
 * LinkedListOrderPoolTest provides methods to test the LinkedListOrderPool.
 * </p>
 * Copyright 2014 SSI Schaefer PEEM GmbH. All rights reserved.
 */

public class LinkedListOrderPoolTest {

  /** Test order #1. */
  private SimpleOrder order_1;

  /** Test order #2. */
  private SimpleOrder order_2;

  /** Test order #3. */
  private SimpleOrder order_3;

  /** Test order #4. */
  private SimpleOrder order_4;

  /**
   * Setup.
   */
  @Before
  public final void setup() {
    this.order_1 = new SimpleOrder("1", "1", "1");
    this.order_2 = new SimpleOrder("2", "2", "2");
    this.order_3 = new SimpleOrder("3", "3", "3");
    this.order_4 = new SimpleOrder("4", "4", "4");
  }

  /**
   * Default test.
   */
  @Test
  public final void test() throws Exception {
    final Injector injector = Guice.createInjector(new LocalConfigurationClientOne());

    final IOrderPool pool = injector.getInstance(IOrderPool.class);

    assertNull(pool.getNext());
    pool.add(this.order_1);
    pool.add(this.order_2);
    pool.add(this.order_3);

    assertEquals(this.order_1, pool.getNext());
    assertEquals(this.order_2, pool.getNext());
    assertEquals(this.order_3, pool.getNext());
    assertNull(pool.getNext());

    pool.add(this.order_4);
    assertEquals(this.order_4, pool.getNext());
    assertNull(pool.getNext());
  }

}

//---------------------------- Revision History ----------------------------
//$Log: LinkedListOrderPoolTest.java,v $
//Revision 1.1  2014/05/07 17:02:17  v.feurer
//[feature]: configurable DI with Google Guice
//
//
