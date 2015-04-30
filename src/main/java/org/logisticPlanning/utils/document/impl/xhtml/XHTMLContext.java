package org.logisticPlanning.utils.document.impl.xhtml;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.logisticPlanning.utils.document.spec.Context;
import org.logisticPlanning.utils.io.EEncoding;
import org.logisticPlanning.utils.io.FileUtils;

/**
 * The <a href="http://en.wikipedia.org/wiki/Xhtml">XHTML</a>&nbsp;[<a
 * href="#cite_W3C2010XHTML" style="font-weight:bold">1</a>] document
 * context. <h2>References</h2>
 * <ol>
 * <li><div><span id="cite_W3C2010XHTML" /><a
 * href="http://www.altheim.com/murray/">Murray Altheim</a> and&nbsp;Shane
 * McCarron: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;XHTML&#8482; 1.1
 * &#8212; Module-based XHTML &#8212; Second Edition,&rdquo;</span>
 * November&nbsp;23, 2010, W3C Recommendation, MIT/CSAIL (USA), ERCIM
 * (France), Keio University (Japan): World Wide Web Consortium (W3C).
 * <div>link: [<a
 * href="http://www.w3.org/TR/2010/REC-xhtml11-20101123">1</a>]</div></div>
 * </li>
 * </ol>
 */
public class XHTMLContext extends Context {

  /** the style sheet to be used in all cases, except for printing */
  public static final String CSS_DEFAULT = "default.css";//$NON-NLS-1$

  /** the style sheet to be used for printing */
  public static final String CSS_PRINT = "print.css";//$NON-NLS-1$

  /** the HTML document file */
  private File m_documentFile;

  /**
   * create the LaTeX context
   * 
   * @param driver
   *          the driver of this context
   * @param baseDir
   *          the basic directory of the context
   * @throws IOException
   *           if io fails
   */
  protected XHTMLContext(final XHTMLDriver driver, final File baseDir)
      throws IOException {
    super(driver, baseDir);
    this.getBaseDir().mkdirs();
  }

  /** {@inheritDoc} */
  @Override
  public XHTMLDriver getDriver() {
    return ((XHTMLDriver) (super.getDriver()));
  }

  /**
   * Get the document file
   * 
   * @return the document file
   */
  public final File getDocumentFile() {
    return this.m_documentFile;
  }

  /**
   * Instantiate the document
   * 
   * @param owner
   *          the owner
   * @param f
   *          the file
   * @return the document
   * @throws IOException
   *           if io fails
   */
  protected XHTMLDocument documentInstantiate(final XHTMLContext owner,
      final File f) throws IOException {
    return new XHTMLDocument(owner, f);
  }

  /** {@inheritDoc} */
  @Override
  protected final XHTMLDocument documentCreate(final Context owner)
      throws IOException {
    final Logger log;

    if (this.m_documentFile != null) {
      throw new IllegalStateException(//
          "Document file already set."); //$NON-NLS-1$
    }

    this.m_documentFile = FileUtils.canonicalize(new File(this
        .getBaseDir(), //
        "index.html")); //$NON-NLS-1$
    if (!(this.m_documentFile.createNewFile())) {
      throw new IOException("Destination file '" + this.m_documentFile + //$NON-NLS-1$
          "' already exists.");//$NON-NLS-1$
    }

    log = this.getLogger();
    if ((log != null) && (log.isLoggable(Level.INFO))) {
      log.info("Document file is '" + this.m_documentFile + //$NON-NLS-1$
          "'."); //$NON-NLS-1$ 
    }

    return this.documentInstantiate(((XHTMLContext) owner),
        this.m_documentFile);
  }

  /**
   * Load the style sheets. This procedure must load two text resources
   * that named according to {@link #CSS_DEFAULT} and {@link #CSS_PRINT}.
   * 
   * @throws IOException
   *           if io fails
   */
  protected void loadCSSStyleSheets() throws IOException {
    this.loadResource(XHTMLContext.class, XHTMLContext.CSS_DEFAULT,
        EEncoding.TEXT);
    this.loadResource(XHTMLContext.class, XHTMLContext.CSS_PRINT,
        EEncoding.TEXT);
  }

  /** {@inheritDoc} */
  @Override
  protected void doPostprocess() throws IOException {
    try {
      this.loadCSSStyleSheets();
    } finally {
      super.doPostprocess();
    }
  }

  /**
   * access the name resolution method
   * 
   * @param relativeName
   *          the relative name
   * @return the file
   * @throws IOException
   *           if i/o fails
   * @throws URISyntaxException
   *           if the uri syntax is wrong
   */
  final File _resolveRelativeName(final URI relativeName)
      throws IOException, URISyntaxException {
    return this.resolveRelativeName(relativeName);
  }
}
