package org.logisticPlanning.utils.document.impl.latex.ieeeConf;

import org.logisticPlanning.utils.document.impl.latex.LaTeXDocumentDimensions;
import org.logisticPlanning.utils.math.units.ELength;

/**
 * This class allows us to access (approximate) size information about the
 * IEEE conference&nbsp;[<a href="#cite_IEEETRAN"
 * style="font-weight:bold">1</a>] document.<h2>References</h2>
 * <ol>
 * <li><div><span id="cite_IEEETRAN" /><a
 * href="http://www.linkedin.com/pub/gerry-murray/31/23/27a">Gerald
 * Murray</a>, Silvano Balemi, Jon Dixon, Peter N&#252;chter, J&#252;rgen
 * von Hagen, and&nbsp;<a href="http://www.michaelshell.org/">Michael
 * Shell</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Official IEEE
 * LaTeX Class for Authors of the Institute of Electrical and Electronics
 * Engineers (IEEE) Transactions Journals and Conferences,&rdquo;</span>
 * (Website), May&nbsp;3, 2007, Piscataway, NJ, USA: IEEE (Institute of
 * Electrical and Electronics Engineers). <div>links: [<a
 * href="http://www.michaelshell.org/tex/ieeetran/">1</a>] and&nbsp;[<a
 * href="http://www.ctan.org/tex-archive/macros/latex/contrib/IEEEtran/"
 * >2</a>]</div></div></li>
 * </ol>
 */
public class IEEEConfDimensions extends LaTeXDocumentDimensions {

  /** the default instance */
  static final IEEEConfDimensions DEFAULT = new IEEEConfDimensions();

  /** the paper height in mm */
  private static final double PAGE_HEIGHT_MM = ELength.convert(672d,
      ELength.PT, ELength.MM);
  /** the column width in mm */
  private static final double COLUMN_WIDTH_MM = ELength.convert(252d,
      ELength.PT, ELength.MM);
  /** the paper width in mm */
  private static final double PAGE_WIDTH_MM = ELength.convert(516d,
      ELength.PT, ELength.MM);

  /** instantiate */
  protected IEEEConfDimensions() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public double getPageHeightMM() {
    return IEEEConfDimensions.PAGE_HEIGHT_MM;
  }

  /** {@inheritDoc} */
  @Override
  public double getColumnWidthMM() {
    return IEEEConfDimensions.COLUMN_WIDTH_MM;
  }

  /** {@inheritDoc} */
  @Override
  public double getPageWidthMM() {
    return IEEEConfDimensions.PAGE_WIDTH_MM;
  }

  /** {@inheritDoc} */
  @Override
  public int getColumnCount() {
    return 2;
  }
}
