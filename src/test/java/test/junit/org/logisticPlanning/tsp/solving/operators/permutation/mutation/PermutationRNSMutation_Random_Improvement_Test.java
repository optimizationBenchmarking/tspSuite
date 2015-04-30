package test.junit.org.logisticPlanning.tsp.solving.operators.permutation.mutation;

import org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.vns.EImprovementSelectionPolicy;
import org.logisticPlanning.tsp.solving.operators.permutation.mutation.PermutationRNSMutation;

/**
 * test the permutation
 * {@link org.logisticPlanning.tsp.solving.operators.permutation.mutation.PermutationRNSMutation
 * RNS mutation operator}
 */
public class PermutationRNSMutation_Random_Improvement_Test extends
    PermutationRNSMutation_Best_Improvement_Test {

  /** instantiate */
  public PermutationRNSMutation_Random_Improvement_Test() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected PermutationRNSMutation createOperator() {
    PermutationRNSMutation r;
    r = super.createOperator();
    r.setImprovementSelectionPolicy(EImprovementSelectionPolicy.DECIDE_RANDOMLY_PER_CALL);
    return r;
  }
}
