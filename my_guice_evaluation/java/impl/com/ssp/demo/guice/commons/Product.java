/** 
 * Copyright 2014 SSI Schaefer PEEM GmbH. All Rights reserved. 
 * <br /> <br />
 * 
 * $Id: Product.java,v 1.1 2014/02/03 13:48:36 v.feurer Exp $
 * <br /> <br />
 *
 */

package com.ssp.demo.guice.commons;

import javax.annotation.Nonnull;

/**
 * A simple product class.
 * <br />
 * 
 * @author fev
 * @version $Revision: 1.1 $
 */

public class Product {

  /** Product ID. */
  private final long id;

  /** Product name. */
  private final String name;

  /** Product price in EUR. */
  private final int price;

  /** Product weight in kg. */
  private final int weight;

  /**
   * Creates a complete product.
   * 
   * @param pId the product Id
   * @param pName name of the product
   * @param pPrice product price in EUR
   * @param pWeight product weight in kilogram
   */
  public Product(@Nonnull final long pId, @Nonnull final String pName, @Nonnull final int pPrice, @Nonnull final int pWeight) {
    this.id = pId;
    this.name = pName;
    this.price = pPrice;
    this.weight = pWeight;
  }

  /**
   * Returns the ID of the product.
   * 
   * @return the ID
   */
  public final long getId() {
    return this.id;
  }

  /**
   * Returns the name of the product.
   * 
   * @return the name
   */
  public final String getName() {
    return this.name;
  }

  /**
   * Returns the price of the product.
   * 
   * @return the price in EUR
   */
  public final int getPrice() {
    return this.price;
  }

  /**
   * Returns the weight of the product.
   * 
   * @return the weight in kilogram
   */
  public final int getWeight() {
    return this.weight;
  }
}

//---------------------------- Revision History ----------------------------
//$Log: Product.java,v $
//Revision 1.1  2014/02/03 13:48:36  v.feurer
//feature: Google Guice: functional programming, DI and AOP demonstration
//
//
