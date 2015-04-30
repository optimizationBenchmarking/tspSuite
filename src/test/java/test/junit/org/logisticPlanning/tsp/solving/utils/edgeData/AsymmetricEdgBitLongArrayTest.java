package test.junit.org.logisticPlanning.tsp.solving.utils.edgeData;

import org.logisticPlanning.tsp.solving.utils.edgeData.EdgeBit;
import org.logisticPlanning.tsp.solving.utils.edgeData._AsymmetricEdgeBitLongArray;

/**
 * The basic test for asymmetric edge bits
 */
public class AsymmetricEdgBitLongArrayTest extends _AsymmetricEdgeBitTest {

  /** instantiate */
  public AsymmetricEdgBitLongArrayTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected EdgeBit instantiate(final int n) {
    return new _AsymmetricEdgeBitLongArray(n);
  }
}
