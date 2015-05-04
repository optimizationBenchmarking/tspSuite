package org.logisticPlanning.tsp.solving.utils.candidates;

import org.logisticPlanning.tsp.benchmarking.objective.Benchmark;
import org.logisticPlanning.tsp.solving.utils.edgeData.EdgeNumber;

/**
 * An object which saves numbers for candidate edges.
 */
public abstract class CandidateEdgeNumber extends EdgeNumber {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * for any dimension below this threshold, a full matrix will be created
   * which has better access times but needs more memory
   */
  private static final int FULL_MATRIX_THRESHOLD = Benchmark.DEFAULT_MATRIX_LIMIT_DIM;

  /** the number of candidate edges per node */
  final int m_m;

  /** the candidate sub-set's data */
  int[] m_candidates;

  /**
   * Instantiate
   *
   * @param n
   *          the number of nodes
   * @param candidates
   *          the candidates
   */
  CandidateEdgeNumber(final int n, final _CandidateSubSet candidates) {
    super(n);
    this.m_m = candidates.m_m;
    this.m_candidates = candidates.m_candidates;
  }

  /**
   * <p>
   * Allocate an
   * {@link org.logisticPlanning.tsp.solving.utils.edgeData.EdgeNumber
   * EdgeNumber} instance with all data elements set to 0. Try to re-use an
   * existing instance {@code old}, if possible. Use the smallest backing
   * array possible.
   * </p>
   * <p>
   * This is an extension of
   * {@link org.logisticPlanning.tsp.solving.utils.edgeData.EdgeNumber
   * EdgeNumber}'s
   * {@link org.logisticPlanning.tsp.solving.utils.edgeData.EdgeNumber#allocate(int, boolean, long, long, boolean, org.logisticPlanning.tsp.solving.utils.edgeData.EdgeNumber)
   * allocate method}. It supports passing in a
   * {@link org.logisticPlanning.tsp.solving.utils.candidates.CandidateSet
   * CandidateSet} instance that may be used to reduce the storage
   * requirements (at the cost of higher access time).
   * </p>
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
   * @param candidates
   *          the candidate set
   * @param old
   *          the old edge byte instance that may be re-used
   * @return the
   *         {@link org.logisticPlanning.tsp.solving.utils.edgeData.EdgeNumber
   *         EdgeNumber} instance with the given features and all bits set
   *         to {@code 0}
   */
  public static final EdgeNumber allocate(final int n,
      final boolean symmetric, final long minValue, final long maxValue,
      final boolean requiresFloats, final CandidateSet candidates,
      final EdgeNumber old) {

    final _CandidateSubSet cs;
    final CandidateEdgeNumber o;

    // First, check if we should default to EdgeNumber.allocate
    if ((candidates == null) || // no candidates? can't reduce storage
        (!(candidates instanceof _CandidateSubSet)) || // useless
        // candidate set
        (n <= CandidateEdgeNumber.FULL_MATRIX_THRESHOLD)) {
      // the memory savings are too small to make up for the higher
      // runtime
      return EdgeNumber.allocate(n, symmetric, minValue, maxValue,
          requiresFloats, ((old instanceof CandidateEdgeNumber) ? null
              : old));
    }

    // ok, there is a potential to save memory here
    // let's first see if we can re-use the old instance
    cs = ((_CandidateSubSet) candidates);
    check: {
      if ((old != null) && (old.n() == n)
          && (symmetric == old.isSymmetric())
          && (old.areFloatsAllowed() == requiresFloats)
          && (old.getMinimumAllowedValue() <= minValue)
          && (old.getMaximumAllowedValue() >= maxValue)) {
        if (old instanceof CandidateEdgeNumber) {
          o = ((CandidateEdgeNumber) old);
          if (o.m_m != cs.m_m) {
            break check;
          }
          // ensure that the right candidate list is used
          o.m_candidates = cs.m_candidates;
        }
        old.clear(); // clear old instance
        return old;
      }
    }

    if (requiresFloats) {
      // Since we specify the minimum and maximum values as long, there is
      // no way to
      // exceed the float range. Hence, we can always return a float-based
      // data structure
      // if floating point values are required.
      return (symmetric ? new _SymmetricCandidateEdgeDouble(n, cs)
          : new _AsymmetricCandidateEdgeDouble(n, cs));
    }

    // can we use byte[] as backing store? i.e., the possible values must
    // be in -128..128
    if ((minValue >= (java.lang.Byte.MIN_VALUE))
        && (maxValue <= (java.lang.Byte.MAX_VALUE))) {
      return (symmetric ? new _SymmetricCandidateEdgeByte(n, cs)
          : new _AsymmetricCandidateEdgeByte(n, cs));
    }
    // can we use short[] as backing store? i.e., the possible values must
    // be in -32768..32767
    if ((minValue >= (java.lang.Short.MIN_VALUE))
        && (maxValue <= (java.lang.Short.MAX_VALUE))) {
      return (symmetric ? new _SymmetricCandidateEdgeShort(n, cs)
          : new _AsymmetricCandidateEdgeShort(n, cs));
    }
    // can we use int[] as backing store? i.e., the possible values must
    // be in -2147483648..2147483647
    if ((minValue >= (java.lang.Integer.MIN_VALUE))
        && (maxValue <= (java.lang.Integer.MAX_VALUE))) {
      return (symmetric ? new _SymmetricCandidateEdgeInt(n, cs)
          : new _AsymmetricCandidateEdgeInt(n, cs));
    }

    // ok, we exceed all smaller ranges, we have to use long[] as backing
    // store
    return (symmetric ? new _SymmetricCandidateEdgeLong(n, cs)
        : new _AsymmetricCandidateEdgeLong(n, cs));
  }

  /**
   * <p>
   * Allocate an
   * {@link org.logisticPlanning.tsp.solving.utils.edgeData.EdgeNumber
   * EdgeNumber} instance with all data elements set to 0. Try to re-use an
   * existing instance {@code old}, if possible. Use the smallest backing
   * array possible.
   * </p>
   * <p>
   * This is an extension of
   * {@link org.logisticPlanning.tsp.solving.utils.edgeData.EdgeNumber
   * EdgeNumber}'s
   * {@link org.logisticPlanning.tsp.solving.utils.edgeData.EdgeNumber#allocate(int, boolean, double, double, boolean, org.logisticPlanning.tsp.solving.utils.edgeData.EdgeNumber)
   * allocate method}. It supports passing in a
   * {@link org.logisticPlanning.tsp.solving.utils.candidates.CandidateSet
   * CandidateSet} instance that may be used to reduce the storage
   * requirements (at the cost of higher access time).
   * </p>
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
   * @param candidates
   *          the candidate set
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
      final CandidateSet candidates, final EdgeNumber old) {

    final _CandidateSubSet cs;
    final CandidateEdgeNumber o;

    // First, check if we should default to EdgeNumber.allocate
    if ((candidates == null) || // no candidates? can't reduce storage
        (!(candidates instanceof _CandidateSubSet)) || // useless
        // candidate set
        (n <= CandidateEdgeNumber.FULL_MATRIX_THRESHOLD)) {
      // the memory savings are too small to make up for the higher
      // runtime
      return EdgeNumber.allocate(n, symmetric, minValue, maxValue,
          requiresFloats, ((old instanceof CandidateEdgeNumber) ? null
              : old));
    }

    // ok, there is a potential to save memory here
    // let's first see if we can re-use the old instance
    cs = ((_CandidateSubSet) candidates);
    check: {
      if ((old != null) && (old.n() == n)
          && (symmetric == old.isSymmetric())
          && (old.areFloatsAllowed() == requiresFloats)
          && (old.getMinimumAllowedValue() <= minValue)
          && (old.getMaximumAllowedValue() >= maxValue)) {
        if (old instanceof CandidateEdgeNumber) {
          o = ((CandidateEdgeNumber) old);
          if (o.m_m != cs.m_m) {
            break check;
          }
          // ensure that the right candidate list is used
          o.m_candidates = cs.m_candidates;
        }
        old.clear(); // clear old instance
        return old;
      }
    }

    if (requiresFloats) {
      // Warning: Specifying infinite values will always lead to
      // double-based data
      // strutures
      if ((minValue >= (-Float.MAX_VALUE))
          && (maxValue <= (Float.MAX_VALUE))) {
        return (symmetric ? new _SymmetricCandidateEdgeFloat(n, cs)
            : new _AsymmetricCandidateEdgeFloat(n, cs));
      }

      return (symmetric ? new _SymmetricCandidateEdgeDouble(n, cs)
          : new _AsymmetricCandidateEdgeDouble(n, cs));
    }

    // can we use byte[] as backing store? i.e., the possible values must
    // be in -128..128
    if ((minValue >= (java.lang.Byte.MIN_VALUE))
        && (maxValue <= (java.lang.Byte.MAX_VALUE))) {
      return (symmetric ? new _SymmetricCandidateEdgeByte(n, cs)
          : new _AsymmetricCandidateEdgeByte(n, cs));
    }
    // can we use short[] as backing store? i.e., the possible values must
    // be in -32768..32767
    if ((minValue >= (java.lang.Short.MIN_VALUE))
        && (maxValue <= (java.lang.Short.MAX_VALUE))) {
      return (symmetric ? new _SymmetricCandidateEdgeShort(n, cs)
          : new _AsymmetricCandidateEdgeShort(n, cs));
    }
    // can we use int[] as backing store? i.e., the possible values must
    // be in -2147483648..2147483647
    if ((minValue >= (java.lang.Integer.MIN_VALUE))
        && (maxValue <= (java.lang.Integer.MAX_VALUE))) {
      return (symmetric ? new _SymmetricCandidateEdgeInt(n, cs)
          : new _AsymmetricCandidateEdgeInt(n, cs));
    }

    // ok, we exceed all smaller ranges, we have to use long[] as backing
    // store
    return (symmetric ? new _SymmetricCandidateEdgeLong(n, cs)
        : new _AsymmetricCandidateEdgeLong(n, cs));
  }

}
