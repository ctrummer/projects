/** 
 * Copyright 2014 SSI Schaefer PEEM GmbH. All Rights reserved. 
 * <br /> <br />
 * 
 * $Id: BadgeToStringTest.java,v 1.1 2014/02/03 13:48:36 v.feurer Exp $
 * <br /> <br />
 *
 */

package com.ssp.demo.guice.projects.customerThree.implementation;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ssp.demo.guice.commons.Product;

/**
 * Test for the badge to string function.<br />
 * <br />
 * 
 * @author fev
 * @version $Revision: 1.1 $
 */

public class BadgeToStringTest {

  /**
   * Testing the to string function.
   */
  @Test
  public void test() {
    Product badge = new BadgeProductProvider().get();
    assertNotNull("badge should be not null", badge);
    String badgeToString = new BadgeToString().apply(badge);
    String manual =
      String.format("Product{NAME=%s, PRICE=%s, ID=%s, WEIGHT=%s}",
                    badge.getName(),
                    Integer.valueOf(badge.getPrice()),
                    Long.valueOf(badge.getId()),
                    Integer.valueOf(badge.getWeight()));
    assertTrue("string should have the proper format", badgeToString.equals(manual));
  }
}

//---------------------------- Revision History ----------------------------
//$Log: BadgeToStringTest.java,v $
//Revision 1.1  2014/02/03 13:48:36  v.feurer
//feature: Google Guice: functional programming, DI and AOP demonstration
//
//
