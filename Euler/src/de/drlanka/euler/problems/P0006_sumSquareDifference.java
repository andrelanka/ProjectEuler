package de.drlanka.euler.problems;

import de.drlanka.euler.EulerProblem;

public class P0006_sumSquareDifference implements EulerProblem {

  private static final int BOUND=100;
  
  @Override
  public Object standardWay() {
    long sumSquares=0;
    long sum=0;
    for(int i=1;i<=BOUND;i++) {
      sumSquares+=i*i;
      sum+=i;
    }
    return Long.valueOf(sum*sum-sumSquares);
  }
  
}
