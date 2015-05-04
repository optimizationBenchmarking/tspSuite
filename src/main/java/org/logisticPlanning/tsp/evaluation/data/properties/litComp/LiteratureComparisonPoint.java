package org.logisticPlanning.tsp.evaluation.data.properties.litComp;

import org.logisticPlanning.tsp.evaluation.data.Accessor;
import org.logisticPlanning.utils.math.statistics.EStatisticParameter;
import org.logisticPlanning.utils.math.statistics.series.StatisticSeries;
import org.logisticPlanning.utils.utils.comparison.ComparisonUtils;
import org.logisticPlanning.utils.utils.comparison.EComparison;

/**
 * A result comparison point
 */
public final class LiteratureComparisonPoint implements
Comparable<LiteratureComparisonPoint> {

  /** the point */
  private final LiteratureResultPoint m_point;

  /** the same quality point */
  private final ResPoint m_qualityLB;
  /** the lower bound of the runtime */
  private final ResPoint m_timeLB;
  /** the upper bound of the runtime */
  private final ResPoint m_timeUB;

  /** the comparison result */
  final ESingleComparison m_cmp;

  /**
   * create a result comparison point
   *
   * @param point
   *          the point
   * @param ser
   *          the series, where the time dimension is the x-coordinate and
   *          the result dimension corresponds to the y-coordinates
   */
  LiteratureComparisonPoint(final LiteratureResultPoint point,
      final StatisticSeries ser) {
    super();

    final Accessor time, quality;
    final EStatisticParameter stat;
    final int tLBidx;
    final ResPoint p;
    ResPoint qLB, tLB, tUB;

    this.m_point = point;
    p = point.m_data;
    stat = point.m_resultStatistic;
    time = point.m_timeDim;
    quality = point.m_resultDim;

    this.m_qualityLB = qLB = LiteratureComparisonPoint.__point(ser,//
        ser.firstIndexOf(p.m_quality, point.m_resultStatistic,
            EComparison.LESS_OR_SAME),//
            time, quality, stat);

    if (qLB != null) {// we can achieve the goal quality
      if (qLB.m_time <= p.m_time) { // and can do so in the right time
        this.m_timeLB = this.m_timeUB = null;
        if ((qLB.m_time < p.m_time) || (qLB.m_quality < p.m_time)) {
          // even faster!
          this.m_cmp = ((time.isTime()) ? ESingleComparison.MAYBE_BETTER
              : ESingleComparison.BETTER);
        } else {
          // at the same time!
          this.m_cmp = ((time.isTime()) ? ESingleComparison.MAYBE_SIMILAR
              : ESingleComparison.SIMILAR);
        }
        return;
      }
    }

    tLBidx = ser.indexOfX(p.m_time);
    tLB = LiteratureComparisonPoint.__point(ser, tLBidx, time, quality,
        stat);

    if ((tLB != null) && (tLB.m_time >= p.m_time)) {
      tUB = tLB;
      tLB = null;
    } else {
      tUB = LiteratureComparisonPoint.__point(ser,
          ((qLB != null) ? (tLBidx + 1) : (ser.size() - 1)), time,
          quality, stat);
      if ((tUB != null) && (tLB != null) && (tLB.m_time >= tUB.m_time)) {
        tLB = null;
      }
    }

    setCmp: {
      if (tUB != null) {

        // ok, our algorithm ran at least as long as the one we compare
        // with...
        if (qLB == null) {
          if (tUB.m_quality <= p.m_quality) {
            throw new IllegalStateException();
          }
          if (tUB.m_time >= p.m_time) {
            // since we did not achieve the same quality, we must be
            // worse
            this.m_cmp = ((time.isTime()) ? ESingleComparison.MAYBE_WORSE
                : ESingleComparison.WORSE);
            break setCmp;
          }
        } else {
          if (qLB.m_time < tUB.m_time) {
            throw new IllegalStateException();
          }
          if (qLB.m_time <= tUB.m_time) {
            tUB = null;
          }

          this.m_cmp = ((time.isTime()) ? ESingleComparison.MAYBE_SLOWER
              : ESingleComparison.SLOWER);
          break setCmp;
        }
      }

      this.m_cmp = ((time.isTime()) ? ESingleComparison.MAYBE_NOT_COMPARABLE
          : ESingleComparison.NOT_COMPARABLE);
    }

    this.m_timeLB = tLB;
    this.m_timeUB = tUB;
  }

  /**
   * make a point
   *
   * @param ser
   *          the series
   * @param idx
   *          the index
   * @param time
   *          the time
   * @param quality
   *          the quality
   * @param stat
   *          the stat
   * @return the point, or {@code null} if undefined
   */
  private static final ResPoint __point(final StatisticSeries ser,
      final int idx, final Accessor time, final Accessor quality,
      final EStatisticParameter stat) {
    final double x, y;

    if (idx < 0) {
      return null;
    }
    if (idx >= ser.size()) {
      return null;
    }

    y = ser.getParameter(idx, stat);
    if ((y != y) || (y >= Double.POSITIVE_INFINITY)) {
      return null;
    }
    quality.validateValue(y, false);

    x = ser.get(idx, StatisticSeries.DIM_X);
    time.validateValue(x, true);

    return new ResPoint(x, y);
  }

  /**
   * Get the literature result point
   *
   * @return the literature result point
   */
  public final LiteratureResultPoint getLiteratureResultPoint() {
    return this.m_point;
  }

  /**
   * Get the point where the quality is at least as good as described in
   * literature, or {@code null} if no such point exists.
   *
   * @return the point where the quality is at least as good as described
   *         in literature -- or {@code null} if no such point exists
   */
  public final ResPoint getQualityLowerBound() {
    return this.m_qualityLB;
  }

  /**
   * Get the closest point with less or equally much consumed runtime than
   * in the literature, or {@code null} if no such point is needed
   *
   * @return the closest point with less or equally much consumed runtime
   *         than in the literature, or {@code null} if no such point is
   *         needed
   */
  public final ResPoint getTimeLowerBound() {
    return this.m_timeLB;
  }

  /**
   * Get the closest point with greater or equally much consumed runtime
   * than in the literature, or {@code null} if no such point is needed
   *
   * @return the closest point with greater or equally much consumed
   *         runtime than in the literature, or {@code null} if no such
   *         point is needed
   */
  public final ResPoint getTimeUpperBound() {
    return this.m_timeUB;
  }

  /**
   * the comparison result
   *
   * @return the comparison result
   */
  public final ESingleComparison getComparisonResult() {
    return this.m_cmp;
  }

  /** {@inheritDoc} */
  @Override
  public final int compareTo(final LiteratureComparisonPoint o) {
    int i;

    if (o == this) {
      return 0;
    }
    if (o == null) {
      return (-1);
    }

    i = this.m_point.compareTo(o.m_point);
    if (i != 0) {
      return i;
    }

    i = this.m_cmp.compareTo(o.m_cmp);
    if (i != 0) {
      return i;
    }

    i = ComparisonUtils.compare(this.m_qualityLB, o.m_qualityLB);
    if (i != 0) {
      return i;
    }

    i = ComparisonUtils.compare(this.m_timeLB, o.m_timeLB);
    if (i != 0) {
      return i;
    }
    return ComparisonUtils.compare(this.m_timeUB, o.m_timeUB);
  }

}
