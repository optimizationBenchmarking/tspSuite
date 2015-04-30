package org.logisticPlanning.utils.math.statistics.aggregates;

/**
 * A filtered aggregate only passes a subset of values to another
 * aggregate.
 */
public abstract class FilteredAggregate extends Aggregate {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the count */
  private Aggregate m_agg;

  /**
   * instantiate
   * 
   * @param agg
   *          the aggregate
   */
  protected FilteredAggregate(final Aggregate agg) {
    super();
    if (agg == null) {
      throw new IllegalArgumentException(//
          "Aggregate must not be null."); //$NON-NLS-1$
    }
    this.m_agg = agg;
  }

  /** {@inheritDoc} */
  @Override
  public FilteredAggregate clone() {
    FilteredAggregate f;

    f = ((FilteredAggregate) (super.clone()));
    f.m_agg = ((Aggregate) (f.m_agg.clone()));
    return f;
  }

  /**
   * Should the given value passed to the aggregate?
   * 
   * @param value
   *          the value
   * @return {@code true} if the value should be aggregated, {@code false}
   *         otherwise
   */
  protected abstract boolean isAllowed(final double value);

  /** {@inheritDoc} */
  @Override
  public final void visitDouble(final double value) {
    if (this.isAllowed(value)) {
      this.m_agg.visitDouble(value);
    }
  }

  /** {@inheritDoc} */
  @Override
  public final double get(final int dimension) {
    return this.m_agg.get(dimension);
  }

  /** {@inheritDoc} */
  @Override
  public final int dimension() {
    return this.m_agg.dimension();
  }

  /** reset the mean */
  @Override
  public final void reset() {
    this.m_agg.reset();
  }

  /** reset the mean */
  @Override
  public final String toString() {
    return (this.getClass().getSimpleName() + '(' + this.m_agg.toString() + ')');
  }
}
