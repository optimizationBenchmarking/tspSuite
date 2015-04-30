package org.logisticPlanning.utils.document.impl.xhtml;

import java.io.IOException;

import org.logisticPlanning.utils.io.charIO.CharCharInput;
import org.logisticPlanning.utils.io.charIO.CharOutput;
import org.logisticPlanning.utils.io.charIO.StringBuilderCharOutput;
import org.logisticPlanning.utils.text.transformations.XMLCharTransformer;

/**
 * a sum
 */
final class _OpSumFA extends _OpDef {

  /** the big */
  private static final char[] BIG = _OpSumFT.BIG;

  /** the for all */
  private static final char[] FA;

  static {
    final int len;
    final StringBuilder sb;

    sb = new StringBuilder(128);

    sb.append(_OpSumFT.SMALL);

    try {
      XMLCharTransformer.INSTANCE.transform(new CharCharInput('\u2200'),
          new StringBuilderCharOutput(sb), 0, 1);
    } catch (final IOException ioe) {
      throw new RuntimeException(ioe);
    }
    sb.append(XHTMLDocument.NBSP);
    len = sb.length();
    FA = new char[len];
    sb.getChars(0, len, _OpSumFA.FA, 0);
  }

  /** the type */
  private final char[] m_type;

  /**
   * create
   * 
   * @param type
   *          the type
   */
  _OpSumFA(final char[] type) {
    super();
    this.m_type = type;
  }

  /** {@inheritDoc} */
  @Override
  final void render(final CharOutput out, final char[][] ops)
      throws IOException {
    char[] put;

    out.write(_OpDef.MO_TAB, 0, _OpDef.MO_TAB.length);

    out.write(_OpDef.MO_TR, 0, _OpDef.MO_TR.length);

    out.write(_OpSumFA.BIG, 0, _OpSumFA.BIG.length);
    put = this.m_type;
    out.write(put, 0, put.length);
    out.write(XHTMLDocument.TD_END, 0, XHTMLDocument.TD_END.length);

    out.write(_OpDef.MO_TD, 0, _OpDef.MO_TD.length);
    put = ops[1];
    if (put != null) {
      out.write(put, 0, put.length);
    }
    out.write(XHTMLDocument.TD_END, 0, XHTMLDocument.TD_END.length);

    out.write(XHTMLDocument.TR_END, 0, XHTMLDocument.TR_END.length);

    put = ops[0];
    if (put != null) {
      out.write(_OpDef.MO_TR, 0, _OpDef.MO_TR.length);
      out.write(_OpSumFA.FA, 0, _OpSumFA.FA.length);
      out.write(put, 0, put.length);
      out.write(XHTMLDocument.TD_END, 0, XHTMLDocument.TD_END.length);
      out.write(XHTMLDocument.TR_END, 0, XHTMLDocument.TR_END.length);
    }

    out.write(XHTMLDocument.TABLE_END, 0, XHTMLDocument.TABLE_END.length);
  }
}
