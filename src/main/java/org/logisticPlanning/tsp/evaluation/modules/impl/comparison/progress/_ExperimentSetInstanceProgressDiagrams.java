package org.logisticPlanning.tsp.evaluation.modules.impl.comparison.progress;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.logisticPlanning.tsp.benchmarking.instances.Instance;
import org.logisticPlanning.tsp.benchmarking.objective.DataPoint;
import org.logisticPlanning.tsp.evaluation.Evaluator;
import org.logisticPlanning.tsp.evaluation.data.Accessor;
import org.logisticPlanning.tsp.evaluation.data.Experiment;
import org.logisticPlanning.tsp.evaluation.data.ExperimentSet;
import org.logisticPlanning.tsp.evaluation.data.RunSet;
import org.logisticPlanning.tsp.evaluation.data.properties.StatisticRunProperty;
import org.logisticPlanning.tsp.evaluation.data.properties.instance.SharedInstancesProperty;
import org.logisticPlanning.tsp.evaluation.data.properties.ranking.RankAggregate;
import org.logisticPlanning.tsp.evaluation.modules.ModuleUtils;
import org.logisticPlanning.tsp.evaluation.modules.impl.ExperimentSequence;
import org.logisticPlanning.tsp.evaluation.modules.impl.macros.Macros;
import org.logisticPlanning.tsp.evaluation.modules.impl.single.progress.ProgressUtils;
import org.logisticPlanning.tsp.evaluation.modules.spec.Module;
import org.logisticPlanning.utils.collections.basic.BasicMapEntry;
import org.logisticPlanning.utils.collections.lists.ArraySetView;
import org.logisticPlanning.utils.document.spec.Document;
import org.logisticPlanning.utils.document.spec.DocumentDimensions;
import org.logisticPlanning.utils.document.spec.EDefaultFigureSize;
import org.logisticPlanning.utils.document.spec.ESequenceType;
import org.logisticPlanning.utils.document.spec.Emphasize;
import org.logisticPlanning.utils.document.spec.FigureBody;
import org.logisticPlanning.utils.document.spec.FigureSeries;
import org.logisticPlanning.utils.document.spec.FigureSeriesCaption;
import org.logisticPlanning.utils.document.spec.Graphic;
import org.logisticPlanning.utils.document.spec.Header;
import org.logisticPlanning.utils.document.spec.Label;
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
import org.logisticPlanning.utils.math.functions.compound.ChainedUnary;
import org.logisticPlanning.utils.math.functions.compound.FixedScale;
import org.logisticPlanning.utils.math.functions.power.Lg;
import org.logisticPlanning.utils.math.statistics.series.StatisticSeries;

/**
 * This evaluator will print diagrams showing the progress of and algorithm
 * over a given time measure for a given benchmark instance.
 */
final class _ExperimentSetInstanceProgressDiagrams extends
    _ExperimentSetProgressBase {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** caption 2 */
  private static final String CAP_2 = " Progress in terms of median "; //$NON-NLS-1$
  /** caption 3 */
  private static final String CAP_3 = " over the consumed "; //$NON-NLS-1$
  /** caption 3 */
  private static final String CAP_4A1 = " (first scaled by "; //$NON-NLS-1$
  /** caption 3 */
  private static final String CAP_4A2 = " and then printed on a log-scaled axis)."; //$NON-NLS-1$
  /** caption 4 */
  private static final String CAP_4B1 = " (with log-scaled "; //$NON-NLS-1$
  /** caption 5 */
  private static final String CAP_4B2 = " axis)."; //$NON-NLS-1$
  /** sub caption 1 */
  private static final String SUB_CAP_1 = "instance "; //$NON-NLS-1$

  /** the property */
  private final StatisticRunProperty m_property;

  /** the accessor */
  private final Accessor m_accessor;

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
  _ExperimentSetInstanceProgressDiagrams(final Module owner,
      final Accessor axs, final boolean isActive) {
    super("experimentSetInstanceProgress" + axs.getShortName(),//$NON-NLS-1$
        owner, isActive);

    this.m_accessor = axs;
    this.m_property = StatisticRunProperty.get(axs.ordinal(),
        DataPoint.RELATIVE_F_INDEX);
  }

  /** {@inheritDoc} */
  @Override
  protected final void initialize(final Header header,
      final ExperimentSet data) throws IOException {//
    this.m_property.initialize(header);
    this.m_accessor.define(header);
    Macros.AUC.define(header);
    super.initialize(header, data);
  }

  /** {@inheritDoc} */
  @Override
  protected final void writeSectionTitle(final SectionTitle title,
      final ExperimentSet data) throws IOException {
    title.write("Progress in Terms of "); //$NON-NLS-1$
    this.m_accessor.writeShortName(title, false);
  }

  /**
   * make a figure
   *
   * @param expset
   *          the experiment set
   * @param inst
   *          the instance
   * @param doc
   *          the document
   * @param lines
   *          the list for the lines
   * @param areaLines
   *          the list of lines to be used for AUC computation
   * @throws IOException
   *           if io fails
   */
  private final void __makeFigure(final ExperimentSet expset,
      final Instance inst, final Document doc,
      final ArrayList<Line2D> lines,
      final ArrayList<Map.Entry<Experiment, Object>> areaLines)
      throws IOException {
    final Accessor axs;
    final long scale;
    final UnaryFunction[] transform;
    final int[] select;
    IDataCollection data;
    RunSet rs;

    lines.clear();
    axs = this.m_accessor;
    scale = axs.calculateScale(inst.n());

    transform = new UnaryFunction[] {
        ((scale == 1) ? Lg.INSTANCE : ChainedUnary
            .chain(FixedScale.scale(Identity.INSTANCE, (1d / scale)),
                Lg.INSTANCE)), Identity.INSTANCE };

    select = new int[] { StatisticSeries.DIM_X,
        StatisticSeries.DIM_Y_MEDIAN };

    for (final Experiment exp : expset) {
      rs = exp.forInstance(inst);

      if ((rs == null) || (rs.isEmpty())) {
        throw new IllegalStateException();
      }

      data = new TransformedCollectionView(new SubCollectionView(
          this.m_property.get(rs, null), select), transform);
      lines.add(new Line2D(exp.shortName(), null,//
          data, null, ELineMode.STAIRS_KEEP_LEFT));
      areaLines.add(new BasicMapEntry<Experiment, Object>(exp, data));
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
    final Accessor axs;
    final Label l;
    final ArraySetView<Instance> insts;
    final ArrayList<Line2D> lines;
    final ArrayList<Map.Entry<Experiment, Object>> aucLines;
    final RankAggregate<Experiment> rag;
    boolean includeAxisLabels, includeLegend;
    LineChart2D chart;
    String s;
    int fc;

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

    axs = this.m_accessor;
    insts = SharedInstancesProperty.oneMustHave2PointsOfAccessor(axs).get(
        data, doc);
    if ((insts == null) || (insts.size() <= 0)) {
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

    includeAxisLabels = includeLegend = false;

    if (dim.contains(dims
        .getFigureDimensionsMM(EDefaultFigureSize.TWO_BY_TWO))) {
      includeAxisLabels = true;
      if (dim.contains(dims
          .getFigureDimensionsMM(EDefaultFigureSize.COLUMN_WIDE))) {
        includeLegend = true;
      }
    }

    lines = new ArrayList<>();
    fc = 0;
    try (FigureSeries fs = body
        .figureSeries(Label.AUTO_LABEL,
            dim,//
            (_ExperimentSetInstanceProgressDiagrams.CAP_2
                + Macros.F_BEST_RELATIVE.getPlaceholder()
                + _ExperimentSetInstanceProgressDiagrams.CAP_3
                + axs.getLongName(true) + (axs.isScaled() ? (_ExperimentSetInstanceProgressDiagrams.CAP_4A1
                + axs.getScaleString() + _ExperimentSetInstanceProgressDiagrams.CAP_4A2)
                : (_ExperimentSetInstanceProgressDiagrams.CAP_4B1
                    + axs.getShortName() + _ExperimentSetInstanceProgressDiagrams.CAP_4B2))),//
            (_ExperimentSetInstanceProgressDiagrams.SUB_CAP_1 + "XXXXX"),//$NON-NLS-1$
            true)) {

      try (FigureSeriesCaption cap = fs.figureSeriesCaption()) {

        cap.write(_ExperimentSetInstanceProgressDiagrams.CAP_2);
        cap.macroInvoke(Macros.F_BEST_RELATIVE);
        cap.write(_ExperimentSetInstanceProgressDiagrams.CAP_3);
        axs.writeLongName(cap, true);
        if (axs.isScaled()) {
          cap.write(_ExperimentSetInstanceProgressDiagrams.CAP_4A1);
          axs.writeScale(cap);
          cap.write(_ExperimentSetInstanceProgressDiagrams.CAP_4A2);
        } else {
          cap.write(_ExperimentSetInstanceProgressDiagrams.CAP_4B1);
          axs.writeShortName(cap, false);
          cap.write(_ExperimentSetInstanceProgressDiagrams.CAP_4B2);
        }
      }

      aucLines = new ArrayList<>();
      rag = new RankAggregate<>(data);
      for (final Instance inst : insts) {
        this.__makeFigure(data, inst, doc, lines, aucLines);

        if (lines.isEmpty()) {
          aucLines.clear();
          continue;
        }

        if (aucLines.size() == data.size()) {
          _ExperimentSetProgressBase._aggregate(aucLines, rag);
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
                      ProgressUtils.DEFAULT_AXIS_DEF_TIME//
                          : ProgressUtils.DEFAULT_AXIS_DEF_COUNT);
                  chart.setLegendType(ELegendType.ONLY_LEGEND);
                  chart.addLines(lines);
                  chart.setAxisTitleX("lg(" + axs.getAxisString() + ')'); //$NON-NLS-1$
                  chart.setAxisTitleY(Macros.F_BEST_RELATIVE
                      .getPlaceholder());
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
          fc++;

          try (SubFigureCaption cap = sf.subFigureCaption()) {
            cap.write(_ExperimentSetInstanceProgressDiagrams.SUB_CAP_1);
            cap.write(inst.name());
          }

          try {
            try (FigureBody fb = sf.figureBody(
                this.makeURI(Module.GRAPHICS_FOLDER, null, inst), dim)) {
              try (Graphic graph = fb.graphic()) {
                chart = drv.createLineChart2D(//
                    axs.isTime() ? //
                    ProgressUtils.DEFAULT_AXIS_DEF_TIME//
                        : ProgressUtils.DEFAULT_AXIS_DEF_COUNT);
                chart.addLines(lines);
                chart.setLegendType(includeLegend ? ELegendType.PUT_LEGEND
                    : ELegendType.NO_LEGEND);
                if (includeAxisLabels) {
                  chart.setAxisTitleX("lg(" + axs.getAxisString() + ')'); //$NON-NLS-1$
                  chart.setAxisTitleY(Macros.F_BEST_RELATIVE
                      .getPlaceholder());
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
      body.write(" we plot the performance of ");//$NON-NLS-1$

      body.writeSequence(new ExperimentSequence(this.getRoot(), data,
          true, true, body), ESequenceType.AND, false);

      body.write(" in terms of the ");//$NON-NLS-1$
      try (Emphasize em = body.emphasize()) {
        em.write("median");//$NON-NLS-1$
      }
      body.write(" relative error ");//$NON-NLS-1$
      body.macroInvoke(Macros.F_BEST_RELATIVE);
      body.write(" for each of the ");//$NON-NLS-1$
      body.writeIntInText(fc, false);
      body.write(" problem instances for which sufficient data exists. "); //$NON-NLS-1$
      body.macroInvoke(Macros.F_BEST_RELATIVE);
      body.write(" is the relative difference of the objective value ");//$NON-NLS-1$
      body.macroInvoke(Macros.F_BEST);
      body.write(" of the best solution known by the algorithm at a time to the objective value ");//$NON-NLS-1$
      body.macroInvoke(Macros.F_OPTIMAL);
      body.write(" of the globally optimal solution. We plot the progress in terms of ");//$NON-NLS-1$
      body.macroInvoke(Macros.F_BEST_RELATIVE);
      body.write(" over the consumed ");//$NON-NLS-1$
      body.write(axs.getLongName(true));
      body.write(" (");//$NON-NLS-1$
      axs.writeShortName(body, true);
      body.write("). In order to get a better view of the data, we ");//$NON-NLS-1$
      if (axs.isScaled()) {
        body.write("first scale the value of ");//$NON-NLS-1$
        axs.writeShortName(body, false);
        body.write(" by ");//$NON-NLS-1$
        axs.writeScale(body);
        body.write(", where ");//$NON-NLS-1$
        body.macroInvoke(Macros.SCALE);
        body.write(" is the number of nodes in a problem instance and then ");//$NON-NLS-1$
      }
      body.write("use a logarithmically-scaled axis (to base "); //$NON-NLS-1$
      body.writeInt(10);
      body.write(").");//$NON-NLS-1$

      body.writeLinebreak();
      this._doRanks(body, data, rag);
    }

    super.writeSectionBody(body, data);
  }
}
