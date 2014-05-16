/** 
 * Copyright 2014 SSI Schaefer PEEM GmbH. All Rights reserved. 
 * <br /> <br />
 * 
 * $Id: ProductInterceptor.java,v 1.1 2014/02/03 13:48:36 v.feurer Exp $
 * <br /> <br />
 *
 */

package com.ssp.demo.guice.projects.customerThree.implementation;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;

import com.ssp.demo.guice.commons.Product;

/**
 * We will catch the badges produced on the fly, and post an opinion about every instance.<br />
 * <br />
 * 
 * @author fev
 * @version $Revision: 1.1 $
 */

public class ProductInterceptor implements MethodInterceptor {

  /** Logger for posting the opinion. */
  private static final Logger LOG = Logger.getLogger(ProductInterceptor.class);

  /**
   * This is the method which will intercept the method calls.
   * 
   * @param invocation the method invocation
   * @returns Object created by the intercepted call
   */
  public Object invoke(final MethodInvocation invocation) throws Throwable {
    LOG.info("--- we will produce a new badge");
    Product product = (Product) invocation.proceed();
    if (product.getName().startsWith("Alpha")) {
      LOG.info("--- hurray, it is an ALPHA badge!!!!");
    } else {
      LOG.info("--- it is only a(n) " + product.getName() + ", better than nothing...");
    }
    return (product);
  }
}

//---------------------------- Revision History ----------------------------
//$Log: ProductInterceptor.java,v $
//Revision 1.1  2014/02/03 13:48:36  v.feurer
//feature: Google Guice: functional programming, DI and AOP demonstration
//
//
