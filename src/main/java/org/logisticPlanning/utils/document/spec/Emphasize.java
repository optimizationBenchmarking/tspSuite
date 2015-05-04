package org.logisticPlanning.utils.document.spec;

import java.io.IOException;

/**
 * The emphasize text.
 */
public class Emphasize extends AbstractTextPlain {

  /**
   * create the text element
   *
   * @param owner
   *          the owning element
   * @throws IOException
   *           if io fails
   */
  protected Emphasize(final AbstractText owner) throws IOException {
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
    this.m_owner.emphasizeEnd(this);
  }

  /** {@inheritDoc} */
  @Override
  public final void close() throws IOException {
    if (this.checkDead(Element.STATE_NOTHING)) {
      this.doClose();
    }
  }
}
