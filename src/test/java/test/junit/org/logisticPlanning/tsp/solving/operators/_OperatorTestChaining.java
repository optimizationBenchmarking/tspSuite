package test.junit.org.logisticPlanning.tsp.solving.operators;

import java.util.Random;

import org.logisticPlanning.tsp.benchmarking.instances.Instance;
import org.logisticPlanning.tsp.benchmarking.objective.Benchmark;
import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;
import org.logisticPlanning.tsp.solving.Individual;

/**
 * the internal class to test an operator's behavior when all input
 * instances are results obtained with the same operator
 * 
 * @param <P>
 *          the product type
 */
final class _OperatorTestChaining<P> extends _OperatorTest<P> {

  /** the randomizer */
  private final Random m_r;

  /**
   * instantiate
   * 
   * @param owner
   *          the owner
   */
  _OperatorTestChaining(final SymmetricOperatorTest<P> owner) {
    super(owner);
    this.m_r = new Random();
  }

  /** {@inheritDoc} */
  @Override
  public void beforeRun(final Instance inst, final Benchmark bm,
      final ObjectiveFunction f) throws Throwable {
    final Individual<P> ind;

    ind = this.m_dest;
    for (final Individual<P> p : this.m_params) {
      if ((p.solution == null) || (ind.solution == null)) {
        this.m_owner.createRandomInstance(p, f);
      } else {
        if (this.m_r.nextDouble() <= (1d / this.m_params.length)) {
          p.assign(this.m_dest);
        }
      }
    }
  }
}
