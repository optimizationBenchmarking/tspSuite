package org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.chainedMNSLocalOpt;

import java.util.Comparator;

/**
 * <p>
 * This comparator defines how moves are compared and sorted in a
 * {@link org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.multiNeighborhoodSearch.MultiNeighborhoodSearch
 * neighborhood manager}.
 */
public enum EMoveComparator implements Comparator<_Move> {

  /** bring the best move first */
  BEST_MOVE_FIRST {
    /** {@inheritDoc} */
    @Override
    public final int compare(final _Move a, final _Move b) {
      return Integer.compare(a.m_delta, b.m_delta);
    }
  },

  /**
   * Bring the best move first, depending on its type.
   */
  TYPE_FIRST_THEN_BEST_MOVE {
    /** {@inheritDoc} */
    @Override
    public final int compare(final _Move a, final _Move b) {
      if (a.m_type < b.m_type) {
        return (-1);
      }
      if (a.m_type > b.m_type) {
        return (1);
      }
      return Integer.compare(a.m_delta, b.m_delta);
    }
  },

}
