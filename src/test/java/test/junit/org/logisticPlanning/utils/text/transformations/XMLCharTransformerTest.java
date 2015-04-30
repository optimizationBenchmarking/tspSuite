package test.junit.org.logisticPlanning.utils.text.transformations;

import org.logisticPlanning.utils.text.CharTransformer;
import org.logisticPlanning.utils.text.transformations.XMLCharTransformer;

import test.junit.org.logisticPlanning.utils.text.CharTransformerTest;

/**
 * test the LaTeX char transformer
 */
public class XMLCharTransformerTest extends CharTransformerTest {

  /** create */
  public XMLCharTransformerTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected final CharTransformer getTransformer() {
    return XMLCharTransformer.INSTANCE;
  }

}
