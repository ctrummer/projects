/** 
 * Copyright 2014 SSI Schaefer PEEM GmbH. All Rights reserved. 
 * <br /> <br />
 * 
 * $Id: Application.java,v 1.1 2014/02/03 13:48:36 v.feurer Exp $
 * <br /> <br />
 *
 */

package com.ssp.demo.guice.projects.customerOne;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.ssp.demo.guice.commons.ProductList;
import com.ssp.demo.guice.commons.util.LoggerConfiguration;

/**
 * This sample customer is an automotive factory. Generated products are cars, sorted by price in
 * descending order. <br />
 * 
 * @author fev
 * @version $Revision: 1.1 $
 */

public final class Application {

  /** The logger. */
  private static final Logger LOG = Logger.getLogger(Application.class);

  /** Protection against instantiation. */
  private Application() {
    // empty on purpose
  }

  /**
   * Demo application which creates some cars (products) and list them sorted by price in a
   * descending order.<br />
   * 
   * @param args the arguments of the program, not used
   */
  public static void main(final String[] args) {
    // configure logger:
    PropertyConfigurator.configure(LoggerConfiguration.getConfiguration());

    LOG.info("Customer One: automotive factory");

    // The injector is the main "object factory" provided by the Google Guice.
    // Only objects generated by this "factory" will be injected with
    // dependencies. (This injector processes the Guice annotations.)
    Injector injector = Guice.createInjector(new LocalConfiguration());

    // get the product list object from Guide, with all the dependencies injected into
    ProductList productList = injector.getInstance(ProductList.class);

    // and do the work
    productList.listAll();
  }

}

//---------------------------- Revision History ----------------------------
//$Log: Application.java,v $
//Revision 1.1  2014/02/03 13:48:36  v.feurer
//feature: Google Guice: functional programming, DI and AOP demonstration
//
//
