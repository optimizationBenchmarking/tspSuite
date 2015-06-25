package org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.random;

import java.io.PrintStream;

import org.logisticPlanning.tsp.benchmarking.instances.Instance;
import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;
import org.logisticPlanning.tsp.solving.TSPAlgorithm;
import org.logisticPlanning.tsp.solving.TSPAlgorithmRunner;
import org.logisticPlanning.tsp.solving.operators.permutation.creation.PermutationCreateCanonical;
import org.logisticPlanning.tsp.solving.operators.permutation.update.PermutationUpdateOperator;
import org.logisticPlanning.tsp.solving.operators.permutation.update.PermutationUpdate_Swap;
import org.logisticPlanning.utils.config.Configurable;
import org.logisticPlanning.utils.config.Configuration;
import org.logisticPlanning.utils.math.random.Randomizer;

/**
 * <p>
 * This algorithm starts with a random permutation and keeps generating new
 * permutations by applying random
 * {@link org.logisticPlanning.tsp.solving.operators.permutation.update.PermutationUpdateOperator
 * updates} to it. It always keeps the new permutation, regardless whether
 * it is better or worse than those seen before, i.e., it performs a <a
 * href="http://en.wikipedia.org/wiki/Random_walk">random walk</a>&nbsp;[<a
 * href="#cite_P1905TPOTRW" style="font-weight:bold">1</a>, <a
 * href="#cite_F1968AITPTAIA" style="font-weight:bold">2</a>, <a
 * href="#cite_H1995RWARERW" style="font-weight:bold">3</a>, <a
 * href="#cite_WR2007RWTASA" style="font-weight:bold">4</a>] over the space
 * of permutations.
 * </p>
 * <h2>References</h2>
 * <ol>
 * <li><div><span id="cite_P1905TPOTRW" />Karl Pearson: <span
 * style="font-weight:bold">&ldquo;The Problem of the Random
 * Walk,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Nature</span> 72:294,
 * July&nbsp;27, 1905. doi:&nbsp;<a
 * href="http://dx.doi.org/10.1038/072294b0">10.1038/072294b0</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/00280836">0028-0836</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/14764687">1476-4687</a></div></li>
 * <li><div><span id="cite_F1968AITPTAIA" />William Feller: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;An Introduction to
 * Probability Theory and Its Applications, Volume 1,&rdquo;</span> 1968,
 * Wiley Series in Probability and Mathematical Statistics &#8210; Applied
 * Probability and Statistics Section Series, Chichester, West Sussex, UK:
 * Wiley Interscience. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0471257087">0471257087</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780471257080">978-0471257080</a>;
 * Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=TkfeSAAACAAJ">TkfeSAAACAAJ</a>
 * and&nbsp;<a
 * href="http://books.google.com/books?id=E9WLSAAACAAJ">E9WLSAAACAAJ
 * </a></div></li>
 * <li><div><span id="cite_H1995RWARERW" />Barry D. Hughes: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Random Walks and
 * Random Environments: Volume 1: Random Walks,&rdquo;</span> May&nbsp;16,
 * 1995, New York, NY, USA: Oxford University Press, Inc.. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0198537883">0-19-853788-3</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780198537885">978-0-19-853788
 * -5</a>; Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=QhOen_t0LeQC"
 * >QhOen_t0LeQC</a></div></li>
 * <li><div><span id="cite_WR2007RWTASA" /><a
 * href="mscl.cit.nih.gov/homepages/ghw/">George H. Weiss</a>
 * and&nbsp;Robert J. Rubin: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Random Walks:
 * Theory and Selected Applications,&rdquo;</span> March&nbsp;14, 2007, <a
 * href="http://en.wikipedia.org/wiki/Ilya_Prigogine">Ilya Romanovich
 * Prigogine</a> and&nbsp;<a
 * href="http://en.wikipedia.org/wiki/Stuart_A._Rice">Stuart Alan Rice</a>,
 * editors, volume 52 of Advances in Chemical Physics, Hoboken, NJ, USA:
 * John Wiley &amp; Sons, Inc.. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780471868453">9780471868453</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780470142769">9780470142769</a>;
 * doi:&nbsp;<a href="http://dx.doi.org/10.1002/9780470142769.ch5">10.1002/
 * 9780470142769. ch5</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/19344791">1934-4791</a></div></li>
 * </ol>
 */
public final class RandomPermutationWalk extends TSPAlgorithm {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the parameter for the update operation: {@value} */
  public static final String PARAM_UPDATE_OPERATION = "updateOperation"; //$NON-NLS-1$

  /** the permutation */
  private transient int[] m_perm;

  /** the update */
  private PermutationUpdateOperator m_update;

  /** instantiate */
  public RandomPermutationWalk() {
    super("Random Walk"); //$NON-NLS-1$
    this.m_update = PermutationUpdate_Swap.INSTANCE;
  }

  /** {@inheritDoc} */
  @Override
  public RandomPermutationWalk clone() {
    final RandomPermutationWalk c;
    c = ((RandomPermutationWalk) (super.clone()));
    c.m_perm = null;
    c.m_update = c.m_update.clone();
    return c;
  }

  /** {@inheritDoc} */
  @Override
  public void configure(final Configuration config) {
    super.configure(config);

    this.m_update = config.getInstance(
        RandomPermutationWalk.PARAM_UPDATE_OPERATION,
        PermutationUpdateOperator.class, null, this.m_update);
  }

  /** {@inheritDoc} */
  @Override
  public void printConfiguration(final PrintStream ps) {
    super.printConfiguration(ps);

    Configurable
        .printKey(RandomPermutationWalk.PARAM_UPDATE_OPERATION, ps);
    ps.println(this.m_update);
  }

  /** {@inheritDoc} */
  @Override
  public void printParameters(final PrintStream ps) {

    super.printParameters(ps);

    Configurable
        .printKey(RandomPermutationWalk.PARAM_UPDATE_OPERATION, ps);
    ps.println("the update operation to use in the walk."); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  public void solve(final ObjectiveFunction f) {
    final int n;
    final Randomizer r;
    final PermutationUpdateOperator op;
    int[] perm;
    int i, j, k;
    long tourLength;

    n = f.n();

    // allocate the data structures
    perm = this.m_perm;
    if ((perm == null) || (perm.length != n)) {
      this.m_perm = perm = new int[n];
    }

    // ok, data structures are allocated, let's begin
    PermutationCreateCanonical.makeCanonical(perm, n);
    r = f.getRandom();
    r.shuffle(perm, 0, n);

    // get the length of the initial permutation
    tourLength = f.evaluate(perm);

    // perform the random walk as long as we should be continue running
    op = this.m_update;
    while (!(f.shouldTerminate())) {

      // find two distinct indices
      i = r.nextInt(n);
      do {
        j = r.nextInt(n);
      } while (i == j);

      // compute the change in tour length
      k = op.delta(perm, f, i, j);
      if (k != PermutationUpdateOperator.NO_EFFECT) {
        // ok, there is a change: apply change, register FE
        tourLength += k;
        op.update(perm, i, j);
        f.registerFE(perm, tourLength);
      }
    }
  }

  /**
   * Perform the random walk heuristic
   *
   * @param args
   *          the command line arguments
   */
  public final static void main(final String[] args) {
    TSPAlgorithmRunner.benchmark(//
        Instance.SYMMETRIC_INSTANCES,//
        RandomPermutationWalk.class,//
        args);
  }

  /** {@inheritDoc} */
  @Override
  public void beginRun(final ObjectiveFunction f) {
    super.beginRun(f);
    this.m_update.beginRun(f);
    this.m_perm = new int[f.n()];
  }

  /** {@inheritDoc} */
  @Override
  public void endRun(final ObjectiveFunction f) {
    this.m_perm = null;
    try {
      this.m_update.endRun(f);
    } finally {
      super.endRun(f);
    }
  }
}
