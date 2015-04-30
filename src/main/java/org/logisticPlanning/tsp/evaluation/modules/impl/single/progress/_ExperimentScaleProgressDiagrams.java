package org.logisticPlanning.tsp.evaluation.modules.impl.single.progress;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.logisticPlanning.tsp.evaluation.Evaluator;
import org.logisticPlanning.tsp.evaluation.data.Accessor;
import org.logisticPlanning.tsp.evaluation.data.Experiment;
import org.logisticPlanning.tsp.evaluation.data.RunSet;
import org.logisticPlanning.tsp.evaluation.data.conditions.RunSetsForInstancesCondition;
import org.logisticPlanning.tsp.evaluation.data.properties.scale.AllScaleInstances;
import org.logisticPlanning.tsp.evaluation.data.properties.scale.AllScaleInstancesProperty;
import org.logisticPlanning.tsp.evaluation.data.properties.scale.SameScaleInstances;
import org.logisticPlanning.tsp.evaluation.modules.ModuleUtils;
import org.logisticPlanning.tsp.evaluation.modules.impl.macros.Macros;
import org.logisticPlanning.tsp.evaluation.modules.spec.Module;
import org.logisticPlanning.utils.collections.lists.ArraySetView;
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
import org.logisticPlanning.utils.math.statistics.EStatisticParameter;
import org.logisticPlanning.utils.math.statistics.series.StatisticSeries;

/**
 * This evaluator will print diagrams showing the progress of and algorithm
 * over a given time measure for a set of benchmark instance with a scale
 * of the same magnitude.
 */
final class _ExperimentScaleProgressDiagrams extends
    _ExperimentProgressBaseB {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** caption 2 */
  private static final String CAP_2 = " in terms of "; //$NON-NLS-1$
  /** caption 3 */
  private static final String CAP_3 = " with runs of the same scale (base "; //$NON-NLS-1$
  /** caption 4 */
  private static final String CAP_4 = ") grouped together, over the consumed "; //$NON-NLS-1$
  /** caption 5a */
  private static final String CAP_5A = " (with "; //$NON-NLS-1$
  /** caption 5b */
  private static final String CAP_5B = " scaled by "; //$NON-NLS-1$
  /** caption 5c */
  private static final String CAP_5C = " before the grouping) "; //$NON-NLS-1$
  /** caption 6 */
  private static final String CAP_6 = " printed on a log-scaled axis)."; //$NON-NLS-1$
  /** sub caption 1 */
  private static final String SUB_CAP_1 = " instances with "; //$NON-NLS-1$
  /** sub caption 2 */
  private static final String SUB_CAP_2 = " instance with "; //$NON-NLS-1$

  /** the property */
  private final AllScaleInstancesProperty<Experiment> m_property;
  /** the accessor */
  private final Accessor m_accessor;

  /**
   * create!
   * 
   * @param owner
   *          the macro's owner
   * @param property
   *          the property
   * @param axs
   *          the accessor
   * @param isActive
   *          should this module be active?
   */
  _ExperimentScaleProgressDiagrams(final Module owner, final Accessor axs,
      final AllScaleInstancesProperty<Experiment> property,
      final boolean isActive) {
    super(("experimentScale" + property.getBase() + //$NON-NLS-1$
        "Progress" + axs.getShortName()), owner, isActive); //$NON-NLS-1$
    this.m_property = property;
    this.m_accessor = axs;
  }

  /** {@inheritDoc} */
  @Override
  protected final void initialize(final Header header,
      final Experiment data) throws IOException {//
    super.initialize(header, data);
    this.m_property.initialize(header);
    this.m_accessor.define(header);
  }

  /** {@inheritDoc} */
  @Override
  protected final void writeSectionTitle(final SectionTitle title,
      final Experiment data) throws IOException {
    title.write("Progress of "); //$NON-NLS-1$
    title.macroInvoke(this.getRoot().getShortName(data));
    title.write(" in Terms of "); //$NON-NLS-1$
    this.m_accessor.writeShortName(title, false);
    title.write(" Aggregated over Problem Scale "); //$NON-NLS-1$
    title.writeInt(this.m_property.getBase());
  }

  /**
   * make a figure
   * 
   * @param collection
   *          the run set collection to use
   * @param includeMean
   *          also print the mean
   * @param includeHighQuantiles
   *          also print high quantiles
   * @param lines
   *          the list of lines
   * @param back
   *          the list of background lines
   * @throws IOException
   *           if io fails
   */
  @SuppressWarnings("unused")
  private final void __makeFigure(final Collection<RunSet> collection,
      final boolean includeMean, final boolean includeHighQuantiles,
      final ArrayList<Line2D> lines, final ArrayList<Line2D> back)
      throws IOException {
    final StatisticSeries series;
    IDataCollection[] data;
    UnaryFunction[] transform;

    lines.clear();
    back.clear();

    data = ProgressUtils.transformRuns(this.m_accessor, collection);
    if ((data == null) || (data.length <= 0)) {
      return;
    }

    series = new StatisticSeries(data, 0, 1, this.m_approxMaxPoints);
    transform = new UnaryFunction[] { Lg.INSTANCE, Identity.INSTANCE };

    if (includeHighQuantiles) {
      lines.add(new Line2D(EStatisticParameter.PERCENTILE_95
          .getShortName(), null,// //
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
        back.add(//
        new Line2D(null,
            null,//
            new TransformedCollectionView(coll, transform), null,
            ELineMode.STAIRS_KEEP_LEFT));
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
    final int base;
    final AllScaleInstances instances;
    final ArrayList<Line2D> lines, back;
    ArraySetView<RunSet> list;
    SameScaleInstances rss;
    boolean includeMean, includeHighQuantiles, includeAxisLabels, includeLegend;
    LineChart2D chart;
    String s;
    int fc, size;

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

    instances = this.m_property.get(data, doc);
    if ((instances == null) || (instances.size() <= 0)) {
      s = Evaluator.WARNING_NO_DATA;
      body.write(s);
      log = this.getLogger();
      if ((log != null) && (log.isLoggable(Level.WARNING))) {
        log.warning(s);
      }
      return;
    }

    base = instances.getBase();
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

    lines = new ArrayList<>();
    back = new ArrayList<>();

    axs = this.m_accessor;
    fc = 0;
    try (FigureSeries fs = body
        .figureSeries(Label.AUTO_LABEL,
            dim,//
            (_ExperimentProgressBaseA.CAP_1
                + data.name()
                + _ExperimentScaleProgressDiagrams.CAP_2
                + Macros.F_BEST_RELATIVE.getPlaceholder()
                + _ExperimentScaleProgressDiagrams.CAP_3
                + base
                + _ExperimentScaleProgressDiagrams.CAP_4
                + axs.getLongName(true)
                + ' '
                + axs.getShortName()
                + 's'
                + (axs.isScaled() ? (_ExperimentScaleProgressDiagrams.CAP_5A
                    + axs.getShortName()
                    + _ExperimentScaleProgressDiagrams.CAP_5B
                    + axs.getScaleString() + _ExperimentScaleProgressDiagrams.CAP_5C)
                    : ("")) + //$NON-NLS-1$
            _ExperimentScaleProgressDiagrams.CAP_6),//
            (_ExperimentScaleProgressDiagrams.SUB_CAP_1 + "XXXXXXXXX"),//$NON-NLS-1$
            true)) {

      try (FigureSeriesCaption cap = fs.figureSeriesCaption()) {
        cap.write(_ExperimentProgressBaseA.CAP_1);
        cap.macroInvoke(this.getRoot().getShortName(data));
        cap.write(_ExperimentScaleProgressDiagrams.CAP_2);
        cap.macroInvoke(Macros.F_BEST_RELATIVE);
        cap.write(_ExperimentScaleProgressDiagrams.CAP_3);
        cap.writeInt(base);
        cap.write(_ExperimentScaleProgressDiagrams.CAP_4);
        axs.writeLongName(cap, true);
        cap.writeChar(' ');
        axs.writeShortName(cap, true);
        if (axs.isScaled()) {
          cap.write(_ExperimentScaleProgressDiagrams.CAP_5A);
          axs.writeShortName(cap, false);
          cap.write(_ExperimentScaleProgressDiagrams.CAP_5B);
          axs.writeScale(cap);
          cap.write(_ExperimentScaleProgressDiagrams.CAP_5C);
        }
        cap.write(_ExperimentScaleProgressDiagrams.CAP_6);
      }

      for (final SameScaleInstances sinsts : instances) {
        list = data.select(new RunSetsForInstancesCondition(sinsts));
        size = list.size();
        if (size <= 0) {
          continue;
        }
        this.__makeFigure(list, includeMean, includeHighQuantiles, lines,
            back);

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

          try (SubFigureCaption cap = sf.subFigureCaption()) {
            cap.writeInt(size);
            cap.write((size <= 1) ? _ExperimentScaleProgressDiagrams.SUB_CAP_2
                : _ExperimentScaleProgressDiagrams.SUB_CAP_1);
            try (InlineMath im = cap.inlineMath()) {
              try (MathOp mo1 = im.mathOp(EMathOp.CMP_LESS_OR_EQUAL)) {
                try (MathOpParam pa1 = mo1.mathOpParam()) {
                  try (MathOp mo2 = im.mathOp(EMathOp.CMP_LESS_OR_EQUAL)) {
                    try (MathOpParam pa2 = mo1.mathOpParam()) {
                      pa2.writeInt(sinsts.getLowerScaleBound());
                    }
                    try (MathOpParam pa3 = mo1.mathOpParam()) {
                      pa3.macroInvoke(Macros.SCALE);
                    }
                  }
                }
                try (MathOpParam pa4 = mo1.mathOpParam()) {
                  pa4.writeInt(sinsts.getUpperScaleBound());
                }
              }
            }
          }

          try {
            try (FigureBody fb = sf.figureBody(
                this.makeURI(Module.GRAPHICS_FOLDER, data, null,
                    (sinsts.getLowerScaleBound() + "_to_" + //$NON-NLS-1$
                    sinsts.getUpperScaleBound())), dim)) {
              try (Graphic graph = fb.graphic()) {
                chart = drv.createLineChart2D(//
                    axs.isTime() ? //
                    ProgressUtils.DEFAULT_AXIS_DEF_TIME//
                        : ProgressUtils.DEFAULT_AXIS_DEF_COUNT);
                fc++;
                chart.addLines(lines);
                chart.addBackgroundLines(back);
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
      body.write(" we plot the performance of the ");//$NON-NLS-1$
      body.macroInvoke(this.getRoot().getLongName(data));
      body.write(" (");//$NON-NLS-1$
      body.macroInvoke(this.getRoot().getShortName(data));
      body.write(") in terms of the relative error ");//$NON-NLS-1$
      body.macroInvoke(Macros.F_BEST_RELATIVE);
      body.write(" aggregated over orders of scale with base ");//$NON-NLS-1$
      body.writeInt(base);
      body.write(", resulting in "); //$NON-NLS-1$
      body.writeIntInText(fc, false);
      body.write(" diagrams. "); //$NON-NLS-1$
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
      body.write("). The runs are grouped by powers of ");//$NON-NLS-1$
      body.writeInt(base);
      body.write(", e.g., we group the runs with a scale ");//$NON-NLS-1$
      body.macroInvoke(Macros.SCALE);
      body.write(" between ");//$NON-NLS-1$
      rss = instances.first();
      body.writeInt(rss.getLowerScaleBound());
      body.write(" and ");//$NON-NLS-1$
      body.writeInt(rss.getUpperScaleBound());
      body.write(" into one group ");//$NON-NLS-1$
      if (fc > 0) {
        body.write(" and those with ");//$NON-NLS-1$
        body.macroInvoke(Macros.SCALE);
        body.write(" between ");//$NON-NLS-1$
        rss = instances.last();
        body.writeInt(rss.getLowerScaleBound());
        body.write(" and ");//$NON-NLS-1$
        body.writeInt(rss.getUpperScaleBound());
        body.write(" into another.");//$NON-NLS-1$
      } else {
        body.writeChar('.');
      }

      if (axs.isScaled()) {
        body.write(" Before this aggregation, the ");//$NON-NLS-1$
        axs.writeShortName(body, false);
        body.write(" of each run are scaled by ");//$NON-NLS-1$
        axs.writeScale(body);
        body.writeChar('.');
      }

      body.write(" In order to get a better view of the data, we use a logarithmically-scaled axis (to base "); //$NON-NLS-1$
      body.writeInt(10);
      body.write(").");//$NON-NLS-1$
    }

    super.writeSectionBody(body, data);
  }
}
