/**
 * Components and an API for drawing charts. <h2>Chart API Package
 * Structure</h2>
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
 */
package org.logisticPlanning.utils.graphics.chart;

