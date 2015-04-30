package org.logisticPlanning.tsp.solving.gpm;

import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;
import org.logisticPlanning.tsp.solving.Individual;
import org.logisticPlanning.tsp.solving.TSPModule;

/**
 * <p>
 * A Genotype-Phenotype Mapping that translates a genotype from the set of
 * genotypes {@code G} to a phenotype in the set of phenotypes. In the TSP,
 * all phenotypes are integer arrays that represent permutations of nodes
 * to visit. The GPM hence can also directly assign an objective value to
 * these phenotypes.
 * </p>
 * <p>
 * In a metaheuristic search, way may distinguish genotypes and phenotypes.
 * A phenotype is a candidate solution. In case of a TSP, that is a
 * sequence of nodes to be visited. A genotype can basically be anything
 * which represents instructions how the phenotype should be built. These
 * instructions are translated to phenotypes via the
 * {@link org.logisticPlanning.tsp.solving.gpm.GPM Genotype-Phenotype
 * Mapping} (GPM). This is a bit like you being a phenotype and your DNA
 * being your genotype. Often, we will use the direct, permutation-based
 * representation for solutions of the TSP for the search. Then, our search
 * operators work on the same data structures, i.e., {@code int[]} and the
 * GPM becomes the
 * {@link org.logisticPlanning.tsp.solving.gpm.IdentityMapping identity
 * mapping} , i.e., is unnecessary. However, sometimes we may wish to have
 * genotypes that are different data structures. Such a data structure
 * could describe how an existing solution should be changed to get a new
 * one, for instance. Matter of fact, we implement such a GPM in our
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.developmental.devUpdatingEA.DevUpdatingEA
 * developmental updating EA} idea&nbsp;[<a href="#cite_OWDC2013SADAFTSP"
 * style="font-weight:bold">1</a>].
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
 * <li><div><span id="cite_OWDC2013SADAFTSP" /><a
 * href="mailto:oyjmical@mail.ustc.edu.cn">Jin Ouyang</a> <span
 * style="color:gray">[&#27431;&#38451;&#26187;</span>], <a
 * href="http://www.it-weise.de/">Thomas Weise</a> <span
 * style="color:gray">[&#27748;&#21355;&#24605;</span>], <a
 * href="http://www.marmakoide.org/">Alexandre Devert</a>, and&nbsp;<a
 * href="http://www.swinburne.edu.my/iSECURES/index.php?do=rchiong">Raymond
 * Chiong</a>: <span style="font-weight:bold">&ldquo;SDGP: A Developmental
 * Approach for Traveling Salesman Problems,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the 2013
 * IEEE Symposium on Computational Intelligence in Production and Logistics
 * Systems (CIPLS'13)</span>, April&nbsp;15&ndash;19, 2013, Singapore:
 * Grand Copthorne Waterfront Hotel, pages 78&ndash;85, Los Alamitos, CA,
 * USA: IEEE Computer Society Press. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/9781467359054"
 * >978-1-4673-5905-4</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1109/CIPLS.2013.6595203">10.1109/CIPLS
 * .2013.6595203</a>; INSPEC Accession Number:&nbsp;13752116;
 * EI:&nbsp;20134116837899. <div>link: [<a
 * href="http://www.it-weise.de/documents/files/OWDC2013SADAFTSP.pdf"
 * >1</a>]</div></div></li>
 * </ol>
 * 
 * @param <G>
 *          the set of genotypes
 */
public class GPM<G> extends TSPModule {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * create the gpm
   * 
   * @param name
   *          the name of the gpm
   */
  protected GPM(final String name) {
    super(name);
  }

  /**
   * Map a genotype to a phenotype
   * 
   * @param f
   *          the objective function and distance computer
   * @param p
   *          the individual record
   */
  public void gpm(final ObjectiveFunction f, final Individual<G> p) {//
  }

  /**
   * a method called before each generation
   * 
   * @param f
   *          the objective function
   */
  public void beforeGeneration(final ObjectiveFunction f) {
    //
  }

  /**
   * a method called after each generation
   * 
   * @param f
   *          the objective function
   */
  public void afterGeneration(final ObjectiveFunction f) {
    //
  }
}
