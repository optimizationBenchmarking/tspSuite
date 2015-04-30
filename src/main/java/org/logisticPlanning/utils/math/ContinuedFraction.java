package org.logisticPlanning.utils.math;

/**
 * A continued fraction class inspired by <a
 * href="https://commons.apache.org/proper/commons-math/">The Apache
 * Commons Mathematics Library</a>&nbsp;[<a href="#cite_A2013CMTACML"
 * style="font-weight:bold">1</a>] and, thus, GPL licensed.<h2>References</h2>
 * <ol>
 * <li><div><span id="cite_A2013CMTACML" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;Commons Math: The
 * Apache Commons Mathematics Library,&rdquo;</span> (Website),
 * April&nbsp;7, 2013, Forest Hill, MD, USA: Apache Software Foundation.
 * <div>link: [<a
 * href="https://commons.apache.org/proper/commons-math/">1</
 * a>]</div></div></li>
 * </ol>
 */
public abstract class ContinuedFraction {

  /** instantiate */
  protected ContinuedFraction() {
    super();
  }

  /**
   * Access the n-th a coefficient of the continued fraction.
   * 
   * @param n
   *          the coefficient index to retrieve.
   * @param x
   *          the evaluation point.
   * @param a
   *          first additional parameter passed to the evaluation function
   * @param b
   *          second additional parameter passed to the evaluation function
   * @return the n-th a coefficient.
   */
  protected abstract double getA(final int n, final double x,
      final double a, final double b);

  /**
   * Access the n-th b coefficient of the continued fraction.
   * 
   * @param n
   *          the coefficient index to retrieve.
   * @param x
   *          the evaluation point.
   * @param a
   *          first additional parameter passed to the evaluation function
   * @param b
   *          second additional parameter passed to the evaluation function
   * @return the n-th b coefficient.
   */
  protected abstract double getB(final int n, final double x,
      final double a, final double b);

  /**
   * Evaluates the continued fraction at the value x.
   * 
   * @param x
   *          the evaluation point.
   * @param epsilon
   *          maximum error allowed.
   * @return the value of the continued fraction evaluated at x.
   */
  public final double evaluate(final double x, final double epsilon) {
    return this.evaluate(x, 0d, 0d, epsilon);
  }

  /**
   * Evaluates the continued fraction at the value x.
   * 
   * @param x
   *          the evaluation point.
   * @param a
   *          the first additional parameter of whatever nature
   * @param b
   *          the second additional parameter of whatever nature
   * @param epsilon
   *          maximum error allowed.
   * @return the value of the continued fraction evaluated at x.
   */
  public final double evaluate(final double x, final double a,
      final double b, final double epsilon) {
    double p0, p1, q0, q1, c, relativeError, vv, ww, p2, q2, r, scaleFactor, lastScaleFactor, scale;
    int n, i;
    boolean infinite;

    p0 = 1.0d;
    p1 = this.getA(0, x, a, b);
    q0 = 0.0d;
    q1 = 1.0d;
    c = (p1 / q1);
    n = 0;

    relativeError = Double.MAX_VALUE;

    while (relativeError > epsilon) {
      ++n;
      vv = this.getA(n, x, a, b);
      ww = this.getB(n, x, a, b);
      p2 = (vv * p1) + (ww * p0);
      q2 = (vv * q1) + (ww * q0);
      infinite = false;
      if (Double.isInfinite(p2) || Double.isInfinite(q2)) {
        scaleFactor = 1d;
        lastScaleFactor = 1d;

        scale = Math.max(vv, ww);
        if (scale <= 0) {
          return Double.NaN;
        }
        infinite = true;
        for (i = 0; i < 5; i++) {
          lastScaleFactor = scaleFactor;
          scaleFactor *= scale;
          if ((vv != 0.0) && (vv > ww)) {
            p2 = (p1 / lastScaleFactor) + ((ww / scaleFactor) * p0);
            q2 = (q1 / lastScaleFactor) + ((ww / scaleFactor) * q0);
          } else
            if (ww != 0) {
              p2 = ((vv / scaleFactor) * p1) + (p0 / lastScaleFactor);
              q2 = ((vv / scaleFactor) * q1) + (q0 / lastScaleFactor);
            }
          infinite = Double.isInfinite(p2) || Double.isInfinite(q2);
          if (!infinite) {
            break;
          }
        }
      }

      if (infinite) {
        return Double.NaN;
      }

      r = (p2 / q2);

      if (Double.isNaN(r)) {
        return Double.NaN;
      }
      relativeError = Math.abs((r / c) - 1.0);

      c = (p2 / q2);
      p0 = p1;
      p1 = p2;
      q0 = q1;
      q1 = q2;
    }

    return c;
  }
}
