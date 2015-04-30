package org.logisticPlanning.utils.graphics.chart.spec;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.logisticPlanning.utils.graphics.chart.spec.range.AxisRange2D;
import org.logisticPlanning.utils.graphics.chart.spec.range.AxisRange2DDef;
import org.logisticPlanning.utils.math.data.iteration.BoundsReducingIterator2D;
import org.logisticPlanning.utils.math.data.iteration.DataCollectionIterator2D;
import org.logisticPlanning.utils.math.data.iteration.DistanceBasedReducingIterator2D;
import org.logisticPlanning.utils.math.data.iteration.StraightReducingIterator2D;
import org.logisticPlanning.utils.math.data.point.Point2D;
import org.logisticPlanning.utils.text.TextUtils;

/**
 * a line chart
 */
public abstract class LineChart2D extends AbstractChart {

  /** the axis range */
  private final AxisRange2D m_range;
  /** the x-axis label */
  private String m_labelX;
  /** the y-axis label */
  private String m_labelY;
  /** the array list with the lines */
  private final ArrayList<Line2D> m_lines;
  /** the array list with the background lines */
  private final ArrayList<Line2D> m_backgroundLines;
  /** the legend type */
  private ELegendType m_legend;

  /**
   * Create a new line chart
   * 
   * @param driver
   *          the line chart's driver
   * @param range
   *          the axis range
   */
  protected LineChart2D(final ChartDriver driver,
      final AxisRange2DDef range) {
    super(driver);
    this.m_range = range.instantiate();
    this.m_lines = new ArrayList<>();
    this.m_backgroundLines = new ArrayList<>();
  }

  /**
   * Add the given line
   * 
   * @param line
   *          the line
   */
  public final void addLine(final Line2D line) {
    if (line == null) {
      throw new IllegalArgumentException();
    }
    this.change();
    this.m_lines.add(line);
    this.m_range.registerLine2D(line);
  }

  /**
   * Add the given lines
   * 
   * @param lines
   *          the lines
   */
  public final void addLines(final Line2D[] lines) {
    if (lines == null) {
      throw new IllegalArgumentException();
    }
    for (final Line2D line : lines) {
      this.addLine(line);
    }
  }

  /**
   * Add the given lines
   * 
   * @param lines
   *          the lines
   */
  public final void addLines(final Iterable<Line2D> lines) {
    if (lines == null) {
      throw new IllegalArgumentException();
    }
    for (final Line2D line : lines) {
      this.addLine(line);
    }
  }

  /**
   * Add the given background line
   * 
   * @param line
   *          the line
   */
  public final void addBackgroundLine(final Line2D line) {
    if (line == null) {
      throw new IllegalArgumentException();
    }
    this.change();
    this.m_backgroundLines.add(line);
    this.m_range.registerLine2D(line);
  }

  /**
   * Add the given background lines
   * 
   * @param lines
   *          the lines
   */
  public final void addBackgroundLines(final Line2D[] lines) {
    if (lines == null) {
      throw new IllegalArgumentException();
    }
    for (final Line2D line : lines) {
      this.addBackgroundLine(line);
    }
  }

  /**
   * Add the given background lines
   * 
   * @param lines
   *          the lines
   */
  public final void addBackgroundLines(final Iterable<Line2D> lines) {
    if (lines == null) {
      throw new IllegalArgumentException();
    }
    for (final Line2D line : lines) {
      this.addBackgroundLine(line);
    }
  }

  /**
   * get a list with the lines
   * 
   * @return a list with the lines
   */
  protected final List<Line2D> getLines() {
    return this.m_lines;
  }

  /**
   * set the legend type
   * 
   * @param legend
   *          the legend type
   */
  public final void setLegendType(final ELegendType legend) {
    this.change();
    this.m_legend = ((legend != null) ? legend : ELegendType.PUT_LEGEND);
  }

  /**
   * Get the legend type
   * 
   * @return the legend type
   */
  public final ELegendType getLegendType() {
    return this.m_legend;
  }

  /**
   * get a list with the background lines
   * 
   * @return a list with the background lines
   */
  protected final List<Line2D> getBackgroundLines() {
    return this.m_backgroundLines;
  }

  /**
   * Get the axis range object
   * 
   * @return the axis range object
   */
  protected final AxisRange2D getAxisRange() {
    return this.m_range;
  }

  /** {@inheritDoc} */
  @Override
  protected void compile() {
    //
  }

  /**
   * set the title of the x-axis
   * 
   * @param title
   *          the axis title
   */
  public final void setAxisTitleX(final String title) {
    this.change();
    this.m_labelX = TextUtils.prepare(title);
  }

  /**
   * set the title of the y-axis
   * 
   * @param title
   *          the axis title
   */
  public final void setAxisTitleY(final String title) {
    this.change();
    this.m_labelY = TextUtils.prepare(title);
  }

  /**
   * Get the title of the x-axis
   * 
   * @return the title of the x-axis
   */
  protected final String getAxisTitleX() {
    return this.m_labelX;
  }

  /**
   * Get the title of the y-axis
   * 
   * @return the title of the y-axis
   */
  protected final String getAxisTitleY() {
    return this.m_labelY;
  }

  /**
   * iterate over all the points in a given line in an effective way
   * 
   * @param line
   *          the line
   * @return the iterator
   */
  protected final Iterator<Point2D> iterateLinePoints(final Line2D line) {
    final AxisRange2D range;
    final double minX, maxX, minY, maxY, dist, scaleX, scaleY;
    final int maxPoints;
    Iterator<Point2D> it, it2;

    range = this.m_range;
    minX = range.getMinimumX();
    maxX = range.getMaximumX();
    minY = range.getMinimumY();
    maxY = range.getMaximumY();

    // create iterators that compress the data as much as possible
    it = new StraightReducingIterator2D(//
        new BoundsReducingIterator2D(//
            new DataCollectionIterator2D(line.getLeftEnd(),//
                line.getData(), line.getRightEnd()),//
            minX, maxX, minY, maxY));

    maxPoints = this.getDriver().getMaxPointsPerLine();
    if ((maxPoints > 0) && (maxPoints < Integer.MAX_VALUE)) {
      scaleX = (1d / (maxX - minX));
      scaleY = (1d / (maxY - minY));
      dist = (1d / maxPoints);
      if ((dist > 0d) && (dist < Double.POSITIVE_INFINITY) && //
          (scaleX > 0d) && (scaleX < Double.POSITIVE_INFINITY) && //
          (scaleY > 0d) && (scaleY < Double.POSITIVE_INFINITY)) {
        it = new DistanceBasedReducingIterator2D(it, dist, scaleX, scaleY);
      }
    }

    it2 = line.getLineMode().iterate(it);
    if (it2 != it) {
      it = new StraightReducingIterator2D(//
          new BoundsReducingIterator2D(//
              it2, minX, maxX, minY, maxY));
    }

    return it;
  }
}
