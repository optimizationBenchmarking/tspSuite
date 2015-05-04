package org.logisticPlanning.utils.math.statistics;

/**
 * an interface that provides statistic information
 */
public interface IStatisticInfo extends IStatisticPoint {

  /**
   * Obtain the minimum of the visited elements.
   *
   * @return The minimum of the visited elements.
   */
  public abstract double getMinimum();

  /**
   * Obtain the maximum of the visited elements.
   *
   * @return The maximum of the visited elements.
   */
  public abstract double getMaximum();

  /**
   * Obtain the average of the visited elements.
   *
   * @return The average of the visited elements.
   */
  public abstract double getArithmeticMean();

  /**
   * Obtain the variance of the visited elements.
   *
   * @return The variance of the visited elements.
   */
  public abstract double getVariance();

  /**
   * Obtain the standard deviation of the visited elements.
   *
   * @return The standard deviation of the visited elements.
   */
  public abstract double getStandardDeviation();

  /**
   * Obtain the skewness of the values visited.
   *
   * @return The skewness of the values visited.
   */
  public abstract double getSkewness();

  /**
   * Obtain the kurtosis of the values visited.
   *
   * @return The kurtosis of the values visited.
   */
  public abstract double getKurtosis();
}
