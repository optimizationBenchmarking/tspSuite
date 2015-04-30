package test.junit.org.logisticPlanning.tsp.solving.operators.permutation.update;

import org.logisticPlanning.tsp.solving.operators.permutation.update.PermutationUpdateOperator;
import org.logisticPlanning.tsp.solving.operators.permutation.update.PermutationUpdate_Reverse;

/** test an update operator */
public class PermutationUpdate_Reverse_Test extends
    _PermutationUpdateOperatorTest {

  /** instantiate */
  public PermutationUpdate_Reverse_Test() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected PermutationUpdateOperator createOperator() {
    return PermutationUpdate_Reverse.INSTANCE;
  }
}
