package org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.ea;

import java.io.PrintStream;

import org.logisticPlanning.tsp.benchmarking.instances.Instance;
import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;
import org.logisticPlanning.tsp.solving.Individual;
import org.logisticPlanning.tsp.solving.TSPAlgorithmRunner;
import org.logisticPlanning.tsp.solving.TSPModule;
import org.logisticPlanning.tsp.solving.algorithms.heuristics.TSPHeuristic;
import org.logisticPlanning.tsp.solving.algorithms.heuristics.TSPHeuristicWithStartNode;
import org.logisticPlanning.tsp.solving.algorithms.heuristics.doubleEndedNearestNeighbor.DoubleEndedNearestNeighborHeuristic;
import org.logisticPlanning.tsp.solving.algorithms.heuristics.edgeGreedy.EdgeGreedyHeuristic;
import org.logisticPlanning.tsp.solving.algorithms.heuristics.mst.MSTHeuristic;
import org.logisticPlanning.tsp.solving.algorithms.heuristics.nearestNeighbor.NearestNeighborHeuristic;
import org.logisticPlanning.tsp.solving.algorithms.heuristics.savings.SavingsHeuristic;
import org.logisticPlanning.tsp.solving.utils.NodeManager;
import org.logisticPlanning.utils.config.Configurable;
import org.logisticPlanning.utils.config.Configuration;
import org.logisticPlanning.utils.math.random.Randomizer;

/**
 * <p>
 * An <a href="http://en.wikipedia.org/wiki/Evolutionary_algorithm">
 * evolutionary algorithm</a>&nbsp;[<a href="#cite_WGOEB"
 * style="font-weight:bold">1</a>, <a href="#cite_CWM2011VOEAFRWA"
 * style="font-weight:bold">2</a>, <a href="#cite_DJ2006ECAUA"
 * style="font-weight:bold">3</a>, <a href="#cite_BFM1997EA"
 * style="font-weight:bold">4</a>, <a href="#cite_SMY2002EO"
 * style="font-weight:bold">5</a>] that works directly on permutations and
 * is initialized with heuristic methods, based on the
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.ea.PermutationEA
 * PermutationEA}.
 * </p>
 * <h2>References</h2>
 * <ol>
 * <li><div><span id="cite_WGOEB" /><a
 * href="http://www.it-weise.de/">Thomas Weise</a> <span
 * style="color:gray">[&#27748;&#21355;&#24605;</span>]: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Global
 * Optimization Algorithms &#8210; Theory and Application,&rdquo;</span>
 * 2009, Germany: it-weise.de (self-published). <div>link: [<a
 * href="http://www.it-weise.de/projects/book.pdf">1</a>]</div></div></li>
 * <li><div><span id="cite_CWM2011VOEAFRWA" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;Variants of
 * Evolutionary Algorithms for Real-World Applications,&rdquo;</span>
 * September&nbsp;30, 2011&ndash;2012, <a
 * href="http://www.swinburne.edu.my/iSECURES/index.php?do=rchiong">Raymond
 * Chiong</a>, <a href="http://www.it-weise.de/">Thomas Weise</a> <span
 * style="color:gray">[&#27748;&#21355;&#24605;</span>], and&nbsp;<a
 * href="http://cs.adelaide.edu.au/~zbyszek/">Zbigniew Michalewicz</a>,
 * editors, Berlin/Heidelberg: Springer-Verlag. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783642234231">978-3-642-23423-1</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783642234248">978-3-642-
 * 23424-8</a>; LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/2011935740">2011935740</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/978-3-642-23424-8">10.1007/978-3-
 * 642-23424-8</a>; Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=B2ONePP40MEC">B2ONePP40MEC</a>;
 * further information: [<a
 * href="http://www.it-weise.de/documents/files/ea-app-book/index.html"
 * >1</a>]</div></li>
 * <li><div><span id="cite_DJ2006ECAUA" /><a
 * href="http://cs.gmu.edu/~kdejong/">Kenneth Alan De Jong</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Evolutionary
 * Computation: A Unified Approach,&rdquo;</span> February&nbsp;2006,
 * volume 4 of Complex Adaptive Systems, Bradford Books, Cambridge, MA,
 * USA: MIT Press. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/8120330021">8120330021</a>, <a
 * href="https://www.worldcat.org/isbn/0262041944">0262041944</a>, <a
 * href="https://www.worldcat.org/isbn/9780262041942">978-0262041942</a>,
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9788120330023">978-8120330023</a>;
 * OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/276452339">276452339</a>, <a
 * href="https://www.worldcat.org/oclc/46500047">46500047</a>, and&nbsp;<a
 * href="https://www.worldcat.org/oclc/182530408">182530408</a>; Google
 * Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=OIRQAAAAMAAJ">OIRQAAAAMAAJ</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/10894365">1089-4365</a>. <div>link:
 * [<a href=
 * "http://www.inf.ufg.br/~telma/topicoscomputacaoevolutiva/EvolutionaryComputation.pdf"
 * >1</a>]</div></div></li>
 * <li><div><span id="cite_BFM1997EA" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;Handbook of
 * Evolutionary Computation,&rdquo;</span> January&nbsp;1, 1997, <a
 * href="http://www.liacs.nl/~baeck/contact.htm">Thomas B&#228;ck</a>, <a
 * href="http://www.natural-selection.com/people_dfogel.html">David B.
 * Fogel</a>, and&nbsp;<a
 * href="http://cs.adelaide.edu.au/~zbyszek/">Zbigniew Michalewicz</a>,
 * editors, Computational Intelligence Library, New York, NY, USA: Oxford
 * University Press, Inc., Dirac House, Temple Back, Bristol, UK: Institute
 * of Physics Publishing Ltd. (IOP), and&nbsp;Boca Raton, FL, USA: CRC
 * Press, Inc.. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0750308958">0-7503-0895-8</a>, <a
 * href="https://www.worldcat.org/isbn/0750303921">0-7503-0392-1</a>, <a
 * href
 * ="https://www.worldcat.org/isbn/9780750308953">978-0-7503-0895-3</a>,
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780750303927">978-0-7503
 * -0392-7</a>; LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/97004461">97004461</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/327018351">327018351</a>, <a
 * href="https://www.worldcat.org/oclc/173074676">173074676</a>,
 * and&nbsp;<a
 * href="https://www.worldcat.org/oclc/327018351">327018351</a>; Google
 * Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=n5nuiIZvmpAC">n5nuiIZvmpAC</a>,
 * <a
 * href="http://books.google.com/books?id=neKNGAAACAAJ">neKNGAAACAAJ</a>,
 * and&nbsp;<a
 * href="http://books.google.com/books?id=kgqGQgAACAAJ">kgqGQgAACAAJ</a>;
 * PPN:&nbsp;<a href="http://gso.gbv.de/PPNSET?PPN=224708430">224708430</a>
 * and&nbsp;<a
 * href="http://gso.gbv.de/PPNSET?PPN=364589272">364589272</a></div></li>
 * <li><div><span id="cite_SMY2002EO" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;Evolutionary
 * Optimization,&rdquo;</span> 2002, <a
 * href="http://seit.unsw.adfa.edu.au/staff/sites/rsarker/">Ruhul Amin
 * Sarker</a>, <a href=
 * "http://www.canberra.edu.au/faculties/ise/research/staff/masoud-mohammadian"
 * >Masoud Mohammadian</a>, and&nbsp;<a
 * href="http://www.cs.bham.ac.uk/~xin/">Xin Yao</a> <span
 * style="color:gray">[&#23002;&#26032;</span>], editors, volume 48 of
 * International Series in Operations Research &amp; Management Science,
 * Norwell, MA, USA: Kluwer Academic Publishers, Dordrecht, Netherlands:
 * Springer Netherlands, and&nbsp;Boston, MA, USA: Springer US.
 * ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0306480417">0-306-48041-7</a>, <a
 * href="https://www.worldcat.org/isbn/0792376544">0-7923-7654-4</a>,
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780792376545">978-0-7923
 * -7654-5</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/b101816">10.1007/b101816</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/08848289">0884-8289</a></div></li>
 * </ol>
 */
public class HeuristicInitPermutationEA extends PermutationEA {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the one-time initializers: {@value} */
  public static final String PARAM_INIT_ONCE = "oneTimeInitializers"; //$NON-NLS-1$

  /** the one-time rest: {@value} */
  public static final String PARAM_INIT_MULTI = "multiInitializers"; //$NON-NLS-1$

  /** the heuristics which are used exactly once */
  @SuppressWarnings("unchecked")
  private static final Class<? extends TSPHeuristic>[] INIT_ONCE = new Class[] {
      MSTHeuristic.class, EdgeGreedyHeuristic.class };

  /** the heuristics which are used exactly once */
  @SuppressWarnings("unchecked")
  private static final Class<? extends TSPHeuristicWithStartNode>[] INIT_MULTI = new Class[] {
      SavingsHeuristic.class, DoubleEndedNearestNeighborHeuristic.class,
      NearestNeighborHeuristic.class };

  /**
   * the heuristics used for initialization
   * 
   * @serial an array of non-null instances
   */
  private TSPHeuristic[] m_initOnce;

  /**
   * the multi-times heuristic
   * 
   * @serial an array of non-null instances
   */
  private TSPHeuristicWithStartNode[] m_initMulti;

  /** the node manager */
  private transient NodeManager m_nodes;

  /**
   * create the ea
   */
  public HeuristicInitPermutationEA() {
    super("Heuristic Init");//$NON-NLS-1$

    int i;

    this.m_initOnce = new TSPHeuristic[i = HeuristicInitPermutationEA.INIT_ONCE.length];
    for (; (--i) >= 0;) {
      try {
        this.m_initOnce[i] = HeuristicInitPermutationEA.INIT_ONCE[i]
            .newInstance();
      } catch (final Throwable t) {
        throw new RuntimeException(t);
      }
    }

    this.m_initMulti = new TSPHeuristicWithStartNode[i = HeuristicInitPermutationEA.INIT_MULTI.length];
    for (; (--i) >= 0;) {
      try {
        this.m_initMulti[i] = HeuristicInitPermutationEA.INIT_MULTI[i]
            .newInstance();
      } catch (final Throwable t) {
        throw new RuntimeException(t);
      }
    }
  }

  /** {@inheritDoc} */
  @Override
  @SuppressWarnings("unchecked")
  protected void createFirstGeneration(final Individual<Object>[] pop,
      final ObjectiveFunction f) {
    int i;
    final int n;
    final Randomizer r;
    NodeManager m;
    Individual<int[]> indi;

    n = f.n();
    m = this.m_nodes;
    m.init(f.n());
    r = f.getRandom();

    for (i = pop.length; (--i) >= 0;) {
      if (f.shouldTerminate()) {
        return;
      }

      indi = new Individual<>();

      if (i >= this.m_initOnce.length) {
        if (m.size() <= 0) {
          m.init(n);
        }
        this.m_initMulti[r.nextInt(this.m_initMulti.length)].solve(f,
            indi, m.deleteRandom(r));
      } else {
        this.m_initOnce[i].solve(f, indi);
      }

      pop[i] = ((Individual<Object>) ((Object) indi));
    }
  }

  /** {@inheritDoc} */
  @Override
  public HeuristicInitPermutationEA clone() {
    HeuristicInitPermutationEA cfg;
    int i;

    cfg = ((HeuristicInitPermutationEA) (super.clone()));

    cfg.m_nodes = null;

    cfg.m_initOnce = cfg.m_initOnce.clone();
    for (i = cfg.m_initOnce.length; (--i) >= 0;) {
      cfg.m_initOnce[i] = ((TSPHeuristic) (cfg.m_initOnce[i].clone()));
    }

    cfg.m_initMulti = cfg.m_initMulti.clone();
    for (i = cfg.m_initMulti.length; (--i) >= 0;) {
      cfg.m_initMulti[i] = ((TSPHeuristicWithStartNode) (cfg.m_initMulti[i]
          .clone()));
    }

    return cfg;
  }

  /** {@inheritDoc} */
  @Override
  public void printConfiguration(final PrintStream ps) {
    super.printConfiguration(ps);

    Configurable.printKey(HeuristicInitPermutationEA.PARAM_INIT_ONCE, ps);
    Configurable.printlnObject(this.m_initOnce, ps);

    Configurable.printKey(HeuristicInitPermutationEA.PARAM_INIT_MULTI, ps);
    Configurable.printlnObject(this.m_initMulti, ps);
  }

  /** {@inheritDoc} */
  @Override
  public void printParameters(final PrintStream ps) {
    super.printParameters(ps);

    for (final TSPHeuristic o : this.m_initOnce) {
      o.printParameters(ps);
    }

    for (final TSPHeuristic m : this.m_initMulti) {
      m.printParameters(ps);
    }
  }

  /** {@inheritDoc} */
  @Override
  public void configure(final Configuration config) {
    super.configure(config);

    for (final TSPHeuristic o : this.m_initOnce) {
      o.configure(config);
    }

    for (final TSPHeuristic m : this.m_initMulti) {
      m.configure(config);
    }
  }

  /**
   * Apply the evolutionary algorithm to all symmetric TSPLib instances.
   * 
   * @param args
   *          the command line arguments
   */
  public static void main(final String[] args) {
    TSPAlgorithmRunner.benchmark(Instance.SYMMETRIC_INSTANCES,
        HeuristicInitPermutationEA.class,//
        args);
  }

  /** {@inheritDoc} */
  @Override
  public void beginRun(final ObjectiveFunction f) {
    super.beginRun(f);
    TSPModule.invokeBeginRun(f, this.m_initOnce);
    TSPModule.invokeBeginRun(f, this.m_initMulti);
    this.m_nodes = new NodeManager();
  }

  /** {@inheritDoc} */
  @Override
  public void endRun(final ObjectiveFunction f) {
    this.m_nodes = null;
    try {
      TSPModule.invokeEndRun(f, this.m_initMulti);
    } finally {
      try {
        TSPModule.invokeEndRun(f, this.m_initOnce);
      } finally {
        super.endRun(f);
      }
    }
  }
}
