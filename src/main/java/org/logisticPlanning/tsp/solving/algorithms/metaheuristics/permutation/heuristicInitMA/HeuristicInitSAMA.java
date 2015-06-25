package org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA;

import org.logisticPlanning.tsp.benchmarking.instances.Instance;
import org.logisticPlanning.tsp.solving.TSPAlgorithmRunner;
import org.logisticPlanning.tsp.solving.operators.BinaryOperator;
import org.logisticPlanning.tsp.solving.operators.UnaryOperator;
import org.logisticPlanning.tsp.solving.operators.permutation.mutation.PermutationSAMutation;
import org.logisticPlanning.tsp.solving.operators.permutation.recombination.PermutationEdgeCrossover;

/**
 * A base class for MAs with Simulated Annealing search.
 *
 * @author Jiahui Liu, jl4161@columbia.edu
 */
public class HeuristicInitSAMA extends PermutationHeuristicInitMA {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * create the MA
   *
   * @param name
   *          a string to be added to the base name
   */
  public HeuristicInitSAMA(final String name) {
    super(//
        "Permutation-based heuristic-initialized SA Memetic Algorithm " + //$NON-NLS-1$
            name);
  }

  /** {@inheritDoc} */
  @Override
  protected final UnaryOperator<int[]> createUnary() {
    return new PermutationSAMutation();
  }

  /** {@inheritDoc} */
  @Override
  protected final BinaryOperator<int[]> createBinary() {
    return new PermutationEdgeCrossover();
  }

  /**
   * Perform the heuristic init sa edge MA
   *
   * @param args
   *          the command line arguments
   */
  public final static void main(final String[] args) {
    TSPAlgorithmRunner.benchmark(Instance.SYMMETRIC_INSTANCES,
        HeuristicInitSAMA.class,//
        args);
  }
}