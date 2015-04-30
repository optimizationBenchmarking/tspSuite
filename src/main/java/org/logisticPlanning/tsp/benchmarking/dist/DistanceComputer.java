package org.logisticPlanning.tsp.benchmarking.dist;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;

/**
 * <p>
 * The base class for everything that can compute distances between two
 * nodes in a TSP. From this class, we derive specialized sub-classes for
 * each situation possible in the TSPLib benchmark set (see <a
 * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
 * >TSPLib</a>)&nbsp;[<a href="#cite_DACO1995TSPLIB"
 * style="font-weight:bold">1</a>, <a href="#cite_R1995T9"
 * style="font-weight:bold">2</a>, <a href="#cite_R1991ATSPL"
 * style="font-weight:bold">3</a>, <a href="#cite_W2003ROCFTB"
 * style="font-weight:bold">4</a>]. The basic idea of the sub-classes
 * provided in this package is that we want to squeeze each bit of
 * performance out of Java that we can by implementing distance computers
 * as specialized as possible.
 * </p>
 * <p>
 * Solutions to the TSP can be represented with different data
 * structures&nbsp;[<a href="#cite_FJMGO1995DSFTS"
 * style="font-weight:bold">5</a>, <a href="#cite_LKMUB1999GAFTTSPARORAO"
 * style="font-weight:bold">6</a>, <a href="#cite_JMG2004EAOHFTS"
 * style="font-weight:bold">7</a>, <a href="#cite_OR2006TSLANDSFTSP"
 * style="font-weight:bold">8</a>], each of which having distinctive
 * advantages and drawbacks. The
 * <em><a href="http://www.logisticPlanning.org/tsp">TSP Suite</a></em> can
 * deal with different representations for the candidate solutions of a
 * TSP:
 * </p>
 * <ol>
 * <li id="pathRepresentation">
 * <p>
 * The <em>path representation</em>&nbsp;[<a
 * href="#cite_LKMUB1999GAFTTSPARORAO" style="font-weight:bold">6</a>, <a
 * href="#cite_P1996GAFTTSP" style="font-weight:bold">9</a>] is probably
 * the most natural representation of a tour. Here, a tour is represented
 * as a permutation of the numbers from {@code 1} to {@code n}. If city
 * {@code i} is the {@code j}<sup>th</sup> element of the list, then this
 * city {@code i} is the {@code j}<sup>th</sup> city to be visited. Hence,
 * the tour
 * <code>3&rarr;2&rarr;4&rarr;1&rarr;7&rarr;5&rarr;8&rarr;6&rarr;3</code>
 * is simply represented by <code>new int[] {3, 2, 4, 1, 7, 5, 8, 6}</code>
 * &nbsp;[<a href="#cite_LKMUB1999GAFTTSPARORAO"
 * style="font-weight:bold">6</a>, <a href="#cite_P1996GAFTTSP"
 * style="font-weight:bold">9</a>].
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
 * href="#cite_GGRVG1985GAFTT" style="font-weight:bold">10</a>, <a
 * href="#cite_LKMUB1999GAFTTSPARORAO" style="font-weight:bold">6</a>, <a
 * href="#cite_P1996GAFTTSP" style="font-weight:bold">9</a>] a tour is also
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
 * style="font-weight:bold">8</a>, <a href="#cite_ORG2005TSLARDLL"
 * style="font-weight:bold">11</a>] are a clever data structure for
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
 * <li><div><span id="cite_DACO1995TSPLIB" /><a
 * href="http://comopt.ifi.uni-heidelberg.de/people/reinelt/">Gerhard
 * Reinelt</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;
 * TSPLIB,&rdquo;</span> (Website), 1995, Heidelberg, Germany: Office
 * Research Group Discrete Optimization, Ruprecht Karls University of
 * Heidelberg. <div>link: [<a
 * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
 * >1</a>]</div></div></li>
 * <li><div><span id="cite_R1995T9" /><a
 * href="http://comopt.ifi.uni-heidelberg.de/people/reinelt/">Gerhard
 * Reinelt</a>: <span style="font-weight:bold">&ldquo;TSPLIB
 * 95,&rdquo;</span> <span
 * style="font-style:italic;font-family:cursive;">Technical Report</span>
 * 1995; published by Heidelberg, Germany: Universit&#228;t Heidelberg,
 * Institut f&#252;r Mathematik. <div>link: [<a
 * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/DOC.PS"
 * >1</a>]</div></div></li>
 * <li><div><span id="cite_R1991ATSPL" /><a
 * href="http://comopt.ifi.uni-heidelberg.de/people/reinelt/">Gerhard
 * Reinelt</a>: <span style="font-weight:bold">&ldquo;TSPLIB &#8212; A
 * Traveling Salesman Problem Library,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">ORSA Journal on
 * Computing</span> 3(4):376&ndash;384, Fall&nbsp;1991; published by
 * Operations Research Society of America (ORSA). doi:&nbsp;<a
 * href="http://dx.doi.org/10.1287/ijoc.3.4.376">10.1287/ijoc.3.4.376</a>;
 * OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/60628815">60628815</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/08991499">0899-1499</a></div></li>
 * <li><div><span id="cite_W2003ROCFTB" /><a
 * href="http://www2.isye.gatech.edu/~wcook/">William John Cook</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Results of
 * Concorde for TSPLib Benchmark,&rdquo;</span> (Website),
 * December&nbsp;2003, Atlanta, GA, USA: Georgia Institute of Technology,
 * H. Milton Stewart School of Industrial and Systems Engineering.
 * <div>link: [<a
 * href="http://www.tsp.gatech.edu/concorde/benchmarks/bench99.html"
 * >1</a>]</div></div></li>
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
 * </ol>
 * 
 * 
 @author <em><a href="http://www.it-weise.de/">Thomas Weise</a></em>,
 *         Email:&nbsp;<a
 *         href="mailto:tweise@ustc.edu.cn">tweise@ustc.edu.cn</a>
 */
public abstract class DistanceComputer extends _Constants {

  /** the number of nodes */
  protected final int m_n;

  /**
   * the distance computer
   * 
   * @param n
   *          the dimension
   */
  protected DistanceComputer(final int n) {
    super();
    if (n <= 1) {
      throw new IllegalArgumentException("Invalid dimension: " + n); //$NON-NLS-1$
    }
    this.m_n = n;
  }

  /**
   * Compute the distance between two cities or nodes. All distances are
   * 1-based, meaning valid node/city indices are in {@code 1..}
   * {@link #n()}.
   * 
   * @param i
   *          the first city (or node), this index is 1-based
   * @param j
   *          the second city (or node), this index is 1-based
   * @return the integer distance between the two cities or nodes
   */
  public abstract int distance(final int i, final int j);

  /**
   * <p>
   * Compute the total round-trip distance of a complete candidate solution
   * to the TSP.
   * <p>
   * The difference to {@link #evaluateAdj(int[])} is that
   * {@link #evaluate(int[])} is for candidate solutions in the path
   * representation&nbsp;[<a href="#cite_LKMUB1999GAFTTSPARORAO"
   * style="font-weight:bold">6</a>, <a href="#cite_P1996GAFTTSP"
   * style="font-weight:bold">9</a>] whereas {@link #evaluateAdj(int[])} is
   * for solutions in the the adjacency list representation&nbsp;[<a
   * href="#cite_GGRVG1985GAFTT" style="font-weight:bold">10</a>, <a
   * href="#cite_LKMUB1999GAFTTSPARORAO" style="font-weight:bold">6</a>, <a
   * href="#cite_P1996GAFTTSP" style="font-weight:bold">9</a>].
   * </p>
   * 
   * @param nodes
   *          The permutation of the nodes from {@code 1..n}
   * @return the total distance sum
   */
  public long evaluate(final int[] nodes) {
    int old;
    long x;

    old = nodes[nodes.length - 1];
    x = 0l;
    for (final int i : nodes) {
      x += this.distance(old, i);
      old = i;
    }

    return x;
  }

  /**
   * <p>
   * Evaluate a given candidate solution in adjacency list
   * representation&nbsp;[<a href="#cite_GGRVG1985GAFTT"
   * style="font-weight:bold">10</a>, <a
   * href="#cite_LKMUB1999GAFTTSPARORAO" style="font-weight:bold">6</a>, <a
   * href="#cite_P1996GAFTTSP" style="font-weight:bold">9</a>], i.e.,
   * calculate the total length of the circular tour represented by the
   * adjacency list {@code adj}.
   * </p>
   * <p>
   * The difference to {@link #evaluate(int[])} is that
   * {@link #evaluateAdj(int[])} is for candidate solutions in the the
   * adjacency list representation&nbsp;[<a href="#cite_GGRVG1985GAFTT"
   * style="font-weight:bold">10</a>, <a
   * href="#cite_LKMUB1999GAFTTSPARORAO" style="font-weight:bold">6</a>, <a
   * href="#cite_P1996GAFTTSP" style="font-weight:bold">9</a>] whereas
   * {@link #evaluate(int[])} is for solutions in the path
   * representation&nbsp;[<a href="#cite_LKMUB1999GAFTTSPARORAO"
   * style="font-weight:bold">6</a>, <a href="#cite_P1996GAFTTSP"
   * style="font-weight:bold">9</a>].
   * </p>
   * 
   * @param adjacencyList
   *          the adjacency list representing the candidate solution to be
   *          evaluated
   * @return the total resulting tour length of {@code adjacencyList}
   */
  public long evaluateAdj(final int[] adjacencyList) {
    long sum;
    int station, last;

    sum = 0l;
    last = 1;
    for (;;) {
      station = adjacencyList[last - 1];
      sum += this.distance(last, station);
      if (station == 1) {
        break;
      }
      last = station;
    }

    return sum;
  }

  /**
   * Obtain the number of cities.
   * 
   * @return the number of cities
   */
  public final int n() {
    return this.m_n;
  }

  /**
   * Print the contents of this distance measuring object.
   * 
   * @param out
   *          the {@link java.io.PrintWriter}
   */
  public void print(final PrintWriter out) {
    _Constants.putTuple(_Constants.DIMENSION_STR,
        String.valueOf(this.m_n), out);
  }

  /**
   * Print the contents of this distance measuring object to a writer.
   * 
   * @param w
   *          the {@link java.io.Writer}
   * @throws IOException
   *           if io fails
   */
  @SuppressWarnings("unused")
  public final void print(final Writer w) throws IOException {
    try (PrintWriter pw = new PrintWriter(w)) {
      this.print(pw);
    }
  }

  /**
   * Print the contents of this distance measuring object to an output
   * stream.
   * 
   * @param s
   *          the output stream
   * @throws IOException
   *           if io fails
   */
  public final void print(final OutputStream s) throws IOException {
    try (OutputStreamWriter w = new OutputStreamWriter(s)) {
      this.print(w);
    }
  }

  /**
   * Print the contents of this distance measuring object to a file
   * 
   * @param f
   *          the file
   * @throws IOException
   *           if io fails
   */
  public final void print(final File f) throws IOException {
    try (FileWriter fw = new FileWriter(f)) {
      this.print(fw);
    }
  }

  /**
   * Load the contents of this distance measuring object.
   * 
   * @param in
   *          the {@link java.io.BufferedReader}
   * @throws IOException
   *           on failure
   */
  @SuppressWarnings("unused")
  void load(final BufferedReader in) throws IOException {
    //
  }

  /** {@inheritDoc} */
  @Override
  public final String toString() {
    try (final StringWriter sw = new StringWriter()) {
      this.print(sw);
      return sw.toString();
    } catch (final Throwable tt) {
      tt.printStackTrace();// should not happen
    }
    return "-- error --"; //$NON-NLS-1$
  }

  /**
   * <p>
   * Load a distance computer from a buffered reader. The procedure will
   * automatically detect the right internal representation. The distance
   * data may be represented as matrix or as coordinate list on which a
   * function is applied.
   * </p>
   * <p>
   * In the latter case, if the number of nodes/cities is below or equal to
   * the provided parameter {@code matrixLimitDim}, then the loader will
   * try to transform the coordinate list into a (symmetric) distance
   * matrix. If during this process an {@link java.lang.OutOfMemoryError}
   * occurs, the original list is returned, other the newly created
   * distance matrix.
   * </p>
   * <p>
   * In the case that the data to be loaded is indeed a coordinate list
   * (regardless whether it will be transformed to a matrix later), there
   * is another chance for improving memory consumption and performance: If
   * it is known that all coordinates in the coordinate lists are integer,
   * then {@code intHint} can be set to {@code true}. In this case, instead
   * of loading {@code double} coordinates, they will be loaded as
   * {@code int}, which reduces memory consumption and speeds up distance
   * computation. However, this process is dangerous: if a single one of
   * the coordinates specified in the stream is a floating point number,
   * the loading will fail with a {@link java.io.IOException}.
   * Additionally, be aware that the range of integers limited at most
   * {@link java.lang.Integer#MAX_VALUE}. If the distances used exceed this
   * range, the results will be wrong.
   * </p>
   * <p>
   * Some problems which are actually symmetric TSPs nevertheless are
   * stored as full matrices in TSPLib. If the loader does not know that
   * the problem is actually symmetric, it will load it as asymmetric
   * problem and hence wasted more than half of the occupied memory plus
   * slow down distance computation since the cache cannot be used
   * efficiently. Therefore, if you are definitely sure that the problem is
   * symmetric, you may set {@code symmetricHint} to {@code true}. In this
   * case, only a triangular matrix is used even if a full matrix is
   * specified. If you set {@code symmetricHint=true} for a problem which
   * is asymmetric, you will get wrong results or even an
   * {@link java.io.IOException} on loading.
   * </p>
   * 
   * @param br
   *          the buffered reader
   * @param intHint
   *          if this parameter is {@code true} and the input defines a
   *          list of coordinates, the coordinates are assumed to be
   *          integer valued. This can speed up loading and distance
   *          computations and saves memory. It will crash when the
   *          coordinates are actually floating point numbers. This is only
   *          available for internal purposes.
   * @param matrixLimitDim
   *          the highest dimension for which coordinate lists will
   *          automatically be transformed to matrices.
   * @param symmetricHint
   *          Is the problem definitely symmetric?
   * @return the distance computer
   * @throws IOException
   *           if i/o fails or the stream is not correctly formatted
   */
  public static final DistanceComputer read(final BufferedReader br,
      final int matrixLimitDim, final boolean intHint,
      final boolean symmetricHint) throws IOException {
    String s, t, ewt, ewf;
    ArrayList<String> pb;
    int dimension, have, i;
    DistanceComputer chosen;
    _DistanceMatrixSymmetric chosen2;

    dimension = -1;
    pb = null;
    ewt = ewf = null;
    have = 0;
    while ((have < 2) && ((s = br.readLine()) != null)) {
      s = s.trim();
      if (s.length() <= 0) {
        continue;
      }
      i = s.indexOf(':');

      if (i > 0) {
        t = s.substring(0, i).trim();
        if (t.length() <= 0) {
          continue;
        }

        if (_Constants.DIMENSION_STR.equalsIgnoreCase(t)
            && (dimension == (-1))) {
          try {
            dimension = Integer.parseInt(s.substring(i + 1).trim());
          } catch (final Throwable z) {
            throw new IOException(z);
          }
          have++;
          continue;
        }

        if (_Constants.EDGE_WEIGHT_TYPE_STR.equalsIgnoreCase(t)) {
          if (ewt == null) {
            ewt = s.substring(i + 1).trim();
            if (ewt.length() <= 0) {
              ewt = null;
            } else {
              if (!(_Constants.EXPLICIT_STR.equalsIgnoreCase(ewt))) {
                have++;
              }
            }
          }
          continue;
        }

        if (_Constants.EDGE_WEIGHT_FORMAT_STR.equalsIgnoreCase(t)) {
          if (ewf == null) {
            ewf = s.substring(i + 1).trim();
            if (ewf.length() <= 0) {
              ewf = null;
            } else {
              have++;
            }
          }
        }
      }

      pb = _PushBackReader.pushBack(pb, s);
    }

    if ((have >= 2) && (dimension > 1)) {
      chosen = null;

      choose: {
        if (_Constants.EUCLIDEAN_2D_STR.equalsIgnoreCase(ewt)) {
          if (intHint) {
            chosen = new _2DEuclideanDistanceInt(dimension);
          } else {
            chosen = new _2DEuclideanDistanceDouble(dimension);
          }
          break choose;
        }

        if (_Constants.EUCLIDEAN_3D_STR.equalsIgnoreCase(ewt)) {
          if (intHint) {
            chosen = new _3DEuclideanDistanceInt(dimension);
          } else {
            chosen = new _3DEuclideanDistanceDouble(dimension);
          }
          break choose;
        }

        if (_Constants.MANHATTAN_2D_STR.equalsIgnoreCase(ewt)) {
          if (intHint) {
            chosen = new _2DManhattanDistanceInt(dimension);
          } else {
            chosen = new _2DManhattanDistanceDouble(dimension);
          }
          break choose;
        }

        if (_Constants.MANHATTAN_3D_STR.equalsIgnoreCase(ewt)) {
          if (intHint) {
            chosen = new _3DManhattanDistanceInt(dimension);
          } else {
            chosen = new _3DManhattanDistanceDouble(dimension);
          }
          break choose;
        }

        if (_Constants.MAXIMUM_2D_STR.equalsIgnoreCase(ewt)) {
          if (intHint) {
            chosen = new _2DMaximumDistanceInt(dimension);
          } else {
            chosen = new _2DMaximumDistanceDouble(dimension);
          }
          break choose;
        }

        if (_Constants.MAXIMUM_3D_STR.equalsIgnoreCase(ewt)) {
          if (intHint) {
            chosen = new _3DMaximumDistanceInt(dimension);
          } else {
            chosen = new _3DMaximumDistanceDouble(dimension);
          }
          break choose;
        }

        if (_Constants.EUCLIDEAN_CEIL_2D_STR.equalsIgnoreCase(ewt)) {
          if (intHint) {
            chosen = new _2DCeiledEuclideanDistanceInt(dimension);
          } else {
            chosen = new _2DCeiledEuclideanDistanceDouble(dimension);
          }
          break choose;
        }

        if (_Constants.EUCLIDEAN_PSEUDO_2D_STR.equalsIgnoreCase(ewt)) {
          if (intHint) {
            chosen = new _2DPseudoEuclideanDistanceInt(dimension);
          } else {
            chosen = new _2DPseudoEuclideanDistanceDouble(dimension);
          }
          break choose;
        }

        if (_Constants.XRAY_1_STR.equalsIgnoreCase(ewt)) {
          if (intHint) {
            chosen = new _3DXRay1DistanceInt(dimension);
          } else {
            chosen = new _3DXRay1DistanceDouble(dimension);
          }
          break choose;
        }

        if (_Constants.GEO_STR.equalsIgnoreCase(ewt)) {
          if (intHint) {
            chosen = new _2DGeoDistanceInt(dimension);
          } else {
            chosen = new _2DGeoDistanceDouble(dimension);
          }
          break choose;
        }

        if (_Constants.FULL_MATRIX_STR.equalsIgnoreCase(ewf)) {
          if (symmetricHint) {
            chosen = new _DistanceMatrixSymmetric(dimension);
          } else {
            chosen = new _DistanceMatrixAsymmetric(dimension);
          }
          break choose;
        }

        if (_Constants.LOWER_ROW_STR.equalsIgnoreCase(ewf) || //
            _Constants.UPPER_ROW_STR.equalsIgnoreCase(ewf) || //
            _Constants.LOWER_DIAG_ROW_STR.equalsIgnoreCase(ewf) || //
            _Constants.UPPER_DIAG_ROW_STR.equalsIgnoreCase(ewf) || //
            _Constants.LOWER_COL_STR.equalsIgnoreCase(ewf) || //
            _Constants.UPPER_COL_STR.equalsIgnoreCase(ewf) || //
            _Constants.LOWER_DIAG_COL_STR.equalsIgnoreCase(ewf) || //
            _Constants.UPPER_DIAG_COL_STR.equalsIgnoreCase(ewf)) {
          chosen = new _DistanceMatrixSymmetric(dimension);
          break choose;
        }
      }

      if (chosen != null) {
        chosen.load(_PushBackReader.wrap(pb, br));
        if (!(chosen instanceof _DistanceMatrix)) {
          if (dimension <= matrixLimitDim) {
            try {
              chosen2 = new _DistanceMatrixSymmetric(dimension);
              chosen2.fillFrom(chosen);
              chosen = chosen2;
            } catch (final OutOfMemoryError ome) {
              //
            }
          }
        }
        return chosen;
      }
    }

    throw new IOException("Error loading distance computer."); //$NON-NLS-1$
  }

  /**
   * <p>
   * Load a distance computer from a {@link java.io.Reader}. The procedure
   * will automatically detect the right internal representation. The
   * distance data may be represented as matrix or as coordinate list on
   * which a function is applied.
   * </p>
   * <p>
   * Please read the documentation of
   * {@link #read(BufferedReader, int, boolean, boolean)} for a detailed
   * explanation of the parameters.
   * </p>
   * 
   * @param r
   *          the reader
   * @param intHint
   *          if this parameter is {@code true} and the input defines a
   *          list of coordinates, the coordinates are assumed to be
   *          integer valued. This can speed up loading and distance
   *          computations and saves memory. It will crash when the
   *          coordinates are actually floating point numbers. This is only
   *          available for internal purposes.
   * @param matrixLimitDim
   *          the highest dimension for which coordinate lists will
   *          automatically be transformed to matrices.
   * @param symmetricHint
   *          Is the problem definitely symmetric?
   * @return the distance computer
   * @throws IOException
   *           if i/o fails or the stream is not correctly formatted
   */
  public static final DistanceComputer read(final Reader r,
      final int matrixLimitDim, final boolean intHint,
      final boolean symmetricHint) throws IOException {
    try (BufferedReader br = new BufferedReader(r)) {
      return DistanceComputer.read(br, matrixLimitDim, intHint,
          symmetricHint);
    }
  }

  /**
   * <p>
   * Load a distance computer from an {@link java.io.InputStream}. The
   * procedure will automatically detect the right internal representation.
   * The distance data may be represented as matrix or as coordinate list
   * on which a function is applied.
   * </p>
   * <p>
   * Please read the documentation of
   * {@link #read(BufferedReader, int, boolean, boolean)} for a detailed
   * explanation of the parameters.
   * </p>
   * 
   * @param is
   *          the input stream
   * @param intHint
   *          if this parameter is {@code true} and the input defines a
   *          list of coordinates, the coordinates are assumed to be
   *          integer valued. This can speed up loading and distance
   *          computations and saves memory. It will crash when the
   *          coordinates are actually floating point numbers. This is only
   *          available for internal purposes.
   * @param matrixLimitDim
   *          the highest dimension for which coordinate lists will
   *          automatically be transformed to matrices.
   * @param symmetricHint
   *          Is the problem definitely symmetric?
   * @return the distance computer
   * @throws IOException
   *           if i/o fails or the stream is not correctly formatted
   */
  public static final DistanceComputer read(final InputStream is,
      final int matrixLimitDim, final boolean intHint,
      final boolean symmetricHint) throws IOException {
    try (InputStreamReader r = new InputStreamReader(is)) {
      return DistanceComputer.read(r, matrixLimitDim, intHint,
          symmetricHint);
    }
  }

  /**
   * <p>
   * Load a distance computer from a {@link java.io.File}. The procedure
   * will automatically detect the right internal representation. The
   * distance data may be represented as matrix or as coordinate list on
   * which a function is applied.
   * </p>
   * <p>
   * Please read the documentation of
   * {@link #read(BufferedReader, int, boolean, boolean)} for a detailed
   * explanation of the parameters.
   * </p>
   * 
   * @param f
   *          the file
   * @param intHint
   *          if this parameter is {@code true} and the input defines a
   *          list of coordinates, the coordinates are assumed to be
   *          integer valued. This can speed up loading and distance
   *          computations and saves memory. It will crash when the
   *          coordinates are actually floating point numbers. This is only
   *          available for internal purposes.
   * @param matrixLimitDim
   *          the highest dimension for which coordinate lists will
   *          automatically be transformed to matrices.
   * @param symmetricHint
   *          Is the problem definitely symmetric?
   * @return the distance computer
   * @throws IOException
   *           if i/o fails or the stream is not correctly formatted
   */
  public static final DistanceComputer read(final File f,
      final int matrixLimitDim, final boolean intHint,
      final boolean symmetricHint) throws IOException {
    try (FileReader fr = new FileReader(f)) {
      return DistanceComputer.read(fr, matrixLimitDim, intHint,
          symmetricHint);
    }
  }
}
