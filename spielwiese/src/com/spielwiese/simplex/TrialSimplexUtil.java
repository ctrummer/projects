
package com.spielwiese.simplex;

import java.util.Date;

import org.apache.commons.math3.optim.PointValuePair;
import org.apache.log4j.Logger;

public class TrialSimplexUtil {

  static Logger logger = Logger.getLogger(TrialSimplexUtil.class);

  private int numberOfSKUs;
  private int numberOfLocations;
  private int numberOfPblLines;
  private int numberOfPblLocationsPerLine;
  private int[] pickAmount;

  public TrialSimplexUtil(int numberOfSKUs, int numberOfPblLines, int numberOfPblLocationsPerLine) {
    this.numberOfSKUs = numberOfSKUs;
    this.numberOfLocations = numberOfPblLines * numberOfPblLocationsPerLine;
    this.numberOfPblLines = numberOfPblLines;
    this.numberOfPblLocationsPerLine = numberOfPblLocationsPerLine;
    this.pickAmount = createExpectedPicksPerSku();
  }

  public static double[] createVector(int size) {
    double[] vector = new double[size];
    return vector;
  }

  public int calculateIndexFromSkuLocation(int skuIndex, int locationIndex) {
    int index = (skuIndex - 1) * getNumberOfLocations() + (locationIndex - 1);
    return index;
  }

  public int calculateLocationFromIndex(int index) {
    int locationIndex = (index % getNumberOfLocations()) + 1;
    return locationIndex;
  }

  public int calculateSkuFromIndex(int index) {
    int productIndex = (index / getNumberOfLocations()) + 1;
    return productIndex;
  }

  public int calcPblLocationIndex(int PblLineIndex, int PblColumnIndex) {
    return (PblLineIndex - 1) * getNumberOfPblLocationsPerLine() + PblColumnIndex - 1;
  }

  public int calcPblColumn(int locationIndex) {
    return (locationIndex % getNumberOfPblLocationsPerLine()) + 1;
  }

  public int calcPblLine(int locationIndex) {
    return (locationIndex / getNumberOfPblLocationsPerLine()) + 1;
  }

  public double calculatePickEffort(int locationIndex) {
    int pblLine = (locationIndex / getNumberOfPblLocationsPerLine()) + 1;
    int pblLocationInLine = (locationIndex % getNumberOfPblLocationsPerLine()) + 1;

    int relLine = Math.abs(pblLine - ((getNumberOfPblLines() / 2) + 1));
    int relLoc = Math.abs(pblLocationInLine - ((getNumberOfPblLocationsPerLine() / 2) + 1));

    return relLine + relLoc;
  }

  public int[] createExpectedPicksPerSku() {
    int[] pickAmounts = new int[getNumberOfSKUs()];
    for (int index = 0; index < getNumberOfSKUs(); index++) {
      pickAmounts[index] = 2 * (getNumberOfSKUs() - index);
    }
    return pickAmounts;
  }

  public void logResult(PointValuePair solution, Date start, Date stop) {
    TrialSimplex.logger.info(solution);
    Double solutionValue = solution.getValue();
    double[] solutionKey = solution.getKey();
    for (int keyIndex = 0; keyIndex < solutionKey.length; keyIndex++) {
      if (solutionKey[keyIndex] > 0.0) {
        TrialSimplex.logger.info(keyIndex + " == " + solutionKey[keyIndex] + " product ==  " + calculateSkuFromIndex(keyIndex) + " location == "
          + calculateLocationFromIndex(keyIndex));

      }
    }
    TrialSimplex.logger.info("Solution Value == " + solutionValue);

    logger.info("Calculation time ==  " + (stop.getTime() - start.getTime()));
    logger.info("SKUs == " + getNumberOfSKUs());
    logger.info("PBL Lines == " + getNumberOfPblLines());
    logger.info("Locations per line == " + getNumberOfPblLocationsPerLine());
    logger.info("Total locations ==  " + getNumberOfLocations());

  }

  public int getNumberOfSKUs() {
    return numberOfSKUs;
  }

  public int getNumberOfLocations() {
    return numberOfLocations;
  }

  public int getNumberOfPblLines() {
    return numberOfPblLines;
  }

  public int getNumberOfPblLocationsPerLine() {
    return numberOfPblLocationsPerLine;
  }

  public int[] getPickAmountPerSKU() {
    return pickAmount;
  }

}

//---------------------------- Revision History ----------------------------
//$Log$
//
