package org.logisticPlanning.utils.math.statistics.aggregates;

import org.logisticPlanning.utils.collections.lists.ArraySetView;
import org.logisticPlanning.utils.math.statistics.EStatisticParameter;
import org.logisticPlanning.utils.math.statistics.IStatisticPoint;

/**
 * An aggregate the computes the median.
 * <em>Warning: Uses lots of memory.</em> .
 */
public class Median extends ScalarAggregate implements IStatisticPoint {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the list of supported statistic parameters */
  private static final ArraySetView<EStatisticParameter> LIST = //
  EStatisticParameter.makeList(//
      EStatisticParameter.MINIMUM,//
      EStatisticParameter.PERCENTILE_05,//
      EStatisticParameter.PERCENTILE_25,//
      EStatisticParameter.MEDIAN,//
      EStatisticParameter.PERCENTILE_75,//
      EStatisticParameter.PERCENTILE_95,//
      EStatisticParameter.MAXIMUM);

  /** the data */
  private _DoubleList m_data;

  /** instantiate */
  public Median() {
    super();
    this.m_data = new _DoubleList();
  }

  /** {@inheritDoc} */
  @Override
  public Median clone() {
    Median m;
    m = ((Median) (super.clone()));
    m.m_data = ((_DoubleList) (m.m_data.clone()));
    return m;
  }

  /**
   * Visit a given {@code double}, i.e., add it to the stable sum.
   * 
   * @param value
   *          the value to add
   */
  @Override
  public void visitDouble(final double value) {
    this.m_data.add(value);
  }

  /**
   * Obtain the current running sum.
   * 
   * @return the current running sum
   */
  @Override
  public double getResult() {
    return this.m_data._quantile(0.5d);
  }

  /** reset the sum to 0 */
  @Override
  public void reset() {
    this.m_data.clear();
  }

  /** {@inheritDoc} */
  @Override
  public String toString() {
    return ((EStatisticParameter.MEDIAN.getShortName() + '=') + this
        .getResult());
  }

  /** {@inheritDoc} */
  @Override
  public ArraySetView<EStatisticParameter> getParameters() {
    return Median.LIST;
  }

  /** {@inheritDoc} */
  @Override
  public double getParameter(final EStatisticParameter param) {
    switch (param) {
      case MINIMUM: {
        return this.m_data._quantile(0d);
      }
      case PERCENTILE_05: {
        return this.m_data._quantile(0.05d);
      }
      case PERCENTILE_25: {
        return this.m_data._quantile(0.25d);
      }
      case MEDIAN: {
        return this.m_data._quantile(0.5d);
      }
      case PERCENTILE_75: {
        return this.m_data._quantile(0.75d);
      }
      case PERCENTILE_95: {
        return this.m_data._quantile(0.95d);
      }
      case MAXIMUM: {
        return this.m_data._quantile(1d);
      }
      default: {
        throw new UnsupportedOperationException(String.valueOf(param));
      }
    }
  }
}
