package test.junit.org.logisticPlanning.tsp.solving.utils;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.logisticPlanning.tsp.solving.utils.RepresentationUtils;
import org.logisticPlanning.tsp.solving.utils.edge.Edge;
import org.logisticPlanning.utils.math.random.Randomizer;

import test.junit.org.logisticPlanning.tsp.PermutationIterator;

/**
 * A test for the static functionality of the objective function.
 */
public final class RepresentationUtilsTest extends TourValidatorTestBase {

  /** instantiate */
  public RepresentationUtilsTest() {
    super();
  }

  /**
   * Test the translation of path representations to adjacency lists and to
   * and from edge lists and vice versa.
   */
  @Test(timeout = 3600000)
  public void testPathAndAdjacencyListAndEdgeList() {
    int i, j, n;
    final Randomizer r;
    final PermutationIterator it;
    int[] v, perm, adj, adj2, perm2;
    Edge[] edges;

    r = new Randomizer();
    it = new PermutationIterator(2, true, r);

    perm = new int[2];
    perm2 = new int[2];
    adj = new int[2];
    adj2 = new int[2];
    edges = new Edge[] { new Edge(), new Edge() };

    for (i = 100000; (--i) >= 1;) {
      v = it.next();
      n = v.length;
      if (n != perm.length) {
        perm = new int[n];
        perm2 = new int[n];
        adj = new int[n];
        adj2 = new int[n];
        edges = new Edge[n];
        for (j = n; (--j) >= 0;) {
          edges[j] = new Edge();
        }
      }

      System.arraycopy(v, 0, perm, 0, n);

      for (j = 10; (--j) >= 0;) {

        // create a random, valid permutation
        r.shuffle(perm, 0, n);
        this.validatePath(perm, null);

        // test the conversion from permutation to adjacency list
        Arrays.fill(adj, -1);
        RepresentationUtils.pathToAdjacencyList(perm, adj);
        Assert.assertTrue(//
            RepresentationUtils.arePathAndAdjacencyListEquivalentSTSP(
                perm, adj));
        this.validateAdjacencyList(adj, null);

        // test the conversion from adjacency list to permutation
        Arrays.fill(perm, -1);
        RepresentationUtils.adjacencyListToPath(adj, perm);
        Assert.assertTrue(RepresentationUtils
            .arePathAndAdjacencyListEquivalentSTSP(perm, adj));
        this.validatePath(perm, null);

        // test the conversion from permutation to adjacency list
        Arrays.fill(adj2, -1);
        RepresentationUtils.pathToAdjacencyList(perm, adj2);
        Assert.assertTrue(RepresentationUtils
            .arePathAndAdjacencyListEquivalentSTSP(perm, adj2));
        this.validateAdjacencyList(adj2, null);
        Assert
        .assertTrue(//
            "Each permutation has exactly one unique adjacency list representation.", //$NON-NLS-1$
            Arrays.equals(adj, adj2));

        // test the conversion from adjacency list to permutation
        Arrays.fill(perm2, -1);
        RepresentationUtils.adjacencyListToPath(adj2, perm2);
        Assert.assertTrue(RepresentationUtils
            .arePathAndAdjacencyListEquivalentSTSP(perm2, adj2));
        this.validatePath(perm2, null);
        Assert
        .assertTrue(//
            "Each adjacency list has exactly one unique canonical permutation representation.", //$NON-NLS-1$
            Arrays.equals(perm, perm2));

      }

      // test the conversion from paths to edge lists to adjacency lists
      RepresentationUtilsTest.__invalidateEdges(edges);
      RepresentationUtils.pathToEdges(perm2, edges);
      this.validateEdgeList(edges, null);

      Arrays.fill(adj2, -1);
      RepresentationUtils.directedEdgesToAdjacencyList(edges, adj2);
      this.validateAdjacencyList(adj2, null);

      Assert.assertTrue(RepresentationUtils
          .arePathAndAdjacencyListEquivalentSTSP(perm, adj2));

      // test the conversion from adjacency lists to edge lists to paths
      RepresentationUtilsTest.__invalidateEdges(edges);
      RepresentationUtils.adjacencyListToEdges(adj, edges);
      this.validateEdgeList(edges, null);

      Arrays.fill(adj2, -1);
      RepresentationUtils.undirectedEdgesToAdjacencyList(edges, adj2);
      this.validateAdjacencyList(adj2, null);

      Assert.assertTrue(RepresentationUtils
          .arePathAndAdjacencyListEquivalentSTSP(perm, adj2));

      // test the conversion from paths to edge lists to adjacency lists
      RepresentationUtilsTest.__invalidateEdges(edges);
      RepresentationUtils.pathToEdges(perm2, edges);
      this.validateEdgeList(edges, null);

      Arrays.fill(adj2, -1);
      RepresentationUtils.undirectedEdgesToAdjacencyList(
          Arrays.asList(edges), adj2);
      this.validateAdjacencyList(adj2, null);

      Assert.assertTrue(RepresentationUtils
          .arePathAndAdjacencyListEquivalentSTSP(perm, adj2));

      // test the conversion from adjacency lists to edge lists to paths
      RepresentationUtilsTest.__invalidateEdges(edges);
      RepresentationUtils.adjacencyListToEdges(adj, edges);
      this.validateEdgeList(edges, null);

      Arrays.fill(adj2, -1);
      RepresentationUtils.undirectedEdgesToAdjacencyList(
          Arrays.asList(edges), adj2);
      this.validateAdjacencyList(adj2, null);

      Assert.assertTrue(RepresentationUtils
          .arePathAndAdjacencyListEquivalentSTSP(perm, adj2));

    }
  }

  /** Test if the equivalence checker routines are correct */
  @Test(timeout = 3600000)
  public final void equivalenceTest() {
    PermutationIterator it1, it2;
    int[] perm1, perm2, perm3, perm4;
    int depth;
    boolean pathEq;

    for (depth = 1; depth <= 8; depth++) {
      it1 = new PermutationIterator(depth, false, null);
      perm3 = new int[depth];
      perm4 = new int[depth];

      while (it1.hasNext()) {
        perm1 = it1.next();

        RepresentationUtils.pathToAdjacencyList(perm1, perm3);
        this.validateAdjacencyList(perm3, null);

        Assert.assertTrue(//
            RepresentationUtils.arePathAndAdjacencyListEquivalentSTSP(
                perm1, perm3));

        RepresentationUtils.adjacencyListToPath(perm3, perm4);
        this.validatePath(perm4, null);
        Assert.assertTrue(//
            RepresentationUtils.arePathsEquivalentSTSP(perm1, perm4));
        Assert.assertTrue(//
            RepresentationUtils.arePathsEquivalentSTSP(perm4, perm1));

        it2 = new PermutationIterator(depth, false, null);
        while (it2.hasNext()) {
          perm2 = it2.next();

          Assert.assertTrue(//
              RepresentationUtils.areAdjacencyListsEquivalentSTSP(perm1,
                  perm2) == //
                  RepresentationUtils.areAdjacencyListsEquivalentSTSP(perm2,
                      perm1));

          pathEq = RepresentationUtils
              .arePathsEquivalentSTSP(perm1, perm2);
          Assert.assertTrue(pathEq == RepresentationUtils
              .arePathsEquivalentSTSP(perm2, perm1));

          if (pathEq) {
            Assert.assertEquals(//
                RepresentationUtils.pathHashCode(perm1),//
                RepresentationUtils.pathHashCode(perm2));
          }

          Assert.assertTrue(//
              RepresentationUtils.areAdjacencyListsEquivalentSTSP(perm3,
                  perm2) == RepresentationUtils
                  .arePathAndAdjacencyListEquivalentSTSP(perm1, perm2));

          Assert.assertTrue(//
              RepresentationUtils.areAdjacencyListsEquivalentSTSP(perm2,
                  perm3) == RepresentationUtils
                  .arePathAndAdjacencyListEquivalentSTSP(perm1, perm2));
        }
      }
    }

  }

  /**
   * invalidate an array of edges
   *
   * @param edges
   *          the edges
   */
  private static final void __invalidateEdges(final Edge[] edges) {
    for (final Edge e : edges) {
      e.a = -1;
      e.b = -1;
    }

  }
}
