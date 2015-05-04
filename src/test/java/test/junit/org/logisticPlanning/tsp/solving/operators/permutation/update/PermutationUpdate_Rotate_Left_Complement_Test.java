package test.junit.org.logisticPlanning.tsp.solving.operators.permutation.update;

import org.logisticPlanning.tsp.solving.operators.permutation.update.PermutationUpdateOperator;
import org.logisticPlanning.tsp.solving.operators.permutation.update.PermutationUpdate_Rotate_Left;

/** test an update operator */
public class PermutationUpdate_Rotate_Left_Complement_Test extends
_PermutationUpdateOperatorTest {

  /** instantiate */
  public PermutationUpdate_Rotate_Left_Complement_Test() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected PermutationUpdateOperator createOperator() {
    return PermutationUpdate_Rotate_Left.COMPLEMENT;
  }
}
