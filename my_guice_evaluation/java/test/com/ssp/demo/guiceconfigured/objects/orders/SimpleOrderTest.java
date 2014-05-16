
package com.ssp.demo.guiceconfigured.objects.orders;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.ssp.demo.guiceconfigured.core.objects.orders.SimpleOrder;

/**
 * <p>
 * SimpleOrderTest provides methods to test the SimpleOrder class.
 * </p>
 * Copyright 2014 SSI Schaefer PEEM GmbH. All rights reserved.
 */

@SuppressWarnings("static-method")
public class SimpleOrderTest {

  /** The order type. */
  private static final String ORDER_TYPE = "complicatedOrder";

  /** The order ID. */
  private static final String ORDER_ID = "1";

  /** The null order ID. */
  private static final String ORDER_ID_NULL = null;

  /** The order description. */
  private static final String ORDER_DESC = "First order";

  /** The order details. */
  private static final Object ORDER_DETAILS = "Details of the first order";

  /**
   * Normal test.
   */
  @Test
  public final void test() {
    final SimpleOrder order = new SimpleOrder(ORDER_ID, ORDER_DESC, ORDER_DETAILS);
    assertEquals(ORDER_TYPE, order.getType());
    assertEquals(ORDER_ID, order.getId());
    assertEquals(ORDER_DESC, order.getDescription());
    assertEquals(ORDER_DETAILS, order.getDetails());
  }

  /**
   * Test without a valid id.
   */
  @SuppressWarnings("unused")
  @Test(expected = NullPointerException.class)
  public final void testWithoutValidId() {
    new SimpleOrder(ORDER_ID_NULL, null, null);
  }

}

//---------------------------- Revision History ----------------------------
//$Log: SimpleOrderTest.java,v $
//Revision 1.1  2014/05/07 17:02:17  v.feurer
//[feature]: configurable DI with Google Guice
//
//
