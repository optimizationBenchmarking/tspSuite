package org.logisticPlanning.utils.document.impl.latex;

import java.util.EnumMap;

import org.logisticPlanning.utils.document.spec.EMathOp;
import org.logisticPlanning.utils.text.EBraces;

/**
 * <p>
 * The internal class for defining math operations.
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
final class _OpDef {

  /** the empty strings */
  private static final char[][] EMPTY = new char[0][];

  /** the start */
  final char[] m_start;

  /** the end */
  final char[] m_end;

  /** the start and end strings for the internal operators */
  final char[][] m_inner;

  /** the map with the operator data */
  static final EnumMap<EMathOp, _OpDef> MAP;

  static {
    final EnumMap<EMathOp, _OpDef> map;
    final char[] up, down, sum, prod, downfa, end, comma, gapOpen, gapClose;

    up = new char[] { '^' };
    down = new char[] { '_' };
    downfa = new char[] { '_', '{', '\\', 'f', 'o', 'r', 'a', 'l', 'l', };
    end = new char[] { '}', };
    comma = new char[] { ',' };
    gapOpen = new char[] { '\\', 'l', 'e', 'f', 't',
        EBraces.PARENTHESES.getBegin() };
    gapClose = new char[] { '\\', 'r', 'i', 'g', 'h', 't',
        EBraces.PARENTHESES.getEnd() };

    map = new EnumMap<>(EMathOp.class);

    map.put(EMathOp.ADD, //
        new _OpDef(null, null, null, new char[] { '+' }));

    map.put(EMathOp.SUB, //
        new _OpDef(null, null, null, new char[] { '-' }));

    map.put(EMathOp.MUL, //
        new _OpDef(null, null, null, new char[] { '*' }));

    map.put(EMathOp.DIV, //
        new _OpDef(new char[] { '\\', 'f', 'r', 'a', 'c' }, null));

    map.put(EMathOp.DIV_INLINE, //
        new _OpDef(new char[] { '\\', 't', 'f', 'r', 'a', 'c' }, null));

    map.put(EMathOp.MOD, //
        new _OpDef(null, null, null,
            new char[] { '\\', 'b', 'm', 'o', 'd' }));

    map.put(EMathOp.POW, //
        new _OpDef(null, null, null, new char[] { '^' }));

    map.put(EMathOp.LOG, //
        new _OpDef(new char[] { '\\', 'l', 'o', 'g', }, null,
            new char[] { '_' }, null));

    map.put(EMathOp.LN, //
        new _OpDef(new char[] { '\\', 'l', 'n' }, null));

    map.put(EMathOp.LOG_10, //
        new _OpDef(new char[] { '\\', 'l', 'o', 'g', '_', '{', '1', '0',
            '}' }, null));

    map.put(EMathOp.LOG_2, //
        new _OpDef(new char[] { '\\', 'l', 'o', 'g', '_', '{', '2', '}' },
            null));

    map.put(EMathOp.SQRT, //
        new _OpDef(new char[] { '\\', 's', 'q', 'r', 't' }, null));

    map.put(EMathOp.ROOT, //
        new _OpDef(new char[] { '\\', 's', 'q', 'r', 't' }, null,
            new char[] { '[' }, new char[] { ']' }));

    map.put(EMathOp.ABS, //
        new _OpDef(new char[] { '\\', 'l', 'e', 'f', 't', '|' },
            new char[] { '\\', 'r', 'i', 'g', 'h', 't', '|' }));

    map.put(EMathOp.MINUS, //
        new _OpDef(new char[] { '-' }, null));

    map.put(EMathOp.FACTORIAL, //
        new _OpDef(null, new char[] { '!' }));

    map.put(EMathOp.SIN, //
        new _OpDef(new char[] { '\\', 's', 'i', 'n' }, null));

    map.put(EMathOp.COS, //
        new _OpDef(new char[] { '\\', 'c', 'o', 's' }, null));

    map.put(EMathOp.TAN, //
        new _OpDef(new char[] { '\\', 't', 'a', 'n' }, null));

    map.put(EMathOp.AGGREGATE_SUM_FROM_TO, //
        new _OpDef((sum = new char[] { '\\', 's', 'u', 'm' }), null, down,
            up, null));

    map.put(EMathOp.AGGREGATE_SUM_FOR_ALL, //
        new _OpDef(sum, null, downfa, end));

    map.put(EMathOp.AGGREGATE_PRODUCT_FROM_TO, //
        new _OpDef((prod = new char[] { '\\', 'p', 'r', 'o', 'd' }), null,
            down, up, null));

    map.put(EMathOp.AGGREGATE_PRODUCT_FOR_ALL, //
        new _OpDef(prod, null, downfa, end));

    map.put(EMathOp.CMP_EQUALS, //
        new _OpDef(null, null, null, new char[] { '=' }));

    map.put(EMathOp.CMP_LESS_OR_EQUAL, //
        new _OpDef(null, null, null, new char[] { '\\', 'l', 'e', 'q' }));

    map.put(EMathOp.CMP_LESS, //
        new _OpDef(null, null, null, new char[] { '<' }));

    map.put(EMathOp.CMP_GREATER_OR_EQUAL, //
        new _OpDef(null, null, null, new char[] { '\\', 'g', 'e', 'q' }));

    map.put(EMathOp.CMP_GREATER, //
        new _OpDef(null, null, null, new char[] { '>' }));

    map.put(EMathOp.CMP_NOT_EQUAL, //
        new _OpDef(null, null, null, new char[] { '\\', 'n', 'e', 'q' }));

    map.put(EMathOp.CMP_APPROX, //
        new _OpDef(null, null, null, new char[] { '\\', 'a', 'p', 'p',
            'r', 'o', 'x' }));

    map.put(EMathOp.CMP_PROPORTIONAL, //
        new _OpDef(null, null, null, new char[] { '\\', 'p', 'r', 'o',
            'p', 't', 'o' }));

    map.put(EMathOp.LOGICAL_FOLLOWS, //
        new _OpDef(null, null, null, new char[] { '\\', 'i', 'm', 'p',
            'l', 'i', 'e', 's' }));

    map.put(EMathOp.LOGICAL_IFF, //
        new _OpDef(null, null, null, new char[] { '\\', 'i', 'f', 'f' }));

    map.put(EMathOp.LOGICAL_AND, //
        new _OpDef(null, null, null,
            new char[] { '\\', 'l', 'a', 'n', 'd' }));

    map.put(EMathOp.LOGICAL_OR, //
        new _OpDef(new char[] { '\\', 'l', 'o', 'r' }, null));

    map.put(EMathOp.LOGICAL_NOT, //
        new _OpDef(null, null, null,
            new char[] { '\\', 'l', 'n', 'o', 't' }));

    map.put(
        EMathOp.TUPLE, //
        new _OpDef(new char[] { '\\', 'l', 'e', 'f', 't', '(' },
            new char[] { '\\', 'r', 'i', 'g', 'h', 't', ')' }, null, comma));

    map.put(EMathOp.SET, //
        new _OpDef(new char[] { '\\', 'l', 'e', 'f', 't', '\\', '{' },
            new char[] { '\\', 'r', 'i', 'g', 'h', 't', '\\', '}' }, null,
            comma));

    map.put(EMathOp.SET_IN, //
        new _OpDef(null, null, null, new char[] { '\\', 'i', 'n' }));

    map.put(EMathOp.SET_SUBSET, //
        new _OpDef(null, null, null, new char[] { '\\', 's', 'u', 'b',
            's', 'e', 't' }));

    map.put(EMathOp.SET_SUBSET_EQ, //
        new _OpDef(null, null, null, new char[] { '\\', 's', 'u', 'b',
            's', 'e', 't', 'e', 'q' }));

    map.put(EMathOp.ENCLOSING_PARENTHESES, //
        new _OpDef(gapOpen, gapClose));

    map.put(EMathOp.ENCLOSING_CURLY_BRACES, //
        new _OpDef(new char[] { '\\', 'l', 'e', 'f', 't', '\\',
            EBraces.CURLY_BRACES.getBegin() }, new char[] { '\\', 'r',
            'i', 'g', 'h', 't', '\\', EBraces.CURLY_BRACES.getEnd() }));

    map.put(EMathOp.ENCLOSING_SQUARE_BRACKETS, //
        new _OpDef(new char[] { '\\', 'l', 'e', 'f', 't',
            EBraces.SQUARE_BRACKETS.getBegin() }, new char[] { '\\', 'r',
            'i', 'g', 'h', 't', EBraces.SQUARE_BRACKETS.getEnd() }));

    map.put(EMathOp.ENCLOSING_ANGLE_BRACKETS, //
        new _OpDef(new char[] { '\\', 'l', 'e', 'f', 't', '\\', 'l', 'a',
            'n', 'g', 'l', 'e' }, new char[] { '\\', 'r', 'i', 'g', 'h',
            't', '\\', 'r', 'a', 'n', 'g', 'l', 'e' }));

    map.put(EMathOp.ENCLOSING_INEQUALITY_BRACKETS, //
        new _OpDef(
            new char[] { '\\', 'l', 'e', 'f', 't', '\\', 'l', 'l' },
            new char[] { '\\', 'r', 'i', 'g', 'h', 't', '\\', 'g', 'g' }));

    map.put(EMathOp.FUNCTION_CALL, //
        new _OpDef(null, gapClose, null, gapOpen, comma));

    map.put(EMathOp.COMPLEX_BIG_O, //
        new _OpDef(new char[] { '\\', 'm', 'a', 't', 'h', 'b', 'f', '{',
            'O', '}', '\\', 'l', 'e', 'f', 't', '(' }, gapClose));

    MAP = map;
  }

  /**
   * create
   *
   * @param start
   *          the start string
   * @param end
   *          the end string
   */
  private _OpDef(final char[] start, final char[] end) {
    this(start, end, _OpDef.EMPTY);
  }

  /**
   * create
   *
   * @param start
   *          the start string
   * @param end
   *          the end string
   * @param inner
   *          the inner strings
   */
  private _OpDef(final char[] start, final char[] end,
      final char[]... inner) {
    super();
    this.m_start = start;
    this.m_end = end;
    this.m_inner = (((inner != null) && (inner.length > 0)) ? inner
        : _OpDef.EMPTY);
  }

}
