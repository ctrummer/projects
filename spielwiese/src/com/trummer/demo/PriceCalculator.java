
package com.trummer.demo;

import com.ssp.common.logging.ILogger;
import com.ssp.common.logging.LogHelper;


public class PriceCalculator {

  private static final ILogger LOG = LogHelper.getLogger();
  
  public double calculatePrice (double basePrice, double extrasPrice, int numberOfExtras, double baseDiscount) {
    double price = basePrice * (1.0 - baseDiscount);
    
    
    
    return price;
  }

}


//---------------------------- Revision History ----------------------------
//$Log$
//
