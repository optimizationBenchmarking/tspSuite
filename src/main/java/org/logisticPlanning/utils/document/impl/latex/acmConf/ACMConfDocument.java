package org.logisticPlanning.utils.document.impl.latex.acmConf;

import java.io.File;
import java.io.IOException;

import org.logisticPlanning.utils.document.impl.latex.LaTeXDocument;
import org.logisticPlanning.utils.document.spec.DocumentDimensions;

/**
 * An ACM conference&nbsp;[<a href="#cite_ACMSIGALTERNATE"
 * style="font-weight:bold">1</a>] LaTeX document.<h2>References</h2>
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
public class ACMConfDocument extends LaTeXDocument {

  /**
   * instantiate
   *
   * @param context
   *          the owning context
   * @param file
   *          the output file
   * @throws IOException
   *           if io fails
   */
  protected ACMConfDocument(final ACMConfContext context, final File file)
      throws IOException {
    super(context, file);
  }

  /** {@inheritDoc} */
  @Override
  public ACMConfContext getOwner() {
    return ((ACMConfContext) (super.getOwner()));
  }

  /** {@inheritDoc} */
  @Override
  protected void putDocumentClass() throws IOException {
    this.writeDocumentClass("sig-alternate"); //$NON-NLS-1$
  }

  // /** {@inheritDoc} */
  // @Override
  // protected boolean hasStarredEnvironments() {
  // return true;
  // }

  /** {@inheritDoc} */
  @Override
  public DocumentDimensions getDimensions() {
    return ACMConfDimensions.DEFAULT;
  }
}
