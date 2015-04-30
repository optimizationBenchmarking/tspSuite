package org.logisticPlanning.utils.graphics.chart.spec.range;

/**
 * A fixed axis end
 */
public final class FixedAxisEnd extends AxisEnd {

  /** the end */
  private final double m_end;

  /**
   * create
   * 
   * @param end
   *          the axis end
   */
  public FixedAxisEnd(final double end) {
    super();
    this.m_end = ((AxisEnd._isLong(end)) ? Math.round(end) : end);
    this.getEnd(); // check errors...
  }

  /** {@inheritDoc} */
  @Override
  protected final double calcEnd() {
    return this.m_end;
  }

  /** {@inheritDoc} */
  @Override
  public AxisEnd clone() {
    return this;
  }
}
