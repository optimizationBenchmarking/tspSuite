package org.logisticPlanning.tsp.solving.utils.candidates;

/**
 * the candidate
 */
final class _Candidate implements Comparable<_Candidate> {

  /** the node */
  int m_node;

  /** the distance */
  int m_dist;

  /** the constructor */
  _Candidate() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public final int compareTo(final _Candidate o) {
    return Integer.compare(this.m_dist, o.m_dist);
  }
}
