package org.logisticPlanning.utils.graphics.chart.spec.range;

/**
 * The abstract base class for axis ranges.
 */
class _AxisRange2DDef {

  /** the minimum x-end */
  final AxisEnd m_minX;

  /** the maximum x-end */
  final AxisEnd m_maxX;

  /** the minimum y-end */
  final AxisEnd m_minY;

  /** the maximum y-end */
  final AxisEnd m_maxY;

  /** expand the x-range automatically if it is collapsed */
  final boolean m_autoExpandX;

  /** expand the y-range automatically if it is collapsed */
  final boolean m_autoExpandY;

  /**
   * create
   * 
   * @param minX
   *          the minimum-x end
   * @param maxX
   *          the maximum-x end
   * @param autoExpandX
   *          automatically expand x-range if it is collapsed to zero range
   * @param minY
   *          the minimum-y end
   * @param maxY
   *          the maximum-y end
   * @param autoExpandY
   *          automatically expand Y-range if it is collapsed to zero range
   */
  _AxisRange2DDef(final AxisEnd minX, final AxisEnd maxX,
      final boolean autoExpandX, final AxisEnd minY, final AxisEnd maxY,
      final boolean autoExpandY) {
    super();

    if ((minX == null) || (maxX == null) || (minY == null)
        || (maxY == null)) {
      throw new IllegalArgumentException(//
          "Axis ends must not be null."); //$NON-NLS-1$
    }

    this.m_minX = minX;
    this.m_maxX = maxX;
    this.m_autoExpandX = autoExpandX;
    this.m_minY = minY;
    this.m_maxY = maxY;
    this.m_autoExpandY = autoExpandY;
  }

}
