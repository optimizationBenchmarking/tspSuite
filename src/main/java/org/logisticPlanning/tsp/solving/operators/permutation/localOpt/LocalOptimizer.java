package org.logisticPlanning.tsp.solving.operators.permutation.localOpt;

import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;
import org.logisticPlanning.tsp.solving.TSPModule;

/**
 * An operator which can locally optimize a sub-sequence of a TSP.
 */
public abstract class LocalOptimizer extends TSPModule {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * The local optimization operator
   *
   * @param length
   *          the length the operator can process
   */
  LocalOptimizer(final int length) {
    super("localOpt" + length); //$NON-NLS-1$
  }

  /**
   * Apply the local optimizer to a given path. If the sequence starting at
   * index {@code start} is already optimal with respect to this operator,
   * {@code 0} will be returned. Otherwise, the path will be modified and
   * the length change (always negative) will be returned.
   *
   * @param path
   *          the path
   * @param beforeStart
   *          the index right before the start of the sequence, i.e., the
   *          node which will remain unchanged
   * @param dist
   *          the distance computer
   * @return the amount the length of the path has changed, or {@code 0L} f
   *         the path was not modified
   */
  public abstract long apply(final int[] path, final int beforeStart,
      final ObjectiveFunction dist);

  /** {@inheritDoc} */
  @Override
  public LocalOptimizer clone() {
    return ((LocalOptimizer) (super.clone()));
  }

  /**
   * Get the length of the sub-path which can be optimized
   *
   * @return the length of the sub-path which can be optimized
   */
  public abstract int getSubPathLength();

}
