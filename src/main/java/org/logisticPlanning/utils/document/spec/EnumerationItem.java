package org.logisticPlanning.utils.document.spec;

import java.io.IOException;

/**
 * The enumeration item element.
 */
public class EnumerationItem extends AbstractItem {

  /**
   * create the document element
   *
   * @param owner
   *          the owning element
   * @throws IOException
   *           if io fails
   */
  protected EnumerationItem(final Enumeration owner) throws IOException {
    super(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void doClose() throws IOException {
    this.m_owner.enumerationItemEnd(this);
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
  public Enumeration getOwner() {
    return ((Enumeration) (this.m_owner));
  }
}
