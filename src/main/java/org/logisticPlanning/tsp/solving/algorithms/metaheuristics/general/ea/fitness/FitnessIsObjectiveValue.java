package org.logisticPlanning.tsp.solving.algorithms.metaheuristics.general.ea.fitness;

import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;
import org.logisticPlanning.tsp.solving.Individual;
import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.general.ea.FitnessAssignmentProcess;

/**
 * A fitness assignment process that uses the objective values directly as
 * fitness values. This is the default method used in
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.general.ea.EA
 * Evolutionary Algorithms}.
 */
public final class FitnessIsObjectiveValue extends
    FitnessAssignmentProcess {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the globally shared instance */
  public static final FitnessIsObjectiveValue INSTANCE = new FitnessIsObjectiveValue();

  /** the constructor */
  private FitnessIsObjectiveValue() {
    super("fitnessIsObjectiveValue"); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  public final void assignFitness(final Individual<?>[] pop,
      final ObjectiveFunction f) {
    for (final Individual<?> i : pop) {
      i.f = i.tourLength;
    }
  }

  /** {@inheritDoc} */
  @Override
  public final FitnessIsObjectiveValue clone() {
    return FitnessIsObjectiveValue.INSTANCE;
  }
}
