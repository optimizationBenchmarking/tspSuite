package org.logisticPlanning.tsp.benchmarking.dist;

import java.io.Serializable;

/**
 * <p>
 * This class is an internal class. Please do not instantiate it, use it by
 * yourself, or otherwise meddle with it.
 * </p>
 * <p>
 * The internal base-class for <a
 * href="https://en.wikipedia.org/wiki/Distance_matrix">distance
 * matrices</a>, i.e., a two-dimensional data structure which stores
 * node-wise distances. Such a matrix may be symmetric or asymmetric. We
 * can either load a distance matrix from a file or translate list-based
 * distance-computing metrics to matrices in order to speed up the
 * computations (as each distance is computed only once. This, of course,
 * is only possible if the dimension <code>n</code> (number of nodes) of
 * the TSP is not too high, as a matrix will always consume
 * <code>O(n<sup>2</sup>)</code> bytes of memory, regardless whether it is
 * symmetric or asymmetric.
 * </p>
 */
abstract class _DistanceMatrix extends DistanceComputer implements
    Serializable {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the maximum numbers per line */
  static final int MAX_NUMBERS_PER_LINE = 16;

  /** the data */
  int[] m_data;

  /**
   * create
   * 
   * @param n
   *          the dimension
   */
  _DistanceMatrix(final int n) {
    super(n);
  }

  /**
   * set the distance between two cities
   * 
   * @param a
   *          the first city
   * @param b
   *          the second city
   * @param dist
   *          the distance
   */
  abstract void setDistance(final int a, final int b, final int dist);

  /**
   * fill this matrix from the given source
   * 
   * @param src
   *          the source
   */
  void fillFrom(final DistanceComputer src) {
    int i, j;

    for (i = this.m_n; i > 0; i--) {
      for (j = this.m_n; j > 0; j--) {
        if (i != j) {
          this.setDistance(i, j, src.distance(i, j));
        }
      }
    }
  }

}
