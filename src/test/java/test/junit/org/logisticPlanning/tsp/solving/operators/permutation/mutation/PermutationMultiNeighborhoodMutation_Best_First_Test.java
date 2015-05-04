package test.junit.org.logisticPlanning.tsp.solving.operators.permutation.mutation;

import org.logisticPlanning.tsp.solving.operators.permutation.mutation.PermutationMultiNeighborhoodMutation;

import test.junit.org.logisticPlanning.tsp.solving.operators.permutation.PermutationOperatorTest;

/**
 * test the permutation multi-neighborhood mutation operator
 */
public class PermutationMultiNeighborhoodMutation_Best_First_Test extends
PermutationOperatorTest {

  /** instantiate */
  public PermutationMultiNeighborhoodMutation_Best_First_Test() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected PermutationMultiNeighborhoodMutation createOperator() {
    return new PermutationMultiNeighborhoodMutation();
  }
}
