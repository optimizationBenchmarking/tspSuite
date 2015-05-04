package test.junit.org.logisticPlanning.utils.text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.logisticPlanning.utils.math.random.Randomizer;
import org.logisticPlanning.utils.text.CharTransformer;
import org.logisticPlanning.utils.text.TextUtils;
import org.logisticPlanning.utils.utils.EmptyUtils;

import test.junit.TestBase;

/**
 * the test of the
 * {@link org.logisticPlanning.utils.text.transformations.LookupCharTransformer
 * character transformation facility} which is employed in the LaTeX text
 * generation modules or in the XML text normalization tools, amongst
 * others.
 */
@Ignore
public abstract class CharTransformerTest extends TestBase {

  /** the begin of unicode data */
  public static final String UNICODE_START = "\\u"; //$NON-NLS-1$

  /** the text cases */
  private String[][] m_cases;

  /** create */
  public CharTransformerTest() {
    super();
  }

  /**
   * get the char transformer
   *
   * @return the char transformer
   */
  protected abstract CharTransformer getTransformer();

  /**
   * get the test cases
   *
   * @return the test cases
   * @throws IOException
   *           if io fails
   */
  protected final String[][] getCases() throws IOException {
    String[][] cases;
    ArrayList<String[]> caseList;
    String s, a, b;
    int i;

    cases = this.m_cases;
    if (cases != null) {
      return cases;
    }

    caseList = new ArrayList<>();

    try (InputStream is = this.getClass().getResourceAsStream(
        this.getClass().getSimpleName() + //
        ".cases")) { //$NON-NLS-1$
      try (InputStreamReader r = new InputStreamReader(is)) {
        try (BufferedReader br = new BufferedReader(r)) {
          while ((s = br.readLine()) != null) {
            s = TextUtils.prepare(s);
            if (s == null) {
              continue;
            }
            i = s.indexOf(' ');
            if (i < 0) {
              continue;
            }

            a = this.prepare(s.substring(0, i));
            b = this.prepare(s.substring(i));

            caseList.add(new String[] { a, b });
          }
        }
      }
    }

    return (this.m_cases = caseList.toArray(new String[caseList.size()][]));

  }

  /**
   * prepare a string
   *
   * @param orig
   *          the original string
   * @return the prepared string
   */
  private final String prepare(final String orig) {
    final String t;
    final StringBuilder sb;
    int i, j;

    t = TextUtils.prepare(orig);
    if (t == null) {
      return EmptyUtils.EMPTY_STRING;
    }
    i = t.indexOf(CharTransformerTest.UNICODE_START);
    if (i < 0) {
      return t;
    }
    sb = new StringBuilder(t.length());
    j = 0;

    do {
      sb.append(t.substring(j, i));
      i += CharTransformerTest.UNICODE_START.length();
      j = i + 4;
      sb.append((char) (Integer.parseInt(t.substring(i, j), 16)));
      i = t.indexOf(CharTransformerTest.UNICODE_START, j);
    } while (i >= 0);
    sb.append(t.substring(j));

    return sb.toString();
  }

  /**
   * test if the transformer can correctly load
   *
   * @throws IOException
   *           the io exception if io fails
   */
  @Test(timeout = 3600000)
  public void testCharTransformerNotNull() throws IOException {
    Assert.assertNotNull(this.getTransformer());
  }

  /**
   * test if the transformer can correctly transform single characters
   *
   * @throws IOException
   *           the io exception if io fails
   */
  @Test(timeout = 3600000)
  public void testSingleTransform() throws IOException {
    final CharTransformer t;

    t = this.getTransformer();
    for (final String[] ss : this.getCases()) {
      Assert.assertEquals(ss[1], t.transform(ss[0]));
    }
  }

  /**
   * test if the transformer can correctly transform single characters
   *
   * @throws IOException
   *           the io exception if io fails
   */
  @Test(timeout = 3600000)
  public void testMultiTransform() throws IOException {
    final Randomizer r;
    final CharTransformer t;
    final StringBuilder a, b;
    final String[][] cases;
    int i, j, x;
    String s;

    r = new Randomizer();
    a = new StringBuilder();
    b = new StringBuilder();

    t = this.getTransformer();
    cases = this.getCases();

    for (x = 100; (--x) >= 0;) {
      a.setLength(0);
      b.setLength(0);
      for (i = r.nextInt(1001); (--i) >= 0;) {

        if (r.nextInt(4) <= 0) {
          j = r.nextInt(cases.length);
          a.append(cases[j][1]);
          b.append(cases[j][0]);
          continue;
        }

        for (;;) {
          try {
            s = String.valueOf((char) (r.nextInt(256)));
            if (s.equals(t.transform(s))) {
              break;
            }
          } catch (final Throwable tt) {
            //
          }
        }

        a.append(s);
        b.append(s);
      }

      Assert.assertEquals(a.toString(), t.transform(b.toString()));
    }
  }

}
