package org.logisticPlanning.utils.document.spec.bib;

import java.net.URI;

import org.logisticPlanning.utils.utils.HashUtils;
import org.logisticPlanning.utils.utils.comparison.ComparisonUtils;

/**
 * a bibliographic for a book
 */
public class BibArticle extends BibRecord {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the journal */
  private final String m_journal;
  /** the volume */
  private final String m_volume;
  /** the number */
  private final String m_number;
  /** the start page */
  private final String m_startPage;
  /** the end page */
  private final String m_endPage;

  /**
   * create a record for technical records
   *
   * @param authors
   *          the authors
   * @param title
   *          the title
   * @param date
   *          the date
   * @param journal
   *          the journal
   * @param volume
   *          the volume
   * @param number
   *          the number
   * @param startPage
   *          the start page
   * @param endPage
   *          the end page
   * @param uri
   *          the uri
   * @param doi
   *          the doi
   */
  public BibArticle(final BibAuthors authors, final String title,
      final BibDate date, final String journal, final String volume,
      final String number, final String startPage, final String endPage,
      final URI uri, final String doi) {
    super(authors, title, date, uri, doi);

    this.m_journal = _BibElement.prepare(journal);
    if (this.m_journal == null) {
      throw new IllegalArgumentException(//
          "Journal must not be empty.");//$NON-NLS-1$
    }

    this.m_volume = _BibElement.prepare(volume);
    if (this.m_volume == null) {
      throw new IllegalArgumentException(//
          "Volume must not be empty.");//$NON-NLS-1$
    }

    this.m_number = _BibElement.prepare(number);
    if (this.m_number == null) {
      throw new IllegalArgumentException(//
          "Number must not be empty.");//$NON-NLS-1$
    }

    this.m_startPage = _BibElement.prepare(startPage);
    this.m_endPage = _BibElement.prepare(endPage);
    if (this.m_startPage == null) {
      if (this.m_endPage != null) {
        throw new IllegalArgumentException(//
            "Start page must not be empty if end page is defined.");//$NON-NLS-1$
      }
    }
    if (this.m_endPage == null) {
      if (this.m_startPage != null) {
        throw new IllegalArgumentException(//
            "End page must not be empty if start page is defined.");//$NON-NLS-1$
      }
    }

    this.m_hashCode = this._hashCode();
  }

  /** {@inheritDoc} */
  @Override
  final int _hashCode() {
    return HashUtils.combineHashes(
        //
        HashUtils.combineHashes(super._hashCode(),//
            HashUtils.hashCode(this.m_journal)),//
            HashUtils.combineHashes(
                HashUtils.hashCode(this.m_volume),//
                HashUtils.combineHashes(
                    //
                    HashUtils.combineHashes(
                        //
                        HashUtils.hashCode(this.m_number),
                        HashUtils.hashCode(this.m_startPage)),
                        HashUtils.hashCode(this.m_endPage))));
  }

  /**
   * Get the journal
   *
   * @return the journal
   */
  public final String getJournal() {
    return this.m_journal;
  }

  /**
   * Get the volume
   *
   * @return the volume
   */
  public final String getVolume() {
    return this.m_volume;
  }

  /**
   * Get the number
   *
   * @return the number
   */
  public final String getNumber() {
    return this.m_number;
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

  /** {@inheritDoc} */
  @Override
  final boolean _equals(final BibRecord r) {
    final BibArticle x;

    if (super._equals(r)) {
      x = ((BibArticle) r);

      return (ComparisonUtils.equals(this.m_journal, x.m_journal) && //
          ComparisonUtils.equals(this.m_volume, x.m_volume) && //
          ComparisonUtils.equals(this.m_number, x.m_number) && //
          ComparisonUtils.equals(this.m_startPage, x.m_startPage) && //
          ComparisonUtils.equals(this.m_endPage, x.m_endPage));
    }

    return false;
  }

  /** {@inheritDoc} */
  @Override
  final int compareRest(final BibRecord o) {
    BibArticle bb;
    int r;

    if (o instanceof BibArticle) {
      bb = ((BibArticle) o);

      r = ComparisonUtils.compare(this.m_journal, bb.m_journal);
      if (r != 0) {
        return r;
      }

      r = ComparisonUtils.compare(this.m_volume, bb.m_volume);
      if (r != 0) {
        return r;
      }

      r = ComparisonUtils.compare(this.m_number, bb.m_number);
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
    }

    return super.compareRest(o);
  }
}
