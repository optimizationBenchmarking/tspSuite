package org.logisticPlanning.tsp.solving.algorithms.exact.branchAndBoundLittle1963;

import java.util.Arrays;

import org.logisticPlanning.tsp.benchmarking.instances.Instance;
import org.logisticPlanning.tsp.benchmarking.objective.LogPoint;
import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;
import org.logisticPlanning.tsp.solving.TSPAlgorithm;
import org.logisticPlanning.tsp.solving.TSPAlgorithmRunner;
import org.logisticPlanning.tsp.solving.operators.permutation.creation.PermutationCreateCanonical;

/**
 *
 <h1>Branch and Bound for the TSP by Little et al.</h1>
 * <p>
 * A first Branch and Bound algorithm for Traveling Salesman Problems was
 * published in the paper
 * <em>An Algorithm for the Traveling Salesman Problem</em>&nbsp;[<a
 * href="#cite_LMSK1963AAFTTSP" style="font-weight:bold">1</a>] by Little
 * et al. in 1963. The idea of Branch and Bound algorithms itself was
 * originally proposed only three years earlier, by Ailsa Land and Alison
 * Doig in&nbsp;[<a href="#cite_LP1960AAMOSDPP"
 * style="font-weight:bold">2</a>]. Other sources on Branch and Bound
 * include the <a
 * href="http://en.wikipedia.org/wiki/Branch-and-bound">Wikipedia page</a>
 * and books like&nbsp;[<a href="#cite_M2012DAAOA"
 * style="font-weight:bold">3</a>].
 * </p>
 * <h2>Basic Idea of this Branch and Bound Algorithm</p>
 * <p>
 * The first major idea of this algorithm is to step-by-step divide (
 * {@link #__branch(_Node) branch}) the search space into smaller and
 * smaller subsets (here called
 * {@link org.logisticPlanning.tsp.solving.algorithms.exact.branchAndBoundLittle1963._Node
 * nodes}). For this purpose a
 * {@link org.logisticPlanning.tsp.solving.algorithms.exact.branchAndBoundLittle1963._Node
 * (sub)set} {@code s} of the search space is taken and a possible edge
 * {@code e} is chosen. The subset {@code s} is then divided into two
 * parts:
 * </p>
 * <ol>
 * <li>One containing all tours that contain (travel over) edge {@code e}
 * and</li>
 * <li>one containing all tours that do not travel over edge {@code e}.</li>
 * </ol>
 * <p>
 * Since any tour contains exactly
 * <code>{@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction#n() n}</code>
 * edges (where
 * <code>{@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction#n() n}</code>
 * is the number of cities in the TSP), after
 * <code>{@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction#n() n}</code>
 * {@link #__branch(_Node) branches} of the first kind, we will have one
 * valid solution. The continued branching moves that we make during this
 * progress somehow resemble the iterative expansion of a tree, where the
 * current solution subset is a node and its child nodes are the branches
 * departing from it. If we continue dissecting the solution space like
 * this, we will eventually have seen all possible solutions and thus,
 * found the optimum.
 * </p>
 * <p>
 * This procedure of repeated {@link #__branch(_Node) branching} until we
 * get tours alone would be equivalent to an
 * {@link org.logisticPlanning.tsp.solving.algorithms.exact.exhaustiveEnumeration.ExhaustivePermutationIteration
 * exhaustive enumeration} of all possible solutions and, thus, would not
 * be very efficient.
 * </p>
 * <p>
 * The trick is the second major compound of the algorithm &mdash; the idea
 * of bounding: For each
 * {@link org.logisticPlanning.tsp.solving.algorithms.exact.branchAndBoundLittle1963._Node
 * (sub)set} {@code s} of the solutions, we can compute a
 * {@link #__computeLowBound(_Node) lower bound} of the tour length. That
 * is an approximation of the tour length that of the shortest tour that
 * this subset {@code s} contains. Being a lower bound, it may
 * underestimate this tour length, but it will never over-estimate it.
 * </p>
 * <p>
 * If we now already have a tour {@code x} with tour length
 * <code>{@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction#evaluate(int[]) f(x)=z}</code>
 * , this would mean that we do never even need to look into any subset
 * {@code s} with a lower bound higher or equal to {@code z}! In other
 * words, we have good chances to throw away quite a few of the subsets of
 * the solution space. Every subset that we can throw away means less
 * solutions to check. As a result, we can be better than an exhaustive
 * enumeration <em>if</em> we can throw away solution subsets.
 * </p>
 * <p>
 * In this implementation here, we first check if the
 * {@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction
 * objective function} has already seen any solution. If so, the length of
 * the best solution created before starting the algorithm (maybe by an
 * initialization procedure or a constructive heuristic) is taken to check
 * the lower bounds of the subspaces in the first round of branches. If no
 * solution exists, we randomly create one and evaluate it. Of course, for
 * checking whether a subset of the solution space can be discarded, we
 * always use the length of the best tour we have discovered.
 * </p>
 * <p>
 * In the following, we discuss details of the algorithm, as defined
 * in&nbsp;[<a href="#cite_LMSK1963AAFTTSP"
 * style="font-weight:bold">1</a>], which mainly concern the questions:
 * </p>
 * <ol>
 * <li>How to choose the subspace to {@link #__branch(_Node) branch} next?</li>
 * <li>How to {@link #__chooseJumpEdge(int[]) choose} the edge to be
 * included/excluded into the new branches?</li>
 * <li>How to compute the {@link #__computeLowBound(_Node) lower bounds}?</li>
 * </ol> <h2>Algorithm Details</h2>
 * <p>
 * Any given matrix {@code C}, be it the original distance matrix or the
 * matrix derived from the previous node, is first reduced by finding the
 * minimal value of each row and then subtract it from each element of the
 * row. After dealing with all the rows, we find the minimal value of each
 * column and then subtract it from each element of the column. Adding all
 * the minimal values from each row and column together, we get a sum and
 * use {@code reduction} to denote it. we use {@code reduced matrix} to
 * denote the matrix that has already subtracted the minimal values of each
 * rows and columns and use {@code reducing matrix} to denote the process
 * described before.
 * </p>
 * <p>
 * Given a {@code reduced matrix}, we will find an edge to branch down
 * according to some criteria, which in this case is determined in terms of
 * a function that operates on the {@code C(k,l)}, which we denote by
 * {@code theta(k,l)}. We define {@code theta(k,l)} as the sum of the
 * smallest element in row {@code k} in {@code C}, and the smallest element
 * in column {@code l} in {@code C}, both omitting the {@code C(k,l)}. In
 * the {@code reduced matrix}, we iterate through all the element whose
 * value is {@code 0}, and compute all corresponding {@code theta(k,l)}. We
 * select edge {@code (k,l)} as the edge to branch if {@code theta(k,l)}
 * has the maximum value over our iteration. The process above is the
 * fundamental in this algorithm and we shall call it
 * {@code choosing a jump edge}.
 * </p>
 * <p>
 * Given an original matrix, we have a
 * {@link org.logisticPlanning.tsp.solving.algorithms.exact.branchAndBoundLittle1963._Node
 * node} corresponding to it. From this node, we start branching. Each node
 * formed after branching consists of an arrays of edges that is going to
 * be included in the solution and an array of edges that is going to be
 * excluded during the next branching. When given those two arrays, we are
 * able to construct the matrix corresponding to this node. Given any node,
 * we construct the corresponding matrix by crossing out all the rows and
 * columns that have been chosen in the
 * {@link org.logisticPlanning.tsp.solving.algorithms.exact.branchAndBoundLittle1963._Node#m_edges}
 * of this node, and then set the distance of edges, which we need not to
 * consider during the upcoming branching, to infinity.
 * {@link #__setInfinityEdges(org.logisticPlanning.tsp.solving.algorithms.exact.branchAndBoundLittle1963._Node)
 * setInfinityEdges} method will give all the edges that ought to be set to
 * infinity. Then we will reduce the matrix and choose the jump edge. After
 * the {@code jump edge} is chosen, we create two new nodes based on the
 * branching node, one has the {@code jump edge} along with all the edges
 * in
 * {@link org.logisticPlanning.tsp.solving.algorithms.exact.branchAndBoundLittle1963._Node#m_edges}
 * in the branching node added to
 * {@link org.logisticPlanning.tsp.solving.algorithms.exact.branchAndBoundLittle1963._Node#m_edges}
 * of this node, the other has the {@code jump edge} along with all the
 * edges in
 * {@link org.logisticPlanning.tsp.solving.algorithms.exact.branchAndBoundLittle1963._Node#m_excludedEdges}
 * in the branching node added to
 * {@link org.logisticPlanning.tsp.solving.algorithms.exact.branchAndBoundLittle1963._Node#m_excludedEdges}
 * . We add the newly created node with the {@code jump edge} stored in the
 * {@link org.logisticPlanning.tsp.solving.algorithms.exact.branchAndBoundLittle1963._Node#m_excludedEdges}
 * into an array of node if the low bound of the node is smaller than the
 * existing minimal low bound, which is kept and updated when a solution is
 * found. The branching process proceed with newly created node that has
 * the {@code jump edge} inside
 * {@link org.logisticPlanning.tsp.solving.algorithms.exact.branchAndBoundLittle1963._Node#m_edges}
 * if the low bound of the node is smaller than the existing low bound. we
 * keep branching until we there is no node stored in the array for us to
 * branch further.
 * </p>
 * <h2>References</h2>
 * <ol>
 * <li><div><span id="cite_LMSK1963AAFTTSP" />John D. C. Little, <a
 * href="http://www-personal.umich.edu/~murty/">Katta G. Murty</a>, Dura W.
 * Sweeny, and&nbsp;Caroline Karel: <span
 * style="font-weight:bold">&ldquo;An Algorithm for the Traveling Salesman
 * Problem,&rdquo;</span> <span
 * style="font-style:italic;font-family:cursive;">Technical Report</span>
 * Number&nbsp;07-63, March&nbsp;1, 1963; published by Cambridge, MA, USA:
 * Massachusetts Institute of Technology (MIT), Sloan School of Management.
 * <div>links: [<a href=
 * "http://dspace.mit.edu/bitstream/handle/1721.1/46828/algorithmfortrav00litt.pdf"
 * >1</a>], [<a href="http://hdl.handle.net/1721.1/46828">2</a>],
 * and&nbsp;[<a href
 * ="https://github.com/karepker/little-tsp/blob/master/source.pdf"
 * >3</a>]</div ></div></li>
 * <li><div><span id="cite_LP1960AAMOSDPP" />Ailsa H. Land and&nbsp;Alison
 * G. Doig: <span style="font-weight:bold">&ldquo;An Automatic Method of
 * Solving Discrete Programming Problems,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Econometrica &#8210;
 * Journal of the Econometric Society</span> 28(3):497&ndash;520,
 * July&nbsp;1960; published by Chichester, West Sussex, UK: Wiley
 * Interscience and&nbsp;Econometric Society. LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/sn97-23014">sn97-23014</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/35705710">35705710</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/00129682">0012-9682</a>. <div>link:
 * [<a
 * href="http://jmvidal.cse.sc.edu/library/land60a.pdf">1</a>]</div></div></li>
 * <li><div><span id="cite_M2012DAAOA" />I. Chandra Mohan: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Design and
 * Analysis of Algorithms,&rdquo;</span> 2008&ndash;2012, New Delhi, India:
 * PHI Learning Pvt. Ltd.. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/8120345754">8120345754</a>, <a
 * href="https://www.worldcat.org/isbn/8120335171">8120335171</a>, <a
 * href="https://www.worldcat.org/isbn/9788120345751">9788120345751</a>,
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9788120335172">9788120335172</a>;
 * Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=qg3snQEACAAJ">qg3snQEACAAJ</a>
 * and&nbsp;<a
 * href="http://books.google.com/books?id=9Pz5K7s2YKgC">9Pz5K7s2YKgC</a>;
 * ASIN:&nbsp;<a href="http://www.amazon.com/dp/B008GBIO80">B008GBIO80</a>
 * and&nbsp;<a
 * href="http://www.amazon.com/dp/8120335171">8120335171</a></div></li>
 * </ol>
 *
 * @author <ul>
 *         <li>
 *         <em><a href="mailto:ljjy23@mail.ustc.edu.cn">Yan Jiang</a></em>
 *         [&#x6C5F;&#x708E;]</li>
 *         <li><em><a href="http://www.it-weise.de/">Thomas Weise</a></em>,
 *         Email:&nbsp;<a
 *         href="mailto:tweise@ustc.edu.cn">tweise@ustc.edu.cn</a> (role:
 *         supervisor of research work for the Science Master's degree)</li>
 *         </ul>
 */
public class BABLittle1963Plus extends TSPAlgorithm {

  /**
   * the serial version uid
   */
  private static final long serialVersionUID = 1L;

  /** size of the TSP instance given. */
  private transient int m_n;

  /**
   * the
   * {@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction
   * objective function} which provides all the information of a TSP
   * instance
   */
  private transient ObjectiveFunction m_objectiveFunction;

  /**
   * given a matrix of any node, the array stores the minimal value of each
   * row corresponding to this matrix.
   */
  private transient long[] m_minOfRows;

  /**
   * given a matrix of any node, the array stores the minimal value of each
   * column. Note the matrix has changed when we compute the minimal value
   * of each column. We subtract each element in each row by the minimal
   * value of that row.
   */
  private transient long[] m_minOfColumns;

  /** rows that should be crossed out during branching */
  private transient boolean[] m_crossedRows;

  /** columns that should be crossed out during branching */
  private transient boolean[] m_crossedColumns;

  /**
   * an two dimensional array used to record the m_edges that should not be
   * considered during the next branch, in other words, distances of those
   * m_edges are going to be set to m_infinity. Since we going to access
   * this array randomly, we use a two dimensional array.
   */
  private transient boolean[][] m_infinity;

  /**
   * this array will record all the elements that is 0 after the matrix
   * reduction.
   */
  private transient int[] m_zeros;

  /**
   * auxiliary array1
   */
  private transient int[] m_auxiArray1;

  /**
   * auxiliary array2
   */
  private transient int[] m_auxiArray2;

  /**
   * this array will store all the nodes whose low bound is less than the
   * existing known smallest low bound. This array is going to be very huge
   * in size.
   */
  private transient _Node[] m_nodeBuffer;

  /**
   * this is used to indicate how many node are there in the
   * {@link #m_nodeBuffer}
   */
  private transient int m_nodeBufferSize;

  /**
   * this is the array used to represent the jump edge we have chosen
   */
  private transient int[] m_jumpEdge;

  /**
   * this is the adjacency representation of the solution.
   */
  private transient int[] m_answer;

  /**
   * this is the array during the branching, to avoid allocating memory
   * every time we create a new node
   */
  private transient int[] m_branchingEdges;

  /**
   * this is the pool trying used to avoid constantly allocating new _Node
   */
  private transient _Node[] m_nodeReferencePool;

  /**
   * this is the size of the of {@link #m_nodeReferencePool}
   */
  private transient int m_poolSize;

  /**
   * create
   *
   * @param name
   *          the algorithm's name
   */
  protected BABLittle1963Plus(final String name) {
    super("Branch and Bound method (Little et al., 1963)" + name);//$NON-NLS-1$
    this.m_jumpEdge = new int[2];
  }

  /** create */
  public BABLittle1963Plus() {
    this(""); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  public BABLittle1963Plus clone() {
    BABLittle1963Plus x;
    x = ((BABLittle1963Plus) (super.clone()));
    x.m_jumpEdge = new int[2];
    return x;
  }

  /** {@inheritDoc} */
  @Override
  public void beginRun(final ObjectiveFunction f) {
    super.beginRun(f);

    this.m_objectiveFunction = f;
    this.m_n = f.n();
    this.m_minOfColumns = new long[this.m_n];
    this.m_minOfRows = new long[this.m_n];

    this.m_crossedColumns = new boolean[this.m_n];
    this.m_crossedRows = new boolean[this.m_n];

    this.m_auxiArray1 = new int[this.m_n + 1];
    this.m_auxiArray2 = new int[this.m_n + 1];

    this.m_zeros = new int[this.m_n * this.m_n];
    this.m_infinity = new boolean[this.m_n][this.m_n];

    this.m_answer = new int[this.m_n];
    this.m_nodeBuffer = new _Node[2 * this.m_n];
    this.m_nodeReferencePool = new _Node[2 * this.m_n];

    this.m_branchingEdges = new int[2 * this.m_n];
  }

  /** {@inheritDoc} */
  @Override
  public void endRun(final ObjectiveFunction f) {

    this.m_objectiveFunction = null;
    this.m_n = -1;
    this.m_minOfColumns = null;
    this.m_minOfRows = null;

    this.m_crossedColumns = null;
    this.m_crossedRows = null;

    this.m_auxiArray1 = null;
    this.m_auxiArray2 = null;

    this.m_zeros = null;
    this.m_infinity = null;

    this.m_answer = null;
    this.m_nodeBuffer = null;

    this.m_branchingEdges = null;
    this.m_nodeReferencePool = null;
    super.endRun(f);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void solve(final ObjectiveFunction f) {
    final int[] sol;
    final LogPoint p;
    long bestF;

    p = f.getCurrentLogPoint();
    sol = this.m_answer;
    if (p.getConsumedFEs() <= 0) {
      PermutationCreateCanonical.makeCanonical(sol);
      f.getRandom().shuffle(sol);
      bestF = f.evaluate(sol);
    } else {
      f.getCopyOfBest(sol);
      bestF = p.getBestF();
    }
    this.onSolutionPath(sol, bestF, f);
    if (f.shouldTerminate()) {
      return;
    }

    _Node toBranch = null;
    this.m_nodeBufferSize = 0;
    this.m_poolSize = 0;
    toBranch = new _Node();
    this.m_nodeBuffer[this.m_nodeBufferSize++] = toBranch;
    toBranch.m_edges = new int[0];
    toBranch.m_nodeLowBound = this.__computeLowBound(toBranch);

    while (this.m_nodeBufferSize > 0) {
      if (f.shouldTerminate()) {
        return;
      }

      bestF = p.getBestF();
      toBranch = null;

      _Node z;
      findBranchAndPruneList: for (int i = this.m_nodeBufferSize; (--i) >= 0;) {
        z = this.m_nodeBuffer[i];

        if (z.m_nodeLowBound >= bestF) {
          this.__addNodeToPool(z);
        } else {
          if (toBranch != null) {
            if (z.m_nodeLowBound < toBranch.m_nodeLowBound) {
              this.m_nodeBuffer[i] = toBranch;// z is removed from
              // list
              toBranch = z;
            }
            continue findBranchAndPruneList;
          }
          // first node with viable lower bound, must be removed from
          // list
          // TODO: Is this right?
          toBranch = z;
        }

        // if we get here, we should remove the node at index i
        this.m_nodeBuffer[i] = this.m_nodeBuffer[--this.m_nodeBufferSize];
        this.m_nodeBuffer[this.m_nodeBufferSize] = null;
      }

      if (toBranch == null) {
        return;
      }

      for (;;) {
        if (f.shouldTerminate()) {
          return;
        }

        final _Node result = this.__branch(toBranch);

        // TODO: check
        if ((result == null) || (toBranch._isSolution(this.m_n))) {
          break;
        }

        if (result.m_nodeLowBound < p.getBestF()) {
          this.__addNodeToBuffer(result);
        }

        if (toBranch.m_nodeLowBound >= p.getBestF()) {
          break;
        }
      }

      Arrays.fill(this.m_branchingEdges, 0);
    }
  }

  /**
   * this method is used to branch from the given node. After each
   * branching process, we may have two nodes, one contains the jump edge
   * and the other not.
   *
   * @param node
   *          the node
   * @return the new node
   */
  private final _Node __branch(final _Node node) {
    long tourLength;

    if (node.m_edges.length < (2 * this.m_n)) {
      System.arraycopy(node.m_edges, 0, this.m_branchingEdges, 0,
          node.m_edges.length);
      node.m_edges = this.m_branchingEdges;
    }

    _Node nextC = null;
    this.__reset();

    if (node.m_edges[(2 * this.m_n) - 5] > 0) {
      this.__setCrossedRowsAndColumns(node);
      this.__setInfinityEdges(node);
      this.__setMinOfRows();
      this.__setMinOfColumns();
      this.__findZeroEdges();
      this.__chooseJumpEdge(this.m_zeros);

      if (this.m_jumpEdge[0] == 0) {
        return null;
      }

      node.m_edges[(2 * this.m_n) - 4] = this.m_jumpEdge[0];
      node.m_edges[(2 * this.m_n) - 3] = this.m_jumpEdge[1];

      for (int i = 1; i <= this.m_n; i++) {
        if (!this.m_crossedRows[i - 1] && (i != this.m_jumpEdge[0])) {
          node.m_edges[(2 * this.m_n) - 2] = i;
        }

        if (!this.m_crossedColumns[i - 1] && (i != this.m_jumpEdge[1])) {
          node.m_edges[(2 * this.m_n) - 1] = i;
        }
      }

      long sum = 0;

      for (int i = 0; i < (2 * this.m_n); i += 2) {
        sum = sum
            + this.m_objectiveFunction.distance(node.m_edges[i],
                node.m_edges[i + 1]);
      }

      this.__formatSolution(node.m_edges);
      tourLength = this.m_objectiveFunction.evaluateAdj(this.m_answer);
      if (this.m_objectiveFunction.shouldTerminate()) {
        return null; // TODO: check
      }

      this.onSolutionAdjacency(this.m_answer, //
          tourLength,//
          this.m_objectiveFunction);

      // TODO: check
      return null;
    }

    this.__setCrossedRowsAndColumns(node);
    this.__setInfinityEdges(node);
    this.__setMinOfRows();
    this.__setMinOfColumns();
    this.__findZeroEdges();
    this.__chooseJumpEdge(this.m_zeros);
    if (this.m_jumpEdge[0] == 0) {
      return null;
    }

    if (this.m_poolSize > 0) {
      nextC = this.m_nodeReferencePool[--this.m_poolSize];
    } else {
      nextC = new _Node();
    }

    int i = 0;
    for (; node.m_edges[i] > 0; i += 2) {
      /* */
    }
    node.m_edges[i] = this.m_jumpEdge[0];
    node.m_edges[i + 1] = this.m_jumpEdge[1];
    nextC.m_edges = Arrays.copyOfRange(node.m_edges, 0, i);
    if (node.m_excludedEdges != null) {
      nextC.m_excludedEdges = new int[node.m_excludedEdges.length + 2];
      for (int j = 0; j < node.m_excludedEdges.length; j++) {
        nextC.m_excludedEdges[j] = node.m_excludedEdges[j];
      }
    } else {
      nextC.m_excludedEdges = new int[2];
    }

    nextC.m_excludedEdges[nextC.m_excludedEdges.length - 2] = this.m_jumpEdge[0];
    nextC.m_excludedEdges[nextC.m_excludedEdges.length - 1] = this.m_jumpEdge[1];

    nextC.m_nodeLowBound = this.__computeLowBound(nextC);
    node.m_nodeLowBound = this.__computeLowBound(node);

    return nextC;
  }

  /**
   * This method is called whenever a new solution in
   * {@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction#evaluateAdj(int[])
   * adjacency list representation} is created. This happens frequently
   * during the search. The values in {@code adjacencyList} may be modified
   * by this method here. Later we can create a subclass of this class that
   * overrides this method and, in the overridden method, we can perform a
   * local search.
   *
   * @param adjacencyList
   *          the
   *          {@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction#evaluateAdj(int[])
   *          adjacency list representation} of the new solution
   * @param tourLength
   *          the tour length
   * @param f
   *          the
   *          {@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction
   *          objective function}
   */
  protected void onSolutionAdjacency(final int[] adjacencyList,
      final long tourLength, final ObjectiveFunction f) {
    //
  }

  /**
   * This method is called whenever a new solution in
   * {@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction#evaluate(int[])
   * path representation} is created. This happens exactly once, when the
   * algorithm starts up. The values in {@code path} may be modified by
   * this method here. Later we can create a subclass of this class that
   * overrides this method and, in the overridden method, we can perform a
   * local search.
   *
   * @param path
   *          the
   *          {@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction#evaluate(int[])
   *          adjacency list representation} of the new solution
   * @param tourLength
   *          the tour length
   * @param f
   *          the
   *          {@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction
   *          objective function}
   */
  protected void onSolutionPath(final int[] path, final long tourLength,
      final ObjectiveFunction f) {
    //
  }

  /**
   * format the answer so it can be registered.
   *
   * @param edges
   *          the edges
   */
  private final void __formatSolution(final int[] edges) {
    for (int i = 0; i < edges.length; i += 2) {
      this.m_answer[edges[i] - 1] = edges[i + 1];
    }
  }

  /**
   * this method compute the low bound of the given node.
   *
   * @param node
   *          the node
   * @return the lower bound
   */
  private final long __computeLowBound(final _Node node) {
    long lowBound = 0;
    final int n = this.m_n;

    this.__reset();

    if (node._isSolution(this.m_n)) {
      for (int i = 0; i < node.m_edges.length; i += 2) {
        lowBound += this.m_objectiveFunction.distance(node.m_edges[i],
            node.m_edges[i + 1]);
      }
      return lowBound;
    }

    if (node.m_edges.length != 0) {
      for (int i = 0; (i < node.m_edges.length) && (node.m_edges[i] > 0); i += 2) {
        lowBound += this.m_objectiveFunction.distance(node.m_edges[i],
            node.m_edges[i + 1]);
        this.m_crossedRows[node.m_edges[i] - 1] = true;
        this.m_crossedColumns[node.m_edges[i + 1] - 1] = true;
      }
    }

    this.__setInfinityEdges(node);
    this.__setMinOfRows();
    this.__setMinOfColumns();
    for (int i = 1; i <= n; i++) {
      lowBound += this.m_minOfRows[i - 1] + this.m_minOfColumns[i - 1];
    }
    return lowBound;
  }

  /**
   * Given the node, set the global variable {@link #m_crossedColumns} and
   * {@link #m_crossedRows}.
   *
   * @param node
   *          the node
   */
  private final void __setCrossedRowsAndColumns(final _Node node) {
    for (int i = 0; (i < node.m_edges.length) && (node.m_edges[i] > 0); i += 2) {
      this.m_crossedColumns[node.m_edges[i + 1] - 1] = true;
      this.m_crossedRows[node.m_edges[i] - 1] = true;
    }
  }

  /**
   * Based on the {@link _Node#m_edges} variable in a node, we compute out
   * the potential m_edges that may form a sub circle with the selected
   * m_edges, which is not a solution, and register those m_edges in
   * {@link #m_infinity} variable.
   *
   * @param node
   *          the node
   */
  private final void __setInfinityEdges(final _Node node) {

    for (int i = 0; (i < node.m_edges.length) && (node.m_edges[i] > 0); i += 2) {
      this.m_auxiArray1[node.m_edges[i + 1]] = node.m_edges[i];
      this.m_auxiArray2[node.m_edges[i]] = node.m_edges[i + 1];
    }

    for (int i = 0; i < (this.m_n + 1); i++) {

      if ((this.m_auxiArray1[i] == 0) && (this.m_auxiArray2[i] == 0)) {
        continue;
      }

      int j, k, temp;
      j = this.m_auxiArray2[i] == 0 ? i : this.m_auxiArray2[i];
      k = this.m_auxiArray1[i] == 0 ? i : this.m_auxiArray1[i];

      while (this.m_auxiArray2[j] != 0) {
        temp = this.m_auxiArray2[j];
        this.m_auxiArray1[j] = this.m_auxiArray2[j] = 0;
        j = temp;
      }
      this.m_auxiArray1[j] = 0;

      while (this.m_auxiArray1[k] != 0) {
        temp = this.m_auxiArray1[k];
        this.m_auxiArray1[k] = this.m_auxiArray2[k] = 0;
        k = temp;
      }
      this.m_auxiArray2[k] = 0;

      this.m_auxiArray1[i] = this.m_auxiArray2[i] = 0;
      this.m_infinity[j - 1][k - 1] = true;
    }

    if (node.m_excludedEdges != null) {
      for (int i = 0; i < node.m_excludedEdges.length; i += 2) {
        this.m_infinity[node.m_excludedEdges[i] - 1][node.m_excludedEdges[i + 1] - 1] = true;
      }
    }
  }

  /**
   * this method is used to find the minimal value of the given row.
   *
   * @param row
   *          the row that needs to find out the minimal value
   */
  private final void __setMinOfRow(final int row) {
    int d;
    long min = Long.MAX_VALUE;

    for (int i = 1; i <= this.m_n; i++) {
      if (!this.m_crossedColumns[i - 1]
          && !this.m_infinity[row - 1][i - 1] && (i != row)) {
        d = this.m_objectiveFunction.distance(row, i);
        if (d < min) {
          min = d;
        }
      }
    }

    this.m_minOfRows[row - 1] = min;
  }

  /** set the minimum of rows */
  private final void __setMinOfRows() {
    for (int i = 1; i <= this.m_n; i++) {
      if (!this.m_crossedRows[i - 1]) {
        this.__setMinOfRow(i);
      }
    }
  }

  /** set the minimum of columns */
  private final void __setMinOfColumns() {
    for (int i = 1; i <= this.m_n; i++) {
      if (!this.m_crossedColumns[i - 1]) {
        this.__setMinOfColumn(i);
      }
    }
  }

  /**
   * this method is used to find the minimal value of the specified column.
   * note that the value of this column is the original value subtracting
   * the minimal value of the row in which the original value is.
   *
   * @param column
   *          the column
   */
  private final void __setMinOfColumn(final int column) {
    long d;
    long min = Long.MAX_VALUE;

    for (int i = 1; i <= this.m_n; i++) {
      if (!this.m_crossedRows[i - 1]
          && !this.m_infinity[i - 1][column - 1] && (i != column)) {
        d = (this.m_objectiveFunction.distance(i, column) - this.m_minOfRows[i - 1]);
        if (d < min) {
          min = d;
        }
      }
    }
    this.m_minOfColumns[column - 1] = min;
  }

  /**
   * this method is used to find all m_edges whose values are zero after
   * subtracting the minimal value of the row the column it is in.
   */
  private final void __findZeroEdges() {
    int k = 0;
    for (int i = 1; i <= this.m_n; i++) {
      if (!this.m_crossedRows[i - 1]) {
        for (int j = 1; j <= this.m_n; j++) {
          if (!this.m_crossedColumns[j - 1]
              && !this.m_infinity[i - 1][j - 1] && (i != j)) {
            if ((this.m_objectiveFunction.distance(i, j)
                - this.m_minOfRows[i - 1] - this.m_minOfColumns[j - 1]) == 0) {
              this.m_zeros[k++] = i;
              this.m_zeros[k++] = j;
            }
          }
        }
      }
    }
  }

  /**
   * this method computes the {@code theta} function, which is the key
   * function in selecting the edge to branch. The {@code theta} function
   * is computed as follows:
   * <ol>
   * <li>For any edge whose value is {@code 0} after the matrix reduction,
   * we use {@code R} to denote the row this value is in and {@code C} to
   * denote the column this value is in.</li>
   * <li>For row {@code R}, we find the minimal value except the value in
   * row <em>in</em> and column <em>out</em>. we denote it by {@code MR}.</li>
   * <li>For column {@code C}, we find the minimal value except the value
   * in row <em>in</em> and column <em>out</em>. we denote it by {@code MC}
   * .</li>
   * <li>We add {@code MR} and {@code M}C together, and the resulting value
   * is the value of the {@code theta} function of an edge with the
   * starting point in and ending point out.</li>
   * </ol>
   *
   * @param in
   *          the starting point of an edge
   * @param out
   *          the ending point of an edge
   * @return the value of the {@code theta} function of a specified edge.
   */
  private final long __computeThetaFunction(final int in, final int out) {
    long secondMinOfRow = Long.MAX_VALUE;
    long secondMinOfColumn = Long.MAX_VALUE;
    long d;

    for (int i = 1; i <= this.m_n; i++) {
      if ((i != in) && (i != out)) {
        if (!this.m_crossedColumns[i - 1]
            && !this.m_infinity[in - 1][i - 1]) {
          d = (this.m_objectiveFunction.distance(in, i)
              - this.m_minOfRows[in - 1] - this.m_minOfColumns[i - 1]);
          if (d < secondMinOfRow) {
            secondMinOfRow = d;
          }
        }

        if (!this.m_crossedRows[i - 1] && !this.m_infinity[i - 1][out - 1]) {
          d = (this.m_objectiveFunction.distance(i, out)
              - this.m_minOfRows[i - 1] - this.m_minOfColumns[out - 1]);
          if (d < secondMinOfColumn) {
            secondMinOfColumn = d;
          }
        }
      }
    }

    if (secondMinOfColumn == Long.MAX_VALUE) {
      secondMinOfColumn = 0;
    }
    if (secondMinOfRow == Long.MAX_VALUE) {
      secondMinOfRow = 0;
    }
    return secondMinOfRow + secondMinOfColumn;

  }

  /**
   * This method takes an array that representing m_edges as parameter, and
   * then iterates through all the m_edges and then computes the
   * {@code theta} value of each edge. we choose the biggest {@code theta}
   * value and then choose the corresponding edge as the jump edge.
   *
   * @param zeros
   *          an array representing m_edges whose value is zero after the
   *          reduction
   * @return an array that represents an edge.
   */
  private final int[] __chooseJumpEdge(final int[] zeros) {
    Arrays.fill(this.m_jumpEdge, 0);
    long theta = 0;
    for (int i = 0; zeros[i] > 0; i += 2) {
      if (this.__computeThetaFunction(zeros[i], zeros[i + 1]) >= theta) {
        theta = this.__computeThetaFunction(zeros[i], zeros[i + 1]);
        this.m_jumpEdge[0] = zeros[i];
        this.m_jumpEdge[1] = zeros[i + 1];
      }
    }
    return this.m_jumpEdge;
  }

  /**
   * reset a two dimensional boolean array to its default value
   *
   * @param a
   *          the array to be reset
   */
  private static final void __resetTwoDimensionalBooleanArray(
      final boolean[][] a) {
    for (final boolean[] b : a) {
      Arrays.fill(b, false);
    }
  }

  /**
   * reset almost all the variable of this class, including
   * {@link #m_minOfColumns}, {@link #m_minOfRows},
   * {@link #m_crossedColumns}, {@link #m_crossedRows}, {@link #m_zeros},
   * {@link #m_infinity}.
   */
  private final void __reset() {
    Arrays.fill(this.m_minOfColumns, 0l);
    Arrays.fill(this.m_minOfRows, 0l);
    Arrays.fill(this.m_jumpEdge, 0);
    Arrays.fill(this.m_crossedColumns, false);
    Arrays.fill(this.m_crossedRows, false);
    Arrays.fill(this.m_zeros, 0);
    Arrays.fill(this.m_auxiArray1, 0);
    Arrays.fill(this.m_auxiArray2, 0);
    BABLittle1963Plus.__resetTwoDimensionalBooleanArray(this.m_infinity);
  }

  /**
   * add the node to {@link #m_nodeBuffer}
   *
   * @param node
   *          the node to add
   */
  private final void __addNodeToBuffer(final _Node node) {
    if (this.m_nodeBufferSize >= this.m_nodeBuffer.length) {
      this.m_nodeBuffer = BABLittle1963Plus
          .__extendArray(this.m_nodeBuffer);
    }
    this.m_nodeBuffer[this.m_nodeBufferSize++] = node;
  }

  /**
   * add the {@code node} to the pool
   *
   * @param node
   *          the node to add
   */
  private final void __addNodeToPool(final _Node node) {
    if (this.m_poolSize >= this.m_nodeReferencePool.length) {
      this.m_nodeReferencePool = BABLittle1963Plus
          .__extendArray(this.m_nodeReferencePool);
    }
    this.m_nodeReferencePool[this.m_poolSize++] = node;
  }

  /**
   * this method allocates a new array if the old one is running out of
   * space and copies all the elements in the old one to the new one.
   *
   * @param oldA
   *          the old array
   * @return the extended array
   */
  private static final _Node[] __extendArray(final _Node[] oldA) {
    final _Node[] newA;
    final int k;

    k = oldA.length << 1;
    newA = new _Node[k < oldA.length ? Integer.MAX_VALUE : k];
    System.arraycopy(oldA, 0, newA, 0, oldA.length);
    return newA;
  }

  /**
   * The main method invoking the algorithm
   *
   * @param args
   *          the command line arguments
   */
  public static void main(final String args[]) {
    TSPAlgorithmRunner.benchmark(Instance.SYMMETRIC_INSTANCES,
        BABLittle1963Plus.class, args);
  }
}
