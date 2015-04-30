package org.logisticPlanning.utils.math.statistics.tests.spec;

import org.logisticPlanning.utils.math.data.collection.IDataCollection;

/**
 * The base class for bivariate tests.
 */
public abstract class BivariateTwoTailedTest extends MultivariateTest {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * instantiate
   * 
   * @param name
   *          the test name
   */
  protected BivariateTwoTailedTest(final String name) {
    super(name);
  }

  /** {@inheritDoc} */
  @Override
  public final boolean isTwoTailed() {
    return true;
  }

  /**
   * do test
   * 
   * @param dest
   *          the destination
   * @param a
   *          the first data set
   * @param b
   *          the second data set
   * @param dim
   *          the dimension to be compared
   * @param i
   *          the index of the first data set
   * @param j
   *          the index of the second data set
   */
  protected abstract void doTest(final MultivariateTestResult dest,
      final int dim, final IDataCollection a, final IDataCollection b,
      final int i, final int j);

  /** {@inheritDoc} */
  @Override
  protected final void doTest(final MultivariateTestResult dest,
      final int dim, final IDataCollection... dataSets) {
    int i, j;

    for (i = dataSets.length; (--i) > 0;) {
      for (j = i; (--j) >= 0;) {
        this.doTest(dest, dim, dataSets[i], dataSets[j], i, j);
      }
    }
  }

}
