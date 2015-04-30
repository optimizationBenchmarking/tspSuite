package org.logisticPlanning.tsp.evaluation.modules.impl.single.progress;

import java.util.ArrayList;
import java.util.Collection;

import org.logisticPlanning.tsp.benchmarking.objective.DataPoint;
import org.logisticPlanning.tsp.evaluation.data.Accessor;
import org.logisticPlanning.tsp.evaluation.data.Run;
import org.logisticPlanning.tsp.evaluation.data.RunSet;
import org.logisticPlanning.tsp.evaluation.data.conditions.RunNotPrematurelyTerminated;
import org.logisticPlanning.tsp.evaluation.data.properties.StatisticRunProperty;
import org.logisticPlanning.utils.graphics.chart.spec.range.AxisEnd;
import org.logisticPlanning.utils.graphics.chart.spec.range.AxisRange2DDef;
import org.logisticPlanning.utils.graphics.chart.spec.range.EValueSelector;
import org.logisticPlanning.utils.graphics.chart.spec.range.FixedAxisEnd;
import org.logisticPlanning.utils.graphics.chart.spec.range.MultipleOfAxisBigEnd;
import org.logisticPlanning.utils.graphics.chart.spec.range.MultipleOfAxisSmallEnd;
import org.logisticPlanning.utils.graphics.chart.spec.range.ValueSelectionAxisBigEnd;
import org.logisticPlanning.utils.math.data.collection.IDataCollection;
import org.logisticPlanning.utils.math.data.collection.SubListView;
import org.logisticPlanning.utils.math.data.collection.TransformedCollectionView;
import org.logisticPlanning.utils.math.functions.UnaryFunction;
import org.logisticPlanning.utils.math.functions.arithmetic.Identity;
import org.logisticPlanning.utils.math.functions.compound.FixedScale;
import org.logisticPlanning.utils.math.statistics.aggregates.Maximum;
import org.logisticPlanning.utils.math.statistics.aggregates.Median;

/**
 * Some utility methods for making progress diagrams
 */
public final class ProgressUtils {

  /** the axis range definition for FEs and DEs */
  public static final AxisRange2DDef DEFAULT_AXIS_DEF_COUNT;

  /** the axis range definition for runtime and normalized runtime */
  public static final AxisRange2DDef DEFAULT_AXIS_DEF_TIME;

  static {
    final AxisEnd a, b, c, d;

    a = new MultipleOfAxisSmallEnd(0, EValueSelector.FINITE_POINT, 0.5d);
    b = new MultipleOfAxisBigEnd(0, 0.5d);
    c = new FixedAxisEnd(0d);
    d = new ValueSelectionAxisBigEnd(1, EValueSelector.FINITE_VALUE,
        new Maximum(), new Median(),//
        new double[] { 0.05d, 0.1d, 0.25d, 0.5d, 0.75d, 1d, 2d, }, true);

    DEFAULT_AXIS_DEF_COUNT = new AxisRange2DDef(a, b, c, d);
    DEFAULT_AXIS_DEF_TIME = new AxisRange2DDef(a, b, true, c, d, false);
  }

  /** the default maximum number of points collected in progress diagrams */
  public static final int DEFAULT_MAX_POINTS = StatisticRunProperty.DEFAULT_MAX_POINTS;

  /** the internal selector */
  private static final int[][] __SELECT = new int[Accessor.ACCESSORS
      .size()][];

  /**
   * Define a transformation which selects a given dimension and creates
   * data collections representing scaled x-values and relative objective
   * values.
   * 
   * @param axs
   *          the accessor
   * @param runs
   *          the runs
   * @param dest
   *          the destination
   */
  public static final void addTransformedRuns(final Accessor axs,
      final RunSet runs, final Collection<IDataCollection> dest) {
    final int ord;
    final long scale;
    final UnaryFunction[] transform;
    int[] select;
    IDataCollection col;

    ord = axs.ordinal();

    select = ProgressUtils.__SELECT[ord];
    if (select == null) {
      ProgressUtils.__SELECT[ord] = select = new int[] { ord,
          DataPoint.RELATIVE_F_INDEX };
    }

    scale = axs.calculateScale(runs.getInstance().n());

    if (scale != 0l) {
      transform = new UnaryFunction[] {
          FixedScale.scale(Identity.INSTANCE, (1d / scale)),
          Identity.INSTANCE };
    } else {
      transform = null;
    }

    for (final Run run : runs) {
      if (!(RunNotPrematurelyTerminated.INSTANCE.check(run))) {
        continue;
      }

      col = new SubListView(run, select);
      if (transform != null) {
        col = new TransformedCollectionView(col, transform);
      }
      dest.add(col);
    }
  }

  /**
   * Define a transformation which selects a given dimension and creates
   * data collections representing scaled x-values and relative objective
   * values.
   * 
   * @param axs
   *          the accessor
   * @param runsets
   *          the runs sets
   * @param dest
   *          the destination
   */
  public static final void addTransformedRuns(final Accessor axs,
      final Collection<RunSet> runsets,
      final Collection<IDataCollection> dest) {
    for (final RunSet runs : runsets) {
      ProgressUtils.addTransformedRuns(axs, runs, dest);
    }
  }

  /**
   * Transformation a set of run sets to a data collection array,
   * 
   * @param axs
   *          the accessor
   * @param runsets
   *          the runs sets
   * @return the data collection array
   */
  public static final IDataCollection[] transformRuns(final Accessor axs,
      final Collection<RunSet> runsets) {
    final ArrayList<IDataCollection> lst;
    final int s;

    lst = new ArrayList<>();
    ProgressUtils.addTransformedRuns(axs, runsets, lst);
    s = lst.size();
    if (s <= 0) {
      return null;
    }
    return lst.toArray(new IDataCollection[s]);
  }

}
