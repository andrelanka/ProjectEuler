package de.drlanka.euler.problems;

import java.util.List;

import de.drlanka.euler.EulerProblem;
import de.drlanka.euler.lib.Prime;

public class P0010_summationOfPrimes implements EulerProblem {

  protected int upperPrimesBound=2_000_000;
  
  @Override
  public Object standardWay() {
    List<Integer> primesBelow = Prime.findAllPrimesBelow(upperPrimesBound);
    long sum=0;
    for(Integer prime : primesBelow)
      sum+=prime.intValue();
    return Long.valueOf(sum);
  }

}
