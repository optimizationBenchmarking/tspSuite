package test.junit.org.logisticPlanning.tsp.solving.utils.edgeData;

/**
 * Testing a configuration of the EdgeNumber facility.
 */
public class AsymmetricEdgeInt_0_13 extends _EdgeNumberTest {

  /** create the AsymmetricEdgeInt_0_13 */
  public AsymmetricEdgeInt_0_13() {
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
    return ((java.lang.Integer.MAX_VALUE));
  }

  /** {@inheritDoc} */
  @Override
  final int floatType() {
    return 0;
  }

}
