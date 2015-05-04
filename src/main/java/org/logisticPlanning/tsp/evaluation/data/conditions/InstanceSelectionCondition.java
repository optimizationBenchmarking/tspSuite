package org.logisticPlanning.tsp.evaluation.data.conditions;

import org.logisticPlanning.tsp.benchmarking.instances.Instance;
import org.logisticPlanning.tsp.evaluation.data.Experiment;
import org.logisticPlanning.tsp.evaluation.data.ExperimentSet;
import org.logisticPlanning.tsp.evaluation.data.RunSet;
import org.logisticPlanning.utils.collections.conditions.Condition;
import org.logisticPlanning.utils.utils.HashUtils;

/**
 * the selection condition
 */
public final class InstanceSelectionCondition extends Condition<Instance> {

  /** the experiment set */
  private final ExperimentSet m_set;

  /** the condition that must be {@code true} for at least one run set */
  private final Condition<? super RunSet> m_exists;

  /** the condition that must be {@code true} for all run sets */
  private final Condition<? super RunSet> m_forAll;

  /** the hash code */
  private final int m_hc;

  /**
   * create
   *
   * @param set
   *          the set
   * @param exists
   *          the condition that must be {@code true} for at least one run
   *          set
   * @param forAll
   *          the condition the condition that must be {@code true} for all
   *          run sets
   */
  public InstanceSelectionCondition(final ExperimentSet set,
      final Condition<? super RunSet> exists,
      final Condition<? super RunSet> forAll) {
    super();

    if ((set == null) || (exists == null) || (forAll == null)) {
      throw new IllegalArgumentException();
    }

    this.m_set = set;
    this.m_forAll = forAll;
    this.m_exists = exists;

    this.m_hc = HashUtils.combineHashes(//
        HashUtils.combineHashes(//
            HashUtils.hashCode(this.getClass()),//
            HashUtils.hashCode(this.m_forAll)),//
            HashUtils.combineHashes(//
                HashUtils.hashCode(this.m_exists),//
                System.identityHashCode(this.m_set)));
  }

  /** {@inheritDoc} */
  @Override
  public final boolean check(final Instance param) {
    final Condition<? super RunSet> forAll, exists;
    boolean exi;
    RunSet rs;

    forAll = this.m_forAll;
    exists = this.m_exists;
    exi = false;
    for (final Experiment e : this.m_set) {
      rs = e.forInstance(param);
      if (rs == null) {
        return false;
      }
      if (!(forAll.check(rs))) {
        return false;
      }
      exi = (exi || exists.check(rs));
    }

    return exi;
  }

  /** {@inheritDoc} */
  @Override
  public final int hashCode() {
    return this.m_hc;
  }

  /** {@inheritDoc} */
  @Override
  public final boolean equals(final Object o) {
    final InstanceSelectionCondition c;

    if (o == this) {
      return true;
    }

    if (o instanceof InstanceSelectionCondition) {
      c = ((InstanceSelectionCondition) o);
      return ((this.m_hc == c.m_hc) && //
          this.m_forAll.equals(c.m_forAll) && //
          this.m_exists.equals(c.m_exists) && //
          (this.m_set == c.m_set));
    }
    return false;
  }
}
