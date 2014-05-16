/** 
 * Copyright 2014 SSI Schaefer PEEM GmbH. All Rights reserved. 
 * <br /> <br />
 * 
 * $Id: BadgeProductProvider.java,v 1.1 2014/02/03 13:48:36 v.feurer Exp $
 * <br /> <br />
 *
 */

package com.ssp.demo.guice.projects.customerThree.implementation;

import java.security.SecureRandom;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.google.common.collect.Lists;
import com.google.inject.Provider;
import com.ssp.demo.guice.commons.Product;

/**
 * Implements an badge product provider.<br />
 * <br />
 * 
 * @author fev
 * @version $Revision: 1.1 $
 */

public class BadgeProductProvider implements Provider<Product> {

  /** Colors for the autos. */
  private static final List<String> COLORS = Lists.newArrayList("Alpha", "Bravo", "Charlie", "Delta", "Echo");

  /** Counter to generate unique IDs. */
  private static final AtomicLong ID_GENERATOR = new AtomicLong(System.currentTimeMillis() + 1);

  /** The random generator. */
  private static final SecureRandom RANDOM = new SecureRandom();

  /** The base price. */
  private static final int BASE_PRICE = 22;

  /** THe base weight. */
  private static final int BASE_WEIGHT = 1;

  /** Variation to produce random factors for price and weight. */
  private static final int VARIATION = 5;

  /** The multiplier to produce random price. */
  private static final int PRICE_MULTIPLIER = 2;

  /** The multiplier to produce random weight. */
  private static final int WEIGHT_MULTIPLIER = 1;

  /** {@inheritDoc} */
  @Override
  public Product get() {
    return new Product(ID_GENERATOR.getAndAdd(1000), COLORS.get(RANDOM.nextInt(COLORS.size())) + " badge", BASE_PRICE
      + (RANDOM.nextInt(VARIATION) * PRICE_MULTIPLIER), BASE_WEIGHT + (RANDOM.nextInt(VARIATION) * WEIGHT_MULTIPLIER));
  }

}

//---------------------------- Revision History ----------------------------
//$Log: BadgeProductProvider.java,v $
//Revision 1.1  2014/02/03 13:48:36  v.feurer
//feature: Google Guice: functional programming, DI and AOP demonstration
//
//
