package de.drlanka.euler.problems;

import java.util.ArrayList;
import java.util.List;

import de.drlanka.euler.EulerProblem;

public class P0007_10001stPrime implements EulerProblem {
  
  static int bound=10001;

  @Override
  public Object standardWay() {
    List<Integer> primes=new ArrayList<>(bound);
    primes.add(Integer.valueOf(2));
    int current=3;
    while(primes.size()<bound) {

      boolean isPrime=true;
      
      for(int prime:primes) {
        
        if(current % prime == 0) {
          isPrime=false;
          break;
        }
        if(prime*prime>current) {
          isPrime=true;
          break;
        }
      }
      
      if(isPrime)
        primes.add(Integer.valueOf(current));
      current+=2;
    }
    return primes.get(bound-1);
  }

}
