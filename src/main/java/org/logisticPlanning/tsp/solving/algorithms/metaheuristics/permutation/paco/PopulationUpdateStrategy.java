package org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.paco;

import org.logisticPlanning.tsp.solving.TSPModule;

/**
 * 
 The population update strategy to be used in the Population-based ACO.
 */
public abstract class PopulationUpdateStrategy extends TSPModule {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * instantiate
   * 
   * @param name
   *          the name
   */
  protected PopulationUpdateStrategy(final String name) {
    super(name);
  }

  /**
   * update the population and pheromone matrix
   * 
   * @param pop
   *          the population
   * @param genBest
   *          the best solution of a generation
   * @param matrix
   *          the pheromone matrix
   */
  public abstract void update(final PACOIndividual[] pop,
      final PACOIndividual genBest, final PheromoneMatrix matrix);
}
