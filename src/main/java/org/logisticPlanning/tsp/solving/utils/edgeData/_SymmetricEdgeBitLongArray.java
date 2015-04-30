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
 * {@link _SymmetricEdgeBitLongArray} represents a list of bits stored in
 * an {@code long} array. This class is intended for symmetric TSPs.
 * </p>
 */
public final class _SymmetricEdgeBitLongArray extends _EdgeBitLongArray {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * create the _SymmetricEdgeBitLongArray
   * 
   * @param n
   *          the number of nodes in the tsp
   */
  public _SymmetricEdgeBitLongArray(final int n) {
    super(n);
    if (n > 46342) {
      // The maximum size is calculated such that 32-bit integer index
      // computations
      // cannot overflow.
      throw new IllegalArgumentException( //
          "Matrix dimension cannot be higher than 46'342."); //$NON-NLS-1$
    }

    this.m_bits = new long[(((((int) ((((long) (n)) * ((n) - 1)) >>> 1))) + 63) >>> 6)];
  }

  /** {@inheritDoc} */
  @Override
  public final boolean get(final int a, final int b) {
    final int i, j, addr;

    if (a > b) {
      i = a;
      j = b;
    } else {
      i = b;
      j = a;
    }
    addr = ((((((i) - 1) * ((i) - 2)) >>> 1) + (j)) - 1);
    return ((this.m_bits[(addr >>> 6)] & (1l << addr)) != 0);
  }

  /** {@inheritDoc} */
  @Override
  public final void set(final int a, final int b, final boolean value) {
    final int i, j, addr;

    if (a > b) {
      i = a;
      j = b;
    } else {
      i = b;
      j = a;
    }
    addr = ((((((i) - 1) * ((i) - 2)) >>> 1) + (j)) - 1);
    if (value) {
      this.m_bits[addr >>> 6] |= (1l << addr);
    } else {
      this.m_bits[addr >>> 6] &= (~(1l << addr));
    }
  }

  /** {@inheritDoc} */
  @Override
  public final void set(final int a, final int b) {
    final int i, j, addr;

    if (a > b) {
      i = a;
      j = b;
    } else {
      i = b;
      j = a;
    }
    addr = ((((((i) - 1) * ((i) - 2)) >>> 1) + (j)) - 1);
    this.m_bits[addr >>> 6] |= (1l << addr);
  }

  /** {@inheritDoc} */
  @Override
  public final boolean toggle(final int a, final int b) {
    final long result, bit;
    final int i, j, addr;

    if (a > b) {
      i = a;
      j = b;
    } else {
      i = b;
      j = a;
    }
    addr = ((((((i) - 1) * ((i) - 2)) >>> 1) + (j)) - 1);
    bit = (1l << addr);
    result = (this.m_bits[addr >>> 6] ^= bit);
    return ((result & bit) != 0l);
  }

  /** {@inheritDoc} */
  @Override
  public final void clear(final int a, final int b) {
    final int i, j, addr;

    if (a > b) {
      i = a;
      j = b;
    } else {
      i = b;
      j = a;
    }
    addr = ((((((i) - 1) * ((i) - 2)) >>> 1) + (j)) - 1);
    this.m_bits[addr >>> 6] &= (~(1l << addr));
  }

  /** {@inheritDoc} */
  @Override
  public final boolean isSymmetric() {
    return true;
  }
}
