package org.logisticPlanning.utils.document.impl.latex;

import java.awt.Dimension;
import java.awt.geom.Dimension2D;
import java.io.BufferedWriter;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.logisticPlanning.utils.document.spec.AbstractTableCell;
import org.logisticPlanning.utils.document.spec.AbstractTableRow;
import org.logisticPlanning.utils.document.spec.Authors;
import org.logisticPlanning.utils.document.spec.BibReference;
import org.logisticPlanning.utils.document.spec.Body;
import org.logisticPlanning.utils.document.spec.Document;
import org.logisticPlanning.utils.document.spec.DocumentDimensions;
import org.logisticPlanning.utils.document.spec.ECitationMode;
import org.logisticPlanning.utils.document.spec.ELabelType;
import org.logisticPlanning.utils.document.spec.ESequenceType;
import org.logisticPlanning.utils.document.spec.Element;
import org.logisticPlanning.utils.document.spec.Emphasize;
import org.logisticPlanning.utils.document.spec.Enumeration;
import org.logisticPlanning.utils.document.spec.EnumerationItem;
import org.logisticPlanning.utils.document.spec.Equation;
import org.logisticPlanning.utils.document.spec.Figure;
import org.logisticPlanning.utils.document.spec.FigureBody;
import org.logisticPlanning.utils.document.spec.FigureCaption;
import org.logisticPlanning.utils.document.spec.FigureSeries;
import org.logisticPlanning.utils.document.spec.FigureSeriesCaption;
import org.logisticPlanning.utils.document.spec.FigureSeriesPage;
import org.logisticPlanning.utils.document.spec.Header;
import org.logisticPlanning.utils.document.spec.InlineMath;
import org.logisticPlanning.utils.document.spec.Itemization;
import org.logisticPlanning.utils.document.spec.ItemizationItem;
import org.logisticPlanning.utils.document.spec.Label;
import org.logisticPlanning.utils.document.spec.Macro;
import org.logisticPlanning.utils.document.spec.MacroDescriptor;
import org.logisticPlanning.utils.document.spec.MacroInvocation;
import org.logisticPlanning.utils.document.spec.MacroParameter;
import org.logisticPlanning.utils.document.spec.MathName;
import org.logisticPlanning.utils.document.spec.MathOp;
import org.logisticPlanning.utils.document.spec.MathOpParam;
import org.logisticPlanning.utils.document.spec.MathSubscript;
import org.logisticPlanning.utils.document.spec.MathSuperscript;
import org.logisticPlanning.utils.document.spec.NormalText;
import org.logisticPlanning.utils.document.spec.Section;
import org.logisticPlanning.utils.document.spec.SectionTitle;
import org.logisticPlanning.utils.document.spec.Sequence;
import org.logisticPlanning.utils.document.spec.SingleLabel;
import org.logisticPlanning.utils.document.spec.SubFigure;
import org.logisticPlanning.utils.document.spec.SubFigureCaption;
import org.logisticPlanning.utils.document.spec.Subscript;
import org.logisticPlanning.utils.document.spec.Summary;
import org.logisticPlanning.utils.document.spec.Superscript;
import org.logisticPlanning.utils.document.spec.Table;
import org.logisticPlanning.utils.document.spec.TableBodyCell;
import org.logisticPlanning.utils.document.spec.TableBodyRow;
import org.logisticPlanning.utils.document.spec.TableCaption;
import org.logisticPlanning.utils.document.spec.TableCellDef;
import org.logisticPlanning.utils.document.spec.TableFooter;
import org.logisticPlanning.utils.document.spec.TableFooterCell;
import org.logisticPlanning.utils.document.spec.TableFooterRow;
import org.logisticPlanning.utils.document.spec.TableHeader;
import org.logisticPlanning.utils.document.spec.TableHeaderCell;
import org.logisticPlanning.utils.document.spec.TableHeaderRow;
import org.logisticPlanning.utils.document.spec.TablePage;
import org.logisticPlanning.utils.document.spec.Title;
import org.logisticPlanning.utils.io.EEncoding;
import org.logisticPlanning.utils.io.charIO.CharCharInput;
import org.logisticPlanning.utils.io.charIO.CharOutput;
import org.logisticPlanning.utils.io.charIO.StringCharInput;
import org.logisticPlanning.utils.io.charIO.WriterCharOutput;
import org.logisticPlanning.utils.text.TextUtils;
import org.logisticPlanning.utils.text.transformations.LaTeXCharTransformer;
import org.logisticPlanning.utils.utils.EmptyUtils;

/**
 * <p>
 * The <a href="http://en.wikipedia.org/wiki/LaTeX">LaTeX</a> document API
 * implementation.
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
 * <h2 id="whySoUgly">Ugly Layout Problem with Tables and Figures</h2>
 * <p>
 * One major problem with the LaTeX output is the positioning of
 * &quot;floating objects&quot; such as
 * {@link org.logisticPlanning.utils.document.spec.SectionBody#figure(Label, boolean)
 * figures} and
 * {@link org.logisticPlanning.utils.document.spec.SectionBody#table(Label, int, boolean, TableCellDef...)
 * tables}. LaTeX will automatically try to find good positions for them.
 * However, it can only cope with 18 outstanding (not-yet-positioned)
 * floats at most before killing itself with an error &quot;
 * {@code ! LaTeX Error: Too many unprocessed floats.}&quot; Since there
 * should not be any such hard limits on the document generation process
 * (which would be fairly hard to evaluate and enforce in a meaningful
 * way), we try to deal with this issue in two ways:%
 * </p>
 * <ol id="stopTheUgly">
 * <li>Our LaTeX documents by default import the package <a
 * href="http://www.ctan.org/pkg/placeins">{@code placeins}</a> which
 * offers the command <code>{@link #FLOAT_BARRIER FloatBarrier}</code>
 * which forces all outstanding floats to be laid out, regardless how it
 * looks. We automatically invoke this command every
 * {@value #MAX_FLOATS_WITHOUT_BARRIER_THRESHOLD} floats. The problem is
 * that we cannot know in advance whether there are outstanding floats,
 * since this will be in the LaTeX compiler's decision which is applied to
 * the document after it has been created by this class. Thus, we can only
 * call this function on a fixed rate. For some reason unclear to me, this
 * method alone seems to fail sometimes.</li>
 * <li>Therefore, we furthermore import the package <a
 * href="http://www.ctan.org/pkg/morefloats">{@code morefloats}</a> which
 * lifts LaTeXes limitation on floating object (but not TEXes).</li>
 * </ol>
 * <p>
 * In a combination, these two measures should prevent LaTeX from crashing
 * when too many floats are inserted. However, having many floats at once
 * also means that the layout of the document will not be pretty. This is
 * the drawback of having a software that can automatically lay out
 * floating objects: We cannot really control how the output looks like. A
 * solution can only be to have sufficiently much text between figures and
 * tables to allow LateXes layout mechanisms to work properly.
 * </p>
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
public class LaTeXDocument extends Document {

  /** the float barrier threshold */
  private static final int MAX_FLOATS_WITHOUT_BARRIER_THRESHOLD = 18;

  /** the line-end string */
  private static final char[] LINE_END;

  /** the newline string */
  private static final char[] NEWLINE;

  /** the line separator length */
  private static final int NEWLINE_BEGIN = 1;

  /** the line separator length */
  private static final int NEWLINE_END;

  /** the line end start */
  private static final int LINE_END_BEGIN = 0;

  /** the line end length */
  private static final int LINE_END_END;

  static {
    int l;

    l = TextUtils.LINE_SEPARATOR.length();
    LINE_END_END = (l + 1);
    NEWLINE_END = (LaTeXDocument.LINE_END_END + l);
    LINE_END = NEWLINE = new char[LaTeXDocument.NEWLINE_END];

    TextUtils.LINE_SEPARATOR.getChars(0, l, LaTeXDocument.NEWLINE,
        LaTeXDocument.NEWLINE_BEGIN);
    TextUtils.LINE_SEPARATOR.getChars(0, l, LaTeXDocument.NEWLINE,
        LaTeXDocument.LINE_END_END);
    LaTeXDocument.LINE_END[0] = '%';

    MacroDescriptor.validateMaxParams(LaTeXDocument.class, 9);
  }

  /** the head state start */
  private static final int HEAD_STATE_START = 0;

  /** the head state in the document */
  private static final int HEAD_STATE_IN_DOCUMENT = (LaTeXDocument.HEAD_STATE_START + 1);

  /** the head state after make title */
  private static final int HEAD_STATE_HAS_MAKE_TITLE = (LaTeXDocument.HEAD_STATE_IN_DOCUMENT + 1);

  /** positive infinity */
  private static final char[] POSITIVE_INFINITY = { '{', '\\', 'e', 'n',
      's', 'u', 'r', 'e', 'm', 'a', 't', 'h', '{', '+', '\\', 'i', 'n',
      'f', 't', 'y', '}', '}' };

  /** negative infinity */
  private static final char[] NEGATIVE_INFINITY = { '{', '\\', 'e', 'n',
      's', 'u', 'r', 'e', 'm', 'a', 't', 'h', '{', '-', '\\', 'i', 'n',
      'f', 't', 'y', '}', '}' };

  /** nan */
  private static final char[] NAN = { '{', '\\', 'e', 'n', 's', 'u', 'r',
      'e', 'm', 'a', 't', 'h', '{', '\\', 'e', 'm', 'p', 't', 'y', 's',
      'e', 't', '}', '}' };

  /** close a command */
  private static final char[] COMMAND_END = { '}', '}' };

  /** the numprint begin */
  private static final char[] NUMPRINT_BEGIN = { '{', '\\', 'n', 'u', 'm',
      'p', 'r', 'i', 'n', 't', '{' };

  /** end numprint */
  private static final int NUMPRINT_END_LEN = 2;

  /** begin text normal */
  private static final char[] TEXTNORMAL_BEGIN = { '{', '\\', 't', 'e',
      'x', 't', 'n', 'o', 'r', 'm', 'a', 'l', '{' };

  /** end text normal */
  private static final int TEXTNORMAL_END = 2;

  /** the title begin */
  private static final char[] TITLE_BEGIN = { '\\', 't', 'i', 't', 'l',
      'e', '{' };

  /** end title */
  private static final int TITLE_END_LEN = 1;

  /** the label begin */
  private static final char[] LABEL_BEGIN = { '\\', 'l', 'a', 'b', 'e',
      'l', '{' };

  /** end label */
  private static final int LABEL_END_LEN = 1;

  /** the sequence starts */
  private static final char[][] SEQUENCE_BEGIN = { EmptyUtils.EMPTY_CHARS,// COMMA
      EmptyUtils.EMPTY_CHARS,// AND
      EmptyUtils.EMPTY_CHARS,// OR
      { 'e', 'i', 't', 'h', 'e', 'r', ' ' },// xor
      { 'n', 'e', 'i', 't', 'h', 'e', 'r', ' ' },// nor
      EmptyUtils.EMPTY_CHARS,// to
  };

  /** the single-element sequence begin */
  private static final char[][] SEQUENCE_SINGLE = {
      EmptyUtils.EMPTY_CHARS,// COMMA
      EmptyUtils.EMPTY_CHARS,// AND
      EmptyUtils.EMPTY_CHARS,// OR
      EmptyUtils.EMPTY_CHARS,// OR
      { 'n', 'o', 't', ' ' },// nor
      EmptyUtils.EMPTY_CHARS,// to
  };

  /** the sequence starts */
  private static final char[][] SEQUENCE_BIG_SEP = {
      EmptyUtils.EMPTY_CHARS,// COMMA
      { 'a', 'n', 'd' },// AND
      { 'o', 'r' },// OR
      { 'o', 'r' },// xor
      { 'n', 'o', 'r' },// nor
      { 't', 'o' },// to
  };

  /** the sequence starts */
  private static final char[] SEQUENCE_SMALL_SEP = { ',', ' ' };

  /** the ref begin */
  private static final char[] REF_BEGIN = { '{', '\\', 'r', 'e', 'f', '{' };
  /** end ref */
  private static final int REF_END_LEN = 2;

  /** the default document class */
  private static final char[] DOCUMENT_CLASS = { '\\', 'd', 'o', 'c', 'u',
      'm', 'e', 'n', 't', 'c', 'l', 'a', 's', 's', };

  /** the default document class */
  private static final char[] REQUIRE_PACKAGE = { '\\', 'R', 'e', 'q',
      'u', 'i', 'r', 'e', 'P', 'a', 'c', 'k', 'a', 'g', 'e', };

  /** start the document */
  private static final char[] DOCUMENT_BEGIN = { '\\', 'b', 'e', 'g', 'i',
      'n', '{', 'd', 'o', 'c', 'u', 'm', 'e', 'n', 't', '}' };

  /** end the document */
  private static final char[] DOCUMENT_END = { '\\', 'e', 'n', 'd', '{',
      'd', 'o', 'c', 'u', 'm', 'e', 'n', 't', '}' };

  /** the authors start */
  private static final char[] AUTHORS_BEGIN = { '\\', 'a', 'u', 't', 'h',
      'o', 'r', '{' };

  /** the authors end length */
  private static final int AUTHORS_END_LEN = 1;

  /** the make title */
  private static final char[] MAKE_TITLE_BEGIN = { '\\', 'm', 'a', 'k',
      'e', 't', 'i', 't', 'l', 'e' };
  /** the authors start */
  private static final char[] DATE_BEGIN = { '\\', 'd', 'a', 't', 'e', '{' };
  /** the date end length */
  private static final int DATE_END_LEN = 1;

  /** start the abstract */
  private static final char[] ABSTRACT_BEGIN_A = { '\\', 'b', 'e', 'g',
      'i', 'n', '{', 'a', 'b', 's', 't', 'r', 'a', 'c', 't', '}' };

  /** end the abstract */
  private static final char[] ABSTRACT_END_A = { '\\', 'e', 'n', 'd', '{',
      'a', 'b', 's', 't', 'r', 'a', 'c', 't', '}' };
  /** start the abstract, alternative */
  private static final char[] ABSTRACT_BEGIN_B = { '\\', 'a', 'b', 's',
      't', 'r', 'a', 'c', 't', '{' };
  /** end abstract */
  private static final int ABSTRACT_END_B = 1;

  /** the sections */
  private static final char[][] SECTION_BEGIN_A = {//
      { '\\', 'p', 'a', 'r', 't', '{' },//
      { '\\', 'c', 'h', 'a', 'p', 't', 'e', 'r', '{' },//
      { '\\', 's', 'e', 'c', 't', 'i', 'o', 'n', '{' },//
      { '\\', 's', 'u', 'b', 's', 'e', 'c', 't', 'i', 'o', 'n', '{' },//
      { '\\', 's', 'u', 'b', 's', 'u', 'b', 's', 'e', 'c', 't', 'i', 'o',
          'n', '{' },//
      { '\\', 'p', 'a', 'r', 'a', 'g', 'r', 'a', 'p', 'h', '{' },//
  /*
   * { '\\', 's', 'u', 'b', 'p', 'a', 'r', 'a', 'g', 'r', 'a', 'p', 'h',
   * '{' }
   */};

  /** start the section */
  private static final char[] SECTION_BEGIN_B = { '\\', 's', 't', 'r',
      'u', 't', '\\', '\\', '\\', 't', 'e', 'x', 't', 'b', 'f', '{' };

  /** section end a */
  private static final int SECTION_END_A = 1;

  /** start the section */
  private static final char[] SECTION_END_B = { '}', '~' };

  /** begin figure a */
  private static final char[] FIGURE_BEGIN_A = { '\\', 'b', 'e', 'g', 'i',
      'n', '{', 'f', 'i', 'g', 'u', 'r', 'e', '}', '[', 't', 'b', ']' };
  /** end figure a */
  private static final char[] FIGURE_END_A = { '\\', 'e', 'n', 'd', '{',
      'f', 'i', 'g', 'u', 'r', 'e', '}' };
  /** begin figure b */
  private static final char[] FIGURE_BEGIN_B = { '\\', 'b', 'e', 'g', 'i',
      'n', '{', 'f', 'i', 'g', 'u', 'r', 'e', '*', '}', '[', 't', 'b', ']' };
  /** end figure b */
  private static final char[] FIGURE_END_B = { '\\', 'e', 'n', 'd', '{',
      'f', 'i', 'g', 'u', 'r', 'e', '*', '}' };
  /** begin figure c */
  private static final char[] CENTER_BEGIN = { '\\', 'b', 'e', 'g', 'i',
      'n', '{', 'c', 'e', 'n', 't', 'e', 'r', '}' };
  /** end figure c */
  private static final char[] CENTER_END = { '\\', 'e', 'n', 'd', '{',
      'c', 'e', 'n', 't', 'e', 'r', '}' };

  /** the caption */
  private static final char[] CAPTION_BEGIN = { '\\', 'c', 'a', 'p', 't',
      'i', 'o', 'n', '{' };

  /** the length of the figure caption end */
  private static final int CAPTION_END_LEN = 1;

  /** the first part of the include graphics call */
  private static final char[] INCLUDE_GRAPHICS_1 = { '\\', 'i', 'n', 'c',
      'l', 'u', 'd', 'e', 'g', 'r', 'a', 'p', 'h', 'i', 'c', 's', '[',
      'w', 'i', 'd', 't', 'h', '=' };
  /** the second part of the include graphics call */
  private static final char[] INCLUDE_GRAPHICS_2 = { 'p', 't', ',', 'h',
      'e', 'i', 'g', 'h', 't', '=' };
  /** the third part of the include graphics call */
  private static final char[] INCLUDE_GRAPHICS_3 = { 'p', 't', ']', '{' };

  /** the last part of the include graphics call */
  private static final int INCLUDE_GRAPHICS_END_LEN = 1;

  /** the continued figure beginning char array */
  private static final char[] CONTINUED_FIGURE_BEGIN = (" Continued from " + ELabelType.FIGURE.single() + //$NON-NLS-1$
  "~\\ref{").toCharArray(); //$NON-NLS-1$

  /** the continued table beginning char array */
  private static final char[] CONTINUED_TABLE_BEGIN = (" Continued from " + ELabelType.TABLE.single() + //$NON-NLS-1$
  "~\\ref{").toCharArray(); //$NON-NLS-1$

  /** the continued end char array */
  private static final char[] CONTINUED_END = { '}', '.', '}' };

  /** the hfill characters */
  private static final char[] HFILL = { '\\', 's', 't', 'r', 'u', 't',
      '\\', 'h', 'f', 'i', 'l', 'l', '\\', 's', 't', 'r', 'u', 't' };

  /** the sub-float */
  private static final char[] SUBFLOAT = { '\\', 's', 'u', 'b', 'f', 'l',
      'o', 'a', 't', '[' };

  /** the begin itemize */
  private static final char[] ITEMIZE_BEGIN = { '\\', 'b', 'e', 'g', 'i',
      'n', '{', 'i', 't', 'e', 'm', 'i', 'z', 'e', '}' };
  /** the end itemize */
  private static final char[] ITEMIZE_END = { '\\', 'e', 'n', 'd', '{',
      'i', 't', 'e', 'm', 'i', 'z', 'e', '}' };
  /** the print an item */
  private static final char[] ITEM = { '\\', 'i', 't', 'e', 'm', ' ' };
  /** the begin enumerate */
  private static final char[] ENUMERATE_BEGIN = { '\\', 'b', 'e', 'g',
      'i', 'n', '{', 'e', 'n', 'u', 'm', 'e', 'r', 'a', 't', 'e', '}' };
  /** the end enumerate */
  private static final char[] ENUMERATE_END = { '\\', 'e', 'n', 'd', '{',
      'e', 'n', 'u', 'm', 'e', 'r', 'a', 't', 'e', '}' };

  /** the float barrier */
  private static final char[] FLOAT_BARRIER = { '\\', 'F', 'l', 'o', 'a',
      't', 'B', 'a', 'r', 'r', 'i', 'e', 'r' };

  /** the float barrier */
  private static final char[] EVEN_ROW_COLOR = { '\\', 'r', 'o', 'w', 'c',
      'o', 'l', 'o', 'r', '{', 'e', 'v', 'e', 'n', 'R', 'o', 'w', 'C',
      'o', 'l', 'o', 'r', '}' };

  /** the multi-col */
  private static final char[] MULTI_COLUMN = { '\\', 'm', 'u', 'l', 't',
      'i', 'c', 'o', 'l', 'u', 'm', 'n', '{' };
  /** the multi-row */
  private static final char[] MULTI_ROW = { '\\', 'm', 'u', 'l', 't', 'i',
      'r', 'o', 'w', '{' };

  /** the text bf */
  private static final char[] TEXT_BF_BEGIN = { '\\', 't', 'e', 'x', 't',
      'b', 'f', '{' };

  /** the text bf */
  private static final int TEXT_BF_END = 1;

  /** the hline */
  private static final char[] HLINE = { '\\', '\\', '\\', 'h', 'l', 'i',
      'n', 'e' };

  /** the citation begin */
  private static final char[][] CITATION_BEGIN = {
      { '~', '{', '\\', 'c', 'i', 't', 'e', 'p', '{' },//
      { '{', '\\', 'c', 'i', 't', 'e', 'p', '{' },//
      { '~', '{', '\\', 'c', 'i', 't', 'e', 't', '{' },//
      { '{', '\\', 'C', 'i', 't', 'e', 't', '{' },//
  };
  /** the citation end */
  private static final int CITATION_END = 2;

  /** begin a hline */
  private static final int HLINE_BEGIN = 2;
  /** end a hline */
  private static final int HLINE_END = LaTeXDocument.HLINE.length;
  /** begin a hline */
  private static final int LINEBREAK_HLINE_BEGIN = 0;
  /** end a hline */
  private static final int LINEBREAK_HLINE_END = LaTeXDocument.HLINE.length;
  /** line break */
  private static final char[] LINEBREAK = LaTeXDocument.HLINE;
  /** begin a linebreak */
  private static final int LINEBREAK_BEGIN = 0;
  /** end a linebreak */
  private static final int LINEBREAK_END = 2;

  /** begin the tabular */
  private static final char[] TABULAR_BEGIN = { '\\', 'b', 'e', 'g', 'i',
      'n', '{', 't', 'a', 'b', 'u', 'l', 'a', 'r', '}', '{' };
  /** end the tabular */
  private static final char[] TABULAR_END = { '\\', 'e', 'n', 'd', '{',
      't', 'a', 'b', 'u', 'l', 'a', 'r', '}' };

  /** begin the table a */
  private static final char[] TABLE_A_BEGIN = { '\\', 'b', 'e', 'g', 'i',
      'n', '{', 't', 'a', 'b', 'l', 'e', '}', '[', 't', 'b', ']' };
  /** begin the table b */
  private static final char[] TABLE_A_END = { '\\', 'e', 'n', 'd', '{',
      't', 'a', 'b', 'l', 'e', '}' };
  /** end the table a */
  private static final char[] TABLE_B_BEGIN = { '\\', 'b', 'e', 'g', 'i',
      'n', '{', 't', 'a', 'b', 'l', 'e', '*', '}', '[', 't', 'b', ']' };
  /** end the table b */
  private static final char[] TABLE_B_END = { '\\', 'e', 'n', 'd', '{',
      't', 'a', 'b', 'l', 'e', '*', '}' };

  /** begin small */
  private static final char[] SMALL_BEGIN = { '\\', 'b', 'e', 'g', 'i',
      'n', '{', 's', 'm', 'a', 'l', 'l', '}' };
  /** end small */
  private static final char[] SMALL_END = { '\\', 'e', 'n', 'd', '{', 's',
      'm', 'a', 'l', 'l', '}' };

  /** begin text superscript */
  private static final char[] TEXT_SUPERSCRIPT_BEGIN = { '{', '\\', 't',
      'e', 'x', 't', 's', 'u', 'p', 'e', 'r', 's', 'c', 'r', 'i', 'p',
      't', '{' };
  /** end a ext superscript */
  private static final int TEXT_SUPERSCRIPT_END = 2;
  /** begin text subscript */
  private static final char[] TEXT_SUBSCRIPT_BEGIN = { '{', '\\', 't',
      'e', 'x', 't', 's', 'u', 'b', 's', 'c', 'r', 'i', 'p', 't', '{' };
  /** end a text subscript */
  private static final int TEXT_SUBSCRIPT_END = 2;
  /** begin emph */
  private static final char[] EMPH_BEGIN = { '{', '\\', 'e', 'm', 'p',
      'h', '{' };
  /** end emph */
  private static final int EMPH_END = 2;

  /** begin equation */
  private static final char[] EQUATION_BEGIN = { '\\', 'b', 'e', 'g', 'i',
      'n', '{', 'e', 'q', 'u', 'a', 't', 'i', 'o', 'n', '}' };
  /** end equation */
  private static final char[] EQUATION_END = { '\\', 'e', 'n', 'd', '{',
      'e', 'q', 'u', 'a', 't', 'i', 'o', 'n', '}' };
  /** begin ensuremath */
  private static final char[] ENSUREMATH_BEGIN = { '{', '\\', 'e', 'n',
      's', 'u', 'r', 'e', 'm', 'a', 't', 'h', '{' };
  /** end ensuremath */
  private static final int ENSUREMATH_END = 2;

  /** begin text normal */
  private static final char[] MATHSUPERSCRIPT_BEGIN = { '^', '{' };
  /** end math superscript end */
  private static final int MATHSUPERSCRIPT_END = 1;
  /** begin math subscript */
  private static final char[] MATHSUBSCRIPT_BEGIN = { '_', '{' };
  /** end math subscript end */
  private static final int MATHSUBSCRIPT_END = 1;

  /** begin gdef begin */
  private static final char[] GDEF_BEGIN = { '\\', 'g', 'd', 'e', 'f',
      '\\' };

  /** begin the bibliography style */
  private static final char[] BIBLIOGRAPHY_STYLE_BEGIN = { '\\', 'b', 'i',
      'b', 'l', 'i', 'o', 'g', 'r', 'a', 'p', 'h', 'y', 's', 't', 'y',
      'l', 'e', '{' };
  /** end the bibliography style */
  private static final int BIBLIOGRAPHY_STYLE_END = 1;
  /** begin the bibliography */
  private static final char[] BIBLIOGRAPHY_BEGIN = { '\\', 'b', 'i', 'b',
      'l', 'i', 'o', 'g', 'r', 'a', 'p', 'h', 'y', '{' };
  /** end the bibliography */
  private static final int BIBLIOGRAPHY_END = 1;

  /** the output stream */
  private final OutputStream m_os;

  /** the output stream writer */
  private final OutputStreamWriter m_osw;

  /** the file output writer */
  private final BufferedWriter m_writer;

  /** the character output to use */
  WriterCharOutput m_out;

  /** are we inside the document body? or did we print the title yet? */
  private int m_headState;

  /** the writer to catch figure and table captions */
  private CharArrayWriter m_captionWriter;
  /** the writer to catch the table header */
  private CharArrayWriter m_headerWriter;
  /** the writer to catch the table footer */
  private CharArrayWriter m_footerWriter;

  /** the temporary output stream */
  private WriterCharOutput m_tempOut;
  /** the last label */
  private SingleLabel m_last;

  /** the float counter */
  private int m_floatCounter;

  /** the horizontal line counter */
  private int m_hlineCounter;

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
  protected LaTeXDocument(final LaTeXContext context, final File file)
      throws IOException {
    super(context);

    this.m_os = new FileOutputStream(file);
    this.m_osw = new OutputStreamWriter(this.m_os,//
        EEncoding.ISO_8859_1.getJavaName());
    this.m_writer = new BufferedWriter(this.m_osw);

    this.m_out = new WriterCharOutput(this.m_writer);
    this.m_headState = LaTeXDocument.HEAD_STATE_START;
  }

  /** {@inheritDoc} */
  @Override
  public LaTeXContext getOwner() {
    return ((LaTeXContext) (super.getOwner()));
  }

  /** {@inheritDoc} */
  @Override
  protected void writeChar(final int data) throws IOException {
    LaTeXCharTransformer.INSTANCE.transform(//
        new CharCharInput(data), this.m_out, 0, 1);
  }

  /** {@inheritDoc} */
  @Override
  protected void write(final String data) throws IOException {
    LaTeXCharTransformer.INSTANCE.transform(//
        new StringCharInput(data), this.m_out, 0, data.length());
  }

  /** {@inheritDoc} */
  @Override
  protected void writeHyphenated(final String data) throws IOException {
    LaTeXCharTransformer.INSTANCE.transformHyphenated(//
        new StringCharInput(data), this.m_out, 0, data.length());
  }

  /**
   * end a line
   * 
   * @throws IOException
   *           if io fails
   */
  protected final void endLine() throws IOException {
    this.m_out.write(LaTeXDocument.LINE_END, LaTeXDocument.LINE_END_BEGIN,
        LaTeXDocument.LINE_END_END);
  }

  /** {@inheritDoc} */
  @Override
  protected void writeInfinity(final boolean positive) throws IOException {
    if (positive) {
      this.m_out.write(LaTeXDocument.POSITIVE_INFINITY, 0,
          LaTeXDocument.POSITIVE_INFINITY.length);
    } else {
      this.m_out.write(LaTeXDocument.NEGATIVE_INFINITY, 0,
          LaTeXDocument.NEGATIVE_INFINITY.length);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void writeNaN() throws IOException {
    this.m_out.write(LaTeXDocument.NAN, 0, LaTeXDocument.NAN.length);
  }

  /** {@inheritDoc} */
  @Override
  protected void writeNumber(final String number) throws IOException {
    this.m_out.write(LaTeXDocument.NUMPRINT_BEGIN, 0,
        LaTeXDocument.NUMPRINT_BEGIN.length);
    this.m_out.write(number, 0, number.length());
    this.m_out.write(LaTeXDocument.COMMAND_END, 0,
        LaTeXDocument.NUMPRINT_END_LEN);
  }

  /** {@inheritDoc} */
  @Override
  protected void writeFormattedNumber(final String number)
      throws IOException {
    this.m_out.write(LaTeXDocument.TEXTNORMAL_BEGIN, 0,
        LaTeXDocument.TEXTNORMAL_BEGIN.length);
    this.m_out.write(number, 0, number.length());
    this.m_out.write(LaTeXDocument.COMMAND_END, 0,
        LaTeXDocument.TEXTNORMAL_END);
  }

  /** {@inheritDoc} */
  @Override
  protected void writeNoneBreakingSpace() throws IOException {
    this.m_out.write('~');
  }

  /** {@inheritDoc} */
  @Override
  protected void writeLinebreak() throws IOException {
    this.m_out.write(LaTeXDocument.NEWLINE, LaTeXDocument.NEWLINE_BEGIN,
        LaTeXDocument.NEWLINE_END);
  }

  /** {@inheritDoc} */
  @Override
  protected void titleBegin(final Title h) throws IOException,
      IllegalStateException {
    super.titleBegin(h);
    this.__ensureInDocument();
    this.m_out.write(LaTeXDocument.LINE_END, LaTeXDocument.LINE_END_BEGIN,
        LaTeXDocument.LINE_END_END);
    this.m_out.write(LaTeXDocument.TITLE_BEGIN, 0,
        LaTeXDocument.TITLE_BEGIN.length);
  }

  /** {@inheritDoc} */
  @Override
  protected void titleEnd(final Title h) throws IOException,
      IllegalStateException {
    try {
      this.m_out.write(LaTeXDocument.COMMAND_END, 0,
          LaTeXDocument.TITLE_END_LEN);
      this.m_out.write(LaTeXDocument.LINE_END,
          LaTeXDocument.LINE_END_BEGIN, LaTeXDocument.LINE_END_END);
    } finally {
      super.titleEnd(h);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void doWriteSingleLabel(final SingleLabel label)
      throws IOException {
    final String l;

    this.m_out.write(LaTeXDocument.LINE_END, LaTeXDocument.LINE_END_BEGIN,
        LaTeXDocument.LINE_END_END);
    this.m_out.write(LaTeXDocument.LABEL_BEGIN, 0,
        LaTeXDocument.LABEL_BEGIN.length);
    l = label.getKey();
    this.m_out.write(l, 0, l.length());
    this.m_out.write(LaTeXDocument.COMMAND_END, 0,
        LaTeXDocument.LABEL_END_LEN);
    this.m_out.write(LaTeXDocument.LINE_END, LaTeXDocument.LINE_END_BEGIN,
        LaTeXDocument.LINE_END_END);
  }

  /** {@inheritDoc} */
  @Override
  protected void writeSequence(final Sequence sequence,
      final ESequenceType type,
      final boolean connectLastElementWithNonBreakableSpace)
      throws IOException {
    final int idx;
    final int len, stop;
    char[] chars;
    int i;

    idx = type.ordinal();
    len = sequence.length();
    if (len < 1) {
      throw new IllegalArgumentException(//
          "Sequence must have at least one element!"); //$NON-NLS-1$
    }

    if (len == 1) {
      chars = LaTeXDocument.SEQUENCE_SINGLE[idx];
      if (chars.length > 0) {
        this.m_out.write(chars, 0, chars.length);
      }
      sequence.write(0);
      return;
    }

    chars = LaTeXDocument.SEQUENCE_BEGIN[idx];
    stop = ((type == ESequenceType.FROM_TO) ? 1 : (len - 1));

    for (i = 0; i < stop; i++) {
      if (chars.length > 0) {
        this.m_out.write(chars, 0, chars.length);
      }
      sequence.write(i);
      chars = LaTeXDocument.SEQUENCE_SMALL_SEP;
    }

    if ((stop > 1) || (type == ESequenceType.COMMA)) {
      if (chars.length > 0) {
        this.m_out.write(chars, 0, chars.length);
      }
    } else {
      this.m_out.write(' ');
    }

    chars = LaTeXDocument.SEQUENCE_BIG_SEP[idx];
    if (chars.length > 0) {
      this.m_out.write(chars, 0, chars.length);
    }

    this.m_out.write(connectLastElementWithNonBreakableSpace ? '~' : ' ');
    sequence.write(len - 1);
  }

  /** {@inheritDoc} */
  @Override
  protected void writeReference(final ESequenceType type,
      final SingleLabel[] labels) throws IOException {
    this.writeSequence(new _RefSequence(labels, this,
        (type == ESequenceType.FROM_TO)), type, true);
  }

  /**
   * write a single reference
   * 
   * @param label
   *          the reference
   * @throws IOException
   *           if io fails
   */
  protected void writeSingleRef(final SingleLabel label)
      throws IOException {
    final String s;

    this.m_out.write(LaTeXDocument.REF_BEGIN, 0,
        LaTeXDocument.REF_BEGIN.length);
    s = label.getKey();
    this.m_out.write(s, 0, s.length());
    this.m_out.write(LaTeXDocument.COMMAND_END, 0,
        LaTeXDocument.REF_END_LEN);
  }

  /** {@inheritDoc} */
  @Override
  protected void doClose() throws IOException {
    try {
      try {
        try {
          try {
            this.endLine();
            this.endLine();
            this.m_writer.write("\\endinput"); //$NON-NLS-1$
            this.endLine();
          } finally {
            this.m_writer.close();
          }
        } finally {
          this.m_osw.close();
        }
      } finally {
        this.m_os.close();
      }
    } finally {
      super.doClose();
    }
  }

  /**
   * print a parameterized header command
   * 
   * @param cmd
   *          the command
   * @param param
   *          the parameters
   * @param args
   *          the args
   * @throws IOException
   *           if io fails
   */
  private final void __headereparamcmd(final char[] cmd,
      final String param, final String... args) throws IOException {
    char last;

    this.m_out.write(cmd, 0, cmd.length);
    if (args != null) {
      last = '[';
      for (String s : args) {
        s = TextUtils.prepare(s);
        if (s != null) {
          this.m_out.write(last);
          this.m_out.write(s, 0, s.length());
          last = ',';
        }
      }
      if (last != '[') {
        this.m_out.write(']');
      }
    }
    this.m_out.write('{');
    this.m_out.write(param, 0, param.length());
    this.m_out.write('}');
    this.m_out.write(LaTeXDocument.LINE_END, LaTeXDocument.LINE_END_BEGIN,
        LaTeXDocument.LINE_END_END);
  }

  /**
   * write the document class
   * 
   * @param clazz
   *          the document class
   * @param args
   *          the arguments
   * @throws IOException
   *           if io fails
   */
  protected void writeDocumentClass(final String clazz,
      final String... args) throws IOException {
    this.__headereparamcmd(LaTeXDocument.DOCUMENT_CLASS, clazz, args);
  }

  /**
   * put the document class
   * 
   * @throws IOException
   *           if io fails
   */
  protected void putDocumentClass() throws IOException {
    this.writeDocumentClass("article", //$NON-NLS-1$
        "11pt", "twoside", //$NON-NLS-1$ //$NON-NLS-2$ 
        "a4paper", "notitlepage"); //$NON-NLS-1$ //$NON-NLS-2$ 
  }

  /**
   * require a package
   * 
   * @param pack
   *          the package name
   * @param args
   *          the package arguments
   * @throws IOException
   *           if io fails
   */
  protected void writeRequirePackage(final String pack,
      final String... args) throws IOException {
    this.__headereparamcmd(LaTeXDocument.REQUIRE_PACKAGE, pack, args);
  }

  /**
   * put the packages
   * 
   * @throws IOException
   *           if io fails
   */
  protected void putPackages() throws IOException {
    String s;

    this.writeRequirePackage("amssymb"); //$NON-NLS-1$
    this.writeRequirePackage("amsmath"); //$NON-NLS-1$    
    this.writeRequirePackage("fixltx2e"); //$NON-NLS-1$
    this.m_out.write(LaTeXDocument.LINE_END, LaTeXDocument.LINE_END_BEGIN,
        LaTeXDocument.LINE_END_END);

    this.writeRequirePackage("color"); //$NON-NLS-1$
    this.writeRequirePackage("xcolor"); //$NON-NLS-1$
    this.writeRequirePackage("caption3"); //$NON-NLS-1$
    this.writeRequirePackage("colortbl"); //$NON-NLS-1$
    s = ("\\definecolor{evenRowColor}{gray}{0.9}"); //$NON-NLS-1$
    this.m_out.write(s, 0, s.length());
    this.m_out.write(LaTeXDocument.LINE_END, LaTeXDocument.LINE_END_BEGIN,
        LaTeXDocument.LINE_END_END);
    this.m_out.write(LaTeXDocument.LINE_END, LaTeXDocument.LINE_END_BEGIN,
        LaTeXDocument.LINE_END_END);

    this.writeRequirePackage("multicol"); //$NON-NLS-1$
    this.writeRequirePackage("multirow"); //$NON-NLS-1$
    this.m_out.write(LaTeXDocument.LINE_END, LaTeXDocument.LINE_END_BEGIN,
        LaTeXDocument.LINE_END_END);

    this.writeRequirePackage("natbib", "square", //$NON-NLS-1$ //$NON-NLS-2$
        "numbers", "comma",//$NON-NLS-1$ //$NON-NLS-2$
        "sort&compress"); //$NON-NLS-1$

    s = ("\\bibpunct{[}{]}{,}{n}{, }{,}");//$NON-NLS-1$
    this.m_out.write(s, 0, s.length());
    this.m_out.write(LaTeXDocument.LINE_END, LaTeXDocument.LINE_END_BEGIN,
        LaTeXDocument.LINE_END_END);
    this.m_out.write(LaTeXDocument.LINE_END, LaTeXDocument.LINE_END_BEGIN,
        LaTeXDocument.LINE_END_END);

    this.writeRequirePackage("numprint"); //$NON-NLS-1$
    s = ("\\npfourdigitnosep");//$NON-NLS-1$
    this.m_out.write(s, 0, s.length());
    s = ("\\npdecimalsign{.}");//$NON-NLS-1$
    this.m_out.write(s, 0, s.length());
    this.m_out.write(LaTeXDocument.LINE_END, LaTeXDocument.LINE_END_BEGIN,
        LaTeXDocument.LINE_END_END);
    this.m_out.write(LaTeXDocument.LINE_END, LaTeXDocument.LINE_END_BEGIN,
        LaTeXDocument.LINE_END_END);

    s = ("\\npaddmissingzero");//$NON-NLS-1$
    this.m_out.write(s, 0, s.length());
    this.m_out.write(LaTeXDocument.LINE_END, LaTeXDocument.LINE_END_BEGIN,
        LaTeXDocument.LINE_END_END);
    s = ("\\npthousandsep{\\hspace*{0.17em}}");//$NON-NLS-1$
    this.m_out.write(s, 0, s.length());
    this.m_out.write(LaTeXDocument.LINE_END, LaTeXDocument.LINE_END_BEGIN,
        LaTeXDocument.LINE_END_END);
    s = ("\\npproductsign{\\cdot}");//$NON-NLS-1$
    this.m_out.write(s, 0, s.length());
    this.m_out.write(LaTeXDocument.LINE_END, LaTeXDocument.LINE_END_BEGIN,
        LaTeXDocument.LINE_END_END);
    this.m_out.write(LaTeXDocument.LINE_END, LaTeXDocument.LINE_END_BEGIN,
        LaTeXDocument.LINE_END_END);

    this.writeRequirePackage("graphicx"); //$NON-NLS-1$
    this.writeRequirePackage("subfig", "caption=false"); //$NON-NLS-1$//$NON-NLS-2$
    s = ("\\makeatletter");//$NON-NLS-1$
    this.m_out.write(s, 0, s.length());
    this.m_out.write(LaTeXDocument.LINE_END, LaTeXDocument.LINE_END_BEGIN,
        LaTeXDocument.LINE_END_END);
    s = ("\\gdef\\thesubfigure{\\arabic{subfigure}}");//$NON-NLS-1$
    this.m_out.write(s, 0, s.length());
    this.m_out.write(LaTeXDocument.LINE_END, LaTeXDocument.LINE_END_BEGIN,
        LaTeXDocument.LINE_END_END);
    s = ("\\gdef\\p@subfigure{\\thefigure.}");//$NON-NLS-1$
    this.m_out.write(s, 0, s.length());
    this.m_out.write(LaTeXDocument.LINE_END, LaTeXDocument.LINE_END_BEGIN,
        LaTeXDocument.LINE_END_END);
    s = ("\\makeatother");//$NON-NLS-1$
    this.m_out.write(s, 0, s.length());
    this.m_out.write(LaTeXDocument.LINE_END, LaTeXDocument.LINE_END_BEGIN,
        LaTeXDocument.LINE_END_END);
    this.m_out.write(LaTeXDocument.LINE_END, LaTeXDocument.LINE_END_BEGIN,
        LaTeXDocument.LINE_END_END);

    this.writeRequirePackage("placeins"); //$NON-NLS-1$
    this.writeRequirePackage("morefloats"); //$NON-NLS-1$
    this.m_out.write(LaTeXDocument.LINE_END, LaTeXDocument.LINE_END_BEGIN,
        LaTeXDocument.LINE_END_END);

    this.writeRequirePackage("hyperref"); //$NON-NLS-1$
    this.writeRequirePackage("breakurl"); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  protected void headerBegin(final Header h) throws IOException,
      IllegalStateException {
    final CharOutput out;

    super.headerBegin(h);

    out = this.m_out;
    this.putDocumentClass();
    out.write(LaTeXDocument.LINE_END, LaTeXDocument.LINE_END_BEGIN,
        LaTeXDocument.LINE_END_END);

    this.putPackages();
  }

  // /** {@inheritDoc} */
  // @Override
  // protected void headerEnd(final Header h) throws IOException,
  // IllegalStateException {
  // super.headerEnd(h);
  // }

  /**
   * ensure that we are in the document
   * 
   * @throws IOException
   *           if io fails
   */
  private final void __ensureInDocument() throws IOException {
    if (this.m_headState >= LaTeXDocument.HEAD_STATE_IN_DOCUMENT) {
      return;
    }
    this.m_headState = LaTeXDocument.HEAD_STATE_IN_DOCUMENT;
    this.m_out.write(LaTeXDocument.LINE_END, LaTeXDocument.LINE_END_BEGIN,
        LaTeXDocument.LINE_END_END);
    this.m_out.write(LaTeXDocument.DOCUMENT_BEGIN, 0,
        LaTeXDocument.DOCUMENT_BEGIN.length);
    this.m_out.write(LaTeXDocument.LINE_END, LaTeXDocument.LINE_END_BEGIN,
        LaTeXDocument.LINE_END_END);
    this.m_out.write(LaTeXDocument.LINE_END, LaTeXDocument.LINE_END_BEGIN,
        LaTeXDocument.LINE_END_END);
  }

  /**
   * ensure that we are have the title
   * 
   * @throws IOException
   *           if io fails
   */
  private final void __ensureMakeTitle() throws IOException {
    final String s;

    this.__ensureInDocument();
    if (this.m_headState >= LaTeXDocument.HEAD_STATE_HAS_MAKE_TITLE) {
      return;
    }
    this.m_headState = LaTeXDocument.HEAD_STATE_HAS_MAKE_TITLE;

    this.m_out.write(LaTeXDocument.LINE_END, LaTeXDocument.LINE_END_BEGIN,
        LaTeXDocument.LINE_END_END);

    s = this.getDateForHeader();
    if (s != null) {
      this.m_out.write(LaTeXDocument.DATE_BEGIN, 0,
          LaTeXDocument.DATE_BEGIN.length);
      this.m_out.write(s, 0, s.length());
      this.m_out.write(LaTeXDocument.COMMAND_END, 0,
          LaTeXDocument.DATE_END_LEN);
      this.m_out.write(LaTeXDocument.LINE_END,
          LaTeXDocument.LINE_END_BEGIN, LaTeXDocument.LINE_END_END);
    }

    this.m_out.write(LaTeXDocument.LINE_END, LaTeXDocument.LINE_END_BEGIN,
        LaTeXDocument.LINE_END_END);
    this.m_out.write(LaTeXDocument.MAKE_TITLE_BEGIN, 0,
        LaTeXDocument.MAKE_TITLE_BEGIN.length);
    this.m_out.write(LaTeXDocument.LINE_END, LaTeXDocument.LINE_END_BEGIN,
        LaTeXDocument.LINE_END_END);
    this.m_out.write(LaTeXDocument.LINE_END, LaTeXDocument.LINE_END_BEGIN,
        LaTeXDocument.LINE_END_END);
  }

  /**
   * Get the date string for printing in the document header, or
   * {@code null} if no date should be printed
   * 
   * @return the date string
   */
  protected String getDateForHeader() {
    return (new SimpleDateFormat("yyyy-MM-dd")).format(new Date()); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  protected void bodyBegin(final Body h) throws IOException,
      IllegalStateException {

    super.bodyBegin(h);
    this.__ensureMakeTitle();
  }

  /** {@inheritDoc} */
  @Override
  protected void bodyEnd(final Body h) throws IOException,
      IllegalStateException {
    try {
      this.m_out.write(LaTeXDocument.LINE_END,
          LaTeXDocument.LINE_END_BEGIN, LaTeXDocument.LINE_END_END);
      this.m_out.write(LaTeXDocument.LINE_END,
          LaTeXDocument.LINE_END_BEGIN, LaTeXDocument.LINE_END_END);
      this.makeBibliography();
      this.m_out.write(LaTeXDocument.LINE_END,
          LaTeXDocument.LINE_END_BEGIN, LaTeXDocument.LINE_END_END);
      this.m_out.write(LaTeXDocument.DOCUMENT_END, 0,
          LaTeXDocument.DOCUMENT_END.length);
    } finally {
      super.bodyEnd(h);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void authorsBegin(final Authors h) throws IOException,
      IllegalStateException {
    super.authorsBegin(h);
    this.__ensureInDocument();
    this.m_out.write(LaTeXDocument.LINE_END, LaTeXDocument.LINE_END_BEGIN,
        LaTeXDocument.LINE_END_END);
    this.m_out.write(LaTeXDocument.AUTHORS_BEGIN, 0,
        LaTeXDocument.AUTHORS_BEGIN.length);
    this.writeSequence(new _AuthorsSequence(h.getAuthors(), this),
        ESequenceType.AND, false);
  }

  /** {@inheritDoc} */
  @Override
  protected void authorsEnd(final Authors h) throws IOException,
      IllegalStateException {
    try {
      this.m_out.write(LaTeXDocument.COMMAND_END, 0,
          LaTeXDocument.AUTHORS_END_LEN);
      this.m_out.write(LaTeXDocument.LINE_END,
          LaTeXDocument.LINE_END_BEGIN, LaTeXDocument.LINE_END_END);
    } finally {
      super.authorsEnd(h);
    }
  }

  /**
   * Is the summary a command or an environment?
   * 
   * @return {@code true} if the summary is embedded in a command,
   *         {@code false} if it is embedded into an environment
   */
  protected boolean summaryIsCommand() {
    return false;
  }

  /** {@inheritDoc} */
  @Override
  protected void summaryBegin(final Summary h) throws IOException,
      IllegalStateException {
    super.summaryBegin(h);

    this.m_out.write(LaTeXDocument.LINE_END, LaTeXDocument.LINE_END_BEGIN,
        LaTeXDocument.LINE_END_END);

    if (this.summaryIsCommand()) {
      this.__ensureInDocument();
      this.m_out.write(LaTeXDocument.ABSTRACT_BEGIN_B, 0,
          LaTeXDocument.ABSTRACT_BEGIN_B.length);
    } else {
      this.__ensureMakeTitle();
      this.m_out.write(LaTeXDocument.ABSTRACT_BEGIN_A, 0,
          LaTeXDocument.ABSTRACT_BEGIN_A.length);
    }

    this.m_out.write(LaTeXDocument.LINE_END, LaTeXDocument.LINE_END_BEGIN,
        LaTeXDocument.LINE_END_END);
  }

  /** {@inheritDoc} */
  @Override
  protected void summaryEnd(final Summary h) throws IOException,
      IllegalStateException {

    try {

      this.m_out.write(LaTeXDocument.LINE_END,
          LaTeXDocument.LINE_END_BEGIN, LaTeXDocument.LINE_END_END);

      if (this.summaryIsCommand()) {
        this.m_out.write(LaTeXDocument.COMMAND_END, 0,
            LaTeXDocument.ABSTRACT_END_B);
      } else {
        this.m_out.write(LaTeXDocument.ABSTRACT_END_A, 0,
            LaTeXDocument.ABSTRACT_END_A.length);
      }

      this.m_out.write(LaTeXDocument.LINE_END,
          LaTeXDocument.LINE_END_BEGIN, LaTeXDocument.LINE_END_END);
      this.m_out.write(LaTeXDocument.LINE_END,
          LaTeXDocument.LINE_END_BEGIN, LaTeXDocument.LINE_END_END);

    } finally {
      super.summaryEnd(h);
    }

  }

  /**
   * get the number to be added to the section depth in order to obtain the
   * right command
   * 
   * @return the number to be added to the section depth in order to obtain
   *         the right command
   */
  protected int getSectionOffset() {
    return (1);
  }

  /** {@inheritDoc} */
  @Override
  protected void sectionEnd(final Section h) throws IOException,
      IllegalStateException {
    try {
      if ((this.m_floatCounter > 0) && (h.getDepth() < 3)) {
        this.m_floatCounter = 0;
        this.m_out.write(LaTeXDocument.LINE_END,
            LaTeXDocument.LINE_END_BEGIN, LaTeXDocument.LINE_END_END);
        this.m_out.write(LaTeXDocument.FLOAT_BARRIER, 0,
            LaTeXDocument.FLOAT_BARRIER.length);
        this.m_out.write(LaTeXDocument.LINE_END,
            LaTeXDocument.LINE_END_BEGIN, LaTeXDocument.LINE_END_END);
      }
    } finally {
      super.sectionEnd(h);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void sectionTitleBegin(final SectionTitle h)
      throws IOException, IllegalStateException {
    final int[] ids;
    char[] ch;
    String s;
    int i, d;

    super.sectionTitleBegin(h);

    this.m_out.write(LaTeXDocument.LINE_END, LaTeXDocument.LINE_END_BEGIN,
        LaTeXDocument.LINE_END_END);
    this.m_out.write(LaTeXDocument.LINE_END, LaTeXDocument.LINE_END_BEGIN,
        LaTeXDocument.LINE_END_END);

    d = (h.getOwner().getDepth() + this.getSectionOffset());
    i = LaTeXDocument.SECTION_BEGIN_A.length;
    if (d < i) {
      ch = LaTeXDocument.SECTION_BEGIN_A[d];
    } else {
      ch = LaTeXDocument.SECTION_BEGIN_B;
      ids = h.getOwner().getAllIndexes();
      for (i = (ids.length - (d - i - 1)); (i < ids.length); i++) {
        s = String.valueOf(ids[i]);
        this.m_out.write(s, 0, s.length());
        this.m_out.write('.');
      }
      this.m_out.write('~');
    }
    this.m_out.write(ch, 0, ch.length);
  }

  /** {@inheritDoc} */
  @Override
  protected void sectionTitleEnd(final SectionTitle h) throws IOException,
      IllegalStateException {
    Section s;
    SingleLabel l;
    int d;

    try {
      s = h.getOwner();

      d = (s.getDepth() + this.getSectionOffset());
      if (d < LaTeXDocument.SECTION_BEGIN_A.length) {
        this.m_out.write(LaTeXDocument.COMMAND_END, 0,
            LaTeXDocument.SECTION_END_A);
      } else {
        this.m_out.write(LaTeXDocument.SECTION_END_B, 0,
            LaTeXDocument.SECTION_END_B.length);
      }

      l = s.getLabel();
      if (l != null) {
        this.writeSingleLabel(l, null);
      }
      this.m_out.write(LaTeXDocument.LINE_END,
          LaTeXDocument.LINE_END_BEGIN, LaTeXDocument.LINE_END_END);
    } finally {
      super.sectionTitleEnd(h);
    }
  }

  /**
   * Should we use starred environments for multi-figures and tables?
   * 
   * @return {@code true} if we should use starred environments,
   *         {@code false} otherwise
   */
  protected boolean hasStarredEnvironments() {
    return (this.getDimensions().getColumnCount() > 1);
  }

  /** {@inheritDoc} */
  @Override
  protected void figureBegin(final Figure h) throws IOException,
      IllegalStateException {
    super.figureBegin(h);

    this.__beginFloat();

    this.m_out.write(LaTeXDocument.LINE_END, LaTeXDocument.LINE_END_BEGIN,
        LaTeXDocument.LINE_END_END);
    this.m_out.write(LaTeXDocument.LINE_END, LaTeXDocument.LINE_END_BEGIN,
        LaTeXDocument.LINE_END_END);
    if (h.spansColumns() && this.hasStarredEnvironments()) {
      this.m_out.write(LaTeXDocument.FIGURE_BEGIN_B, 0,
          LaTeXDocument.FIGURE_BEGIN_B.length);
    } else {
      this.m_out.write(LaTeXDocument.FIGURE_BEGIN_A, 0,
          LaTeXDocument.FIGURE_BEGIN_A.length);
    }
    this.m_out.write(LaTeXDocument.LINE_END, LaTeXDocument.LINE_END_BEGIN,
        LaTeXDocument.LINE_END_END);
    this.m_out.write(LaTeXDocument.CENTER_BEGIN, 0,
        LaTeXDocument.CENTER_BEGIN.length);
  }

  /** {@inheritDoc} */
  @Override
  protected void figureEnd(final Figure h) throws IOException,
      IllegalStateException {
    final SingleLabel l;

    try {
      this.m_out.write(LaTeXDocument.LINE_END,
          LaTeXDocument.LINE_END_BEGIN, LaTeXDocument.LINE_END_END);
      this.m_out.write(LaTeXDocument.CAPTION_BEGIN, 0,
          LaTeXDocument.CAPTION_BEGIN.length);
      this.m_captionWriter.writeTo(this.m_writer);
      this.m_captionWriter = null;
      this.m_out.write(LaTeXDocument.COMMAND_END, 0,
          LaTeXDocument.CAPTION_END_LEN);
      l = h.getLabel();
      if (l != null) {
        this.writeSingleLabel(l, null);
      } else {
        this.m_out.write(LaTeXDocument.LINE_END,
            LaTeXDocument.LINE_END_BEGIN, LaTeXDocument.LINE_END_END);
      }

      this.m_out.write(LaTeXDocument.LINE_END,
          LaTeXDocument.LINE_END_BEGIN, LaTeXDocument.LINE_END_END);
      this.m_out.write(LaTeXDocument.CENTER_END, 0,
          LaTeXDocument.CENTER_END.length);
      this.m_out.write(LaTeXDocument.LINE_END,
          LaTeXDocument.LINE_END_BEGIN, LaTeXDocument.LINE_END_END);
      if (h.spansColumns() && this.hasStarredEnvironments()) {
        this.m_out.write(LaTeXDocument.FIGURE_END_B, 0,
            LaTeXDocument.FIGURE_END_B.length);
      } else {
        this.m_out.write(LaTeXDocument.FIGURE_END_A, 0,
            LaTeXDocument.FIGURE_END_A.length);
      }
      this.m_out.write(LaTeXDocument.LINE_END,
          LaTeXDocument.LINE_END_BEGIN, LaTeXDocument.LINE_END_END);
      this.m_out.write(LaTeXDocument.LINE_END,
          LaTeXDocument.LINE_END_BEGIN, LaTeXDocument.LINE_END_END);
    } finally {
      super.figureEnd(h);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void figureCaptionBegin(final FigureCaption h)
      throws IOException, IllegalStateException {//
    super.figureCaptionBegin(h);

    this.m_writer.flush();
    this.m_captionWriter = new CharArrayWriter();
    this.m_tempOut = this.m_out;
    this.m_out = new WriterCharOutput(this.m_captionWriter);
  }

  /** {@inheritDoc} */
  @Override
  protected void figureCaptionEnd(final FigureCaption h)
      throws IOException, IllegalStateException {
    try {
      this.m_out = this.m_tempOut;
      this.m_tempOut = null;
      this.m_last = null;
      this.m_captionWriter.flush();
      this.m_writer.flush();

    } finally {
      super.figureCaptionEnd(h);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected FigureBody figureBodyCreate(final Element owner,
      final URI relativeNameBase, final Dimension2D sizeInMM)
      throws IOException, URISyntaxException {
    return new _EPSFigureBody(owner, relativeNameBase, sizeInMM);
  }

  /**
   * check a given figure body
   * 
   * @param h
   *          the figure body
   */
  private static final void checkFigureBody(final FigureBody h) {
    if (!(h instanceof _EPSFigureBody)) {
      throw new IllegalArgumentException(//
          "Figure body is of wrong type."); //$NON-NLS-1$
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void figureBodyBegin(final FigureBody h) throws IOException,
      IllegalStateException {

    super.figureBodyBegin(h);
    LaTeXDocument.checkFigureBody(h);
  }

  /** {@inheritDoc} */
  @Override
  protected void figureBodyEnd(final FigureBody h) throws IOException,
      IllegalStateException {
    final _EPSFigureBody body;
    final Dimension dim;
    String s;

    try {
      LaTeXDocument.checkFigureBody(h);

      body = ((_EPSFigureBody) h);
      this.m_out.write(LaTeXDocument.LINE_END,
          LaTeXDocument.LINE_END_BEGIN, LaTeXDocument.LINE_END_END);
      this.m_out.write(LaTeXDocument.LINE_END,
          LaTeXDocument.LINE_END_BEGIN, LaTeXDocument.LINE_END_END);
      this.m_out.write(LaTeXDocument.INCLUDE_GRAPHICS_1, 0,
          LaTeXDocument.INCLUDE_GRAPHICS_1.length);
      dim = body.m_dimInPT;
      s = String.valueOf(dim.width);
      this.m_out.write(s, 0, s.length());
      this.m_out.write(LaTeXDocument.INCLUDE_GRAPHICS_2, 0,
          LaTeXDocument.INCLUDE_GRAPHICS_2.length);
      s = String.valueOf(dim.height);
      this.m_out.write(s, 0, s.length());
      this.m_out.write(LaTeXDocument.INCLUDE_GRAPHICS_3, 0,
          LaTeXDocument.INCLUDE_GRAPHICS_3.length);
      try {
        s = this.relativizeFile(body.getFile()).toString();
      } catch (final URISyntaxException u) {
        throw new IOException(u);
      }
      this.m_out.write(s, 0, s.length());
      this.m_out.write(LaTeXDocument.COMMAND_END, 0,
          LaTeXDocument.INCLUDE_GRAPHICS_END_LEN);
      this.m_out.write(LaTeXDocument.LINE_END,
          LaTeXDocument.LINE_END_BEGIN, LaTeXDocument.LINE_END_END);

    } finally {
      super.figureBodyEnd(h);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected FigureSeries figureSeries(final Label label,
      final Dimension2D subFigureSizeInMM, final String captionTemplate,
      final String subCaptionTemplate, final boolean colspan)
      throws IOException, IllegalStateException {
    final int x, y, t;
    final double ofs, width;
    final DocumentDimensions d;

    d = this.getDimensions();
    width = (colspan ? d.getPageWidthMM() : d.getColumnWidthMM());

    x = Math.max(1, ((int) (width / subFigureSizeInMM.getWidth())));

    ofs = (width - (0.5d * ((int) (0.5d + Math.ceil(((captionTemplate
        .length() / (((!colspan) && this.hasStarredEnvironments()) ? 30d
        : 60d))))))));

    t = ((int) (0.5d + Math
        .ceil(subCaptionTemplate.length()
            / ((((!colspan) && this.hasStarredEnvironments()) ? 40d : 80d) / x))));

    y = Math.max(1,
        ((int) (ofs / (((t * 0.4d) + subFigureSizeInMM.getHeight())))));

    return this.figureSeries(label, x, y, colspan);
  }

  /** {@inheritDoc} */
  @Override
  protected void figureSeriesEnd(final FigureSeries h) throws IOException,
      IllegalStateException {
    this.m_captionWriter = null;
    super.figureSeriesEnd(h);
  }

  /** {@inheritDoc} */
  @Override
  protected void figureSeriesCaptionBegin(final FigureSeriesCaption h)
      throws IOException, IllegalStateException {
    super.figureSeriesCaptionBegin(h);

    this.m_writer.flush();
    this.m_captionWriter = new CharArrayWriter();
    this.m_tempOut = this.m_out;
    this.m_out = new WriterCharOutput(this.m_captionWriter);
  }

  /** {@inheritDoc} */
  @Override
  protected void figureSeriesCaptionEnd(final FigureSeriesCaption h)
      throws IOException, IllegalStateException {
    try {
      this.m_out = this.m_tempOut;
      this.m_tempOut = null;
      this.m_last = null;
      this.m_captionWriter.flush();
      this.m_writer.flush();
    } finally {
      super.figureSeriesCaptionEnd(h);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void figureSeriesPageBegin(final FigureSeriesPage h)
      throws IOException, IllegalStateException {

    super.figureSeriesPageBegin(h);

    this.__beginFloat();

    this.m_out.write(LaTeXDocument.LINE_END, LaTeXDocument.LINE_END_BEGIN,
        LaTeXDocument.LINE_END_END);
    this.m_out.write(LaTeXDocument.LINE_END, LaTeXDocument.LINE_END_BEGIN,
        LaTeXDocument.LINE_END_END);
    if (h.getOwner().spansColumns() && this.hasStarredEnvironments()) {
      this.m_out.write(LaTeXDocument.FIGURE_BEGIN_B, 0,
          LaTeXDocument.FIGURE_BEGIN_B.length);
    } else {
      this.m_out.write(LaTeXDocument.FIGURE_BEGIN_A, 0,
          LaTeXDocument.FIGURE_BEGIN_A.length);
    }

    this.m_out.write(LaTeXDocument.LINE_END, LaTeXDocument.LINE_END_BEGIN,
        LaTeXDocument.LINE_END_END);
    this.m_out.write(LaTeXDocument.CENTER_BEGIN, 0,
        LaTeXDocument.CENTER_BEGIN.length);
    this.m_out.write(LaTeXDocument.LINE_END, LaTeXDocument.LINE_END_BEGIN,
        LaTeXDocument.LINE_END_END);

  }

  /** {@inheritDoc} */
  @Override
  protected void figureSeriesPageEnd(final FigureSeriesPage h)
      throws IOException, IllegalStateException {
    String s;
    SingleLabel l;

    try {
      this.m_out.write(LaTeXDocument.LINE_END,
          LaTeXDocument.LINE_END_BEGIN, LaTeXDocument.LINE_END_END);
      this.m_out.write(LaTeXDocument.CAPTION_BEGIN, 0,
          LaTeXDocument.CAPTION_BEGIN.length);

      this.m_captionWriter.writeTo(this.m_writer);

      l = this.m_last;
      if (l != null) {
        this.m_out.write(LaTeXDocument.CONTINUED_FIGURE_BEGIN, 0,
            LaTeXDocument.CONTINUED_FIGURE_BEGIN.length);
        s = l.getKey();
        this.m_out.write(s, 0, s.length());
        this.m_out.write(LaTeXDocument.CONTINUED_END, 0,
            LaTeXDocument.CONTINUED_END.length);
      } else {
        this.m_out.write(LaTeXDocument.COMMAND_END, 0,
            LaTeXDocument.CAPTION_END_LEN);
      }

      l = h.getLabel();
      if (l != null) {
        this.writeSingleLabel(l, null);
      } else {
        this.m_out.write(LaTeXDocument.LINE_END,
            LaTeXDocument.LINE_END_BEGIN, LaTeXDocument.LINE_END_END);
      }
      this.m_last = l;

      this.m_out.write(LaTeXDocument.LINE_END,
          LaTeXDocument.LINE_END_BEGIN, LaTeXDocument.LINE_END_END);
      this.m_out.write(LaTeXDocument.CENTER_END, 0,
          LaTeXDocument.CENTER_END.length);
      this.m_out.write(LaTeXDocument.LINE_END,
          LaTeXDocument.LINE_END_BEGIN, LaTeXDocument.LINE_END_END);

      if (h.getOwner().spansColumns() && this.hasStarredEnvironments()) {
        this.m_out.write(LaTeXDocument.FIGURE_END_B, 0,
            LaTeXDocument.FIGURE_END_B.length);
      } else {
        this.m_out.write(LaTeXDocument.FIGURE_END_A, 0,
            LaTeXDocument.FIGURE_END_A.length);
      }
      this.m_out.write(LaTeXDocument.LINE_END,
          LaTeXDocument.LINE_END_BEGIN, LaTeXDocument.LINE_END_END);
      this.m_out.write(LaTeXDocument.LINE_END,
          LaTeXDocument.LINE_END_BEGIN, LaTeXDocument.LINE_END_END);
    } finally {
      super.figureSeriesPageEnd(h);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void subFigureBegin(final SubFigure h) throws IOException,
      IllegalStateException {
    super.subFigureBegin(h);

    if ((h.getCol() <= 1) && (h.getRow() > 1)) {
      this.m_out.write(LaTeXDocument.LINE_END,
          LaTeXDocument.LINE_END_BEGIN, LaTeXDocument.LINE_END_END);
      this.m_out.write(LaTeXDocument.LINEBREAK,
          LaTeXDocument.LINEBREAK_BEGIN, LaTeXDocument.LINEBREAK_END);
    }

    this.m_out.write(LaTeXDocument.HFILL, 0, LaTeXDocument.HFILL.length);
    this.m_out.write(LaTeXDocument.LINE_END, LaTeXDocument.LINE_END_BEGIN,
        LaTeXDocument.LINE_END_END);
    this.m_out.write(LaTeXDocument.SUBFLOAT, 0,
        LaTeXDocument.SUBFLOAT.length);
  }

  /** {@inheritDoc} */
  @Override
  protected void subFigureEnd(final SubFigure h) throws IOException,
      IllegalStateException {
    try {
      this.m_out.write('}');
      this.m_out.write(LaTeXDocument.LINE_END,
          LaTeXDocument.LINE_END_BEGIN, LaTeXDocument.LINE_END_END);
      this.m_out.write(LaTeXDocument.HFILL, 0, LaTeXDocument.HFILL.length);
      this.m_out.write(LaTeXDocument.LINE_END,
          LaTeXDocument.LINE_END_BEGIN, LaTeXDocument.LINE_END_END);
    } finally {
      super.subFigureEnd(h);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void subFigureCaptionEnd(final SubFigureCaption h)
      throws IOException, IllegalStateException {
    SingleLabel l;
    try {
      this.m_out.write(']');
      this.m_out.write('{');

      l = h.getOwner().getLabel();
      l = h.getOwner().getLabel();
      if (l != null) {
        this.writeSingleLabel(l, null);
      }
    } finally {
      super.subFigureCaptionEnd(h);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void itemizationBegin(final Itemization h) throws IOException,
      IllegalStateException {
    super.itemizationBegin(h);
    this.m_out.write(LaTeXDocument.LINE_END, LaTeXDocument.LINE_END_BEGIN,
        LaTeXDocument.LINE_END_END);
    this.m_out.write(LaTeXDocument.ITEMIZE_BEGIN, 0,
        LaTeXDocument.ITEMIZE_BEGIN.length);
    this.m_out.write(LaTeXDocument.LINE_END, LaTeXDocument.LINE_END_BEGIN,
        LaTeXDocument.LINE_END_END);
  }

  /** {@inheritDoc} */
  @Override
  protected void itemizationEnd(final Itemization h) throws IOException,
      IllegalStateException {
    try {
      this.m_out.write(LaTeXDocument.LINE_END,
          LaTeXDocument.LINE_END_BEGIN, LaTeXDocument.LINE_END_END);
      this.m_out.write(LaTeXDocument.ITEMIZE_END, 0,
          LaTeXDocument.ITEMIZE_END.length);
      this.m_out.write(LaTeXDocument.LINE_END,
          LaTeXDocument.LINE_END_BEGIN, LaTeXDocument.LINE_END_END);
    } finally {
      super.itemizationEnd(h);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void itemizationItemBegin(final ItemizationItem h)
      throws IOException, IllegalStateException {
    super.itemizationItemBegin(h);
    this.m_out.write(LaTeXDocument.LINE_END, LaTeXDocument.LINE_END_BEGIN,
        LaTeXDocument.LINE_END_END);
    this.m_out.write(LaTeXDocument.ITEM, 0, LaTeXDocument.ITEM.length);
  }

  /** {@inheritDoc} */
  @Override
  protected void itemizationItemEnd(final ItemizationItem h)
      throws IOException, IllegalStateException {
    try {
      this.m_out.write(LaTeXDocument.LINE_END,
          LaTeXDocument.LINE_END_BEGIN, LaTeXDocument.LINE_END_END);
    } finally {
      super.itemizationItemEnd(h);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void enumerationBegin(final Enumeration h) throws IOException,
      IllegalStateException {
    super.enumerationBegin(h);
    this.m_out.write(LaTeXDocument.LINE_END, LaTeXDocument.LINE_END_BEGIN,
        LaTeXDocument.LINE_END_END);
    this.m_out.write(LaTeXDocument.ENUMERATE_BEGIN, 0,
        LaTeXDocument.ENUMERATE_BEGIN.length);
    this.m_out.write(LaTeXDocument.LINE_END, LaTeXDocument.LINE_END_BEGIN,
        LaTeXDocument.LINE_END_END);
  }

  /** {@inheritDoc} */
  @Override
  protected void enumerationEnd(final Enumeration h) throws IOException,
      IllegalStateException {
    try {
      this.m_out.write(LaTeXDocument.LINE_END,
          LaTeXDocument.LINE_END_BEGIN, LaTeXDocument.LINE_END_END);
      this.m_out.write(LaTeXDocument.ENUMERATE_END, 0,
          LaTeXDocument.ENUMERATE_END.length);
      this.m_out.write(LaTeXDocument.LINE_END,
          LaTeXDocument.LINE_END_BEGIN, LaTeXDocument.LINE_END_END);
    } finally {
      super.enumerationEnd(h);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void enumerationItemBegin(final EnumerationItem h)
      throws IOException, IllegalStateException {
    super.enumerationItemBegin(h);
    this.m_out.write(LaTeXDocument.LINE_END, LaTeXDocument.LINE_END_BEGIN,
        LaTeXDocument.LINE_END_END);
    this.m_out.write(LaTeXDocument.ITEM, 0, LaTeXDocument.ITEM.length);
  }

  /** {@inheritDoc} */
  @Override
  protected void enumerationItemEnd(final EnumerationItem h)
      throws IOException, IllegalStateException {
    try {
      this.m_out.write(LaTeXDocument.LINE_END,
          LaTeXDocument.LINE_END_BEGIN, LaTeXDocument.LINE_END_END);
    } finally {
      super.enumerationItemEnd(h);
    }
  }

  /**
   * begin a new float, flush outstanding ones if necessary the floats
   * 
   * @throws IOException
   *           if io fails
   */
  private final void __beginFloat() throws IOException {
    if ((++this.m_floatCounter) >= LaTeXDocument.MAX_FLOATS_WITHOUT_BARRIER_THRESHOLD) {
      this.m_floatCounter = 1;
      this.m_out.write(LaTeXDocument.LINE_END,
          LaTeXDocument.LINE_END_BEGIN, LaTeXDocument.LINE_END_END);
      this.m_out.write(LaTeXDocument.FLOAT_BARRIER, 0,
          LaTeXDocument.FLOAT_BARRIER.length);
      this.m_out.write(LaTeXDocument.LINE_END,
          LaTeXDocument.LINE_END_BEGIN, LaTeXDocument.LINE_END_END);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void tableCaptionBegin(final TableCaption h)
      throws IOException, IllegalStateException {
    super.tableCaptionBegin(h);

    this.m_writer.flush();
    this.m_captionWriter = new CharArrayWriter();
    this.m_tempOut = this.m_out;
    this.m_out = new WriterCharOutput(this.m_captionWriter);
  }

  /** {@inheritDoc} */
  @Override
  protected void tableCaptionEnd(final TableCaption h) throws IOException,
      IllegalStateException {
    try {
      this.m_out = this.m_tempOut;
      this.m_tempOut = null;
      this.m_last = null;
      this.m_captionWriter.flush();
      this.m_writer.flush();
    } finally {
      super.tableCaptionEnd(h);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void tableEnd(final Table h) throws IOException,
      IllegalStateException {
    try {
      this.m_captionWriter = null;
      this.m_footerWriter = null;
      this.m_headerWriter = null;
    } finally {
      super.tableEnd(h);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void tableHeaderBegin(final TableHeader h) throws IOException,
      IllegalStateException {
    super.tableHeaderBegin(h);

    this.m_writer.flush();
    this.m_headerWriter = new CharArrayWriter();
    this.m_tempOut = this.m_out;
    this.m_out = new WriterCharOutput(this.m_headerWriter);

    this.m_out.write(LaTeXDocument.LINE_END, LaTeXDocument.LINE_END_BEGIN,
        LaTeXDocument.LINE_END_END);
    this.m_out.write(LaTeXDocument.SMALL_BEGIN, 0,
        LaTeXDocument.SMALL_BEGIN.length);
    this.m_out.write(LaTeXDocument.LINE_END, LaTeXDocument.LINE_END_BEGIN,
        LaTeXDocument.LINE_END_END);
    this.m_out.write(LaTeXDocument.TABULAR_BEGIN, 0,
        LaTeXDocument.TABULAR_BEGIN.length);
    this.__cellDefs(h.getOwner());
    this.m_out.write('}');
    this.m_out.write(LaTeXDocument.LINE_END, LaTeXDocument.LINE_END_BEGIN,
        LaTeXDocument.LINE_END_END);
    this.m_out.write(LaTeXDocument.HLINE, LaTeXDocument.HLINE_BEGIN,
        LaTeXDocument.HLINE_END);
    this.m_out.write(LaTeXDocument.LINE_END, LaTeXDocument.LINE_END_BEGIN,
        LaTeXDocument.LINE_END_END);
  }

  /** {@inheritDoc} */
  @Override
  protected void tableHeaderEnd(final TableHeader h) throws IOException,
      IllegalStateException {
    try {
      this.m_out.write(LaTeXDocument.LINE_END,
          LaTeXDocument.LINE_END_BEGIN, LaTeXDocument.LINE_END_END);
      this.m_out.write(LaTeXDocument.HLINE,
          LaTeXDocument.LINEBREAK_HLINE_BEGIN,
          LaTeXDocument.LINEBREAK_HLINE_END);
      this.m_out.write(LaTeXDocument.LINE_END,
          LaTeXDocument.LINE_END_BEGIN, LaTeXDocument.LINE_END_END);

      this.m_out = this.m_tempOut;
      this.m_tempOut = null;
      this.m_last = null;
      this.m_headerWriter.flush();
      this.m_writer.flush();
    } finally {
      super.tableHeaderEnd(h);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void tableFooterBegin(final TableFooter h) throws IOException,
      IllegalStateException {

    super.tableFooterBegin(h);

    this.m_writer.flush();
    this.m_footerWriter = new CharArrayWriter();
    this.m_tempOut = this.m_out;
    this.m_out = new WriterCharOutput(this.m_footerWriter);

    this.m_out.write(LaTeXDocument.LINE_END, LaTeXDocument.LINE_END_BEGIN,
        LaTeXDocument.LINE_END_END);
    this.m_out.write(LaTeXDocument.HLINE,
        LaTeXDocument.LINEBREAK_HLINE_BEGIN,
        LaTeXDocument.LINEBREAK_HLINE_END);
    this.m_out.write(LaTeXDocument.LINE_END, LaTeXDocument.LINE_END_BEGIN,
        LaTeXDocument.LINE_END_END);
  }

  /** {@inheritDoc} */
  @Override
  protected void tableFooterEnd(final TableFooter h) throws IOException,
      IllegalStateException {
    try {

      if (h.getCurrentRowIndex() > 0) {
        this.m_out.write(LaTeXDocument.LINE_END,
            LaTeXDocument.LINE_END_BEGIN, LaTeXDocument.LINE_END_END);
        this.m_out.write(LaTeXDocument.HLINE,
            LaTeXDocument.LINEBREAK_HLINE_BEGIN,
            LaTeXDocument.LINEBREAK_HLINE_END);
      }

      this.m_out.write(LaTeXDocument.LINE_END,
          LaTeXDocument.LINE_END_BEGIN, LaTeXDocument.LINE_END_END);
      this.m_out.write(LaTeXDocument.TABULAR_END, 0,
          LaTeXDocument.TABULAR_END.length);
      this.m_out.write(LaTeXDocument.LINE_END,
          LaTeXDocument.LINE_END_BEGIN, LaTeXDocument.LINE_END_END);
      this.m_out.write(LaTeXDocument.SMALL_END, 0,
          LaTeXDocument.SMALL_END.length);

      this.m_out = this.m_tempOut;
      this.m_tempOut = null;
      this.m_last = null;
      this.m_footerWriter.flush();
      this.m_writer.flush();
    } finally {
      super.tableFooterEnd(h);
    }
  }

  /**
   * begin a row
   * 
   * @param h
   *          the row
   * @throws IOException
   *           if io fails
   */
  private final void __rowBegin(final AbstractTableRow h)
      throws IOException {
    int idx;

    if ((h.getRowOnPage() > 1)) {
      this.m_out.write(LaTeXDocument.LINEBREAK,
          LaTeXDocument.LINEBREAK_BEGIN, LaTeXDocument.LINEBREAK_END);
      this.m_out.write(LaTeXDocument.LINE_END,
          LaTeXDocument.LINE_END_BEGIN, LaTeXDocument.LINE_END_END);
    }

    idx = this.m_hlineCounter;
    this.m_hlineCounter = 0;
    for (; idx > 0; idx--) {
      this.m_out.write(LaTeXDocument.HLINE, LaTeXDocument.HLINE_BEGIN,
          LaTeXDocument.HLINE_END);
      this.m_out.write(LaTeXDocument.LINE_END,
          LaTeXDocument.LINE_END_BEGIN, LaTeXDocument.LINE_END_END);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void tableHeaderRowBegin(final TableHeaderRow h)
      throws IOException, IllegalStateException {//
    super.tableHeaderRowBegin(h);
    this.__rowBegin(h);
  }

  /** {@inheritDoc} */
  @Override
  protected void tableFooterRowBegin(final TableFooterRow h)
      throws IOException, IllegalStateException {//
    super.tableFooterRowBegin(h);
    this.__rowBegin(h);
  }

  /** {@inheritDoc} */
  @Override
  protected void tableBodyRowBegin(final TableBodyRow h)
      throws IOException, IllegalStateException {//
    super.tableBodyRowBegin(h);
    this.__rowBegin(h);
    if ((h.getRowOnPage() & 1) == 0) {
      this.m_out.write(LaTeXDocument.EVEN_ROW_COLOR, 0,
          LaTeXDocument.EVEN_ROW_COLOR.length);
    }
  }

  /**
   * Iterate over the table cell definitions
   * 
   * @param h
   *          the cell definitions
   * @throws IOException
   *           if io fails
   */
  private final void __cellDefs(final Iterable<TableCellDef> h)
      throws IOException {
    String s;

    for (final TableCellDef t : h) {

      if (t.hasRelativeWidth()) {
        s = "p{" + t.getRelativeWidth() + //$NON-NLS-1$
            "\\columnwidth}";//$NON-NLS-1$
        this.m_out.write(s, 0, s.length());
      } else {
        switch (t.getType()) {

          case TableCellDef.ALIGN_CENTER: {
            this.m_out.write('c');
            break;
          }
          case TableCellDef.ALIGN_LEFT:
          case TableCellDef.ALIGN_DISTRIBUTE: {
            this.m_out.write('l');
            break;
          }
          case TableCellDef.ALIGN_RIGHT: {
            this.m_out.write('r');
            break;
          }

          case TableCellDef.VERTICAL_ROW: {
            this.m_out.write('|');
            break;
          }

          default: {
            throw new IllegalArgumentException(//
                "Incompatible cell definition."); //$NON-NLS-1$
          }
        }
      }
    }
  }

  /**
   * begin a cell
   * 
   * @param h
   *          the cell
   * @throws IOException
   *           if io fails
   */
  private final void __cellBegin(final AbstractTableCell h)
      throws IOException {
    final int cols, rows;
    String s;

    if (h.getStartCol() > 1) {
      this.m_out.write('&');
    }

    if (h.treatAsMulti()) {
      cols = h.getColumnSpan();
      rows = h.getRowSpan();

      if ((cols > 1) || ((cols <= 1) && (rows <= 1))) {
        this.m_out.write(LaTeXDocument.MULTI_COLUMN, 0,
            LaTeXDocument.MULTI_COLUMN.length);
        s = String.valueOf(cols);
        this.m_out.write(s, 0, s.length());
        this.m_out.write('}');
        this.m_out.write('{');
        this.__cellDefs(h);
        this.m_out.write('}');
        this.m_out.write('{');
      }
      if (rows > 1) {
        this.m_out.write(LaTeXDocument.MULTI_ROW, 0,
            LaTeXDocument.MULTI_ROW.length);
        s = String.valueOf(cols);
        this.m_out.write(s, 0, s.length());
        this.m_out.write('}');
        this.m_out.write('{');
      }
    }
  }

  /**
   * end a cell
   * 
   * @param h
   *          the cell
   * @param min
   *          the minimum end
   * @throws IOException
   *           if io fails
   */
  private final void __cellEnd(final AbstractTableCell h, final int min)
      throws IOException {
    final int cols, rows;
    int end;

    end = min;
    if (h.treatAsMulti()) {
      cols = h.getColumnSpan();
      rows = h.getRowSpan();

      if ((cols > 1) || ((cols <= 1) && (rows <= 1))) {
        end++;
      }
      if (rows > 1) {
        end++;
      }
    }
    if (end > 0) {
      this.m_out.write(LaTeXDocument.COMMAND_END, 0, end);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void tableHeaderCellBegin(final TableHeaderCell h)
      throws IOException, IllegalStateException {
    super.tableHeaderCellBegin(h);
    this.__cellBegin(h);
    this.m_out.write(LaTeXDocument.TEXT_BF_BEGIN, 0,
        LaTeXDocument.TEXT_BF_BEGIN.length);
  }

  /** {@inheritDoc} */
  @Override
  protected void tableHeaderCellEnd(final TableHeaderCell h)
      throws IOException, IllegalStateException {
    try {
      this.__cellEnd(h, LaTeXDocument.TEXT_BF_END);
    } finally {
      super.tableHeaderCellEnd(h);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void tableFooterCellBegin(final TableFooterCell h)
      throws IOException, IllegalStateException {
    super.tableFooterCellBegin(h);
    this.__cellBegin(h);
    this.m_out.write(LaTeXDocument.TEXT_BF_BEGIN, 0,
        LaTeXDocument.TEXT_BF_BEGIN.length);
  }

  /** {@inheritDoc} */
  @Override
  protected void tableFooterCellEnd(final TableFooterCell h)
      throws IOException, IllegalStateException {
    try {
      this.__cellEnd(h, LaTeXDocument.TEXT_BF_END);
    } finally {
      super.tableFooterCellEnd(h);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void tableBodyCellBegin(final TableBodyCell h)
      throws IOException, IllegalStateException {
    super.tableBodyCellBegin(h);
    this.__cellBegin(h);
  }

  /** {@inheritDoc} */
  @Override
  protected void tableBodyCellEnd(final TableBodyCell h)
      throws IOException, IllegalStateException {
    try {
      this.__cellEnd(h, 0);
    } finally {
      super.tableBodyCellEnd(h);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void tablePageBegin(final TablePage h) throws IOException,
      IllegalStateException {//
    String s;
    SingleLabel l;

    super.tablePageBegin(h);

    this.m_out.write(LaTeXDocument.LINE_END, LaTeXDocument.LINE_END_BEGIN,
        LaTeXDocument.LINE_END_END);
    if (h.getOwner().getOwner().spansColumns()
        && this.hasStarredEnvironments()) {
      this.m_out.write(LaTeXDocument.TABLE_B_BEGIN, 0,
          LaTeXDocument.TABLE_B_BEGIN.length);
    } else {
      this.m_out.write(LaTeXDocument.TABLE_A_BEGIN, 0,
          LaTeXDocument.TABLE_A_BEGIN.length);
    }

    this.m_out.write(LaTeXDocument.LINE_END, LaTeXDocument.LINE_END_BEGIN,
        LaTeXDocument.LINE_END_END);
    this.m_out.write(LaTeXDocument.CENTER_BEGIN, 0,
        LaTeXDocument.CENTER_BEGIN.length);
    this.m_out.write(LaTeXDocument.LINE_END, LaTeXDocument.LINE_END_BEGIN,
        LaTeXDocument.LINE_END_END);

    this.m_out.write(LaTeXDocument.CAPTION_BEGIN, 0,
        LaTeXDocument.CAPTION_BEGIN.length);

    this.m_captionWriter.writeTo(this.m_writer);

    l = this.m_last;
    if (l != null) {
      this.m_out.write(LaTeXDocument.CONTINUED_TABLE_BEGIN, 0,
          LaTeXDocument.CONTINUED_TABLE_BEGIN.length);
      s = l.getKey();
      this.m_out.write(s, 0, s.length());
      this.m_out.write(LaTeXDocument.CONTINUED_END, 0,
          LaTeXDocument.CONTINUED_END.length);
    } else {
      this.m_out.write(LaTeXDocument.COMMAND_END, 0,
          LaTeXDocument.CAPTION_END_LEN);
    }

    l = h.getLabel();
    if (l != null) {
      this.writeSingleLabel(l, null);
    } else {
      this.m_out.write(LaTeXDocument.LINE_END,
          LaTeXDocument.LINE_END_BEGIN, LaTeXDocument.LINE_END_END);
    }
    this.m_last = l;

    this.m_headerWriter.writeTo(this.m_writer);
  }

  /** {@inheritDoc} */
  @Override
  protected void tablePageEnd(final TablePage h) throws IOException,
      IllegalStateException {
    try {
      this.m_footerWriter.writeTo(this.m_writer);

      this.m_out.write(LaTeXDocument.LINE_END,
          LaTeXDocument.LINE_END_BEGIN, LaTeXDocument.LINE_END_END);
      this.m_out.write(LaTeXDocument.CENTER_END, 0,
          LaTeXDocument.CENTER_END.length);
      this.m_out.write(LaTeXDocument.LINE_END,
          LaTeXDocument.LINE_END_BEGIN, LaTeXDocument.LINE_END_END);

      if (h.getOwner().getOwner().spansColumns()
          && this.hasStarredEnvironments()) {
        this.m_out.write(LaTeXDocument.TABLE_B_END, 0,
            LaTeXDocument.TABLE_B_END.length);
      } else {
        this.m_out.write(LaTeXDocument.TABLE_A_END, 0,
            LaTeXDocument.TABLE_A_END.length);
      }
      this.m_out.write(LaTeXDocument.LINE_END,
          LaTeXDocument.LINE_END_BEGIN, LaTeXDocument.LINE_END_END);
      this.m_out.write(LaTeXDocument.LINE_END,
          LaTeXDocument.LINE_END_BEGIN, LaTeXDocument.LINE_END_END);
    } finally {
      super.tablePageEnd(h);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void tableHorizontalLine() throws IOException,
      IllegalStateException {
    this.m_hlineCounter++;
  }

  /** {@inheritDoc} */
  @Override
  protected Table table(final Label label, final String captionDraft,
      final boolean colspan, final TableCellDef... def)
      throws IOException, IllegalStateException {
    final int y;

    y = ((int) (50d - Math
        .ceil(0.8d * ((captionDraft.length() / (((!colspan) && this
            .hasStarredEnvironments()) ? 30d : 60d))))));
    return this.table(label, y, colspan, def);
  }

  /** {@inheritDoc} */
  @Override
  protected void subscriptBegin(final Subscript h) throws IOException,
      IllegalStateException {
    super.subscriptBegin(h);
    this.m_out.write(LaTeXDocument.TEXT_SUBSCRIPT_BEGIN, 0,
        LaTeXDocument.TEXT_SUBSCRIPT_BEGIN.length);
  }

  /** {@inheritDoc} */
  @Override
  protected void subscriptEnd(final Subscript h) throws IOException,
      IllegalStateException {
    try {
      this.m_out.write(LaTeXDocument.COMMAND_END, 0,
          LaTeXDocument.TEXT_SUBSCRIPT_END);
    } finally {
      super.subscriptEnd(h);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void superscriptBegin(final Superscript h) throws IOException,
      IllegalStateException {
    super.superscriptBegin(h);
    this.m_out.write(LaTeXDocument.TEXT_SUPERSCRIPT_BEGIN, 0,
        LaTeXDocument.TEXT_SUPERSCRIPT_BEGIN.length);
  }

  /** {@inheritDoc} */
  @Override
  protected void superscriptEnd(final Superscript h) throws IOException,
      IllegalStateException {
    try {
      this.m_out.write(LaTeXDocument.COMMAND_END, 0,
          LaTeXDocument.TEXT_SUPERSCRIPT_END);
    } finally {
      super.superscriptEnd(h);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void emphasizeBegin(final Emphasize h) throws IOException,
      IllegalStateException {
    super.emphasizeBegin(h);
    this.m_out.write(LaTeXDocument.EMPH_BEGIN, 0,
        LaTeXDocument.EMPH_BEGIN.length);
  }

  /** {@inheritDoc} */
  @Override
  protected void emphasizeEnd(final Emphasize h) throws IOException,
      IllegalStateException {
    try {
      this.m_out.write(LaTeXDocument.COMMAND_END, 0,
          LaTeXDocument.EMPH_END);
    } finally {
      super.emphasizeEnd(h);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void equationBegin(final Equation h) throws IOException,
      IllegalStateException {
    super.equationBegin(h);
    this.m_out.write(LaTeXDocument.LINE_END, LaTeXDocument.LINE_END_BEGIN,
        LaTeXDocument.LINE_END_END);
    this.m_out.write(LaTeXDocument.EQUATION_BEGIN, 0,
        LaTeXDocument.EQUATION_BEGIN.length);
    this.m_out.write(LaTeXDocument.LINE_END, LaTeXDocument.LINE_END_BEGIN,
        LaTeXDocument.LINE_END_END);
  }

  /** {@inheritDoc} */
  @Override
  protected void equationEnd(final Equation h) throws IOException,
      IllegalStateException {
    final SingleLabel lbl;
    try {
      lbl = h.getLabel();
      if (lbl != null) {
        this.writeSingleLabel(lbl, null);
      } else {
        this.m_out.write(LaTeXDocument.LINE_END,
            LaTeXDocument.LINE_END_BEGIN, LaTeXDocument.LINE_END_END);
      }
      this.m_out.write(LaTeXDocument.EQUATION_END, 0,
          LaTeXDocument.EQUATION_END.length);
      this.m_out.write(LaTeXDocument.LINE_END,
          LaTeXDocument.LINE_END_BEGIN, LaTeXDocument.LINE_END_END);
    } finally {
      super.equationEnd(h);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void inlineMathBegin(final InlineMath h) throws IOException,
      IllegalStateException {
    super.inlineMathBegin(h);
    this.m_out.write(LaTeXDocument.ENSUREMATH_BEGIN, 0,
        LaTeXDocument.ENSUREMATH_BEGIN.length);
  }

  /** {@inheritDoc} */
  @Override
  protected void inlineMathEnd(final InlineMath h) throws IOException,
      IllegalStateException {
    try {
      this.m_out.write(LaTeXDocument.COMMAND_END, 0,
          LaTeXDocument.ENSUREMATH_END);
    } finally {
      super.inlineMathEnd(h);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void normalTextBegin(final NormalText h) throws IOException,
      IllegalStateException {//
    super.normalTextBegin(h);
    this.m_out.write(LaTeXDocument.TEXTNORMAL_BEGIN, 0,
        LaTeXDocument.TEXTNORMAL_BEGIN.length);
  }

  /** {@inheritDoc} */
  @Override
  protected void normalTextEnd(final NormalText h) throws IOException,
      IllegalStateException {
    try {
      this.m_out.write(LaTeXDocument.COMMAND_END, 0,
          LaTeXDocument.TEXTNORMAL_END);
    } finally {
      super.normalTextEnd(h);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void mathSubscriptBegin(final MathSubscript h)
      throws IOException, IllegalStateException {
    super.mathSubscriptBegin(h);
    this.m_out.write(LaTeXDocument.MATHSUBSCRIPT_BEGIN, 0,
        LaTeXDocument.MATHSUBSCRIPT_BEGIN.length);
  }

  /** {@inheritDoc} */
  @Override
  protected void mathSubscriptEnd(final MathSubscript h)
      throws IOException, IllegalStateException {
    try {
      this.m_out.write(LaTeXDocument.COMMAND_END, 0,
          LaTeXDocument.MATHSUBSCRIPT_END);
    } finally {
      super.mathSubscriptEnd(h);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void mathSuperscriptBegin(final MathSuperscript h)
      throws IOException, IllegalStateException {
    super.mathSuperscriptBegin(h);
    this.m_out.write(LaTeXDocument.MATHSUPERSCRIPT_BEGIN, 0,
        LaTeXDocument.MATHSUPERSCRIPT_BEGIN.length);
  }

  /** {@inheritDoc} */
  @Override
  protected void mathSuperscriptEnd(final MathSuperscript h)
      throws IOException, IllegalStateException {
    try {
      this.m_out.write(LaTeXDocument.COMMAND_END, 0,
          LaTeXDocument.MATHSUPERSCRIPT_END);
    } finally {
      super.mathSuperscriptEnd(h);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void mathNameBegin(final MathName h) throws IOException,
      IllegalStateException {//
    final _NameDef d;

    super.mathNameBegin(h);
    d = _NameDef.MAP.get(h.getType());
    this.m_out.write(d.m_start, 0, d.m_start.length);
  }

  /** {@inheritDoc} */
  @Override
  protected void mathNameEnd(final MathName h) throws IOException,
      IllegalStateException {

    try {
      this.m_out.write(LaTeXDocument.COMMAND_END, 0,
          _NameDef.MAP.get(h.getType()).m_end);
    } finally {
      super.mathNameEnd(h);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void mathOpBegin(final MathOp h) throws IOException,
      IllegalStateException {
    final _OpDef f;

    super.mathOpBegin(h);

    this.m_out.write('{');
    f = _OpDef.MAP.get(h.getOperator());
    if (f.m_start != null) {
      this.m_out.write(f.m_start, 0, f.m_start.length);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void mathOpEnd(final MathOp h) throws IOException,
      IllegalStateException {
    final _OpDef f;

    try {
      f = _OpDef.MAP.get(h.getOperator());
      if (f.m_end != null) {
        this.m_out.write(f.m_end, 0, f.m_end.length);
      }
      this.m_out.write('}');
    } finally {
      super.mathOpEnd(h);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void mathOpParamBegin(final MathOpParam h) throws IOException,
      IllegalStateException {
    final _OpDef f;
    final int idx;
    final char[] inner;

    super.mathOpParamBegin(h);

    f = _OpDef.MAP.get(h.getOwner().getOperator());
    if (f.m_inner != null) {
      idx = Math.min(f.m_inner.length, h.getIndex());
      if (idx > 0) {
        inner = f.m_inner[idx - 1];
        if (inner != null) {
          this.m_out.write(inner, 0, inner.length);
        }
      }
    }
    this.m_out.write('{');
  }

  /** {@inheritDoc} */
  @Override
  protected void mathOpParamEnd(final MathOpParam h) throws IOException,
      IllegalStateException {
    try {
      this.m_out.write('}');
    } finally {
      super.mathOpParamEnd(h);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void macroBegin(final Macro h) throws IOException,
      IllegalStateException {
    final int e;
    final MacroDescriptor desc;
    int i;
    String s;

    super.macroBegin(h);

    this.m_out.write(LaTeXDocument.LINE_END, LaTeXDocument.LINE_END_BEGIN,
        LaTeXDocument.LINE_END_END);
    this.m_out.write(LaTeXDocument.GDEF_BEGIN, 0,
        LaTeXDocument.GDEF_BEGIN.length);
    desc = h.getDescriptor();
    s = desc.name();
    this.m_out.write(s, 0, s.length());

    e = desc.getParamCount();
    for (i = 1; i <= e; i++) {
      this.m_out.write('#');
      this.m_out.write('0' + i);
    }
    this.m_out.write('{');
    this.m_out.write(LaTeXDocument.LINE_END, LaTeXDocument.LINE_END_BEGIN,
        LaTeXDocument.LINE_END_END);
  }

  /** {@inheritDoc} */
  @Override
  protected void macroEnd(final Macro h) throws IOException,
      IllegalStateException {
    try {
      this.m_out.write(LaTeXDocument.LINE_END,
          LaTeXDocument.LINE_END_BEGIN, LaTeXDocument.LINE_END_END);
      this.m_out.write('}');
      this.m_out.write(LaTeXDocument.LINE_END,
          LaTeXDocument.LINE_END_BEGIN, LaTeXDocument.LINE_END_END);
    } finally {
      super.macroEnd(h);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void macroParameter(final int id) throws IOException {
    super.macroParameter(id);
    this.m_out.write('{');
    this.m_out.write('#');
    this.m_out.write('0' + id);
    this.m_out.write('}');
  }

  /** {@inheritDoc} */
  @Override
  protected void macroInvocationBegin(final MacroInvocation h)
      throws IOException, IllegalStateException {
    String s;

    super.macroInvocationBegin(h);

    this.m_out.write('{');
    this.m_out.write('\\');
    s = h.getDescriptor().name();
    this.m_out.write(s, 0, s.length());
  }

  /** {@inheritDoc} */
  @Override
  protected void macroInvocationEnd(final MacroInvocation h)
      throws IOException, IllegalStateException {
    try {
      this.m_out.write('}');
    } finally {
      super.macroInvocationEnd(h);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void macroParameterBegin(final MacroParameter h)
      throws IOException, IllegalStateException {
    super.macroParameterBegin(h);
    this.m_out.write('{');
  }

  /** {@inheritDoc} */
  @Override
  protected void macroParameterEnd(final MacroParameter h)
      throws IOException, IllegalStateException {
    try {
      this.m_out.write('}');
    } finally {
      super.macroParameterEnd(h);
    }
  }

  /**
   * get the bibliography style
   * 
   * @return the bibliography style
   */
  protected String getBibliographyStyle() {
    return "unsrtnat"; //$NON-NLS-1$
  }

  /**
   * should the bibliography be small?
   * 
   * @return {@code true} if we put the bibliography into a small
   *         environment, {@code false} otherwise
   */
  protected boolean isBibliographySmall() {
    return true;
  }

  /**
   * Make the bibliography style, file, and references
   * 
   * @throws IOException
   *           if io fails
   */
  protected void makeBibliography() throws IOException {
    final BibReference[] brs;
    String s;

    brs = this.getReferences();
    if ((brs != null) && (brs.length > 0)) {

      if (this.isBibliographySmall()) {
        this.m_out.write(LaTeXDocument.LINE_END,
            LaTeXDocument.LINE_END_BEGIN, LaTeXDocument.LINE_END_END);
        s = "\\begin{footnotesize}"; //$NON-NLS-1$
        this.m_out.write(s, 0, s.length());
      }

      this.m_out.write(LaTeXDocument.LINE_END,
          LaTeXDocument.LINE_END_BEGIN, LaTeXDocument.LINE_END_END);

      this.m_out.write(LaTeXDocument.BIBLIOGRAPHY_STYLE_BEGIN, 0,
          LaTeXDocument.BIBLIOGRAPHY_STYLE_BEGIN.length);
      s = this.getBibliographyStyle();
      this.m_out.write(s, 0, s.length());
      this.m_out.write(LaTeXDocument.COMMAND_END, 0,
          LaTeXDocument.BIBLIOGRAPHY_STYLE_END);
      this.m_out.write(LaTeXDocument.LINE_END,
          LaTeXDocument.LINE_END_BEGIN, LaTeXDocument.LINE_END_END);

      s = "\\pdfbookmark[1]{References}{\\refname}"; //$NON-NLS-1$
      this.m_out.write(s, 0, s.length());
      this.m_out.write(LaTeXDocument.LINE_END,
          LaTeXDocument.LINE_END_BEGIN, LaTeXDocument.LINE_END_END);

      this.m_out.write(LaTeXDocument.BIBLIOGRAPHY_BEGIN, 0,
          LaTeXDocument.BIBLIOGRAPHY_BEGIN.length);
      s = this.getOwner().createBibliography(brs);
      this.m_out.write(s, 0, s.length());
      this.m_out.write(LaTeXDocument.COMMAND_END, 0,
          LaTeXDocument.BIBLIOGRAPHY_END);
      this.m_out.write(LaTeXDocument.LINE_END,
          LaTeXDocument.LINE_END_BEGIN, LaTeXDocument.LINE_END_END);

      if (this.isBibliographySmall()) {
        this.m_out.write(LaTeXDocument.LINE_END,
            LaTeXDocument.LINE_END_BEGIN, LaTeXDocument.LINE_END_END);
        s = "\\end{footnotesize}"; //$NON-NLS-1$
        this.m_out.write(s, 0, s.length());
      }

      this.m_out.write(LaTeXDocument.LINE_END,
          LaTeXDocument.LINE_END_BEGIN, LaTeXDocument.LINE_END_END);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void cite(final ECitationMode mode,
      final BibReference... references) throws IOException {
    char[] ch;
    char c;
    String s;

    ch = LaTeXDocument.CITATION_BEGIN[mode.ordinal()];
    this.m_out.write(ch, 0, ch.length);
    c = 0;
    for (final BibReference ref : references) {
      if (c > 0) {
        this.m_out.write(c);
      }
      s = ref.getKey();
      this.m_out.write(s, 0, s.length());
      c = ',';
    }
    this.m_out.write(LaTeXDocument.COMMAND_END, 0,
        LaTeXDocument.CITATION_END);
  }

  /** {@inheritDoc} */
  @Override
  public DocumentDimensions getDimensions() {
    return LaTeXDocumentDimensions.DEFAULT;
  }
}
