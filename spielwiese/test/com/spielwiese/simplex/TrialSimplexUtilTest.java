
package com.spielwiese.simplex;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

public class TrialSimplexUtilTest {

  static Logger logger = Logger.getLogger(TrialSimplexUtilTest.class);

  TrialSimplexUtil helper;

  public void testInit() {
    helper = new TrialSimplexUtil(15, 3, 15);
  }

  @Test
  public void testPickEffort() {
    testInit();

    for (int index = 0; index < helper.getNumberOfLocations(); index++) {
      logger.info("index == " + index + " line == " + helper.calcPblLine(index) //
        + " column == " + helper.calcPblColumn(index) + " effort == " + helper.calculatePickEffort(index));

    }
  }

  @Test
  public void testVectorIndexHandling() {
    testInit();

    for (int skuIndex = 1; skuIndex <= helper.getNumberOfSKUs(); skuIndex++) {
      for (int locationIndex = 1; locationIndex <= helper.getNumberOfLocations(); locationIndex++) {
        int index = helper.calculateIndexFromSkuLocation(skuIndex, locationIndex);

        Assert.assertEquals(skuIndex, helper.calculateSkuFromIndex(index));
        Assert.assertEquals(locationIndex, helper.calculateLocationFromIndex(index));
      }
    }
  }

  @Test
  public void testPblIndexHandling() {
    testInit();

    for (int lineIndex = 1; lineIndex <= helper.getNumberOfPblLines(); lineIndex++) {
      for (int columnIndex = 1; columnIndex <= helper.getNumberOfPblLocationsPerLine(); columnIndex++) {

        int pblIndex = helper.calcPblLocationIndex(lineIndex, columnIndex);
        Assert.assertEquals(lineIndex, helper.calcPblLine(pblIndex));
        Assert.assertEquals(columnIndex, helper.calcPblColumn(pblIndex));
      }
    }
  }

  // Pick Effort

  // Pick Amount

  // creation empty Vector
}
