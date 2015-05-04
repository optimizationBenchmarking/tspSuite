package org.logisticPlanning.tsp.evaluation.modules.impl.comparison.progress;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.logisticPlanning.tsp.benchmarking.instances.Instance;
import org.logisticPlanning.tsp.evaluation.data.Accessor;
import org.logisticPlanning.tsp.evaluation.data.Experiment;
import org.logisticPlanning.tsp.evaluation.data.ExperimentSet;
import org.logisticPlanning.tsp.evaluation.data.conditions.RunSetsForInstancesCondition;
import org.logisticPlanning.tsp.evaluation.data.properties.instance.SharedInstancesProperty;
import org.logisticPlanning.tsp.evaluation.data.properties.ranking.RankAggregate;
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
import org.logisticPlanning.utils.math.functions.power.Lg;
import org.logisticPlanning.utils.math.statistics.series.StatisticSeries;

/**
 * This evaluator will print diagrams showing the progress of and algorithm
 * over a given time measure over all benchmark instances.
 */
final class _ExperimentSetProgressDiagrams extends
    _ExperimentSetProgressBase {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the caption 1 */
  private static final String CAP_1 = "Performance Comparison of the "; //$NON-NLS-1$

  /** the caption 2 */
  private static final String CAP_2 = " algorithms aggregated (and appropriately scaled) over the benchmark data sets for which sufficient data exists, for different time measures.";//$NON-NLS-1$

  /** the sub caption 1 */
  private static final String SCAP_1 = (" over the ");//$NON-NLS-1$
  /** the sub caption 2 */
  private static final String SCAP_2 = (" for the ");//$NON-NLS-1$
  /** the sub caption 3 */
  private static final String SCAP_3 = (" scaled by ");//$NON-NLS-1$

  /**
   * create!
   *
   * @param owner
   *          the macro's owner
   * @param isActive
   *          should this module be active?
   */
  _ExperimentSetProgressDiagrams(final Module owner, final boolean isActive) {
    super("experimentSetProgress", owner, isActive); //$NON-NLS-1$
    this.m_size = EDefaultFigureSize.TWO_IN_A_ROW;
  }

  /** {@inheritDoc} */
  @Override
  protected final void initialize(final Header header,
      final ExperimentSet data) throws IOException {//
    for (final Accessor axs : Accessor.UNBIASED_TIME_MEASURES) {
      axs.define(header);
    }

    for (final Accessor axs : Accessor.UNBIASED_TIME_MEASURES) {
      SharedInstancesProperty.oneMustHave2PointsOfAccessor(axs)
          .initialize(header);
    }
    super.initialize(header, data);
  }

  /** {@inheritDoc} */
  @Override
  protected final void writeSectionTitle(final SectionTitle title,
      final ExperimentSet data) throws IOException {
    title.write("Progress over All Benchmark Instances "); //$NON-NLS-1$
  }

  /**
   * make a figure
   *
   * @param expset
   *          the experiment set
   * @param axs
   *          the accessor
   * @param lines
   *          the list for the lines
   * @param doc
   *          the document
   * @param insts
   *          the instances
   * @param areaLines
   *          the list of lines to be used for AUC computation
   * @throws IOException
   *           if io fails
   */
  private final void __makeFigure(final ExperimentSet expset,
      final ArraySetView<Instance> insts, final Accessor axs,
      final Document doc, final ArrayList<Line2D> lines,
      final ArrayList<Map.Entry<Experiment, Object>> areaLines)
      throws IOException {
    final int[] select;
    final UnaryFunction[] transform;
    final RunSetsForInstancesCondition choose;
    IDataCollection[] data;
    IDataCollection ld;

    lines.clear();

    transform = new UnaryFunction[] { Lg.INSTANCE, Identity.INSTANCE };
    select = new int[] { StatisticSeries.DIM_X,
        StatisticSeries.DIM_Y_MEDIAN };
    choose = new RunSetsForInstancesCondition(insts);

    for (final Experiment exp : expset) {
      data = ProgressUtils.transformRuns(axs, exp.select(choose));
      if (data == null) {
        throw new IllegalStateException();
      }

      ld = new TransformedCollectionView(new SubCollectionView(//
          new StatisticSeries(data, 0, 1, this.m_approxMaxPoints),//
          select), transform);
      lines.add(new Line2D(exp.shortName(),//
          null, ld, null, ELineMode.STAIRS_KEEP_LEFT));
      areaLines.add(new BasicMapEntry<Experiment, Object>(exp, ld));
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
    boolean includeAxisLabels, includeLegend;
    ArraySetView<Instance> insts;
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

    dims = doc.getDimensions();
    dim = dims.getFigureDimensionsMM(this.m_size);

    includeAxisLabels = includeLegend = false;
    if (dim.contains(dims.getFigureDimensionsMM(//
        EDefaultFigureSize.TWO_IN_A_ROW))) {
      includeAxisLabels = true;
      includeLegend = true;
    }

    fc = 0;
    try (FigureSeries fs = body.figureSeries(
        Label.AUTO_LABEL,
        dim,//
        _ExperimentSetProgressDiagrams.CAP_1 + data.size()
            + _ExperimentSetProgressDiagrams.CAP_2,
        (Macros.F_BEST_RELATIVE.getPlaceholder()
            + _ExperimentSetProgressDiagrams.SCAP_1 + 100
            + _ExperimentSetProgressDiagrams.SCAP_2
            + Accessor.DE.getShortName()
            + _ExperimentSetProgressDiagrams.SCAP_3
            + Accessor.DE.getScaleString() + '.'),//
        true)) {

      try (FigureSeriesCaption cap = fs.figureSeriesCaption()) {

        cap.write(_ExperimentSetProgressDiagrams.CAP_1);
        cap.writeIntInText(data.size(), false);
        cap.write(_ExperimentSetProgressDiagrams.CAP_2);
      }

      lines = new ArrayList<>();
      aucLines = new ArrayList<>();
      rag = new RankAggregate<>(data);
      for (final Accessor axs : Accessor.UNBIASED_TIME_MEASURES) {
        insts = SharedInstancesProperty.oneMustHave2PointsOfAccessor(axs)
            .get(data, doc);
        if ((insts == null) || (insts.isEmpty())) {
          continue;
        }

        this.__makeFigure(data, insts, axs, doc, lines, aucLines);
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

          try (SubFigureCaption cap = sf.subFigureCaption()) {
            cap.macroInvoke(Macros.F_BEST_RELATIVE);
            cap.write(_ExperimentSetProgressDiagrams.SCAP_1);
            cap.writeIntInText(insts.size(), false);
            cap.write(_ExperimentSetProgressDiagrams.SCAP_2);
            axs.writeShortName(cap);
            if (axs.isScaled()) {
              cap.write(_ExperimentSetProgressDiagrams.SCAP_3);
              axs.writeScale(cap);
            }
            cap.writeChar('.');
          }

          try {
            try (FigureBody fb = sf.figureBody(
                this.makeURI(Module.GRAPHICS_FOLDER, null, null,
                    axs.getShortName()), dim)) {
              try (Graphic graph = fb.graphic()) {
                chart = drv.createLineChart2D(//
                    axs.isTime() ? //
                    ProgressUtils.DEFAULT_AXIS_DEF_TIME//
                        : ProgressUtils.DEFAULT_AXIS_DEF_COUNT);
                chart.addLines(lines);
                fc++;
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
      body.writeSequence(new ExperimentSequence(this.getRoot(), data,
          true, true, body), ESequenceType.AND, false);
      body.write(" in terms of the ");//$NON-NLS-1$
      try (Emphasize em = body.emphasize()) {
        em.write("median");//$NON-NLS-1$
      }
      body.write(" of the relative error ");//$NON-NLS-1$
      body.macroInvoke(Macros.F_BEST_RELATIVE);
      body.write(" aggregated over the instances for which all experiments provided sufficient data. We draw these plots for different time measures. "); //$NON-NLS-1$
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

      body.writeLinebreak();
      this._doRanks(body, data, rag);
    }

    super.writeSectionBody(body, data);
  }
}
