package org.logisticPlanning.utils.document.spec;

import java.io.IOException;

import org.logisticPlanning.utils.document.spec.bib.BibRecord;

/**
 * The basic breakable text element.
 */
public abstract class AbstractTextComplex extends AbstractText {

  /**
   * create the text element
   *
   * @param owner
   *          the owning element
   * @throws IOException
   *           if io fails
   */
  AbstractTextComplex(final Element owner) throws IOException {
    super(owner);
  }

  /**
   * Write a set of references.
   *
   * @param ref
   *          the references
   * @throws IOException
   *           if io fails
   */
  public final void reference(final Label... ref) throws IOException {
    this.reference(ESequenceType.AND, ref);
  }

  /** {@inheritDoc} */
  @Override
  public void reference(final ESequenceType type, final Label... ref)
      throws IOException {
    super.reference(type, ref);
  }

  /** {@inheritDoc} */
  @Override
  public void cite(final ECitationMode mode, final BibRecord... records)
      throws IOException {
    super.cite(mode, records);
  }

  /** {@inheritDoc} */
  @Override
  protected InlineMath inlineMathCreate(final AbstractTextComplex owner)
      throws IOException {
    return super.inlineMathCreate(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected void inlineMathBegin(final InlineMath h) throws IOException,
  IllegalStateException {
    super.inlineMathBegin(h);
  }

  /** {@inheritDoc} */
  @Override
  protected void inlineMathEnd(final InlineMath h) throws IOException,
  IllegalStateException {
    super.inlineMathEnd(h);
  }

  /** {@inheritDoc} */
  @Override
  public InlineMath inlineMath() throws IOException, IllegalStateException {
    return super.inlineMath();
  }
}
