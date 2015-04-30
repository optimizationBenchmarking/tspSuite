package org.logisticPlanning.utils.document.spec;

import java.io.IOException;

/**
 * The itemization item element.
 */
public class ItemizationItem extends AbstractItem {

  /**
   * create the document element
   * 
   * @param owner
   *          the owning element
   * @throws IOException
   *           if io fails
   */
  protected ItemizationItem(final Itemization owner) throws IOException {
    super(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void doClose() throws IOException {
    this.m_owner.itemizationItemEnd(this);
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
  public Itemization getOwner() {
    return ((Itemization) (this.m_owner));
  }
}
