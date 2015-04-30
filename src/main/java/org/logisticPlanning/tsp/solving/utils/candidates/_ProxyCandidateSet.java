package org.logisticPlanning.tsp.solving.utils.candidates;

/**
 * A proxy-candidate set is a candidate set that behaves exactly like the
 * original problem, where each node has
 * <code>{@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction#n()
 * n}-1</code> neighbors
 */
final class _ProxyCandidateSet extends CandidateSet {

  /** instantiate */
  _ProxyCandidateSet() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public final int getCandidate(final int node, final int id) {
    return ((id >= node) ? (id + 1) : id);
  }

  /** {@inheritDoc} */
  @Override
  public final int getPseudoID(final int node, final int candidateNode) {
    return ((candidateNode > node) ? (candidateNode - 1) : candidateNode);
  }
}
