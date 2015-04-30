package org.logisticPlanning.tsp.solving.utils.edgeData;

/**
 * <p>
 * This class is an internal class. Please do not instantiate it, use it by
 * yourself, or otherwise meddle with it.
 * </p>
 * <p>
 * A class that assigns on double to each edge, specialized for asymmetric
 * TSPs.
 * </p>
 * <p>
 * Warning: If you use an instance of this class in your code, you are
 * likely to not be able to tackle larger TSPs due to the memory
 * consumption!
 * </p>
 * <p>
 * The backing store of this matrix is a one-dimensional array of
 * double-precision (64-bit) floating point number (double) ({@code double}
 * ). Arrays are indexed with 32 bit signed integer (int) ({@code int}s).
 * Thus, the highest possible value of the matrix dimension {@code n} is
 * 46'341. Be aware that such a matrix may have a high memory consumption
 * for high values of {@code n}:
 * </p>
 * <table border="1">
 * <tr>
 * <td>{@code n=10}</td>
 * <td>{@code n=64}</td>
 * <td>{@code n=100}</td>
 * <td>{@code n=1'024}</td>
 * <td>{@code n=10'000}</td>
 * <td>{@code n=16'384}</td>
 * <td>{@code n=46'341}</td>
 * </tr>
 * <tr>
 * <td>720&nbsp;bytes</td>
 * <td>32&prime;256&nbsp;bytes &asymp; 32&nbsp;KiB</td>
 * <td>79&prime;200&nbsp;bytes &asymp; 78&nbsp;KiB</td>
 * <td>8&prime;380&prime;416&nbsp;bytes &asymp; 8&nbsp;MiB</td>
 * <td>799&prime;920&prime;000&nbsp;bytes &asymp; 763&nbsp;MiB</td>
 * <td>2&prime;147&prime;352&prime;576&nbsp;bytes &asymp; 2&nbsp;GiB</td>
 * <td>17&prime;179&prime;535&prime;520&nbsp;bytes &asymp; 16&nbsp;GiB</td>
 * </tr>
 * </table>
 * <p>
 * Thus, if {@code n} rises or you allocate many objects, you may get an
 * {@link java.lang.OutOfMemoryError}.
 * </p>
 * 
 * 
 @author <em><a href="http://www.it-weise.de/">Thomas Weise</a></em>,
 *         Email:&nbsp;<a
 *         href="mailto:tweise@ustc.edu.cn">tweise@ustc.edu.cn</a>
 */
final class _AsymmetricEdgeDouble extends EdgeDouble {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * create the _AsymmetricEdgeDouble
   * 
   * @param n
   *          the number of nodes
   */
  _AsymmetricEdgeDouble(final int n) {
    super(n);
    if (n > 46341) {
      // The maximum size is calculated such that 32-bit integer index
      // computations
      // cannot overflow.
      throw new IllegalArgumentException( //
          "Matrix dimension cannot be higher than 46'341."); //$NON-NLS-1$
    }
    this.m_data = new double[((n) * ((n) - 1))];
  }

  /** {@inheritDoc} */
  @Override
  public final void setByte(final int a, final int b, final byte value) {
    this.m_data[((((a) - 1) * ((this.m_n) - 1)) + (((b) > (a)) ? ((b) - 2)
        : ((b) - 1)))] = (value);
  }

  /** {@inheritDoc} */
  @Override
  public final byte getByte(final int a, final int b) {
    return ((byte) ((this.m_data[((((a) - 1) * ((this.m_n) - 1)) + (((b) > (a)) ? ((b) - 2)
        : ((b) - 1)))])));
  }

  /** {@inheritDoc} */
  @Override
  public final void addByte(final int a, final int b, final byte value) {
    this.m_data[((((a) - 1) * ((this.m_n) - 1)) + (((b) > (a)) ? ((b) - 2)
        : ((b) - 1)))] += (value);
  }

  /** {@inheritDoc} */
  @Override
  public final void setShort(final int a, final int b, final short value) {
    this.m_data[((((a) - 1) * ((this.m_n) - 1)) + (((b) > (a)) ? ((b) - 2)
        : ((b) - 1)))] = (value);
  }

  /** {@inheritDoc} */
  @Override
  public final short getShort(final int a, final int b) {
    return ((short) ((this.m_data[((((a) - 1) * ((this.m_n) - 1)) + (((b) > (a)) ? ((b) - 2)
        : ((b) - 1)))])));
  }

  /** {@inheritDoc} */
  @Override
  public final void addShort(final int a, final int b, final short value) {
    this.m_data[((((a) - 1) * ((this.m_n) - 1)) + (((b) > (a)) ? ((b) - 2)
        : ((b) - 1)))] += (value);
  }

  /** {@inheritDoc} */
  @Override
  public final void setInt(final int a, final int b, final int value) {
    this.m_data[((((a) - 1) * ((this.m_n) - 1)) + (((b) > (a)) ? ((b) - 2)
        : ((b) - 1)))] = (value);
  }

  /** {@inheritDoc} */
  @Override
  public final int getInt(final int a, final int b) {
    return ((int) ((this.m_data[((((a) - 1) * ((this.m_n) - 1)) + (((b) > (a)) ? ((b) - 2)
        : ((b) - 1)))])));
  }

  /** {@inheritDoc} */
  @Override
  public final void addInt(final int a, final int b, final int value) {
    this.m_data[((((a) - 1) * ((this.m_n) - 1)) + (((b) > (a)) ? ((b) - 2)
        : ((b) - 1)))] += (value);
  }

  /** {@inheritDoc} */
  @Override
  public final void setLong(final int a, final int b, final long value) {
    this.m_data[((((a) - 1) * ((this.m_n) - 1)) + (((b) > (a)) ? ((b) - 2)
        : ((b) - 1)))] = (value);
  }

  /** {@inheritDoc} */
  @Override
  public final long getLong(final int a, final int b) {
    return ((long) ((this.m_data[((((a) - 1) * ((this.m_n) - 1)) + (((b) > (a)) ? ((b) - 2)
        : ((b) - 1)))])));
  }

  /** {@inheritDoc} */
  @Override
  public final void addLong(final int a, final int b, final long value) {
    this.m_data[((((a) - 1) * ((this.m_n) - 1)) + (((b) > (a)) ? ((b) - 2)
        : ((b) - 1)))] += (value);
  }

  /** {@inheritDoc} */
  @Override
  public final void setFloat(final int a, final int b, final float value) {
    this.m_data[((((a) - 1) * ((this.m_n) - 1)) + (((b) > (a)) ? ((b) - 2)
        : ((b) - 1)))] = (value);
  }

  /** {@inheritDoc} */
  @Override
  public final float getFloat(final int a, final int b) {
    return ((float) (this.m_data[((((a) - 1) * ((this.m_n) - 1)) + (((b) > (a)) ? ((b) - 2)
        : ((b) - 1)))]));
  }

  /** {@inheritDoc} */
  @Override
  public final void addFloat(final int a, final int b, final float value) {
    this.m_data[((((a) - 1) * ((this.m_n) - 1)) + (((b) > (a)) ? ((b) - 2)
        : ((b) - 1)))] += (value);
  }

  /** {@inheritDoc} */
  @Override
  public final void setDouble(final int a, final int b, final double value) {
    this.m_data[((((a) - 1) * ((this.m_n) - 1)) + (((b) > (a)) ? ((b) - 2)
        : ((b) - 1)))] = (value);
  }

  /** {@inheritDoc} */
  @Override
  public final double getDouble(final int a, final int b) {
    return (this.m_data[((((a) - 1) * ((this.m_n) - 1)) + (((b) > (a)) ? ((b) - 2)
        : ((b) - 1)))]);
  }

  /** {@inheritDoc} */
  @Override
  public final void addDouble(final int a, final int b, final double value) {
    this.m_data[((((a) - 1) * ((this.m_n) - 1)) + (((b) > (a)) ? ((b) - 2)
        : ((b) - 1)))] += (value);
  }

  /** {@inheritDoc} */
  @Override
  public final void inc(final int a, final int b) {
    this.m_data[((((a) - 1) * ((this.m_n) - 1)) + (((b) > (a)) ? ((b) - 2)
        : ((b) - 1)))]++;
  }

  /** {@inheritDoc} */
  @Override
  public final void dec(final int a, final int b) {
    this.m_data[((((a) - 1) * ((this.m_n) - 1)) + (((b) > (a)) ? ((b) - 2)
        : ((b) - 1)))]--;
  }

  /** {@inheritDoc} */
  @Override
  public final boolean isSymmetric() {
    return false;
  }

}
