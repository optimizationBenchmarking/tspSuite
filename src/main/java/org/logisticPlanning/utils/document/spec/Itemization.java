package org.logisticPlanning.utils.document.spec;

import java.io.IOException;

/**
 * The itemization element.
 */
public class Itemization extends AbstractList {

  /**
   * create the document element
   * 
   * @param owner
   *          the owning element
   * @throws IOException
   *           if io fails
   */
  protected Itemization(final AbstractTextBlock owner) throws IOException {
    super(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void doClose() throws IOException {
    this.m_owner.itemizationEnd(this);
  }

  /** {@inheritDoc} */
  @Override
  public final void close() throws IOException {
    if (this.checkClose()) {
      this.doClose();
    }
  }

  /** {@inheritDoc} */
  @Override
  protected ItemizationItem itemizationItemCreate(final Itemization owner)
      throws IOException {
    return super.itemizationItemCreate(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void itemizationItemBegin(final ItemizationItem h)
      throws IOException, IllegalStateException {
    this.beginItem(h);
    super.itemizationItemBegin(h);
  }

  /** {@inheritDoc} */
  @Override
  protected void itemizationItemEnd(final ItemizationItem h)
      throws IOException, IllegalStateException {
    this.endItem(h);
    super.itemizationItemEnd(h);
  }

  /** {@inheritDoc} */
  @Override
  public ItemizationItem item() throws IOException, IllegalStateException {
    return this.itemizationItem();
  }

}
