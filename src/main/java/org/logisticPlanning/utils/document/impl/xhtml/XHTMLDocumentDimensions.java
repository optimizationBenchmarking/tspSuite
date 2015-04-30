package org.logisticPlanning.utils.document.impl.xhtml;

import org.logisticPlanning.utils.document.spec.DocumentDimensions;

/**
 * This class allows us to access (approximate) size information about the
 * document for a computer screen: We assume infinite length (as a browser
 * supports scrolling) and a width of about 32cm. It is used in the <a
 * href="http://en.wikipedia.org/wiki/Xhtml">XHTML</a>&nbsp;[<a
 * href="#cite_W3C2010XHTML" style="font-weight:bold">1</a>] driver.<h2>
 * References</h2>
 * <ol>
 * <li><div><span id="cite_W3C2010XHTML" /><a
 * href="http://www.altheim.com/murray/">Murray Altheim</a> and&nbsp;Shane
 * McCarron: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;XHTML&#8482; 1.1
 * &#8212; Module-based XHTML &#8212; Second Edition,&rdquo;</span>
 * November&nbsp;23, 2010, W3C Recommendation, MIT/CSAIL (USA), ERCIM
 * (France), Keio University (Japan): World Wide Web Consortium (W3C).
 * <div>link: [<a
 * href="http://www.w3.org/TR/2010/REC-xhtml11-20101123">1</a>]</div></div>
 * </li>
 * </ol>
 */
public class XHTMLDocumentDimensions extends DocumentDimensions {

  /** the default instance */
  static final XHTMLDocumentDimensions DEFAULT = new XHTMLDocumentDimensions();

  /** the paper height in mm: infinite */
  private static final double PAGE_HEIGHT_MM = Double.POSITIVE_INFINITY;
  /** the column width in mm: let's say 32cm */
  private static final double COLUMN_WIDTH_MM = 320d;
  /** the paper width in mm */
  private static final double PAGE_WIDTH_MM = XHTMLDocumentDimensions.COLUMN_WIDTH_MM;

  /** instantiate */
  protected XHTMLDocumentDimensions() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public double getPageHeightMM() {
    return XHTMLDocumentDimensions.PAGE_HEIGHT_MM;
  }

  /** {@inheritDoc} */
  @Override
  public double getColumnWidthMM() {
    return XHTMLDocumentDimensions.COLUMN_WIDTH_MM;
  }

  /** {@inheritDoc} */
  @Override
  public double getPageWidthMM() {
    return XHTMLDocumentDimensions.PAGE_WIDTH_MM;
  }

}
