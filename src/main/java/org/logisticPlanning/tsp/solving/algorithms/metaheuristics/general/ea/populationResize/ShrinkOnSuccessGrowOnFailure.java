package org.logisticPlanning.tsp.solving.algorithms.metaheuristics.general.ea.populationResize;

import java.util.Random;

/**
 * A population resizing strategy which shrinks the population size on
 * success and grows it on failure
 */
public class ShrinkOnSuccessGrowOnFailure extends _ByFactor {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the constructor */
  public ShrinkOnSuccessGrowOnFailure() {
    super("shrinkOnSuccessGrowOnFailure"); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  public double getResizeFactor(final boolean hasImproved,
      final Random rand) {
    return (hasImproved ? this.m_factor : (1d / this.m_factor));
  }
}