package de.drlanka.euler.problems;

import java.util.HashMap;
import java.util.Map;

import de.drlanka.euler.EulerProblem;
import de.drlanka.euler.lib.Prime;

public class P0021_amicableNumbers implements EulerProblem {

  int bound = 10000;

  @Override
  public Object solve() {
    Map<Integer, Integer> cache = new HashMap<>();
    int amicableSum = 0;
    for (int i = 1; i < bound; i++) {
      int sum = sumOfProperDivisors(i);
      Integer storedIForSum = cache.get(sum);
      if (storedIForSum != null && storedIForSum == i)
        amicableSum += i + sum;
      cache.put(i, sum);
    }
    return amicableSum;
  }

  int sumOfProperDivisors(int number) {
    Map<Long, Long> factorization = Prime.findPrimeFactorization(number);
    int sum = 1;
    //Calculate sum of independent products by multiplying the sum (associative law). This is quickly done by using the formula for the geometric series   
    for (Map.Entry<Long, Long> entry : factorization.entrySet()) {
      long prime = entry.getKey();
      long multiplicity = entry.getValue();
      sum *= (Math.pow(prime, multiplicity + 1) - 1) / (prime - 1);
    }
    sum-=number;  //Only proper divisors
    return sum;
  }

}