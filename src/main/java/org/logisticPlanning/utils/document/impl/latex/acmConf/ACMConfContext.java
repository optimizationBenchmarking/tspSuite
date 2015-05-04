package org.logisticPlanning.utils.document.impl.latex.acmConf;

import java.io.File;
import java.io.IOException;

import org.logisticPlanning.utils.document.impl.latex.LaTeXContext;
import org.logisticPlanning.utils.io.EEncoding;

/**
 * A ACM conference&nbsp;[<a href="#cite_ACMSIGALTERNATE"
 * style="font-weight:bold">1</a>] LaTeX context.<h2>References</h2>
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
public class ACMConfContext extends LaTeXContext {

  /**
   * create the ACM Conference context
   *
   * @param driver
   *          the driver of this context
   * @param baseDir
   *          the basic directory of the context
   * @throws IOException
   *           if io fails
   */
  protected ACMConfContext(final ACMConfDriver driver, final File baseDir)
      throws IOException {
    super(driver, baseDir);
  }

  /** {@inheritDoc} */
  @Override
  protected void doPreprocess() throws IOException {
    super.doPreprocess();

    this.loadResource("sig-alternate.cls", EEncoding.TEXT);//$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  protected ACMConfDocument documentInstantiate(final LaTeXContext owner,
      final File f) throws IOException {
    return new ACMConfDocument(((ACMConfContext) owner), f);
  }
}
