package org.logisticPlanning.tsp.evaluation.data.conditions;

import org.logisticPlanning.tsp.benchmarking.objective.DataPoint;
import org.logisticPlanning.tsp.evaluation.data.Accessor;
import org.logisticPlanning.tsp.evaluation.data.Run;
import org.logisticPlanning.utils.collections.conditions.Condition;
import org.logisticPlanning.utils.utils.HashUtils;

/**
 * A condition that is {@code true} if a run was not prematurely terminated
 * and has at least 2 points with different values for a given accessor.
 */
public class RunHas2DifferentPoints extends Condition<Run> {

  /** the instances */
  private static final RunHas2DifferentPoints[] __INSTANCES = //
      new RunHas2DifferentPoints[Accessor.ACCESSORS.size()];

  /** the accessor */
  private final Accessor m_axs;

  /** the hash code */
  private final int m_hc;

  /**
   * create
   *
   * @param axs
   *          the accessor
   */
  private RunHas2DifferentPoints(final Accessor axs) {
    super();

    if (axs == null) {
      throw new IllegalArgumentException();
    }

    this.m_axs = axs;
    this.m_hc = HashUtils.combineHashes(//
        HashUtils.hashCode(axs),//
        HashUtils.hashCode(RunHas2DifferentPoints.class));
  }

  /** {@inheritDoc} */
  @Override
  public final boolean check(final Run param) {
    final int s;
    final DataPoint y;

    if (param == null) {
      return false;
    }

    if ((s = param.size()) < 2) {
      return false;
    }

    y = param.get(s - 1);
    if (y.isPrematureTermination()) {
      return false;
    }

    return (Double.compare(this.m_axs.fromPoint(y),
        this.m_axs.fromPoint(param.get(0))) != 0);
  }

  /** {@inheritDoc} */
  @Override
  public final boolean equals(final Object o) {
    return ((o == this) || //
        ((o instanceof RunHas2DifferentPoints) && //
            (((RunHas2DifferentPoints) o).m_axs == this.m_axs)));
  }

  /** {@inheritDoc} */
  @Override
  public final int hashCode() {
    return this.m_hc;
  }

  /**
   * Get the instance for a given accessor
   *
   * @param axs
   *          the accessor
   * @return the corresponding instance
   */
  public static final RunHas2DifferentPoints forAccessor(final Accessor axs) {
    final int i;
    RunHas2DifferentPoints d;

    i = axs.ordinal();
    d = RunHas2DifferentPoints.__INSTANCES[i];
    if (d == null) {
      RunHas2DifferentPoints.__INSTANCES[i] = d = new RunHas2DifferentPoints(
          axs);
    }

    return d;
  }
}
