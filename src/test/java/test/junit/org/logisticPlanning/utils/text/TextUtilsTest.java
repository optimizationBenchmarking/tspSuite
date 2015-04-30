package test.junit.org.logisticPlanning.utils.text;

import org.junit.Assert;
import org.junit.Test;
import org.logisticPlanning.utils.math.random.Randomizer;
import org.logisticPlanning.utils.text.TextUtils;

import test.junit.TestBase;

/**
 * The test of the {@link org.logisticPlanning.utils.text.TextUtils text
 * utilities class}
 */
public class TextUtilsTest extends TestBase {

  /** the randomizer */
  private final Randomizer m_r;

  /** create */
  public TextUtilsTest() {
    super();
    this.m_r = new Randomizer();
  }

  // /**
  // * test if strings are normalized correctly
  // *
  // * @throws IOException
  // * the io exception if io fails
  // */
  // @Test
  // public void testNormalize() throws IOException {
  //    Assert.assertEquals("One", //$NON-NLS-1$
  //        TextUtils.normalize("1")); //$NON-NLS-1$
  //
  //    Assert.assertEquals("AUnderscoreBUnderscoreAt",//$NON-NLS-1$
  //        TextUtils.normalize("A_B_@")); //$NON-NLS-1$
  //
  // Assert
  // .assertEquals(//
  //            "ParenthesisLeftOnePlusOneParenthesisRightSpaceevolutionSpacestrategy",//$NON-NLS-1$
  //            TextUtils.normalize("(1+1) evolution strategy")); //$NON-NLS-1$
  // }

  /**
   * test a double
   * 
   * @param s
   *          the string
   * @param d
   *          the double
   */
  private static final void testDouble(final double d, final String s) {
    final double prec, res, a;

    if (Double.isInfinite(d) || Double.isNaN(d)) {
      prec = 1d;
    } else {
      a = Math.abs(d);
      if (a > Double.MIN_NORMAL) {
        prec = (1e-10d * a);
      } else {
        prec = 1e-10;
      }
    }
    res = TextUtils.parseDouble(s);

    Assert.assertEquals(d, res, prec);
  }

  /**
   * Test whether parsing strings to doubles works well
   */
  @Test(timeout = 3600000)
  public void testParseDouble() {
    int i;
    double test;
    long testLong;

    TextUtilsTest.testDouble(0, "0");//$NON-NLS-1$

    TextUtilsTest.testDouble(1e-13, "1e-13");//$NON-NLS-1$

    TextUtilsTest.testDouble(-1.31234234, "-1.31234234");//$NON-NLS-1$

    TextUtilsTest.testDouble(3, "0x3");//$NON-NLS-1$

    TextUtilsTest.testDouble(-16, "-0x10");//$NON-NLS-1$

    TextUtilsTest.testDouble(Double.POSITIVE_INFINITY,//
        "Double.POSITIVE_INFINITY");//$NON-NLS-1$

    TextUtilsTest.testDouble(Double.NEGATIVE_INFINITY,//
        "\"-+- +-'Double.POSITIVE_INFINITY'\"");//$NON-NLS-1$

    TextUtilsTest.testDouble(Math.E, "Math.E");//$NON-NLS-1$

    TextUtilsTest.testDouble(-Math.PI, "-java.lang.Math.PI");//$NON-NLS-1$

    TextUtilsTest.testDouble(Double.NaN, String.valueOf(Double.NaN));//

    TextUtilsTest.testDouble(-Math.PI,//
        ('-' + Character.toString((char) 0x3c0)));

    TextUtilsTest.testDouble(Math.E, " Ma th. E ");//$NON-NLS-1$

    TextUtilsTest.testDouble(Math.E / 100, (" Ma th. E %"));//$NON-NLS-1$

    TextUtilsTest.testDouble(-(1 | 2 | 4 | 8 | 256 | 512 | 1024 | 2048),//
        ("-0b0000_1111_0000_1111"));//$NON-NLS-1$

    TextUtilsTest.testDouble(Integer.MAX_VALUE,//
        "Integer.MAX_VALUE");//$NON-NLS-1$

    TextUtilsTest.testDouble(Short.SIZE, "Short.SIZE");//$NON-NLS-1$;

    TextUtilsTest.testDouble(-0.3334d, "-[0.3334]");//$NON-NLS-1$

    TextUtilsTest.testDouble(-0.3334d / 100d, "-[0.3334]%");//$NON-NLS-1$
    TextUtilsTest.testDouble(-0.3334d / 1e6d, "-[0.3334" + //$NON-NLS-1$
        Character.toString((char) (0xb5)) + ']');

    for (i = 1000; i >= 0; i--) {
      synchronized (this.m_r) {
        if (this.m_r.nextBoolean()) {
          test = this.m_r.nextLong();
        } else {
          test = this.m_r.nextDouble();
        }
      }
      test = Double.parseDouble(Double.toString(test));

      TextUtilsTest.testDouble(test, Double.toString(test));
      TextUtilsTest.testDouble(test, Double.toHexString(test));

      test = (-test);
      test = Double.parseDouble(Double.toString(test));
      TextUtilsTest.testDouble(test, Double.toString(test));
      TextUtilsTest.testDouble(test, Double.toHexString(test));

      test = Math.abs(test);
      testLong = Math.round(test);
      test = testLong;
      TextUtilsTest.testDouble(test, ("0x" + Long.toHexString(testLong))); //$NON-NLS-1$
      TextUtilsTest.testDouble(test,
          ("0b" + Long.toBinaryString(testLong))); //$NON-NLS-1$
      TextUtilsTest
          .testDouble(test, ("0o" + Long.toOctalString(testLong))); //$NON-NLS-1$
    }
  }

}
