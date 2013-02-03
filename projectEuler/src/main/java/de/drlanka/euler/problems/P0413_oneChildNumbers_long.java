package de.drlanka.euler.problems;

import java.math.BigInteger;

import de.drlanka.euler.EulerProblem;

public class P0413_oneChildNumbers_long implements EulerProblem {
  
  int boundExponent=19;   //Currently 19 is max
  
  int currentLength=boundExponent;
  int pseudoCurrentLength;
  
  long tailOfNumber;
  long headOfNumber;
    
  final static long boundForTail=1_000_000_000_000_000_000L;
  
  final static long[] powersOfTen=new long[19];
  
  
  static {
    long power=1;
    for(int i=0;i<19;i++) {
      powersOfTen[i]=power;
      power*=10;
    }
  }
  final static int tailExponent=18;

  private BigInteger modInt;

  @Override
  public Object solve() {
    int result=0;
//    nextNumber();
    for (int i = 1; i <= boundExponent; i++) {
      currentLength=i;
      pseudoCurrentLength=(i==19)?18:currentLength;
      initialize();
      while (true) {
        // String number = getNumberString();
        int res = isOneChild();
        if (res == -1) {
          result++;
          nextNumber();
        } else if (res == -2) {
          break;
        } else
          incrementPosition(res);
      }
    }
    return result;
  }

  void incrementPosition(int position) {
    if(currentLength==19) {
      if(position==0) {
        headOfNumber++;
        tailOfNumber=0;
      } else {
        long head=tailOfNumber/powersOfTen[18-position];
        head++;
        if((long)(head/powersOfTen[position])>0) {
          headOfNumber++;
          tailOfNumber=0;
        } else {
          tailOfNumber=head*powersOfTen[18-position];
        }        
      }
    } else {
      long head=tailOfNumber/powersOfTen[currentLength-position-1];
      head++;
      tailOfNumber=head*powersOfTen[currentLength-position-1];
    }
    
    if(position<3)
      System.out.println(getNumberString());
  }

  protected int isOneChild() {
    if(currentLength==19) {
      if(headOfNumber>9)
        return -2;
    } else 
      if((long)(tailOfNumber/powersOfTen[currentLength])>0)
        return -2;

    boolean alreadyFound = false;
    int oldJ = 0;
    for (int j = 0; j < currentLength; j++) {
      for (int i = 0; i <= j ; i++) {
        int length=j-i+1;
//        int j = i + length;
        long sub;
        if(length==19) {
          sub=((tailOfNumber%currentLength)+((headOfNumber*powersOfTen[10])%currentLength)*powersOfTen[8])%currentLength;
        } else if(currentLength==19){
          if(i==0) {
            sub=headOfNumber*powersOfTen[length-1]+tailOfNumber/powersOfTen[19-length];
          } else {
            sub=(tailOfNumber%powersOfTen[currentLength-i])/powersOfTen[currentLength-j-1];
          }
        } else {
          sub=(tailOfNumber%powersOfTen[currentLength-i])/powersOfTen[currentLength-j-1];
        }
        // }
        // long sub1=tailOfNumber%divisor;
        // long sub2=(headOfNumber%divisor)*(boundForTail%divisor);
        // long sub=(sub1+sub2)%divisor;
        sub=sub%currentLength;
        if (sub == 0) {
          if (alreadyFound) {
            return Math.max(j, oldJ);
          } else {
            alreadyFound = true;
            oldJ = j;
          }
        }
      }
    }
    if(alreadyFound)
      return -1;
    else
      return currentLength-1;
  }
  
  
  void nextNumber() {
    tailOfNumber++;
    if (tailOfNumber >= boundForTail) {
      tailOfNumber -= boundForTail;
      headOfNumber++;
    }
  }

  void initialize() {
    if(currentLength==19) {
      headOfNumber=1;
      tailOfNumber=0;
    } else {
      headOfNumber=0;
      tailOfNumber=powersOfTen[currentLength-1];
    }
  }
  
  String getNumberString() {
    String s= "";
    if(headOfNumber>0) {
      s+=headOfNumber;
      s+=String.format("%0"+tailExponent+"d", tailOfNumber);
    } else {
      s+=tailOfNumber;
    }
    return s;
  }

}
