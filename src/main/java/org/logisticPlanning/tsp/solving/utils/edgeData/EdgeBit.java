package org.logisticPlanning.tsp.solving.utils.edgeData;

import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;

/**
 * <p>
 * The base class for storing bits for edges.
 * </p>
 * <p>
 * In TSP problems, we often want to mark edges, e.g., as Tabu or included
 * or excluded. This means we have to store at least one bit per edge.
 * Naturally, we could use a two-dimensional array of {@code boolean}.
 * However, it our problem size becomes reasonably large, that may not work
 * anymore. Say, if we have 89'000 cities, the size would be at least
 * 7'921'000'000 bytes, a.e., 7.4 GiB. However, if we use 1 bit per edge,
 * then that would be only 990'125'000 bytes, a.e., 944 MiB. Furthermore,
 * if we have a symmetric TSP, we actually only need to store
 * 89'000*88'999/2 bits, a.e., 495'056'938 byte = 472 MiB. Of course,
 * arrays of {@code boolean} are the fasted what we can do, as bit
 * operations take time. Therefore, using the right data type may speed up
 * our application or lead to an {@link java.lang.OutOfMemoryError}.
 * </p>
 * <p>
 * This class here is a base class offering abstract functionality that is
 * implemented by internal classes differently for different situations.
 * The goal is to offer a good memory consumption / speed trade-off for
 * different numbers of nodes (and hence, edges) in the TSPs.
 * </p>
 * <p>
 * Warning: I have done no performance tests on the different classes so
 * far. The implementation is chosen according to: 1. Use {@code boolean[]}
 * where possible, as it should be fastest. 2. Use {@code long[]}
 * necessary.
 * </p>
 * <p>
 * One thing is clear, though: Using this class will always be faster than
 * using {@link java.util.BitSet}, because {@link java.util.BitSet BitSet}
 * additionally maintains the number of bits stored (which is constant
 * here) and performs santiy checks and stuff (which we omit for the sake
 * of speed).
 * </p>
 *
 *
 @author <em><a href="http://www.it-weise.de/">Thomas Weise</a></em>,
 *         Email:&nbsp;<a
 *         href="mailto:tweise@ustc.edu.cn">tweise@ustc.edu.cn</a>
 */
public abstract class EdgeBit extends EdgeNumber implements Cloneable {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * The threshold starting at which {@code long} arrays are used for
   * backing the edge bits. We chose a low threshold of {@code 256} in
   * order to allow the calling program to create many edge bit sets, as
   * may be necessary in a, say, branch and bound algorithm.
   */
  public static final int LONG_THRESHOLD = 256;

  /**
   * the internal constructor of the edge bit set
   *
   * @param n
   *          the number of nodes in the TSP
   */
  EdgeBit(final int n) {
    super(n);
  }

  /**
   * Get the bit associated with the edge {@code (a,b)}. Be careful: This
   * class is designed for speed, it does not perform sanity checks on
   * {@code a} and {@code b}.
   *
   * @param a
   *          the first node of the edge
   * @param b
   *          the second node of the edge
   * @return the associated bit
   */
  public abstract boolean get(final int a, final int b);

  /**
   * Toggle the bit associated with the edge {@code (a,b)}. Be careful:
   * This class is designed for speed, it does not perform sanity checks on
   * {@code a} and {@code b}.
   *
   * @param a
   *          the first node of the edge
   * @param b
   *          the second node of the edge
   * @return the NEW value of the bit
   */
  public abstract boolean toggle(final int a, final int b);

  /**
   * Set the bit associated with the edge {@code (a,b)} to {@code true}. Be
   * careful: This class is designed for speed, it does not perform sanity
   * checks on {@code a} and {@code b}.
   *
   * @param a
   *          the first node of the edge
   * @param b
   *          the second node of the edge
   */
  public abstract void set(final int a, final int b);

  /**
   * Set the bit associated with the edge {@code (a,b)} to {@code false}.
   * Be careful: This class is designed for speed, it does not perform
   * sanity checks on {@code a} and {@code b}.
   *
   * @param a
   *          the first node of the edge
   * @param b
   *          the second node of the edge
   */
  public abstract void clear(final int a, final int b);

  /**
   * Set the bit associated with the edge {@code (a,b)} to a given value.
   * Be careful: This class is designed for speed, it does not perform
   * sanity checks on {@code a} and {@code b}.
   *
   * @param a
   *          the first node of the edge
   * @param b
   *          the second node of the edge
   * @param value
   *          the new value for the associated bit
   */
  public abstract void set(final int a, final int b, final boolean value);

  /**
   * Set all bits: set all bits to {@code true}.
   */
  public abstract void setAll();

  /** {@inheritDoc} */
  @Override
  public EdgeBit clone() {
    try {
      return ((EdgeBit) (super.clone()));
    } catch (final Throwable t) {
      throw new RuntimeException(t); // should never happen
    }
  }

  /**
   * Allocate an edge bits array with all bits set to {@code false}. This
   * is the only right way to instantiate {@link EdgeBit}.
   *
   * @param f
   *          the objective function
   * @param old
   *          the old edge bits, that may be re-used
   * @return the {@code EdgeBits} instance with the given features and all
   *         bits set to {@code false}
   */
  public static final EdgeBit allocate(final ObjectiveFunction f,
      final EdgeBit old) {
    return EdgeBit.allocate(f.n(), f.symmetric(), old);
  }

  /**
   * Allocate an edge bits array with all bits set to {@code false}. This
   * is the only right way to instantiate {@link EdgeBit}.
   *
   * @param n
   *          the number of nodes
   * @param symmetric
   *          is the instance symmetric?
   * @param old
   *          the old edge bits, that may be re-used
   * @return the {@code EdgeBits} instance with the given features and all
   *         bits set to {@code false}
   */
  public static final EdgeBit allocate(final int n,
      final boolean symmetric, final EdgeBit old) {

    if ((old != null) && (old.m_n == n)
        && (symmetric == old.isSymmetric())) {
      old.clear();
      return old;
    }

    if (n < EdgeBit.LONG_THRESHOLD) {
      return (symmetric ? new _SymmetricEdgeBitBooleanArray(n)
          : new _AsymmetricEdgeBitBooleanArray(n));
    }
    return (symmetric ? new _SymmetricEdgeBitLongArray(n)
        : new _AsymmetricEdgeBitLongArray(n));
  }

  /** {@inheritDoc} */
  @Override
  public final String toString() {
    final StringBuilder sb;
    int i, j;

    sb = new StringBuilder();
    for (i = 1; i <= this.m_n; i++) {
      for (j = 1; j <= this.m_n; j++) {
        sb.append(((i != j) ? this.get(i, j) : false) ? '0' : '1');
      }
      sb.append('\n');
    }

    return sb.toString();
  }

  /** {@inheritDoc} */
  @Override
  public final void setByte(final int a, final int b, final byte value) {
    this.set(a, b, (value != 0));
  }

  /** {@inheritDoc} */
  @Override
  public final byte getByte(final int a, final int b) {
    return (this.get(a, b) ? 0 : ((byte) 1));
  }

  /** {@inheritDoc} */
  @Override
  public final void addByte(final int a, final int b, final byte value) {
    if (value != 0) {
      this.toggle(a, b);
    }
  }

  /** {@inheritDoc} */
  @Override
  public final void setShort(final int a, final int b, final short value) {
    this.set(a, b, (value != 0));
  }

  /** {@inheritDoc} */
  @Override
  public final short getShort(final int a, final int b) {
    return (this.get(a, b) ? 0 : ((short) 1));
  }

  /** {@inheritDoc} */
  @Override
  public final void addShort(final int a, final int b, final short value) {
    if (value != 0) {
      this.toggle(a, b);
    }
  }

  /** {@inheritDoc} */
  @Override
  public final void setInt(final int a, final int b, final int value) {
    this.set(a, b, (value != 0));
  }

  /** {@inheritDoc} */
  @Override
  public final int getInt(final int a, final int b) {
    return (this.get(a, b) ? 0 : 1);
  }

  /** {@inheritDoc} */
  @Override
  public final void addInt(final int a, final int b, final int value) {
    if (value != 0) {
      this.toggle(a, b);
    }
  }

  /** {@inheritDoc} */
  @Override
  public final void setLong(final int a, final int b, final long value) {
    this.set(a, b, (value != 0l));
  }

  /** {@inheritDoc} */
  @Override
  public final long getLong(final int a, final int b) {
    return (this.get(a, b) ? 0l : 1l);
  }

  /** {@inheritDoc} */
  @Override
  public final void addLong(final int a, final int b, final long value) {
    if (value != 0) {
      this.toggle(a, b);
    }
  }

  /** {@inheritDoc} */
  @Override
  public final void setFloat(final int a, final int b, final float value) {
    this.set(a, b, (value != 0f));
  }

  /** {@inheritDoc} */
  @Override
  public final float getFloat(final int a, final int b) {
    return (this.get(a, b) ? 0f : 1f);
  }

  /** {@inheritDoc} */
  @Override
  public final void addFloat(final int a, final int b, final float value) {
    if (value != 0f) {
      this.toggle(a, b);
    }
  }

  /** {@inheritDoc} */
  @Override
  public final void setDouble(final int a, final int b, final double value) {
    this.set(a, b, (value != 0d));
  }

  /** {@inheritDoc} */
  @Override
  public final double getDouble(final int a, final int b) {
    return (this.get(a, b) ? 0d : 1d);
  }

  /** {@inheritDoc} */
  @Override
  public final void addDouble(final int a, final int b, final double value) {
    if (value != 0d) {
      this.toggle(a, b);
    }
  }

  /** {@inheritDoc} */
  @Override
  public final void inc(final int a, final int b) {
    this.toggle(a, b);
  }

  /** {@inheritDoc} */
  @Override
  public final void dec(final int a, final int b) {
    this.toggle(a, b);
  }

  /** {@inheritDoc} */
  @Override
  public final long getMinimumAllowedValue() {
    return 0l;
  }

  /** {@inheritDoc} */
  @Override
  public final long getMaximumAllowedValue() {
    return 1l;
  }

  /** {@inheritDoc} */
  @Override
  public final boolean areFloatsAllowed() {
    return false;
  }
}
