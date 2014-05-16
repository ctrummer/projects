/** 
 * Copyright 2014 SSI Schaefer PEEM GmbH. All Rights reserved. 
 * <br /> <br />
 * 
 * $Id: ProductTest.java,v 1.1 2014/02/03 13:48:36 v.feurer Exp $
 * <br /> <br />
 *
 */

package com.ssp.demo.guice.commons;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test cases for the product class.<br />
 * <br />
 * 
 * @author fev
 * @version $Revision: 1.1 $
 */

public class ProductTest {

  @Test
  public void test() {
    final long id = 1;
    final String name = "Product 1";
    final int price = 2;
    final int weight = 3;

    Product product = new Product(id, name, price, weight);
    assertNotNull("product should not be null", product);
    assertTrue("id should be " + id, product.getId() == id);
    assertTrue("name should be " + name, product.getName().equals(name));
    assertTrue("price should be " + price, product.getPrice() == price);
    assertTrue("weight should be " + weight, product.getWeight() == weight);
  }

}

//---------------------------- Revision History ----------------------------
//$Log: ProductTest.java,v $
//Revision 1.1  2014/02/03 13:48:36  v.feurer
//feature: Google Guice: functional programming, DI and AOP demonstration
//
//
