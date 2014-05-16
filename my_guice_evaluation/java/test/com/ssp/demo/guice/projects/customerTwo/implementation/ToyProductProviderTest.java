/** 
 * Copyright 2014 SSI Schaefer PEEM GmbH. All Rights reserved. 
 * <br /> <br />
 * 
 * $Id: ToyProductProviderTest.java,v 1.1 2014/02/03 13:48:36 v.feurer Exp $
 * <br /> <br />
 *
 */

package com.ssp.demo.guice.projects.customerTwo.implementation;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ssp.demo.guice.commons.Product;

/**
 * Test for the toy provider.<br />
 * <br />
 * 
 * @author fev
 * @version $Revision: 1.1 $
 */

public class ToyProductProviderTest {

  /** Minimal price. */
  private static final int MIN_PRICE = 5;

  /** Maximal price. */
  private static final int MAX_PRICE = 11;

  /** Minimal weight. */
  private static final int MIN_WEIGHT = 1;

  /** Maximal weight. */
  private static final int MAX_WEIGHT = 4;

  /** Test iterations. */
  private static final int TEST_COUNT = 100000;

  /**
   * Test case for toy creation.
   */
  @Test
  public void test() {
    final long start = System.currentTimeMillis();

    for (int i = 0; i < TEST_COUNT; i++) {
      Product toy = new ToyProductProvider().get();
      assertNotNull("toy should be not null", toy);
      assertTrue("name should end with ' toy'", toy.getName().endsWith(" toy"));
      assertTrue("id should be greater or equal than start time", toy.getId() >= start);
      assertTrue("price should be between " + MIN_PRICE + " and  " + MAX_PRICE, toy.getPrice() >= MIN_PRICE && toy.getPrice() <= MAX_PRICE);
      assertTrue("weigth should be between " + MIN_WEIGHT + " and  " + MAX_WEIGHT, toy.getWeight() >= MIN_WEIGHT && toy.getWeight() <= MAX_WEIGHT);
    }
  }
}

//---------------------------- Revision History ----------------------------
//$Log: ToyProductProviderTest.java,v $
//Revision 1.1  2014/02/03 13:48:36  v.feurer
//feature: Google Guice: functional programming, DI and AOP demonstration
//
//
