package com.spielwiese.trial;

import org.apache.commons.math.ode.FirstOrderDifferentialEquations;
import org.apache.commons.math.ode.FirstOrderIntegrator;
import org.apache.commons.math.ode.nonstiff.ClassicalRungeKuttaIntegrator;
import org.apache.commons.math.optimization.GoalType;
import org.apache.commons.math.optimization.RealPointValuePair;
import org.apache.commons.math.optimization.linear.LinearConstraint;
import org.apache.commons.math.optimization.linear.LinearObjectiveFunction;
import org.apache.commons.math.optimization.linear.Relationship;
import org.apache.commons.math.optimization.linear.SimplexSolver;
 
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class trial2 {

  static Logger logger = Logger.getLogger(trial2.class);
  
  
  

   
  public class Main {
   
  /*
  Объявление класса нашего диффура
  */
  private static class CircleODE implements FirstOrderDifferentialEquations {
   
  private double[] c;
  private double omega;
   
  public CircleODE(double[] c, double omega) {
  this.c = c;
  this.omega = omega;
  }
   
  public int getDimension() {
  return 2;
  }
  //Получает значения y' по y
  public void computeDerivatives(double t, double[] y, double[] yDot) {
  yDot[0] = omega * (c[1] - y[1]);
  yDot[1] = omega * (y[0] - c[0]);
  }
   
  }
   
   
  public static void main(String[] args) throws Exception {
  {
  // описание задачи (целевая функция и ограничения)
  LinearObjectiveFunction f = new LinearObjectiveFunction(new double[]{-2, 1}, -5);
  Collection constraints = new ArrayList();
  constraints.add(new LinearConstraint(new double[]{1, 2}, Relationship.LEQ, 6));
  constraints.add(new LinearConstraint(new double[]{3, 2}, Relationship.LEQ, 12));
  constraints.add(new LinearConstraint(new double[]{0, 1}, Relationship.GEQ, 0));
   
  // решаем
  SimplexSolver ss = new SimplexSolver();
  RealPointValuePair solution = new SimplexSolver().optimize(f, constraints, GoalType.MINIMIZE, false);
   
   
  // получаем результат
  double x = solution.getPoint()[0];
  double y = solution.getPoint()[1];
  double min = solution.getValue();
  System.out.println(x + " " + y + " " + min);
  }
   
  FirstOrderIntegrator runge = new ClassicalRungeKuttaIntegrator(1.0e-1);
  FirstOrderDifferentialEquations ode = new CircleODE(new double[]{1.0, 1.0}, 0.1);
  double[] y = new double[]{0.0, 1.0}; // initial state
  runge.integrate(ode, 0.0, y, 16.0, y); // now y contains final state at time t=16.0
  System.out.println(Arrays.toString(y));
  }
  }

  
  
  
//public void trialSimplex() {
//  
//  // non-linear (???)
//  // SimplexOptimizer optimizer = new SimplexOptimizer(0, 0);
//  // optimizer.optimize(optData)
//  
//  
//  // linear (!!!)
//  SimplexSolver solver = new SimplexSolver();
//  LinearConstraint constraints = null; // new LinearConstraint(null, null, 0);
//  OptimizationData optData = new LinearConstraintSet(constraints );
//  solver.optimize(optData);
//  
//  
//  
//  
//  
//  
//}

//public void trialExample() {
//// describe the optimization problem
//  LinearObjectiveFunction f = new LinearObjectiveFunction(new double[] { -2, 1 }, -5);
//  Collection<LinearConstraint> constraints = new ArrayList<LinearConstraint>();
//  constraints.add(new LinearConstraint(new double[] { 1, 2 }, Relationship.LEQ, 6));
//  constraints.add(new LinearConstraint(new double[] { 3, 2 }, Relationship.LEQ, 12));
//  constraints.add(new LinearConstraint(new double[] { 0, 1 }, Relationship.GEQ, 0));
//  
//  OptimizationData optData = new LinearConstraintSet(constraints );
//
//  // create and run the solver
//  new SimplexSolver().optimize(optData);
//  PointValuePair solution = new SimplexSolver().optimize(optData);
////  RealPointValuePair solution = new SimplexSolver().optimize(f, constraints, GoalType.MINIMIZE, false);
//  
//
//  // get the solution
//  double x = solution.getPoint()[0];
//  double y = solution.getPoint()[1];
//  double min = solution.getValue();
//  
//}


  
  
  
  
  
}


//---------------------------- Revision History ----------------------------
//$Log$
//
