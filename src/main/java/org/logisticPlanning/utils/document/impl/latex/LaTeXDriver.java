package org.logisticPlanning.utils.document.impl.latex;

import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import org.logisticPlanning.utils.config.Configurable;
import org.logisticPlanning.utils.config.Configuration;
import org.logisticPlanning.utils.document.impl.latex.ieeeArticle.IEEEArticleDriver;
import org.logisticPlanning.utils.document.spec.DocumentDriver;
import org.logisticPlanning.utils.document.spec.FigureBody;
import org.logisticPlanning.utils.document.spec.Graphic;
import org.logisticPlanning.utils.io.FileUtils;

/**
 * <p>
 * This is the base class for <a
 * href="http://en.wikipedia.org/wiki/LaTeX">LaTeX</a> document drivers.
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
public class LaTeXDriver extends DocumentDriver {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the latex: {@value} , see {@link #PARAM_COMPILE_LATEX} */
  public static final String PARAM_LATEX = "LaTeX"; //$NON-NLS-1$
  /** the xelatex: {@value} , see {@link #PARAM_COMPILE_LATEX} */
  public static final String PARAM_XELATEX = "XeLaTeX"; //$NON-NLS-1$
  /** the bibtex: {@value} */
  public static final String PARAM_BIBTEX = "BibTeX"; //$NON-NLS-1$
  /** the dvi 2 ps: {@value} */
  public static final String PARAM_DVI_2_PS = "Dvi2Ps"; //$NON-NLS-1$
  /** the ps 2 pdf: {@value} */
  public static final String PARAM_PS_2_PDF = "Ps2Pdf"; //$NON-NLS-1$
  /** the ghost script: {@value} */
  public static final String PARAM_GHOST_SCRIPT = "ghostScript"; //$NON-NLS-1$
  /** the use latex: {@value} ? */
  public static final String PARAM_USE_LATEX = "useLaTeX"; //$NON-NLS-1$
  /** the use xelatex?: {@value} */
  public static final String PARAM_USE_XELATEX = "useXeLaTeX"; //$NON-NLS-1$

  /** should we compile latex documents to pdf: {@value} */
  public static final String PARAM_COMPILE_LATEX = "compileLaTeX"; //$NON-NLS-1$

  /** should we log the output of the processes: {@value} */
  public static final String PARAM_LOG_PROCESS_OUTPUT = "logProcessOutput"; //$NON-NLS-1$

  /** we synchronize on this object */
  private static final Object SYNCH = org.freehep.graphicsio.ps.PSGraphics2D.class;

  /** the default latex command binary */
  private static final File LATEX;
  /** the default xelatex command binary */
  private static final File XELATEX;
  /** the default bibtex command binary */
  private static final File BIBTEX;
  /** the default dvi 2 ps command binary */
  private static final File DVI2PS;
  /** the default ps 2 pdf command binary */
  private static final File PS2PDF;
  /** the default ghost script command binary */
  private static final File GHOST;

  static {
    File f;

    f = LaTeXDriver.__findLaTeX("latex"); //$NON-NLS-1$
    if (f == null) {
      f = LaTeXDriver.__findLaTeX("cslatex"); //$NON-NLS-1$
    }
    LATEX = f;

    f = FileUtils.findExecutableInPath("bibtex"); //$NON-NLS-1$
    if (f == null) {
      f = FileUtils.findExecutableInPath("bibtex8"); //$NON-NLS-1$
      if (f == null) {
        f = FileUtils.findExecutableInPath("miktex-bibtex"); //$NON-NLS-1$
      }
    }
    BIBTEX = f;

    f = FileUtils.findExecutableInPath("dvips"); //$NON-NLS-1$
    if (f == null) {
      f = FileUtils.findExecutableInPath("dvi2ps"); //$NON-NLS-1$
      if (f == null) {
        f = FileUtils.findExecutableInPath("dvitops"); //$NON-NLS-1$
      }
    }
    DVI2PS = f;

    XELATEX = FileUtils.findExecutableInPath("xelatex"); //$NON-NLS-1$

    f = FileUtils.findExecutableInPath("pspdf"); //$NON-NLS-1$
    if (f == null) {
      f = FileUtils.findExecutableInPath("ps2pdf"); //$NON-NLS-1$
      if (f == null) {
        f = FileUtils.findExecutableInPath("pstopdf"); //$NON-NLS-1$
        if (f == null) {
          f = FileUtils.findExecutableInPath("ps2pdf14"); //$NON-NLS-1$
          if (f == null) {
            f = FileUtils.findExecutableInPath("ps2pdf13"); //$NON-NLS-1$
            if (f == null) {
              f = FileUtils.findExecutableInPath("ps2pdf12"); //$NON-NLS-1$
            }
          }
        }
      }
    }
    PS2PDF = f;

    f = FileUtils.findExecutableInPath("gswin64c"); //$NON-NLS-1$
    if (f == null) {
      f = FileUtils.findExecutableInPath("gswin32c"); //$NON-NLS-1$
    }
    GHOST = f;
  }

  /** the internal synchronizer */
  final Object m_synch;

  /** the properties */
  private org.freehep.util.UserProperties m_props;

  /** are we initialized? */
  private boolean m_init;

  /** the latex command, see {@link #PARAM_LATEX} */
  private File m_LaTeX;

  /** the xelatex command, see {@link #PARAM_XELATEX} */
  private File m_XeLaTeX;

  /** the bibtex command, see {@link #PARAM_BIBTEX} */
  private File m_BibTeX;

  /** the dvi 2 ps command, see {@link #PARAM_DVI_2_PS} */
  private File m_dvi2Ps;

  /** the ps 2 pdf command, see {@link #PARAM_PS_2_PDF} */
  private File m_ps2Pdf;

  /** the ghost script command, see {@link #PARAM_GHOST_SCRIPT} */
  private File m_ghostScript;

  /** the latex tool chain is enabled, see {@link #PARAM_USE_LATEX} */
  private boolean m_LaTeXenabled;
  /** the xelatex tool chain is enabled, see {@link #PARAM_XELATEX} */
  private boolean m_XeLaTeXenabled;
  /** compile LaTeX documents, see {@link #PARAM_COMPILE_LATEX} */
  private boolean m_compileLaTeX;
  /** log the process output, see {@link #PARAM_LOG_PROCESS_OUTPUT} */
  private boolean m_logProcessOutput;

  /** instantiate the driver */
  public LaTeXDriver() {
    this("LaTeX"); //$NON-NLS-1$
  }

  /**
   * create the LaTeX driver
   *
   * @param name
   *          the driver's name
   */
  protected LaTeXDriver(final String name) {
    super(name);
    this.m_LaTeX = LaTeXDriver.LATEX;
    this.m_XeLaTeX = LaTeXDriver.XELATEX;
    this.m_BibTeX = LaTeXDriver.BIBTEX;
    this.m_dvi2Ps = LaTeXDriver.DVI2PS;
    this.m_ps2Pdf = LaTeXDriver.PS2PDF;
    this.m_ghostScript = LaTeXDriver.GHOST;

    this.m_LaTeXenabled = true;
    this.m_XeLaTeXenabled = true;
    this.m_compileLaTeX = true;
    this.m_synch = new Object();
  }

  /**
   * find a latex executable
   *
   * @param s
   *          the string
   * @return the file
   */
  private static final File __findLaTeX(final String s) {
    final File q;
    final String ss;

    q = FileUtils.findExecutableInPath(s);
    if (q != null) {
      ss = q.toString().toLowerCase();
      if ((ss.contains("pdftex") || //$NON-NLS-1$
      ss.contains("pdflatex"))) {//$NON-NLS-1$
        return null;
      }

    }
    return q;
  }

  /**
   * can we use LaTeX?
   *
   * @return {@code true} if the latex tool chain is enabled, {@code false}
   *         otherwise
   */
  public final boolean canUseLaTeX() {
    synchronized (this.m_synch) {
      return this.m_LaTeXenabled;
    }
  }

  /**
   * set whether we can use the LaTeX tool chain or not
   *
   * @param canUse
   *          {@code true} for enabling the latex tool chain, {@code false}
   *          otherwise
   */
  public final void setUseLaTeX(final boolean canUse) {
    synchronized (this.m_synch) {
      this.m_LaTeXenabled = canUse;
    }
  }

  /**
   * Should we compile the LaTeX document to PDF?
   *
   * @return {@code true} if compiling LaTeX is enabled, {@code false}
   *         otherwise
   */
  public final boolean shouldCompileLaTeX() {
    synchronized (this.m_synch) {
      return this.m_compileLaTeX;
    }
  }

  /**
   * Set whether we can compile LaTeX documents or not
   *
   * @param canCompile
   *          {@code true} for enabling LaTeX compilation , {@code false}
   *          otherwise
   */
  public final void setCompileLaTeX(final boolean canCompile) {
    synchronized (this.m_synch) {
      this.m_compileLaTeX = canCompile;
    }
  }

  /**
   * Should we log the process output?
   *
   * @return {@code true} if process output should be logged, {@code false}
   *         if it should silently be discarted
   */
  public final boolean logProcessOutput() {
    synchronized (this.m_synch) {
      return this.m_logProcessOutput;
    }
  }

  /**
   * set whether we log the process output
   *
   * @param logProcessOutput
   *          {@code true} if process output should be logged,
   *          {@code false} if it should silently be discarted
   */
  public final void setLogProcessOutput(final boolean logProcessOutput) {
    synchronized (this.m_synch) {
      this.m_logProcessOutput = logProcessOutput;
    }
  }

  /**
   * can we use XeLaTeX?
   *
   * @return {@code true} if the xelatex tool chain is enabled,
   *         {@code false} otherwise
   */
  public final boolean canUseXeLaTeX() {
    synchronized (this.m_synch) {
      return this.m_XeLaTeXenabled;
    }
  }

  /**
   * set whether we can use the XeLaTeX tool chain or not
   *
   * @param canUse
   *          {@code true} for enabling the xelatex tool chain,
   *          {@code false} otherwise
   */
  public final void setUseXeLaTeX(final boolean canUse) {
    synchronized (this.m_synch) {
      this.m_XeLaTeXenabled = canUse;
    }
  }

  /**
   * get the LaTeX executable
   *
   * @return the LaTeX executable
   */
  public final File getLaTeX() {
    synchronized (this.m_synch) {
      return this.m_LaTeX;
    }
  }

  /**
   * set the LaTeX executable
   *
   * @param f
   *          the new value of the LaTeX executable
   */
  public final void setLaTeX(final File f) {
    synchronized (this.m_synch) {
      this.m_LaTeX = FileUtils.canonicalize(f);
    }
  }

  /**
   * get the XeLaTeX executable
   *
   * @return the XeLaTeX executable
   */
  public final File getXeLaTeX() {
    synchronized (this.m_synch) {
      return this.m_XeLaTeX;
    }
  }

  /**
   * set the XeLaTeX executable
   *
   * @param f
   *          the new value of the XeLaTeX executable
   */
  public final void setXeLaTeX(final File f) {
    synchronized (this.m_synch) {
      this.m_XeLaTeX = FileUtils.canonicalize(f);
    }
  }

  /**
   * get the BibTeX executable
   *
   * @return the BibTeX executable
   */
  public final File getBibTeX() {
    synchronized (this.m_synch) {
      return this.m_BibTeX;
    }
  }

  /**
   * set the BibTeX executable
   *
   * @param f
   *          the new value of the BibTeX executable
   */
  public final void setBibTeX(final File f) {
    synchronized (this.m_synch) {
      this.m_BibTeX = FileUtils.canonicalize(f);
    }
  }

  /**
   * get the DVI2PS executable
   *
   * @return the DVI2PS executable
   */
  public final File getDvi2Ps() {
    synchronized (this.m_synch) {
      return this.m_dvi2Ps;
    }
  }

  /**
   * set the DVI2PS executable
   *
   * @param f
   *          the new value of the DVI2PS executable
   */
  public final void setDvi2Ps(final File f) {
    synchronized (this.m_synch) {
      this.m_dvi2Ps = FileUtils.canonicalize(f);
    }
  }

  /**
   * get the Ps2Pdf executable
   *
   * @return the Ps2Pdf executable
   */
  public final File getPs2Pdf() {
    synchronized (this.m_synch) {
      return this.m_ps2Pdf;
    }
  }

  /**
   * set the Ps2Pdf executable
   *
   * @param f
   *          the new value of the Ps2Pdf executable
   */
  public final void setPs2Pdf(final File f) {
    synchronized (this.m_synch) {
      this.m_ps2Pdf = FileUtils.canonicalize(f);
    }
  }

  /**
   * get the ghost script executable
   *
   * @return the ghost script executable
   */
  public final File getGhostScript() {
    synchronized (this.m_synch) {
      return this.m_ghostScript;
    }
  }

  /**
   * set the ghost script executable
   *
   * @param f
   *          the new value of the ghost script executable
   */
  public final void setGhostScript(final File f) {
    synchronized (this.m_synch) {
      this.m_ghostScript = FileUtils.canonicalize(f);
    }
  }

  /** initialize this driver */
  private final void __init() {
    synchronized (this.m_synch) {
      synchronized (LaTeXDriver.SYNCH) {

        if (this.m_init) {
          return;
        }
        this.m_init = true;

        this.m_props = new org.freehep.util.UserProperties();

        org.freehep.graphicsio.ps.PSGraphics2D.setClipEnabled(true);

        this.m_props.putAll(org.freehep.graphicsio.ps.PSGraphics2D
            .getDefaultProperties());
        this.m_props.setProperty(
            org.freehep.graphicsio.ps.PSGraphics2D.PAGE_SIZE,
            org.freehep.graphicsio.PageConstants.BEST_FIT);
        this.m_props.setProperty(
            org.freehep.graphicsio.ps.PSGraphics2D.EMBED_FONTS, true);
        this.m_props.setProperty(
            org.freehep.graphicsio.ps.PSGraphics2D.EMBED_FONTS_AS,
            org.freehep.graphicsio.FontConstants.EMBED_FONTS_TYPE3);
        this.m_props.setProperty(
            org.freehep.graphicsio.ps.PSGraphics2D.BACKGROUND_COLOR,
            Color.WHITE);

      }
    }
  }

  /** {@inheritDoc} */
  @Override
  protected Graphic graphicCreate(final FigureBody owner)
      throws IOException {

    final org.freehep.util.UserProperties up;
    final _EPSFigureBody o;
    final Dimension dim;
    final File outFile;
    final org.freehep.graphicsio.ps.PSGraphics2D g;
    final _EPSGraphics res;

    o = ((_EPSFigureBody) (owner));
    dim = o.getDimensionsInPT();
    outFile = o.getFile();
    outFile.getParentFile().mkdirs();
    synchronized (this.m_synch) {
      synchronized (LaTeXDriver.SYNCH) {

        this.__init();

        up = new org.freehep.util.UserProperties();
        up.putAll(this.m_props);

        org.freehep.graphicsio.ps.PSGraphics2D.setClipEnabled(true);

        g = new org.freehep.graphicsio.ps.PSGraphics2D(outFile, dim);

        g.setProperties(up);
        g.setMultiPage(false);

        g.startExport();
        g.setClip(0, 0, dim.width, dim.height);
        res = new _EPSGraphics(o, g);

      }
    }

    return res;
  }

  /** {@inheritDoc} */
  @Override
  public LaTeXContext createContext(final File dir) throws IOException {
    return new LaTeXContext(this, dir);
  }

  /** {@inheritDoc} */
  @Override
  public void configure(final Configuration config) {
    super.configure(config);

    synchronized (this.m_synch) {
      this.setBibTeX(config.getFile(LaTeXDriver.PARAM_BIBTEX,
          this.getBibTeX()));
      this.setDvi2Ps(config.getFile(LaTeXDriver.PARAM_DVI_2_PS,
          this.getDvi2Ps()));
      this.setGhostScript(config.getFile(LaTeXDriver.PARAM_GHOST_SCRIPT,
          this.getGhostScript()));
      this.setLaTeX(config.getFile(LaTeXDriver.PARAM_LATEX,
          this.getLaTeX()));
      this.setPs2Pdf(config.getFile(LaTeXDriver.PARAM_PS_2_PDF,
          this.getPs2Pdf()));
      this.setXeLaTeX(config.getFile(LaTeXDriver.PARAM_XELATEX,
          this.getXeLaTeX()));
      this.setUseXeLaTeX(config.getBoolean(LaTeXDriver.PARAM_USE_XELATEX,
          this.canUseXeLaTeX()));
      this.setUseLaTeX(config.getBoolean(LaTeXDriver.PARAM_USE_LATEX,
          this.canUseLaTeX()));

      this.setCompileLaTeX(config.getBoolean(
          LaTeXDriver.PARAM_COMPILE_LATEX, this.shouldCompileLaTeX()));
      this.setLogProcessOutput(config.getBoolean(
          LaTeXDriver.PARAM_LOG_PROCESS_OUTPUT, this.logProcessOutput()));
    }
  }

  /** {@inheritDoc} */
  @Override
  public void printParameters(final PrintStream ps) {
    super.printParameters(ps);
    synchronized (this.m_synch) {
      Configurable.printKey(LaTeXDriver.PARAM_BIBTEX, ps);
      ps.println("The BibTeX executable"); //$NON-NLS-1$

      Configurable.printKey(LaTeXDriver.PARAM_DVI_2_PS, ps);
      ps.println("The dvi-2-ps executable"); //$NON-NLS-1$

      Configurable.printKey(LaTeXDriver.PARAM_GHOST_SCRIPT, ps);
      ps.println("The ghost script executable"); //$NON-NLS-1$

      Configurable.printKey(LaTeXDriver.PARAM_LATEX, ps);
      ps.println("The LaTeX executable"); //$NON-NLS-1$

      Configurable.printKey(LaTeXDriver.PARAM_PS_2_PDF, ps);
      ps.println("The ps-2-pdf executable"); //$NON-NLS-1$

      Configurable.printKey(LaTeXDriver.PARAM_XELATEX, ps);
      ps.println("The XeLaTeX executable"); //$NON-NLS-1$

      Configurable.printKey(LaTeXDriver.PARAM_COMPILE_LATEX, ps);
      ps.println("Should we compile LaTeX?"); //$NON-NLS-1$

      Configurable.printKey(LaTeXDriver.PARAM_USE_LATEX, ps);
      ps.println("Can we use the LaTeX tool chain if it is detected/specified?"); //$NON-NLS-1$

      Configurable.printKey(LaTeXDriver.PARAM_USE_XELATEX, ps);
      ps.println("Can we use the XeLaTeX tool chain if it is detected/specified?"); //$NON-NLS-1$
    }
  }

  /** {@inheritDoc} */
  @Override
  public void printConfiguration(final PrintStream ps) {
    super.printConfiguration(ps);

    synchronized (this.m_synch) {
      Configurable.printKey(LaTeXDriver.PARAM_COMPILE_LATEX, ps);
      ps.println(this.shouldCompileLaTeX());

      Configurable.printKey(LaTeXDriver.PARAM_USE_LATEX, ps);
      ps.println(this.canUseLaTeX());

      Configurable.printKey(LaTeXDriver.PARAM_USE_XELATEX, ps);
      ps.println(this.canUseXeLaTeX());

      Configurable.printKey(LaTeXDriver.PARAM_BIBTEX, ps);
      ps.println(this.getBibTeX());

      Configurable.printKey(LaTeXDriver.PARAM_DVI_2_PS, ps);
      ps.println(this.getDvi2Ps());

      Configurable.printKey(LaTeXDriver.PARAM_GHOST_SCRIPT, ps);
      ps.println(this.getGhostScript());

      Configurable.printKey(LaTeXDriver.PARAM_LATEX, ps);
      ps.println(this.getLaTeX());

      Configurable.printKey(LaTeXDriver.PARAM_PS_2_PDF, ps);
      ps.println(this.getPs2Pdf());

      Configurable.printKey(LaTeXDriver.PARAM_XELATEX, ps);
      ps.println(this.getXeLaTeX());
    }
  }

  /**
   * Create an instance of the default LaTeX document driver
   *
   * @return an instance of the default LaTeX document driver
   */
  public static final LaTeXDriver createDefaultLaTeXDriver() {
    return new IEEEArticleDriver();
  }
}
