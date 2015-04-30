package test.junit.org.logisticPlanning.tsp.solving.algorithms.metaheuristics.developmental.devUpdatingEA;

import org.logisticPlanning.tsp.solving.algorithms.heuristics.mst.MSTHeuristic;

/**
 * the test of the developmental update of permutations initialized with an
 * MST heuristic
 */
public class DevUpdatingEAWithMSTTest extends DevUpdatingEATest {

  /** create */
  public DevUpdatingEAWithMSTTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected MSTHeuristic createInitAlgorithm() {
    return new MSTHeuristic();
  }
}
