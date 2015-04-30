package org.logisticPlanning.tsp.evaluation.data.properties.objective;

import org.logisticPlanning.tsp.benchmarking.instances.Instance;
import org.logisticPlanning.tsp.benchmarking.objective.Benchmark;

/**
 * the thresholds for objective values per instance
 */
public final class InstanceObjectiveThresholds {

  /** the thresholds */
  private final long[][] m_thresholds;

  /** the instance objective thresholds */
  InstanceObjectiveThresholds() {
    super();
    this.m_thresholds = new long[Instance.ALL_INSTANCES.size()][];
  }

  /**
   * Get the thresholds for a given instance. Warning: The returned array
   * must not be modified. If you must modify this data,
   * {@link java.lang.Object#clone() clone} it and modify the clone!
   * 
   * @param inst
   *          the instance
   * @return the thresholds
   */
  public final long[] getThresholds(final Instance inst) {
    final int idx;
    long[] t;

    idx = Instance.ALL_INSTANCES.indexOf(inst);
    if (idx < 0) {
      throw new IllegalArgumentException(String.valueOf(inst));
    }

    t = this.m_thresholds[idx];
    if (t == null) {
      this.m_thresholds[idx] = t = //
      Benchmark.getLogObjectiveValues(inst.optimum());
    }

    return t;
  }
}
