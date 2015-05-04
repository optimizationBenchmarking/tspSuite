package org.logisticPlanning.utils.math.statistics.tests.spec;

/**
 * The bivariate test result represents a two-tailed (open-ended)
 * comparison of two distributions.
 */
public final class BivariateTwoTailedTestResult extends
    _TwoTailedComparisonTestResult {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the error probability */
  private double m_errorProb;

  /** the comparison result */
  private int m_c;

  /**
   * Create a new bivariate test result
   */
  public BivariateTwoTailedTestResult() {
    super();
    this.clear();
  }

  /** {@inheritDoc} */
  @Override
  final void clear() {
    this.m_c = 0;
    this.m_errorProb = Double.NaN;
  }

  /** {@inheritDoc} */
  @Override
  final double _getErrorProbability(final int i, final int j) {
    return this.m_errorProb;
  }

  /** {@inheritDoc} */
  @Override
  final int _compare(final int i, final int j) {
    return ((i == 0) ? this.m_c : (-this.m_c));
  }

  /** {@inheritDoc} */
  @Override
  final void _setResult(final int i, final int j, final int cmp,
      final double errorProb) {
    this.m_errorProb = errorProb;
    this.m_c = ((i == 0) ? cmp : (-cmp));
  }

  /** {@inheritDoc} */
  @Override
  public final int n() {
    return 2;
  }
}
