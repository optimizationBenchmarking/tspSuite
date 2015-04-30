package org.logisticPlanning.utils.document.spec;

import java.io.IOException;

/**
 * The math operation.
 */
public class MathOpParam extends AbstractMathElement {

  /** the argument index */
  private final int m_index;

  /**
   * create the text element
   * 
   * @param owner
   *          the owning element
   * @throws IOException
   *           if io fails
   */
  protected MathOpParam(final MathOp owner) throws IOException {
    super(owner);
    this.m_index = owner.nextArg();
  }

  /**
   * Get the mathematical operator
   * 
   * @return the mathematical operator
   */
  public final int getIndex() {
    return this.m_index;
  }

  /** {@inheritDoc} */
  @Override
  public MathOp getOwner() {
    return ((MathOp) (this.m_owner));
  }

  /** {@inheritDoc} */
  @Override
  protected void doClose() throws IOException {
    this.m_owner.mathOpParamEnd(this);
  }

  /** {@inheritDoc} */
  @Override
  public final void close() throws IOException {
    if (this.checkDead(Element.STATE_NOTHING)) {
      this.doClose();
    }
  }
}
