package de.drlanka.euler.problems;

import junit.framework.TestCase;

public class P0413oneChildNumbers_CacheGroupsTest extends TestCase {
  
  public void testCacheGroups() {
    P0413_oneChildNumbers_CacheGroups p0413 = new P0413_oneChildNumbers_CacheGroups();
    p0413.currentLength=11;
    p0413.initialize();
    p0413.calculateEx0();
    p0413.extendEx0();
  }

}
