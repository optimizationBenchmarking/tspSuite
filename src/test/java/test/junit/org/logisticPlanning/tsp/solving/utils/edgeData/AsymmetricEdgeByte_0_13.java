package test.junit.org.logisticPlanning.tsp.solving.utils.edgeData;

/**
 * Testing a configuration of the EdgeNumber facility.
 */
public class AsymmetricEdgeByte_0_13 extends _EdgeNumberTest {

  /** create the AsymmetricEdgeByte_0_13 */
  public AsymmetricEdgeByte_0_13() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  final boolean isSymmetric() {
    return false;
  }

  /** {@inheritDoc} */
  @Override
  final int n() {
    return 13;
  }

  /** {@inheritDoc} */
  @Override
  final long getMaxValue() {
    return ((java.lang.Byte.MAX_VALUE));
  }

  /** {@inheritDoc} */
  @Override
  final int floatType() {
    return 0;
  }

}
