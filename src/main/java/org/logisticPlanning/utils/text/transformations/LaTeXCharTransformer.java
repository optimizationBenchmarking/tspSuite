package org.logisticPlanning.utils.text.transformations;

import java.text.Normalizer;

/**
 * <p>
 * The character transformation class that translates unicode
 * characters&nbsp;[<a href="#cite_UC2011TUC"
 * style="font-weight:bold">1</a>, <a href="#cite_K2006UE"
 * style="font-weight:bold">2</a>, <a href="#cite_UC2006TUSV5"
 * style="font-weight:bold">3</a>] to their <a
 * href="http://en.wikipedia.org/wiki/LaTeX">LaTeX</a>&nbsp;[<a
 * href="#cite_L1994LADPSUGARM" style="font-weight:bold">4</a>, <a
 * href="#cite_GMS1994TLC" style="font-weight:bold">5</a>, <a
 * href="#cite_MGBCR2004TLC" style="font-weight:bold">6</a>, <a
 * href="#cite_OPHS2011TNSSITLOLI1M" style="font-weight:bold">7</a>, <a
 * href="#cite_JF2011LEEUEBM" style="font-weight:bold">8</a>, <a
 * href="#cite_J1995LFAONVDH" style="font-weight:bold">9</a>]
 * representation.
 * </p>
 * <p>
 * You can find more information on how to automatically generate LaTeX
 * documents via a comfortable
 * {@link org.logisticPlanning.utils.document.spec.Document API} in the
 * documentation of the package
 * {@link org.logisticPlanning.utils.document.impl.latex}
 * <h2>References</h2>
 * <ol>
 * <li><div><span id="cite_UC2011TUC" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;The Unicode
 * Consortium,&rdquo;</span> (Website), 2011, Mountain View, CA, USA: The
 * Unicode Consortium. <div>link: [<a
 * href="http://www.unicode.org/">1</a>]</div></div></li>
 * <li><div><span id="cite_K2006UE" />Jukka K. Korpela: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Unicode
 * Explained,&rdquo;</span> June&nbsp;28, 2006, Internationalize Documents,
 * Programs, and Web Sites, Sebastopol, CA, USA: O'Reilly Media, Inc..
 * ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/059610121X">059610121X</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780596101213">9780596101213</a>;
 * Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=PcWU2yxc8WkC"
 * >PcWU2yxc8WkC</a></div></li>
 * <li><div><span id="cite_UC2006TUSV5" />Mountain View, CA, USA: The
 * Unicode Consortium and&nbsp;Julie D. Allen: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;The Unicode
 * Standard, Version 5.0,&rdquo;</span> 2007, Reading, MA, USA:
 * Addison-Wesley Professional. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0321480910">0-321-48091-0</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780321480910">978-0-321-
 * 48091-0</a>; Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=Yn1UAAAAMAAJ"
 * >Yn1UAAAAMAAJ</a></div></li>
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
 * </ol>
 */
public final class LaTeXCharTransformer extends LookupCharTransformer {

  /** the LaTeX character transformer */
  public static final LaTeXCharTransformer INSTANCE = new LaTeXCharTransformer();

  /** instantiate */
  private LaTeXCharTransformer() {
    super(Normalizer.Form.NFC, "LaTeXCharTransformationMap.transform"); //$NON-NLS-1$
  }

}
