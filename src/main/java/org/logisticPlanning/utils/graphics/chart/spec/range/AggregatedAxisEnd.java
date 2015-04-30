package org.logisticPlanning.utils.graphics.chart.spec.range;

import org.logisticPlanning.utils.math.data.point.Point;
import org.logisticPlanning.utils.math.statistics.aggregates.Aggregate;
import org.logisticPlanning.utils.math.statistics.aggregates.Maximum;
import org.logisticPlanning.utils.math.statistics.aggregates.Minimum;
import org.logisticPlanning.utils.math.statistics.aggregates.OnlyFiniteFilter;
import org.logisticPlanning.utils.math.statistics.aggregates.ScalarAggregate;
import org.logisticPlanning.utils.utils.comparison.ComparisonUtils;

/**
 * An aggregated axis end
 */
public class AggregatedAxisEnd extends AxisEnd {

  /** the dimension */
  private final int m_dim;
  /** the selector */
  private final EValueSelector m_sel;

  /** the per line aggregate */
  private Aggregate m_perLineAggregate;

  /** the total aggregate */
  private Aggregate m_totalAggregate;

  /** are the values computed? */
  private transient boolean m_computed;
  /** the (computed) value */
  private transient double m_value;

  /**
   * create
   * 
   * @param dim
   *          the dimension
   * @param sel
   *          the value selector
   * @param perLineAggregate
   *          the per-line aggregate
   * @param totalAggregate
   *          the total aggregate
   */
  public AggregatedAxisEnd(final int dim, final EValueSelector sel,
      final ScalarAggregate perLineAggregate,
      final ScalarAggregate totalAggregate) {
    super();

    if ((dim < 0) || (dim > 3)) {
      throw new IllegalArgumentException("Axis invalid: " + dim); //$NON-NLS-1$
    }
    this.m_dim = dim;

    this.m_sel = ((sel != null) ? sel : EValueSelector.FINITE_VALUE);

    if (perLineAggregate == null) {
      throw new IllegalArgumentException(//
          "Per-line aggregate must not be null."); //$NON-NLS-1$
    }
    if (totalAggregate == null) {
      throw new IllegalArgumentException(//
          "Total aggregate must not be null."); //$NON-NLS-1$
    }
    this.m_perLineAggregate = new OnlyFiniteFilter(perLineAggregate);
    this.m_totalAggregate = new OnlyFiniteFilter(totalAggregate);

    this.m_computed = false;
    this.m_value = Double.NaN;
    perLineAggregate.reset();
    totalAggregate.reset();
  }

  /** {@inheritDoc} */
  @Override
  public AggregatedAxisEnd clone() {
    AggregatedAxisEnd e;

    e = ((AggregatedAxisEnd) (super.clone()));
    e.m_perLineAggregate = ((Aggregate) (e.m_perLineAggregate.clone()));
    e.m_totalAggregate = ((Aggregate) (e.m_totalAggregate.clone()));

    return e;
  }

  /**
   * get the default small per line aggregate
   * 
   * @param agg
   *          the aggregate
   * @return the default small per line aggregate
   */
  static final ScalarAggregate _defaultSmallPerLineAggregate(
      final ScalarAggregate agg) {
    return ((agg != null) ? agg : new Minimum());
  }

  /**
   * get the default big per line aggregate
   * 
   * @param agg
   *          the aggregate
   * @return the default big per line aggregate
   */
  static final ScalarAggregate _defaultBigPerLineAggregate(
      final ScalarAggregate agg) {
    return ((agg != null) ? agg : new Maximum());
  }

  /**
   * get the default small total aggregate
   * 
   * @param agg
   *          the aggregate
   * @return the default small total aggregate
   */
  static final ScalarAggregate _defaultSmallTotalAggregate(
      final ScalarAggregate agg) {
    return ((agg != null) ? agg : new Minimum());
  }

  /**
   * get the default big total aggregate
   * 
   * @param agg
   *          the aggregate
   * @return the default big total aggregate
   */
  static final ScalarAggregate _defaultBigTotalAggregate(
      final ScalarAggregate agg) {
    return ((agg != null) ? agg : new Maximum());
  }

  /** {@inheritDoc} */
  @Override
  public final boolean beginData() {
    this.m_computed = false;
    this.m_perLineAggregate.reset();
    this.m_value = Double.NaN;
    return true;
  }

  /** {@inheritDoc} */
  @Override
  public final void endData() {
    this.m_totalAggregate.visitDouble(this.m_perLineAggregate.get(0));
    super.endData();
  }

  /** {@inheritDoc} */
  @SuppressWarnings("fallthrough")
  @Override
  public final boolean registerPoint(final Point p) {
    int i;

    if (p == null) {
      return true;
    }

    switch (this.m_sel) {
      case FINITE_POINT: {
        for (i = p.dimension(); (--i) >= 0;) {
          if (!(ComparisonUtils.isFinite(p.get(i)))) {
            return true;
          }
        }
      }
      case FINITE_VALUE: {
        if (!(ComparisonUtils.isFinite(p.get(this.m_dim)))) {
          return true;
        }
      }
      default: {
        this.m_perLineAggregate.visitDouble(p.get(this.m_dim));
        return false;
      }
    }
  }

  /**
   * Calculate the end value from an aggregate
   * 
   * @param aggregate
   *          the aggregate
   * @return the result
   */
  protected double calcEnd(final double aggregate) {
    return aggregate;
  }

  /** {@inheritDoc} */
  @Override
  protected final double calcEnd() {
    if (this.m_computed) {
      return this.m_value;
    }
    this.m_value = this.calcEnd(this.m_totalAggregate.get(0));
    this.m_computed = true;
    return this.m_value;
  }

  // /** {@inheritDoc} */
  // @Override
  // public void reset() {
  // this.m_computed = false;
  // this.m_value = Double.NaN;
  // this.m_perLineAggregate.reset();
  // this.m_totalAggregate.reset();
  // super.reset();
  // }
}
