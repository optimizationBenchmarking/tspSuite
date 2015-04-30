package org.logisticPlanning.tsp.benchmarking.dist;

import java.io.PrintWriter;

/**
 * <p>
 * This class is an internal class. Please do not instantiate it, use it by
 * yourself, or otherwise meddle with it.
 * </p>
 * <p>
 * _2DGeoDistanceInt represents list of 2-dimensional 32 bit signed integer
 * (int) coordinates between which the Geo measure is computed as specified
 * in&nbsp;[<a href="#cite_R1995T9" style="font-weight:bold">1</a>].
 * </p>
 * <p>
 * The <a
 * href="http://en.wikipedia.org/wiki/Geographical_distance">geographical
 * distance</a> between two points on the surface is the length of the
 * shortes geodesic connecting them. Geographical coordinates are specified
 * as longitude and latitude coordinates on the earth's surface, which here
 * is assumed to be a perfect sphere. This is a very strong simplification,
 * but for us here, that's OK, as the distance computation method could
 * well be replaced with a more precise one in an actual implementation.
 * </p>
 * <p>
 * Notice that there is a slight contradiction between&nbsp;[<a
 * href="#cite_R1995T9" style="font-weight:bold">1</a>] and&nbsp;[<a
 * href="#cite_R2000TQAA" style="font-weight:bold">11</a>] regarding how
 * the distance in geographical problems should be computed which leads to
 * different results. More precisely,&nbsp;[<a href="#cite_R1995T9"
 * style="font-weight:bold">1</a>, <a href="#cite_R2000TQAA"
 * style="font-weight:bold">11</a>] differ in the preliminary step that
 * translate degree-based coordinates into radian-based coordinates. This
 * implementation here uses the version in&nbsp;[<a href="#cite_R2000TQAA"
 * style="font-weight:bold">11</a>], as it produces the right results in
 * the {@link test.junit.org.logisticPlanning.tsp.benchmarking.instances
 * unit tests} (right here means &quot;compatible with known optima and
 * results from literature&quot;).
 * </p>
 * <p>
 * In&nbsp;[<a href="#cite_R1995T9" style="font-weight:bold">1</a>],
 * longitute and latitude coordinates {@code X} are converted: to radians
 * using the formula
 * </p>
 * 
 * <pre>
 * PI = 3.141592;<br/>
 * <span tyle="color:red">deg = nint(X);</span><br/>
 * min = X- deg;<br/>
 * rad = PI * (deg + 5.0 * min/ 3.0) / 180.0;
 * </pre>
 * <p>
 * while&nbsp;[<a href="#cite_R2000TQAA" style="font-weight:bold">11</a>]
 * uses the formula:
 * </p>
 * 
 * <pre>
 * PI = 3.141592;<br/>
 * <span tyle="color:red">deg = (int) X;</span><br/>
 * min = X - deg;<br/>
 * rad = PI * (deg + 5.0 * min/ 3.0) / 180.0;<br/>
 * </pre>
 * <p>
 * The difference is that <span tyle="color:red"><code>nint(X)</code>
 * </span> rounds to the nearest integer (in case of <code>X=1.9</code>
 * that would be <code>2</code>, whereas <span tyle="color:red">
 * <code>(int)X</code></span> in Java is a truncation, i.e., the fractional
 * part is cut off&nbsp;[<a href="#cite_GJSB2005JAVALS"
 * style="font-weight:bold">12</a>, <a href="#cite_GMG1996JAVAJLE"
 * style="font-weight:bold">13</a>, <a href="#cite_JAVA2PSEAS"
 * style="font-weight:bold">14</a>, <a href="#cite_S2002JABG"
 * style="font-weight:bold">15</a>, <a href="#cite_S2003JPGFP"
 * style="font-weight:bold">16</a>]. This leads to <code>1</code> for
 * <code>X=1.9</code>. As said, we here stick with the second variant,
 * i.e., the version given in&nbsp;[<a href="#cite_R2000TQAA"
 * style="font-weight:bold">11</a>].
 * </p>
 * <p>
 * In both cases, the further computation is performed as follows:
 * </p>
 * 
 * <pre>
 * q1  = cos( longA - longB )<br/>
 * q2  = cos( latA  - latB  )<br/>
 * q3  = cos( latA  + latB  )<br/>
 * return ((int) ( 6378.388d * acos( 0.5d * ( (1.0 + q1) * q2 - (1.0 - q1) * q3) ) + 1.0))
 * </pre>
 * <p>
 * You can find more information in the file <code>tsplib_doc.pdf</code>
 * that we attach in the documentation folder (<code>doc</code>) of this
 * library.
 * </p>
 * <p>
 * Solutions to the TSP can be represented with different data
 * structures&nbsp;[<a href="#cite_FJMGO1995DSFTS"
 * style="font-weight:bold">3</a>, <a href="#cite_LKMUB1999GAFTTSPARORAO"
 * style="font-weight:bold">4</a>, <a href="#cite_JMG2004EAOHFTS"
 * style="font-weight:bold">5</a>, <a href="#cite_OR2006TSLANDSFTSP"
 * style="font-weight:bold">6</a>], each of which having distinctive
 * advantages and drawbacks. The
 * <em><a href="http://www.logisticPlanning.org/tsp">TSP Suite</a></em> can
 * deal with different representations for the candidate solutions of a
 * TSP:
 * </p>
 * <ol>
 * <li id="pathRepresentation">
 * <p>
 * The <em>path representation</em>&nbsp;[<a
 * href="#cite_LKMUB1999GAFTTSPARORAO" style="font-weight:bold">4</a>, <a
 * href="#cite_P1996GAFTTSP" style="font-weight:bold">7</a>] is probably
 * the most natural representation of a tour. Here, a tour is represented
 * as a permutation of the numbers from {@code 1} to {@code n}. If city
 * {@code i} is the {@code j}<sup>th</sup> element of the list, then this
 * city {@code i} is the {@code j}<sup>th</sup> city to be visited. Hence,
 * the tour
 * <code>3&rarr;2&rarr;4&rarr;1&rarr;7&rarr;5&rarr;8&rarr;6&rarr;3</code>
 * is simply represented by <code>new int[] {3, 2, 4, 1, 7, 5, 8, 6}</code>
 * &nbsp;[<a href="#cite_LKMUB1999GAFTTSPARORAO"
 * style="font-weight:bold">4</a>, <a href="#cite_P1996GAFTTSP"
 * style="font-weight:bold">7</a>].
 * </p>
 * <p id="pathNormalForm">
 * There are multiple permutations that encode for the same tour in path
 * representation. In order to ease visual comparison of tours, the
 * {@link org.logisticPlanning.tsp.solving.utils.RepresentationUtils#appendPathInNormalForm(int[],java.lang.Appendable)
 * Normal Form} for path representation was defined. Here, the first
 * element is node {@code 1} and then the neighboring node with the lowest
 * index follows.
 * </p>
 * <p>
 * The method associated with this representation is
 * {@link #evaluate(int[])}.
 * </p>
 * </li>
 * <li id="adjacencyRepresentation">
 * <p>
 * In the <em>adjacency representation</em>&nbsp;[<a
 * href="#cite_GGRVG1985GAFTT" style="font-weight:bold">8</a>, <a
 * href="#cite_LKMUB1999GAFTTSPARORAO" style="font-weight:bold">4</a>, <a
 * href="#cite_P1996GAFTTSP" style="font-weight:bold">7</a>] a tour is also
 * represented as permutation of the numbers from {@code 1} to {@code n}.
 * City {@code j} is listed in position {@code i} if, and only if, the tour
 * directly leads from city {@code i} to city {@code j}. Thus, the list
 * <code>new int[] {3, 5, 7, 6, 4, 8, 2, 1}</code> represents the tour
 * <code>1&rarr;3&rarr;7&rarr;2&rarr;5&rarr;4&rarr;6&rarr;8&rarr;1</code>.
 * While each possible permutation is a valid solution in <a
 * href="#pathRepresentation"><em>path representation</em></a>, this is not
 * true for the adjacency representation. The permutation {@code (1, 3, 2)}
 * , for instance, is not a valid adjacency list, since it would mean
 * &quot;after node 1 go to node 1&quot; (cycle of length 1) and
 * &quot;after node 2 go to node 3, after node 3 go to node 2&quot; (cycle
 * of length 2).
 * </p>
 * <p>
 * The method associated with this representation is
 * {@link #evaluateAdj(int[])} .
 * </p>
 * <p>
 * Although a tour in adjacency representation here is stored in an array
 * of {@code int}, we could consider this representation as
 * <em>linked list</em> (a singly linked list, not a doubly linked one).
 * Effectively, we could begin at any city {@code i} which would mark the
 * start of the tour. At index {@code i-1} in the array, we would find the
 * next city {@code j} to visit. This could be interpreted as link or
 * pointer, since we would then look at index {@code j-1} in the array to
 * get the next-next city, and so on.
 * </p>
 * <li id="satelliteList">
 * <p>
 * {@link org.logisticPlanning.tsp.solving.utils.satelliteList.SatelliteList
 * Satellite lists}&nbsp;[<a href="#cite_OR2006TSLANDSFTSP"
 * style="font-weight:bold">6</a>, <a href="#cite_ORG2005TSLARDLL"
 * style="font-weight:bold">9</a>] are a clever data structure for
 * representing tours for symmetric TSPs. Compared to the path and
 * adjacency representation, the have the advantage that the reversal of a
 * segment of the tour can be achieved in <em>O(1)</em> instead of
 * <em>O(n)</em>. You can find them implemented in the utilities package
 * {@link org.logisticPlanning.tsp.solving.utils.satelliteList satellite},
 * together with corresponding conversion routines.
 * <p>
 * </p> {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
 * distance computers} does not support this data structure directly. </p></li>
 * <li id="edgeRepresentation">
 * <p>
 * We furthermore introduce the class
 * {@link org.logisticPlanning.tsp.solving.utils.edge.Edge} to represent a
 * directed or undirected edge.
 * {@link org.logisticPlanning.tsp.solving.utils.edge.Edge Edge} therefore
 * has two public member variables
 * {@link org.logisticPlanning.tsp.solving.utils.edge.Edge#a a} and
 * {@link org.logisticPlanning.tsp.solving.utils.edge.Edge#b b} indicating
 * its start an end nodes. Thus, another way to represent a solution is as
 * a set of such edge objects. Such a set can be stored in an array or in
 * one of Java's {@link java.util.Collection collection} objects, e.g., a
 * {@link java.util.Set set} or {@link java.util.List list}.
 * </p>
 * <p>
 * The {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer
 * distance computers} do not provide specialized support methods to
 * evaluate solutions in this representation nor does the
 * {@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction
 * objective function} have dedicated evaluation methods for them. Such
 * specialized methods would just make these classes bigger without
 * performance gain.
 * </p>
 * <p>
 * However, the
 * {@link org.logisticPlanning.tsp.solving.utils.RepresentationUtils}
 * sports several <a href="#edgeListMethods">conversion routines</a> as of
 * version {@code 0.9.8}.
 * </p>
 * </li>
 * </ol>
 * <p>
 * Elements in either representation can be converted by using the
 * following static methods:
 * </p>
 * <ol>
 * <li>
 * {@link org.logisticPlanning.tsp.solving.utils.RepresentationUtils#adjacencyListToPath(int[], int[])
 * adjacencyListToPath} converts a solution from <a
 * href="#adjacencyRepresentation"><em>adjacency representation</em></a> to
 * <a href="#pathRepresentation"><em>path representation</em></a>.</li>
 * <li>
 * {@link org.logisticPlanning.tsp.solving.utils.RepresentationUtils#pathToAdjacencyList(int[], int[])
 * pathToAdjacencyList} converts a solution from <a
 * href="#pathRepresentation"> <em>path representation</em></a> to <a
 * href="#adjacencyRepresentation"> <em>adjacency representation</em></a>.</li>
 * <li id="edgeListMethods">
 * {@link org.logisticPlanning.tsp.solving.utils.RepresentationUtils#pathToEdges(int[],org.logisticPlanning.tsp.solving.utils.edge.Edge[])
 * pathToDirectedEdges} converts a solution in <a
 * href="#pathRepresentation"> <em>path representation</em></a> to a set
 * (array) of edges.</li>
 * <li>
 * {@link org.logisticPlanning.tsp.solving.utils.RepresentationUtils#adjacencyListToEdges(int[],org.logisticPlanning.tsp.solving.utils.edge.Edge[])
 * adjacencyListToDirectedEdges} converts a solution in <a
 * href="#adjacencyRepresentation"><em>adjacency representation</em></a> to
 * a set (array) of edges.</li>
 * <li>
 * {@link org.logisticPlanning.tsp.solving.utils.RepresentationUtils#undirectedEdgesToAdjacencyList(java.lang.Iterable,int[])
 * undirectedEdgesToAdjacencyList(Iterable, int[])} converts a
 * {@link java.lang.Iterable iterable} set or list of potentially
 * undirected edges into a solution in <a href="#adjacencyRepresentation">
 * <em>adjacency representation</em></a>.</li>
 * <li>
 * {@link org.logisticPlanning.tsp.solving.utils.RepresentationUtils#directedEdgesToAdjacencyList(java.lang.Iterable,int[])
 * directedEdgesToAdjacencyList(Iterable, int[])} converts a
 * {@link java.lang.Iterable iterable} set or list of directed edges into a
 * solution in <a href="#adjacencyRepresentation">
 * <em>adjacency representation</em></a>.</li>
 * <li>
 * {@link org.logisticPlanning.tsp.solving.utils.RepresentationUtils#undirectedEdgesToAdjacencyList(org.logisticPlanning.tsp.solving.utils.edge.Edge[],int[])
 * undirectedEdgesToAdjacencyList(Edge[], int[])} converts an array of
 * potentially undirected edges into a solution in <a
 * href="#adjacencyRepresentation"><em>adjacency representation</em></a>.</li>
 * <li>
 * {@link org.logisticPlanning.tsp.solving.utils.RepresentationUtils#directedEdgesToAdjacencyList(org.logisticPlanning.tsp.solving.utils.edge.Edge[],int[])
 * directedEdgesToAdjacencyList(Edge[], int[])} converts an array of
 * directed edges into a solution in <a href="#adjacencyRepresentation">
 * <em>adjacency representation</em></a>.</li>
 * <li>
 * {@link org.logisticPlanning.tsp.solving.utils.satelliteList.SatelliteList#fromPath(int[])
 * fromPath(int[])} of
 * {@link org.logisticPlanning.tsp.solving.utils.satelliteList.SatelliteList}
 * loads the contents of an integer array holding a solution in <a
 * href="#pathRepresentation"><em>path representation</em></a> into a
 * satellite list instance.</li>
 * <li>
 * {@link org.logisticPlanning.tsp.solving.utils.satelliteList.SatelliteList#toPath(int[])
 * toPath(int[])} of class
 * {@link org.logisticPlanning.tsp.solving.utils.satelliteList.SatelliteList}
 * translates the contents of a satellite list to the <a
 * href="#pathRepresentation"><em>path representation</em></a>. While on
 * it, it also checks if the translation results in a feasible path. (There
 * may be infeasible satellite lists).</li>
 * </ol>
 * <h2>References</h2>
 * <ol>
 * <li><div><span id="cite_R1995T9" /><a
 * href="http://comopt.ifi.uni-heidelberg.de/people/reinelt/">Gerhard
 * Reinelt</a>: <span style="font-weight:bold">&ldquo;TSPLIB
 * 95,&rdquo;</span> <span
 * style="font-style:italic;font-family:cursive;">Technical Report</span>
 * 1995; published by Heidelberg, Germany: Universit&#228;t Heidelberg,
 * Institut f&#252;r Mathematik. <div>link: [<a
 * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/DOC.PS"
 * >1</a>]</div></div></li>
 * <li><div><span id="cite_K1986TGAAINEG" />Eugene F. Krause: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Taxicab Geometry
 * &#8210; An Adventure in Non-Euclidean Geometry,&rdquo;</span> 1986,
 * North Chelmsford, MA, USA: Courier Dover Publications. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0486252027">0486252027</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780486252025">9780486252025</a>;
 * Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=IW7ICV0QXWwC"
 * >IW7ICV0QXWwC</a></div></li>
 * <li><div><span id="cite_FJMGO1995DSFTS" /><a
 * href="http://en.wikipedia.org/wiki/Michael_Fredman">Michael L.
 * Fredman</a>, <a
 * href="https://en.wikipedia.org/wiki/David_S._Johnson">David Stifler
 * Johnson</a>, <a
 * href="https://www.amherst.edu/people/facstaff/lamcgeoch">Lyle A.
 * McGeoch</a>, and&nbsp;Gerard Ostheimer: <span
 * style="font-weight:bold">&ldquo;Data Structures for Traveling
 * Salesmen,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Journal of
 * Algorithms</span> 18(3):432&ndash;479, May&nbsp;1995; published by
 * Amsterdam, The Netherlands: Elsevier Science Publishers B.V..
 * doi:&nbsp;<a
 * href="http://dx.doi.org/10.1006/jagm.1995.1018">10.1006/jagm
 * .1995.1018</a>. <div>link: [<a
 * href="ftp://dimacs.rutgers.edu/pub/dsj/temp/data.ps">1</a>];
 * CiteSeer<sup>x</sup><sub style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href=
 * "http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.71.170">10.1
 * .1.71 .170</a></div></div></li>
 * <li><div><span id="cite_LKMUB1999GAFTTSPARORAO" /><a href=
 * "http://cig.fi.upm.es/index.php?option=com_content&amp;view=article&amp;id=78&amp;Itemid=111"
 * >Pedro Larra&#241;aga</a>, <a href=
 * "http://www.tilburguniversity.edu/nl/webwijs/show/&amp;uid=c.m.h.kuijpers?uid=c.m.h.kuijpers"
 * >Cindy M. H. Kuijpers</a>, Roberto H. Murga, <a
 * href="http://www.sc.ehu.es/ccwbayes/members/inaki.htm">I&#241;aki
 * Inza</a>, and&nbsp;Sejla Dizdarevic: <span
 * style="font-weight:bold">&ldquo;Genetic Algorithms for the Travelling
 * Salesman Problem: A Review of Representations and
 * Operators,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Journal of Artificial
 * Intelligence Research (JAIR)</span> 13(2):129&ndash;170,
 * April&nbsp;1999; published by El Segundo, CA, USA: AI Access Foundation,
 * Inc. and&nbsp;Menlo Park, CA, USA: AAAI Press. doi:&nbsp;<a
 * href="http://dx.doi.org/10.1023/A:1006529012972"
 * >10.1023/A:1006529012972</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/110769757">11076-9757</a>.
 * <div>link: [<a href=
 * "http://www.dca.fee.unicamp.br/~gomide/courses/EA072/artigos/Genetic_Algorithm_TSPR_eview_Larranaga_1999.pdf"
 * >1</a>]; CiteSeer<sup>x</sup><sub
 * style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.35.8882"
 * >10.1.1.35.8882</a></div></div></li>
 * <li><div><span id="cite_JMG2004EAOHFTS" /><a
 * href="https://en.wikipedia.org/wiki/David_S._Johnson">David Stifler
 * Johnson</a> and&nbsp;<a
 * href="https://www.amherst.edu/people/facstaff/lamcgeoch">Lyle A.
 * McGeoch</a>: <span style="font-weight:bold">&ldquo;Experimental Analysis
 * of Heuristics for the STSP,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">The Traveling Salesman
 * Problem and its Variations</span>, chapter 369&ndash;443, pages
 * 369&ndash;443, <a href=
 * "http://www.rhul.ac.uk/computerscience/staffdirectory/ggutin/home.aspx">
 * Gregory Z. Gutin</a> and&nbsp;<a
 * href="http://www.sfu.ca/~apunnen/">Abraham P. Punnen</a>, editors,
 * volume 12 of Combinatorial Optimization, Norwell, MA, USA: Kluwer
 * Academic Publishers. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0306482134">0-306-48213-4</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9781402006647">978-1-4020
 * -0664-7</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/0-306-48213-4_9"
 * >10.1007/0-306-48213-4_9</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/55085594">55085594</a>; Google Book
 * ID:&nbsp;<a
 * href="http://books.google.com/books?id=TRYkPg_Xf20C">TRYkPg_Xf20C</a>.
 * <div>link: [<a
 * href="http://www2.research.att.com/~dsj/papers/stspchap.pdf">1</a>];
 * CiteSeer<sup>x</sup><sub style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href=
 * "http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.15.9438">10.1
 * .1.15 .9438</a></div></div></li>
 * <li><div><span id="cite_OR2006TSLANDSFTSP" /><a
 * href="http://www.researchgate.net/profile/Colin_Osterman/">Colin
 * Osterman</a> and&nbsp;<a
 * href="http://faculty.bus.olemiss.edu/crego/">C&#233;sar Rego</a>: <span
 * style="font-weight:bold">&ldquo;The Satellite List and New Data
 * Structures for Traveling Salesman Problems,&rdquo;</span> <span
 * style="font-style:italic;font-family:cursive;">Technical Report</span>
 * Number&nbsp;HCES-06-03, March&nbsp;2006; published by University, MS,
 * USA: University of Mississippi, School of Business Administration,
 * Hearin Center for Enterprise Science. <div>links: [<a href=
 * "http://www.akira.ruc.dk/~keld/teaching/algoritmedesign_f08/Artikler/02/Osterman03.pdf"
 * >1</a>] and&nbsp;[<a href=
 * "http://faculty.bus.olemiss.edu/crego/papers/HCES-06-03%20Paper%20Revised.pdf"
 * >2</a>]; CiteSeer<sup>x</sup><sub
 * style="font-style:italic">&#946;</sub>:&nbsp;<a href=
 * "http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.135.4730"
 * >10.1.1.135.4730</a></div></div></li>
 * <li><div><span id="cite_P1996GAFTTSP" /><a
 * href="http://www.iro.umontreal.ca/~potvin/">Jean-Yves Potvin</a>: <span
 * style="font-weight:bold">&ldquo;Genetic Algorithms for the Traveling
 * Salesman Problem,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Annals of Operations
 * Research</span> 63(3):337&ndash;370, June&nbsp;1, 1996; published by
 * Dordrecht, Netherlands: Springer Netherlands and&nbsp;Amsterdam, The
 * Netherlands: J. C. Baltzer AG, Science Publishers. doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/BF02125403">10.1007/BF02125403</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/02545330">0254-5330</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/15729338">1572-9338</a>.
 * <div>CiteSeer<sup>x</sup><sub
 * style="font-style:italic">&#946;</sub>:&nbsp;<a href
 * ="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.93.2179"
 * >10.1.1.93 .2179</a></div></div></li>
 * <li><div><span id="cite_GGRVG1985GAFTT" /><a
 * href="http://www.biostat.pitt.edu/directory/bios/grefenstette.asp">John
 * J. Grefenstette</a>, Rajeev Gopal, <a href=
 * "http://academics.hamilton.edu/computer_science/brosmait/index.html"
 * >Brian J. Rosmaita</a>, and&nbsp;<a
 * href="http://www.cs.indiana.edu/~vgucht/">Dirk Van Gucht</a>: <span
 * style="font-weight:bold">&ldquo;Genetic Algorithms for the
 * TSP,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the 1st
 * International Conference on Genetic Algorithms and their Applications
 * (ICGA'85)</span>, June&nbsp;24&ndash;26, 1985, Pittsburgh, PA, USA:
 * Carnegy Mellon University (CMU), pages 160&ndash;168, <a
 * href="http://www.biostat.pitt.edu/directory/bios/grefenstette.asp">John
 * J. Grefenstette</a>, editor, Hillsdale, NJ, USA: Lawrence Erlbaum
 * Associates. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0805804269">0-8058-0426-9</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780805804263">978-0-8058
 * -0426-3</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/19702892">19702892</a></div></li>
 * <li><div><span id="cite_ORG2005TSLARDLL" /><a
 * href="http://www.researchgate.net/profile/Colin_Osterman/">Colin
 * Osterman</a>, <a href="http://faculty.bus.olemiss.edu/crego/">C&#233;sar
 * Rego</a>, and&nbsp;<a href=
 * "http://www.researchgate.net/profile/Dorabela_Gamboa/publications/"
 * >Dorabela Gamboa</a>: <span style="font-weight:bold">&ldquo;The
 * Satellite List: A Reversible Doubly-Linked List,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the 7th
 * International Conference on Adaptive and Natural Computing Algorithms
 * (ICANNGA'05)</span>, March&nbsp;21&ndash;23, 2005, Coimbra, Portugal:
 * University of Coimbra, pages 542&ndash;545, <a
 * href="https://www.cisuc.uc.pt/people/show/2020">Bernardete Ribeiro</a>,
 * Rudolf F. Albrecht, <a
 * href="http://www.fri.uni-lj.si/en/andrej-dobnikar/">Andrej Dobnikar</a>,
 * David W. Pearson, and&nbsp;Nigel C. Steele, editors, Vienna, Austria:
 * Springer Verlag GmbH. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783211273890"
 * >978-3-211-27389-0</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/3-211-27389-1_131">10.1007/3-211-
 * 27389-1_131</a>; Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=FvMHsHo8DJcC">FvMHsHo8DJcC</a>;
 * further information: [<a href="http://icannga05.dei.uc.pt/">1</a>]</div>
 * </li>
 * <li><div><span id="cite_EM300ELEMENTS" /><span
 * style="font-style:italic;font-family:cursive;"
 * >&ldquo;$&#931;&#964;&#959;&#953;&#967;&#949;&#8150;&#945;$ (Stoicheia,
 * Elements),&rdquo;</span> 300&nbsp;BC, Euclid of Alexandria, editor,
 * Alexandria, Greece: self-published. Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=jeUGAAAAYAAJ">jeUGAAAAYAAJ</a>
 * and&nbsp;<a
 * href="http://books.google.com/books?id=PdZg5lSy1rUC">PdZg5lSy1rUC</a>.
 * <div>links: [<a
 * href="http://aleph0.clarku.edu/~djoyce/java/elements/elements.html"
 * >1</a>], [<a href="http://www.gutenberg.org/etext/21076">2</a>], [<a
 * href="http://farside.ph.utexas.edu/euclid.html">3</a>], [<a
 * href="http://en.wikipedia.org/wiki/Image:Euclid-Elements.pdf">4</a>],
 * and&nbsp;[<a
 * href="http://en.wikipedia.org/wiki/Euclid%27s_Elements">5</a
 * >]</div></div></li>
 * <li><div><span id="cite_R2000TQAA" /><a
 * href="http://comopt.ifi.uni-heidelberg.de/people/reinelt/">Gerhard
 * Reinelt</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;TSPLIB: Questions
 * and Answers,&rdquo;</span> (Website), November&nbsp;9, 2000. <div>link:
 * [<a href=
 * "http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/TSPFAQ.html"
 * >1</a>]</div></div></li>
 * <li><div><span id="cite_GJSB2005JAVALS" /><a
 * href="http://de.wikipedia.org/wiki/James_Gosling">James Gosling</a>, <a
 * href="http://en.wikipedia.org/wiki/Bill_Joy">William Nelson Joy</a>, <a
 * href="http://en.wikipedia.org/wiki/Guy_L._Steele,_Jr.">Guy Lewis Steele
 * Jr.</a>, and&nbsp;<a href="http://bracha.org/">Gilad Bracha</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;The Java&#8482;
 * Language Specification,&rdquo;</span> May&nbsp;2005, The Java Series,
 * Upper Saddle River, NJ, USA: Prentice Hall International Inc., Santa
 * Clara, CA, USA: Sun Microsystems Press (SMP), and&nbsp;Reading, MA, USA:
 * Addison-Wesley Professional. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0321246780">0-321-24678-0</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780321246783">978-0321246783</a>.
 * <div>link: [<a
 * href="http://java.sun.com/docs/books/jls/">1</a>]</div></div></li>
 * <li><div><span id="cite_GMG1996JAVAJLE" /><a
 * href="http://de.wikipedia.org/wiki/James_Gosling">James Gosling</a>
 * and&nbsp;<a href="http://www.trilithon.com/">Henry McGilton</a>: <span
 * style="font-weight:bold">&ldquo;The Java Language Environment &#8210; A
 * White Paper,&rdquo;</span> <span
 * style="font-style:italic;font-family:cursive;">Technical Report</span>
 * May&nbsp;1996; published by Santa Clara, CA, USA: Sun Microsystems,
 * Inc.. <div>link: [<a
 * href="http://java.sun.com/docs/white/langenv/">1</a>]</div></div></li>
 * <li><div><span id="cite_JAVA2PSEAS" />Santa Clara, CA, USA: Sun
 * Microsystems, Inc.: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Java&#8482; 2
 * Platform Standard Edition 5.0 &#8210; API Specification,&rdquo;</span>
 * October&nbsp;19, 2010, Redwood Shores, CA, USA: Oracle Corporation</div>
 * </li>
 * <li><div><span id="cite_S2002JABG" /><a
 * href="http://www.herbschildt.com/">Herbert Schildt</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Java 2: A
 * Beginner's Guide,&rdquo;</span> 2002, Essential Skills for First-Time
 * Programmers, Maidenhead, England, UK: McGraw-Hill Ltd.. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0072225130">0072225130</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780072225136">9780072225136</a>;
 * Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=YWDJJGYaLG4C"
 * >YWDJJGYaLG4C</a></div></li>
 * <li><div><span id="cite_S2003JPGFP" /><a
 * href="http://www.linkedin.com/pub/michael-sikora/9/1a1/525">Zbigniew
 * Michael Sikora</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Java: Practical
 * Guide for Programmers,&rdquo;</span> 2003, Morgan Kaufmann Practical
 * Guides, San Francisco, CA, USA: Morgan Kaufmann Publishers Inc..
 * ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/1558609091">1558609091</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9781558609099">9781558609099</a>;
 * Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=YQLj_AsVN9QC"
 * >YQLj_AsVN9QC</a></div></li>
 * </ol>
 */
final class _2DGeoDistanceInt extends _2DIntCoords {

  /**
   * create
   * 
   * @param n
   *          the dimension
   */
  _2DGeoDistanceInt(final int n) {
    super(n);
  }

  /** {@inheritDoc} */
  @Override
  public final int distance(final int i, final int j) {

    if (i == j) {
      return 0;
    }

    final int[] m;
    int a;
    int x, y;

    final double latA, longA, latB, longB, q1, q2, q3;

    m = this.m_coords;

    a = ((i - 1) << 1);
    x = m[a++];
    y = m[a];
    latA = ((3.141592d * x) / 180d);
    longA = ((3.141592d * y) / 180d);

    a = ((j - 1) << 1);
    x = m[a++];
    y = m[a];
    latB = ((3.141592d * x) / 180d);
    longB = ((3.141592d * y) / 180d);

    q1 = Math.cos(longA - longB);
    q2 = Math.cos(latA - latB);
    q3 = Math.cos(latA + latB);

    return ((int) ((6378.388d * Math
        .acos(0.5d * (((1d + q1) * q2) - ((1d - q1) * q3)))) + 1d));
  }

  /**
   * Compute the total round-trip distance of a complete candidate solution
   * to the TSP. This function does not just add up distances like what the
   * implementation in
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer#evaluate(int[])}
   * is doing. Instead, it is implemented in a way that reduces memory
   * access and maybe even computations. It therefore should be faster than
   * using
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer#distance(int,int)}
   * {@code n} times.
   * 
   * @param nodes
   *          The permutation of the nodes from {@code 1..n}
   * @return the total distance sum
   */
  @Override
  public final long evaluate(final int[] nodes) {
    int a, old_i;
    long totalDist;
    final int[] m;
    int x, y;

    double lat1, long1, lat2, long2, q1, q2, q3;

    old_i = nodes[nodes.length - 1];
    totalDist = 0l;
    m = this.m_coords;

    a = ((old_i - 1) << 1);
    x = m[a++];
    y = m[a];
    lat2 = ((3.141592d * x) / 180d);
    long2 = ((3.141592d * y) / 180d);

    for (final int cur_i : nodes) {
      a = ((cur_i - 1) << 1);

      x = m[a++];
      y = m[a];
      lat1 = ((3.141592d * x) / 180d);
      long1 = ((3.141592d * y) / 180d);

      q1 = Math.cos(long2 - long1);
      q2 = Math.cos(lat2 - lat1);
      q3 = Math.cos(lat2 + lat1);

      totalDist += ((int) ((6378.388d * Math
          .acos(0.5d * (((1d + q1) * q2) - ((1d - q1) * q3)))) + 1d));
      long2 = long1;
      lat2 = lat1;

      old_i = cur_i;
    }

    return totalDist;
  }

  /**
   * Compute the total round-trip distance of a complete candidate solution
   * in adjacency list representation&nbsp;[<a href="#cite_GGRVG1985GAFTT"
   * style="font-weight:bold">8</a>, <a href="#cite_LKMUB1999GAFTTSPARORAO"
   * style="font-weight:bold">4</a>, <a href="#cite_P1996GAFTTSP"
   * style="font-weight:bold">7</a>] to the TSP. This function does not
   * just add up distances like what the implementation in
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer#evaluateAdj(int[])}
   * is doing. Instead, it is implemented in a way that reduces memory
   * access and maybe even computations. It therefore should be faster than
   * using
   * {@link org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer#distance(int,int)}
   * {@code n} times.
   * 
   * @param adj
   *          The adjacency list representation of the solution
   * @return the total distance sum
   */
  @Override
  public final long evaluateAdj(final int[] adj) {
    int a, old_i, cur_i;
    long totalDist;
    final int[] m;
    int x, y;

    double lat1, long1, lat2, long2, q1, q2, q3;

    old_i = 1;
    totalDist = 0l;
    m = this.m_coords;

    a = ((old_i - 1) << 1);
    x = m[a++];
    y = m[a];
    lat2 = ((3.141592d * x) / 180d);
    long2 = ((3.141592d * y) / 180d);

    for (;;) {
      cur_i = adj[old_i - 1];
      a = ((cur_i - 1) << 1);

      x = m[a++];
      y = m[a];
      lat1 = ((3.141592d * x) / 180d);
      long1 = ((3.141592d * y) / 180d);

      q1 = Math.cos(long2 - long1);
      q2 = Math.cos(lat2 - lat1);
      q3 = Math.cos(lat2 + lat1);

      totalDist += ((int) ((6378.388d * Math
          .acos(0.5d * (((1d + q1) * q2) - ((1d - q1) * q3)))) + 1d));
      long2 = long1;
      lat2 = lat1;

      if (cur_i == 1) {
        return totalDist;
      }
      old_i = cur_i;
    }
  }

  /** {@inheritDoc} */
  @Override
  public final void print(final PrintWriter out) {
    super.print(out);
    this._print(out, _Constants.GEO_STR);
  }
}
