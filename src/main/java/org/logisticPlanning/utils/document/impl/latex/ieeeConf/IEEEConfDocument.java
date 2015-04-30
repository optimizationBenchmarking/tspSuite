package org.logisticPlanning.utils.document.impl.latex.ieeeConf;

import java.io.File;
import java.io.IOException;

import org.logisticPlanning.utils.document.impl.latex.LaTeXDocument;
import org.logisticPlanning.utils.document.spec.DocumentDimensions;

/**
 * An IEEE Conference&nbsp;[<a href="#cite_IEEETRAN"
 * style="font-weight:bold">1</a>] LaTeX document.<h2>References</h2>
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
public class IEEEConfDocument extends LaTeXDocument {

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
  protected IEEEConfDocument(final IEEEConfContext context, final File file)
      throws IOException {
    super(context, file);
  }

  /** {@inheritDoc} */
  @Override
  public IEEEConfContext getOwner() {
    return ((IEEEConfContext) (super.getOwner()));
  }

  /** {@inheritDoc} */
  @Override
  protected void putDocumentClass() throws IOException {
    this.writeDocumentClass("IEEEtran", //$NON-NLS-1$
        "10pt", //$NON-NLS-1$
        "conference", //$NON-NLS-1$
        "compsocconf"); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  protected String getBibliographyStyle() {
    return "IEEEtranN"; //$NON-NLS-1$
  }

  // /** {@inheritDoc} */
  // @Override
  // protected boolean hasStarredEnvironments() {
  // return true;
  // }

  /** {@inheritDoc} */
  @Override
  public DocumentDimensions getDimensions() {
    return IEEEConfDimensions.DEFAULT;
  }
}
