package test.junit.org.logisticPlanning.tsp.solving.utils.edgeData;

import org.logisticPlanning.tsp.solving.utils.edgeData.EdgeBit;
import org.logisticPlanning.tsp.solving.utils.edgeData._SymmetricEdgeBitBooleanArray;

/**
 * The basic test for symmetric edge bits
 */
public class SymmetricEdgeBitBooleanArrayTest extends
    _SymmetricEdgeBitTest {

  /** instantiate */
  public SymmetricEdgeBitBooleanArrayTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected EdgeBit instantiate(final int n) {
    return new _SymmetricEdgeBitBooleanArray(n);
  }
}
