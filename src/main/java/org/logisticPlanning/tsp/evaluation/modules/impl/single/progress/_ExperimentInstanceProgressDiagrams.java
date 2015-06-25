package org.logisticPlanning.tsp.evaluation.modules.impl.single.progress;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.logisticPlanning.tsp.benchmarking.instances.Instance;
import org.logisticPlanning.tsp.benchmarking.objective.DataPoint;
import org.logisticPlanning.tsp.evaluation.Evaluator;
import org.logisticPlanning.tsp.evaluation.data.Accessor;
import org.logisticPlanning.tsp.evaluation.data.Experiment;
import org.logisticPlanning.tsp.evaluation.data.Run;
import org.logisticPlanning.tsp.evaluation.data.RunSet;
import org.logisticPlanning.tsp.evaluation.data.properties.StatisticRunProperty;
import org.logisticPlanning.tsp.evaluation.data.properties.instance.InstanceSelectionUtils;
import org.logisticPlanning.tsp.evaluation.modules.ModuleUtils;
import org.logisticPlanning.tsp.evaluation.modules.impl.macros.Macros;
import org.logisticPlanning.tsp.evaluation.modules.spec.Module;
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
import org.logisticPlanning.utils.math.data.collection.SubCollectionView;
import org.logisticPlanning.utils.math.data.collection.SubListView;
import org.logisticPlanning.utils.math.data.collection.TransformedCollectionView;
import org.logisticPlanning.utils.math.functions.UnaryFunction;
import org.logisticPlanning.utils.math.functions.arithmetic.Identity;
import org.logisticPlanning.utils.math.functions.compound.ChainedUnary;
import org.logisticPlanning.utils.math.functions.compound.FixedScale;
import org.logisticPlanning.utils.math.functions.power.Lg;
import org.logisticPlanning.utils.math.statistics.EStatisticParameter;
import org.logisticPlanning.utils.math.statistics.series.StatisticSeries;

/**
 * This evaluator will print diagrams showing the progress of and algorithm
 * over a given time measure for a given benchmark instance.
 */
final class _ExperimentInstanceProgressDiagrams extends
    _ExperimentProgressBaseA {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** caption 2 */
  private static final String CAP_2 = " in terms of "; //$NON-NLS-1$
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
  _ExperimentInstanceProgressDiagrams(final Module owner,
      final Accessor axs, final boolean isActive) {
    super("experimentInstanceProgress" + axs.getShortName(),//$NON-NLS-1$
        owner, isActive);

    this.m_accessor = axs;
    this.m_property = StatisticRunProperty.get(axs.ordinal(),
        DataPoint.RELATIVE_F_INDEX);
  }

  /** {@inheritDoc} */
  @Override
  protected final void initialize(final Header header,
      final Experiment data) throws IOException {//
    this.m_property.initialize(header);
    this.m_accessor.define(header);
    super.initialize(header, data);
  }

  /** {@inheritDoc} */
  @Override
  protected final void writeSectionTitle(final SectionTitle title,
      final Experiment data) throws IOException {
    title.write("Progress of "); //$NON-NLS-1$
    title.macroInvoke(this.getRoot().getShortName(data));
    title.write(" in Terms of "); //$NON-NLS-1$
    this.m_accessor.writeShortName(title, false);
  }

  /**
   * make a figure
   *
   * @param ss
   *          the statistic series to use
   * @param rs
   *          the run set
   * @param inst
   *          the instance
   * @param includeMean
   *          also print the mean
   * @param includeHighQuantiles
   *          also print high quantiles
   * @param lines
   *          the list to receive the lines
   * @param back
   *          the list to receive the background
   * @throws IOException
   *           if io fails
   */
  private final void __makeFigure(final StatisticSeries ss,
      final RunSet rs, final Instance inst, final boolean includeMean,
      final boolean includeHighQuantiles, final ArrayList<Line2D> lines,
      final ArrayList<Line2D> back) throws IOException {
    final Accessor axs;
    final int dimX, size;
    final long scale;
    final UnaryFunction[] transform;
    final int[] select;
    Run r;
    int i;

    lines.clear();
    back.clear();

    axs = this.m_accessor;
    dimX = axs.ordinal();
    scale = axs.calculateScale(inst.n());

    transform = new UnaryFunction[] {
        ((scale == 1) ? Lg.INSTANCE : ChainedUnary
            .chain(FixedScale.scale(Identity.INSTANCE, (1d / scale)),
                Lg.INSTANCE)), Identity.INSTANCE };

    if (includeHighQuantiles) {
      lines.add(new Line2D(EStatisticParameter.PERCENTILE_95
          .getShortName(), null,//
          new TransformedCollectionView(
              new SubCollectionView(ss, new int[] { StatisticSeries.DIM_X,
                  StatisticSeries.DIM_Y_Q95 }), transform), null,
          ELineMode.STAIRS_KEEP_LEFT));
    }
    lines
        .add(new Line2D(EStatisticParameter.PERCENTILE_75.getShortName(),
            null,//
            new TransformedCollectionView(new SubCollectionView(ss,
                new int[] { StatisticSeries.DIM_X,
                    StatisticSeries.DIM_Y_Q75 }), transform), null,
            ELineMode.STAIRS_KEEP_LEFT));
    lines.add(new Line2D(EStatisticParameter.MEDIAN.getShortName(), null,//
        new TransformedCollectionView(new SubCollectionView(ss, new int[] {
            StatisticSeries.DIM_X, StatisticSeries.DIM_Y_MEDIAN }),
            transform), null, ELineMode.STAIRS_KEEP_LEFT));

    if (includeMean) {
      lines.add(new Line2D(EStatisticParameter.ARITHMETIC_MEAN
          .getShortName(), null,//
          new TransformedCollectionView(new SubCollectionView(ss,
              new int[] { StatisticSeries.DIM_X,
                  StatisticSeries.DIM_Y_MEAN }), transform), null,
          ELineMode.STAIRS_KEEP_LEFT));
    }

    lines
        .add(new Line2D(EStatisticParameter.PERCENTILE_25.getShortName(),
            null,//
            new TransformedCollectionView(new SubCollectionView(ss,
                new int[] { StatisticSeries.DIM_X,
                    StatisticSeries.DIM_Y_Q25 }), transform), null,
            ELineMode.STAIRS_KEEP_LEFT));

    if (includeHighQuantiles) {
      lines.add(new Line2D(EStatisticParameter.PERCENTILE_05
          .getShortName(), null,//
          new TransformedCollectionView(
              new SubCollectionView(ss, new int[] { StatisticSeries.DIM_X,
                  StatisticSeries.DIM_Y_Q05 }), transform), null,
          ELineMode.STAIRS_KEEP_LEFT));
    }

    if (this.m_printRuns) {
      select = new int[] { dimX, DataPoint.RELATIVE_F_INDEX };
      size = rs.size();
      for (i = 0; i < size; i++) {
        r = rs.get(i);
        back.add(new Line2D(null, null,//
            new TransformedCollectionView(new SubListView(r, select),
                transform), null, ELineMode.STAIRS_KEEP_LEFT));
      }
    }
  }

  /** {@inheritDoc} */
  @SuppressWarnings("resource")
  @Override
  protected final void writeSectionBody(final SectionBody body,
      final Experiment data) throws IOException {
    final ChartDriver drv;
    final Document doc;
    final Logger log;
    final DoubleDimension dim;
    final DocumentDimensions dims;
    final Accessor axs;
    final Label l;
    final ArraySetView<RunSet> runs;
    final ArrayList<Line2D> lines, back;
    boolean includeMean, includeHighQuantiles, includeAxisLabels, includeLegend;
    StatisticSeries ss;
    Instance inst;
    LineChart2D chart;
    String s;
    int z, fc;

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
    runs = data.select(InstanceSelectionUtils
        .oneMustHave2PointsOfAccessor(axs));

    if ((runs == null) || (runs.isEmpty())) {
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

    includeMean = includeHighQuantiles = includeAxisLabels = includeLegend = false;
    if (dim.contains(dims
        .getFigureDimensionsMM(EDefaultFigureSize.FOUR_IN_A_ROW))) {
      includeMean = true;
      if (dim.contains(dims
          .getFigureDimensionsMM(EDefaultFigureSize.THREE_IN_A_ROW))) {
        includeHighQuantiles = true;
        if (dim.contains(dims
            .getFigureDimensionsMM(EDefaultFigureSize.TWO_BY_TWO))) {
          includeAxisLabels = true;
          if (dim.contains(dims
              .getFigureDimensionsMM(EDefaultFigureSize.COLUMN_WIDE))) {
            includeLegend = true;
          }
        }
      }
    }

    fc = 0;
    lines = new ArrayList<>();
    back = new ArrayList<>();
    try (FigureSeries fs = body
        .figureSeries(Label.AUTO_LABEL,
            dim,//
            (_ExperimentProgressBaseA.CAP_1 + data.name()
                + _ExperimentInstanceProgressDiagrams.CAP_2
                + Macros.F_BEST_RELATIVE.getPlaceholder()
                + _ExperimentInstanceProgressDiagrams.CAP_3
                + axs.getLongName(true) + (axs.isScaled() ? (_ExperimentInstanceProgressDiagrams.CAP_4A1
                + axs.getScaleString() + _ExperimentInstanceProgressDiagrams.CAP_4A2)
                : (_ExperimentInstanceProgressDiagrams.CAP_4B1
                    + axs.getShortName() + _ExperimentInstanceProgressDiagrams.CAP_4B2))),//
            (_ExperimentInstanceProgressDiagrams.SUB_CAP_1 + "XXXXX"),//$NON-NLS-1$
            true)) {

      try (FigureSeriesCaption cap = fs.figureSeriesCaption()) {
        cap.write(_ExperimentProgressBaseA.CAP_1);
        cap.macroInvoke(this.getRoot().getShortName(data));
        cap.write(_ExperimentInstanceProgressDiagrams.CAP_2);
        cap.macroInvoke(Macros.F_BEST_RELATIVE);
        cap.write(_ExperimentInstanceProgressDiagrams.CAP_3);
        axs.writeLongName(cap, true);
        if (axs.isScaled()) {
          cap.write(_ExperimentInstanceProgressDiagrams.CAP_4A1);
          axs.writeScale(cap);
          cap.write(_ExperimentInstanceProgressDiagrams.CAP_4A2);
        } else {
          cap.write(_ExperimentInstanceProgressDiagrams.CAP_4B1);
          axs.writeShortName(cap, false);
          cap.write(_ExperimentInstanceProgressDiagrams.CAP_4B2);
        }
      }

      for (final RunSet rs : data) {

        inst = rs.getInstance();

        ss = this.m_property.get(rs, doc);
        if ((ss != null) && ((z = ss.size()) > 1)
            && (ss.get(z - 1, StatisticSeries.DIM_COUNT) > 1)) {
          this.__makeFigure(ss, rs, inst, includeMean,
              includeHighQuantiles, lines, back);

          if ((fc <= 0) && (!(includeLegend))) {

            if (lines.isEmpty() && back.isEmpty()) {
              continue;
            }

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
                        ProgressUtils.DEFAULT_AXIS_DEF_TIME//
                            : ProgressUtils.DEFAULT_AXIS_DEF_COUNT);
                    chart.setLegendType(ELegendType.ONLY_LEGEND);
                    chart.addLines(lines);
                    chart.addBackgroundLines(back);
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
              cap.write(_ExperimentInstanceProgressDiagrams.SUB_CAP_1);
              cap.write(inst.name());
            }

            try {
              try (FigureBody fb = sf.figureBody(
                  this.makeURI(Module.GRAPHICS_FOLDER, data,
                      rs.getInstance()), dim)) {
                try (Graphic graph = fb.graphic()) {
                  chart = drv.createLineChart2D(//
                      axs.isTime() ? //
                      ProgressUtils.DEFAULT_AXIS_DEF_TIME//
                          : ProgressUtils.DEFAULT_AXIS_DEF_COUNT);
                  chart.addLines(lines);
                  chart.addBackgroundLines(back);
                  chart
                      .setLegendType(includeLegend ? ELegendType.PUT_LEGEND
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
    }

    super.writeSectionBody(body, data);
  }
}
