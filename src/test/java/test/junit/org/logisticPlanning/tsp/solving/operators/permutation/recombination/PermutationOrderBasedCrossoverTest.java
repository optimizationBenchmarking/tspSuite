package test.junit.org.logisticPlanning.tsp.solving.operators.permutation.recombination;

import org.logisticPlanning.tsp.solving.operators.permutation.recombination.PermutationOrderBasedCrossover;

import test.junit.org.logisticPlanning.tsp.solving.operators.permutation.PermutationOperatorTest;

/**
 * test the permutation order-based recombination operator
 */
public class PermutationOrderBasedCrossoverTest extends
PermutationOperatorTest {

  /** instantiate */
  public PermutationOrderBasedCrossoverTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected PermutationOrderBasedCrossover createOperator() {
    return new PermutationOrderBasedCrossover();
  }
}
