package de.drlanka.euler.problems;

import de.drlanka.euler.EulerProblem;
import de.drlanka.euler.EulerProblemTrivial;

public class P0002_evenFibonacciNumbers implements EulerProblem, EulerProblemTrivial {

  private static final int BOUND=4_000_000; 
  
  @Override
  public Object complicatedWay() {
    double sqrt5=Math.sqrt(5);
    double q1=Math.pow((1+sqrt5)/2,3);
    double q2=Math.pow((1-sqrt5)/2,3);
    int n=1;
    long sum=0;
    double evenFib;
    while ((evenFib=(Math.pow(q1, n)-Math.pow(q2, n))/sqrt5)<=BOUND) {
      sum+=(long)evenFib;
      n++;
    }
    return Long.valueOf(sum);
  }

  @Override
  public Object easyWay() {
    long aN=1;
    long aNSuccessor=1;
    long next;
    long sum=0;
    while((next=aN+aNSuccessor)<=BOUND) {
      if(next%2==0)
        sum+=next;
      aN=aNSuccessor;
      aNSuccessor=next;
    }
    return Long.valueOf(sum);
  }

}
