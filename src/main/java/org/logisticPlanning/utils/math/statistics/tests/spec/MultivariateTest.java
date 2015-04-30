package org.logisticPlanning.utils.math.statistics.tests.spec;

import org.logisticPlanning.utils.NamedObject;
import org.logisticPlanning.utils.math.data.collection.IDataCollection;

/**
 * <p>
 * The base class for multi-variate tests. A multi-variate test can compare
 * a statistical parameter of two (or more) distributions represented by
 * data sets.
 * </p>
 * <p>
 * Multi-variate tests are either one-tailed or two-tailed. A two-tailed
 * test should be used if we want to know if a statistical parameter of one
 * distribution is different from a statistical parameter of the other. If
 * we want to either know if a parameter is bigger exclusive-or smaller
 * than the parameter of the other, the test can be one-tailed.&nbsp;[<a
 * href="#cite_SE20061STITIAAC" style="font-weight:bold">1</a>]
 * <p>
 * An instance of this class may have a state (e.g., temporary variables),
 * so tests are not thread safe. You may thus want to {@link #clone()} them
 * if using multiple threads.
 * </p>
 * <h2>References</h2>
 * <ol>
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
public abstract class MultivariateTest extends NamedObject {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * instantiate
   * 
   * @param name
   *          the test name
   */
  protected MultivariateTest(final String name) {
    super(name);
  }

  /**
   * Return {@code true} if and only if the test is two tailed,
   * {@code false} for one-tailed tests
   * 
   * @return {@code true} if and only if the test is two tailed,
   *         {@code false} for one-tailed tests
   */
  public abstract boolean isTwoTailed();

  /**
   * Compare the a set of data collections in terms of dimension
   * {@code dim}
   * 
   * @param dataSets
   *          the data sets
   * @param dim
   *          the dimension to be compared
   * @param dest
   *          the result record to override, or guaranteed to not be
   *          {@code null}
   */
  protected void doTest(final MultivariateTestResult dest, final int dim,
      final IDataCollection... dataSets) {
    //
  }

  /**
   * Initialize the destination
   * 
   * @param dest
   *          the destination
   * @param n
   *          the dimension
   * @return the result
   */
  private final MultivariateTestResult __init(
      final MultivariateTestResult dest, final int n) {
    if (dest == null) {
      return this.createResult(n);
    }

    if (dest.isTwoTailed() != this.isTwoTailed()) {
      throw new IllegalArgumentException(//
          "Tailedness of result must be same as tailed-ness of test."); //$NON-NLS-1$
    }

    if (dest.n() != n) {
      throw new IllegalArgumentException(//
          ((("Dimension must be same, but are " + dest.n()) + ',') + n)); //$NON-NLS-1$          
    }

    dest.clear();
    return dest;
  }

  /**
   * compare the a set of data collections in terms of dimension
   * {@code dim}
   * 
   * @param dataSets
   *          the data sets
   * @param dim
   *          the dimension to be compared
   * @param dest
   *          the result record to override, or {@code null} to create a
   *          new one
   * @return the result
   */
  public MultivariateTestResult test(final MultivariateTestResult dest,
      final int dim, final IDataCollection... dataSets) {
    final MultivariateTestResult r;
    final int n;

    if (dim < 0) {
      throw new IllegalArgumentException(//
          "Dimension cannot be less than zero, but is " + dim); //$NON-NLS-1$
    }

    n = dataSets.length;
    if (n <= 1) {
      return (this.isTwoTailed() ? _EmptyTestResult.TT
          : _EmptyTestResult.OT);
    }

    r = this.__init(dest, n);
    this.doTest(r, dim, dataSets);
    return r;
  }

  /**
   * Set the test result
   * 
   * @param i
   *          the first compared distribution
   * @param j
   *          the second compared distribution
   * @param dest
   *          the destination
   * @param compRes
   *          the comparison result
   * @param errorProb
   *          the error probability
   */
  protected static final void setResult(final MultivariateTestResult dest,
      final int i, final int j, final int compRes, final double errorProb) {
    if ((errorProb < 0d) || (errorProb > 1d)) {
      throw new IllegalArgumentException(//
          "Error probability must be in [0,1], but is " + errorProb); //$NON-NLS-1$
    }
    if (i == j) {
      return;
    }
    dest.setResult(i, j, compRes, errorProb);
  }

  /**
   * Create a result record
   * 
   * @param n
   *          the number of compared data sets
   * @return the result
   */
  protected MultivariateTestResult createResult(final int n) {
    if (n <= 1) {
      return (this.isTwoTailed() ? _EmptyTestResult.TT
          : _EmptyTestResult.OT);
    }
    if (this.isTwoTailed()) {
      return ((n == 2) ? new BivariateTwoTailedTestResult()
          : new MultivariateTwoTailedTestResult(n));
    }
    throw new UnsupportedOperationException();
  }

  /** {@inheritDoc} */
  @Override
  public MultivariateTest clone() {
    return ((MultivariateTest) (super.clone()));
  }
}
