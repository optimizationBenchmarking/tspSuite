package org.logisticPlanning.tsp.solving.utils.edgeData;

import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;

/**
 * <p>
 * A class that assigns an float to each edge, specialized for
 * {@link org.logisticPlanning.tsp.solving.utils.edgeData._SymmetricEdgeFloat
 * symmetric} and
 * {@link org.logisticPlanning.tsp.solving.utils.edgeData._AsymmetricEdgeFloat
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
 * A {@code float} needs at least 4 bytes in memory, leading to a total
 * consumption of at last 1599920000 bytes, i.e., about 2&nbsp;GiB. In the
 * symmetric case, it is approximately half as much.
 * </p>
 */
public abstract class EdgeFloat extends EdgeNumber {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the data array */
  float[] m_data;

  /**
   * create
   *
   * @param n
   *          the number of nodes
   */
  EdgeFloat(final int n) {
    super(n);
  }

  /** {@inheritDoc} */
  @Override
  public final void clear() {
    java.util.Arrays.fill(this.m_data, (0f));
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
   * Allocate an edge float array with all {@code float}s set to
   * {@code (0f)}.
   *
   * @param f
   *          the objective function
   * @param old
   *          the old edge float instance that may be re-used
   * @return the {@code EdgeBits} instance with the given features and all
   *         bits set to {@code false}
   */
  public static final EdgeFloat allocate(final ObjectiveFunction f,
      final EdgeFloat old) {

    return EdgeFloat.allocate(f.n(), f.symmetric(), old);
  }

  /**
   * Allocate an edge float array with all {@code float}s set to
   * {@code (0f)}.
   *
   * @param n
   *          the number of nodes
   * @param symmetric
   *          is the instance symmetric?
   * @param old
   *          the old edge float instance that may be re-used
   * @return the {@code EdgeBits} instance with the given features and all
   *         bits set to {@code false}
   */
  public static final EdgeFloat allocate(final int n,
      final boolean symmetric, final EdgeFloat old) {

    if ((old != null) && (old.m_n == n)
        && (symmetric == old.isSymmetric())) {
      old.clear();
      return old;
    }

    return (symmetric ? new _SymmetricEdgeFloat(n)
        : new _AsymmetricEdgeFloat(n));
  }

}
