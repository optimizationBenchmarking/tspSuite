package org.logisticPlanning.tsp.solving.operators;

import java.io.PrintStream;

import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;
import org.logisticPlanning.tsp.solving.Individual;
import org.logisticPlanning.tsp.solving.TSPModule;
import org.logisticPlanning.utils.config.Configurable;
import org.logisticPlanning.utils.config.Configuration;

/**
 * <p>
 * A unary operator that randomly chooses one of its sub-operators.
 * </p>
 * <p>
 * Starting at version 0.9.8 of TSP Suite, we provide run-depending
 * initialization and finalization support for algorithms and algorithm
 * modules. The new class
 * {@link org.logisticPlanning.tsp.solving.TSPModule} is introduced as
 * base-class for all algorithm modules (such as
 * {@link org.logisticPlanning.tsp.solving.operators.Operator search
 * operators} or {@link org.logisticPlanning.tsp.solving.gpm.GPM
 * genotype-phenotype mappings}) and the
 * {@link org.logisticPlanning.tsp.solving.TSPAlgorithm TSP algorithms}
 * themselves. This new class provides two methods,
 * {@link org.logisticPlanning.tsp.solving.TSPModule#beginRun(org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction)}
 * and
 * {@link org.logisticPlanning.tsp.solving.TSPModule#endRun(org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction)}
 * which must be called before and after a run, respectively. Each
 * algorithm module must then invoke them recursively for all of its
 * sub-components. The new method
 * {@link org.logisticPlanning.tsp.solving.TSPAlgorithm#call(org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction)}
 * now acts as a wrapper for
 * {@link org.logisticPlanning.tsp.solving.TSPAlgorithm#solve(org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction)}
 * and invokes them. This allows for a more targeted allocation and
 * de-allocation of data structures for each run.
 * </p>
 * 
 * @param <P>
 *          the product type
 */
public final class MultiUnaryOperator<P> extends UnaryOperator<P> {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the sub-operators */
  private UnaryOperator<P>[] m_sub;

  /**
   * instantiate
   * 
   * @param sub
   *          the sub-operators
   */
  @SafeVarargs
  public MultiUnaryOperator(final UnaryOperator<P>... sub) {
    super("multiUnaryOperator"); //$NON-NLS-1$
    this.m_sub = sub.clone();
  }

  /** {@inheritDoc} */
  @Override
  public final void mutate(final Individual<P> dest,
      final ObjectiveFunction f, final Individual<P> parent) {
    this.m_sub[f.getRandom().nextInt(this.m_sub.length)].mutate(dest, f,
        parent);
  }

  /** {@inheritDoc} */
  @Override
  public final MultiUnaryOperator<P> clone() {
    final MultiUnaryOperator<P> res;
    final UnaryOperator<P>[] a;
    int i;

    res = ((MultiUnaryOperator<P>) (super.clone()));
    res.m_sub = a = res.m_sub.clone();

    for (i = a.length; (--i) >= 0;) {
      a[i] = ((UnaryOperator<P>) (a[i].clone()));
    }

    return res;
  }

  /** {@inheritDoc} */
  @Override
  public final void configure(final Configuration config) {
    super.configure(config);
    for (final UnaryOperator<P> o : this.m_sub) {
      o.configure(config);
    }
  }

  /** {@inheritDoc} */
  @Override
  public final void printConfiguration(final PrintStream ps) {
    super.printConfiguration(ps);

    Configurable.printKey("subMutators", ps); //$NON-NLS-1$
    Configurable.printlnObject(this.m_sub, ps);
  }

  /** {@inheritDoc} */
  @Override
  public final void printParameters(final PrintStream ps) {
    super.printParameters(ps);
    for (final UnaryOperator<P> o : this.m_sub) {
      o.printParameters(ps);
    }
  }

  /** {@inheritDoc} */
  @Override
  public void beginRun(final ObjectiveFunction f) {
    super.beginRun(f);
    TSPModule.invokeBeginRun(f, this.m_sub);
  }

  /** {@inheritDoc} */
  @Override
  public void endRun(final ObjectiveFunction f) {
    try {
      TSPModule.invokeEndRun(f, this.m_sub);
    } finally {
      super.endRun(f);
    }
  }
}
