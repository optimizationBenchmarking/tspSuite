package org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.paco.update;

import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.paco.PACOIndividual;
import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.paco.PheromoneMatrix;
import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.paco.PopulationUpdateStrategy;

/**
 * The &quot;age&quot; strategy for updating the population in the pACO.
 */
public final class Age extends PopulationUpdateStrategy {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the globally shared instance */
  public static final Age INSTANCE = new Age();

  /** instantiate */
  public Age() {
    super("age"); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  public final Age clone() {
    return Age.INSTANCE;
  }

  /** {@inheritDoc} */
  @Override
  public final void update(final PACOIndividual[] pop,
      final PACOIndividual genBest, final PheromoneMatrix matrix) {
    PACOIndividual replace;
    boolean mustDelete;

    replace = pop[0];
    mustDelete = true;
    for (final PACOIndividual x : pop) {
      if (x.isEmpty()) {
        mustDelete = false;
        replace = x;
        break;
      }

      if (x.m_birthday < replace.m_birthday) {
        replace = x;
      }
    }

    if (mustDelete) {
      matrix.delete(replace.solution);
    }
    matrix.add(genBest.solution);
    replace.assign(genBest);
  }
}
