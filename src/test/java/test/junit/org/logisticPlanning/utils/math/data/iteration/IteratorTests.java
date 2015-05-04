package test.junit.org.logisticPlanning.utils.math.data.iteration;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Assert;
import org.junit.Test;
import org.logisticPlanning.utils.math.data.collection.ListCollection;
import org.logisticPlanning.utils.math.data.iteration.BoundsReducingIterator2D;
import org.logisticPlanning.utils.math.data.iteration.DataCollectionIterator2D;
import org.logisticPlanning.utils.math.data.iteration.StairsKeepLeftIterator2D;
import org.logisticPlanning.utils.math.data.iteration.StraightReducingIterator2D;
import org.logisticPlanning.utils.math.data.point.Point2D;

import test.junit.TestBase;

/**
 * The test of the line builder 2d
 */
public class IteratorTests extends TestBase {

  /** create */
  public IteratorTests() {
    super();
  }

  /** test the iteration 1 */
  @Test(timeout = 3600000)
  public void testStraightReducingIterator2D() {
    final ArrayList<Point2D> list;
    final Point2D leftEnd, rightEnd;
    final Iterator<Point2D> it;

    leftEnd = new Point2D(Double.NEGATIVE_INFINITY,
        Double.NEGATIVE_INFINITY);
    rightEnd = new Point2D(Double.POSITIVE_INFINITY,
        Double.POSITIVE_INFINITY);

    list = new ArrayList<>();
    list.add(new Point2D(0, 0));// 0
    list.add(new Point2D(1, 1));// 1
    list.add(new Point2D(2, 3));// 2
    list.add(new Point2D(3, 3));// 3
    list.add(new Point2D(4, 4));// 4
    list.add(new Point2D(4, 3));// 5
    list.add(new Point2D(4, 7));// 6
    list.add(new Point2D(4, 5));// 7
    list.add(new Point2D(4, 9));// 8
    list.add(new Point2D(4, 2));// 9
    list.add(new Point2D(5, 5));// 10
    list.add(new Point2D(5, 5));// 11
    list.add(new Point2D(6, 5));// 12
    list.add(new Point2D(7, 5));// 13

    it = new StraightReducingIterator2D(//
        new DataCollectionIterator2D(leftEnd,//
            new ListCollection(list, 2), rightEnd));

    Assert.assertTrue(it.hasNext());
    Assert.assertEquals(leftEnd, it.next());
    for (int i = 0; i <= 4; i++) {
      Assert.assertEquals(list.get(i), it.next());
    }
    Assert.assertEquals(list.get(8), it.next());
    Assert.assertEquals(list.get(9), it.next());
    Assert.assertTrue(it.hasNext());
    Assert.assertEquals(list.get(10), it.next());
    Assert.assertEquals(list.get(13), it.next());
    Assert.assertTrue(it.hasNext());
    Assert.assertEquals(rightEnd, it.next());
    Assert.assertFalse(it.hasNext());
  }

  /** test the iteration 1 */
  @Test(timeout = 3600000)
  public void testStraightReducingIterator2DWithStairs() {
    final ArrayList<Point2D> list;
    final Point2D leftEnd, rightEnd;
    final Iterator<Point2D> it;

    leftEnd = new Point2D(Double.NEGATIVE_INFINITY,
        Double.NEGATIVE_INFINITY);
    rightEnd = new Point2D(Double.POSITIVE_INFINITY,
        Double.POSITIVE_INFINITY);

    list = new ArrayList<>();
    list.add(new Point2D(0, 0));// 0
    list.add(new Point2D(1, 1));// 1
    list.add(new Point2D(2, 3));// 2
    list.add(new Point2D(3, 3));// 3
    list.add(new Point2D(4, 4));// 4
    list.add(new Point2D(4, 3));// 5
    list.add(new Point2D(4, 7));// 6
    list.add(new Point2D(4, 5));// 7
    list.add(new Point2D(4, 9));// 8
    list.add(new Point2D(4, 2));// 9
    list.add(new Point2D(5, 5));// 10
    list.add(new Point2D(5, 5));// 11
    list.add(new Point2D(6, 5));// 12
    list.add(new Point2D(7, 5));// 13

    it = new StraightReducingIterator2D(//
        new StairsKeepLeftIterator2D(//
            new DataCollectionIterator2D(leftEnd,//
                new ListCollection(list, 2), rightEnd)));

    Assert.assertTrue(it.hasNext());
    Assert.assertEquals(leftEnd, it.next());
    Assert.assertEquals(new Point2D(list.get(0).getX(), leftEnd.getY()),//
        it.next());
    Assert.assertEquals(list.get(0), it.next());

    Assert.assertEquals(new Point2D(list.get(1).getX(),//
        list.get(0).getY()),//
        it.next());
    Assert.assertEquals(list.get(1), it.next());

    Assert.assertEquals(new Point2D(list.get(2).getX(),//
        list.get(1).getY()),//
        it.next());
    Assert.assertEquals(list.get(2), it.next());

    Assert.assertEquals(new Point2D(list.get(4).getX(),//
        list.get(3).getY()),//
        it.next());

    Assert.assertEquals(list.get(8), it.next());
    Assert.assertEquals(list.get(9), it.next());
    Assert.assertTrue(it.hasNext());

    Assert.assertEquals(new Point2D(list.get(10).getX(),//
        list.get(9).getY()),//
        it.next());

    Assert.assertEquals(list.get(10), it.next());

    Assert.assertEquals(new Point2D(rightEnd.getX(),//
        list.get(13).getY()),//
        it.next());

    Assert.assertTrue(it.hasNext());
    Assert.assertEquals(rightEnd, it.next());
    Assert.assertFalse(it.hasNext());
  }

  /** test the iteration 1 */
  @Test(timeout = 3600000)
  public void testBoundsReducingIterator2D() {
    final ArrayList<Point2D> list;
    final Point2D leftEnd, rightEnd;
    final Iterator<Point2D> it;

    leftEnd = new Point2D(Double.NEGATIVE_INFINITY,
        Double.NEGATIVE_INFINITY);
    rightEnd = new Point2D(Double.POSITIVE_INFINITY,
        Double.POSITIVE_INFINITY);

    list = new ArrayList<>();
    list.add(new Point2D(0, 0));// 0
    list.add(new Point2D(1, 1));// 1
    list.add(new Point2D(2, 3));// 2
    list.add(new Point2D(3, 3));// 3
    list.add(new Point2D(4, 4));// 4
    list.add(new Point2D(4, 3));// 5
    list.add(new Point2D(4, 7));// 6
    list.add(new Point2D(4, 5));// 7
    list.add(new Point2D(4, 9));// 8
    list.add(new Point2D(4, 2));// 9
    list.add(new Point2D(5, 5));// 10
    list.add(new Point2D(5, 5));// 11
    list.add(new Point2D(6, 5));// 12
    list.add(new Point2D(7, 5));// 13

    it = new BoundsReducingIterator2D(//
        new DataCollectionIterator2D(leftEnd,//
            new ListCollection(list, 2), rightEnd),//
        1, 5, 1, 4);

    Assert.assertTrue(it.hasNext());
    Assert.assertEquals(list.get(0), it.next());
    Assert.assertEquals(list.get(1), it.next());
    Assert.assertEquals(list.get(2), it.next());
    Assert.assertEquals(list.get(3), it.next());
    Assert.assertEquals(list.get(4), it.next());
    Assert.assertEquals(list.get(5), it.next());
    Assert.assertEquals(list.get(6), it.next());
    Assert.assertEquals(list.get(8), it.next());
    Assert.assertEquals(list.get(9), it.next());
    Assert.assertEquals(list.get(10), it.next());
    Assert.assertFalse(it.hasNext());
  }
}
