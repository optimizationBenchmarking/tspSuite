package org.logisticPlanning.utils.document.spec.bib;

import java.net.URI;

import org.logisticPlanning.utils.utils.HashUtils;
import org.logisticPlanning.utils.utils.comparison.ComparisonUtils;

/**
 * a bibliographic for a book
 */
public class BibBook extends BibRecord {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the editors @serial serial field */
  private final BibAuthors m_editors;

  /** the publisher @serial serial field */
  private final String m_publisher;
  /** the publisher's address @serial serial field */
  private final String m_address;

  /** the series @serial serial field */
  private final String m_series;

  /** the volume @serial serial field */
  private final String m_volume;

  /** the edition @serial serial field */
  private final String m_edition;

  /**
   * Create a new bibliography record
   * 
   * @param authors
   *          the authors
   * @param title
   *          the title
   * @param uri
   *          the uri
   * @param date
   *          the date
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
   * @param edition
   *          the edition
   * @param doi
   *          the doi
   */
  public BibBook(final BibAuthors authors, final String title,
      final BibDate date, final BibAuthors editors,
      final String publisher, final String address, final String series,
      final String volume, final String edition, final URI uri,
      final String doi) {
    this(authors, title, date, editors, publisher, address, series,
        volume, edition, uri, doi, true, true);
  }

  /**
   * Create a new bibliography record
   * 
   * @param authors
   *          the authors
   * @param title
   *          the title
   * @param uri
   *          the uri
   * @param date
   *          the date
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
   * @param edition
   *          the edition
   * @param publisherMustNotBeNull
   *          if the publisher can be null
   * @param needsAuthorsOrEditors
   *          are authors or editors needed?
   * @param doi
   *          the doi
   */
  BibBook(final BibAuthors authors, final String title,
      final BibDate date, final BibAuthors editors,
      final String publisher, final String address, final String series,
      final String volume, final String edition, final URI uri,
      final String doi, final boolean publisherMustNotBeNull,
      final boolean needsAuthorsOrEditors) {
    super(authors, title, date, uri, doi);

    if (editors == null) {
      throw new IllegalArgumentException(//
          "Editors must not be null.");//$NON-NLS-1$
    }
    this.m_editors = editors;

    if (needsAuthorsOrEditors) {
      if ((authors.size() + editors.size()) <= 0) {
        throw new IllegalArgumentException(//
            "Either authors or editors must be specified.");//$NON-NLS-1$
      }
    }

    this.m_publisher = _BibElement.prepare(publisher);
    if (publisherMustNotBeNull && (this.m_publisher == null)) {
      throw new IllegalArgumentException(//
          "Publisher must not be empty.");//$NON-NLS-1$
    }

    this.m_address = _BibElement.prepare(address);
    if ((this.m_publisher != null) && (this.m_address == null)) {
      throw new IllegalArgumentException(//
          "If publisher is specified, the publisher's address must not be empty.");//$NON-NLS-1$
    }

    this.m_series = _BibElement.prepare(series);
    this.m_volume = _BibElement.prepare(volume);
    if (volume != null) {
      if (this.m_series == null) {
        throw new IllegalArgumentException(//
            "If volume is not empty, then series must not be empty.");//$NON-NLS-1$
      }
    }

    this.m_edition = _BibElement.prepare(edition);

    this.m_hashCode = this._hashCode();
  }

  /** {@inheritDoc} */
  @Override
  int _hashCode() {
    return HashUtils.combineHashes(HashUtils.combineHashes(HashUtils
        .combineHashes(super._hashCode(),
            HashUtils.hashCode(this.m_address)), HashUtils.combineHashes(
        HashUtils.hashCode(this.m_edition),
        HashUtils.hashCode(this.m_editors))), HashUtils.combineHashes(
        HashUtils.combineHashes(HashUtils.hashCode(this.m_publisher),
            HashUtils.hashCode(this.m_series)), HashUtils
            .hashCode(this.m_volume)));
  }

  /**
   * Get the bibliography editors
   * 
   * @return the bibliography editors
   */
  public final BibAuthors getEditors() {
    return this.m_editors;
  }

  /**
   * Get the publisher
   * 
   * @return the publisher
   */
  public final String getPublisher() {
    return this.m_publisher;
  }

  /**
   * Get the publisher's address
   * 
   * @return the publisher's address
   */
  public final String getPublisherAddress() {
    return this.m_address;
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
   * Get the volume
   * 
   * @return the volume
   */
  public final String getVolume() {
    return this.m_volume;
  }

  /**
   * Get the edition
   * 
   * @return the edition
   */
  public final String getEdition() {
    return this.m_edition;
  }

  /** {@inheritDoc} */
  @Override
  boolean _equals(final BibRecord r) {
    final BibBook x;

    if (super._equals(r)) {
      x = ((BibBook) r);

      return (ComparisonUtils.equals(this.m_publisher, x.m_publisher) && //
          ComparisonUtils.equals(this.m_address, x.m_address) && //
          ComparisonUtils.equals(this.m_edition, x.m_edition) && //
          ComparisonUtils.equals(this.m_series, x.m_series) && //
      ComparisonUtils.equals(this.m_volume, x.m_volume));

    }

    return false;
  }

  /** {@inheritDoc} */
  @Override
  int compareRest(final BibRecord o) {
    BibBook bb;
    int r;

    if (o instanceof BibBook) {
      bb = ((BibBook) o);

      r = ComparisonUtils.compare(this.m_editors, bb.m_editors);
      if (r != 0) {
        return r;
      }

      r = ComparisonUtils.compare(this.m_series, bb.m_series);
      if (r != 0) {
        return r;
      }

      r = ComparisonUtils.compare(this.m_volume, bb.m_volume);
      if (r != 0) {
        return r;
      }

      r = ComparisonUtils.compare(this.m_publisher, bb.m_publisher);
      if (r != 0) {
        return r;
      }

      r = ComparisonUtils.compare(this.m_address, bb.m_address);
      if (r != 0) {
        return r;
      }

      r = ComparisonUtils.compare(this.m_edition, bb.m_edition);
      if (r != 0) {
        return r;
      }
    }

    return super.compareRest(o);
  }
}
