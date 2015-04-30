package org.logisticPlanning.tsp.solving.utils.edgeData;

import java.util.Arrays;

/**
 * <p>
 * This class is an internal class. Please do not instantiate it, use it by
 * yourself, or otherwise meddle with it.
 * </p>
 * <p>
 * {@link _EdgeBitBooleanArray} represents a list of bits stored in an
 * {@code boolean} array.
 * </p>
 */
abstract class _EdgeBitBooleanArray extends EdgeBit {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the bits @serial the {@code boolean}-array holding the bit values */
  boolean[] m_bits;

  /**
   * create
   * 
   * @param n
   *          the number of nodes in the tsp
   */
  _EdgeBitBooleanArray(final int n) {
    super(n);
  }

  /** {@inheritDoc} */
  @Override
  public final void clear() {
    Arrays.fill(this.m_bits, false);
  }

  /** {@inheritDoc} */
  @Override
  public final void setAll() {
    Arrays.fill(this.m_bits, true);
  }

  /** {@inheritDoc} */
  @Override
  public final _EdgeBitBooleanArray clone() {
    final _EdgeBitBooleanArray res;

    res = ((_EdgeBitBooleanArray) (super.clone()));
    res.m_bits = res.m_bits.clone();

    return res;
  }

}
