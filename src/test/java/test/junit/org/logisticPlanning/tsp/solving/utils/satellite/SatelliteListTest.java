package test.junit.org.logisticPlanning.tsp.solving.utils.satellite;

import org.junit.Assert;
import org.junit.Test;
import org.logisticPlanning.tsp.solving.operators.permutation.creation.PermutationCreateUniform;
import org.logisticPlanning.tsp.solving.utils.RepresentationUtils;
import org.logisticPlanning.tsp.solving.utils.satelliteList.SatelliteList;
import org.logisticPlanning.tsp.solving.utils.satelliteList.SatelliteNode;
import org.logisticPlanning.utils.math.random.Randomizer;

import test.junit.TestBase;
import test.junit.org.logisticPlanning.tsp.PermutationIterator;

/**
 * Test the basic satellite list.
 */
public class SatelliteListTest extends TestBase {

  /** create */
  public SatelliteListTest() {
    super();
  }

  /**
   * create a virgin satellite list
   *
   * @param n
   *          the number of nodes
   * @return satellite list
   */
  protected SatelliteList<? extends SatelliteNode> createList(final int n) {
    return new SatelliteList<>(n);
  }

  /** test the path conversation */
  @Test(timeout = 3600000)
  public void testFromToPath() {
    final Randomizer r;
    SatelliteList<? extends SatelliteNode> list;
    SatelliteNode x;
    int i, j, k, n;
    int[] a, b;

    r = new Randomizer();

    for (i = 2; i <= 500; i++) {
      if (i < 20) {
        n = i;
      } else {
        n = (2 + r.nextInt(1000));
      }

      list = this.createList(n);
      b = new int[n];
      Assert.assertFalse(list.toPath(b));

      for (j = 30; (--j) >= 0;) {
        a = PermutationCreateUniform.create(n, r);
        list.fromPath(a);

        for (k = list.n(); k > 0; k--) {
          x = list.getNode(k);
          Assert.assertTrue(x.equals(x));
          Assert.assertEquals(x.id, k);
          Assert.assertNotNull(x.getNeighbor(1));
          Assert.assertNotNull(x.getNeighbor(0));
          Assert.assertFalse(x.equals(list.getNode(((k + 1) > n) ? 1
              : (k + 1))));
          Assert.assertFalse(x.equals(x.getNeighbor(0)));
          Assert.assertFalse(x.equals(x.getNeighbor(1)));
        }

        Assert.assertTrue(list.toPath(b));
        Assert
            .assertTrue(RepresentationUtils.arePathsEquivalentSTSP(a, b));
      }
    }
  }

  /** test the path conversation */
  @Test(timeout = 3600000)
  public void testFromToPath2() {
    final Randomizer r;
    SatelliteList<? extends SatelliteNode> list;
    SatelliteNode x;
    final PermutationIterator it;
    int i, k;
    int[] a, b;

    r = new Randomizer();
    it = new PermutationIterator(1, true, r);
    list = this.createList(1);
    b = new int[1];

    for (i = 1; i <= 5000000; i++) {
      a = it.next();
      if (list.n() != a.length) {
        list = this.createList(a.length);
        b = new int[a.length];
      }
      list.fromPath(a);

      for (k = list.n(); k > 0; k--) {
        x = list.getNode(k);
        Assert.assertTrue(x.equals(x));
        Assert.assertEquals(x.id, k);
        Assert.assertNotNull(x.getNeighbor(1));
        Assert.assertNotNull(x.getNeighbor(0));
        if (a.length > 1) {
          Assert.assertFalse(x.equals(list
              .getNode(((k + 1) > a.length) ? 1 : (k + 1))));
          Assert.assertFalse(x.equals(x.getNeighbor(0)));
          Assert.assertFalse(x.equals(x.getNeighbor(1)));
        }
      }

      Assert.assertTrue(list.toPath(b));
      Assert.assertTrue(RepresentationUtils.arePathsEquivalentSTSP(a, b));
    }
  }
}
