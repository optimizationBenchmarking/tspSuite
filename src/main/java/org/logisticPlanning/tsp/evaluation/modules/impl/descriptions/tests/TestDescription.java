package org.logisticPlanning.tsp.evaluation.modules.impl.descriptions.tests;

import java.io.IOException;

import org.logisticPlanning.tsp.evaluation.modules.spec.DescriptionModule;
import org.logisticPlanning.tsp.evaluation.modules.spec.Module;
import org.logisticPlanning.utils.document.spec.AbstractTextComplex;
import org.logisticPlanning.utils.document.spec.AbstractTextPlain;
import org.logisticPlanning.utils.document.spec.ECitationMode;

/**
 * A base class for descriptions of specific statistical tests.
 */
public abstract class TestDescription extends DescriptionModule {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * create!
   * 
   * @param owner
   *          the owner
   * @param name
   *          the module name
   */
  TestDescription(final String name, final Module owner) {
    super(name, owner, false);
  }

  /**
   * Write the name of the test in a text
   * 
   * @param out
   *          the output destination
   * @param plural
   *          should we write the name in plural?
   * @throws IOException
   *           if io fails
   */
  public void writeName(final AbstractTextPlain out, final boolean plural)
      throws IOException {
    out.write(this.getName(plural));
  }

  /**
   * Get the name
   * 
   * @param plural
   *          should we write the name in plural?
   * @return the name
   */
  public abstract String getName(final boolean plural);

  /**
   * Write the references about this test
   * 
   * @param txt
   *          the destination
   * @param how
   *          the citation mode
   * @throws IOException
   *           if i/o fails
   */
  @SuppressWarnings("unused")
  public void cite(final ECitationMode how, final AbstractTextComplex txt)
      throws IOException {
    //
  }
}
