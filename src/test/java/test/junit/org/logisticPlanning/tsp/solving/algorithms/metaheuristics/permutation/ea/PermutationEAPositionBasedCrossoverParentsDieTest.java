package test.junit.org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.ea;

import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.ea.PermutationEA;
import org.logisticPlanning.tsp.solving.operators.permutation.recombination.PermutationPositionBasedCrossover;

/**
 * the test of the permutation EA with position-based crossover
 */
public class PermutationEAPositionBasedCrossoverParentsDieTest extends
PermutationEAParentsDieTest {

  /** create */
  public PermutationEAPositionBasedCrossoverParentsDieTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected PermutationEA createAlgorithm() {
    PermutationEA ea;

    ea = super.createAlgorithm();
    ea.setBinaryOperator(new PermutationPositionBasedCrossover());

    return ea;
  }
}
