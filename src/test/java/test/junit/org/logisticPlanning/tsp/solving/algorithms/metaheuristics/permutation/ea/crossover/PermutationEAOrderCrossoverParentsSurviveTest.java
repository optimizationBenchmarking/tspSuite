package test.junit.org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.ea.crossover;

import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.ea.PermutationEA;
import org.logisticPlanning.tsp.solving.operators.permutation.recombination.PermutationOrderCrossover;

import test.junit.org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.ea.PermutationEAParentsSurviveTest;

/**
 * the test of the permutation EA with order crossover
 */
public class PermutationEAOrderCrossoverParentsSurviveTest extends
    PermutationEAParentsSurviveTest {

  /** create */
  public PermutationEAOrderCrossoverParentsSurviveTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected PermutationEA createAlgorithm() {
    PermutationEA ea;

    ea = super.createAlgorithm();
    ea.setBinaryOperator(new PermutationOrderCrossover());

    return ea;
  }
}
