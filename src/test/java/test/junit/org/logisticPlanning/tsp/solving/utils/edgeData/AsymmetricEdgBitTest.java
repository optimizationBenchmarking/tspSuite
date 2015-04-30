package test.junit.org.logisticPlanning.tsp.solving.utils.edgeData;

import org.logisticPlanning.tsp.solving.utils.edgeData.EdgeBit;

/**
 * The basic test for asymmetric edge bits
 */
public class AsymmetricEdgBitTest extends _AsymmetricEdgeBitTest {

  /** instantiate */
  public AsymmetricEdgBitTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected EdgeBit instantiate(final int n) {
    return EdgeBit.allocate(n, false, null);
  }
}
