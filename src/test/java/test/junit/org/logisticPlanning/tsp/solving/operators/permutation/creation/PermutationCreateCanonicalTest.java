package test.junit.org.logisticPlanning.tsp.solving.operators.permutation.creation;

import org.logisticPlanning.tsp.solving.operators.permutation.creation.PermutationCreateCanonical;

import test.junit.org.logisticPlanning.tsp.solving.operators.permutation.PermutationOperatorTest;

/**
 * test the permutation cycle recombination operator
 */
public class PermutationCreateCanonicalTest extends
    PermutationOperatorTest {

  /** instantiate */
  public PermutationCreateCanonicalTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected PermutationCreateCanonical createOperator() {
    return new PermutationCreateCanonical();
  }
}
