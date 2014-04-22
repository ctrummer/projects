
package com.spielwiese.simplex;

import com.ssp.common.logging.ILogger;
import com.ssp.common.logging.LogHelper;


public class trial2 {

  private static final ILogger LOG = LogHelper.getLogger();
  
  
  
  import org.apache.commons.math.ode.FirstOrderDifferentialEquations;
  import org.apache.commons.math.ode.FirstOrderIntegrator;
  import org.apache.commons.math.ode.nonstiff.ClassicalRungeKuttaIntegrator;
  import org.apache.commons.math.optimization.GoalType;
  import org.apache.commons.math.optimization.RealPointValuePair;
  import org.apache.commons.math.optimization.linear.LinearConstraint;
  import org.apache.commons.math.optimization.linear.LinearObjectiveFunction;
  import org.apache.commons.math.optimization.linear.Relationship;
  import org.apache.commons.math.optimization.linear.SimplexSolver;
   
  import java.util.ArrayList;
  import java.util.Arrays;
  import java.util.Collection;
   
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

}


//---------------------------- Revision History ----------------------------
//$Log$
//
