package org.logisticPlanning.utils.document.impl.latex;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.logisticPlanning.utils.io.FileUtils;
import org.logisticPlanning.utils.processes.AutoProcess;
import org.logisticPlanning.utils.text.TextUtils;

/**
 * <p>
 * The internal compiler for translating LaTeX to PDF.
 * </p>
 * <p>
 * <a href="http://en.wikipedia.org/wiki/LaTeX">LaTeX</a>&nbsp;[<a
 * href="#cite_L1994LADPSUGARM" style="font-weight:bold">1</a>, <a
 * href="#cite_GMS1994TLC" style="font-weight:bold">2</a>, <a
 * href="#cite_MGBCR2004TLC" style="font-weight:bold">3</a>, <a
 * href="#cite_OPHS2011TNSSITLOLI1M" style="font-weight:bold">4</a>, <a
 * href="#cite_JF2011LEEUEBM" style="font-weight:bold">5</a>, <a
 * href="#cite_J1995LFAONVDH" style="font-weight:bold">6</a>] is a text
 * document definition and type setting system predominately used for
 * scientific publications. It allows for defining structured documents
 * that consist of sections, equations, and floating figures and tables. It
 * has a specific way to define
 * {@link org.logisticPlanning.utils.text.transformations obscure
 * characters} like {@code &auml;} or {@code &szlig;}.
 * </p>
 * <p>
 * The implementation here can not only generate documents in the <a
 * href="http://en.wikipedia.org/wiki/LaTeX">LaTeX</a> format, but also
 * automatically detect and apply a tool chain (such as <a
 * href="http://miktex.org/">MiKTeX</a>&nbsp;[<a href="#cite_MIKTEX"
 * style="font-weight:bold">7</a>], <a href=
 * "http://scripts.sil.org/cms/scripts/page.php?site_id=nrsi&id=xetex"
 * >XeTeX</a>&nbsp;[<a href="#cite_XETEX" style="font-weight:bold">8</a>],
 * or <a href="http://www.tug.org/texlive/">TeX Live</a>&nbsp;[<a
 * href="#cite_TEXLIVE" style="font-weight:bold">9</a>]) to compile such
 * documents to the Portable Document Format (<a
 * href="http://en.wikipedia.org/wiki/Pdf">PDF</a>, &nbsp;[<a
 * href="#cite_ADOBEISO32000" style="font-weight:bold">10</a>, <a
 * href="#cite_ISO320002008" style="font-weight:bold">11</a>]). This
 * process is slow and has only been tested with MiKTeX under
 * Windows&nbsp;7, so far. Thus, make sure to watch the logging information
 * when trying it.
 * </p>
 * <p>
 * Images and graphics here are generated in the Encapsulated PostScript
 * (<a href="http://en.wikipedia.org/wiki/Encapsulated_PostScript">EPS</a>)
 * format&nbsp;[<a href="#cite_A1992EPFFS" style="font-weight:bold">12</a>,
 * <a href="#cite_W2006AFGTP" style="font-weight:bold">13</a>] by using the
 * open source project <a
 * href="http://java.freehep.org/">FreeHEP</a>&nbsp;[<a
 * href="#cite_DJOSTBHLPAFBHKS2010FJL" style="font-weight:bold">14</a>],
 * which is licensed under the <a
 * href="http://www.gnu.org/copyleft/lesser.html">LGPL</a>.
 * </p>
 * <p>
 * Bibliographic information (citations to literature) are stored in the <a
 * href="http://www.bibtex.org/">BibTeX</a> <a
 * href="http://en.wikipedia.org/wiki/BibTeX">format</a>&nbsp;[<a
 * href="#cite_O1988B" style="font-weight:bold">15</a>].
 * </p>
 * <p>
 * The basic LaTeX output here can be extended / specialized for different
 * document formats, such as:
 * </p>
 * <ol>
 * <li>the {@link org.logisticPlanning.utils.document.impl.latex.acmConf
 * ACM Conference} template</li>
 * <li>the
 * {@link org.logisticPlanning.utils.document.impl.latex.ieeeArticle IEEE
 * article} template</li>
 * <li>the {@link org.logisticPlanning.utils.document.impl.latex.ieeeConf
 * IEEE Conference} template</li>
 * <li>the
 * {@link org.logisticPlanning.utils.document.impl.latex.springerConf
 * Springer Lecture Notes on Computer Science}-based conference template</li>
 * </ol>
 * <h2>References</h2>
 * <ol>
 * <li><div><span id="cite_L1994LADPSUGARM" /><a
 * href="https://en.wikipedia.org/wiki/Leslie_Lamport">Leslie Lamport</a>:
 * <span style="font-style:italic;font-family:cursive;">&ldquo;LaTeX: A
 * Document Preparation System. User's Guide and Reference
 * Manual,&rdquo;</span> 1994, Reading, MA, USA: Addison-Wesley Publishing
 * Co. Inc.. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0201529831">0201529831</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780201529838">9780201529838</a>;
 * Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=19pzDwEACAAJ"
 * >19pzDwEACAAJ</a></div></li>
 * <li><div><span id="cite_GMS1994TLC" /><a
 * href="http://goossens.web.cern.ch/goossens/">Michel Goossens</a>, <a
 * href="http://de.linkedin.com/in/frankmittelbach">Frank Mittelbach</a>,
 * and&nbsp;Alexander Samarin: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;The LaTeX
 * Companion,&rdquo;</span> 1994, Tools and Techniques for Computer
 * Typesetting, Reading, MA, USA: Addison-Wesley Publishing Co. Inc..
 * ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0201541998">0201541998</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780201541991">9780201541991</a>;
 * Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=54A3MuBzIrEC"
 * >54A3MuBzIrEC</a></div></li>
 * <li><div><span id="cite_MGBCR2004TLC" /><a
 * href="http://de.linkedin.com/in/frankmittelbach">Frank Mittelbach</a>,
 * <a href="http://goossens.web.cern.ch/goossens/">Michel Goossens</a>, <a
 * href="http://www.linkedin.com/in/johannesbraams">Johannes Braams</a>, <a
 * href="http://www.dcarlisle.demon.co.uk/david/">David Carlisle</a>,
 * and&nbsp;<a href=
 * "http://www.informit.com/authors/bio.aspx?a=909F1094-95B5-42F8-B6CF-80C27C69B728"
 * >Chris Rowley</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;The LaTeX
 * Companion,&rdquo;</span> 2004, Reading, MA, USA: Addison-Wesley
 * Publishing Co. Inc.. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0201362996">0-201-36299-6</a></div></li>
 * <li><div><span id="cite_OPHS2011TNSSITLOLI1M" /><a
 * href="http://tobi.oetiker.ch/">Tobias Oetiker</a>, <a
 * href="http://homepage.boku.ac.at/partl/">Hubert Partl</a>, Irene Hyna,
 * and&nbsp;Elisabeth Schlegl: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;The Not So Short
 * Introduction to LaTeX2&#949; &#8210; Or LaTeX2&#949; in 157
 * minutes,&rdquo;</span> April&nbsp;6, 2011. <div>links: [<a
 * href="http://tobi.oetiker.ch/lshort/lshort.pdf">1</a>] and&nbsp;[<a
 * href=
 * "http://www.vde.et.ruhr-uni-bochum.de/latex/dokumentation/tex_lshort.pdf"
 * >2</a>]</div></div></li>
 * <li><div><span id="cite_JF2011LEEUEBM" /><a
 * href="mailto:manuela.juergens@fernuni-hagen.de">Manuela J&#252;rgens</a>
 * and&nbsp;<a
 * href="http://www.fernuni-hagen.de/pr/team/thomas.feuerstack.shtml"
 * >Thomas Feuerstack</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;LaTeX eine
 * Einf&#252;hrung und ein bisschen mehr&#8230;,&rdquo;</span>
 * September&nbsp;2011, Hagen, North Rhine-Westphalia, Germany:
 * FernUniversit&#228;t in Hagen, Zentrum f&#252;r Medien &amp; IT.
 * <div>links: [<a href=
 * "http://www.fernuni-hagen.de/imperia/md/content/zmi_2010/a026_latex_einf.pdf"
 * >1</a>] and&nbsp;[<a href=
 * "http://www.wiwiss.fu-berlin.de/institute/iso/links/latex_einfuehrung_manuela_juergens.pdf"
 * >2</a>]</div></div></li>
 * <li><div><span id="cite_J1995LFAONVDH" /><a
 * href="mailto:manuela.juergens@fernuni-hagen.de">Manuela
 * J&#252;rgens</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;LaTeX &#8210;
 * Fortgeschrittene Anwendungen &#8210; oder: neues von den
 * Hobbits&#8230;,&rdquo;</span> 1995, Hagen, North Rhine-Westphalia,
 * Germany: FernUniversit&#228;t Gesamthochschule in Hagen,
 * Universit&#228;tsrechenzentrum, Abteilung Wissenschaftliche Anwendungen.
 * <div>links: [<a href=
 * "ftp://ftp.fernuni-hagen.de/pub/pdf/urz-broschueren/broschueren/a0279510.pdf"
 * >1</a>] and&nbsp;[<a
 * href="http://www2.fz-juelich.de/jsc/files/docs/bhb/bhb-0135.pdf"
 * >2</a>]</div></div></li>
 * <li><div><span id="cite_MIKTEX" /><a
 * href="http://miktex.org/impressum">Christian Schenk</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;MiKTEX
 * &#8230;typesetting beautiful documents&#8230;,&rdquo;</span> (Software),
 * 2013. <div>link: [<a href="http://miktex.org/">1</a>]</div></div></li>
 * <li><div><span id="cite_XETEX" /><a
 * href="https://tug.org/interviews/kew.html">Jonathan Kew</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;The XeTeX
 * Typesetting System,&rdquo;</span> (Software), 2013, Dallas, TX, USA: SIL
 * International. <div>link: [<a href=
 * "http://scripts.sil.org/cms/scripts/page.php?site_id=nrsi&amp;amp;id=xetex"
 * >1</a>]</div></div></li>
 * <li><div><span id="cite_TEXLIVE" /><a
 * href="http://users.ox.ac.uk/~rahtz/">Sebastian Rahtz</a>, Akira Kakuto,
 * Karl Berry, Manuel P&#233;gouri&#233;-Gonnard, Norbert Preining, Peter
 * Breitenlohner, Reinhard Kotucha, Siep Kroonenberg, Staszek Wawrykiewicz,
 * and&nbsp;Tomasz Trzeciak: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;TeX
 * Live,&rdquo;</span> (Software), June&nbsp;30, 2013, Portland, OR, USA:
 * TeX Users Group (TUG). <div>link: [<a
 * href="http://www.tug.org/texlive/">1</a>]</div></div></li>
 * <li><div><span id="cite_ADOBEISO32000" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;Adobe&#174;
 * Supplement to the ISO 32000; BaseVersion: 1.7; ExtensionLevel: 3;
 * Adobe&#174; Acrobat&#174; SDK, Version 9.0,&rdquo;</span>
 * June&nbsp;2008. <div>link: [<a href=
 * "http://wwwimages.adobe.com/www.adobe.com/content/dam/Adobe/en/devnet/pdf/pdfs/adobe_supplement_iso32000.pdf"
 * >1</a>]</div></div></li>
 * <li><div><span id="cite_ISO320002008" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;Document
 * Management &#8210; Portable Document Format &#8210; Part 1: PDF
 * 1.7,&rdquo;</span> July&nbsp;2008</div></li>
 * <li><div><span id="cite_A1992EPFFS" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;Encapsulated
 * PostScript File Format Specification,&rdquo;</span> May&nbsp;1, 1992.
 * <div>link: [<a href
 * ="http://partners.adobe.com/public/developer/en/ps/5002.EPSF_Spec.pdf"
 * >1</ a>]</div></div></li>
 * <li><div><span id="cite_W2006AFGTP" /><a
 * href="http://www.tailrecursive.org/">Peter J. Weingartner</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;A First Guide to
 * PostScript,&rdquo;</span> (Website), February&nbsp;24, 2006. <div>links:
 * [<a
 * href="http://www.tailrecursive.org/postscript/postscript.html">1</a>],
 * [<a href
 * ="http://ftp.cc.ac.cn/chinese/icmsec/stydy/FirstGuideToPostScript.pdf"
 * >2</ a>], [<a href=
 * "http://w3-o.cs.hm.edu/~ruckert/compiler/www.cs.indiana.edu/contacting.html"
 * >3</a>], and&nbsp;[<a
 * href="http://www.tailrecursive.org/postscript/">4</a>]</div></div></li>
 * <li><div><span id="cite_DJOSTBHLPAFBHKS2010FJL" />Mark Donszelmann, Tony
 * Johnson, Dmitry Onoprienko, Victor Serbo, Max Turri, Gary Bower, Julius
 * Hrivnac, Charles Loomis, Joseph Perl, Peter Armstrong, Simon Fischer,
 * Andre Bach, Partick Hellwig, Sami Kama, and&nbsp;Paul Spence: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;FreeHEP Java
 * Libraries,&rdquo;</span> (Software), 2010, Menlo Park, CA, USA: SLAC
 * National Accelerator Laboratory, Stanford University. <div>link: [<a
 * href="http://java.freehep.org/">1</a>]</div></div></li>
 * <li><div><span id="cite_O1988B" /><a
 * href="http://en.wikipedia.org/wiki/Oren_Patashnik">Oren Patashnik</a>:
 * <span style
 * ="font-style:italic;font-family:cursive;">&ldquo;BIBTEXing,&rdquo
 * ;</span> (Website), February&nbsp;8, 1988. <div>link: [<a
 * href="http://ftp.ctex.org/mirrors/CTAN/biblio/bibtex/base/btxdoc.pdf"
 * >1</a>]</div></div></li>
 * </ol>
 */
final class _Compiler {

  /** have we been set up? */
  private boolean m_setup;

  /** the latex context */
  private final LaTeXContext m_ctx;

  /** the logger */
  private Logger m_log;

  /** the directory we're in */
  private File m_dir;

  /** is latex enabled? */
  private boolean m_LaTeXenabled;
  /** is xelatex enabled? */
  private boolean m_XeLaTeXenabled;
  /** the latex command */
  private String m_LaTeX;
  /** the xelatex command */
  private String m_XeLaTeX;
  /** the bibtex command */
  private String m_BibTeX;
  /** the dvi 2 ps command */
  private String m_dvi2Ps;
  /** the ps 2 pdf command */
  private String m_ps2Pdf;
  /** the ghost script command */
  private String m_ghostScript;

  /** the base name */
  private String m_baseName;

  /** the pdf file */
  private File m_pdfFile;
  /** the pdf file name */
  private String m_pdfName;

  /** the ps file */
  private File m_psFile;
  /** the ps file name */
  private String m_psName;

  /** the dvi file */
  private File m_dviFile;
  /** the dvi file name */
  private String m_dviName;

  /** should we log process output? */
  private boolean m_logOutput;

  /**
   * instantiate
   *
   * @param ctx
   *          the context
   */
  _Compiler(final LaTeXContext ctx) {
    super();
    this.m_ctx = ctx;
    this.m_setup = false;
  }

  /** setup */
  private synchronized final void __setup() {
    final LaTeXDriver driver;
    final String documentName;
    int i;

    if (this.m_setup) {
      throw new IllegalStateException();
    }
    this.m_setup = true;

    this.m_dir = FileUtils.canonicalize(this.m_ctx.getBaseDir());

    documentName = this.m_ctx.getDocumentFile().getName();
    i = documentName.lastIndexOf('.');
    if (i > 0) {
      this.m_baseName = TextUtils.prepare(documentName.substring(0, i));
    } else {
      this.m_baseName = documentName;
    }

    this.m_pdfName = (this.m_baseName + ".pdf"); //$NON-NLS-1$
    this.m_pdfFile = FileUtils.canonicalize(new File(this.m_dir,
        this.m_pdfName));

    driver = this.m_ctx.getDriver();
    synchronized (driver.m_synch) {
      this.m_LaTeXenabled = driver.canUseLaTeX();
      if (this.m_LaTeXenabled) {
        this.m_LaTeX = _Compiler.__toString(driver.getLaTeX());
        this.m_dvi2Ps = _Compiler.__toString(driver.getDvi2Ps());
        this.m_ps2Pdf = _Compiler.__toString(driver.getPs2Pdf());
        this.m_ghostScript = _Compiler.__toString(driver.getPs2Pdf());
      } else {
        this.m_LaTeX = null;
        this.m_dvi2Ps = null;
        this.m_ps2Pdf = null;
        this.m_ghostScript = null;
      }

      this.m_XeLaTeXenabled = driver.canUseXeLaTeX();
      if (this.m_XeLaTeXenabled) {
        this.m_XeLaTeX = _Compiler.__toString(driver.getXeLaTeX());
      } else {
        this.m_XeLaTeX = null;
      }
      this.m_BibTeX = _Compiler.__toString(driver.getBibTeX());
    }

    this.m_logOutput = driver.logProcessOutput();
  }

  /**
   * file-to-string
   *
   * @param f
   *          the file
   * @return the string version
   */
  private static final String __toString(final File f) {
    if (f == null) {
      return null;
    }
    try {
      return f.getCanonicalPath();
    } catch (final Throwable t) {
      return String.valueOf(f);
    }
  }

  /** clean up */
  private final void __cleanup() {
    final Logger log;

    log = this.m_log;
    if ((log != null) && (log.isLoggable(Level.INFO))) {
      log.info("Now cleaning up: deleting unnecessary temporary files.");//$NON-NLS-1$
    }
    try {
      try {
        new File(this.m_dir, (this.m_baseName + ".aux")).delete();//$NON-NLS-1$
      } finally {
        try {
          new File(this.m_dir, (this.m_baseName + ".log")).delete();//$NON-NLS-1$
        } finally {
          try {
            new File(this.m_dir, (this.m_baseName + ".bbl")).delete();//$NON-NLS-1$
          } finally {
            try {
              new File(this.m_dir, (this.m_baseName + ".blg")).delete();//$NON-NLS-1$
            } finally {
              new File(this.m_dir, (this.m_baseName + ".out")).delete();//$NON-NLS-1$
            }
          }
        }
      }
    } finally {
      if ((log != null) && (log.isLoggable(Level.INFO))) {
        log.info("Finished cleaning up.");//$NON-NLS-1$
      }
    }
  }

  /**
   * compile the latex file
   *
   * @return {@code true} if compiling was possible, {@code false}
   *         otherwise
   * @throws IOException
   *           if io fails
   */
  private final boolean __compile_latex() throws IOException {
    int i;
    long oldSize, newSize;
    boolean ret;
    final Logger log;

    if (this.m_LaTeXenabled && //
        ((this.m_LaTeX != null) && //
            (this.m_dvi2Ps != null) && //
            ((this.m_ps2Pdf != null) || (this.m_ghostScript != null)))) {//
      ret = true;

      // ok, we found a LaTeX installation, so we can run the toolchain
      // (0) -> latex -> bibtex -> return to (0) if file length changes
      // otherwise -> dvi2ps -> ps2pdf/ghostscript

      log = this.m_log;
      if ((log != null) && (log.isLoggable(Level.INFO))) {
        log.info("Using LaTeX tool chain.");//$NON-NLS-1$
      }

      this.m_dviName = (this.m_baseName + ".dvi"); //$NON-NLS-1$
      this.m_dviFile = FileUtils.canonicalize(new File(this.m_dir,
          this.m_dviName));

      this.m_psName = (this.m_baseName + ".ps"); //$NON-NLS-1$
      this.m_psFile = FileUtils.canonicalize(new File(this.m_dir,
          this.m_psName));

      maker: {
        try {
          try {
            newSize = (-1l);
            i = 1;

            do {
              if ((log != null) && (log.isLoggable(Level.INFO))) {
                log.info("Begin LaTeX iteration " + i);//$NON-NLS-1$
              }

              try {
                oldSize = newSize;
                if (!(this.__latex())) {
                  ret = false;
                  break maker;
                }
                if (!(this.__bibtex())) {
                  ret = false;
                  break maker;
                }
                newSize = _Compiler.__length(this.m_dviFile);
              } finally {
                if ((log != null) && (log.isLoggable(Level.INFO))) {
                  log.info("Finished LaTeX iteration " + i);//$NON-NLS-1$
                }
              }

              if ((i++) > 10) {
                break;
              }
            } while (newSize > oldSize);

            if ((log != null) && (log.isLoggable(Level.INFO))) {
              log.info("Transforming DVI file to PS.");//$NON-NLS-1$
            }

            if (!(this.__dvi2ps())) {
              ret = false;
              break maker;
            }
          } finally {
            this.m_dviFile.delete();
          }

          if ((log != null) && (log.isLoggable(Level.INFO))) {
            log.info("Transforming PS file to PDF.");//$NON-NLS-1$
          }
          try {
            if (!(this.__ps2pdf())) {
              ret = false;
              break maker;
            }
          } finally {
            this.m_psFile.delete();
          }

        } finally {
          this.__cleanup();
        }
      }
      return (ret && (_Compiler.__length(this.m_pdfFile) > 100));
    }

    return false;
  }

  /**
   * compile the xelatex file
   *
   * @return {@code true} if compiling was possible, {@code false}
   *         otherwise
   * @throws IOException
   *           if io fails
   */
  private final boolean __compile_xelatex() throws IOException {
    boolean ret;
    int i;
    long oldSize, newSize;
    final Logger log;

    if (this.m_XeLaTeXenabled && (this.m_XeLaTeX != null)) {
      ret = true;

      // good: we found a xelatex installation, so we iterate xelatex as
      // long
      // as the file sizes change
      log = this.m_log;
      if ((log != null) && (log.isLoggable(Level.INFO))) {
        log.info("Using XeLaTeX tool chain.");//$NON-NLS-1$
      }

      maker: {

        try {
          newSize = (-1l);
          i = 1;
          do {
            oldSize = newSize;

            if ((log != null) && (log.isLoggable(Level.INFO))) {
              log.info("Begin XeLaTeX iteration " + i);//$NON-NLS-1$
            }

            if (!(this.__xelatex())) {
              ret = false;
              break maker;
            }
            if (!(this.__bibtex())) {
              ret = false;
              break maker;
            }

            if ((log != null) && (log.isLoggable(Level.INFO))) {
              log.info("End XeLaTeX iteration " + i);//$NON-NLS-1$
            }

            newSize = _Compiler.__length(this.m_pdfFile);
            if ((i++) > 10) {
              break;
            }
          } while (newSize > oldSize);
        } finally {
          this.__cleanup();
        }

      }
      return (ret && (_Compiler.__length(this.m_pdfFile) > 100));
    }

    return false;
  }

  /**
   * run!
   *
   * @return the latex file
   * @throws IOException
   *           if io fails
   */
  public final File run() throws IOException {
    IOException thr;
    final String n;
    final Logger log;

    log = this.m_log = this.m_ctx.getLogger();

    if ((log != null) && (log.isLoggable(Level.INFO))) {
      n = _Compiler.__toString(this.m_ctx.getDocumentFile());
      log.info("Begin compiling document " + n);//$NON-NLS-1$
    } else {
      n = null;
    }

    try {

      this.__setup();
      thr = null;

      compiler: {
        try {
          // ok, we found a LaTeX installation, so we can run the
          // toolchain
          // (0) -> latex -> bibtex -> return to (0) if file length
          // changes
          // otherwise -> dvi2ps -> ps2pdf/ghostscript

          if (this.__compile_latex()) {
            break compiler;
          }
        } catch (final IOException ioe) {
          thr = ioe;
        }
        try {
          if (this.__compile_xelatex()) {
            break compiler;
          }
        } catch (final IOException ioe) {
          thr = ioe;
        }

      }

      if (thr != null) {
        throw thr;
      }

    } finally {
      if ((log != null) && (log.isLoggable(Level.INFO))) {
        log.info("Finished compiling document " + n);//$NON-NLS-1$
      }
    }

    return this.m_pdfFile;
  }

  /**
   * get the file size
   *
   * @param f
   *          the file
   * @return the file size
   */
  private static final long __length(final File f) {
    if (f.exists()) {
      return Math.max(f.length(), 0l);
    }
    return 0l;
  }

  /**
   * run a process
   *
   * @param pb
   *          the process builder
   * @return {@code true} if and only if the execution we successful,
   *         {@code false} otherwise
   * @throws IOException
   *           if io fails
   */
  private final boolean __run(final ProcessBuilder pb) throws IOException {
    final AutoProcess ap;
    final Logger log;
    // Process p;
    // InputStream os;
    boolean ret;

    ret = false;
    log = this.m_log;

    if ((log != null) && (log.isLoggable(Level.INFO))) {
      log.fine("Starting program '" + //$NON-NLS-1$
          pb.command().get(0) + "'.");//$NON-NLS-1$
    }
    try {
      pb.directory(this.m_dir);

      pb.redirectErrorStream(false);
      ap = new AutoProcess((this.m_logOutput ? log : null), Level.INFO,
          pb.start());
      ret = (ap.waitFor() == 0);
    } finally {
      if ((log != null) && (log.isLoggable(Level.INFO))) {
        log.fine("Program '" + //$NON-NLS-1$
            pb.command().get(0) + "' has finished.");//$NON-NLS-1$
      }
    }

    return ret;
  }

  /**
   * compile the latex file
   *
   * @return {@code true} if and only if the execution we successful,
   *         {@code false} otherwise
   * @throws IOException
   *           if io fails
   */
  private final boolean __latex() throws IOException {
    final ProcessBuilder pb;

    pb = new ProcessBuilder();
    pb.command(this.m_LaTeX, this.m_baseName);
    return this.__run(pb);
  }

  /**
   * compile the xelatex file if io fails
   *
   * @return {@code true} if and only if the execution we successful,
   *         {@code false} otherwise
   * @throws IOException
   *           if io fails
   */
  private final boolean __xelatex() throws IOException {
    final ProcessBuilder pb;

    pb = new ProcessBuilder();
    pb.command(this.m_XeLaTeX, this.m_baseName);
    return this.__run(pb);
  }

  /**
   * compile the bibtex file
   *
   * @return {@code true} if and only if the execution we successful,
   *         {@code false} otherwise
   * @throws IOException
   *           if io fails
   */
  private final boolean __bibtex() throws IOException {
    final ProcessBuilder pb;

    if (this.m_BibTeX != null) {
      pb = new ProcessBuilder();
      pb.command(this.m_BibTeX, this.m_baseName);
      return this.__run(pb);
    }
    return false;
  }

  /**
   * compile the dvi file
   *
   * @return {@code true} if and only if the execution we successful,
   *         {@code false} otherwise
   * @throws IOException
   *           if io fails
   */
  private final boolean __dvi2ps() throws IOException {
    final ProcessBuilder pb;

    if (this.m_dvi2Ps != null) {
      pb = new ProcessBuilder();
      pb.command(this.m_dvi2Ps, this.m_dviName, "-o", this.m_psName); //$NON-NLS-1$
      return this.__run(pb);
    }

    return false;
  }

  /**
   * compile the ps file
   *
   * @return {@code true} if and only if the execution we successful,
   *         {@code false} otherwise
   * @throws IOException
   *           if io fails
   */
  private final boolean __ps2pdf() throws IOException {
    ProcessBuilder pb;
    IOException thr;

    thr = null;

    if (this.m_ps2Pdf != null) {
      try {
        pb = new ProcessBuilder();
        pb.directory(this.m_dir);
        pb.command(this.m_ps2Pdf, this.m_psName, this.m_pdfName);
        if (this.__run(pb)) {
          if ((_Compiler.__length(this.m_pdfFile) > 100)) {
            return true;
          }
        }
      } catch (final IOException ioe) {
        thr = ioe;
      }
    }

    if (this.m_ghostScript != null) {
      pb = new ProcessBuilder();
      pb.command(this.m_ghostScript, "-q", //$NON-NLS-1$
          "-dNOPAUSE",//$NON-NLS-1$
          "-sDEVICE=pdfwrite",//$NON-NLS-1$
          ("-sOutputFile=" + this.m_pdfName),//$NON-NLS-1$
          this.m_psName, "-c", //$NON-NLS-1$
          "quit");//$NON-NLS-1$
      return this.__run(pb);
    }

    if (thr != null) {
      throw thr;
    }
    return false;
  }
}
