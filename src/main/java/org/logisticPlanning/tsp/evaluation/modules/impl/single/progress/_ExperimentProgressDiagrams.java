package org.logisticPlanning.tsp.evaluation.modules.impl.single.progress;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.logisticPlanning.tsp.evaluation.data.Accessor;
import org.logisticPlanning.tsp.evaluation.data.Experiment;
import org.logisticPlanning.tsp.evaluation.data.RunSet;
import org.logisticPlanning.tsp.evaluation.data.conditions.RunHas2DifferentPoints;
import org.logisticPlanning.tsp.evaluation.modules.impl.macros.Macros;
import org.logisticPlanning.tsp.evaluation.modules.spec.Module;
import org.logisticPlanning.utils.collections.conditions.CollectiveCondition;
import org.logisticPlanning.utils.collections.conditions.CompoundCondition;
import org.logisticPlanning.utils.collections.conditions.NotEmptyCondition;
import org.logisticPlanning.utils.collections.lists.ArraySetView;
import org.logisticPlanning.utils.document.spec.Document;
import org.logisticPlanning.utils.document.spec.DocumentDimensions;
import org.logisticPlanning.utils.document.spec.EDefaultFigureSize;
import org.logisticPlanning.utils.document.spec.ESequenceType;
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
import org.logisticPlanning.utils.math.functions.logic.LAnd;
import org.logisticPlanning.utils.math.functions.power.Lg;
import org.logisticPlanning.utils.math.statistics.EStatisticParameter;
import org.logisticPlanning.utils.math.statistics.series.StatisticSeries;

/**
 * This evaluator will print diagrams showing the progress of and algorithm
 * over a given time measure over all benchmark instances.
 */
final class _ExperimentProgressDiagrams extends _ExperimentProgressBaseB {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the caption 2 */
  private static final String CAP_2 = (" over all benchmark instances and appropriatly scaled.");//$NON-NLS-1$

  /** the sub caption 1 */
  private static final String SCAP_1 = (" over the ");//$NON-NLS-1$
  /** the sub caption 2 */
  private static final String SCAP_2 = (" scaled by ");//$NON-NLS-1$

  /**
   * create!
   *
   * @param owner
   *          the macro's owner
   * @param isActive
   *          should this module be active?
   */
  _ExperimentProgressDiagrams(final Module owner, final boolean isActive) {
    super("experimentProgress", owner, isActive); //$NON-NLS-1$
    this.m_size = EDefaultFigureSize.TWO_IN_A_ROW;
  }

  /** {@inheritDoc} */
  @Override
  protected final void initialize(final Header header,
      final Experiment data) throws IOException {//
    for (final Accessor axs : Accessor.TIME_MEASURES) {
      axs.define(header);
    }
    super.initialize(header, data);
  }

  /** {@inheritDoc} */
  @Override
  protected final void writeSectionTitle(final SectionTitle title,
      final Experiment data) throws IOException {
    title.write("Progress of "); //$NON-NLS-1$
    title.macroInvoke(this.getRoot().getShortName(data));
    title.write(" over All Benchmark Instances "); //$NON-NLS-1$
  }

  /**
   * make a figure
   *
   * @param exp
   *          the experiment
   * @param includeMean
   *          also print the mean
   * @param includeHighQuantiles
   *          also print high quantiles
   * @param axs
   *          the accessor
   * @param lines
   *          the destination list for lines
   * @param back
   *          the destination list for background lines
   * @throws IOException
   *           if io fails
   */
  private final void __makeFigure(final ArraySetView<RunSet> exp,
      final boolean includeMean, final Accessor axs,
      final boolean includeHighQuantiles, final ArrayList<Line2D> lines,
      final ArrayList<Line2D> back) throws IOException {
    final StatisticSeries series;
    IDataCollection[] data;
    UnaryFunction[] transform;

    lines.clear();
    back.clear();

    data = ProgressUtils.transformRuns(axs, exp);
    if ((data == null) || (data.length <= 0)) {
      return;
    }

    series = new StatisticSeries(data, 0, 1, this.m_approxMaxPoints);
    transform = new UnaryFunction[] { Lg.INSTANCE, Identity.INSTANCE };

    if (includeHighQuantiles) {
      lines.add(new Line2D(EStatisticParameter.PERCENTILE_95
          .getShortName(), null,//
          new TransformedCollectionView(
              new SubCollectionView(series, new int[] {
                  StatisticSeries.DIM_X, StatisticSeries.DIM_Y_Q95 }),
              transform), null, ELineMode.STAIRS_KEEP_LEFT));
    }
    lines.add(new Line2D(EStatisticParameter.PERCENTILE_75.getShortName(),
        null,//
        new TransformedCollectionView(
            new SubCollectionView(series, new int[] {
                StatisticSeries.DIM_X, StatisticSeries.DIM_Y_Q75 }),
            transform), null, ELineMode.STAIRS_KEEP_LEFT));
    lines.add(new Line2D(EStatisticParameter.MEDIAN.getShortName(), null,//
        new TransformedCollectionView(new SubCollectionView(series,
            new int[] { StatisticSeries.DIM_X,
                StatisticSeries.DIM_Y_MEDIAN }), transform), null,
        ELineMode.STAIRS_KEEP_LEFT));

    if (includeMean) {
      lines.add(new Line2D(EStatisticParameter.ARITHMETIC_MEAN
          .getShortName(), null,//
          new TransformedCollectionView(new SubCollectionView(series,
              new int[] { StatisticSeries.DIM_X,
                  StatisticSeries.DIM_Y_MEAN }), transform), null,
          ELineMode.STAIRS_KEEP_LEFT));
    }

    lines.add(new Line2D(EStatisticParameter.PERCENTILE_25.getShortName(),
        null,//
        new TransformedCollectionView(
            new SubCollectionView(series, new int[] {
                StatisticSeries.DIM_X, StatisticSeries.DIM_Y_Q25 }),
            transform), null, ELineMode.STAIRS_KEEP_LEFT));

    if (includeHighQuantiles) {
      lines.add(new Line2D(EStatisticParameter.PERCENTILE_05
          .getShortName(), null,//
          new TransformedCollectionView(
              new SubCollectionView(series, new int[] {
                  StatisticSeries.DIM_X, StatisticSeries.DIM_Y_Q05 }),
              transform), null, ELineMode.STAIRS_KEEP_LEFT));
    }

    if (this.m_printRuns) {
      for (final IDataCollection coll : data) {
        back.add(new Line2D(null,
            null,//
            new TransformedCollectionView(coll, transform), null,
            ELineMode.STAIRS_KEEP_LEFT));
      }
    }
  }

  /** {@inheritDoc} */
  @SuppressWarnings({ "resource" })
  @Override
  protected final void writeSectionBody(final SectionBody body,
      final Experiment data) throws IOException {
    final ChartDriver drv;
    final Document doc;
    final Logger log;
    final DoubleDimension dim;
    final DocumentDimensions dims;
    final Label l;
    final boolean[] useful;
    final ArrayList<Line2D> lines, back;
    boolean hasUseful, b, includeMean, includeHighQuantiles, includeAxisLabels, includeLegend;
    LineChart2D chart;
    String s;
    int fc;

    doc = body.getDocument();
    drv = doc.getOwner().getDriver().getChartDriver();
    if (drv == null) {
      s = "No chart driver detected - cannot paint charts.";//$NON-NLS-1$
      body.write(s);
      log = this.getLogger();
      if ((log != null) && (log.isLoggable(Level.WARNING))) {
        log.warning(s);
      }
      return;
    }

    useful = new boolean[Accessor.ACCESSORS.size()];
    hasUseful = false;
    for (final Accessor axs : Accessor.ACCESSORS) {
      useful[axs.ordinal()] = b = //
      data.hasAny(new CompoundCondition<>(LAnd.INSTANCE,
          NotEmptyCondition.INSTANCE, new CollectiveCondition<>(
              RunHas2DifferentPoints.forAccessor(axs), false)));
      hasUseful |= b;
    }

    if (!hasUseful) {
      s = "No useful data found to paint progress diagrams. This is very odd.";//$NON-NLS-1$
      body.write(s);
      log = this.getLogger();
      if ((log != null) && (log.isLoggable(Level.WARNING))) {
        log.warning(s);
      }
      return;
    }

    dims = doc.getDimensions();
    dim = dims.getFigureDimensionsMM(this.m_size);

    includeMean = includeHighQuantiles = includeAxisLabels = includeLegend = false;
    if (dim.contains(dims
        .getFigureDimensionsMM(EDefaultFigureSize.FOUR_IN_A_ROW))) {
      includeMean = true;
      if (dim.contains(dims
          .getFigureDimensionsMM(EDefaultFigureSize.THREE_IN_A_ROW))) {
        includeHighQuantiles = true;
        if (dim.contains(dims
            .getFigureDimensionsMM(EDefaultFigureSize.TWO_IN_A_ROW))) {
          includeAxisLabels = true;
          // if (dim.contains(dims
          // .getFigureDimensionsMM(EDefaultFigureSize.COLUMN_WIDE)))
          // {
          includeLegend = true;
          // }
        }
      }
    }

    lines = new ArrayList<>();
    back = new ArrayList<>();

    fc = 0;
    try (FigureSeries fs = body
        .figureSeries(
            Label.AUTO_LABEL,
            dim,//
            (_ExperimentProgressBaseA.CAP_1 + data.name() + _ExperimentProgressDiagrams.CAP_2),//
            (Macros.F_BEST_RELATIVE.getPlaceholder()
                + _ExperimentProgressDiagrams.SCAP_1
                + Accessor.DE.getShortName()
                + _ExperimentProgressDiagrams.SCAP_2
                + Accessor.DE.getScaleString() + '.'),//
            true)) {

      try (FigureSeriesCaption cap = fs.figureSeriesCaption()) {
        cap.write(_ExperimentProgressBaseA.CAP_1);
        cap.macroInvoke(this.getRoot().getShortName(data));
        cap.write(_ExperimentProgressDiagrams.CAP_2);
        cap.macroInvoke(Macros.F_BEST_RELATIVE);
      }

      for (final Accessor axs : Accessor.TIME_MEASURES) {
        if (!(useful[axs.ordinal()])) {
          continue;
        }

        this.__makeFigure(data, includeMean, axs, includeHighQuantiles,
            lines, back);

        if ((fc <= 0) && (!(includeLegend))) {

          if (lines.isEmpty() && back.isEmpty()) {
            continue;
          }

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
                  chart.addBackgroundLines(lines);
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

          try (SubFigureCaption cap = sf.subFigureCaption()) {
            cap.macroInvoke(Macros.F_BEST_RELATIVE);
            cap.write(" over the "); //$NON-NLS-1$
            axs.writeShortName(cap);
            if (axs.isScaled()) {
              cap.write(" scaled by "); //$NON-NLS-1$
              axs.writeScale(cap);
            }
            cap.writeChar('.');
          }

          try {
            try (FigureBody fb = sf.figureBody(
                this.makeURI(Module.GRAPHICS_FOLDER, data, null,
                    axs.getShortName()), dim)) {
              try (Graphic graph = fb.graphic()) {
                chart = drv.createLineChart2D(//
                    axs.isTime() ? //
                    ProgressUtils.DEFAULT_AXIS_DEF_TIME//
                        : ProgressUtils.DEFAULT_AXIS_DEF_COUNT);
                fc++;
                chart.setLegendType(includeLegend ? ELegendType.PUT_LEGEND
                    : ELegendType.NO_LEGEND);
                chart.addLines(lines);
                chart.addBackgroundLines(lines);
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
      body.write(" we plot the performance of the ");//$NON-NLS-1$
      body.macroInvoke(this.getRoot().getLongName(data));
      body.write(" (");//$NON-NLS-1$
      body.macroInvoke(this.getRoot().getShortName(data));
      body.write(") in terms of the relative error ");//$NON-NLS-1$
      body.macroInvoke(Macros.F_BEST_RELATIVE);
      body.write(" aggregated over all benchmarks for different time measures. "); //$NON-NLS-1$
      body.macroInvoke(Macros.F_BEST_RELATIVE);
      body.write(" is the relative difference of the objective value ");//$NON-NLS-1$
      body.macroInvoke(Macros.F_BEST);
      body.write(" of the best solution known by the algorithm at a time to the objective value ");//$NON-NLS-1$
      body.macroInvoke(Macros.F_OPTIMAL);
      body.write(" of the globally optimal solution. We plot the progress in terms of ");//$NON-NLS-1$
      body.macroInvoke(Macros.F_BEST_RELATIVE);
      body.write(" over different time measures, each appropriately scaled.");//$NON-NLS-1$

      body.write(" In order to get a better view of the data, we use a logarithmically-scaled axis (to base "); //$NON-NLS-1$
      body.writeInt(10);
      body.write(").");//$NON-NLS-1$
    }

    super.writeSectionBody(body, data);
  }
}
