package test.junit.org.logisticPlanning.tsp.solving.operators.permutation.recombination;

import org.logisticPlanning.tsp.solving.operators.permutation.recombination.PermutationCycleCrossover;

import test.junit.org.logisticPlanning.tsp.solving.operators.permutation.PermutationOperatorTest;

/**
 * test the permutation cycle recombination operator
 */
public class PermutationCycleCrossoverTest extends PermutationOperatorTest {

  /** instantiate */
  public PermutationCycleCrossoverTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected PermutationCycleCrossover createOperator() {
    return new PermutationCycleCrossover();
  }
}
