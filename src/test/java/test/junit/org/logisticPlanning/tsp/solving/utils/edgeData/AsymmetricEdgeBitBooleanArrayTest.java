package test.junit.org.logisticPlanning.tsp.solving.utils.edgeData;

import org.logisticPlanning.tsp.solving.utils.edgeData.EdgeBit;
import org.logisticPlanning.tsp.solving.utils.edgeData._AsymmetricEdgeBitBooleanArray;

/**
 * The basic test for asymmetric edge bits
 */
public class AsymmetricEdgeBitBooleanArrayTest extends
    _AsymmetricEdgeBitTest {

  /** instantiate */
  public AsymmetricEdgeBitBooleanArrayTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected EdgeBit instantiate(final int n) {
    return new _AsymmetricEdgeBitBooleanArray(n);
  }
}
