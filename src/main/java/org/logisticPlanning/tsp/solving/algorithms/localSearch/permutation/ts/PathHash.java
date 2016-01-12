package org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.ts;

import java.util.Arrays;

import org.logisticPlanning.tsp.solving.utils.RepresentationUtils;

/** A key that can be used to represent permutations in hashes. */
public final class PathHash implements Cloneable {

  /** the data, i.e., permutation */
  int[] m_data;

  /** the hash */
  int m_hash;

  /**
   * create the permutation hash key
   *
   * @param data
   *          the data
   */
  public PathHash(final int[] data) {
    super();
    this.setData(data);
  }

  /**
   * create the permutation hash key
   *
   * @param length
   *          the length
   */
  public PathHash(final int length) {
    super();
    this.m_data = new int[length];
  }

  /**
   * Set the data array, i.e., the permutation
   *
   * @param data
   *          the data
   */
  public final void setData(final int[] data) {
    this.m_data = data;
    this.updateHashCode();
  }

  /** Update the hash code after the data has been changed */
  public final void updateHashCode() {
    this.m_hash = RepresentationUtils.pathHashCode(this.m_data);
  }

  /**
   * Get the data, i.e., the permutation
   *
   * @return the permutation
   */
  public final int[] getData() {
    return this.m_data;
  }

  /** {@inheritDoc} */
  @Override
  public final int hashCode() {
    return this.m_hash;
  }

  /** {@inheritDoc} */
  @Override
  public final boolean equals(final Object o) {
    return ((o == this) || //
        ((o instanceof PathHash) && //
            RepresentationUtils.arePathsEquivalentSTSP(this.m_data,
                ((PathHash) o).m_data)));
  }

  /** {@inheritDoc} */
  @Override
  public final String toString() {
    return (this.m_hash + ": " + //$NON-NLS-1$
        Arrays.toString(this.m_data));
  }

  /** {@inheritDoc} */
  @Override
  public final PathHash clone() {
    PathHash old;
    try {
      old = ((PathHash) (super.clone()));
    } catch (@SuppressWarnings("unused") final Throwable tt) {
      return null;
    }
    old.m_data = old.m_data.clone();

    return old;
  }
}
