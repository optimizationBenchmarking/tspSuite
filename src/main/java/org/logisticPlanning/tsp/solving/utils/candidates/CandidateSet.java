package org.logisticPlanning.tsp.solving.utils.candidates;

import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;

/**
 * A candidate set that presents {@code m} candidates per node, i.e., the
 * {@code m} nearest other nodes. The candidates for each node receive
 * &quot;pseudo-node ids&quot; ranging from {@code 1} to {@code m}.
 */
public abstract class CandidateSet {

  /** the candidate count */
  int m_m;

  /** instantiate */
  CandidateSet() {
    super();
  }

  /**
   * Get the number of candidates
   *
   * @return the number of candidates
   */
  public final int m() {
    return this.m_m;
  }

  /**
   * Allocate and initialize a candidate set.
   *
   * @param f
   *          the objective function
   * @param m
   *          the number of candidates per node
   * @param old
   *          an old candidate set that can maybe be re-used
   * @return the candidate set, or {@code null} if the objective function
   *         should always be used (if {@code m<=0} or
   *         {@code m>=Integer.MAX_VALUE})
   */
  public static final CandidateSet allocate(final ObjectiveFunction f,
      final int m, final CandidateSet old) {
    final int n, nm1, mm;
    final CandidateSet ret;

    nm1 = ((n = f.n()) - 1);
    if ((m <= 0) || (m >= nm1)) {
      mm = nm1;
      if (old instanceof _ProxyCandidateSet) {
        ret = old;
      } else {
        ret = new _ProxyCandidateSet();
      }
    } else {
      mm = m;
      if (old instanceof _CandidateSubSet) {
        ret = old;
      } else {
        ret = new _CandidateSubSet();
      }
    }

    ret.init(f, n, mm);
    return ret;
  }

  /**
   * Get the neighbor candidate of node {@code node} at the given
   * {@code index} . Candidates are ordered by distance. The return value
   * is a real node name that can be passed, e.g., into the
   * {@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction#distance(int, int)
   * distance function}.
   *
   * @param node
   *          the node
   * @param id
   *          the pseudo id, a value in <code>1..{@link #m()}</code>, NOT
   *          {@code 0}
   * @return the candidate node
   */
  public abstract int getCandidate(final int node, final int id);

  /**
   * Get the pseudo id of a candidate node of a given node. The return
   * value is a value in <code>1..{@link #m()}</code> that can be passed as
   * id to {@link #getCandidate(int, int)}.
   *
   * @param node
   *          the node
   * @param candidateNode
   *          the candidate node
   * @return the id {@code i} for which {@link #getCandidate(int, int)}
   *         returns {@code candidateNode} when called with {@code node}
   *         and {@code i}, or {@code -1} if {@code candidateNode} is no
   *         candidate for {@code node}
   */
  public abstract int getPseudoID(final int node, final int candidateNode);

  /**
   * initialize the candidate set
   *
   * @param f
   *          the objective function
   * @param m
   *          the number of candidates per node
   * @param n
   *          the number of nodes n
   */
  void init(final ObjectiveFunction f, final int n, final int m) {
    this.m_m = m;
  }
}
