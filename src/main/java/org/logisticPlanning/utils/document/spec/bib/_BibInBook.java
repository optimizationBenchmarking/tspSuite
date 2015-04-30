package org.logisticPlanning.utils.document.spec.bib;

import java.net.URI;

import org.logisticPlanning.utils.utils.HashUtils;
import org.logisticPlanning.utils.utils.comparison.ComparisonUtils;

/**
 * a bibliographic record for a thing that appeared in a book
 * 
 * @param <B>
 *          the book type
 */
class _BibInBook<B extends BibBook> extends BibRecord {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the book */
  private final B m_book;

  /** the start page */
  private final String m_startPage;

  /** the end page */
  private final String m_endPage;

  /** the chapter */
  private final String m_chapter;

  /**
   * Create a new bibliography record for things that are parts of book
   * 
   * @param authors
   *          the authors
   * @param title
   *          the title
   * @param uri
   *          the uri
   * @param book
   *          the book
   * @param startPage
   *          the start page
   * @param endPage
   *          the end page
   * @param chapter
   *          the chapter
   * @param doi
   *          the doi
   */
  _BibInBook(final BibAuthors authors, final String title, final B book,
      final String startPage, final String endPage, final String chapter,
      final URI uri, final String doi) {
    super(authors, title, book.getDate(), uri, doi);

    this.m_startPage = _BibElement.prepare(startPage);
    this.m_endPage = _BibElement.prepare(endPage);

    if ((this.m_endPage != null) ^ (this.m_startPage != null)) {
      throw new IllegalArgumentException(//
          "If start or end page is specified, both need to be specified.");//$NON-NLS-1$
    }

    this.m_chapter = _BibElement.prepare(chapter);

    if ((this.m_chapter == null) && (this.m_startPage == null)) {
      throw new IllegalArgumentException(//
          "Either pages or chapter need to be specified.");//$NON-NLS-1$
    }

    this.m_book = book;

    this.m_hashCode = this._hashCode();
  }

  /** {@inheritDoc} */
  @Override
  final int _hashCode() {
    return HashUtils.combineHashes(HashUtils.combineHashes(//
        HashUtils.combineHashes(super._hashCode(),//
            HashUtils.hashCode(this.m_book)),//
        HashUtils.combineHashes(//
            HashUtils.hashCode(this.m_startPage),//
            HashUtils.hashCode(this.m_endPage))),//
        HashUtils.hashCode(this.m_chapter));
  }

  /**
   * Get the book containing this chapter or paper
   * 
   * @return the bibliography editors
   */
  public final B getBook() {
    return this.m_book;
  }

  /**
   * Get the start page
   * 
   * @return the start page
   */
  public final String getStartPage() {
    return this.m_startPage;
  }

  /**
   * Get the end page
   * 
   * @return the end page
   */
  public final String getEndPage() {
    return this.m_endPage;
  }

  /**
   * Get the chapter
   * 
   * @return the chapter
   */
  public final String getChapter() {
    return this.m_chapter;
  }

  /** {@inheritDoc} */
  @Override
  @SuppressWarnings("unchecked")
  final boolean _equals(final BibRecord r) {
    final _BibInBook<BibBook> x;

    if (super._equals(r)) {
      x = ((_BibInBook<BibBook>) r);

      return (ComparisonUtils.equals(this.m_book, x.m_book) && //
          ComparisonUtils.equals(this.m_startPage, x.m_startPage) && //
          ComparisonUtils.equals(this.m_endPage, x.m_endPage) && //
      ComparisonUtils.equals(this.m_chapter, x.m_chapter));
    }

    return false;
  }

  /** {@inheritDoc} */
  @Override
  @SuppressWarnings("unchecked")
  final int compareRest(final BibRecord o) {
    _BibInBook<BibBook> bb;
    int r;

    if (o instanceof _BibInBook) {
      bb = ((_BibInBook<BibBook>) o);

      r = ComparisonUtils.compare(((BibRecord) (this.m_book)),
          ((BibRecord) (bb.m_book)));
      if (r != 0) {
        return r;
      }

      r = ComparisonUtils.compare(this.m_startPage, bb.m_startPage);
      if (r != 0) {
        return r;
      }

      r = ComparisonUtils.compare(this.m_endPage, bb.m_endPage);
      if (r != 0) {
        return r;
      }

      r = ComparisonUtils.compare(this.m_chapter, bb.m_chapter);
      if (r != 0) {
        return r;
      }
    }

    return super.compareRest(o);
  }
}
