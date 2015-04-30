package test.junit.org.logisticPlanning.tsp.solving.operators.permutation.recombination;

import org.logisticPlanning.tsp.solving.operators.permutation.recombination.PermutationSavingsCrossover;

import test.junit.org.logisticPlanning.tsp.solving.operators.permutation.PermutationOperatorTest;

/**
 * Test the permutation savings recombination operator
 */
public class PermutationSavingsCrossoverTest extends
    PermutationOperatorTest {

  /** instantiate */
  public PermutationSavingsCrossoverTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected PermutationSavingsCrossover createOperator() {
    return new PermutationSavingsCrossover();
  }
}
