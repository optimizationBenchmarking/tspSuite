package org.logisticPlanning.utils.document.spec;

import java.io.IOException;

/**
 * The basic text element.
 */
public abstract class AbstractText extends AbstractTextPlain {

  /**
   * create the text element
   *
   * @param owner
   *          the owning element
   * @throws IOException
   *           if io fails
   */
  AbstractText(final Element owner) throws IOException {
    super(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected Subscript subscriptCreate(final AbstractText owner)
      throws IOException {
    return super.subscriptCreate(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void subscriptBegin(final Subscript h) throws IOException,
  IllegalStateException {
    super.subscriptBegin(h);
  }

  /** {@inheritDoc} */
  @Override
  protected void subscriptEnd(final Subscript h) throws IOException,
  IllegalStateException {
    super.subscriptEnd(h);
  }

  /** {@inheritDoc} */
  @Override
  public Subscript subscript() throws IOException, IllegalStateException {
    return super.subscript();
  }

  /** {@inheritDoc} */
  @Override
  protected Superscript superscriptCreate(final AbstractText owner)
      throws IOException {
    return super.superscriptCreate(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void superscriptBegin(final Superscript h) throws IOException,
  IllegalStateException {
    super.superscriptBegin(h);
  }

  /** {@inheritDoc} */
  @Override
  protected void superscriptEnd(final Superscript h) throws IOException,
  IllegalStateException {
    super.superscriptEnd(h);
  }

  /** {@inheritDoc} */
  @Override
  public Superscript superscript() throws IOException,
  IllegalStateException {
    return super.superscript();
  }

  /** {@inheritDoc} */
  @Override
  protected Emphasize emphasizeCreate(final AbstractText owner)
      throws IOException {
    return super.emphasizeCreate(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void emphasizeBegin(final Emphasize h) throws IOException,
  IllegalStateException {
    super.emphasizeBegin(h);
  }

  /** {@inheritDoc} */
  @Override
  protected void emphasizeEnd(final Emphasize h) throws IOException,
  IllegalStateException {
    super.emphasizeEnd(h);
  }

  /** {@inheritDoc} */
  @Override
  public Emphasize emphasize() throws IOException, IllegalStateException {
    return super.emphasize();
  }

  /** {@inheritDoc} */
  @Override
  public void writeSequence(final Sequence sequence,
      final ESequenceType type,
      final boolean connectLastElementWithNonBreakableSpace)
          throws IOException {
    super.writeSequence(sequence, type,
        connectLastElementWithNonBreakableSpace);
  }
}
