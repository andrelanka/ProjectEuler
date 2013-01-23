package de.drlanka.euler.lib;

import java.util.HashMap;
import java.util.Map;

public class Prime {

  public static Map<Long, Long> findPrimeFactorization(long number) {
    Map<Long, Long> primeFactors=new HashMap<>();
    findPrimeFactorization(number, primeFactors);
    return primeFactors;
  }
  
  public static long multiplyPrimes(Map<Long,Long> primeFactorization) {
    long result=1;
    for(Map.Entry<Long, Long> entry:primeFactorization.entrySet()) {
      result*=Math.pow(entry.getKey().longValue(), entry.getValue().longValue());
    }
    return result;
  }
  
  private static void findPrimeFactorization(long number, Map<Long, Long> primeFactorizationprimes) {
    if(number==1)
      return;
    if(number%2==0) {
      incrementKey(primeFactorizationprimes,2);
      findPrimeFactorization(number/2,primeFactorizationprimes);
      return;
    }
    long max=(long)Math.sqrt(number);
    for(long divisor=3;divisor<=max;divisor+=2)
      if(number%divisor==0) {
        incrementKey(primeFactorizationprimes, divisor);
        findPrimeFactorization(number/divisor, primeFactorizationprimes);
        return;
      }
    incrementKey(primeFactorizationprimes, number);
  }

  private static void incrementKey(Map<Long, Long> primes, long key) {
    Long boxedKey=Long.valueOf(key);
    Long oldKey = primes.get(boxedKey);
    if(oldKey==null)
      primes.put(boxedKey, Long.valueOf(1));
    else
      primes.put(boxedKey, Long.valueOf(oldKey.longValue()+1));
  }
  
}
