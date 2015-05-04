package org.logisticPlanning.tsp.solving.utils.edgeData;

import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;

/**
 * <p>
 * A class that assigns an byte to each edge, specialized for
 * {@link org.logisticPlanning.tsp.solving.utils.edgeData._SymmetricEdgeByte
 * symmetric} and
 * {@link org.logisticPlanning.tsp.solving.utils.edgeData._AsymmetricEdgeByte
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
 * A {@code byte} needs at least 1 bytes in memory, leading to a total
 * consumption of at last 399980000 bytes, i.e., about 382&nbsp;MiB. In the
 * symmetric case, it is approximately half as much.
 * </p>
 */
public abstract class EdgeByte extends EdgeNumber {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the data array */
  byte[] m_data;

  /**
   * create
   *
   * @param n
   *          the number of nodes
   */
  EdgeByte(final int n) {
    super(n);
  }

  /** {@inheritDoc} */
  @Override
  public final void clear() {
    java.util.Arrays.fill(this.m_data, ((byte) (0)));
  }

  /** {@inheritDoc} */
  @Override
  public final boolean areFloatsAllowed() {
    return false;
  }

  /** {@inheritDoc} */
  @Override
  public final long getMinimumAllowedValue() {
    return (java.lang.Byte.MIN_VALUE);
  }

  /** {@inheritDoc} */
  @Override
  public final long getMaximumAllowedValue() {
    return (java.lang.Byte.MAX_VALUE);
  }

  /**
   * Allocate an edge byte array with all {@code byte}s set to
   * {@code ((byte)(0))}.
   *
   * @param f
   *          the objective function
   * @param old
   *          the old edge byte instance that may be re-used
   * @return the {@code EdgeBits} instance with the given features and all
   *         bits set to {@code false}
   */
  public static final EdgeByte allocate(final ObjectiveFunction f,
      final EdgeByte old) {

    return EdgeByte.allocate(f.n(), f.symmetric(), old);
  }

  /**
   * Allocate an edge byte array with all {@code byte}s set to
   * {@code ((byte)(0))}.
   *
   * @param n
   *          the number of nodes
   * @param symmetric
   *          is the instance symmetric?
   * @param old
   *          the old edge byte instance that may be re-used
   * @return the {@code EdgeBits} instance with the given features and all
   *         bits set to {@code false}
   */
  public static final EdgeByte allocate(final int n,
      final boolean symmetric, final EdgeByte old) {

    if ((old != null) && (old.m_n == n)
        && (symmetric == old.isSymmetric())) {
      old.clear();
      return old;
    }

    return (symmetric ? new _SymmetricEdgeByte(n)
        : new _AsymmetricEdgeByte(n));
  }

}
