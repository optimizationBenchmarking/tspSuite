package test.junit.org.logisticPlanning.tsp.solving.algorithms;

import org.junit.Assert;
import org.logisticPlanning.tsp.benchmarking.instances.Instance;
import org.logisticPlanning.tsp.benchmarking.objective.Benchmark;
import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;
import org.logisticPlanning.tsp.solving.TSPAlgorithm;

import test.junit.org.logisticPlanning.tsp.benchmarking.objective.ObjectiveTest;

/**
 * an internal class for testing optimization algorithms
 */
final class _AlgorithmTest extends ObjectiveTest {

  /** owner */
  private final TSPAlgorithmSymmetricTest m_owner;

  /**
   * instantiate
   *
   * @param owner
   *          the owner
   */
  _AlgorithmTest(final TSPAlgorithmSymmetricTest owner) {
    super();
    this.m_owner = owner;
  }

  /** {@inheritDoc} */
  @Override
  public final boolean isInitializationNecessary() {
    return (this.m_owner.getInitAlgorithm() != null);
  }

  /** {@inheritDoc} */
  @Override
  public final void run(final Instance inst, final Benchmark bm,
      final ObjectiveFunction f) throws Throwable {
    final TSPAlgorithm algo;

    algo = this.m_owner.getAlgorithm();
    Assert.assertNotNull(TSPAlgorithmSymmetricTest.TSP_ALGO_NULL, algo);

    algo.call(f);
  }

  /** {@inheritDoc} */
  @Override
  public final void initialize(final Instance inst, final Benchmark bm,
      final ObjectiveFunction f) throws Throwable {
    final TSPAlgorithm algo;

    algo = this.m_owner.getInitAlgorithm();
    Assert.assertNotNull(
        "Error in test: Initialization algorithm disappeared!", //$NON-NLS-1$
        algo);

    algo.call(f);
  }
}
