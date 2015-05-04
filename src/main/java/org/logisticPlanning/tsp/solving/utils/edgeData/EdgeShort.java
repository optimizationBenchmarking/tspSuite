package org.logisticPlanning.tsp.solving.utils.edgeData;

import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;

/**
 * <p>
 * A class that assigns an short to each edge, specialized for
 * {@link org.logisticPlanning.tsp.solving.utils.edgeData._SymmetricEdgeShort
 * symmetric} and
 * {@link org.logisticPlanning.tsp.solving.utils.edgeData._AsymmetricEdgeShort
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
 * A {@code short} needs at least 2 bytes in memory, leading to a total
 * consumption of at last 799960000 bytes, i.e., about 763&nbsp;MiB. In the
 * symmetric case, it is approximately half as much.
 * </p>
 */
public abstract class EdgeShort extends EdgeNumber {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the data array */
  short[] m_data;

  /**
   * create
   *
   * @param n
   *          the number of nodes
   */
  EdgeShort(final int n) {
    super(n);
  }

  /** {@inheritDoc} */
  @Override
  public final void clear() {
    java.util.Arrays.fill(this.m_data, ((short) (0)));
  }

  /** {@inheritDoc} */
  @Override
  public final boolean areFloatsAllowed() {
    return false;
  }

  /** {@inheritDoc} */
  @Override
  public final long getMinimumAllowedValue() {
    return (java.lang.Short.MIN_VALUE);
  }

  /** {@inheritDoc} */
  @Override
  public final long getMaximumAllowedValue() {
    return (java.lang.Short.MAX_VALUE);
  }

  /**
   * Allocate an edge short array with all {@code short}s set to
   * {@code ((short)(0))}.
   *
   * @param f
   *          the objective function
   * @param old
   *          the old edge short instance that may be re-used
   * @return the {@code EdgeBits} instance with the given features and all
   *         bits set to {@code false}
   */
  public static final EdgeShort allocate(final ObjectiveFunction f,
      final EdgeShort old) {

    return EdgeShort.allocate(f.n(), f.symmetric(), old);
  }

  /**
   * Allocate an edge short array with all {@code short}s set to
   * {@code ((short)(0))}.
   *
   * @param n
   *          the number of nodes
   * @param symmetric
   *          is the instance symmetric?
   * @param old
   *          the old edge short instance that may be re-used
   * @return the {@code EdgeBits} instance with the given features and all
   *         bits set to {@code false}
   */
  public static final EdgeShort allocate(final int n,
      final boolean symmetric, final EdgeShort old) {

    if ((old != null) && (old.m_n == n)
        && (symmetric == old.isSymmetric())) {
      old.clear();
      return old;
    }

    return (symmetric ? new _SymmetricEdgeShort(n)
    : new _AsymmetricEdgeShort(n));
  }

}
