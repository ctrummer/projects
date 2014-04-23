
package com.spielwiese.simplex;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.math3.optim.OptimizationData;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.*;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;
import org.apache.log4j.Logger;
import org.junit.Assert;

public class TrialSimplex {
  
  static Logger logger = Logger.getLogger(TrialSimplex.class);
  
  private int numberOfProducts;
  private int numberOfLocations;
  private int numberOfPblLines;
  private int numberOfLocationsPerPblLine;
  
  public static void main(String[] args) throws Exception { 
    TrialSimplex trial = new TrialSimplex(10,5,15);
    trial.testMath272();
    trial.createVector();
  }

  public TrialSimplex(int numberOfProducts, int numberOfPblLines, int numberOfLocationsPerPblLine) {
    this.numberOfProducts = numberOfProducts;
    this.numberOfLocations = numberOfPblLines * numberOfLocationsPerPblLine;
    this.numberOfPblLines = numberOfPblLines;
    this.numberOfLocationsPerPblLine = numberOfLocationsPerPblLine;
  }

  
  public void testMath272() {
    LinearObjectiveFunction f = new LinearObjectiveFunction(new double[] { 2, 2, 1 }, 0);
    Collection<LinearConstraint> constraints = new ArrayList<LinearConstraint>();
    constraints.add(new LinearConstraint(new double[] { 1, 1, 0 }, Relationship.GEQ,  1));
    constraints.add(new LinearConstraint(new double[] { 1, 0, 1 }, Relationship.GEQ,  1));
    constraints.add(new LinearConstraint(new double[] { 0, 1, 0 }, Relationship.GEQ,  1));
    
    LinearConstraintSet set = new LinearConstraintSet(constraints);

    org.apache.commons.math3.optim.linear.SimplexSolver solver = new SimplexSolver();
    PointValuePair solution =  solver.optimize(f, set);

    Assert.assertEquals(0.0, solution.getPoint()[0], .0000001);
    Assert.assertEquals(1.0, solution.getPoint()[1], .0000001);
    Assert.assertEquals(1.0, solution.getPoint()[2], .0000001);
    Assert.assertEquals(3.0, solution.getValue(), .0000001);
}
  
/**
 *  1..m SKUs
 *  1..n Locations
 *  xi for 1 <= i <= m*n 
 *   
 *   
 */
  
  private void restriction() {
    Collection<LinearConstraint> constraints = new ArrayList<LinearConstraint>();
    constraints.add(new LinearConstraint(new double[] { 1, 1, 0 }, Relationship.GEQ,  1));
    
    
  }
  
  private double[] createVector() {
    int size = numberOfProducts * numberOfLocations;
    double[] vector = new double[size];
    logger.info("vector initial is: " + vectorToString(vector));
    
    
    return vector;
  }
  
  private String vectorToString(double vector[]) {
    String textString = ""; // FIXME StringBuffer
    for (int index = 0; index < vector.length; index++) {
      textString += " " + index + " " + " == " + " " + vector[index];
    }
    return textString;
  }
  
 

}


//---------------------------- Revision History ----------------------------
//$Log$
//
