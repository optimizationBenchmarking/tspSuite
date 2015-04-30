package org.logisticPlanning.tsp.solving.utils.edgeData;

import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;

/**
 * <p>
 * A class that assigns an int to each edge, specialized for
 * {@link org.logisticPlanning.tsp.solving.utils.edgeData._SymmetricEdgeInt
 * symmetric} and
 * {@link org.logisticPlanning.tsp.solving.utils.edgeData._AsymmetricEdgeInt
 * asymmetric} TSPs.
 * </p>
 * <p>
 * Warning: If you use an instance of this class in your code, you are
 * likely to not be able to tackle larger TSPs due to the memory
 * consumption! If you use an instance of this class in your code, you are
 * likely to not be able to tackle larger TSPs! If a TSP has
 * {@code n=20000} nodes, then in the asymmetric case, this will be
 * {@code n*(n-1) = 399980000} edges.
 * </p>
 * <p>
 * A {@code int} needs at least 4 bytes in memory, leading to a total
 * consumption of at last 1599920000 bytes, i.e., about 2&nbsp;GiB. In the
 * symmetric case, it is approximately half as much.
 * </p>
 */
public abstract class EdgeInt extends EdgeNumber {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the data array */
  int[] m_data;

  /**
   * create
   * 
   * @param n
   *          the number of nodes
   */
  EdgeInt(final int n) {
    super(n);
  }

  /** {@inheritDoc} */
  @Override
  public final void clear() {
    java.util.Arrays.fill(this.m_data, (0));
  }

  /** {@inheritDoc} */
  @Override
  public final boolean areFloatsAllowed() {
    return false;
  }

  /** {@inheritDoc} */
  @Override
  public final long getMinimumAllowedValue() {
    return (java.lang.Integer.MIN_VALUE);
  }

  /** {@inheritDoc} */
  @Override
  public final long getMaximumAllowedValue() {
    return (java.lang.Integer.MAX_VALUE);
  }

  /**
   * Allocate an edge int array with all {@code int}s set to {@code (0)}.
   * 
   * @param f
   *          the objective function
   * @param old
   *          the old edge int instance that may be re-used
   * @return the {@code EdgeBits} instance with the given features and all
   *         bits set to {@code false}
   */
  public static final EdgeInt allocate(final ObjectiveFunction f,
      final EdgeInt old) {

    return EdgeInt.allocate(f.n(), f.symmetric(), old);
  }

  /**
   * Allocate an edge int array with all {@code int}s set to {@code (0)}.
   * 
   * @param n
   *          the number of nodes
   * @param symmetric
   *          is the instance symmetric?
   * @param old
   *          the old edge int instance that may be re-used
   * @return the {@code EdgeBits} instance with the given features and all
   *         bits set to {@code false}
   */
  public static final EdgeInt allocate(final int n,
      final boolean symmetric, final EdgeInt old) {

    if ((old != null) && (old.m_n == n)
        && (symmetric == old.isSymmetric())) {
      old.clear();
      return old;
    }

    return (symmetric ? new _SymmetricEdgeInt(n) : new _AsymmetricEdgeInt(
        n));
  }

}
