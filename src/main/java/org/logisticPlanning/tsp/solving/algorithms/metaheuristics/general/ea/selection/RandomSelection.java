package org.logisticPlanning.tsp.solving.algorithms.metaheuristics.general.ea.selection;

import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;
import org.logisticPlanning.tsp.solving.Individual;
import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.general.ea.SelectionAlgorithm;
import org.logisticPlanning.utils.math.random.Randomizer;

/**
 * <p>
 * This selection algorithm chooses the individuals to be copied into the
 * mating pool entirely randomly without any seach bias. This method may be
 * good for comparison experiments, to see whether a method works correctly
 * or not.
 */
public final class RandomSelection extends SelectionAlgorithm {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the globally shared instance */
  public static final RandomSelection INSTANCE = new RandomSelection();

  /**
   * the random selection algorithm's constructor: private, use
   * {@link #INSTANCE}
   */
  private RandomSelection() {
    super("randomSelection"); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  public final void select(final Individual<?>[] pop,
      final Individual<?>[] mate, final ObjectiveFunction f) {
    final Randomizer r;
    Individual<?> ind;
    int i, j, s;

    r = f.getRandom();
    s = pop.length;

    for (i = mate.length; (--i) >= 0;) {
      j = r.nextInt(s);
      mate[i] = ind = pop[j];
      pop[j] = pop[--s];
      pop[s] = ind;
    }
  }

  /** {@inheritDoc} */
  @Override
  public final RandomSelection clone() {
    return RandomSelection.INSTANCE;
  }

  //

  // default, automatic serialization replacement and resolve routines for
  // singletons
  //
  /**
   * Write replace: the instance this method is invoked on will be replaced
   * with the singleton instance {@link RandomSelection#INSTANCE
   * RandomSelection.INSTANCE} for serialization, i.e., when the instance
   * is written with {@link java.io.ObjectOutputStream#writeObject(Object)}
   * .
   * 
   * @return the replacement instance (always
   *         {@link RandomSelection#INSTANCE RandomSelection.INSTANCE})
   */
  private final Object writeReplace() {
    return RandomSelection.INSTANCE;
  }

  /**
   * Read resolve: The instance this method is invoked on will be replaced
   * with the singleton instance {@link RandomSelection#INSTANCE
   * RandomSelection.INSTANCE} after serialization, i.e., when the instance
   * is read with {@link java.io.ObjectInputStream#readObject()}.
   * 
   * @return the replacement instance (always
   *         {@link RandomSelection#INSTANCE RandomSelection.INSTANCE})
   */
  private final Object readResolve() {
    return RandomSelection.INSTANCE;
  }

}
