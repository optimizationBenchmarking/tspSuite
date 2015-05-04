package test.junit.org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.ea;

import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.ea.PermutationEA;
import org.logisticPlanning.tsp.solving.operators.permutation.recombination.PermutationOrderBasedCrossover;

/**
 * the test of the permutation EA with order-based crossover
 */
public class PermutationEAOrderBasedCrossoverParentsSurviveTest extends
PermutationEAParentsSurviveTest {

  /** create */
  public PermutationEAOrderBasedCrossoverParentsSurviveTest() {
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
