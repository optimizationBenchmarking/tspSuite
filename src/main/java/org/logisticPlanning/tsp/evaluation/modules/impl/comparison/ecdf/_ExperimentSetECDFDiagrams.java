package org.logisticPlanning.tsp.evaluation.modules.impl.comparison.ecdf;

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
import org.logisticPlanning.tsp.evaluation.data.Property;
import org.logisticPlanning.tsp.evaluation.data.properties.ecdf.ExperimentECDFProperty;
import org.logisticPlanning.tsp.evaluation.data.properties.ecdf.RunSetECDFProperty;
import org.logisticPlanning.tsp.evaluation.data.properties.instance.InstancesProperty;
import org.logisticPlanning.tsp.evaluation.data.properties.instance.SharedInstancesProperty;
import org.logisticPlanning.tsp.evaluation.data.properties.ranking.RankAggregate;
import org.logisticPlanning.tsp.evaluation.modules.ModuleUtils;
import org.logisticPlanning.tsp.evaluation.modules.impl.AccessorSequence;
import org.logisticPlanning.tsp.evaluation.modules.impl.ExperimentSequence;
import org.logisticPlanning.tsp.evaluation.modules.impl.macros.Macros;
import org.logisticPlanning.tsp.evaluation.modules.impl.single.ecdf.ECDFUtils;
import org.logisticPlanning.tsp.evaluation.modules.spec.Module;
import org.logisticPlanning.utils.collections.basic.BasicMapEntry;
import org.logisticPlanning.utils.collections.lists.ArraySetView;
import org.logisticPlanning.utils.document.impl.utils.SequenceDouble;
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
 * style="font-weight:bold">2</a>].
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
final class _ExperimentSetECDFDiagrams extends _ExperimentSetECDFBase {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the caption 1 */
  private static final String CAP_1 = "Comparison of the "; //$NON-NLS-1$

  /** the caption 2 */
  private static final String CAP_2 = " algorithms in terms of the mean ";//$NON-NLS-1$

  /** the caption 3 */
  private static final String CAP_3 = " over all benchmark instances where sufficient data was available, for different time measures (with proper scaling) and goal relative objective values ";//$NON-NLS-1$

  /** the sub caption 0 */
  private static final String SCAP_0 = ("mean ");//$NON-NLS-1$  
  /** the sub caption 1 */
  private static final String SCAP_1 = (" over the ");//$NON-NLS-1$
  /** the sub caption 2 */
  private static final String SCAP_2 = (" for goal ");//$NON-NLS-1$
  /** the sub caption 2 */
  private static final String SCAP_3 = ("; the ");//$NON-NLS-1$
  /** the sub caption 3 */
  private static final String SCAP_4 = (" are scaled by ");//$NON-NLS-1$

  /** the goals */
  static final double[] GOALS = { 0d, 0.01d, 0.025d, 0.1d };

  /**
   * create!
   * 
   * @param owner
   *          the macro's owner
   * @param isActive
   *          should this module be active?
   */
  _ExperimentSetECDFDiagrams(final Module owner, final boolean isActive) {
    super("", owner, isActive); //$NON-NLS-1$
    this.m_size = EDefaultFigureSize.TWO_IN_A_ROW;
  }

  /** {@inheritDoc} */
  @Override
  protected final void initialize(final Header header,
      final ExperimentSet data) throws IOException {//
    for (final Accessor axs : Accessor.UNBIASED_TIME_MEASURES) {
      axs.define(header);
    }

    SharedInstancesProperty.NON_EMPTY_SHARED.initialize(header);
    super.initialize(header, data);
  }

  /** {@inheritDoc} */
  @Override
  protected final void writeSectionTitle(final SectionTitle title,
      final ExperimentSet data) throws IOException {
    title.write("Mean "); //$NON-NLS-1$
    title.macroInvoke(Macros.ECDF);
    title.write(" over All Benchmark Instances"); //$NON-NLS-1$
  }

  /**
   * make a figure
   * 
   * @param expset
   *          the experiment set
   * @param property
   *          the property
   * @param lines
   *          the list for the lines
   * @param doc
   *          the document
   * @param areaLines
   *          the list of lines to be used for AUC computation
   * @throws IOException
   *           if io fails
   */
  @SuppressWarnings("unused")
  private final void __makeFigure(final ExperimentSet expset,
      final ExperimentECDFProperty property, final Document doc,
      final ArrayList<Line2D> lines,
      final ArrayList<Map.Entry<Experiment, Object>> areaLines)
      throws IOException {
    final int[] select;
    final UnaryFunction[] transform;
    IDataCollection coll;

    lines.clear();

    transform = new UnaryFunction[] { Lg.INSTANCE, Identity.INSTANCE };
    select = new int[] { StatisticSeries.DIM_X, StatisticSeries.DIM_Y_MEAN };

    for (final Experiment exp : expset) {
      coll = property.get(exp, doc);

      coll = new TransformedCollectionView(new SubCollectionView(//
          coll, select), transform);
      lines.add(new Line2D(exp.shortName(),//
          null, coll, null, ELineMode.STAIRS_KEEP_LEFT));
      areaLines.add(new BasicMapEntry<Experiment, Object>(exp, coll));
    }
  }

  /** {@inheritDoc} */
  @SuppressWarnings({ "resource" })
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
    final Property<? super Experiment, ArraySetView<Instance>> insts;
    final ArraySetView<Instance> instances;
    boolean includeAxisLabels, includeLegend;
    LineChart2D chart;
    String s;
    int fc;

    doc = body.getDocument();

    instances = SharedInstancesProperty.NON_EMPTY_SHARED.get(data, doc);
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

    dims = doc.getDimensions();
    dim = dims.getFigureDimensionsMM(this.m_size);

    includeAxisLabels = includeLegend = false;
    if (dim.contains(dims.getFigureDimensionsMM(//
        EDefaultFigureSize.TWO_IN_A_ROW))) {
      includeAxisLabels = true;
      includeLegend = true;
    }

    insts = new InstancesProperty(instances);

    fc = 0;
    try (FigureSeries fs = body
        .figureSeries(
            Label.AUTO_LABEL,
            dim,//
            (_ExperimentSetECDFDiagrams.CAP_1 + data.size()
                + _ExperimentSetECDFDiagrams.CAP_2
                + Macros.ECDF.getPlaceholder() + _ExperimentSetECDFDiagrams.CAP_3),//
            (_ExperimentSetECDFDiagrams.SCAP_0
                + Macros.ECDF.getPlaceholder()
                + _ExperimentSetECDFDiagrams.SCAP_1
                + Accessor.DE.getShortName()
                + _ExperimentSetECDFDiagrams.SCAP_2
                + (Macros.F_THRESHOLD_RELATIVE.getPlaceholder() + '=')
                + 100 + _ExperimentSetECDFDiagrams.SCAP_3
                + Accessor.DE.getShortName()
                + Macros.SCALE.getPlaceholder() + '.'),//
            true)) {

      try (FigureSeriesCaption cap = fs.figureSeriesCaption()) {

        cap.write(_ExperimentSetECDFDiagrams.CAP_1);
        cap.writeIntInText(data.size(), false);
        cap.write(_ExperimentSetECDFDiagrams.CAP_2);
      }

      lines = new ArrayList<>();
      aucLines = new ArrayList<>();
      rag = new RankAggregate<>(data);
      for (final double goal : _ExperimentSetECDFDiagrams.GOALS) {
        for (final Accessor axs : Accessor.UNBIASED_TIME_MEASURES) {

          this.__makeFigure(
              data,//
              new ExperimentECDFProperty(
              //
                  RunSetECDFProperty.getInstance(axs, goal), insts), doc,
              lines, aucLines);
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
                try (FigureBody fb = sf.figureBody(
                    this.makeURI(Module.GRAPHICS_FOLDER, Module.LEGEND),
                    dim)) {
                  try (Graphic graph = fb.graphic()) {
                    chart = drv.createLineChart2D(//
                        axs.isTime() ? //
                        ECDFUtils.DEFAULT_AXIS_DEF_TIME//
                            : ECDFUtils.DEFAULT_AXIS_DEF_COUNT);
                    chart.setLegendType(ELegendType.ONLY_LEGEND);
                    chart.addLines(lines);
                    chart.setAxisTitleX("lg(scaled time measure)"); //$NON-NLS-1$
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
              cap.write(_ExperimentSetECDFDiagrams.SCAP_0);
              cap.macroInvoke(Macros.ECDF);
              cap.write(_ExperimentSetECDFDiagrams.SCAP_1);
              axs.writeShortName(cap, true);
              cap.write(_ExperimentSetECDFDiagrams.SCAP_2);
              try (InlineMath im = cap.inlineMath()) {
                try (MathOp mo = im.mathOp(EMathOp.CMP_EQUALS)) {
                  try (MathOpParam p1 = mo.mathOpParam()) {
                    p1.macroInvoke(Macros.F_THRESHOLD_RELATIVE);
                  }
                  try (MathOpParam p2 = mo.mathOpParam()) {
                    p2.writeDouble(goal);
                  }
                }
              }
              if (axs.isScaled()) {
                cap.write(_ExperimentSetECDFDiagrams.SCAP_3);
                axs.writeShortName(cap, true);
                cap.write(_ExperimentSetECDFDiagrams.SCAP_4);
                axs.writeScale(cap);
              }
              cap.writeChar('.');
            }

            try {
              try (FigureBody fb = sf.figureBody(
                  this.makeURI(Module.GRAPHICS_FOLDER, null, null,
                      ((axs.getShortName() + '_') + goal)), dim)) {
                try (Graphic graph = fb.graphic()) {
                  chart = drv.createLineChart2D(//
                      axs.isTime() ? //
                      ECDFUtils.DEFAULT_AXIS_DEF_TIME//
                          : ECDFUtils.DEFAULT_AXIS_DEF_COUNT);
                  chart.addLines(lines);
                  fc++;
                  chart
                      .setLegendType(includeLegend ? ELegendType.PUT_LEGEND
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

      body.write(" for different time measures and relative goal objective values ");//$NON-NLS-1$
      body.macroInvoke(Macros.F_THRESHOLD_RELATIVE);
      body.write(" (see ");//$NON-NLS-1$
      body.reference(this.m_descRel.getLabel(data));
      body.write(") aggregated over all ");//$NON-NLS-1$
      body.writeIntInText(instances.size(), false);
      body.write(//
      " benchmark instances for which sufficient data was available. The x-axes of the diagrams are the logarithms (base ");//$NON-NLS-1$
      body.writeInt(10);
      body.write(//
      " of the runtime measured in ");//$NON-NLS-1$
      body.writeSequence(//
          new AccessorSequence(Accessor.UNBIASED_TIME_MEASURES, true,
              false, true, body),//
          ESequenceType.XOR, false);
      body.write(//
      " (scale appropriately). The ");//$NON-NLS-1$
      body.macroInvoke(Macros.ECDF);
      body.write(//
      " returns the fraction of runs that have reached the goal (relative) objective value ");//$NON-NLS-1$
      body.macroInvoke(Macros.F_THRESHOLD_RELATIVE);
      body.write(//
      " for a given point in time. The higher this fraction rises (it can reach at most ");//$NON-NLS-1$
      body.writeInt(1);
      body.write(//
      ") and the sooner it gets there, the better. We plot such diagrams for the following values of ");//$NON-NLS-1$
      body.macroInvoke(Macros.F_THRESHOLD_RELATIVE);
      body.write(//
      ": ");//$NON-NLS-1$
      body.writeSequence(new SequenceDouble(body, null,
          _ExperimentSetECDFDiagrams.GOALS), ESequenceType.AND, true);
      body.writeChar('.');

      body.writeLinebreak();
      this._doRanks(body, data, rag);
    }

    super.writeSectionBody(body, data);
  }
}
