package de.drlanka.euler.lib;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Prime {

  private static long[] primes = new long[1_000_000];
  /**
   * Indicates that {@link #primes} contains all primes between 2 and this value
   * (including both bounds if possible)
   */
  private static long primesFoundUpperBound;
  private static int lastIndexUsed;

  static {
    initialize();
  }

  protected static void initialize() {
    lastIndexUsed = 0;
    addPrime(2L);
    primesFoundUpperBound = 2;
  }

  final static void addPrime(long prime) {
    primes[lastIndexUsed++] = prime;
  }

  public static Map<Long, Long> findPrimeFactorization(long number) {
    Map<Long, Long> primeFactors = new HashMap<>();
    findPrimeFactorization(number, primeFactors);
    return primeFactors;
  }

  public static long multiplyPrimes(Map<Long, Long> primeFactorization) {
    long result = 1;
    for (Map.Entry<Long, Long> entry : primeFactorization.entrySet()) {
      result *= Math.pow(entry.getKey().longValue(), entry.getValue().longValue());
    }
    return result;
  }

  private static void findPrimeFactorization(long number, Map<Long, Long> primeFactorizationprimes) {
    if (number == 1)
      return;
    if (number % 2 == 0) {
      incrementKey(primeFactorizationprimes, 2L);
      findPrimeFactorization(number / 2, primeFactorizationprimes);
      return;
    }
    long max = (long) Math.sqrt(number);
    calculatePrimesUpToNumber(max + 1);
    for (int i = 0; i < lastIndexUsed && primes[i] <= max; i++) {
      long divisor = primes[i];
      if (number % divisor == 0) {
        incrementKey(primeFactorizationprimes, divisor);
        findPrimeFactorization(number / divisor, primeFactorizationprimes);
        return;
      }
    }
    incrementKey(primeFactorizationprimes, number);
  }

  final private static void incrementKey(Map<Long, Long> factorization, Long key) {
    Long oldKey = factorization.get(key);
    factorization.put(key, oldKey == null ? Long.valueOf(1) : oldKey.longValue() + 1);
  }

  public static long[] getAllPrimesBelow(long bound) {
    calculatePrimesUpToNumber(bound - 1);
    int index = Arrays.binarySearch(primes, bound - 1);
    return Arrays.copyOfRange(primes, 0, index >= 0 ? index : -index - 2);
  }

  final protected static void calculatePrimesUpToNumber(long bound) {

    if (primesFoundUpperBound >= bound)
      return;

    long current = primesFoundUpperBound + 1;
    if (current % 2 == 0)
      current++;
    while (current < bound) {
      if (hasFactorInPrimesFound(current)) {
        addPrime(current);
        primesFoundUpperBound = current;
      }
      current += 2;
    }
  }

  protected static boolean isPrime(long current) {
    boolean isPrime = true;

    if (primesFoundUpperBound >= current)
      return Arrays.binarySearch(primes, current) >= 0;

    int bound = (int) Math.sqrt(current);
    calculatePrimesUpToNumber(bound);

    for (long prime : primes) {
      if (current % prime == 0) {
        isPrime = false;
        break;
      }
      if (prime > bound)
        break;
    }
    return isPrime;
  }

  private static boolean hasFactorInPrimesFound(long number) {

    int bound = (int) Math.sqrt(number);

    if (bound > primesFoundUpperBound)
      throw new IllegalArgumentException("Given number " + number + " is too large. It yields bound " + bound
          + " but we have only primes up to " + primesFoundUpperBound);

    for (Long prime : primes) {
      if (number % prime == 0)
        return false;
      if (prime > bound)
        return true;
    }
    return true;
  }

}
