package de.drlanka.euler.lib;

public class NumberStrings {
  
  public static long decodeString(String numberString, int start, int length) {
    String substring = numberString.substring(start, Math.min(numberString.length(), start+length));
    return Long.parseLong(substring);
  }

  public static long firstDigits(long value, int digits) {
    if(value<0)
      digits++;
    return decodeString(""+value, 0, digits);
  }

  public static long digitSum(String number) {
    long sum=0;
    for(int i=number.length()-1;i>=0;i--)
      sum+=decodeString(number, i, 1);
    return sum;
  }

}
