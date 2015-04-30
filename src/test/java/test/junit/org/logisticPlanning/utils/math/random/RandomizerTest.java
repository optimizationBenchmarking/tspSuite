package test.junit.org.logisticPlanning.utils.math.random;

import java.util.Random;

import org.junit.Assert;
import org.junit.Test;
import org.logisticPlanning.utils.math.random.Randomizer;

import test.junit.TestBase;

/**
 * the test for the
 * {@link org.logisticPlanning.utils.math.random.Randomizer} class.
 */
public class RandomizerTest extends TestBase {

  /** create */
  public RandomizerTest() {
    super();
  }

  /**
   * create a new randomizer
   * 
   * @return the randomizer
   */
  protected Randomizer createRandomizer() {
    return new Randomizer();
  }

  /**
   * Test whether the randomizer provides different results directly after
   * creation. This test may fail, but the probability of it failing should
   * be very, very low ({@code 200000/Long.MAX_VALUE}). Therefore, if it
   * fails (or fails multiple times), something fishy is going on.
   */
  @Test(timeout = 3600000)
  public void testRandomizerDifferentResultsAfterCreationLong() {
    Randomizer a;
    long[] zz;
    long r;
    int s, i;

    zz = new long[200000];
    s = 0;
    for (s = 0; s < zz.length; s++) {
      a = this.createRandomizer();
      zz[s] = r = a.nextLong();
      for (i = s; (--i) >= 0;) {
        Assert.assertFalse(zz[i] == r);
      }
    }
  }

  /**
   * Test whether the randomizer provides different results for different
   * seeds. This should never fail.
   */
  @Test(timeout = 3600000)
  public void testRandomizerDifferentResultsForSimilarSeedsLong() {
    final Randomizer a;
    final Random seed;
    long s, r;

    a = this.createRandomizer();
    seed = new Random();

    for (int i = 1; i < 1000000; i++) {
      s = seed.nextLong();
      a.setSeed(s);
      r = a.nextLong();
      a.setSeed(s + 1);
      Assert.assertFalse(r == a.nextLong());
    }
  }

  /**
   * Test whether the randomizer provides different results for different
   * seeds. This should never fail.
   */
  @Test(timeout = 3600000)
  public void testRandomizerDifferentResultsForDifferentSeedsLong() {
    final Randomizer a;
    final Random seed;
    long s1, s2, r;

    seed = new Random();
    a = this.createRandomizer();

    for (int i = 1; i < 1000000; i++) {

      s1 = seed.nextLong();
      do {
        s2 = seed.nextLong();
      } while (s1 == s2);

      a.setSeed(s1);
      r = a.nextLong();
      a.setSeed(s2);
      Assert.assertFalse(r == a.nextLong());
    }
  }

  /**
   * Test whether the randomizer provides same results for same seeds. This
   * should never fail.
   */
  @Test(timeout = 3600000)
  public void testRandomizerSameResultsForSameSeedsLong() {
    final Randomizer a;
    final Random seed;
    long s, r;

    a = this.createRandomizer();
    seed = new Random();

    for (int i = 1; i < 1000000; i++) {

      s = seed.nextLong();

      a.setSeed(s);
      r = a.nextLong();
      a.setSeed(s);
      Assert.assertTrue(r == a.nextLong());
    }
  }

  /**
   * Test whether the randomizer provides same results for same seeds. This
   * should never fail.
   */
  @Test(timeout = 3600000)
  public void testRandomizerSameResultsForSameSeedsInt() {
    final Randomizer a;
    final Random seed;
    long s;
    int r;

    a = this.createRandomizer();
    seed = new Random();

    for (int i = 1; i < 1000000; i++) {

      s = seed.nextInt();

      a.setSeed(s);
      r = a.nextInt();
      a.setSeed(s);
      Assert.assertTrue(r == a.nextInt());
    }
  }

}
