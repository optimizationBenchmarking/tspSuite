package org.logisticPlanning.tsp.evaluation.data.properties.auc;

import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;

import org.logisticPlanning.utils.document.spec.AbstractInlineElement;
import org.logisticPlanning.utils.document.spec.AbstractMathElement;
import org.logisticPlanning.utils.document.spec.AbstractTextComplex;
import org.logisticPlanning.utils.document.spec.EMathOp;
import org.logisticPlanning.utils.document.spec.InlineMath;
import org.logisticPlanning.utils.document.spec.MathOp;
import org.logisticPlanning.utils.document.spec.MathOpParam;
import org.logisticPlanning.utils.document.spec.NormalText;
import org.logisticPlanning.utils.math.data.point.Point;
import org.logisticPlanning.utils.math.statistics.aggregates.StableProduct;
import org.logisticPlanning.utils.math.statistics.aggregates.StableSum;
import org.logisticPlanning.utils.utils.HashUtils;
import org.logisticPlanning.utils.utils.comparison.ComparisonUtils;

/**
 * A class which represents an Area Under the Curve (AUC), which we use for
 * comparing graphics. This measure stems from Machine Learning&nbsp;[<a
 * href="#cite_F2006AITRA" style="font-weight:bold">1</a>, <a
 * href="#cite_B1997TUOTAUTRCITEOMLA" style="font-weight:bold">2</a>], but
 * may be useful here too, with some modifications. See the documentation
 * of the class
 * {@link org.logisticPlanning.tsp.evaluation.modules.impl.descriptions.eval.DescAUC
 * DescAUC} for more information.<h2>References</h2>
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
public class AreaUnderCurve implements Serializable {
  /** The serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * the length along the x-axis over which the area of the curve is
   * negative infinity
   */
  final double m_negInfinite;
  /**
   * the length along the x-axis over which the area of the curve is
   * positive infinity
   */
  final double m_posInfinite;
  /**
   * the length along the x-axis over which the area of the curve is not
   * defined or transcends from negative to positive infinity (or vice
   * versa)
   */
  final double m_nan;
  /** the finite area under the curve */
  final double m_finite;

  /**
   * create a constant area
   *
   * @param finite
   *          the finite area under the curve
   * @param negInf
   *          the length along the x-axis over which the area of the curve
   *          is negative infinity
   * @param posInf
   *          tthe length along the x-axis over which the area of the curve
   *          is positive infinity
   * @param nan
   *          the length along the x-axis over which the area of the curve
   *          is not defined or transcends from negative to positive
   *          infinity (or vice versa)
   */
  public AreaUnderCurve(final double finite, final double negInf,
      final double posInf, final double nan) {
    super();
    this.m_finite = finite;
    this.m_negInfinite = negInf;
    this.m_posInfinite = posInf;
    this.m_nan = nan;
  }

  /**
   * Create the area under the curve
   *
   * @param iterator
   *          the point iterator
   */
  public AreaUnderCurve(final Iterator<? extends Point> iterator) {
    super();

    final StableSum ninf, nan, pinf, area, dx, dy;
    final StableProduct sp;
    double lastX, lastY, curX, curY, dX, dY;
    Point p;

    ninf = new StableSum();
    nan = new StableSum();
    pinf = new StableSum();
    area = new StableSum();
    dx = new StableSum();
    dy = new StableSum();
    sp = new StableProduct();

    if (iterator.hasNext()) {
      p = iterator.next();

      curX = p.get(0);
      curY = p.get(1);

      while (iterator.hasNext()) {
        p = iterator.next();

        lastX = curX;
        lastY = curY;
        curX = p.get(0);
        curY = p.get(1);

        if (lastX < curX) {

          // using the stable sum here deals with overflows
          dx.reset();
          dx.visitDouble(curX);
          dx.visitDouble(-lastX);
          dX = dx.getResult();

          if (curY != lastY) {
            // using the stable sum here deals with overflows
            dy.reset();
            dy.visitDouble(0.5d * curY);
            dy.visitDouble(0.5d * lastY);
            dY = dy.getResult();
          } else {
            dY = curY;
          }

          if (dY == 0d) {
            continue;
          }

          if (dY != dY) {
            nan.visitDouble(dX);
            continue;
          }

          if (dY <= Double.NEGATIVE_INFINITY) {
            ninf.visitDouble(dX);
            continue;
          }

          if (dY >= Double.POSITIVE_INFINITY) {
            pinf.visitDouble(dX);
            continue;
          }

          sp.reset();
          sp.visitDouble(dX);
          sp.visitDouble(dY);
          area.visitDouble(sp.getResult());
          continue;
        }

        // lastX <= curX
        if (lastX > curX) {
          throw new IllegalStateException(//
              "Last x cannot be greater than current x, but " + //$NON-NLS-1$
              curX + " follows " + lastX);//$NON-NLS-1$
        }
      }
    }

    this.m_negInfinite = ninf.getResult();
    this.m_nan = nan.getResult();
    this.m_posInfinite = pinf.getResult();
    this.m_finite = area.getResult();
  }

  /**
   * Get the length along the x-axis where the y-values are
   * {@link java.lang.Double#NEGATIVE_INFINITY}.
   *
   * @return the length along the x-axis where the y-values are
   *         {@link java.lang.Double#NEGATIVE_INFINITY}.
   */
  public final double getNegativeInfiniteLength() {
    return this.m_negInfinite;
  }

  /**
   * Get the length along the x-axis where the y-values are
   * {@link java.lang.Double#POSITIVE_INFINITY}.
   *
   * @return the length along the x-axis where the y-values are
   *         {@link java.lang.Double#POSITIVE_INFINITY}.
   */
  public final double getPositiveInfiniteLength() {
    return this.m_posInfinite;
  }

  /**
   * Get the length along the x-axis where the y-values are
   * {@link java.lang.Double#NaN}.
   *
   * @return the length along the x-axis where the y-values are
   *         {@link java.lang.Double#NaN}.
   */
  public final double getNaNLength() {
    return this.m_nan;
  }

  /**
   * Get the finite area, i.e., the area where y coordinates are finite
   *
   * @return the finite area, i.e., the area where y coordinates are finite
   */
  public final double getFiniteArea() {
    return this.m_finite;
  }

  /** {@inheritDoc} */
  @Override
  public final boolean equals(final Object o) {
    final AreaUnderCurve auc;
    if (o == this) {
      return true;
    }
    if (o instanceof AreaUnderCurve) {
      auc = ((AreaUnderCurve) o);

      if (ComparisonUtils.equals(this.m_finite, auc.m_finite) && //
          ComparisonUtils.equals(this.m_nan, auc.m_nan) && //
          ComparisonUtils.equals(this.m_negInfinite, auc.m_negInfinite) && //
          ComparisonUtils.equals(this.m_posInfinite, auc.m_posInfinite)) {
        return true;
      }
    }
    return false;
  }

  /** {@inheritDoc} */
  @Override
  public final int hashCode() {
    return HashUtils.combineHashes(//
        HashUtils.combineHashes(//
            HashUtils.hashCode(this.m_finite),//
            HashUtils.hashCode(this.m_nan)),//
            HashUtils.combineHashes(//
                HashUtils.hashCode(this.m_negInfinite),//
                HashUtils.hashCode(this.m_posInfinite)));
  }

  /**
   * Write the value of this AUC.
   *
   * @param im
   *          the inline math
   * @throws IOException
   *           if i/o fails
   */
  private final void __write(final AbstractMathElement im)
      throws IOException {
    try (MathOp tuple = im.mathOp(EMathOp.TUPLE)) {
      try (MathOpParam p = tuple.mathOpParam()) {
        p.writeDouble(this.m_finite);
      }

      if (this.m_nan != 0) {
        try (MathOpParam p = tuple.mathOpParam()) {
          p.writeDouble(Double.NaN);
          try (NormalText tn = p.normalText()) {
            tn.writeChar(':');
          }
          p.writeDouble(this.m_nan);
        }
      }

      if (this.m_negInfinite != 0) {
        try (MathOpParam p = tuple.mathOpParam()) {
          p.writeDouble(Double.NEGATIVE_INFINITY);
          try (NormalText tn = p.normalText()) {
            tn.writeChar(':');
          }
          p.writeDouble(this.m_negInfinite);
        }
      }

      if (this.m_posInfinite != 0) {
        try (MathOpParam p = tuple.mathOpParam()) {
          p.writeDouble(Double.POSITIVE_INFINITY);
          try (NormalText tn = p.normalText()) {
            tn.writeChar(':');
          }
          p.writeDouble(this.m_posInfinite);
        }
      }
    }
  }

  /**
   * Write the value of this AUC.
   *
   * @param dest
   *          the destination to write to
   * @throws IOException
   *           if i/o fails
   */
  public final void write(final AbstractInlineElement dest)
      throws IOException {
    if (dest instanceof AbstractTextComplex) {
      try (InlineMath im = ((AbstractTextComplex) dest).inlineMath()) {
        this.__write(im);
      }
    } else {
      if (dest instanceof AbstractMathElement) {
        this.__write((AbstractMathElement) dest);
      } else {
        throw new IllegalArgumentException(//
            dest.getClass().getCanonicalName() + " not supported."); //$NON-NLS-1$
      }
    }
  }

  /** {@inheritDoc} */
  @Override
  public final String toString() {
    final StringBuilder sb;

    sb = new StringBuilder(128);
    sb.append('(');

    sb.append(this.m_finite);

    if (this.m_nan != 0) {
      sb.append(", \u2205:");//$NON-NLS-1$
      sb.append(this.m_nan);
    }

    if (this.m_negInfinite != 0) {
      sb.append(", -\u221e:");//$NON-NLS-1$
      sb.append(this.m_negInfinite);
    }

    if (this.m_posInfinite != 0) {
      sb.append(", +\u221e:");//$NON-NLS-1$
      sb.append(this.m_posInfinite);
    }

    sb.append(')');

    return sb.toString();
  }
}
