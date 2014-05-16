/** 
 * Copyright 2014 SSI Schaefer PEEM GmbH. All Rights reserved. 
 * <br /> <br />
 * 
 * $Id: BadgeProductProviderTest.java,v 1.1 2014/02/03 13:48:36 v.feurer Exp $
 * <br /> <br />
 *
 */

package com.ssp.demo.guice.projects.customerThree.implementation;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ssp.demo.guice.commons.Product;

/**
 * Test for the badge provider.<br />
 * <br />
 * 
 * @author fev
 * @version $Revision: 1.1 $
 */

public class BadgeProductProviderTest {

  /** Minimal price. */
  private static final int MIN_PRICE = 22;

  /** Maximal price. */
  private static final int MAX_PRICE = 32;

  /** Minimal weight. */
  private static final int MIN_WEIGHT = 1;

  /** Maximal weight. */
  private static final int MAX_WEIGHT = 6;

  /** Test iterations. */
  private static final int TEST_COUNT = 100000;

  /**
   * Test case for badge creation.
   */
  @Test
  public void test() {
    final long start = System.currentTimeMillis();

    for (int i = 0; i < TEST_COUNT; i++) {
      Product badge = new BadgeProductProvider().get();
      assertNotNull("badge should be not null", badge);
      assertTrue("name should end with ' badge'", badge.getName().endsWith(" badge"));
      assertTrue("id should be greater or equal than start time", badge.getId() >= start);
      assertTrue("price should be between " + MIN_PRICE + " and  " + MAX_PRICE, badge.getPrice() >= MIN_PRICE && badge.getPrice() <= MAX_PRICE);
      assertTrue("weigth should be between " + MIN_WEIGHT + " and  " + MAX_WEIGHT, badge.getWeight() >= MIN_WEIGHT && badge.getWeight() <= MAX_WEIGHT);
    }
  }
}

//---------------------------- Revision History ----------------------------
//$Log: BadgeProductProviderTest.java,v $
//Revision 1.1  2014/02/03 13:48:36  v.feurer
//feature: Google Guice: functional programming, DI and AOP demonstration
//
//
