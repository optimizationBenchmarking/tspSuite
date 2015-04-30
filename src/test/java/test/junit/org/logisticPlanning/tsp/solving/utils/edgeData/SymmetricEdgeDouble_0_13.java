package test.junit.org.logisticPlanning.tsp.solving.utils.edgeData;

/**
 * Testing a configuration of the EdgeNumber facility.
 */
public class SymmetricEdgeDouble_0_13 extends _EdgeNumberTest {

  /** create the SymmetricEdgeDouble_0_13 */
  public SymmetricEdgeDouble_0_13() {
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
    return 13;
  }

  /** {@inheritDoc} */
  @Override
  final long getMaxValue() {
    return Short.MAX_VALUE;
  }

  /** {@inheritDoc} */
  @Override
  final int floatType() {
    return 2;
  }

}
