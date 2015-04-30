package org.logisticPlanning.tsp.solving.operators;

import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;
import org.logisticPlanning.tsp.solving.Individual;

/**
 * <p>
 * This is the base class for all unary search operators in this framework.
 * A unary search operation takes one parent solution and creates one
 * offspring solution from it. The offspring is a slightly modified copy of
 * the parent. If small changes to a solution lead to small changes in its
 * quality&nbsp;[<a href="#cite_R1973ES" style="font-weight:bold">1</a>, <a
 * href="#cite_R1994ES" style="font-weight:bold">2</a>, <a
 * href="#cite_RB1995GPCAUS" style="font-weight:bold">3</a>], then we have
 * a certain chance that the offspring may be better than its parent. (If
 * there is no so-called &quot;strong causality&quot;, then we may as well
 * create random solutions).
 * </p>
 * <p>
 * Starting at version 0.9.8 of TSP Suite, we provide run-depending
 * initialization and finalization support for algorithms and algorithm
 * modules. The new class
 * {@link org.logisticPlanning.tsp.solving.TSPModule} is introduced as
 * base-class for all algorithm modules (such as
 * {@link org.logisticPlanning.tsp.solving.operators.Operator search
 * operators} or {@link org.logisticPlanning.tsp.solving.gpm.GPM
 * genotype-phenotype mappings}) and the
 * {@link org.logisticPlanning.tsp.solving.TSPAlgorithm TSP algorithms}
 * themselves. This new class provides two methods,
 * {@link org.logisticPlanning.tsp.solving.TSPModule#beginRun(org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction)}
 * and
 * {@link org.logisticPlanning.tsp.solving.TSPModule#endRun(org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction)}
 * which must be called before and after a run, respectively. Each
 * algorithm module must then invoke them recursively for all of its
 * sub-components. The new method
 * {@link org.logisticPlanning.tsp.solving.TSPAlgorithm#call(org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction)}
 * now acts as a wrapper for
 * {@link org.logisticPlanning.tsp.solving.TSPAlgorithm#solve(org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction)}
 * and invokes them. This allows for a more targeted allocation and
 * de-allocation of data structures for each run.
 * </p>
 * <h2>References</h2>
 * <ol>
 * <li><div><span id="cite_R1973ES" /><a
 * href="https://en.wikipedia.org/wiki/Ingo_Rechenberg">Ingo
 * Rechenberg</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo
 * ;Evolutionsstrategie: Optimierung technischer Systeme nach Prinzipien
 * der biologischen Evolution,&rdquo;</span> PhD Thesis, 1971&ndash;1973,
 * Berlin, Germany: Technische Universit&#228;t Berlin. volume 15 of
 * Problemata, Stuttgart, Germany: Friedrick Frommann Verlag and&nbsp;Bad
 * Cannstadt, Stuttgart, Baden-W&#252;rttemberg, Germany: Frommann-Holzboog
 * Verlag. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/3772803741">3-7728-0374-1</a>, <a
 * href="https://www.worldcat.org/isbn/3772803733">3-7728-0373-3</a>, <a
 * href
 * ="https://www.worldcat.org/isbn/9783772803741">978-3-7728-0374-1</a>,
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783772803734">978-3-7728
 * -0373-4</a>; LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/74320689">74320689</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/9020616">9020616</a>, <a
 * href="https://www.worldcat.org/oclc/500569005">500569005</a>,
 * and&nbsp;<a
 * href="https://www.worldcat.org/oclc/462818122">462818122</a>; Google
 * Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=QcNNGQAACAAJ">QcNNGQAACAAJ</a>;
 * PPN:&nbsp;<a
 * href="http://gso.gbv.de/PPNSET?PPN=024852090">024852090</a></div></li>
 * <li><div><span id="cite_R1994ES" /><a
 * href="https://en.wikipedia.org/wiki/Ingo_Rechenberg">Ingo
 * Rechenberg</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo
 * ;Evolutionsstrategie '94,&rdquo;</span> 1994, volume 1 of Werkstatt
 * Bionik und Evolutionstechnik, Bad Cannstadt, Stuttgart,
 * Baden-W&#252;rttemberg, Germany: Frommann-Holzboog Verlag. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/3772816428">3-7728-1642-8</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783772816420">978-3-772-
 * 81642-0</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/75424354">75424354</a>; Google Book
 * ID:&nbsp;<a
 * href="http://books.google.com/books?id=savAAAACAAJ">savAAAACAAJ</a>;
 * PPN:&nbsp;<a
 * href="http://gso.gbv.de/PPNSET?PPN=153251220">153251220</a></div></li>
 * <li><div><span id="cite_RB1995GPCAUS" />Justinian P. Rosca and&nbsp;Dana
 * H. Ballard: <span style="font-weight:bold">&ldquo;Causality in Genetic
 * Programming,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the Sixth
 * International Conference on Genetic Algorithms (ICGA'95)</span>,
 * July&nbsp;15&ndash;19, 1995, Pittsburgh, PA, USA: University of
 * Pittsburgh, pages 256&ndash;263, Larry J. Eshelman, editor, San
 * Francisco, CA, USA: Morgan Kaufmann Publishers Inc.. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/1558603700">1-55860-370-0</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9781558603707">9781558603707</a>;
 * Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=9xRRAAAAMAAJ">9xRRAAAAMAAJ</a>.
 * <div>link: [<a
 * href="http://citeseer.ist.psu.edu/rosca95causality.html">1</a>];
 * CiteSeer<sup>x</sup><sub style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href=
 * "http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.26.2503">10.1
 * .1.26 .2503</a></div></div></li>
 * </ol>
 * 
 * @param <P>
 *          the product type
 */
public class UnaryOperator<P> extends Operator<P> {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** a dummy operator */
  public static final UnaryOperator<Object> DUMMY = new _DummyUnaryOperator();

  /**
   * Create an operator that creates modified copies of given genotypes
   * 
   * @param name
   *          the name of this object
   */
  protected UnaryOperator(final String name) {
    super(name, 1);
  }

  /**
   * <p>
   * Create a modified copy of a candidate solution and store it in the
   * destination record {@code dest}. It is expected that the destination
   * record {@code dest} is either
   * {@link org.logisticPlanning.tsp.solving.Individual#Individual() new}
   * or has its evaluation results
   * {@link org.logisticPlanning.tsp.solving.Individual#clearEvaluation()}
   * and that its contents can immediately be overridden.
   * </p>
   * <p>
   * The cleared destination record has its
   * {@link org.logisticPlanning.tsp.solving.Individual#tourLength
   * objective value} (the tour length) set to
   * {@link java.lang.Long#MAX_VALUE}. If this method maybe performs a
   * slight change in existing solution that allow to compute the new tour
   * length more efficiently than what the
   * {@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction#evaluate(int[])
   * objective function} does, it may set the
   * {@link org.logisticPlanning.tsp.solving.Individual#tourLength tour
   * length} directly and safe that computational effort.
   * </p>
   * 
   * @param f
   *          the objective function
   * @param parent
   *          the parent
   * @param dest
   *          the destination individual
   */
  public void mutate(final Individual<P> dest, final ObjectiveFunction f,
      final Individual<P> parent) {
    dest.assign(parent);
  }

  /** {@inheritDoc} */
  @Override
  @SuppressWarnings("unchecked")
  public final void apply(final Individual<P> dest,
      final ObjectiveFunction f, final Individual<P>... parents) {
    this.mutate(dest, f, parents[0]);
  }

}
