package org.logisticPlanning.utils.document.spec.bib;

import java.net.URI;

import org.logisticPlanning.utils.text.TextUtils;
import org.logisticPlanning.utils.utils.HashUtils;
import org.logisticPlanning.utils.utils.comparison.ComparisonUtils;

/**
 * a bibliographic record
 */
public abstract class BibRecord extends _BibElement<BibRecord> {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the authors @serial serial field */
  private final BibAuthors m_authors;

  /** the title @serial serial field */
  private final String m_title;

  /** the start date @serial serial field */
  private final BibDate m_date;

  /** the uri @serial serial field */
  private final URI m_uri;

  /** the doi @serial serial field */
  private final String m_doi;

  /**
   * Create a new bibliography record
   *
   * @param authors
   *          the authors
   * @param title
   *          the title
   * @param date
   *          the date of the record
   * @param uri
   *          the uri
   * @param doi
   *          the doi
   */
  BibRecord(final BibAuthors authors, final String title,
      final BibDate date, final URI uri, final String doi) {
    super();

    String d;

    if (authors == null) {
      throw new IllegalArgumentException(//
          "Authors must not be null.");//$NON-NLS-1$
    }

    if (date == null) {
      throw new IllegalArgumentException(//
          "The date must not be null.");//$NON-NLS-1$
    }

    this.m_title = _BibElement.prepare(title);
    if (this.m_title == null) {
      throw new IllegalArgumentException(//
          "Title must not be null."); //$NON-NLS-1$
    }

    d = _BibElement.prepare(doi);
    if (d != null) {
      if (d.startsWith("http://dx.doi.org/")) {//$NON-NLS-1$
        d = TextUtils.prepare(d.substring(18));
      } else {
        if (d.startsWith("doi:")) { //$NON-NLS-1$
          d = TextUtils.prepare(d.substring(4));
        }
      }
    }
    this.m_doi = d;

    this.m_authors = authors;
    this.m_uri = uri;
    this.m_date = date;
  }

  /**
   * Get the bibliography authors
   *
   * @return the bibliography authors
   */
  public final BibAuthors getAuthors() {
    return this.m_authors;
  }

  /**
   * Get the title
   *
   * @return the title
   */
  public final String getTitle() {
    return this.m_title;
  }

  /**
   * Get the doi
   *
   * @return the doi
   */
  public final String getDoi() {
    return this.m_doi;
  }

  /**
   * Get the uri
   *
   * @return the uri
   */
  public final URI getURI() {
    return this.m_uri;
  }

  /**
   * get the bibliography date
   *
   * @return the bibliography date
   */
  public final BibDate getDate() {
    return this.m_date;
  }

  /** {@inheritDoc} */
  @Override
  public final boolean equals(final Object o) {
    if (o == this) {
      return true;
    }
    if (o == null) {
      return false;
    }

    if (o.getClass() == this.getClass()) {
      return this._equals((BibRecord) o);
    }
    return false;
  }

  /**
   * compare to another bib record
   *
   * @param r
   *          the bib record
   * @return {@code true} if this object is the
   */
  boolean _equals(final BibRecord r) {
    return (ComparisonUtils.equals(this.m_authors, r.m_authors) && //
        ComparisonUtils.equals(this.m_title, r.m_title) && //
        ComparisonUtils.equals(this.m_date, r.m_date) && //
        ComparisonUtils.equals(this.m_uri, r.m_uri) && //
    ComparisonUtils.equals(this.m_doi, r.m_doi));
  }

  /** {@inheritDoc} */
  @Override
  int _hashCode() {
    return HashUtils.combineHashes(
        //
        HashUtils.combineHashes(//
            HashUtils.hashCode(this.m_authors),//
            HashUtils.hashCode(this.m_date)),//
        HashUtils.combineHashes(
            //
            HashUtils.combineHashes(
                //
                HashUtils.hashCode(this.m_title),
                HashUtils.hashCode(this.m_uri)),
            HashUtils.hashCode(this.m_doi))

    );
  }

  /** {@inheritDoc} */
  @Override
  public final int compareTo(final BibRecord o) {
    int r;

    if (o == this) {
      return 0;
    }

    if (o == null) {
      return (-1);
    }

    r = ComparisonUtils.compare(this.m_date, o.m_date);
    if (r != 0) {
      return r;
    }

    r = ComparisonUtils.compare(this.m_authors, o.m_authors);
    if (r != 0) {
      return r;
    }

    r = ComparisonUtils.compare(this.m_title, o.m_title);
    if (r != 0) {
      return r;
    }

    return this.compareRest(o);
  }

  /**
   * compare the rest of the bib record
   *
   * @param o
   *          the other bib record
   * @return the result
   */
  int compareRest(final BibRecord o) {
    return ComparisonUtils.compare(this.m_uri, o.m_uri);
  }

  /** {@inheritDoc} */
  @Override
  final void toStringBuilder(final StringBuilder sb) {
    if (this.m_authors.size() > 0) {
      this.m_authors.toStringBuilder(sb);
      sb.append(':');
      sb.append(' ');
    }
    sb.append('"');
    sb.append(this.m_title);
    sb.append(',');
    sb.append('"');
    this.m_date.toStringBuilder(sb);
    if (this.m_uri != null) {
      sb.append(", link: "); //$NON-NLS-1$
      sb.append(this.m_uri);
    }
    if (this.m_doi != null) {
      sb.append(", doi:"); //$NON-NLS-1$
      sb.append(this.m_doi);
    }
  }
}
