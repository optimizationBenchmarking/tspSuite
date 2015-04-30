package org.logisticPlanning.tsp.solving.algorithms.metaheuristics.developmental.devUpdatingEA.indexes;

import java.io.PrintStream;
import java.util.Random;

import org.logisticPlanning.utils.config.Configurable;

/**
 * an index iterator creating normally distributed indices
 */
public class NormalRandomIndexIterator extends IndexIterator {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the standard deviation */
  private double m_stddev;

  /** create */
  public NormalRandomIndexIterator() {
    super("normal_sampling"); //$NON-NLS-1$
    this.m_stddev = Double.NaN;
  }

  /** {@inheritDoc} */
  @Override
  public final void start(final int n, final Random r) {
    super.start(n, r);
    this.m_stddev = Math.max(1d, Math.log(n));
  }

  /** {@inheritDoc} */
  @Override
  public final void next(final int[] indexes, final int n, final Random r) {

    indexes[0] = r.nextInt(n);
    do {
      indexes[1] = (((((int) (indexes[0] + (r.nextGaussian() * this.m_stddev))) % n) + n) % n);
    } while (indexes[0] == indexes[1]);
  }

  /** {@inheritDoc} */
  @Override
  public void printConfiguration(final PrintStream ps) {
    final double d;

    super.printConfiguration(ps);

    d = this.m_stddev;
    if (!(Double.isNaN(d))) {
      Configurable.printKey("indexIteratorStdDev", ps); //$NON-NLS-1$
      ps.println(d);
    }

  }
}
