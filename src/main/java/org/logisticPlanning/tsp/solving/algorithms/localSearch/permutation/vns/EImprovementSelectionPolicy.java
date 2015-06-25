package org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.vns;

import org.logisticPlanning.utils.math.random.Randomizer;

/**
 * This enum tells us which kind of improvement should be used in the
 * permutation-based
 * {@link org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.vns.PermutationRNS
 * Variable Neighborhood Search} VNS: We can either use the best possible
 * improvement that an operator provides (which takes O(n^2) distance
 * evaluations to find) or the first possible improvement that can be
 * discovered (which, in the worst case, takes the same time but also might
 * be much faster).
 */
public enum EImprovementSelectionPolicy {

  /** always use the best improvement */
  ALWAYS_USE_BEST_IMPROVEMENT {
  // /** {@inheritDoc} */
  // @Override
  // public final boolean useFirstImprovement(){return false;
  // }
  },

  /** always use the first improvement discovered */
  ALWAYS_USE_FIRST_IMPROVEMENT {

    /** {@inheritDoc} */
    @Override
    public final boolean useFirstImprovement() {
      return true;
    }
  },

  /**
   * decide randomly, per call, whether the best improvement or the first
   * improvement discovered should be used
   */
  DECIDE_RANDOMLY_PER_CALL {
    /** {@inheritDoc} */
    @Override
    public final EImprovementSelectionPolicy getCallPolicy(
        final Randomizer r) {
      return (r.nextBoolean() ? EImprovementSelectionPolicy.ALWAYS_USE_BEST_IMPROVEMENT
          : EImprovementSelectionPolicy.ALWAYS_USE_FIRST_IMPROVEMENT);
    }
  },

  /**
   * decide randomly, per iteration, whether the best improvement or the
   * first improvement discovered should be used
   */
  DECIDE_RANDOMLY_PER_ITERATION {
    /** {@inheritDoc} */
    @Override
    public final EImprovementSelectionPolicy getIterationPolicy(
        final Randomizer r) {
      return (r.nextBoolean() ? EImprovementSelectionPolicy.ALWAYS_USE_BEST_IMPROVEMENT
          : EImprovementSelectionPolicy.ALWAYS_USE_FIRST_IMPROVEMENT);
    }
  }

  ;

  /** the default policy */
  public static final EImprovementSelectionPolicy DEFAULT = EImprovementSelectionPolicy.ALWAYS_USE_BEST_IMPROVEMENT;

  /**
   * Whether or not we should use the first improvement discovered (or
   * search for the best possible one) in the next call/run of the VNS
   *
   * @param r
   *          a randomizer
   * @return the policy for the next call of the vns
   */
  public EImprovementSelectionPolicy getCallPolicy(final Randomizer r) {
    return this;
  }

  /**
   * Whether or not we should use the first improvement discovered (or
   * search for the best possible one) in the next iteration of the VNS: A
   * new iteration in this context in this context begins after reaching a
   * new local optimum).
   *
   * @param r
   *          a randomizer
   * @return the policy to be applied after the current iteration
   */
  public EImprovementSelectionPolicy getIterationPolicy(final Randomizer r) {
    return this;
  }

  /**
   * Should we use the first improvement (returns {@code true}) or the best
   * possible improvement (returns {@code false}).
   *
   * @return {@code true} if the heuristic should use the first improvement
   *         it discovers, {@code false} if it should use the best one
   */
  public boolean useFirstImprovement() {
    return false;
  }
}
