package org.logisticPlanning.utils.document.spec;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.logisticPlanning.utils.io.EEncoding;
import org.logisticPlanning.utils.io.FileUtils;
import org.logisticPlanning.utils.io.Transcoder;

/**
 * A context is the very base class for any document producing system. It
 * may provide a (virtual?) folder for writing output.
 */
public abstract class Context extends Element {

  /** in preprocessing */
  private static final int STATE_IN_PREPROCESS = (Element.STATE_NOTHING + 1);

  /** after preprocessing */
  private static final int STATE_AFTER_PREPROCESS = (Context.STATE_IN_PREPROCESS + 1);

  /** in document */
  private static final int STATE_IN_DOCUMENT = (Context.STATE_AFTER_PREPROCESS + 1);

  /** after document */
  private static final int STATE_AFTER_DOCUMENT = (Context.STATE_IN_DOCUMENT + 1);

  /** in poseprocessing */
  private static final int STATE_IN_POSTPROCESS = (Context.STATE_AFTER_DOCUMENT + 1);

  /** after postprocessing */
  private static final int STATE_AFTER_POSTPROCESS = (Context.STATE_IN_POSTPROCESS + 1);

  /** the internal name counter */
  private volatile long m_nameCounter;

  /** the owning driver */
  private final DocumentDriver m_driver;

  /** the basic directory of the context */
  private final File m_baseDir;

  /** the logger */
  private final Logger m_logger;

  /**
   * create the context
   *
   * @param driver
   *          the driver of this context
   * @param baseDir
   *          the basic directory of the context
   * @throws IOException
   *           if io fails
   */
  protected Context(final DocumentDriver driver, final File baseDir)
      throws IOException {
    super(null);

    final Logger log;
    this.m_driver = driver;
    this.m_logger = log = driver.getLogger();

    this.m_baseDir = FileUtils.canonicalize(baseDir);
    if (this.m_baseDir == null) {
      throw new IllegalArgumentException(//
          "Illegal base directory for context."); //$NON-NLS-1$
    }

    if ((log != null) && (log.isLoggable(Level.INFO))) {
      log.info(this.toString() + //
          " for directory '" + this.m_baseDir + //$NON-NLS-1$
          "' generated.");//$NON-NLS-1$
    }
  }

  /** {@inheritDoc} */
  @Override
  public final Logger getLogger() {
    return this.m_logger;
  }

  /**
   * Obtain the base directory of this context. All output of this context
   * will be written to this directory.
   *
   * @return the base directory of this context
   */
  public final File getBaseDir() {
    return this.m_baseDir;
  }

  /**
   * Get the driver
   *
   * @return the driver
   */
  public DocumentDriver getDriver() {
    return this.m_driver;
  }

  /**
   * Perform any pre-processing necessary before creating a document
   *
   * @throws IOException
   *           if io fails
   */
  protected void doPreprocess() throws IOException {
    //
  }

  /**
   * Perform any pre-processing necessary before creating a document
   *
   * @throws IOException
   *           if io fails
   */
  public final void preprocess() throws IOException {
    final Logger log;

    log = this.m_logger;
    if ((log != null) && (log.isLoggable(Level.INFO))) {
      log.info("Preprocessing begins: " + this.toString());//$NON-NLS-1$
    }

    if (this.m_state != Element.STATE_NOTHING) {
      throw new IllegalStateException(//
          "Preprocessing only allowed at startup."); //$NON-NLS-1$
    }
    this.m_state = Context.STATE_IN_PREPROCESS;

    this.doPreprocess();

    if (this.m_state != Context.STATE_IN_PREPROCESS) {
      throw new IllegalStateException(//
          "Error during preprocessing: inconsistent state."); //$NON-NLS-1$
    }
    this.m_state = Context.STATE_AFTER_PREPROCESS;

    if ((log != null) && (log.isLoggable(Level.INFO))) {
      log.info("Preprocessing finished: " + this.toString());//$NON-NLS-1$
    }
  }

  /** {@inheritDoc} */
  @Override
  protected abstract Document documentCreate(final Context owner)
      throws IOException;

  /** {@inheritDoc} */
  @Override
  protected void documentBegin(final Document d) throws IOException,
  IllegalStateException {
    final Logger log;

    log = this.m_logger;

    if ((log != null) && (log.isLoggable(Level.INFO))) {
      log.info("Document generation begins: " + this.toString());//$NON-NLS-1$
    }

    if (this.m_state != Context.STATE_AFTER_PREPROCESS) {
      throw new IllegalStateException(//
          "Only one document can be created per context and only after preprocessing has finished."); //$NON-NLS-1$
    }
    this.m_state = Context.STATE_IN_DOCUMENT;
    if (this.m_document != null) {
      throw new IllegalStateException(//
          "Context document already set."); //$NON-NLS-1$
    }
    this.m_document = d;
  }

  /** {@inheritDoc} */
  @Override
  protected void documentEnd(final Document h) throws IOException,
  IllegalStateException {
    final Logger log;

    if (this.m_state != Context.STATE_IN_DOCUMENT) {
      throw new IllegalStateException(//
          "Document must be closed after it has been created."); //$NON-NLS-1$
    }
    this.m_state = Context.STATE_AFTER_DOCUMENT;

    log = this.m_logger;

    if ((log != null) && (log.isLoggable(Level.INFO))) {
      log.info("Document generation finished: " + this.toString());//$NON-NLS-1$
    }
  }

  /** {@inheritDoc} */
  @Override
  public final Document document() throws IOException,
  IllegalStateException {
    final Document h;
    h = this.documentCreate(this);
    this.documentBegin(h);
    return h;
  }

  /**
   * Perform any post-processing necessary after creating a document, e.g.,
   * compiling the document
   *
   * @throws IOException
   *           if io fails
   */
  protected void doPostprocess() throws IOException {
    //
  }

  /**
   * Perform any post-processing necessary after creating a document, e.g.,
   * compiling the document
   *
   * @throws IOException
   *           if io fails
   */
  public final void postprocess() throws IOException {
    final Logger log;

    log = this.m_logger;

    if ((log != null) && (log.isLoggable(Level.INFO))) {
      log.info("Postprocessing begins: " + this.toString());//$NON-NLS-1$
    }

    if (this.m_state != Context.STATE_AFTER_DOCUMENT) {
      throw new IllegalStateException(//
          "Postprocessing only allowed after document has been closed."); //$NON-NLS-1$
    }
    this.m_state = Context.STATE_IN_POSTPROCESS;

    this.doPostprocess();

    if (this.m_state != Context.STATE_IN_POSTPROCESS) {
      throw new IllegalStateException(//
          "Error during postprocessing: inconsistent state."); //$NON-NLS-1$
    }
    this.m_state = Context.STATE_AFTER_POSTPROCESS;

    if ((log != null) && (log.isLoggable(Level.INFO))) {
      log.info("Postprocessing finished: " + this.toString());//$NON-NLS-1$
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void doClose() throws IOException {
    //
  }

  /** {@inheritDoc} */
  @Override
  protected final String autoName() {
    final char[] data;
    int idx;
    long name;

    data = new char[19];
    data[0] = 'A';
    data[1] = 'U';
    data[2] = 'T';
    data[3] = 'O';
    idx = 4;

    name = (this.m_nameCounter++);
    if (name < 0) {
      data[0] = 'Z';
      name = (-name);
    }
    do {
      data[idx++] = ((char) ('A' + (name % 25)));
      name /= 25;
    } while (name > 0l);

    return String.valueOf(data, 0, idx);
  }

  /** {@inheritDoc} */
  @Override
  protected File resolveRelativeName(final URI relativeName)
      throws IOException, URISyntaxException {
    return FileUtils.uriToFile(this.m_baseDir, relativeName);
  }

  /** {@inheritDoc} */
  @Override
  protected URI relativizeFile(final File file) throws IOException,
  URISyntaxException {
    String s;

    s = FileUtils.getRelativeName(this.m_baseDir, file);
    if (File.separatorChar != '/') {
      s = s.replace(File.separatorChar, '/');
    }
    return new URI(s);
  }

  /** {@inheritDoc} */
  @Override
  public final void close() throws IOException {
    final Logger log;

    if (this.checkDead(Context.STATE_AFTER_POSTPROCESS)) {
      this.doClose();
    }

    log = this.m_logger;

    if ((log != null) && (log.isLoggable(Level.INFO))) {
      log.info(this.toString() + //
          " for directory '" + this.m_baseDir + //$NON-NLS-1$
          "' closed.");//$NON-NLS-1$
    }
  }

  /**
   * Load a resource file from a given class into the directory of this
   * context
   *
   * @param clazz
   *          the class to load the resource from
   * @param name
   *          the resource file name
   * @param encoding
   *          the encoding to use
   * @throws IOException
   *           if io fails
   */
  protected void loadResource(final Class<?> clazz, final String name,
      final EEncoding encoding) throws IOException {
    final EEncoding s, d;

    d = ((encoding == null) ? EEncoding.BINARY : //
      ((encoding == EEncoding.TEXT) ? //
          this.m_driver.getPreferedTextEncoding()
          : encoding));
    s = ((d == EEncoding.BINARY) ? EEncoding.BINARY : EEncoding.TEXT);
    Transcoder.copyResourceToFile(clazz, name, s,//
        FileUtils.canonicalize(new File(this.m_baseDir, name)), d);

  }

  /**
   * Load a resource file into the directory of this context
   *
   * @param name
   *          the resource file name
   * @param encoding
   *          the encoding to use
   * @throws IOException
   *           if io fails
   */
  protected void loadResource(final String name, final EEncoding encoding)
      throws IOException {
    this.loadResource(this.getClass(), name, encoding);
  }

  /** {@inheritDoc} */
  @Override
  protected Graphic graphicCreate(final FigureBody owner)
      throws IOException {
    return this.m_driver.graphicCreate(owner);
  }
}
