package test.junit.org.logisticPlanning.tsp.solving.operators.permutation.recombination;

import org.logisticPlanning.tsp.solving.operators.permutation.recombination.PermutationOrderCrossover;

import test.junit.org.logisticPlanning.tsp.solving.operators.permutation.PermutationOperatorTest;

/**
 * test the permutation order recombination operator
 */
public class PermutationOrderCrossoverTest extends PermutationOperatorTest {

  /** instantiate */
  public PermutationOrderCrossoverTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected PermutationOrderCrossover createOperator() {
    return new PermutationOrderCrossover();
  }
}
