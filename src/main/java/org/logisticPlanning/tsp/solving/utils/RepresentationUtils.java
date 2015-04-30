package org.logisticPlanning.tsp.solving.utils;

import java.io.IOException;
import java.util.Arrays;

import org.logisticPlanning.tsp.solving.utils.edge.Edge;
import org.logisticPlanning.utils.utils.HashUtils;

/**
 * <p>
 * Static utility methods for dealing with different representations for
 * solutions of Traveling Salesman Problems (TSPs), such as the path or
 * adjacency representation.
 * </p>
 * <h2>Representations for the TSP</h2>
 * <p>
 * Solutions to the TSP can be represented with different data
 * structures&nbsp;[<a href="#cite_FJMGO1995DSFTS"
 * style="font-weight:bold">1</a>, <a href="#cite_LKMUB1999GAFTTSPARORAO"
 * style="font-weight:bold">2</a>, <a href="#cite_JMG2004EAOHFTS"
 * style="font-weight:bold">3</a>, <a href="#cite_OR2006TSLANDSFTSP"
 * style="font-weight:bold">4</a>], each of which having distinctive
 * advantages and drawbacks. The
 * <em><a href="http://www.logisticPlanning.org/tsp">TSP Suite</a></em> can
 * deal with different representations for the candidate solutions of a
 * TSP:
 * </p>
 * <ol>
 * <li id="pathRepresentation">
 * <p>
 * The <em>path representation</em>&nbsp;[<a
 * href="#cite_LKMUB1999GAFTTSPARORAO" style="font-weight:bold">2</a>, <a
 * href="#cite_P1996GAFTTSP" style="font-weight:bold">5</a>] is probably
 * the most natural representation of a tour. Here, a tour is represented
 * as a permutation of the numbers from {@code 1} to {@code n}. If city
 * {@code i} is the {@code j}<sup>th</sup> element of the list, then this
 * city {@code i} is the {@code j}<sup>th</sup> city to be visited. Hence,
 * the tour
 * <code>3&rarr;2&rarr;4&rarr;1&rarr;7&rarr;5&rarr;8&rarr;6&rarr;3</code>
 * is simply represented by <code>new int[] {3, 2, 4, 1, 7, 5, 8, 6}</code>
 * &nbsp;[<a href="#cite_LKMUB1999GAFTTSPARORAO"
 * style="font-weight:bold">2</a>, <a href="#cite_P1996GAFTTSP"
 * style="font-weight:bold">5</a>].
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
 * href="#cite_GGRVG1985GAFTT" style="font-weight:bold">6</a>, <a
 * href="#cite_LKMUB1999GAFTTSPARORAO" style="font-weight:bold">2</a>, <a
 * href="#cite_P1996GAFTTSP" style="font-weight:bold">5</a>] a tour is also
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
 * style="font-weight:bold">4</a>, <a href="#cite_ORG2005TSLARDLL"
 * style="font-weight:bold">7</a>] are a clever data structure for
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
 * @since TSP Suite/0.9.8
 */
public final class RepresentationUtils {

  /**
   * Check if two tours in <a href="#pathRepresentation">
   * <em>path representation</em></a> are equivalent from the perspective
   * of a symmetric TSP. Two paths in a symmetric TSP are equivalent if
   * they can be reversed or rotated to match each other. For example
   * {@code (1, 2, 3, 4)} &#8801; {@code (4, 3, 2, 1)} &#8801;
   * {@code (4, 1, 2, 3)} &#8801; {@code (3, 4, 1, 2)} &#8801;
   * {@code (2, 1, 4, 3)} but &#8802; {@code (1, 3, 2, 4)}.
   * 
   * @param path1
   *          the first tour in <a href="#pathRepresentation">
   *          <em>path representation</em></a>
   * @param path2
   *          the second tour in <a href="#pathRepresentation">
   *          <em>path representation</em></a>
   * @return {@code true} if both paths are equivalent, {@code false} if
   *         they are different
   */
  public static final boolean arePathsEquivalentSTSP(final int[] path1,
      final int[] path2) {
    final int n;
    int i, curP1, prevP1, prevP2;
    boolean dir;

    if ((path1 == null) || (path2 == null)) {
      return false;
    }

    if (path1 == path2) {
      return true;
    }

    if ((n = path1.length) != path2.length) {
      return false;
    }

    if (n <= 0) {
      return true;
    }

    prevP1 = path1[0];
    if (n <= 1) {
      return (prevP1 == path2[0]);
    }
    curP1 = path1[1];
    i = 0;

    prevP2 = path2[n - 1];
    findStartP2: {
      for (final int curP2 : path2) {
        if (curP2 == curP1) {
          dir = (prevP1 == prevP2);
          break findStartP2;
        }
        prevP2 = curP2;
        i++;
      }
      return false;
    }

    if (dir) {
      if ((--i) < 0) {
        i = (n - 1);
      }
      for (final int cur1 : path1) {
        if (cur1 != path2[i]) {
          return false;
        }
        if ((++i) >= n) {
          i = 0;
        }
      }
      return true;
    }

    if ((++i) >= n) {
      i = 0;
    }
    for (final int cur1 : path1) {
      if (cur1 != path2[i]) {
        return false;
      }
      if ((--i) < 0) {
        i = (n - 1);
      }
    }
    return true;
  }

  /**
   * Check whether a solution in <a href="#pathRepresentation">
   * <em>path representation</em></a> is equivalent to a solution in <a
   * href="#adjacencyRepresentation"><em>adjacency representation</em></a>
   * for a symmetric TSP. In this case, the tours {@code (1, 2, 3, 4)}
   * &#8801; {@code (4, 3, 2, 1)} in path representation are equivalent to
   * the adjacency lists {@code (2, 3, 4, 1)} and {@code (4, 1, 2, 3)}.
   * 
   * @param path
   *          the first tour, given in <a href="#pathRepresentation">
   *          <em>path representation</em></a>
   * @param adjacencyList
   *          the second tour, given in <a href="#adjacencyRepresentation">
   *          <em>adjacency representation</em></a>
   * @return {@code true} if both tours represent the same solution,
   *         {@code false} otherwise
   */
  @SuppressWarnings("incomplete-switch")
  public static final boolean arePathAndAdjacencyListEquivalentSTSP(
      final int[] path, final int[] adjacencyList) {

    final int n;
    int prev, dir;

    if ((path == null) || (adjacencyList == null)
        || ((n = path.length) != adjacencyList.length)) {
      return false;
    }

    if (n <= 0) {
      return true;
    }

    prev = path[n - 1];
    dir = 0;
    for (final int cur : path) {
      switch (dir) {
        case 0: {
          if (adjacencyList[prev - 1] == cur) {
            dir = 1;
          } else {
            if (adjacencyList[cur - 1] == prev) {
              dir = 2;
            } else {
              return false;
            }
          }
          break;
        }

        case 1: {
          if (adjacencyList[prev - 1] != cur) {
            return false;
          }
          break;
        }

        case 2: {
          if (adjacencyList[cur - 1] != prev) {
            return false;
          }
          break;
        }
      }

      prev = cur;
    }

    return true;
  }

  /**
   * Check if two tours in <a href="#adjacencyRepresentation">
   * <em>adjacency representation</em></a> are equivalent from the
   * perspective of a symmetric TSP.
   * 
   * @param adjacencyList1
   *          the first tour in <a href="#adjacencyRepresentation">
   *          <em>adjacency representation</em></a>
   * @param adjacencyList2
   *          the second tour in <a href="#adjacencyRepresentation">
   *          <em>adjacency representation</em></a>
   * @return {@code true} if both paths are equivalent, {@code false} if
   *         they are different
   */
  @SuppressWarnings("incomplete-switch")
  public static final boolean areAdjacencyListsEquivalentSTSP(
      final int[] adjacencyList1, final int[] adjacencyList2) {
    final int n;
    int prev, dir;

    if ((adjacencyList1 == null) || (adjacencyList2 == null)) {
      return false;
    }

    if (adjacencyList1 == adjacencyList2) {
      return true;
    }

    if ((n = adjacencyList1.length) != adjacencyList2.length) {
      return false;
    }

    if (n <= 0) {
      return true;
    }

    prev = 1;
    dir = 0;
    for (final int cur : adjacencyList1) {
      switch (dir) {
        case 0: {
          if (adjacencyList2[prev - 1] == cur) {
            dir = 1;
          } else {
            if (adjacencyList2[cur - 1] == prev) {
              dir = 2;
            } else {
              return false;
            }
          }
          break;
        }

        case 1: {
          if (adjacencyList2[prev - 1] != cur) {
            return false;
          }
          break;
        }

        case 2: {
          if (adjacencyList2[cur - 1] != prev) {
            return false;
          }
          break;
        }
      }

      prev++;
    }

    return true;
  }

  /**
   * Print a tour in <a href="#pathRepresentation">
   * <em>path representation</em></a> in normal form: Start at node
   * {@code 1} and continuing into the direction of its lowest valued
   * neighbor.
   * 
   * @param path
   *          the tour in <a href="#pathRepresentation">
   *          <em>path representation</em></a>
   * @param out
   *          the {@link java.lang.Appendable output destination} to print
   *          to
   * @throws IOException
   *           if IO fails
   */
  public static final void appendPathInNormalForm(final int[] path,
      final Appendable out) throws IOException {
    final int len;
    int i, j, step;

    len = path.length;
    for (i = len; (--i) >= 0;) {
      if (path[i] == 1) {
        break;
      }
    }

    step = (len - 1);
    if (path[(i + step) % len] > path[(i + 1) % len]) {
      step = 1;
    }

    for (j = len; (--j) >= 0;) {
      out.append(Integer.toString(path[i]));
      i = ((i + step) % len);
      if (j > 0) {
        out.append('\t');
      }
    }
  }

  /**
   * Convert a solution from <a href="#adjacencyRepresentation">
   * <em>adjacency representation</em></a> to <a
   * href="#pathRepresentation"> <em>path representation</em></a>. An
   * adjacency list is an integer array where the element at index
   * {@code (i-1)} stands for the city {@code i} and holds the index of the
   * city which should appear after that element. A path, too, is a
   * permutation represented as integer array. Here, the element at index
   * {@code i} will come at position {@code i} in the tour.
   * 
   * @param adjacencyList
   *          the source, an {@code int[]} holding a solution in <a
   *          href="#adjacencyRepresentation">
   *          <em>adjacency representation</em></a>
   * @param path
   *          the destination: an {@code int[]} to receive a permutation in
   *          representing a solution as <a href="#pathRepresentation">
   *          <em>path representation</em></a>
   * @see #pathToAdjacencyList(int[], int[])
   */
  public static final void adjacencyListToPath(final int[] adjacencyList,
      final int[] path) {
    int idx, cur;

    cur = 1;
    idx = 0;
    do {
      path[idx++] = cur;
      cur = adjacencyList[cur - 1];
    } while (cur != 1);
  }

  /**
   * Convert a solution from <a href="#pathRepresentation">
   * <em>path representation</em></a> to <a
   * href="#adjacencyRepresentation"> <em>adjacency representation</em>
   * </a>. An adjacency list is an integer array where the element at index
   * {@code (i-1)} stands for the city {@code i} and holds the index of the
   * city which should appear after that element. A path, too, is a
   * permutation represented as integer array. Here, the element at index
   * {@code i} will come at position {@code i} in the tour.
   * 
   * @param path
   *          the source: an {@code int[]} holding a permutation in
   *          representing a solution in <a href="#pathRepresentation">
   *          <em>path representation</em></a>
   * @param adjacencyList
   *          the destination, an {@code int[]} to receive a solution in <a
   *          href="#adjacencyRepresentation">
   *          <em>adjacency representation</em></a>
   * @see #adjacencyListToPath(int[], int[])
   */
  public static final void pathToAdjacencyList(final int[] path,
      final int[] adjacencyList) {
    int last;

    last = path[path.length - 1];
    for (final int p : path) {
      adjacencyList[last - 1] = p;
      last = p;
    }
  }

  /**
   * Convert an array of directed
   * {@link org.logisticPlanning.tsp.solving.utils.edge.Edge edges} to a
   * solution in <a href="#adjacencyRepresentation">
   * <em>adjacency representation</em></a>.
   * 
   * @param edges
   *          the array of directed
   *          {@link org.logisticPlanning.tsp.solving.utils.edge.Edge
   *          edges}
   * @param adjacencyList
   *          the destination, an {@code int[]} to receive a solution in <a
   *          href="#adjacencyRepresentation">
   *          <em>adjacency representation</em></a>
   */
  public static final void directedEdgesToAdjacencyList(
      final Edge[] edges, final int[] adjacencyList) {
    for (final Edge e : edges) {
      adjacencyList[e.a - 1] = e.b;
    }
  }

  /**
   * Convert an array of undirected
   * {@link org.logisticPlanning.tsp.solving.utils.edge.Edge edges} to a
   * solution in <a href="#adjacencyRepresentation">
   * <em>adjacency representation</em></a>. The fact that the edges are
   * undirected, makes the conversion a bit more complex than in the
   * {@link #directedEdgesToAdjacencyList(org.logisticPlanning.tsp.solving.utils.edge.Edge[],int[])
   * directed case}.
   * 
   * @param edges
   *          the array of undirected
   *          {@link org.logisticPlanning.tsp.solving.utils.edge.Edge
   *          edges}
   * @param adjacencyList
   *          the destination, an {@code int[]} to receive a solution in <a
   *          href="#adjacencyRepresentation">
   *          <em>adjacency representation</em></a>.
   */
  public static final void undirectedEdgesToAdjacencyList(
      final Edge[] edges, final int[] adjacencyList) {
    int i;

    Arrays.fill(adjacencyList, 0);
    for (final Edge e : edges) {
      i = (e.a - 1);
      if (adjacencyList[i] <= 0) {
        adjacencyList[i] = e.b;
      } else {
        adjacencyList[e.b - 1] = e.a;
      }
    }
  }

  /**
   * Convert an {@link java.lang.Iterable iterable} of directed
   * {@link org.logisticPlanning.tsp.solving.utils.edge.Edge edges} to a
   * solution in <a href="#adjacencyRepresentation">
   * <em>adjacency representation</em></a>.
   * 
   * @param edges
   *          the {@link java.lang.Iterable iterable} of directed
   *          {@link org.logisticPlanning.tsp.solving.utils.edge.Edge
   *          edges}
   * @param adjacencyList
   *          the destination, an {@code int[]} to receive a solution in <a
   *          href="#adjacencyRepresentation">
   *          <em>adjacency representation</em></a>
   */
  public static final void directedEdgesToAdjacencyList(
      final Iterable<Edge> edges, final int[] adjacencyList) {
    for (final Edge e : edges) {
      adjacencyList[e.a - 1] = e.b;
    }
  }

  /**
   * Convert an {@link java.lang.Iterable iterable} of undirected
   * {@link org.logisticPlanning.tsp.solving.utils.edge.Edge edges} to a
   * solution in <a href="#adjacencyRepresentation">
   * <em>adjacency representation</em></a>. The fact that the edges are
   * undirected, makes the conversion a bit more complex than in the
   * {@link #directedEdgesToAdjacencyList(java.lang.Iterable,int[])
   * directed case}.
   * 
   * @param edges
   *          the {@link java.lang.Iterable iterable} of undirected
   *          {@link org.logisticPlanning.tsp.solving.utils.edge.Edge
   *          edges}
   * @param adjacencyList
   *          the destination, an {@code int[]} to receive a solution in <a
   *          href="#adjacencyRepresentation">
   *          <em>adjacency representation</em></a>
   */
  public static final void undirectedEdgesToAdjacencyList(
      final Iterable<? extends Edge> edges, final int[] adjacencyList) {
    int i;

    Arrays.fill(adjacencyList, 0);
    for (final Edge e : edges) {
      i = (e.a - 1);
      if (adjacencyList[i] <= 0) {
        adjacencyList[i] = e.b;
      } else {
        adjacencyList[e.b - 1] = e.a;
      }
    }
  }

  /**
   * Convert a solution in <a href="#adjacencyRepresentation">
   * <em>adjacency representation</em></a> to an array of (directed)
   * {@link org.logisticPlanning.tsp.solving.utils.edge.Edge edges}. Both
   * arrays must have the same length and the array {@code edges} must be
   * filled with existing instances of
   * {@link org.logisticPlanning.tsp.solving.utils.edge.Edge}, i.e., there
   * must be no {@code null} elements in it.
   * 
   * @param adjacencyList
   *          the source, an {@code int[]} holding a solution in <a
   *          href="#adjacencyRepresentation">
   *          <em>adjacency representation</em></a>
   * @param edges
   *          the destination array to receive the directed
   *          {@link org.logisticPlanning.tsp.solving.utils.edge.Edge
   *          edges} , all elements of the array must be different, non-
   *          {@code null} instances!
   */
  public static final void adjacencyListToEdges(final int[] adjacencyList,
      final Edge[] edges) {
    int cur, next;

    next = 1;
    for (final Edge e : edges) {
      e.a = cur = next;
      e.b = next = adjacencyList[cur - 1];
      if (next == 1) {
        return;
      }
    }
  }

  /**
   * Convert a solution in <a href="#pathRepresentation">
   * <em>path representation</em></a> to an array of (directed)
   * {@link org.logisticPlanning.tsp.solving.utils.edge.Edge edges}. Both
   * arrays must have the same length and the array {@code edges} must be
   * filled with existing instances of
   * {@link org.logisticPlanning.tsp.solving.utils.edge.Edge}, i.e., there
   * must be no {@code null} elements in it.
   * 
   * @param path
   *          the source, an {@code int[]} holding a solution in <a
   *          href="#pathRepresentation"><em>path representation</em></a>
   * @param edges
   *          the destination array to receive the directed
   *          {@link org.logisticPlanning.tsp.solving.utils.edge.Edge
   *          edges} , all elements of the array must be different, non-
   *          {@code null} instances!
   */
  public static final void pathToEdges(final int[] path, final Edge[] edges) {
    int last, idx;
    Edge e;

    last = path[path.length - 1];
    idx = 0;
    for (final int p : path) {
      e = edges[idx++];
      e.a = last;
      e.b = last = p;
    }
  }

  /**
   * <p>
   * Compute a hash code of a tour in <a href="#pathRepresentation">
   * <em>path representation</em></a>. Two
   * {@link org.logisticPlanning.tsp.solving.utils.RepresentationUtils#arePathsEquivalentSTSP(int[], int[])
   * equivalent} tours in path representation will always have the same
   * hash code, even if they are in two different path representations. The
   * three permutations {@code (1, 2, 3, 4, 5, 6) },
   * {@code (3, 4, 5, 6, 1, 2)}, and {@code (6, 5, 4, 3, 2, 1) }, for
   * example, all have the same hash code {@code 30569571}. This is
   * achieved by computing hash codes based on the
   * {@link #appendPathInNormalForm(int[], Appendable) normal form}.
   * </p>
   * <p>
   * It should be mentioned that there also may be two paths with the same
   * hash code that actually encode two different solutions. An exhaustive
   * search up to paths of length 8 has not turned up any such <a
   * href="http://en.wikipedia.org/wiki/Collision_%28computer_science%29"
   * >collision</a>. This is a result of the fact that the set of
   * permutations of length {@code n} contains {@code n!/(2n)} possible
   * <em>different</em> paths, but a hash code is a 32 bit integer with
   * <code>2<sup>32</sup></code> values. We thus know that for
   * <code>n&geq;15</code>, there should <a
   * href="http://en.wikipedia.org/wiki/Pigeonhole_principle"
   * >necessarily</a> be collisions. The two permutations
   * {@code (1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)} and
   * {@code  (1, 10, 8, 9, 6, 5, 4, 12, 7, 15, 11, 3, 14, 2, 13)} stand for
   * different paths, but both have the same hash code {@code -456626168}.
   * </p>
   * <p>
   * The hash codes are thus mainly quick hint: They allow you to know for
   * sure if two solutions are different. They give an indicator that two
   * solutions <em>may</em> be equivalent.
   * </p>
   * 
   * @param path
   *          the tour in <a href="#pathRepresentation">
   *          <em>path representation</em></a>
   * @return the hash code
   * @see #arePathsEquivalentSTSP(int[], int[])
   * @see #appendPathInNormalForm(int[], Appendable)
   */
  public static final int pathHashCode(final int[] path) {
    final int len;
    int i, j, step, hash;

    if (path == null) {
      return 0;
    }

    len = path.length;
    if (len <= 0) {
      return (-1);
    }

    for (i = len; (--i) >= 0;) {
      if (path[i] == 1) {
        break;
      }
    }

    step = (len - 1);
    if (path[(i + step) % len] > path[(i + 1) % len]) {
      step = 1;
    }

    hash = 0;
    for (j = len; (--j) >= 0;) {
      hash = HashUtils.combineHashes(hash, path[i]);
      i = ((i + step) % len);
    }

    return hash;
  }

  /** Don't instantiate this class. Why would you do that anyway? */
  private RepresentationUtils() {
    throw new UnsupportedOperationException();
  }
}
