package org.logisticPlanning.tsp.solving.utils;

import org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer;

/**
 * a distance computer always returning the same distance: {@code 0}
 * 
 * @since 0.9.8
 */
final class _Distance0Computer extends DistanceComputer {

  /** the one and only shared instance of this distance computer */
  static final _Distance0Computer INSTANCE = new _Distance0Computer();

  /** create */
  private _Distance0Computer() {
    super(2);
  }

  /**
   * always returns {@code 0}
   * 
   * @param i
   *          the first city
   * @param j
   *          the second city
   * @return 1
   */
  @Override
  public final int distance(final int i, final int j) {
    return 0;
  }

  /**
   * always returns {@code 0}
   * 
   * @param nodes
   *          the permutation
   * @return {@code 0}
   */
  @Override
  public final long evaluate(final int[] nodes) {
    return 0l;
  }

  /**
   * always returns {@code 0}
   * 
   * @param adjacencyList
   *          the adjacency list permutation
   * @return {@code 0}
   */
  @Override
  public final long evaluateAdj(final int[] adjacencyList) {
    return 0l;
  }
}
