package org.logisticPlanning.tsp.evaluation.modules.impl.comparison.ecdf;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.logisticPlanning.tsp.evaluation.Evaluator;
import org.logisticPlanning.tsp.evaluation.data.Accessor;
import org.logisticPlanning.tsp.evaluation.data.Experiment;
import org.logisticPlanning.tsp.evaluation.data.ExperimentSet;
import org.logisticPlanning.tsp.evaluation.data.properties.ecdf.ExperimentECDFProperty;
import org.logisticPlanning.tsp.evaluation.data.properties.ecdf.RunSetECDFProperty;
import org.logisticPlanning.tsp.evaluation.data.properties.instance.InstancesProperty;
import org.logisticPlanning.tsp.evaluation.data.properties.ranking.RankAggregate;
import org.logisticPlanning.tsp.evaluation.data.properties.scale.AllScaleInstances;
import org.logisticPlanning.tsp.evaluation.data.properties.scale.AllScaleInstancesProperty;
import org.logisticPlanning.tsp.evaluation.data.properties.scale.SameScaleInstances;
import org.logisticPlanning.tsp.evaluation.modules.ModuleUtils;
import org.logisticPlanning.tsp.evaluation.modules.impl.ExperimentSequence;
import org.logisticPlanning.tsp.evaluation.modules.impl.macros.Macros;
import org.logisticPlanning.tsp.evaluation.modules.impl.single.ecdf.ECDFUtils;
import org.logisticPlanning.tsp.evaluation.modules.spec.Module;
import org.logisticPlanning.utils.collections.basic.BasicMapEntry;
import org.logisticPlanning.utils.document.spec.Document;
import org.logisticPlanning.utils.document.spec.DocumentDimensions;
import org.logisticPlanning.utils.document.spec.EDefaultFigureSize;
import org.logisticPlanning.utils.document.spec.EMathOp;
import org.logisticPlanning.utils.document.spec.ESequenceType;
import org.logisticPlanning.utils.document.spec.FigureBody;
import org.logisticPlanning.utils.document.spec.FigureSeries;
import org.logisticPlanning.utils.document.spec.FigureSeriesCaption;
import org.logisticPlanning.utils.document.spec.Graphic;
import org.logisticPlanning.utils.document.spec.Header;
import org.logisticPlanning.utils.document.spec.InlineMath;
import org.logisticPlanning.utils.document.spec.Label;
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
import org.logisticPlanning.utils.math.data.collection.SubCollectionView;
import org.logisticPlanning.utils.math.data.collection.TransformedCollectionView;
import org.logisticPlanning.utils.math.functions.UnaryFunction;
import org.logisticPlanning.utils.math.functions.arithmetic.Identity;
import org.logisticPlanning.utils.math.functions.power.Lg;
import org.logisticPlanning.utils.math.statistics.series.StatisticSeries;

/**
 * <p>
 * This evaluator will print diagrams showing the Empirical (Cumulative)
 * Distribution Functions (ECDFs)&nbsp;[<a href="#cite_HAFR2012RPBBOBES"
 * style="font-weight:bold">1</a>, <a href="#cite_HS1998ELVAPAR"
 * style="font-weight:bold">2</a>] aggregated over benchmark sets that have
 * been grouped together in terms of their scale.
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
 * <li><div><span id="cite_HS1998ELVAPAR" /><a
 * href="http://www.cs.ubc.ca/~hoos/">Holger H. Hoos</a> and&nbsp;<a
 * href="http://iridia.ulb.ac.be/~stuetzle/">Thomas St&#252;tzle</a>: <span
 * style="font-weight:bold">&ldquo;Evaluating Las Vegas Algorithms &#8210;
 * Pitfalls and Remedies,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the 14th
 * Conference on Uncertainty in Artificial Intelligence (UAI'98)</span>,
 * July&nbsp;24&ndash;26, 1998, Madison, WI, USA, pages 238&ndash;245,
 * Gregory F. Cooper and&nbsp;Serafin Moral, editors, San Francisco, CA,
 * USA: Morgan Kaufmann Publishers Inc.. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/155860555X">1-55860-555-X</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9781558605558">978-1-55860
 * -555-8</a>. <div>link: [<a href=
 * "http://www.intellektik.informatik.tu-darmstadt.de/TR/1998/98-02.ps.Z"
 * >1</a>]</div></div></li>
 * </ol>
 */
final class _ExperimentSetECDFOverScaleDiagrams extends
    _ExperimentSetECDFBase {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the caption 1 */
  private static final String CAP_1 = "Comparison of the "; //$NON-NLS-1$

  /** the caption 2 */
  private static final String CAP_2 = " algorithms in terms of the mean ";//$NON-NLS-1$

  /** the caption 3 */
  private static final String CAP_3 = " over benchmark instances of the same scale (base "; //$NON-NLS-1$
  /** caption 4 */
  private static final String CAP_4 = ") grouped together, over the consumed "; //$NON-NLS-1$
  /** caption 5a */
  private static final String CAP_5A = " (with the "; //$NON-NLS-1$
  /** caption 5b */
  private static final String CAP_5B = " scaled by "; //$NON-NLS-1$
  /** caption 5c */
  private static final String CAP_5C = " before the grouping) "; //$NON-NLS-1$
  /** caption 6 */
  private static final String CAP_6 = " printed over a log-scaled x-axis."; //$NON-NLS-1$

  /** sub caption 1 */
  private static final String SUB_CAP_1 = " instances with "; //$NON-NLS-1$
  /** sub caption 2 */
  private static final String SUB_CAP_2 = " instance with "; //$NON-NLS-1$

  /** the property */
  private final RunSetECDFProperty m_source;

  /** the scales property */
  private final AllScaleInstancesProperty<ExperimentSet> m_scales;

  /**
   * create!
   *
   * @param owner
   *          the macro's owner
   * @param isActive
   *          should this module be active?
   * @param source
   *          the source probability
   * @param scales
   *          the scales property
   */
  _ExperimentSetECDFOverScaleDiagrams(final Module owner,
      final RunSetECDFProperty source,
      final AllScaleInstancesProperty<ExperimentSet> scales,
      final boolean isActive) {
    super(("Scale" + scales.getBase() + //$NON-NLS-1$
        source.getAccessor().getShortName() + //
        "Goal" + source.getGoal()), owner, isActive); //$NON-NLS-1$
    this.m_scales = scales;
    this.m_source = source;
  }

  /** {@inheritDoc} */
  @Override
  protected final void initialize(final Header header,
      final ExperimentSet data) throws IOException {//
    super.initialize(header, data);
    this.m_scales.initialize(header);
    this.m_source.initialize(header);
    Macros.SCALE.define(header);
    Macros.ECDF.define(header);
  }

  /** {@inheritDoc} */
  @Override
  protected final void writeSectionTitle(final SectionTitle title,
      final ExperimentSet data) throws IOException {
    title.write("Mean "); //$NON-NLS-1$
    title.macroInvoke(Macros.ECDF);
    title.write(" in Terms of "); //$NON-NLS-1$
    this.m_source.getAccessor().writeShortName(title, false);
    title.write(" over Problem Scale "); //$NON-NLS-1$
    title.writeInt(this.m_scales.getBase());
  }

  /**
   * make a figure
   *
   * @param expset
   *          the experiment set
   * @param instances
   *          the instances
   * @param lines
   *          the list for the lines
   * @param doc
   *          the document
   * @param areaLines
   *          the list of lines to be used for AUC computation
   * @throws IOException
   *           if io fails
   */
  private final void __makeFigure(final ExperimentSet expset,
      final SameScaleInstances instances, final Document doc,
      final ArrayList<Line2D> lines,
      final ArrayList<Map.Entry<Experiment, Object>> areaLines)
      throws IOException {
    final int[] select;
    final UnaryFunction[] transform;
    final ExperimentECDFProperty prop;
    IDataCollection coll;

    lines.clear();

    transform = new UnaryFunction[] { Lg.INSTANCE, Identity.INSTANCE };
    select = new int[] { StatisticSeries.DIM_X, StatisticSeries.DIM_Y_MEAN };
    prop = new ExperimentECDFProperty(this.m_source,
        new InstancesProperty(instances));

    for (final Experiment exp : expset) {
      coll = prop.get(exp, doc);

      coll = new TransformedCollectionView(new SubCollectionView(//
          coll, select), transform);
      lines.add(new Line2D(exp.shortName(),//
          null, coll, null, ELineMode.STAIRS_KEEP_LEFT));
      areaLines.add(new BasicMapEntry<Experiment, Object>(exp, coll));
    }
  }

  /** {@inheritDoc} */
  @SuppressWarnings("resource")
  @Override
  protected final void writeSectionBody(final SectionBody body,
      final ExperimentSet data) throws IOException {
    final ChartDriver drv;
    final Document doc;
    final Logger log;
    final DoubleDimension dim;
    final DocumentDimensions dims;
    final Label l;
    final ArrayList<Line2D> lines;
    final ArrayList<Map.Entry<Experiment, Object>> aucLines;
    final RankAggregate<Experiment> rag;
    final AllScaleInstances instances;
    boolean includeAxisLabels, includeLegend;
    final Accessor axs;
    final int base;
    LineChart2D chart;
    String s;
    int fc, size;

    doc = body.getDocument();

    instances = this.m_scales.get(data, doc);
    if ((instances == null) || (instances.isEmpty())) {
      s = Evaluator.WARNING_NO_DATA;
      body.write(s);
      log = this.getLogger();
      if ((log != null) && (log.isLoggable(Level.WARNING))) {
        log.warning(s);
      }
      return;
    }

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

    axs = this.m_source.getAccessor();
    base = this.m_scales.getBase();
    dims = doc.getDimensions();
    dim = dims.getFigureDimensionsMM(this.m_size);

    includeAxisLabels = includeLegend = false;
    if (dim.contains(dims.getFigureDimensionsMM(//
        EDefaultFigureSize.TWO_IN_A_ROW))) {
      includeAxisLabels = true;
      includeLegend = true;
    }

    fc = 0;
    try (FigureSeries fs = body
        .figureSeries(Label.AUTO_LABEL,
            dim,//
            (_ExperimentSetECDFOverScaleDiagrams.CAP_1
                + 100
                + _ExperimentSetECDFOverScaleDiagrams.CAP_2
                + Macros.ECDF.getPlaceholder()
                + _ExperimentSetECDFOverScaleDiagrams.CAP_3
                + base
                + _ExperimentSetECDFOverScaleDiagrams.CAP_4
                + axs.getShortName()
                + 's'
                + (axs.isScaled() ? (_ExperimentSetECDFOverScaleDiagrams.CAP_5A
                    + axs.getShortName()
                    + _ExperimentSetECDFOverScaleDiagrams.CAP_5B
                    + axs.getScaleString() + _ExperimentSetECDFOverScaleDiagrams.CAP_5C)
                    : ("")) + _ExperimentSetECDFOverScaleDiagrams.CAP_6),//$NON-NLS-1$
            (_ExperimentSetECDFOverScaleDiagrams.SUB_CAP_1 + 100
                + _ExperimentSetECDFOverScaleDiagrams.SUB_CAP_2 + 100),//
            true)) {

      try (FigureSeriesCaption cap = fs.figureSeriesCaption()) {

        cap.write(_ExperimentSetECDFOverScaleDiagrams.CAP_1);
        cap.writeIntInText(data.size(), false);
        cap.write(_ExperimentSetECDFOverScaleDiagrams.CAP_2);
        cap.macroInvoke(Macros.ECDF);
        cap.write(_ExperimentSetECDFOverScaleDiagrams.CAP_3);
        cap.writeInt(base);
        cap.write(_ExperimentSetECDFOverScaleDiagrams.CAP_4);
        axs.writeShortName(cap, true);
        if (axs.isScaled()) {
          cap.write(_ExperimentSetECDFOverScaleDiagrams.CAP_5A);
          axs.writeShortName(cap, true);
          cap.write(_ExperimentSetECDFOverScaleDiagrams.CAP_5B);
          axs.writeScale(cap);
          cap.write(_ExperimentSetECDFOverScaleDiagrams.CAP_5C);
        }
        cap.write(_ExperimentSetECDFOverScaleDiagrams.CAP_6);
      }

      lines = new ArrayList<>();
      aucLines = new ArrayList<>();
      rag = new RankAggregate<>(data);
      for (final SameScaleInstances ssi : instances) {
        if ((ssi == null) || ((size = ssi.size()) <= 0)) {
          continue;
        }

        this.__makeFigure(data, ssi, doc, lines, aucLines);
        if (lines.isEmpty()) {
          aucLines.clear();
          continue;
        }
        if (aucLines.size() == data.size()) {
          _ExperimentSetECDFBase._aggregate(aucLines, rag);
          aucLines.clear();
        }

        if ((fc <= 0) && (!(includeLegend))) {

          try (SubFigure sf = fs.subFigure(null)) {

            try (SubFigureCaption cap = sf.subFigureCaption()) {
              cap.write("Legend."); //$NON-NLS-1$
            }

            try {
              try (FigureBody fb = sf
                  .figureBody(
                      this.makeURI(Module.GRAPHICS_FOLDER, Module.LEGEND),
                      dim)) {
                try (Graphic graph = fb.graphic()) {
                  chart = drv.createLineChart2D(//
                      axs.isTime() ? //
                      ECDFUtils.DEFAULT_AXIS_DEF_TIME//
                          : ECDFUtils.DEFAULT_AXIS_DEF_COUNT);
                  chart.setLegendType(ELegendType.ONLY_LEGEND);
                  chart.addLines(lines);
                  chart.setAxisTitleX("lg(" + axs.getAxisString() + ')'); //$NON-NLS-1$
                  chart.setAxisTitleY(Macros.ECDF.getPlaceholder());
                  chart.paint(graph);
                  chart = null;
                }
              }

            } catch (final URISyntaxException use) {
              throw new IOException(use);
            }
          }

        }

        try (SubFigure sf = fs.subFigure(null)) {

          try (SubFigureCaption cap = sf.subFigureCaption()) {
            cap.writeInt(size);
            cap.write((size <= 1) ? _ExperimentSetECDFOverScaleDiagrams.SUB_CAP_2
                : _ExperimentSetECDFOverScaleDiagrams.SUB_CAP_1);
            try (InlineMath im = cap.inlineMath()) {
              try (MathOp mo1 = im.mathOp(EMathOp.CMP_LESS_OR_EQUAL)) {
                try (MathOpParam pa1 = mo1.mathOpParam()) {
                  try (MathOp mo2 = im.mathOp(EMathOp.CMP_LESS_OR_EQUAL)) {
                    try (MathOpParam pa2 = mo1.mathOpParam()) {
                      pa2.writeInt(ssi.getLowerScaleBound());
                    }
                    try (MathOpParam pa3 = mo1.mathOpParam()) {
                      pa3.macroInvoke(Macros.SCALE);
                    }
                  }
                }
                try (MathOpParam pa4 = mo1.mathOpParam()) {
                  pa4.writeInt(ssi.getUpperScaleBound());
                }
              }
            }
          }

          try {
            try (FigureBody fb = sf.figureBody(
                this.makeURI(Module.GRAPHICS_FOLDER, null, null,
                    (ssi.getLowerScaleBound() + "_to_" + //$NON-NLS-1$
                    ssi.getUpperScaleBound())), dim)) {
              try (Graphic graph = fb.graphic()) {
                chart = drv.createLineChart2D(//
                    axs.isTime() ? //
                    ECDFUtils.DEFAULT_AXIS_DEF_TIME//
                        : ECDFUtils.DEFAULT_AXIS_DEF_COUNT);
                chart.addLines(lines);
                fc++;
                chart.setLegendType(includeLegend ? ELegendType.PUT_LEGEND
                    : ELegendType.NO_LEGEND);
                if (includeAxisLabels) {
                  chart.setAxisTitleX("lg(" + axs.getAxisString() + ')'); //$NON-NLS-1$
                  chart.setAxisTitleY(Macros.ECDF.getPlaceholder());
                }
                chart.paint(graph);
                chart = null;
              }
            }

          } catch (final URISyntaxException use) {
            throw new IOException(use);
          }
        }
      }

      l = fs.getLabel();
    }

    if ((fc > 0) && (l != null)) {
      body.write("In ");//$NON-NLS-1$
      body.reference(ESequenceType.FROM_TO, l);
      body.write(" we plot the arithmetic mean of the Empirical (Cumulative) Distribution Functions (");//$NON-NLS-1$
      body.macroInvoke(Macros.ECDF);
      body.write(", discussed in ");//$NON-NLS-1$
      body.reference(this.m_ecdf.getLabel(data));
      body.write(") of the ");//$NON-NLS-1$
      body.writeSequence(new ExperimentSequence(this.getRoot(), data,
          true, true, body), ESequenceType.AND, false);

      body.write(" over the ");//$NON-NLS-1$
      axs.writeLongName(body, true);
      body.write(" for the relative goal objective value ");//$NON-NLS-1$
      try (InlineMath im = body.inlineMath()) {
        try (MathOp mo = im.mathOp(EMathOp.CMP_EQUALS)) {
          try (MathOpParam p1 = mo.mathOpParam()) {
            p1.macroInvoke(Macros.F_THRESHOLD_RELATIVE);
          }
          try (MathOpParam p2 = mo.mathOpParam()) {
            p2.writeDouble(this.m_source.getGoal());
          }
        }
      }

      body.write(". We group the benchmark instances into ");//$NON-NLS-1$
      body.writeIntInText(instances.size(), false);
      body.write(" sets, each set containing instances with a scale ");//$NON-NLS-1$
      body.macroInvoke(Macros.SCALE);
      body.write(" of the same order of magnitude in terms of base ");//$NON-NLS-1$
      body.writeInt(base);
      body.write(". The ");//$NON-NLS-1$
      body.macroInvoke(Macros.ECDF);
      body.write(//
      " are aggregated over the instances in each group and their arithmetic means are plotted. The x-axes of the diagrams are the ");//$NON-NLS-1$
      axs.writeShortName(body, true);
      if (axs.isScaled()) {
        body.write(" (scaled by");//$NON-NLS-1$
        axs.writeScale(body);
        body.writeChar(')');
      }
      body.write(//
      " on a log-scale. Larger values are better for the ");//$NON-NLS-1$
      body.macroInvoke(Macros.ECDF);
      body.writeChar('.');

      body.writeLinebreak();
      this._doRanks(body, data, rag);
    }

    super.writeSectionBody(body, data);
  }
}
