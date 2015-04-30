package org.logisticPlanning.utils.document.impl.xhtml;

import java.io.IOException;

import org.logisticPlanning.utils.document.spec.Sequence;
import org.logisticPlanning.utils.document.spec.bib.BibAuthor;
import org.logisticPlanning.utils.document.spec.bib.BibAuthors;

/**
 * the internal class for writing authors to a bibliographic listing
 */
final class _BibAuthorsSequence extends Sequence {

  /** the labels */
  private final BibAuthors m_authors;

  /** begin */
  private final char[] m_begin;

  /** the document */
  private final XHTMLDocument m_doc;

  /**
   * create
   * 
   * @param authors
   *          the authors
   * @param begin
   *          the char array
   * @param doc
   *          the document
   */
  _BibAuthorsSequence(final BibAuthors authors, final char[] begin,
      final XHTMLDocument doc) {
    super();
    this.m_authors = authors;
    this.m_begin = begin;
    this.m_doc = doc;
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
    this.m_doc.m_out.write(this.m_begin, 0, this.m_begin.length);
    this.m_doc.write(auth.getPersonalName());
    this.m_doc.write(" "); //$NON-NLS-1$
    this.m_doc.write(auth.getFamilyName());
    this.m_doc.m_out.write(XHTMLDocument.SPAN_END, 0,
        XHTMLDocument.SPAN_END.length);
  }
}
