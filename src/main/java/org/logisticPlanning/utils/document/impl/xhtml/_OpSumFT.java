package org.logisticPlanning.utils.document.impl.xhtml;

import java.io.IOException;

import org.logisticPlanning.utils.io.charIO.CharOutput;

/**
 * a sum
 */
final class _OpSumFT extends _OpDef {

  /** the big */
  static final char[] BIG = { '<', 't', 'd', ' ', 'c', 'l', 'a', 's', 's',
      '=', '"', 'm', 'a', 't', 'h', 'O', 'p', 'B', 'i', 'g', '"', '>' };

  /** the small */
  static final char[] SMALL = { '<', 't', 'd', ' ', 'c', 'l', 'a', 's',
      's', '=', '"', 'm', 'a', 't', 'h', 'O', 'p', 'S', 'm', 'a', 'l',
      'l', '"', '>' };

  /** the type */
  private final char[] m_type;

  /**
   * create
   *
   * @param type
   *          the type
   */
  _OpSumFT(final char[] type) {
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
    out.write(_OpSumFT.SMALL, 0, _OpSumFT.SMALL.length);
    put = ops[1];
    if (put != null) {
      out.write(put, 0, put.length);
    }
    out.write(XHTMLDocument.TD_END, 0, XHTMLDocument.TD_END.length);
    out.write(XHTMLDocument.TR_END, 0, XHTMLDocument.TR_END.length);

    out.write(_OpDef.MO_TR, 0, _OpDef.MO_TR.length);

    out.write(_OpSumFT.BIG, 0, _OpSumFT.BIG.length);
    put = this.m_type;
    out.write(put, 0, put.length);
    out.write(XHTMLDocument.TD_END, 0, XHTMLDocument.TD_END.length);

    out.write(_OpDef.MO_TD, 0, _OpDef.MO_TD.length);
    put = ops[2];
    if (put != null) {
      out.write(put, 0, put.length);
    }
    out.write(XHTMLDocument.TD_END, 0, XHTMLDocument.TD_END.length);

    out.write(XHTMLDocument.TR_END, 0, XHTMLDocument.TR_END.length);

    out.write(_OpDef.MO_TR, 0, _OpDef.MO_TR.length);
    out.write(_OpSumFT.SMALL, 0, _OpSumFT.SMALL.length);
    put = ops[0];
    if (put != null) {
      out.write(put, 0, put.length);
    }
    out.write(XHTMLDocument.TD_END, 0, XHTMLDocument.TD_END.length);
    out.write(XHTMLDocument.TR_END, 0, XHTMLDocument.TR_END.length);

    out.write(XHTMLDocument.TABLE_END, 0, XHTMLDocument.TABLE_END.length);
  }
}
