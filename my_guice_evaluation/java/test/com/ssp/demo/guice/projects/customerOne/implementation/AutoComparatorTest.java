/** 
 * Copyright 2014 SSI Schaefer PEEM GmbH. All Rights reserved. 
 * <br /> <br />
 * 
 * $Id: AutoComparatorTest.java,v 1.1 2014/02/03 13:48:36 v.feurer Exp $
 * <br /> <br />
 *
 */

package com.ssp.demo.guice.projects.customerOne.implementation;

import static org.junit.Assert.*;

import java.util.Comparator;

import org.junit.Test;

import com.ssp.demo.guice.commons.Product;

/**
 * Test for the comparator.<br />
 * <br />
 * 
 * @author fev
 * @version $Revision: 1.1 $
 */

public class AutoComparatorTest {

  /** High price. */
  private static final int HIGH_PRICE = 5000;

  /** Low price. */
  private static final int LOW_PRICE = 1000;

  /** Weight. */
  private static final int WEIGHT = 1900;

  /**
   * Testing the comparator, should return value under 0 when the first product is more expensive
   * than the second.
   */
  @Test
  public void test() {
    Product expensive = new Product(0, "Car 1", HIGH_PRICE, WEIGHT);
    Product cheap = new Product(0, "Car 1", LOW_PRICE, WEIGHT);
    Comparator<Product> comparator = new AutoComparator();
    assertTrue("expensive car should be more expensive", comparator.compare(expensive, cheap) < 0);
  }

}

//---------------------------- Revision History ----------------------------
//$Log: AutoComparatorTest.java,v $
//Revision 1.1  2014/02/03 13:48:36  v.feurer
//feature: Google Guice: functional programming, DI and AOP demonstration
//
//
