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
 * {@link _SymmetricEdgeBitBooleanArray} represents a list of bits stored
 * in an {@code boolean} array. This class is intended for symmetric TSPs.
 * </p>
 * <p>
 * The backing store of this matrix is a one-dimensional array of Boolean
 * (true/false) ({@code boolean}). Arrays are indexed with 32 bit signed
 * integer (int) ({@code int}s). Thus, the highest possible value of the
 * matrix dimension {@code n} is 46'342. Be aware that such a matrix may
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
 * <td>{@code n=46'342}</td>
 * </tr>
 * <tr>
 * <td>45&nbsp;bytes</td>
 * <td>2&prime;016&nbsp;bytes &asymp; 2&nbsp;KiB</td>
 * <td>4&prime;950&nbsp;bytes &asymp; 5&nbsp;KiB</td>
 * <td>523&prime;776&nbsp;bytes &asymp; 512&nbsp;KiB</td>
 * <td>49&prime;995&prime;000&nbsp;bytes &asymp; 48&nbsp;MiB</td>
 * <td>134&prime;209&prime;536&nbsp;bytes &asymp; 128&nbsp;MiB</td>
 * <td>1&prime;073&prime;767&prime;311&nbsp;bytes &asymp; 2&nbsp;GiB</td>
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
public final class _SymmetricEdgeBitBooleanArray extends
    _EdgeBitBooleanArray {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * create the _SymmetricEdgeBitBooleanArray
   * 
   * @param n
   *          the number of nodes in the tsp
   */
  public _SymmetricEdgeBitBooleanArray(final int n) {
    super(n);
    if (n > 46342) {
      // The maximum size is calculated such that 32-bit integer index
      // computations
      // cannot overflow.
      throw new IllegalArgumentException( //
          "Matrix dimension cannot be higher than 46'342."); //$NON-NLS-1$
    }
    this.m_bits = new boolean[((int) ((((long) (n)) * ((n) - 1)) >>> 1))];
  }

  /** {@inheritDoc} */
  @Override
  public final boolean get(final int a, final int b) {
    final int i, j;

    if (a > b) {
      i = a;
      j = b;
    } else {
      i = b;
      j = a;
    }
    return this.m_bits[((((((i) - 1) * ((i) - 2)) >>> 1) + (j)) - 1)];
  }

  /** {@inheritDoc} */
  @Override
  public final void set(final int a, final int b, final boolean value) {
    final int i, j;

    if (a > b) {
      i = a;
      j = b;
    } else {
      i = b;
      j = a;
    }
    this.m_bits[((((((i) - 1) * ((i) - 2)) >>> 1) + (j)) - 1)] = value;
  }

  /** {@inheritDoc} */
  @Override
  public final void set(final int a, final int b) {
    final int i, j;

    if (a > b) {
      i = a;
      j = b;
    } else {
      i = b;
      j = a;
    }
    this.m_bits[((((((i) - 1) * ((i) - 2)) >>> 1) + (j)) - 1)] = true;
  }

  /** {@inheritDoc} */
  @Override
  public final boolean toggle(final int a, final int b) {
    final int i, j;

    if (a > b) {
      i = a;
      j = b;
    } else {
      i = b;
      j = a;
    }
    return (this.m_bits[((((((i) - 1) * ((i) - 2)) >>> 1) + (j)) - 1)] ^= true);
  }

  /** {@inheritDoc} */
  @Override
  public final void clear(final int a, final int b) {
    final int i, j;

    if (a > b) {
      i = a;
      j = b;
    } else {
      i = b;
      j = a;
    }
    this.m_bits[((((((i) - 1) * ((i) - 2)) >>> 1) + (j)) - 1)] = false;
  }

  /** {@inheritDoc} */
  @Override
  public final boolean isSymmetric() {
    return true;
  }
}
