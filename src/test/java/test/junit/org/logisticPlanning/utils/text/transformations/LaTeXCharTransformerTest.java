package test.junit.org.logisticPlanning.utils.text.transformations;

import org.junit.Assert;
import org.junit.Test;
import org.logisticPlanning.utils.text.CharTransformer;
import org.logisticPlanning.utils.text.transformations.LaTeXCharTransformer;

import test.junit.org.logisticPlanning.utils.text.CharTransformerTest;

/**
 * test the LaTeX char transformer
 */
public class LaTeXCharTransformerTest extends CharTransformerTest {

  /** create */
  public LaTeXCharTransformerTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected final CharTransformer getTransformer() {
    return LaTeXCharTransformer.INSTANCE;
  }

  /** test special characters */
  @Test(timeout = 3600000)
  public void testSpecialCharacters() {

    Assert.assertEquals("{\\{}", //$NON-NLS-1$
        LaTeXCharTransformer.INSTANCE.transform(//
            "{")); //$NON-NLS-1$

    Assert.assertEquals("{\\}}", //$NON-NLS-1$
        LaTeXCharTransformer.INSTANCE.transform(//
            "}")); //$NON-NLS-1$

  }

}
