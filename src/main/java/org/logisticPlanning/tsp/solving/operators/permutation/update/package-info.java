/**
 * <p>
 * {@link org.logisticPlanning.tsp.solving.operators.permutation.update.PermutationUpdateOperator
 * Permutation update} operators are
 * {@link org.logisticPlanning.tsp.solving.operators.UnaryOperator unary
 * operators} able to modify a permutation in a way that does not require
 * to completely re-evaluate the objective function. This makes them
 * especially fast to apply. In particular, they provide neighborhoods
 * (search operators) that have the property that, given an input solution
 * and its corresponding tour length {@code l}, they can generate an output
 * solution whose objective value can be computed in {@code O(1)} depending
 * {@code l}. These operators also usually define neighborhoods of size
 * <code>{@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction#n() n}²</code>
 * . In particular, we provide the following operators:
 * </p>
 * <ol>
 * <li>
 * {@link org.logisticPlanning.tsp.solving.operators.permutation.update.PermutationUpdate_Swap
 * Swap}, well, swaps two nodes in a permutation.</li>
 * <li>
 * {@link org.logisticPlanning.tsp.solving.operators.permutation.update.PermutationUpdate_Rotate_Left
 * Rotate left} takes a sub-sequence in a permutation and rotates it one
 * step to the left.</li>
 * <li>
 * {@link org.logisticPlanning.tsp.solving.operators.permutation.update.PermutationUpdate_Rotate_Right
 * Rotate right} takes a sub-sequence in a permutation and rotates it one
 * step to the right.</li>
 * <li>
 * {@link org.logisticPlanning.tsp.solving.operators.permutation.update.PermutationUpdate_Reverse
 * Reverse} takes a sub-sequence in a permutation and reverses the sequence
 * of the nodes in it.</li>
 * </ol>
 * <p>
 * More complex unary search operations, in particular such that perform
 * local searches, are given in package
 * {@link org.logisticPlanning.tsp.solving.operators.permutation.mutation}.
 * </p>
 */
package org.logisticPlanning.tsp.solving.operators.permutation.update;

