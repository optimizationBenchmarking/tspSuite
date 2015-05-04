package org.logisticPlanning.utils.math.statistics.tests.spec;

import java.util.Arrays;

/**
 * The result of a two-tailed multivariate test.
 */
public final class MultivariateTwoTailedTestResult extends
    _TwoTailedComparisonTestResult {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the number of compared distributions */
  private final int m_n;

  /** the error probabilities */
  private final double[] m_error;

  /** the comparison results */
  private final int[] m_cmp;

  /**
   * the test result
   *
   * @param n
   *          the number of compared distributions
   */
  public MultivariateTwoTailedTestResult(final int n) {
    super();

    final int s;

    if (n <= 0) {
      throw new IllegalArgumentException(//
          "The dimension must be greater or equal to 1, but is " + n); //$NON-NLS-1$
    }

    s = ((int) ((((long) (n)) * ((n) - 1)) >>> 1));
    this.m_error = new double[s];
    this.m_cmp = new int[s];
    this.m_n = n;

    this.clear();
  }

  /** {@inheritDoc} */
  @Override
  final double _getErrorProbability(final int i, final int j) {
    final int a, b;
    final double r;

    if (i > j) {
      a = i;
      b = j;
    } else {
      a = j;
      b = i;
    }

    r = this.m_error[(((a) * (a - 1)) >>> 1) + b];
    if (r != r) {
      throw new IllegalStateException(
          (("No comparison result has been set for indexes " + i) + //$NON-NLS-1$
              ',')
              + j);
    }
    return r;
  }

  /** {@inheritDoc} */
  @Override
  final int _compare(final int i, final int j) {
    final int a, b, r;
    final boolean toggle;

    if (i > j) {
      a = i;
      b = j;
      toggle = false;
    } else {
      a = j;
      b = i;
      toggle = true;
    }

    r = this.m_cmp[(((a) * (a - 1)) >>> 1) + b];

    return (toggle ? (-r) : r);
  }

  /** {@inheritDoc} */
  @Override
  final void clear() {
    Arrays.fill(this.m_cmp, 0);
    Arrays.fill(this.m_error, Double.NaN);
  }

  /** {@inheritDoc} */
  @Override
  final void _setResult(final int i, final int j, final int cmp,
      final double errorProb) {
    final int a, b, idx;
    final boolean toggle;

    if (i > j) {
      a = i;
      b = j;
      toggle = false;
    } else {
      a = j;
      b = i;
      toggle = true;
    }

    idx = ((((a) * (a - 1)) >>> 1) + b);

    this.m_error[idx] = errorProb;
    this.m_cmp[idx] = (toggle ? (-cmp) : cmp);
  }

  /** {@inheritDoc} */
  @Override
  public final int n() {
    return this.m_n;
  }
}
