package de.drlanka.euler.problems;

import de.drlanka.euler.EulerProblem;

public class P0003_largestPrimeNumber implements EulerProblem {

  long number=600851475143L; 
  
  @Override
  public Object solve() {
    return findLargestPrimeFactor(number);
  }

  private long findLargestPrimeFactor(long currentNumber) {
    if(currentNumber==1)
      return currentNumber;
    if(currentNumber%2==0)
      return Math.max(2, findLargestPrimeFactor(currentNumber/2));
    long max=(long)Math.sqrt(currentNumber);
    for(long divisor=3;divisor<=max;divisor+=2)
      if(currentNumber%divisor==0)
        return Math.max(divisor, findLargestPrimeFactor(currentNumber/divisor));
    return currentNumber;
  }
  
}
