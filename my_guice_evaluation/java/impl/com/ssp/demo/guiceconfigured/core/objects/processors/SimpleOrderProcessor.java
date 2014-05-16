
package com.ssp.demo.guiceconfigured.core.objects.processors;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.annotation.Nonnull;
import javax.inject.Inject;

import org.apache.commons.configuration.Configuration;

import com.ssp.demo.guiceconfigured.core.interfaces.IOrder;
import com.ssp.demo.guiceconfigured.core.interfaces.IProcessor;

/**
 * <p>
 * SimpleOrderProcessor provides a processor to process simple orders.
 * </p>
 * <p>
 * SimpleOrderProcessor is immutable and may be freely exchanged between threads. <br />
 * Calls to methods of SimpleOrderProcessor are thread safe.
 * </p>
 * Copyright 2014 SSI Schaefer PEEM GmbH. All rights reserved.
 */

public final class SimpleOrderProcessor implements IProcessor {

  /** The error message complaining about a value must not be null. */
  private static final String ERR_NOT_NULL = "'%s' must not be null";

  /** The processing limit. */
  private int limit;

  /** The counter for processed orders. */
  private int processed = 0;

  /** The configuration. */
  @Inject
  private Configuration config;

  /** {@inheritDoc} */
  @Override
  public boolean process(@Nonnull final IOrder order) {
    checkNotNull(order, String.format(ERR_NOT_NULL, "order"));
    this.limit = this.config.getInt("processor.limit", 5);
    boolean result = false;
    if (this.processed < this.limit && order.getType().equals("simpleOrder")) {
      result = true;
      this.processed++;
    }
    return result;
  }

}

//---------------------------- Revision History ----------------------------
//$Log: SimpleOrderProcessor.java,v $
//Revision 1.1  2014/05/07 17:02:17  v.feurer
//[feature]: configurable DI with Google Guice
//
//
