package org.logisticPlanning.utils.document.spec.bib;

import java.net.URI;

import org.logisticPlanning.utils.utils.HashUtils;
import org.logisticPlanning.utils.utils.comparison.ComparisonUtils;

/**
 * a bibliographic for a website
 */
public class BibWebsite extends BibRecord {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the publisher @serial serial field */
  private final String m_publisher;
  /** the publisher's address @serial serial field */
  private final String m_address;

  /**
   * Create a new bibliography record
   * 
   * @param authors
   *          the authors
   * @param title
   *          the title
   * @param date
   *          the date
   * @param publisher
   *          the publisher
   * @param address
   *          the address
   * @param uri
   *          the uri
   */
  public BibWebsite(final BibAuthors authors, final String title,
      final BibDate date, final String publisher, final String address,
      final URI uri) {

    super(authors, title, date, uri, null);

    this.m_publisher = _BibElement.prepare(publisher);
    this.m_address = _BibElement.prepare(address);
    if ((this.m_publisher != null) && (this.m_address == null)) {
      throw new IllegalArgumentException(//
          "If publisher is specified, the publisher's address must not be empty.");//$NON-NLS-1$
    }

    this.m_hashCode = this._hashCode();
  }

  /** {@inheritDoc} */
  @Override
  int _hashCode() {
    return HashUtils.combineHashes(
        HashUtils.combineHashes(super._hashCode(),
            HashUtils.hashCode(this.m_address)),
        HashUtils.hashCode(this.m_publisher));
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

  /** {@inheritDoc} */
  @Override
  boolean _equals(final BibRecord r) {
    final BibWebsite x;

    if (super._equals(r)) {
      x = ((BibWebsite) r);

      return (ComparisonUtils.equals(this.m_publisher, x.m_publisher) && //
      ComparisonUtils.equals(this.m_address, x.m_address));

    }

    return false;
  }

  /** {@inheritDoc} */
  @Override
  int compareRest(final BibRecord o) {
    BibWebsite bb;
    int r;

    if (o instanceof BibWebsite) {
      bb = ((BibWebsite) o);

      r = ComparisonUtils.compare(this.m_publisher, bb.m_publisher);
      if (r != 0) {
        return r;
      }

      r = ComparisonUtils.compare(this.m_address, bb.m_address);
      if (r != 0) {
        return r;
      }
    }

    return super.compareRest(o);
  }
}
