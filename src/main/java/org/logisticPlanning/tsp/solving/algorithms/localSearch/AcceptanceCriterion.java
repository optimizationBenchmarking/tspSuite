package org.logisticPlanning.tsp.solving.algorithms.localSearch;

import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;
import org.logisticPlanning.tsp.solving.Individual;
import org.logisticPlanning.tsp.solving.TSPModule;

/**
 * Instances of this class decide whether a current solution should be
 * accepted or not.
 * 
 * @since 0.9.8
 */
public abstract class AcceptanceCriterion extends TSPModule {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * Create the solution acceptance criterion
   * 
   * @param name
   *          the name of the criterion
   */
  protected AcceptanceCriterion(final String name) {
    super(name);
  }

  /**
   * Should we accept a given solution?
   * 
   * @param solution
   *          the solution individual record
   * @param f
   *          the objective function
   * @return {@code true} if the solution should be accepted, {@code false}
   *         otherwise
   */
  public abstract boolean shouldAccept(final Individual<?> solution,
      final ObjectiveFunction f);
}
