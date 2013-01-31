package de.drlanka.euler.problems;

import de.drlanka.euler.EulerProblem;
import de.drlanka.euler.lib.Prime;

public class P0010_summationOfPrimes implements EulerProblem {

  protected long upperPrimesBound=2_000_000;
  
  @Override
  public Object solve() {
    long sum=0;
    for(Long prime : Prime.getAllPrimesBelow(upperPrimesBound))
      sum+=prime.longValue();
    return Long.valueOf(sum);
  }

}
