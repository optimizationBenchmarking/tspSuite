package org.logisticPlanning.tsp.solving.operators.permutation.localOpt;

import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.Callable;

import org.logisticPlanning.utils.utils.MemoryUtils;

/** list some differences and similarities */
final class _PermutationPathBuilder implements
    Callable<_PermutationPath[]>, Comparator<int[]> {

  /** the length */
  private final int m_length;

  /** the permutations */
  private _PermutationPath[] m_permutations;

  /**
   * create
   *
   * @param length
   *          the length
   */
  _PermutationPathBuilder(final int length) {
    super();
    this.m_length = length;
  }

  /** allocate the permutations */
  private final void __allocatePermutations() {
    final int length;
    _PermutationIterator it;
    int count, i;

    length = this.m_length;
    count = 1;
    for (i = 1; i <= length; i++) {
      count *= i;
    }

    this.m_permutations = new _PermutationPath[count];
    it = new _PermutationIterator(length);

    for (i = 0; i < count; i++) {
      this.m_permutations[i] = new _PermutationPath(it.next().clone(), i);
    }
  }

  // /**
  // * the main method
  // *
  // * @param args
  // * the args
  // */
  // public static void main(String[] args) {
  // final _PermutationPath path;
  //
  // path = new _PermutationPathBuilder(11).call();
  //
  // __gc();
  // path._print();
  // }

  /** {@inheritDoc} */
  @Override
  public _PermutationPath[] call() {
    final _PermutationPath[] res;

    this.__allocatePermutations();
    this.__findShortestPaths();
    res = this.m_permutations;

    _PermutationPathBuilder.__gc();

    return res;
  }

  /** perform gc */
  private static final void __gc() {
    for (int i = 0; i < 10; i++) {
      MemoryUtils.gc();
    }
  }

  /** find the shortest paths */
  private final void __findShortestPaths() {
    final _PermutationPath[] paths;
    final int count;
    final int[][] ownerPairs, childPairs;
    int ownerIndex, childIndex, minPathCount, add_1_1, add_1_2, add_2_1, add_2_2, remove_1_1, remove_1_2, remove_2_1, remove_2_2, childrenCount;
    _PermutationPath ownerPath, childPath, temp;
    _PermutationPath[] children;

    paths = this.m_permutations;
    count = this.m_permutations.length;

    ownerPairs = new int[this.m_length + 1][2];
    childPairs = new int[this.m_length + 1][2];
    children = new _PermutationPath[(this.m_length * (this.m_length - 1)) >>> 1];

    ownerPath = paths[0];
    ownerPath.m_add_1_1 = ownerPath.m_add_1_2 = ownerPath.m_add_2_1 = ownerPath.m_add_2_2 = ownerPath.m_remove_1_1 = ownerPath.m_remove_1_2 = ownerPath.m_remove_2_1 = ownerPath.m_remove_2_2 = (-1);

    minPathCount = 1;
    for (ownerIndex = 0; ownerIndex < count; ownerIndex++) {
      ownerPath = paths[ownerIndex];
      childrenCount = 0;
      this.__pairs(ownerPath.m_permutation, ownerPairs);

      looper: for (childIndex = minPathCount; childIndex < count; childIndex++) {
        childPath = paths[childIndex];

        this.__pairs(childPath.m_permutation, childPairs);

        add_1_1 = add_1_2 = add_2_1 = add_2_2 = remove_1_1 = remove_1_2 = remove_2_1 = remove_2_2 = (-1);

        for (final int[] a : childPairs) {
          if (Arrays.binarySearch(ownerPairs, a, this) < 0) {
            if (add_1_1 >= 0) {
              if (add_2_1 >= 0) {
                continue looper;
              }
              add_2_1 = a[0];
              add_2_2 = a[1];
            } else {
              add_1_1 = a[0];
              add_1_2 = a[1];
            }
          }
        }

        for (final int[] a : ownerPairs) {
          if (Arrays.binarySearch(childPairs, a, this) < 0) {
            if (remove_1_1 >= 0) {
              if (remove_2_1 >= 0) {
                continue looper;
              }
              remove_2_1 = a[0];
              remove_2_2 = a[1];
            } else {
              remove_1_1 = a[0];
              remove_1_2 = a[1];
            }
          }
        }

        childPath.m_owner = ownerPath;
        childPath.m_add_1_1 = add_1_1;
        childPath.m_add_1_2 = add_1_2;
        childPath.m_add_2_1 = add_2_1;
        childPath.m_add_2_2 = add_2_2;
        childPath.m_remove_1_1 = remove_1_1;
        childPath.m_remove_1_2 = remove_1_2;
        childPath.m_remove_2_1 = remove_2_1;
        childPath.m_remove_2_2 = remove_2_2;

        temp = paths[minPathCount];
        paths[minPathCount] = childPath;
        paths[childIndex] = temp;
        children[childrenCount++] = childPath;
        if ((++minPathCount) >= count) {
          break looper;
        }
      }

      if (childrenCount > 0) {
        ownerPath.m_children = Arrays.copyOf(children, childrenCount);
      }
    }

    if (minPathCount < paths.length) {
      throw new IllegalStateException();
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
  private final void __pairs(final int[] perm, final int[][] pairs) {
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
    pairs[i][1] = Integer.MAX_VALUE;
    Arrays.sort(pairs, this);
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

}
