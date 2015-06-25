package org.logisticPlanning.tsp.solving.utils.edgeData;

/**
 * <p>
 * This class is an internal class. Please do not instantiate it, use it by
 * yourself, or otherwise meddle with it.
 * </p>
 * <p>
 * A class that assigns on byte to each edge, specialized for asymmetric
 * TSPs.
 * </p>
 * <p>
 * Warning: If you use an instance of this class in your code, you are
 * likely to not be able to tackle larger TSPs due to the memory
 * consumption!
 * </p>
 * <p>
 * The backing store of this matrix is a one-dimensional array of 8 bit
 * signed integer (byte) ({@code byte}). Arrays are indexed with 32 bit
 * signed integer (int) ({@code int}s). Thus, the highest possible value of
 * the matrix dimension {@code n} is 46'341. Be aware that such a matrix
 * may have a high memory consumption for high values of {@code n}:
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
 * <td>90&nbsp;bytes</td>
 * <td>4&prime;032&nbsp;bytes &asymp; 4&nbsp;KiB</td>
 * <td>9&prime;900&nbsp;bytes &asymp; 10&nbsp;KiB</td>
 * <td>1&prime;047&prime;552&nbsp;bytes &asymp; 1&prime;023&nbsp;KiB</td>
 * <td>99&prime;990&prime;000&nbsp;bytes &asymp; 96&nbsp;MiB</td>
 * <td>268&prime;419&prime;072&nbsp;bytes &asymp; 256&nbsp;MiB</td>
 * <td>2&prime;147&prime;441&prime;940&nbsp;bytes &asymp; 2&nbsp;GiB</td>
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
final class _AsymmetricEdgeByte extends EdgeByte {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * create the _AsymmetricEdgeByte
   *
   * @param n
   *          the number of nodes
   */
  _AsymmetricEdgeByte(final int n) {
    super(n);
    if (n > 46341) {
      // The maximum size is calculated such that 32-bit integer index
      // computations
      // cannot overflow.
      throw new IllegalArgumentException( //
          "Matrix dimension cannot be higher than 46'341."); //$NON-NLS-1$
    }
    this.m_data = new byte[((n) * ((n) - 1))];
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
    return (this.m_data[((((a) - 1) * ((this.m_n) - 1)) + (((b) > (a)) ? ((b) - 2)
        : ((b) - 1)))]);
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
        : ((b) - 1)))] = ((byte) (value));
  }

  /** {@inheritDoc} */
  @Override
  public final short getShort(final int a, final int b) {
    return (this.m_data[((((a) - 1) * ((this.m_n) - 1)) + (((b) > (a)) ? ((b) - 2)
        : ((b) - 1)))]);
  }

  /** {@inheritDoc} */
  @Override
  public final void addShort(final int a, final int b, final short value) {
    this.m_data[((((a) - 1) * ((this.m_n) - 1)) + (((b) > (a)) ? ((b) - 2)
        : ((b) - 1)))] += ((byte) (value));
  }

  /** {@inheritDoc} */
  @Override
  public final void setInt(final int a, final int b, final int value) {
    this.m_data[((((a) - 1) * ((this.m_n) - 1)) + (((b) > (a)) ? ((b) - 2)
        : ((b) - 1)))] = ((byte) (value));
  }

  /** {@inheritDoc} */
  @Override
  public final int getInt(final int a, final int b) {
    return (this.m_data[((((a) - 1) * ((this.m_n) - 1)) + (((b) > (a)) ? ((b) - 2)
        : ((b) - 1)))]);
  }

  /** {@inheritDoc} */
  @Override
  public final void addInt(final int a, final int b, final int value) {
    this.m_data[((((a) - 1) * ((this.m_n) - 1)) + (((b) > (a)) ? ((b) - 2)
        : ((b) - 1)))] += ((byte) (value));
  }

  /** {@inheritDoc} */
  @Override
  public final void setLong(final int a, final int b, final long value) {
    this.m_data[((((a) - 1) * ((this.m_n) - 1)) + (((b) > (a)) ? ((b) - 2)
        : ((b) - 1)))] = ((byte) (value));
  }

  /** {@inheritDoc} */
  @Override
  public final long getLong(final int a, final int b) {
    return (this.m_data[((((a) - 1) * ((this.m_n) - 1)) + (((b) > (a)) ? ((b) - 2)
        : ((b) - 1)))]);
  }

  /** {@inheritDoc} */
  @Override
  public final void addLong(final int a, final int b, final long value) {
    this.m_data[((((a) - 1) * ((this.m_n) - 1)) + (((b) > (a)) ? ((b) - 2)
        : ((b) - 1)))] += ((byte) (value));
  }

  /** {@inheritDoc} */
  @Override
  public final void setFloat(final int a, final int b, final float value) {
    this.m_data[((((a) - 1) * ((this.m_n) - 1)) + (((b) > (a)) ? ((b) - 2)
        : ((b) - 1)))] = ((byte) ((value)));
  }

  /** {@inheritDoc} */
  @Override
  public final float getFloat(final int a, final int b) {
    return (this.m_data[((((a) - 1) * ((this.m_n) - 1)) + (((b) > (a)) ? ((b) - 2)
        : ((b) - 1)))]);
  }

  /** {@inheritDoc} */
  @Override
  public final void addFloat(final int a, final int b, final float value) {
    this.m_data[((((a) - 1) * ((this.m_n) - 1)) + (((b) > (a)) ? ((b) - 2)
        : ((b) - 1)))] += ((byte) ((value)));
  }

  /** {@inheritDoc} */
  @Override
  public final void setDouble(final int a, final int b, final double value) {
    this.m_data[((((a) - 1) * ((this.m_n) - 1)) + (((b) > (a)) ? ((b) - 2)
        : ((b) - 1)))] = ((byte) ((value)));
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
        : ((b) - 1)))] += ((byte) ((value)));
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
