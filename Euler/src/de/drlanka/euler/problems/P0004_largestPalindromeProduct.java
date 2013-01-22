package de.drlanka.euler.problems;

import de.drlanka.euler.EulerProblem;

public class P0004_largestPalindromeProduct implements EulerProblem {

  private static final int MAX=999; 
  
  @Override
  public Object complicatedWay() {
    boolean found=false;
    int maxPalindrome=Integer.MAX_VALUE;
    for(int sum=MAX+MAX;sum>1;sum--) {
      for(int first=sum/2;first>0;first--) {
        int second=sum-first;
        if(second>MAX)
          break;
        int product=first*second;
        if(isPalindrome(product)) {
          if(found==false) {
            found =true;
            maxPalindrome=product;
          } else if (maxPalindrome<product){
            maxPalindrome=product;
          }
        }
        if(found && product<=maxPalindrome)
          break;
      }
        
    }
    return Long.valueOf(maxPalindrome);
  }

  private boolean isPalindrome(int number) {
    String sNumber=""+number;
    int bound=sNumber.length()/2;
    for(int i=0;i<bound;i++)
      if(sNumber.charAt(i)!=sNumber.charAt(sNumber.length()-i-1))
        return false;
    return true;
  }

}
