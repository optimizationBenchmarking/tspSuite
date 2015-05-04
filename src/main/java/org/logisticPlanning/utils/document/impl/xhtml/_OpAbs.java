package org.logisticPlanning.utils.document.impl.xhtml;

import java.io.IOException;

import org.logisticPlanning.utils.io.charIO.CharOutput;

/**
 * a absolute
 */
final class _OpAbs extends _OpDef {

  /** the instance */
  static final _OpDef INST = new _OpAbs();

  /** the before */
  private final static char[] ABS = { '<', 't', 'd', ' ', 'c', 'l', 'a',
      's', 's', '=', '"', 'm', 'a', 't', 'h', 'B', 'L', '"', '>' };

  /** create */
  private _OpAbs() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  final void render(final CharOutput out, final char[][] ops)
      throws IOException {
    char[] put;

    out.write(_OpDef.MO_TAB, 0, _OpDef.MO_TAB.length);
    out.write(_OpDef.MO_TR, 0, _OpDef.MO_TR.length);
    out.write(_OpAbs.ABS, 0, _OpAbs.ABS.length);

    put = ops[0];
    if (put != null) {
      out.write(put, 0, put.length);
    }

    out.write(XHTMLDocument.TD_END, 0, XHTMLDocument.TD_END.length);
    out.write(XHTMLDocument.TR_END, 0, XHTMLDocument.TR_END.length);
    out.write(XHTMLDocument.TABLE_END, 0, XHTMLDocument.TABLE_END.length);
  }
}
