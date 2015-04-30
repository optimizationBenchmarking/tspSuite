package org.logisticPlanning.utils.document.spec;

import java.io.IOException;

/**
 * The block of text and other elements.
 */
public abstract class AbstractTextBlock extends AbstractTextComplex {

  /**
   * create the text element
   * 
   * @param owner
   *          the owning element
   * @throws IOException
   *           if io fails
   */
  AbstractTextBlock(final Element owner) throws IOException {
    super(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected Itemization itemizationCreate(final AbstractTextBlock owner)
      throws IOException {
    return super.itemizationCreate(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void itemizationBegin(final Itemization h) throws IOException,
      IllegalStateException {
    super.itemizationBegin(h);
  }

  /** {@inheritDoc} */
  @Override
  protected void itemizationEnd(final Itemization h) throws IOException,
      IllegalStateException {
    super.itemizationEnd(h);
  }

  /** {@inheritDoc} */
  @Override
  public Itemization itemization() throws IOException,
      IllegalStateException {
    return super.itemization();
  }

  /** {@inheritDoc} */
  @Override
  protected Enumeration enumerationCreate(final AbstractTextBlock owner)
      throws IOException {
    return super.enumerationCreate(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void enumerationBegin(final Enumeration h) throws IOException,
      IllegalStateException {
    super.enumerationBegin(h);
  }

  /** {@inheritDoc} */
  @Override
  protected void enumerationEnd(final Enumeration h) throws IOException,
      IllegalStateException {
    super.enumerationEnd(h);
  }

  /** {@inheritDoc} */
  @Override
  public Enumeration enumeration() throws IOException,
      IllegalStateException {
    return super.enumeration();
  }
}
