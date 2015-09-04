package test.junit.org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.ea.crossover;

import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.ea.PermutationEA;
import org.logisticPlanning.tsp.solving.operators.permutation.recombination.PermutationOrderCrossover;

import test.junit.org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.ea.PermutationEAParentsDieTest;

/**
 * the test of the permutation EA with order crossover
 */
public class PermutationEAOrderCrossoverParentsDieTest extends
    PermutationEAParentsDieTest {

  /** create */
  public PermutationEAOrderCrossoverParentsDieTest() {
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
