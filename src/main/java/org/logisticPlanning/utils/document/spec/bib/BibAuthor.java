package org.logisticPlanning.utils.document.spec.bib;

import org.logisticPlanning.utils.utils.HashUtils;
import org.logisticPlanning.utils.utils.comparison.ComparisonUtils;

/**
 * a bibliography author
 */
public class BibAuthor extends _BibElement<BibAuthor> {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the personal name @serial serial field */
  private final String m_personalName;

  /** the family name @serial serial field */
  private final String m_familyName;

  /**
   * Create the bibliography author.
   *
   * @param personalName
   *          the personal name
   * @param familyName
   *          the family name
   */
  public BibAuthor(final String personalName, final String familyName) {
    super();

    this.m_personalName = _BibElement.prepare(personalName);
    if (this.m_personalName == null) {
      throw new IllegalArgumentException(//
          "Personal name cannot be null."); //$NON-NLS-1$
    }

    this.m_familyName = _BibElement.prepare(familyName);
    if (this.m_familyName == null) {
      throw new IllegalArgumentException(//
          "Family name cannot be null."); //$NON-NLS-1$
    }

    this.m_hashCode = this._hashCode();
  }

  /** {@inheritDoc} */
  @Override
  final int _hashCode() {
    return HashUtils.combineHashes(this.m_familyName.hashCode(),
        this.m_personalName.hashCode());
  }

  /**
   * get the personal name
   *
   * @return the personal name
   */
  public final String getPersonalName() {
    return this.m_personalName;
  }

  /**
   * get the family name
   *
   * @return the family name
   */
  public final String getFamilyName() {
    return this.m_familyName;
  }

  /** {@inheritDoc} */
  @Override
  public final boolean equals(final Object o) {
    final BibAuthor b;
    if (o == this) {
      return true;
    }
    if (o == null) {
      return false;
    }
    if (o instanceof BibAuthor) {
      b = ((BibAuthor) o);
      return (this.m_familyName.equals(b.m_familyName) && //
          this.m_personalName.equals(b.m_personalName));
    }
    return false;
  }

  /** {@inheritDoc} */
  @Override
  public final int compareTo(final BibAuthor o) {
    final int i;

    if (o == null) {
      return (-1);
    }
    if (o == this) {
      return 0;
    }

    i = ComparisonUtils.compare(this.m_familyName, o.m_familyName);
    if (i != 0) {
      return i;
    }

    return ComparisonUtils.compare(this.m_personalName, o.m_personalName);
  }

  /** {@inheritDoc} */
  @Override
  final void toStringBuilder(final StringBuilder sb) {
    sb.append(this.m_personalName);
    sb.append(' ');
    sb.append(this.m_familyName);
  }
}
