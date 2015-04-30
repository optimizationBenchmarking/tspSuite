package org.logisticPlanning.utils.math.statistics.aggregates;

import org.logisticPlanning.utils.collections.lists.DoubleList;
import org.logisticPlanning.utils.math.statistics.Quantile;

/**
 * the double list
 */
final class _DoubleList extends DoubleList {
  /** The serial version uid */
  private static final long serialVersionUID = 1L;

  /** the modification counter after the last sorting */
  private int m_sortMod;

  /** create */
  _DoubleList() {
    super(0);
    this.m_sortMod = (-1);// m_sortMod==modCount==-1: consider list as
    // unsorted
  }

  /** {@inheritDoc} */
  @Override
  public final void sort() {
    if ((this.m_sortMod != this.modCount) || (this.m_sortMod == (-1))) {
      super.sort();
      this.m_sortMod = this.modCount;
    }
  }

  /**
   * get the quantile
   * 
   * @param quantile
   *          the quantile
   * @return the quantile value
   */
  final double _quantile(final double quantile) {
    this.sort();
    return Quantile.getQuantile(this.m_data, 0, this.m_size, quantile);
  }
}
