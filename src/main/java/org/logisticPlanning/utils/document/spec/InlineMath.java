package org.logisticPlanning.utils.document.spec;

import java.io.IOException;

/**
 * The inline math element.
 */
public class InlineMath extends AbstractMathElement {

  /**
   * create the document element
   *
   * @param owner
   *          the owning element
   * @throws IOException
   *           if io fails
   */
  protected InlineMath(final AbstractTextComplex owner) throws IOException {
    super(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void doClose() throws IOException {
    this.m_owner.inlineMathEnd(this);
  }

  /** {@inheritDoc} */
  @Override
  public final void close() throws IOException {
    if (this.checkDead(Element.STATE_NOTHING)) {
      this.doClose();
    }
  }

  /** {@inheritDoc} */
  @Override
  public AbstractTextComplex getOwner() {
    return ((AbstractTextComplex) (this.m_owner));
  }
}
