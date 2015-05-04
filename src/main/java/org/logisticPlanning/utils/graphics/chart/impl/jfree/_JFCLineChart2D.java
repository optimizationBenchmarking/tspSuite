package org.logisticPlanning.utils.graphics.chart.impl.jfree;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;
import java.util.Iterator;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.LegendItem;
import org.jfree.chart.LegendItemCollection;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockParams;
import org.jfree.chart.block.LengthConstraintType;
import org.jfree.chart.block.RectangleConstraint;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.SeriesRenderingOrder;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.Range;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.Size2D;
import org.jfree.ui.VerticalAlignment;
import org.jfree.util.UnitType;
import org.logisticPlanning.utils.graphics.chart.spec.ChartPalette;
import org.logisticPlanning.utils.graphics.chart.spec.ELegendType;
import org.logisticPlanning.utils.graphics.chart.spec.Line2D;
import org.logisticPlanning.utils.graphics.chart.spec.LineChart2D;
import org.logisticPlanning.utils.graphics.chart.spec.range.AxisRange2D;
import org.logisticPlanning.utils.graphics.chart.spec.range.AxisRange2DDef;
import org.logisticPlanning.utils.math.data.point.Point2D;
import org.logisticPlanning.utils.utils.EmptyUtils;

/**
 * <p>
 * The 2d line chart as JFreeChar-facade.
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
final class _JFCLineChart2D extends LineChart2D {

  /**
   * a place holder for positive infinity: due to restrictions of the
   * plotting libraries, only a relatively small subset of {@code double}
   * can actually be used
   */
  private static final double P_INF = (1e20d);

  /**
   * a place holder for negative infinity: due to restrictions of the
   * plotting libraries, only a relatively small subset of {@code double}
   * can actually be used
   */
  private static final double N_INF = (-_JFCLineChart2D.P_INF);

  /** the default insets */
  private static final RectangleInsets CHART_INSETS = RectangleInsets.ZERO_INSETS;
  /** the default insets */
  private static final RectangleInsets LEGEND_MARGIN = new RectangleInsets(
      UnitType.ABSOLUTE, 0.5d, 0.5d, 0.5d, 0.5d);

  /** the rendering hints */
  private static final RenderingHints RENDER_HINTS = new RenderingHints(
      RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

  /** the empty shape */
  private static final Shape EMPTY_SHAPE = new java.awt.geom.Line2D.Float(
      0f, 0f, 0f, 0f);

  static {
    _JFCLineChart2D.RENDER_HINTS.put(
        RenderingHints.KEY_ALPHA_INTERPOLATION,
        RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
    _JFCLineChart2D.RENDER_HINTS.put(RenderingHints.KEY_COLOR_RENDERING,
        RenderingHints.VALUE_COLOR_RENDER_QUALITY);
    _JFCLineChart2D.RENDER_HINTS.put(RenderingHints.KEY_FRACTIONALMETRICS,
        RenderingHints.VALUE_FRACTIONALMETRICS_ON);
    _JFCLineChart2D.RENDER_HINTS.put(RenderingHints.KEY_INTERPOLATION,
        RenderingHints.VALUE_INTERPOLATION_BICUBIC);
    _JFCLineChart2D.RENDER_HINTS.put(RenderingHints.KEY_RENDERING,
        RenderingHints.VALUE_RENDER_QUALITY);
    _JFCLineChart2D.RENDER_HINTS.put(RenderingHints.KEY_STROKE_CONTROL,
        RenderingHints.VALUE_STROKE_NORMALIZE);
    _JFCLineChart2D.RENDER_HINTS.put(RenderingHints.KEY_TEXT_ANTIALIASING,
        RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
  }

  /** the internal data set */
  private final XYSeriesCollection m_dataset;

  /** the chart */
  private final JFreeChart m_chart;

  /** the plot */
  private final XYPlot m_plot;

  /** the x-axis */
  private final NumberAxis m_x;

  /** the y-axis */
  private final NumberAxis m_y;

  /**
   * Create a new line chart
   *
   * @param driver
   *          the line chart's driver
   * @param range
   *          the axis range
   */
  _JFCLineChart2D(final JFreeChartDriver driver, final AxisRange2DDef range) {
    super(driver, range);

    final ChartPalette palette;
    final boolean grid, ticks;
    final _JFCXYLineAndShapeRenderer renderer;

    palette = driver.getPalette();

    this.m_dataset = new XYSeriesCollection();
    this.m_chart = ChartFactory.createXYLineChart(null, null, null,
        this.m_dataset, PlotOrientation.VERTICAL, false, false, false);

    this.m_chart.setBackgroundPaint(palette.getBackgroundColor());
    this.m_chart.setPadding(_JFCLineChart2D.CHART_INSETS);
    this.m_chart.setRenderingHints(_JFCLineChart2D.RENDER_HINTS);

    this.m_plot = this.m_chart.getXYPlot();
    this.m_plot.setBackgroundPaint(palette.getBackgroundColor());
    this.m_plot.setBackgroundAlpha(0f);
    this.m_plot.setForegroundAlpha(1f);
    this.m_plot.setOutlineVisible(false);

    renderer = new _JFCXYLineAndShapeRenderer(driver.getPalette());
    this.m_plot.setDrawingSupplier(renderer);
    this.m_plot.setRenderer(renderer);
    this.m_plot.setRenderer(0, renderer);

    grid = palette.useGridLines();
    this.m_plot.setDomainGridlinesVisible(grid);
    this.m_plot.setRangeGridlinesVisible(grid);
    if (grid) {
      this.m_plot.setDomainGridlinePaint(palette.getGridLineColor());
      this.m_plot.setDomainGridlineStroke(palette.getGridLineStroke());
      this.m_plot.setRangeGridlinePaint(palette.getGridLineColor());
      this.m_plot.setRangeGridlineStroke(palette.getGridLineStroke());
    }

    this.m_plot.setAxisOffset(_JFCLineChart2D.CHART_INSETS);
    this.m_plot.setDomainMinorGridlinesVisible(false);
    this.m_plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
    this.m_plot.setSeriesRenderingOrder(SeriesRenderingOrder.FORWARD);

    this.m_x = ((NumberAxis) (this.m_plot.getDomainAxis()));
    this.m_x.setAxisLineStroke(palette.getAxisStroke());
    this.m_x.setAxisLinePaint(palette.getAxisColor());
    this.m_x.setLabelFont(palette.getAxisTitleFont());
    ticks = palette.useAxisTicks();
    this.m_x.setTickMarksVisible(ticks);
    this.m_x.setTickLabelsVisible(ticks);
    if (ticks) {
      if (driver.m_units != null) {
        this.m_x.setStandardTickUnits(driver.m_units);
      }
      this.m_x.setTickLabelFont(palette.getAxisTickFont());
      this.m_x.setTickLabelPaint(palette.getAxisTickColor());
      this.m_x.setTickMarkPaint(palette.getAxisTickColor());
      this.m_x.setTickMarkStroke(palette.getAxisTickStroke());
    }

    this.m_y = ((NumberAxis) (this.m_plot.getRangeAxis()));
    this.m_y.setAxisLineStroke(palette.getAxisStroke());
    this.m_y.setAxisLinePaint(palette.getAxisColor());
    this.m_y.setLabelFont(palette.getAxisTitleFont());
    this.m_y.setTickMarksVisible(ticks);
    this.m_y.setTickLabelsVisible(ticks);
    if (ticks) {
      if (driver.m_units != null) {
        this.m_y.setStandardTickUnits(driver.m_units);
      }
      this.m_y.setTickLabelFont(palette.getAxisTickFont());
      this.m_y.setTickLabelPaint(palette.getAxisTickColor());
      this.m_y.setTickMarkPaint(palette.getAxisTickColor());
      this.m_y.setTickMarkStroke(palette.getAxisTickStroke());
    }

    this.m_chart.setAntiAlias(true);
    this.m_chart.setTextAntiAlias(true);
  }

  /** process the lines */
  private final void __processLines() {
    final LegendItemCollection legendCollection;
    final boolean addLegend;
    final ChartPalette palette;
    Stroke legendStroke;
    Color legendColor;
    int index;
    Line2D line;
    List<Line2D> lines;
    Iterator<Point2D> it;
    Point2D p;
    boolean foreground;
    XYSeries ser;
    String name;
    LegendItem item;

    lines = this.getBackgroundLines();
    ((_JFCXYLineAndShapeRenderer) (this.m_plot.getDrawingSupplier())).m_backgroundCount = lines
        .size();
    foreground = false;

    legendCollection = new LegendItemCollection();
    addLegend = (this.getLegendType() != ELegendType.NO_LEGEND);
    palette = this.getDriver().getPalette();

    // first add all foreground lines, than all background lines
    outer: for (;;) {
      index = 0;

      while (lines.size() > 0) {
        line = lines.remove(0);
        it = this.iterateLinePoints(line);

        // // are there any points to plot?
        // if (it.hasNext()) {
        // // then let's make a series and plot them!
        name = line.name();
        ser = new XYSeries(((name != null) ? name
            : EmptyUtils.EMPTY_STRING), false, true);

        while (it.hasNext()) {
          p = it.next();
          ser.add(_JFCLineChart2D.__format(p.getX()),
              _JFCLineChart2D.__format(p.getY()));
        }

        this.m_dataset.addSeries(ser);

        if (addLegend && (name != null) && (name.length() > 0)) {

          legendColor = (foreground ? //
              palette.getForegroundDataColor(index)//
              : palette.getBackgroundDataColor(index));
          legendStroke = (foreground ? //
              palette.getForegroundDataStroke(index)//
              : palette.getBackgroundDataStroke(index));

          item = new LegendItem(//
              name, null, null, null, // text
              false, _JFCLineChart2D.EMPTY_SHAPE, false,// shape
              legendColor,//
              false,//
              legendColor,//
              legendStroke,//
              false, _JFCLineChart2D.EMPTY_SHAPE,// line
              legendStroke,//
              legendColor//
              );
          item.setLabelFont(palette.getLegendFont());
          item.setLabelPaint(legendColor);
          legendCollection.add(item);
        }
        index++;
        // }
      }

      if (foreground) {
        break outer;
      }
      foreground = true;
      lines = this.getLines();
    }

    this.m_plot.setFixedLegendItems(legendCollection);
  }

  /** {@inheritDoc} */
  @Override
  protected final void compile() {

    super.compile();

    this.__initAxes();
    this.__processLines();
    this.__initLegend();
  }

  /** init the axes */
  private final void __initAxes() {
    final AxisRange2D range;
    double min, max;
    String xt;

    xt = this.getAxisTitleX();
    if (xt != null) {
      this.m_x.setLabel(xt);
    }

    xt = this.getAxisTitleY();
    if (xt != null) {
      this.m_y.setLabel(xt);
    }

    range = this.getAxisRange();
    min = range.getMinimumX();
    max = range.getMaximumX();
    this.m_x.setAutoRange(false);
    this.m_x.setLowerBound(min);
    this.m_x.setUpperBound(max);
    this.m_x.setRange(min, max);

    min = range.getMinimumY();
    max = range.getMaximumY();
    this.m_y.setAutoRange(false);
    this.m_y.setLowerBound(min);
    this.m_y.setUpperBound(max);
    this.m_y.setRange(min, max);
  }

  /** {@inheritDoc} */
  @Override
  protected final void draw(final Graphics2D graph, final Rectangle bounds) {
    final LegendTitle t;
    final RectangleInsets ri;

    if (this.getLegendType() == ELegendType.ONLY_LEGEND) {
      this.__paintAsLegend(graph, bounds);
      return;
    }

    t = this.m_chart.getLegend();
    if ((t != null) && (t.isVisible())) {
      if (bounds.height < bounds.width) {
        t.setPosition(RectangleEdge.RIGHT);
      } else {
        t.setPosition(RectangleEdge.BOTTOM);
      }
    }
    ri = this.m_plot.getInsets();

    this.m_plot.setInsets(new RectangleInsets(ri.getTop(), 0d, 0d,//
        ri.getRight()));
    this.m_chart.draw(graph, bounds);
  }

  /**
   * Format a double: due to restrictions of the plotting libraries, only a
   * relatively small subset of {@code double} can actually be used
   *
   * @param d
   *          the double
   * @return the formatted value
   */
  private static final double __format(final double d) {
    return (Math.min(_JFCLineChart2D.P_INF,
        Math.max(_JFCLineChart2D.N_INF, d)));
  }

  /** init the legend */
  private final void __initLegend() {
    final LegendTitle t;

    if (this.getLegendType() != ELegendType.NO_LEGEND) {
      t = new LegendTitle(this.m_plot);
      t.setItemFont(this.getDriver().getPalette().getLegendFont());
      t.setBackgroundPaint(this.getDriver().getPalette()
          .getBackgroundColor());
      t.setVisible(true);
      this.m_chart.addLegend(t);
    }
  }

  /**
   * Creates a rectangle that is aligned to the frame.
   *
   * @param dimensions
   *          the dimensions for the rectangle.
   * @param frame
   *          the frame to align to.
   * @param hAlign
   *          the horizontal alignment.
   * @param vAlign
   *          the vertical alignment.
   * @return A rectangle.
   */
  private static final Rectangle2D __createAlignedRectangle2D(
      final Size2D dimensions, final Rectangle2D frame,
      final HorizontalAlignment hAlign, final VerticalAlignment vAlign) {
    double x, y;

    x = y = Double.NaN;

    if (hAlign == HorizontalAlignment.LEFT) {
      x = frame.getX();
    } else
      if (hAlign == HorizontalAlignment.CENTER) {
        x = frame.getCenterX() - (dimensions.width / 2.0);
      } else
        if (hAlign == HorizontalAlignment.RIGHT) {
          x = frame.getMaxX() - dimensions.width;
        }
    if (vAlign == VerticalAlignment.TOP) {
      y = frame.getY();
    } else
      if (vAlign == VerticalAlignment.CENTER) {
        y = frame.getCenterY() - (dimensions.height / 2.0);
      } else
        if (vAlign == VerticalAlignment.BOTTOM) {
          y = frame.getMaxY() - dimensions.height;
        }

    return new Rectangle2D.Double(x, y, dimensions.width,
        dimensions.height);
  }

  /**
   * paint a legend
   *
   * @param legend
   *          the legend
   * @param graphics
   *          the graphics
   * @param bounds
   *          the bounds
   */
  private static final void __paintLegend(final LegendTitle legend,
      final Graphics2D graphics, final Rectangle2D bounds) {
    final Rectangle2D titleArea;
    final BlockParams p;
    final double ww, hh;
    final RectangleConstraint constraint;
    final Size2D size;

    p = new BlockParams();
    p.setGenerateEntities(false);

    ww = bounds.getWidth();
    if (ww <= 0.0d) {
      return;
    }
    hh = bounds.getHeight();
    if (hh <= 0.0d) {
      return;
    }

    constraint = new RectangleConstraint(ww, new Range(0.0, ww),
        LengthConstraintType.RANGE, hh, new Range(0.0, hh),
        LengthConstraintType.RANGE);

    size = legend.arrange(graphics, constraint);
    titleArea = _JFCLineChart2D.__createAlignedRectangle2D(size, bounds,
        legend.getHorizontalAlignment(), VerticalAlignment.TOP);
    legend.setMargin(_JFCLineChart2D.LEGEND_MARGIN);
    legend.setPadding(_JFCLineChart2D.CHART_INSETS);
    legend.draw(graphics, titleArea, p);
  }

  /**
   * paint this chart as legend
   *
   * @param graphics
   *          the graphics
   * @param bounds
   *          the bounds
   */
  private final void __paintAsLegend(final Graphics2D graphics,
      final Rectangle bounds) {
    final LegendTitle legend;
    final _JFCXYItemRenderer render;
    final XYItemRenderer old;
    Rectangle2D r;
    double w, h;

    legend = this.m_chart.getLegend();
    if ((legend == null) || (!(legend.isVisible()))) {
      this.m_chart.draw(graphics, bounds);
      return;
    }

    legend.setVisible(false);
    old = this.m_plot.getRenderer();
    try {
      render = new _JFCXYItemRenderer(old);
      this.m_plot.setRenderer(render);
      this.m_plot.setRenderer(0, render);
      this.paint(graphics, bounds);
      legend.setVisible(true);

      legend.setBackgroundPaint(this.m_plot.getBackgroundPaint());
      w = (render.m_maxX - render.m_minX);
      h = (render.m_maxY - render.m_minY);
      r = new Rectangle2D.Double(//
          render.m_minX + (0.05d * w), //
          render.m_minY + (0.05d * h),//
          w * 0.9d, //
          h * 0.9d);

      _JFCLineChart2D.__paintLegend(legend, graphics, r);
    } finally {
      this.m_plot.setRenderer(old);
    }
  }
}
