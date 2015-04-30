package org.logisticPlanning.tsp.evaluation.data.properties.litComp;

/**
 * a comparison set
 */
public final class LiteratureComparisonPointSet extends
    LiteratureComparisonSet<LiteratureComparisonPoint> implements
    Comparable<LiteratureComparisonPointSet> {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the owning literature point set */
  private final LiteratureResultPointSet m_owner;

  /**
   * create!
   * 
   * @param owner
   *          the owning literature result point set
   * @param points
   *          the points
   */
  LiteratureComparisonPointSet(final LiteratureResultPointSet owner,
      final LiteratureComparisonPoint[] points) {
    super(points);
    this.m_owner = owner;
  }

  /** {@inheritDoc} */
  @Override
  final void getComparisonData(final LiteratureComparisonPoint[] array,
      final int[] count) {
    for (final LiteratureComparisonPoint p : array) {
      count[p.m_cmp.ordinal()]++;
    }
  }

  /**
   * Get the owning literature result point set
   * 
   * @return the owning literature result point set
   */
  public final LiteratureResultPointSet getLiteratureResultPointSet() {
    return this.m_owner;
  }

  /** {@inheritDoc} */
  @Override
  public final int compareTo(final LiteratureComparisonPointSet o) {
    int i;

    if (o == this) {
      return 0;
    }
    if (o == null) {
      return (-1);
    }

    i = this.m_owner.compareTo(o.m_owner);
    if (i != 0) {
      return i;
    }

    return this.m_cmp.compareTo(o.m_cmp);
  }
}
