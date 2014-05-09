
package com.testdemos;

public class PriceCalculator {

  public double calculatePrice(double basePrice, double extrasPrice, int numberOfExtras, double baseDiscount) {
    double price = basePrice * (1.0 - baseDiscount);

    return price;
  }

}

//---------------------------- Revision History ----------------------------
//$Log$
//
