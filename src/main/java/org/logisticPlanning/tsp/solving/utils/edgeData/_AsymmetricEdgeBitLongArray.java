package org.logisticPlanning.tsp.solving.utils.edgeData;

/**
 * <p>
 * This class is an internal class. You <em>MUST NEVER</em> instantiate
 * this class. It is only public for unit testing purposes. Hands off! This
 * is not for you! Use
 * {@link org.logisticPlanning.tsp.solving.utils.edgeData.EdgeBit#allocate(org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction,EdgeBit)}
 * to get the right type of
 * {@link org.logisticPlanning.tsp.solving.utils.edgeData.EdgeBit EdgeBits}
 * .
 * </p>
 * <p>
 * {@link _AsymmetricEdgeBitLongArray} represents a list of bits stored in
 * an {@code long} array. This class is intended for asymmetric TSPs.
 * </p>
 */
public final class _AsymmetricEdgeBitLongArray extends _EdgeBitLongArray {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * create the _AsymmetricEdgeBitLongArray
   * 
   * @param n
   *          the number of nodes in the tsp
   */
  public _AsymmetricEdgeBitLongArray(final int n) {
    super(n);
    if (n > 46341) {
      // The maximum size is calculated such that 32-bit integer index
      // computations
      // cannot overflow.
      throw new IllegalArgumentException( //
          "Matrix dimension cannot be higher than 46'341."); //$NON-NLS-1$
    }

    this.m_bits = new long[(((((n) * ((n) - 1))) + 63) >>> 6)];
  }

  /** {@inheritDoc} */
  @Override
  public final boolean get(final int a, final int b) {

    final int addr;
    addr = ((((a) - 1) * ((this.m_n) - 1)) + (((b) > (a)) ? ((b) - 2)
        : ((b) - 1)));
    return ((this.m_bits[(addr >>> 6)] & (1l << addr)) != 0);
  }

  /** {@inheritDoc} */
  @Override
  public final void set(final int a, final int b, final boolean value) {

    final int addr;
    addr = ((((a) - 1) * ((this.m_n) - 1)) + (((b) > (a)) ? ((b) - 2)
        : ((b) - 1)));
    if (value) {
      this.m_bits[addr >>> 6] |= (1l << addr);
    } else {
      this.m_bits[addr >>> 6] &= (~(1l << addr));
    }
  }

  /** {@inheritDoc} */
  @Override
  public final void set(final int a, final int b) {

    final int addr;
    addr = ((((a) - 1) * ((this.m_n) - 1)) + (((b) > (a)) ? ((b) - 2)
        : ((b) - 1)));
    this.m_bits[addr >>> 6] |= (1l << addr);
  }

  /** {@inheritDoc} */
  @Override
  public final boolean toggle(final int a, final int b) {
    final long result, bit;

    final int addr;
    addr = ((((a) - 1) * ((this.m_n) - 1)) + (((b) > (a)) ? ((b) - 2)
        : ((b) - 1)));
    bit = (1l << addr);
    result = (this.m_bits[addr >>> 6] ^= bit);
    return ((result & bit) != 0l);
  }

  /** {@inheritDoc} */
  @Override
  public final void clear(final int a, final int b) {

    final int addr;
    addr = ((((a) - 1) * ((this.m_n) - 1)) + (((b) > (a)) ? ((b) - 2)
        : ((b) - 1)));
    this.m_bits[addr >>> 6] &= (~(1l << addr));
  }

  /** {@inheritDoc} */
  @Override
  public final boolean isSymmetric() {
    return false;
  }
}
