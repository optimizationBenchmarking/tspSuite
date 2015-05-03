package org.logisticPlanning.tsp.solving.utils.candidates;

import java.util.Arrays;

import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;

/**
 * <p>
 * A candidate set that presents {@code m} candidates per node, i.e., the
 * {@code m} nearest other nodes. The candidate set is backed by a
 * one-dimensional array of {@code int} which holds {@code n*m} elements,
 * where {@code n} is the total number of nodes.
 * </p>
 * <p>
 * The {@code m} candidates/neighbors of the node <code>i&isin;1..n</code>
 * are stored starting at index {@code (i-1)*m} (inclusive) to index
 * {@code i*m} (exclusive) in the backing array. This means that the the
 * {@code j} <sup>th</sup> neighbor of {@code i}, with
 * <code>j&isin;1..m</code>, is at index {@code ((i-1)*m)+j-1} in memory.
 * Finding whether node <code>k&isin;1..n</code> is a neighbor of {@code i}
 * can be done by applying a binary search in range {@code (i-1)*m}
 * (inclusive) to {@code i*m} (exclusive), i.e., in {@code O(log m)} steps.
 * </p>
 * <p>
 * I am not sure if this representation is the most efficient one, maybe I
 * can improve on it later. However, it is relatively straightforward and
 * requires little memory.
 * </p>
 */
final class _CandidateSubSet extends CandidateSet {

  /** the data */
  int[] m_candidates;

  /** instantiate */
  _CandidateSubSet() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  final void init(final ObjectiveFunction f, final int n, final int m) {
    final int matrixSize, listLen;
    int[] data;
    final _Candidate[] list;
    _Candidate c;
    int sourceNode, destNode, listIndex, i;

    this.m_m = m;
    matrixSize = (n * m);

    // allocate the internal data array
    data = this.m_candidates;
    if ((data == null) || (data.length < matrixSize)) {
      this.m_candidates = data = new int[matrixSize];
    }

    // allocate the temporary list
    listLen = (n - 1);
    list = new _Candidate[listLen];
    for (i = listLen; (--i) >= 0;) {
      list[i] = new _Candidate();
    }

    // fill the candidate list: structure candidate-distance
    // candidate-distance
    i = 0;
    for (sourceNode = 1; sourceNode <= n; sourceNode++) {

      // load the distances from node sourceNode
      listIndex = listLen;
      for (destNode = n; destNode > 0; destNode--) {
        if (destNode != sourceNode) {
          c = list[--listIndex];
          c.m_node = destNode;
          c.m_dist = f.distance(sourceNode, destNode);
        }
      }

      // choose the m nearest nodes
      Arrays.sort(list);
      destNode = i;
      for (listIndex = m; (--listIndex) >= 0;) {
        data[i++] = list[listIndex].m_node;
      }
      Arrays.sort(data, destNode, i);
    }
  }

  /** {@inheritDoc} */
  @Override
  public final int getCandidate(final int node, final int id) {
    return this.m_candidates[(((node - 1) * this.m_m) + id) - 1];
  }

  /** {@inheritDoc} */
  @Override
  public final int getPseudoID(final int node, final int candidateNode) {
    final int idx, start;

    start = ((node - 1) * this.m_m);
    idx = Arrays.binarySearch(this.m_candidates, start,
        (start + this.m_m), candidateNode);
    if (idx >= 0) {
      return ((idx - start) + 1);
    }
    return -1;
  }
}
