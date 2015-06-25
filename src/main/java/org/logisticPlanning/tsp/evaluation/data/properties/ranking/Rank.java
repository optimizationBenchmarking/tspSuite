package org.logisticPlanning.tsp.evaluation.data.properties.ranking;

import java.util.Map;

import org.logisticPlanning.utils.collections.basic.BasicAssociation;
import org.logisticPlanning.utils.utils.HashUtils;
import org.logisticPlanning.utils.utils.comparison.ComparisonUtils;

/**
 * An assignment of a rank to an object. We apply
 * <em>fractional ranking</em> &nbsp;[<a href="#cite_WIKIPEDIA2013RANKING"
 * style="font-weight:bold">1</a>], as described in the documentation of
 * classes
 * {@link org.logisticPlanning.tsp.evaluation.data.properties.ranking.Ranking
 * Ranking} and
 * {@link org.logisticPlanning.tsp.evaluation.modules.impl.descriptions.eval.DescRanking
 * DescRanking} in detail.
 *
 * @param <T>
 *          the type of object being ranked <h2>References</h2>
 *          <ol>
 *          <li><div><span id="cite_WIKIPEDIA2013RANKING" /><span
 *          style="font-style:italic;font-family:cursive;"
 *          >&ldquo;Ranking,&rdquo;</span> (Website), August&nbsp;21, 2013,
 *          San Francisco, CA, USA: Wikimedia Foundation, Inc. and&nbsp;San
 *          Francisco, CA, USA: Wikimedia Foundation, Inc.. <div>link: [<a
 *          href="http://en.wikipedia.org/wiki/Ranking">1</a>]</div></div></li>
 *          </ol>
 */
public class Rank<T> extends BasicAssociation<T, Double> implements
    Comparable<Rank<T>> {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the object being ranked */
  private final T m_key;

  /** the rank */
  private double m_rank;

  /**
   * Create the rank object
   *
   * @param key
   *          the object being ranked
   */
  public Rank(final T key) {
    super(key);

    if (key == null) {
      throw new IllegalArgumentException();
    }

    this.m_key = key;
    this.m_rank = Double.NaN;
  }

  /**
   * Check if this object can be modified. This method will throw an
   * {@link java.lang.IllegalStateException} if the object has already been
   * assigned a rank.
   */
  protected void checkModify() {
    if (this.m_rank != this.m_rank) {
      return;
    }
    throw new IllegalStateException(//
        "Rank cannot be assigned twice: Object '" + this.m_key + //$NON-NLS-1$
            "' has already rank '" + this.m_rank + //$NON-NLS-1$
            "' and cannot be modified anymore."); //$NON-NLS-1$
  }

  /**
   * Set the rank of this object. This method can only be called once.
   *
   * @param r
   *          the rank
   */
  final void setRank(final double r) {
    this.checkModify();
    if ((r >= 1d) && (r < Double.POSITIVE_INFINITY) && (r == r)) {
      this.m_rank = r;
    } else {
      throw new IllegalArgumentException(//
          "Object '" + this.m_key + //$NON-NLS-1$
              "' cannot get invalid rank '" + r + //$NON-NLS-1$
              "'.");//$NON-NLS-1$
    }
  }

  /**
   * Get the rank of the {@link #getKey() object}
   *
   * @return the rank
   */
  public final double getRank() {
    return this.m_rank;
  }

  /** {@inheritDoc} */
  @Override
  public final int compareTo(final Rank<T> o) {

    if (o == this) {
      return 0;
    }

    if (o == null) {
      return (-1);
    }

    if (this.m_rank < o.m_rank) {
      return (-1);
    }
    if (this.m_rank > o.m_rank) {
      return 1;
    }

    return 0;
  }

  /**
   * Get the rank
   *
   * @return the rank
   */
  @Override
  public final Double getValue() {
    return Double.valueOf(this.m_rank);
  }

  /** {@inheritDoc} */
  @Override
  public final String toString() {
    return ((String.valueOf(this.m_key) + '=') + this.m_rank);
  }

  /** {@inheritDoc} */
  @Override
  public final int hashCode() {
    return HashUtils.combineHashes(//
        HashUtils.hashCode(this.m_key),//
        HashUtils.hashCode(this.m_rank));
  }

  /** {@inheritDoc} */
  @SuppressWarnings("rawtypes")
  @Override
  public final boolean equals(final Object o) {
    final Map.Entry e;
    final Rank r;

    if (o == this) {
      return true;
    }

    if (o instanceof Rank) {
      r = ((Rank) o);
      return (ComparisonUtils.equals(this.getKey(), r.getKey()) && //
      (this.m_rank == r.m_rank));
    }

    if (o instanceof Map.Entry) {
      e = ((Map.Entry) o);
      return (ComparisonUtils.equals(this.m_key, e.getKey()) && //
      ComparisonUtils.equals(this.getValue(), e.getValue()));
    }

    return false;
  }
}
