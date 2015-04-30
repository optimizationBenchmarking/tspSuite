package org.logisticPlanning.utils.math.statistics.aggregates;

/**
 * an aggregate
 */
public abstract class ScalarAggregate extends Aggregate {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** instantiate */
  protected ScalarAggregate() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public final int dimension() {
    return 1;
  }

  /**
   * Obtain the current value of the aggregate
   * 
   * @return the current value of the aggregate
   */
  public abstract double getResult();

  /** {@inheritDoc} */
  @Override
  public final double get(final int dimension) {
    return this.getResult();
  }

}
