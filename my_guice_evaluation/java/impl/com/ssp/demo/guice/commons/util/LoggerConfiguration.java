/** 
 * Copyright 2014 SSI Schaefer PEEM GmbH. All Rights reserved. 
 * <br /> <br />
 * 
 * $Id: LoggerConfiguration.java,v 1.1 2014/02/03 13:48:36 v.feurer Exp $
 * <br /> <br />
 *
 */

package com.ssp.demo.guice.commons.util;

import java.util.Properties;

/**
 * A simple logger configuration. Just for the testing.<br />
 * <br />
 * 
 * @author fev
 * @version $Revision: 1.1 $
 */

public class LoggerConfiguration {

  /** Protection. */
  private LoggerConfiguration() {
    // empty on purpose
  }

  /**
   * Returns the logger configuration.
   * 
   * @return Property object containing Log4J configuration
   */
  public static final Properties getConfiguration() {
    Properties props = new Properties();
    props.put("log4j.rootLogger", "ERROR, console");
    props.put("log4j.logger.com.ssp", "DEBUG");
    props.put("log4j.appender.console", "org.apache.log4j.ConsoleAppender");
    props.put("log4j.appender.console.target", "System.out");
    props.put("log4j.appender.console.layout", "org.apache.log4j.PatternLayout");
    props.put("log4j.appender.console.layout.ConversionPattern", "%d %m%n");
    return props;
  }
}

//---------------------------- Revision History ----------------------------
//$Log: LoggerConfiguration.java,v $
//Revision 1.1  2014/02/03 13:48:36  v.feurer
//feature: Google Guice: functional programming, DI and AOP demonstration
//
//
