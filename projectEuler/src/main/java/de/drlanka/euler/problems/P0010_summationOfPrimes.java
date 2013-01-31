package de.drlanka.euler.problems;

import java.util.Set;

import de.drlanka.euler.EulerProblem;
import de.drlanka.euler.lib.Prime;

public class P0010_summationOfPrimes implements EulerProblem {

  protected long upperPrimesBound=2_000_000;
  
  @Override
  public Object standardWay() {
    Set<Long> primesBelow = Prime.findAllPrimesBelow(upperPrimesBound);
    long sum=0;
    for(Long prime : primesBelow)
      sum+=prime.longValue();
    return Long.valueOf(sum);
  }

}
