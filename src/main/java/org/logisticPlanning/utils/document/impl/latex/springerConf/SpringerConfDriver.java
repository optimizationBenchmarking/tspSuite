package org.logisticPlanning.utils.document.impl.latex.springerConf;

import java.io.File;
import java.io.IOException;

import org.logisticPlanning.utils.document.impl.latex.LaTeXDriver;

/**
 * A LaTeX driver for Springer Lecture Notes on Computer Science-based
 * conferences&nbsp;[<a href="#cite_SPRINGERLNCS"
 * style="font-weight:bold">1</a>].<h2>References</h2>
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
public class SpringerConfDriver extends LaTeXDriver {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** instantiate the driver */
  public SpringerConfDriver() {
    super("Springer Conference LaTeX"); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  public SpringerConfContext createContext(final File dir)
      throws IOException {
    return new SpringerConfContext(this, dir);
  }
}
