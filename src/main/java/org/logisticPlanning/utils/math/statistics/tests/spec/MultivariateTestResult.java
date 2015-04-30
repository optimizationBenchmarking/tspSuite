package org.logisticPlanning.utils.math.statistics.tests.spec;

import java.io.Serializable;

/**
 * The result of a multivariate test.
 */
public abstract class MultivariateTestResult implements Serializable {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the test result */
  protected MultivariateTestResult() {
    super();
  }

  /**
   * Obtain the probability of a type-one error, i.e., the probability that
   * the the {@link #compare(int, int) observed difference} (if any),
   * between distribution {@code i} and {@code j}, was insignificant if
   * assumed to be significant
   * 
   * @param i
   *          the index of the first distribution
   * @param j
   *          the index of the second distribution
   * @return the error probability
   */
  public abstract double getErrorProbability(final int i, final int j);

  /**
   * Get the comparison result of distribution {@code i} and {@code j}.
   * 
   * @param i
   *          the index of the first distribution
   * @param j
   *          the index of the second distribution
   * @return the comparison result: {@code -1} if the compared parameter of
   *         distribution {@code i} tends to be smaller than the same
   *         parameter for distribution {@code j}, {@code 0} if there is no
   *         difference, and {@code 1} if it tends to be larger
   */
  public abstract int compare(final int i, final int j);

  /**
   * the number of compared data samples / distributions
   * 
   * @return the number of compared data samples / distributions
   */
  public abstract int n();

  /** clear this result record */
  abstract void clear();

  /**
   * Set the comparison result
   * 
   * @param i
   *          the index of the first distribution
   * @param j
   *          the index of the second distribution
   * @param cmp
   *          the comparison result
   * @param errorProb
   *          the error probability.
   */
  abstract void setResult(final int i, final int j, final int cmp,
      final double errorProb);

  /**
   * Is the result obtained with a two-tailed test version?
   * 
   * @return {@code true} if the result obtained with a two-tailed test
   *         version, {@code false} otherwise
   */
  public abstract boolean isTwoTailed();
}
