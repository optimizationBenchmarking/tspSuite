package test.junit.org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.ea.crossover;

import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.ea.PermutationEA;
import org.logisticPlanning.tsp.solving.operators.permutation.recombination.PermutationPartiallyMappedCrossover;

import test.junit.org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.ea.PermutationEAParentsDieTest;

/**
 * the test of the permutation EA with partially-mapped crossover
 */
public class PermutationEAPartiallyMappedCrossoverParentsDieTest extends
    PermutationEAParentsDieTest {

  /** create */
  public PermutationEAPartiallyMappedCrossoverParentsDieTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected PermutationEA createAlgorithm() {
    PermutationEA ea;

    ea = super.createAlgorithm();
    ea.setBinaryOperator(new PermutationPartiallyMappedCrossover());

    return ea;
  }
}
