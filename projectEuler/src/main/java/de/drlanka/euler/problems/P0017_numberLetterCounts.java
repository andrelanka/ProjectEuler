package de.drlanka.euler.problems;

import de.drlanka.euler.EulerProblem;

public class P0017_numberLetterCounts implements EulerProblem {
  
  int maxCount=1000;    //Solution is only made for maxCount between 1 and 1000.
  
  private int[] hundredsLengths=new int[10];  
  
  private int[] tensLengths=new int[]{
      0,
      "ten".length(),
      "twenty".length(),
      "thirty".length(),
      "forty".length(),
      "fifty".length(),
      "sixty".length(),
      "seventy".length(),
      "eighty".length(),
      "ninety".length()
      };

  private int[] onesLengths=new int[]{
      0,
      "one".length(),
      "two".length(),
      "three".length(),
      "four".length(),
      "five".length(),
      "six".length(),
      "seven".length(),
      "eight".length(),
      "nine".length(),
      "ten".length(),
      "eleven".length(),
      "twelve".length(),
      "thirteen".length(),
      "fourteen".length(),
      "fifteen".length(),
      "sixteen".length(),
      "seventeen".length(),
      "eighteen".length(),
      "nineteen".length(),
      "twenty".length(),
      };
  
  public P0017_numberLetterCounts() {
    hundredsLengths[0]=0;
    for(int i=1;i<10;i++) {
      hundredsLengths[i]=onesLengths[i]+"hundredand".length();
    }
  }

  @Override
  public Object solve() {
    
    int current = maxCount;
    int length = 0;
    
    if(current==1000) {
      length="onethousand".length();
      current--;
    }
    
    int hundred = current / 100;
    int ten = (current % 100) / 10;
    int one = current % 10;

    if (current > 20) {
      if (one != 9) {
        if (ten == 1) {
          for (int i = 0; i < one + 1; i++)
            length += onesLengths[10 + i];
          length += (one + 1) * hundredsLengths[hundred];
        } else {
          for (int i = 0; i < one + 1; i++)
            length += onesLengths[i];
          length += (one + 1) * (hundredsLengths[hundred] + tensLengths[ten]);
        }
        current -= one + 1;
      }
    }
    
    // Now current ends on 9, we summarize blocks of ten numbers
    int sumOnesForTen = 0;
    for (int i = 0; i < 10; i++)
      sumOnesForTen += onesLengths[i];

    int sumIrregularOnesForTen = 0;
    for (int i = 10; i < 20; i++)
      sumIrregularOnesForTen += onesLengths[i];

    while (current > 20) {
      hundred = current / 100;
      ten = (current % 100) / 10;
      if (ten == 1) {
        length += 10 * hundredsLengths[hundred] + sumIrregularOnesForTen;
      } else {
        length += 10 * (hundredsLengths[hundred] + tensLengths[ten]);
        length += sumOnesForTen;
        if (hundred > 0 && ten == 0)
          length -= 3; // We have no 'and' for one hundred, two hundred, ...
      }
      current -= 10;
    }

    //Now, the remaining numbers from current to twenty    
    while(current>0) {
      length+=onesLengths[current--];
    }
    
    return length;
  }

}
