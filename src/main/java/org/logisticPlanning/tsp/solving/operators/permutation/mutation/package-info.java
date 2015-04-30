/**
 * <p>
 * Some {@link org.logisticPlanning.tsp.solving.operators.UnaryOperator
 * mutation operators} for permutations that perform, e.g., a local search
 * to refine an input solutions or are otherwise complex.
 * </p>
 * <ol>
 * <li>
 * {@link org.logisticPlanning.tsp.solving.operators.permutation.mutation.PermutationMultiNeighborhoodMutation
 * PermutationMultiNeighborhoodMutation} is a mutation operator that
 * performs a local search based on the
 * {@link org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.multiNeighborhoodSearch.MultiNeighborhoodSearch
 * Multi-Neighborhood Search} algorithm.</li>
 * <li>
 * {@link org.logisticPlanning.tsp.solving.operators.permutation.mutation.PermutationVNSMutation
 * PermutationMultiNeighborhoodMutation} is a mutation operator that
 * performs a local search based on the
 * {@link org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.vns.PermutationVNS
 * Variable Neighborhood Search} algorithm.</li>
 * <li>
 * {@link org.logisticPlanning.tsp.solving.operators.permutation.mutation.PermutationRNSMutation
 * PermutationMultiNeighborhoodMutation} is a mutation operator that
 * performs a local search based on the
 * {@link org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.vns.PermutationRNS
 * Random Neighborhood Search} algorithm.</li>
 * </ol>
 * <p>
 * The package
 * {@link org.logisticPlanning.tsp.solving.operators.permutation.update.PermutationUpdateOperator}
 * provides some other neighborhoods (search operators) that have the
 * property that, given an input solution and its corresponding tour length
 * {@code l}, they can generate an output solution whose objective value
 * can be computed in {@code O(1)} depending {@code l}.
 * </p>
 */
package org.logisticPlanning.tsp.solving.operators.permutation.mutation;

