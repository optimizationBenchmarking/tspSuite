package org.logisticPlanning.tsp.solving.operators.permutation.localOpt;

/**
 * The &quot;canonical permutation creator&quot; that always returns the
 * canonical permutation <code>(1, 2, 3, &hellip;, n)</code>.
 */
public final class _CanonicalPermutation {

  /** the internal globally shared blueprint */
  private static volatile int[] s_blueprint = {//
  1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16,//
  };

  /**
   * get the blueprint of at least length {@code n}
   * 
   * @param n
   *          the number of elements
   * @return the blueprint
   */
  private static final int[] __blueprint(final int n) {
    int i;
    int[] blue, blue2;

    blue = _CanonicalPermutation.s_blueprint;
    if ((i = blue.length) < n) {
      blue2 = new int[n];
      System.arraycopy(blue, 0, blue2, 0, i);
      while (i < n) {
        blue2[i++] = i;
      }
      blue = _CanonicalPermutation.s_blueprint;
      if (blue.length < n) {
        _CanonicalPermutation.s_blueprint = blue2;
        return blue2;
      }
    }

    return blue;
  }

  /**
   * Fill a portion of an array with a canonical permutation whose first
   * element is one. Passing in the array
   * {@code (10, 10, 10, 10, 10, 10, 10)} , {@code start=1} and
   * {@code count=4} to this method leads to
   * {@code (10, 1, 2, 3, 4, 10, 10)}.
   * 
   * @param dest
   *          the destination array
   * @param start
   *          the starting index where to start writing the canonical
   *          permutation
   * @param count
   *          the length of the canonical permutation to use
   */
  public static final void makeCanonicalOne(final int[] dest,
      final int start, final int count) {
    if (count > 0) {
      System.arraycopy(_CanonicalPermutation.__blueprint(count), 0, dest,
          start, count);
    }
  }

  /**
   * Fill a portion of an array with a canonical permutation whose first
   * element is zero. Passing in the array
   * {@code (10, 10, 10, 10, 10, 10, 10)}, {@code start=1} and
   * {@code count=4} to this method leads to
   * {@code (10, 0, 1, 2, 3, 10, 10)}.
   * 
   * @param dest
   *          the destination array
   * @param start
   *          the starting index where to start writing the canonical
   *          permutation
   * @param count
   *          the length of the canonical permutation to use
   */
  public static final void makeCanonicalZero(final int[] dest,
      final int start, final int count) {
    int copy;

    copy = count;
    if (copy > 0) {
      dest[start] = 0;
      if ((--copy) > 0) {
        System.arraycopy(_CanonicalPermutation.__blueprint(copy), 0, dest,
            (start + 1), copy);
      }
    }
  }

  /**
   * Fill an array with a canonical permutation whose first element is one.
   * Passing in the array {@code (10, 10, 10, 10, 10, 10, 10)} to this
   * method leads to {@code (1, 2, 3, 4, 5, 6, 7)}.
   * 
   * @param dest
   *          the destination array
   */
  public static final void makeCanonicalOne(final int[] dest) {
    final int copy;
    copy = dest.length;
    System.arraycopy(_CanonicalPermutation.__blueprint(copy), 0, dest, 0,
        copy);
  }

  /**
   * Fill an array with a canonical permutation whose first element is
   * zero. Passing in the array {@code (10, 10, 10, 10, 10, 10, 10)} to
   * this method leads to {@code (0, 1, 2, 3, 4, 5, 6)}.
   * 
   * @param dest
   *          the destination array
   */
  public static final void makeCanonicalZero(final int[] dest) {
    int copy;

    if ((copy = dest.length) > 0) {
      dest[0] = 0;
      if ((--copy) > 0) {
        System.arraycopy(_CanonicalPermutation.__blueprint(copy), 0, dest,
            1, copy);
      }
    }
  }

  /**
   * Create a canonical permutation of a given length whose first element
   * is one. Passing in {@code count=4} to this method leads to
   * {@code (1, 2, 3, 4)}.
   * 
   * @param count
   *          the length of the canonical permutation to create
   * @return the permutation
   */
  public static final int[] createCanonicalOne(final int count) {
    final int[] blue, res;

    if (count > 0) {
      blue = _CanonicalPermutation.__blueprint(count);
      if (blue.length == count) {
        return blue.clone();
      }
      res = new int[count];
      System.arraycopy(blue, 0, res, 0, count);
      return res;
    }
    return null;
  }

  /**
   * Create a canonical permutation of a given length whose first element
   * is zero. Passing in {@code count=4} to this method leads to
   * {@code (0, 1, 2, 3)}.
   * 
   * @param count
   *          the length of the canonical permutation to create
   * @return the permutation
   */
  public static final int[] createCanonicalZero(final int count) {
    final int[] blue, res;
    int num;

    if ((num = count) > 0) {
      res = new int[count];
      if ((--num) > 0) {
        blue = _CanonicalPermutation.__blueprint(num);
        System.arraycopy(blue, 1, res, 0, num);
      }
      return res;
    }
    return null;
  }
}
