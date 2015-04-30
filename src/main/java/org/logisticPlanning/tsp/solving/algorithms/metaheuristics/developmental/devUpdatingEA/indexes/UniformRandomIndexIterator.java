package org.logisticPlanning.tsp.solving.algorithms.metaheuristics.developmental.devUpdatingEA.indexes;

import java.util.Random;

/** an index iterator iterating that uniformly samples indexes */
public final class UniformRandomIndexIterator extends IndexIterator {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** create */
  public UniformRandomIndexIterator() {
    super("uniform_sampling"); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  public final void next(final int[] indexes, final int n, final Random r) {
    indexes[0] = r.nextInt(n);
    do {
      indexes[1] = r.nextInt(n);
    } while (indexes[0] == indexes[1]);
  }

  /** {@inheritDoc} */
  @Override
  public final UniformRandomIndexIterator clone() {
    return this;
  }

}
