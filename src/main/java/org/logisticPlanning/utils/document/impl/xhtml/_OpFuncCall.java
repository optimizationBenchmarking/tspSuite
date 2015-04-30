package org.logisticPlanning.utils.document.impl.xhtml;

import java.io.IOException;

import org.logisticPlanning.utils.io.charIO.CharOutput;

/**
 * an operator definition with the operator at a given position
 */
final class _OpFuncCall extends _OpDef {

  /** create */
  _OpFuncCall() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  final void render(final CharOutput out, final char[][] ops)
      throws IOException {
    int i;
    char[] ch, put;

    out.write(_OpDef.MO_TAB, 0, _OpDef.MO_TAB.length);
    out.write(_OpDef.MO_TR, 0, _OpDef.MO_TR.length);

    put = ops[0];
    out.write(_OpDef.MO_TD, 0, _OpDef.MO_TD.length);
    if (put != null) {
      out.write(put, 0, put.length);
    }
    out.write(XHTMLDocument.TD_END, 0, XHTMLDocument.TD_END.length);

    put = _OpDef.GAP_OPEN;
    for (i = 1; i < ops.length; i++) {

      out.write(_OpDef.MO_TD, 0, _OpDef.MO_TD.length);
      out.write(put, 0, put.length);
      out.write(XHTMLDocument.TD_END, 0, XHTMLDocument.TD_END.length);

      ch = ops[i];
      if (ch != null) {
        out.write(_OpDef.MO_TD, 0, _OpDef.MO_TD.length);
        out.write(ch, 0, ch.length);
        out.write(XHTMLDocument.TD_END, 0, XHTMLDocument.TD_END.length);
      }

      put = _OpDef.COMMA;
    }

    out.write(_OpDef.MO_TD, 0, _OpDef.MO_TD.length);
    out.write(_OpDef.GAP_CLOSE, 0, _OpDef.GAP_CLOSE.length);
    out.write(XHTMLDocument.TD_END, 0, XHTMLDocument.TD_END.length);

    out.write(XHTMLDocument.TR_END, 0, XHTMLDocument.TR_END.length);
    out.write(XHTMLDocument.TABLE_END, 0, XHTMLDocument.TABLE_END.length);
  }
}
