package org.logisticPlanning.tsp.solving.utils.edgeData;

import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;

/**
 * <p>
 * A class that assigns an double to each edge, specialized for
 * {@link org.logisticPlanning.tsp.solving.utils.edgeData._SymmetricEdgeDouble
 * symmetric} and
 * {@link org.logisticPlanning.tsp.solving.utils.edgeData._AsymmetricEdgeDouble
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
 * A {@code double} needs at least 8 bytes in memory, leading to a total
 * consumption of at last 3199840000 bytes, i.e., about 3&nbsp;GiB. In the
 * symmetric case, it is approximately half as much.
 * </p>
 */
public abstract class EdgeDouble extends EdgeNumber {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the data array */
  double[] m_data;

  /**
   * create
   *
   * @param n
   *          the number of nodes
   */
  EdgeDouble(final int n) {
    super(n);
  }

  /** {@inheritDoc} */
  @Override
  public final void clear() {
    java.util.Arrays.fill(this.m_data, (0d));
  }

  /** {@inheritDoc} */
  @Override
  public final boolean areFloatsAllowed() {
    return true;
  }

  /** {@inheritDoc} */
  @Override
  public final long getMinimumAllowedValue() {
    return (java.lang.Long.MIN_VALUE);
  }

  /** {@inheritDoc} */
  @Override
  public final long getMaximumAllowedValue() {
    return (java.lang.Long.MAX_VALUE);
  }

  /**
   * Allocate an edge double array with all {@code double}s set to
   * {@code (0d)}.
   *
   * @param f
   *          the objective function
   * @param old
   *          the old edge double instance that may be re-used
   * @return the {@code EdgeBits} instance with the given features and all
   *         bits set to {@code false}
   */
  public static final EdgeDouble allocate(final ObjectiveFunction f,
      final EdgeDouble old) {

    return EdgeDouble.allocate(f.n(), f.symmetric(), old);
  }

  /**
   * Allocate an edge double array with all {@code double}s set to
   * {@code (0d)}.
   *
   * @param n
   *          the number of nodes
   * @param symmetric
   *          is the instance symmetric?
   * @param old
   *          the old edge double instance that may be re-used
   * @return the {@code EdgeBits} instance with the given features and all
   *         bits set to {@code false}
   */
  public static final EdgeDouble allocate(final int n,
      final boolean symmetric, final EdgeDouble old) {

    if ((old != null) && (old.m_n == n)
        && (symmetric == old.isSymmetric())) {
      old.clear();
      return old;
    }

    return (symmetric ? new _SymmetricEdgeDouble(n)
    : new _AsymmetricEdgeDouble(n));
  }

}
