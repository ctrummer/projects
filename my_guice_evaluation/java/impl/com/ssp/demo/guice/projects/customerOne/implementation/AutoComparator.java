/** 
 * Copyright 2014 SSI Schaefer PEEM GmbH. All Rights reserved. 
 * <br /> <br />
 * 
 * $Id: AutoComparator.java,v 1.1 2014/02/03 13:48:36 v.feurer Exp $
 * <br /> <br />
 *
 */

package com.ssp.demo.guice.projects.customerOne.implementation;

import java.io.Serializable;
import java.util.Comparator;

import com.ssp.demo.guice.commons.Product;

/**
 * Comparator for automotive products.<br />
 * <br />
 * 
 * @author fev
 * @version $Revision: 1.1 $
 */

public class AutoComparator implements Comparator<Product>, Serializable {

  /** Generated serial ID. */
  private static final long serialVersionUID = 7951332092518131526L;

  //** {@inheritDoc} */
  @Override
  public final int compare(final Product o1, final Product o2) {
    // negate the result to provide descending order
    return Long.valueOf(o1.getPrice()).compareTo(Long.valueOf(o2.getPrice())) * -1;
  }

}

//---------------------------- Revision History ----------------------------
//$Log: AutoComparator.java,v $
//Revision 1.1  2014/02/03 13:48:36  v.feurer
//feature: Google Guice: functional programming, DI and AOP demonstration
//
//
