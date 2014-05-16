
package com.ssp.demo.guiceconfigured.core.objects.engines;

import java.util.Collection;

import javax.annotation.Nonnull;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.log4j.Logger;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.ssp.demo.guiceconfigured.core.OrderBuilder;
import com.ssp.demo.guiceconfigured.core.interfaces.IOrder;
import com.ssp.demo.guiceconfigured.core.interfaces.IOrderPool;
import com.ssp.demo.guiceconfigured.core.interfaces.IOrderProcessorEngine;
import com.ssp.demo.guiceconfigured.core.interfaces.IProcessor;

/**
 * <p>
 * DefaultOrderProcessorEngine provides a default implementation of the order processor engine.
 * </p>
 * <p>
 * DefaultOrderProcessorEngine is must not be freely exchanged between threads.
 * </p>
 * Copyright 2014 SSI Schaefer PEEM GmbH. All rights reserved.
 */

public final class DefaultOrderProcessorEngine implements IOrderProcessorEngine {

  /** The messages. */
  private static class Messages {
    /** Reports the number of objects to be populated. */
    public static final String INFO_WILL_POPULATE = "%s order(s) will be populated";

    /** Reports the order id to be rejected. */
    public static final String INFO_WILL_REJECT = "Order will be rejected when ID is: %s";

    /** Reports the rejection of an order. */
    public static final String INFO_REJECTED = "Order rejected by id: %s";

    /** Reports an order discarded. */
    public static final String INFO_DISCARDED = "Order discarded: %s";

    /** Reports an order processed. */
    public static final String INFO_PROCESSED = "Order processed: %s";
  }

  /** The logger. */
  private static final Logger LOG = Logger.getLogger(DefaultOrderProcessorEngine.class);

  /** The configuration. */
  @Inject
  private Configuration configuration;

  /** The injector to use for populating orders. */
  private Injector injector;

  /** The order pool. */
  @Inject
  private IOrderPool pool;

  /** The order processor. */
  @Inject
  private IProcessor processor;

  /** The number of messages processed. */
  private int processed = 0;

  /** The number of messages rejected. */
  private int rejected = 0;

  /** The number of messages discarded. */
  private int discarded = 0;

  /** {@inheritDoc} */
  @Override
  public void loadOrders() {
    final String reject = this.configuration.getString("engine.rejectId");
    if (reject != null) {
      LOG.info(String.format(Messages.INFO_WILL_REJECT, reject));
    }
    final Object prop = this.configuration.getProperty("orders.order.type");
    final int numberOfOrders = ((Collection<?>) prop).size();
    LOG.info(String.format(Messages.INFO_WILL_POPULATE, String.valueOf(numberOfOrders)));

    for (int i = 0; i < numberOfOrders; i++) {
      final HierarchicalConfiguration orderConfig =
        ((HierarchicalConfiguration) this.configuration).configurationAt(String.format("orders.order(%s)", String.valueOf(i)));
      final IOrder order = this.injector.getInstance(OrderBuilder.class).build(orderConfig);
      if (order.getId().equals(reject)) {
        LOG.info(String.format(Messages.INFO_REJECTED, order.toString()));
        this.rejected++;
        continue;
      }
      this.pool.add(order);
    }
  }

  /** {@inheritDoc} */
  @Override
  public void processOrders() {
    IOrder order = this.pool.getNext();
    while (order != null) {
      final boolean result = this.processor.process(order);
      if (result) {
        LOG.info(String.format(Messages.INFO_PROCESSED, order.toString()));
        this.processed++;
      } else {
        LOG.info(String.format(Messages.INFO_DISCARDED, order.toString()));
        this.discarded++;
      }
      order = this.pool.getNext();
    }
  }

  /** {@inheritDoc} */
  @Override
  public int getOrdersProcessed() {
    return this.processed;
  }

  /** {@inheritDoc} */
  @Override
  public int getOrdersRejected() {
    return this.rejected;
  }

  /** {@inheritDoc} */
  @Override
  public int getOrdersDiscarded() {
    return this.discarded;
  }

  /** {@inheritDoc} */
  @Override
  public void setInjector(@Nonnull final Injector injector) {
    this.injector = injector;
  }

}

//---------------------------- Revision History ----------------------------
//$Log: DefaultOrderProcessorEngine.java,v $
//Revision 1.1  2014/05/07 17:02:17  v.feurer
//[feature]: configurable DI with Google Guice
//
//
