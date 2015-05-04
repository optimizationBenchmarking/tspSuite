package org.logisticPlanning.tsp.solving.algorithms.metaheuristics.developmental.devUpdatingEA;

import java.io.PrintStream;

import org.logisticPlanning.tsp.benchmarking.instances.Instance;
import org.logisticPlanning.tsp.solving.TSPAlgorithmRunner;
import org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.hc.UpdatingPermutationHillClimber;
import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.developmental.devUpdatingEA.indexes.IndexIterator;
import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.developmental.devUpdatingEA.indexes.UniformRandomIndexIterator;
import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.general.ea.EA;
import org.logisticPlanning.tsp.solving.gpm.GPM;
import org.logisticPlanning.tsp.solving.operators.permutation.update.PermutationUpdateOperator;
import org.logisticPlanning.tsp.solving.operators.permutation.update.PermutationUpdateOperators;
import org.logisticPlanning.tsp.solving.operators.trees.creation.TreeRampedHalfAndHalf;
import org.logisticPlanning.tsp.solving.operators.trees.mutation.SubTreeReplacementMutator;
import org.logisticPlanning.tsp.solving.operators.trees.recombination.SubTreeExchangeRecombination;
import org.logisticPlanning.tsp.solving.searchSpaces.trees.NodeTypeSet;
import org.logisticPlanning.tsp.solving.searchSpaces.trees.ReflectionNodeType;
import org.logisticPlanning.tsp.solving.searchSpaces.trees.math.Function;
import org.logisticPlanning.tsp.solving.searchSpaces.trees.math.arith.Angle;
import org.logisticPlanning.tsp.solving.searchSpaces.trees.math.arith.Mul;
import org.logisticPlanning.tsp.solving.searchSpaces.trees.math.basic.VariableType;
import org.logisticPlanning.tsp.solving.searchSpaces.trees.math.ctrl.IfThenElse;
import org.logisticPlanning.tsp.solving.searchSpaces.trees.math.trig.Sin;
import org.logisticPlanning.utils.config.Configurable;
import org.logisticPlanning.utils.math.functions.UnaryFunction;
import org.logisticPlanning.utils.math.functions.arithmetic.Identity;

/**
 * <p>
 * An evolutionary algorithm that uses a developmental updating
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.developmental.devUpdatingEA.DevUpdatingGPM
 * GPM}, very similar to the method introduced in&nbsp;[<a
 * href="#cite_OWDC2013SADAFTSP" style="font-weight:bold">1</a>], but with
 * a uniform random way to pick up indices and thus, not a {@code O(n^2)}
 * complexity. A different (older) developmental idea applied to CARPs
 * instead of TSPs is given in&nbsp;[<a href="#cite_OWDC2013SADAFTSP"
 * style="font-weight:bold">1</a>, <a href="#cite_WDT2012ADSTDCARPUGP"
 * style="font-weight:bold">2</a>, <a href="#cite_W2012RFLP"
 * style="font-weight:bold">3</a>] and here we present the algorithm
 * published in&nbsp;[<a href="#cite_OWDC2013SADAFTSP"
 * style="font-weight:bold">1</a>] in a refined version.
 * </p>
 * <p>
 * In &quot;indirect representations&quot;, the search space is
 * significantly different from the solution space and a
 * {@link org.logisticPlanning.tsp.solving.gpm.GPM Genotype-Phenotype
 * Mapping} (GPM) translates between them. At least two classes of indirect
 * representations may be distinguished&nbsp;[<a
 * href="#cite_DWT2011ASOSRFEOOGS" style="font-weight:bold">4</a>, <a
 * href="#cite_BK1999TWTGDACOEFAEDP" style="font-weight:bold">5</a>]:
 * generative and ontogenic approaches. In the generative method, the GPM
 * is a &quot;one-shot&quot; functional mapping from the genotypes to the
 * phenotypes. The GPM may be an arbitrarily complex decoder, but it only
 * uses the information given in the genotypes as input. One example for
 * such mappings in the area of Genetic Programming is Grammatical
 * Evolution (GE)&nbsp;[<a href="#cite_RCON1998GPGE"
 * style="font-weight:bold">6</a>] with context-free grammars, where the
 * genotypes are integer strings and the candidate solutions are sentences
 * of a language defined by a given grammar. In GE the GPM starts with the
 * starting symbol of that static grammar as a current variable. The first
 * gene in the genotype identifies the first rule of the grammar fitting to
 * the current variable to be expanded. This may result in new variables
 * occurring, which then are expanded by rules identified by the following
 * genes. A study on using different grammar rules to indirect encoding of
 * Neural Network structures is given in&nbsp;[<a
 * href="#cite_YS1995APSODANNUCE" style="font-weight:bold">7</a>, <a
 * href="#cite_Y1999EANN" style="font-weight:bold">8</a>].
 * </p>
 * <p>
 * Ontogenic (or &quot;developmental&quot;) mappings additionally involve
 * feedback from simulations or the process of computing the objective
 * values when building the phenotypes in an iterative manner&nbsp;[<a
 * href="#cite_DWT2011ASOSRFEOOGS" style="font-weight:bold">4</a>, <a
 * href="#cite_D2009BPOTAAOBA" style="font-weight:bold">9</a>]. Our work
 * herre is a developmental, ontogenic approach that refines an existing
 * candidate solution based on information obtained from the process of
 * computing the objective function.
 * </p>
 * <p>
 * Recently, it was shown that ontogenic mappings can yield results similar
 * to direct and generative ones but with lesser computational effort
 * despite the more complex solution creation and evaluation process on the
 * example of the evolution of rigid truss design optimization&nbsp;[<a
 * href="#cite_DWT2011ASOSRFEOOGS" style="font-weight:bold">4</a>]. In a
 * direct method, the volume of the (up to 600) beams of a truss would be
 * optimized by a numerical optimization algorithm directly. The genotype
 * of a generative approach could be a function that translates the
 * coordinates of a beam to its thickness. In the ontogenic method&nbsp;[<a
 * href="#cite_DWT2011ASOSRFEOOGS" style="font-weight:bold">4</a>], the
 * genotypes are functions that receive as a parameter the mechanical
 * stress on a beam and return how much the cross section of the beam
 * should be increased. Beginning with a basic beam structure, the
 * mechanical stress is evaluated and the function is applied to each of
 * the beams. The updated truss is simulated again and the process is
 * iterated a couple of times. The resulting structure, the phenotype, has
 * up to 600 parameters whereas the genotypes in&nbsp;[<a
 * href="#cite_DWT2011ASOSRFEOOGS" style="font-weight:bold">4</a>] are MLPs
 * representing the modification function, encoded as real vectors
 * containing, e.g., only 12 neural weights.
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
 * <li><div><span id="cite_WDT2012ADSTDCARPUGP" /><a
 * href="http://www.it-weise.de/">Thomas Weise</a> <span
 * style="color:gray">[&#27748;&#21355;&#24605;</span>], <a
 * href="http://staff.ustc.edu.cn/~ketang/">Ke Tang</a> <span
 * style="color:gray">[&#21776;&#29634;</span>], and&nbsp;<a
 * href="http://www.marmakoide.org/">Alexandre Devert</a>: <span
 * style="font-weight:bold">&ldquo;A Developmental Solution to (Dynamic)
 * Capacitated Arc Routing Problems using Genetic
 * Programming,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the
 * Genetic and Evolutionary Computation Conference (GECCO'12)</span>,
 * July&nbsp;7&ndash;11, 2012, Philadelphia, PA, USA: Doubletree by Hilton
 * Hotel Philadelphia Center City, pages 831&ndash;838, Terence Soule
 * and&nbsp;<a href="http://www.epistasis.org/jason.html">Jason H.
 * Moore</a>, editors, New York, NY, USA: Association for Computing
 * Machinery (ACM). ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/9781450311779"
 * >978-1-4503-1177-9</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1145/2330163.2330278"
 * >10.1145/2330163.2330278</a>; SCI/WOS:&nbsp;WOS:000309611100104;
 * EI:&nbsp;20123315330852. <div>link: [<a href
 * ="http://www.it-weise.de/documents/files/WDT2012ADSTDCARPUGP.pdf"
 * >1</a>]</ div></div></li>
 * <li><div><span id="cite_W2012RFLP" /><a
 * href="http://www.it-weise.de/">Thomas Weise</a> <span
 * style="color:gray">[&#27748;&#21355;&#24605;</span>]: <span
 * style="font-weight:bold">&ldquo;Representations for Logistic
 * Planning,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">The Third NICaiA Workshop
 * on Nature Inspired Computation and Its Applications (NICaiA'12)</span>,
 * April&nbsp;16&ndash;17, 2012, Birmingham, UK: University of Birmingham,
 * Computer Science Building, <a href="http://www.cs.bham.ac.uk/~xin/">Xin
 * Yao</a> <span style="color:gray">[&#23002;&#26032;</span>], editor.
 * further information: [<a href=
 * "http://www.cercia.ac.uk/projects/research/NICaiA/2012workshopsinfor.php"
 * >1</a>]. <div>link: [<a
 * href="http://www.it-weise.de/documents/files/W2012RFLP.pdf"
 * >1</a>]</div></div></li>
 * <li><div><span id="cite_DWT2011ASOSRFEOOGS" /><a
 * href="http://www.marmakoide.org/">Alexandre Devert</a>, <a
 * href="http://www.it-weise.de/">Thomas Weise</a> <span
 * style="color:gray">[&#27748;&#21355;&#24605;</span>], and&nbsp;<a
 * href="http://staff.ustc.edu.cn/~ketang/">Ke Tang</a> <span
 * style="color:gray">[&#21776;&#29634;</span>]: <span
 * style="font-weight:bold">&ldquo;A Study on Scalable Representations for
 * Evolutionary Optimization of Ground Structures,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Evolutionary
 * Computation</span> 20(3):453&ndash;472, Fall&nbsp;2012; published by
 * Cambridge, MA, USA: MIT Press. doi:&nbsp;<a
 * href="http://dx.doi.org/10.1162/EVCO_a_00054">10.1162/EVCO_a_00054</a>;
 * OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/44169764">44169764</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/10636560">1063-6560</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/15309304">1530-9304</a>;
 * PubMed:&nbsp;<a
 * href="https://www.ncbi.nlm.nih.gov/pubmed/22004002">22004002</a>;
 * SCI/WOS:&nbsp;WOS:000306767200005. <div>links: [<a href=
 * "http://www.marmakoide.org/download/publications/devweita-ecj-preprint.pdf"
 * >1</a>] and&nbsp;[<a
 * href="http://www.it-weise.de/documents/files/DWT2011ASOSRFEOOGS.pdf"
 * >2</a>]</div></div></li>
 * <li><div><span id="cite_BK1999TWTGDACOEFAEDP" /><a
 * href="http://peterjbentley.com/">Peter John Bentley</a> and&nbsp;<a
 * href="http://iianalytics.com/iia-faculty/sanjeev-kumar-2/">Sanjeev P.
 * Kumar</a>: <span style="font-weight:bold">&ldquo;The Ways to Grow
 * Designs: A Comparison of Embryogenies for an Evolutionary Design
 * Problem,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the
 * Genetic and Evolutionary Computation Conference (GECCO'99)</span>,
 * July&nbsp;13&ndash;17, 1999, Orlando, FL, USA, pages 35&ndash;43, <a
 * href="http://www.cs.mun.ca/~banzhaf/">Wolfgang Banzhaf</a>, Jason M.
 * Daida, <a href="http://www.cs.vu.nl/~gusz/">&#193;goston E. Eiben</a>
 * aka. Gusz/Guszti, Max H. Garzon, Vasant Honavar, Mark J. Jakiela,
 * and&nbsp;Robert Elliott Smith, editors, San Francisco, CA, USA: Morgan
 * Kaufmann Publishers Inc.. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/1558606114">1-55860-611-4</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9781558606111">978-1-55860
 * -611-1</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/59333111">59333111</a>; Google Book
 * ID:&nbsp;<a
 * href="http://books.google.com/books?id=-1vTAAAACAAJ">-1vTAAAACAAJ</a>.
 * <div>link: [<a
 * href="http://www.cs.ucl.ac.uk/staff/ucacpjb/BEKUC1.pdf">1</a>];
 * CiteSeer<sup>x</sup><sub style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href=
 * "http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.51.1345">10.1
 * .1.51 .1345</a></div></div></li>
 * <li><div><span id="cite_RCON1998GPGE" /><a
 * href="http://www.csis.ul.ie/staff/ConorRyan/">Conor Ryan</a>, <a
 * href="http://www.csis.ul.ie/staff/JJCollins/">John James Collins</a>,
 * and&nbsp;<a href="http://ncra.ucd.ie/members/oneillm.html">Michael
 * O'Neill</a>: <span style="font-weight:bold">&ldquo;Grammatical
 * Evolution: Evolving Programs for an Arbitrary Language,&rdquo;</span> in
 * <span style="font-style:italic;font-family:cursive;">Proceedings of the
 * First European Workshop on Genetic Programming (EuroGP'98)</span>,
 * April&nbsp;14&ndash;15, 1998, Paris, France, pages 83&ndash;95, <a
 * href="http://www.cs.mun.ca/~banzhaf/">Wolfgang Banzhaf</a>, <a
 * href="http://cswww.essex.ac.uk/staff/poli/">Riccardo Poli</a>, <a
 * href="http://www.lri.fr/~marc/">Marc Schoenauer</a>, and&nbsp;Terence
 * Claus Fogarty, editors, volume 1391/1998 of Lecture Notes in Computer
 * Science (LNCS), Berlin, Germany: Springer-Verlag GmbH. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/3540643605">3-540-64360-5</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783540643609">978-3-540-
 * 64360-9</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/03029743">0302-9743</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/16113349">1611-3349</a>. <div>links:
 * [<a
 * href="http://www.grammatical-evolution.org/papers/eurogp98.ps">1</a>]
 * and&nbsp;[<a
 * href="http://citeseer.ist.psu.edu/ryan98grammatical.html">2</a>];
 * CiteSeer<sup>x</sup><sub style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href=
 * "http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.38.7697">10.1
 * .1.38 .7697</a></div></div></li>
 * <li><div><span id="cite_YS1995APSODANNUCE" /><a
 * href="http://www.cs.bham.ac.uk/~xin/">Xin Yao</a> <span
 * style="color:gray">[&#23002;&#26032;</span>] and&nbsp;<a href=
 * "http://www.xjtlu.edu.cn/en/faculty/academic-subject-staff/item/102-yuhui-shi.html"
 * >Yuhui Shi</a> <span
 * style="color:gray">[&#21490;&#29577;&#22238;</span>]: <span
 * style="font-weight:bold">&ldquo;A Preliminary Study on Designing
 * Artificial Neural Networks Using Co-Evolution,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of 1st IEEE
 * Singapore International Conference on Intelligent Control and
 * Instrumentation (SICICI'95)</span>, July&nbsp;2&ndash;8, 1995, pages
 * 149&ndash;154, Singapore: IEEE (Institute of Electrical and Electronics
 * Engineers), IEEE Singapore Section. <div>CiteSeer<sup>x</sup><sub
 * style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.55.6071"
 * >10.1.1.55.6071</a></div></div></li>
 * <li><div><span id="cite_Y1999EANN" /><a
 * href="http://www.cs.bham.ac.uk/~xin/">Xin Yao</a> <span
 * style="color:gray">[&#23002;&#26032;</span>]: <span
 * style="font-weight:bold">&ldquo;Evolving Artificial Neural
 * Networks,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the
 * IEEE</span> 87(9):1423&ndash;1447, September&nbsp;1999; published by New
 * York, NY, USA: Institute of Electrical and Electronics Engineers (IEEE)
 * Press. doi:&nbsp;<a
 * href="http://dx.doi.org/10.1109/5.784219">10.1109/5.784219</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/00189219">0018-9219</a>; INSPEC
 * Accession Number:&nbsp;6350615. <div>CiteSeer<sup>x</sup><sub
 * style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.14.793"
 * >10.1.1.14.793</a></div></div></li>
 * <li><div><span id="cite_D2009BPOTAAOBA" /><a
 * href="http://www.marmakoide.org/">Alexandre Devert</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Building Processes
 * Optimization: Toward an Artificial Ontogeny based
 * Approach,&rdquo;</span> PhD Thesis, May&nbsp;2009, Paris, France:
 * Universit&#233; Paris-Sud, Ecole Doctorale d'Informatique
 * and&nbsp;Orsay, France: Institut National de Recherche en Informatique
 * et en Automatique (INRIA), Centre de Recherche Saclay &#8211;
 * &#206;le-de-France</div></li>
 * </ol>
 *
 * @author <ul>
 *         <li>
 *         <em><a href="mailto:oyjmical@mail.ustc.edu.cn">Jin Ouyang</a></em>
 *         [&#x6B27;&#x9633;&#x664B;]</li>
 *         <li><em><a href="http://www.it-weise.de/">Thomas Weise</a></em>,
 *         Email:&nbsp;<a
 *         href="mailto:tweise@ustc.edu.cn">tweise@ustc.edu.cn</a> (role:
 *         adaption to benchmarking framework)</li>
 *         </ul>
 */
public class DevUpdatingEA extends EA {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the updating operators: {@value} */
  public static final String PARAM_UPDATING_OPERATORS = UpdatingPermutationHillClimber.PARAM_UPDATING_OPERATORS;

  /** the index iterator: {@value} */
  public static final String PARAM_INDEX_ITERATOR = "indexIterator"; //$NON-NLS-1$

  /** the dev steps: {@value} */
  public static final String PARAM_DEV_STEPS = "devStepsPerGPM"; //$NON-NLS-1$

  /** the variable count: {@value} */
  static final int VAR_COUNT = 3;

  /** the update operators @serial serializable field */
  final PermutationUpdateOperator[] m_ops;

  /** the index iterator @serial serializable field */
  IndexIterator m_it;

  /** the number of developmental steps @serial serializable field */
  UnaryFunction m_devSteps;

  /** the functions @serial serializable field */
  private final NodeTypeSet<Function> m_functions;

  /**
   * create the ea
   */
  public DevUpdatingEA() {
    super("Developmentally Updating Evolutionary Algorithm");//$NON-NLS-1$

    this.m_ops = PermutationUpdateOperators.OPERATORS_AND_COMPLEMENT;
    this.m_it = new UniformRandomIndexIterator();
    this.m_devSteps = Identity.INSTANCE;

    this.m_functions = DevUpdatingEA.makeNTS();

    this.setGPM(new DevUpdatingGPM(this));

    this.setNullaryOperator(//
        new TreeRampedHalfAndHalf<>(this.m_functions, 7));

    this.setUnaryOperator(//
        new SubTreeReplacementMutator<>(7));

    this.setBinaryOperator(//
        new SubTreeExchangeRecombination<>(7));
  }

  /**
   * make the node type set
   *
   * @return the node type set
   */
  @SuppressWarnings("unchecked")
  private static final NodeTypeSet<Function> makeNTS() {
    final NodeTypeSet<Function> nts;
    final NodeTypeSet<Function>[] binary, unary, ternary;

    nts = new NodeTypeSet<>();
    binary = new NodeTypeSet[] { nts, nts };
    unary = new NodeTypeSet[] { nts };
    ternary = new NodeTypeSet[] { nts, nts, nts };

    nts.add(new ReflectionNodeType<>(Sin.class, unary));
    nts.add(new ReflectionNodeType<>(Mul.class, binary));
    nts.add(new ReflectionNodeType<>(Angle.class, binary));
    nts.add(new ReflectionNodeType<>(IfThenElse.class, ternary));
    nts.add(new VariableType(DevUpdatingEA.VAR_COUNT));
    return nts;
  }

  /**
   * Perform the developmental updating EA
   *
   * @param args
   *          the command line arguments
   */
  public final static void main(final String[] args) {
    TSPAlgorithmRunner.benchmark(Instance.SYMMETRIC_INSTANCES,
        DevUpdatingEA.class,//
        args);
  }

  /** {@inheritDoc} */
  @Override
  public final DevUpdatingEA clone() {
    DevUpdatingEA cfg;
    GPM<?> g;

    cfg = ((DevUpdatingEA) (super.clone()));

    // cfg.m_devSteps = cfg.m_devSteps.clone();
    cfg.m_it = ((IndexIterator) (cfg.m_it.clone()));

    g = cfg.getGPM();
    if (g instanceof DevUpdatingGPM) {
      ((DevUpdatingGPM) g).m_cfg = cfg;
    }

    return cfg;
  }

  /** {@inheritDoc} */
  @Override
  public void printConfiguration(final PrintStream ps) {
    super.printConfiguration(ps);

    Configurable.printKey(DevUpdatingEA.PARAM_UPDATING_OPERATORS, ps);
    Configurable.printlnObject(this.m_ops, ps);

    Configurable.printKey(DevUpdatingEA.PARAM_INDEX_ITERATOR, ps);
    Configurable.printlnObject(this.m_it, ps);

    Configurable.printKey(DevUpdatingEA.PARAM_DEV_STEPS, ps);
    Configurable.printlnObject(this.m_devSteps, ps);

    this.m_functions.printConfiguration(ps);
  }
}
