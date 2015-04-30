package org.logisticPlanning.utils.document.spec.bib;

import java.net.URI;

import org.logisticPlanning.utils.utils.HashUtils;
import org.logisticPlanning.utils.utils.comparison.ComparisonUtils;

/**
 * a bibliographic for a book
 */
public class BibTechReport extends BibRecord {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the institute */
  private final String m_institute;
  /** the institute's address */
  private final String m_instituteAddress;
  /** the series */
  private final String m_series;
  /** the number */
  private final String m_number;

  /**
   * create a record for technical records
   * 
   * @param authors
   *          the authors
   * @param title
   *          the title
   * @param date
   *          the date
   * @param institute
   *          the institute
   * @param instituteAddress
   *          the address of the institute
   * @param series
   *          the series
   * @param number
   *          the number
   * @param uri
   *          the uri
   * @param doi
   *          the doi
   */
  public BibTechReport(final BibAuthors authors, final String title,
      final BibDate date, final String institute,
      final String instituteAddress, final String series,
      final String number, final URI uri, final String doi) {
    super(authors, title, date, uri, doi);

    this.m_institute = _BibElement.prepare(institute);
    if (this.m_institute == null) {
      throw new IllegalArgumentException(//
          "Institute must not be empty.");//$NON-NLS-1$
    }

    this.m_instituteAddress = _BibElement.prepare(instituteAddress);
    if (this.m_instituteAddress == null) {
      throw new IllegalArgumentException(//
          "Institute's address must not be empty.");//$NON-NLS-1$
    }

    this.m_series = _BibElement.prepare(series);
    this.m_number = _BibElement.prepare(number);

    this.m_hashCode = this._hashCode();
  }

  /** {@inheritDoc} */
  @Override
  final int _hashCode() {
    return HashUtils.combineHashes(
        //
        HashUtils.combineHashes(super._hashCode(),//
            HashUtils.hashCode(this.m_institute)),//
        HashUtils.combineHashes(
            HashUtils.hashCode(this.m_instituteAddress),//
            HashUtils.combineHashes(
                //
                HashUtils.hashCode(this.m_series),
                HashUtils.hashCode(this.m_number))));
  }

  /**
   * Get the institute
   * 
   * @return the institute
   */
  public final String getInstitute() {
    return this.m_institute;
  }

  /**
   * Get the institute's address
   * 
   * @return the institute's address
   */
  public final String getInstituteAddress() {
    return this.m_instituteAddress;
  }

  /**
   * Get the series
   * 
   * @return the series
   */
  public final String getSeries() {
    return this.m_series;
  }

  /**
   * Get the number
   * 
   * @return the number
   */
  public final String getNumber() {
    return this.m_number;
  }

  /** {@inheritDoc} */
  @Override
  final boolean _equals(final BibRecord r) {
    final BibTechReport x;

    if (super._equals(r)) {
      x = ((BibTechReport) r);

      return (ComparisonUtils.equals(this.m_institute, x.m_institute) && //
          ComparisonUtils.equals(this.m_instituteAddress,
              x.m_instituteAddress) && //
          ComparisonUtils.equals(this.m_series, x.m_series) && //
      ComparisonUtils.equals(this.m_number, x.m_number));
    }

    return false;
  }

  /** {@inheritDoc} */
  @Override
  final int compareRest(final BibRecord o) {
    BibTechReport bb;
    int r;

    if (o instanceof BibTechReport) {
      bb = ((BibTechReport) o);

      r = ComparisonUtils.compare(this.m_institute, bb.m_institute);
      if (r != 0) {
        return r;
      }

      r = ComparisonUtils.compare(this.m_instituteAddress,
          bb.m_instituteAddress);
      if (r != 0) {
        return r;
      }

      r = ComparisonUtils.compare(this.m_series, bb.m_series);
      if (r != 0) {
        return r;
      }

      r = ComparisonUtils.compare(this.m_number, bb.m_number);
      if (r != 0) {
        return r;
      }
    }

    return super.compareRest(o);
  }
}
