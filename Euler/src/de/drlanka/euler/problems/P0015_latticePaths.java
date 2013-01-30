package de.drlanka.euler.problems;

import de.drlanka.euler.EulerProblem;

public class P0015_latticePaths implements EulerProblem {
  
  protected int GRID_LENGTH=20;

  @Override
  public Object standardWay() {
    // (i,j) Stores the number of paths in a grid with height i and width j  
    long[][] paths=new long[GRID_LENGTH+1][GRID_LENGTH+1];
    
    for(int borderPosition=0;borderPosition<=GRID_LENGTH;borderPosition++) {
      paths[borderPosition][0]=1;
      paths[0][borderPosition]=1;
    }
    
    for(int height=1;height<=GRID_LENGTH;height++)
      for(int width=1;width<=GRID_LENGTH;width++)
        paths[height][width]=paths[height-1][width]+paths[height][width-1];
        
    return Long.valueOf(paths[GRID_LENGTH][GRID_LENGTH]);
  }

}
