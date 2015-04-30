package org.logisticPlanning.utils.document.spec.bib;

import java.util.Arrays;
import java.util.Iterator;

import org.logisticPlanning.utils.collections.iterators.ArrayIterator;

/**
 * a bibliography authors
 */
public class BibAuthors extends _BibElement<BibAuthors> implements
    Iterable<BibAuthor> {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the empty set of authors */
  public static final BibAuthors EMPTY = new BibAuthors(false,
      new BibAuthor[0]);

  /** the authors */
  private final BibAuthor[] m_authors;

  /**
   * Create the bibliography authors.
   * 
   * @param authors
   *          the authors
   */
  public BibAuthors(final BibAuthor... authors) {
    this(true, authors);
  }

  /**
   * Create the bibliography authors.
   * 
   * @param authors
   *          the authors
   * @param cannotBeEmpty
   *          the author list cannot be empty
   */
  private BibAuthors(final boolean cannotBeEmpty,
      final BibAuthor... authors) {
    super();

    int i, j;
    BibAuthor a;

    if (authors == null) {
      throw new IllegalArgumentException(//
          "Authors cannot be null."); //$NON-NLS-1$
    }

    if (cannotBeEmpty) {
      if (authors.length <= 0) {
        throw new IllegalArgumentException(//
            "Authors must contain at least one author.");//$NON-NLS-1$
      }
    }

    for (i = authors.length; (--i) >= 0;) {
      a = authors[i];

      if (a == null) {
        throw new IllegalArgumentException(//
            "Author cannot be null."); //$NON-NLS-1$ 
      }

      for (j = i; (--j) >= 0;) {
        if (a.equals(authors[j])) {
          throw new IllegalArgumentException("Author '" + a + //$NON-NLS-1$ 
              "' occurs twice in author list."); //$NON-NLS-1$ 
        }
      }
    }

    this.m_authors = (cannotBeEmpty ? authors.clone() : authors);
    this.m_hashCode = this._hashCode();
  }

  /** {@inheritDoc} */
  @Override
  final int _hashCode() {
    return Arrays.hashCode(this.m_authors);
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
    if (o instanceof BibAuthors) {
      return Arrays.equals(this.m_authors, ((BibAuthors) o).m_authors);
    }
    return false;
  }

  /** {@inheritDoc} */
  @Override
  public final int compareTo(final BibAuthors o) {
    final int l1, l2, l;
    int i, r;

    if (o == null) {
      return (-1);
    }
    if (o == this) {
      return 0;
    }

    l1 = this.m_authors.length;
    l2 = o.m_authors.length;
    l = Math.min(l1, l2);

    for (i = 0; i < l; i++) {
      r = this.m_authors[i].compareTo(o.m_authors[i]);
      if (r != 0) {
        return r;
      }
    }

    return (l1 - l2);
  }

  /**
   * Get the number of authors.
   * 
   * @return the number of authors
   */
  public final int size() {
    return this.m_authors.length;
  }

  /**
   * Get the author at a specific index {@code i}
   * 
   * @param i
   *          the index
   * @return the author at a specific index {@code i}
   */
  public final BibAuthor get(final int i) {
    return this.m_authors[i];
  }

  /** {@inheritDoc} */
  @Override
  final void toStringBuilder(final StringBuilder sb) {
    int i;
    final int l;
    final BibAuthor[] nn;

    nn = this.m_authors;
    l = (nn.length - 1);
    for (i = 0; i < nn.length; i++) {
      if (i > 0) {
        if (l > 1) {
          sb.append(',');
        }
        sb.append(' ');
        if (i >= l) {
          sb.append("and "); //$NON-NLS-1$
        }
      }
      nn[i].toStringBuilder(sb);
    }
  }

  /** {@inheritDoc} */
  @Override
  public final Iterator<BibAuthor> iterator() {
    return new ArrayIterator<>(this.m_authors);
  }

}
