package org.logisticPlanning.utils.graphics.chart.spec.range;

/**
 * The abstract base class for axis ranges.
 */
public class AxisRange2DDef extends _AxisRange2DDef {

  /**
   * create
   * 
   * @param minX
   *          the minimum-x end
   * @param maxX
   *          the maximum-x end
   * @param minY
   *          the minimum-y end
   * @param maxY
   *          the maximum-y end
   */
  public AxisRange2DDef(final AxisEnd minX, final AxisEnd maxX,
      final AxisEnd minY, final AxisEnd maxY) {
    this(minX, maxX, false, minY, maxY, false);
  }

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
  public AxisRange2DDef(final AxisEnd minX, final AxisEnd maxX,
      final boolean autoExpandX, final AxisEnd minY, final AxisEnd maxY,
      final boolean autoExpandY) {
    super(minX, maxX, autoExpandX, minY, maxY, autoExpandY);
  }

  /**
   * instantiate
   * 
   * @return the axis range object
   */
  public AxisRange2D instantiate() {
    return new AxisRange2D(this.m_minX.clone(), this.m_maxX.clone(),
        this.m_autoExpandX, this.m_minY.clone(), this.m_maxY.clone(),
        this.m_autoExpandY);
  }
}
