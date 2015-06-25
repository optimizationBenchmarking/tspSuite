package org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.random;

import org.logisticPlanning.tsp.benchmarking.instances.Instance;
import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;
import org.logisticPlanning.tsp.solving.TSPAlgorithm;
import org.logisticPlanning.tsp.solving.TSPAlgorithmRunner;
import org.logisticPlanning.tsp.solving.operators.permutation.creation.PermutationCreateCanonical;
import org.logisticPlanning.utils.math.random.Randomizer;

/**
 * Perform the random sampling, i.e., randomly generate permutations and
 * remember the best one found.
 */
public final class RandomPermutationSampling extends TSPAlgorithm {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** instantiate */
  public RandomPermutationSampling() {
    super("Uniformly Random Permutation Sampling"); //$NON-NLS-1$
  }

  /**
   * Perform the random sampling algorithm
   *
   * @param args
   *          the command line arguments
   */
  public final static void main(final String[] args) {
    TSPAlgorithmRunner.benchmark(Instance.ALL_INSTANCES,
        RandomPermutationSampling.class,//
        args);
  }

  /** {@inheritDoc} */
  @Override
  public void solve(final ObjectiveFunction f) {
    final int[] sol;
    final Randomizer r;

    sol = PermutationCreateCanonical.canonical(f.n());
    r = f.getRandom();
    while (!(f.shouldTerminate())) {
      // PermutationCreateUniform.randomize(sol, r);
      r.shuffle(sol, 0, sol.length);
      f.evaluate(sol);
    }
  }

  /** {@inheritDoc} */
  @Override
  public final RandomPermutationSampling clone() {
    return this;
  }

}
