package org.logisticPlanning.tsp.evaluation.modules.impl.comparison.ert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.logisticPlanning.tsp.evaluation.data.Experiment;
import org.logisticPlanning.tsp.evaluation.data.ExperimentSet;
import org.logisticPlanning.tsp.evaluation.data.properties.auc.AreaUnderCurve;
import org.logisticPlanning.tsp.evaluation.data.properties.auc.AreaUnderCurveComparator;
import org.logisticPlanning.tsp.evaluation.data.properties.ert.ExperimentERTProperty;
import org.logisticPlanning.tsp.evaluation.data.properties.ranking.RankAggregate;
import org.logisticPlanning.tsp.evaluation.data.properties.ranking.Ranking;
import org.logisticPlanning.tsp.evaluation.modules.impl.descriptions.eval.DescAUC;
import org.logisticPlanning.tsp.evaluation.modules.impl.descriptions.tsp.DescTimeMeasures;
import org.logisticPlanning.tsp.evaluation.modules.impl.macros.Macros;
import org.logisticPlanning.tsp.evaluation.modules.spec.Module;
import org.logisticPlanning.utils.collections.basic.BasicMapEntry;
import org.logisticPlanning.utils.document.spec.Document;
import org.logisticPlanning.utils.document.spec.EDefaultFigureSize;
import org.logisticPlanning.utils.document.spec.Header;
import org.logisticPlanning.utils.graphics.chart.spec.ELineMode;
import org.logisticPlanning.utils.graphics.chart.spec.Line2D;
import org.logisticPlanning.utils.math.data.collection.IDataCollection;
import org.logisticPlanning.utils.math.data.collection.SubCollectionView;
import org.logisticPlanning.utils.math.data.collection.TransformedCollectionView;
import org.logisticPlanning.utils.math.data.iteration.DataCollectionIterator2D;
import org.logisticPlanning.utils.math.data.iteration.StairsKeepLeftIterator2D;
import org.logisticPlanning.utils.math.data.point.Point2D;
import org.logisticPlanning.utils.math.functions.UnaryFunction;
import org.logisticPlanning.utils.math.functions.arithmetic.Identity;
import org.logisticPlanning.utils.math.functions.arithmetic.Max;
import org.logisticPlanning.utils.math.functions.compound.ChainedBinary;
import org.logisticPlanning.utils.math.functions.compound.ChainedUnary;
import org.logisticPlanning.utils.math.functions.power.Lg;
import org.logisticPlanning.utils.math.statistics.series.StatisticSeries;
import org.logisticPlanning.utils.utils.comparison.ComparisonUtils;

/**
 * <p>
 * A base class for plotting the medians of Estimated Running Time (ERT) to
 * success&nbsp;[<a href="#cite_HAFR2012RPBBOBES"
 * style="font-weight:bold">1</a>, <a href="#cite_AH2005PEOAALSEA"
 * style="font-weight:bold">2</a>, <a href="#cite_P1997DEVTFOT2I"
 * style="font-weight:bold">3</a>] over the relative error aggregated over
 * all benchmark instances. See the documentation of the class
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
abstract class _ExperimentSetERTOverRelativeObjectiveValueDiagramBase
extends _ExperimentSetERTDiagramsBase {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the dimensions */
  private static final int[] DIMS = { StatisticSeries.DIM_X,
    StatisticSeries.DIM_Y_MEDIAN };

  /** the scaled version */
  private static final UnaryFunction[] SCALE = { Identity.INSTANCE,
    ChainedBinary.chain(0d,//
        ChainedUnary.chain(//
            ChainedBinary.chain(1d, Identity.INSTANCE, Max.INSTANCE),//
            Lg.INSTANCE),//
            Max.INSTANCE) };

  /** the caption 1 */
  static final String CAP_1 = //
      "Comparison of the algorithms in terms of the Estimated Running Time to success plotted over goal objective values. The x-axes are the relative target objective value thresholds "; //$NON-NLS-1$
  /** the caption 3 */
  static final String CAP_2 = //
      ". The y-axes are the logarithms of the medians of the scaled "; //$NON-NLS-1$

  /** the sub caption 1 */
  static final String SCAP_1 = "median "; //$NON-NLS-1$

  /** the sub caption 2 */
  static final String SCAP_2 = " scaled by "; //$NON-NLS-1$

  /** the x-axis caption */
  static final String X_AXIS = Macros.F_THRESHOLD_RELATIVE
      .getPlaceholder();

  /** the y-axis caption part 1 */
  static final String Y_AXIS_1 = "max{0, lg(med " + //$NON-NLS-1$
      Macros.ERT.getPlaceholder();
  /** the y-axis caption part 2 */
  static final String Y_AXIS_2 = ")}";//$NON-NLS-1$
  /** the time measures description */
  final DescTimeMeasures m_descTime;

  /** the auc description */
  final DescAUC m_descAuc;

  /**
   * create!
   *
   * @param name
   *          the name
   * @param owner
   *          the macro's owner
   * @param isActive
   *          should this module be active?
   */
  _ExperimentSetERTOverRelativeObjectiveValueDiagramBase(
      final String name, final Module owner, final boolean isActive) {
    super("OverRelativeObjectiveValue" + name, owner, isActive); //$NON-NLS-1$
    this.m_size = EDefaultFigureSize.TWO_IN_A_ROW;

    this.m_descTime = this.getRoot().findInstance(DescTimeMeasures.class);
    this.addDependency(this.m_descTime);

    this.m_descAuc = this.getRoot().findInstance(DescAUC.class);
    this.addDependency(this.m_descAuc);
  }

  /**
   * Aggregate the experiment performance based on the area under the
   * curve.
   *
   * @param lst
   *          the list of assignments of experiments to data collections
   * @param agg
   *          the aggregated ranking
   */
  @SuppressWarnings("unchecked")
  static final void _aggregate(
      final ArrayList<Map.Entry<Experiment, Object>> lst,
      final RankAggregate<Experiment> agg) {
    double right, left, x;
    int v;
    IDataCollection c;
    Point2D l, r;

    right = Double.NEGATIVE_INFINITY;
    left = Double.POSITIVE_INFINITY;
    for (final Map.Entry<Experiment, Object> e : lst) {
      c = ((IDataCollection) (e.getValue()));
      x = c.get((c.size() - 1), 0);
      if (x > right) {
        right = x;
      }
      x = c.get(0, 0);
      if (x < left) {
        left = x;
      }
    }

    for (final Map.Entry<Experiment, Object> e : lst) {
      c = ((IDataCollection) (e.getValue()));

      if (c.get(0, 0) > left) {
        l = new Point2D(left, Double.POSITIVE_INFINITY);
      } else {
        l = null;
      }

      v = (c.size() - 1);
      if (c.get(v, 0) < right) {
        r = new Point2D(right, c.get(v, 1));
      } else {
        r = null;
      }

      e.setValue(new AreaUnderCurve(new StairsKeepLeftIterator2D(//
          new DataCollectionIterator2D(l, c, r)//
          )));
    }

    agg.aggregateRanks(//
        Ranking.rank(AreaUnderCurveComparator.SMALLER_IS_BETTER,//
            lst.toArray(new Map.Entry[lst.size()])));
  }

  /** {@inheritDoc} */
  @Override
  protected void initialize(final Header header, final ExperimentSet data)
      throws IOException {//
    super.initialize(header, data);
    Macros.SCALE.define(header);
    Macros.F_THRESHOLD_RELATIVE.define(header);
    Macros.ERT.define(header);
    Macros.ERTi.define(header);
  }

  /**
   * Make the figure
   *
   * @param data
   *          the data
   * @param prop
   *          the property
   * @param lines
   *          the list to receive the lines
   * @param doc
   *          the document
   * @param areaLines
   *          the list of lines to be used for AUC computation
   * @return {@code true} if some meaningful figure was created,
   *         {@code false} otherwise
   */
  final boolean _makeFigure(final ExperimentSet data,
      final ExperimentERTProperty prop, final ArrayList<Line2D> lines,
      final Document doc,
      final ArrayList<Map.Entry<Experiment, Object>> areaLines) {
    int j;
    IDataCollection col;
    double a, b, c, d;
    boolean hasData;

    lines.clear();

    hasData = false;
    for (final Experiment exp : data) {
      col = prop.compute(exp, doc);
      if ((col == null) || ((j = col.size()) <= 0)) {
        throw new IllegalStateException();
      }

      j--;
      a = col.get(0, StatisticSeries.DIM_X);
      b = col.get(j, StatisticSeries.DIM_X);
      if (a > b) {
        throw new IllegalStateException();
      }

      col = new SubCollectionView(col,
          _ExperimentSetERTOverRelativeObjectiveValueDiagramBase.DIMS);

      areaLines.add(new BasicMapEntry<Experiment, Object>(exp, col));

      c = col.get(0, 1);
      d = col.get(j, 1);

      hasData |= ((a < b) && (c != d) && //
          (ComparisonUtils.isFinite(a) || ComparisonUtils.isFinite(b)) && //
          (ComparisonUtils.isFinite(c) || ComparisonUtils.isFinite(d)));

      col = new TransformedCollectionView(col,
          _ExperimentSetERTOverRelativeObjectiveValueDiagramBase.SCALE);

      lines.add(new Line2D(exp.shortName(), null, col, null,//
          ELineMode.STAIRS_KEEP_LEFT));
    }

    return hasData;
  }

}
