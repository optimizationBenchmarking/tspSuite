package org.logisticPlanning.utils.document.spec;

import java.io.IOException;

/**
 * The normal text.
 */
public class NormalText extends AbstractTextPlain {

  /**
   * create the text element
   * 
   * @param owner
   *          the owning element
   * @throws IOException
   *           if io fails
   */
  protected NormalText(final AbstractMathElement owner) throws IOException {
    super(owner);
  }

  /** {@inheritDoc} */
  @Override
  public AbstractMathElement getOwner() {
    return ((AbstractMathElement) (this.m_owner));
  }

  /** {@inheritDoc} */
  @Override
  protected void doClose() throws IOException {
    this.m_owner.normalTextEnd(this);
  }

  /** {@inheritDoc} */
  @Override
  public final void close() throws IOException {
    if (this.checkDead(Element.STATE_NOTHING)) {
      this.doClose();
    }
  }
}
