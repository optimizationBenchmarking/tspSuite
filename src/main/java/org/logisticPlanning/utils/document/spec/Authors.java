package org.logisticPlanning.utils.document.spec;

import java.io.IOException;

import org.logisticPlanning.utils.document.spec.bib.BibAuthors;

/**
 * The authors header element.
 */
public class Authors extends Element {

  /** the authors record */
  private final BibAuthors m_authors;

  /**
   * create the document element
   *
   * @param authors
   *          the authors list
   * @param owner
   *          the owning element
   * @throws IOException
   *           if io fails
   */
  protected Authors(final Header owner, final BibAuthors authors)
      throws IOException {
    super(owner);

    if (authors == null) {
      throw new IllegalArgumentException(//
          "Authors must not be null."); //$NON-NLS-1$
    }
    if (authors.size() <= 0) {
      throw new IllegalArgumentException(//
          "Authors must not be empty."); //$NON-NLS-1$
    }
    this.m_authors = authors;
  }

  /** {@inheritDoc} */
  @Override
  public Header getOwner() {
    return ((Header) (this.m_owner));
  }

  /** {@inheritDoc} */
  @Override
  protected void doClose() throws IOException {
    this.m_owner.authorsEnd(this);
  }

  /** {@inheritDoc} */
  @Override
  public void close() throws IOException {
    if (this.checkDead(Element.STATE_NOTHING)) {
      this.doClose();
    }
  }

  /**
   * Get the bibliography authors
   *
   * @return the bibliography authors
   */
  public final BibAuthors getAuthors() {
    return this.m_authors;
  }

}
