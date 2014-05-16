
package com.ssp.demo.guiceconfigured.clients.one;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.log4j.Logger;

import com.google.inject.AbstractModule;
import com.ssp.demo.guiceconfigured.core.OrderBuilder;
import com.ssp.demo.guiceconfigured.core.interfaces.IOrderPool;
import com.ssp.demo.guiceconfigured.core.interfaces.IOrderProcessorEngine;
import com.ssp.demo.guiceconfigured.core.interfaces.IProcessor;
import com.ssp.demo.guiceconfigured.core.objects.engines.DefaultOrderProcessorEngine;
import com.ssp.demo.guiceconfigured.core.objects.orderpools.ArrayListOrderPool;
import com.ssp.demo.guiceconfigured.core.objects.processors.SimpleOrderProcessor;

/**
 * <p>
 * LocalConfigurationClientOne is the configuration for the client #1.
 * </p>
 * Copyright 2014 SSI Schaefer PEEM GmbH. All rights reserved.
 */

public class LocalConfigurationClientOne extends AbstractModule {

  /** The logger. */
  private static final Logger LOG = Logger.getLogger(LocalConfigurationClientOne.class);

  /** The external configuration. */
  private Configuration configuration;

  /** The order builder. */
  private OrderBuilder orderBuilder;

  /** {@inheritDoc} */
  @Override
  protected final void configure() {

    try {
      this.configuration = new XMLConfiguration("client_1.xml");

      this.orderBuilder = new OrderBuilder();

      this.bind(Configuration.class).toInstance(this.configuration);

      this.bind(OrderBuilder.class).toInstance(this.orderBuilder);

      this.bind(IOrderProcessorEngine.class).to(DefaultOrderProcessorEngine.class);

      this.bind(IOrderPool.class).to(ArrayListOrderPool.class);

      this.bind(IProcessor.class).to(SimpleOrderProcessor.class);

    } catch (final Exception e) {
      LOG.fatal("cannot configure injector", e);
    }
  }
}

//---------------------------- Revision History ----------------------------
//
//
