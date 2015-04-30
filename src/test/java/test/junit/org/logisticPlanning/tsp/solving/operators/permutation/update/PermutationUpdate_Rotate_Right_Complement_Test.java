package test.junit.org.logisticPlanning.tsp.solving.operators.permutation.update;

import org.logisticPlanning.tsp.solving.operators.permutation.update.PermutationUpdateOperator;
import org.logisticPlanning.tsp.solving.operators.permutation.update.PermutationUpdate_Rotate_Right;

/** test an update operator */
public class PermutationUpdate_Rotate_Right_Complement_Test extends
    _PermutationUpdateOperatorTest {

  /** instantiate */
  public PermutationUpdate_Rotate_Right_Complement_Test() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected PermutationUpdateOperator createOperator() {
    return PermutationUpdate_Rotate_Right.COMPLEMENT;
  }
}
