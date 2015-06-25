package org.logisticPlanning.tsp.evaluation.data.properties.litComp;

/** the result point */
public final class ResPoint implements Comparable<ResPoint> {

  /** the time */
  final double m_time;
  /** the quality */
  final double m_quality;

  /**
   * create
   *
   * @param t
   *          the time
   * @param q
   *          the quality
   */
  ResPoint(final double t, final double q) {
    super();
    this.m_time = t;
    this.m_quality = q;
  }

  /**
   * get the time
   *
   * @return the time
   */
  public final double getTime() {
    return this.m_time;
  }

  /**
   * get the quality
   *
   * @return the quality
   */
  public final double getQuality() {
    return this.m_quality;
  }

  /** {@inheritDoc} */
  @Override
  public final int compareTo(final ResPoint o) {
    int i;

    if (o == this) {
      return 0;
    }
    if (o == null) {
      return -1;
    }

    i = Double.compare(this.m_quality, o.m_quality);
    if (i != 0) {
      return i;
    }

    return Double.compare(this.m_time, o.m_time);
  }

  /** {@inheritDoc} */
  @Override
  public final String toString() {
    return (this.m_time + ":" + this.m_quality); //$NON-NLS-1$
  }
}
