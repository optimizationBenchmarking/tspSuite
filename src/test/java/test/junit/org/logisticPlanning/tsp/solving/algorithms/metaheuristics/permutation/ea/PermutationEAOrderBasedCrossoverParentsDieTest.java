package test.junit.org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.ea;

import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.ea.PermutationEA;
import org.logisticPlanning.tsp.solving.operators.permutation.recombination.PermutationOrderBasedCrossover;

/**
 * the test of the permutation EA with order-based crossover
 */
public class PermutationEAOrderBasedCrossoverParentsDieTest extends
    PermutationEAParentsDieTest {

  /** create */
  public PermutationEAOrderBasedCrossoverParentsDieTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected PermutationEA createAlgorithm() {
    PermutationEA ea;

    ea = super.createAlgorithm();
    ea.setBinaryOperator(new PermutationOrderBasedCrossover());

    return ea;
  }
}
