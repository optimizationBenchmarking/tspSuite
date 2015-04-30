package org.logisticPlanning.utils.document.spec;

import java.io.IOException;

/**
 * The document summary element.
 */
public class Summary extends AbstractText {

  /**
   * create the document element
   * 
   * @param owner
   *          the owning element
   * @throws IOException
   *           if io fails
   */
  protected Summary(final Header owner) throws IOException {
    super(owner);
  }

  /** {@inheritDoc} */
  @Override
  public Header getOwner() {
    return ((Header) (this.m_owner));
  }

  /** {@inheritDoc} */
  @Override
  protected void doClose() throws IOException {
    this.m_owner.summaryEnd(this);
  }

  /** {@inheritDoc} */
  @Override
  public final void close() throws IOException {
    if (this.checkDead(Element.STATE_NOTHING)) {
      this.doClose();
    }
  }

}
