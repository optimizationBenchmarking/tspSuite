package test.junit.org.logisticPlanning.tsp.solving.operators;

import org.logisticPlanning.tsp.benchmarking.instances.Instance;
import org.logisticPlanning.tsp.benchmarking.objective.Benchmark;
import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;
import org.logisticPlanning.tsp.solving.Individual;

/**
 * the internal class to test an operator's behavior when all input
 * instances are different
 * 
 * @param <P>
 *          the product type
 */
final class _OperatorTestAllDifferent<P> extends _OperatorTest<P> {

  /**
   * instantiate
   * 
   * @param owner
   *          the owner
   */
  _OperatorTestAllDifferent(final SymmetricOperatorTest<P> owner) {
    super(owner);
  }

  /** {@inheritDoc} */
  @Override
  public void beforeRun(final Instance inst, final Benchmark bm,
      final ObjectiveFunction f) throws Throwable {
    for (final Individual<P> p : this.m_params) {
      this.m_owner.createRandomInstance(p, f);
    }
  }
}
