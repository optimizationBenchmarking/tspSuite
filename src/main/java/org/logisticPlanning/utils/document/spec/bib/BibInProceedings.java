package org.logisticPlanning.utils.document.spec.bib;

import java.net.URI;

/**
 * a bibliographic record for a paper that appeared in a proceedings
 */
public class BibInProceedings extends _BibInBook<BibProceedings> {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * Create a new bibliography record for papers that appeared in
   * proceedings
   *
   * @param authors
   *          the authors
   * @param title
   *          the title
   * @param uri
   *          the uri
   * @param proceedings
   *          the proceedings
   * @param startPage
   *          the start page
   * @param endPage
   *          the end page
   * @param chapter
   *          the chapter
   * @param doi
   *          the doi
   */
  public BibInProceedings(final BibAuthors authors, final String title,
      final BibProceedings proceedings, final String startPage,
      final String endPage, final String chapter, final URI uri,
      final String doi) {
    super(authors, title, proceedings, startPage, endPage, chapter, uri,
        doi);
  }
}
