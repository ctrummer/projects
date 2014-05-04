package com.spielwiese.simplex;

import java.util.ArrayList;
import java.util.Collection;

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

    private int numberOfProducts;
    private int numberOfLocations;
    private int numberOfPblLines;
    private int numberOfLocationsPerPblLine;
    private int[] pickAmount;

    public static void main(String[] args) throws Exception {
        TrialSimplex trial = new TrialSimplex(15, 3, 5);
        // trial.testMath272();
        trial.optimizePblStationSlotting();
    }

    //
    // private void prepare() {
    // createVector(numberOfProducts * numberOfLocations);
    // }

    public TrialSimplex(int numberOfProducts, int numberOfPblLines, int numberOfLocationsPerPblLine) {
        this.numberOfProducts = numberOfProducts;
        this.numberOfLocations = numberOfPblLines * numberOfLocationsPerPblLine;
        this.numberOfPblLines = numberOfPblLines;
        this.numberOfLocationsPerPblLine = numberOfLocationsPerPblLine;
        this.pickAmount = createExpectedPicksPerSku();

        testIndexCalculation();
        testPickEffort();

    }

    private void testPickEffort() {
        for (int index = 0; index < numberOfLocations; index++) {
            logger.info("index == " + index + " line == " + calculatePblLine(index) + " column == "
                    + calculatePblColumn(index) + " effort == " + calculatePickEffort(index));

        }
    }

    private void testIndexCalculation() {
        for (int skuIndex = 1; skuIndex <= numberOfProducts; skuIndex++) {
            for (int locationIndex = 1; locationIndex <= numberOfLocations; locationIndex++) {
                int index = calculateIndex(skuIndex, locationIndex);
                logger.info("skuIndex == " + skuIndex + " locationIndex == " + locationIndex
                        + " index == " + index + " " + "skuIndexCalc == " + calculateProduct(index)
                        + " locationIndex == " + calculateLocation(index));
            }
        }

    }

    // public void testMath272() {
    // LinearObjectiveFunction f = new LinearObjectiveFunction(new double[] {2, 2, 1}, 0);
    // Collection<LinearConstraint> constraints = new ArrayList<LinearConstraint>();
    // constraints.add(new LinearConstraint(new double[] {1, 1, 0}, Relationship.GEQ, 1));
    // constraints.add(new LinearConstraint(new double[] {1, 0, 1}, Relationship.GEQ, 1));
    // constraints.add(new LinearConstraint(new double[] {0, 1, 0}, Relationship.GEQ, 1));
    //
    // LinearConstraintSet set = new LinearConstraintSet(constraints);
    //
    // org.apache.commons.math3.optim.linear.SimplexSolver solver = new SimplexSolver();
    // PointValuePair solution = solver.optimize(f, set);
    //
    // Assert.assertEquals(0.0, solution.getPoint()[0], .0000001);
    // Assert.assertEquals(1.0, solution.getPoint()[1], .0000001);
    // Assert.assertEquals(1.0, solution.getPoint()[2], .0000001);
    // Assert.assertEquals(3.0, solution.getValue(), .0000001);
    // }

    public void optimizePblStationSlotting() {
        LinearObjectiveFunction f = createObjectiveFunction();
        Collection<LinearConstraint> constraints = createRestrictionOneLocationPerProduct();
        constraints.addAll(createRestrictionOneSKUperLocation());

        LinearConstraintSet set = new LinearConstraintSet(constraints);

        NonNegativeConstraint nonNegativeConstraint = new NonNegativeConstraint(true);

        SimplexSolver solver = new SimplexSolver();
        PointValuePair solution = solver.optimize(f, set, nonNegativeConstraint);

        logger.info(solution);
        Double solutionValue = solution.getValue();
        double[] solutionKey = solution.getKey();
        for (int keyIndex = 0; keyIndex < solutionKey.length; keyIndex++) {
            if (solutionKey[keyIndex] > 0.0) {
                logger.info(keyIndex + " == " + solutionKey[keyIndex] + " product ==  "
                        + calculateProduct(keyIndex) + " location == "
                        + calculateLocation(keyIndex));

            }
        }
        logger.info("Solution Value == " + solutionValue);

    }

    private Collection<LinearConstraint> createRestrictionOneSKUperLocation() {
        Collection<LinearConstraint> constraints = new ArrayList<LinearConstraint>();
        double[] vectorUpperLimit;
        double[] vectorLowerLimit;
        for (int locationIndex = 1; locationIndex <= numberOfLocations; locationIndex++) {
            vectorUpperLimit = createVector(numberOfProducts * numberOfLocations);
            vectorLowerLimit = createVector(numberOfProducts * numberOfLocations);
            for (int skuIndex = 1; skuIndex <= numberOfProducts; skuIndex++) {
                vectorUpperLimit[calculateIndex(skuIndex, locationIndex)] = 1.0;
                vectorLowerLimit[calculateIndex(skuIndex, locationIndex)] = 1.0;
            }
            constraints.add(new LinearConstraint(vectorUpperLimit, Relationship.EQ, 1));
            // constraints.add(new LinearConstraint(vectorLowerLimit, Relationship.GEQ, 0));
        }
        return constraints;
    }

    private Collection<LinearConstraint> createRestrictionOneLocationPerProduct() {
        Collection<LinearConstraint> constraints = new ArrayList<LinearConstraint>();
        double[] vectorUpperLimit;
        double[] vectorLowerLimit;
        for (int skuIndex = 1; skuIndex <= numberOfProducts; skuIndex++) {
            vectorUpperLimit = createVector(numberOfProducts * numberOfLocations);
            vectorLowerLimit = createVector(numberOfProducts * numberOfLocations);
            for (int locationIndex = 1; locationIndex <= numberOfLocations; locationIndex++) {
                vectorUpperLimit[calculateIndex(skuIndex, locationIndex)] = 1.0;
                vectorLowerLimit[calculateIndex(skuIndex, locationIndex)] = 1.0;
            }
            constraints.add(new LinearConstraint(vectorUpperLimit, Relationship.EQ, 1));
            // constraints.add(new LinearConstraint(vectorLowerLimit, Relationship.GEQ, 0));

        }
        return constraints;
    }

    private LinearObjectiveFunction createObjectiveFunction() {
        // costs for picking
        // costs for moving in
        // costs for moving out

        double[] vector = createVector(numberOfProducts * numberOfLocations);
        for (int skuIndex = 1; skuIndex <= numberOfProducts; skuIndex++) {
            for (int locationIndex = 1; locationIndex <= numberOfLocations; locationIndex++) {
                double value = pickAmount[skuIndex - 1] * calculatePickEffort(locationIndex);
                vector[calculateIndex(skuIndex, locationIndex)] = value;
            }
        }

        LinearObjectiveFunction f = new LinearObjectiveFunction(vector, 0);

        return f;
    }

    private int[] createExpectedPicksPerSku() {
        int[] pickAmounts = new int[numberOfProducts];
        for (int index = 0; index < numberOfProducts; index++) {
            pickAmounts[index] = numberOfProducts - index;
        }
        return pickAmounts;
    }

    private double calculatePickEffort(int locationIndex) {
        int pblLine = (locationIndex / numberOfLocationsPerPblLine) + 1;
        int pblLocationInLine = (locationIndex % numberOfLocationsPerPblLine) + 1;

        int relLine = Math.abs(pblLine - ((numberOfPblLines / 2) + 1));
        int relLoc = Math.abs(pblLocationInLine - ((numberOfLocationsPerPblLine / 2) + 1));

        return relLine + relLoc;
    }

    private int caluculateLocationIndex(int PblLineIndex, int PblColumnIndex) {
        return (PblLineIndex - 1) * numberOfLocationsPerPblLine + PblColumnIndex - 1;
    }

    private int calculatePblLine(int locationIndex) {
        return (locationIndex / numberOfLocationsPerPblLine) + 1;
    }

    private int calculatePblColumn(int locationIndex) {
        return (locationIndex % numberOfLocationsPerPblLine) + 1;
    }

    private int calculateIndex(int skuIndex, int locationIndex) {
        int index = (skuIndex - 1) * numberOfLocations + locationIndex - 1;
        // logger.debug("calculateIndex == " + index);
        return index;
    }

    private int calculateLocation(int index) {
        int locationIndex = (index % numberOfLocations) + 1;
        return locationIndex;
    }

    private int calculateProduct(int index) {
        int productIndex = (index / numberOfLocations) + 1;
        return productIndex;
    }

    private double[] createVector(int size) {
        double[] vector = new double[size];
        logger.debug("size of created vector is == " + vector.length);
        return vector;
    }

    // private String vectorToString(double vector[]) {
    // String textString = "\n"; // FIXME StringBuffer
    // for (int index = 0; index < vector.length; index++) {
    // textString += " " + index + " " + " == " + " " + vector[index];
    // if (index % numberOfLocations == 0) {
    // textString += "\n";
    // }
    // }
    // return textString;
    // }

}
