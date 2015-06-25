package org.logisticPlanning.tsp.evaluation.data.properties.litComp;

import java.io.IOException;
import java.util.Arrays;

import org.logisticPlanning.utils.collections.lists.ArraySetView;
import org.logisticPlanning.utils.document.spec.Header;

/**
 * The results class
 */
public final class LiteratureResults extends
    ArraySetView<LiteratureResultPointSet> {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * Create
   *
   * @param pointSets
   *          the point sets.
   */
  public LiteratureResults(final LiteratureResultPointSet... pointSets) {
    super(pointSets);
    Arrays.sort(pointSets);
  }

  /**
   * Initialize the result point sets
   *
   * @param header
   *          the header
   * @throws IOException
   *           the i/o exception if i/o fails
   */
  final void _initialize(final Header header) throws IOException {
    for (final LiteratureResultPointSet p : this.m_data) {
      p.initialize(header);
    }
  }
}
