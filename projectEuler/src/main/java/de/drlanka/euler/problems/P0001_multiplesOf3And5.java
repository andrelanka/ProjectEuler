package de.drlanka.euler.problems;

import de.drlanka.euler.EulerProblem;

public class P0001_multiplesOf3And5 implements EulerProblem {

  int upperBound=1000; 
  
  @Override
  public Object solve() {
    return Long.valueOf(sum(3)+sum(5)-sum(15));
  }
  
  private int sum(int i) {
    int count=(int)((upperBound-1)/i);
    return i*count*(count+1)/2;
  }

}
