package test.junit.org.logisticPlanning.tsp.solving.operators.permutation.mutation;

import org.logisticPlanning.tsp.solving.operators.permutation.mutation.PermutationLinKernighanMutation;

import test.junit.org.logisticPlanning.tsp.solving.operators.permutation.PermutationOperatorTest;

/**
 * test the permutation Lin-Kernighan mutation operator
 */
public class PermutationLinKernighanMutation_Test extends
PermutationOperatorTest {

  /** instantiate */
  public PermutationLinKernighanMutation_Test() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected PermutationLinKernighanMutation createOperator() {
    return new PermutationLinKernighanMutation();
  }
}
