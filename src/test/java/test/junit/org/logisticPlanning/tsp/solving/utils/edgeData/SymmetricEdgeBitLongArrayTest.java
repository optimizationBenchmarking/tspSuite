package test.junit.org.logisticPlanning.tsp.solving.utils.edgeData;

import org.logisticPlanning.tsp.solving.utils.edgeData.EdgeBit;
import org.logisticPlanning.tsp.solving.utils.edgeData._SymmetricEdgeBitLongArray;

/**
 * The basic test for symmetric edge bits
 */
public class SymmetricEdgeBitLongArrayTest extends _SymmetricEdgeBitTest {

  /** instantiate */
  public SymmetricEdgeBitLongArrayTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected EdgeBit instantiate(final int n) {
    return new _SymmetricEdgeBitLongArray(n);
  }
}
