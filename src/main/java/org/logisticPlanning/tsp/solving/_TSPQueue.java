package org.logisticPlanning.tsp.solving;

import java.io.PrintStream;

import org.logisticPlanning.tsp.benchmarking.instances.Instance;
import org.logisticPlanning.tsp.benchmarking.objective.Benchmark;
import org.logisticPlanning.utils.collections.lists.ArrayListView;
import org.logisticPlanning.utils.config.Configurable;
import org.logisticPlanning.utils.config.Configuration;

/**
 * a queue holding information for running a tsp algorithm
 */
final class _TSPQueue extends Configurable {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the instances */
  private final ArrayListView<Instance> m_instances;

  /** the configuration */
  private Configuration m_cfg;

  /** the current benchmark */
  private volatile Benchmark m_bm;

  /** the index */
  private volatile int m_idx;

  /**
   * instantiate
   *
   * @param inst
   *          the instances
   */
  _TSPQueue(final ArrayListView<Instance> inst) {
    super("queue"); //$NON-NLS-1$
    this.m_instances = inst;
    this.m_idx = (-1);
  }

  /** {@inheritDoc} */
  @Override
  public final void configure(final Configuration config) {
    super.configure(config);
    this.m_cfg = config;
  }

  /** {@inheritDoc} */
  @Override
  public final void printConfiguration(final PrintStream ps) {
    Benchmark bm;

    super.printConfiguration(ps);

    if (this.m_cfg != null) {
      bm = this.next();
      if (bm == null) {
        bm = new Benchmark(this.m_instances.first());
        bm.configure(this.m_cfg);
      }
      bm.printConfiguration(ps);
    }
  }

  /** {@inheritDoc} */
  @Override
  public final void printParameters(final PrintStream ps) {
    Benchmark bm;

    super.printParameters(ps);

    if (this.m_cfg != null) {
      bm = this.next();
      if (bm == null) {
        bm = new Benchmark(this.m_instances.first());
        bm.configure(this.m_cfg);
      }
      bm.printParameters(ps);
    }
  }

  /**
   * get the next benchmark to work on
   *
   * @return the next benchmark to work on
   */
  final Benchmark next() {
    Benchmark bm;

    bm = this.m_bm;
    if ((bm != null) && (bm.remainingRunCount() > 0)) {
      return bm;
    }

    for (;;) {
      this.m_bm = bm = null;

      if ((++this.m_idx) >= this.m_instances.size()) {
        return null;
      }

      this.m_bm = bm = new Benchmark(this.m_instances.get(this.m_idx));
      bm.configure(this.m_cfg);

      if (bm.remainingRunCount() > 0) {
        return bm;
      }
    }
  }
}
