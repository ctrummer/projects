/** 
 * Copyright 2014 SSI Schaefer PEEM GmbH. All Rights reserved. 
 * <br /> <br />
 * 
 * $Id: LocalConfiguration.java,v 1.1 2014/02/03 13:48:36 v.feurer Exp $
 * <br /> <br />
 *
 */

package com.ssp.demo.guice.projects.customerThree;

import java.util.Comparator;

import com.google.common.base.Function;
import com.google.inject.AbstractModule;
import com.google.inject.Provider;
import com.google.inject.TypeLiteral;
import com.google.inject.matcher.Matchers;
import com.google.inject.name.Names;
import com.ssp.demo.guice.commons.Product;
import com.ssp.demo.guice.projects.customerThree.implementation.BadgeComparator;
import com.ssp.demo.guice.projects.customerThree.implementation.BadgeProductProvider;
import com.ssp.demo.guice.projects.customerThree.implementation.BadgeToString;
import com.ssp.demo.guice.projects.customerThree.implementation.ProductInterceptor;

/**
 * This is the local configuration which is different in every customer system. This class governs
 * the whole work or Guice (dependency injection, aspects), so this is the place where everything
 * needs to be configured instead of an XML file.<br />
 * <br />
 * 
 * @author fev
 * @version $Revision: 1.1 $
 */

public class LocalConfiguration extends AbstractModule {

  /** Number of products to populate. */
  private static final int PRODUCT_COUNT = 8;

  /** {@inheritDoc} */
  @Override
  protected final void configure() {

    // For every time when a Product is to be provided, use the AutoProductProvider class,
    // see ProductList.productProvider
    bind(Product.class).toProvider(BadgeProductProvider.class);

    // For the comparator (ProductList.productComparator), use the AutoComparator class
    bind(new TypeLiteral<Comparator<Product>>() {
      /** empty on purpose */
    }).to(BadgeComparator.class);

    // the ProductList.compareProductToString we are using the name-implied binding,
    // here just for demonstration purposes. The real power of this naming is when
    // you have multiple injection targets with the same type and you want to inject
    // different kind of dependees into different targets. Be aware, this kind of
    // binding is a little bit cumbersome to refactor (because of the string name).
    bind(new TypeLiteral<Function<Product, String>>() {
      /** empty on purpose */
    }).annotatedWith(Names.named("convertProductToString")).to(BadgeToString.class);

    // we can inject also constants
    bindConstant().annotatedWith(Names.named("productCount")).to(PRODUCT_COUNT);

    // now we define an aspect: the any methods returning a Product instance of any provider
    // will be intercepted (in this case the BagdeProductProvider.get()
    bindInterceptor(Matchers.subclassesOf(Provider.class),
                    Matchers.returns(Matchers.only(Product.class)),
                    new ProductInterceptor());
  }
}

//---------------------------- Revision History ----------------------------
//$Log: LocalConfiguration.java,v $
//Revision 1.1  2014/02/03 13:48:36  v.feurer
//feature: Google Guice: functional programming, DI and AOP demonstration
//
//
