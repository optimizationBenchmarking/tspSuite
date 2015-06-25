package org.logisticPlanning.utils.math.statistics;

import org.logisticPlanning.utils.collections.lists.ArraySetView;

/**
 * A statistic data element offers some statistic parameters
 */
public interface IStatisticData {

  /**
   * Obtain a list of the supported statistic parameters.
   *
   * @return a list of the supported statistic parameters.
   */
  public abstract ArraySetView<EStatisticParameter> getParameters();

}
