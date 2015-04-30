package org.logisticPlanning.tsp.solving.operators;

import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;
import org.logisticPlanning.tsp.solving.Individual;

/**
 * <p>
 * The base class for all binary operators that are used to solve TSPs. A
 * binary search operator takes two parent individuals and creates one
 * offspring solution. The basic assumption is that each solution consists
 * of several distinguishable elements, its &quot;genes&quot;, that
 * together are responsible for its (good and bad) characteristics. In a
 * TSP solution in permutation representation, these genes may be the edges
 * that are used in the solution or sub-sequences of nodes/cities. It is
 * known that genes with good influence may be copied more often from one
 * generation to another&nbsp;[<a href="#cite_H1975GA"
 * style="font-weight:bold">1</a>, <a href="#cite_DJ1975GA"
 * style="font-weight:bold">2</a>, <a href="#cite_H1992GA"
 * style="font-weight:bold">3</a>]. The idea behind a binary (crossover)
 * operator is that the two inputs, parent individuals, may have different
 * good genes. If the operator can combine these genes, over the course of
 * evolution, they may aggregate and we would get better and better
 * individuals. This so-called Building Block Hypothesis is still not
 * proven, though&nbsp;[<a href="#cite_G1989GA"
 * style="font-weight:bold">4</a>, <a href="#cite_H1975GA"
 * style="font-weight:bold">1</a>, <a href="#cite_MFH1992RRGAFLGAP"
 * style="font-weight:bold">5</a>], and there also exist alternative
 * hypotheses&nbsp;[<a href="#cite_BS2002ES"
 * style="font-weight:bold">6</a>, <a href="#cite_B1997AAEFTMIWGAO"
 * style="font-weight:bold">7</a>]. In any case, the crossover operation
 * aims at combining different good individuals to obtain even better ones.
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
 * <li><div><span id="cite_H1975GA" /><a
 * href="https://en.wikipedia.org/wiki/John_Henry_Holland">John Henry
 * Holland</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Adaptation in
 * Natural and Artificial Systems: An Introductory Analysis with
 * Applications to Biology, Control, and Artificial
 * Intelligence,&rdquo;</span> 1975, Ann Arbor, MI, USA: University of
 * Michigan Press. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0472084607">0-472-08460-7</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780472084609">978-0-472-
 * 08460-9</a>; LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/74078988">74078988</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/1531617">1531617</a> and&nbsp;<a
 * href="https://www.worldcat.org/oclc/266623839">266623839</a>; Google
 * Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=JE5RAAAAMAAJ">JE5RAAAAMAAJ</a>,
 * <a
 * href="http://books.google.com/books?id=YE5RAAAAMAAJ">YE5RAAAAMAAJ</a>,
 * <a
 * href="http://books.google.com/books?id=Qk5RAAAAMAAJ">Qk5RAAAAMAAJ</a>,
 * <a
 * href="http://books.google.com/books?id=vk5RAAAAMAAJ">vk5RAAAAMAAJ</a>,
 * and&nbsp;<a
 * href="http://books.google.com/books?id=JOv3AQAACAAJ">JOv3AQAACAAJ</a>;
 * PPN:&nbsp;<a
 * href="http://gso.gbv.de/PPNSET?PPN=075982293">075982293</a></div></li>
 * <li><div><span id="cite_DJ1975GA" /><a
 * href="http://cs.gmu.edu/~kdejong/">Kenneth Alan De Jong</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;An Analysis of the
 * Behavior of a Class of Genetic Adaptive Systems,&rdquo;</span> PhD
 * Thesis, August&nbsp;1975, Ann Arbor, MI, USA: University of Michigan.
 * <div>link: [<a
 * href="http://cs.gmu.edu/~eclab/kdj_thesis.html">1</a>]</div></div></li>
 * <li><div><span id="cite_H1992GA" /><a
 * href="https://en.wikipedia.org/wiki/John_Henry_Holland">John Henry
 * Holland</a>: <span style="font-weight:bold">&ldquo;Genetic Algorithms
 * &#8210; Computer programs that &quot;evolve&quot; in ways that resemble
 * natural selection can solve complex problems even their creators do not
 * fully understand,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Scientific
 * American</span> 267(1):44&ndash;50, July&nbsp;1992; published by New
 * York, NY, USA: Scientific American, Inc.. <div>links: [<a
 * href="http://www2.econ.iastate.edu/tesfatsi/holland.gaintro.htm">1</a>],
 * [<a href =
 * "http://www.cc.gatech.edu/~turk/bio_sim/articles/genetic_algorithm.pdf"
 * >2 </a>], [<a
 * href="http://members.fortunecity.com/templarseries/algo.html">3</a>],
 * and&nbsp;[<a
 * href="http://www.im.isu.edu.tw/faculty/pwu/heuristic/GA_1.pdf">4<
 * /a>]</div></div></li>
 * <li><div><span id="cite_G1989GA" /><a
 * href="http://www.davidegoldberg.com/de-goldberg.html">David Edward
 * Goldberg</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Genetic Algorithms
 * in Search, Optimization, and Machine Learning,&rdquo;</span> 1989,
 * Boston, MA, USA: Addison-Wesley Longman Publishing Co., Inc..
 * ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0201157675">0-201-15767-5</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780201157673">978-0-201-
 * 15767-3</a>; LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/88006276">88006276</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/299467773">299467773</a>, <a
 * href="https://www.worldcat.org/oclc/255664278">255664278</a>, <a
 * href="https://www.worldcat.org/oclc/246916144">246916144</a>,
 * and&nbsp;<a href="https://www.worldcat.org/oclc/17674450">17674450</a>;
 * Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=2IIJAAAACAAJ">2IIJAAAACAAJ</a>,
 * <a
 * href="http://books.google.com/books?id=3_RQAAAAMAAJ">3_RQAAAAMAAJ</a>,
 * and&nbsp;<a
 * href="http://books.google.com/books?id=bO9SAAAACAAJ">bO9SAAAACAAJ</a>;
 * PPN:&nbsp;<a
 * href="http://gso.gbv.de/PPNSET?PPN=250764105">250764105</a>, <a
 * href="http://gso.gbv.de/PPNSET?PPN=301364087">301364087</a>, <a
 * href="http://gso.gbv.de/PPNSET?PPN=357619773">357619773</a>, <a
 * href="http://gso.gbv.de/PPNSET?PPN=357663551">357663551</a>, <a
 * href="http://gso.gbv.de/PPNSET?PPN=478167571">478167571</a>, <a
 * href="http://gso.gbv.de/PPNSET?PPN=499485440">499485440</a>, <a
 * href="http://gso.gbv.de/PPNSET?PPN=558515053">558515053</a>, and&nbsp;<a
 * href="http://gso.gbv.de/PPNSET?PPN=62589894X">62589894X</a></div></li>
 * <li><div><span id="cite_MFH1992RRGAFLGAP" /><a
 * href="http://web.cecs.pdx.edu/~mm/">Melanie Mitchell</a>, <a
 * href="http://www.cs.unm.edu/~forrest/">Stephanie Forrest</a>,
 * and&nbsp;<a href="https://en.wikipedia.org/wiki/John_Henry_Holland">John
 * Henry Holland</a>: <span style="font-weight:bold">&ldquo;The Royal Road
 * for Genetic Algorithms: Fitness Landscapes and GA
 * Performance,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Toward a Practice of
 * Autonomous Systems: Proceedings of the First European Conference on
 * Artificial Life (Actes de la Premi&#232;re Conf&#233;rence
 * Europ&#233;enne sur la Vie Artificielle) (ECAL'91)</span>,
 * December&nbsp;11&ndash;13, 1991, Paris, France, pages 245&ndash;254,
 * Francisco J. Varela and&nbsp;Paul Bourgine, editors, Bradford Books,
 * Cambridge, MA, USA: MIT Press. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0262720191">0-262-72019-1</a>.
 * <div>links: [<a
 * href="http://citeseer.ist.psu.edu/mitchell91royal.html">1</a>]
 * and&nbsp;[<a href="http://web.cecs.pdx.edu/~mm/ecal92.pdf">2</a>];
 * CiteSeer<sup>x</sup><sub style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href=
 * "http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.49.212">10.1
 * .1.49 .212</a></div></div></li>
 * <li><div><span id="cite_BS2002ES" /><a
 * href="https://homepages.fhv.at/hgb/">Hans-Georg Beyer</a> and&nbsp;<a
 * href="https://en.wikipedia.org/wiki/Hans-Paul_Schwefel">Hans-Paul
 * Schwefel</a>: <span style="font-weight:bold">&ldquo;Evolution Strategies
 * &#8210; A Comprehensive Introduction,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Natural Computing: An
 * International Journal</span> 1(1):3&ndash;52, March&nbsp;2002; published
 * by Norwell, MA, USA: Kluwer Academic Publishers and&nbsp;Dordrecht,
 * Netherlands: Springer Netherlands. doi:&nbsp;<a
 * href="http://dx.doi.org/10.1023/A:1015059928466"
 * >10.1023/A:1015059928466</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/15677818">1567-7818</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/15729796">1572-9796</a>. <div>link:
 * [<a href="http://www.cs.bham.ac.uk/~pxt/NIL/es.pdf">1</a>]</div></div></li>
 * <li><div><span id="cite_B1997AAEFTMIWGAO" /><a
 * href="https://homepages.fhv.at/hgb/">Hans-Georg Beyer</a>: <span
 * style="font-weight:bold">&ldquo;An Alternative Explanation for the
 * Manner in which Genetic Algorithms Operate,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Biosystems</span>
 * 41(1):1&ndash;15, January&nbsp;1997; published by East Park, Shannon,
 * Ireland: Elsevier Science Ireland Ltd. and&nbsp;Amsterdam, The
 * Netherlands: North-Holland Scientific Publishers Ltd.. doi:&nbsp;<a
 * href="http://dx.doi.org/10.1016/S0303-2647(96)01657-7"
 * >10.1016/S0303-2647(96)01657-7</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/03032647">0303-2647</a>.
 * <div>CiteSeer<sup>x</sup><sub
 * style="font-style:italic">&#946;</sub>:&nbsp;<a href
 * ="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.39.307"
 * >10.1.1.39 .307</a></div></div></li>
 * </ol>
 * 
 * @param <P>
 *          the product type
 */
public class BinaryOperator<P> extends Operator<P> {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** a dummy operator */
  public static final BinaryOperator<Object> DUMMY = new _DummyBinaryOperator();

  /**
   * create
   * 
   * @param name
   *          the name of this object
   */
  protected BinaryOperator(final String name) {
    super(name, 2);
  }

  /**
   * <p>
   * Create a new genotype which is a combination of two existing ones and
   * store it in a destination individual record {@code dest}. It is
   * expected that the destination record is either
   * {@link org.logisticPlanning.tsp.solving.Individual#Individual() new}
   * has its evaluation results
   * {@link org.logisticPlanning.tsp.solving.Individual#clearEvaluation()}
   * and that its contents can immediately be overridden.
   * </p>
   * <p>
   * The cleared destination record has its
   * {@link org.logisticPlanning.tsp.solving.Individual#tourLength
   * objective value} (the tour length) set to
   * {@link java.lang.Long#MAX_VALUE}. If this method maybe performs a
   * slight change in existing permutations that allow to compute the new
   * tour length more efficiently than what the
   * {@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction#evaluate(int[])
   * objective function} does, it may set the
   * {@link org.logisticPlanning.tsp.solving.Individual#tourLength tour
   * length} directly and safe that computational effort.
   * </p>
   * 
   * @param dest
   *          the destination individual
   * @param f
   *          the objective function
   * @param parent1
   *          the first parent
   * @param parent2
   *          the second parent
   */
  public void recombine(final Individual<P> dest,
      final ObjectiveFunction f, final Individual<P> parent1,
      final Individual<P> parent2) {
    dest.assign(f.getRandom().nextBoolean() ? parent1 : parent2);
  }

  /** {@inheritDoc} */
  @Override
  @SuppressWarnings("unchecked")
  public final void apply(final Individual<P> dest,
      final ObjectiveFunction f, final Individual<P>... parents) {
    this.recombine(dest, f, parents[0], parents[1]);
  }

}
