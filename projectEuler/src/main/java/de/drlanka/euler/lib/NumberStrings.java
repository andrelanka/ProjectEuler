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

}
