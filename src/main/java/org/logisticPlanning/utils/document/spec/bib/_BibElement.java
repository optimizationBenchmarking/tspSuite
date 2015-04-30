package org.logisticPlanning.utils.document.spec.bib;

import java.io.Serializable;
import java.text.Normalizer;

import org.logisticPlanning.utils.text.TextUtils;

/**
 * a bibliography element
 * 
 * @param <T>
 *          the comparable type
 */
abstract class _BibElement<T extends _BibElement<T>> implements
    Serializable, Comparable<T> {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the hash code */
  transient int m_hashCode;

  /** create */
  _BibElement() {
    super();
  }

  /**
   * prepare a string
   * 
   * @param s
   *          the string
   * @return the prepared version
   */
  static final String prepare(final String s) {
    String t;

    t = TextUtils.prepare(s);
    if (t == null) {
      return null;
    }

    return TextUtils
        .prepare(Normalizer.normalize(t, Normalizer.Form.NFKC));
  }

  /** {@inheritDoc} */
  @Override
  public final int hashCode() {
    return this.m_hashCode;
  }

  /**
   * use to compute the hash code
   * 
   * @return the hash code
   */
  abstract int _hashCode();

  /**
   * store the textual representation of this record in a string builder
   * 
   * @param sb
   *          the destination string builder
   */
  abstract void toStringBuilder(final StringBuilder sb);

  /** {@inheritDoc} */
  @Override
  public final String toString() {
    final StringBuilder sb;
    sb = new StringBuilder();
    this.toStringBuilder(sb);
    return sb.toString();
  }
}
