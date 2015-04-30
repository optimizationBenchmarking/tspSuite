/**
 * {@link org.logisticPlanning.tsp.solving.operators.NullaryOperator
 * Nullary operators} for the path/permutation representation that can
 * create completely new permutations without deriving them from existing
 * ones. Currently, this package provides two operators:
 * <ol>
 * <li>An
 * {@link org.logisticPlanning.tsp.solving.operators.permutation.creation.PermutationCreateCanonical
 * operator} that always returns the canonical permutation
 * <code>(1, 2, 3, &hellip;, {@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction#n() n})</code>
 * .</li>
 * <li>An
 * {@link org.logisticPlanning.tsp.solving.operators.permutation.creation.PermutationCreateUniform
 * operator} that uniformly randomly samples permutations, i.e., that
 * returns each possible permutation of length
 * <code>{@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction#n() n}</code>
 * with the same probability.</li>
 * </ol>
 */
package org.logisticPlanning.tsp.solving.operators.permutation.creation;

