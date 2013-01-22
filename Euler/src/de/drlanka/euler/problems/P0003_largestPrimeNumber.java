package de.drlanka.euler.problems;

import java.util.Collections;

import de.drlanka.euler.EulerProblem;
import de.drlanka.euler.EulerProblemTrivial;
import de.drlanka.euler.lib.Prime;

public class P0003_largestPrimeNumber implements EulerProblem, EulerProblemTrivial {

  private static final long NUMBER=600851475143L; 
  
  @Override
  public Object complicatedWay() {
    return Long.valueOf(findLargestPrimeFactor(NUMBER));
  }

  private long findLargestPrimeFactor(long number) {
    if(number==1)
      return number;
    if(number%2==0)
      return Math.max(2, findLargestPrimeFactor(number/2));
    long max=(long)Math.sqrt(number);
    for(long divisor=3;divisor<=max;divisor+=2)
      if(number%divisor==0)
        return Math.max(divisor, findLargestPrimeFactor(number/divisor));
    return number;
  }
  
  @Override
  public Object easyWay() {
    return Collections.max(Prime.findPrimes(NUMBER).keySet());
  }

}
