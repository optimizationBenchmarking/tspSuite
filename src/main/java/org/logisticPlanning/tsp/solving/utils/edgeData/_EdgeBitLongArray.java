package org.logisticPlanning.tsp.solving.utils.edgeData;

import java.util.Arrays;

/**
 * <p>
 * This class is an internal class. Please do not instantiate it, use it by
 * yourself, or otherwise meddle with it.
 * </p>
 * <p>
 * {@link _EdgeBitLongArray} represents a list of bits stored in an
 * {@code long} array.
 * </p>
 */
abstract class _EdgeBitLongArray extends EdgeBit {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the bits @serial the {@code long}-array holding the bit values */
  long[] m_bits;

  /**
   * create
   * 
   * @param n
   *          the number of nodes in the tsp
   */
  _EdgeBitLongArray(final int n) {
    super(n);
  }

  /** {@inheritDoc} */
  @Override
  public final void clear() {
    Arrays.fill(this.m_bits, 0l);
  }

  /** {@inheritDoc} */
  @Override
  public final void setAll() {
    Arrays.fill(this.m_bits, 0xffffffffffffffffl);
  }

  /** {@inheritDoc} */
  @Override
  public final _EdgeBitLongArray clone() {
    final _EdgeBitLongArray res;

    res = ((_EdgeBitLongArray) (super.clone()));
    res.m_bits = res.m_bits.clone();

    return res;
  }

}
