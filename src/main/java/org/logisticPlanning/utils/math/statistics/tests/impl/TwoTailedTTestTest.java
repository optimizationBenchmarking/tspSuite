package org.logisticPlanning.utils.math.statistics.tests.impl;

import org.logisticPlanning.utils.math.data.collection.IDataCollection;
import org.logisticPlanning.utils.math.functions.stochastic.TCDF;
import org.logisticPlanning.utils.math.statistics.aggregates.StatisticInfo;
import org.logisticPlanning.utils.math.statistics.tests.spec.BivariateTwoTailedTest;
import org.logisticPlanning.utils.math.statistics.tests.spec.MultivariateTest;
import org.logisticPlanning.utils.math.statistics.tests.spec.MultivariateTestResult;

/**
 * <p>
 * The two-tailed, non-homoscedastic t-Test, implemented similarly to the
 * suggestion in&nbsp;[<a href="#cite_A2013CMTACML"
 * style="font-weight:bold">1</a>], with the difference that the
 * approximate degrees of freedom are rounded after approximating them as
 * suggested in&nbsp;[<a href="#cite_SE20061STITIAAC"
 * style="font-weight:bold">2</a>].
 * </p>
 * <p>
 * TODO: This class needs to be tested.
 * </p>
 * <h2>References</h2>
 * <ol>
 * <li><div><span id="cite_A2013CMTACML" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;Commons Math: The
 * Apache Commons Mathematics Library,&rdquo;</span> (Website),
 * April&nbsp;7, 2013, Forest Hill, MD, USA: Apache Software Foundation.
 * <div>link: [<a
 * href="https://commons.apache.org/proper/commons-math/">1</
 * a>]</div></div></li>
 * <li><div><span id="cite_SE20061STITIAAC" />David C. Stone and&nbsp;Jon
 * Ellis: <span style="font-style:italic;font-family:cursive;">&ldquo;Stats
 * Tutorial &#8211; Intro to Instrumental Analysis and
 * Calibration,&rdquo;</span> (Website), August&nbsp;23, 2006, Toronto, ON,
 * Canada: University of Toronto (UofT), Department of Chemistry.
 * <div>link: [<a href=
 * "http://www.chem.utoronto.ca/coursenotes/analsci/StatsTutorial/index.html"
 * >1</a>]</div></div></li>
 * </ol>
 */
public final class TwoTailedTTestTest extends BivariateTwoTailedTest {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the first statistic info record */
  private transient StatisticInfo m_infoA;
  /** the second statistic info record */
  private transient StatisticInfo m_infoB;

  /** the internal constructor */
  public TwoTailedTTestTest() {
    super("tTest"); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  protected final void doTest(final MultivariateTestResult dest,
      final int dim, final IDataCollection a, final IDataCollection b,
      final int i, final int j) {
    final double m1, m2, v1, v2, r;
    final long n1, n2;
    StatisticInfo aa, bb;
    int ii;

    aa = this.m_infoA;
    if (aa == null) {
      this.m_infoA = aa = new StatisticInfo();
    } else {
      aa.reset();
    }

    bb = this.m_infoB;
    if (bb == null) {
      this.m_infoB = bb = new StatisticInfo();
    } else {
      bb.reset();
    }

    for (ii = a.size(); (--ii) >= 0;) {
      aa.visitDouble(a.get(ii, dim));
    }
    for (ii = b.size(); (--ii) >= 0;) {
      bb.visitDouble(b.get(ii, dim));
    }

    m1 = aa.getArithmeticMean();
    m2 = bb.getArithmeticMean();
    v1 = aa.getVariance();
    v2 = bb.getVariance();
    n1 = aa.getCount();
    n2 = bb.getCount();

    r = TCDF.tCDF(TwoTailedTTestTest.__df(v1, v2, n1, n2), //
        (-Math.abs(TwoTailedTTestTest.__t(m1, m2, v1, v2, n1, n2))));

    MultivariateTest
        .setResult(dest, i, j, Double.compare(m1, m2), (r + r));
  }

  /** {@inheritDoc} */
  @Override
  public final TwoTailedTTestTest clone() {
    TwoTailedTTestTest r;

    r = ((TwoTailedTTestTest) (super.clone()));
    r.m_infoA = null;
    r.m_infoB = null;

    return r;
  }

  /**
   * /** Computes the degrees of freedom for 2-sample t-test, according
   * to&nbsp;[<a href="#cite_A2013CMTACML" style="font-weight:bold">1</a>]
   * but rounded as suggested by&nbsp;[<a href="#cite_SE20061STITIAAC"
   * style="font-weight:bold">2</a>].
   * 
   * @param v1
   *          first sample variance
   * @param v2
   *          second sample variance
   * @param n1
   *          first sample n
   * @param n2
   *          second sample n
   * @return approximate degrees of freedom
   */
  private static final long __df(final double v1, final double v2,
      final double n1, final double n2) {
    final double v;
    v = ((v1 / n1) + (v2 / n2));
    return Math.round((v * v) / (//
        ((v1 * v1) / (n1 * n1 * (n1 - 1d))) + //
        ((v2 * v2) / (n2 * n2 * (n2 - 1d)))));
  }

  /**
   * Computes t test statistic for 2-sample t-test as defined in&nbsp;[<a
   * href="#cite_A2013CMTACML" style="font-weight:bold">1</a>].
   * 
   * @param m1
   *          first sample mean
   * @param m2
   *          second sample mean
   * @param v1
   *          first sample variance
   * @param v2
   *          second sample variance
   * @param n1
   *          first sample n
   * @param n2
   *          second sample n
   * @return t test statistic
   */
  private static final double __t(final double m1, final double m2,
      final double v1, final double v2, final long n1, final long n2) {
    return ((m1 - m2) / Math.sqrt((v1 / n1) + (v2 / n2)));
  }

}
