package org.logisticPlanning.utils.document.spec;

import java.io.IOException;

import org.logisticPlanning.utils.text.EQuotes;

/**
 * The text in quotes.
 */
public class InQuotes extends AbstractTextPlain {

  /** the quotes to use */
  private final EQuotes m_quotes;

  /**
   * create the text element
   * 
   * @param owner
   *          the owning element
   * @param quotes
   *          the quotes to use
   * @throws IOException
   *           if io fails
   */
  protected InQuotes(final AbstractText owner, final EQuotes quotes)
      throws IOException {
    super(owner);
    if (quotes == null) {
      throw new IllegalArgumentException(//
          "Quotation marks must not be null."); //$NON-NLS-1$
    }
    this.m_quotes = quotes;
  }

  /**
   * Get the quotes
   * 
   * @return the quotes
   */
  public final EQuotes getQuotes() {
    return this.m_quotes;
  }

  /** {@inheritDoc} */
  @Override
  public AbstractText getOwner() {
    return ((AbstractText) (this.m_owner));
  }

  /** {@inheritDoc} */
  @Override
  protected void doClose() throws IOException {
    this.m_owner.inQuotesEnd(this);
  }

  /** {@inheritDoc} */
  @Override
  public final void close() throws IOException {
    if (this.checkDead(Element.STATE_NOTHING)) {
      this.doClose();
    }
  }
}
