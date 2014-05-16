/** 
 * Copyright 2014 SSI Schaefer PEEM GmbH. All Rights reserved. 
 * <br /> <br />
 * 
 * $Id: ToyComparator.java,v 1.1 2014/02/03 13:48:36 v.feurer Exp $
 * <br /> <br />
 *
 */

package com.ssp.demo.guice.projects.customerTwo.implementation;

import java.io.Serializable;
import java.util.Comparator;

import com.ssp.demo.guice.commons.Product;

/**
 * Comparator for toy products.<br />
 * <br />
 * 
 * @author fev
 * @version $Revision: 1.1 $
 */

public class ToyComparator implements Comparator<Product>, Serializable {

  /** Generated ID. */
  private static final long serialVersionUID = 6671320984492589235L;

  //** {@inheritDoc} */
  @Override
  public final int compare(final Product o1, final Product o2) {
    // negate the result to provide descending order
    return Long.valueOf(o1.getWeight()).compareTo(Long.valueOf(o2.getWeight())) * -1;
  }

}

//---------------------------- Revision History ----------------------------
//$Log: ToyComparator.java,v $
//Revision 1.1  2014/02/03 13:48:36  v.feurer
//feature: Google Guice: functional programming, DI and AOP demonstration
//
//
