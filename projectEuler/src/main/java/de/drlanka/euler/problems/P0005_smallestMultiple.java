package de.drlanka.euler.problems;

import java.util.HashMap;
import java.util.Map;

import de.drlanka.euler.EulerProblem;
import de.drlanka.euler.lib.Prime;

public class P0005_smallestMultiple implements EulerProblem {

  int upperBound=20; 
  
  @Override
  public Object solve() {
    Map<Long, Long> primes=new HashMap<>();
    for(int i=2;i<=upperBound;i++) {
      for(Map.Entry<Long,Long> entry:Prime.findPrimeFactorization(i).entrySet()) {
        Long current=primes.get(entry.getKey());
        if(current==null || current.longValue()<entry.getValue().longValue()) {
          primes.put(entry.getKey(), entry.getValue());
        }
      }
    }
    return Long.valueOf(Prime.multiplyPrimes(primes));
  }
}
