package test.junit.org.logisticPlanning.tsp.solving.utils.edgeData;

import org.junit.Ignore;
import org.logisticPlanning.tsp.solving.utils.edgeData.EdgeBit;

import test.junit.TestBase;

/**
 * The basic test for edge bits
 */
@Ignore
abstract class _EdgeBitTest extends TestBase {

  /** the dimension up to which we test */
  static final int TEST_DIMENSION = ((5 * EdgeBit.LONG_THRESHOLD) >>> 1);

  /** instantiate */
  _EdgeBitTest() {
    super();
  }

  /**
   * Instantiate the bit set
   * 
   * @param n
   *          the dimension
   * @return the edge bit set
   */
  protected abstract EdgeBit instantiate(final int n);
}
