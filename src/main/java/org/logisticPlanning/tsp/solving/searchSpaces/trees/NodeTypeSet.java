package org.logisticPlanning.tsp.solving.searchSpaces.trees;

import java.io.PrintStream;
import java.util.Random;

import org.logisticPlanning.utils.config.Configurable;

/**
 * <p>
 * A set of node type records. For each child position of a genotype, we
 * need to specify such a set of possible child types. This set provides an
 * easy interface to access the possible types, the possible non-leaf
 * types, and the possible leaf-types stored in it. Also, we can find very
 * efficiently if a node type is in a node type set (in O(1)), which is a
 * necessary operation of all tree mutation and crossover operations of
 * strongly-typed Genetic Programming.
 * </p>
 * <p>
 * In other words, our Genetic Programming&nbsp;[<a
 * href="#cite_PLMP2008AFGTGP" style="font-weight:bold">1</a>, <a
 * href="#cite_KOZ1992GP" style="font-weight:bold">2</a>, <a
 * href="#cite_WGOEB" style="font-weight:bold">3</a>, <a
 * href="#cite_K1994AIGPV1" style="font-weight:bold">4</a>, <a
 * href="#cite_K1994GPIIADRP" style="font-weight:bold">5</a>, <a
 * href="#cite_KKSMYL2005GP" style="font-weight:bold">6</a>, <a
 * href="#cite_LP2002GP" style="font-weight:bold">7</a>, <a
 * href="#cite_L1998GPADGPDSAP" style="font-weight:bold">8</a>, <a
 * href="#cite_PROC1996AGP" style="font-weight:bold">9</a>, <a
 * href="#cite_PROC1996AGPCLBP" style="font-weight:bold">10</a>, <a
 * href="#cite_PROC1997AGPC" style="font-weight:bold">11</a>, <a
 * href="#cite_PROC1997AGPCLBP" style="font-weight:bold">12</a>, <a
 * href="#cite_PROC1998AGPC" style="font-weight:bold">13</a>, <a
 * href="#cite_PROC1998AGPCLBP" style="font-weight:bold">14</a>, <a
 * href="#cite_PROC1998EUROGP" style="font-weight:bold">15</a>, <a
 * href="#cite_PROC1998EUROGPLBP" style="font-weight:bold">16</a>, <a
 * href="#cite_PROC1999EUROGP" style="font-weight:bold">17</a>, <a
 * href="#cite_PROC2000EUROGP" style="font-weight:bold">18</a>, <a
 * href="#cite_PROC2001EUROGP" style="font-weight:bold">19</a>, <a
 * href="#cite_PROC2002EUROGP" style="font-weight:bold">20</a>]
 * implementation is a form of Strongly-Typed Genetic Programming&nbsp;[<a
 * href="#cite_M1993STGP" style="font-weight:bold">21</a>, <a
 * href="#cite_M1994STGP" style="font-weight:bold">22</a>, <a
 * href="#cite_M1995STGP" style="font-weight:bold">23</a>].
 * </p>
 * <h2>References</h2>
 * <ol>
 * <li><div><span id="cite_PLMP2008AFGTGP" /><a
 * href="http://cswww.essex.ac.uk/staff/poli/">Riccardo Poli</a>, <a
 * href="http://www0.cs.ucl.ac.uk/staff/W.Langdon/">William Benjamin
 * Langdon</a>, and&nbsp;<a
 * href="http://facultypages.morris.umn.edu/~mcphee/">Nicholas Freitag
 * McPhee</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;A Field Guide to
 * Genetic Programming,&rdquo;</span> March&nbsp;2008, London, UK: Lulu
 * Enterprises UK Ltd. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/1409200736">1-4092-0073-6</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9781409200734">978-1-4092
 * -0073-4</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/225855345">225855345</a>; Google
 * Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=3PBrqNK5fFQC">3PBrqNK5fFQC</a>.
 * <div>links: [<a href=
 * "http://www.lulu.com/items/volume_63/2167000/2167025/2/print/book.pdf"
 * >1</a>] and&nbsp;[<a
 * href="http://www.gp-field-guide.org.uk/">2</a>]</div></div></li>
 * <li><div><span id="cite_KOZ1992GP" /><a
 * href="https://en.wikipedia.org/wiki/John_Koza">John R. Koza</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Genetic
 * Programming: On the Programming of Computers by Means of Natural
 * Selection,&rdquo;</span> December&nbsp;1992, Bradford Books, Cambridge,
 * MA, USA: MIT Press. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0262111705">0-262-11170-5</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780262111706">978-0-262-
 * 11170-6</a>; LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/92025785">92025785</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/245844858">245844858</a>, <a
 * href="https://www.worldcat.org/oclc/476715745">476715745</a>, <a
 * href="https://www.worldcat.org/oclc/312492070">312492070</a>, <a
 * href="https://www.worldcat.org/oclc/26263956">26263956</a>, <a
 * href="https://www.worldcat.org/oclc/610975642">610975642</a>,
 * and&nbsp;<a
 * href="https://www.worldcat.org/oclc/473279968">473279968</a>; Google
 * Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=Bhtxo60BV0EC">Bhtxo60BV0EC</a>
 * and&nbsp;<a
 * href="http://books.google.com/books?id=Wd8TAAAACAAJ">Wd8TAAAACAAJ</a>;
 * PPN:&nbsp;<a
 * href="http://gso.gbv.de/PPNSET?PPN=110450795">110450795</a>, <a
 * href="http://gso.gbv.de/PPNSET?PPN=152621075">152621075</a>, <a
 * href="http://gso.gbv.de/PPNSET?PPN=282081046">282081046</a>, <a
 * href="http://gso.gbv.de/PPNSET?PPN=191489522">191489522</a>, <a
 * href="http://gso.gbv.de/PPNSET?PPN=237675633">237675633</a>, <a
 * href="http://gso.gbv.de/PPNSET?PPN=246890320">246890320</a>, <a
 * href="http://gso.gbv.de/PPNSET?PPN=49354836X">49354836X</a>, and&nbsp;<a
 * href="http://gso.gbv.de/PPNSET?PPN=185924921">185924921</a></div></li>
 * <li><div><span id="cite_WGOEB" /><a
 * href="http://www.it-weise.de/">Thomas Weise</a> <span
 * style="color:gray">[&#27748;&#21355;&#24605;</span>]: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Global
 * Optimization Algorithms &#8210; Theory and Application,&rdquo;</span>
 * 2009, Germany: it-weise.de (self-published). <div>link: [<a
 * href="http://www.it-weise.de/projects/book.pdf">1</a>]</div></div></li>
 * <li><div><span id="cite_K1994AIGPV1" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;Advances in
 * Genetic Programming I,&rdquo;</span> April&nbsp;7, 1994, Kenneth E.
 * Kinnear, Jr, editor, Complex Adaptive Systems, Bradford Books,
 * Cambridge, MA, USA: MIT Press. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0262111888">0-262-11188-8</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780262111881">978-0-262-
 * 11188-1</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/473279948">473279948</a>, <a
 * href="https://www.worldcat.org/oclc/468496888">468496888</a>,
 * and&nbsp;<a href="https://www.worldcat.org/oclc/29595260">29595260</a>;
 * Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=eu2JplnQdBkC">eu2JplnQdBkC</a>
 * and&nbsp;<a
 * href="http://books.google.com/books?id=eu2JplnQdBkC">eu2JplnQdBkC</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/10894365">1089-4365</a>;
 * PPN:&nbsp;<a href="http://gso.gbv.de/PPNSET?PPN=133135691">133135691</a>
 * and&nbsp;<a
 * href="http://gso.gbv.de/PPNSET?PPN=192075969">192075969</a></div></li>
 * <li><div><span id="cite_K1994GPIIADRP" /><a
 * href="https://en.wikipedia.org/wiki/John_Koza">John R. Koza</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Genetic
 * Programming II: Automatic Discovery of Reusable Programs,&rdquo;</span>
 * July&nbsp;4, 1994, Complex Adaptive Systems, Bradford Books, Cambridge,
 * MA, USA: MIT Press. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0262111896">0-262-11189-6</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780262111898">978-0-262-
 * 11189-8</a>; LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/94076375">94076375</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/311925261">311925261</a>, <a
 * href="https://www.worldcat.org/oclc/493266030">493266030</a>, <a
 * href="https://www.worldcat.org/oclc/180673805">180673805</a>, <a
 * href="https://www.worldcat.org/oclc/634102150">634102150</a>, <a
 * href="https://www.worldcat.org/oclc/30725207">30725207</a>, and&nbsp;<a
 * href="https://www.worldcat.org/oclc/264562231">264562231</a>; Google
 * Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=t7tpQgAACAAJ">t7tpQgAACAAJ</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/10894365">1089-4365</a>;
 * PPN:&nbsp;<a
 * href="http://gso.gbv.de/PPNSET?PPN=153763795">153763795</a>, <a
 * href="http://gso.gbv.de/PPNSET?PPN=186785119">186785119</a>, and&nbsp;<a
 * href="http://gso.gbv.de/PPNSET?PPN=308224353">308224353</a></div></li>
 * <li><div><span id="cite_KKSMYL2005GP" /><a
 * href="https://en.wikipedia.org/wiki/John_Koza">John R. Koza</a>, Martin
 * A. Keane, Matthew J. Streeter, William Mydlowec, Jessen Yu,
 * and&nbsp;Guido Lanza: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Genetic
 * Programming IV: Routine Human-Competitive Machine
 * Intelligence,&rdquo;</span> 2003, volume 5 of Genetic Programming
 * Series, New York, NY, USA: Springer Science+Business Media, Inc..
 * ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/1402074468">1402074468</a>, <a
 * href="https://www.worldcat.org/isbn/0387264175">0-387-26417-5</a>, <a
 * href="https://www.worldcat.org/isbn/6610611831">6610611831</a>, <a
 * href="https://www.worldcat.org/isbn/0387250670">0-387-25067-0</a>, <a
 * href
 * ="https://www.worldcat.org/isbn/9780387250670">978-0-387-25067-0</a>, <a
 * href="https://www.worldcat.org/isbn/9781402074462">978-1402074462</a>,
 * <a
 * href="https://www.worldcat.org/isbn/9780387264172">978-0-387-26417-2</
 * a>, and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9786610611836">9786610611836</a>;
 * OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/51817183">51817183</a>, <a
 * href="https://www.worldcat.org/oclc/249361385">249361385</a>, <a
 * href="https://www.worldcat.org/oclc/403761573">403761573</a>, <a
 * href="https://www.worldcat.org/oclc/60594679">60594679</a>, <a
 * href="https://www.worldcat.org/oclc/255445558">255445558</a>,
 * and&nbsp;<a
 * href="https://www.worldcat.org/oclc/318293809">318293809</a>; Google
 * Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=YQxWzAEnINIC">YQxWzAEnINIC</a>
 * and&nbsp;<a
 * href="http://books.google.com/books?id=vMaVhoI-hVUC">vMaVhoI-hVUC</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/15667863">1566-7863</a></div></li>
 * <li><div><span id="cite_LP2002GP" /><a
 * href="http://www0.cs.ucl.ac.uk/staff/W.Langdon/">William Benjamin
 * Langdon</a> and&nbsp;<a
 * href="http://cswww.essex.ac.uk/staff/poli/">Riccardo Poli</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Foundations of
 * Genetic Programming,&rdquo;</span> January&nbsp;2002, Berlin, Germany:
 * Springer-Verlag GmbH. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/3540424512">3-540-42451-2</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783540424512">978-3-540-
 * 42451-2</a>; LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/2001049394">2001049394</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/491544616">491544616</a>, <a
 * href="https://www.worldcat.org/oclc/248023954">248023954</a>,
 * and&nbsp;<a href="https://www.worldcat.org/oclc/47717957">47717957</a>;
 * Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=PZli0s95Jd0C">PZli0s95Jd0C</a>;
 * PPN:&nbsp;<a
 * href="http://gso.gbv.de/PPNSET?PPN=334151872">334151872</a>. <div>partly
 * available: [<a
 * href="http://www.cs.ucl.ac.uk/staff/W.Langdon/FOGP/">1</a>]</div></div></li>
 * <li><div><span id="cite_L1998GPADGPDSAP" /><a
 * href="http://www0.cs.ucl.ac.uk/staff/W.Langdon/">William Benjamin
 * Langdon</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Genetic
 * Programming and Data Structures: Genetic Programming + Data Structures =
 * Automatic Programming!,&rdquo;</span> PhD Thesis, September&nbsp;27,
 * 1996, London, UK: University College London (UCL), Department of
 * Computer Science. volume 1 of Genetic Programming Series, New York, NY,
 * USA: Springer Science+Business Media, Inc.. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0792381351">0-7923-8135-1</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780792381353">978-0-7923
 * -8135-3</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/15667863">1566-7863</a>.
 * <div>CiteSeer<sup>x</sup><sub
 * style="font-style:italic">&#946;</sub>:&nbsp;<a href
 * ="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.92.3142"
 * >10.1.1.92 .3142</a></div></div></li>
 * <li><div><span id="cite_PROC1996AGP" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;Proceedings of the
 * First Annual Conference of Genetic Programming (GP'96),&rdquo;</span>
 * July&nbsp;28&ndash;31, 1996, <a
 * href="https://en.wikipedia.org/wiki/John_Koza">John R. Koza</a>, <a
 * href="http://www.davidegoldberg.com/de-goldberg.html">David Edward
 * Goldberg</a>, <a
 * href="http://www.natural-selection.com/people_dfogel.html">David B.
 * Fogel</a>, and&nbsp;Rick L. Riolo, editors, Complex Adaptive Systems,
 * Bradford Books, Cambridge, MA, USA: MIT Press. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0262611279">0-262-61127-9</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780262611275">978-0-262-
 * 61127-5</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/35580073">35580073</a>, <a
 * href="https://www.worldcat.org/oclc/422034061">422034061</a>,
 * and&nbsp;<a href="https://www.worldcat.org/oclc/70655786">70655786</a>;
 * Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=ndy0QgAACAAJ">ndy0QgAACAAJ</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/10894365">1089-4365</a></div></li>
 * <li><div><span id="cite_PROC1996AGPCLBP" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;Late Breaking
 * Papers at the First Annual Conference Genetic Programming (GP'96
 * LBP),&rdquo;</span> July&nbsp;28&ndash;31, 1996, <a
 * href="https://en.wikipedia.org/wiki/John_Koza">John R. Koza</a>, editor,
 * Stanford, CA, USA: Stanford University Bookstore, Stanford University.
 * ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0182010317">0-18-201031-7</a></div></li>
 * <li><div><span id="cite_PROC1997AGPC" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;Proceedings of the
 * Second Annual Conference on Genetic Programming (GP'97),&rdquo;</span>
 * July&nbsp;13&ndash;16, 1997, <a
 * href="https://en.wikipedia.org/wiki/John_Koza">John R. Koza</a>, <a
 * href="http://www.iitk.ac.in/kangal/deb.shtml">Kalyanmoy Deb</a>, <a
 * href="https://en.wikipedia.org/wiki/Marco_Dorigo">Marco Dorigo</a>, <a
 * href="http://www.natural-selection.com/people_dfogel.html">David B.
 * Fogel</a>, Max H. Garzon, Hitoshi Iba, and&nbsp;Rick L. Riolo, editors,
 * San Francisco, CA, USA: Morgan Kaufmann Publishers Inc.. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/1558604839">1-558-60483-9</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9781558604834">978-1-558-
 * 60483-4</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/60136918">60136918</a>; Google Book
 * ID:&nbsp;<a
 * href="http://books.google.com/books?id=PFIZAQAAIAAJ">PFIZAQAAIAAJ</a>;
 * PPN:&nbsp;<a
 * href="http://gso.gbv.de/PPNSET?PPN=235727091">235727091</a></div></li>
 * <li><div><span id="cite_PROC1997AGPCLBP" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;Late Breaking
 * Papers at the 1997 Genetic Programming Conference (GP'97
 * LBP),&rdquo;</span> July&nbsp;13&ndash;16, 1997, <a
 * href="https://en.wikipedia.org/wiki/John_Koza">John R. Koza</a>, editor,
 * Stanford, CA, USA: Stanford University Bookstore, Stanford University.
 * ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0182069958">0-18-206995-8</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780182069958">978-0-18-206995
 * -8</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/38045123">38045123</a>; Google Book
 * ID:&nbsp;<a
 * href="http://books.google.com/books?id=Vn-nAAAACAAJ">Vn-nAAAACAAJ</a>
 * and&nbsp;<a
 * href="http://books.google.com/books?id=cFI7OAAACAAJ">cFI7OAAACAAJ
 * </a></div></li>
 * <li><div><span id="cite_PROC1998AGPC" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;Proceedings of the
 * Third Annual Genetic Programming Conference (GP'98),&rdquo;</span>
 * July&nbsp;22&ndash;25, 1998, <a
 * href="https://en.wikipedia.org/wiki/John_Koza">John R. Koza</a>, <a
 * href="http://www.cs.mun.ca/~banzhaf/">Wolfgang Banzhaf</a>, Kumar
 * Chellapilla, <a href="http://www.iitk.ac.in/kangal/deb.shtml">Kalyanmoy
 * Deb</a>, <a href="https://en.wikipedia.org/wiki/Marco_Dorigo">Marco
 * Dorigo</a>, <a
 * href="http://www.natural-selection.com/people_dfogel.html">David B.
 * Fogel</a>, Max H. Garzon, <a
 * href="http://www.davidegoldberg.com/de-goldberg.html">David Edward
 * Goldberg</a>, Hitoshi Iba, and&nbsp;Rick L. Riolo, editors, San
 * Francisco, CA, USA: Morgan Kaufmann Publishers Inc.. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/1558605487">1-55860-548-7</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9781558605480">978-1-55860
 * -548-0</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/123292121">123292121</a></div></li>
 * <li><div><span id="cite_PROC1998AGPCLBP" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;Late Breaking
 * Papers at the Genetic Programming 1998 Conference (GP'98
 * LBP),&rdquo;</span> June&nbsp;22&ndash;25, 1998, <a
 * href="https://en.wikipedia.org/wiki/John_Koza">John R. Koza</a>, editor,
 * Stanford, CA, USA: Stanford University Bookstore, Stanford
 * University</div></li>
 * <li><div><span id="cite_PROC1998EUROGP" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;Proceedings of the
 * First European Workshop on Genetic Programming
 * (EuroGP'98),&rdquo;</span> April&nbsp;14&ndash;15, 1998, <a
 * href="http://www.cs.mun.ca/~banzhaf/">Wolfgang Banzhaf</a>, <a
 * href="http://cswww.essex.ac.uk/staff/poli/">Riccardo Poli</a>, <a
 * href="http://www.lri.fr/~marc/">Marc Schoenauer</a>, and&nbsp;Terence
 * Claus Fogarty, editors, volume 1391/1998 of Lecture Notes in Computer
 * Science (LNCS), Berlin, Germany: Springer-Verlag GmbH. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/3540643605">3-540-64360-5</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783540643609">978-3-540-
 * 64360-9</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/BFb0055923">10.1007/BFb0055923</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/03029743">0302-9743</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/16113349">1611-3349</a></div></li>
 * <li><div><span id="cite_PROC1998EUROGPLBP" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;Late Breaking
 * Papers at EuroGP'98: The First European Workshop on Genetic Programming
 * (EuroGP'98 LBP),&rdquo;</span> April&nbsp;14&ndash;15, 1998, <a
 * href="http://cswww.essex.ac.uk/staff/poli/">Riccardo Poli</a>, <a
 * href="http://www0.cs.ucl.ac.uk/staff/W.Langdon/">William Benjamin
 * Langdon</a>, <a href="http://www.lri.fr/~marc/">Marc Schoenauer</a>,
 * Terence Claus Fogarty, and&nbsp;<a
 * href="http://www.cs.mun.ca/~banzhaf/">Wolfgang Banzhaf</a>,
 * editors</div></li>
 * <li><div><span id="cite_PROC1999EUROGP" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;Proceedings of the
 * Second European Workshop on Genetic Programming
 * (EuroGP'99),&rdquo;</span> May&nbsp;26&ndash;27, 1999, <a
 * href="http://cswww.essex.ac.uk/staff/poli/">Riccardo Poli</a>, Peter
 * Nordin, <a href="http://www0.cs.ucl.ac.uk/staff/W.Langdon/">William
 * Benjamin Langdon</a>, and&nbsp;Terence Claus Fogarty, editors, volume
 * 1598/1999 of Lecture Notes in Computer Science (LNCS), Berlin, Germany:
 * Springer-Verlag GmbH. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/3540658998">3-540-65899-8</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783540658993">978-3-540-
 * 65899-3</a>; LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/99028995">99028995</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/634229593">634229593</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/03029743">0302-9743</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/16113349">1611-3349</a></div></li>
 * <li><div><span id="cite_PROC2000EUROGP" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;Proceedings of the
 * Third European Conference on Genetic Programming
 * (EuroGP'00),&rdquo;</span> April&nbsp;15&ndash;16, 2000, <a
 * href="http://cswww.essex.ac.uk/staff/poli/">Riccardo Poli</a>, <a
 * href="http://www.cs.mun.ca/~banzhaf/">Wolfgang Banzhaf</a>, <a
 * href="http://www0.cs.ucl.ac.uk/staff/W.Langdon/">William Benjamin
 * Langdon</a>, <a href="http://www.elec.york.ac.uk/staff/jfm7.html">Julian
 * Francis Miller</a>, Peter Nordin, and&nbsp;Terence Claus Fogarty,
 * editors, volume 1802/2000 of Lecture Notes in Computer Science (LNCS),
 * Berlin, Germany: Springer-Verlag GmbH and&nbsp;France: EvoGP, The
 * Genetic Programming Working Group of EvoNET, The Network of Excellence
 * in Evolutionary Computing. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/3540673393">3-540-67339-3</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783540673392">978-3-540-
 * 67339-2</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/b75085">10.1007/b75085</a>;
 * OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/43811252">43811252</a>, <a
 * href="https://www.worldcat.org/oclc/313540688">313540688</a>, <a
 * href="https://www.worldcat.org/oclc/174380171">174380171</a>,
 * and&nbsp;<a
 * href="https://www.worldcat.org/oclc/243487736">243487736</a>; Google
 * Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=KhaR0XZHedEC">KhaR0XZHedEC</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/03029743">0302-9743</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/16113349">1611-3349</a></div></li>
 * <li><div><span id="cite_PROC2001EUROGP" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;Proceedings of the
 * 4th European Conference on Genetic Programming
 * (EuroGP'01),&rdquo;</span> April&nbsp;18&ndash;20, 2001, <a
 * href="http://www.elec.york.ac.uk/staff/jfm7.html">Julian Francis
 * Miller</a>, Marco Tomassini, Pier Luca Lanzi, <a
 * href="http://www.csis.ul.ie/staff/ConorRyan/">Conor Ryan</a>, Andrea G.
 * B. Tettamanzi, and&nbsp;<a
 * href="http://www0.cs.ucl.ac.uk/staff/W.Langdon/">William Benjamin
 * Langdon</a>, editors, volume 2038/2001 of Lecture Notes in Computer
 * Science (LNCS), Berlin, Germany: Springer-Verlag GmbH. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/3540418997">3-540-41899-7</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783540418993">978-3-540-
 * 41899-3</a>; LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/2001020853">2001020853</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/66624523">66624523</a>, <a
 * href="https://www.worldcat.org/oclc/456658150">456658150</a>, <a
 * href="https://www.worldcat.org/oclc/634229636">634229636</a>, <a
 * href="https://www.worldcat.org/oclc/48730451">48730451</a>, <a
 * href="https://www.worldcat.org/oclc/76281028">76281028</a>, <a
 * href="https://www.worldcat.org/oclc/501702396">501702396</a>, <a
 * href="https://www.worldcat.org/oclc/614899119">614899119</a>,
 * and&nbsp;<a href="https://www.worldcat.org/oclc/46502093">46502093</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/03029743">0302-9743</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/16113349">1611-3349</a></div></li>
 * <li><div><span id="cite_PROC2002EUROGP" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;Proceedings of the
 * 5th European Conference on Genetic Programming
 * (EuroGP'02),&rdquo;</span> April&nbsp;3&ndash;5, 2002, <a
 * href="http://www.sci.uidaho.edu/biosci/faculty/Foster.htm">James A.
 * Foster</a>, <a href="http://evelyne.lutton.free.fr/">Evelyne Lutton</a>,
 * <a href="http://www.elec.york.ac.uk/staff/jfm7.html">Julian Francis
 * Miller</a>, <a href="http://www.csis.ul.ie/staff/ConorRyan/">Conor
 * Ryan</a>, and&nbsp;Andrea G. B. Tettamanzi, editors, volume 2278/2002 of
 * Lecture Notes in Computer Science (LNCS), Berlin, Germany:
 * Springer-Verlag GmbH. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/3540433783">3-540-43378-3</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783540433781">978-3-540-
 * 43378-1</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/3-540-45984-7">
 * 10.1007/3-540-45984-7</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/314133163">314133163</a>, <a
 * href="https://www.worldcat.org/oclc/49312634">49312634</a>, <a
 * href="https://www.worldcat.org/oclc/66624505">66624505</a>, <a
 * href="https://www.worldcat.org/oclc/64651626">64651626</a>, <a
 * href="https://www.worldcat.org/oclc/50589656">50589656</a>, and&nbsp;<a
 * href="https://www.worldcat.org/oclc/76394308">76394308</a>; Google Book
 * ID:&nbsp;<a
 * href="http://books.google.com/books?id=RpNQAAAAMAAJ">RpNQAAAAMAAJ</a>
 * and&nbsp;<a
 * href="http://books.google.com/books?id=eCbu4GwRLusC">eCbu4GwRLusC</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/03029743">0302-9743</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/16113349">1611-3349</a></div></li>
 * <li><div><span id="cite_M1993STGP" />David J. Montana: <span
 * style="font-weight:bold">&ldquo;Strongly Typed Genetic
 * Programming,&rdquo;</span> <span
 * style="font-style:italic;font-family:cursive;">Technical Report</span>
 * Number&nbsp;#7866, May&nbsp;7, 1993; published by Cambridge, MA, USA:
 * Bolt Beranek and Newman Inc. (BBN). <div>link: [<a href=
 * "http://www.cs.ucl.ac.uk/staff/W.Langdon/ftp/ftp.io.com/papers/stgp.ps.Z"
 * >1</a>]; CiteSeer<sup>x</sup><sub
 * style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.38.4986"
 * >10.1.1.38.4986</a></div></div></li>
 * <li><div><span id="cite_M1994STGP" />David J. Montana: <span
 * style="font-weight:bold">&ldquo;Strongly Typed Genetic
 * Programming,&rdquo;</span> <span
 * style="font-style:italic;font-family:cursive;">Technical Report</span>
 * Number&nbsp;#7866, March&nbsp;25, 1994; published by Cambridge, MA, USA:
 * Bolt Beranek and Newman Inc. (BBN). <div>links: [<a
 * href="http://citeseer.ist.psu.edu/345471.html">1</a>] and&nbsp;[<a href=
 * "http://www.cs.ucl.ac.uk/staff/W.Langdon/ftp/ftp.io.com/papers/stgp2.ps.Z"
 * >2</a>]; CiteSeer<sup>x</sup><sub
 * style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.38.4986"
 * >10.1.1.38.4986</a></div></div></li>
 * <li><div><span id="cite_M1995STGP" />David J. Montana: <span
 * style="font-weight:bold">&ldquo;Strongly Typed Genetic
 * Programming,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Evolutionary
 * Computation</span> 3(2):199&ndash;230, Summer&nbsp;1995; published by
 * Cambridge, MA, USA: MIT Press. doi:&nbsp;<a
 * href="http://dx.doi.org/10.1162/evco.1995.3.2.199"
 * >10.1162/evco.1995.3.2.199</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/44169764">44169764</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/10636560">1063-6560</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/15309304">1530-9304</a>. <div>links:
 * [<a href="http://vishnu.bbn.com/papers/stgp.pdf">1</a>], [<a href=
 * "http://www.cs.bham.ac.uk/~wbl/biblio/gp-html/montana_stgpEC.html"
 * >2</a>], and&nbsp;[<a
 * href="http://personal.d.bbn.com/~dmontana/papers/stgp.pdf"
 * >3</a>]</div></div></li>
 * </ol>
 *
 * @param <CT>
 *          the node type
 */
public final class NodeTypeSet<CT extends Node<CT>> extends Configurable {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** no children */
  public static final NodeType<?, ?>[] EMPTY_CHILDREN = new NodeType[0];

  /** the list */
  private NodeType<CT, CT>[] m_lst;

  /** the number of node type records */
  private int m_cnt;

  /** the terminal nodes */
  private NodeType<CT, CT>[] m_terminals;

  /** the number of terminal node type records */
  private int m_terminalCnt;

  /** the non-terminal nodes */
  private NodeType<CT, CT>[] m_nonTerminals;

  /** the number of non terminal node type records */
  private int m_nonTerminalCnt;

  /** is the node type set finalized? */
  private boolean m_committed;

  /**
   * Create a new node type set
   */
  @SuppressWarnings("unchecked")
  public NodeTypeSet() {
    super("nodeTypeSet"); //$NON-NLS-1$
    this.m_lst = new NodeType[16];
    this.m_terminals = new NodeType[16];
    this.m_nonTerminals = new NodeType[16];
    this.m_committed = false;
  }

  /** {@inheritDoc} */
  @Override
  public final NodeTypeSet<CT> clone() {
    this.commit();
    return this;
  }

  /** commit! */
  @SuppressWarnings("unchecked")
  public final void commit() {

    NodeType<CT, CT>[] a;

    if (this.m_committed) {
      return;
    }
    try {

      a = this.m_nonTerminals;
      if (a.length != this.m_nonTerminalCnt) {
        this.m_nonTerminals = new NodeType[this.m_nonTerminalCnt];
        System.arraycopy(a, 0, this.m_nonTerminals, 0,
            this.m_nonTerminalCnt);
      }

      a = this.m_terminals;
      if (a.length != this.m_terminalCnt) {
        this.m_terminals = new NodeType[this.m_terminalCnt];
        System.arraycopy(a, 0, this.m_terminals, 0, this.m_terminalCnt);
      }

      a = this.m_lst;
      if (a.length != this.m_cnt) {
        this.m_lst = new NodeType[this.m_cnt];
        System.arraycopy(a, 0, this.m_lst, 0, this.m_cnt);
      }

    } finally {
      this.m_committed = true;
    }
  }

  /**
   * Get the number of entries
   *
   * @return the number of entries
   */
  public final int size() {
    return this.m_cnt;
  }

  /**
   * Get the node type at the specified index
   *
   * @param index
   *          the index into the information set
   * @return the node type at the specified index
   */
  public final NodeType<CT, CT> get(final int index) {
    return this.m_lst[index];
  }

  /**
   * Get the number of terminal node types
   *
   * @return the number of terminal node types
   */
  public final int terminalCount() {
    return this.m_terminalCnt;
  }

  /**
   * Get the terminal node type at the specified index
   *
   * @param index
   *          the index into the information set
   * @return the node type at the specified index
   */
  public final NodeType<CT, CT> getTerminal(final int index) {
    return this.m_terminals[index];
  }

  /**
   * Get the number of non-terminal node types
   *
   * @return the number of non-terminal node types
   */
  public final int nonTerminalCount() {
    return this.m_nonTerminalCnt;
  }

  /**
   * Get the non-terminal node type at the specified index
   *
   * @param index
   *          the index into the information set
   * @return the node type at the specified index
   */
  public final NodeType<CT, CT> getNonTerminal(final int index) {
    return this.m_nonTerminals[index];
  }

  /**
   * IfThenElse a new entry to the node type set
   *
   * @param type
   *          the node type to be added
   */
  @SuppressWarnings("unchecked")
  public final void add(final NodeType<? extends CT, CT> type) {
    NodeType<CT, CT>[] l;
    int i;

    if (type != null) {

      l = this.m_lst;
      i = this.m_cnt;
      type.m_key = i;

      if (i >= l.length) {
        l = new NodeType[i << 1];
        System.arraycopy(this.m_lst, 0, l, 0, i);
        this.m_lst = l;
      }
      l[i++] = ((NodeType<CT, CT>) type);
      this.m_cnt = i;

      if (type.isTerminal()) {
        l = this.m_terminals;
        i = this.m_terminalCnt;
        if (i >= l.length) {
          l = new NodeType[i << 1];
          System.arraycopy(this.m_terminals, 0, l, 0, i);
          this.m_terminals = l;
        }
        l[i++] = ((NodeType<CT, CT>) type);
        this.m_terminalCnt = i;
      } else {
        l = this.m_nonTerminals;
        i = this.m_nonTerminalCnt;
        if (i >= l.length) {
          l = new NodeType[i << 1];
          System.arraycopy(this.m_nonTerminals, 0, l, 0, i);
          this.m_nonTerminals = l;
        }
        l[i++] = ((NodeType<CT, CT>) type);
        this.m_nonTerminalCnt = i;

      }
    }
  }

  /**
   * Obtain a random node type
   *
   * @param r
   *          the random number generator
   * @return the node type
   */
  public final NodeType<CT, CT> randomType(final Random r) {
    final int i;

    i = this.m_cnt;
    if (i <= 0) {
      return null;
    }
    return this.m_lst[r.nextInt(i)];
  }

  /**
   * Obtain a random terminal node type
   *
   * @param r
   *          the random number generator
   * @return the terminal node type
   */
  public final NodeType<CT, CT> randomTerminalType(final Random r) {
    final int i;

    i = this.m_terminalCnt;
    if (i <= 0) {
      return null;
    }
    return this.m_terminals[r.nextInt(i)];
  }

  /**
   * Obtain a random non-terminal node type
   *
   * @param r
   *          the random number generator
   * @return the non-terminal node type
   */
  public final NodeType<CT, CT> randomNonTerminalType(final Random r) {
    final int i;

    i = this.m_nonTerminalCnt;
    if (i <= 0) {
      return null;
    }
    return this.m_nonTerminals[r.nextInt(i)];
  }

  /**
   * Check whether the given node type is contained in this type set or not
   *
   * @param t
   *          the node type
   * @return true if the type is contained, false otherwise
   */
  public final boolean containsType(final NodeType<?, ?> t) {
    final int i;

    i = t.m_key;

    return ((i < this.m_lst.length) && (this.m_lst[i] == t));
  }

  /** {@inheritDoc} */
  @Override
  public void printConfiguration(final PrintStream ps) {
    this.commit();

    super.printConfiguration(ps);

    Configurable.printKey("terminalSymbols", ps); //$NON-NLS-1$
    Configurable.printlnObject(this.m_terminals, ps);

    Configurable.printKey("nonTerminalSymbols", ps); //$NON-NLS-1$
    Configurable.printlnObject(this.m_nonTerminals, ps);
  }
}
