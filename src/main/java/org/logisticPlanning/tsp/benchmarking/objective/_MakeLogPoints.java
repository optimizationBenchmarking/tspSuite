package org.logisticPlanning.tsp.benchmarking.objective;

import java.util.ArrayList;
import java.util.Collections;

/**
 * <p>
 * This class is an internal class. Please do not instantiate it, use it by
 * yourself, or otherwise meddle with it.
 * </p>
 * <p>
 * This class was used to generate a list of threshold values for log
 * points as used by
 * {@link org.logisticPlanning.tsp.benchmarking.objective.Benchmark} . It
 * is not part of the life system and here exists solely for documentation
 * purposes.
 * </p>
 */
final class _MakeLogPoints {

  /**
   * The main method
   * 
   * @param args
   *          ignored
   */
  public static void main(final String[] args) {
    long l, l1, l2;
    Long v;
    int i, s;
    ArrayList<Long> list;

    list = new ArrayList<>();

    for (l = 1; l > 0; l += l) {
      v = Long.valueOf(l);
      if (!(list.contains(v))) {
        list.add(v);
      }
    }

    for (l = 1; l > 0; l = (3l * l)) {
      v = Long.valueOf(l);
      if (!(list.contains(v))) {
        list.add(v);
      }
    }

    for (l = 1; l > 0; l = (5l * l)) {
      v = Long.valueOf(l);
      if (!(list.contains(v))) {
        list.add(v);
      }
    }

    for (l = 1; l > 0; l = (7l * l)) {
      v = Long.valueOf(l);
      if (!(list.contains(v))) {
        list.add(v);
      }
    }

    for (l = 1; l > 0; l = (11l * l)) {
      v = Long.valueOf(l);
      if (!(list.contains(v))) {
        list.add(v);
      }
    }

    for (l = 1; l > 0; l = (10l * l)) {
      v = Long.valueOf(l);
      if (!(list.contains(v))) {
        list.add(v);
      }
    }

    for (l = 5; l <= 200l; l += 5l) {
      v = Long.valueOf(l);
      if (!(list.contains(v))) {
        list.add(v);
      }
    }

    for (l = 1, l2 = 10; l2 > 0l; l2 *= 10l) {
      l1 = l;
      for (; l < l2; l += l1) {
        v = Long.valueOf(l);
        if (!(list.contains(v))) {
          list.add(v);
        }
      }
    }

    for (l = 1; l < 20; l++) {
      v = Long.valueOf(l * 250l);
      if (!(list.contains(v))) {
        list.add(v);
      }
      v = Long.valueOf(l * 2500l);
      if (!(list.contains(v))) {
        list.add(v);
      }
      v = Long.valueOf(l * 25000l);
      if (!(list.contains(v))) {
        list.add(v);
      }
    }

    Collections.sort(list);
    s = list.size();
    for (i = 0; (i < s); i++) {
      System.out.print(list.get(i));
      System.out.print('l');
      System.out.print(',');
      System.out.print(' ');
    }

    System.out.println();
    System.out.println();
    System.out.println(s);
  }

}
