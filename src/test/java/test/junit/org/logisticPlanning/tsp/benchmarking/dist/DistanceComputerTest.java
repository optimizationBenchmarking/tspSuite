package test.junit.org.logisticPlanning.tsp.benchmarking.dist;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer;
import org.logisticPlanning.tsp.solving.operators.permutation.creation.PermutationCreateCanonical;
import org.logisticPlanning.utils.math.random.Randomizer;

import test.junit.TestBase;

/**
 * Testing the distance computer. This class forms the basis of what will
 * then be done for each instance in the test class
 * {@link test.junit.org.logisticPlanning.tsp.benchmarking.instances._InstanceTest}
 * . The idea here is to derive two distance computers from each problem
 * instance, one that is backed by a distance matrix and one which is
 * backed by the original coordinate list, i.e., not by a matrix (of
 * course, if distances are given as matrix, then both will be backed). We
 * then compare whether these two computers agree and whether their results
 * meet all sanity criteria.
 */
@Ignore
public class DistanceComputerTest extends TestBase {

  /** the minimum number of required random tests */
  private static final int MIN_TESTS = 4096;

  /** the maximum number of required random tests */
  private static final int MAX_TESTS = (2 * DistanceComputerTest.MIN_TESTS);

  /** the internal distance computer without matrix */
  private DistanceComputer m_distNoMatrix;

  /** the internal distance computer with matrix */
  private DistanceComputer m_distMatrix;

  /** the matrix-based distance computer is null */
  public static final String MATRIX_IS_NULL = "Matrix-based distance computer is null."; //$NON-NLS-1$
  /** the raw distance computer is null */
  public static final String RAW_IS_NULL = "Raw distance computer is null."; //$NON-NLS-1$

  /** the matrix-based distance computer has wrong dimension */
  public static final String MATRIX_WRONG_DIMENSION = "Matrix-based distance computer has wrong dimension."; //$NON-NLS-1$

  /** the raw distance computer has wrong dimension */
  public static final String RAW_WRONG_DIMENSION = "Raw distance computer has wrong dimension."; //$NON-NLS-1$

  /**
   * the matrix-based distance computer and the raw one disagree about the
   * dimension
   */
  public static final String DISAGREE_DIMENSION = "Matrix-based and raw distance computers disagree about dimension."; //$NON-NLS-1$

  /** the matrix-based distance computer computes wrong point distance */
  public static final String MATRIX_WRONG_POINT_DISTANCE = "Matrix-based distance computer has wrong point-distance."; //$NON-NLS-1$
  /** the raw-based distance computer computes wrong point distance */
  public static final String RAW_WRONG_POINT_DISTANCE = "Raw distance computer has wrong point-distance."; //$NON-NLS-1$
  /**
   * the matrix and raw-based distance computer disagree about a point
   * distance
   */
  public static final String DISAGREE_POINT_DISTANCE = "Matrix-based and raw distance computer disagree about point-distance."; //$NON-NLS-1$
  /**
   * the matrix and raw-based distance computer agree but have a wrong
   * point distance
   */
  public static final String WRONG_POINT_DISTANCE = "Both distance computers agree, but have a wrong point-distance."; //$NON-NLS-1$

  /** the matrix-based distance computer computes wrong overall distance */
  public static final String MATRIX_WRONG_EVAL = "Matrix-based distance computer evaluates overall distance incorrectly."; //$NON-NLS-1$
  /** the raw-based distance computer computes wrong overall distance */
  public static final String RAW_WRONG_EVAL = "Raw distance computer evaluates overall distance incorrectly."; //$NON-NLS-1$

  /**
   * the matrix-based distance computer computes wrong overall distance in
   * adjacency representation
   */
  public static final String MATRIX_WRONG_EVAL_ADJ = "Matrix-based distance computer evaluates overall distance incorrectly for adjacency-list based representations."; //$NON-NLS-1$
  /**
   * the raw-based distance computer computes wrong overall distance in
   * adjacency representation
   */
  public static final String RAW_WRONG_EVAL_ADJ = "Raw distance computer evaluates overall distance incorrectly for adjacency-list based representations."; //$NON-NLS-1$

  /**
   * the matrix and raw-based distance computer disagree about an
   * evaluation result
   */
  public static final String DISAGREE_EVAL = "Matrix-based and raw distance computer disagree about evaluation result."; //$NON-NLS-1$

  /** the internally used randomizer */
  protected final Randomizer m_r;

  /**
   * Create the distance computer test case.
   */
  protected DistanceComputerTest() {
    super();
    this.m_distNoMatrix = null;
    this.m_distMatrix = null;

    this.m_r = new Randomizer();
  }

  /**
   * Create the distance computer. This method will later be overridden in
   * class
   * {@link test.junit.org.logisticPlanning.tsp.benchmarking.instances._InstanceTest}
   * .
   *
   * @param threshold
   *          the threshold up to which a matrix is to be used
   * @return the distance computer
   * @throws Throwable
   *           if something goes wrong
   */
  protected DistanceComputer createDistanceComputer(final int threshold)
      throws Throwable {
    return null;
  }

  /**
   * Get the matrix-based distance computer for testing.
   *
   * @return the matrix-based distance computer
   */
  protected synchronized final DistanceComputer getDistanceComputerMat() {
    if (this.m_distMatrix == null) {
      try {
        this.m_distMatrix = this.createDistanceComputer(Integer.MAX_VALUE);
      } catch (final Throwable x) {
        if (x instanceof IllegalArgumentException) {
          // this error may be caused if the problem dimension exceeds
          // the
          // maximum allowed dimension for distance matrixes.
          return this.getDistanceComputerNoMat();
        }
        if (x instanceof OutOfMemoryError) {
          // this error may be caused if the matrix doesn't fit into
          // memory
          return this.getDistanceComputerNoMat();
        }
        throw new RuntimeException(x);
      }
    }
    return this.m_distMatrix;
  }

  /**
   * Get the raw distance computer for testing.
   *
   * @return the raw distance computer
   */
  protected synchronized final DistanceComputer getDistanceComputerNoMat() {
    if (this.m_distNoMatrix == null) {
      try {
        this.m_distNoMatrix = this.createDistanceComputer(0);
      } catch (final Throwable x) {
        throw new RuntimeException(x);
      }
    }
    return this.m_distNoMatrix;
  }

  /** test whether the distance computer is not null */
  @Test(timeout = 3600000)
  public final void testDistanceComputerNotNull() {
    Assert.assertNotNull(DistanceComputerTest.MATRIX_IS_NULL,
        this.getDistanceComputerMat());
    Assert.assertNotNull(DistanceComputerTest.RAW_IS_NULL,
        this.getDistanceComputerNoMat());
  }

  /** test whether the distance computer has a sane dimension */
  @Test(timeout = 3600000)
  public final void testDistanceComputerDimensionSane() {
    final int n, m;

    n = this.getDistanceComputerMat().n();
    Assert.assertTrue(DistanceComputerTest.MATRIX_WRONG_DIMENSION, n > 1);
    m = this.getDistanceComputerNoMat().n();
    Assert.assertTrue(DistanceComputerTest.RAW_WRONG_DIMENSION, n > 1);
    Assert.assertEquals(DistanceComputerTest.DISAGREE_DIMENSION, n, m);
  }

  /** test whether the distance computer returns sane distances */
  @Test(timeout = 3600000)
  public final void testDistanceComputerDistancesSane() {
    int v1, v2;
    long os1, sum1, os2, sum2;
    final DistanceComputer d1, d2;
    final int n;

    d1 = this.getDistanceComputerMat();
    Assert.assertNotNull(DistanceComputerTest.MATRIX_IS_NULL, d1);
    d2 = this.getDistanceComputerNoMat();
    Assert.assertNotNull(DistanceComputerTest.RAW_IS_NULL, d2);

    n = d1.n();
    Assert
        .assertEquals(DistanceComputerTest.DISAGREE_DIMENSION, n, d2.n());
    for (int i = n; i > 0; i--) {
      sum1 = sum2 = 0l;
      for (int j = n; j > 0; j--) {
        v1 = d1.distance(i, j);
        v2 = d2.distance(i, j);

        if (i == j) {
          Assert.assertTrue(
              DistanceComputerTest.MATRIX_WRONG_POINT_DISTANCE, v1 == 0);
          Assert.assertTrue(DistanceComputerTest.RAW_WRONG_POINT_DISTANCE,
              v2 == 0);
        } else {
          Assert.assertTrue(
              DistanceComputerTest.MATRIX_WRONG_POINT_DISTANCE, v1 >= 0);
          Assert.assertTrue(DistanceComputerTest.RAW_WRONG_POINT_DISTANCE,
              v2 >= 0);
        }

        Assert.assertEquals(DistanceComputerTest.DISAGREE_POINT_DISTANCE,
            v1, v2);
        os1 = sum1;
        sum1 += v1;
        Assert.assertTrue(
            DistanceComputerTest.MATRIX_WRONG_POINT_DISTANCE, sum1 >= os1);

        os2 = sum2;
        sum2 += v2;
        Assert.assertTrue(DistanceComputerTest.RAW_WRONG_POINT_DISTANCE,
            sum2 >= os2);
      }
    }
  }

  /**
   * Test whether the result of direct evaluation is the same as the result
   * of adding up distances.
   *
   * @throws Throwable
   *           if loading fails
   */
  @Test(timeout = 3600000)
  public final void testDistanceComputerEvaluate() throws Throwable {
    int[] tour;
    int n;
    int o, va, vb, z;
    long l1, l2a, l2b, ola, olb;
    final DistanceComputer d1, d2;

    d1 = this.getDistanceComputerMat();
    Assert.assertNotNull(DistanceComputerTest.MATRIX_IS_NULL, d1);
    d2 = this.getDistanceComputerNoMat();
    Assert.assertNotNull(DistanceComputerTest.RAW_IS_NULL, d2);
    n = d1.n();
    Assert
        .assertEquals(DistanceComputerTest.DISAGREE_DIMENSION, n, d2.n());
    tour = PermutationCreateCanonical.canonical(n);

    for (z = DistanceComputerTest.getRequiredTests(n);;) {
      l1 = d1.evaluate(tour);
      Assert.assertEquals(DistanceComputerTest.DISAGREE_EVAL, l1,
          d2.evaluate(tour));

      o = tour[n - 1];
      l2a = l2b = 0l;
      for (final int nn : tour) {
        ola = l2a;
        va = d1.distance(o, nn);
        l2a += va;
        Assert.assertTrue(
            DistanceComputerTest.MATRIX_WRONG_POINT_DISTANCE, l2a >= ola);

        olb = l2b;
        vb = d2.distance(o, nn);
        l2b += vb;
        Assert.assertTrue(DistanceComputerTest.RAW_WRONG_POINT_DISTANCE,
            l2b >= olb);

        Assert.assertEquals(DistanceComputerTest.DISAGREE_POINT_DISTANCE,
            va, vb);

        o = nn;
      }

      Assert.assertEquals(DistanceComputerTest.MATRIX_WRONG_EVAL, l1, l2a);
      Assert.assertEquals(DistanceComputerTest.RAW_WRONG_EVAL, l1, l2b);

      if ((--z) <= 0) {
        break;
      }

      synchronized (this.m_r) {
        this.m_r.shuffle(tour, 0, tour.length);
      }
      // PermutationCreateUniform.randomize(tour, TestBase.RANDOM);
    }
  }

  /**
   * get the number of required random tests
   *
   * @param n
   *          the problem dimension
   * @return the number of required random tests
   */
  public static final int getRequiredTests(final int n) {
    return Math.min(DistanceComputerTest.MAX_TESTS,
        Math.max(n, DistanceComputerTest.MIN_TESTS));
  }
}
