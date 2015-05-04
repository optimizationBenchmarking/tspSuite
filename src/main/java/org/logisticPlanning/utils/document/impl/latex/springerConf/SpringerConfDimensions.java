package org.logisticPlanning.utils.document.impl.latex.springerConf;

import org.logisticPlanning.utils.document.impl.latex.LaTeXDocumentDimensions;
import org.logisticPlanning.utils.math.units.ELength;

/**
 * This class allows us to access (approximate) size information about the
 * Springer Lecture Notes in Computer Science conference document&nbsp;[<a
 * href="#cite_SPRINGERLNCS" style="font-weight:bold">1</a>].<h2>References
 * </h2>
 * <ol>
 * <li><div><span id="cite_SPRINGERLNCS" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;LLNCS Document
 * Class &#8210; Springer Verlag LaTeX2e support for Lecture Notes in
 * Computer Science,&rdquo;</span> (Website), June&nbsp;12, 2010, Berlin,
 * Germany: Springer-Verlag GmbH. <div>link: [<a
 * href="ftp://ftp.springer.de/pub/tex/latex/llncs/latex2e/llncs2e.zip"
 * >1</a>]</div></div></li>
 * </ol>
 */
public class SpringerConfDimensions extends LaTeXDocumentDimensions {

  /** the default instance */
  static final SpringerConfDimensions DEFAULT = new SpringerConfDimensions();

  /** the paper height in mm */
  private static final double PAGE_HEIGHT_MM = // approx 193 mm;
      ELength.convert(549.13828d, ELength.PT, ELength.MM);
  /** the column width in mm */
  private static final double COLUMN_WIDTH_MM = // approx 122 mm;
      ELength.convert(347.12354d, ELength.PT, ELength.MM);
  /** the paper width in mm */
  private static final double PAGE_WIDTH_MM = SpringerConfDimensions.COLUMN_WIDTH_MM;

  /** instantiate */
  protected SpringerConfDimensions() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public double getPageHeightMM() {
    return SpringerConfDimensions.PAGE_HEIGHT_MM;
  }

  /** {@inheritDoc} */
  @Override
  public double getColumnWidthMM() {
    return SpringerConfDimensions.COLUMN_WIDTH_MM;
  }

  /** {@inheritDoc} */
  @Override
  public double getPageWidthMM() {
    return SpringerConfDimensions.PAGE_WIDTH_MM;
  }

  /** {@inheritDoc} */
  @Override
  public int getColumnCount() {
    return 1;
  }
}
