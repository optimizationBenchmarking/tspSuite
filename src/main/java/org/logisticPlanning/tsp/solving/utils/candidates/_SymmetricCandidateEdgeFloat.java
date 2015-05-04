package org.logisticPlanning.tsp.solving.utils.candidates;

import java.util.Arrays;

/**
 * <p>
 * This class is an internal class. Please do not instantiate it, use it by
 * yourself, or otherwise meddle with it.
 * </p>
 * <p>
 * A class that assigns on float to each edge, specialized for symmetric
 * TSPs and using an {@code float} array as backing store. The size of the
 * backing store is {@code m*n}, where {@code m} is the number of candidate
 * nodes/edges per node and {@code n} is the total number of nodes.
 * </p>
 * <p>
 * This means that this array requires significantly less storage than the
 * corresponding
 * {@link org.logisticPlanning.tsp.solving.utils.edgeData.EdgeFloat} data
 * structures and is thus especially suitable for large-scale problems
 * where data structures in {@code O(n)} cannot be allocated.
 * </p>
 * <p>
 * This reduction of memory has to be paid for in runtime: Each access to
 * this matrix will cost you {@code O(log m)} memory accesses. Especially
 * this symmetric version of the {@code float}-array backed
 * {@link org.logisticPlanning.tsp.solving.utils.candidates.CandidateEdgeNumber
 * CandidateEdgeNumber} is costly, since each update costs two such
 * variable accesses, since updating the edge between node {@code a} and
 * {@code b} will require us to change both, the values associated with
 * edge {@code a-b} and {@code b-a}.
 * </p>
 * <p>
 * There may be more efficient data structures to do this, but currently I
 * don't know any. Maybe I can improve on this issue in future.
 * </p>
 */
final class _SymmetricCandidateEdgeFloat extends CandidateEdgeNumber {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the data */
  private final float[] m_data;

  /**
   * Instantiate
   *
   * @param n
   *          the number of nodes
   * @param candidates
   *          the candidates
   */
  _SymmetricCandidateEdgeFloat(final int n,
      final _CandidateSubSet candidates) {
    super(n, candidates);
    this.m_data = new float[n * this.m_m];
  }

  /** {@inheritDoc} */
  @Override
  public final void clear() {
    Arrays.fill(this.m_data, (0f));
  }

  /** {@inheritDoc} */
  @Override
  public final void setByte(final int a, final int b, final byte value) {
    int idx, start;

    start = ((a - 1) * this.m_m);
    idx = Arrays.binarySearch(this.m_candidates, start,
        (start + this.m_m), b);
    if (idx >= 0) {
      this.m_data[idx] = (value);
    }

    start = ((b - 1) * this.m_m);
    idx = Arrays.binarySearch(this.m_candidates, start,
        (start + this.m_m), a);
    if (idx >= 0) {
      this.m_data[idx] = (value);
    }
  }

  /** {@inheritDoc} */
  @Override
  public final byte getByte(final int a, final int b) {
    int idx, start;

    start = ((a - 1) * this.m_m);
    idx = Arrays.binarySearch(this.m_candidates, start,
        (start + this.m_m), b);
    if (idx >= 0) {
      return ((byte) ((this.m_data[idx])));
    }

    start = ((b - 1) * this.m_m);
    idx = Arrays.binarySearch(this.m_candidates, start,
        (start + this.m_m), a);
    if (idx >= 0) {
      return ((byte) ((this.m_data[idx])));
    }

    return ((byte) (0));
  }

  /** {@inheritDoc} */
  @Override
  public final void addByte(final int a, final int b, final byte value) {
    int idx, start;

    start = ((a - 1) * this.m_m);
    idx = Arrays.binarySearch(this.m_candidates, start,
        (start + this.m_m), b);
    if (idx >= 0) {
      this.m_data[idx] += (value);
    }

    start = ((b - 1) * this.m_m);
    idx = Arrays.binarySearch(this.m_candidates, start,
        (start + this.m_m), a);
    if (idx >= 0) {
      this.m_data[idx] += (value);
    }
  }

  /** {@inheritDoc} */
  @Override
  public final void setShort(final int a, final int b, final short value) {
    int idx, start;

    start = ((a - 1) * this.m_m);
    idx = Arrays.binarySearch(this.m_candidates, start,
        (start + this.m_m), b);
    if (idx >= 0) {
      this.m_data[idx] = (value);
    }

    start = ((b - 1) * this.m_m);
    idx = Arrays.binarySearch(this.m_candidates, start,
        (start + this.m_m), a);
    if (idx >= 0) {
      this.m_data[idx] = (value);
    }
  }

  /** {@inheritDoc} */
  @Override
  public final short getShort(final int a, final int b) {
    int idx, start;

    start = ((a - 1) * this.m_m);
    idx = Arrays.binarySearch(this.m_candidates, start,
        (start + this.m_m), b);
    if (idx >= 0) {
      return ((short) ((this.m_data[idx])));
    }

    start = ((b - 1) * this.m_m);
    idx = Arrays.binarySearch(this.m_candidates, start,
        (start + this.m_m), a);
    if (idx >= 0) {
      return ((short) ((this.m_data[idx])));
    }

    return ((short) (0));
  }

  /** {@inheritDoc} */
  @Override
  public final void addShort(final int a, final int b, final short value) {
    int idx, start;

    start = ((a - 1) * this.m_m);
    idx = Arrays.binarySearch(this.m_candidates, start,
        (start + this.m_m), b);
    if (idx >= 0) {
      this.m_data[idx] += (value);
    }

    start = ((b - 1) * this.m_m);
    idx = Arrays.binarySearch(this.m_candidates, start,
        (start + this.m_m), a);
    if (idx >= 0) {
      this.m_data[idx] += (value);
    }
  }

  /** {@inheritDoc} */
  @Override
  public final void setInt(final int a, final int b, final int value) {
    int idx, start;

    start = ((a - 1) * this.m_m);
    idx = Arrays.binarySearch(this.m_candidates, start,
        (start + this.m_m), b);
    if (idx >= 0) {
      this.m_data[idx] = (value);
    }

    start = ((b - 1) * this.m_m);
    idx = Arrays.binarySearch(this.m_candidates, start,
        (start + this.m_m), a);
    if (idx >= 0) {
      this.m_data[idx] = (value);
    }
  }

  /** {@inheritDoc} */
  @Override
  public final int getInt(final int a, final int b) {
    int idx, start;

    start = ((a - 1) * this.m_m);
    idx = Arrays.binarySearch(this.m_candidates, start,
        (start + this.m_m), b);
    if (idx >= 0) {
      return ((int) ((this.m_data[idx])));
    }

    start = ((b - 1) * this.m_m);
    idx = Arrays.binarySearch(this.m_candidates, start,
        (start + this.m_m), a);
    if (idx >= 0) {
      return ((int) ((this.m_data[idx])));
    }

    return (0);
  }

  /** {@inheritDoc} */
  @Override
  public final void addInt(final int a, final int b, final int value) {
    int idx, start;

    start = ((a - 1) * this.m_m);
    idx = Arrays.binarySearch(this.m_candidates, start,
        (start + this.m_m), b);
    if (idx >= 0) {
      this.m_data[idx] += (value);
    }

    start = ((b - 1) * this.m_m);
    idx = Arrays.binarySearch(this.m_candidates, start,
        (start + this.m_m), a);
    if (idx >= 0) {
      this.m_data[idx] += (value);
    }
  }

  /** {@inheritDoc} */
  @Override
  public final void setLong(final int a, final int b, final long value) {
    int idx, start;

    start = ((a - 1) * this.m_m);
    idx = Arrays.binarySearch(this.m_candidates, start,
        (start + this.m_m), b);
    if (idx >= 0) {
      this.m_data[idx] = (value);
    }

    start = ((b - 1) * this.m_m);
    idx = Arrays.binarySearch(this.m_candidates, start,
        (start + this.m_m), a);
    if (idx >= 0) {
      this.m_data[idx] = (value);
    }
  }

  /** {@inheritDoc} */
  @Override
  public final long getLong(final int a, final int b) {
    int idx, start;

    start = ((a - 1) * this.m_m);
    idx = Arrays.binarySearch(this.m_candidates, start,
        (start + this.m_m), b);
    if (idx >= 0) {
      return ((long) ((this.m_data[idx])));
    }

    start = ((b - 1) * this.m_m);
    idx = Arrays.binarySearch(this.m_candidates, start,
        (start + this.m_m), a);
    if (idx >= 0) {
      return ((long) ((this.m_data[idx])));
    }

    return (0l);
  }

  /** {@inheritDoc} */
  @Override
  public final void addLong(final int a, final int b, final long value) {
    int idx, start;

    start = ((a - 1) * this.m_m);
    idx = Arrays.binarySearch(this.m_candidates, start,
        (start + this.m_m), b);
    if (idx >= 0) {
      this.m_data[idx] += (value);
    }

    start = ((b - 1) * this.m_m);
    idx = Arrays.binarySearch(this.m_candidates, start,
        (start + this.m_m), a);
    if (idx >= 0) {
      this.m_data[idx] += (value);
    }
  }

  /** {@inheritDoc} */
  @Override
  public final void setFloat(final int a, final int b, final float value) {
    int idx, start;

    start = ((a - 1) * this.m_m);
    idx = Arrays.binarySearch(this.m_candidates, start,
        (start + this.m_m), b);
    if (idx >= 0) {
      this.m_data[idx] = (value);
    }

    start = ((b - 1) * this.m_m);
    idx = Arrays.binarySearch(this.m_candidates, start,
        (start + this.m_m), a);
    if (idx >= 0) {
      this.m_data[idx] = (value);
    }
  }

  /** {@inheritDoc} */
  @Override
  public final float getFloat(final int a, final int b) {
    int idx, start;

    start = ((a - 1) * this.m_m);
    idx = Arrays.binarySearch(this.m_candidates, start,
        (start + this.m_m), b);
    if (idx >= 0) {
      return (this.m_data[idx]);
    }

    start = ((b - 1) * this.m_m);
    idx = Arrays.binarySearch(this.m_candidates, start,
        (start + this.m_m), a);
    if (idx >= 0) {
      return (this.m_data[idx]);
    }

    return (0f);
  }

  /** {@inheritDoc} */
  @Override
  public final void addFloat(final int a, final int b, final float value) {
    int idx, start;

    start = ((a - 1) * this.m_m);
    idx = Arrays.binarySearch(this.m_candidates, start,
        (start + this.m_m), b);
    if (idx >= 0) {
      this.m_data[idx] += (value);
    }

    start = ((b - 1) * this.m_m);
    idx = Arrays.binarySearch(this.m_candidates, start,
        (start + this.m_m), a);
    if (idx >= 0) {
      this.m_data[idx] += (value);
    }
  }

  /** {@inheritDoc} */
  @Override
  public final void setDouble(final int a, final int b, final double value) {
    int idx, start;

    start = ((a - 1) * this.m_m);
    idx = Arrays.binarySearch(this.m_candidates, start,
        (start + this.m_m), b);
    if (idx >= 0) {
      this.m_data[idx] = ((float) (value));
    }

    start = ((b - 1) * this.m_m);
    idx = Arrays.binarySearch(this.m_candidates, start,
        (start + this.m_m), a);
    if (idx >= 0) {
      this.m_data[idx] = ((float) (value));
    }
  }

  /** {@inheritDoc} */
  @Override
  public final double getDouble(final int a, final int b) {
    int idx, start;

    start = ((a - 1) * this.m_m);
    idx = Arrays.binarySearch(this.m_candidates, start,
        (start + this.m_m), b);
    if (idx >= 0) {
      return (this.m_data[idx]);
    }

    start = ((b - 1) * this.m_m);
    idx = Arrays.binarySearch(this.m_candidates, start,
        (start + this.m_m), a);
    if (idx >= 0) {
      return (this.m_data[idx]);
    }

    return (0d);
  }

  /** {@inheritDoc} */
  @Override
  public final void addDouble(final int a, final int b, final double value) {
    int idx, start;

    start = ((a - 1) * this.m_m);
    idx = Arrays.binarySearch(this.m_candidates, start,
        (start + this.m_m), b);
    if (idx >= 0) {
      this.m_data[idx] += ((float) (value));
    }

    start = ((b - 1) * this.m_m);
    idx = Arrays.binarySearch(this.m_candidates, start,
        (start + this.m_m), a);
    if (idx >= 0) {
      this.m_data[idx] += ((float) (value));
    }
  }

  /** {@inheritDoc} */
  @Override
  public final void inc(final int a, final int b) {
    int idx, start;

    start = ((a - 1) * this.m_m);
    idx = Arrays.binarySearch(this.m_candidates, start,
        (start + this.m_m), b);
    if (idx >= 0) {
      this.m_data[idx]++;
    }

    start = ((b - 1) * this.m_m);
    idx = Arrays.binarySearch(this.m_candidates, start,
        (start + this.m_m), a);
    if (idx >= 0) {
      this.m_data[idx]++;
    }
  }

  /** {@inheritDoc} */
  @Override
  public final void dec(final int a, final int b) {
    int idx, start;

    start = ((a - 1) * this.m_m);
    idx = Arrays.binarySearch(this.m_candidates, start,
        (start + this.m_m), b);
    if (idx >= 0) {
      this.m_data[idx]--;
    }

    start = ((b - 1) * this.m_m);
    idx = Arrays.binarySearch(this.m_candidates, start,
        (start + this.m_m), a);
    if (idx >= 0) {
      this.m_data[idx]--;
    }
  }

  /** {@inheritDoc} */
  @Override
  public final boolean isSymmetric() {
    return true;
  }

  /** {@inheritDoc} */
  @Override
  public final boolean areFloatsAllowed() {
    return true;
  }

  /** {@inheritDoc} */
  @Override
  public final long getMinimumAllowedValue() {
    return (java.lang.Long.MIN_VALUE);
  }

  /** {@inheritDoc} */
  @Override
  public final long getMaximumAllowedValue() {
    return (java.lang.Long.MAX_VALUE);
  }
}
