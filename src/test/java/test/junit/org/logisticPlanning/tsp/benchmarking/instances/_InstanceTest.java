package test.junit.org.logisticPlanning.tsp.benchmarking.instances;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer;
import org.logisticPlanning.tsp.benchmarking.instances.Instance;
import org.logisticPlanning.tsp.solving.operators.permutation.creation.PermutationCreateCanonical;
import org.logisticPlanning.tsp.solving.operators.permutation.update.PermutationUpdateOperator;
import org.logisticPlanning.tsp.solving.operators.permutation.update.PermutationUpdateOperators;
import org.logisticPlanning.tsp.solving.utils.RepresentationUtils;
import org.logisticPlanning.utils.NumberReader;

import test.junit.org.logisticPlanning.tsp.benchmarking.dist.DistanceComputerTest;

/**
 * Test whether the TSPLib instances are fully functional. This class will
 * be overridden in order to test a particular instance of
 * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance} .
 */
@Ignore
public class _InstanceTest extends DistanceComputerTest {

  /** the instance has wrong dimension */
  public static final String INSTANCE_WRONG_DIMENSION = "Instance has wrong dimension."; //$NON-NLS-1$

  /** the instance has a wrong optimal value set */
  public static final String INSTANCE_WRONG_OPTIMUM = "Impossible optimal value."; //$NON-NLS-1$

  /** the instance has a wrong canonical tour length */
  public static final String WRONG_CANONICAL_TOUR_LENGTH = "Length of canonical tour shorter than optimum."; //$NON-NLS-1$

  /** the internal instance */
  final Instance m_i;

  /** the optimal tour */
  private int[] m_optTour;

  /**
   * test a given instance
   *
   * @param i
   *          the instance
   */
  _InstanceTest(final Instance i) {
    super();
    this.m_i = i;
    this.m_optTour = null;
  }

  /** {@inheritDoc} */
  @Override
  protected final DistanceComputer createDistanceComputer(
      final int threshold) throws Throwable {
    return this.m_i.load(threshold);
  }

  /**
   * If the length of the canonical tour is known from the documentation,
   * test whether it is equal to what we get by evaluating said tour.
   *
   * @throws Throwable
   *           if loading fails
   */
  @Test(timeout = 3600000)
  public final void testCanonicalTour() throws Throwable {
    int i, n;
    long l, xa, xb;
    final DistanceComputer d1, d2;
    int[] adj;

    l = this.m_i.canonicalTourLength();
    if (l <= 0l) {
      return;
    }

    Assert.assertTrue(_InstanceTest.WRONG_CANONICAL_TOUR_LENGTH,
        (l >= this.m_i.optimum()));

    d1 = this.getDistanceComputerMat();
    Assert.assertNotNull(DistanceComputerTest.MATRIX_IS_NULL, d1);
    d2 = this.getDistanceComputerNoMat();
    Assert.assertNotNull(DistanceComputerTest.RAW_IS_NULL, d2);

    n = d1.n();
    Assert.assertEquals(DistanceComputerTest.MATRIX_WRONG_DIMENSION, n,
        this.m_i.n());
    Assert.assertEquals(DistanceComputerTest.RAW_WRONG_DIMENSION, d2.n(),
        this.m_i.n());
    Assert
    .assertEquals(DistanceComputerTest.DISAGREE_DIMENSION, n, d2.n());

    adj = new int[n];
    xa = xb = 0l;
    for (i = 2; i <= n; i++) {
      adj[i - 2] = i;
      xa += d1.distance(i - 1, i);
      xb += d2.distance(i - 1, i);
    }
    xa += d1.distance(n, 1);
    xb += d2.distance(n, 1);
    adj[i - 2] = 1;

    Assert.assertEquals(DistanceComputerTest.MATRIX_WRONG_POINT_DISTANCE,
        xa, l);
    Assert.assertEquals(DistanceComputerTest.MATRIX_WRONG_POINT_DISTANCE,
        xa, l);
    Assert.assertEquals(DistanceComputerTest.RAW_WRONG_POINT_DISTANCE, xb,
        l);

    Assert
    .assertEquals(
        //
        DistanceComputerTest.MATRIX_WRONG_EVAL_ADJ,
        d1.evaluateAdj(adj), l);

    Assert.assertEquals(//
        DistanceComputerTest.RAW_WRONG_EVAL_ADJ, d1.evaluateAdj(adj), l);
  }

  /**
   * If the optimal tour is known from the documentation, test whether
   * evaluating that tour step-by-step via the point distance leads to the
   * same length as found in the documentation.
   *
   * @throws Throwable
   *           if loading fails
   */
  @Test(timeout = 3600000)
  public final void testOptTourDistance() throws Throwable {
    final int[] t;
    final DistanceComputer d1, d2;
    long xa, xb;
    int old;

    t = this.loadTour();
    if (t == null) {
      return;
    }

    Assert.assertEquals(_InstanceTest.INSTANCE_WRONG_DIMENSION, t.length,
        this.m_i.n());

    d1 = this.getDistanceComputerMat();
    Assert.assertEquals(DistanceComputerTest.MATRIX_WRONG_DIMENSION,
        t.length, d1.n());
    d2 = this.getDistanceComputerNoMat();
    Assert.assertEquals(DistanceComputerTest.RAW_WRONG_DIMENSION,
        t.length, d2.n());

    old = t[t.length - 1];
    xa = xb = 0l;
    for (final int i : t) {
      xa += d1.distance(old, i);
      xb += d2.distance(old, i);
      old = i;
    }

    Assert.assertEquals(DistanceComputerTest.MATRIX_WRONG_DIMENSION, xa,
        this.m_i.optimum());
    Assert.assertEquals(DistanceComputerTest.RAW_WRONG_DIMENSION, xb,
        this.m_i.optimum());
  }

  /**
   * If the optimal tour is known from the documentation, test whether
   * evaluating that tour leads to the same length as found in the
   * documentation.
   *
   * @throws Throwable
   *           if loading fails
   */
  @Test(timeout = 3600000)
  public final void testOptTourEval() throws Throwable {
    final int[] t, adj;
    final DistanceComputer d1, d2;
    final long opt;
    int i;

    t = this.loadTour();
    if (t == null) {
      return;
    }

    Assert.assertEquals(_InstanceTest.INSTANCE_WRONG_DIMENSION, t.length,
        this.m_i.n());

    d1 = this.getDistanceComputerMat();
    Assert.assertEquals(DistanceComputerTest.MATRIX_WRONG_DIMENSION,
        t.length, d1.n());
    d2 = this.getDistanceComputerNoMat();
    Assert.assertEquals(DistanceComputerTest.RAW_WRONG_DIMENSION,
        t.length, d2.n());

    opt = this.m_i.optimum();
    adj = new int[t.length];

    looper: for (i = 0;;) {
      Assert.assertEquals(DistanceComputerTest.MATRIX_WRONG_EVAL,
          d1.evaluate(t), opt);
      Assert.assertEquals(DistanceComputerTest.RAW_WRONG_EVAL,
          d2.evaluate(t), opt);

      RepresentationUtils.pathToAdjacencyList(t, adj);
      Assert.assertEquals(DistanceComputerTest.MATRIX_WRONG_EVAL_ADJ,
          d1.evaluateAdj(adj), opt);
      Assert.assertEquals(DistanceComputerTest.RAW_WRONG_EVAL_ADJ,
          d2.evaluateAdj(adj), opt);

      if ((i++) > 2) {
        break looper;
      }

      RepresentationUtils.adjacencyListToPath(adj, t);
    }
  }

  /**
   * Try to randomly construct quite a few tours and to find whether one of
   * them is shorter than the globally optimal tour. If so, then there must
   * be an error.
   */
  @Test(timeout = 3600000)
  public final void testBetterThanOptTourRS() {
    final DistanceComputer d1, d2;
    final int[] perm;
    final long l;
    final int n;
    long xy, xz;
    int i;

    n = this.m_i.n();
    perm = PermutationCreateCanonical.canonical(n);
    l = this.m_i.optimum();
    Assert.assertTrue(_InstanceTest.INSTANCE_WRONG_OPTIMUM, l > 0l);

    d1 = this.getDistanceComputerMat();
    d2 = this.getDistanceComputerNoMat();
    Assert.assertEquals(DistanceComputerTest.MATRIX_WRONG_DIMENSION,
        d1.n(), n);
    Assert.assertEquals(DistanceComputerTest.RAW_WRONG_DIMENSION, d2.n(),
        n);

    for (i = DistanceComputerTest.getRequiredTests(n);;) {
      xy = d1.evaluate(perm);
      Assert.assertTrue(DistanceComputerTest.MATRIX_WRONG_EVAL, xy >= l);
      xz = d2.evaluate(perm);
      Assert.assertTrue(DistanceComputerTest.RAW_WRONG_EVAL, xz >= l);
      Assert.assertEquals(DistanceComputerTest.DISAGREE_EVAL, xy, xz);
      if ((--i) <= 0) {
        return;
      }
      // PermutationCreateUniform.randomize(perm, TestBase.RANDOM);
      synchronized (this.m_r) {
        this.m_r.shuffle(perm, 0, perm.length);
      }
    }
  }

  /** the permutation update operators */
  private static final PermutationUpdateOperator[] OPERATORS = //
      PermutationUpdateOperators.OPERATORS_AND_COMPLEMENT;

  /**
   * Try to randomly construct quite a few tours and to find whether one of
   * them is shorter than the globally optimal tour. If so, then there must
   * be an error.
   */
  @Test(timeout = 3600000)
  public final void testBetterThanOptTourHC() {
    final DistanceComputer d1, d2;
    final int[] perm;
    final long opt;
    final int n;
    long cur1, cur2;
    int i, j, a, b, delta, ndelta;
    PermutationUpdateOperator chosen;

    if (!(this.m_i.symmetric())) {
      return; // the operators only work for the symmetric instances
    }

    n = this.m_i.n();
    opt = this.m_i.optimum();
    Assert.assertTrue(_InstanceTest.INSTANCE_WRONG_OPTIMUM, opt > 0l);
    perm = PermutationCreateCanonical.canonical(n);

    d1 = this.getDistanceComputerMat();
    Assert.assertEquals(DistanceComputerTest.MATRIX_WRONG_DIMENSION, n,
        d1.n());
    d2 = this.getDistanceComputerNoMat();
    Assert.assertEquals(DistanceComputerTest.RAW_WRONG_DIMENSION, n,
        d2.n());

    for (i = 50; (--i) >= 0;) {
      cur1 = d1.evaluate(perm);
      cur2 = d1.evaluate(perm);
      Assert.assertTrue(DistanceComputerTest.MATRIX_WRONG_EVAL,
          cur1 >= opt);
      Assert.assertTrue(DistanceComputerTest.RAW_WRONG_EVAL, cur2 >= opt);
      Assert.assertEquals(DistanceComputerTest.DISAGREE_EVAL, cur1, cur2);

      for (j = DistanceComputerTest.getRequiredTests(n); (--j) >= 0;) {
        synchronized (this.m_r) {
          a = this.m_r.nextInt(n);
          do {
            b = this.m_r.nextInt(n);
          } while (b == a);
        }

        delta = Integer.MAX_VALUE;
        chosen = null;
        for (final PermutationUpdateOperator candidate : _InstanceTest.OPERATORS) {
          ndelta = candidate.delta(perm, d1, a, b);
          if (ndelta != PermutationUpdateOperator.NO_EFFECT) {
            Assert.assertEquals(
                DistanceComputerTest.DISAGREE_POINT_DISTANCE, ndelta,
                candidate.delta(perm, d2, a, b));
            if (ndelta <= delta) {
              delta = ndelta;
              chosen = candidate;
            }
          }
        }

        if ((delta <= 0) && (chosen != null)) {
          cur1 += delta;
          Assert.assertTrue(DistanceComputerTest.WRONG_POINT_DISTANCE,
              cur1 >= opt);
          chosen.update(perm, a, b);
        }
      }
      // PermutationCreateUniform.randomize(perm, TestBase.RANDOM);
      synchronized (this.m_r) {
        this.m_r.shuffle(perm, 0, perm.length);
      }
    }
  }

  /**
   * try to load the optimal tour
   *
   * @return the optimal tour, or null
   */
  private final int[] loadTour() {
    String s;
    final int[] ret;
    int i;
    NumberReader nr;

    if (this.m_optTour != null) {
      return this.m_optTour;
    }

    try {
      try (InputStream is = _InstanceTest.class.getResourceAsStream(//
          this.m_i.name() + ".opt.tour")) { //$NON-NLS-1$
        try (InputStreamReader isr = new InputStreamReader(is)) {
          try (BufferedReader br = new BufferedReader(isr)) {

            while ((s = br.readLine()) != null) {
              s = s.trim();
              if (s.length() <= 0) {
                continue;
              }
              if ("TOUR_SECTION".equalsIgnoreCase(s)) { //$NON-NLS-1$

                ret = new int[this.m_i.n()];
                nr = new NumberReader(br);
                for (i = 0; i < ret.length; i++) {
                  ret[i] = nr.nextInt();
                }

                return (this.m_optTour = ret);
              }
            }

          }
        }
      }
    } catch (final Throwable t) {
      //
    }

    return null;
  }

}
