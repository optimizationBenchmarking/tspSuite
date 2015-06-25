package org.logisticPlanning.utils.document.impl.latex.springerConf;

import java.io.File;
import java.io.IOException;

import org.logisticPlanning.utils.document.impl.latex.LaTeXContext;
import org.logisticPlanning.utils.io.EEncoding;

/**
 * A Springer Lecture Notes on Computer Science-based conference LaTeX
 * context&nbsp;[<a href="#cite_SPRINGERLNCS"
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
public class SpringerConfContext extends LaTeXContext {

  /**
   * create the Springer Lecture Notes on Computer Science-based Conference
   * context
   *
   * @param driver
   *          the driver of this context
   * @param baseDir
   *          the basic directory of the context
   * @throws IOException
   *           if io fails
   */
  protected SpringerConfContext(final SpringerConfDriver driver,
      final File baseDir) throws IOException {
    super(driver, baseDir);
  }

  /** {@inheritDoc} */
  @Override
  protected void doPreprocess() throws IOException {
    super.doPreprocess();

    this.loadResource("llncs.cls", EEncoding.TEXT);//$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  protected SpringerConfDocument documentInstantiate(
      final LaTeXContext owner, final File f) throws IOException {
    return new SpringerConfDocument(((SpringerConfContext) owner), f);
  }
}
