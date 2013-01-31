package de.drlanka.euler.problems;

import de.drlanka.euler.EulerProblem;

public class P0002_evenFibonacciNumbers implements EulerProblem {

  int sequenceBound = 4_000_000;

  @Override
  public Object solve() {
    long aN = 1;
    long aNSuccessor = 1;
    long next;
    long sum = 0;
    while ((next = aN + aNSuccessor) <= sequenceBound) {
      if (next % 2 == 0)
        sum += next;
      aN = aNSuccessor;
      aNSuccessor = next;
    }
    return Long.valueOf(sum);
  }

}
