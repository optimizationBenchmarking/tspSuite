package org.logisticPlanning.tsp.solving.algorithms.localSearch;

/**
 * A local search used in an mutation operation may terminate under
 * different conditions.
 */
public enum ELocalSearchTermination {

  /**
   * Do not terminate the local search before all runtime budget is used
   * up.
   */
  NEVER() {
    /** {@inheritDoc} */
    @Override
    public final boolean shouldTerminate(final long currentTourLength,
        final long originalTourLength) {
      return false;
    }
  },

  /**
   * Terminate the local search if a solution was found which is different
   * from the input solution.
   */
  TERMINATE_IF_DIFFERENT() {
    /** {@inheritDoc} */
    @Override
    public final boolean shouldTerminate(final long currentTourLength,
        final long originalTourLength) {
      return (currentTourLength != originalTourLength);
    }
  },

  /**
   * Terminate the local search if a solution was found which is better
   * than the input solution.
   */
  TERMINATE_IF_BETTER() {
    /** {@inheritDoc} */
    @Override
    public final boolean shouldTerminate(final long currentTourLength,
        final long originalTourLength) {
      return (currentTourLength < originalTourLength);
    }
  },

  /**
   * Terminate the local search if a solution was found which is better
   * than or equal to the input solution.
   */
  TERMINATE_IF_BETTER_OR_EQUAL() {
    /** {@inheritDoc} */
    @Override
    public final boolean shouldTerminate(final long currentTourLength,
        final long originalTourLength) {
      return (currentTourLength < originalTourLength);
    }
  },
  ;

  /**
   * Should the local search terminate
   * 
   * @param currentTourLength
   *          the current tour length
   * @param originalTourLength
   *          the original tour length
   * @return {@code true} if the local search should terminate,
   *         {@code false} otherwise
   */
  public abstract boolean shouldTerminate(final long currentTourLength,
      final long originalTourLength);
}
