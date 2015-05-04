package test.junit.org.logisticPlanning.tsp.solving.operators.permutation.recombination;

import org.logisticPlanning.tsp.solving.operators.permutation.recombination.PermutationAlternatingPositionCrossover;

import test.junit.org.logisticPlanning.tsp.solving.operators.permutation.PermutationOperatorTest;

/**
 * test the permutation alternating position recombination operator
 */
public class PermutationAlternatingPositionCrossoverTest extends
PermutationOperatorTest {

  /** instantiate */
  public PermutationAlternatingPositionCrossoverTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected PermutationAlternatingPositionCrossover createOperator() {
    return new PermutationAlternatingPositionCrossover();
  }
}
