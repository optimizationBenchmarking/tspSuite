package org.logisticPlanning.utils.math.statistics.tests.spec;

/**
 * The result of a two-tailed multivariate test.
 */
abstract class _TwoTailedComparisonTestResult extends
    MultivariateTestResult {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the test result */
  _TwoTailedComparisonTestResult() {
    super();
  }

  /**
   * Check the parameters
   * 
   * @param i
   *          the i
   * @param j
   *          the j
   * @param n
   *          the n
   */
  private final void __throw(final int i, final int j, final int n) {
    throw new IllegalArgumentException((((((//
        "Both indexes must be between 0 and " + n) + //$NON-NLS-1$
        " but are (") + i) + ',') + j) + ')'); //$NON-NLS-1$
  }

  /**
   * do get the error probability
   * 
   * @param i
   *          the i
   * @param j
   *          the j
   * @return the probability
   */
  abstract double _getErrorProbability(final int i, final int j);

  /** {@inheritDoc} */
  @Override
  public final double getErrorProbability(final int i, final int j) {
    final int n;
    final double r;

    n = this.n();
    if ((i < 0) || (i >= n) || (j < 0) || (j >= n)) {
      this.__throw(i, j, n);
    }

    if (i == j) {
      return 0d;
    }

    r = this._getErrorProbability(i, j);
    if (r != r) {
      throw new IllegalStateException(
          (("No comparison result has been set for indexes " + i) + //$NON-NLS-1$
              ',')
              + j);
    }
    return r;
  }

  /**
   * compare
   * 
   * @param i
   *          the i
   * @param j
   *          the j
   * @return the result
   */
  abstract int _compare(final int i, final int j);

  /** {@inheritDoc} */
  @Override
  public final int compare(final int i, final int j) {
    final int n;

    n = this.n();
    if ((i < 0) || (i >= n) || (j < 0) || (j >= n)) {
      this.__throw(i, j, n);
    }

    if (i == j) {
      return 0;
    }

    return this._compare(i, j);
  }

  /**
   * set the result
   * 
   * @param i
   *          the i
   * @param j
   *          the j
   * @param cmp
   *          the comparison result
   * @param errorProb
   *          the error probability
   */
  abstract void _setResult(final int i, final int j, final int cmp,
      final double errorProb);

  /** {@inheritDoc} */
  @Override
  final void setResult(final int i, final int j, final int cmp,
      final double errorProb) {
    final int n;
    int r;

    n = this.n();
    if ((i < 0) || (i >= n) || (j < 0) || (j >= n)) {
      this.__throw(i, j, n);
    }

    if (i == j) {
      if (cmp != 0) {
        throw new IllegalArgumentException(//
            "Both indexes are the same (" + i + //$NON-NLS-1$
                "), but comparison result is not 0, instead it's "//$NON-NLS-1$
                + cmp);
      }
      return;
    }

    this._setResult(i, j,//
        (r = ((errorProb >= 1d) ? 0 : ((cmp < 0) ? (-1) : ((cmp > 0) ? 1
            : 0)))),//
        ((r == 0) ? 0d : ((errorProb <= 0d) ? 0d : ((errorProb >= 1d) ? 1d
            : errorProb))));
  }

  /** {@inheritDoc} */
  @Override
  public final boolean isTwoTailed() {
    return true;
  }
}
