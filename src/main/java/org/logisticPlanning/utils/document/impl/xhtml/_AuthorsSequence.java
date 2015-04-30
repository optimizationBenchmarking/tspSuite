package org.logisticPlanning.utils.document.impl.xhtml;

import java.io.IOException;

import org.logisticPlanning.utils.document.spec.Sequence;
import org.logisticPlanning.utils.document.spec.bib.BibAuthor;
import org.logisticPlanning.utils.document.spec.bib.BibAuthors;

/**
 * the internal class for writing authors
 */
final class _AuthorsSequence extends Sequence {

  /** the labels */
  private final BibAuthors m_authors;

  /** the document */
  private final XHTMLDocument m_doc;

  /** also print the first names? */
  private final boolean m_printFirstNames;

  /**
   * create
   * 
   * @param authors
   *          the authors
   * @param printFirstNames
   *          print the first names?
   * @param doc
   *          the document
   */
  _AuthorsSequence(final BibAuthors authors,
      final boolean printFirstNames, final XHTMLDocument doc) {
    super();
    this.m_authors = authors;
    this.m_doc = doc;
    this.m_printFirstNames = printFirstNames;
  }

  /** {@inheritDoc} */
  @Override
  public final int length() {
    return this.m_authors.size();
  }

  /** {@inheritDoc} */
  @Override
  public final void write(final int index) throws IOException {
    final BibAuthor auth;

    auth = this.m_authors.get(index);
    if (this.m_printFirstNames) {
      this.m_doc.write(auth.getPersonalName());
      this.m_doc.write(" "); //$NON-NLS-1$
    }
    this.m_doc.write(auth.getFamilyName());
  }
}
