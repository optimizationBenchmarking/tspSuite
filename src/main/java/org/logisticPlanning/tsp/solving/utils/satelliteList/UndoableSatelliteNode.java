package org.logisticPlanning.tsp.solving.utils.satelliteList;

/**
 * <p>
 * A mutable
 * {@link org.logisticPlanning.tsp.solving.utils.satelliteList.SatelliteNode
 * satellite node} for
 * {@link org.logisticPlanning.tsp.solving.utils.satelliteList.UndoableSatelliteList
 * satellite lists} that allow for undo-able modification of their nodes,
 * as needed for backtracking in local searches.
 * </p>
 * <h2>Satellite List Representation</h2>
 * <p>
 * A
 * {@link org.logisticPlanning.tsp.solving.utils.satelliteList.SatelliteList
 * satellite list}&nbsp;[<a href="#cite_OR2006TSLANDSFTSP"
 * style="font-weight:bold">1</a>, <a href="#cite_ORG2005TSLARDLL"
 * style="font-weight:bold">2</a>] is a representation for TSP solutions
 * which allows operations such as reversal (two-opt steps) to take place
 * in <em>O(1)</em>. This is much faster than in the case of the <a
 * href="#pathRepresentation"><em>path representation</em></a> or <a
 * href="#adjacencyRepresentation"><em>adjacency representation</em></a>,
 * which need <em>O(k)</em> for that where <em>k</em> is the length of the
 * segment to be reversed (and often <em>k=n</em>). A satellite list can be
 * translated
 * {@link org.logisticPlanning.tsp.solving.utils.satelliteList.SatelliteList#toPath(int[])
 * to} and
 * {@link org.logisticPlanning.tsp.solving.utils.satelliteList.SatelliteList#fromPath(int[])
 * from} the path or adjacency representation in <em>O(n)</em>.
 * </p>
 * <p>
 * {@link org.logisticPlanning.tsp.solving.utils.satelliteList.SatelliteList
 * Satellite lists} are here are implemented as objects backed by an array
 * of
 * {@link org.logisticPlanning.tsp.solving.utils.satelliteList.SatelliteNode}
 * . Each
 * {@link org.logisticPlanning.tsp.solving.utils.satelliteList.SatelliteNode}
 * represents a node in the TSP. Its
 * {@link org.logisticPlanning.tsp.solving.utils.satelliteList.SatelliteNode#id
 * id} corresponds to the node id. It furthermore has two pointers to two
 * other
 * {@link org.logisticPlanning.tsp.solving.utils.satelliteList.SatelliteNode}
 * s.
 * </p>
 * <p>
 * This is somewhat similar to a doubly linked list, with one significant
 * difference: In the doubly linked list, the two pointers have fixed
 * meanings. There is one pointer &quot;next&quot; which points to the next
 * node to visit in the tour and one pointer &quot;previous&quit; which
 * points to the node that was visited before the current node. If you want
 * to reverse a sub-sequence of a doubly linked list, you have to swap the
 * values of all next and previous pointers within (plus re-connect the
 * segment ends).
 * </p>
 * <p>
 * In a satellite list, the two pointers per node have no such predefined
 * meaning, instead they just point to the two
 * {@link org.logisticPlanning.tsp.solving.utils.satelliteList.SatelliteNode#getNeighbor(int)
 * neighboring nodes} in any possible order. They receive a meaning during
 * the traversal of the list. Let's say you arrive at a node {@code b} from
 * node {@code a}. Assume that the two pointers stored in {@code b} point
 * to {@code a} and {@code c}. Since you came from {@code a}, the next node
 * to visit cannot be {@code a} and must be {@code c}. Thus, you do not
 * need to know which pointer stands for the next or previous node by the
 * name of the pointer variable, instead it is simply clear from the
 * context. If you would arrive at {@code b} coming from {@code c}, you
 * would still see that it has two pointers to {@code a} and {@code c}, but
 * it would be obvious for you to continue your traversal towards {@code a}
 * . Reversing a sub-sequence of a satellite list thus just requires you to
 * re-connect both ends of the sequence, which happens in <em>O(1)</em>.
 * </p>
 * <p>
 * Such a satellite list can be represented as array of {@code int} of
 * length
 * <code>2*{@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction#n() n}</code>
 * . We chose to implement it as array of
 * {@link org.logisticPlanning.tsp.solving.utils.satelliteList.SatelliteNode}
 * objects of length
 * <code>{@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction#n() n}</code>
 * instead, because this allows us to extend the
 * {@link org.logisticPlanning.tsp.solving.utils.satelliteList.SatelliteNode}
 * class additional information in it. An example for this is the
 * {@link org.logisticPlanning.tsp.solving.utils.satelliteList.UndoableSatelliteList}
 * with
 * {@link org.logisticPlanning.tsp.solving.utils.satelliteList.UndoableSatelliteNode}
 * s. Here, each node can be tentatively disconnected from one or both of
 * its neighbors and can tentatively be connected to one or both of its
 * neighbors. These changes then can either be committed or undone. This is
 * suitable for implementing local search with backtracking.
 * </p>
 * <h3>Note on Benchmarking</h3>
 * <p>
 * Our benchmarking environment is sort of unfair towards the satellite
 * list and any other &quot;sophisticated&quot; representation, as it
 * requires a serialization to the path representation for each
 * improvement. This is necessary in order to be able to output the best
 * result in the end of the optimization process, but turns an
 * <em>O(1)</em> modification into a <em>O(n)</em> one. In the wild, a
 * satellite list thus can be expected to perform better.
 * </p>
 * <p>
 * On the other hand, a satellite list is most often used as a fast
 * replacement for a path representation. If an algorithm <code>A</code> is
 * faster than an algorithm <code>B</code> when both are using the path
 * representation, it can be assumed that the same is true if both use the
 * satellite representation. This sort of justifies our benchmarking
 * philosophy.
 * </p>
 * <h2>Representations in General</h2>
 * <p>
 * Solutions to the TSP can be represented with different data
 * structures&nbsp;[<a href="#cite_FJMGO1995DSFTS"
 * style="font-weight:bold">3</a>, <a href="#cite_LKMUB1999GAFTTSPARORAO"
 * style="font-weight:bold">4</a>, <a href="#cite_JMG2004EAOHFTS"
 * style="font-weight:bold">5</a>, <a href="#cite_OR2006TSLANDSFTSP"
 * style="font-weight:bold">1</a>], each of which having distinctive
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
 * href="#cite_P1996GAFTTSP" style="font-weight:bold">6</a>] is probably
 * the most natural representation of a tour. Here, a tour is represented
 * as a permutation of the numbers from {@code 1} to {@code n}. If city
 * {@code i} is the {@code j}<sup>th</sup> element of the list, then this
 * city {@code i} is the {@code j}<sup>th</sup> city to be visited. Hence,
 * the tour
 * <code>3&rarr;2&rarr;4&rarr;1&rarr;7&rarr;5&rarr;8&rarr;6&rarr;3</code>
 * is simply represented by <code>new int[] {3, 2, 4, 1, 7, 5, 8, 6}</code>
 * &nbsp;[<a href="#cite_LKMUB1999GAFTTSPARORAO"
 * style="font-weight:bold">4</a>, <a href="#cite_P1996GAFTTSP"
 * style="font-weight:bold">6</a>].
 * </p>
 * <p id="pathNormalForm">
 * There are multiple permutations that encode for the same tour in path
 * representation. In order to ease visual comparison of tours, the
 * {@link org.logisticPlanning.tsp.solving.utils.RepresentationUtils#appendPathInNormalForm(int[],java.lang.Appendable)
 * Normal Form} for path representation was defined. Here, the first
 * element is node {@code 1} and then the neighboring node with the lowest
 * index follows.
 * </p>
 * </li>
 * <li id="adjacencyRepresentation">
 * <p>
 * In the <em>adjacency representation</em>&nbsp;[<a
 * href="#cite_GGRVG1985GAFTT" style="font-weight:bold">7</a>, <a
 * href="#cite_LKMUB1999GAFTTSPARORAO" style="font-weight:bold">4</a>, <a
 * href="#cite_P1996GAFTTSP" style="font-weight:bold">6</a>] a tour is also
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
 * <li id="satelliteList">
 * <p>
 * {@link org.logisticPlanning.tsp.solving.utils.satelliteList.SatelliteList
 * Satellite lists}&nbsp;[<a href="#cite_OR2006TSLANDSFTSP"
 * style="font-weight:bold">1</a>, <a href="#cite_ORG2005TSLARDLL"
 * style="font-weight:bold">2</a>] are a clever data structure for
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
 * </ol>
 *
 * @since TSP Suite/0.9.8
 */
public class UndoableSatelliteNode extends SatelliteNode {

  /** has the edge to {@link #m_p1} been deleted? */
  private boolean m_p1_deleted;

  /** has the edge to {@link #m_p2} been deleted? */
  private boolean m_p2_deleted;

  /** the previous value of {@link #m_p1} */
  private SatelliteNode m_old_p1;
  /** the previous value of {@link #m_p2} */
  private SatelliteNode m_old_p2;

  /** the first cached node */
  private SatelliteNode m_cache_1;
  /** the second cached node */
  private SatelliteNode m_cache_2;

  /**
   * create
   *
   * @param pid
   *          the node if
   */
  protected UndoableSatelliteNode(final int pid) {
    super(pid);
  }

  /**
   * Commit all tentative changes. The data held for undo operations will
   * be dropped.
   */
  @Override
  public final void commit() {
    this.m_p1_deleted = false;
    this.m_p2_deleted = false;
    // if (this.m_cache_1 != null) { throw new IllegalStateException(); }//
    // TODO
    this.m_cache_1 = null;
    // if (this.m_cache_2 != null) { throw new IllegalStateException(); }//
    // TODO
    this.m_cache_2 = null;
    this.m_old_p1 = null;
    this.m_old_p2 = null;
  }

  /** {@inheritDoc} */
  @Override
  public final UndoableSatelliteNode getNeighbor(final int index) {
    if (index <= 0) {
      if (this.m_p1_deleted) {
        return null;
      }
      return ((UndoableSatelliteNode) (this.m_p1));
    }
    if (this.m_p2_deleted) {
      return null;
    }
    return ((UndoableSatelliteNode) (this.m_p2));
  }

  /**
   * Check whether the deletions of edges incident to this node is balanced
   * with the addition of edges connected to this node. In other words,
   * this function will return {@code true} if the same number of edges
   * that is connected to the node is added and deleted.
   *
   * @return {@code true} if the same amount of edges is added and deleted
   *         to this node.
   */
  @Override
  public final boolean isBalanced() {
    return ((this.m_p1 != null) && (this.m_p2 != null) && (this.m_cache_1 == null));
  }

  /**
   * Delete the connection to node {@code node} in a reversible way.
   *
   * @param node
   *          the node
   * @see #undoDisconnect(SatelliteNode)
   */
  @Override
  public final void doDisconnect(final SatelliteNode node) {

    if (node == this.m_p1) {// if node n is the m_p1 node, we delete it
      // if (this.m_old_p1 != null) { throw new IllegalStateException();
      // }//
      // TODO
      this.m_old_p1 = this.m_p1; // remember the old node m_p1 (for undo)
      this.m_p1_deleted = true; // mark the m_p1 connection as deleted
      this.m_p1 = this.m_cache_1; // pop waiting incoming connections
    } else {// if it is not m_p1, it must be m_p2
      // if (this.m_old_p2 != null) { throw new IllegalStateException();
      // }//
      // TODO
      // if (this.m_p2 != node) { throw new IllegalStateException(); }//
      // TODO
      this.m_old_p2 = this.m_p2; // remember the old node m_p2 (for undo)
      this.m_p2_deleted = true; // mark the m_p2 connection as deleted
      this.m_p2 = this.m_cache_1; // pop waiting incoming connections
    }
    this.m_cache_1 = this.m_cache_2;
    this.m_cache_2 = null;
  }

  /**
   * Undo the deletion of the connection to node {@code n}
   *
   * @param node
   *          the node
   * @see #doDisconnect(SatelliteNode)
   */
  @Override
  public final void undoDisconnect(final SatelliteNode node) {
    SatelliteNode cache;

    if (node == this.m_old_p1) { // n is the deleted m_p1
      cache = this.m_p1; // remember it
      this.m_p1 = node; // and undelete it
      this.m_p1_deleted = false; // kill the delete flag
      this.m_old_p1 = null; // remove the old m_p1 pointer
      if (this.m_p2 == null) { // maybe we can re-wire cache to m_p2
        this.m_p2 = cache;
        return;
      }
    } else { // n must be the deleted m_p2
      // if (this.m_old_p2 != node) { throw new IllegalStateException();
      // }//
      // TODO
      cache = this.m_p2; // remember it
      this.m_p2 = node; // and undelete it
      this.m_p2_deleted = false; // kill the delete flag
      this.m_old_p2 = null; // remove the old m_p2 pointer
      if (this.m_p1 == null) {// maybe we can re-wire cache to m_p1
        this.m_p1 = cache;
        return;
      }
    }

    // no re-wire possibility: enqueue connection into cache
    this.m_cache_2 = this.m_cache_1;
    this.m_cache_1 = cache;
  }

  /**
   * Connect this node to another node {@code node} in a reversible way.
   *
   * @param node
   *          the other node
   * @see #undoConnect(SatelliteNode)
   */
  @Override
  public final void doConnect(final SatelliteNode node) {
    if (this.m_p1 == null) { // no m_p1? connect directly
      this.m_p1 = node; //
    } else {
      if (this.m_p2 == null) { // no m_p2? connect directly
        this.m_p2 = node;
      } else { // m_p1 and m_p2? cache!
        // if (this.m_cache_2 != null) { throw new
        // IllegalStateException(); }//
        // TODO
        this.m_cache_2 = this.m_cache_1;
        this.m_cache_1 = node;
      }
    }
  }

  /**
   * Undo the connection to the given node {@code node}.
   *
   * @param node
   *          the node
   * @see #doConnect(SatelliteNode)
   */
  @Override
  public final void undoConnect(final SatelliteNode node) {
    if (this.m_p1 == node) { // connected to m_p1? disconnect
      this.m_p1 = this.m_cache_1;
    } else {
      if (this.m_p2 == node) { // connected to m_p2? disconnect
        this.m_p2 = this.m_cache_1;
      } else {
        if (this.m_cache_1 == node) { // cache 1? remove
          this.m_cache_1 = this.m_cache_2;
        } // otherwise it must have been in cache 2
        // else if (this.m_cache_2 != node) { throw new
        // IllegalStateException(); }// TODO
      }
    }
    // cache 2 must be set to null either way
    this.m_cache_2 = null;
  }

  /** {@inheritDoc} */
  @Override
  public final void toStringBuilder(final StringBuilder sb) {
    sb.append(this.id);

    sb.append('(');
    if (this.m_p1 != null) {
      sb.append(this.m_p1.id);
    } else {
      sb.append('x');
    }
    if (this.m_old_p1 != null) {
      sb.append('/');
      sb.append(this.m_old_p1.id);
    }

    sb.append(',');
    sb.append(' ');
    if (this.m_p2 != null) {
      sb.append(this.m_p2.id);
    } else {
      sb.append('x');
    }
    if (this.m_old_p2 != null) {
      sb.append('/');
      sb.append(this.m_old_p2.id);
    }

    if (this.m_cache_1 != null) {
      sb.append(' ');
      sb.append('[');
      sb.append(this.m_cache_1.id);
      if (this.m_cache_2 != null) {
        sb.append(',');
        sb.append(' ');
        sb.append(this.m_cache_2.id);
      }
      sb.append(']');
    }
    sb.append(')');
  }

  /**
   * This node is part of how many edges pending deletion?
   *
   * @return the number of edges pending deletion that this node is part of
   */
  public final int numberOfPendingDeletions() {
    return ((this.m_p1_deleted ? 1 : 0) + (this.m_p2_deleted ? 1 : 0));
  }

  /**
   * This node is part of how many edges pending additions?
   *
   * @return the number of edges pending addition that this node is part of
   */
  public final int numberOfPendingAdditions() {
    if (this.m_cache_2 != null) {
      return 2;
    }

    return (((this.m_p1_deleted && (this.m_p1 != null) ? 1 : 0)) + //
        ((this.m_p2_deleted && (this.m_p2 != null) ? 1 : 0)) + //
        ((this.m_cache_1 != null) ? 1 : 0));
  }

  /**
   * Is the given node a neighbor of this node or has it been a neighbor
   * before?
   *
   * @param node
   *          the node
   * @return {@code true} if it is a neighbor, {@code false} otherwise
   */
  public final boolean isRelated(final SatelliteNode node) {
    return ((this.m_p1 == node) || (this.m_p2 == node) || //
        (this.m_old_p1 == node) || (this.m_old_p2 == node) || //
        (this.m_cache_1 == node) || (this.m_cache_2 == node));
  }
}
