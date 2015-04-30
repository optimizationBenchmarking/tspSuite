/**
 * <p>
 * Here we test the utility classes of the logisticPlanning.org tool suite
 * in package {@link org.logisticPlanning.utils}.
 * </p>
 * <h2>Package Structure of TSP Suite</h2>
 * <p>
 * The packages in this {@link org.logisticPlanning software framework}
 * follow can be divided into two sets:
 * </p>
 * <ol>
 * <li>Packages with classes related to the Traveling Salesman Problem
 * (TSP) are contained in package {@link org.logisticPlanning.tsp}.</li>
 * <li>Packages with general utility classes are contained within package
 * {@link org.logisticPlanning.utils}.</li>
 * </ol>
 * <p>
 * The {@link org.logisticPlanning.tsp TSP}-related packages all serve the
 * <em>TSP Suite</em> idea of implementing, benchmarking, and evaluating
 * TSP solvers. Hence, they a divided into three sub-packages:
 * </p>
 * <ol>
 * <li>Package {@link org.logisticPlanning.tsp.solving} contains both base
 * classes for implementing
 * {@link org.logisticPlanning.tsp.solving.TSPAlgorithm TSP solvers} as
 * well as utility classes (such as
 * {@link org.logisticPlanning.tsp.solving.operators search operations},
 * {@link org.logisticPlanning.tsp.solving.gpm GPM}s,
 * {@link org.logisticPlanning.tsp.solving.searchSpaces search space}
 * -related data structures, and other
 * {@link org.logisticPlanning.tsp.solving.utils utilities and data
 * structures} like
 * {@link org.logisticPlanning.tsp.solving.utils.candidates candidate sets}
 * and {@link org.logisticPlanning.tsp.solving.utils.edge edge objects})
 * and a variety of actual
 * {@link org.logisticPlanning.tsp.solving.algorithms algorithm
 * implementations}. This package also contains the classes for
 * automatically
 * {@link org.logisticPlanning.tsp.solving.TSPAlgorithmRunner run
 * experiments} in a parallel and distributed fashion.</li>
 * <li>Package {@link org.logisticPlanning.tsp.benchmarking} contains the
 * utilities for benchmarking the algorithms: Classes for
 * {@link org.logisticPlanning.tsp.benchmarking.dist distance computation},
 * the {@link org.logisticPlanning.tsp.benchmarking.instances benchmark
 * instances}, and the
 * {@link org.logisticPlanning.tsp.benchmarking.objective Objective
 * function} classes.</li>
 * <li>The package {@link org.logisticPlanning.tsp.evaluation} holds all
 * the modules of the {@link org.logisticPlanning.tsp.evaluation.Evaluator
 * Evaluator} component that can load the
 * {@link org.logisticPlanning.tsp.evaluation.data data} generated from the
 * benchmark runs and uses it report the performance of a
 * {@link org.logisticPlanning.tsp.evaluation.modules.impl.single single
 * algorithms} or
 * {@link org.logisticPlanning.tsp.evaluation.modules.impl.comparison
 * compare different algorithms}.</li>
 * </ol>
 * <p>
 * The {@link org.logisticPlanning.utils utility package} contains a
 * variety of different tools, such as
 * </p>
 * <ol>
 * <li>{@link org.logisticPlanning.utils.collections collection classes}</li>
 * <li>The package {@link org.logisticPlanning.utils.config config}
 * provides {@link org.logisticPlanning.utils.config.Configuration classes}
 * that can parse command line parameters or configuration files and
 * provide them to {@link org.logisticPlanning.utils.config.Configurable
 * classes} that can be configured that way.</li>
 * <li>The package {@link org.logisticPlanning.utils.document document}
 * holds a complete {@link org.logisticPlanning.utils.document.spec API}
 * for generating documents and reports with figures, tables, literature
 * references, and equations which is them
 * {@link org.logisticPlanning.utils.document.impl implemented} for, e.g.,
 * {@link org.logisticPlanning.utils.document.impl.latex LaTeX} and
 * {@link org.logisticPlanning.utils.document.impl.xhtml XHTML}.</li>
 * <li>The package {@link org.logisticPlanning.utils.graphics graphics}
 * provides utility classes for graphics and
 * {@link org.logisticPlanning.utils.graphics.chart charts}.</li>
 * <li>In package {@link org.logisticPlanning.utils.io} provides utilities
 * for {@link org.logisticPlanning.utils.io.FileUtils file interaction} and
 * {@link org.logisticPlanning.utils.io.charIO text-based IO}.</li>
 * <li>Package {@link org.logisticPlanning.utils.math} provides a lot of
 * mathematical core functionality, ranging from basic
 * {@link org.logisticPlanning.utils.math.functions functions} to
 * {@link org.logisticPlanning.utils.math.statistics statistics}.</li>
 * <li>The {@link org.logisticPlanning.utils.text text package} has
 * {@link org.logisticPlanning.utils.text.TextUtils utilities} for creating
 * and parsing text as well as for
 * {@link org.logisticPlanning.utils.text.CharTransformer transforming
 * text} (in particular special characters) in certain formats such as
 * {@link org.logisticPlanning.utils.text.transformations.XMLCharTransformer
 * XML} or
 * {@link org.logisticPlanning.utils.text.transformations.LaTeXCharTransformer
 * LaTeX}.</li>
 * </ol>
 * <p>
 * This overall structure of &quot;source code&quot; packages is mirrored
 * with an increasing number <a href="http://junit.org/">JUnit</a> test
 * cases in package {@link test.junit}. The test cases for a class
 * {@code XYZ} in package {@code org.bla.blubb} can be found in package
 * {@code test.junit.org.bla.blubb} and usually should be named something
 * like {@code XYZTest}.
 * </p>
 */
package test.junit.org.logisticPlanning.utils;

