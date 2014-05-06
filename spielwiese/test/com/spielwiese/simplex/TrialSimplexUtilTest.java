
package com.spielwiese.simplex;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TrialSimplexUtilTest {

  static Logger logger = Logger.getLogger(TrialSimplexUtilTest.class);

  TrialSimplexUtil helper;

  @Before
  public void testInit() {
    helper = new TrialSimplexUtil(45, 3, 15);
  }

  @Test
  public void testPickEffort() {
    for (int index = 0; index < helper.getNumberOfLocations(); index++) {
      logger.info("index == " + index + " line == " + helper.calcPblLine(index) //
        + " column == " + helper.calcPblColumn(index) + " effort == " + helper.calculatePickEffort(index));

    }
  }

  @Test
  public void testVectorIndexHandling() {
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
    for (int lineIndex = 1; lineIndex <= helper.getNumberOfPblLines(); lineIndex++) {
      for (int columnIndex = 1; columnIndex <= helper.getNumberOfPblLocationsPerLine(); columnIndex++) {

        int pblIndex = helper.calcPblLocationIndex(lineIndex, columnIndex);
        Assert.assertEquals(lineIndex, helper.calcPblLine(pblIndex));
        Assert.assertEquals(columnIndex, helper.calcPblColumn(pblIndex));
      }
    }
  }

  // Pick Effort
  @Test
  public void testCalcPickEffort() {
    helper = new TrialSimplexUtil(32, 4, 8);
    String line = "\n";
    for (int locationIndex = 0; locationIndex < helper.getNumberOfLocations(); locationIndex++) {
      double effort = helper.calculatePickEffort(locationIndex);
      line += effort + " -- ";
      if (locationIndex % helper.getNumberOfPblLocationsPerLine() == 0) {
        line += "\n";
      }
    }
    logger.info(line);

  }

  // Pick Amount

  // creation empty Vector
}
