package org.logisticPlanning.tsp.solving.algorithms.metaheuristics.general.ea.populationResize;

import java.util.Random;

/**
 * A population resizing strategy which steadily increases the population
 * size.
 */
public class AlwaysGrow extends _ByFactor {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the constructor */
  public AlwaysGrow() {
    super("grow"); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  public double getResizeFactor(final boolean hasImproved,
      final Random rand) {
    return (1d / this.m_factor);
  }
}