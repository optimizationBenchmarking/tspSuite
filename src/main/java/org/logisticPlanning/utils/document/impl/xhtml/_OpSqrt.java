package org.logisticPlanning.utils.document.impl.xhtml;

import java.io.IOException;

import org.logisticPlanning.utils.io.charIO.CharOutput;
import org.logisticPlanning.utils.text.transformations.XMLCharTransformer;

/**
 * a square root
 */
final class _OpSqrt extends _OpDef {

  /** the instance */
  static final _OpDef INST = new _OpSqrt();

  /** the before */
  private final static char[] ROOT = XMLCharTransformer.INSTANCE
      .transform("\u221a").toCharArray(); //$NON-NLS-1$

  /** the overlined cell */
  private final static char[] OL = { '<', 't', 'd', ' ', 'c', 'l', 'a',
      's', 's', '=', '"', 'm', 'a', 't', 'h', 'T', 'L', '"', '>' };

  /** create */
  private _OpSqrt() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  final void render(final CharOutput out, final char[][] ops)
      throws IOException {
    char[] put;

    out.write(_OpDef.MO_TAB, 0, _OpDef.MO_TAB.length);
    out.write(_OpDef.MO_TR, 0, _OpDef.MO_TR.length);
    out.write(_OpDef.MO_TD, 0, _OpDef.MO_TD.length);
    out.write(_OpSqrt.ROOT, 0, _OpSqrt.ROOT.length);
    out.write(XHTMLDocument.TD_END, 0, XHTMLDocument.TD_END.length);

    out.write(_OpSqrt.OL, 0, _OpSqrt.OL.length);

    put = ops[0];
    if (put != null) {
      out.write(put, 0, put.length);
    }

    out.write(XHTMLDocument.TD_END, 0, XHTMLDocument.TD_END.length);
    out.write(XHTMLDocument.TR_END, 0, XHTMLDocument.TR_END.length);
    out.write(XHTMLDocument.TABLE_END, 0, XHTMLDocument.TABLE_END.length);
  }
}
