package org.logisticPlanning.tsp.solving.operators.permutation.localOpt;

import java.util.Arrays;
import java.util.Comparator;

/** list some differences and similarities */
public final class _PivotTest implements Comparator<int[]> {

  /** the shared instance */
  private static final _PivotTest INSTANCE = new _PivotTest();

  /**
   * the main method
   * 
   * @param args
   *          the args
   */
  public static void main(final String[] args) {
    _PermutationIterator a, b;
    int[] p, q;
    int[][] p_pairs, q_pairs;
    int length, x, y;

    for (length = 2; length < 5; length++) {
      System.out.println();
      System.out.println();
      System.out.println();

      a = new _PermutationIterator(length);
      b = new _PermutationIterator(length);

      p_pairs = new int[length + 1][2];
      q_pairs = new int[length + 1][2];

      while (a.hasNext()) {
        System.out.print('\t');
        _PivotTest.__print(a.next());
      }
      System.out.println();

      a.reset();
      x = 0;
      while (a.hasNext()) {
        p = a.next();
        x++;
        _PivotTest.__pairs(p, p_pairs);
        _PivotTest.__print(p);
        b.reset();
        y = 0;
        while (b.hasNext()) {
          y++;
          System.out.print('\t');
          q = b.next();
          if (x < y) {
            if (Arrays.equals(p, q)) {
              System.out.print("---"); //$NON-NLS-1$
              continue;
            }
            _PivotTest.__pairs(q, q_pairs);

            System.out.print('(');
            _PivotTest.__printDiff(p_pairs, q_pairs);
            System.out.print(')');
            System.out.print('<');
            System.out.print('(');
            _PivotTest.__printDiff(q_pairs, p_pairs);
            System.out.print(')');
          }

        }
        System.out.println();
      }
    }
  }

  /**
   * print the pair differences
   * 
   * @param p
   *          the p pairs
   * @param q
   *          the q pairs
   */
  private static final void __printDiff(final int[][] p, final int[][] q) {
    boolean has;

    has = false;
    for (final int[] a : p) {
      if (Arrays.binarySearch(q, a, _PivotTest.INSTANCE) < 0) {
        if (has) {
          System.out.print('+');
        } else {
          has = true;
        }
        _PivotTest.__print(a[0]);
        _PivotTest.__print(a[1]);
      }
    }
  }

  /**
   * load all the pairs
   * 
   * @param perm
   *          the perm
   * @param pairs
   *          the pairs
   */
  private static final void __pairs(final int[] perm, final int[][] pairs) {
    int i, last, cur;

    last = 0;
    for (i = 0; i < perm.length; i++) {
      cur = perm[i];
      if (cur < last) {
        pairs[i][0] = cur;
        pairs[i][1] = last;
      } else {
        pairs[i][0] = last;
        pairs[i][1] = cur;
      }
      last = cur;
    }

    pairs[i][0] = last;
    pairs[i][1] = 10;
    Arrays.sort(pairs, _PivotTest.INSTANCE);
  }

  /**
   * print an index
   * 
   * @param i
   *          the index
   */
  private static final void __print(final int i) {
    final char print;
    switch (i) {
      case 0: {
        print = 'S';
        break;
      }
      case 10: {
        print = 'E';
        break;
      }
      default: {
        print = ((char) ('0' + i));
        break;
      }

    }
    System.out.print(print);
  }

  /**
   * print the array
   * 
   * @param array
   *          the array
   */
  private static final void __print(final int[] array) {
    for (final int i : array) {
      _PivotTest.__print(i);
    }
  }

  /** {@inheritDoc} */
  @Override
  public final int compare(final int[] o1, final int[] o2) {
    int res;

    res = Integer.compare(o1[0], o2[0]);
    if (res != 0) {
      return res;
    }

    return Integer.compare(o1[1], o2[1]);
  }

  /** create */
  private _PivotTest() {
    super();
  }
}
