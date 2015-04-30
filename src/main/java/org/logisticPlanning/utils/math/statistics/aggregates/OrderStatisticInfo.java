package org.logisticPlanning.utils.math.statistics.aggregates;

import java.util.ArrayList;

import org.logisticPlanning.utils.collections.lists.ArraySetView;
import org.logisticPlanning.utils.math.statistics.EStatisticParameter;

/**
 * An aggregate that also collects order statistics.
 */
public final class OrderStatisticInfo extends StatisticInfo {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the list of supported statistic parameters */
  private static final ArraySetView<EStatisticParameter> NLIST;

  /** the dimension of the order statistic info record */
  public static final int ORDER_STATISTIC_INFO_DIMENSION;

  static {
    final ArrayList<EStatisticParameter> al;
    al = new ArrayList<>();
    al.addAll(StatisticInfo.LIST);
    al.add(EStatisticParameter.PERCENTILE_05);
    al.add(EStatisticParameter.PERCENTILE_25);
    al.add(EStatisticParameter.MEDIAN);
    al.add(EStatisticParameter.PERCENTILE_75);
    al.add(EStatisticParameter.PERCENTILE_95);

    NLIST = EStatisticParameter.makeList(//
        al.toArray(new EStatisticParameter[al.size()]));
    ORDER_STATISTIC_INFO_DIMENSION = OrderStatisticInfo.NLIST.size();
  }

  /** the list where adding is forwarded to */
  private _DoubleList m_list;

  /** Create the list aggregate */
  public OrderStatisticInfo() {
    super();
    this.m_list = new _DoubleList();
  }

  /** {@inheritDoc} */
  @Override
  public OrderStatisticInfo clone() {
    OrderStatisticInfo o;

    o = ((OrderStatisticInfo) (super.clone()));
    o.m_list = ((_DoubleList) (o.m_list.clone()));

    return o;
  }

  /**
   * get the quantile
   * 
   * @param quantile
   *          the quantile
   * @return the quantile value
   */
  public final double getQuantile(final double quantile) {
    return this.m_list._quantile(quantile);
  }

  /** {@inheritDoc} */
  @Override
  public final void visitDouble(final double value) {
    this.m_list.add(value);
    super.visitDouble(value);
  }

  /** {@inheritDoc} */
  @Override
  public final void reset() {
    this.m_list.clear();
    super.reset();
  }

  /** {@inheritDoc} */
  @Override
  public final ArraySetView<EStatisticParameter> getParameters() {
    return OrderStatisticInfo.NLIST;
  }

  /** {@inheritDoc} */
  @Override
  public final double getParameter(final EStatisticParameter param) {
    switch (param) {
      case PERCENTILE_05: {
        return this.getQuantile(0.05d);
      }
      case PERCENTILE_25: {
        return this.getQuantile(0.25d);
      }
      case MEDIAN: {
        return this.getQuantile(0.5d);
      }
      case PERCENTILE_75: {
        return this.getQuantile(0.75d);
      }
      case PERCENTILE_95: {
        return this.getQuantile(0.95d);
      }
      default: {
        return super.getParameter(param);
      }
    }
  }

  /** {@inheritDoc} */
  @Override
  public final double get(final int dimension) {
    return this.getParameter(StatisticInfo.LIST.get(dimension));
  }

  /** {@inheritDoc} */
  @Override
  public final int dimension() {
    return OrderStatisticInfo.ORDER_STATISTIC_INFO_DIMENSION;
  }

}
