package test.junit.org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.ea;

import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.ea.PermutationEA;
import org.logisticPlanning.tsp.solving.operators.permutation.recombination.PermutationSavingsCrossover;

/**
 * the test of the permutation EA with savings crossover
 */
public class PermutationEASavingsCrossoverParentsDieTest extends
    PermutationEAParentsDieTest {

  /** create */
  public PermutationEASavingsCrossoverParentsDieTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected PermutationEA createAlgorithm() {
    PermutationEA ea;

    ea = super.createAlgorithm();
    ea.setBinaryOperator(new PermutationSavingsCrossover());

    return ea;
  }
}
