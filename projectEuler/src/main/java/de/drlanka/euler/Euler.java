package de.drlanka.euler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.reflections.Reflections;

public class Euler {

  /**
   * @param args
   */
  public static void main(String[] args) {
    Reflections reflections = new Reflections("de.drlanka.euler");

    List<Class<? extends EulerProblem>> types = new ArrayList<>(reflections.getSubTypesOf(EulerProblem.class));
    Collections.sort(types, new Comparator<Class<?>>() {

      @Override
      public int compare(Class<?> o1, Class<?> o2) {
        if (o1 == null)
          return o2 == null ? 0 : -1;
        return (o2 == null) ? 1 : o1.getSimpleName().compareTo(o2.getSimpleName());
      }
    });

    for (Class<? extends EulerProblem> type : types)
      exerciseProblem(type);

  }

  protected static void exerciseProblem(Class<? extends EulerProblem> type) {
    EulerProblem problem = null;
    try {
      problem = type.newInstance();
    } catch (InstantiationException | IllegalAccessException e) {
      System.err.println("Unable to instantiate " + type.getSimpleName());
    }
    if (problem != null) {
      long start = System.nanoTime();
      Object solution = problem.solve();
      long end = System.nanoTime();
      System.out.println("Smart   solution for problem " + type.getSimpleName() + " is " + solution
          + " and was found in " + formatTimespan(start, end));

      if (problem instanceof EulerProblemTrivial) {
        start = System.nanoTime();
        Object trivialSolution = ((EulerProblemTrivial) problem).easyWay();
        end = System.nanoTime();
        System.out.println("Trivial solution for problem " + type.getSimpleName() + " is " + trivialSolution
            + " and was found in " + formatTimespan(start, end));
        if (solution.equals(trivialSolution) == false)
          throw new RuntimeException("Solutions for problem " + type.getSimpleName() + " don't match");
      }
    }

  }

  private static final String formatTimespan(long startInNS, long endInNS) {
    double span = endInNS - startInNS;
    if (span < 1000)
      return span + " ns";
    span /= 1000;
    if (span < 1000)
      return String.format("%.1f", Double.valueOf(span)) + " µs";
    span /= 1000;
    if (span < 1000)
      return String.format("%.1f", Double.valueOf(span)) + " ms";
    span /= 1000;
    return String.format("%.1f", Double.valueOf(span)) + " s";
  }

}
