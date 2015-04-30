package org.logisticPlanning.utils.document.spec;

import java.io.IOException;

/**
 * The table caption element.
 */
public class TableCaption extends AbstractTextComplex {

  /**
   * create the document element
   * 
   * @param owner
   *          the owning element
   * @throws IOException
   *           if io fails
   */
  protected TableCaption(final Table owner) throws IOException {
    super(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void doClose() throws IOException {
    this.m_owner.tableCaptionEnd(this);
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
  public Table getOwner() {
    return ((Table) (this.m_owner));
  }
}
