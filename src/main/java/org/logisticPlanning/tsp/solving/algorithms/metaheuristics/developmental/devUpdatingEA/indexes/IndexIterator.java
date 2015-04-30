package org.logisticPlanning.tsp.solving.algorithms.metaheuristics.developmental.devUpdatingEA.indexes;

import java.util.Random;

import org.logisticPlanning.utils.config.Configurable;

/**
 * The abstract index iterator base class. Index iterators provide a way to
 * iterate over pairs {@code (a,b)} of indices into a permutation. These
 * indices could be random or generated according to some policy. An index
 * iterator guarantees that {@code a!=b} for any call to
 * {@link #next(int[], int, Random)}.
 */
public abstract class IndexIterator extends Configurable {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * create
   * 
   * @param name
   *          the name/type of the iterator
   */
  protected IndexIterator(final String name) {
    super(name);
  }

  /**
   * start
   * 
   * @param n
   *          the problem dimension
   * @param r
   *          a randomizer
   */
  public void start(final int n, final Random r) {//
  }

  /**
   * get the next index pair
   * 
   * @param indexes
   *          an array of at least length to hold a pair of indices
   * @param n
   *          the dimension
   * @param r
   *          a randomizer
   */
  public void next(final int[] indexes, final int n, final Random r) {
    //
  }
}
