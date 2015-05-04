package test.junit.org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.ea;

import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.ea.PermutationEA;
import org.logisticPlanning.tsp.solving.operators.permutation.recombination.PermutationPartiallyMappedCrossover;

/**
 * the test of the permutation EA with partially-mapped crossover
 */
public class PermutationEAPartiallyMappedCrossoverParentsSurviveTest
extends PermutationEAParentsSurviveTest {

  /** create */
  public PermutationEAPartiallyMappedCrossoverParentsSurviveTest() {
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
