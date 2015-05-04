package test.junit.org.logisticPlanning.tsp.solving.operators.permutation.mutation;

import org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.vns.EImprovementSelectionPolicy;
import org.logisticPlanning.tsp.solving.operators.permutation.mutation.PermutationRNSMutation;

/**
 * test the permutation
 * {@link org.logisticPlanning.tsp.solving.operators.permutation.mutation.PermutationRNSMutation
 * RNS mutation operator}
 */
public class PermutationRNSMutation_First_Improvement_Test extends
PermutationRNSMutation_Best_Improvement_Test {

  /** instantiate */
  public PermutationRNSMutation_First_Improvement_Test() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected PermutationRNSMutation createOperator() {
    PermutationRNSMutation r;
    r = super.createOperator();
    r.setImprovementSelectionPolicy(EImprovementSelectionPolicy.ALWAYS_USE_FIRST_IMPROVEMENT);
    return r;
  }
}
