
package com.spielwiese.simplex;

import org.apache.log4j.Logger;
import org.junit.AfterClass;

public class TrialSimplexUtilTest {

  static Logger logger = Logger.getLogger(TrialSimplexUtilTest.class);

  TrialSimplexUtil helper;

  public TrialSimplexUtilTest(TrialSimplexUtil helper) {
    this.helper = helper;
  }

  public void testPickEffort() {
    for (int index = 0; index < helper.getNumberOfLocations(); index++) {
      logger.info("index == " + index + " line == " + helper.calculatePblLine(index) + " column == " + helper.calculatePblColumn(index) + " effort == "
        + helper.calculatePickEffort(index));

    }
  }

  public void testIndexCalculation() {

    for (int skuIndex = 1; skuIndex <= helper.getNumberOfSKUs(); skuIndex++) {
      for (int locationIndex = 1; locationIndex <= helper.getNumberOfLocations(); locationIndex++) {
        int index = helper.calculateIndex(skuIndex, locationIndex);
        logger.info("skuIndex == " + skuIndex + " locationIndex == " + locationIndex + " index == " + index + " " + "skuIndexCalc == "
          + helper.calculateProduct(index) + " locationIndex == " + helper.calculateLocation(index));
      }
    }

  }

  @AfterClass
  public static void tearDownAfterClass() throws Exception {
  }

}

//---------------------------- Revision History ----------------------------
//$Log$
//
