package org.logisticPlanning.tsp.evaluation.data.conditions;

import org.logisticPlanning.tsp.benchmarking.instances.Instance;
import org.logisticPlanning.tsp.evaluation.data.RunSet;
import org.logisticPlanning.utils.collections.conditions.Condition;
import org.logisticPlanning.utils.collections.lists.ArraySetView;

/**
 * the selection condition for run sets
 */
public final class RunSetsForInstancesCondition extends Condition<RunSet> {

  /** the instances */
  private final ArraySetView<Instance> m_insts;

  /**
   * create
   * 
   * @param insts
   *          the instances
   */
  public RunSetsForInstancesCondition(final ArraySetView<Instance> insts) {
    super();
    this.m_insts = insts;
  }

  /** {@inheritDoc} */
  @Override
  public final boolean check(final RunSet param) {
    return ((param != null) && this.m_insts.contains(param.getInstance()));
  }

}
