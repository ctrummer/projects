/** 
 * Copyright 2014 SSI Schaefer PEEM GmbH. All Rights reserved. 
 * <br /> <br />
 * 
 * $Id: ProductList.java,v 1.1 2014/02/03 13:48:36 v.feurer Exp $
 * <br /> <br />
 *
 */

package com.ssp.demo.guice.commons;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.common.base.Function;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;

/**
 * A product list class. <br />
 * 
 * @author fev
 * @version $Revision: 1.1 $
 */

public class ProductList {

  /** The logger. */
  private static final Logger LOG = Logger.getLogger(ProductList.class);

  /** The list for the products. */
  private final List<Product> products = new ArrayList<Product>(20);

  /** How many products to populate at startup. */
  @Inject
  @Named("productCount")
  private int productCount;

  /** The product provider. */
  @Inject
  private Provider<Product> productProvider;

  /** The product comparator. Named inject because there can be many comparators. */
  @Inject
  private Comparator<Product> productComparator;

  /**
   * The function to convert a product to a string. Named inject because there can be many
   * functions.
   */
  @Inject
  @Named("convertProductToString")
  private Function<Product, String> productToString;

  /**
   * This constructor will populate some products.
   */
  public ProductList() {
  }

  /**
   * Populates a product list.
   */
  public final void init() {
    for (int i = 0; i < this.productCount; i++) {
      this.products.add(this.productProvider.get());
    }
  }

  /**
   * Sorts the collection using the internal comparator.
   */
  private void sort() {
    if (this.products.isEmpty()) {
      this.init();
    }
    Collections.sort(this.products, this.productComparator);
  }

  /**
   * Lists all the products in the order defined by the injected comparator using the injected
   * converter function.
   */
  public final void listAll() {
    this.sort();
    for (Product current : this.products) {
      LOG.info(this.productToString.apply(current));
    }
  }

}

//---------------------------- Revision History ----------------------------
//$Log: ProductList.java,v $
//Revision 1.1  2014/02/03 13:48:36  v.feurer
//feature: Google Guice: functional programming, DI and AOP demonstration
//
//
