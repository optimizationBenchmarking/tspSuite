package test.junit.org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.ea;

import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.ea.PermutationEA;
import org.logisticPlanning.tsp.solving.operators.permutation.recombination.PermutationHeuristicCrossover;

/**
 * the test of the permutation EA with heuristic crossover
 */
public class PermutationEAHeuristicCrossoverParentsDieTest extends
PermutationEAParentsDieTest {

  /** create */
  public PermutationEAHeuristicCrossoverParentsDieTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected PermutationEA createAlgorithm() {
    PermutationEA ea;

    ea = super.createAlgorithm();
    ea.setBinaryOperator(new PermutationHeuristicCrossover());

    return ea;
  }
}
