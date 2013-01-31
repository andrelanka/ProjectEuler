package de.drlanka.euler.problems;

import java.util.Map;

import de.drlanka.euler.EulerProblem;
import de.drlanka.euler.lib.Prime;

public class P0012_highlyDivisibleTriangularNumber implements EulerProblem {

  protected long divisorCount = 500;

  @Override
  public Object solve() {
    long sum = 0;
    for (long currentSummand = 1;; currentSummand++) {
      sum += currentSummand;
      Map<Long, Long> findPrimeFactorization = Prime.findPrimeFactorization(sum);
      long divisors = 1;
      for (Long multiplicity : findPrimeFactorization.values())
        divisors *= multiplicity.longValue() + 1;
      if (divisors > divisorCount)
        return Long.valueOf(sum);
    }
  }

}
