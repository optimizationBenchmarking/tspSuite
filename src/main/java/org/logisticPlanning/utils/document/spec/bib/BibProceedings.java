package org.logisticPlanning.utils.document.spec.bib;

import java.net.URI;

import org.logisticPlanning.utils.utils.HashUtils;
import org.logisticPlanning.utils.utils.comparison.ComparisonUtils;

/**
 * a bibliographic record for a proceedings
 */
public class BibProceedings extends BibBook {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the location @serial serial field */
  private final String m_location;

  /** the end date @serial serial field */
  private final BibDate m_endDate;

  /**
   * Create a new bibliography record
   *
   * @param title
   *          the title
   * @param uri
   *          the uri
   * @param startDate
   *          the start date
   * @param endDate
   *          the end date
   * @param editors
   *          the book's editors
   * @param publisher
   *          the publisher
   * @param address
   *          the address
   * @param series
   *          the series
   * @param volume
   *          the volume
   * @param location
   *          the location
   * @param doi
   *          the doi
   */
  public BibProceedings(final String title, final BibDate startDate,
      final BibDate endDate, final BibAuthors editors,
      final String location, final String publisher, final String address,
      final String series, final String volume, final URI uri,
      final String doi) {
    super(BibAuthors.EMPTY, title, startDate, editors, publisher, address,
        series, volume, null, uri, doi, true, false);

    this.m_location = _BibElement.prepare(location);
    this.m_endDate = ((endDate != null) ? //
        ((endDate.equals(startDate)) ? startDate : endDate)//
        : startDate);
    if (this.m_endDate.m_hashCode < startDate.m_hashCode) {
      throw new IllegalArgumentException(//
          "End date must not be before start date.");//$NON-NLS-1$
    }

    this.m_hashCode = this._hashCode();
  }

  /** {@inheritDoc} */
  @Override
  int _hashCode() {
    return HashUtils.combineHashes(
        HashUtils.combineHashes(super._hashCode(),
            HashUtils.hashCode(this.m_endDate)),
            HashUtils.hashCode(this.m_location));
  }

  /**
   * Get the location
   *
   * @return the location
   */
  public final String getLocation() {
    return this.m_location;
  }

  /**
   * Get the start date of the conference
   *
   * @return the start date of the conference
   */
  public final BibDate getStartDate() {
    return this.getDate();
  }

  /**
   * Get the end date of the conference
   *
   * @return the end date of the conference
   */
  public final BibDate getEndDate() {
    return this.m_endDate;
  }

  /** {@inheritDoc} */
  @Override
  boolean _equals(final BibRecord r) {
    final BibProceedings x;

    if (super._equals(r)) {
      x = ((BibProceedings) r);

      return (ComparisonUtils.equals(this.m_location, x.m_location) && //
          ComparisonUtils.equals(this.m_endDate, x.m_endDate));
    }

    return false;
  }
}
