package test.junit.org.logisticPlanning.tsp.solving.operators.permutation.mutation;

import org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.vns.EImprovementSelectionPolicy;
import org.logisticPlanning.tsp.solving.operators.permutation.mutation.PermutationVNSMutation;

/**
 * test the permutation
 * {@link org.logisticPlanning.tsp.solving.operators.permutation.mutation.PermutationVNSMutation
 * VNS mutation operator}
 */
public class PermutationVNSMutation_First_Improvement_Test extends
PermutationVNSMutation_Best_Improvement_Test {

  /** instantiate */
  public PermutationVNSMutation_First_Improvement_Test() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected PermutationVNSMutation createOperator() {
    PermutationVNSMutation r;
    r = super.createOperator();
    r.setImprovementSelectionPolicy(EImprovementSelectionPolicy.ALWAYS_USE_FIRST_IMPROVEMENT);
    return r;
  }
}
