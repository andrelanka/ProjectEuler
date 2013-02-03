package de.drlanka.euler.problems;

import java.math.BigInteger;

import de.drlanka.euler.EulerProblem;

public class P0413_oneChildNumbers implements EulerProblem {
  
  int boundExponent=19;   //Currently 19 is max
  
  int currentLength=boundExponent;
  
  long tailOfNumber;
  long headOfNumber;
  
  BigInteger number=null;
  
  final static long boundForTail=1_000_000_000_000_000_000L;
  final static int tailExponent=18;

  private BigInteger modInt;

  @Override
  public Object solve() {
    int result=0;
//    nextNumber();
    for (int i = 1; i <= boundExponent; i++) {
      currentLength=i;
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
    String head = getNumberString().substring(0,position+1);   
    BigInteger headI=new BigInteger(head);
    String newTail=(currentLength-1!=position)?String.format("%0"+(currentLength-position-1)+"d", 0):"";
    number=new BigInteger(headI.add(BigInteger.ONE)+newTail);
    if(position<3)
      System.out.println(number);
  }

  protected int isOneChild() {
    String number1=number.toString();
    int divisor = number1.length();
    if(divisor!=currentLength)
      return -2;
    boolean alreadyFound = false;
    int oldJ = 0;
    for (int length = 0; length < divisor; length++) {
      for (int i = 0; i < divisor - length; i++) {
        int j = i + length;
        String subString = number1.substring(i, j + 1);
        // if(subString.length()>tailExponent) {
        BigInteger bigInt = new BigInteger(subString);
        BigInteger mod = bigInt.mod(modInt);
        // }
        // long sub1=tailOfNumber%divisor;
        // long sub2=(headOfNumber%divisor)*(boundForTail%divisor);
        // long sub=(sub1+sub2)%divisor;
        int sub = mod.intValue();
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
      return divisor-1;
  }
  
  
  void nextNumber() {
    if(number==null)
      initialize();
    else
      number=number.add(BigInteger.ONE);
    
//    if(headOfNumber==0 && tailOfNumber==0) {
//      if (currentLength>tailExponent)
//        headOfNumber=1;
//      else {
//        headOfNumber=0;
//        tailOfNumber=(long)Math.pow(10,currentLength-1);
//      }
//    } else {
//      tailOfNumber++;
//      if(tailOfNumber>=boundForTail) {
//        tailOfNumber-=boundForTail;
//        headOfNumber++;
//      }
//    }
  }

  void initialize() {
    modInt = new BigInteger("" + currentLength);
    if(currentLength==1)
      number=BigInteger.ONE;
    else
      number=new BigInteger("1"+String.format("%0"+(currentLength-1)+"d", 0));
  }
  
  String getNumberString() {
    return number.toString();
//    String s= "";
//    if(headOfNumber>0) {
//      s+=headOfNumber;
//      s+=String.format("%0"+tailExponent+"d", tailOfNumber);
//    } else {
//      s+=tailOfNumber;
//    }
//    return s;
  }

}
