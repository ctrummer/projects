/** 
 * Copyright 2014 SSI Schaefer PEEM GmbH. All Rights reserved. 
 * <br /> <br />
 * 
 * $Id: ToyToString.java,v 1.1 2014/02/03 13:48:36 v.feurer Exp $
 * <br /> <br />
 *
 */

package com.ssp.demo.guice.projects.customerTwo.implementation;

import javax.annotation.Nonnull;

import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.ssp.demo.guice.commons.Product;

/**
 * Custom function to convert an toy product to string.<br />
 * <br />
 * 
 * @author fev
 * @version $Revision: 1.1 $
 */

public class ToyToString implements Function<Product, String> {

  /** {@inheritDoc} */
  @Override
  public final String apply(@Nonnull final Product product) {
    return Objects.toStringHelper(product)
                  .add("WEIGHT", product.getWeight())
                  .add("ID", product.getId())
                  .add("NAME", product.getName())
                  .add("PRICE", product.getPrice())
                  .toString();
  }

}

//---------------------------- Revision History ----------------------------
//$Log: ToyToString.java,v $
//Revision 1.1  2014/02/03 13:48:36  v.feurer
//feature: Google Guice: functional programming, DI and AOP demonstration
//
//
