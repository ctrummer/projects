
package com.spielwiese.simplex;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.math3.optim.OptimizationData;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.*;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;
import org.junit.Assert;

import com.ssp.common.logging.ILogger;
import com.ssp.common.logging.LogHelper;

public class TrialSimplex {

  private static final ILogger LOG = LogHelper.getLogger();
  
  
  
//  public void trialSimplex() {
//    
//    // non-linear (???)
//    // SimplexOptimizer optimizer = new SimplexOptimizer(0, 0);
//    // optimizer.optimize(optData)
//    
//    
//    // linear (!!!)
//    SimplexSolver solver = new SimplexSolver();
//    LinearConstraint constraints = null; // new LinearConstraint(null, null, 0);
//    OptimizationData optData = new LinearConstraintSet(constraints );
//    solver.optimize(optData);
//    
//    
//    
//    
//    
//    
//  }
  
//  public void trialExample() {
// // describe the optimization problem
//    LinearObjectiveFunction f = new LinearObjectiveFunction(new double[] { -2, 1 }, -5);
//    Collection<LinearConstraint> constraints = new ArrayList<LinearConstraint>();
//    constraints.add(new LinearConstraint(new double[] { 1, 2 }, Relationship.LEQ, 6));
//    constraints.add(new LinearConstraint(new double[] { 3, 2 }, Relationship.LEQ, 12));
//    constraints.add(new LinearConstraint(new double[] { 0, 1 }, Relationship.GEQ, 0));
//    
//    OptimizationData optData = new LinearConstraintSet(constraints );
//
//    // create and run the solver
//    new SimplexSolver().optimize(optData);
//    PointValuePair solution = new SimplexSolver().optimize(optData);
////    RealPointValuePair solution = new SimplexSolver().optimize(f, constraints, GoalType.MINIMIZE, false);
//    
//
//    // get the solution
//    double x = solution.getPoint()[0];
//    double y = solution.getPoint()[1];
//    double min = solution.getValue();
//    
//  }
  
  public static void main(String[] args) throws Exception { 
    TrialSimplex trial = new TrialSimplex();
    trial.testMath272();
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
  
  
  private void restrictions
  
 

}


//---------------------------- Revision History ----------------------------
//$Log$
//
