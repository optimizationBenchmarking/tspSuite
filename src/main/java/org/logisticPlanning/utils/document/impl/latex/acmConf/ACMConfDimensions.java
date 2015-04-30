package org.logisticPlanning.utils.document.impl.latex.acmConf;

import org.logisticPlanning.utils.document.impl.latex.LaTeXDocumentDimensions;
import org.logisticPlanning.utils.math.units.ELength;

/**
 * This class allows us to access (approximate) size information about the
 * document for ACM conference&nbsp;[<a href="#cite_ACMSIGALTERNATE"
 * style="font-weight:bold">1</a>] papers.<h2>References</h2>
 * <ol>
 * <li><div><span id="cite_ACMSIGALTERNATE" /><a
 * href="http://www.linkedin.com/pub/gerry-murray/31/23/27a">Gerald
 * Murray</a> and&nbsp;G.K.M. Tobin: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;SIG-ALTERNATE.CLS
 * &#8210; Version 2.4 (Compatible with the ACM_PROC_ARTICLE-SP.CLS&quot;
 * V3.2SP),&rdquo;</span> (Website), April&nbsp;22, 2009, New York, NY,
 * USA: Association for Computing Machinery (ACM). <div>link: [<a
 * href="http://www.acm.org/sigs/publications/proceedings-templates"
 * >1</a>]</div></div></li>
 * </ol>
 */
public class ACMConfDimensions extends LaTeXDocumentDimensions {

  /** the default instance */
  static final ACMConfDimensions DEFAULT = new ACMConfDimensions();

  /** the paper height in mm */
  private static final double PAGE_HEIGHT_MM = ELength.convert(666d,
      ELength.PT, ELength.MM);
  /** the column width in mm */
  private static final double COLUMN_WIDTH_MM = ELength.convert(240d,
      ELength.PT, ELength.MM);
  /** the paper width in mm */
  private static final double PAGE_WIDTH_MM = ELength.convert(504d,
      ELength.PT, ELength.MM);

  /** instantiate */
  protected ACMConfDimensions() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public double getPageHeightMM() {
    return ACMConfDimensions.PAGE_HEIGHT_MM;
  }

  /** {@inheritDoc} */
  @Override
  public double getColumnWidthMM() {
    return ACMConfDimensions.COLUMN_WIDTH_MM;
  }

  /** {@inheritDoc} */
  @Override
  public double getPageWidthMM() {
    return ACMConfDimensions.PAGE_WIDTH_MM;
  }

  /** {@inheritDoc} */
  @Override
  public int getColumnCount() {
    return 2;
  }
}
