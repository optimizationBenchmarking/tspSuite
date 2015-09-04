package org.logisticPlanning.tsp.solving.algorithms.metaheuristics.general.ea;

import java.util.Random;

import org.logisticPlanning.tsp.solving.TSPModule;

/**
 * A population resizing strategy allows us to change the population size
 * of an EA.
 */
public class PopulationResizeStrategy extends TSPModule {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * the population resize strategy algorithm constructor
   *
   * @param name
   *          the algorithm's name
   */
  protected PopulationResizeStrategy(final String name) {
    super(name);
  }

  /**
   * Get the resize factor with which the population sizes mu and lambda
   * are to be multiplied.
   * 
   * @param hasImproved
   *          was there an improvement in the last generation?
   * @param rand
   *          a random number generator
   * @return the resize factor
   */
  public double getResizeFactor(final boolean hasImproved,
      final Random rand) {
    return 1d;
  }
}