package de.drlanka.euler.problems;

import gnu.trove.procedure.TIntProcedure;
import gnu.trove.set.hash.TIntHashSet;
import gnu.trove.set.hash.TLongHashSet;

import java.util.logging.Level;
import java.util.logging.Logger;

import de.drlanka.euler.EulerProblem;

public class P0413_oneChildNumbers_CacheGroups implements EulerProblem {

  Logger logger=Logger.getLogger(this.getClass().getSimpleName());
  
  public final static int MAX_BOUND = 19;

  int boundExponent = 19;

  /**
   * Holds the solutions of length k having no substring divisible by
   * {@link #currentLength}
   */
  private TIntHashSet[] ex0Cache = new TIntHashSet[MAX_BOUND];
  
  private TLongHashSet solutions=new TLongHashSet();

  /**
   * Holds the solutions of length k having exactly one substring divisible by
   * {@link #currentLength}
   */
  private TIntHashSet[] ex1Cache = new TIntHashSet[MAX_BOUND];

  public P0413_oneChildNumbers_CacheGroups() {
    for (int i = 0; i < MAX_BOUND; i++) {
      ex0Cache[i] = new TIntHashSet();
      ex1Cache[i] = new TIntHashSet();
    }
    currentNumbers = new int[MAX_BOUND];
  }

  // ex0(k)=ex0(k-1)*ex0(1);
  // ex1(k)=ex1(k/2)*ex0(k/2)+ex0(k/2)*ex1(k/2)+ex0(k-1)*ex0(1)

  int currentLength;

  int[] currentNumbers;
  int result;

  int groupLength;

  @Override
  public Object solve() {
    for (int i = 1; i <= boundExponent; i++) {
      if (i == 10) // No solutions possible
        continue;
      currentLength = i;
      initialize();
      calculateEx0();
      extendEx0();
//      next(0, false);
    }
    return solutions.size();
  }

  void extendEx0() {
    if (currentLength == 1) {
      solutions.clear();
      for(int i=1;i<10;i++)
        solutions.add(i);
      return;
    }
    final int length=currentLength/2;
    final int count=ex0Cache[length].size();
    final long start=System.nanoTime();
    ex0Cache[length].forEach(new TIntProcedure() {
      
      int thisCount=0;
      long lastElapsed;
      
      @Override
      public boolean execute(int part) {
        thisCount++;
        setOnPosition(0, part, length);
        fillRight(length,false);
        int startPos=currentLength-length;
        setOnPosition(startPos, part, length);
        fillLeft(startPos,false);
        long elapsed=System.nanoTime()-start;
        if((elapsed-lastElapsed>10_000_000_000L) && logger.isLoggable(Level.INFO)) {
          lastElapsed=elapsed;
          logger.info(((elapsed * count / thisCount - elapsed) / 1000 / 1000 / 1000)
              + " seconds remain");
        }
        return true;
      }
    });
    System.out.println("Current length: "+currentLength+" gives "+solutions.size());
  }

  void calculateEx0() {
    TIntHashSet set = ex0Cache[1];
    for (int n = 1; n < 10; n++) {
      if (n % currentLength > 0)
        set.add(n);
    }
    for (int i = 1; i < currentLength/2; i++) {
      final int firstLength = i;
      TIntHashSet firstSet = ex0Cache[i];
      final TIntHashSet secondSet = ex0Cache[1];
      final TIntHashSet nextSet = ex0Cache[i + 1];
      firstSet.forEach(new TIntProcedure() {

        @Override
        public boolean execute(final int first) {
          secondSet.forEach(new TIntProcedure() {

            @Override
            public boolean execute(int second) {
              int remain = first;
              int divisor = 1;
              for (int k = 0; k <= firstLength; k++) {
                if ((((remain % divisor) * 10 + second) % currentLength) == 0)
                  return true;
                divisor*=10;
              }
              nextSet.add(first * 10 + second);
              return true;
            }
          });
          return true;
        }
      });
      System.out.println((i+1)+": "+nextSet.size());
    }
  }
  
  void fillRight(int thisPathLength, boolean hadDivisor) {

    for (int i = 0; i < 10; i++) {
      currentNumbers[thisPathLength] = i;

      // Check current path
      boolean nowFoundDivisor = false;
      boolean goAhead = false;

      int mod = 0;
      long multiplier = 1;
      for (int j = thisPathLength; j >= 0; j--) {
        mod = (int) ((mod + currentNumbers[j] * multiplier) % currentLength);
        if (mod == 0) {
          if (nowFoundDivisor || hadDivisor)
            goAhead = true;
          nowFoundDivisor = true;
          if (goAhead)
            break;
        }
        multiplier *= 10;
      }
      if (goAhead)
        continue;
      if (thisPathLength == currentLength - 1) {
        if (nowFoundDivisor ^ hadDivisor)
          solutions.add(fromCurrentNumbers());
      } else
        fillRight(thisPathLength + 1, nowFoundDivisor || hadDivisor);
    }
  }  

  private long fromCurrentNumbers() {
    long value=0;
    for(int i=0;i<currentLength;i++)
      value=value*10+currentNumbers[i];
    return value;
  }

  void fillLeft(int thisPathStart, boolean hadDivisor) {

    for (int i = 0; i < 10; i++) {
      if(i==0 && thisPathStart==1)  //No leading 0
        continue;
      currentNumbers[thisPathStart-1] = i;

      // Check current path
      boolean nowFoundDivisor = false;
      boolean goAhead=false;

      int mod = 0;
      for (int j = thisPathStart-1; j < currentLength; j++) {
        mod = (mod * 10 + currentNumbers[j]) % currentLength;
        if (mod == 0) {
          if(nowFoundDivisor||hadDivisor)
            goAhead=true;
          nowFoundDivisor=true;
          if (goAhead)
            break;
        }
      }
      if(goAhead)
        continue;
      if (thisPathStart == 1) {
        if (nowFoundDivisor^hadDivisor)
          solutions.add(fromCurrentNumbers());
      } else
        fillLeft(thisPathStart-1, nowFoundDivisor||hadDivisor);
    }
  }  

  
  void initialize() {
    for(int i=0;i<MAX_BOUND;i++)
      ex0Cache[i].clear();

  }

  private final void setOnPosition(int position, int number, int numberLength) {
    int thisPosition = position + numberLength;
    while (thisPosition-- > position) {
      currentNumbers[thisPosition] = number % 10;
      number = number / 10;
    }

  }

}
