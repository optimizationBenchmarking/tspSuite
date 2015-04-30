package test.junit.org.logisticPlanning.tsp.solving.utils.edgeData;

import org.logisticPlanning.tsp.solving.utils.edgeData.EdgeBit;

/**
 * The basic test for symmetric edge bits
 */
public class SymmetricEdgeBitTest extends _SymmetricEdgeBitTest {

  /** instantiate */
  public SymmetricEdgeBitTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected EdgeBit instantiate(final int n) {
    return EdgeBit.allocate(n, true, null);
  }
}
