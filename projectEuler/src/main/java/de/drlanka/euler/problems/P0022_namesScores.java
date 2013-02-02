package de.drlanka.euler.problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import de.drlanka.euler.EulerProblem;

public class P0022_namesScores implements EulerProblem {
  
  int namesCount;
  String[] names;
  
  public P0022_namesScores() {
    StringBuilder builder=new StringBuilder();
    String allNames;

    try (BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/inputProblem0022.txt")))) {
      String line;
      while ((line = reader.readLine()) != null) {
        builder.append(line);
      }
      allNames = builder.toString();
    } catch (IOException e) {
      names = null;
      return;
    }

    names=allNames.split("\",\"");
    namesCount=names.length;
    names[0]=names[0].substring(1);
    String lastName=names[namesCount-1];
    names[namesCount-1]=lastName.substring(0,lastName.length()-1);
  }

  @Override
  public Object solve() {
    Arrays.sort(names);
    long sum=0;
    int count=0;
    for(String name:names) {
      int nameSum=0;
      for(char c:name.toCharArray())
        nameSum+=Character.digit(c, 36)-9;
      sum+=nameSum*(++count);
    }
    return sum;
  }

}
