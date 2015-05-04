package org.logisticPlanning.utils.document.impl.xhtml;

import java.io.IOException;

import org.logisticPlanning.utils.io.charIO.CharOutput;

/**
 * a pwoer
 */
final class _OpPow extends _OpDef {

  /** the instance */
  static final _OpDef INST = new _OpPow();

  /** the overlined cell */
  private final static char[] EXP = { '<', 't', 'd', ' ', 'c', 'l', 'a',
      's', 's', '=', '"', 'm', 'a', 't', 'h', 'E', 'x', 'p', '"', ' ',
      'r', 'o', 'w', 's', 'p', 'a', 'n', '=', '"', '2', '"', '>' };
  /** the power tr */
  private final static char[] POWR = { '<', 't', 'r', ' ', 'c', 'l', 'a',
      's', 's', '=', '"', 'm', 'a', 't', 'h', 'P', 'o', 'w', 'T', 'R',
      '"', '>' };

  /** create */
  private _OpPow() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  final void render(final CharOutput out, final char[][] ops)
      throws IOException {
    char[] put;

    out.write(_OpDef.MO_TAB, 0, _OpDef.MO_TAB.length);
    out.write(_OpPow.POWR, 0, _OpPow.POWR.length);
    out.write(_OpDef.MO_TD, 0, _OpDef.MO_TD.length);
    out.write(XHTMLDocument.TD_END, 0, XHTMLDocument.TD_END.length);

    out.write(_OpPow.EXP, 0, _OpPow.EXP.length);
    put = ops[1];
    if (put != null) {
      out.write(put, 0, put.length);
    }
    out.write(XHTMLDocument.TD_END, 0, XHTMLDocument.TD_END.length);
    out.write(XHTMLDocument.TR_END, 0, XHTMLDocument.TR_END.length);

    out.write(_OpDef.MO_TR, 0, _OpDef.MO_TR.length);
    out.write(_OpDef.MO_TD, 0, _OpDef.MO_TD.length);

    put = ops[0];
    if (put != null) {
      out.write(put, 0, put.length);
    }

    out.write(XHTMLDocument.TD_END, 0, XHTMLDocument.TD_END.length);
    out.write(XHTMLDocument.TR_END, 0, XHTMLDocument.TR_END.length);
    out.write(XHTMLDocument.TABLE_END, 0, XHTMLDocument.TABLE_END.length);
  }
}
