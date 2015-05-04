package test.junit.org.logisticPlanning.tsp.solving.utils.edgeData;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.logisticPlanning.tsp.solving.utils.edgeData.EdgeBit;
import org.logisticPlanning.utils.math.random.Randomizer;

/**
 * The basic test for asymmetric edge bits
 */
@Ignore
public abstract class _AsymmetricEdgeBitTest extends _EdgeBitTest {

  /** instantiate */
  public _AsymmetricEdgeBitTest() {
    super();
  }

  /** test bit setting and getting */
  @Test(timeout = 3600000)
  public void testBitSetAndGet() {
    int n, i, j, k;
    EdgeBit bits;

    for (n = 2; n < _EdgeBitTest.TEST_DIMENSION; n++) {

      bits = this.instantiate(n);

      // test that everything is false initially
      for (i = 1; i <= n; i++) {
        for (j = 1; j <= n; j++) {

          if (i == j) {
            continue;
          }
          Assert.assertFalse("Bits must initially be false!", //$NON-NLS-1$
              bits.get(i, j));
        }
      }

      // test setting and getting
      for (k = 20; (--k) >= 0;) {
        for (i = 1; i <= n; i++) {
          for (j = 1; j <= n; j++) {
            if (i == j) {
              continue;
            }

            Assert.assertFalse(
                "Bits must initially be false before setting it to true!", //$NON-NLS-1$
                bits.get(i, j));

            bits.set(i, j);
            Assert.assertTrue(
                "Bits must true after setting it to true with 'set'!", //$NON-NLS-1$
                bits.get(i, j));

            bits.clear(i, j);
            Assert
            .assertFalse(
                "Bits must initially be false after setting it to false with 'clear'!", //$NON-NLS-1$
                bits.get(i, j));

            bits.set(i, j, true);
            Assert.assertTrue(
                "Bits must true after setting it to true with 'assign'!", //$NON-NLS-1$
                bits.get(i, j));

            bits.set(i, j, false);
            Assert
            .assertFalse(
                "Bits must initially be false after setting it to false with 'assign'!", //$NON-NLS-1$
                bits.get(i, j));
          }
        }
      }
    }
  }

  /** test bit setting and the rest must be false */
  @Test(timeout = 3600000)
  public void testBitSetRestFalse() {
    int n, i, j, k, a, b;
    EdgeBit bits;
    Randomizer r;

    r = new Randomizer();
    for (n = 2; n < _EdgeBitTest.TEST_DIMENSION; n++) {

      bits = this.instantiate(n);

      for (k = 20; (--k) >= 0;) {

        a = (r.nextInt(n) + 1);
        do {
          b = (r.nextInt(n) + 1);
        } while (a == b);

        // check that a given bit is set and only that this bit is set
        bits.set(a, b);
        for (i = n; i > 0; i--) {
          for (j = n; j > 0; j--) {
            if (i == j) {
              continue;
            }
            Assert.assertTrue(((i == a) && (j == b)) == bits.get(i, j));
          }
        }

        // everything must be false now
        bits.clear(a, b);
        for (i = n; i > 0; i--) {
          for (j = n; j > 0; j--) {
            if (i == j) {
              continue;
            }
            Assert.assertFalse(bits.get(i, j));
          }
        }

        // everything must be true
        bits.setAll();
        for (i = n; i > 0; i--) {
          for (j = n; j > 0; j--) {
            if (i == j) {
              continue;
            }
            Assert.assertTrue(bits.get(i, j));
          }
        }

        // check that a given bit is set and only that this bit is false
        bits.clear(a, b);
        for (i = n; i > 0; i--) {
          for (j = n; j > 0; j--) {
            if (i == j) {
              continue;
            }
            Assert.assertTrue(((i != a) || (j != b)) == bits.get(i, j));
          }
        }

        // everything must be false
        bits.clear();
        for (i = n; i > 0; i--) {
          for (j = n; j > 0; j--) {
            if (i == j) {
              continue;
            }
            Assert.assertFalse(bits.get(i, j));
          }
        }
      }
    }
  }

}
