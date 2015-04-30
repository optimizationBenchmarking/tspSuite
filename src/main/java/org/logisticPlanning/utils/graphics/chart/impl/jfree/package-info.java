/**
 * <p>
 * Wrapper classes that ground the
 * {@link org.logisticPlanning.utils.graphics.chart chart API} to a
 * JFreeChart fa&#xe7;ade.
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
 * <h2>Chart API Package Structure</h2>
 * <p>
 * We develop a set of classes for painting charts in package
 * {@link org.logisticPlanning.utils.graphics.chart}. Like the
 * {@link org.logisticPlanning.utils.document document API}, the
 * {@link org.logisticPlanning.utils.graphics.chart chart API} is divided
 * into a {@link org.logisticPlanning.utils.graphics.chart.spec
 * specification} and an
 * {@link org.logisticPlanning.utils.graphics.chart.impl implementation}
 * package.
 * </p>
 * <p>
 * The specification contains the abstract base classes that can be used
 * for drawing charts. The main entry point for accessing charts is the
 * abstract class
 * {@link org.logisticPlanning.utils.graphics.chart.spec.ChartDriver
 * ChartDriver}. An chart API implementation will provide an implementation
 * of this class. Under its hood, this implementation then can provide the
 * specialized drawing and data processing utilities. Currently, the
 * following implementations of the chart API exist:
 * </p>
 * <ol>
 * <li>The implementation package
 * {@link org.logisticPlanning.utils.graphics.chart.impl.jfree jfree}
 * grounds the chart API to a <a
 * href="http://www.jfree.org/jfreechart/">JFreeChart</a>-fa&#xe7;ade.</li>
 * </ol>
 * <p>
 * The chart API makes heavy use of the
 * {@link org.logisticPlanning.utils.math.data.collection numeric
 * collections} and {@link org.logisticPlanning.utils.math.data.iteration
 * iteration} classes.
 * </p>
 * <h2>References</h2>
 * <ol>
 * <li><div><span id="cite_GM2000J" /><a
 * href="http://www.jroller.com/dgilbert/">David Gilbert</a> and&nbsp;<a
 * href="http://www.linkedin.com/in/thomasmorgner">Thomas Morgner</a>:
 * <span style="font-style:italic;font-family:cursive;">&ldquo;JFreeChart,&
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
package org.logisticPlanning.utils.graphics.chart.impl.jfree;

