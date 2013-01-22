package de.drlanka.euler.problems;

import de.drlanka.euler.EulerProblem;
import de.drlanka.euler.EulerProblemTrivial;

public class P0001_multiplesOf3And5 implements EulerProblem, EulerProblemTrivial {

  private static final int BELOW=1000; 
  
  @Override
  public Object complicatedWay() {
    return Long.valueOf(sum(3)+sum(5)-sum(15));
  }
  
  

  private int sum(int i) {
    int count=(int)((BELOW-1)/i);
    return i*count*(count+1)/2;
  }



  @Override
  public Object easyWay() {
    long sum = 0;
    for (int i = 0; i < 1000; i++)
      if (i % 3 == 0 || i % 5 == 0)
        sum += i;
    return Long.valueOf(sum);
  }

}
