package de.drlanka.euler.problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class P0067_maximumPathSumII extends P0018_maximumPathSumI {

  public P0067_maximumPathSumII() {

    try {
      InputStream stream = P0067_maximumPathSumII.class.getResourceAsStream("/inputProblem0067.txt");
      BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

      List<String> allLines=new LinkedList<>();
      String nextLine=null;
      while((nextLine=reader.readLine())!=null)
        allLines.add(nextLine);      
      
      lines = allLines.size();
      triangle = new int[position(lines - 1, lines - 1)+1];

      int currentLine = 0;
      for (String line : allLines) {

        String[] numbers = line.split(" ");
        if (numbers.length != currentLine + 1)
          throw new IOException("Wrong file format expected " + currentLine + " numbers but found " + numbers.length);

        int column = 0;
        for (String number : numbers) {
          triangle[position(currentLine, column)] = Integer.parseInt(number, 10);
          column++;
        }
        currentLine++;
      }
    } catch (IOException e) {
      triangle = null;
    }
  }

}
