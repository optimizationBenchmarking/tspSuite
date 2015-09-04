package org.logisticPlanning.tsp.solving.algorithms.metaheuristics.general.ea.populationResize;

import java.util.Random;

import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.general.ea.PopulationResizeStrategy;

/**
 * A population resizing strategy which keeps the same population size.
 */
public class StaticPopulationSize extends PopulationResizeStrategy {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the globally shared instance */
  public static final StaticPopulationSize INSTANCE = new StaticPopulationSize();

  /** the constructor */
  private StaticPopulationSize() {
    super("static"); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  public double getResizeFactor(final boolean hasImproved,
      final Random rand) {
    return 1d;
  }
}