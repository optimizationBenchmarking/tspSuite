package org.logisticPlanning.tsp.solving.algorithms.exact.branchAndBoundHeldCarp;

import java.util.Arrays;
import java.util.PriorityQueue;

import org.logisticPlanning.tsp.benchmarking.instances.Instance;
import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;
import org.logisticPlanning.tsp.solving.TSPAlgorithm;
import org.logisticPlanning.tsp.solving.TSPAlgorithmRunner;
import org.logisticPlanning.utils.math.random.Randomizer;

/**
 * <p>
 * Warning: This algorithm is a really sketchy, dodgy, and probably faulty
 * implementation, since I adapted it from an algorithm i found online
 * without properly understanding how it works. However, it passes all unit
 * tests. It is only experimental and is only here to see how stuffs found
 * in the internet performs.
 * </p>
 * <p>
 * One of the changes I made: create intermediate solutions.
 * </p>
 * <p>
 * According to&nbsp;[<a href="#cite_CI2011OTA"
 * style="font-weight:bold">1</a>], the web page where I found it, this is
 * a Branch and Bound algorithm with the Held-Karp relaxation. The TSP is
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
 * Different from the approach presented in&nbsp;[<a href="#cite_CI2011OTA"
 * style="font-weight:bold">1</a>], the program implemented here does not
 * hold any matrix in memory. This is necessary in order to be able to
 * solve larger-scale problem instances. Also, we achieve at least some
 * sort of randomization by creating a permutation of the node list before
 * applying the algorithm.
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
 * [<a href
 * ="http://www.iro.umontreal.ca/~gendron/IFT6551/LECTURES/TSP.pdf"
 * >1</a>]</div ></div></li>
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
 * href=
 * "http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.64.7595">10.1
 * .1.64 .7595</a></div></div></li>
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
 * href=
 * "http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.18.2">10.1
 * .1.18.2 </a></div></div></li>
 * </ol>
 */
public class BAB_HK extends TSPAlgorithm {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the best node */
  private transient _Node m_bestNode;

  /** the n */
  private int m_n;

  /** the objective function */
  private transient ObjectiveFunction m_f;

  /** the node entries */
  private transient _NodeEntry[] m_nodes;

  /** the solution array */
  private transient int[] m_sol;

  /** instantiate */
  public BAB_HK() {
    super("Branch-and-Bound with Held-Karp Relaxation"); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  public BAB_HK clone() {
    BAB_HK r;
    r = ((BAB_HK) (super.clone()));
    r.m_bestNode = null;
    r.m_nodes = null;
    r.m_sol = null;
    return r;
  }

  /** {@inheritDoc} */
  @Override
  public void beginRun(final ObjectiveFunction f) {
    final int n;
    final _NodeEntry[] nodes;
    final Randomizer r;
    int i, j, k;
    _NodeEntry x;

    super.beginRun(f);

    this.m_f = f;
    this.m_n = n = f.n();

    r = f.getRandom();

    // create nodes array
    this.m_nodes = nodes = new _NodeEntry[n];
    for (i = n; i > 0;) {
      x = new _NodeEntry();
      x.m_node = i;
      nodes[--i] = x;
    }

    // permutate
    for (i = n; (--i) >= 0;) {
      j = r.nextInt(n);
      k = nodes[i].m_node;
      nodes[i].m_node = nodes[j].m_node;
      nodes[j].m_node = k;
    }
    // ok, we have the node permutation

    // allocate solution array
    this.m_sol = new int[n];

    // allocate best node
    this.m_bestNode = new _Node();
    this.m_bestNode.m_lowerBound = Double.POSITIVE_INFINITY;

  }

  /** {@inheritDoc} */
  @Override
  public void endRun(final ObjectiveFunction f) {
    try {
      this.m_f = null;
      this.m_n = (-1);

      this.m_nodes = null;
      this.m_sol = null;
      this.m_bestNode = null;
    } finally {
      super.endRun(f);
    }
  }

  /** {@inheritDoc} */
  @Override
  public void solve(final ObjectiveFunction f) {
    _Node currentNode;
    final PriorityQueue<_Node> pq;
    final PriorityQueue<_Node> children;
    final int n;
    int i, j;

    n = f.n();

    currentNode = new _Node();
    currentNode.m_excluded = new boolean[n][n];

    this.computeHeldKarp(currentNode);
    if (f.shouldTerminate()) {
      return;
    }

    pq = new PriorityQueue<>(11, _NodeComparator.INSTANCE);
    children = new PriorityQueue<>(11, _NodeComparator.INSTANCE);

    do {
      do {
        i = -1;
        for (j = 0; j < this.m_n; j++) {
          if ((currentNode.m_degree[j] > 2)
              && ((i < 0) || (currentNode.m_degree[j] < currentNode.m_degree[i]))) {
            i = j;
          }
        }
        if (i < 0) {
          if (currentNode.m_lowerBound < this.m_bestNode.m_lowerBound) {
            this.m_bestNode = currentNode;
          }
          break;
        }

        children.clear();
        children
        .add(this.exclude(currentNode, i, currentNode.m_parent[i]));
        for (j = 0; j < this.m_n; j++) {
          if (currentNode.m_parent[j] == i) {
            children.add(this.exclude(currentNode, i, j));
            if (f.shouldTerminate()) {
              return;
            }
          }
        }
        currentNode = children.poll();
        pq.addAll(children);
      } while ((currentNode.m_lowerBound < this.m_bestNode.m_lowerBound)
          && (!(f.shouldTerminate())));

      currentNode = pq.poll();
    } while ((currentNode != null)
        && (currentNode.m_lowerBound < this.m_bestNode.m_lowerBound)
        && (!(f.shouldTerminate())));
  }

  /**
   * exclude something
   *
   * @param node
   *          the node
   * @param i
   *          the i
   * @param j
   *          the j
   * @return the node with the exclusion
   */
  private final _Node exclude(final _Node node, final int i, final int j) {
    final _Node child;

    child = new _Node();
    child.m_excluded = node.m_excluded.clone();
    child.m_excluded[i] = node.m_excluded[i].clone();
    child.m_excluded[j] = node.m_excluded[j].clone();
    child.m_excluded[i][j] = true;
    child.m_excluded[j][i] = true;
    this.computeHeldKarp(child);
    return child;
  }

  /**
   * compute the held-karp
   *
   * @param node
   *          the node
   */
  private final void computeHeldKarp(final _Node node) {
    double lambda, previousLowerBound, t;
    int denom, d, i;

    node.m_pi = new double[this.m_n];
    node.m_lowerBound = Double.NEGATIVE_INFINITY;
    node.m_degree = new int[this.m_n];
    node.m_parent = new int[this.m_n];

    lambda = 0.1d;
    while ((lambda > 1e-06d) && (!(this.m_f.shouldTerminate()))) {
      previousLowerBound = node.m_lowerBound;
      this.computeOneTree(node);

      if (node.m_lowerBound >= this.m_bestNode.m_lowerBound) {
        return;
      }

      if (node.m_lowerBound >= previousLowerBound) {
        lambda *= 0.9d;
      }

      denom = 0;
      for (i = 1; i < this.m_n; i++) {
        d = (node.m_degree[i] - 2);
        denom += (d * d);
      }
      if (denom == 0) {
        return;
      }

      t = ((lambda * node.m_lowerBound) / denom);
      for (i = 1; i < this.m_n; i++) {
        node.m_pi[i] += (t * (node.m_degree[i] - 2));
      }
    }
  }

  /**
   * Compute the 1-tree
   *
   * @param node
   *          the node with the search information
   */
  private final void computeOneTree(final _Node node) {
    int firstNeighbor, secondNeighbor, i, j, k, curDist, secondDist;
    double costWithPiFirst, costWithPiSecond, curCostWithPi;
    final _NodeEntry[] nodes;
    _NodeEntry cur;
    final _NodeEntry root;
    _NodeEntry a;
    final int n, nodeNull;

    node.m_lowerBound = 0d;
    Arrays.fill(node.m_degree, 0);

    n = this.m_n;
    costWithPiFirst = Double.POSITIVE_INFINITY;
    costWithPiSecond = Double.POSITIVE_INFINITY;
    nodes = this.m_nodes;
    firstNeighbor = 1;
    secondNeighbor = 2;
    a = nodes[1];

    // find the two cheapest edges departing from node 0
    root = nodes[0];
    root.clear();
    nodeNull = root.m_node;

    for (j = 1; j < n; j++) {
      cur = nodes[j];
      cur.clear();

      if (node.m_excluded[0][j]) {
        curCostWithPi = Double.POSITIVE_INFINITY;
        // this is set as of "clear" in cur
      } else {
        cur.m_dist = this.m_f.distance(nodeNull, cur.m_node);
        cur.m_costWithPi = curCostWithPi = (cur.m_dist + node.m_pi[0] + node.m_pi[j]);
      }

      // curCostWithPi = this.costWithPi(0, j, node);
      if (curCostWithPi < costWithPiSecond) {
        if (curCostWithPi < costWithPiFirst) {
          a = cur;
          secondNeighbor = firstNeighbor;
          costWithPiSecond = costWithPiFirst;
          costWithPiFirst = curCostWithPi;
          firstNeighbor = j;
        } else {
          secondNeighbor = j;
          costWithPiSecond = curCostWithPi;
        }
      }
    }
    secondDist = nodes[secondNeighbor].m_dist;
    BAB_HK.addEdge(node, 0, firstNeighbor, costWithPiFirst);
    a.m_parent = root;
    root.m_child = a;

    // now create the minimum cost list for the MST creation
    Arrays.fill(node.m_parent, firstNeighbor);
    node.m_parent[firstNeighbor] = 0;

    // compute the minimum remaining costs
    for (i = n; (--i) >= 0;) {
      if (i != firstNeighbor) {
        cur = nodes[i];
        cur.m_parent = a;

        if (node.m_excluded[firstNeighbor][i]) {
          cur.m_dist = Integer.MAX_VALUE;
          cur.m_costWithPi = Double.POSITIVE_INFINITY;
        } else {
          cur.m_dist = this.m_f.distance(a.m_node, cur.m_node);
          cur.m_costWithPi = (cur.m_dist + node.m_pi[firstNeighbor] + node.m_pi[i]);
        }
      }
    }

    // compute the minimum spanning tree on nodes 1..n-1
    for (k = 2; k < n; k++) {
      for (i = 1; i < n; i++) {
        if (node.m_degree[i] == 0) {
          break;
        }
      }

      for (j = i + 1; j < this.m_n; j++) {
        if ((node.m_degree[j] == 0)
            && (nodes[j].m_costWithPi < nodes[i].m_costWithPi)) {
          i = j;
        }
      }

      // add the edge
      cur = nodes[i];
      cur.m_nextChild = cur.m_parent.m_child;
      cur.m_parent.m_child = cur;
      a = cur;
      BAB_HK.addEdge(node, node.m_parent[i], i, cur.m_costWithPi);

      for (j = 1; j < n; j++) {
        if (node.m_degree[j] == 0) {
          if (!(node.m_excluded[i][j])) {
            cur = nodes[j];
            curDist = this.m_f.distance(a.m_node, cur.m_node);
            curCostWithPi = (curDist + node.m_pi[i] + node.m_pi[j]);
            if (curCostWithPi < cur.m_costWithPi) {
              cur.m_costWithPi = curCostWithPi;
              cur.m_dist = curDist;
              node.m_parent[j] = i;
              cur.m_parent = a;
            }
          }
        }
      }
    }

    // add edge to second neighbor
    curCostWithPi = (secondDist + node.m_pi[0] + node.m_pi[secondNeighbor]);
    BAB_HK.addEdge(node, 0, secondNeighbor, curCostWithPi);
    node.m_parent[0] = secondNeighbor;

    // connect root to second neighbor
    root.m_parent = nodes[secondNeighbor];
    root.m_dist = secondDist;

    // // we omit this edge in the solution generation, as it will lead to
    // a
    // //(useless?) loop

    // cur = nodes[secondNeighbor];
    // cur.m_dist = secondDist;
    // cur.m_costWithPi = curCostWithPi;
    // cur.m_nextChild = root.m_child;
    // root.m_child = cur;

    node.m_lowerBound = Math.rint(node.m_lowerBound);
    // ok, we have a new spanning tree and a new lower bound

    // create a solution representing what we've got
    this.makeSolution();
  }

  /** make a solution */
  private final void makeSolution() {
    final int n;
    _NodeEntry root, next, cur, last;// , loopFind;
    final int[] perm;
    int i, dist;
    long totalD;

    n = this.m_n;
    perm = this.m_sol;

    // start at the root
    i = 0;
    root = this.m_nodes[0];
    perm[i++] = root.m_node;
    totalD = 0l;
    // loopFind = null;

    // start at the root of the tree at follow it
    for (last = cur = root; cur != null;) {

      next = cur.m_child;

      // is there such a child?
      if (next != null) {
        // loopFind = null;
        cur.m_child = next.m_nextChild;
        next.m_nextChild = null;

        // did we already visit that child?
        if (next.m_ready) {
          next.m_ready = false;

          // add it to the permutation
          perm[i++] = next.m_node;

          // what is the distance to that child?
          getDistance: {
            if (next.m_parent == last) {
              // the parent is the current node: don't need to
              // re-compute
              dist = next.m_dist;
              // is distance valid?
              if ((dist >= 0) && (dist < Integer.MAX_VALUE)) {
                break getDistance;
              }
            }
            // otherwise, take shortcut
            dist = this.m_f.distance(last.m_node, next.m_node);
          }

          totalD += dist;
          last = next;
        }

        cur = next;

        if (i >= n) {
          break;
        }
        continue;// next iteration
      }

      // no such child exists? go back one step
      // if (loopFind == null) {
      // loopFind = cur;
      // } else {
      // if (cur == loopFind) {
      // throw new RuntimeException(); //hehe, here i should do something
      // }
      // }

      cur = cur.m_parent;
    }

    totalD += this.m_f.distance(last.m_node, root.m_node);
    this.m_f.registerFE(perm, totalD);// register solution
  }

  /**
   * add an edge
   *
   * @param node
   *          the node
   * @param i
   *          the i
   * @param j
   *          the j
   * @param costWithPi
   *          the costs with pi
   */
  private static final void addEdge(final _Node node, final int i,
      final int j, final double costWithPi) {
    node.m_lowerBound += costWithPi;// this.costWithPi(i, j, node);
    node.m_degree[i]++;
    node.m_degree[j]++;
  }

  /**
   * Perform the branch and bound with held-karp
   *
   * @param args
   *          the command line arguments
   */
  public final static void main(final String[] args) {
    TSPAlgorithmRunner.benchmark(Instance.SYMMETRIC_INSTANCES,
        BAB_HK.class,//
        args);
  }
}
