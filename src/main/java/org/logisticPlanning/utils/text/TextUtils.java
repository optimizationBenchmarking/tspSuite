package org.logisticPlanning.utils.text;

import org.logisticPlanning.utils.collections.lists.ArrayListView;
import org.logisticPlanning.utils.text.transformations.NormalCharTransformer;
import org.logisticPlanning.utils.utils.ReflectionUtils;

/**
 * The text utilities class provides several methods that help us to parse
 * Strings or to transform data to textual representations.
 */
public final class TextUtils {

  /** default single quotes start */
  static final char DEFAULT_SINGLE_A = 0x2018;
  /** default single quotes end */
  static final char DEFAULT_SINGLE_B = 0x2019;

  /** default double quotes start */
  static final char DEFAULT_DOUBLE_A = 0x201c;
  /** default double quotes end */
  static final char DEFAULT_DOUBLE_B = 0x201d;

  /** default triple quotes start */
  static final char DEFAULT_TRIPLE_A = 0x2037;
  /** default triple quotes end */
  static final char DEFAULT_TRIPLE_B = 0x2034;
  /** alternative single quotes start */
  static final char ALT_SINGLE_A = 0x2035;
  /** alternative single quotes end */
  static final char ALT_SINGLE_B = 0x2032;
  /** alternative double quotes start */
  static final char ALT_DOUBLE_A = 0x2036;
  /** alternative double quotes end */
  static final char ALT_DOUBLE_B = 0x2033;
  /** primitive double quotes */
  static final char PRIMITIVE_DOUBLE = 0x22;
  /** primitive single quotes */
  static final char PRIMITIVE_SINGLE = 0x27;
  /** angle double quotes start */
  static final char ANGLE_DOUBLE_A = 0x00ab;
  /** angle double quotes end */
  static final char ANGLE_DOUBLE_B = 0x00bb;
  /** primitive angle single quotes start */
  static final char PRIMITIVE_ANGLE_SINGLE_A = 0x3c;
  /** primitive angle single quotes end */
  static final char PRIMITIVE_ANGLE_SINGLE_B = 0x3e;
  /** angle single quotes start */
  static final char ANGLE_SINGLE_A = 0x2039;
  /** angle single quotes end */
  static final char ANGLE_SINGLE_B = 0x203a;
  /** Chinese angle single quotes start */
  static final char CHINESE_ANGLE_SINGLE_A = 0x3008;
  /** Chinese angle single quotes end */
  static final char CHINESE_ANGLE_SINGLE_B = 0x3009;
  /** Chinese angle double quotes start */
  static final char CHINESE_ANGLE_DOUBLE_A = 0x300a;
  /** Chinese angle double quotes end */
  static final char CHINESE_ANGLE_DOUBLE_B = 0x300b;
  /** Chinese bracket single quotes start */
  static final char CHINESE_BRACKET_SINGLE_A = 0x300c;
  /** Chinese bracket single quotes end */
  static final char CHINESE_BRACKET_SINGLE_B = 0x300d;
  /** Chinese bracket double quotes start */
  static final char CHINESE_BRACKET_DOUBLE_A = 0x300e;
  /** Chinese bracket double quotes end */
  static final char CHINESE_BRACKET_DOUBLE_B = 0x300f;
  /** Chinese double quotes start */
  static final char CHINESE_DOUBLE_A = 0x301d;
  /** Chinese double quotes end */
  static final char CHINESE_DOUBLE_B = 0x301e;
  /** accent single quotes start */
  static final char ACCENT_SINGLE_A = 0x60;
  /** accent single quotes end */
  static final char ACCENT_SINGLE_B = 0xb4;
  /** single bottom-top quotes */
  static final char SINGLE_BOTTOM_A = 0x201a;
  /** double bottom-top quotes */
  static final char DOUBLE_BOTTOM_A = 0x201e;
  /** digbat single quotes start */
  static final char DINGBAT_SINGLE_A = 0x275b;
  /** digbat single quotes end */
  static final char DINGBAT_SINGLE_B = 0x275c;

  /** digbat double quotes start */
  static final char DINGBAT_DOUBLE_A = 0x275d;
  /** digbat double quotes end */
  static final char DINGBAT_DOUBLE_B = 0x275e;

  /** parentheses a */
  static final char PARENTHESES_A = 0x28;
  /** parentheses b */
  static final char PARENTHESES_B = 0x29;
  /** brackets a */
  static final char BRACKETS_A = 0x5b;
  /** brackets b */
  static final char BRACKETS_B = 0x5d;
  /** braces a */
  static final char BRACES_A = 0x7b;
  /** braces b */
  static final char BRACES_B = 0x7d;
  /** vertical a */
  static final char VERTICAL_A = 0x203f;
  /** vertical b */
  static final char VERTICAL_B = 0x2040;
  /** brackets with dash a */
  static final char BRACKETS_WITH_DASH_A = 0x2045;
  /** brackets with dash b */
  static final char BRACKETS_WITH_DASH_B = 0x2046;
  /** raised parentheses a */
  static final char RAISED_PARENTHESES_A = 0x207d;
  /** raised parentheses b */
  static final char RAISED_PARENTHESES_B = 0x207e;
  /** sunk parentheses a */
  static final char SUNK_PARENTHESES_A = 0x208d;
  /** sunk parentheses b */
  static final char SUNK_PARENTHESES_B = 0x208e;

  /** checvron a */
  static final char CHEVRON_A = 0x27e8;
  /** checvron b */
  static final char CHEVRON_B = 0x27e9;

  /** chinese black parentheses a */
  static final char CHINESE_BLACK_PARENTHESES_A = 0x3010;
  /** chinese black parentheses b */
  static final char CHINESE_BLACK_PARENTHESES_B = 0x3011;
  /** chinese convex parentheses a */
  static final char CHINESE_CONVEX_A = 0x3014;
  /** chinese convex parentheses b */
  static final char CHINESE_CONVEX_B = 0x3015;
  /** chinese white parentheses a */
  static final char CHINESE_WHITE_PARENTHESES_A = 0x3016;
  /** chinese white parentheses b */
  static final char CHINESE_WHITE_PARENTHESES_B = 0x3017;
  /** chinese double convex parentheses a */
  static final char CHINESE_DOUBLE_CONVEX_A = 0x3018;
  /** chinese double convex parentheses b */
  static final char CHINESE_DOUBLE_CONVEX_B = 0x3019;
  /** chinese double braces a */
  static final char CHINESE_DOUBLE_BRACES_A = 0x301a;
  /** chinese double braces b */
  static final char CHINESE_DOUBLE_BRACES_B = 0x301b;

  /** the null string */
  public static final String NULL_STRING = String.valueOf((Object) null);

  /**
   * the globally shared line separator: The value of this variable is
   * compatible to what {@link java.io.BufferedWriter} et al. are using
   */
  public static final String LINE_SEPARATOR = TextUtils.getLineSeparator();

  /**
   * the set of small numbers that always should be written directly as
   * text: In written English, the numbers from {@code 0} to {@code 12}
   * should be written as words, whereas outside that range should be
   * written as numbers.
   */
  public static final ArrayListView<String> SMALL_NUMBERS = //
      ArrayListView.makeArrayListView(_NumberText.SMALL_NUMBERS);

  /**
   * obtain the line separator
   *
   * @return the line separator
   */
  private static final String getLineSeparator() {
    final byte[] bs;
    final char[] cs;
    final int l;
    String r;
    int i;

    r = null;
    try {
      try (java.io.CharArrayWriter caw = new java.io.CharArrayWriter(2)) {
        try (java.io.BufferedWriter bw = new java.io.BufferedWriter(caw, 2)) {
          bw.newLine();
          bw.flush();
        }
        r = caw.toString();
        if ((r != null) && (r.length() > 0)) {
          return r;
        }
      }
    } catch (final Throwable tt) {
      //
    }

    try {
      try (java.io.CharArrayWriter caw = new java.io.CharArrayWriter(2)) {
        try (java.io.PrintWriter pw = new java.io.PrintWriter(caw)) {
          pw.println();
          pw.flush();
        }
        r = caw.toString();
        if ((r != null) && (r.length() > 0)) {
          return r;
        }
      }
    } catch (final Throwable tt) {
      //
    }

    try {
      try (java.io.ByteArrayOutputStream baos = //
          new java.io.ByteArrayOutputStream(2)) {
        try (java.io.PrintStream ps = new java.io.PrintStream(baos)) {
          ps.println();
          ps.flush();
        }
        bs = baos.toByteArray();
        if (bs != null) {
          l = bs.length;
          if (l > 0) {
            cs = new char[l];
            for (i = l; (--i) >= 0;) {
              cs[i] = ((char) (bs[i]));
              r = new String(cs, 0, l);
            }
          }
        }
      }
    } catch (final Throwable tt) {
      //
    }

    if ((r != null) && (r.length() > 0)) {
      return r;
    }
    return "\n"; //$NON-NLS-1$
  }

  /**
   * Parse a boolean value
   *
   * @param s
   *          the string to parse
   * @return -1 for false, 1 for true, 0 for undecided
   */
  public static final boolean parseBoolean(final String s) {
    final _PreparedString prep;
    Boolean b;
    _TextConst cnst;
    String str;

    prep = new _PreparedString(s); // this object should land on the stack

    outer: while ((str = prep.next()) != null) {

      for (;;) {
        cnst = _TextConst.findConst(str);
        if (cnst != null) {
          if (cnst.hasBoolean()) {
            return cnst.m_b;
          }
          break outer;
        }

        try {
          b = ReflectionUtils.getStaticConstant(str, Boolean.class);
          if (b != null) {
            return prep.getReturn(b.booleanValue());
          }
        } catch (final Throwable t) {
          // ignore this
        }
      }
    }

    throw new IllegalArgumentException('\'' + s + //
        "' is not a valid boolean value."); //$NON-NLS-1$
  }

  /**
   * Parse a {@link java.lang.String} to a 8 bit signed integer (byte)
   * value. This method tries to first convert the string directly, then
   * looks for synonyms and constant values that may match to the string.
   *
   * @param s
   *          the string to parse
   * @return the byte value
   * @throws NumberFormatException
   *           if the number is formatted wrongly
   */
  public static final byte parseByte(final String s)
      throws NumberFormatException {

    return ((byte) (Math.min((java.lang.Byte.MAX_VALUE),
        Math.max((java.lang.Byte.MIN_VALUE), TextUtils.parseInt(s)))));
  }

  /**
   * Parse a {@link java.lang.String} to a 16 bit signed integer (short)
   * value. This method tries to first convert the string directly, then
   * looks for synonyms and constant values that may match to the string.
   *
   * @param s
   *          the string to parse
   * @return the short value
   * @throws NumberFormatException
   *           if the number is formatted wrongly
   */
  public static final short parseShort(final String s)
      throws NumberFormatException {

    return ((short) (Math.min((java.lang.Short.MAX_VALUE),
        Math.max((java.lang.Short.MIN_VALUE), TextUtils.parseInt(s)))));
  }

  /**
   * get the base identified by the character
   *
   * @param chr
   *          the identifier
   * @return the base, or {@code 0} if none
   */
  @SuppressWarnings("incomplete-switch")
  private static final int _getBase(final int chr) {
    switch (chr) {
      case 'B':
      case 'b': {// binary
        return 2;
      }
      case 'D':
      case 'd': {// decimal
        return 10;
      }
      case 'O':
      case 'o': {// octal
        return 8;
      }
      case 'X':
      case 'x': {// hexadecimal
        return 16;
      }
    }
    return 0;
  }

  /**
   * Parse a {@link java.lang.String} to a 32 bit signed integer (int)
   * value. This method tries to first convert the string directly, then
   * looks for synonyms and constant values that may match to the string.
   *
   * @param s
   *          the string to parse
   * @return the int value
   * @throws NumberFormatException
   *           if the number is formatted wrongly
   */
  public static final int parseInt(final String s)
      throws NumberFormatException {
    final _PreparedString prep;
    String str;
    _TextConst cnst;
    Number number;
    int base;

    try {
      // first we try to cast the string directly to a int
      // this will be the fast execution path for 'correctly' formatted
      // numbers
      return java.lang.Integer.parseInt(s);

    } catch (final NumberFormatException origNumberError) {
      // ok, the string does not follow java's default number format
      // so now, we check whether it is hexadecimal/octal/binary
      // formatted, a
      // constant, or even a static member identifier

      prep = new _PreparedString(s);

      outer: while ((str = prep.next()) != null) {
        // we now iterate over the prepared strings: step-by-step, we
        // will try
        // to process the string by, e.g., trimming it; removing +, -,
        // (, ),
        // etc; removing internal white spaces and underscores (to match
        // Java
        // 7's new binary syntax) and so on - until we can either parse
        // it or
        // have to give up

        if (str != s) {
          // try again to parse the string directly
          try {
            return prep.getReturn(java.lang.Integer.parseInt(str));
          } catch (final Throwable canBeIgnored) {
            // we ignore this exception, as we will throw the
            // original one
            // on failure
          }
        }

        // let's see if it was a constant in some other base
        if ((str.length() > 2) && (str.charAt(0) == '0')) {
          if ((base = TextUtils._getBase(str.charAt(1))) != 0) {
            try {
              return prep.getReturn(java.lang.Integer.parseInt(
                  str.substring(2), base));
            } catch (final Throwable canBeIgnored) {
              // we ignore this exception, as we will throw the
              // original one
              // on failure
            }
          }
        }

        // does the string correspond to a constant we know?
        cnst = _TextConst.findConst(str);
        if (cnst != null) {
          if (cnst.hasInt()) {
            return prep.getReturn(cnst.m_i);
          }
          break outer;
        }

        // ok, it is no constant, maybe it is a public static final
        // member?
        try {
          number = ReflectionUtils.getStaticConstant(str, Number.class);
          if (number != null) {
            return prep.getReturn(number.intValue());
          }
        } catch (final Throwable canBeIgnored) {
          // ignore this exception: it will be thrown if no member
          // fits
          // in which case we will throw the original error anyway at
          // the
          // end
        }
      }

      // ok, all our ideas to resolve the number have failed - throw
      // original
      // error again
      throw origNumberError;
    }
  }

  /**
   * Parse a {@link java.lang.String} to a 64 bit signed integer (long)
   * value. This method tries to first convert the string directly, then
   * looks for synonyms and constant values that may match to the string.
   *
   * @param s
   *          the string to parse
   * @return the long value
   * @throws NumberFormatException
   *           if the number is formatted wrongly
   */
  public static final long parseLong(final String s)
      throws NumberFormatException {
    final _PreparedString prep;
    String str;
    _TextConst cnst;
    Number number;
    int base;

    try {
      // first we try to cast the string directly to a long
      // this will be the fast execution path for 'correctly' formatted
      // numbers
      return java.lang.Long.parseLong(s);

    } catch (final NumberFormatException origNumberError) {
      // ok, the string does not follow java's default number format
      // so now, we check whether it is hexadecimal/octal/binary
      // formatted, a
      // constant, or even a static member identifier

      prep = new _PreparedString(s);

      outer: while ((str = prep.next()) != null) {
        // we now iterate over the prepared strings: step-by-step, we
        // will try
        // to process the string by, e.g., trimming it; removing +, -,
        // (, ),
        // etc; removing internal white spaces and underscores (to match
        // Java
        // 7's new binary syntax) and so on - until we can either parse
        // it or
        // have to give up

        if (str != s) {
          // try again to parse the string directly
          try {
            return prep.getReturn(java.lang.Long.parseLong(str));
          } catch (final Throwable canBeIgnored) {
            // we ignore this exception, as we will throw the
            // original one
            // on failure
          }
        }

        // let's see if it was a constant in some other base
        if ((str.length() > 2) && (str.charAt(0) == '0')) {
          if ((base = TextUtils._getBase(str.charAt(1))) != 0) {
            try {
              return prep.getReturn(java.lang.Long.parseLong(
                  str.substring(2), base));
            } catch (final Throwable canBeIgnored) {
              // we ignore this exception, as we will throw the
              // original one
              // on failure
            }
          }
        }

        // does the string correspond to a constant we know?
        cnst = _TextConst.findConst(str);
        if (cnst != null) {
          if (cnst.hasInt()) {
            return prep.getReturn(cnst.m_l);
          }
          break outer;
        }

        // ok, it is no constant, maybe it is a public static final
        // member?
        try {
          number = ReflectionUtils.getStaticConstant(str, Number.class);
          if (number != null) {
            return prep.getReturn(number.longValue());
          }
        } catch (final Throwable canBeIgnored) {
          // ignore this exception: it will be thrown if no member
          // fits
          // in which case we will throw the original error anyway at
          // the
          // end
        }
      }

      // ok, all our ideas to resolve the number have failed - throw
      // original
      // error again
      throw origNumberError;
    }
  }

  /**
   * Parse a {@link java.lang.String} to a single-precision (32-bit)
   * floating point number (float) value. This method tries to first
   * convert the string directly, then looks for synonyms and constant
   * values that may match to the string.
   *
   * @param s
   *          the string to parse
   * @return the float value
   * @throws NumberFormatException
   *           if the number is formatted wrongly
   */
  public static final float parseFloat(final String s)
      throws NumberFormatException {
    return ((float) (TextUtils.parseDouble(s)));
  }

  /**
   * Parse a {@link java.lang.String} to a double-precision (64-bit)
   * floating point number (double) value. This method tries to first
   * convert the string directly, then looks for synonyms and constant
   * values that may match to the string.
   *
   * @param s
   *          the string to parse
   * @return the double value
   * @throws NumberFormatException
   *           if the number is formatted wrongly
   */
  public static final double parseDouble(final String s)
      throws NumberFormatException {
    final _PreparedString prep;
    String str;
    _TextConst cnst;
    Number number;
    int base;

    try {
      // first we try to cast the string directly to a long
      // this will be the fast execution path for 'correctly' formatted
      // numbers
      return java.lang.Double.parseDouble(s);

    } catch (final NumberFormatException origNumberError) {
      // ok, the string does not follow java's default number format
      // so now, we check whether it is hexadecimal/octal/binary
      // formatted, a
      // constant, or even a static member identifier
      prep = new _PreparedString(s);

      outer: while ((str = prep.next()) != null) {
        // we now iterate over the prepared strings: step-by-step, we
        // will try
        // to process the string by, e.g., trimming it; removing +, -,
        // (, ),
        // etc; removing internal white spaces and underscores (to match
        // Java
        // 7's new binary syntax) and so on - until we can either parse
        // it or
        // have to give up

        // try again to parse the string directly
        if (str != s) {
          try {
            return prep.getReturn(java.lang.Double.parseDouble(str));
          } catch (final Throwable canBeIgnored) {
            // we ignore this exception, as we will throw the
            // original one
            // on failure
          }
        }

        // let's see if it was a constant in some other base
        if ((str.length() > 2) && (str.charAt(0) == '0')) {
          if ((base = TextUtils._getBase(str.charAt(1))) != 0) {
            try {
              return prep.getReturn(java.lang.Long.parseLong(
                  str.substring(2), base));
            } catch (final Throwable canBeIgnored) {
              // we ignore this exception, as we will throw the
              // original one
              // on failure
            }
          }
        }

        // does the string correspond to a constant we know?
        cnst = _TextConst.findConst(str);
        if (cnst != null) {
          if (cnst.hasDouble()) {
            return prep.getReturn(cnst.m_d);
          }
          break outer;
        }

        // ok, it is no constant, maybe it is a public static final
        // member?
        try {
          number = ReflectionUtils.getStaticConstant(str, Number.class);
          if (number != null) {
            return prep.getReturn(number.doubleValue());
          }
        } catch (final Throwable canBeIgnored) {
          // ignore this exception: it will be thrown if no member
          // fits
          // in which case we will throw the original error anyway at
          // the
          // end
        }
      }

      // ok, all our ideas to resolve the number have failed - throw
      // original
      // error again
      throw origNumberError;
    }
  }

  /**
   * Parse a character
   *
   * @param s
   *          the string to parse
   * @return the character value
   * @throws NumberFormatException
   *           if the number is formatted wrongly
   */
  public static final char parseChar(final String s)
      throws NumberFormatException {
    return (((s != null) && (s.length() > 0)) ? s.charAt(0) : 0);
  }

  /**
   * convert a double to a short string
   *
   * @param t
   *          the double
   * @return the string
   */
  private static final String _d2s(final double t) {
    String s;
    int i;

    s = Double.toString(t);
    i = s.length();
    if (i <= 2) {
      return s;
    }
    if ((s.charAt(--i) == '0') && (s.charAt(--i) == '.')) {
      return s.substring(0, i);
    }
    return s;
  }

  /**
   * convert a double to string
   *
   * @param t
   *          the double
   * @return the string
   */
  public static final String toStringApprox(final double t) {
    return TextUtils.toStringApprox(t, 3);
  }

  /**
   * convert a double to string
   *
   * @param t
   *          the double
   * @param steps
   *          the number of value steps permitted in both directions
   * @return the string
   */
  public static final String toStringApprox(final double t, final int steps) {
    final String s1;
    String s2, s3;
    double a, b;
    int i;

    s1 = TextUtils._d2s(t);
    if (Double.isInfinite(t) || Double.isNaN(t)) {
      return s1;
    }

    a = b = t;
    s2 = s3 = s1;
    s3 = s1;
    for (i = steps; (--i) >= 0;) {
      a = Math.nextUp(a);
      if (!(Double.isInfinite(a) || Double.isNaN(a))) {
        s2 = TextUtils._d2s(a);
        if (s2.length() < s3.length()) {
          s3 = s2;
        }
      }

      b = Math.nextAfter(b, Double.NEGATIVE_INFINITY);
      if (!(Double.isInfinite(b) || Double.isNaN(b))) {
        s2 = TextUtils._d2s(b);
        if (s2.length() < s3.length()) {
          s3 = s2;
        }
      }
    }

    if (s3.length() < (s1.length() - 2)) {
      return s3;
    }

    return s1;
  }

  /**
   * Prepare a string by trimming it and setting it to {@code null} if the
   * length is 0.
   *
   * @param s
   *          the string
   * @return the prepared version of {@code s}, {@code null} if it was
   *         empty
   */
  public static final String prepare(final String s) {
    String t;

    if (s == null) {
      return null;
    }

    t = s.trim();
    if (t.length() <= 0) {
      return null;
    }

    return t;
  }

  /**
   * Transform a string to its normalized representation. A normalized
   * string will only contain the Latin letters {@code A} to {@code Z} in
   * upper and lower case (i.e., {@code A} to {@code Z} and {@code a} to
   * {@code z}). All other characters or numerals are replaced with their
   * unique names. For instance, {@code -} becomes {@code Minus}, {@code 6}
   * becomes {@code Six}, {@code ?} becomes {@code QuestionMark} and so on.
   * Such a transformation makes sense if strings are to be printed a
   * format that can be used as command names in a programming language or
   * as identifier in an XML document for instance. As only the 26 Latin
   * characters are used and all other characters are translated to their
   * textual names, this should preserve the identity of strings while
   * turning them into valid identifiers in virtually all reasonable
   * textual formats or programming language.
   *
   * @param name
   *          the name to be sanitized
   * @return the name to be sanitized
   */
  public static final String normalize(final String name) {
    return NormalCharTransformer.INSTANCE.transform(name);
  }

  /**
   * Checks whether a character is a quotation character or not and returns
   * the appropriate
   * {@link org.logisticPlanning.utils.text.EQuotes#getDashCount() dash
   * count} . See also {@link org.logisticPlanning.utils.text.EQuotes}.
   *
   * @param character
   *          the character
   * @return {@code 0} if the character is not a quotation character, 1 for
   *         single quotes like {@code '}, 2 for double quotes like
   *         {@code "}, 3 for triple quotes like {@code &#x2024;} and so on
   */
  @SuppressWarnings("incomplete-switch")
  public static final int isQuote(final int character) {
    switch (character) {

      case PRIMITIVE_DOUBLE: { // "
        return 2;
      }

      case PRIMITIVE_SINGLE: // '
      case PRIMITIVE_ANGLE_SINGLE_A: // <
      case PRIMITIVE_ANGLE_SINGLE_B: // >
      case ACCENT_SINGLE_A: { // `
        return 1;
      }

      case ANGLE_DOUBLE_A: {
        return 2;
      }
      case ACCENT_SINGLE_B: // `
      {
        return 1;
      }
      case ANGLE_DOUBLE_B: {
        return 2;
      }

      case DEFAULT_SINGLE_A:
      case DEFAULT_SINGLE_B:
      case SINGLE_BOTTOM_A:
      case 0x201b: {
        return 1;
      }

      case DEFAULT_DOUBLE_A:
      case DEFAULT_DOUBLE_B:
      case DOUBLE_BOTTOM_A:
      case 0x201f: {
        return 2;
      }

      case ALT_SINGLE_B: {
        return 1;
      }

      case ALT_DOUBLE_B: {
        return 2;
      }
      case DEFAULT_TRIPLE_B: {
        return 3;
      }

      case ALT_SINGLE_A: {
        return 1;
      }

      case ALT_DOUBLE_A: {
        return 2;
      }

      case DEFAULT_TRIPLE_A: {
        return 3;
      }

      case ANGLE_SINGLE_A:
      case ANGLE_SINGLE_B:
      case DINGBAT_SINGLE_A:
      case DINGBAT_SINGLE_B: {
        return 1;
      }

      case DINGBAT_DOUBLE_A:
      case DINGBAT_DOUBLE_B: {
        return 2;
      }

      case CHINESE_ANGLE_SINGLE_A:
      case CHINESE_ANGLE_SINGLE_B: {
        return 1;
      }
      case CHINESE_ANGLE_DOUBLE_A:
      case CHINESE_ANGLE_DOUBLE_B: {
        return 2;
      }

      case CHINESE_BRACKET_SINGLE_A:
      case CHINESE_BRACKET_SINGLE_B: {
        return 1;
      }
      case CHINESE_BRACKET_DOUBLE_A:
      case CHINESE_BRACKET_DOUBLE_B:
      case CHINESE_DOUBLE_A:
      case CHINESE_DOUBLE_B:
      case 0x301f: {
        return 2;
      }

    }

    return (0);
  }

  /**
   * checks whether a character is a brace or not. See also
   * {@link org.logisticPlanning.utils.text.EBraces}.
   *
   * @param character
   *          the character
   * @return {@code 0} if the character is not a brace, a positive value
   *         for an opening brace, a negative value for a closing one
   *         &ndash; two values will add up to zero for two corresponding
   *         opening and closing braces
   */
  @SuppressWarnings("incomplete-switch")
  public static final int isBrace(final int character) {
    switch (character) {
      case PARENTHESES_A: {
        return 1;
      }
      case PARENTHESES_B: {
        return (-1);
      }
      case BRACKETS_A: {
        return 2;
      }
      case BRACKETS_B: {
        return (-2);
      }
      case BRACES_A: {
        return 3;
      }
      case BRACES_B: {
        return (-3);
      }
      case VERTICAL_A: {
        return 4;
      }
      case VERTICAL_B: {
        return (-4);
      }
      case BRACKETS_WITH_DASH_A: {
        return 5;
      }
      case BRACKETS_WITH_DASH_B: {
        return (-5);
      }
      case RAISED_PARENTHESES_A: {
        return 6;
      }
      case RAISED_PARENTHESES_B: {
        return (-6);
      }
      case SUNK_PARENTHESES_A: {
        return 7;
      }
      case SUNK_PARENTHESES_B: {
        return (-7);
      }

      case CHEVRON_A: {
        return 8;
      }
      case CHEVRON_B: {
        return (-8);
      }
      case CHINESE_BLACK_PARENTHESES_A: {
        return 9;
      }
      case CHINESE_BLACK_PARENTHESES_B: {
        return (-9);
      }

      case CHINESE_CONVEX_A: {
        return 10;
      }
      case CHINESE_CONVEX_B: {
        return (-10);
      }
      case CHINESE_WHITE_PARENTHESES_A: {
        return 11;
      }
      case CHINESE_WHITE_PARENTHESES_B: {
        return (-11);
      }
      case CHINESE_DOUBLE_CONVEX_A: {
        return 12;
      }
      case CHINESE_DOUBLE_CONVEX_B: {
        return (-12);
      }
      case CHINESE_DOUBLE_BRACES_A: {
        return 13;
      }
      case CHINESE_DOUBLE_BRACES_B: {
        return (-13);
      }
    }

    return 0;
  }

  /**
   * Get an easy-to-use representation of a class.
   *
   * @param c
   *          the class
   * @return the name
   */
  public static final String className(final Class<?> c) {
    String s;

    if (c == null) {
      return "No class specified."; //$NON-NLS-1$
    }

    s = c.getCanonicalName();
    if ((s != null) && (s.length() > 0)) {
      return s;
    }

    s = c.getName();
    if ((s != null) && (s.length() > 0)) {
      return s;
    }

    s = c.getSimpleName();
    if ((s != null) && (s.length() > 0)) {
      return s;
    }

    s = c.toString();
    if ((s != null) && (s.length() > 0)) {
      return s;
    }
    return "nameless"; //$NON-NLS-1$
  }

  /**
   * Append a {@code long} to a string builder in a representation which
   * does not contain any numbers and only textually describes the value of
   * the number.
   *
   * @param number
   *          the number
   * @param dest
   *          the destination string builder
   */
  public static final void appendLongAsText(final long number,
      final StringBuilder dest) {
    _NumberText._appendLongAsText(number, dest, dest.length(), 0);
  }

  /**
   * Convert a {@code long} to a full textual representation, i.e., to a
   * representation which does not contain any numbers and only textually
   * describes the value of the number.
   *
   * @param number
   *          the number
   * @return the string version of {@code number}
   */
  public static final String longToText(final long number) {
    final StringBuilder sb;

    sb = new StringBuilder(128);
    _NumberText._appendLongAsText(number, sb, 0, 0);
    return sb.toString();
  }

  /**
   * Append a {@code double} to a string builder in a representation which
   * does not contain any numbers and only textually describes the value of
   * the number.
   *
   * @param number
   *          the number
   * @param dest
   *          the destination string builder
   */
  public static final void appendDoubleAsText(final long number,
      final StringBuilder dest) {
    _NumberText._appendDoubleAsText(number, dest, dest.length());
  }

  /**
   * Convert a {@code double} to textual representation, i.e., to a
   * representation which does not contain any numbers and only textually
   * describes the value of the number.
   *
   * @param number
   *          the number
   * @return the string version of {@code number}
   */
  public static final String doubleToText(final double number) {
    final StringBuilder sb;

    sb = new StringBuilder(128);
    _NumberText._appendDoubleAsText(number, sb, 0);
    return sb.toString();
  }
}
