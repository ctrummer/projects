
package com.ssp.demo.guiceconfigured.objects.processors;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.ssp.demo.guiceconfigured.clients.two.LocalConfigurationClientTwo;
import com.ssp.demo.guiceconfigured.core.interfaces.IProcessor;
import com.ssp.demo.guiceconfigured.core.objects.orders.ComplicatedOrder;
import com.ssp.demo.guiceconfigured.core.objects.orders.SimpleOrder;

/**
 * <p>
 * ComplicatedOrderProcessorTest provides methods to test the ComplicatedOrderProcessor.
 * </p>
 * Copyright 2014 SSI Schaefer PEEM GmbH. All rights reserved.
 */

public class ComplicatedOrderProcessorTest {

  /** A simple order. */
  private static final SimpleOrder SIMPLE = new SimpleOrder("1", "1", "1");

  /** A complicated order. */
  private static final ComplicatedOrder COMPLICATED = new ComplicatedOrder("1", "1", "1");

  /**
   * Default test.
   * 
   * @throws Exception If anything goes wrong
   */
  @Test
  public final void test() throws Exception {
    final Injector injector = Guice.createInjector(new LocalConfigurationClientTwo());
    final IProcessor processor = injector.getInstance(IProcessor.class);

    assertFalse(processor.process(SIMPLE));
    assertTrue(processor.process(COMPLICATED));
    assertTrue(processor.process(COMPLICATED));
    assertTrue(processor.process(COMPLICATED));
    assertTrue(processor.process(COMPLICATED));
    assertTrue(processor.process(COMPLICATED));
    assertTrue(processor.process(COMPLICATED));
    assertTrue(processor.process(COMPLICATED));
    assertTrue(processor.process(COMPLICATED));
    assertTrue(processor.process(COMPLICATED));
    assertTrue(processor.process(COMPLICATED));
    assertTrue(processor.process(COMPLICATED));
    assertTrue(processor.process(COMPLICATED));
    assertTrue(processor.process(COMPLICATED));
    assertTrue(processor.process(COMPLICATED));
    assertTrue(processor.process(COMPLICATED));
    assertTrue(processor.process(COMPLICATED));
    assertTrue(processor.process(COMPLICATED));
    assertTrue(processor.process(COMPLICATED));
    assertTrue(processor.process(COMPLICATED));
    assertTrue(processor.process(COMPLICATED));
    assertFalse(processor.process(COMPLICATED)); // limit (20) reached ;)
  }
}

//---------------------------- Revision History ----------------------------
//$Log: ComplicatedOrderProcessorTest.java,v $
//Revision 1.1  2014/05/07 17:02:17  v.feurer
//[feature]: configurable DI with Google Guice
//
//
