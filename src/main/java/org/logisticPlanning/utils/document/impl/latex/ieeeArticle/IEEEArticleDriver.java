package org.logisticPlanning.utils.document.impl.latex.ieeeArticle;

import java.io.File;
import java.io.IOException;

import org.logisticPlanning.utils.document.impl.latex.LaTeXDriver;

/**
 * A LaTeX driver for IEEE articles&nbsp;[<a href="#cite_IEEETRAN"
 * style="font-weight:bold">1</a>].<h2>References</h2>
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
public class IEEEArticleDriver extends LaTeXDriver {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** instantiate the driver */
  public IEEEArticleDriver() {
    super("IEEE Transactions Article LaTeX"); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  public IEEEArticleContext createContext(final File dir)
      throws IOException {
    return new IEEEArticleContext(this, dir);
  }
}
