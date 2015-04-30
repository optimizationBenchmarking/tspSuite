package org.logisticPlanning.utils.math.statistics;

import org.logisticPlanning.utils.utils.comparison.EComparison;
import org.logisticPlanning.utils.utils.comparison.PreciseComparator;

/**
 * A comparator for statistic information. Generally, for any statistics,
 * we consider smaller values to be better
 */
public final class StatisticInfoComparator extends
    PreciseComparator<IStatisticPoint> {

  /** the parameter */
  private final EStatisticParameter m_param;

  /**
   * Create!
   * 
   * @param param
   *          the parameter to be compared.
   */
  public StatisticInfoComparator(final EStatisticParameter param) {
    super();
    this.m_param = param;
  }

  /** {@inheritDoc} */
  @Override
  protected final EComparison doPreciseCompare(final IStatisticPoint a,
      final IStatisticPoint b) {
    return this.m_param.compare(a.getParameter(this.m_param),
        b.getParameter(this.m_param));
  }
}
