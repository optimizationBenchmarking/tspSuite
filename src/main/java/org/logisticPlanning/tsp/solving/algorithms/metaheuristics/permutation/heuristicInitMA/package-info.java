/**
 * <p>
 * Several Memetic Algorithms, which are initialized with different
 * heuristics and work on permutations by applying a crossover followed by
 * a local searcher.
 * </p>
 * <table border="1">
 * <tr>
 * <td/>
 * <th colspan="2">
 * {@link org.logisticPlanning.tsp.solving.operators.permutation.recombination.PermutationEdgeCrossover
 * Edge Recombination}</th>
 * <th colspan="2>
 * {@link org.logisticPlanning.tsp.solving.operators.permutation.recombination.PermutationSavingsCrossover
 * Savings Crossover}</th>
 * </tr>
 * <tr>
 * <td/>
 * <th>
 * {@link org.logisticPlanning.tsp.solving.operators.permutation.mutation.PermutationMultiNeighborhoodMutation
 * MNS}</th>
 * <th>
 * {@link org.logisticPlanning.tsp.solving.operators.permutation.mutation.PermutationRNSMutation
 * RNS}</th>
 * <th>
 * {@link org.logisticPlanning.tsp.solving.operators.permutation.mutation.PermutationVNSMutation
 * VNS}</th>
 * <th>
 * {@link org.logisticPlanning.tsp.solving.operators.permutation.mutation.PermutationMultiNeighborhoodMutation
 * MNS}</th>
 * <th>
 * {@link org.logisticPlanning.tsp.solving.operators.permutation.mutation.PermutationRNSMutation
 * RNS}</th>
 * <th>
 * {@link org.logisticPlanning.tsp.solving.operators.permutation.mutation.PermutationVNSMutation
 * VNS}</th>
 * </tr>
 * <tr>
 * <th>plain</th>
 * <td>
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA.HeuristicInitMultiNeighborhoodEdgeMA
 * X}</td>
 * <td>
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA.HeuristicInitRNSEdgeMA
 * X}</td>
 * <td>
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA.HeuristicInitVNSEdgeMA
 * X}</td>
 * <td>
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA.HeuristicInitMultiNeighborhoodSavingsMA
 * X}</td>
 * <td>
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA.HeuristicInitRNSSavingsMA
 * X}</td>
 * <td>
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA.HeuristicInitVNSSavingsMA
 * X}</td>
 * </tr>
 * <tr>
 * <th>with
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.general.ea.fitness.FFA
 * FFA}</th>
 * <td>
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA.HeuristicInitMultiNeighborhoodEdgeMAFFA
 * X}</td>
 * <td>
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA.HeuristicInitRNSEdgeMAFFA
 * X}</td>
 * <td>
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA.HeuristicInitVNSEdgeMAFFA
 * X}</td>
 * <td>
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA.HeuristicInitMultiNeighborhoodSavingsMAFFA
 * X}</td>
 * <td>
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA.HeuristicInitRNSSavingsMAFFA
 * X}</td>
 * <td>
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA.HeuristicInitVNSSavingsMAFFA
 * X}</td>
 * </tr>
 * <tr>
 * <th>with
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.general.ea.selection.RandomSelection
 * Random Selection}</th>
 * <td>
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA.HeuristicInitMultiNeighborhoodEdgeMARandomSelection
 * X}</td>
 * <td>
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA.HeuristicInitRNSEdgeMARandomSelection
 * X}</td>
 * <td>
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA.HeuristicInitVNSEdgeMARandomSelection
 * X}</td>
 * <td>
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA.HeuristicInitMultiNeighborhoodSavingsMARandomSelection
 * X}</td>
 * <td>
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA.HeuristicInitRNSSavingsMARandomSelection
 * X}</td>
 * <td>
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA.HeuristicInitVNSSavingsMARandomSelection
 * X}</td>
 * </tr>
 * <tr>
 * <th>with
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.general.ea.selection.FitnessUniformSelection
 * Fitness Uniform Selection}</th>
 * <td>
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA.HeuristicInitMultiNeighborhoodEdgeMAFUSS
 * X}</td>
 * <td>
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA.HeuristicInitRNSEdgeMAFUSS
 * X}</td>
 * <td>
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA.HeuristicInitVNSEdgeMAFUSS
 * X}</td>
 * <td>
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA.HeuristicInitMultiNeighborhoodSavingsMAFUSS
 * X}</td>
 * <td>
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA.HeuristicInitRNSSavingsMAFUSS
 * X}</td>
 * <td>
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA.HeuristicInitVNSSavingsMAFUSS
 * X}</td>
 * </tr>
 * </table>
 */
package org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA;

