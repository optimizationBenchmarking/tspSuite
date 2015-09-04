package test.junit.org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.ea.crossover;

import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.ea.PermutationEA;
import org.logisticPlanning.tsp.solving.operators.permutation.recombination.PermutationEdgeCrossover;

import test.junit.org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.ea.PermutationEAParentsSurviveTest;

/**
 * the test of the permutation EA with edge crossover
 */
public class PermutationEAEdgeCrossoverParentsSurviveTest extends
    PermutationEAParentsSurviveTest {

  /** create */
  public PermutationEAEdgeCrossoverParentsSurviveTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected PermutationEA createAlgorithm() {
    PermutationEA ea;

    ea = super.createAlgorithm();
    ea.setBinaryOperator(new PermutationEdgeCrossover());

    return ea;
  }
}
