package org.logisticPlanning.utils.document.spec;

import java.io.IOException;

/**
 * The body of an equation.
 */
public class EquationBody extends AbstractMathElement {

  /**
   * create the document element
   * 
   * @param owner
   *          the owning element
   * @throws IOException
   *           if io fails
   */
  protected EquationBody(final Equation owner) throws IOException {
    super(owner);
  }

  /** {@inheritDoc} */
  @Override
  public Equation getOwner() {
    return ((Equation) (this.m_owner));
  }

  /** {@inheritDoc} */
  @Override
  protected void doClose() throws IOException {
    this.m_owner.equationBodyEnd(this);
  }

  /** {@inheritDoc} */
  @Override
  public final void close() throws IOException {
    if (this.checkDead(Element.STATE_NOTHING)) {
      this.doClose();
    }
  }
}
