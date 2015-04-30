package test.junit.org.logisticPlanning.tsp.solving.operators.permutation.recombination;

import org.logisticPlanning.tsp.solving.operators.permutation.recombination.PermutationPartiallyMappedCrossover;

import test.junit.org.logisticPlanning.tsp.solving.operators.permutation.PermutationOperatorTest;

/**
 * test the permutation parially mapped recombination operator
 */
public class PermutationPartiallyMappedCrossoverTest extends
    PermutationOperatorTest {

  /** instantiate */
  public PermutationPartiallyMappedCrossoverTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected PermutationPartiallyMappedCrossover createOperator() {
    return new PermutationPartiallyMappedCrossover();
  }
}
