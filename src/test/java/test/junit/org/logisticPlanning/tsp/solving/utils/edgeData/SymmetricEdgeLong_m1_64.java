package test.junit.org.logisticPlanning.tsp.solving.utils.edgeData;

/**
 * Testing a configuration of the EdgeNumber facility.
 */
public class SymmetricEdgeLong_m1_64 extends _EdgeNumberTest {

  /** create the SymmetricEdgeLong_m1_64 */
  public SymmetricEdgeLong_m1_64() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  final boolean isSymmetric() {
    return true;
  }

  /** {@inheritDoc} */
  @Override
  final int n() {
    return 64;
  }

  /** {@inheritDoc} */
  @Override
  final long getMaxValue() {
    return ((java.lang.Long.MAX_VALUE) + (-1l));
  }

  /** {@inheritDoc} */
  @Override
  final int floatType() {
    return 0;
  }

}
