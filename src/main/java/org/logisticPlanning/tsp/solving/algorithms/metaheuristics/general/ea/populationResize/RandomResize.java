package org.logisticPlanning.tsp.solving.algorithms.metaheuristics.general.ea.populationResize;

import java.util.Random;

import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.general.ea.PopulationResizeStrategy;

/**
 * A population resizing strategy which randomly changes the population
 * size: it may increase it up to factor 2 or shrink it down to factor 0.5
 * (or anything in between), randomly.
 */
public class RandomResize extends PopulationResizeStrategy {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the globally shared instance */
  public static final RandomResize INSTANCE = new RandomResize();

  /** the constructor */
  private RandomResize() {
    super("random"); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  public double getResizeFactor(final boolean hasImproved,
      final Random rand) {
    double val;

    val = (0.5d + (0.5d * rand.nextDouble()));
    return (rand.nextBoolean() ? val : (1d / val));
  }
}