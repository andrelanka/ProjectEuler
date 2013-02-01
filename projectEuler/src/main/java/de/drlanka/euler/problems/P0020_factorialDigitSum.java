package de.drlanka.euler.problems;

import java.math.BigInteger;

import de.drlanka.euler.EulerProblem;
import de.drlanka.euler.lib.NumberStrings;

public class P0020_factorialDigitSum implements EulerProblem {
  
  int n=100;

  @Override
  public Object solve() {
    
    BigInteger fac=BigInteger.ONE;
    for(int i=2;i<=n;i++)
      fac=fac.multiply(new  BigInteger(""+i));

    return NumberStrings.digitSum(fac.toString());
  }

}
