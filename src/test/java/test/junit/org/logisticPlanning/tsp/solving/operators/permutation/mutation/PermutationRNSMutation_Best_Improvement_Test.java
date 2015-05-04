package test.junit.org.logisticPlanning.tsp.solving.operators.permutation.mutation;

import org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.vns.EImprovementSelectionPolicy;
import org.logisticPlanning.tsp.solving.operators.permutation.mutation.PermutationRNSMutation;

import test.junit.org.logisticPlanning.tsp.solving.operators.permutation.PermutationOperatorTest;

/**
 * test the permutation
 * {@link org.logisticPlanning.tsp.solving.operators.permutation.mutation.PermutationRNSMutation
 * RNS mutation operator}
 */
public class PermutationRNSMutation_Best_Improvement_Test extends
PermutationOperatorTest {

  /** instantiate */
  public PermutationRNSMutation_Best_Improvement_Test() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected PermutationRNSMutation createOperator() {
    PermutationRNSMutation r;
    r = new PermutationRNSMutation();
    r.setImprovementSelectionPolicy(EImprovementSelectionPolicy.ALWAYS_USE_BEST_IMPROVEMENT);
    return r;
  }
}
