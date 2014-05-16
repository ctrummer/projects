/** 
 * Copyright 2014 SSI Schaefer PEEM GmbH. All Rights reserved. 
 * <br /> <br />
 * 
 * $Id: ToyComparatorTest.java,v 1.1 2014/02/03 13:48:36 v.feurer Exp $
 * <br /> <br />
 *
 */

package com.ssp.demo.guice.projects.customerTwo.implementation;

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

public class ToyComparatorTest {

  /** High price. */
  private static final int HEAVY_WEIGHT = 5000;

  /** Low price. */
  private static final int LOW_WEIGHT = 1000;

  /** Weight. */
  private static final int PRICE = 1900;

  /**
   * Testing the comparator, should return value under 0 when the first product heavier
   * than the second.
   */
  @Test
  public void test() {
    Product heavy = new Product(0, "Toy 1", PRICE, HEAVY_WEIGHT);
    Product light = new Product(0, "Toy 2", PRICE, LOW_WEIGHT);
    Comparator<Product> comparator = new ToyComparator();
    assertTrue("heavier should have more weight", comparator.compare(heavy, light) < 0);
  }

}

//---------------------------- Revision History ----------------------------
//$Log: ToyComparatorTest.java,v $
//Revision 1.1  2014/02/03 13:48:36  v.feurer
//feature: Google Guice: functional programming, DI and AOP demonstration
//
//
