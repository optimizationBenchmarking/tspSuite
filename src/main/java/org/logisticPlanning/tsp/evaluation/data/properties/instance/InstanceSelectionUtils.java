package org.logisticPlanning.tsp.evaluation.data.properties.instance;

import org.logisticPlanning.tsp.evaluation.data.Accessor;
import org.logisticPlanning.tsp.evaluation.data.RunSet;
import org.logisticPlanning.tsp.evaluation.data.conditions.RunHas2DifferentPoints;
import org.logisticPlanning.tsp.evaluation.data.conditions.RunNotPrematurelyTerminated;
import org.logisticPlanning.utils.collections.conditions.CollectiveCondition;
import org.logisticPlanning.utils.collections.conditions.CompoundCondition;
import org.logisticPlanning.utils.collections.conditions.NotEmptyCondition;
import org.logisticPlanning.utils.math.functions.logic.LAnd;

/**
 * some utilities for selecting instances
 */
public final class InstanceSelectionUtils {

  /** the condition for non-empty run sets */
  public static final CompoundCondition<RunSet> ONE_RUN_MUST_BE_NOT_EMPTY = new CompoundCondition<>(
      LAnd.INSTANCE, NotEmptyCondition.INSTANCE,
      new CollectiveCondition<>(NotEmptyCondition.INSTANCE, false));

  /** the condition for non-empty, non-prematurely killed run sets */
  public static final CompoundCondition<RunSet> ONE_RUN_MUST_BE_NOT_PREMATURLY_TERMINATED = //
      new CompoundCondition<>(LAnd.INSTANCE, NotEmptyCondition.INSTANCE,
          new CollectiveCondition<>(RunNotPrematurelyTerminated.INSTANCE,
              false));

  /** the conditions */
  @SuppressWarnings("unchecked")
  private static final CompoundCondition<RunSet>[] __ONE_MUST_HAVE_2 = //
  new CompoundCondition[Accessor.ACCESSORS.size()];

  /**
   * Get the condition for non-empty, non-prematurely killed run sets, at
   * least one run must have two points with different values for a given
   * {@link org.logisticPlanning.tsp.evaluation.data.Accessor accessor}.
   *
   * @param axs
   *          the accessor
   * @return the condition
   */
  public static final CompoundCondition<RunSet> oneMustHave2PointsOfAccessor(
      final Accessor axs) {
    final int i;
    CompoundCondition<RunSet> c;

    i = axs.ordinal();
    c = InstanceSelectionUtils.__ONE_MUST_HAVE_2[i];
    if (c == null) {
      InstanceSelectionUtils.__ONE_MUST_HAVE_2[i] = c = new CompoundCondition<>(
          LAnd.INSTANCE, NotEmptyCondition.INSTANCE,
          new CollectiveCondition<>(
              RunHas2DifferentPoints.forAccessor(axs), false));
    }

    return c;
  }
}
