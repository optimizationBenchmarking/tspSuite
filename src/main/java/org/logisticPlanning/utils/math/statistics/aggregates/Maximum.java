package org.logisticPlanning.utils.math.statistics.aggregates;

import org.logisticPlanning.utils.collections.lists.ArraySetView;
import org.logisticPlanning.utils.math.statistics.EStatisticParameter;
import org.logisticPlanning.utils.math.statistics.IStatisticPoint;

/**
 * This class computes the maximum of a set of numbers.
 */
public final class Maximum extends ScalarAggregate implements
IStatisticPoint {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the list of supported statistic parameters */
  private static final ArraySetView<EStatisticParameter> LIST = EStatisticParameter
      .makeList(EStatisticParameter.MAXIMUM);

  /** the value of the maximum */
  private double m_value;

  /** instantiate */
  public Maximum() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public final void visitDouble(final double value) {
    if (value > this.m_value) {
      this.m_value = value;
    }
  }

  /** {@inheritDoc} */
  @Override
  public final double getResult() {
    return this.m_value;
  }

  /** {@inheritDoc} */
  @Override
  public final void reset() {
    this.m_value = Double.NEGATIVE_INFINITY;
  }

  /** {@inheritDoc} */
  @Override
  public final String toString() {
    return EStatisticParameter.MAXIMUM.getLongName(false);
  }

  /** {@inheritDoc} */
  @Override
  public final ArraySetView<EStatisticParameter> getParameters() {
    return Maximum.LIST;
  }

  /** {@inheritDoc} */
  @Override
  public final double getParameter(final EStatisticParameter param) {
    if (param == EStatisticParameter.MAXIMUM) {
      return this.m_value;
    }
    throw new UnsupportedOperationException(String.valueOf(param));
  }
}
