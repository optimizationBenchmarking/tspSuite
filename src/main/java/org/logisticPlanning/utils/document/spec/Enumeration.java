package org.logisticPlanning.utils.document.spec;

import java.io.IOException;

/**
 * The enumeration element.
 */
public class Enumeration extends AbstractList {

  /**
   * create the document element
   *
   * @param owner
   *          the owning element
   * @throws IOException
   *           if io fails
   */
  protected Enumeration(final AbstractTextBlock owner) throws IOException {
    super(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void doClose() throws IOException {
    this.m_owner.enumerationEnd(this);
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
  protected EnumerationItem enumerationItemCreate(final Enumeration owner)
      throws IOException {
    return super.enumerationItemCreate(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void enumerationItemBegin(final EnumerationItem h)
      throws IOException, IllegalStateException {
    this.beginItem(h);
    super.enumerationItemBegin(h);
  }

  /** {@inheritDoc} */
  @Override
  protected void enumerationItemEnd(final EnumerationItem h)
      throws IOException, IllegalStateException {
    this.endItem(h);
    super.enumerationItemEnd(h);
  }

  /** {@inheritDoc} */
  @Override
  public EnumerationItem item() throws IOException, IllegalStateException {
    return this.enumerationItem();
  }

}
