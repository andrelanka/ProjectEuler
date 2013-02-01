package de.drlanka.euler.problems;

import java.util.GregorianCalendar;

import de.drlanka.euler.EulerProblem;

public class P0019_countingSundays implements EulerProblem {

  GregorianCalendar startDate = new GregorianCalendar(1901, 0, 1);
  GregorianCalendar endDate = new GregorianCalendar(2000, 11, 31);

  @Override
  public Object solve() {
    GregorianCalendar currentDate=(GregorianCalendar) startDate.clone();
    int count=0;
    while(currentDate.before(endDate)) {
      if(currentDate.get(GregorianCalendar.DAY_OF_WEEK)==GregorianCalendar.SUNDAY) {
        count++;
      }
      currentDate.add(GregorianCalendar.MONTH, 1);
    }
    return count;
  }
}
