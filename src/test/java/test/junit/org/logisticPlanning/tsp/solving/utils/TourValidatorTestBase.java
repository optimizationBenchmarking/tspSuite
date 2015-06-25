package test.junit.org.logisticPlanning.tsp.solving.utils;

import java.util.Arrays;

import org.junit.Assert;
import org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer;
import org.logisticPlanning.tsp.solving.utils.SolutionValidator;
import org.logisticPlanning.tsp.solving.utils.edge.Edge;

import test.junit.TestBase;

/**
 * This internal class provides some basic methods to validate
 * permutations.
 */
public class TourValidatorTestBase extends TestBase {

  /** a temporary variable */
  private transient boolean[] m_used;
  /** a temporary variable */
  private transient int[] m_perm;

  /** the package-internal constructor */
  protected TourValidatorTestBase() {
    super();
  }

  /**
   * Validate a permutation
   *
   * @param permutation
   *          the permutation
   * @param dc
   *          the distance computer, or {@code null} if no distance checks
   *          need to be performed
   * @return the number of nodes in the permutation
   */
  protected static final int initValidate(final int[] permutation,
      final DistanceComputer dc) {
    final int n;

    Assert.assertNotNull("The permutation must not be null.",//$NON-NLS-1$
        permutation);

    if (dc != null) {
      n = dc.n();
      Assert
          .assertEquals(//
              "The length of the permutation must be the same as the length provided by the distance computer.", //$NON-NLS-1$
              n, permutation.length);

      Assert
          .assertTrue(//
              "The length provided by the distance computer must be greater than 0.", //$NON-NLS-1$
              (n > 0));
      return n;
    }

    n = permutation.length;
    Assert.assertTrue(//
        "The length of the permutation must be greater than 0.", //$NON-NLS-1$
        (n > 0));
    return n;
  }

  /**
   * validate a array
   *
   * @param array
   *          the array
   * @param dc
   *          the distance computer, or {@code null} if no distance checks
   *          need to be performed
   * @return the number of nodes in the permutation
   */
  protected static final int initValidate(final Object[] array,
      final DistanceComputer dc) {
    final int n;

    Assert.assertNotNull("The array must not be null.",//$NON-NLS-1$
        array);

    if (dc != null) {
      n = dc.n();
      Assert
          .assertEquals(//
              "The length of the array must be the same as the length provided by the distance computer.", //$NON-NLS-1$
              n, array.length);

      Assert
          .assertTrue(//
              "The length provided by the distance computer must be greater than 0.", //$NON-NLS-1$
              (n > 0));
      return n;
    }

    n = array.length;
    Assert.assertTrue(//
        "The length of the array must be greater than 0.", //$NON-NLS-1$
        (n > 0));
    return n;
  }

  /**
   * Validate a permutation.
   *
   * @param path
   *          the path permutation
   * @param dc
   *          the distance computer, or {@code null} if no distance checks
   *          need to be performed
   * @return the length of the tour, if {@code dc} is provided, {@code 0l}
   *         otherwise
   */
  protected final long validatePath(final int[] path,
      final DistanceComputer dc) {
    final int n;
    int last, d;
    long sum;
    boolean[] used;

    n = TourValidatorTestBase.initValidate(path, dc);

    used = this.m_used;
    if ((used == null) || (used.length != n)) {
      this.m_used = used = new boolean[n];
    } else {
      Arrays.fill(used, 0, n, false);
    }

    last = path[n - 1];
    sum = 0l;
    for (final int station : path) {

      Assert.assertTrue(//
          "All node ids must be greater than zero.", //$NON-NLS-1$
          (station > 0));

      Assert
          .assertTrue(//
              "All node ids must be less or equal to the total number of nodes.", //$NON-NLS-1$
              (station <= n));

      Assert.assertFalse(//
          "The best tour must not contain any node more than once.", //$NON-NLS-1$
          used[station - 1]);

      used[station - 1] = true;

      if (dc != null) {
        d = dc.distance(last, station);
        Assert.assertTrue(//
            "All point distances must be greater or equal to zero.", //$NON-NLS-1$
            (d >= 0l));

        sum += d;
        last = station;
      }
    }

    if (dc != null) {
      Assert
          .assertEquals(
              "The distance sum returned by \"evaluate\" of the distance computer must equal to the sum of the edge lengths computed via \"distance\".", //$NON-NLS-1$
              sum, dc.evaluate(path));
    }

    SolutionValidator.validatePath(path, -1l, dc);

    return sum;
  }

  /**
   * Validate an adjacency list. An adjacency list is an integer array
   * where the element at index {@code (i-1)} stands for the city {@code i}
   * and holds the index of the city which should appear after that
   * element.
   *
   * @param adjacencyList
   *          the adjacencyList
   * @param dc
   *          the distance computer, or {@code null} if no distance checks
   *          need to be performed
   * @return the length of the tour, if {@code dc} is provided, {@code 0l}
   *         otherwise
   */
  protected final long validateAdjacencyList(final int[] adjacencyList,
      final DistanceComputer dc) {
    final int n;
    int last, d, count, station;
    long sum;
    boolean[] used;

    n = TourValidatorTestBase.initValidate(adjacencyList, dc);

    used = this.m_used;
    if ((used == null) || (used.length != n)) {
      this.m_used = used = new boolean[n];
    } else {
      Arrays.fill(used, 0, n, false);
    }

    last = 1;
    count = 0;
    sum = 0l;
    for (;;) {
      station = adjacencyList[last - 1];

      count++;
      Assert.assertTrue(//
          "The adjacency list must not contain a cycle shorter than n.", //$NON-NLS-1$
          (count <= n));

      Assert.assertTrue(//
          "All node ids must be greater than zero.", //$NON-NLS-1$
          (station > 0));

      Assert
          .assertTrue(//
              "All node ids must be less or equal to the total number of nodes.", //$NON-NLS-1$
              (station <= n));

      Assert.assertFalse(//
          "The best tour must not contain any node more than once.", //$NON-NLS-1$
          used[station - 1]);

      used[station - 1] = true;

      if (dc != null) {
        d = dc.distance(last, station);
        Assert.assertTrue(//
            "All point distances must be greater or equal to zero.", //$NON-NLS-1$
            (d >= 0l));

        sum += d;
      }

      if (station == 1) {
        break;
      }

      last = station;
    }

    Assert
        .assertFalse(//
            "The adjacency list must be a cycle of exactly length n, but it is shorter.", //$NON-NLS-1$
            (count < n));

    Assert
        .assertFalse(// should not happen, but this is a test, so who
            // cares
            "The adjacency list must be a cycle of exactly length n, but it is longer.", //$NON-NLS-1$
            (count > n));

    if (dc != null) {
      Assert
          .assertEquals(
              "The distance sum returned by \"evaluateAdj\" of the distance computer must equal to the sum of the edge lengths computed via \"distance\".", //$NON-NLS-1$
              sum, dc.evaluateAdj(adjacencyList));
    }

    return sum;
  }

  /**
   * Validate a edge list, i.e., a list of undirected edges.
   *
   * @param edges
   *          the edges
   * @param dc
   *          the distance computer, or {@code null} if no distance checks
   *          need to be performed
   * @return the length of the tour, if {@code dc} is provided, {@code 0l}
   *         otherwise
   */
  protected final long validateEdgeList(final Edge[] edges,
      final DistanceComputer dc) {
    final int n;
    int a, b, d;
    long sum;
    int[] adjacencyList;

    n = TourValidatorTestBase.initValidate(edges, dc);

    adjacencyList = this.m_perm;
    if ((adjacencyList == null) || (adjacencyList.length != n)) {
      this.m_perm = adjacencyList = new int[n];
    } else {
      Arrays.fill(adjacencyList, 0, n, 0);
    }

    sum = 0l;
    for (final Edge e : edges) {

      Assert.assertNotNull("No edge must be null.", e);//$NON-NLS-1$
      a = e.a;
      b = e.b;
      Assert.assertTrue(//
          "All start node ids must be greater than zero.", //$NON-NLS-1$
          (a > 0));

      Assert
          .assertTrue(//
              "All start node ids must be less or equal to the total number of nodes.", //$NON-NLS-1$
              (a <= n));

      Assert.assertTrue(//
          "All end node ids must be greater than zero.", //$NON-NLS-1$
          (b > 0));

      Assert
          .assertTrue(//
              "All end node ids must be less or equal to the total number of nodes.", //$NON-NLS-1$
              (b <= n));

      if (adjacencyList[a - 1] == 0) {
        adjacencyList[a - 1] = b;
      } else {
        Assert.assertTrue(
            "There must be at most 2 edges connecting to a node, but found edge " //$NON-NLS-1$
                + e, adjacencyList[b - 1] == 0);
        adjacencyList[b - 1] = a;
      }

      if (dc != null) {
        d = dc.distance(a, b);
        Assert.assertTrue(//
            "All point distances must be greater or equal to zero.", //$NON-NLS-1$
            (d >= 0l));

        sum += d;
      }
    }

    this.validateAdjacencyList(adjacencyList, dc);

    return sum;
  }
}
