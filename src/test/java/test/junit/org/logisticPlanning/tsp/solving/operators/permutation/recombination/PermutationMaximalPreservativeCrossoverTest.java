package test.junit.org.logisticPlanning.tsp.solving.operators.permutation.recombination;

import org.logisticPlanning.tsp.solving.operators.permutation.recombination.PermutationMaximalPreservativeCrossover;

import test.junit.org.logisticPlanning.tsp.solving.operators.permutation.PermutationOperatorTest;

/**
 * test the permutation maximal preservative recombination operator
 */
public class PermutationMaximalPreservativeCrossoverTest extends
PermutationOperatorTest {

  /** instantiate */
  public PermutationMaximalPreservativeCrossoverTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected PermutationMaximalPreservativeCrossover createOperator() {
    return new PermutationMaximalPreservativeCrossover();
  }
}
