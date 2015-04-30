package test.junit.org.logisticPlanning.tsp.evaluation.data.properties.auc;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Assert;
import org.junit.Test;
import org.logisticPlanning.tsp.evaluation.data.properties.auc.AreaUnderCurve;
import org.logisticPlanning.utils.math.data.collection.IDataCollection;
import org.logisticPlanning.utils.math.data.collection.ListCollection;
import org.logisticPlanning.utils.math.data.iteration.DataCollectionIterator2D;
import org.logisticPlanning.utils.math.data.iteration.StairsKeepLeftIterator2D;
import org.logisticPlanning.utils.math.data.point.Point2D;
import org.logisticPlanning.utils.math.random.Randomizer;
import org.logisticPlanning.utils.math.statistics.aggregates.StableSum;

/**
 * Test the computation of thr
 * {@link org.logisticPlanning.tsp.evaluation.data.properties.auc.AreaUnderCurve
 * area under the curves}
 */
public class AreaUnderCurveTest {

  /** create */
  public AreaUnderCurveTest() {
    super();
  }

  /**
   * Add a random finite area, beginning at the last x-coordinate in the
   * dest (or a random point)
   * 
   * @param dest
   *          the destination array
   * @param rand
   *          the randomizer
   * @param areaSum
   *          the area sum
   * @param keepY
   *          do we keep the last y-coordinate?
   */
  private static final void __addRandomFinite(
      final ArrayList<Point2D> dest, final Randomizer rand,
      final StableSum areaSum, final boolean keepY) {
    final StableSum xsum;
    int s;
    double x, y, lastY;

    s = dest.size();
    x = ((s > 0) ? dest.get(s - 1).getX() : rand.nextDouble());
    xsum = new StableSum();

    xsum.visitDouble(x);
    y = rand.nextDouble();
    dest.add(new Point2D(x, y));
    do {
      lastY = y;
      y = rand.nextDouble();
      x = rand.nextDouble();

      if (keepY) {
        areaSum.visitDouble(x * lastY);
      } else {
        areaSum.visitDouble(x * ((0.5d * lastY) + (0.5d * y)));
      }

      xsum.visitDouble(x);
      dest.add(new Point2D(xsum.getResult(), y));
    } while (rand.nextInt(5) > 0);
  }

  /** The first test */
  @Test(timeout = 3600000)
  public void testArea1() {
    final ArrayList<Point2D> dest;
    final IDataCollection data;
    final Randomizer rand;
    final StableSum finite, ninf, pinf;
    Iterator<Point2D> it;
    AreaUnderCurve auc;
    boolean keepY;
    double lastX, x;
    int i, s;

    rand = new Randomizer();
    finite = new StableSum();
    ninf = new StableSum();
    pinf = new StableSum();
    dest = new ArrayList<>();
    data = new ListCollection(dest, 2);
    keepY = rand.nextBoolean();

    for (i = 10000; (--i) >= 0;) {
      if (rand.nextInt(10) <= 0) {
        finite.reset();
        ninf.reset();
        pinf.reset();
        dest.clear();
        keepY = rand.nextBoolean();
      }

      switch (rand.nextInt(3)) {
        case 0: {
          AreaUnderCurveTest.__addRandomFinite(dest, rand, finite, keepY);
          break;
        }

        case 1: {
          s = dest.size();
          lastX = ((s > 0) ? dest.get(s - 1).getX() : rand.nextDouble());
          dest.add(new Point2D(lastX, Double.POSITIVE_INFINITY));
          x = rand.nextDouble();
          pinf.visitDouble(x);
          lastX += x;
          dest.add(new Point2D(lastX, Double.POSITIVE_INFINITY));
          dest.add(new Point2D(lastX, rand.nextDouble()));
          break;
        }

        case 2: {
          s = dest.size();
          lastX = ((s > 0) ? dest.get(s - 1).getX() : rand.nextDouble());
          dest.add(new Point2D(lastX, Double.NEGATIVE_INFINITY));
          x = rand.nextDouble();
          pinf.visitDouble(x);
          lastX += x;
          dest.add(new Point2D(lastX, Double.NEGATIVE_INFINITY));
          dest.add(new Point2D(lastX, rand.nextDouble()));
          break;
        }

        default: {
          throw new IllegalStateException();
        }
      }

      it = new DataCollectionIterator2D(data);
      if (keepY) {
        it = new StairsKeepLeftIterator2D(it);
      }

      auc = new AreaUnderCurve(it);

      Assert.assertTrue(auc.getNaNLength() == 0d);
      Assert.assertEquals(auc.getNegativeInfiniteLength(),
          ninf.getResult(), 1e13d);
      Assert.assertEquals(auc.getPositiveInfiniteLength(),
          pinf.getResult(), 1e13d);
      Assert.assertEquals(auc.getFiniteArea(), finite.getResult(), 1e13d);
    }

  }
}
