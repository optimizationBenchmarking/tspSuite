package test.junit.org.logisticPlanning.tsp.solving.utils.edgeData;

/**
 * Testing a configuration of the EdgeNumber facility.
 */
public class SymmetricEdgeShort_1_2 extends _EdgeNumberTest {

  /** create the SymmetricEdgeShort_1_2 */
  public SymmetricEdgeShort_1_2() {
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
    return 2;
  }

  /** {@inheritDoc} */
  @Override
  final long getMaxValue() {
    return ((java.lang.Short.MAX_VALUE) + (1l));
  }

  /** {@inheritDoc} */
  @Override
  final int floatType() {
    return 0;
  }

}
