package org.logisticPlanning.utils.document.spec;

import java.io.IOException;

/**
 * The superscript text.
 */
public class Superscript extends AbstractTextPlain {

  /**
   * create the text element
   *
   * @param owner
   *          the owning element
   * @throws IOException
   *           if io fails
   */
  protected Superscript(final AbstractText owner) throws IOException {
    super(owner);
  }

  /** {@inheritDoc} */
  @Override
  public AbstractText getOwner() {
    return ((AbstractText) (this.m_owner));
  }

  /** {@inheritDoc} */
  @Override
  protected void doClose() throws IOException {
    this.m_owner.superscriptEnd(this);
  }

  /** {@inheritDoc} */
  @Override
  public final void close() throws IOException {
    if (this.checkDead(Element.STATE_NOTHING)) {
      this.doClose();
    }
  }

}
