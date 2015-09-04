package org.logisticPlanning.tsp.solving.algorithms.metaheuristics.general.ea.populationResize;

import java.util.Random;

/**
 * A population resizing strategy which randomly changes the population
 * size: it may increase it up to a certain factor or shrink it down to the
 * inverse factor (or anything in between), randomly.
 */
public class RandomResize extends _ByFactor {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the constructor */
  public RandomResize() {
    super("random"); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  public double getResizeFactor(final boolean hasImproved,
      final Random rand) {
    double val;

    val = (1d - (rand.nextDouble() * (1d - this.m_factor)));
    return (rand.nextBoolean() ? val : (1d / val));
  }
}