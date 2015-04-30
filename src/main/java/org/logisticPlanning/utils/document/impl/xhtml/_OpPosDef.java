package org.logisticPlanning.utils.document.impl.xhtml;

import java.io.IOException;

import org.logisticPlanning.utils.io.charIO.CharOutput;

/**
 * an operator definition with the operator at a given position
 */
final class _OpPosDef extends _OpDef {

  /** the before */
  private final char[] m_before;

  /** the middle */
  private final char[] m_middle;

  /** the after */
  private final char[] m_after;

  /**
   * create
   * 
   * @param before
   *          the before
   * @param middle
   *          the middle
   * @param after
   *          the after
   */
  _OpPosDef(final char[] before, final char[] middle, final char[] after) {
    super();
    this.m_before = before;
    this.m_middle = middle;
    this.m_after = after;
  }

  /** {@inheritDoc} */
  @Override
  final void render(final CharOutput out, final char[][] ops)
      throws IOException {
    char[] put;

    out.write(_OpDef.MO_TAB, 0, _OpDef.MO_TAB.length);
    out.write(_OpDef.MO_TR, 0, _OpDef.MO_TR.length);

    put = this.m_before;
    for (final char[] ch : ops) {
      if (put != null) {
        out.write(_OpDef.MO_TD, 0, _OpDef.MO_TD.length);
        out.write(put, 0, put.length);
        out.write(XHTMLDocument.TD_END, 0, XHTMLDocument.TD_END.length);
      }
      out.write(_OpDef.MO_TD, 0, _OpDef.MO_TD.length);
      if (ch != null) {
        out.write(ch, 0, ch.length);
      }
      out.write(XHTMLDocument.TD_END, 0, XHTMLDocument.TD_END.length);
      put = this.m_middle;
    }

    if (this.m_after != null) {
      out.write(_OpDef.MO_TD, 0, _OpDef.MO_TD.length);
      out.write(this.m_after, 0, this.m_after.length);
      out.write(XHTMLDocument.TD_END, 0, XHTMLDocument.TD_END.length);
    }

    out.write(XHTMLDocument.TR_END, 0, XHTMLDocument.TR_END.length);
    out.write(XHTMLDocument.TABLE_END, 0, XHTMLDocument.TABLE_END.length);
  }
}
