
package com.ssp.demo.guiceconfigured.objects.engines;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.ssp.demo.guiceconfigured.clients.one.LocalConfigurationClientOne;
import com.ssp.demo.guiceconfigured.core.interfaces.IOrderProcessorEngine;

/**
 * <p>
 * DefaultEngineTestClientOne provides methods to test the default engine with the client one
 * configuration.
 * </p>
 * Copyright 2014 SSI Schaefer PEEM GmbH. All rights reserved.
 */
public class DefaultEngineClientOneTest {

  /** The injector. */
  private Injector injector;

  /**
   * Setup.
   * 
   * @throws Exception If anything goes wrong
   */
  @Before
  public final void setup() throws Exception {
    this.injector = Guice.createInjector(new LocalConfigurationClientOne());
  }

  /**
   * Default test.
   */
  @Test
  public final void test() {
    final IOrderProcessorEngine engine = this.injector.getInstance(IOrderProcessorEngine.class);
    engine.setInjector(this.injector);
    engine.loadOrders();
    engine.processOrders();

    assertEquals(8, engine.getOrdersProcessed());
    assertEquals(1, engine.getOrdersRejected());
    assertEquals(1, engine.getOrdersDiscarded());
  }
}

//---------------------------- Revision History ----------------------------
//$Log: DefaultEngineClientOneTest.java,v $
//Revision 1.1  2014/05/07 17:02:17  v.feurer
//[feature]: configurable DI with Google Guice
//
//
