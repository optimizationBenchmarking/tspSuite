package org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.localOpt;

import java.io.PrintStream;

import org.logisticPlanning.tsp.benchmarking.instances.Instance;
import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;
import org.logisticPlanning.tsp.solving.Individual;
import org.logisticPlanning.tsp.solving.TSPAlgorithmRunner;
import org.logisticPlanning.tsp.solving.algorithms.localSearch.TSPLocalSearchAlgorithm;
import org.logisticPlanning.tsp.solving.operators.permutation.localOpt.ExhaustivelyEnumeratingLocal7Optimizer;
import org.logisticPlanning.tsp.solving.operators.permutation.localOpt.LocalOptimizer;
import org.logisticPlanning.utils.config.Configurable;
import org.logisticPlanning.utils.config.Configuration;
import org.logisticPlanning.utils.math.random.Randomizer;

/**
 * An algorithm applying an {@code n}-opt locally: It uses a
 * {@link org.logisticPlanning.tsp.solving.operators.permutation.localOpt.LocalOptimizer
 * local optimization operator} which can find a optimal sub-sequence of a
 * given length starting (right after) a given index. We then divide the
 * solution into patches of length {@code n-1}, i.e., patches which do
 * overlap for exactly {@code 1} node. The operator is applied to each
 * patch. If it succeeds in finding an improvement for a given patch, then
 * we apply it to the patch before and after which overlap by {@code 1}
 * node. In order to potentially speed up the search, we may also try an
 * additional patch with a random overlap. This is repeated until no
 * improvement can be found anymore.
 */
public class LocalNOpt extends TSPLocalSearchAlgorithm<int[]> {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the n-optimizer length */
  public static final String PARAM_N_OPT = "nOpt"; //$NON-NLS-1$
  /** the random overlap parameter */
  public static final String PARAM_RANDOM_OVERLAP = "randomOverlap"; //$NON-NLS-1$

  /** should we use overlaps? */
  private boolean m_randomOverlap;

  /** the operation to be used */
  private LocalOptimizer m_opt;

  /** the order in which the nodes are processed */
  private transient int[] m_currentAllowed;
  /** the order in which the nodes are processed */
  private transient int[] m_nextAllowed;
  /** the order in which the nodes are processed */
  private transient boolean[] m_nextAllowedBits;

  /** instantiate */
  public LocalNOpt() {
    super("Local N-Opt");//$NON-NLS-1$
    this.m_opt = new ExhaustivelyEnumeratingLocal7Optimizer();
    this.m_randomOverlap = true;
  }

  /**
   * Perform the Local Optimization
   *
   * @param args
   *          the command line arguments
   */
  public final static void main(final String[] args) {
    TSPAlgorithmRunner.benchmark(//
        Instance.SYMMETRIC_INSTANCES, LocalNOpt.class,//
        args);
  }

  /** {@inheritDoc} */
  @Override
  public void localSearch(final Individual<int[]> srcdst,
      final ObjectiveFunction f) {
    final Randomizer rand;
    final int n, len, lenM1;
    final LocalOptimizer opt;
    final boolean overlap;
    int[] currentAllowed, nextAllowed, tempI;
    int i, j, nextAllowedCount, currentAllowedCount;
    boolean[] nextAllowedBits;
    long delta;

    rand = f.getRandom();
    nextAllowed = this.m_nextAllowed;
    nextAllowedBits = this.m_nextAllowedBits;
    opt = this.m_opt;

    n = f.n();
    len = opt.getSubPathLength();
    lenM1 = (len - 1);
    nextAllowedCount = (n / lenM1);
    if ((nextAllowedCount * lenM1) < n) {
      nextAllowedCount++;
    }
    j = rand.nextInt(n);
    for (i = nextAllowedCount; (--i) >= 0;) {
      nextAllowed[i] = j;
      j += lenM1;
      if (j >= n) {
        j = 0;
      }
    }

    currentAllowed = this.m_currentAllowed;
    overlap = this.m_randomOverlap;

    while (nextAllowedCount > 0) {
      tempI = currentAllowed;
      currentAllowed = nextAllowed;
      nextAllowed = tempI;

      currentAllowedCount = nextAllowedCount;
      for (; (--nextAllowedCount) >= 0;) {
        nextAllowedBits[currentAllowed[nextAllowedCount]] = false;
      }
      nextAllowedCount = 0;

      while (currentAllowedCount > 0) {
        i = rand.nextInt(currentAllowedCount);
        j = currentAllowed[i];
        currentAllowed[i] = currentAllowed[--currentAllowedCount];

        if (!nextAllowedBits[j]) {
          delta = opt.apply(srcdst.solution, j, f);
          if (delta < 0L) {
            srcdst.tourLength += delta;
            f.registerFE(srcdst.solution, srcdst.tourLength);

            i = (j - lenM1);
            if (i < 0) {
              i += n;
            }
            if (!nextAllowedBits[i]) {
              nextAllowedBits[i] = true;
              nextAllowed[nextAllowedCount++] = i;
            }

            i = (j + lenM1);
            if (i >= n) {
              i -= n;
            }
            if (!nextAllowedBits[i]) {
              nextAllowedBits[i] = true;
              nextAllowed[nextAllowedCount++] = i;
            }

            // try a randomized speed-up if there are large overlaps
            if (overlap) {
              i = (j + (rand.nextInt(len) - (len >>> 1)));
              if (i < 0) {
                i += n;
              } else {
                if (i >= n) {
                  i -= n;
                }
              }
              if (!nextAllowedBits[i]) {
                nextAllowedBits[i] = true;
                nextAllowed[nextAllowedCount++] = i;
              }
            }

          }
          if (f.shouldTerminate()) {
            return;
          }
        }
      }

    }
  }

  /**
   * Set the local optimizer
   *
   * @param opt
   *          the local optimizer
   */
  public final void setLocalOptimizer(final LocalOptimizer opt) {
    this.m_opt = opt;
  }

  /**
   * Should we also test randomly overlapping patches?
   *
   * @param overlap
   *          {@code true} if we also should test randomly overlapping
   *          patches
   */
  public final void setRandomOverlap(final boolean overlap) {
    this.m_randomOverlap = overlap;
  }

  /** {@inheritDoc} */
  @Override
  public void printConfiguration(final PrintStream ps) {
    super.printConfiguration(ps);

    Configurable.printKey(LocalNOpt.PARAM_N_OPT, ps);
    Configurable.printlnObject(this.m_opt, ps);

    Configurable.printKey(LocalNOpt.PARAM_RANDOM_OVERLAP, ps);
    ps.println(this.m_randomOverlap);
  }

  /** {@inheritDoc} */
  @Override
  public void printParameters(final PrintStream ps) {
    super.printParameters(ps);

    Configurable.printKey(LocalNOpt.PARAM_N_OPT, ps);
    ps.println("The local n-optimizer to use."); //$NON-NLS-1$

    Configurable.printKey(LocalNOpt.PARAM_RANDOM_OVERLAP, ps);
    ps.println("Test randomly overlapping or only minimally overlapping patches?");//$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  public void configure(final Configuration config) {
    super.configure(config);

    this.setLocalOptimizer(config.getInstance(LocalNOpt.PARAM_N_OPT,
        LocalOptimizer.class,
        ExhaustivelyEnumeratingLocal7Optimizer.class, this.m_opt));
    this.setRandomOverlap(config.getBoolean(
        LocalNOpt.PARAM_RANDOM_OVERLAP, this.m_randomOverlap));
  }

  /** {@inheritDoc} */
  @Override
  public void beginRun(final ObjectiveFunction f) {
    final int n;

    super.beginRun(f);
    this.m_opt.beginRun(f);
    n = f.n();

    this.m_currentAllowed = new int[n];
    this.m_nextAllowed = new int[n];
    this.m_nextAllowedBits = new boolean[n];
  }

  /** {@inheritDoc} */
  @Override
  public void endRun(final ObjectiveFunction f) {
    this.m_opt.endRun(f);
    this.m_currentAllowed = null;
    this.m_nextAllowed = null;
    this.m_nextAllowedBits = null;
    super.endRun(f);
  }

  /** {@inheritDoc} */
  @Override
  public LocalNOpt clone() {
    final LocalNOpt clo;

    clo = ((LocalNOpt) (super.clone()));
    this.m_currentAllowed = null;
    this.m_nextAllowed = null;
    this.m_nextAllowedBits = null;
    clo.m_opt = clo.m_opt.clone();
    return clo;
  }
}
