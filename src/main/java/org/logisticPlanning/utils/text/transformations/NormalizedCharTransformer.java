package org.logisticPlanning.utils.text.transformations;

import java.io.IOException;
import java.text.Normalizer;
import java.text.Normalizer.Form;

import org.logisticPlanning.utils.io.charIO.CharInput;
import org.logisticPlanning.utils.io.charIO.CharOutput;
import org.logisticPlanning.utils.io.charIO.StringCharInput;
import org.logisticPlanning.utils.text.CharTransformer;

/**
 * <p>
 * A character transformer that applies a unicode&nbsp;[<a
 * href="#cite_UC2011TUC" style="font-weight:bold">1</a>, <a
 * href="#cite_K2006UE" style="font-weight:bold">2</a>, <a
 * href="#cite_UC2006TUSV5" style="font-weight:bold">3</a>]
 * {@link java.text.Normalizer normalization procedure} before transforming
 * the characters.
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
 */
public abstract class NormalizedCharTransformer extends CharTransformer {

  /** the normalization form */
  private final Form m_form;

  /**
   * Create
   *
   * @param form
   *          the form, or {@code null} if no such transformation it to be
   *          performed
   */
  protected NormalizedCharTransformer(final Form form) {
    super();
    this.m_form = form;
  }

  /**
   * Do the transformation work: Transform all characters between the
   * starting at index {@code start} (inclusive) and the end index
   * {@code end} (exclusive) from input {@code in} to the output
   * {@code out}.
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
  protected abstract void doTransform(final CharInput in,
      final CharOutput out, final int start, final int end)
          throws IOException;

  /** {@inheritDoc} */
  @Override
  public final void transform(final CharInput in, final CharOutput out,
      final int start, final int end) throws IOException {
    final String s;

    if (this.m_form == null) {
      this.doTransform(in, out, start, end);
    } else {
      s = Normalizer.normalize(in.toString(start, end), this.m_form);
      this.doTransform(new StringCharInput(s), out, 0, s.length());
    }
  }

  /**
   * Do the transformation work: Transform all characters between the
   * starting at index {@code start} (inclusive) and the end index
   * {@code end} (exclusive) from input {@code in} to the output
   * {@code out} and insert hyphens where possible.
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
  protected void doTransformHyphenated(final CharInput in,
      final CharOutput out, final int start, final int end)
          throws IOException {
    this.doTransform(in, out, start, end);
  }

  /** {@inheritDoc} */
  @Override
  public final void transformHyphenated(final CharInput in,
      final CharOutput out, final int start, final int end)
          throws IOException {
    final String s;

    if (this.m_form == null) {
      this.doTransformHyphenated(in, out, start, end);
    } else {
      s = Normalizer.normalize(in.toString(start, end), this.m_form);
      this.doTransformHyphenated(new StringCharInput(s), out, 0,
          s.length());
    }
  }
}
