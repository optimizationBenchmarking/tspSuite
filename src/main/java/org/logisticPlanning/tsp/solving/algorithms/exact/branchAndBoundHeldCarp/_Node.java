package org.logisticPlanning.tsp.solving.algorithms.exact.branchAndBoundHeldCarp;

/**
 * an internal class to represent nodes
 */
final class _Node {

  /** instantiate */
  _Node() {
    super();
  }

  /** the excluded stuff */
  boolean[][] m_excluded;

  /** Held-Karp solution: pi */
  double[] m_pi;

  /** Held-Karp solution: lower bound */
  double m_lowerBound;

  /** Held-Karp solution: degree */
  int[] m_degree;

  /** Held-Karp solution: parent */
  int[] m_parent;
}
