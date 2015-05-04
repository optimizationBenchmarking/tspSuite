package test.junit.org.logisticPlanning.utils.text;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.logisticPlanning.utils.text.TextUtils;

/**
 * A test of the transformation of numbers to text. Test cases are partly
 * taken from&nbsp;[<a href="#cite_JRJWMAA2010"
 * style="font-weight:bold">1</a>]. <h2>References</h2>
 * <ol>
 * <li><div><span id="cite_JRJWMAA2010" />Jason, Yanick Rochon, Jigar
 * Joshi, David Wallace, Santhosh Reddy Mandadi, Ankit Arjaria,
 * and&nbsp;Riyar Ajay: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Question on
 * Stackoverflow.com: How to Convert Number to Words in Java,&rdquo;</span>
 * (Website), October&nbsp;12, 2010, New York, NY, USA: Stack Exchange
 * Inc., stackoverflow. <div>link: [<a href=
 * "http://stackoverflow.com/questions/3911966/how-to-convert-number-to-words-in-java"
 * >1</a>]</div></div></li>
 * </ol>
 *
 *
 @author <em><a href="http://www.it-weise.de/">Thomas Weise</a></em>,
 *         Email:&nbsp;<a
 *         href="mailto:tweise@ustc.edu.cn">tweise@ustc.edu.cn</a>
 */
public class TextUtilsNumberTextTest {

  /** the long text cases */
  private static final long[] LONG_TEST_CASES = new long[] { 0l, //
    1l, //
    2l, //
    3l, //
    4l, //
    10l, //
    12l, //
    19l, //
    20l, //
    21l, //
    100l, //
    108l, //
    299l, //
    1000l, //
    1003l, //
    2040l, //
    45213l, //
    100000l, //
    100005l, //
    100010l, //
    202020l, //
    202022l, //
    999999l, //
    1000000l, //
    1000001l, //
    10000000l, //
    10000007l, //
    99999999l, //
    Long.MAX_VALUE, //
    (Long.MAX_VALUE - 1), //
    Long.MIN_VALUE, //
    (Long.MIN_VALUE + 1) };

  /** the long text case results */
  private static final String[] LONG_TEST_CASE_RESULTS = new String[] {
    "zero", //$NON-NLS-1$
    "one", //$NON-NLS-1$
    "two", //$NON-NLS-1$
    "three", //$NON-NLS-1$
    "four", //$NON-NLS-1$
    "ten", //$NON-NLS-1$
    "twelve", //$NON-NLS-1$
    "nineteen", //$NON-NLS-1$
    "twenty", //$NON-NLS-1$
    "twenty-one", //$NON-NLS-1$
    "one hundred", //$NON-NLS-1$
    "one hundred eight", //$NON-NLS-1$
    "two hundred ninety-nine", //$NON-NLS-1$
    "one thousand", //$NON-NLS-1$
    "one thousand three", //$NON-NLS-1$
    "two thousand fourty", //$NON-NLS-1$
    "fourty-five thousand two hundred thirteen", //$NON-NLS-1$
    "one hundred thousand", //$NON-NLS-1$
    "one hundred thousand five", //$NON-NLS-1$
    "one hundred thousand ten", //$NON-NLS-1$
    "two hundred two thousand twenty", //$NON-NLS-1$
    "two hundred two thousand twenty-two", //$NON-NLS-1$
    "nine hundred ninety-nine thousand nine hundred ninety-nine", //$NON-NLS-1$
    "one million", //$NON-NLS-1$
    "one million one", //$NON-NLS-1$
    "ten million", //$NON-NLS-1$
    "ten million seven", //$NON-NLS-1$
    "ninety-nine million nine hundred ninety-nine thousand nine hundred ninety-nine", //$NON-NLS-1$
    "nine quintillion two hundred twenty-three quadrillion three hundred seventy-two trillion thirty-six billion eight hundred fifty-four million seven hundred seventy-five thousand eight hundred seven", //$NON-NLS-1$
    "nine quintillion two hundred twenty-three quadrillion three hundred seventy-two trillion thirty-six billion eight hundred fifty-four million seven hundred seventy-five thousand eight hundred six", //$NON-NLS-1$
    "minus nine quintillion two hundred twenty-three quadrillion three hundred seventy-two trillion thirty-six billion eight hundred fifty-four million seven hundred seventy-five thousand eight hundred eight", //$NON-NLS-1$
    "minus nine quintillion two hundred twenty-three quadrillion three hundred seventy-two trillion thirty-six billion eight hundred fifty-four million seven hundred seventy-five thousand eight hundred seven", //$NON-NLS-1$
  };

  /** the double text cases */
  private static final double[] DOUBLE_TEST_CASES = new double[] { 1e9d, //
    1e12d, //
    1e15d, //
    1e18d, //
    1e21d, //
    1e24d, //
    1e27d, //
    1e30d, //
    10e30d, //
    1e63d, //
    1e93d, //
    1e297d, //
    1e303d, //
    27e303d, //
    3.141592d, //
  };

  /** the double text case results */
  private static final String[] DOUBLE_TEST_CASE_RESULTS = new String[] {
    "one billion", //$NON-NLS-1$
    "one trillion", //$NON-NLS-1$
    "one quadrillion", //$NON-NLS-1$
    "one quintillion", //$NON-NLS-1$
    "one sextillion", //$NON-NLS-1$
    "one septillion", //$NON-NLS-1$
    "one octillion", //$NON-NLS-1$
    "one nonillion", //$NON-NLS-1$
    "ten nonillion", //$NON-NLS-1$
    "one vigintillion", //$NON-NLS-1$
    "one trigintillion", //$NON-NLS-1$
    "one octanonagintillion", //$NON-NLS-1$
    "one centillion", //$NON-NLS-1$
    "twenty-seven centillion", //$NON-NLS-1$
    "three and one hundred fourty-one thousand five hundred ninety-two millionths", //$NON-NLS-1$
  };

  /**
   * test if the long-to-text transformation works as it should
   *
   * @throws IOException
   *           the io exception if io fails
   */
  @Test(timeout = 3600000)
  public void testLongToText() throws IOException {
    int i;
    for (i = 0; i < TextUtilsNumberTextTest.LONG_TEST_CASES.length; i++) {
      Assert
      .assertEquals(TextUtilsNumberTextTest.LONG_TEST_CASE_RESULTS[i],
          TextUtils
          .longToText(TextUtilsNumberTextTest.LONG_TEST_CASES[i]));
    }
  }

  /**
   * test if the double-to-text transformation works as it should
   *
   * @throws IOException
   *           the io exception if io fails
   */
  @Test(timeout = 3600000)
  public void testDoubleToTextAsLongs() throws IOException {
    int i;
    long t;
    double v;

    for (i = 0; i < TextUtilsNumberTextTest.LONG_TEST_CASES.length; i++) {
      t = TextUtilsNumberTextTest.LONG_TEST_CASES[i];
      v = t;
      if (((long) v) == t) {
        Assert.assertEquals(
            TextUtilsNumberTextTest.LONG_TEST_CASE_RESULTS[i],
            TextUtils.doubleToText(v));
      }
    }
  }

  /**
   * test if the double-to-text transformation works as it should
   *
   * @throws IOException
   *           the io exception if io fails
   */
  @Test(timeout = 3600000)
  public void testDoubleToText() throws IOException {
    int i;
    for (i = 0; i < TextUtilsNumberTextTest.DOUBLE_TEST_CASES.length; i++) {
      Assert.assertEquals(
          TextUtilsNumberTextTest.DOUBLE_TEST_CASE_RESULTS[i], TextUtils
          .doubleToText(TextUtilsNumberTextTest.DOUBLE_TEST_CASES[i]));
    }
  }
}
