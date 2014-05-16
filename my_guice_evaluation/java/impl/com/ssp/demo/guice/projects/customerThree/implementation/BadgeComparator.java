/** 
 * Copyright 2014 SSI Schaefer PEEM GmbH. All Rights reserved. 
 * <br /> <br />
 * 
 * $Id: BadgeComparator.java,v 1.1 2014/02/03 13:48:36 v.feurer Exp $
 * <br /> <br />
 *
 */

package com.ssp.demo.guice.projects.customerThree.implementation;

import java.io.Serializable;
import java.util.Comparator;

import com.ssp.demo.guice.commons.Product;

/**
 * Comparator for badge products.<br />
 * <br />
 * 
 * @author fev
 * @version $Revision: 1.1 $
 */

public class BadgeComparator implements Comparator<Product>, Serializable {

  /** Geneated ID. */
  private static final long serialVersionUID = 5571873106406979866L;

  //** {@inheritDoc} */
  @Override
  public final int compare(final Product o1, final Product o2) {
    // negate the result to provide descending order
    return (o1.getName().compareTo(o2.getName()) * -1);
  }

}

//---------------------------- Revision History ----------------------------
//$Log: BadgeComparator.java,v $
//Revision 1.1  2014/02/03 13:48:36  v.feurer
//feature: Google Guice: functional programming, DI and AOP demonstration
//
//
