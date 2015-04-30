package org.logisticPlanning.tsp.solving.operators.permutation.localOpt;

import java.util.Arrays;
import java.util.Iterator;

import org.logisticPlanning.utils.utils.EmptyUtils;

/**
 * This iterator enumerates all permutations of a given length. It starts
 * with a canonical permutation and enumerates all possible permutations
 * one by one. The implementation here uses <a href=
 * "http://en.wikipedia.org/wiki/Steinhaus-Johnson-Trotter_algorithm#Even.27s_speedup"
 * >Even's Speedup</a>&nbsp;[<a href="#cite_E1973AC"
 * style="font-weight:bold">1</a>] of the <a href=
 * "http://en.wikipedia.org/wiki/Steinhaus-Johnson-Trotter_algorithm"
 * >Steinhaus-Johnson-Trotter Algorithm</a>. Even's Speedup allows us to
 * get the next permutation from the current permutation by performing a
 * single, adjacent swap (i.e., exchanging two neighboring elements).
 */
final class _PermutationIterator implements Iterator<int[]> {

  /** the m_work */
  private final int[] m_work;

  /** the direction */
  private final boolean[] m_dir;

  /** the state */
  private int m_state;

  /**
   * create the iterator
   * 
   * @param n
   *          the length of the permutations
   */
  public _PermutationIterator(final int n) {
    super();
    if (n > 0) {
      this.m_work = new int[n];
      this.m_dir = new boolean[n];
    } else {
      this.m_work = EmptyUtils.EMPTY_INTS;
      this.m_dir = EmptyUtils.EMPTY_BOOLEANS;
    }
    this.reset();
  }

  /** reset the state of the iterator */
  public final void reset() {
    this.m_state = 0;
    _CanonicalPermutation.makeCanonicalOne(this.m_work);
    Arrays.fill(this.m_dir, false);
  }

  /** {@inheritDoc} */
  @Override
  public final boolean hasNext() {
    final int movePos, n, nMinus1;
    final int[] work;
    final boolean[] dir;
    int largestMobile, largestMobileIndex, before, after, current, index, indexBefore;
    boolean largestMobileDir, currentDir;

    switch (this.m_state) {

      case 0: {
        return true;
      }

      case 1: {

        work = this.m_work;
        dir = this.m_dir;

        largestMobileDir = false;
        largestMobile = largestMobileIndex = (-1);
        n = work.length;
        nMinus1 = (n - 1);

        index = n;
        indexBefore = (index - 1);
        before = work[indexBefore];
        current = (-1);

        looper: while (index > 0) {
          index = indexBefore;
          after = current;
          current = before;
          if (index > 0) {
            before = work[--indexBefore];
          }

          if (largestMobile < current) {

            currentDir = dir[index];
            isNotMobile: {

              if (index <= 0) {
                if (!currentDir) {
                  continue looper;
                }
                if (current > after) {
                  break isNotMobile;
                }
                continue looper;
              }

              if (index >= nMinus1) {
                if (currentDir) {
                  continue looper;
                }
                if (current > before) {
                  break isNotMobile;
                }
                continue looper;
              }

              if (currentDir) {
                if (current > after) {
                  break isNotMobile;
                }
              } else {
                if (current > before) {
                  break isNotMobile;
                }
              }
              continue looper;
            }

            largestMobile = current;
            largestMobileIndex = index;
            largestMobileDir = currentDir;
            if (current >= n) {
              break looper;
            }
          }
        }

        if (largestMobileIndex < 0) {// we are at the end
          this.m_state = 2;
          return false;
        }

        movePos = (largestMobileIndex + (largestMobileDir ? 1 : (-1)));

        work[largestMobileIndex] = work[movePos];
        work[movePos] = largestMobile;

        dir[largestMobileIndex] = dir[movePos];
        dir[movePos] = largestMobileDir;

        // reverse direction
        index = 0;
        for (final int workAtIndex : this.m_work) {
          if (workAtIndex > largestMobile) {
            dir[index] ^= true;// toggle
          }
          index++;
        }

        this.m_state = 0;
        return true;
      }
      default: {
        return false;
      }
    }
  }

  /** {@inheritDoc} */
  @Override
  public final int[] next() {
    if (this.hasNext()) {
      this.m_state = 1;
      return this.m_work;
    }
    return null;
  }

  /** {@inheritDoc} */
  @Override
  public final void remove() {
    // do nothing
  }
}
