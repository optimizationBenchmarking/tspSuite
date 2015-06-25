package org.logisticPlanning.tsp.solving.utils.edgeData;

/**
 * <p>
 * A class that assigns a number to each edge. It is specialized for
 * different numerical types, i.e., {@code byte}, {@code short},
 * {@code int}, {@code long}, {@code float}, {@code double}. The methods
 * {@link #allocate(int, boolean, long, long, boolean, EdgeNumber)} and
 * {@link #allocate(int, boolean, double, double, boolean, EdgeNumber)} can
 * be used to automatically allocate the right object for your scenario.
 * </p>
 * <p>
 * Warning: The implementations of this class in this package here have a
 * high memory requirement and may fail with
 * {@link java.lang.OutOfMemoryError}s for higher dimensions {@code n}. If
 * you use a
 * {@link org.logisticPlanning.tsp.solving.utils.candidates.CandidateSet
 * CandidateSet} to reduce the amount of edges/nodes considered in the
 * computation, you may use instances of the class
 * {@link org.logisticPlanning.tsp.solving.utils.candidates.CandidateEdgeNumber
 * CandidateEdgeNumber} which have a lower memory footprint (at the cost of
 * higher access times).
 * </p>
 * <p>
 * These methods choose the right backing store depending on whether you a)
 * require asymmetric storage (edge {@code a-b} may have a different value
 * assigned than edge {@code b-a}) or symmetric stores (edges {@code a-b}
 * and {@code b-a} map to the same value) and b) the range of values that
 * can be assigned. If we only assign values in the range {@code -128} to
 * {@code 128}, for instance, we can use a {@code byte} array as backing
 * store, for values in {@code [-32768,32767]}, we can use a {@code short}
 * array, and so on.
 * </p>
 * <p>
 * Please be aware that using this class may render your application
 * infeasible for large-scale problems (those with a high
 * {@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction#n()
 * number} of nodes) due to memory consumption. The approximate memory
 * requirements of the backing stores for different configurations are
 * listed in the table below.
 * </p>
 * <table border="1">
 * <tr>
 * <th>Symmetric?</th>
 * <th>Storage</th>
 * <th>
 * <code>{@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction#n() n=100}</code>
 * </th>
 * <th>
 * <code>{@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction#n() n=1000}</code>
 * </th>
 * <th>
 * <code>{@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction#n() n=10000}</code>
 * </th>
 * <th>
 * <code>{@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction#n() n=46341}</code>
 * </th>
 * <th>
 * <code>{@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction#n() n=46342}</code>
 * </th>
 * </tr>
 * <tr>
 * <th rowspan="6">yes</th>
 * <th>{@code byte[]}</th>
 * <td>9&prime;900&nbsp;bytes &asymp; 10&nbsp;KiB</td>
 * <td>999&prime;000&nbsp;bytes &asymp; 976&nbsp;KiB</td>
 * <td>99&prime;990&prime;000&nbsp;bytes &asymp; 96&nbsp;MiB</td>
 * <td>2&prime;147&prime;441&prime;940&nbsp;bytes &asymp; 2&nbsp;GiB</td>
 * <td>2&prime;147&prime;534&prime;622&nbsp;bytes &asymp; 3&nbsp;GiB</td>
 * </tr>
 * <tr>
 * <th>{@code short[]}</th>
 * <td>19&prime;800&nbsp;bytes &asymp; 20&nbsp;KiB</td>
 * <td>1&prime;998&prime;000&nbsp;bytes &asymp; 2&nbsp;MiB</td>
 * <td>199&prime;980&prime;000&nbsp;bytes &asymp; 191&nbsp;MiB</td>
 * <td>4&prime;294&prime;883&prime;880&nbsp;bytes &asymp; 4&nbsp;GiB</td>
 * <td>4&prime;295&prime;069&prime;244&nbsp;bytes &asymp; 5&nbsp;GiB</td>
 * </tr>
 * <tr>
 * <th>{@code int[]}</th>
 * <td>39&prime;600&nbsp;bytes &asymp; 39&nbsp;KiB</td>
 * <td>3&prime;996&prime;000&nbsp;bytes &asymp; 4&nbsp;MiB</td>
 * <td>399&prime;960&prime;000&nbsp;bytes &asymp; 382&nbsp;MiB</td>
 * <td>8&prime;589&prime;767&prime;760&nbsp;bytes &asymp; 8&nbsp;GiB</td>
 * <td>8&prime;590&prime;138&prime;488&nbsp;bytes &asymp; 9&nbsp;GiB</td>
 * </tr>
 * <tr>
 * <th>{@code long[]}</th>
 * <td>79&prime;200&nbsp;bytes &asymp; 78&nbsp;KiB</td>
 * <td>7&prime;992&prime;000&nbsp;bytes &asymp; 8&nbsp;MiB</td>
 * <td>799&prime;920&prime;000&nbsp;bytes &asymp; 763&nbsp;MiB</td>
 * <td>17&prime;179&prime;535&prime;520&nbsp;bytes &asymp; 16&nbsp;GiB</td>
 * <td>17&prime;180&prime;276&prime;976&nbsp;bytes &asymp; 17&nbsp;GiB</td>
 * </tr>
 * <tr>
 * <th>{@code float[]}</th>
 * <td>39&prime;600&nbsp;bytes &asymp; 39&nbsp;KiB</td>
 * <td>3&prime;996&prime;000&nbsp;bytes &asymp; 4&nbsp;MiB</td>
 * <td>399&prime;960&prime;000&nbsp;bytes &asymp; 382&nbsp;MiB</td>
 * <td>8&prime;589&prime;767&prime;760&nbsp;bytes &asymp; 8&nbsp;GiB</td>
 * <td>8&prime;590&prime;138&prime;488&nbsp;bytes &asymp; 9&nbsp;GiB</td>
 * </tr>
 * <tr>
 * <th>{@code double[]}</th>
 * <td>79&prime;200&nbsp;bytes &asymp; 78&nbsp;KiB</td>
 * <td>7&prime;992&prime;000&nbsp;bytes &asymp; 8&nbsp;MiB</td>
 * <td>799&prime;920&prime;000&nbsp;bytes &asymp; 763&nbsp;MiB</td>
 * <td>17&prime;179&prime;535&prime;520&nbsp;bytes &asymp; 16&nbsp;GiB</td>
 * <td>17&prime;180&prime;276&prime;976&nbsp;bytes &asymp; 17&nbsp;GiB</td>
 * </tr>
 * <tr>
 * <th rowspan="6">no</th>
 * <th>{@code byte[]}</th>
 * <td>9&prime;900&nbsp;bytes &asymp; 10&nbsp;KiB</td>
 * <td>999&prime;000&nbsp;bytes &asymp; 976&nbsp;KiB</td>
 * <td>99&prime;990&prime;000&nbsp;bytes &asymp; 96&nbsp;MiB</td>
 * <td>2&prime;147&prime;441&prime;940&nbsp;bytes &asymp; 2&nbsp;GiB</td>
 * <td>2&prime;147&prime;534&prime;622&nbsp;bytes &asymp; 3&nbsp;GiB</td>
 * </tr>
 * <tr>
 * <th>{@code short[]}</th>
 * <td>19&prime;800&nbsp;bytes &asymp; 20&nbsp;KiB</td>
 * <td>1&prime;998&prime;000&nbsp;bytes &asymp; 2&nbsp;MiB</td>
 * <td>199&prime;980&prime;000&nbsp;bytes &asymp; 191&nbsp;MiB</td>
 * <td>4&prime;294&prime;883&prime;880&nbsp;bytes &asymp; 4&nbsp;GiB</td>
 * <td>4&prime;295&prime;069&prime;244&nbsp;bytes &asymp; 5&nbsp;GiB</td>
 * </tr>
 * <tr>
 * <th>{@code int[]}</th>
 * <td>39&prime;600&nbsp;bytes &asymp; 39&nbsp;KiB</td>
 * <td>3&prime;996&prime;000&nbsp;bytes &asymp; 4&nbsp;MiB</td>
 * <td>399&prime;960&prime;000&nbsp;bytes &asymp; 382&nbsp;MiB</td>
 * <td>8&prime;589&prime;767&prime;760&nbsp;bytes &asymp; 8&nbsp;GiB</td>
 * <td>8&prime;590&prime;138&prime;488&nbsp;bytes &asymp; 9&nbsp;GiB</td>
 * </tr>
 * <tr>
 * <th>{@code long[]}</th>
 * <td>79&prime;200&nbsp;bytes &asymp; 78&nbsp;KiB</td>
 * <td>7&prime;992&prime;000&nbsp;bytes &asymp; 8&nbsp;MiB</td>
 * <td>799&prime;920&prime;000&nbsp;bytes &asymp; 763&nbsp;MiB</td>
 * <td>17&prime;179&prime;535&prime;520&nbsp;bytes &asymp; 16&nbsp;GiB</td>
 * <td>17&prime;180&prime;276&prime;976&nbsp;bytes &asymp; 17&nbsp;GiB</td>
 * </tr>
 * <tr>
 * <th>{@code float[]}</th>
 * <td>39&prime;600&nbsp;bytes &asymp; 39&nbsp;KiB</td>
 * <td>3&prime;996&prime;000&nbsp;bytes &asymp; 4&nbsp;MiB</td>
 * <td>399&prime;960&prime;000&nbsp;bytes &asymp; 382&nbsp;MiB</td>
 * <td>8&prime;589&prime;767&prime;760&nbsp;bytes &asymp; 8&nbsp;GiB</td>
 * <td>8&prime;590&prime;138&prime;488&nbsp;bytes &asymp; 9&nbsp;GiB</td>
 * </tr>
 * <tr>
 * <th>{@code double[]}</th>
 * <td>79&prime;200&nbsp;bytes &asymp; 78&nbsp;KiB</td>
 * <td>7&prime;992&prime;000&nbsp;bytes &asymp; 8&nbsp;MiB</td>
 * <td>799&prime;920&prime;000&nbsp;bytes &asymp; 763&nbsp;MiB</td>
 * <td>17&prime;179&prime;535&prime;520&nbsp;bytes &asymp; 16&nbsp;GiB</td>
 * <td>17&prime;180&prime;276&prime;976&nbsp;bytes &asymp; 17&nbsp;GiB</td>
 * </tr>
 * </table>
 */
public abstract class EdgeNumber extends EdgeData {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * create
   *
   * @param n
   *          the number of nodes
   */
  protected EdgeNumber(final int n) {
    super(n);
  }

  /**
   * Set the value of the given edge
   *
   * @param a
   *          the first city
   * @param b
   *          the second city
   * @param value
   *          the value
   */
  public abstract void setByte(final int a, final int b, final byte value);

  /**
   * Get the value of the given edge
   *
   * @param a
   *          the first city
   * @param b
   *          the second city
   * @return value the value
   */
  public abstract byte getByte(final int a, final int b);

  /**
   * Add a given to the value stored for a given edge
   *
   * @param a
   *          the first city
   * @param b
   *          the second city
   * @param value
   *          the value
   */
  public abstract void addByte(final int a, final int b, final byte value);

  /**
   * Set the value of the given edge
   *
   * @param a
   *          the first city
   * @param b
   *          the second city
   * @param value
   *          the value
   */
  public abstract void setShort(final int a, final int b, final short value);

  /**
   * Get the value of the given edge
   *
   * @param a
   *          the first city
   * @param b
   *          the second city
   * @return value the value
   */
  public abstract short getShort(final int a, final int b);

  /**
   * Add a given to the value stored for a given edge
   *
   * @param a
   *          the first city
   * @param b
   *          the second city
   * @param value
   *          the value
   */
  public abstract void addShort(final int a, final int b, final short value);

  /**
   * Set the value of the given edge
   *
   * @param a
   *          the first city
   * @param b
   *          the second city
   * @param value
   *          the value
   */
  public abstract void setInt(final int a, final int b, final int value);

  /**
   * Get the value of the given edge
   *
   * @param a
   *          the first city
   * @param b
   *          the second city
   * @return value the value
   */
  public abstract int getInt(final int a, final int b);

  /**
   * Add a given to the value stored for a given edge
   *
   * @param a
   *          the first city
   * @param b
   *          the second city
   * @param value
   *          the value
   */
  public abstract void addInt(final int a, final int b, final int value);

  /**
   * Set the value of the given edge
   *
   * @param a
   *          the first city
   * @param b
   *          the second city
   * @param value
   *          the value
   */
  public abstract void setLong(final int a, final int b, final long value);

  /**
   * Get the value of the given edge
   *
   * @param a
   *          the first city
   * @param b
   *          the second city
   * @return value the value
   */
  public abstract long getLong(final int a, final int b);

  /**
   * Add a given to the value stored for a given edge
   *
   * @param a
   *          the first city
   * @param b
   *          the second city
   * @param value
   *          the value
   */
  public abstract void addLong(final int a, final int b, final long value);

  /**
   * Set the value of the given edge
   *
   * @param a
   *          the first city
   * @param b
   *          the second city
   * @param value
   *          the value
   */
  public abstract void setFloat(final int a, final int b, final float value);

  /**
   * Get the value of the given edge
   *
   * @param a
   *          the first city
   * @param b
   *          the second city
   * @return value the value
   */
  public abstract float getFloat(final int a, final int b);

  /**
   * Add a given to the value stored for a given edge
   *
   * @param a
   *          the first city
   * @param b
   *          the second city
   * @param value
   *          the value
   */
  public abstract void addFloat(final int a, final int b, final float value);

  /**
   * Set the value of the given edge
   *
   * @param a
   *          the first city
   * @param b
   *          the second city
   * @param value
   *          the value
   */
  public abstract void setDouble(final int a, final int b,
      final double value);

  /**
   * Get the value of the given edge
   *
   * @param a
   *          the first city
   * @param b
   *          the second city
   * @return value the value
   */
  public abstract double getDouble(final int a, final int b);

  /**
   * Add a given to the value stored for a given edge
   *
   * @param a
   *          the first city
   * @param b
   *          the second city
   * @param value
   *          the value
   */
  public abstract void addDouble(final int a, final int b,
      final double value);

  /**
   * Increase the value of an edge by 1
   *
   * @param a
   *          the first city of the edge
   * @param b
   *          the second city of the edge
   */
  public abstract void inc(final int a, final int b);

  /**
   * Decrease the value of an edge by 1
   *
   * @param a
   *          the first city of the edge
   * @param b
   *          the second city of the edge
   */
  public abstract void dec(final int a, final int b);

  /**
   * Get the minimum value allowed by this class
   *
   * @return the minimum value allowed by this class
   */
  public abstract long getMinimumAllowedValue();

  /**
   * Get the maximum value allowed by this class
   *
   * @return the maximum value allowed by this class
   */
  public abstract long getMaximumAllowedValue();

  /**
   * Is storing floating point numbers allowed?
   *
   * @return is the storage of floating point numbers allowed?
   */
  public abstract boolean areFloatsAllowed();

  /**
   * Allocate an {@link EdgeNumber} instance with all data elements set to
   * 0. Try to re-use an existing instance {@code old}, if possible. Use
   * the smallest backing array possible. See the method
   * {@link org.logisticPlanning.tsp.solving.utils.candidates.CandidateEdgeNumber#allocate(int, boolean, long, long, boolean, org.logisticPlanning.tsp.solving.utils.candidates.CandidateSet, org.logisticPlanning.tsp.solving.utils.edgeData.EdgeNumber)
   * allocate} in class
   * {@link org.logisticPlanning.tsp.solving.utils.candidates.CandidateEdgeNumber
   * CandidateEdgeNumber} if you want to reduce the memory footprint.
   *
   * @param n
   *          the number of nodes
   * @param symmetric
   *          is the instance symmetric?
   * @param minValue
   *          the minimum value to be stored, specified as 64 bit signed
   *          integer (long)
   * @param maxValue
   *          the maximum value to be stored, specified as 64 bit signed
   *          integer (long)
   * @param requiresFloats
   *          do we need to support floating point numbers?
   * @param old
   *          the old edge byte instance that may be re-used
   * @return the
   *         {@link org.logisticPlanning.tsp.solving.utils.edgeData.EdgeNumber
   *         EdgeNumber} instance with the given features and all bits set
   *         to {@code 0}
   */
  public static final EdgeNumber allocate(final int n,
      final boolean symmetric, final long minValue, final long maxValue,
      final boolean requiresFloats, final EdgeNumber old) {

    // first, let us check if we can re-use the old object
    if ((old != null) && (old.m_n == n)
        && (symmetric == old.isSymmetric())
        && (old.areFloatsAllowed() == requiresFloats)
        && (old.getMinimumAllowedValue() <= minValue)
        && (old.getMaximumAllowedValue() >= maxValue)) {
      old.clear();
      return old;
    }

    if (requiresFloats) {
      // Since we specify the minimum and maximum values as long, there is
      // no way to
      // exceed the float range. Hence, we can always return a float-based
      // data structure
      // if floating point values are required.
      return (symmetric ? new _SymmetricEdgeFloat(n)
          : new _AsymmetricEdgeFloat(n));
    }

    // can we use bits as backing store? i.e., the possible values must
    // be in {0, 1}
    if ((minValue >= 0) && (maxValue <= 1)) {
      return EdgeBit.allocate(n, symmetric, null);
    }

    // can we use byte[] as backing store? i.e., the possible values must
    // be in -128..128
    if ((minValue >= (java.lang.Byte.MIN_VALUE))
        && (maxValue <= (java.lang.Byte.MAX_VALUE))) {
      return (symmetric ? new _SymmetricEdgeByte(n)
          : new _AsymmetricEdgeByte(n));
    }
    // can we use short[] as backing store? i.e., the possible values must
    // be in -32768..32767
    if ((minValue >= (java.lang.Short.MIN_VALUE))
        && (maxValue <= (java.lang.Short.MAX_VALUE))) {
      return (symmetric ? new _SymmetricEdgeShort(n)
          : new _AsymmetricEdgeShort(n));
    }
    // can we use int[] as backing store? i.e., the possible values must
    // be in -2147483648..2147483647
    if ((minValue >= (java.lang.Integer.MIN_VALUE))
        && (maxValue <= (java.lang.Integer.MAX_VALUE))) {
      return (symmetric ? new _SymmetricEdgeInt(n)
          : new _AsymmetricEdgeInt(n));
    }

    // ok, we exceed all smaller ranges, we have to use long[] as backing
    // store
    return (symmetric ? new _SymmetricEdgeLong(n)
        : new _AsymmetricEdgeLong(n));
  }

  /**
   * Allocate an {@link EdgeNumber} instance with all data elements set to
   * 0. Try to re-use an existing instance {@code old}, if possible. Use
   * the smallest backing array possible. See the method
   * {@link org.logisticPlanning.tsp.solving.utils.candidates.CandidateEdgeNumber#allocate(int, boolean, double, double, boolean, org.logisticPlanning.tsp.solving.utils.candidates.CandidateSet, org.logisticPlanning.tsp.solving.utils.edgeData.EdgeNumber)
   * allocate} in class
   * {@link org.logisticPlanning.tsp.solving.utils.candidates.CandidateEdgeNumber
   * CandidateEdgeNumber} if you want to reduce the memory footprint.
   *
   * @param n
   *          the number of nodes
   * @param symmetric
   *          is the instance symmetric?
   * @param minValue
   *          the minimum value to be stored, specified as double-precision
   *          (64-bit) floating point number (double)
   * @param maxValue
   *          the maximum value to be stored, specified as double-precision
   *          (64-bit) floating point number (double)
   * @param requiresFloats
   *          do we need to support floating point numbers?
   * @param old
   *          the old edge byte instance that may be re-used
   * @return the
   *         {@link org.logisticPlanning.tsp.solving.utils.edgeData.EdgeNumber
   *         EdgeNumber} instance with the given features and all bits set
   *         to {@code 0}
   */
  public static final EdgeNumber allocate(final int n,
      final boolean symmetric, final double minValue,
      final double maxValue, final boolean requiresFloats,
      final EdgeNumber old) {

    // first, let us check if we can re-use the old object
    if ((old != null) && (old.m_n == n)
        && (symmetric == old.isSymmetric())
        && (old.areFloatsAllowed() == requiresFloats)
        && (old.getMinimumAllowedValue() <= minValue)
        && (old.getMaximumAllowedValue() >= maxValue)) {
      old.clear();
      return old;
    }

    if (requiresFloats) {
      // Warning: Specifying infinite values will always lead to
      // double-based data
      // strutures
      if ((minValue >= (-Float.MAX_VALUE))
          && (maxValue <= (Float.MAX_VALUE))) {
        return (symmetric ? new _SymmetricEdgeFloat(n)
            : new _AsymmetricEdgeFloat(n));
      }

      return (symmetric ? new _SymmetricEdgeDouble(n)
          : new _AsymmetricEdgeDouble(n));
    }

    // can we use bits as backing store? i.e., the possible values must
    // be in {0, 1}
    if ((minValue >= 0) && (maxValue <= 1)) {
      return EdgeBit.allocate(n, symmetric, null);
    }

    // can we use byte[] as backing store? i.e., the possible values must
    // be in -128..128
    if ((minValue >= (java.lang.Byte.MIN_VALUE))
        && (maxValue <= (java.lang.Byte.MAX_VALUE))) {
      return (symmetric ? new _SymmetricEdgeByte(n)
          : new _AsymmetricEdgeByte(n));
    }
    // can we use short[] as backing store? i.e., the possible values must
    // be in -32768..32767
    if ((minValue >= (java.lang.Short.MIN_VALUE))
        && (maxValue <= (java.lang.Short.MAX_VALUE))) {
      return (symmetric ? new _SymmetricEdgeShort(n)
          : new _AsymmetricEdgeShort(n));
    }
    // can we use int[] as backing store? i.e., the possible values must
    // be in -2147483648..2147483647
    if ((minValue >= (java.lang.Integer.MIN_VALUE))
        && (maxValue <= (java.lang.Integer.MAX_VALUE))) {
      return (symmetric ? new _SymmetricEdgeInt(n)
          : new _AsymmetricEdgeInt(n));
    }

    // ok, we exceed all smaller ranges, we have to use long[] as backing
    // store
    return (symmetric ? new _SymmetricEdgeLong(n)
        : new _AsymmetricEdgeLong(n));
  }
}
