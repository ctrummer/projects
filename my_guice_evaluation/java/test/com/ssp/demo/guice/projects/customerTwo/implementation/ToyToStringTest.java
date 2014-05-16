/** 
 * Copyright 2014 SSI Schaefer PEEM GmbH. All Rights reserved. 
 * <br /> <br />
 * 
 * $Id: ToyToStringTest.java,v 1.1 2014/02/03 13:48:36 v.feurer Exp $
 * <br /> <br />
 *
 */

package com.ssp.demo.guice.projects.customerTwo.implementation;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ssp.demo.guice.commons.Product;

/**
 * Test for the toy to string function.<br />
 * <br />
 * 
 * @author fev
 * @version $Revision: 1.1 $
 */

public class ToyToStringTest {

  /**
   * Testing the to string function.
   */
  @Test
  public void test() {
    Product toy = new ToyProductProvider().get();
    assertNotNull("toy should be not null", toy);
    String toyToString = new ToyToString().apply(toy);
    String manual =
      String.format("Product{WEIGHT=%s, ID=%s, NAME=%s, PRICE=%s}",
                    Integer.valueOf(toy.getWeight()),
                    Long.valueOf(toy.getId()),
                    toy.getName(),
                    Integer.valueOf(toy.getPrice()));
    assertTrue("string should have the proper format", toyToString.equals(manual));
  }
}

//---------------------------- Revision History ----------------------------
//$Log: ToyToStringTest.java,v $
//Revision 1.1  2014/02/03 13:48:36  v.feurer
//feature: Google Guice: functional programming, DI and AOP demonstration
//
//
