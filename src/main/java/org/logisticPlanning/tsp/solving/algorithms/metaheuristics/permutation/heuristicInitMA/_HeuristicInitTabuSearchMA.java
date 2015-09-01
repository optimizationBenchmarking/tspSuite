// TSP Suite, version 0.9.8
// Copyright (c) 2012-2014 Thomas Weise, http://www.it-weise.de/
// License : The GNU Lesser General Public License, version 3.0
// Project : TSP Suite, Version: 0.9.8, Target Platform: Java 1.7
// File :
// main.java.org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA._HeuristicInitTabuSearchMA.java
// Website : http://www.logisticPlanning.org/tsp
// Packaged: 2014-04-26 18:05:48 GMT+0800
//
//
package org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA;

import org.logisticPlanning.tsp.solving.operators.UnaryOperator;
import org.logisticPlanning.tsp.solving.operators.permutation.mutation.PermutationTabuSearchMutation;

/**
 *
 <p>
 * This class is an internal class. You should never load or instantiate
 * it. Use the classes derived from it.
 * </p>
 */
abstract class _HeuristicInitTabuSearchMA extends
    PermutationHeuristicInitMA {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * create the ea
   *
   * @param name
   *          a string to be added to the base name
   */
  public _HeuristicInitTabuSearchMA(final String name) {
    super(//
        "Permutation-based heuristic-initialized Tabu Search Memetic Algorithm "//$NON-NLS-1$
            + name);
  }

  /** {@inheritDoc} */
  @Override
  protected final UnaryOperator<int[]> createUnary() {
    return new PermutationTabuSearchMutation();
  }
}