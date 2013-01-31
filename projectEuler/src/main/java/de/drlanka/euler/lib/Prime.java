package de.drlanka.euler.lib;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class Prime {

  private static SortedSet<Long> primes=new TreeSet<>();
  
  static {
    initialize();
  }

  protected static void initialize() {
    primesFoundUpperBound=2;
    primes.add(Long.valueOf(2));
  }
  
  /** Indicates that {@link #primes} contains all primes between 2 and this value (including both bounds if possible)*/
  private static long primesFoundUpperBound=2;

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
    for(long divisor : findAllPrimesBelow(max+1))
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
  
  public static SortedSet<Long> findAllPrimesBelow(long bound) {
    
    if(primesFoundUpperBound<bound) {
      long current=primesFoundUpperBound+1;
      if(current%2==0)
        current++;
      while(current<bound) {
        if(isPrime(current)) {
          primes.add(Long.valueOf(current));
          primesFoundUpperBound=current;
        }
        current+=2;
      }
      
    }
    return Collections.unmodifiableSortedSet(primes.headSet(Long.valueOf(bound)));
  }

  protected static boolean isPrime(long current) {
    boolean isPrime=true;
    
    int bound=(int)Math.sqrt(current);
    
    for(long prime:findAllPrimesBelow(bound+1)) {      
      if(current % prime == 0) {
        isPrime=false;
        break;
      }
    }
    return isPrime;
  }
  
}
