/**
 * <p>
 * In this package, we implement a Branch and Bound algorithm with the
 * Held-Karp relaxation, taken more or less from&nbsp;[<a
 * href="#cite_CI2011OTA" style="font-weight:bold">1</a>]. The TSP is
 * represented as integer program similar to the way introduced in&nbsp;[<a
 * href="#cite_DFJ1954SOALSTSP" style="font-weight:bold">2</a>]. The
 * algorithm uses the Held-Karp relaxation&nbsp;[<a
 * href="#cite_HK1970TTSPAMST" style="font-weight:bold">3</a>, <a
 * href="#cite_CV2004OTHKRFTAASTSP" style="font-weight:bold">4</a>, <a
 * href="#cite_HK1971TTSPAMSTPII" style="font-weight:bold">5</a>, <a
 * href="#cite_W1990AOTHKHFTTSP" style="font-weight:bold">6</a>].
 * </p>
 * <p>
 * The result of this algorithm will not be worse than two times the
 * optimum (if it was implemented right).
 * </p>
 * <p>
 * Warning: This algorithm is a really sketchy, dodgy, and probably faulty
 * implementation. It is only experimental. One of the changes we made:
 * create intermediate solutions.
 * </p>
 * <h2>References</h2>
 * <ol>
 * <li><div><span id="cite_CI2011OTA" /><a
 * href="http://stackoverflow.com/users/908076/comestibles">comestibles</a>
 * and&nbsp;<a
 * href="http://stackoverflow.com/users/270287/ivlad">IVlad</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Optimized TSP
 * Algorithms,&rdquo;</span> (Website), August&nbsp;23, 2011, New York, NY,
 * USA: Stack Exchange Inc., stackoverflow. <div>link: [<a href=
 * "http://stackoverflow.com/questions/7159259/optimized-tsp-algorithms"
 * >1</a>]</div></div></li>
 * <li><div><span id="cite_DFJ1954SOALSTSP" /><a
 * href="https://en.wikipedia.org/wiki/George_Dantzig">George Bernard
 * Dantzig</a>, R. Fulkerson, and&nbsp;S. Johnson: <span
 * style="font-weight:bold">&ldquo;Solution of a Large-Scale
 * Traveling-Salesman Problem,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Journal of the Operations
 * Research Society of America</span> 2(4):393&ndash;410,
 * November&nbsp;1954; published by Linthicum, ML, USA: Institute for
 * Operations Research and the Management Sciences (INFORMS). JSTOR
 * stable:&nbsp;<a href="http://www.jstor.org/stable/166695">166695</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/00963984">0096-3984</a>. <div>link:
 * [<a
 * href="http://www.iro.umontreal.ca/~gendron/IFT6551/LECTURES/TSP.pdf">
 * 1</a>]</div></div></li>
 * <li><div><span id="cite_HK1970TTSPAMST" />Michael Held and&nbsp;<a
 * href="https://en.wikipedia.org/wiki/Richard_M._Karp">Richard Manning
 * Karp</a>: <span style="font-weight:bold">&ldquo;The Traveling Salesman
 * Problem and Minimum Spanning Trees,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Operations Research
 * (Oper. Res.)</span> 18(6):1138&ndash;1162, November&ndash;December 1970;
 * published by Linthicum, ML, USA: Institute for Operations Research and
 * the Management Sciences (INFORMS) and&nbsp;Cambridge, MA, USA: HighWire
 * Press (Stanford University). LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/66099702">66099702</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1287/opre.18.6.1138"
 * >10.1287/opre.18.6.1138</a>; JSTOR stable:&nbsp;<a
 * href="http://www.jstor.org/stable/169411">169411</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/2394608">2394608</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/0030364X">0030-364X</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/15265463">1526-5463</a>;
 * CODEN:&nbsp;<a href=
 * "http://www-library.desy.de/cgi-bin/spiface/find/coden/www?code=OPREAI"
 * >OPREAI</a>. <div>link: [<a
 * href="http://or.journal.informs.org/content/18/6/1138.full.pdf"
 * >1</a>]</div></div></li>
 * <li><div><span id="cite_CV2004OTHKRFTAASTSP" />Robert D. Carr
 * and&nbsp;<a href="http://www.cc.gatech.edu/~vempala/">Santosh S.
 * Vempala</a>: <span style="font-weight:bold">&ldquo;On the Held-Karp
 * Relaxation for the Asymmetric and Symmetric Traveling Salesman
 * Problems,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Mathematical
 * Programming</span> 100(3):569&ndash;587, July&nbsp;2004; published by
 * Berlin/Heidelberg: Springer-Verlag. LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/74618643">74618643</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/s10107-004-0506-y"
 * >10.1007/s10107-004-0506-y</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/1585989">1585989</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/00255610">0025-5610</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/14364646">1436-4646</a>. <div>link:
 * [<a href="http://www.cc.gatech.edu/~vempala/papers/sodatsp.ps">1</a>];
 * CiteSeer<sup>x</sup><sub style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href ="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.64.7595">
 * 10.1 .1.64.7595</a></div></div></li>
 * <li><div><span id="cite_HK1971TTSPAMSTPII" />Michael Held and&nbsp;<a
 * href="https://en.wikipedia.org/wiki/Richard_M._Karp">Richard Manning
 * Karp</a>: <span style="font-weight:bold">&ldquo;The Traveling-Salesman
 * Problem and Minimum Spanning Trees: Part II,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Mathematical
 * Programming</span> 1(1):6&ndash;25, December&nbsp;1, 1971; published by
 * Berlin/Heidelberg: Springer-Verlag. LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/74618643">74618643</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/BF01584070">10.1007/BF01584070</a>;
 * OCLC:&nbsp;<a href="https://www.worldcat.org/oclc/1585989">1585989</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/00255610">0025-5610</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/14364646">1436-4646</a></div></li>
 * <li><div><span id="cite_W1990AOTHKHFTTSP" /><a
 * href="http://www.orie.cornell.edu/people/profile.cfm?netid=dw36">David
 * Paul Williamson</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Analysis of the
 * Held-Karp Heuristic for the Traveling Salesman Problem,&rdquo;</span>
 * Master's Thesis, May&nbsp;11, 1990, Cambridge, MA, USA: Massachusetts
 * Institute of Technology (MIT), Department of Electrical Engineering and
 * Computer Science. volume 479 of MIT/LCS/TR, Cambridge, MA, USA:
 * Massachusetts Institute of Technology (MIT). OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/22135051">22135051</a>; Google Book
 * ID:&nbsp;<a
 * href="http://books.google.com/books?id=kIqePgAACAAJ">kIqePgAACAAJ</a>.
 * <div>link: [<a
 * href="http://people.orie.cornell.edu/dpw/papers/masters.pdf">1</a>];
 * CiteSeer<sup>x</sup><sub style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href
 * ="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.18.2">10.1.1
 * .18.2</a></div></div></li>
 * </ol>
 */
package org.logisticPlanning.tsp.solving.algorithms.exact.branchAndBoundHeldCarp;

