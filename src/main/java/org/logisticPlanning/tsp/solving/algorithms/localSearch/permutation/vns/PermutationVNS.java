package org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.vns;

import java.io.PrintStream;

import org.logisticPlanning.tsp.benchmarking.instances.Instance;
import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;
import org.logisticPlanning.tsp.solving.Individual;
import org.logisticPlanning.tsp.solving.TSPAlgorithmRunner;
import org.logisticPlanning.tsp.solving.TSPModule;
import org.logisticPlanning.tsp.solving.algorithms.localSearch.TSPLocalSearchAlgorithm;
import org.logisticPlanning.tsp.solving.operators.permutation.update.PermutationUpdateOperator;
import org.logisticPlanning.tsp.solving.operators.permutation.update.PermutationUpdate_Reverse;
import org.logisticPlanning.tsp.solving.operators.permutation.update.PermutationUpdate_Rotate_Left;
import org.logisticPlanning.tsp.solving.operators.permutation.update.PermutationUpdate_Rotate_Right;
import org.logisticPlanning.tsp.solving.operators.permutation.update.PermutationUpdate_Swap;
import org.logisticPlanning.utils.config.Configurable;
import org.logisticPlanning.utils.config.Configuration;
import org.logisticPlanning.utils.math.random.Randomizer;

/**
 * <p>
 * This class defines a Variable Neighborhood Search (<a
 * href="http://en.wikipedia.org/wiki/Variable_Neighborhood_Search"
 * >VNS</a>)&nbsp;[<a href="#cite_HM2001VNSPAA"
 * style="font-weight:bold">1</a>, <a href="#cite_HMP2008VNSMAA"
 * style="font-weight:bold">2</a>, <a href="#cite_HMP2010VNSMAA"
 * style="font-weight:bold">3</a>, <a href="#cite_HMBP2010VNS"
 * style="font-weight:bold">4</a>] algorithm embedded in a loop of
 * perturbation and neighborhood shuffling.
 * </p>
 * <p>
 * The VNS is defined over four neighborhoods (search operators) for the
 * path representation (permutation of cities in visiting order):
 * {@link org.logisticPlanning.tsp.solving.operators.permutation.update.PermutationUpdate_Swap
 * swap},
 * {@link org.logisticPlanning.tsp.solving.operators.permutation.update.PermutationUpdate_Rotate_Right
 * rotate right},
 * {@link org.logisticPlanning.tsp.solving.operators.permutation.update.PermutationUpdate_Rotate_Left
 * rotate left}, and
 * {@link org.logisticPlanning.tsp.solving.operators.permutation.update.PermutationUpdate_Reverse
 * reverse}. Each of these operations takes two integer values
 * {@code (i, j)} as parameter. The neighborhoods have proximately the same
 * sizes ({@code n²}) and are disjoint for most parameter tuples. This
 * means that it is not entirely clear which neighborhood we should start
 * with.
 * </p>
 * <p>
 * In the main loop of the search, the neighborhoods used in the VNS are
 * therefore shuffled. Then a fully deterministic local search is performed
 * (Variable Neighborhood Descend). After that concluded in a local
 * optimum, a random part of the solution is shuffled and the process is
 * repeated.
 * </p>
 * <ol>
 * <li>Our VNS first randomizes the order of the neighborhoods (search
 * operators).</li>
 * <li>The current neighborhood index {@code k} is set to {@code 0}.</li>
 * <li>In a loop repeat (until the
 * {@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction#shouldTerminate()
 * termination criterion} is met):
 * <ol>
 * <li>As long as the neighborhood index {@code k} is between {@code 0} and
 * {@code number of neighborhoods}, repeat:
 * <ol>
 * <li>Try to find an improving move, i.e., a move that can improve the
 * candidate solution. This means indices {@code i} and {@code j} for
 * neighborhood {@code k} that would lead to a distance reduction of tour
 * {@code x}. Depending on the {@link #m_improvementSelectionPolicy},
 * either the first or best such move is chosen.</li>
 * <li>If an improving move was found, apply it and set {@code k=0},
 * otherwise set {@code k=k+1}.</li>
 * </ol>
 * </li>
 * <li>If we get here, then none of the neighborhoods (search operators)
 * can make the current tour {@code x} any shorter for any index pair
 * {@code (i,j)} . This means that we arrived in a local optimum from the
 * perspective of all available operators. In order to escape from there,
 * we do the following:
 * <ol>
 * <li>Shuffle a random part of {@code x}.</li>
 * <li>Shuffle the neighborhoods.</li>
 * </ol>
 * </li>
 * </ol>
 * </li>
 * </ol>
 * <h2>References</h2>
 * <ol>
 * <li><div><span id="cite_HM2001VNSPAA" /><a
 * href="http://www.hec.ca/profs/pierre.hansen.html">Pierre Hansen</a>
 * and&nbsp;<a href=
 * "http://www.brunel.ac.uk/siscm/mathematical-sciences/people-in-maths/academic-staff/drnenadmladenovic"
 * >Nenad Mladenovi&#263;</a>: <span
 * style="font-weight:bold">&ldquo;Variable Neighborhood Search: Principles
 * and Applications,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">European Journal of
 * Operational Research (EJOR)</span> 130(3):449&ndash;467, May&nbsp;1,
 * 2001; published by Amsterdam, The Netherlands: Elsevier Science
 * Publishers B.V. and&nbsp;Amsterdam, The Netherlands: North-Holland
 * Scientific Publishers Ltd.. doi:&nbsp;<a
 * href="http://dx.doi.org/10.1016/S0377-2217(00)00100-4">10.1016
 * /S0377-2217(00)00100-4</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/03772217">0377-2217</a></div></li>
 * <li><div><span id="cite_HMP2008VNSMAA" /><a
 * href="http://www.hec.ca/profs/pierre.hansen.html">Pierre Hansen</a>, <a
 * href=
 * "http://www.brunel.ac.uk/siscm/mathematical-sciences/people-in-maths/academic-staff/drnenadmladenovic"
 * >Nenad Mladenovi&#263;</a>, and&nbsp;<a
 * href="http://jamoreno.webs.ull.es/">Jos&#233; Andr&#233;s Moreno
 * P&#233;rez</a>: <span style="font-weight:bold">&ldquo;Variable
 * Neighbourhood Search: Methods and Applications,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">4OR</span>
 * 6(4):319&ndash;360, December&nbsp;1, 2008; published by Berlin, Germany:
 * Springer-Verlag GmbH. doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/s10288-008-0089-1">10.1007/s10288
 * -008-0089-1</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/16194500">1619-4500</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/16142411">1614-2411</a></div></li>
 * <li><div><span id="cite_HMP2010VNSMAA" /><a
 * href="http://www.hec.ca/profs/pierre.hansen.html">Pierre Hansen</a>, <a
 * href=
 * "http://www.brunel.ac.uk/siscm/mathematical-sciences/people-in-maths/academic-staff/drnenadmladenovic"
 * >Nenad Mladenovi&#263;</a>, and&nbsp;<a
 * href="http://jamoreno.webs.ull.es/">Jos&#233; Andr&#233;s Moreno
 * P&#233;rez</a>: <span style="font-weight:bold">&ldquo;Variable
 * Neighbourhood Search: Methods and Applications,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Annals of Operations
 * Research</span> 175(1):367&ndash;407, March&nbsp;1, 2010; published by
 * Dordrecht, Netherlands: Springer Netherlands and&nbsp;Amsterdam, The
 * Netherlands: J. C. Baltzer AG, Science Publishers. doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/s10479-009-0657-6"
 * >10.1007/s10479-009-0657-6</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/02545330">0254-5330</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/15729338">1572-9338</a></div></li>
 * <li><div><span id="cite_HMBP2010VNS" /><a
 * href="http://www.hec.ca/profs/pierre.hansen.html">Pierre Hansen</a>, <a
 * href=
 * "http://www.brunel.ac.uk/siscm/mathematical-sciences/people-in-maths/academic-staff/drnenadmladenovic"
 * >Nenad Mladenovi&#263;</a>, <a
 * href="http://www.hec.ca/en/profs/jack.brimberg.html">Jack Brimberg</a>,
 * and&nbsp;<a href="http://jamoreno.webs.ull.es/">Jos&#233; Andr&#233;s
 * Moreno P&#233;rez</a>: <span style="font-weight:bold">&ldquo;Variable
 * Neighborhood Search,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Handbook of
 * Metaheuristics</span>, chapter 61&ndash;86, pages 61&ndash;86, <a
 * href="http://www.crt.umontreal.ca/~michelg/">Michel Gendrau</a>
 * and&nbsp;<a href="http://www.iro.umontreal.ca/~potvin/">Jean-Yves
 * Potvin</a>, editors, volume 146 of International Series in Operations
 * Research &amp; Management Science, Norwell, MA, USA: Kluwer Academic
 * Publishers, Dordrecht, Netherlands: Springer Netherlands,
 * and&nbsp;Boston, MA, USA: Springer US. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/1461426901">1461426901</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9781441916655">978-1-4419
 * -1665-5</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/978-1-4419-1665-5_3">10.1007/978-
 * 1-4419-1665-5_3</a>; Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=xMTS5dyDhwMC">xMTS5dyDhwMC</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/08848289">0884-8289</a>;
 * ASIN:&nbsp;<a
 * href="http://www.amazon.com/dp/1461426901">1461426901</a></div></li>
 * </ol>
 */
public class PermutationVNS extends TSPLocalSearchAlgorithm<int[]> {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the neighborhoods: {@value} */
  public static final String PARAM_NEIGHBORHOODS = "vnsNeighborhoods"; //$NON-NLS-1$

  /**
   * the policy regarding whether we should use the first improvement or
   * best improvement: {@value}
   */
  public static final String PARAM_IMPROVEMENT_SELECTION_POLICY = "vnsImprovementSelectionPolicy"; //$NON-NLS-1$

  /** the default neighborhoods */
  private static final PermutationUpdateOperator[] DEFAULT_NEIGHBORHOODS = new PermutationUpdateOperator[] {//
  PermutationUpdate_Swap.INSTANCE,//
      PermutationUpdate_Reverse.INSTANCE,//
      PermutationUpdate_Rotate_Left.INSTANCE,//
      PermutationUpdate_Rotate_Right.INSTANCE, };

  /**
   * the update operations (neighborhoods)
   *
   * @serial a non-null array with the (non-null) updating operations
   *         (neighborhoods)
   */
  private PermutationUpdateOperator[] m_ops;

  /**
   * the policy regarding whether we should use the first improvement or
   * best improvement
   *
   * @serial the non-null improvement selection policy
   */
  private EImprovementSelectionPolicy m_improvementSelectionPolicy;

  /** the operations to be used */
  private transient PermutationUpdateOperator[] m_useOps;

  /** instantiate */
  public PermutationVNS() {
    super("Variable Neighborhood Search");//$NON-NLS-1$

    this.m_ops = PermutationVNS.DEFAULT_NEIGHBORHOODS;
    this.m_improvementSelectionPolicy = EImprovementSelectionPolicy.DEFAULT;
  }

  /**
   * Perform the VNS
   *
   * @param args
   *          the command line arguments
   */
  public final static void main(final String[] args) {
    TSPAlgorithmRunner.benchmark(//
        Instance.SYMMETRIC_INSTANCES, PermutationVNS.class,//
        args);
  }

  /** {@inheritDoc} */
  @Override
  public void localSearch(final Individual<int[]> srcdst,
      final ObjectiveFunction f) {

    final int n;
    final Randomizer r;
    final PermutationUpdateOperator[] ops;
    final EImprovementSelectionPolicy policy;
    final boolean firstImpIt;
    int chosen, delta, bestDelta, i, j, bestI, bestJ;
    PermutationUpdateOperator op;

    n = f.n();
    r = f.getRandom();

    // see which kind of improvement we are looking for
    policy = this.m_improvementSelectionPolicy.getCallPolicy(r);
    firstImpIt = policy.getIterationPolicy(r).useFirstImprovement();

    ops = this.m_useOps;

    // We first randomize the neighborhood orders (to make the algorithm
    // more
    // random) and then perform a VNS. The VNS tries to find improving
    // moves
    // in
    // the neighborhood at index "0". If none is found there, it goes to
    // neighborhood "1", then to "2", and so on. As soon as an improving
    // move
    // was found, we return to neighborhood 0. If no improving move is
    // found,
    // we come back right here, after shuffling a part of the solution at
    // the
    // bottom of this loop.
    chosen = 0;
    r.shuffle(ops);

    // Perform the VNS. Chosen will be increased when no improving move can
    // be found.
    while (chosen < ops.length) {
      op = ops[chosen];
      bestI = bestJ = bestDelta = Integer.MAX_VALUE;

      // Test all possible modifications that the current neighborhood can
      // offer (or just grab the first improving move).
      findBest: for (j = n; (--j) > 0;) {
        for (i = j; (--i) >= 0;) {

          // Check operation for indices (i,j)
          delta = op.delta(srcdst.solution, f, i, j);
          if (delta < bestDelta) { // Remember best possible move.
            bestDelta = delta;
            bestI = i;
            bestJ = j;
            if (firstImpIt && (delta < 0)) {
              // if configuration says so, we can also stop as
              // soon as we
              // have one improving move
              break findBest;
            }
          }
        }
      }

      // Did we find an improvement, i.e., is there an application of the
      // search operator that would decrease the tour length?
      if (bestDelta < 0) {// Yes! we found one improving move!
        op.update(srcdst.solution, bestI, bestJ);// apply this move
        srcdst.tourLength += bestDelta; // modify tour length
        f.registerFE(srcdst.solution, srcdst.tourLength); // register
        // new tour
        // and
        // length
        chosen = 0; // go back to the initial neighborhood
      } else {
        chosen++; // try next neighborhood
      }

      if (f.shouldTerminate()) {
        return; // should we terminate?
      }
    }

    // No further improvements are possible.
    // The new solution is an optimum with respect to all neighborhoods.
    // So we exit to the local search towards the main loop here.
    // The main loop may perturb srcdst and then enter this procedure
    // again,
    // if it wishes to do so.
  }

  /** {@inheritDoc} */
  @Override
  public void printConfiguration(final PrintStream ps) {
    super.printConfiguration(ps);

    Configurable.printKey(PermutationVNS.PARAM_NEIGHBORHOODS, ps);
    Configurable.printlnObject(this.m_ops, ps);

    Configurable.printKey(
        PermutationVNS.PARAM_IMPROVEMENT_SELECTION_POLICY, ps);
    Configurable.printlnObject(this.m_improvementSelectionPolicy, ps);
  }

  /** {@inheritDoc} */
  @Override
  public void printParameters(final PrintStream ps) {
    super.printParameters(ps);

    Configurable.printKey(
        PermutationVNS.PARAM_IMPROVEMENT_SELECTION_POLICY, ps);
    ps.println(//
    "should we use the best possible improvement or the first one discovered?"); //$NON-NLS-1$

    ps.println(//
    "the minimum number of nodes shuffled when trying to escape a local optimum"); //$NON-NLS-1$

    for (final Configurable c : this.m_ops) {
      c.printParameters(ps);
    }
  }

  /** {@inheritDoc} */
  @Override
  public void configure(final Configuration config) {
    super.configure(config);

    this.m_improvementSelectionPolicy = config.getConstant(
        PermutationVNS.PARAM_IMPROVEMENT_SELECTION_POLICY,
        EImprovementSelectionPolicy.class,
        EImprovementSelectionPolicy.class,
        this.m_improvementSelectionPolicy);

    for (final Configurable c : this.m_ops) {
      c.configure(config);
    }
  }

  /**
   * set the policy regarding whether the first improvement or the best
   * improvement should be used
   *
   * @param i
   *          the policy
   */
  public final void setImprovementSelectionPolicy(
      final EImprovementSelectionPolicy i) {
    if (i != null) {
      this.m_improvementSelectionPolicy = i;
    }
  }

  /** {@inheritDoc} */
  @Override
  public void beginRun(final ObjectiveFunction f) {
    super.beginRun(f);
    TSPModule.invokeBeginRun(f, this.m_ops);
    this.m_useOps = this.m_ops.clone();
  }

  /** {@inheritDoc} */
  @Override
  public void endRun(final ObjectiveFunction f) {
    this.m_useOps = null;
    try {
      TSPModule.invokeEndRun(f, this.m_ops);
    } finally {
      super.endRun(f);
    }
  }

  /** {@inheritDoc} */
  @Override
  public PermutationVNS clone() {
    int i;
    final PermutationVNS clo;
    final PermutationUpdateOperator[] ops;

    clo = ((PermutationVNS) (super.clone()));
    clo.m_useOps = null;
    clo.m_ops = ops = clo.m_ops.clone();
    for (i = ops.length; (--i) >= 0;) {
      ops[i] = ops[i].clone();
    }
    return clo;
  }
}
