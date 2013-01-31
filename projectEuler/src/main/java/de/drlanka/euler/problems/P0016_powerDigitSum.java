package de.drlanka.euler.problems;

import java.math.BigInteger;

import de.drlanka.euler.EulerProblem;
import de.drlanka.euler.lib.NumberStrings;

public class P0016_powerDigitSum implements EulerProblem {
  
  int power=1000;

  @Override
  public Object solve() {
    
    String number=BigInteger.ONE.shiftLeft(power).toString();
    
    long sum=0;
    for(int i=number.length()-1;i>=0;i--)
      sum+=NumberStrings.decodeString(number, i, 1);
    return sum;
  }

}
