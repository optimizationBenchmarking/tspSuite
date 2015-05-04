package org.logisticPlanning.tsp.evaluation.data.properties.auc;

import org.logisticPlanning.utils.utils.comparison.EComparison;
import org.logisticPlanning.utils.utils.comparison.PreciseComparator;

/**
 * <p>
 * A comparator for collating two Areas Under the Curve (AUC). This measure
 * stems from Machine Learning&nbsp;[<a href="#cite_F2006AITRA"
 * style="font-weight:bold">1</a>, <a href="#cite_B1997TUOTAUTRCITEOMLA"
 * style="font-weight:bold">2</a>], but may be useful here too, with some
 * modifications. See the documentation of the class
 * {@link org.logisticPlanning.tsp.evaluation.modules.impl.descriptions.eval.DescAUC
 * DescAUC} for more information.
 * </p>
 * <p>
 * This comparator can be used for sorting. It has one semantical pitfall,
 * though: Some areas under the curves (AUCs) may not be directly
 * comparable at all. Say, one area has a range with some positive infinite
 * y-coordinates whereas another area encloses some negative infinite
 * y-coordinates. Even if smaller y-coordinates are better, both areas
 * would be of infinite size and thus, not comparable. In such cases,
 * {@code 0} is returned &ndash; but the meaning is not that both areas are
 * the same, they are just not comparable. Anyway, the sorting comparison
 * {@link #doPreciseCompare(AreaUnderCurve, AreaUnderCurve)} tries to come
 * up with a reasonable order for different area definitions. This
 * consistent result is accessible via
 * {@link org.logisticPlanning.utils.utils.comparison.PreciseComparator#preciseCompare(Object, Object)}
 * and returns an instance of instance of
 * {@link org.logisticPlanning.utils.utils.comparison.EComparison}.
 * </p>
 * <h2>References</h2>
 * <ol>
 * <li><div><span id="cite_F2006AITRA" /><a
 * href="http://home.comcast.net/~tom.fawcett/public_html/index.html">Tom
 * Fawcett</a>: <span style="font-weight:bold">&ldquo;An Introduction to
 * ROC Analysis,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Pattern Recognition
 * Letters</span> 27(8):861&ndash;874, June&nbsp;2006; published by
 * Amsterdam, The Netherlands: Elsevier Science Publishers B.V.
 * and&nbsp;Amsterdam, The Netherlands: North-Holland Scientific Publishers
 * Ltd.. doi:&nbsp;<a href="http://dx.doi.org/10.1016/j.patrec.2005.10.010"
 * >10.1016/j.patrec.2005.10.010</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/01678655">0167-8655</a></div></li>
 * <li><div><span id="cite_B1997TUOTAUTRCITEOMLA" /><a
 * href="http://itee.uq.edu.au/~bradley/">Andrew P. Bradley</a>: <span
 * style="font-weight:bold">&ldquo;The Use of the Area Under the ROC Curve
 * in the Evaluation of Machine Learning Algorithms,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Pattern
 * Recognition</span> 30(7):1145&ndash;1159, July&nbsp;1997; published by
 * Essex, UK: Elsevier Science Publishers B.V. and&nbsp;Oxford, UK:
 * Pergamon Press. doi:&nbsp;<a
 * href="http://dx.doi.org/10.1016/S0031-3203(96)00142-2"
 * >10.1016/S0031-3203(96) 00142-2</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/00313203">0031-3203</a></div></li>
 * </ol>
 */
public final class AreaUnderCurveComparator extends
PreciseComparator<AreaUnderCurve> {

  /** smaller areas are better */
  public static final AreaUnderCurveComparator SMALLER_IS_BETTER = //
      new AreaUnderCurveComparator(true);

  /** larger areas are better */
  public static final AreaUnderCurveComparator LARGER_IS_BETTER = //
      new AreaUnderCurveComparator(false);

  /** are smaller {@code y} values better or worse? */
  private final boolean m_smallerIsBetter;

  /**
   * The area under the curve comparator
   *
   * @param isSmallerBetter
   *          are smaller {@code y} better
   */
  private AreaUnderCurveComparator(final boolean isSmallerBetter) {
    super();
    this.m_smallerIsBetter = isSmallerBetter;
  }

  /**
   * Compare to areas under a curve in a semantically correct way.
   *
   * @param a
   *          the first area
   * @param b
   *          the second area
   * @return <ol>
   *         <li>
   *         {@link org.logisticPlanning.utils.utils.comparison.EComparison#EQUAL}
   *         if both areas are equal.</li>
   *         <li>
   *         {@link org.logisticPlanning.utils.utils.comparison.EComparison#GREATER}
   *         if area {@code a} is worse than area {@code b} under this
   *         comparison, i.e., if it should come after {@code b} in a
   *         sorted sequence.</li>
   *         <li>
   *         {@link org.logisticPlanning.utils.utils.comparison.EComparison#LESS}
   *         if area {@code a} is better than area {@code b} under this
   *         comparison, i.e., if it should come before {@code b} in a
   *         sorted sequence.</li>
   *         <li>
   *         {@link org.logisticPlanning.utils.utils.comparison.EComparison#NOT_EQUAL}
   *         if area {@code a} cannot be compared with area {@code b} under
   *         this comparison &ndash; neither is better or worse than the
   *         other.</li>
   *         <li>
   *         {@link org.logisticPlanning.utils.utils.comparison.EComparison#SAME}
   *         if and only if {@code a==b}.</li>
   *         </ol>
   */
  @Override
  protected final EComparison doPreciseCompare(final AreaUnderCurve a,
      final AreaUnderCurve b) {
    EComparison r1, r2;

    // whatever has more "NaN" is always worse
    r1 = AreaUnderCurveComparator.__cmpSpecial(a.m_nan, b.m_nan, true);
    if (r1 != EComparison.EQUAL) {
      return r1;
    }
    // only if the NaN areas are EXACTLY of the same size

    r1 = AreaUnderCurveComparator.__cmpSpecial(a.m_negInfinite,
        b.m_negInfinite, (!(this.m_smallerIsBetter)));
    r2 = AreaUnderCurveComparator.__cmpSpecial(a.m_posInfinite,
        b.m_posInfinite, this.m_smallerIsBetter);

    outer: switch (r1) {

      case LESS: {
        switch (r2) {
          case GREATER:
          case NOT_EQUAL: {
            return EComparison.NOT_EQUAL;
          }
          default: {
            return r1;
          }
        }
      }

      case EQUAL: {
        switch (r2) {
          case LESS: {
            return EComparison.LESS;
          }
          case GREATER: {
            return EComparison.GREATER;
          }
          case NOT_EQUAL: {
            return EComparison.NOT_EQUAL;
          }
          default: {// both areas are exactly equal
            break outer;
          }
        }
      }

      case GREATER: {
        switch (r2) {
          case LESS:
          case NOT_EQUAL: {
            return EComparison.NOT_EQUAL;
          }
          default: {
            return r1;
          }
        }
      }

      default: {
        return EComparison.NOT_EQUAL;// not equal
      }
    }

    return AreaUnderCurveComparator.__cmpSpecial(a.m_finite, b.m_finite,
        this.m_smallerIsBetter);
  }

  /**
   * compare two double values
   *
   * @param a
   *          the first value
   * @param b
   *          the second value
   * @param smallerIsBetter
   *          is smaller better?
   * @return the appropriate constant
   */
  private static final EComparison __cmpSpecial(final double a,
      final double b, final boolean smallerIsBetter) {

    if ((a != a) || (b != b)) {
      return EComparison.NOT_EQUAL;
    }

    if (a < b) {
      return (smallerIsBetter ? EComparison.LESS : EComparison.GREATER);
    }

    if (a > b) {
      return (smallerIsBetter ? EComparison.GREATER : EComparison.LESS);
    }

    if ((a <= Double.NEGATIVE_INFINITY) // b is also <= -inf
        || (a >= Double.POSITIVE_INFINITY)) { // b is also >= inf
      // the areas are either undefined or infinite
      return EComparison.NOT_EQUAL;
    }

    return EComparison.EQUAL;
  }
}
