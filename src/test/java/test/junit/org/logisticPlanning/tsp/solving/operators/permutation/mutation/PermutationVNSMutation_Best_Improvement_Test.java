package test.junit.org.logisticPlanning.tsp.solving.operators.permutation.mutation;

import org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.vns.EImprovementSelectionPolicy;
import org.logisticPlanning.tsp.solving.operators.permutation.mutation.PermutationVNSMutation;

import test.junit.org.logisticPlanning.tsp.solving.operators.permutation.PermutationOperatorTest;

/**
 * test the permutation
 * {@link org.logisticPlanning.tsp.solving.operators.permutation.mutation.PermutationVNSMutation
 * VNS mutation operator}
 */
public class PermutationVNSMutation_Best_Improvement_Test extends
    PermutationOperatorTest {

  /** instantiate */
  public PermutationVNSMutation_Best_Improvement_Test() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected PermutationVNSMutation createOperator() {
    PermutationVNSMutation r;
    r = new PermutationVNSMutation();
    r.setImprovementSelectionPolicy(EImprovementSelectionPolicy.ALWAYS_USE_BEST_IMPROVEMENT);
    return r;
  }
}
