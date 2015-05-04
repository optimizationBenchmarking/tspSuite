package test.junit.org.logisticPlanning.tsp.solving.operators.permutation.recombination;

import org.logisticPlanning.tsp.solving.operators.permutation.recombination.PermutationPositionBasedCrossover;

import test.junit.org.logisticPlanning.tsp.solving.operators.permutation.PermutationOperatorTest;

/**
 * test the permutation position-based recombination operator
 */
public class PermutationPositionBasedCrossoverTest extends
PermutationOperatorTest {

  /** instantiate */
  public PermutationPositionBasedCrossoverTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected PermutationPositionBasedCrossover createOperator() {
    return new PermutationPositionBasedCrossover();
  }
}
