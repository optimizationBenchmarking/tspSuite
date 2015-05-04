package test.junit.org.logisticPlanning.tsp.solving.utils.edgeData;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.logisticPlanning.tsp.solving.utils.edgeData.EdgeNumber;
import org.logisticPlanning.utils.math.random.Randomizer;

import test.junit.TestBase;

/**
 * The basic test for edge data
 */
@Ignore
public abstract class _EdgeNumberTest extends TestBase {

  /** the instance */
  private EdgeNumber m_inst;

  /** instantiate */
  _EdgeNumberTest() {
    super();
  }

  /**
   * is this symmetric?
   *
   * @return {@code true} for symmetric values, {@code false} otherwise
   */
  abstract boolean isSymmetric();

  /**
   * Get the minimum value
   *
   * @return the minimum value
   */
  long getMinValue() {
    return (-this.getMaxValue());
  }

  /**
   * get the dimension
   *
   * @return the dimension
   */
  abstract int n();

  /**
   * Get the maximum value
   *
   * @return the maximum value
   */
  long getMaxValue() {
    return Short.MAX_VALUE;
  }

  /**
   * Are we floats?
   *
   * @return {@code 0} for integer calculation, {@code 1} for float-based
   *         calculation, {@code 2} for double-based calculation
   */
  abstract int floatType();

  /**
   * Instantiate the bit set
   *
   * @return the edge bit set
   */
  protected final EdgeNumber get() {
    final int ft;

    ft = this.floatType();

    if (ft <= 1) {
      return (this.m_inst = EdgeNumber.allocate(this.n(),
          this.isSymmetric(), this.getMinValue(), this.getMaxValue(),
          (ft != 0), this.m_inst));
    }

    return (this.m_inst = EdgeNumber.allocate(this.n(),
        this.isSymmetric(), Double.NEGATIVE_INFINITY,
        Double.POSITIVE_INFINITY, true, this.m_inst));
  }

  /** Test the instance features */
  @Test(timeout = 3600000)
  public void testInstanceFeatures() {
    final EdgeNumber e;
    int i, j;

    e = this.get();

    Assert.assertEquals(this.n(), e.n());
    Assert.assertTrue((this.floatType() > 0) == e.areFloatsAllowed());
    Assert.assertTrue(this.isSymmetric() == e.isSymmetric());
    Assert.assertTrue(this.getMinValue() >= e.getMinimumAllowedValue());
    Assert.assertTrue(this.getMaxValue() <= e.getMaximumAllowedValue());

    for (i = e.n(); i > 0; i--) {
      for (j = e.n(); j > 0; j--) {
        if (i != j) {
          Assert.assertEquals(0l, e.getLong(i, j));
          Assert.assertEquals(0d, e.getDouble(i, j), 0d);
        }
      }
    }
  }

  /** test whether we read the exactly same values that we write */
  @Test(timeout = 3600000)
  public void testReadWrite() {
    final EdgeNumber e;
    long min, max;
    final int n;
    final boolean symmetric;
    final long[][] data;
    final Randomizer r;
    final boolean flt;
    int trial, i, j;
    long v;

    e = this.get();

    flt = (this.floatType() > 0);
    Assert.assertTrue(flt == e.areFloatsAllowed());
    min = e.getMinimumAllowedValue();
    Assert.assertTrue(min <= this.getMinValue());
    max = e.getMaximumAllowedValue();
    Assert.assertTrue(max >= this.getMaxValue());
    if (flt) {
      min = Short.MIN_VALUE;
      max = Short.MAX_VALUE;
    }
    Assert.assertEquals((n = this.n()), e.n());
    symmetric = this.isSymmetric();
    Assert.assertTrue(symmetric == e.isSymmetric());

    r = new Randomizer();

    data = new long[n][n];
    for (trial = 40; (--trial) >= 0;) {

      for (i = n; i > 0; i--) {
        for (j = n; j > 0; j--) {
          if (i != j) {
            Assert.assertEquals(data[i - 1][j - 1], e.getLong(i, j));
          }
        }
      }

      if (symmetric) {
        for (i = n; i > 0; i--) {
          for (j = i; (--j) > 0;) {

            if ((min <= Long.MIN_VALUE) && (max >= Long.MAX_VALUE)) {
              v = r.nextLong();
            } else {
              do {
                v = ((r.nextLong() % ((max - min) + 1)) + min);
              } while ((v < min) || (v > max));
            }

            data[i - 1][j - 1] = v;
            data[j - 1][i - 1] = v;
            if (r.nextBoolean()) {
              e.setLong(i, j, v);
            } else {
              e.setLong(j, i, v);
            }
          }
        }
      } else {
        for (i = n; i > 0; i--) {
          for (j = n; j > 0; j--) {
            if (i != j) {
              if ((min <= Long.MIN_VALUE) && (max >= Long.MAX_VALUE)) {
                v = r.nextLong();
              } else {
                do {
                  v = ((r.nextLong() % ((max - min) + 1)) + min);
                } while ((v < min) || (v > max));
              }

              data[i - 1][j - 1] = v;
              e.setLong(i, j, v);
            }
          }
        }
      }
    }
  }

  /** test whether increasing and decreasing works */
  @Test(timeout = 3600000)
  public void testIncDec() {
    final EdgeNumber e;
    long min, max;
    final int n;
    final boolean symmetric;
    final long[][] data;
    final Randomizer r;
    int trial, i, j;
    final boolean flt;

    e = this.get();

    flt = (this.floatType() > 0);
    Assert.assertTrue(flt == e.areFloatsAllowed());
    min = e.getMinimumAllowedValue();
    Assert.assertTrue(min <= this.getMinValue());
    max = e.getMaximumAllowedValue();
    Assert.assertTrue(max >= this.getMaxValue());
    if (flt) {
      min = Short.MIN_VALUE;
      max = Short.MAX_VALUE;
    }
    Assert.assertEquals((n = this.n()), e.n());
    symmetric = this.isSymmetric();
    Assert.assertTrue(symmetric == e.isSymmetric());

    r = new Randomizer();

    data = new long[n][n];
    for (trial = 40; (--trial) >= 0;) {

      for (i = n; i > 0; i--) {
        for (j = n; j > 0; j--) {
          if (i != j) {
            Assert.assertEquals(data[i - 1][j - 1], e.getLong(i, j));
          }
        }
      }

      if (symmetric) {
        for (i = n; i > 0; i--) {
          for (j = i; (--j) > 0;) {

            if (r.nextBoolean()) {
              if (data[i - i][j - 1] < max) {
                data[i - 1][j - 1]++;
                data[j - 1][i - 1]++;
                if (r.nextBoolean()) {
                  e.inc(i, j);
                } else {
                  e.inc(j, i);
                }
              }
            } else {
              if (data[i - i][j - 1] > min) {
                data[i - 1][j - 1]--;
                data[j - 1][i - 1]--;
                if (r.nextBoolean()) {
                  e.dec(i, j);
                } else {
                  e.dec(j, i);
                }
              }
            }

          }
        }
      } else {
        for (i = n; i > 0; i--) {
          for (j = n; j > 0; j--) {
            if (i != j) {
              if (r.nextBoolean()) {
                if (data[i - i][j - 1] < max) {
                  data[i - 1][j - 1]++;
                  e.inc(i, j);
                }
              } else {
                if (data[i - i][j - 1] > min) {
                  data[i - 1][j - 1]--;
                  e.dec(i, j);
                }
              }
            }
          }
        }
      }
    }
  }

}
