/** 
 * Copyright 2014 SSI Schaefer PEEM GmbH. All Rights reserved. 
 * <br /> <br />
 * 
 * $Id: BadgeComparatorTest.java,v 1.1 2014/02/03 13:48:36 v.feurer Exp $
 * <br /> <br />
 *
 */

package com.ssp.demo.guice.projects.customerThree.implementation;

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

public class BadgeComparatorTest {

  /** Price. */
  private static final int PRICE = 5000;

  /** Weight. */
  private static final int WEIGHT = 1900;

  /**
   * Testing the comparator, should return value under 0 when the first product is after the second in 
   * the alphabetical order.
   */
  @Test
  public void test() {
    Product first = new Product(0, "Badge 1", PRICE, WEIGHT);
    Product second = new Product(0, "Badge 2", PRICE, WEIGHT);
    Comparator<Product> comparator = new BadgeComparator();
    assertTrue("expensive badge should be more expensive", comparator.compare(second, first) < 0);
  }

}

//---------------------------- Revision History ----------------------------
//$Log: BadgeComparatorTest.java,v $
//Revision 1.1  2014/02/03 13:48:36  v.feurer
//feature: Google Guice: functional programming, DI and AOP demonstration
//
//
