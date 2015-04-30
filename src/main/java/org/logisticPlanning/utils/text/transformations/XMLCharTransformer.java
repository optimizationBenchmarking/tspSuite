package org.logisticPlanning.utils.text.transformations;

import java.io.IOException;
import java.text.Normalizer;
import java.util.Arrays;

import org.logisticPlanning.utils.io.charIO.CharInput;
import org.logisticPlanning.utils.io.charIO.CharOutput;

/**
 * <p>
 * A character transformer that transforms unicode&nbsp;[<a
 * href="#cite_UC2011TUC" style="font-weight:bold">1</a>, <a
 * href="#cite_K2006UE" style="font-weight:bold">2</a>, <a
 * href="#cite_UC2006TUSV5" style="font-weight:bold">3</a>] characters to
 * XML entities.
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
public final class XMLCharTransformer extends NormalizedCharTransformer {

  /** instantiate */
  public static final XMLCharTransformer INSTANCE = new XMLCharTransformer();

  /** transform the character */
  private static final int MODE_PASS_THROUGH = 0;

  /** ignore the character */
  private static final int MODE_IGNORE = (XMLCharTransformer.MODE_PASS_THROUGH + 1);

  /** transform the character into a space */
  private static final int MODE_TO_SPACE = (XMLCharTransformer.MODE_IGNORE + 1);

  /** transform the character */
  private static final int MODE_TRANSFORM = (XMLCharTransformer.MODE_TO_SPACE + 1);

  /** the hyphen */
  private static final char[] HYPHEN = { '&', '#', '8', '2', '0', '3', ';' };

  /** the lookup */
  private static final char[] LOOKUP = { '0', '1', '2', '3', '4', '5',
      '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

  /** the default replacement */
  private static final char[] REPLACE = { '&', '#', 'x', 0, 0, 0, 0, ';' };

  /** the table of characters that can directly be passed on */
  private static final int[] MODES;

  static {
    final int[] list;

    list = new int[128];

    Arrays.fill(list, XMLCharTransformer.MODE_TRANSFORM);
    Arrays.fill(list, 0, 32, XMLCharTransformer.MODE_IGNORE);
    list[' '] = XMLCharTransformer.MODE_PASS_THROUGH;
    list['\n'] = XMLCharTransformer.MODE_PASS_THROUGH;
    list['\t'] = XMLCharTransformer.MODE_TO_SPACE;

    Arrays.fill(list, '(', ';', XMLCharTransformer.MODE_PASS_THROUGH);
    Arrays
        .fill(list, '?', ('_' + 1), XMLCharTransformer.MODE_PASS_THROUGH);
    Arrays.fill(list, 'a', 126, XMLCharTransformer.MODE_PASS_THROUGH);
    list['!'] = XMLCharTransformer.MODE_PASS_THROUGH;
    list['$'] = XMLCharTransformer.MODE_PASS_THROUGH;
    list['%'] = XMLCharTransformer.MODE_PASS_THROUGH;
    list['='] = XMLCharTransformer.MODE_PASS_THROUGH;

    MODES = list;
  }

  /** Create */
  protected XMLCharTransformer() {
    super(Normalizer.Form.NFKC);
  }

  /**
   * the character-to-entity conversion
   * 
   * @param chr
   *          the char
   * @param dest
   *          the destination
   * @return len the length
   */
  private static final int __makeEntity(final int chr, final char[] dest) {
    boolean go;
    int idx, val;

    idx = 3;
    go = false;

    val = ((chr & 0xf000) >>> 12);
    if (val != 0) {
      dest[idx++] = XMLCharTransformer.LOOKUP[val];
      go = true;
    }

    val = ((chr & 0xf00) >>> 8);
    if ((val != 0) || go) {
      go = true;
      dest[idx++] = XMLCharTransformer.LOOKUP[val];
    }

    val = ((chr & 0xf0) >>> 4);
    if ((val != 0) || go) {
      go = true;
      dest[idx++] = XMLCharTransformer.LOOKUP[val];
    }

    dest[idx++] = XMLCharTransformer.LOOKUP[chr & 0xf];
    dest[idx++] = ';';
    return idx;
  }

  /** {@inheritDoc} */
  @Override
  protected final void doTransform(final CharInput in,
      final CharOutput out, final int start, final int end)
      throws IOException {

    char[] replace;
    int i, currentStart, currentChar, mode;

    // setup
    currentStart = start;
    replace = null;

    // iterate over all characters in the character array data
    outer: for (i = currentStart; i < end; i++) {
      currentChar = in.get(i);

      // check character mode, if available
      if (currentChar < XMLCharTransformer.MODES.length) {
        mode = XMLCharTransformer.MODES[currentChar];
        if (mode == XMLCharTransformer.MODE_PASS_THROUGH) {
          continue outer;
        }
      } else {
        mode = XMLCharTransformer.MODE_TRANSFORM;
      }

      // ok, we need to do something special
      in.write(out, currentStart, i);
      currentStart = (i + 1);

      // convert tabs to spaces
      if (mode == XMLCharTransformer.MODE_TO_SPACE) {
        out.write(' ');
        continue;
      }

      // omit ascii control characters
      if (mode == XMLCharTransformer.MODE_IGNORE) {
        continue;
      }

      // omit iso control characters
      if (Character.isISOControl(currentChar)) {
        continue;
      }

      // ok, the character is no control char -> transform it!
      if (replace == null) {
        replace = XMLCharTransformer.REPLACE.clone();
      }
      out.write(replace, 0,
          XMLCharTransformer.__makeEntity(currentChar, replace));
    }

    in.write(out, currentStart, end);
  }

  /** {@inheritDoc} */
  @Override
  protected final void doTransformHyphenated(final CharInput in,
      final CharOutput out, final int start, final int end)
      throws IOException {

    char[] replace;
    int mode, i, currentStart, currentChar;
    boolean doHyphen, nextDoHyphen, notFirst;

    // setup
    currentStart = start;
    replace = null;
    nextDoHyphen = notFirst = false;

    // iterate over all characters in the character array data
    outer: for (i = currentStart; i < end; i++) {
      currentChar = in.get(i);

      if (currentChar <= 32) {
        nextDoHyphen = doHyphen = false;
      } else {
        if (Character.isAlphabetic(currentChar)) {
          doHyphen = (notFirst && (Character.isUpperCase(currentChar) || nextDoHyphen));
          nextDoHyphen = false;
        } else {
          doHyphen = (notFirst && nextDoHyphen);
          nextDoHyphen = (!(Character.isDigit(currentChar)));
        }
      }
      notFirst = true;

      if (doHyphen) {
        in.write(out, currentStart, i);
        currentStart = i;
        out.write(XMLCharTransformer.HYPHEN, 0,
            XMLCharTransformer.HYPHEN.length);
      }

      // is there a state information for this character?
      if (currentChar < XMLCharTransformer.MODES.length) {
        mode = XMLCharTransformer.MODES[currentChar];
        if (mode == XMLCharTransformer.MODE_PASS_THROUGH) {
          continue outer;
        }
      } else {
        mode = XMLCharTransformer.MODE_TRANSFORM;
      }

      // ok, we need to do something special
      if (!doHyphen) {
        in.write(out, currentStart, i);
      }
      currentStart = (i + 1);

      // convert tabs to spaces
      checkContinue: {
        noContinue: {
          if (mode == XMLCharTransformer.MODE_TO_SPACE) {
            out.write(' ');
            break noContinue;
          }

          // omit ascii control characters
          if (mode == XMLCharTransformer.MODE_IGNORE) {
            break noContinue;
          }

          // omit iso control characters
          if (Character.isISOControl(currentChar)) {
            break noContinue;
          }

          break checkContinue;
        }
        notFirst = nextDoHyphen = false;
        continue outer;
      }

      // ok, the character is no control char -> transform it!
      if (replace == null) {
        replace = XMLCharTransformer.REPLACE.clone();
      }
      out.write(replace, 0,
          XMLCharTransformer.__makeEntity(currentChar, replace));
    }

    in.write(out, currentStart, end);
  }
}
