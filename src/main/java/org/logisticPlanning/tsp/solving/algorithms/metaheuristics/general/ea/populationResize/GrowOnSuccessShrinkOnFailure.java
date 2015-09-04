package org.logisticPlanning.tsp.solving.algorithms.metaheuristics.general.ea.populationResize;

import java.util.Random;

/**
 * A population resizing strategy which grows the population size on
 * success and shrinks it on failure
 */
public class GrowOnSuccessShrinkOnFailure extends _ByFactor {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the constructor */
  public GrowOnSuccessShrinkOnFailure() {
    super("growOnSuccessShrinkOnFailure"); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  public double getResizeFactor(final boolean hasImproved,
      final Random rand) {
    return (hasImproved ? (1d / this.m_factor) : this.m_factor);
  }
}