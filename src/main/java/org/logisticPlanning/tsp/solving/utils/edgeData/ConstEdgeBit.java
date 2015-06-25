package org.logisticPlanning.tsp.solving.utils.edgeData;

/**
 * <p>
 * An edge bit set which is constant.
 * </p>
 *
 *
 @author <em><a href="http://www.it-weise.de/">Thomas Weise</a></em>,
 *         Email:&nbsp;<a
 *         href="mailto:tweise@ustc.edu.cn">tweise@ustc.edu.cn</a>
 */
public final class ConstEdgeBit extends EdgeBit {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * the constant edge bit value
   *
   * @serial a boolean
   */
  private final boolean m_value;

  /**
   * the internal constructor of the edge bit set
   *
   * @param n
   *          the number of nodes in the TSP
   * @param value
   *          the value to be returned
   */
  public ConstEdgeBit(final int n, final boolean value) {
    super(n);
    this.m_value = value;
  }

  /** {@inheritDoc} */
  @Override
  public final boolean get(final int a, final int b) {
    return this.m_value;
  }

  /** {@inheritDoc} */
  @Override
  public final boolean toggle(final int a, final int b) {
    throw new UnsupportedOperationException();
  }

  /** {@inheritDoc} */
  @Override
  public final void set(final int a, final int b) {
    throw new UnsupportedOperationException();
  }

  /** {@inheritDoc} */
  @Override
  public final void clear(final int a, final int b) {
    throw new UnsupportedOperationException();
  }

  /** {@inheritDoc} */
  @Override
  public final void set(final int a, final int b, final boolean value) {
    throw new UnsupportedOperationException();
  }

  /** {@inheritDoc} */
  @Override
  public final void setAll() {
    throw new UnsupportedOperationException();
  }

  /** {@inheritDoc} */
  @Override
  public final ConstEdgeBit clone() {
    return this;
  }

  /** {@inheritDoc} */
  @Override
  public boolean isSymmetric() {
    return true;
  }

  /** {@inheritDoc} */
  @Override
  public void clear() {
    throw new UnsupportedOperationException();
  }

}
