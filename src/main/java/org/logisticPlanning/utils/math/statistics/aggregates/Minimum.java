package org.logisticPlanning.utils.math.statistics.aggregates;

import org.logisticPlanning.utils.collections.lists.ArraySetView;
import org.logisticPlanning.utils.math.statistics.EStatisticParameter;
import org.logisticPlanning.utils.math.statistics.IStatisticPoint;

/**
 * This class computes the minimum of a set of numbers.
 */
public final class Minimum extends ScalarAggregate implements
    IStatisticPoint {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the list of supported statistic parameters */
  private static final ArraySetView<EStatisticParameter> LIST = EStatisticParameter
      .makeList(EStatisticParameter.MINIMUM);

  /** the value of the minimum */
  private double m_value;

  /** instantiate */
  public Minimum() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public final void visitDouble(final double value) {
    if (value < this.m_value) {
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
    this.m_value = Double.POSITIVE_INFINITY;
  }

  /** {@inheritDoc} */
  @Override
  public final String toString() {
    return EStatisticParameter.MINIMUM.getLongName(false);
  }

  /** {@inheritDoc} */
  @Override
  public final ArraySetView<EStatisticParameter> getParameters() {
    return Minimum.LIST;
  }

  /** {@inheritDoc} */
  @Override
  public final double getParameter(final EStatisticParameter param) {
    if (param == EStatisticParameter.MINIMUM) {
      return this.m_value;
    }
    throw new UnsupportedOperationException(String.valueOf(param));
  }
}
