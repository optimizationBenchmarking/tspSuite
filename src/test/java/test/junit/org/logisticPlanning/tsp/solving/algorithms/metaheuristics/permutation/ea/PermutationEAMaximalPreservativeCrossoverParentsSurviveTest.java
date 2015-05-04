package test.junit.org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.ea;

import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.ea.PermutationEA;
import org.logisticPlanning.tsp.solving.operators.permutation.recombination.PermutationMaximalPreservativeCrossover;

/**
 * the test of the permutation EA with maximal preservative crossover
 */
public class PermutationEAMaximalPreservativeCrossoverParentsSurviveTest
    extends PermutationEAParentsSurviveTest {

  /** create */
  public PermutationEAMaximalPreservativeCrossoverParentsSurviveTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected PermutationEA createAlgorithm() {
    PermutationEA ea;

    ea = super.createAlgorithm();
    ea.setBinaryOperator(new PermutationMaximalPreservativeCrossover());

    return ea;
  }
}
