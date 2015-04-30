package org.logisticPlanning.tsp.solving.algorithms.metaheuristics.developmental.devUpdatingEA.indexes;

import java.util.Random;

/**
 * an iterator that schematically iterates over different indices
 */
public class SchematicIndexIterator extends IndexIterator {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the internal variable */
  private int m_i;
  /** the internal variable */
  private int m_j;
  /** the internal variable */
  private int m_z;
  /** the internal variable */
  private int m_a;
  /** the internal variable */
  private int m_b;

  /** create */
  public SchematicIndexIterator() {
    super("schematic"); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  public final void next(final int[] indexes, final int n, final Random r) {
    if ((--this.m_j) <= 0) {
      if ((--this.m_i) <= 0) {
        this.m_i = (n - 1);
      }
      this.m_j = (n / this.m_i);
      if ((n % this.m_i) != 0) {
        this.m_j++;
      }
      this.m_b = ((this.m_z++) % n);
    }
    indexes[0] = this.m_a = ((this.m_b + 1) % n);
    indexes[1] = this.m_b = ((this.m_a + this.m_i) % n);
  }

  /** {@inheritDoc} */
  @Override
  public final void start(final int n, final Random r) {
    this.m_i = n;
    this.m_j = 0;
    this.m_z = r.nextInt(n);
  }
}
