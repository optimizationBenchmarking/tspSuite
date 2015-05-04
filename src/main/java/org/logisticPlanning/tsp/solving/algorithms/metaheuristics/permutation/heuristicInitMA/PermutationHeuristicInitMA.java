package org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA;

import java.io.PrintStream;

import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;
import org.logisticPlanning.tsp.solving.Individual;
import org.logisticPlanning.tsp.solving.TSPModule;
import org.logisticPlanning.tsp.solving.algorithms.heuristics.TSPHeuristic;
import org.logisticPlanning.tsp.solving.algorithms.heuristics.TSPHeuristicWithStartNode;
import org.logisticPlanning.tsp.solving.algorithms.heuristics.doubleEndedNearestNeighbor.DoubleEndedNearestNeighborHeuristic;
import org.logisticPlanning.tsp.solving.algorithms.heuristics.edgeGreedy.EdgeGreedyHeuristic;
import org.logisticPlanning.tsp.solving.algorithms.heuristics.mst.MSTHeuristic;
import org.logisticPlanning.tsp.solving.algorithms.heuristics.nearestNeighbor.NearestNeighborHeuristic;
import org.logisticPlanning.tsp.solving.algorithms.heuristics.savings.SavingsHeuristic;
import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.general.ea.EA;
import org.logisticPlanning.tsp.solving.operators.BinaryOperator;
import org.logisticPlanning.tsp.solving.operators.BinaryOperatorFollowedByUnary;
import org.logisticPlanning.tsp.solving.operators.UnaryOperator;
import org.logisticPlanning.tsp.solving.utils.NodeManager;
import org.logisticPlanning.utils.config.Configurable;
import org.logisticPlanning.utils.config.Configuration;
import org.logisticPlanning.utils.math.random.Randomizer;

/**
 *
 <p>
 * A Memetic Algorithm (MA)&nbsp;[<a href="#cite_M1989MA"
 * style="font-weight:bold">1</a>, <a href="#cite_M2002MA"
 * style="font-weight:bold">2</a>, <a href="#cite_MC2003AGITMA"
 * style="font-weight:bold">3</a>, <a href="#cite_ES2003HWOTMA"
 * style="font-weight:bold">4</a>, <a href="#cite_HKS2005RAIMA"
 * style="font-weight:bold">5</a>, <a href="#cite_DM2004MA"
 * style="font-weight:bold">6</a>, <a href="#cite_RS1994FMA"
 * style="font-weight:bold">7</a>] for solving TSPs in permutation
 * representation.
 * </p>
 * <p>
 * This algorithm has the following key characteristics:
 * </p>
 * <ol>
 * <li>
 * <p>
 * An invariant of this MA is that each individual in the population is
 * always the result of a <a href="#localSearch">local search</a>
 * procedure.
 * </p>
 * </li>
 * <li>
 * <p>
 * The population in the first generation is initialized using heuristic
 * procedures such as
 * </p>
 * <ol>
 * <li>the
 * {@link org.logisticPlanning.tsp.solving.algorithms.heuristics.mst.MSTHeuristic
 * double minimum spanning tree method},</li>
 * <li>the
 * {@link org.logisticPlanning.tsp.solving.algorithms.heuristics.edgeGreedy.EdgeGreedyHeuristic
 * edge greedy method},</li>
 * <li>the
 * {@link org.logisticPlanning.tsp.solving.algorithms.heuristics.savings.SavingsHeuristic
 * savings heuristic},</li>
 * <li>the
 * {@link org.logisticPlanning.tsp.solving.algorithms.heuristics.doubleEndedNearestNeighbor.DoubleEndedNearestNeighborHeuristic
 * double-ended nearest neighbor heuristic}, and</li>
 * <li>the
 * {@link org.logisticPlanning.tsp.solving.algorithms.heuristics.nearestNeighbor.NearestNeighborHeuristic
 * nearest neighbor heuristic}.</li>
 * </ol>
 * <p>
 * The first two heuristics each contribute one solution to the initial
 * population. The latter three heuristics are instances of
 * {@link org.logisticPlanning.tsp.solving.algorithms.heuristics.TSPHeuristicWithStartNode}
 * , which can potentially produce up to {@code n} different solutions in
 * {@code n}-node TSPs. Therefore, we use these to fill up the population
 * and try to invoke them with different, random initial nodes. This should
 * lead to initial population which is diverse and still contains
 * reasonably good solutions.
 * </p>
 * <p>
 * Additionally, directly after an individual is created with any of the
 * above heuristics, it is refined with a <a href="#localSearch">local
 * search</a>.
 * </p>
 * </li>
 * <li>
 * <p>
 * The MA has a 100% crossover rate, but after each <a
 * href="#crossover">crossover</a>, we apply mutation / <a
 * href="#localSearch">local search</a> to each discovered solution. In
 * other words, the each individual in the offspring generation is created
 * by applying a crossover operation to combine two individuals from the
 * parent generation. It is then immediately refined with a local search
 * method before anything else happens. This is achieved by using the class
 * {@link org.logisticPlanning.tsp.solving.operators.BinaryOperatorFollowedByUnary
 * BinaryOperatorFollowedByUnary}.
 * </p>
 * </li>
 * <li id="localSearch">
 * <p>
 * A local search operator is applied after each crossover operation. It
 * takes the place of a mutation operation.
 * </p>
 * </li>
 * <li id="crossover">A crossover operation combines two parent individuals
 * to an offspring individual.</p></li>
 * <li>
 * <p>
 * There may be an additional
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.general.ea.FitnessAssignmentProcess
 * fitness assignment process} or
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.general.ea.SelectionAlgorithm
 * selection algorithm}.
 * <p></li>
 * </ol>
 * <table border="1">
 * <tr>
 * <td/>
 * <th colspan="2">
 * {@link org.logisticPlanning.tsp.solving.operators.permutation.recombination.PermutationEdgeCrossover
 * Edge Recombination}</th>
 * <th colspan="2>
 * {@link org.logisticPlanning.tsp.solving.operators.permutation.recombination.PermutationSavingsCrossover
 * Savings Crossover}</th>
 * </tr>
 * <tr>
 * <td/>
 * <th>
 * {@link org.logisticPlanning.tsp.solving.operators.permutation.mutation.PermutationMultiNeighborhoodMutation
 * MNS}</th>
 * <th>
 * {@link org.logisticPlanning.tsp.solving.operators.permutation.mutation.PermutationRNSMutation
 * RNS}</th>
 * <th>
 * {@link org.logisticPlanning.tsp.solving.operators.permutation.mutation.PermutationVNSMutation
 * VNS}</th>
 * <th>
 * {@link org.logisticPlanning.tsp.solving.operators.permutation.mutation.PermutationMultiNeighborhoodMutation
 * MNS}</th>
 * <th>
 * {@link org.logisticPlanning.tsp.solving.operators.permutation.mutation.PermutationRNSMutation
 * RNS}</th>
 * <th>
 * {@link org.logisticPlanning.tsp.solving.operators.permutation.mutation.PermutationVNSMutation
 * VNS}</th>
 * </tr>
 * <tr>
 * <th>plain</th>
 * <td>
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA.HeuristicInitMultiNeighborhoodEdgeMA
 * X}</td>
 * <td>
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA.HeuristicInitRNSEdgeMA
 * X}</td>
 * <td>
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA.HeuristicInitVNSEdgeMA
 * X}</td>
 * <td>
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA.HeuristicInitMultiNeighborhoodSavingsMA
 * X}</td>
 * <td>
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA.HeuristicInitRNSSavingsMA
 * X}</td>
 * <td>
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA.HeuristicInitVNSSavingsMA
 * X}</td>
 * </tr>
 * <tr>
 * <th>with
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.general.ea.fitness.FFA
 * FFA}</th>
 * <td>
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA.HeuristicInitMultiNeighborhoodEdgeMAFFA
 * X}</td>
 * <td>
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA.HeuristicInitRNSEdgeMAFFA
 * X}</td>
 * <td>
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA.HeuristicInitVNSEdgeMAFFA
 * X}</td>
 * <td>
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA.HeuristicInitMultiNeighborhoodSavingsMAFFA
 * X}</td>
 * <td>
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA.HeuristicInitRNSSavingsMAFFA
 * X}</td>
 * <td>
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA.HeuristicInitVNSSavingsMAFFA
 * X}</td>
 * </tr>
 * <tr>
 * <th>with
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.general.ea.selection.RandomSelection
 * Random Selection}</th>
 * <td>
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA.HeuristicInitMultiNeighborhoodEdgeMARandomSelection
 * X}</td>
 * <td>
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA.HeuristicInitRNSEdgeMARandomSelection
 * X}</td>
 * <td>
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA.HeuristicInitVNSEdgeMARandomSelection
 * X}</td>
 * <td>
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA.HeuristicInitMultiNeighborhoodSavingsMARandomSelection
 * X}</td>
 * <td>
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA.HeuristicInitRNSSavingsMARandomSelection
 * X}</td>
 * <td>
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA.HeuristicInitVNSSavingsMARandomSelection
 * X}</td>
 * </tr>
 * <tr>
 * <th>with
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.general.ea.selection.FitnessUniformSelection
 * Fitness Uniform Selection}</th>
 * <td>
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA.HeuristicInitMultiNeighborhoodEdgeMAFUSS
 * X}</td>
 * <td>
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA.HeuristicInitRNSEdgeMAFUSS
 * X}</td>
 * <td>
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA.HeuristicInitVNSEdgeMAFUSS
 * X}</td>
 * <td>
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA.HeuristicInitMultiNeighborhoodSavingsMAFUSS
 * X}</td>
 * <td>
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA.HeuristicInitRNSSavingsMAFUSS
 * X}</td>
 * <td>
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA.HeuristicInitVNSSavingsMAFUSS
 * X}</td>
 * </tr>
 * </table>
 * <h2>References</h2>
 * <ol>
 * <li><div><span id="cite_M1989MA" /><a href=
 * "http://www.newcastle.edu.au/staff/research-profile/Pablo_Moscato/"
 * >Pablo Moscato</a>: <span style="font-weight:bold">&ldquo;On Evolution,
 * Search, Optimization, Genetic Algorithms and Martial Arts: Towards
 * Memetic Algorithms,&rdquo;</span> <span
 * style="font-style:italic;font-family:cursive;">Technical Report</span>
 * Number&nbsp;C3P 826, 1989; published by Pasadena, CA, USA: California
 * Institute of Technology (Caltech), Caltech Concurrent Computation
 * Program (C3P). <div>links: [<a
 * href="http://www.densis.fee.unicamp.br/~moscato/papers/bigone.ps">1</a>]
 * and&nbsp;[<a href=
 * "http://www.each.usp.br/sarajane/SubPaginas/arquivos_aulas_IA/memetic.pdf"
 * >2</a>]; CiteSeer<sup>x</sup><sub
 * style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.27.9474"
 * >10.1.1.27.9474</a></div></div></li>
 * <li><div><span id="cite_M2002MA" /><a href=
 * "http://www.newcastle.edu.au/staff/research-profile/Pablo_Moscato/"
 * >Pablo Moscato</a>: <span style="font-weight:bold">&ldquo;Memetic
 * Algorithms,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Handbook of Applied
 * Optimization</span>, chapter 157&ndash;167, pages 157&ndash;167, Panos
 * M. Pardalos and&nbsp;Mauricio G.C. Resende, editors, New York, NY, USA:
 * Oxford University Press, Inc.. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0195125940">0-19-512594-0</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780195125948">978-0-19-512594
 * -8</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/45532495">45532495</a></div></li>
 * <li><div><span id="cite_MC2003AGITMA" /><a href=
 * "http://www.newcastle.edu.au/staff/research-profile/Pablo_Moscato/"
 * >Pablo Moscato</a> and&nbsp;<a
 * href="http://www.lcc.uma.es/~ccottap/">Carlos Cotta</a>: <span
 * style="font-weight:bold">&ldquo;A Gentle Introduction to Memetic
 * Algorithms,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Handbook of
 * Metaheuristics</span>, chapter 105&ndash;144, pages 105&ndash;144, <a
 * href="http://en.wikipedia.org/wiki/Fred_W._Glover">Fred W. Glover</a>
 * and&nbsp;<a href=
 * "http://www.ucdenver.edu/academics/colleges/business/Faculty-Research/FacultyDirectory/Pages/Gary-Kochenberger.aspx"
 * >Gary A. Kochenberger</a>, editors, volume 57 of International Series in
 * Operations Research &amp; Management Science, Norwell, MA, USA: Kluwer
 * Academic Publishers, Dordrecht, Netherlands: Springer Netherlands,
 * and&nbsp;Boston, MA, USA: Springer US. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/1402072635">1-4020-7263-5</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780306480560">978-0-306-
 * 48056-0</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/0-306-48056-5_5"
 * >10.1007/0-306-48056-5_5</a>; Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=O_10T_KeqOgC">O_10T_KeqOgC</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/08848289">0884-8289</a>. <div>link:
 * [<a
 * href="http://www.lcc.uma.es/~ccottap/papers/handbook03memetic.pdf">1<
 * /a>]; CiteSeer<sup>x</sup><sub
 * style="font-style:italic">&#946;</sub>:&nbsp;<a href=
 * "http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.77.5300"
 * >10.1.1.77 .5300</a></div></div></li>
 * <li><div><span id="cite_ES2003HWOTMA" /><a
 * href="http://www.cs.vu.nl/~gusz/">&#193;goston E. Eiben</a> aka.
 * Gusz/Guszti and&nbsp;<a href="http://www.cems.uwe.ac.uk/~jsmith/">James
 * E. Smith</a>: <span style="font-weight:bold">&ldquo;Hybridisation with
 * other Techniques: Memetic Algorithms,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Introduction to
 * Evolutionary Computing</span>, chapter 173&ndash;188, pages
 * 173&ndash;188, Natural Computing Series, New York, NY, USA: Springer New
 * York. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/3540401849">3540401849</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783540401841">978-3540401841</a>;
 * Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=7IOE5VIpFpwC">7IOE5VIpFpwC</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/16197127">1619-7127</a></div></li>
 * <li><div><span id="cite_HKS2005RAIMA" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;Recent Advances in
 * Memetic Algorithms,&rdquo;</span> 2005, <a
 * href="http://www.cs.sandia.gov/~wehart/Main/Home.html">William Eugene
 * Hart</a>, <a href="http://www.cs.nott.ac.uk/~nxk/">Natalio
 * Krasnogor</a>, and&nbsp;<a
 * href="http://www.cems.uwe.ac.uk/~jsmith/">James E. Smith</a>, editors,
 * volume 166/2005 of Studies in Fuzziness and Soft Computing, Berlin,
 * Germany: Springer-Verlag GmbH. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/3540229043">3-540-22904-3</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783540229049">978-3-540-
 * 22904-9</a>; LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/2004111139">2004111139</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/3-540-32363-5"
 * >10.1007/3-540-32363-5</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/318297267">318297267</a> and&nbsp;<a
 * href="https://www.worldcat.org/oclc/56697114">56697114</a>; Google Book
 * ID:&nbsp;<a
 * href="http://books.google.com/books?id=LYf7YW4DmkUC">LYf7YW4DmkUC</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/14349922">1434-9922</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/18600808">1860-0808</a></div></li>
 * <li><div><span id="cite_DM2004MA" />Jason Digalakis
 * and&nbsp;Konstantinos Margaritis: <span
 * style="font-weight:bold">&ldquo;Performance Comparison of Memetic
 * Algorithms,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Journal of Applied
 * Mathematics and Computation</span> 158:237&ndash;252, October&nbsp;2004;
 * published by Essex, UK: Elsevier Science Publishers B.V.. doi:&nbsp;<a
 * href="http://dx.doi.org/10.1016/j.amc.2003.08.115"
 * >10.1016/j.amc.2003.08.115</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/00963003">0096-3003</a>;
 * CODEN:&nbsp;<a href=
 * "http://www-library.desy.de/cgi-bin/spiface/find/coden/www?code=AMHCBQ">
 * AMHCBQ</a>. <div>links: [<a
 * href="http://citeseer.ist.psu.edu/458892.html">1</a>], [<a href=
 * "http://www.complexity.org.au/ci/draft/draft/digala02/digala02s.pdf"
 * >2</a>], and&nbsp;[<a
 * href="http://www.complexity.org.au/ci/vol10/digala02/digala02s.pdf"
 * >3</a>]; CiteSeer<sup>x</sup><sub
 * style="font-style:italic">&#946;</sub>:&nbsp;<a href=
 * "http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.21.5495"
 * >10.1.1.21 .5495</a></div></div></li>
 * <li><div><span id="cite_RS1994FMA" /><a
 * href="http://users.breathe.com/njr/">Nicholas J. Radcliffe</a>
 * and&nbsp;<a href="http://www.linkedin.com/in/patricksurry">Patrick David
 * Surry</a>: <span style="font-weight:bold">&ldquo;Formal Memetic
 * Algorithms,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the
 * Workshop on Artificial Intelligence and Simulation of Behaviour,
 * International Workshop on Evolutionary Computing, Selected Papers
 * (AISB'94)</span>, April&nbsp;11&ndash;13, 1994, Leeds, UK, pages
 * 1&ndash;16, Terence Claus Fogarty, editor, volume 865/1994 of Lecture
 * Notes in Computer Science (LNCS), Berlin, Germany: Springer-Verlag GmbH
 * and&nbsp;Chichester, West Sussex, UK: Society for the Study of
 * Artificial Intelligence and the Simulation of Behaviour (SSAISB).
 * ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/3540584838">3-540-58483-8</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783540584834">978-3-540-
 * 58483-4</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/3-540-58483-8_1"
 * >10.1007/3-540-58483-8_1</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/03029743">0302-9743</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/16113349">1611-3349</a>;
 * PPN:&nbsp;<a
 * href="http://gso.gbv.de/PPNSET?PPN=59512478X">59512478X</a>.
 * <div>CiteSeer<sup>x</sup><sub
 * style="font-style:italic">&#946;</sub>:&nbsp;<a href
 * ="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.38.9885"
 * >10.1.1.38 .9885</a></div></div></li>
 * </ol>
 */
public abstract class PermutationHeuristicInitMA extends EA {
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
   * Create the permutation MA.
   *
   * @param name
   *          the algorithm's name
   */
  protected PermutationHeuristicInitMA(final String name) {
    super(name);

    final UnaryOperator<int[]> u;
    int i;

    u = this.createUnary();

    this.setUnaryOperator(u);
    this.setBinaryOperator(//
        new BinaryOperatorFollowedByUnary<>(//
            this.createBinary(),//
            u));//
    this.setCrossoverRate(1d);

    this.m_initOnce = new TSPHeuristic[i = PermutationHeuristicInitMA.INIT_ONCE.length];
    for (; (--i) >= 0;) {
      try {
        this.m_initOnce[i] = PermutationHeuristicInitMA.INIT_ONCE[i]
            .newInstance();
      } catch (final Throwable t) {
        throw new RuntimeException(t);
      }
    }

    this.m_initMulti = new TSPHeuristicWithStartNode[i = PermutationHeuristicInitMA.INIT_MULTI.length];
    for (; (--i) >= 0;) {
      try {
        this.m_initMulti[i] = PermutationHeuristicInitMA.INIT_MULTI[i]
            .newInstance();
      } catch (final Throwable t) {
        throw new RuntimeException(t);
      }
    }
  }

  /**
   * Create the binary operator to be used
   *
   * @return the binary operator to be used
   */
  protected abstract BinaryOperator<int[]> createBinary();

  /**
   * Create the unary operator to be used
   *
   * @return the unary operator to be used
   */
  protected abstract UnaryOperator<int[]> createUnary();

  /** {@inheritDoc} */
  @Override
  @SuppressWarnings("unchecked")
  protected void createFirstGeneration(final Individual<Object>[] pop,
      final ObjectiveFunction f) {
    int i;
    final int n;
    final Randomizer r;
    final UnaryOperator<int[]> op;
    final NodeManager m;
    Individual<int[]> indi, temp;

    n = f.n();
    m = this.m_nodes;
    m.init(f.n());
    r = f.getRandom();

    temp = new Individual<>();
    op = ((BinaryOperatorFollowedByUnary<int[]>) (((Object) (//
        this.getBinaryOperator())))).getUnaryOperator();//

    for (i = pop.length; (--i) >= 0;) {
      if (f.shouldTerminate()) {
        return;
      }

      if (i >= this.m_initOnce.length) {
        if (m.size() <= 0) {
          m.init(n);
        }
        this.m_initMulti[r.nextInt(this.m_initMulti.length)].solve(f,
            temp, m.deleteRandom(r));
      } else {
        this.m_initOnce[i].solve(f, temp);
      }

      indi = new Individual<>();
      op.mutate(indi, f, temp);
      pop[i] = ((Individual<Object>) ((Object) indi));
    }
  }

  /** {@inheritDoc} */
  @Override
  public PermutationHeuristicInitMA clone() {
    PermutationHeuristicInitMA cfg;
    int i;

    cfg = ((PermutationHeuristicInitMA) (super.clone()));

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

    if (this.getUnaryOperator() == //
        ((BinaryOperatorFollowedByUnary<Object>) (this.getBinaryOperator())).//
        getUnaryOperator()) {//
      cfg.setUnaryOperator(//
          ((BinaryOperatorFollowedByUnary<Object>) (cfg.getBinaryOperator())).//
          getUnaryOperator());
    }

    return cfg;
  }

  /** {@inheritDoc} */
  @Override
  public void printConfiguration(final PrintStream ps) {
    super.printConfiguration(ps);

    Configurable.printKey(PermutationHeuristicInitMA.PARAM_INIT_ONCE, ps);
    Configurable.printlnObject(this.m_initOnce, ps);

    Configurable.printKey(PermutationHeuristicInitMA.PARAM_INIT_MULTI, ps);
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
