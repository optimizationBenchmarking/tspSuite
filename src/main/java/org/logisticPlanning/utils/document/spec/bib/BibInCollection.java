package org.logisticPlanning.utils.document.spec.bib;

import java.net.URI;

/**
 * a bibliographic record for a paper that appeared in a proceedings
 */
public class BibInCollection extends _BibInBook<BibBook> {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * Create a new bibliography record for papers that appeared in a
   * collection/book
   *
   * @param authors
   *          the authors
   * @param title
   *          the title
   * @param uri
   *          the uri
   * @param collection
   *          the collection
   * @param startPage
   *          the start page
   * @param endPage
   *          the end page
   * @param chapter
   *          the chapter
   * @param doi
   *          the doi
   */
  public BibInCollection(final BibAuthors authors, final String title,
      final BibBook collection, final String startPage,
      final String endPage, final String chapter, final URI uri,
      final String doi) {
    super(authors, title, collection, startPage, endPage, chapter, uri,
        doi);
  }
}
