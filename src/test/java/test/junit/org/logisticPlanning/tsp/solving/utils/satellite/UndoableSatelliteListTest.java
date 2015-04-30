package test.junit.org.logisticPlanning.tsp.solving.utils.satellite;

import org.junit.Assert;
import org.junit.Test;
import org.logisticPlanning.tsp.solving.utils.RepresentationUtils;
import org.logisticPlanning.tsp.solving.utils.satelliteList.UndoableSatelliteList;
import org.logisticPlanning.tsp.solving.utils.satelliteList.UndoableSatelliteNode;

/**
 * Test the undoable satellite list.
 */
public class UndoableSatelliteListTest extends SatelliteListTest {

  /** create */
  public UndoableSatelliteListTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected UndoableSatelliteList<? extends UndoableSatelliteNode> createList(
      final int n) {
    return new UndoableSatelliteList<>(n);
  }

  /** test an example */
  @Test(timeout = 3600000)
  public void testExample() {
    final UndoableSatelliteList<? extends UndoableSatelliteNode> list;
    final int[] exampleA, exampleB, result;
    final int[][] perm4;
    UndoableSatelliteNode a, b, c;
    int j, i;

    exampleA = new int[] { 1, 2, 3, 4, 5, 6, 7, 8 };
    exampleB = new int[] { 1, 2, 4, 3, 5, 6, 7, 8 };

    perm4 = new int[][] { { 0, 1, 2, 3 }, { 0, 1, 3, 2 }, { 0, 2, 1, 3 },
        { 0, 2, 3, 1 }, { 0, 3, 1, 2 }, { 0, 3, 2, 1 }, { 1, 0, 2, 3 },
        { 1, 0, 3, 2 }, { 1, 2, 0, 3 }, { 1, 2, 3, 0 }, { 1, 3, 0, 2 },
        { 1, 3, 2, 0 }, { 2, 0, 1, 3 }, { 2, 0, 3, 1 }, { 2, 1, 0, 3 },
        { 2, 1, 3, 0 }, { 2, 3, 0, 1 }, { 2, 3, 1, 0 }, { 3, 0, 1, 2 },
        { 3, 0, 2, 1 }, { 3, 1, 0, 2 }, { 3, 1, 2, 0 }, { 3, 2, 0, 1 },
        { 3, 2, 1, 0 }, };

    list = this.createList(exampleA.length);
    result = new int[exampleA.length];

    for (j = 1; j >= 0; j--) {
      list.fromPath(exampleA);
      Assert.assertTrue(list.toPath(result));
      Assert.assertTrue(//
          RepresentationUtils.arePathsEquivalentSTSP(exampleA,//
              result));

      for (i = 1; i >= 0; i--) {
        if (i == j) {
          a = list.getNode(2);
          b = list.getNode(3);
          c = list.getNode(5);
          a.doDisconnect(b);
          b.doDisconnect(a);
          b.doConnect(c);
          c.doConnect(b);
        } else {
          a = list.getNode(5);
          b = list.getNode(4);
          c = list.getNode(2);
          a.doDisconnect(b);
          b.doDisconnect(a);
          b.doConnect(c);
          c.doConnect(b);
        }
      }

      Assert.assertTrue(list.toPath(result));
      Assert.assertTrue(//
          RepresentationUtils.arePathsEquivalentSTSP(exampleB,//
              result));
      list.commit();
      Assert.assertTrue(list.toPath(result));
      Assert.assertTrue(//
          RepresentationUtils.arePathsEquivalentSTSP(exampleB,//
              result));
    }

    for (final int[] order : perm4) {

      list.fromPath(exampleA);
      Assert.assertTrue(list.toPath(result));
      Assert.assertTrue(//
          RepresentationUtils.arePathsEquivalentSTSP(exampleA,//
              result));
      a = list.getNode(2);
      b = list.getNode(3);
      c = list.getNode(5);
      a.doDisconnect(b);
      b.doDisconnect(a);
      b.doConnect(c);
      c.doConnect(b);
      a = list.getNode(5);
      b = list.getNode(4);
      c = list.getNode(2);
      a.doDisconnect(b);
      b.doDisconnect(a);
      b.doConnect(c);
      c.doConnect(b);
      list.commit();
      Assert.assertTrue(list.toPath(result));
      Assert.assertTrue(//
          RepresentationUtils.arePathsEquivalentSTSP(exampleB,//
              result));

      for (final int o : order) {
        switch (o) {
          case 0: {
            a = list.getNode(5);
            b = list.getNode(3);
            a.doDisconnect(b);
            b.doDisconnect(a);
            break;
          }

          case 1: {
            a = list.getNode(5);
            b = list.getNode(4);
            a.doConnect(b);
            b.doConnect(a);
            break;
          }

          case 2: {
            a = list.getNode(2);
            b = list.getNode(4);
            a.doDisconnect(b);
            b.doDisconnect(a);
            break;
          }

          default: {
            a = list.getNode(2);
            b = list.getNode(3);
            a.doConnect(b);
            b.doConnect(a);
            break;
          }
        }
      }

      Assert.assertTrue(list.toPath(result));
      Assert.assertTrue(//
          RepresentationUtils.arePathsEquivalentSTSP(exampleA,//
              result));
      list.commit();
      Assert.assertTrue(list.toPath(result));
      Assert.assertTrue(//
          RepresentationUtils.arePathsEquivalentSTSP(exampleA,//
              result));

    }

    list.fromPath(exampleA);
    Assert.assertTrue(list.toPath(result));
    Assert.assertTrue(//
        RepresentationUtils.arePathsEquivalentSTSP(exampleA,//
            result));
    list.commit();
    Assert.assertTrue(list.toPath(result));
    Assert.assertTrue(//
        RepresentationUtils.arePathsEquivalentSTSP(exampleA,//
            result));

    a = list.getNode(2);
    b = list.getNode(3);
    c = list.getNode(5);
    a.doDisconnect(b);
    b.doDisconnect(a);
    b.doConnect(c);
    c.doConnect(b);

    a = list.getNode(5);
    b = list.getNode(6);
    c = list.getNode(2);
    a.doDisconnect(b);
    b.doDisconnect(a);
    b.doConnect(c);
    c.doConnect(b);

    Assert.assertFalse(list.toPath(result));

    for (j = 1; j >= 0; j--) {
      list.fromPath(exampleA);
      Assert.assertTrue(list.toPath(result));
      Assert.assertTrue(//
          RepresentationUtils.arePathsEquivalentSTSP(exampleA,//
              result));

      for (i = 1; i >= 0; i--) {
        if (i == j) {
          a = list.getNode(2);
          b = list.getNode(3);
          c = list.getNode(5);
          a.doDisconnect(b);
          b.doDisconnect(a);
          b.doConnect(c);
          c.doConnect(b);
        } else {
          a = list.getNode(5);
          b = list.getNode(4);
          c = list.getNode(2);
          a.doDisconnect(b);
          b.doDisconnect(a);
          b.doConnect(c);
          c.doConnect(b);
        }
      }

      for (i = 1; i >= 0; i--) {
        if (i == j) {
          a = list.getNode(2);
          b = list.getNode(3);
          c = list.getNode(5);

          c.undoConnect(b);
          b.undoConnect(c);
          b.undoDisconnect(a);
          a.undoDisconnect(b);
        } else {
          a = list.getNode(5);
          b = list.getNode(4);
          c = list.getNode(2);
          c.undoConnect(b);
          b.undoConnect(c);
          b.undoDisconnect(a);
          a.undoDisconnect(b);
        }
      }

      Assert.assertTrue(list.toPath(result));
      Assert.assertTrue(//
          RepresentationUtils.arePathsEquivalentSTSP(exampleA,//
              result));
      list.commit();
      Assert.assertTrue(list.toPath(result));
      Assert.assertTrue(//
          RepresentationUtils.arePathsEquivalentSTSP(exampleA,//
              result));
    }

    for (final int[] order : perm4) {

      list.fromPath(exampleA);
      Assert.assertTrue(list.toPath(result));
      Assert.assertTrue(//
          RepresentationUtils.arePathsEquivalentSTSP(exampleA,//
              result));
      a = list.getNode(2);
      b = list.getNode(3);
      c = list.getNode(5);
      a.doDisconnect(b);
      b.doDisconnect(a);
      b.doConnect(c);
      c.doConnect(b);
      a = list.getNode(5);
      b = list.getNode(4);
      c = list.getNode(2);
      a.doDisconnect(b);
      b.doDisconnect(a);
      b.doConnect(c);
      c.doConnect(b);
      list.commit();
      Assert.assertTrue(list.toPath(result));
      Assert.assertTrue(//
          RepresentationUtils.arePathsEquivalentSTSP(exampleB,//
              result));

      for (final int o : order) {
        switch (o) {
          case 0: {
            a = list.getNode(5);
            b = list.getNode(3);
            a.doDisconnect(b);
            b.doDisconnect(a);
            break;
          }

          case 1: {
            a = list.getNode(5);
            b = list.getNode(4);
            a.doConnect(b);
            b.doConnect(a);
            break;
          }

          case 2: {
            a = list.getNode(2);
            b = list.getNode(4);
            a.doDisconnect(b);
            b.doDisconnect(a);
            break;
          }

          default: {
            a = list.getNode(2);
            b = list.getNode(3);
            a.doConnect(b);
            b.doConnect(a);
            break;
          }
        }
      }

      for (final int o : order) {
        switch (o) {
          case 0: {
            a = list.getNode(5);
            b = list.getNode(3);
            a.undoDisconnect(b);
            b.undoDisconnect(a);
            break;
          }

          case 1: {
            a = list.getNode(5);
            b = list.getNode(4);
            a.undoConnect(b);
            b.undoConnect(a);
            break;
          }

          case 2: {
            a = list.getNode(2);
            b = list.getNode(4);
            a.undoDisconnect(b);
            b.undoDisconnect(a);
            break;
          }

          default: {
            a = list.getNode(2);
            b = list.getNode(3);
            a.undoConnect(b);
            b.undoConnect(a);
            break;
          }
        }
      }

      Assert.assertTrue(list.toPath(result));
      Assert.assertTrue(//
          RepresentationUtils.arePathsEquivalentSTSP(exampleB,//
              result));
      list.commit();
      Assert.assertTrue(list.toPath(result));
      Assert.assertTrue(//
          RepresentationUtils.arePathsEquivalentSTSP(exampleB,//
              result));

    }
  }
}
