package org.logisticPlanning.tsp.evaluation.data.properties.litComp;

/**
 * the results comparisons
 */
public final class LiteratureComparisonResult extends
LiteratureComparisonSet<LiteratureComparisonPointSet> {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * create!
   *
   * @param data
   *          the data
   */
  LiteratureComparisonResult(final LiteratureComparisonPointSet[] data) {
    super(data);
  }

  /** {@inheritDoc} */
  @Override
  final void getComparisonData(final LiteratureComparisonPointSet[] array,
      final int[] count) {
    for (final LiteratureComparisonPointSet s : array) {
      count[s.m_cmp.ordinal()]++;
    }
  }
}
