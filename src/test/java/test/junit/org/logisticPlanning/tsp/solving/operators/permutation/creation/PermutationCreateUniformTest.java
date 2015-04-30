package test.junit.org.logisticPlanning.tsp.solving.operators.permutation.creation;

import org.logisticPlanning.tsp.solving.operators.permutation.creation.PermutationCreateUniform;

import test.junit.org.logisticPlanning.tsp.solving.operators.permutation.PermutationOperatorTest;

/**
 * test the permutation cycle recombination operator
 */
public class PermutationCreateUniformTest extends PermutationOperatorTest {

  /** instantiate */
  public PermutationCreateUniformTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected PermutationCreateUniform createOperator() {
    return new PermutationCreateUniform();
  }
}
