package org.logisticPlanning.utils.document.impl.latex;

import java.io.IOException;
import java.net.URI;

import org.logisticPlanning.utils.document.spec.BibReference;
import org.logisticPlanning.utils.document.spec.bib.BibArticle;
import org.logisticPlanning.utils.document.spec.bib.BibAuthor;
import org.logisticPlanning.utils.document.spec.bib.BibAuthors;
import org.logisticPlanning.utils.document.spec.bib.BibBook;
import org.logisticPlanning.utils.document.spec.bib.BibDate;
import org.logisticPlanning.utils.document.spec.bib.BibInCollection;
import org.logisticPlanning.utils.document.spec.bib.BibInProceedings;
import org.logisticPlanning.utils.document.spec.bib.BibProceedings;
import org.logisticPlanning.utils.document.spec.bib.BibRecord;
import org.logisticPlanning.utils.document.spec.bib.BibTechReport;
import org.logisticPlanning.utils.document.spec.bib.BibThesis;
import org.logisticPlanning.utils.document.spec.bib.BibWebsite;
import org.logisticPlanning.utils.document.spec.bib.EBibMonth;
import org.logisticPlanning.utils.document.spec.bib.EBibQuarter;
import org.logisticPlanning.utils.io.charIO.StringCharInput;
import org.logisticPlanning.utils.io.charIO.WriterCharOutput;
import org.logisticPlanning.utils.text.TextUtils;
import org.logisticPlanning.utils.text.transformations.LaTeXCharTransformer;

/**
 * <p>
 * The internal class for writing the BibTeX / bibliography files.
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
final class _Bibliography {

  /** a new line */
  private static final char[] NEWLINE;
  /** begin new line */
  private static final int NEWLINE_BEGIN;
  /** end line */
  private static final int NEWLINE_END;

  /** end a line */
  private static final char[] ENDLINE;
  /** end a line begin */
  private static final int ENDLINE_BEGIN;
  /** end a line end */
  private static final int ENDLINE_END;

  /** the chars between key and value */
  private static final char[] MID;
  /** the chars between key and value begin */
  private static final int MID_BEGIN;
  /** the chars between key and value end */
  private static final int MID_END;

  /** the chars between key and value */
  private static final char[] START;
  /** the chars between key and value begin */
  private static final int START_BEGIN;
  /** the chars between key and value end */
  private static final int START_END;

  /** article */
  private static final char[] ARTICLE = { '@', 'a', 'r', 't', 'i', 'c',
      'l', 'e', '{' };
  /** misc */
  private static final char[] MISC = { '@', 'm', 'i', 's', 'c', '{' };
  /** book */
  private static final char[] BOOK = { '@', 'b', 'o', 'o', 'k', '{' };
  /** inproceedings */
  private static final char[] INPROCEEDINGS = { '@', 'i', 'n', 'p', 'r',
      'o', 'c', 'e', 'e', 'd', 'i', 'n', 'g', 's', '{' };
  /** proceedings */
  private static final char[] PROCEEDINGS = { '@', 'p', 'r', 'o', 'c',
      'e', 'e', 'd', 'i', 'n', 'g', 's', '{' };
  /** techreport */
  private static final char[] TECHREPORT = { '@', 't', 'e', 'c', 'h', 'r',
      'e', 'p', 'o', 'r', 't', '{' };
  /** in collection */
  private static final char[] INCOLLECTION = { '@', 'i', 'n', 'c', 'o',
      'l', 'l', 'e', 'c', 't', 'i', 'o', 'n', '{' };
  /** phd thesis */
  private static final char[] PHD_THESIS = { '@', 'p', 'h', 'd', 't', 'h',
      'e', 's', 'i', 's', '{' };

  /** and */
  private static final char[] AND = { ' ', 'a', 'n', 'd', ' ' };
  /** title */
  private static final char[] TITLE = { 't', 'i', 't', 'l', 'e' };
  /** title */
  private static final char[] INSTITUTION = { 'i', 'n', 's', 't', 'i',
      't', 'u', 't', 'i', 'o', 'n' };

  /** author */
  private static final char[] AUTHOR = { 'a', 'u', 't', 'h', 'o', 'r' };
  /** editor */
  private static final char[] EDITOR = { 'e', 'd', 'i', 't', 'o', 'r' };
  /** chapter */
  private static final char[] CHAPTER = { 'c', 'h', 'a', 'p', 't', 'e',
      'r' };
  /** school */
  private static final char[] SCHOOL = { 's', 'c', 'h', 'o', 'o', 'l' };
  /** publisher */
  private static final char[] PUBLISHER = { 'p', 'u', 'b', 'l', 'i', 's',
      'h', 'e', 'r' };
  /** address */
  private static final char[] ADDRESS = { 'a', 'd', 'd', 'r', 'e', 's',
      's' };
  /** year */
  private static final char[] YEAR = { 'y', 'e', 'a', 'r' };

  /** journal */
  private static final char[] JOURNAL = { 'j', 'o', 'u', 'r', 'n', 'a',
      'l' };
  /** volume */
  private static final char[] VOLUME = { 'v', 'o', 'l', 'u', 'm', 'e' };
  /** number */
  private static final char[] NUMBER = { 'n', 'u', 'm', 'b', 'e', 'r' };
  /** type */
  private static final char[] TYPE = { 't', 'y', 'p', 'e' };
  /** series */
  private static final char[] SERIES = { 's', 'e', 'r', 'i', 'e', 's' };
  /** booktitle */
  private static final char[] BOOKTITLE = { 'b', 'o', 'o', 'k', 't', 'i',
      't', 'l', 'e' };
  /** doi */
  private static final char[] DOI = { 'd', 'o', 'i' };
  /** url */
  private static final char[] URL = { 'u', 'r', 'l' };
  /** pages */
  private static final char[] PAGES = { 'p', 'a', 'g', 'e', 's' };
  /** edition */
  private static final char[] EDITION = { 'e', 'd', 'i', 't', 'i', 'o',
      'n' };

  static {
    final char[] ch;
    int i, l;

    l = TextUtils.LINE_SEPARATOR.length();
    MID = ENDLINE = START = NEWLINE = ch = new char[l + 8];
    i = ENDLINE_BEGIN = 0;

    ch[i++] = '}';
    START_BEGIN = i;
    ch[i++] = ',';
    NEWLINE_BEGIN = i;
    TextUtils.LINE_SEPARATOR.getChars(0, l, ch, i);
    i += l;
    MID_BEGIN = NEWLINE_END = i;
    ch[i++] = ' ';
    ch[i++] = ' ';
    START_END = ENDLINE_END = i;
    ch[i++] = '=';
    ch[i++] = ' ';
    ch[i++] = ' ';
    ch[i++] = '{';
    MID_END = i;
  }

  /**
   * Create the bibliography file based on the reference set.
   * 
   * @param refs
   *          the reference set
   * @param wco
   *          the writer char output
   * @throws IOException
   *           if io fails
   */
  static final void _writeReferences(final BibReference[] refs,
      final WriterCharOutput wco) throws IOException {
    for (final BibReference ref : refs) {
      wco.write(_Bibliography.NEWLINE, _Bibliography.NEWLINE_BEGIN,
          _Bibliography.NEWLINE_END);
      _Bibliography.__writeReference(ref, wco);
      wco.write(_Bibliography.NEWLINE, _Bibliography.NEWLINE_BEGIN,
          _Bibliography.NEWLINE_END);
    }
  }

  /**
   * Write a reference
   * 
   * @param ref
   *          the reference
   * @param wco
   *          the writer char output
   * @throws IOException
   *           if io fails
   */
  private static final void __writeReference(final BibReference ref,
      final WriterCharOutput wco) throws IOException {
    final BibRecord r;

    r = ref.getRecord();

    if (r instanceof BibArticle) {
      _Bibliography.__writeArticle(ref.getKey(), ((BibArticle) r), wco);
      return;
    }
    if (r instanceof BibProceedings) {
      _Bibliography.__writeProceedings(ref.getKey(), ((BibProceedings) r),
          wco);
      return;
    }
    if (r instanceof BibInProceedings) {
      _Bibliography.__writeInProceedings(ref.getKey(),
          ((BibInProceedings) r), wco);
      return;
    }
    if (r instanceof BibInCollection) {
      _Bibliography.__writeInCollection(ref.getKey(),
          ((BibInCollection) r), wco);
      return;
    }
    if (r instanceof BibTechReport) {
      _Bibliography.__writeTechReport(ref.getKey(), ((BibTechReport) r),
          wco);
      return;
    }
    if (r instanceof BibThesis) {
      _Bibliography.__writeThesis(ref.getKey(), ((BibThesis) r), wco);
      return;
    }
    if (r instanceof BibBook) {
      _Bibliography.__writeBook(ref.getKey(), ((BibBook) r), wco);
      return;
    }
    if (r instanceof BibWebsite) {
      _Bibliography.__writeWebsite(ref.getKey(), ((BibWebsite) r), wco);
      return;
    }

    _Bibliography.__writeDefault(ref.getKey(), ref.getRecord(), wco);
  }

  /**
   * write a string
   * 
   * @param what
   *          what to write
   * @param t
   *          the string
   * @param wco
   *          the output
   * @throws IOException
   *           if io fails
   */
  private static final void __string(final char[] what, final String t,
      final WriterCharOutput wco) throws IOException {
    final String s;
    s = TextUtils.prepare(t);
    if (s != null) {
      wco.write(what, 0, what.length);
      wco.write(_Bibliography.MID, _Bibliography.MID_BEGIN,
          _Bibliography.MID_END);
      LaTeXCharTransformer.INSTANCE.transform(new StringCharInput(s), wco,
          0, s.length());
      wco.write(_Bibliography.ENDLINE, _Bibliography.ENDLINE_BEGIN,
          _Bibliography.ENDLINE_END);
    }
  }

  /**
   * write a string
   * 
   * @param what
   *          what to write
   * @param auth
   *          the authors
   * @param wco
   *          the output
   * @throws IOException
   *           if io fails
   */
  private static final void __people(final char[] what,
      final BibAuthors auth, final WriterCharOutput wco)
      throws IOException {
    int i, s;
    boolean x;
    String v, n;
    BibAuthor au;

    if (auth != null) {
      s = auth.size();
      if (s > 0) {
        wco.write(what, 0, what.length);
        wco.write(_Bibliography.MID, _Bibliography.MID_BEGIN,
            _Bibliography.MID_END);
        for (i = 0; i < s; i++) {
          if (i > 0) {
            wco.write(_Bibliography.AND, 0, _Bibliography.AND.length);
          }
          au = auth.get(i);

          v = TextUtils.prepare(au.getPersonalName());
          if (v != null) {
            LaTeXCharTransformer.INSTANCE.transform(
                new StringCharInput(v), wco, 0, v.length());
          }

          n = TextUtils.prepare(au.getFamilyName());
          if (n != null) {
            if (v != null) {
              wco.write(' ');
            }
            x = (n.lastIndexOf(' ') >= 0);
            if (x) {
              wco.write('{');
            }
            LaTeXCharTransformer.INSTANCE.transform(
                new StringCharInput(n), wco, 0, n.length());
            if (x) {
              wco.write('}');
            }
          }
        }
        wco.write(_Bibliography.ENDLINE, _Bibliography.ENDLINE_BEGIN,
            _Bibliography.ENDLINE_END);
      }
    }
  }

  /**
   * write a full date
   * 
   * @param year
   *          the year
   * @param quarter
   *          the quarter
   * @param month
   *          the month
   * @param day
   *          the eay
   * @param wco
   *          the output
   * @throws IOException
   *           if io fails
   */
  private static final void __fullDate(final int year,
      final EBibQuarter quarter, final EBibMonth month, final int day,
      final WriterCharOutput wco) throws IOException {
    String s;

    if (month != null) {
      s = month.getLongName();
      wco.write(s, 0, s.length());
      if (day > 0) {
        wco.write('~');
        s = String.valueOf(day);
        wco.write(s, 0, s.length());
      }
      if (year > 0) {
        wco.write(',');
        wco.write(' ');
      }
    } else {
      if (quarter != null) {
        s = quarter.getLongName();
        wco.write(s, 0, s.length());
        if (year > 0) {
          wco.write('~');
        }
      }
    }

    if (year > 0) {
      s = String.valueOf(year);
      wco.write(s, 0, s.length());
    }
  }

  /**
   * write date
   * 
   * @param start
   *          the start
   * @param end
   *          the end
   * @param wco
   *          the output
   * @throws IOException
   *           if io fails
   */
  private static final void __date(final BibDate start, final BibDate end,
      final WriterCharOutput wco) throws IOException {
    final EBibMonth month1, month2;
    final EBibQuarter quarter1, quarter2;
    final int day1, day2, year1, year2;
    BibDate a, b;
    String s;

    a = start;
    if (a == null) {
      a = end;
      if (a == null) {
        return;
      }
      b = null;
    } else {
      if (a.equals(end)) {
        b = null;
      } else {
        b = end;
      }
    }

    wco.write(_Bibliography.YEAR, 0, _Bibliography.YEAR.length);
    wco.write(_Bibliography.MID, _Bibliography.MID_BEGIN,
        _Bibliography.MID_END);

    day1 = a.getDay();
    month1 = a.getMonth();
    quarter1 = a.getQuarter();
    year1 = a.getYear();

    if (b == null) {
      _Bibliography.__fullDate(year1, quarter1, month1, day1, wco);
    } else {

      day2 = b.getDay();
      month2 = b.getMonth();
      quarter2 = b.getQuarter();
      year2 = b.getYear();

      if (year1 == year2) {

        if (month1 != null) {

          if (month1 == month2) {
            s = month1.getLongName();
            wco.write(s, 0, s.length());
            if ((day1 > 0) && (day2 > 0)) {
              wco.write('~');
              s = String.valueOf(day1);
              wco.write(s, 0, s.length());
              wco.write('-');
              wco.write('-');
              s = String.valueOf(day2);
              wco.write(s, 0, s.length());
              wco.write(',');
              wco.write(' ');
            }
          } else {
            _Bibliography.__fullDate(-1, null, month1, day1, wco);
            if (month2 != null) {
              wco.write('-');
              wco.write('-');
              _Bibliography.__fullDate(-1, null, month2, day2, wco);
            }
            if (quarter2 != null) {
              wco.write('-');
              wco.write('-');
              s = quarter2.getLongName();
              wco.write(s, 0, s.length());
            }
            wco.write(',');
            wco.write(' ');
          }
        } else {

          if (quarter1 != null) {
            s = quarter1.getLongName();
            wco.write(s, 0, s.length());

            if (quarter2 == quarter1) {
              wco.write('~');
            } else {
              if (quarter2 != null) {
                wco.write('-');
                wco.write('-');
                s = quarter2.getLongName();
                wco.write(s, 0, s.length());
                wco.write(',');
                wco.write(' ');
              } else {
                if (month2 != null) {
                  wco.write('-');
                  wco.write('-');
                  _Bibliography
                      .__fullDate(-1, quarter2, month2, day2, wco);
                  wco.write(',');
                  wco.write(' ');
                }
              }
            }

          } else {
            _Bibliography.__fullDate(-1, quarter2, month2, day2, wco);
            if ((quarter2 != null) && (month2 != null)) {
              wco.write(' ');
            }
          }

        }

        s = String.valueOf(year1);
        wco.write(s, 0, s.length());
      } else {
        _Bibliography.__fullDate(year1, quarter1, month1, day1, wco);
        wco.write('-');
        wco.write('-');
        _Bibliography.__fullDate(year2, quarter2, month2, day2, wco);
      }

    }

    wco.write(_Bibliography.ENDLINE, _Bibliography.ENDLINE_BEGIN,
        _Bibliography.ENDLINE_END);
  }

  /**
   * write the address
   * 
   * @param what
   *          what -- the key
   * @param institution
   *          the institution the end
   * @param addr
   *          the address
   * @param wco
   *          the output
   * @throws IOException
   *           if io fails
   */
  private static final void __address(final char[] what,
      final String institution, final String addr,
      final WriterCharOutput wco) throws IOException {
    String a, b;

    a = TextUtils.prepare(institution);
    b = TextUtils.prepare(addr);

    if ((a != null) || (b != null)) {

      if (b != null) {
        wco.write(what, 0, what.length);
        wco.write(_Bibliography.MID, _Bibliography.MID_BEGIN,
            _Bibliography.MID_END);
        LaTeXCharTransformer.INSTANCE.transform(new StringCharInput(b),
            wco, 0, b.length());
        if (a != null) {
          wco.write(':');
          wco.write(' ');
        }
      }

      if (a != null) {
        LaTeXCharTransformer.INSTANCE.transform(new StringCharInput(a),
            wco, 0, a.length());
      }
      wco.write(_Bibliography.ENDLINE, _Bibliography.ENDLINE_BEGIN,
          _Bibliography.ENDLINE_END);
    }
  }

  /**
   * write pages
   * 
   * @param start
   *          the start string
   * @param end
   *          the end string
   * @param wco
   *          the output
   * @throws IOException
   *           if io fails
   */
  private static final void __pages(final String start, final String end,
      final WriterCharOutput wco) throws IOException {
    final String a, b;

    a = TextUtils.prepare(start);
    b = TextUtils.prepare(end);

    if ((a != null) || (b != null)) {
      wco.write(_Bibliography.PAGES, 0, _Bibliography.PAGES.length);
      wco.write(_Bibliography.MID, _Bibliography.MID_BEGIN,
          _Bibliography.MID_END);

      if (a != null) {
        LaTeXCharTransformer.INSTANCE.transform(new StringCharInput(a),
            wco, 0, a.length());
      }

      if (b != null) {
        if (a != null) {
          wco.write('-');
          wco.write('-');
        }
        LaTeXCharTransformer.INSTANCE.transform(new StringCharInput(b),
            wco, 0, b.length());
      }

      wco.write(_Bibliography.ENDLINE, _Bibliography.ENDLINE_BEGIN,
          _Bibliography.ENDLINE_END);
    }
  }

  /**
   * Write an article
   * 
   * @param id
   *          the id
   * @param ref
   *          the article
   * @param wco
   *          the writer char output
   * @throws IOException
   *           if io fails
   */
  private static final void __writeArticle(final String id,
      final BibArticle ref, final WriterCharOutput wco) throws IOException {
    URI u;

    wco.write(_Bibliography.ARTICLE, 0, _Bibliography.ARTICLE.length);
    wco.write(id, 0, id.length());
    wco.write(_Bibliography.START, _Bibliography.START_BEGIN,
        _Bibliography.START_END);
    _Bibliography.__string(_Bibliography.TITLE, ref.getTitle(), wco);
    _Bibliography.__people(_Bibliography.AUTHOR, ref.getAuthors(), wco);
    _Bibliography.__date(ref.getDate(), null, wco);
    _Bibliography.__string(_Bibliography.JOURNAL, ref.getJournal(), wco);
    _Bibliography.__string(_Bibliography.VOLUME, ref.getVolume(), wco);
    _Bibliography.__string(_Bibliography.NUMBER, ref.getNumber(), wco);

    _Bibliography.__pages(ref.getStartPage(), ref.getEndPage(), wco);

    u = ref.getURI();
    if (u != null) {
      _Bibliography.__string(_Bibliography.URL, u.toString(), wco);
    }
    _Bibliography.__string(_Bibliography.DOI, ref.getDoi(), wco);
    wco.write('}');
  }

  /**
   * Write a book
   * 
   * @param id
   *          the id
   * @param ref
   *          the article
   * @param wco
   *          the writer char output
   * @throws IOException
   *           if io fails
   */
  private static final void __writeBook(final String id,
      final BibBook ref, final WriterCharOutput wco) throws IOException {
    URI u;

    wco.write(_Bibliography.BOOK, 0, _Bibliography.BOOK.length);
    wco.write(id, 0, id.length());
    wco.write(_Bibliography.START, _Bibliography.START_BEGIN,
        _Bibliography.START_END);
    _Bibliography.__string(_Bibliography.TITLE, ref.getTitle(), wco);
    _Bibliography.__people(_Bibliography.AUTHOR, ref.getAuthors(), wco);
    _Bibliography.__people(_Bibliography.EDITOR, ref.getEditors(), wco);
    _Bibliography.__date(ref.getDate(), null, wco);
    _Bibliography.__address(_Bibliography.PUBLISHER, ref.getPublisher(),
        ref.getPublisherAddress(), wco);
    _Bibliography.__string(_Bibliography.SERIES, ref.getSeries(), wco);
    _Bibliography.__string(_Bibliography.VOLUME, ref.getVolume(), wco);
    _Bibliography.__string(_Bibliography.EDITION, ref.getEdition(), wco);

    u = ref.getURI();
    if (u != null) {
      _Bibliography.__string(_Bibliography.URL, u.toString(), wco);
    }
    _Bibliography.__string(_Bibliography.DOI, ref.getDoi(), wco);
    wco.write('}');
  }

  /**
   * Write a book
   * 
   * @param id
   *          the id
   * @param ref
   *          the article
   * @param wco
   *          the writer char output
   * @throws IOException
   *           if io fails
   */
  private static final void __writeWebsite(final String id,
      final BibWebsite ref, final WriterCharOutput wco) throws IOException {
    URI u;

    wco.write(_Bibliography.MISC, 0, _Bibliography.MISC.length);
    wco.write(id, 0, id.length());
    wco.write(_Bibliography.START, _Bibliography.START_BEGIN,
        _Bibliography.START_END);
    _Bibliography.__string(_Bibliography.TITLE, ref.getTitle(), wco);
    _Bibliography.__people(_Bibliography.AUTHOR, ref.getAuthors(), wco);

    _Bibliography.__date(ref.getDate(), null, wco);
    _Bibliography.__address(_Bibliography.PUBLISHER, ref.getPublisher(),
        ref.getPublisherAddress(), wco);

    u = ref.getURI();
    if (u != null) {
      _Bibliography.__string(_Bibliography.URL, u.toString(), wco);
    }
    _Bibliography.__string(_Bibliography.DOI, ref.getDoi(), wco);
    wco.write('}');
  }

  /**
   * Write a proceedings
   * 
   * @param id
   *          the id
   * @param ref
   *          the article
   * @param wco
   *          the writer char output
   * @throws IOException
   *           if io fails
   */
  private static final void __writeProceedings(final String id,
      final BibProceedings ref, final WriterCharOutput wco)
      throws IOException {
    URI u;

    wco.write(_Bibliography.PROCEEDINGS, 0,
        _Bibliography.PROCEEDINGS.length);
    wco.write(id, 0, id.length());
    wco.write(_Bibliography.START, _Bibliography.START_BEGIN,
        _Bibliography.START_END);

    _Bibliography.__string(_Bibliography.TITLE, ref.getTitle(), wco);
    _Bibliography.__people(_Bibliography.AUTHOR, ref.getAuthors(), wco);
    _Bibliography.__people(_Bibliography.EDITOR, ref.getEditors(), wco);
    _Bibliography.__date(ref.getStartDate(), ref.getEndDate(), wco);
    _Bibliography.__address(_Bibliography.PUBLISHER, ref.getPublisher(),
        ref.getPublisherAddress(), wco);
    _Bibliography.__address(_Bibliography.ADDRESS, null,
        ref.getLocation(), wco);
    _Bibliography.__string(_Bibliography.SERIES, ref.getSeries(), wco);
    _Bibliography.__string(_Bibliography.VOLUME, ref.getVolume(), wco);
    _Bibliography.__string(_Bibliography.EDITION, ref.getEdition(), wco);

    u = ref.getURI();
    if (u != null) {
      _Bibliography.__string(_Bibliography.URL, u.toString(), wco);
    }
    _Bibliography.__string(_Bibliography.DOI, ref.getDoi(), wco);
    wco.write('}');
  }

  /**
   * Write an in proceedings
   * 
   * @param id
   *          the id
   * @param ref
   *          the article
   * @param wco
   *          the writer char output
   * @throws IOException
   *           if io fails
   */
  private static final void __writeInProceedings(final String id,
      final BibInProceedings ref, final WriterCharOutput wco)
      throws IOException {
    URI u;
    String a;
    final BibProceedings proc;

    wco.write(_Bibliography.INPROCEEDINGS, 0,
        _Bibliography.INPROCEEDINGS.length);
    wco.write(id, 0, id.length());
    wco.write(_Bibliography.START, _Bibliography.START_BEGIN,
        _Bibliography.START_END);

    _Bibliography.__string(_Bibliography.TITLE, ref.getTitle(), wco);
    _Bibliography.__people(_Bibliography.AUTHOR, ref.getAuthors(), wco);

    proc = ref.getBook();
    _Bibliography.__string(_Bibliography.BOOKTITLE, proc.getTitle(), wco);
    _Bibliography.__pages(ref.getStartPage(), ref.getEndPage(), wco);
    _Bibliography.__string(_Bibliography.CHAPTER, ref.getChapter(), wco);
    _Bibliography.__people(_Bibliography.EDITOR, proc.getEditors(), wco);
    _Bibliography.__date(proc.getStartDate(), proc.getEndDate(), wco);
    _Bibliography.__address(_Bibliography.PUBLISHER, proc.getPublisher(),
        proc.getPublisherAddress(), wco);
    _Bibliography.__address(_Bibliography.ADDRESS, null,
        proc.getLocation(), wco);
    _Bibliography.__string(_Bibliography.SERIES, proc.getSeries(), wco);
    _Bibliography.__string(_Bibliography.VOLUME, proc.getVolume(), wco);
    _Bibliography.__string(_Bibliography.EDITION, proc.getEdition(), wco);

    u = ref.getURI();
    if (u != null) {
      _Bibliography.__string(_Bibliography.URL, u.toString(), wco);
    } else {
      u = proc.getURI();
      if (u != null) {
        _Bibliography.__string(_Bibliography.URL, u.toString(), wco);
      }
    }

    a = ref.getDoi();
    if (a != null) {
      _Bibliography.__string(_Bibliography.DOI, a, wco);
    } else {
      a = proc.getDoi();
      _Bibliography.__string(_Bibliography.DOI, a, wco);
    }
    wco.write('}');
  }

  /**
   * Write an in collection
   * 
   * @param id
   *          the id
   * @param ref
   *          the article
   * @param wco
   *          the writer char output
   * @throws IOException
   *           if io fails
   */
  private static final void __writeInCollection(final String id,
      final BibInCollection ref, final WriterCharOutput wco)
      throws IOException {
    URI u;
    String a;
    final BibBook proc;

    wco.write(_Bibliography.INCOLLECTION, 0,
        _Bibliography.INCOLLECTION.length);
    wco.write(id, 0, id.length());
    wco.write(_Bibliography.START, _Bibliography.START_BEGIN,
        _Bibliography.START_END);

    _Bibliography.__string(_Bibliography.TITLE, ref.getTitle(), wco);
    _Bibliography.__people(_Bibliography.AUTHOR, ref.getAuthors(), wco);

    proc = ref.getBook();
    _Bibliography.__string(_Bibliography.BOOKTITLE, proc.getTitle(), wco);
    _Bibliography.__pages(ref.getStartPage(), ref.getEndPage(), wco);
    _Bibliography.__string(_Bibliography.CHAPTER, ref.getChapter(), wco);
    _Bibliography.__people(_Bibliography.EDITOR, proc.getEditors(), wco);
    _Bibliography.__date(proc.getDate(), null, wco);
    _Bibliography.__address(_Bibliography.PUBLISHER, proc.getPublisher(),
        proc.getPublisherAddress(), wco);
    _Bibliography.__string(_Bibliography.SERIES, proc.getSeries(), wco);
    _Bibliography.__string(_Bibliography.VOLUME, proc.getVolume(), wco);
    _Bibliography.__string(_Bibliography.EDITION, proc.getEdition(), wco);

    u = ref.getURI();
    if (u != null) {
      _Bibliography.__string(_Bibliography.URL, u.toString(), wco);
    } else {
      u = proc.getURI();
      if (u != null) {
        _Bibliography.__string(_Bibliography.URL, u.toString(), wco);
      }
    }

    a = ref.getDoi();
    if (a != null) {
      _Bibliography.__string(_Bibliography.DOI, a, wco);
    } else {
      a = proc.getDoi();
      _Bibliography.__string(_Bibliography.DOI, a, wco);
    }
    wco.write('}');
  }

  /**
   * Write a tech report
   * 
   * @param id
   *          the id
   * @param ref
   *          the article
   * @param wco
   *          the writer char output
   * @throws IOException
   *           if io fails
   */
  private static final void __writeTechReport(final String id,
      final BibTechReport ref, final WriterCharOutput wco)
      throws IOException {
    URI u;

    wco.write(_Bibliography.TECHREPORT, 0, _Bibliography.TECHREPORT.length);
    wco.write(id, 0, id.length());
    wco.write(_Bibliography.START, _Bibliography.START_BEGIN,
        _Bibliography.START_END);
    _Bibliography.__string(_Bibliography.TITLE, ref.getTitle(), wco);
    _Bibliography.__people(_Bibliography.AUTHOR, ref.getAuthors(), wco);
    _Bibliography.__date(ref.getDate(), null, wco);
    _Bibliography.__string(_Bibliography.TYPE, ref.getSeries(), wco);
    _Bibliography.__string(_Bibliography.NUMBER, ref.getNumber(), wco);
    _Bibliography.__address(_Bibliography.INSTITUTION, ref.getInstitute(),
        ref.getInstituteAddress(), wco);

    u = ref.getURI();
    if (u != null) {
      _Bibliography.__string(_Bibliography.URL, u.toString(), wco);
    }
    _Bibliography.__string(_Bibliography.DOI, ref.getDoi(), wco);
    wco.write('}');
  }

  /**
   * Write a tech report
   * 
   * @param id
   *          the id
   * @param ref
   *          the article
   * @param wco
   *          the writer char output
   * @throws IOException
   *           if io fails
   */
  private static final void __writeThesis(final String id,
      final BibThesis ref, final WriterCharOutput wco) throws IOException {
    URI u;

    wco.write(_Bibliography.PHD_THESIS, 0, _Bibliography.PHD_THESIS.length);
    wco.write(id, 0, id.length());
    wco.write(_Bibliography.START, _Bibliography.START_BEGIN,
        _Bibliography.START_END);

    _Bibliography.__string(_Bibliography.TITLE, ref.getTitle(), wco);
    _Bibliography.__people(_Bibliography.AUTHOR, ref.getAuthors(), wco);
    _Bibliography.__date(ref.getDate(), null, wco);

    _Bibliography.__string(_Bibliography.TYPE, ref.getType().getName(),
        wco);

    _Bibliography.__address(_Bibliography.SCHOOL, ref.getSchool(),
        ref.getSchoolAddress(), wco);
    _Bibliography.__address(_Bibliography.PUBLISHER, ref.getPublisher(),
        ref.getPublisherAddress(), wco);
    _Bibliography.__string(_Bibliography.SERIES, ref.getSeries(), wco);
    _Bibliography.__string(_Bibliography.VOLUME, ref.getVolume(), wco);

    u = ref.getURI();
    if (u != null) {
      _Bibliography.__string(_Bibliography.URL, u.toString(), wco);
    }
    _Bibliography.__string(_Bibliography.DOI, ref.getDoi(), wco);
    wco.write('}');
  }

  /**
   * Write a record
   * 
   * @param id
   *          the id
   * @param ref
   *          the record
   * @param wco
   *          the writer char output
   * @throws IOException
   *           if io fails
   */
  private static final void __writeDefault(final String id,
      final BibRecord ref, final WriterCharOutput wco) throws IOException {
    URI u;

    wco.write(_Bibliography.MISC, 0, _Bibliography.MISC.length);
    wco.write(id, 0, id.length());
    wco.write(_Bibliography.START, _Bibliography.START_BEGIN,
        _Bibliography.START_END);
    _Bibliography.__string(_Bibliography.TITLE, ref.getTitle(), wco);
    _Bibliography.__people(_Bibliography.AUTHOR, ref.getAuthors(), wco);
    _Bibliography.__date(ref.getDate(), null, wco);

    u = ref.getURI();
    if (u != null) {
      _Bibliography.__string(_Bibliography.URL, u.toString(), wco);
    }
    _Bibliography.__string(_Bibliography.DOI, ref.getDoi(), wco);
    wco.write('}');
  }

}
