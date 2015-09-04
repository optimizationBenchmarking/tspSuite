package test.junit.org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.ea.crossover;

import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.ea.PermutationEA;
import org.logisticPlanning.tsp.solving.operators.permutation.recombination.PermutationAlternatingPositionCrossover;

import test.junit.org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.ea.PermutationEAParentsSurviveTest;

/**
 * the test of the permutation EA with alternating position crossover
 */
public class PermutationEAAlternatingPositionCrossoverParentsSurviveTest
    extends PermutationEAParentsSurviveTest {

  /** create */
  public PermutationEAAlternatingPositionCrossoverParentsSurviveTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected PermutationEA createAlgorithm() {
    PermutationEA ea;

    ea = super.createAlgorithm();
    ea.setBinaryOperator(new PermutationAlternatingPositionCrossover());

    return ea;
  }
}
