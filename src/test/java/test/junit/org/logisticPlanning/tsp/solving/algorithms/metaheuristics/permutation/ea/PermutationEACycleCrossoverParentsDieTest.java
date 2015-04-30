package test.junit.org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.ea;

import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.ea.PermutationEA;
import org.logisticPlanning.tsp.solving.operators.permutation.recombination.PermutationCycleCrossover;

/**
 * the test of the permutation EA with cycle crossover
 */
public class PermutationEACycleCrossoverParentsDieTest extends
    PermutationEAParentsDieTest {

  /** create */
  public PermutationEACycleCrossoverParentsDieTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected PermutationEA createAlgorithm() {
    PermutationEA ea;

    ea = super.createAlgorithm();
    ea.setBinaryOperator(new PermutationCycleCrossover());

    return ea;
  }
}
