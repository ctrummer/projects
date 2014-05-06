
package com.spielwiese.simplex;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.LinearConstraint;
import org.apache.commons.math3.optim.linear.LinearConstraintSet;
import org.apache.commons.math3.optim.linear.LinearObjectiveFunction;
import org.apache.commons.math3.optim.linear.NonNegativeConstraint;
import org.apache.commons.math3.optim.linear.Relationship;
import org.apache.commons.math3.optim.linear.SimplexSolver;
import org.apache.log4j.Logger;

public class TrialSimplex {

  static Logger logger = Logger.getLogger(TrialSimplex.class);

  TrialSimplexUtil helper;

  public static void main(String[] args) throws Exception {
    TrialSimplex trial = new TrialSimplex(21, 3, 7);
    trial.optimizePblStationSlotting();
  }

  public TrialSimplex(int numberOfSKUs, int numberOfPblLines, int numberOfLocationsPerPblLine) {
    helper = new TrialSimplexUtil(numberOfSKUs, numberOfPblLines, numberOfLocationsPerPblLine);
  }

  public void optimizePblStationSlotting() {
    LinearObjectiveFunction objectiveFunction = createObjectiveFunction();

    Collection<LinearConstraint> constraints = createRestrictionOneLocationPerProduct();
    constraints.addAll(createRestrictionOneSKUperLocation());

    LinearConstraintSet set = new LinearConstraintSet(constraints);
    NonNegativeConstraint nonNegativeConstraint = new NonNegativeConstraint(true);

    SimplexSolver solver = new SimplexSolver();

    Date start = new Date();
    PointValuePair solution = solver.optimize(objectiveFunction, set, nonNegativeConstraint);
    Date stop = new Date();

    helper.logResult(solution, start, stop);

  }

  private LinearObjectiveFunction createObjectiveFunction() {
    // costs for picking
    // costs for moving in
    // costs for moving out

    double[] vector = TrialSimplexUtil.createVector(helper.getNumberOfSKUs() * helper.getNumberOfLocations());
    for (int skuIndex = 1; skuIndex <= helper.getNumberOfSKUs(); skuIndex++) {
      for (int locationIndex = 1; locationIndex <= helper.getNumberOfLocations(); locationIndex++) {
        double value = helper.getPickAmountPerSKU()[skuIndex - 1] * helper.calculatePickEffort(locationIndex - 1);
        vector[helper.calculateIndexFromSkuLocation(skuIndex, locationIndex)] = value;
      }
    }

    LinearObjectiveFunction f = new LinearObjectiveFunction(vector, 0);
    return f;
  }

  private Collection<LinearConstraint> createRestrictionOneSKUperLocation() {
    Collection<LinearConstraint> constraints = new ArrayList<LinearConstraint>();
    double[] vectorUpperLimit;

    for (int locationIndex = 1; locationIndex <= helper.getNumberOfLocations(); locationIndex++) {
      vectorUpperLimit = TrialSimplexUtil.createVector(helper.getNumberOfSKUs() * helper.getNumberOfLocations());
      for (int skuIndex = 1; skuIndex <= helper.getNumberOfSKUs(); skuIndex++) {
        vectorUpperLimit[helper.calculateIndexFromSkuLocation(skuIndex, locationIndex)] = 1.0;
      }
      constraints.add(new LinearConstraint(vectorUpperLimit, Relationship.EQ, 1));
    }
    return constraints;
  }

  private Collection<LinearConstraint> createRestrictionOneLocationPerProduct() {
    Collection<LinearConstraint> constraints = new ArrayList<LinearConstraint>();
    double[] vectorUpperLimit;

    for (int skuIndex = 1; skuIndex <= helper.getNumberOfSKUs(); skuIndex++) {
      vectorUpperLimit = TrialSimplexUtil.createVector(helper.getNumberOfSKUs() * helper.getNumberOfLocations());
      for (int locationIndex = 1; locationIndex <= helper.getNumberOfLocations(); locationIndex++) {
        vectorUpperLimit[helper.calculateIndexFromSkuLocation(skuIndex, locationIndex)] = 1.0;
      }
      constraints.add(new LinearConstraint(vectorUpperLimit, Relationship.EQ, 1));
    }
    return constraints;
  }

}
