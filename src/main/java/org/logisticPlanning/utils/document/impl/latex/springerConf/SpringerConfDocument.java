package org.logisticPlanning.utils.document.impl.latex.springerConf;

import java.io.File;
import java.io.IOException;

import org.logisticPlanning.utils.document.impl.latex.LaTeXDocument;
import org.logisticPlanning.utils.document.spec.DocumentDimensions;

/**
 * A Springer Lecture Notes on Computer Science-based conference LaTeX
 * document&nbsp;[<a href="#cite_SPRINGERLNCS"
 * style="font-weight:bold">1</a>]. <h2>References</h2>
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
public class SpringerConfDocument extends LaTeXDocument {

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
  protected SpringerConfDocument(final SpringerConfContext context,
      final File file) throws IOException {
    super(context, file);
  }

  /** {@inheritDoc} */
  @Override
  public SpringerConfContext getOwner() {
    return ((SpringerConfContext) (super.getOwner()));
  }

  /** {@inheritDoc} */
  @Override
  protected void putDocumentClass() throws IOException {
    this.writeDocumentClass("llncs"); //$NON-NLS-1$
  }

  // /** {@inheritDoc} */
  // @Override
  // protected boolean hasStarredEnvironments() {
  // return false;
  // }

  /** {@inheritDoc} */
  @Override
  protected String getBibliographyStyle() {
    return "abbrvnat"; //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  public DocumentDimensions getDimensions() {
    return SpringerConfDimensions.DEFAULT;
  }
}
