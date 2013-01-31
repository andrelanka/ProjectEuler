package de.drlanka.euler.problems;

import de.drlanka.euler.EulerProblem;

public class P0006_sumSquareDifference implements EulerProblem {

  int bound=100;
  
  @Override
  public Object solve() {
    long sumSquares=0;
    long sum=0;
    for(int i=1;i<=bound;i++) {
      sumSquares+=i*i;
      sum+=i;
    }
    return sum*sum-sumSquares;
  }
  
}
