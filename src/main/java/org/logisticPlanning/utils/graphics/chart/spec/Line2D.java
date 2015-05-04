package org.logisticPlanning.utils.graphics.chart.spec;

import org.logisticPlanning.utils.NamedObject;
import org.logisticPlanning.utils.math.data.collection.IDataCollection;
import org.logisticPlanning.utils.math.data.point.Point;
import org.logisticPlanning.utils.text.TextUtils;

/**
 * A line
 */
public final class Line2D extends NamedObject {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the point dimension must be two */
  private static final String DIM_ERROR = "Dimension must be 2, but is "; //$NON-NLS-1$

  /** the left end of the line */
  private final Point m_leftEnd;

  /** the data collection */
  private final IDataCollection m_data;

  /** the right end of the line */
  private final Point m_rightEnd;

  /** the line mode */
  private final ELineMode m_mode;

  /**
   * create
   *
   * @param name
   *          the line's name
   * @param leftEnd
   *          the left end of the line
   * @param data
   *          the data
   * @param rightEnd
   *          the right end of the line
   * @param mode
   *          the line mode
   */
  public Line2D(final String name, final Point leftEnd,
      final IDataCollection data, final Point rightEnd,
      final ELineMode mode) {
    super(TextUtils.prepare(name));

    int d;

    if (data != null) {
      d = data.dimension();
      if (d != 2) {
        throw new IllegalArgumentException(Line2D.DIM_ERROR + d);
      }
    }
    this.m_data = data;

    this.m_mode = ((mode != null) ? mode : ELineMode.DIRECT);

    if (leftEnd != null) {
      d = leftEnd.dimension();
      if (d != 2) {
        throw new IllegalArgumentException(Line2D.DIM_ERROR + d);
      }
    }
    this.m_leftEnd = leftEnd;

    if (rightEnd != null) {
      d = rightEnd.dimension();
      if (d != 2) {
        throw new IllegalArgumentException(Line2D.DIM_ERROR + d);
      }
    }
    this.m_rightEnd = rightEnd;
  }

  /**
   * Get the data.
   *
   * @return the data
   */
  public final IDataCollection getData() {
    return this.m_data;
  }

  /**
   * Get the line mode
   *
   * @return the line mode
   */
  public final ELineMode getLineMode() {
    return this.m_mode;
  }

  /**
   * Get the left end
   *
   * @return the left end
   */
  public final Point getLeftEnd() {
    return this.m_leftEnd;
  }

  /**
   * Get the right end
   *
   * @return the right end
   */
  public final Point getRightEnd() {
    return this.m_rightEnd;
  }

  /** {@inheritDoc} */
  @Override
  public final Line2D clone() {
    return this;
  }
}
