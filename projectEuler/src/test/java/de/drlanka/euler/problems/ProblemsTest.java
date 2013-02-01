package de.drlanka.euler.problems;

import junit.framework.TestCase;

public class ProblemsTest extends TestCase {

  public ProblemsTest(String s) {
    super(s);
  }

  public void testProblem0001() {
    P0001_multiplesOf3And5 p0001 = new P0001_multiplesOf3And5();
    p0001.upperBound = 10;
    assertEquals(23L, p0001.solve());
  }

  public void testProblem0002() {
    P0002_evenFibonacciNumbers p0002 = new P0002_evenFibonacciNumbers();
    p0002.sequenceBound = 100;
    assertEquals(44L, p0002.solve());
  }

  public void testProblem0003() {
    P0003_largestPrimeNumber p0003 = new P0003_largestPrimeNumber();
    p0003.number = 13195;
    assertEquals(29L, p0003.solve());
  }

  public void testProblem0004() {
    P0004_largestPalindromeProduct p0004 = new P0004_largestPalindromeProduct();
    p0004.maxFactor = 99;
    assertEquals(9009L, p0004.solve());
  }

  public void testProblem0005() {
    P0005_smallestMultiple p0005 = new P0005_smallestMultiple();
    p0005.upperBound = 10;
    assertEquals(2520L, p0005.solve());
  }

  public void testProblem0006() {
    P0006_sumSquareDifference p0006 = new P0006_sumSquareDifference();
    p0006.bound = 10;
    assertEquals(2640L, p0006.solve());
  }

  public void testProblem0007() {
    P0007_10001stPrime p0007 = new P0007_10001stPrime();
    p0007.bound = 6;
    assertEquals(13, p0007.solve());
  }

  public void testProblem0008() {

    P0008_largestProductInASeries p0008 = new P0008_largestProductInASeries();

    p0008.length = 1;
    assertEquals(9, p0008.solve());

    p0008.length = 2;
    assertEquals(9 * 9, p0008.solve());
  }

  public void testProblem0009() {
    P0009_specialPythagoreanTriplet p0009 = new P0009_specialPythagoreanTriplet();
    p0009.sum = 12;
    assertEquals(60, p0009.solve());
  }

  public void testProblem0010() {
    P0010_summationOfPrimes p0010 = new P0010_summationOfPrimes();
    p0010.upperPrimesBound = 10;
    assertEquals(17L, p0010.solve());
  }

  public void testProblem0011() {
    P0011_largestProductInAGrid p0011 = new P0011_largestProductInAGrid();
    p0011.productLength = 2;
    assertEquals(97 * 99L, p0011.solve());
  }

  public void testProblem0012() {
    P0012_highlyDivisibleTriangularNumber p0012 = new P0012_highlyDivisibleTriangularNumber();
    p0012.divisorCount = 5;
    assertEquals(28L, p0012.solve());
  }

  public void testProblem0013() {
    P0013_largeSum p0013 = new P0013_largeSum();
    assertEquals(p0013.NUMBER_COUNT * p0013.NUMBER_LENGTH, p0013.NUMBER_STRING.length());
  }

  public void testProblem0014() {
    P0014_longestCollatzSequence p0014 = new P0014_longestCollatzSequence();
    assertEquals(1, p0014.collatzLength(1));
    assertEquals(10, p0014.collatzLength(13));
  }

  public void testProblem0015() {
    P0015_latticePaths p0015 = new P0015_latticePaths();
    p0015.gridLength = 2;
    assertEquals(6L, p0015.solve());
  }

  public void testProblem0016() {
    P0016_powerDigitSum p0016 = new P0016_powerDigitSum();
    p0016.power = 15;
    assertEquals(26L, p0016.solve());
  }
  
  public void testProblem0017() {
    P0017_numberLetterCounts p0017 = new P0017_numberLetterCounts();
    assertTrue(p0017.maxCount<=1000);
    
    p0017.maxCount=5;
    assertEquals(19, p0017.solve());
  }
}
