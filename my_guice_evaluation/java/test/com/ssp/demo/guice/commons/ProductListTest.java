/** 
 * Copyright 2014 SSI Schaefer PEEM GmbH. All Rights reserved. 
 * <br /> <br />
 * 
 * $Id: ProductListTest.java,v 1.1 2014/02/03 13:48:36 v.feurer Exp $
 * <br /> <br />
 *
 */

package com.ssp.demo.guice.commons;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.base.Function;
import com.google.inject.Provider;

import static org.mockito.Mockito.mock;

/**
 * Test cases for the product list.<br />
 * <br />
 * 
 * @author fev
 * @version $Revision: 1.1 $
 */
@RunWith(MockitoJUnitRunner.class)
public class ProductListTest {

  /** The length of the product list. */
  private static final int PRODUCT_COUNT = 5;

  /** Price multiplier. */
  private static final int PRICE_MULTIPLIER = 2;

  /** Weight multiplier. */
  private static final int WEIGHT_MULTIPLIER = 3;

  /** The mocked test object. */
  private ProductList mockProductList;

  /** Mock provider to generate products. */
  private Provider<Product> mockProductProvider;

  /** Mock comparator to compare products. */
  private Comparator<Product> mockProductComparator;

  /** Mock function to convert products to string. */
  private Function<Product, String> mockToStringFunction;

  /** Counter to generate unique IDs. */
  protected static AtomicLong uniqueId = new AtomicLong();

  /** Mock array list to hold the products. */
  private List<Product> mockProducts = new ArrayList<Product>(PRODUCT_COUNT);

  /**
   * Mock the objects.
   * 
   * @throws NoSuchFieldException when productCount field not found
   * @throws IllegalAccessException when productCount field cannot be inserted
   */
  @Before
  public final void setUp() throws NoSuchFieldException, IllegalAccessException {
    mockProductList = mock(ProductList.class);

    injectProductCount();
    injectProductList();
    injectProductProvider();
    injectComparator();
    injectToString();
  }

  /**
   * Injects the <code>productComparator</code> method of the mock product list.
   * 
   * @throws NoSuchFieldException when productCount field not found
   * @throws IllegalAccessException when productCount field cannot be inserted
   */
  private void injectToString() throws NoSuchFieldException, IllegalAccessException {
    mockToStringFunction = new Function<Product, String>() {
      /** {@inheritDoc} */
      @Override
      public String apply(final Product product) {
        return "Product[" + product.getId() + "]";
      }
    };

    Field field = ProductList.class.getDeclaredField("productToString");
    field.setAccessible(true);
    field.set(mockProductList, mockToStringFunction);
  }

  /**
   * Injects the <code>productComparator</code> method of the mock product list.
   * 
   * @throws NoSuchFieldException when productCount field not found
   * @throws IllegalAccessException when productCount field cannot be inserted
   */
  private void injectComparator() throws NoSuchFieldException, IllegalAccessException {
    mockProductComparator = new Comparator<Product>() {
      /** {@inheritDoc} */
      @Override
      public final int compare(final Product o1, final Product o2) {
        return Long.valueOf(o1.getId()).compareTo(Long.valueOf(o2.getId())) * -1;
      }
    };

    Field field = ProductList.class.getDeclaredField("productComparator");
    field.setAccessible(true);
    field.set(mockProductList, mockProductComparator);
  }

  /**
   * Injects the <code>productProviced</code> method of the mock product list.
   * 
   * @throws NoSuchFieldException when productCount field not found
   * @throws IllegalAccessException when productCount field cannot be inserted
   */
  private void injectProductProvider() throws NoSuchFieldException, IllegalAccessException {
    mockProductProvider = new Provider<Product>() {
      /** {@inheritDoc} */
      @Override
      public final Product get() {
        long id = uniqueId.getAndIncrement();
        return new Product(id, "object " + id, (int) (id * PRICE_MULTIPLIER), (int) (id * WEIGHT_MULTIPLIER));
      }
    };

    Field field = ProductList.class.getDeclaredField("productProvider");
    field.setAccessible(true);
    field.set(mockProductList, mockProductProvider);
  }

  /**
   * Injects the <code>products</code> field of the mock product list.
   * 
   * @throws NoSuchFieldException when productCount field not found
   * @throws IllegalAccessException when productCount field cannot be inserted
   */
  private void injectProductList() throws NoSuchFieldException, IllegalAccessException {
    Field field = ProductList.class.getDeclaredField("products");
    field.setAccessible(true);
    field.set(mockProductList, mockProducts);
  }

  /**
   * Injects the <code>productCount</code> field of the mock product list.
   * 
   * @throws NoSuchFieldException when productCount field not found
   * @throws IllegalAccessException when productCount field cannot be inserted
   */
  private void injectProductCount() throws NoSuchFieldException, IllegalAccessException {
    Field field = ProductList.class.getDeclaredField("productCount");
    field.setAccessible(true);
    field.set(mockProductList, Integer.valueOf(PRODUCT_COUNT));
  }

  @Test
  public final void test() {
    mockProductList.init();
    assertTrue("should contain " + PRODUCT_COUNT + " elements", mockProducts.size() == PRODUCT_COUNT);

    for (int i = 0; i < PRODUCT_COUNT; i++) {
      assertTrue("element " + i + " should have ID of " + i, mockProducts.get(i).getId() == i);
      assertTrue("element " + i + " should have the price of " + i * PRICE_MULTIPLIER, mockProducts.get(i).getPrice() == mockProducts.get(i).getId() * PRICE_MULTIPLIER);
      assertTrue("element " + i + " should have the weight of " + i * WEIGHT_MULTIPLIER, mockProducts.get(i).getWeight() == mockProducts.get(i).getId() * WEIGHT_MULTIPLIER);
    }

    mockProductList.listAll();
    for (int i = 0; i < PRODUCT_COUNT; i++) {
      assertTrue("element " + i + " should have ID of " + (PRODUCT_COUNT - 1 - i), mockProducts.get(i).getId() == PRODUCT_COUNT - 1 - i);
    }
  }

}

//---------------------------- Revision History ----------------------------
//$Log: ProductListTest.java,v $
//Revision 1.1  2014/02/03 13:48:36  v.feurer
//feature: Google Guice: functional programming, DI and AOP demonstration
//
//
