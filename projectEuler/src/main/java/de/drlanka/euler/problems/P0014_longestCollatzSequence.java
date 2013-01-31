package de.drlanka.euler.problems;

import java.util.HashMap;
import java.util.Map;


import de.drlanka.euler.EulerProblem;

public class P0014_longestCollatzSequence implements EulerProblem {

  protected int maxStartValue = 1_000_000;
  private Map<Long, Integer> sequenceLength=new HashMap<>();
  
  public P0014_longestCollatzSequence() {
    sequenceLength.put(Long.valueOf(1), Integer.valueOf(0));
  }

  @Override
  public Object solve() {
    long valueWithLongestChain=1;
    int longestChain = 0;
    for (long current = 2; current < maxStartValue; current++) {
      long thisValue = current;
      int thisChainLength = collatzLength(thisValue);

      sequenceLength.put(Long.valueOf(current), Integer.valueOf(thisChainLength));
      if (thisChainLength > longestChain) {
        longestChain = thisChainLength;
        valueWithLongestChain = current;
      }

    }

    return Long.valueOf(valueWithLongestChain);
  }

  public int collatzLength(long startValue) {
    int thisChainLength = 0;

    while (true) {
      Integer remainingLength = sequenceLength.get(Long.valueOf(startValue));
      thisChainLength++;
      if (remainingLength != null) {
        thisChainLength += remainingLength.intValue();
        break;
      }
      if (startValue % 2 == 0)
        startValue >>= 1;
      else
        startValue = startValue * 3 + 1;
    }
    return thisChainLength;
  }

}
