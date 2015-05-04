package org.logisticPlanning.utils.math.statistics;

/**
 * A statistic collection a collection or sequence of points of statistic
 * parameters.
 */
public interface IStatisticDataCollection extends IStatisticData {

  /**
   * Obtain the value of the statistic parameter. Throws an
   * {@link java.lang.UnsupportedOperationException} if the parameter is
   * not in the list {@link #getParameters()}.
   *
   * @param index
   *          the point's index
   * @param param
   *          the parameter
   * @return the value
   * @throws UnsupportedOperationException
   *           if the parameter is not supported
   */
  public abstract double getParameter(final int index,
      final EStatisticParameter param);

}
