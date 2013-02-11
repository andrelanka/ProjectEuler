package de.drlanka.euler.problems;

import java.util.Arrays;

import de.drlanka.euler.EulerProblem;

public class P0413_oneChildNumbers_DFS implements EulerProblem {

  int boundExponent = 19; // Currently 19 is max

  int currentLength;

  int[] currentNumbers;
  int result;

  @Override
  public Object solve() {
    for (int i = 1; i <= boundExponent; i++) {
      if (i == 10) // No solutions possible
        continue;
      currentLength = i;
      initialize();
      next(0, false);
    }
    return result;
  }

  void next(int thisPathLength, boolean hadDivisor) {
    if (thisPathLength == 3) {
      System.out.println(Arrays.toString(currentNumbers));
    }
    // Check current path
    if (thisPathLength > 0) {
      long mod = 0;
      for (int j = thisPathLength - 1; j >= 0; j--) {
        mod = (mod * 10 + currentNumbers[j]) % currentLength;
        if (mod == 0) {
          if (hadDivisor)
            return;
          else
            hadDivisor = true;
        }
      }
      if (thisPathLength == currentLength) {
        if (hadDivisor)
          result++;
        return;
      }
    }

    for (int i = 0; i < 10; i++) {
      if (thisPathLength == currentLength - 1 && i == 0)  //Skip leading 0
        continue;
      currentNumbers[thisPathLength] = i;
      next(thisPathLength + 1, hadDivisor);
    }
  }

  void initialize() {
    currentNumbers = new int[currentLength];

  }

}
