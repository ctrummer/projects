
package com.ssp.demo.guiceconfigured.clients.two;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.ssp.demo.guice.commons.util.LoggerConfiguration;
import com.ssp.demo.guice.projects.customerOne.Application;
import com.ssp.demo.guiceconfigured.core.interfaces.IOrderProcessorEngine;

/**
 * <p>
 * ApplicationClientTwo provides a simple sample application.
 * </p>
 * Copyright 2014 SSI Schaefer PEEM GmbH. All rights reserved.
 */

public final class ApplicationClientTwo {

  /** The logger. */
  private static final Logger LOG = Logger.getLogger(Application.class);

  /**
   * Demo application.
   * 
   * @param args the arguments of the program, not used
   */
  public static void main(final String[] args) {
    PropertyConfigurator.configure(LoggerConfiguration.getConfiguration());
    final Injector injector = Guice.createInjector(new LocalConfigurationClientTwo());
    final IOrderProcessorEngine engine = injector.getInstance(IOrderProcessorEngine.class);
    engine.setInjector(injector);
    engine.loadOrders();
    engine.processOrders();
  }
}

//---------------------------- Revision History ----------------------------
//$Log: ApplicationClientTwo.java,v $
//Revision 1.1  2014/05/07 17:02:17  v.feurer
//[feature]: configurable DI with Google Guice
//
//
