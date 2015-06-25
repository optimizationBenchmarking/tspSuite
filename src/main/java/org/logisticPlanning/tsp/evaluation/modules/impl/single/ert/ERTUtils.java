package org.logisticPlanning.tsp.evaluation.modules.impl.single.ert;

import java.util.Arrays;

import org.logisticPlanning.tsp.benchmarking.instances.Instance;
import org.logisticPlanning.tsp.evaluation.data.Accessor;
import org.logisticPlanning.tsp.evaluation.data.Experiment;
import org.logisticPlanning.tsp.evaluation.data.RunSet;
import org.logisticPlanning.tsp.evaluation.data.properties.StatisticRunProperty;
import org.logisticPlanning.tsp.evaluation.data.properties.ert.ERTSeriesProperty;
import org.logisticPlanning.utils.collections.lists.ArraySetView;
import org.logisticPlanning.utils.document.spec.Document;
import org.logisticPlanning.utils.graphics.chart.spec.range.AxisEnd;
import org.logisticPlanning.utils.graphics.chart.spec.range.AxisRange2DDef;
import org.logisticPlanning.utils.graphics.chart.spec.range.EValueSelector;
import org.logisticPlanning.utils.graphics.chart.spec.range.FixedAxisEnd;
import org.logisticPlanning.utils.graphics.chart.spec.range.MultipleOfAxisBigEnd;
import org.logisticPlanning.utils.graphics.chart.spec.range.MultipleOfAxisSmallEnd;
import org.logisticPlanning.utils.math.data.collection.ArrayDataCollectionView;
import org.logisticPlanning.utils.math.data.collection.IDataCollection;
import org.logisticPlanning.utils.math.data.point.Point2D;
import org.logisticPlanning.utils.math.statistics.Quantile;

/**
 * <p>
 * Some utility methods related to diagrams for the Estimated Running Time
 * (ERT) to success&nbsp;[<a href="#cite_HAFR2012RPBBOBES"
 * style="font-weight:bold">1</a>, <a href="#cite_AH2005PEOAALSEA"
 * style="font-weight:bold">2</a>, <a href="#cite_P1997DEVTFOT2I"
 * style="font-weight:bold">3</a>]. See the documentation of the class
 * {@link org.logisticPlanning.tsp.evaluation.modules.impl.descriptions.eval.DescERT
 * DescERT} for more information.
 * </p>
 * <h2>References</h2>
 * <ol>
 * <li><div><span id="cite_HAFR2012RPBBOBES" /><a
 * href="http://www.lri.fr/~hansen/">Nikolaus Hansen</a>, <a
 * href="http://www.lri.fr/~auger/">Anne Auger</a>, <a
 * href="http://www.researchgate.net/profile/Steffen_Finck/">Steffen
 * Finck</a>, and&nbsp;<a
 * href="https://tao.lri.fr/tiki-index.php?page=people">Raymond Ros</a>:
 * <span style="font-weight:bold">&ldquo;Real-Parameter Black-Box
 * Optimization Benchmarking: Experimental Setup,&rdquo;</span> <span
 * style="font-style:italic;font-family:cursive;">Technical Report</span>
 * March&nbsp;24, 2012; published by Orsay, France: Universit&#233; Paris
 * Sud, Institut National de Recherche en Informatique et en Automatique
 * (INRIA) Futurs, &#201;quipe TAO. <div>link: [<a href=
 * "http://coco.lri.fr/BBOB-downloads/download11.05/bbobdocexperiment.pdf"
 * >1</a>]</div></div></li>
 * <li><div><span id="cite_AH2005PEOAALSEA" /><a
 * href="http://www.lri.fr/~auger/">Anne Auger</a> and&nbsp;<a
 * href="http://www.lri.fr/~hansen/">Nikolaus Hansen</a>: <span
 * style="font-weight:bold">&ldquo;Performance Evaluation of an Advanced
 * Local Search Evolutionary Algorithm,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the IEEE
 * Congress on Evolutionary Computation (CEC'05)</span>,
 * September&nbsp;2&ndash;5, 2005, Edinburgh, Scotland, UK, pages
 * 1777&ndash;1784, <a
 * href="http://www.macs.hw.ac.uk/staff-directory/david-wolfe-corne.htm"
 * >David Wolfe Corne</a>, <a
 * href="http://cs.adelaide.edu.au/~zbyszek/">Zbigniew Michalewicz</a>, <a
 * href="http://sc.snu.ac.kr/~rim/index.html">Robert Ian McKay</a>, <a
 * href="http://www.cs.vu.nl/~gusz/">&#193;goston E. Eiben</a> aka.
 * Gusz/Guszti, <a
 * href="http://www.natural-selection.com/people_dfogel.html">David B.
 * Fogel</a>, Carlos M. Fonseca, <a
 * href="https://www.ads.tuwien.ac.at/raidl/">G&#252;nther R. Raidl</a>, <a
 * href="http://vlab.ee.nus.edu.sg/~kctan/">Kay Chen Tan</a>, and&nbsp;Ali
 * M. S. Zalzala, editors, Piscataway, NJ, USA: IEEE Computer Society.
 * ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0780393635">0-7803-9363-5</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780780393639">978-0-7803
 * -9363-9</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1109/CEC.2005.1554903">10.1109/CEC.2005
 * .1554903</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/62773008">62773008</a>; Google Book
 * ID:&nbsp;<a
 * href="http://books.google.com/books?id=-QIVAAAACAAJ">-QIVAAAACAAJ</a>;
 * INSPEC Accession Number:&nbsp;8715465. <div>link: [<a
 * href="http://www.lri.fr/~hansen/cec2005localcmaes.pdf">1</a>];
 * CiteSeer<sup>x</sup><sub style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href=
 * "http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.71.6248">10.1
 * .1.71 .6248</a></div></div></li>
 * <li><div><span id="cite_P1997DEVTFOT2I" />Kenneth V. Price: <span
 * style="font-weight:bold">&ldquo;Differential Evolution vs. the Functions
 * of the 2nd ICEO,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the IEEE
 * International Conference on Evolutionary Computation (CEC'97)</span>,
 * April&nbsp;13&ndash;16, 1997, Indianapolis, IN, USA, pages
 * 153&ndash;157, <a href="http://www.liacs.nl/~baeck/contact.htm">Thomas
 * B&#228;ck</a>, <a href="http://cs.adelaide.edu.au/~zbyszek/">Zbigniew
 * Michalewicz</a>, and&nbsp;<a href="http://www.cs.bham.ac.uk/~xin/">Xin
 * Yao</a> <span style="color:gray">[&#23002;&#26032;</span>], editors,
 * Piscataway, NJ, USA: IEEE Computer Society. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0780339495">0-7803-3949-5</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780780339491">978-0-7803
 * -3949-1</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1109/ICEC.1997.592287">10.1109/ICEC.1997
 * .592287</a>; Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=6JZVAAAAMAAJ">6JZVAAAAMAAJ</a>;
 * INSPEC Accession Number:&nbsp;5573043</div></li>
 * </ol>
 */
public final class ERTUtils {

  /** the axis range definition for default situations */
  public static final AxisRange2DDef DEFAULT_AXIS_DEF;
  /** the axis range definition for scale diagrams */
  public static final AxisRange2DDef DEFAULT_X_SCALE_AXIS_DEF;

  static {
    final AxisEnd zero, one, multi11s, multi11b, multi005s, multi005b;

    zero = new FixedAxisEnd(0d);
    one = new FixedAxisEnd(1d);
    multi11s = new MultipleOfAxisSmallEnd(1, EValueSelector.FINITE_POINT,
        1d);
    multi11b = new MultipleOfAxisBigEnd(1, EValueSelector.FINITE_POINT, 1d);
    multi005s = new MultipleOfAxisSmallEnd(0, EValueSelector.FINITE_POINT,
        0.5d);
    multi005b = new MultipleOfAxisBigEnd(0, EValueSelector.FINITE_POINT,
        0.5d);

    DEFAULT_AXIS_DEF = new AxisRange2DDef(zero, one, true, multi11s,
        multi11b, true);
    DEFAULT_X_SCALE_AXIS_DEF = new AxisRange2DDef(multi005s, multi005b,
        false, multi11s, multi11b, true);

  }

  /** the default maximum number of points collected in ert diagrams */
  public static final int DEFAULT_MAX_POINTS = StatisticRunProperty.DEFAULT_MAX_POINTS;

  /**
   * Make the default left end for lines in a progress diagram
   *
   * @return the default left end for lines in a progress diagram
   */
  public static final Point2D makeDefaultLeftLineEnd() {
    return new Point2D(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
  }

  /**
   * compute the protected logarithm
   *
   * @param d
   *          the d
   * @return the protected logarithm
   */
  private static final double __log(final double d) {
    if (d <= 0d) {
      return Double.NEGATIVE_INFINITY;
    }
    if (d >= Double.POSITIVE_INFINITY) {
      return Double.POSITIVE_INFINITY;
    }
    if (d != d) {
      throw new IllegalStateException();
    }
    return Math.log10(d);
  }

  /***
   * Get a data collection representing the progress in terms of ert over
   * scale
   *
   * @param instances
   *          the instances
   * @param runSets
   *          the run sets
   * @param axs
   *          the accessor to use
   * @param goal
   *          the goal relative error
   * @param logX
   *          should we take the log<sub>10</sub> of the x-coordinates
   *          (i.e., the problem dimensions?)
   * @param scaleY
   *          should we scale the y-coordinates (via
   *          {@link org.logisticPlanning.tsp.evaluation.data.Accessor#calculateScale(int)}
   *          )?
   * @param logY
   *          should we also take the logarithm along the y-axis? Since
   *          this would lead to {@link java.lang.Double#NEGATIVE_INFINITY}
   *          for 0 values, which would show up as missing points on
   *          diagrams, we cut off all results at 0. This in turn means 0
   *          runtime values and runtimes between 0 and 1 will all show up
   *          as zero..
   * @param doc
   *          a pointer to a document
   * @return the data collection
   */
  public static final IDataCollection medianErtOverScales(
      final ArraySetView<Instance> instances, final Experiment runSets,
      final Accessor axs, final double goal, final boolean logX,
      final boolean scaleY, final boolean logY, final Document doc) {
    final double[] data, median, ret;
    final ERTSeriesProperty prop;
    final int size;
    final boolean isYScaled;
    RunSet rs;
    int dataIdx, curN, lastN, medIdx;
    double d;

    size = instances.size();
    if (size <= 0) {
      return null;
    }

    prop = ERTSeriesProperty.forAccessor(axs);
    if (prop == null) {
      return null;
    }

    isYScaled = (scaleY && axs.isScaled());

    median = new double[size];
    data = new double[size << 1];
    medIdx = dataIdx = 0;
    curN = lastN = (-1);

    for (final Instance inst : instances) {
      rs = runSets.forInstance(inst);
      if (rs == null) {
        continue;
      }

      lastN = curN;
      curN = inst.n();

      if (lastN != curN) {
        if (medIdx > 0) {
          data[dataIdx++] = (logX ? ERTUtils.__log(lastN) : lastN);
          Arrays.sort(median, 0, medIdx);
          d = Quantile.getQuantile(median, 0, medIdx, 0.5d);
          if (isYScaled) {
            d /= axs.calculateScale(lastN);
          }
          if (logY) {
            d = ERTUtils.__log(d);
            if (d < 0d) {
              d = 0d;
            }
          }
          data[dataIdx++] = d;
          medIdx = 0;
        }
      }

      median[medIdx++] = prop.get(rs, doc).forBestRelF(goal).getERT();
    }

    // the last, remaining data
    if (medIdx > 0) {
      data[dataIdx++] = (logX ? ERTUtils.__log(lastN) : lastN);
      Arrays.sort(median, 0, medIdx);
      d = Quantile.getQuantile(median, 0, medIdx, 0.5d);
      if (isYScaled) {
        d /= axs.calculateScale(lastN);
      }
      if (logY) {
        d = ERTUtils.__log(d);
        if (d < 0d) {
          d = 0d;
        }
      }
      data[dataIdx++] = d;
    }

    if (dataIdx == data.length) {
      ret = data;
    } else {
      if (dataIdx <= 0) {
        return null;
      }
      ret = new double[dataIdx];
      System.arraycopy(data, 0, ret, 0, dataIdx);
    }

    return new ArrayDataCollectionView(ret, (dataIdx >>> 1), 2);
  }
}
