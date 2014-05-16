
package com.ssp.demo.guiceconfigured.core.objects.orders;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.base.Objects;
import com.ssp.demo.guiceconfigured.core.interfaces.IOrder;

/**
 * <p>
 * ComplicatedOrder is a complicated order implementation.
 * </p>
 * <p>
 * ComplicatedOrder is immutable and may be freely exchanged between threads. <br />
 * Calls to methods of ComplicatedOrder are thread safe.
 * </p>
 * Copyright 2014 SSI Schaefer PEEM GmbH. All rights reserved.
 */

public final class ComplicatedOrder implements IOrder {

  /** The error message complaining about a value must not be null. */
  private static final String ERR_NOT_NULL = "'%s' must not be null";

  /** The order type. */
  private final String type;

  /** The ID of the order. */
  private final String id;

  /** The description of the order. */
  private final String description;

  /** The details of the order. */
  private final Object details;

  /**
   * Creates a complicated order.
   * 
   * @param id The id of the order.
   * @param description The description of the order
   * @param details The details of the order
   */
  public ComplicatedOrder(@Nonnull final String id, @Nullable final String description, @Nullable final Object details) {
    checkNotNull(id, String.format(ERR_NOT_NULL, "id"));
    this.type = "complicatedOrder";
    this.id = id;
    this.description = description;
    this.details = details;
  }

  /** {@inheritDoc} */
  @Override
  public String getType() {
    return this.type;
  }

  /** {@inheritDoc} */
  @Override
  public String getId() {
    return this.id;
  }

  /** {@inheritDoc} */
  @Override
  public String getDescription() {
    return this.description;
  }

  /** {@inheritDoc} */
  @Override
  public Object getDetails() {
    return this.details;
  }

  /** {@inheritDoc} */
  @Override
  public String toString() {
    return Objects.toStringHelper(this)
                  .omitNullValues()
                  .add("type", this.type)
                  .add("id", this.id)
                  .add("desc", this.description)
                  .add("hasDetails", this.details != null)
                  .toString();
  }
}

//---------------------------- Revision History ----------------------------
//$Log: ComplicatedOrder.java,v $
//Revision 1.1  2014/05/07 17:02:17  v.feurer
//[feature]: configurable DI with Google Guice
//
//
