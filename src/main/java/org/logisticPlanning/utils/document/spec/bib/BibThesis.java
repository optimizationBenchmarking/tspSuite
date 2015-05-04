package org.logisticPlanning.utils.document.spec.bib;

import java.net.URI;

import org.logisticPlanning.utils.utils.HashUtils;
import org.logisticPlanning.utils.utils.comparison.ComparisonUtils;

/**
 * a bibliographic for a book
 */
public class BibThesis extends BibBook {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the thesis type @serial serial field */
  private final EThesisType m_type;

  /** the school @serial serial field */
  private final String m_school;

  /** the school's address @serial serial field */
  private final String m_schoolAddress;

  /**
   * Create a new bibliography record
   *
   * @param authors
   *          the authors
   * @param title
   *          the title
   * @param type
   *          the thesis type
   * @param school
   *          the school
   * @param schoolAddress
   *          the school's address
   * @param uri
   *          the uri
   * @param date
   *          the date
   * @param publisher
   *          the publisher
   * @param publisherAddress
   *          the publisher's address
   * @param series
   *          the series
   * @param volume
   *          the volume
   * @param doi
   *          the doi
   */
  public BibThesis(final BibAuthors authors, final String title,
      final BibDate date, final EThesisType type, final String school,
      final String schoolAddress, final String publisher,
      final String publisherAddress, final String series,
      final String volume, final String doi, final URI uri) {
    super(authors, title, date, BibAuthors.EMPTY, publisher,
        publisherAddress, series, volume, null, uri, doi, false, true);

    if (type == null) {
      throw new IllegalArgumentException(//
          "Thesis type must not be empty.");//$NON-NLS-1$
    }

    this.m_school = _BibElement.prepare(school);
    if (this.m_school == null) {
      throw new IllegalArgumentException(//
          "School must not be empty.");//$NON-NLS-1$
    }

    this.m_schoolAddress = _BibElement.prepare(schoolAddress);
    if (this.m_schoolAddress == null) {
      throw new IllegalArgumentException(//
          "School's address must not be empty.");//$NON-NLS-1$
    }

    this.m_type = type;
    this.m_hashCode = this._hashCode();
  }

  /** {@inheritDoc} */
  @Override
  final int _hashCode() {
    return HashUtils.combineHashes(
        //
        HashUtils.combineHashes(super._hashCode(),//
            HashUtils.hashCode(this.m_school)),//
            HashUtils.combineHashes(HashUtils.hashCode(this.m_schoolAddress),//
                HashUtils.hashCode(this.m_type)));
  }

  /**
   * Get the thesis type
   *
   * @return the thesis type
   */
  public final EThesisType getType() {
    return this.m_type;
  }

  /**
   * Get the school
   *
   * @return the school
   */
  public final String getSchool() {
    return this.m_school;
  }

  /**
   * Get the school's address
   *
   * @return the school's address
   */
  public final String getSchoolAddress() {
    return this.m_schoolAddress;
  }

  /** {@inheritDoc} */
  @Override
  final boolean _equals(final BibRecord r) {
    final BibThesis x;

    if (super._equals(r)) {
      x = ((BibThesis) r);

      return (ComparisonUtils.equals(this.m_type, x.m_type) && //
          ComparisonUtils.equals(this.m_school, x.m_school) && //
          ComparisonUtils.equals(this.m_schoolAddress, x.m_schoolAddress));
    }

    return false;
  }

  /** {@inheritDoc} */
  @Override
  final int compareRest(final BibRecord o) {
    BibThesis bb;
    int r;

    if (o instanceof BibThesis) {
      bb = ((BibThesis) o);

      r = ComparisonUtils.compare(this.m_type, bb.m_type);
      if (r != 0) {
        return r;
      }

      r = ComparisonUtils.compare(this.m_school, bb.m_school);
      if (r != 0) {
        return r;
      }

      r = ComparisonUtils
          .compare(this.m_schoolAddress, bb.m_schoolAddress);
      if (r != 0) {
        return r;
      }
    }

    return super.compareRest(o);
  }
}
