package org.logisticPlanning.utils.document.impl.latex.ieeeArticle;

import java.io.File;
import java.io.IOException;

import org.logisticPlanning.utils.document.impl.latex.LaTeXContext;
import org.logisticPlanning.utils.io.EEncoding;

/**
 * An IEEE article&nbsp;[<a href="#cite_IEEETRAN"
 * style="font-weight:bold">1</a>] LaTeX context.<h2>References</h2>
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
public class IEEEArticleContext extends LaTeXContext {

  /**
   * create the IEEE article context
   * 
   * @param driver
   *          the driver of this context
   * @param baseDir
   *          the basic directory of the context
   * @throws IOException
   *           if io fails
   */
  protected IEEEArticleContext(final IEEEArticleDriver driver,
      final File baseDir) throws IOException {
    super(driver, baseDir);
  }

  /** {@inheritDoc} */
  @Override
  protected void doPreprocess() throws IOException {
    super.doPreprocess();

    this.loadResource("IEEEtran.cls", EEncoding.TEXT);//$NON-NLS-1$
    this.loadResource("IEEEtranN.bst", EEncoding.TEXT); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  protected IEEEArticleDocument documentInstantiate(
      final LaTeXContext owner, final File f) throws IOException {
    return new IEEEArticleDocument(((IEEEArticleContext) owner), f);
  }
}
