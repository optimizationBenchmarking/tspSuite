package org.logisticPlanning.utils.graphics.chart.impl.jfree;

import java.util.Locale;

import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.TickUnitSource;
import org.logisticPlanning.utils.graphics.chart.spec.ChartDriver;
import org.logisticPlanning.utils.graphics.chart.spec.LineChart2D;
import org.logisticPlanning.utils.graphics.chart.spec.range.AxisRange2DDef;

/**
 * <p>
 * A chart driver for producing charts with JFreeChart.
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
public final class JFreeChartDriver extends ChartDriver {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the tick unit source */
  final TickUnitSource m_units;

  /** create */
  public JFreeChartDriver() {
    super("JFreeChart Driver"); //$NON-NLS-1$
    this.m_units = NumberAxis.createStandardTickUnits(Locale.US);
  }

  /** {@inheritDoc} */
  @Override
  public final LineChart2D createLineChart2D(final AxisRange2DDef range) {
    return new _JFCLineChart2D(this, range);
  }

}
