/** 
 * Copyright 2014 SSI Schaefer PEEM GmbH. All Rights reserved. 
 * <br /> <br />
 * 
 * $Id: AutoProductProvider.java,v 1.1 2014/02/03 13:48:36 v.feurer Exp $
 * <br /> <br />
 *
 */

package com.ssp.demo.guice.projects.customerOne.implementation;

import java.security.SecureRandom;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.google.common.collect.Lists;
import com.google.inject.Provider;
import com.ssp.demo.guice.commons.Product;

/**
 * Implements an automotive product provider.<br />
 * <br />
 * 
 * @author fev
 * @version $Revision: 1.1 $
 */

public class AutoProductProvider implements Provider<Product> {

  /** Colors for the autos. */
  private static final List<String> COLORS = Lists.newArrayList("Red", "Green", "Blue", "Black", "White");

  /** Counter to generate unique IDs. */
  private static final AtomicLong ID_GENERATOR = new AtomicLong(System.currentTimeMillis() + 1);

  /** The random generator. */
  private static final SecureRandom RANDOM = new SecureRandom();

  /** The base price. */
  private static final int BASE_PRICE = 3000;

  /** THe base weight. */
  private static final int BASE_WEIGHT = 1500;

  /** Variation to produce random factors for price and weight. */
  private static final int VARIATION = 30;

  /** The multiplier to produce random price. */
  private static final int PRICE_MULTIPLIER = 100;

  /** The multiplier to produce random weight. */
  private static final int WEIGHT_MULTIPLIER = 50;

  /** {@inheritDoc} */
  @Override
  public final Product get() {
    return new Product(ID_GENERATOR.getAndAdd(1000), COLORS.get(RANDOM.nextInt(COLORS.size())) + " car", BASE_PRICE
      + (RANDOM.nextInt(VARIATION) * PRICE_MULTIPLIER), BASE_WEIGHT + (RANDOM.nextInt(VARIATION) * WEIGHT_MULTIPLIER));
  }

}

//---------------------------- Revision History ----------------------------
//$Log: AutoProductProvider.java,v $
//Revision 1.1  2014/02/03 13:48:36  v.feurer
//feature: Google Guice: functional programming, DI and AOP demonstration
//
//
