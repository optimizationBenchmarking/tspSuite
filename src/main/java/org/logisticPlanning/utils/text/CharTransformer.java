package org.logisticPlanning.utils.text;

import java.io.IOException;

import org.logisticPlanning.utils.io.charIO.CharInput;
import org.logisticPlanning.utils.io.charIO.CharOutput;
import org.logisticPlanning.utils.io.charIO.StringBuilderCharOutput;
import org.logisticPlanning.utils.io.charIO.StringCharInput;

/**
 * <p>
 * A character transformer copies a character sequence from a source to a
 * destination and applies some modification. The modification may be to
 * replace some characters with other symbols.Instances of this class
 * translate unicode characters&nbsp;[<a href="#cite_UC2011TUC"
 * style="font-weight:bold">1</a>, <a href="#cite_K2006UE"
 * style="font-weight:bold">2</a>, <a href="#cite_UC2006TUSV5"
 * style="font-weight:bold">3</a>] ( {@code char}s) to a (canonical)
 * textual representations.
 * </p>
 * <h2>References</h2>
 * <ol>
 * <li><div><span id="cite_UC2011TUC" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;The Unicode
 * Consortium,&rdquo;</span> (Website), 2011, Mountain View, CA, USA: The
 * Unicode Consortium. <div>link: [<a
 * href="http://www.unicode.org/">1</a>]</div></div></li>
 * <li><div><span id="cite_K2006UE" />Jukka K. Korpela: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Unicode
 * Explained,&rdquo;</span> June&nbsp;28, 2006, Internationalize Documents,
 * Programs, and Web Sites, Sebastopol, CA, USA: O'Reilly Media, Inc..
 * ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/059610121X">059610121X</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780596101213">9780596101213</a>;
 * Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=PcWU2yxc8WkC"
 * >PcWU2yxc8WkC</a></div></li>
 * <li><div><span id="cite_UC2006TUSV5" />Mountain View, CA, USA: The
 * Unicode Consortium and&nbsp;Julie D. Allen: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;The Unicode
 * Standard, Version 5.0,&rdquo;</span> 2007, Reading, MA, USA:
 * Addison-Wesley Professional. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0321480910">0-321-48091-0</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780321480910">978-0-321-
 * 48091-0</a>; Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=Yn1UAAAAMAAJ"
 * >Yn1UAAAAMAAJ</a></div></li>
 * </ol>
 * 
 * 
 @author <em><a href="http://www.it-weise.de/">Thomas Weise</a></em>,
 *         Email:&nbsp;<a
 *         href="mailto:tweise@ustc.edu.cn">tweise@ustc.edu.cn</a>
 */
public abstract class CharTransformer {

  /** create */
  protected CharTransformer() {
    super();
  }

  /**
   * Transform all characters between the starting at index {@code start}
   * (inclusive) and the end index {@code end} (exclusive) from input
   * {@code in} to the output {@code out}.
   * 
   * @param in
   *          the input
   * @param out
   *          the output
   * @param start
   *          the (inclusive) start index
   * @param end
   *          the (exclusive) end index
   * @throws IOException
   *           if io fails
   */
  public abstract void transform(final CharInput in, final CharOutput out,
      final int start, final int end) throws IOException;

  /**
   * Transform all characters between the starting at index {@code start}
   * (inclusive) and the end index {@code end} (exclusive) from input
   * {@code in} to the output {@code out} and insert hyphens where
   * possible.
   * 
   * @param in
   *          the input
   * @param out
   *          the output
   * @param start
   *          the (inclusive) start index
   * @param end
   *          the (exclusive) end index
   * @throws IOException
   *           if io fails
   */
  public void transformHyphenated(final CharInput in,
      final CharOutput out, final int start, final int end)
      throws IOException {
    this.transform(in, out, start, end);
  }

  /**
   * Transform a string
   * 
   * @param s
   *          the string
   * @return the transformed string
   */
  public final String transform(final String s) {
    final String t, r;
    final int len;
    final StringBuilder sb;

    t = ((s == null) ? TextUtils.NULL_STRING : s);
    len = t.length();
    sb = new StringBuilder(len << 1);
    try {
      this.transform(new StringCharInput(t), new StringBuilderCharOutput(
          sb), 0, len);
    } catch (final IOException ioe) {
      throw new IllegalStateException(ioe);
    }
    r = sb.toString();
    if (r.equals(s)) {
      return s;
    }
    return r;
  }

  /**
   * Transform a string
   * 
   * @param s
   *          the string
   * @return the transformed string
   */
  public final String transformHyphenated(final String s) {
    final String t, r;
    final int len;
    final StringBuilder sb;

    t = ((s == null) ? TextUtils.NULL_STRING : s);
    len = t.length();
    sb = new StringBuilder(len << 1);
    try {
      this.transformHyphenated(new StringCharInput(t),
          new StringBuilderCharOutput(sb), 0, len);
    } catch (final IOException ioe) {
      throw new IllegalStateException(ioe);
    }
    r = sb.toString();
    if (r.equals(s)) {
      return s;
    }
    return r;
  }
}
