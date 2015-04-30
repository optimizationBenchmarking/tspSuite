package org.logisticPlanning.utils.text.transformations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;

import org.logisticPlanning.utils.io.charIO.CharInput;
import org.logisticPlanning.utils.io.charIO.CharOutput;
import org.logisticPlanning.utils.text.TextUtils;
import org.logisticPlanning.utils.utils.EmptyUtils;

/**
 * <p>
 * A lookup-table based character transformer: Instances of this class
 * translate unicode characters&nbsp;[<a href="#cite_UC2011TUC"
 * style="font-weight:bold">1</a>, <a href="#cite_K2006UE"
 * style="font-weight:bold">2</a>, <a href="#cite_UC2006TUSV5"
 * style="font-weight:bold">3</a>] ({@code char}s) to a (canonical) textual
 * representations. Single chacters can either be omitted (see
 * {@link #STATE_MARK_OMIT}), copied directly (see
 * {@link #STATE_MARK_DIRECT}), transformed to a normal space character
 * (see {@link #STATE_MARK_TO_SPACE}), or replaced by a fixed, pre-defined
 * string (see {@link #STATE_LOOKUP}). The character transformer loads the
 * information what to do from a resource text file.
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
public class LookupCharTransformer extends NormalizedCharTransformer {

  /** the default data */
  private static final char[][] DEF_DATA = new char[0][];

  /** the default hyphen */
  private static final char[] DEF_HY = new char[0];

  /** the default character state */
  private static final byte[] DEF_STATE = new byte[0];

  /** a replacement that transforms a character to a space */
  private static final char[] TO_SPACE = new char[] { ' ', ' ' };

  /** the character must be looked up */
  private static final byte STATE_LOOKUP = 0;

  /** the character can be written directly */
  private static final byte STATE_DIRECT = (LookupCharTransformer.STATE_LOOKUP + 1);

  /** the character is omitted, nothing is written to the output */
  private static final byte STATE_OMIT = (LookupCharTransformer.STATE_DIRECT + 1);

  /** the character is omitted, nothing is written to the output */
  private static final byte STATE_TO_SPACE = (LookupCharTransformer.STATE_OMIT + 1);

  /** the state mark for direct output */
  private static final char STATE_MARK_DIRECT = '+';

  /** the state mark for omitting */
  private static final char STATE_MARK_OMIT = '-';

  /** the state mark for omitting */
  private static final char STATE_MARK_TO_SPACE = '_';
  /** the hyphen */
  private static final String HYPHEN_MARK = "hyphen"; //$NON-NLS-1$

  /** the data */
  private final char[][] m_data;

  /** the characters that can be written directly to the output */
  private final byte[] m_state;

  /** the hyphen text */
  private final char[] m_hyphen;

  /**
   * instantiate
   * 
   * @param form
   *          the form
   * @param resource
   *          the name of the resource file
   */
  protected LookupCharTransformer(final Normalizer.Form form,
      final String resource) {
    super(form);

    final ArrayList<char[]> al;
    byte[] state;
    int lastDir;
    String s, a, b;
    int i, chrA, chrB, cur, last;
    byte nextState;
    char[] chrs, hyphen;

    al = new ArrayList<>();
    state = new byte[256];
    lastDir = -1;
    hyphen = LookupCharTransformer.DEF_HY;

    // load the transformation description from a file
    try (final InputStream is = this.getClass().getResourceAsStream(
        resource)) {
      try (final InputStreamReader isr = new InputStreamReader(is)) {
        try (final BufferedReader br = new BufferedReader(isr)) {

          //
          outer: while ((s = br.readLine()) != null) {
            s = TextUtils.prepare(s);
            if (s == null) {
              continue;
            }
            i = s.indexOf(' ');
            if (i <= 0) {
              continue;
            }
            a = TextUtils.prepare(s.substring(0, i));
            if (a == null) {
              continue;
            }

            // did we find a hyphen mark?
            if (a.equals(LookupCharTransformer.HYPHEN_MARK)) {
              if (hyphen != LookupCharTransformer.DEF_HY) {
                throw new IllegalStateException(//
                    "You can only define one hyphen mark"); //$NON-NLS-1$
              }
              b = TextUtils.prepare(s.substring(i + 1));
              if (b != null) {
                hyphen = b.toCharArray();
              }
              continue;
            }

            a = a.toLowerCase();
            try {
              chrA = Integer.parseInt(a, 16);
            } catch (final Throwable t) {
              throw new IOException(t);
            }

            if (chrA < 0) {
              continue;
            }

            b = TextUtils.prepare(s.substring(i + 1));
            if (b == null) {
              b = EmptyUtils.EMPTY_STRING;
              chrB = LookupCharTransformer.STATE_MARK_OMIT;
            } else {
              chrB = b.charAt(0);
            }

            nextState = (-1);

            switch (chrB) {
              case LookupCharTransformer.STATE_MARK_DIRECT: {
                nextState = LookupCharTransformer.STATE_DIRECT;
                break;
              }
              case LookupCharTransformer.STATE_MARK_OMIT: {
                nextState = LookupCharTransformer.STATE_OMIT;
                break;
              }
              case LookupCharTransformer.STATE_MARK_TO_SPACE: {
                nextState = LookupCharTransformer.STATE_TO_SPACE;
                break;
              }
              default: {
                nextState = (((chrB == chrA) && (b.length() <= 1)) ? //
                LookupCharTransformer.STATE_DIRECT
                    : ((byte) (-1)));
              }
            }

            if (nextState >= 0) {
              if (chrA >= state.length) {
                state = Arrays.copyOf(state, ((chrA + 1) << 1));
              }
              state[chrA] = nextState;
              lastDir = Math.max(chrA, lastDir);

              if (lastDir >= 0x17e) { // keep special chars
                // outside of table
                continue outer; // for high char indices
              }

              // for low char indices, try to build a complete
              // table to speed
              // up
              // lookup operations
              chrs = new char[] { ((char) chrA) };
            } else {
              i = b.length();
              chrs = new char[i + 1];
              chrs[0] = ((char) (chrA));
              b.getChars(0, i, chrs, 1);
            }
            al.add(chrs);
          }
        }
      }
    } catch (final Throwable t) {
      throw new ExceptionInInitializerError(t);
    }

    // ok, we finished loading - now let us prepare the data
    this.m_hyphen = hyphen;

    // for lower-index characters, we have a direct lookup
    if (lastDir >= 0) {
      lastDir++;
      if (lastDir == state.length) {
        this.m_state = state;
      } else {
        this.m_state = new byte[lastDir];
        System.arraycopy(state, 0, this.m_state, 0, lastDir);
      }
    } else {
      this.m_state = LookupCharTransformer.DEF_STATE;
    }

    // the others go into a sorted table
    i = al.size();
    if (i > 0) {

      this.m_data = al.toArray(new char[i][]);
      Arrays.sort(this.m_data, _CharTransformerSorter.SORTER);

      last = (-1);
      for (final char ch[] : this.m_data) {
        cur = ch[0];
        if (cur == last) {
          throw new IllegalArgumentException(//
              "Double mapping for char " + cur + //$NON-NLS-1$
                  "(0x" + Integer.toHexString(cur) + //$NON-NLS-1$
                  "): " + Arrays.toString(ch));//$NON-NLS-1$
        }
        last = cur;
      }

    } else {
      this.m_data = LookupCharTransformer.DEF_DATA;
    }
  }

  /**
   * This method is called when an unknown character is encountered. By
   * default it throws an {@link java.lang.UnsupportedOperationException}.
   * 
   * @param ch
   *          the character
   * @throws UnsupportedOperationException
   *           by default
   */
  protected final void onUnknown(final char ch) {
    throw new UnsupportedOperationException(//
        "No mapping known for character '" + //$NON-NLS-1$
            String.valueOf(ch) + "' (0x" + //$NON-NLS-1$
            Integer.toHexString(ch) + ").");//$NON-NLS-1$

  }

  /** {@inheritDoc} */
  @SuppressWarnings("incomplete-switch")
  @Override
  protected final void doTransform(final CharInput in,
      final CharOutput out, final int start, final int end)
      throws IOException {

    final char[][] transform;
    final byte[] state;
    char[] replace;
    int i, currentStart, currentChar, midChar, low, high, mid;

    // setup
    transform = this.m_data;
    state = this.m_state;
    currentStart = start;

    // iterate over all characters in the character array data
    outer: for (i = currentStart; i < end; i++) {
      currentChar = in.get(i);

      // check if we need to do something with this character
      resolver: {

        // is there a state information for this character?
        if (currentChar < state.length) {

          // the state array tells us what to do
          switch (state[currentChar]) {

            case LookupCharTransformer.STATE_DIRECT: {
              // the character can be copied directly:
              // we continue the loop
              continue outer;
            }

            case LookupCharTransformer.STATE_OMIT: {
              // the character must be omitted:
              // set the replacement to null and write the previous
              // data
              replace = null;
              break resolver;
            }

            case LookupCharTransformer.STATE_TO_SPACE: {
              // the character must be transformed to a space:
              replace = LookupCharTransformer.TO_SPACE;
              break resolver;
            }

            /*
             * default: { // the character must be resolved! // so execute
             * the resolver! }
             */
          }
        }

        low = 0;
        high = transform.length;
        if (currentChar > high) {
          mid = (high >>> 1);
        } else {
          // if we are lucky, we find the transformation in one step
          mid = currentChar;
        }

        while (low <= high) {
          replace = transform[mid];
          midChar = replace[0];

          if (midChar < currentChar) {
            low = (mid + 1);
          } else {
            if (midChar > currentChar) {
              high = (mid - 1);
            } else {
              break resolver;
            }
          }

          mid = ((low + high) >>> 1);
        }

        // no transformation found: delegate
        this.onUnknown((char) currentChar); // this should throw an
        // error
        // if it did not throw an error, omit the char
        replace = null;
      }

      // write data before the current char
      in.write(out, currentStart, i);

      if (replace != null) {
        // if transformation found, write transformation
        out.write(replace, 1, replace.length);
      }

      // continue
      currentStart = (i + 1);
    }

    in.write(out, currentStart, end);
  }

  /** {@inheritDoc} */
  @Override
  @SuppressWarnings("incomplete-switch")
  protected final void doTransformHyphenated(final CharInput in,
      final CharOutput out, final int start, final int end)
      throws IOException {

    final char[][] transform;
    final byte[] state;
    final char[] hyphen;
    char[] replace;
    int i, currentStart, currentChar, midChar, low, high, mid;
    boolean doHyphen, nextDoHyphen, notFirst;

    hyphen = this.m_hyphen;
    if (hyphen.length <= 0) {
      this.transform(in, out, start, end);
      return;
    }

    // setup
    transform = this.m_data;
    state = this.m_state;
    nextDoHyphen = false;
    notFirst = false;
    currentStart = start;

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
        out.write(hyphen, 0, hyphen.length);
      }

      // check if we need to do something with this character
      resolver: {

        // is there a state information for this character?
        if (currentChar < state.length) {

          // the state array tells us what to do
          switch (state[currentChar]) {

            case LookupCharTransformer.STATE_DIRECT: {
              // the character can be copied directly:
              // we continue the loop
              continue outer;
            }

            case LookupCharTransformer.STATE_OMIT: {
              // the character must be omitted:
              // set the replacement to null and write the previous
              // data
              replace = null;
              break resolver;
            }

            case LookupCharTransformer.STATE_TO_SPACE: {
              // the character must be transformed to a space:
              replace = LookupCharTransformer.TO_SPACE;
              // no hyphen should follow this
              notFirst = nextDoHyphen = doHyphen = false;
              break resolver;
            }

            /*
             * default: { // the character must be resolved! // so execute
             * the resolver! }
             */
          }
        }

        low = 0;
        high = transform.length;
        if (currentChar > high) {
          mid = (high >>> 1);
        } else {
          // if we are lucky, we find the transformation in one step
          mid = currentChar;
        }

        while (low <= high) {
          replace = transform[mid];
          midChar = replace[0];

          if (midChar < currentChar) {
            low = (mid + 1);
          } else {
            if (midChar > currentChar) {
              high = (mid - 1);
            } else {
              break resolver;
            }
          }

          mid = ((low + high) >>> 1);
        }

        // no transformation found: delegate
        this.onUnknown((char) currentChar); // this should throw an
        // error
        // if it did not throw an error, omit the char
        replace = null;
      }

      // write data before the current char
      if (!doHyphen) {
        in.write(out, currentStart, i);
      }

      if (replace != null) {
        // if transformation found, write transformation
        out.write(replace, 1, replace.length);
      }

      // continue
      currentStart = (i + 1);
    }

    in.write(out, currentStart, end);
  }
}
