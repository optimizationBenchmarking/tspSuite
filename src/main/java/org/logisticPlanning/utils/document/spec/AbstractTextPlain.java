package org.logisticPlanning.utils.document.spec;

import java.io.IOException;

import org.logisticPlanning.utils.text.EQuotes;

/**
 * The basic plain text element.
 */
public abstract class AbstractTextPlain extends AbstractInlineElement {

  /**
   * create the text element
   * 
   * @param owner
   *          the owning element
   * @throws IOException
   *           if io fails
   */
  AbstractTextPlain(final Element owner) throws IOException {
    super(owner);
  }

  /** {@inheritDoc} */
  @Override
  public void writeChar(final int data) throws IOException {
    super.writeChar(data);
  }

  /** {@inheritDoc} */
  @Override
  public void writeIntInText(final int v, final boolean beginUpperCase)
      throws IOException {
    super.writeIntInText(v, beginUpperCase);
  }

  /** {@inheritDoc} */
  @Override
  public void writeLongInText(final long v, final boolean beginUpperCase)
      throws IOException {
    super.writeLongInText(v, beginUpperCase);
  }

  /** {@inheritDoc} */
  @Override
  public void write(final String data) throws IOException {
    super.write(data);
  }

  /** {@inheritDoc} */
  @Override
  public void writeHyphenated(final String data) throws IOException {
    super.writeHyphenated(data);
  }

  /** {@inheritDoc} */
  @Override
  public void writeNoneBreakingSpace() throws IOException {
    super.writeNoneBreakingSpace();
  }

  /** {@inheritDoc} */
  @Override
  protected InQuotes inQuotesCreate(final AbstractText owner,
      final EQuotes quotes) throws IOException {
    return super.inQuotesCreate(owner, quotes);
  }

  /** {@inheritDoc} */
  @Override
  protected void inQuotesBegin(final InQuotes h) throws IOException,
      IllegalStateException {
    super.inQuotesBegin(h);
  }

  /** {@inheritDoc} */
  @Override
  protected void inQuotesEnd(final InQuotes h) throws IOException,
      IllegalStateException {
    super.inQuotesEnd(h);
  }

  /** {@inheritDoc} */
  @Override
  public InQuotes inQuotes(final EQuotes quotes) throws IOException,
      IllegalStateException {
    return super.inQuotes(quotes);
  }
}
