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
 * {@link _AsymmetricEdgeBitBooleanArray} represents a list of bits stored
 * in an {@code boolean} array. This class is intended for asymmetric TSPs.
 * </p>
 * <p>
 * The backing store of this matrix is a one-dimensional array of Boolean
 * (true/false) ({@code boolean}). Arrays are indexed with 32 bit signed
 * integer (int) ({@code int}s). Thus, the highest possible value of the
 * matrix dimension {@code n} is 46'341. Be aware that such a matrix may
 * have a high memory consumption for high values of {@code n}:
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
public final class _AsymmetricEdgeBitBooleanArray extends
    _EdgeBitBooleanArray {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * create the _AsymmetricEdgeBitBooleanArray
   *
   * @param n
   *          the number of nodes in the tsp
   */
  public _AsymmetricEdgeBitBooleanArray(final int n) {
    super(n);
    if (n > 46341) {
      // The maximum size is calculated such that 32-bit integer index
      // computations
      // cannot overflow.
      throw new IllegalArgumentException( //
          "Matrix dimension cannot be higher than 46'341."); //$NON-NLS-1$
    }
    this.m_bits = new boolean[((n) * ((n) - 1))];
  }

  /** {@inheritDoc} */
  @Override
  public final boolean get(final int a, final int b) {
    return this.m_bits[((((a) - 1) * ((this.m_n) - 1)) + (((b) > (a)) ? ((b) - 2)
        : ((b) - 1)))];
  }

  /** {@inheritDoc} */
  @Override
  public final void set(final int a, final int b, final boolean value) {
    this.m_bits[((((a) - 1) * ((this.m_n) - 1)) + (((b) > (a)) ? ((b) - 2)
        : ((b) - 1)))] = value;
  }

  /** {@inheritDoc} */
  @Override
  public final void set(final int a, final int b) {
    this.m_bits[((((a) - 1) * ((this.m_n) - 1)) + (((b) > (a)) ? ((b) - 2)
        : ((b) - 1)))] = true;
  }

  /** {@inheritDoc} */
  @Override
  public final boolean toggle(final int a, final int b) {
    return (this.m_bits[((((a) - 1) * ((this.m_n) - 1)) + (((b) > (a)) ? ((b) - 2)
        : ((b) - 1)))] ^= true);
  }

  /** {@inheritDoc} */
  @Override
  public final void clear(final int a, final int b) {
    this.m_bits[((((a) - 1) * ((this.m_n) - 1)) + (((b) > (a)) ? ((b) - 2)
        : ((b) - 1)))] = false;
  }

  /** {@inheritDoc} */
  @Override
  public final boolean isSymmetric() {
    return false;
  }
}
