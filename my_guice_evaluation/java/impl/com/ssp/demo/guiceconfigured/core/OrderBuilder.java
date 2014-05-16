
package com.ssp.demo.guiceconfigured.core;

import javax.annotation.Nonnull;

import org.apache.commons.configuration.Configuration;

import com.ssp.demo.guiceconfigured.core.interfaces.IOrder;
import com.ssp.demo.guiceconfigured.core.objects.orders.ComplicatedOrder;
import com.ssp.demo.guiceconfigured.core.objects.orders.SimpleOrder;

/**
 * <p>
 * OrderProvider provides a builder for creating new orders.
 * </p>
 * Copyright 2014 SSI Schaefer PEEM GmbH. All rights reserved.
 */

public final class OrderBuilder {

  /**
   * Builds a new order by a configuration.
   * 
   * @param config The configuration
   * @return The order instance
   */
  @SuppressWarnings("static-method")
  public IOrder build(@Nonnull final Configuration config) {

    IOrder order = null;
    if (config.getString("type").equals("simple")) {
      order = new SimpleOrder(config.getString("id"), config.getString("desc"), config.getString("details"));
    }
    if (config.getString("type").equals("complicated")) {
      order = new ComplicatedOrder(config.getString("id"), config.getString("desc"), config.getString("details"));
    }
    return order;
  }

}

//---------------------------- Revision History ----------------------------
//$Log: OrderBuilder.java,v $
//Revision 1.1  2014/05/07 17:02:17  v.feurer
//[feature]: configurable DI with Google Guice
//
//
