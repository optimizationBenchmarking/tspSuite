package org.logisticPlanning.utils.math.statistics;

/**
 * A statistic point is a single element of statistic information.
 */
public interface IStatisticPoint extends IStatisticData {

  /**
   * Obtain the value of the statistic parameter. Throws an
   * {@link java.lang.UnsupportedOperationException} if the parameter is
   * not in the list {@link #getParameters()}.
   * 
   * @param param
   *          the parameter
   * @return the value
   * @throws UnsupportedOperationException
   *           if the parameter is not supported
   */
  public abstract double getParameter(final EStatisticParameter param);

}
