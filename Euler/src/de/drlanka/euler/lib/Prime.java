package de.drlanka.euler.lib;

import java.util.HashMap;
import java.util.Map;

public class Prime {

  public static Map<Long, Long> findPrimes(long number) {
    Map<Long, Long> primes=new HashMap<>();
    findPrimes(number, primes);
    return primes;
  }
  
  public static long multiplyPrimes(Map<Long,Long> primes) {
    long result=1;
    for(Map.Entry<Long, Long> entry:primes.entrySet()) {
      result*=Math.pow(entry.getKey().longValue(), entry.getValue().longValue());
    }
    return result;
  }
  
  private static void findPrimes(long number, Map<Long, Long> primes) {
    if(number==1)
      return;
    if(number%2==0) {
      incrementKey(primes,2);
      findPrimes(number/2,primes);
      return;
    }
    long max=(long)Math.sqrt(number);
    for(long divisor=3;divisor<=max;divisor+=2)
      if(number%divisor==0) {
        incrementKey(primes, divisor);
        findPrimes(number/divisor, primes);
        return;
      }
    incrementKey(primes, number);
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
