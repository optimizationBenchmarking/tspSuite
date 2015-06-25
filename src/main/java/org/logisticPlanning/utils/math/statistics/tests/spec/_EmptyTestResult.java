package org.logisticPlanning.utils.math.statistics.tests.spec;

/**
 * The bivariate test result represents a two-tailed (open-ended)
 * comparison of two distributions.
 */
final class _EmptyTestResult extends MultivariateTestResult {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** two-tailed */
  static final MultivariateTestResult TT = new _EmptyTestResult(true);
  /** one-tailed */
  static final MultivariateTestResult OT = new _EmptyTestResult(false);

  /** is this two tailed? */
  private final boolean m_tt;

  /**
   * Create a new bivariate test result
   *
   * @param tt
   *          the tailed-ness
   */
  private _EmptyTestResult(final boolean tt) {
    super();
    this.m_tt = tt;
  }

  /** {@inheritDoc} */
  @Override
  final void clear() {//
  }

  /** {@inheritDoc} */
  @Override
  public final int n() {
    return 1;
  }

  /** {@inheritDoc} */
  @Override
  public final double getErrorProbability(final int i, final int j) {
    if ((i != 0) || (j != 0)) {
      throw new IllegalArgumentException((String.valueOf(i) + ',') + j);
    }
    return 0d;
  }

  /** {@inheritDoc} */
  @Override
  public final int compare(final int i, final int j) {
    if ((i != 0) || (j != 0)) {
      throw new IllegalArgumentException((String.valueOf(i) + ',') + j);
    }
    return 0;
  }

  /** {@inheritDoc} */
  @Override
  final void setResult(final int i, final int j, final int cmp,
      final double errorProb) {
    //
  }

  /** {@inheritDoc} */
  @Override
  public final boolean isTwoTailed() {
    return this.m_tt;
  }
}
