package test.junit.org.logisticPlanning.tsp.solving.algorithms.exact.branchAndBoundLittle1963;

import org.logisticPlanning.tsp.solving.algorithms.heuristics.savings.SavingsHeuristic;

/**
 * the test of the
 * {@link org.logisticPlanning.tsp.solving.algorithms.exact.branchAndBoundLittle1963.BABLittle1963
 * branch and bound algorithm} published by Little et al. in 1963.
 * initialized with a
 * {@link org.logisticPlanning.tsp.solving.algorithms.heuristics.savings.SavingsHeuristic
 * savings heuristic}.
 */
public class BABLittle1963SavingsTest extends BABLittle1963Test {

  /** create */
  public BABLittle1963SavingsTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected SavingsHeuristic createInitAlgorithm() {
    return new SavingsHeuristic();
  }
}
