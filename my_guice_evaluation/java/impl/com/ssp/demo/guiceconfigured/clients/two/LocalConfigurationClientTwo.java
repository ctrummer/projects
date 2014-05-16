
package com.ssp.demo.guiceconfigured.clients.two;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.log4j.Logger;

import com.google.inject.AbstractModule;
import com.ssp.demo.guiceconfigured.core.OrderBuilder;
import com.ssp.demo.guiceconfigured.core.interfaces.IOrderPool;
import com.ssp.demo.guiceconfigured.core.interfaces.IOrderProcessorEngine;
import com.ssp.demo.guiceconfigured.core.interfaces.IProcessor;
import com.ssp.demo.guiceconfigured.core.objects.engines.DefaultOrderProcessorEngine;
import com.ssp.demo.guiceconfigured.core.objects.orderpools.LinkedListOrderPool;
import com.ssp.demo.guiceconfigured.core.objects.processors.ComplicatedOrderProcessor;

/**
 * <p>
 * LocalConfigurationClientOne is the configuration for the client #2.
 * </p>
 * Copyright 2014 SSI Schaefer PEEM GmbH. All rights reserved.
 */

public class LocalConfigurationClientTwo extends AbstractModule {

  /** The logger. */
  private static final Logger LOG = Logger.getLogger(LocalConfigurationClientTwo.class);

  /** The external configuration. */
  private Configuration configuration;

  /** The order builder. */
  private OrderBuilder orderBuilder;

  /** {@inheritDoc} */
  @Override
  protected final void configure() {

    try {
      this.configuration = new XMLConfiguration("client_2.xml");

      this.orderBuilder = new OrderBuilder();

      this.bind(Configuration.class).toInstance(this.configuration);

      this.bind(OrderBuilder.class).toInstance(this.orderBuilder);

      this.bind(IOrderProcessorEngine.class).to(DefaultOrderProcessorEngine.class);

      this.bind(IOrderPool.class).to(LinkedListOrderPool.class);

      this.bind(IProcessor.class).to(ComplicatedOrderProcessor.class);

    } catch (final Exception e) {
      LOG.fatal("cannot configure injector", e);
    }
  }
}

//---------------------------- Revision History ----------------------------
//
//
