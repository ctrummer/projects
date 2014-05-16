/** 
 * Copyright 2014 SSI Schaefer PEEM GmbH. All Rights reserved. 
 * <br /> <br />
 * 
 * $Id: AutoProductProviderTest.java,v 1.1 2014/02/03 13:48:36 v.feurer Exp $
 * <br /> <br />
 *
 */

package com.ssp.demo.guice.projects.customerOne.implementation;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ssp.demo.guice.commons.Product;

/**
 * Test for the auto provider.<br />
 * <br />
 * 
 * @author fev
 * @version $Revision: 1.1 $
 */

public class AutoProductProviderTest {

  /** Minimal price. */
  private static final int MIN_PRICE = 3000;

  /** Maximal price. */
  private static final int MAX_PRICE = 6000;

  /** Minimal weight. */
  private static final int MIN_WEIGHT = 1500;

  /** Maximal weight. */
  private static final int MAX_WEIGHT = 3000;

  /** Test iterations. */
  private static final int TEST_COUNT = 100000;

  /**
   * Test case for auto creation.
   */
  @Test
  public void test() {
    final long start = System.currentTimeMillis();

    for (int i = 0; i < TEST_COUNT; i++) {
      Product auto = new AutoProductProvider().get();
      assertNotNull("auto should be not null", auto);
      assertTrue("name should end with ' car'", auto.getName().endsWith(" car"));
      assertTrue("id should be greater or equal than start time", auto.getId() >= start);
      assertTrue("price should be between " + MIN_PRICE + " and  " + MAX_PRICE, auto.getPrice() >= MIN_PRICE && auto.getPrice() <= MAX_PRICE);
      assertTrue("weigth should be between " + MIN_WEIGHT + " and  " + MAX_WEIGHT, auto.getWeight() >= MIN_WEIGHT && auto.getWeight() <= MAX_WEIGHT);
    }
  }
}

//---------------------------- Revision History ----------------------------
//$Log: AutoProductProviderTest.java,v $
//Revision 1.1  2014/02/03 13:48:36  v.feurer
//feature: Google Guice: functional programming, DI and AOP demonstration
//
//
