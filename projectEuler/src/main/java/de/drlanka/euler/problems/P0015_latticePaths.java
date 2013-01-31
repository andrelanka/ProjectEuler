package de.drlanka.euler.problems;

import de.drlanka.euler.EulerProblem;

public class P0015_latticePaths implements EulerProblem {
  
  public int gridLength=20;

  @Override
  public Object solve() {
    // (i,j) Stores the number of paths in a grid with height i and width j  
    long[][] paths=new long[gridLength+1][gridLength+1];
    
    for(int borderPosition=0;borderPosition<=gridLength;borderPosition++) {
      paths[borderPosition][0]=1;
      paths[0][borderPosition]=1;
    }
    
    for(int height=1;height<=gridLength;height++)
      for(int width=1;width<=gridLength;width++)
        paths[height][width]=paths[height-1][width]+paths[height][width-1];
        
    return Long.valueOf(paths[gridLength][gridLength]);
  }

}
