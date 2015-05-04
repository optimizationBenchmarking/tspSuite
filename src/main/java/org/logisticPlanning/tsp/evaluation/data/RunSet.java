package org.logisticPlanning.tsp.evaluation.data;

import org.logisticPlanning.tsp.benchmarking.instances.Instance;

/**
 * <p>
 * A set of runs that all have been obtained with the same algorithm
 * configuration for the same
 * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance
 * benchmark instances}.
 * </p>
 */
public class RunSet extends _OwnedSet<Run, Experiment> {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the instance */
  final Instance m_inst;

  /**
   * instantiate
   *
   * @param inst
   *          the benchmark instance to which this run set belongs
   * @param data
   *          the data of the run set
   */
  public RunSet(final Instance inst, final Run[] data) {
    super(inst.name(), data);
    this.m_inst = inst;
    DataSet._setup(data, this);
  }

  /**
   * Get the instance to which this run set belongs
   *
   * @return the instance to which this run set belongs
   */
  public final Instance getInstance() {
    return this.m_inst;
  }

  /** {@inheritDoc} */
  @Override
  final int _compareTo(final Object o) {
    int r;
    if (o instanceof RunSet) {
      r = this.m_inst.compareTo(((RunSet) o).m_inst);
      if (r != 0) {
        return r;
      }
    }
    if (o instanceof Instance) {
      r = this.m_inst.compareTo((Instance) o);
      if (r != 0) {
        return r;
      }
    }
    return super._compareTo(o);
  }
}
