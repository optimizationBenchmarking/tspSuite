package org.logisticPlanning.tsp.evaluation.modules.impl.comparison.progress;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Map;

import org.logisticPlanning.tsp.evaluation.data.Experiment;
import org.logisticPlanning.tsp.evaluation.data.ExperimentSet;
import org.logisticPlanning.tsp.evaluation.data.properties.auc.AreaUnderCurve;
import org.logisticPlanning.tsp.evaluation.data.properties.auc.AreaUnderCurveComparator;
import org.logisticPlanning.tsp.evaluation.data.properties.ranking.RankAggregate;
import org.logisticPlanning.tsp.evaluation.data.properties.ranking.Ranking;
import org.logisticPlanning.tsp.evaluation.modules.impl.comparison.RankedComparisonModule;
import org.logisticPlanning.tsp.evaluation.modules.impl.descriptions.eval.DescAUC;
import org.logisticPlanning.tsp.evaluation.modules.impl.macros.Macros;
import org.logisticPlanning.tsp.evaluation.modules.impl.single.progress.ProgressUtils;
import org.logisticPlanning.tsp.evaluation.modules.spec.Module;
import org.logisticPlanning.utils.config.Configurable;
import org.logisticPlanning.utils.config.Configuration;
import org.logisticPlanning.utils.document.spec.EDefaultFigureSize;
import org.logisticPlanning.utils.document.spec.Header;
import org.logisticPlanning.utils.document.spec.SectionBody;
import org.logisticPlanning.utils.math.data.collection.CollectionPart;
import org.logisticPlanning.utils.math.data.collection.IDataCollection;
import org.logisticPlanning.utils.math.data.iteration.DataCollectionIterator2D;
import org.logisticPlanning.utils.math.data.iteration.StairsKeepLeftIterator2D;
import org.logisticPlanning.utils.math.data.point.Point2D;
import org.logisticPlanning.utils.math.statistics.EStatisticParameter;

/**
 * The internal base class for statistic diagrams.
 */
class _ExperimentSetProgressBase extends RankedComparisonModule {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the approximate maximum points along the x-axis */
  int m_approxMaxPoints;

  /** the approximate maximum points along the x-axis parameter */
  private final String m_approxMaxPointsParam;

  /** the figure size parameter */
  private final String m_sizeParam;

  /** the figure size */
  EDefaultFigureSize m_size;

  /** the auc description */
  private final DescAUC m_auc;

  /**
   * create!
   *
   * @param owner
   *          the macro's owner
   * @param name
   *          the name
   * @param isActive
   *          should this module be active?
   */
  _ExperimentSetProgressBase(final String name, final Module owner,
      final boolean isActive) {
    super(name + "Diagrams", owner, isActive); //$NON-NLS-1$

    this.m_sizeParam = (this.name() + Module.FIGURE_SIZE_PARAM);
    this.m_size = EDefaultFigureSize.FOUR_IN_A_ROW;

    this.m_approxMaxPointsParam = (this.name() + "ApproxMaxPoints");//$NON-NLS-1$
    this.m_approxMaxPoints = ProgressUtils.DEFAULT_MAX_POINTS;

    this.m_auc = this.getRoot().findInstance(DescAUC.class);
    this.addDependency(this.m_auc);
  }

  /**
   * Write a description of the ranking result
   *
   * @param body
   *          the body
   * @param data
   *          the experiment set
   * @param rag
   *          the rank aggregate
   * @throws IOException
   *           if i/o fails
   */
  void _doRanks(final SectionBody body, final ExperimentSet data,
      final RankAggregate<Experiment> rag) throws IOException {
    final Ranking<Experiment> ranking;
    ranking = rag.rank(EStatisticParameter.MEDIAN);

    body.write(//
        "For each algorithm and benchmark case, we compute the "); //$NON-NLS-1$
    body.macroInvoke(Macros.AUC);
    body.write(" discussed in "); //$NON-NLS-1$
    body.reference(this.m_auc.getLabel(data));
    body.write(". We limit the areas to the left by the biggest smallest x-coordinate over all methods. To the right, we chose the largest x-coordinate amongst all curves. Each curve is then extended by one point with that x-coordinate and the y-coordinate of its right-most point. This extension makes sense instead of cutting the curves off on the right since some algorithms may reach the optimum very quickly, which would make the comparison interval very small. We then compute "); //$NON-NLS-1$
    body.macroInvoke(Macros.AUC);
    body.write(//
        " for each algorithm and benchmark case, by using the exactly same scaling as in the figures (only the ranges of the axes may differ). For each benchmark case we then rank the methods according to their corresponding "); //$NON-NLS-1$
    body.macroInvoke(Macros.AUC);
    body.write(//
        " (smaller areas are better). The methods are then re-ranked according to their median rank over all benchmark cases, as discussed in "); //$NON-NLS-1$
    body.reference(this.m_descRanking.getLabel(data));
    body.writeChar('.');

    body.writeLinebreak();
    body.write("As a result,"); //$NON-NLS-1$
    this.summarizeRanks(ranking,
        body,//
        "either achieve better objective values or the same values faster, or both"); //$NON-NLS-1$
    this.propagateRanking(ranking, data, body.getDocument());
  }

  /**
   * Aggregate the experiment performance based on the area under the
   * curve. We we limit the areas to the left by the biggest smallest
   * x-coordinate over all methods and to the right with the largest
   * biggest x-coordinate.
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
    int li, ri;
    IDataCollection c;

    right = left = Double.NEGATIVE_INFINITY;
    for (final Map.Entry<Experiment, Object> e : lst) {
      c = ((IDataCollection) (e.getValue()));
      x = c.get((c.size() - 1), 0);
      if (x > right) {
        right = x;
      }
      x = c.get(0, 0);
      if (x > left) {
        left = x;
      }
    }

    for (final Map.Entry<Experiment, Object> e : lst) {
      c = ((IDataCollection) (e.getValue()));
      ri = c.size();

      for (li = 0; li < ri; li++) {
        if (c.get(li, 0) >= left) {
          break;
        }
      }

      if (li >= ri) {
        // if the curves have no common area, simply return
        return;
      }

      e.setValue(new AreaUnderCurve(new StairsKeepLeftIterator2D(//
          new DataCollectionIterator2D(//
              null,//
              new CollectionPart(c, li, (ri - li)),//
              new Point2D(right, c.get(ri - 1, 1))//
              ))));
    }

    agg.aggregateRanks(//
        Ranking.rank(AreaUnderCurveComparator.SMALLER_IS_BETTER,//
            lst.toArray(new Map.Entry[lst.size()])));
  }

  /** {@inheritDoc} */
  @Override
  public void configure(final Configuration config) {
    super.configure(config);

    this.m_size = config.getConstant(this.m_sizeParam,
        EDefaultFigureSize.class, EDefaultFigureSize.class, this.m_size);

    this.m_approxMaxPoints = config.getInt(this.m_approxMaxPointsParam,
        -1, Integer.MAX_VALUE, this.m_approxMaxPoints);
  }

  /** {@inheritDoc} */
  @Override
  public void printConfiguration(final PrintStream ps) {
    super.printConfiguration(ps);

    Configurable.printKey(this.m_sizeParam, ps);
    Configurable.printlnObject(this.m_size, ps);

    Configurable.printKey(this.m_approxMaxPointsParam, ps);
    ps.println(this.m_approxMaxPoints);
  }

  /** {@inheritDoc} */
  @Override
  public void printParameters(final PrintStream ps) {
    super.printParameters(ps);

    Configurable.printKey(this.m_sizeParam, ps);
    ps.println(Module.FIGURE_SIZE_PARAM_DESC_PREFIX + this.name());

    Configurable.printKey(this.m_approxMaxPointsParam, ps);
    ps.println("the approximate point limit when computing " + this.name()); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  protected void initialize(final Header header, final ExperimentSet data)
      throws IOException {//
    super.initialize(header, data);
    Macros.SCALE.define(header);
    Macros.F_OPTIMAL.define(header);
    Macros.F_BEST_RELATIVE.define(header);
    Macros.F_BEST.define(header);
    Macros.AUC.define(header);
  }
}
