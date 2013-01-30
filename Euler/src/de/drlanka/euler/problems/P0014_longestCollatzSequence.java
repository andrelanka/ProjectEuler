package de.drlanka.euler.problems;

import java.util.HashMap;
import java.util.Map;


import de.drlanka.euler.EulerProblem;

public class P0014_longestCollatzSequence implements EulerProblem {

  protected int startBound = 1_000_000;

  @Override
  public Object standardWay() {
    Map<Long, Integer> sequenceLength = new HashMap<>();

    sequenceLength.put(Long.valueOf(1), Integer.valueOf(0));
    long valueWithLongestChain=1;
    int longestChain = 0;
    for (long current = 2; current < startBound; current++) {
      long thisValue = current;
      int thisChainLength = 0;

      while (true) {
        Integer remainingLength = sequenceLength.get(Long.valueOf(thisValue));
        thisChainLength++;
        if (remainingLength != null) {
          thisChainLength += remainingLength.intValue();
          break;
        }
        if (thisValue % 2 == 0)
          thisValue >>= 1;
        else
          thisValue = thisValue * 3 + 1;
      }

      sequenceLength.put(Long.valueOf(current), Integer.valueOf(thisChainLength));
      if (thisChainLength > longestChain) {
        longestChain = thisChainLength;
        valueWithLongestChain = current;
      }

    }

    return Long.valueOf(valueWithLongestChain);
  }

}
