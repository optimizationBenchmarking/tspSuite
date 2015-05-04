package org.logisticPlanning.tsp.evaluation.modules.impl.comparison.ert;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.logisticPlanning.tsp.benchmarking.instances.Instance;
import org.logisticPlanning.tsp.evaluation.Evaluator;
import org.logisticPlanning.tsp.evaluation.data.Accessor;
import org.logisticPlanning.tsp.evaluation.data.Experiment;
import org.logisticPlanning.tsp.evaluation.data.ExperimentSet;
import org.logisticPlanning.tsp.evaluation.data.properties.auc.AreaUnderCurve;
import org.logisticPlanning.tsp.evaluation.data.properties.auc.AreaUnderCurveComparator;
import org.logisticPlanning.tsp.evaluation.data.properties.instance.SharedInstancesProperty;
import org.logisticPlanning.tsp.evaluation.data.properties.ranking.RankAggregate;
import org.logisticPlanning.tsp.evaluation.data.properties.ranking.Ranking;
import org.logisticPlanning.tsp.evaluation.modules.ModuleUtils;
import org.logisticPlanning.tsp.evaluation.modules.impl.descriptions.eval.DescAUC;
import org.logisticPlanning.tsp.evaluation.modules.impl.descriptions.tsp.DescTimeMeasures;
import org.logisticPlanning.tsp.evaluation.modules.impl.macros.Macros;
import org.logisticPlanning.tsp.evaluation.modules.impl.single.ert.ERTUtils;
import org.logisticPlanning.tsp.evaluation.modules.spec.Module;
import org.logisticPlanning.utils.collections.basic.BasicMapEntry;
import org.logisticPlanning.utils.collections.lists.ArraySetView;
import org.logisticPlanning.utils.document.spec.Document;
import org.logisticPlanning.utils.document.spec.DocumentDimensions;
import org.logisticPlanning.utils.document.spec.EDefaultFigureSize;
import org.logisticPlanning.utils.document.spec.EMathOp;
import org.logisticPlanning.utils.document.spec.FigureBody;
import org.logisticPlanning.utils.document.spec.FigureSeries;
import org.logisticPlanning.utils.document.spec.FigureSeriesCaption;
import org.logisticPlanning.utils.document.spec.Graphic;
import org.logisticPlanning.utils.document.spec.Header;
import org.logisticPlanning.utils.document.spec.InlineMath;
import org.logisticPlanning.utils.document.spec.Label;
import org.logisticPlanning.utils.document.spec.MacroInvocation;
import org.logisticPlanning.utils.document.spec.MacroParameter;
import org.logisticPlanning.utils.document.spec.MathOp;
import org.logisticPlanning.utils.document.spec.MathOpParam;
import org.logisticPlanning.utils.document.spec.SectionBody;
import org.logisticPlanning.utils.document.spec.SectionTitle;
import org.logisticPlanning.utils.document.spec.SubFigure;
import org.logisticPlanning.utils.document.spec.SubFigureCaption;
import org.logisticPlanning.utils.graphics.DoubleDimension;
import org.logisticPlanning.utils.graphics.chart.spec.ChartDriver;
import org.logisticPlanning.utils.graphics.chart.spec.ELegendType;
import org.logisticPlanning.utils.graphics.chart.spec.ELineMode;
import org.logisticPlanning.utils.graphics.chart.spec.Line2D;
import org.logisticPlanning.utils.graphics.chart.spec.LineChart2D;
import org.logisticPlanning.utils.math.data.collection.IDataCollection;
import org.logisticPlanning.utils.math.data.iteration.DataCollectionIterator2D;
import org.logisticPlanning.utils.math.data.iteration.StairsKeepLeftIterator2D;
import org.logisticPlanning.utils.math.statistics.EStatisticParameter;
import org.logisticPlanning.utils.utils.EmptyUtils;
import org.logisticPlanning.utils.utils.comparison.ComparisonUtils;

/**
 * <p>
 * We plot the Estimated Running Time (ERT) to success&nbsp;[<a
 * href="#cite_HAFR2012RPBBOBES" style="font-weight:bold">1</a>, <a
 * href="#cite_AH2005PEOAALSEA" style="font-weight:bold">2</a>, <a
 * href="#cite_P1997DEVTFOT2I" style="font-weight:bold">3</a>] over the
 * problem scale. See the documentation of the class
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
final class _ExperimentSetERTOverScaleDiagram extends
_ExperimentSetERTDiagramsBase {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the caption 1 */
  private static final String CAP_1 = //
      "The Estimated Running Time to success for several different relative goal objective values "; //$NON-NLS-1$
  /** the caption 2 */
  private static final String CAP_2 = //
      ". The x-axes are the logarithms of the problem scales "; //$NON-NLS-1$
  /** the caption 3 */
  private static final String CAP_3A = //
      "And the y-axes are the logarithms of the scaled "; //$NON-NLS-1$
  /** the caption 3 */
  private static final String CAP_3B = //
      "And the y-axes are the logarithms of the "; //$NON-NLS-1$
  /** the caption 4 */
  private static final String CAP_4 = //
      ", cut off at 0"; //$NON-NLS-1$

  /** the sub caption 1 */
  private static final String SCAP = //
      " for "; //$NON-NLS-1$

  /** the x-axis caption */
  private static final String X_AXIS = ("log of problem scale " + //$NON-NLS-1$
      Macros.SCALE.getPlaceholder());

  /** the goal values */
  private static final double[] GOALS = { 0.0d, 0.01d, 0.02d, 0.05d, 0.1d };

  /** the time measures description */
  private final DescTimeMeasures m_descTime;

  /** the auc description */
  private final DescAUC m_descAuc;

  /** the accessor */
  private final Accessor m_axs;

  /**
   * create!
   *
   * @param owner
   *          the macro's owner
   * @param axs
   *          the accessor
   * @param isActive
   *          should this module be active?
   */
  _ExperimentSetERTOverScaleDiagram(final Module owner,
      final Accessor axs, final boolean isActive) {
    super(axs.getShortName() + "OverScale", owner, isActive); //$NON-NLS-1$
    this.m_size = EDefaultFigureSize.TWO_IN_A_ROW;

    this.m_descTime = this.getRoot().findInstance(DescTimeMeasures.class);
    this.addDependency(this.m_descTime);

    this.m_descAuc = this.getRoot().findInstance(DescAUC.class);
    this.addDependency(this.m_descAuc);

    this.m_axs = axs;
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
    IDataCollection c;

    for (final Map.Entry<Experiment, Object> e : lst) {
      c = ((IDataCollection) (e.getValue()));
      e.setValue(new AreaUnderCurve(new StairsKeepLeftIterator2D(//
          new DataCollectionIterator2D(c))));
    }

    agg.aggregateRanks(//
        Ranking.rank(AreaUnderCurveComparator.SMALLER_IS_BETTER,//
            lst.toArray(new Map.Entry[lst.size()])));
  }

  /** {@inheritDoc} */
  @Override
  protected final void initialize(final Header header,
      final ExperimentSet data) throws IOException {//
    super.initialize(header, data);
    this.m_axs.define(header);
    Macros.SCALE.define(header);
    Macros.F_THRESHOLD_RELATIVE.define(header);
    Macros.ERT.define(header);
    Macros.ERTi.define(header);
  }

  /** {@inheritDoc} */
  @Override
  protected final void writeSectionTitle(final SectionTitle title,
      final ExperimentSet data) throws IOException {
    try (MacroInvocation mi = title.macroInvocation(Macros.ERTi)) {
      try (MacroParameter p = mi.macroParameter()) {
        this.m_axs.writeShortName(p);
      }
    }
    title.write(" over Problem Scale"); //$NON-NLS-1$
  }

  /**
   * Make the figure
   *
   * @param instances
   *          the instances
   * @param data
   *          the data
   * @param goal
   *          the goal
   * @param lines
   *          the list to receive the lines
   * @param doc
   *          the document
   * @param areaLines
   *          the list of lines to be used for AUC computation
   * @return {@code true} if some meaningful figure was created,
   *         {@code false} otherwise
   */
  private final boolean __makeFigure(
      final ArraySetView<Instance> instances, final ExperimentSet data,
      final double goal, final ArrayList<Line2D> lines,
      final Document doc,
      final ArrayList<Map.Entry<Experiment, Object>> areaLines) {
    int j;
    IDataCollection col;
    double a, b, c, d;
    boolean hasData;

    lines.clear();

    hasData = false;
    for (final Experiment exp : data) {
      col = ERTUtils.medianErtOverScales(instances, exp, this.m_axs, goal,
          true, true, true, doc);
      if ((col == null) || ((j = col.size()) <= 0)) {
        throw new IllegalStateException();
      }

      j--;
      a = col.get(0, 0);
      b = col.get(j, 0);
      if (a > b) {
        throw new IllegalStateException();
      }

      areaLines.add(new BasicMapEntry<Experiment, Object>(exp, col));

      c = col.get(0, 1);
      d = col.get(j, 1);

      hasData |= ((a < b) && (c != d) && //
          (ComparisonUtils.isFinite(a) || ComparisonUtils.isFinite(b)) && //
          (ComparisonUtils.isFinite(c) || ComparisonUtils.isFinite(d)));

      lines.add(new Line2D(exp.shortName(), null, col, null,//
          ELineMode.DIRECT));
    }

    return hasData;
  }

  /** {@inheritDoc} */
  @SuppressWarnings("resource")
  @Override
  protected final void writeSectionBody(final SectionBody body,
      final ExperimentSet data) throws IOException {
    final ChartDriver drv;
    final Document doc;
    final Logger log;
    final ArraySetView<Instance> instances;
    final DocumentDimensions dims;
    final DoubleDimension dim;
    final ArrayList<Line2D> lines;
    final Label lbl;
    final Accessor axs;
    final ArrayList<Map.Entry<Experiment, Object>> aucLines;
    final RankAggregate<Experiment> rag;
    final Ranking<Experiment> ranking;
    boolean includeLegend, includeAxisLabels;
    double g;
    int fc;
    LineChart2D lc2d;
    String s;

    doc = body.getDocument();
    drv = doc.getOwner().getDriver().getChartDriver();
    if (drv == null) {
      s = ModuleUtils.WARNING_NO_CHART_DRIVER;
      body.write(s);
      log = this.getLogger();
      if ((log != null) && (log.isLoggable(Level.WARNING))) {
        log.warning(s);
      }
      return;
    }

    instances = SharedInstancesProperty.NON_PREMATURE_SHARED
        .get(data, doc);
    if ((instances == null) || (instances.size() <= 0)) {
      s = Evaluator.WARNING_NO_DATA;
      body.write(s);
      log = this.getLogger();
      if ((log != null) && (log.isLoggable(Level.WARNING))) {
        log.warning(s);
      }
      return;
    }

    dims = doc.getDimensions();
    dim = dims.getFigureDimensionsMM(this.m_size);
    lines = new ArrayList<>(data.size());
    includeAxisLabels = includeLegend = false;
    axs = this.m_axs;
    aucLines = new ArrayList<>(data.size());
    rag = new RankAggregate<>(data);

    if (dim.contains(dims.getFigureDimensionsMM(//
        EDefaultFigureSize.TWO_BY_TWO))) {
      includeAxisLabels = true;
      if (dim.contains(dims.getFigureDimensionsMM(//
          EDefaultFigureSize.COLUMN_WIDE))) {
        includeLegend = true;
      }
    }

    fc = 0;

    try (FigureSeries fs = body.figureSeries(Label.AUTO_LABEL,
        dim,//
        (_ExperimentSetERTOverScaleDiagram.CAP_1
            + Macros.F_THRESHOLD_RELATIVE.getPlaceholder()
            + _ExperimentSetERTOverScaleDiagram.CAP_2
            + Macros.SCALE.getPlaceholder()
            + (axs.isScaled() ? _ExperimentSetERTOverScaleDiagram.CAP_3A
                : _ExperimentSetERTOverScaleDiagram.CAP_3B)
                + axs.getShortName()
                + (axs.isTime() ? _ExperimentSetERTOverScaleDiagram.CAP_4
                    : EmptyUtils.EMPTY_STRING) + '.'),//
                    (Macros.ERT.getPlaceholder()
                        + _ExperimentSetERTOverScaleDiagram.SCAP
                        + Macros.F_THRESHOLD_RELATIVE.getPlaceholder() + "=999"), //$NON-NLS-1$
                        true)) {

      try (FigureSeriesCaption cap = fs.figureSeriesCaption()) {
        cap.write(_ExperimentSetERTOverScaleDiagram.CAP_1);
        cap.macroInvoke(Macros.F_THRESHOLD_RELATIVE);
        cap.write(_ExperimentSetERTOverScaleDiagram.CAP_2);
        cap.macroInvoke(Macros.SCALE);
        cap.write(axs.isScaled() ? _ExperimentSetERTOverScaleDiagram.CAP_3A
            : _ExperimentSetERTOverScaleDiagram.CAP_3B);
        axs.writeShortName(cap);
        if (axs.isTime()) {
          cap.write(_ExperimentSetERTOverScaleDiagram.CAP_4);
        }
        cap.writeChar('.');
      }

      outer: for (final double goal : _ExperimentSetERTOverScaleDiagram.GOALS) {

        if ((!(this.__makeFigure(instances, data, goal, lines, doc,
            aucLines))) || lines.isEmpty()) {
          continue outer;
        }

        if (lines.isEmpty()) {
          aucLines.clear();
          continue;
        }
        if (aucLines.size() == data.size()) {
          _ExperimentSetERTOverScaleDiagram._aggregate(aucLines, rag);
          aucLines.clear();
        }

        s = "max(0, log of "; //$NON-NLS-1$
        if (axs.isScaled()) {
          s += "scaled "; //$NON-NLS-1$
        }
        s += Macros.ERT.getPlaceholder();
        s += axs.getShortName();
        s += ')';

        if ((fc <= 0) && (!(includeLegend))) {
          try (SubFigure sf = fs.subFigure(null)) {
            try (SubFigureCaption cap = sf.subFigureCaption()) {
              cap.write("Legend."); //$NON-NLS-1$
            }
            try (FigureBody fb = sf.figureBody(
                this.makeURI(Module.GRAPHICS_FOLDER, Module.LEGEND), dim)) {
              try (Graphic graph = fb.graphic()) {
                lc2d = drv
                    .createLineChart2D(ERTUtils.DEFAULT_X_SCALE_AXIS_DEF);
                lc2d.setAxisTitleX(_ExperimentSetERTOverScaleDiagram.X_AXIS);
                lc2d.setAxisTitleY(s);
                lc2d.setLegendType(ELegendType.ONLY_LEGEND);
                lc2d.addLines(lines);
                lc2d.paint(graph);
              }
            } catch (final URISyntaxException use) {
              throw new IOException(use);
            }
          }
        }

        fc++;

        try (SubFigure sf = fs.subFigure(null)) {
          try (SubFigureCaption cap = sf.subFigureCaption()) {
            try (MacroInvocation mi = cap.macroInvocation(Macros.ERTi)) {
              try (MacroParameter p = mi.macroParameter()) {
                this.m_axs.writeShortName(p);
              }
            }
            cap.write(_ExperimentSetERTOverScaleDiagram.SCAP);
            try (InlineMath im = cap.inlineMath()) {
              try (MathOp eq = im.mathOp(EMathOp.CMP_EQUALS)) {
                try (MathOpParam eq1 = eq.mathOpParam()) {
                  eq1.macroInvoke(Macros.F_THRESHOLD_RELATIVE);
                }
                try (MathOpParam eq2 = eq.mathOpParam()) {
                  eq2.writeDouble(goal);
                }
              }
            }
          }
          try (FigureBody fb = sf.figureBody(
              this.makeURI(Module.GRAPHICS_FOLDER, null, null,
                  String.valueOf(goal)), dim)) {
            try (Graphic graph = fb.graphic()) {
              lc2d = drv
                  .createLineChart2D(ERTUtils.DEFAULT_X_SCALE_AXIS_DEF);

              lc2d.setLegendType(includeLegend ? ELegendType.PUT_LEGEND
                  : ELegendType.NO_LEGEND);
              if (includeAxisLabels) {
                lc2d.setAxisTitleX(_ExperimentSetERTOverScaleDiagram.X_AXIS);
                lc2d.setAxisTitleY(s);
              }
              lc2d.addLines(lines);
              lc2d.paint(graph);
            }
          } catch (final URISyntaxException use) {
            throw new IOException(use);
          }
        }
      }

      lbl = fs.getLabel();
    }

    body.write("In "); //$NON-NLS-1$
    body.reference(lbl);
    body.write(" we plot the estimated running time "); //$NON-NLS-1$
    try (MacroInvocation mi = body.macroInvocation(Macros.ERTi)) {
      try (MacroParameter p = mi.macroParameter()) {
        axs.writeShortName(p);
      }
    }

    body.write(", introduced in "); //$NON-NLS-1$
    body.reference(this.m_descERT.getLabel(data));
    body.write(", for different relative target objective values "); //$NON-NLS-1$
    body.macroInvoke(Macros.F_THRESHOLD_RELATIVE);
    body.write(" over the problem scale "); //$NON-NLS-1$
    body.macroInvoke(Macros.SCALE);
    body.write(" for all experiments. This allows us to compare how the runtime requirements for the different algorithms change when the problems get bigger. We can so how long (in terms of the "); //$NON-NLS-1$
    axs.writeLongName(body, true);
    body.write(" they need to reach, say, the global optimum ("); //$NON-NLS-1$
    try (InlineMath im = body.inlineMath()) {
      try (MathOp eq = im.mathOp(EMathOp.CMP_EQUALS)) {
        try (MathOpParam eq1 = eq.mathOpParam()) {
          eq1.macroInvoke(Macros.F_THRESHOLD_RELATIVE);
        }
        try (MathOpParam eq2 = eq.mathOpParam()) {
          eq2.writeDouble(0);
        }
      }
    }

    body.write(") or to get close enough to it to have an error of, say, no more than "); //$NON-NLS-1$
    g = _ExperimentSetERTOverScaleDiagram.GOALS[Math.max(1,
        (_ExperimentSetERTOverScaleDiagram.GOALS.length >>> 1))];
    fc = ((int) (0.5d + (100d * g)));
    body.writeInt(fc);
    body.write("% ("); //$NON-NLS-1$
    try (InlineMath im = body.inlineMath()) {
      try (MathOp eq = im.mathOp(EMathOp.CMP_EQUALS)) {
        try (MathOpParam eq1 = eq.mathOpParam()) {
          eq1.macroInvoke(Macros.F_THRESHOLD_RELATIVE);
        }
        try (MathOpParam eq2 = eq.mathOpParam()) {
          eq2.writeDouble(g);
        }
      }
    }
    body.write(//
        "). If data for multiple benchmark cases for the same scale exist, the median "); //$NON-NLS-1$
    try (MacroInvocation mi = body.macroInvocation(Macros.ERTi)) {
      try (MacroParameter p = mi.macroParameter()) {
        axs.writeShortName(p);
      }
    }
    body.write(" is plotted."); //$NON-NLS-1$

    body.writeLinebreak();

    body.write("The x-axes in the sub-figures of "); //$NON-NLS-1$
    body.reference(lbl);
    body.write(" represent the problem scale "); //$NON-NLS-1$
    body.macroInvoke(Macros.SCALE);
    body.write(" and are logarithmically scaled (base "); //$NON-NLS-1$
    body.writeInt(10);
    body.write("). The y-axes represent the "); //$NON-NLS-1$
    try (MacroInvocation mi = body.macroInvocation(Macros.ERTi)) {
      try (MacroParameter p = mi.macroParameter()) {
        axs.writeShortName(p);
      }
    }
    body.write(", i.e., the estimated running time estimated in "); //$NON-NLS-1$
    axs.writeShortName(body, true);
    if (axs.isScaled()) {
      body.write(" and scaled by "); //$NON-NLS-1$
      axs.writeScale(body);
    }
    body.write(//
        ". We plot the logarithms of these values in order to make the diagrams readable. This has the disadvantage that values such as "); //$NON-NLS-1$
    body.writeInt(0);
    body.write(" cannot be plotted. We therefore choose to represent all values less than "); //$NON-NLS-1$
    body.writeInt(1);
    body.write(" as "); //$NON-NLS-1$
    body.writeInt(0);
    body.write(" in the diagrams. This results in the drawback is that values between "); //$NON-NLS-1$
    body.writeInt(0);
    body.write(" and "); //$NON-NLS-1$
    body.writeInt(1);
    body.write(" will now also be represented as "); //$NON-NLS-1$
    body.writeInt(0);
    body.write(//
        ", which makes the diagrams potentially misleading for small time values / small-scale benchmark cases."); //$NON-NLS-1$

    body.writeLinebreak();
    ranking = rag.rank(EStatisticParameter.MEDIAN);
    body.write(//
        "We now rank the methods according to their Area Under the Curve ("); //$NON-NLS-1$
    body.macroInvoke(Macros.AUC);
    body.write(", ");//$NON-NLS-1$
    body.reference(this.m_descAuc.getLabel(data));
    body.write(//
        "). Of course, smaller areas are better and we use the same scaling as in the diagrams. We then re-ranking the methods according to their median rank over all diagrams, as discussed in "); //$NON-NLS-1$
    body.reference(this.m_descRanking.getLabel(data));
    body.write(". From this, ");//$NON-NLS-1$
    this.summarizeRanks(ranking, body, "discover good solutions faster"); //$NON-NLS-1$
    this.propagateRanking(ranking, data, doc);
  }
}
