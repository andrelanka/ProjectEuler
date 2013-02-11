package de.drlanka.euler.problems;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import de.drlanka.euler.EulerProblem;

public class P0413_oneChildNumbers_CacheGroups implements EulerProblem {

  int boundExponent = 19; // Currently 19 is max

  private Map<Integer, Set<Integer>> groupCache = new HashMap<>();
  private Set<Integer> keys = new HashSet<>();

  int currentLength;

  int[] currentNumbers;
  int result;

  int groupLength;

  private int groupBound;

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
      if (thisPathLength == currentLength - 1 && i == 0) // Skip leading 0
        continue;
      currentNumbers[thisPathLength] = i;
      next(thisPathLength + 1, hadDivisor);
    }
  }

  void initialize() {
    currentNumbers = new int[currentLength];
    keys.clear();
    groupCache.clear();
    groupBound = (int) (Math.pow(10, groupLength));

    for (int i = 0; i < groupBound; i++) {
      setOnPosition(0, i, groupLength);
      if (isPossible(groupLength))
        keys.add(i);
    }

    int pairsFound = 0;
    for (Integer i : keys) {
      setOnPosition(0, i, groupLength);
      for (Integer j : keys) {
        setOnPosition(groupLength, j, groupLength);
        if (isPossible(2 * groupLength)) {
          addToGroupCache(i, j);
          pairsFound++;
        }
      }
    }

    double percentage = pairsFound * 100 / groupBound / groupBound;
  }

  private boolean isPossible(int length) {
    int dividends = 0;
    for (int i = 0; i < length; i++) {
      for (int j = i + 1; j <= length; j++) {
        dividends += countDividends(i, j);
        if (dividends > 1)
          return false;
      }
    }
    return true;
  }

  private final void setOnPosition(int position, int number, int numberLength) {
    int thisPosition = position + numberLength;
    while (thisPosition-- > position) {
      currentNumbers[thisPosition] = number % 10;
      number = number / 10;
    }

  }

  protected int countDividends(int lower, int upper) {
    long mod = 0;
    boolean hadDivisor = false;
    for (int i = lower; i < upper; i++)
      for (int j = upper - 1; j >= i; j--) {
        mod = (mod * 10 + currentNumbers[j]) % currentLength;
        if (mod == 0) {
          if (hadDivisor)
            return 2;
          else
            hadDivisor = true;
        }
      }
    return hadDivisor ? 1 : 0;
  }

  private void addToGroupCache(Integer key, Integer value) {
    Set<Integer> oldValue = groupCache.get(key);
    if (oldValue == null) {
      oldValue = new HashSet<>();
      groupCache.put(key, oldValue);
    }
    oldValue.add(value);
  }

}
