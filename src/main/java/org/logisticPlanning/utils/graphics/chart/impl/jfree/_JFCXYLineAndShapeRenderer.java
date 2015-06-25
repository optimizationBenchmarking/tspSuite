package org.logisticPlanning.utils.graphics.chart.impl.jfree;

import java.awt.Paint;
import java.awt.Shape;
import java.awt.Stroke;

import org.jfree.chart.plot.DrawingSupplier;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.logisticPlanning.utils.graphics.chart.spec.ChartPalette;

/**
 * <p>
 * This internal class tries to avoid a problem with
 * {@link org.jfree.chart.renderer.xy.XYLineAndShapeRenderer}.
 * {@link org.jfree.chart.renderer.xy.XYLineAndShapeRenderer
 * XYLineAndShapeRenderer} iterates over the lines in a data set. It paints
 * them only if they are within the clip bounds of the drawing window. Only
 * when it paints them, it will use methods such as
 * {@link org.jfree.chart.plot.DefaultDrawingSupplier#getNextPaint()
 * getNextPaint} of the {@link org.jfree.chart.plot.DefaultDrawingSupplier
 * DefaultDrawingSupplier}. This, in turn, may lead to lines being painted
 * with the wrong color if the lines before them are outside of the limits
 * of the figure&hellip;
 * </p>
 * <h2>JFreeChart Fa&#xe7;ade of Chart API</h2>
 * <p>
 * <a href="http://www.jfree.org/jfreechart/">JFreeChart</a>&nbsp;[<a
 * href="#cite_GM2000J" style="font-weight:bold">1</a>] is an open source
 * (<a href="http://www.gnu.org/licenses/lgpl.html">LGPL</a>-licensed) Java
 * software package for drawing charts and diagrams. With the classes in
 * package {@link org.logisticPlanning.utils.graphics.chart.impl.jfree
 * jfree}, we provide a wrapper of our
 * {@link org.logisticPlanning.utils.graphics.chart.spec.ChartDriver chart
 * API} around the JFreeChart code. Since our chart API is grounded on
 * Java's {@link java.awt.Graphics2D Graphics2D} and only provides a few
 * simple methods, this implementation hides most of the complexity of
 * JFreeChart and provides simple chart-drawing capabilities that can be
 * used especially in conjunction with the
 * {@link org.logisticPlanning.utils.document.spec.Document document API}.
 * </p>
 * <p>
 * JFreeChart seems to be a bit too powerful for our purposes. On the
 * surface, it looked easy to use but has a few minor quirks that need to
 * be fixed with special code in order to seamelessly work in our
 * environment, mostly related to the use and order of colors. Therefore,
 * our {@link org.logisticPlanning.utils.graphics.chart.impl.jfree package}
 * contains some package-private classes. These have been developed for
 * {@code JFreeChart 1.0.13} in conjunction with {@code JCommon 1.0.16}. If
 * you use our wrapper together with different versions of this software,
 * please be particularly careful about the colors in which the
 * {@link org.logisticPlanning.utils.graphics.chart.spec.LineChart2D line
 * charts} are displayed.
 * </p>
 * <h2>References</h2>
 * <ol>
 * <li><div><span id="cite_GM2000J" /><a
 * href="http://www.jroller.com/dgilbert/">David Gilbert</a> and&nbsp;<a
 * href="http://www.linkedin.com/in/thomasmorgner">Thomas Morgner</a>:
 * <span style
 * ="font-style:italic;font-family:cursive;">&ldquo;JFreeChart,&
 * rdquo;</span> (Software), November&nbsp;27, 2000, Harpenden,
 * Hertfordshire, England, UK: Object Refinery Limited. <div>links: [<a
 * href="http://www.jfree.org/jfreechart/">1</a>] and&nbsp;[<a
 * href="http://sourceforge.net/projects/jfreechart/">2</a>]</div></div></li>
 * </ol>
 *
 *
 @author <em><a href="http://www.it-weise.de/">Thomas Weise</a></em>,
 *         Email:&nbsp;<a
 *         href="mailto:tweise@ustc.edu.cn">tweise@ustc.edu.cn</a>
 */
final class _JFCXYLineAndShapeRenderer extends XYLineAndShapeRenderer
    implements DrawingSupplier {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;
  /** the number of lines in the background */
  int m_backgroundCount;

  // /** the outline stroke index */
  // private int m_outlineStrokeIndex;

  /** the stroke index */
  private int m_strokeIndex;

  /** the color index */
  private int m_paintIndex;

  // /** the outline paint index */
  // private int m_outlinePaintIndex;
  // /** the fill paint index */
  // private int m_fillPaintIndex;

  /** the palette to use */
  private final ChartPalette m_palette;

  /**
   * create
   *
   * @param palette
   *          the palette
   */
  _JFCXYLineAndShapeRenderer(final ChartPalette palette) {
    super();
    this.m_backgroundCount = 0;
    this.m_palette = palette;
  }

  /**
   * Get the paint for the given item
   *
   * @param row
   *          the row (series)
   * @param column
   *          the column
   * @return the paint
   */
  @Override
  public final Paint getItemPaint(final int row, final int column) {
    return this.lookupSeriesPaint(row);
  }

  /**
   * Get the series paint for the given series
   *
   * @param series
   *          the series
   * @return the paint
   */
  @Override
  public final Paint getSeriesPaint(final int series) {
    return this.lookupSeriesPaint(series);
  }

  /**
   * lookup the series paint for the given series
   *
   * @param series
   *          the series
   * @return the paint
   */
  @Override
  public final Paint lookupSeriesPaint(final int series) {
    if (series < this.m_backgroundCount) {
      return this.m_palette.getBackgroundDataColor(series);
    }
    return this.m_palette.getForegroundDataColor(series
        - this.m_backgroundCount);
  }

  /**
   * Get the stroke for the given item
   *
   * @param row
   *          the row (series)
   * @param column
   *          the column
   * @return the stroke
   */
  @Override
  public final Stroke getItemStroke(final int row, final int column) {
    return this.lookupSeriesStroke(row);
  }

  /**
   * Get the series stroke for the given series
   *
   * @param series
   *          the series
   * @return the stroke
   */
  @Override
  public final Stroke getSeriesStroke(final int series) {
    return this.lookupSeriesStroke(series);
  }

  /**
   * lookup the series stroke for the given series
   *
   * @param series
   *          the series
   * @return the stroke
   */
  @Override
  public final Stroke lookupSeriesStroke(final int series) {
    if (series < this.m_backgroundCount) {
      return this.m_palette.getBackgroundDataStroke(series);
    }
    return this.m_palette.getForegroundDataStroke(series
        - this.m_backgroundCount);
  }

  /** {@inheritDoc} */
  @Override
  public final Stroke getNextStroke() {
    return this.lookupSeriesStroke(this.m_strokeIndex++);
  }

  /** {@inheritDoc} */
  @Override
  public final Stroke getNextOutlineStroke() {
    return null;// this.lookupSeriesStroke(this.m_outlineStrokeIndex++);
  }

  /** {@inheritDoc} */
  @Override
  public final Paint getNextPaint() {
    return this.lookupSeriesPaint(this.m_paintIndex++);
  }

  /** {@inheritDoc} */
  @Override
  public final Paint getNextOutlinePaint() {
    return null;// this.lookupSeriesPaint(this.m_outlinePaintIndex++);
  }

  /** {@inheritDoc} */
  @Override
  public Paint getNextFillPaint() {
    return null;// this.lookupSeriesPaint(this.m_fillPaintIndex++);
  }

  /**
   * Returns always {@code null}
   *
   * @return {@code null}
   */
  @Override
  public Shape getNextShape() {
    return null;
  }

  /**
   * Returns always {@code this}
   *
   * @return {@code this}
   */
  @Override
  public DrawingSupplier getDrawingSupplier() {
    return this;
  }

  /**
   * Returns always {@code false}
   *
   * @return {@code false}
   */
  @Override
  public final boolean getItemShapeVisible(final int series, final int item) {
    return false;
  }
}
