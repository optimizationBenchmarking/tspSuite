package org.logisticPlanning.tsp.evaluation.data.properties.scale;

import java.util.Arrays;

import org.logisticPlanning.utils.collections.lists.ArraySetView;

/**
 * The base class for scaled sets.
 *
 * @param <T>
 *          the type parameter
 */
abstract class _ScaleSetBase<T> extends ArraySetView<T> implements
Comparable<_ScaleSetBase<?>> {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;
  /** the lower scale bound */
  final int m_lowerScaleBound;
  /** the upper scale bound */
  final int m_upperScaleBound;
  /** the lower scale bound */
  private final int m_lowestScale;
  /** the highest scale bound */
  private final int m_highestScale;

  /**
   * Create a scale run set
   *
   * @param lowerScaleBound
   *          the lower scale bound
   * @param upperScaleBound
   *          the upper scale bound
   * @param lowestScale
   *          the lowest actual scale
   * @param highestScale
   *          the highest actual scale
   * @param set
   *          the data set
   */
  _ScaleSetBase(final int lowerScaleBound, final int upperScaleBound,
      final int lowestScale, final int highestScale, final T[] set) {
    super(set);
    Arrays.sort(this.m_data);

    this.m_lowerScaleBound = lowerScaleBound;
    this.m_upperScaleBound = upperScaleBound;
    this.m_lowestScale = lowestScale;
    this.m_highestScale = highestScale;
  }

  /**
   * Get the lower bound of scale
   *
   * @return the lower bound of scale
   */
  public final int getLowerScaleBound() {
    return this.m_lowerScaleBound;
  }

  /**
   * Get the upper bound of scale
   *
   * @return the upper bound of scale
   */
  public final int getUpperScaleBound() {
    return this.m_upperScaleBound;
  }

  /**
   * Get the lowest scale actually in the set
   *
   * @return the lowest scale actually in the set
   */
  public final int getLowestScale() {
    return this.m_lowestScale;
  }

  /**
   * Get the highest scale actually in the set
   *
   * @return the highest scale actually in the set
   */
  public final int getHighestScale() {
    return this.m_highestScale;
  }

  /** {@inheritDoc} */
  @Override
  public int compareTo(final _ScaleSetBase<?> o) {
    int i;

    if (o == null) {
      return (-1);
    }
    if (o == this) {
      return 0;
    }

    i = Integer.compare(this.m_upperScaleBound, o.m_upperScaleBound);
    if (i != 0) {
      return i;
    }

    i = Integer.compare(this.m_highestScale, o.m_highestScale);
    if (i != 0) {
      return i;
    }

    i = Integer.compare(this.m_lowestScale, o.m_lowestScale);
    if (i != 0) {
      return i;
    }

    i = Integer.compare(this.m_lowerScaleBound, o.m_lowerScaleBound);
    if (i != 0) {
      return i;
    }

    return 0;
  }

  /** {@inheritDoc} */
  @Override
  public String toString() {
    return (((((this.m_data.length + " elements in range [") + //$NON-NLS-1$
        this.m_lowerScaleBound) + ", ") + //$NON-NLS-1$
        this.m_upperScaleBound) + ']');
  }
}
