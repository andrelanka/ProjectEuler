package de.drlanka.euler.problems;

import de.drlanka.euler.EulerProblem;

public class P0009_specialPythagoreanTriplet implements EulerProblem {

  int sum = 1000;

  @Override
  public Object solve() {
    for (int a = 0; a < sum / 3; a++)
      for (int b = a; b < sum / 2; b++) {
        int c = sum - a - b;
        if (a * a + b * b == c * c)
          return Integer.valueOf(a * b * c);
      }
    return null;
  }

}
