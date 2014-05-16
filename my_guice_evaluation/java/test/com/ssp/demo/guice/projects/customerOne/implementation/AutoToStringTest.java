/** 
 * Copyright 2014 SSI Schaefer PEEM GmbH. All Rights reserved. 
 * <br /> <br />
 * 
 * $Id: AutoToStringTest.java,v 1.1 2014/02/03 13:48:36 v.feurer Exp $
 * <br /> <br />
 *
 */

package com.ssp.demo.guice.projects.customerOne.implementation;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ssp.demo.guice.commons.Product;

/**
 * Test for the auto to string function.<br />
 * <br />
 * 
 * @author fev
 * @version $Revision: 1.1 $
 */

public class AutoToStringTest {

  /**
   * Testing the to string function.
   */
  @Test
  public void test() {
    Product auto = new AutoProductProvider().get();
    assertNotNull("auto should be not null", auto);
    String autoToString = new AutoToString().apply(auto);
    String manual =
      String.format("Product{PRICE=%s, ID=%s, NAME=%s, WEIGHT=%s}",
                    Integer.valueOf(auto.getPrice()),
                    Long.valueOf(auto.getId()),
                    auto.getName(),
                    Integer.valueOf(auto.getWeight()));
    assertTrue("string should have the proper format", autoToString.equals(manual));
  }
}

//---------------------------- Revision History ----------------------------
//$Log: AutoToStringTest.java,v $
//Revision 1.1  2014/02/03 13:48:36  v.feurer
//feature: Google Guice: functional programming, DI and AOP demonstration
//
//
