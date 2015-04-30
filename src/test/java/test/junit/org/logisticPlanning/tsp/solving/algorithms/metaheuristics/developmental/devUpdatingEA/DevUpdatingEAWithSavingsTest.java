package test.junit.org.logisticPlanning.tsp.solving.algorithms.metaheuristics.developmental.devUpdatingEA;

import org.logisticPlanning.tsp.solving.algorithms.heuristics.savings.SavingsHeuristic;

/**
 * the test of the developmental update of permutations initialized with an
 * MST heuristic
 */
public class DevUpdatingEAWithSavingsTest extends DevUpdatingEATest {

  /** create */
  public DevUpdatingEAWithSavingsTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected SavingsHeuristic createInitAlgorithm() {
    return new SavingsHeuristic();
  }
}
