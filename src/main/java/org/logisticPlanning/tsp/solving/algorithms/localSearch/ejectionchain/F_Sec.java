package org.logisticPlanning.tsp.solving.algorithms.localSearch.ejectionchain;

import org.logisticPlanning.tsp.benchmarking.instances.Instance;
import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;
import org.logisticPlanning.tsp.solving.Individual;
import org.logisticPlanning.tsp.solving.TSPAlgorithmRunner;
import org.logisticPlanning.tsp.solving.algorithms.localSearch.TSPLocalSearchAlgorithm;
import org.logisticPlanning.tsp.solving.operators.permutation.creation.PermutationCreateCanonical;
import org.logisticPlanning.tsp.solving.utils.candidates.CandidateSet;

/**
 * The F_Sec algorithm working internally on a stem-and-cycle data
 * structure. The performance of the P_Sec is kind of poor. It should be
 * better than P_Sec, but I do not know why.
 */
public class F_Sec extends TSPLocalSearchAlgorithm<int[]> {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;
  // Liu Weichen's varies
  /** The number of all the nodes */
  private int m_n;
  /**
   * the node neighbors, store left and right node of each node from 1 to n
   */
  private int[] m_neighbors;
  /** The better tour */
  private int[] m_betterTour;
  /** The root node */
  private int m_rootNode;
  /** The first node of the stem connecting the root node */
  private int m_beginStemNode;
  /** The last node of the stem */
  private int m_endStemNode;
  /** The j node, selecting form the available nodes */
  private int m_jNode;
  /** The level to change the structure */
  private int m_level;
  /** Store the deleted edges */
  private boolean[][] m_tabuEdge;
  /** The list of root node can be selected */
  private int[] m_rootList;
  /** The length of the root list */
  private int m_rootListLength;
  /** The root list for sec in fsec */
  private int[] m_rootListFSec;
  /** The length of the rootListESec */
  private int m_rootListFSecLength;
  /** The neighbor list for PSec in the FSec */
  private CandidateSet m_nearestNeighborhood;
  /** The best solution in the loop of FSec , in the embedded PSec */
  private int[] m_bestSolutionFSec;
  /** The length of best solution in the loop of FSec, in the embedded PSec */
  private long m_bestSolutionLengthFSec;
  /**
   * The improvement of the current solution compared with the best
   * solution
   */
  private int m_improvement;
  // private boolean m_doOnce = true;
  /** The information of tour */
  private ObjectiveFunction m_f;
  /** the destination */
  private Individual<int[]> m_dst;

  /** create */
  public F_Sec() {
    super(//
        "F_Sec Algorithm using a fundamental Stem-and-Cycle Datastructure"); //$NON-NLS-1$
  }

  /**
   * The main routine
   *
   * @param srcdst
   *          an individual record with a tour in srcdst.solution
   *          represented as permutation of the numbers (1, 2, 3, ..., n)
   *          and the tour length is stored in srcdst.tourLength. this
   *          variable is also the output: at the end of this method, you
   *          need to store the best tour you found again in
   *          srcdst.solution (as permutation) and the tour length store in
   *          srcdest.tourlength
   * @param f
   *          if the objective function which can tell you the distance
   *          between two cities and the total tour length of a permutation
   *          or adjacency list representation
   */
  /** {@inheritDoc} */
  @Override
  public void beginRun(final ObjectiveFunction f) {
    super.beginRun(f);
    this.m_f = f;
    this.m_n = f.n();
    // Store left and right node of each node from 1 to n
    this.m_neighbors = new int[this.m_n << 1];
    this.m_betterTour = new int[this.m_n];
    // if you need to allocate/create a big object, say, an integer array
    // or
    // a list or something, you can allocate it here and store it in a
    // member variable. you can then use this variable in the localSearch
    // method
    this.m_nearestNeighborhood = CandidateSet.allocate(this.m_f, 10,
        this.m_nearestNeighborhood);
  }

  /** {@inheritDoc} */
  @Override
  public void endRun(final ObjectiveFunction f) {
    // if you have allocated an object in beginRun, here you need to
    // dispose
    // it: if you store something in a member variable, here you need to
    // set
    // this variable to null
    super.endRun(f);
    this.m_neighbors = null; // The left and right node of each node
    this.m_betterTour = null; // The better tour
    /** The root node */
    this.m_rootNode = 0;
    /** The first node of the stem connecting the root node */
    this.m_beginStemNode = 0;
    /** The last node of the stem */
    this.m_endStemNode = 0;
    /** The j node, selecting form the available nodes */
    this.m_jNode = 0;
    /** Store the deleted edges */
    this.m_tabuEdge = null;
    /** The list of root node can be selected */
    this.m_rootList = null;
    /** The root list for sec in fsec */
    this.m_rootListFSec = null;
    /** The neighbor list for PSec in the FSec */
    // this.m_nearestNeighbor = null;
    /** The best solution in the loop of FSec , in the embeded PSec */
    this.m_bestSolutionFSec = null;
    /** The information of tour */
    this.m_f = null;
    this.m_dst = null;
  }

  /** {@inheritDoc} */
  @Override
  public void localSearch(final Individual<int[]> srcdst,
      final ObjectiveFunction f) {
    // The initialization of the tour
    this.m_dst = srcdst;
    this.__resetBestTour();
    // Initialization of the m_betterTour[]
    this.m_betterTour = new int[this.m_n];
    // Initialization of the root list
    this.m_rootList = new int[this.m_n];
    // The root list for FSec
    this.m_rootListFSec = new int[this.m_n];
    this.m_bestSolutionFSec = new int[this.m_n];
    this.m_level = this.m_n << 1;
    this.m_tabuEdge = new boolean[this.m_n][this.m_n];

    this.__fSecRootNoChanged();
  }

  /**
   * get random root node from root list
   *
   * @return the available root node
   */
  private final int __getRandomRoot() {
    int root = 0;
    if (this.m_rootListLength != 0) {
      final int index = this.m_f.getRandom()
          .nextInt(this.m_rootListLength);
      root = this.m_rootList[index];
      this.m_rootList[index] = this.m_rootList[--this.m_rootListLength];
    } else {
      final int[] i = new int[2];
      i[2] = 8;
      System.exit(1);
    }
    return root;
  }

  /**
   * this.m_dst stores the best tour, reset the best tour to current tour
   */
  private final void __resetBestTour() {
    final int[] sol = this.m_dst.solution;
    // Set the neighbors of the nodes including left and right nodes
    this.__setLeftNeighbor(sol[0], sol[this.m_n - 1]);
    this.__setRightNeighbor(sol[0], sol[1]);
    this.__setRightNeighbor(sol[this.m_n - 1], sol[0]);
    this.__setLeftNeighbor(sol[this.m_n - 1], sol[this.m_n - 2]);
    for (int i = 1; i < (this.m_n - 1); i++) {
      this.__setLeftNeighbor(sol[i], sol[i - 1]);
      this.__setRightNeighbor(sol[i], sol[i + 1]);
    }
  }

  /**
   * reset the best tour to current tour
   *
   * @param tour
   *          the tour
   */
  private final void __resetTour(final int[] tour) {
    final int[] sol = tour;
    // Set the neighbors of the nodes including left and right nodes
    this.__setLeftNeighbor(sol[0], sol[this.m_n - 1]);
    this.__setRightNeighbor(sol[0], sol[1]);
    this.__setRightNeighbor(sol[this.m_n - 1], sol[0]);
    this.__setLeftNeighbor(sol[this.m_n - 1], sol[this.m_n - 2]);
    for (int i = 1; i < (this.m_n - 1); i++) {
      this.__setLeftNeighbor(sol[i], sol[i - 1]);
      this.__setRightNeighbor(sol[i], sol[i + 1]);
    }
  }

  /**
   * initialization of the root list
   */
  private final void __initRootList() {
    this.m_rootListLength = this.m_n;
    PermutationCreateCanonical.makeCanonical(this.m_rootList);
  }

  /**
   * Imply two rules that root node does not change, do the process of pFsc
   */
  private final void __pSecRootNoChanged() {
    long lastTourLength = Long.MAX_VALUE;
    this.__initRootList();
    while ((this.m_rootListLength != 0) && (!(this.m_f.shouldTerminate()))) {
      this.m_improvement = 0;
      this.__secRootNoChanged();
      this.__resetBestTour();
      this.m_beginStemNode = this.m_rootNode;
      this.m_endStemNode = this.m_rootNode;
      final long currentTour = this.__getBetterTour();
      if (currentTour < lastTourLength) {
        lastTourLength = currentTour;
        this.__initRootList();
      }
    }
  }

  /**
   * The process of the full sec
   */
  private final void __fSecRootNoChanged() {
    this.__pSecRootNoChanged();
    this.__initRootList();
    while ((this.m_rootListLength > 0) && (!(this.m_f.shouldTerminate()))) {
      this.__secFirstFsec();
      this.__pSecForFSec();
      this.__resetBestTour();
    }
    this.__resetBestTour();
  }

  /**
   * process of the psec for fsec
   */
  private final void __pSecForFSec() {
    this.m_beginStemNode = this.m_rootNode;
    this.m_endStemNode = this.m_rootNode;
    // Before the psec, the best tour length
    long lastTourLength = this.__getBetterTour();
    this.m_bestSolutionLengthFSec = lastTourLength;
    System.arraycopy(this.m_betterTour, 0, this.m_bestSolutionFSec, 0,
        this.m_n);
    while (this.m_rootListFSecLength > 1) {
      this.m_improvement = 0;
      this.__secRootNoChangedFSec();
      if (this.m_bestSolutionLengthFSec < lastTourLength) {
        lastTourLength = this.m_bestSolutionLengthFSec;
        this.__initRootList();
      }
    }
  }

  /**
   * the process of sec for the FSec
   */
  private final void __secFirstFsec() {
    // Set for the FSec
    this.m_level = 6;
    final int[] deletedEdge = new int[14];
    int deletedEdgeLength = 0;
    this.__initTabuList();
    int countLevel = 0;
    this.m_rootNode = this.__getRandomRoot();
    // Add the root node to the deleted edges
    deletedEdge[deletedEdgeLength++] = this.m_rootNode;

    this.m_beginStemNode = this.m_rootNode;
    this.m_endStemNode = this.m_rootNode;
    while (countLevel < this.m_level) {
      countLevel++;
      final int[] jOnCycle = this.__jOnCycleRootNoChanged();
      final int[] jOnStem = this.__jOnStemNoRootChanged();
      if ((jOnCycle == null) && (jOnStem == null)) {
        break;
      }
      if ((jOnCycle != null)
          && ((jOnStem == null) || (jOnCycle[0] >= jOnStem[0]))) {
        this.__addEdge(jOnCycle[2], jOnCycle[3]);
        this.m_jNode = jOnCycle[2];
        int formerNode = 0;
        int laterNode = 0;
        if (jOnCycle[1] == 1) {
          formerNode = jOnCycle[3];
          laterNode = this.__getNeighbor(this.m_jNode, formerNode);
        } else
          if (jOnCycle[1] == 2) {
            laterNode = jOnCycle[3];
            formerNode = this.__getNeighbor(this.m_jNode, laterNode);
          }
        final int subRoot = this.__getLeftNeighbor(this.m_rootNode);
        final int subRootAno = this.__getRightNeighbor(this.m_rootNode);

        if (jOnCycle[1] == 1) {
          if (this.m_beginStemNode != this.m_rootNode) {
            // change the structure
            this.__setNeighborSame(this.m_endStemNode, -1, this.m_jNode);
            this.__setNeighborSame(this.m_jNode, formerNode,
                this.m_endStemNode);
            this.__setNeighborSame(formerNode, this.m_jNode, -1);
            this.__setNeighborSame(this.m_rootNode, subRoot,
                this.m_beginStemNode);
            // change the root node
            this.m_beginStemNode = subRoot;
            this.m_endStemNode = formerNode;
          } else {
            // change the structure
            this.__setNeighborSame(this.m_rootNode, subRoot, this.m_jNode);
            this.__setNeighborSame(this.m_jNode, formerNode,
                this.m_rootNode);
            this.__setNeighborSame(formerNode, this.m_jNode, -1);
            // change the root node
            this.m_beginStemNode = subRoot;
            this.m_endStemNode = formerNode;
          }
        } else
          if (jOnCycle[1] == 2) {
            if (this.m_beginStemNode != this.m_rootNode) {
              // change the structure
              this.__setNeighborSame(this.m_endStemNode, -1, this.m_jNode);
              this.__setNeighborSame(this.m_jNode, laterNode,
                  this.m_endStemNode);
              this.__setNeighborSame(laterNode, this.m_jNode, -1);
              this.__setNeighborSame(this.m_rootNode, subRootAno,
                  this.m_beginStemNode);
              // change the root node
              this.m_beginStemNode = subRootAno;
              this.m_endStemNode = laterNode;
            } else {
              // change the structure
              this.__setNeighborSame(this.m_rootNode, subRootAno,
                  this.m_jNode);
              this.__setNeighborSame(this.m_jNode, laterNode,
                  this.m_rootNode);
              this.__setNeighborSame(laterNode, this.m_jNode, -1);
              // change the root node
              this.m_beginStemNode = subRootAno;
              this.m_endStemNode = laterNode;
            }
          }
        // When get the best tour, put the edge to deletedEdge[]
        deletedEdge[deletedEdgeLength++] = jOnCycle[2];
        deletedEdge[deletedEdgeLength++] = jOnCycle[3];

      } else
        if ((jOnStem != null)
            && ((jOnCycle == null) || (jOnStem[0] >= jOnCycle[0]))) {
          this.__addEdge(jOnStem[1], jOnStem[2]);
          this.m_jNode = jOnStem[1];
          final int laterNode = jOnStem[2];
          final int formerNode = this.__getNeighbor(this.m_jNode,
              laterNode);
          // change the relation
          this.__setNeighborSame(this.m_endStemNode, -1, this.m_jNode);
          this.__setNeighborDifferent(this.m_jNode, formerNode,
              this.m_endStemNode);
          this.__setNeighborSame(laterNode, this.m_jNode, -1);
          // change the root node, begin stem node
          this.m_endStemNode = laterNode;
          // When get the best tour, put the edge to deletedEdge[]
          deletedEdge[deletedEdgeLength++] = jOnStem[1];
          deletedEdge[deletedEdgeLength++] = jOnStem[2];
        }
    }
    // Remove the repeated node
    for (int i = deletedEdgeLength; i > 0; i--) {
      for (int j = i - 1; j > 0; j--) {
        if (deletedEdge[j - 1] == deletedEdge[i - 1]) {
          deletedEdge[j - 1] = deletedEdge[--deletedEdgeLength];
          break;
        }
      }
    }
    // Get the 10 nearest neighborhood list
    this.m_rootListFSecLength = 0;
    final boolean[] rootListFSec = new boolean[this.m_n];
    for (int i = 0; i < deletedEdgeLength; i++) {
      for (int j = 0; j < 10; j++) {
        final int dex = this.m_nearestNeighborhood.getCandidate(
            deletedEdge[i], j + 1) - 1;
        rootListFSec[dex] = true;
      }
    }
    for (int i = 0; i < this.m_n; i++) {
      if (rootListFSec[i]) {
        this.m_rootListFSec[this.m_rootListFSecLength++] = i + 1;
      }
    }
    // Reset the best solution in the loop
    this.__resetTour(this.__getBetterSolution());
  }

  /** process of sec,in the psec of the fsec */
  private final void __secRootNoChangedFSec() {

    this.__initTabuList();
    this.m_level = this.m_n << 1;
    int countLevel = 0;
    this.m_rootNode = this.m_rootListFSec[--this.m_rootListFSecLength];
    this.m_beginStemNode = this.m_rootNode;
    this.m_endStemNode = this.m_rootNode;
    while (countLevel < this.m_level) {
      countLevel++;
      final int trialBefore = this.__trialSolution(this.m_rootNode,
          this.__getLeftNeighbor(this.m_rootNode),
          this.__getRightNeighbor(this.m_rootNode), this.m_endStemNode);
      final int[] jOnCycle = this.__jOnCycleRootNoChanged();
      final int[] jOnStem = this.__jOnStemNoRootChanged();

      if ((jOnCycle == null) && (jOnStem == null)) {
        break;
      }
      if ((jOnCycle != null)
          && ((jOnStem == null) || (jOnCycle[0] >= jOnStem[0]))) {
        this.__addEdge(jOnCycle[2], jOnCycle[3]);
        this.m_jNode = jOnCycle[2];
        int formerNode = 0;
        int laterNode = 0;
        if (jOnCycle[1] == 1) {
          formerNode = jOnCycle[3];
          laterNode = this.__getNeighbor(this.m_jNode, formerNode);
        } else
          if (jOnCycle[1] == 2) {
            laterNode = jOnCycle[3];
            formerNode = this.__getNeighbor(this.m_jNode, laterNode);
          }
        final int subRoot = this.__getLeftNeighbor(this.m_rootNode);
        final int subRootAno = this.__getRightNeighbor(this.m_rootNode);

        if (jOnCycle[1] == 1) {
          if (this.m_beginStemNode != this.m_rootNode) {
            // change the structure
            this.__setNeighborSame(this.m_endStemNode, -1, this.m_jNode);
            this.__setNeighborSame(this.m_jNode, formerNode,
                this.m_endStemNode);
            this.__setNeighborSame(formerNode, this.m_jNode, -1);
            this.__setNeighborSame(this.m_rootNode, subRoot,
                this.m_beginStemNode);
            // change the root node
            this.m_beginStemNode = subRoot;
            this.m_endStemNode = formerNode;
          } else {
            // change the structure
            this.__setNeighborSame(this.m_rootNode, subRoot, this.m_jNode);
            this.__setNeighborSame(this.m_jNode, formerNode,
                this.m_rootNode);
            this.__setNeighborSame(formerNode, this.m_jNode, -1);
            // change the root node
            this.m_beginStemNode = subRoot;
            this.m_endStemNode = formerNode;
          }
        } else
          if (jOnCycle[1] == 2) {
            if (this.m_beginStemNode != this.m_rootNode) {
              // change the structure
              this.__setNeighborSame(this.m_endStemNode, -1, this.m_jNode);
              this.__setNeighborSame(this.m_jNode, laterNode,
                  this.m_endStemNode);
              this.__setNeighborSame(laterNode, this.m_jNode, -1);
              this.__setNeighborSame(this.m_rootNode, subRootAno,
                  this.m_beginStemNode);
              // change the root node
              this.m_beginStemNode = subRootAno;
              this.m_endStemNode = laterNode;
            } else {
              // change the structure
              this.__setNeighborSame(this.m_rootNode, subRootAno,
                  this.m_jNode);
              this.__setNeighborSame(this.m_jNode, laterNode,
                  this.m_rootNode);
              this.__setNeighborSame(laterNode, this.m_jNode, -1);
              // change the root node
              this.m_beginStemNode = subRootAno;
              this.m_endStemNode = laterNode;
            }
          }
        final int trialAfter = this.__trialSolution(this.m_rootNode,
            this.__getLeftNeighbor(this.m_rootNode),
            this.__getRightNeighbor(this.m_rootNode), this.m_endStemNode);
        this.m_improvement += (jOnCycle[0] + trialAfter) - trialBefore;
        if (this.m_improvement > 0) {
          this.m_improvement = 0;
          final long currentTourlength = this.__getBetterTour();
          if (currentTourlength < this.m_bestSolutionLengthFSec) {
            System.arraycopy(this.m_betterTour, 0,
                this.m_bestSolutionFSec, 0, this.m_n);
            this.m_bestSolutionLengthFSec = currentTourlength;
          }
        }
      } else
        if ((jOnStem != null)
            && ((jOnCycle == null) || (jOnStem[0] >= jOnCycle[0]))) {
          this.__addEdge(jOnStem[1], jOnStem[2]);
          this.m_jNode = jOnStem[1];
          final int laterNode = jOnStem[2];
          final int formerNode = this.__getNeighbor(this.m_jNode,
              laterNode);
          // change the relation
          this.__setNeighborSame(this.m_endStemNode, -1, this.m_jNode);
          this.__setNeighborDifferent(this.m_jNode, formerNode,
              this.m_endStemNode);
          this.__setNeighborSame(laterNode, this.m_jNode, -1);
          // change the root node, begin stem node
          this.m_endStemNode = laterNode;
          final int trialAfter = this
              .__trialSolution(this.m_rootNode,
                  this.__getLeftNeighbor(this.m_rootNode),
                  this.__getRightNeighbor(this.m_rootNode),
                  this.m_endStemNode);
          this.m_improvement += (jOnStem[0] + trialAfter) - trialBefore;
          if (this.m_improvement > 0) {
            this.m_improvement = 0;
            final long currentTourlength = this.__getBetterTour();
            if (currentTourlength < this.m_bestSolutionLengthFSec) {
              System.arraycopy(this.m_betterTour, 0,
                  this.m_bestSolutionFSec, 0, this.m_n);
              this.m_bestSolutionLengthFSec = currentTourlength;
            }
          }
        }
    }
    // Set the best solution in the loop
    this.__resetTour(this.m_bestSolutionFSec);
  }

  /**
   * process of sec, root is not changed
   */
  private final void __secRootNoChanged() {

    this.__initTabuList();
    // this.__initRootList();
    this.m_level = this.m_n << 1;
    int countLevel = 0;
    this.m_rootNode = this.__getRandomRoot();
    this.m_beginStemNode = this.m_rootNode;
    this.m_endStemNode = this.m_rootNode;
    while (countLevel < this.m_level) {
      countLevel++;
      final int trialBefore = this.__trialSolution(this.m_rootNode,
          this.__getLeftNeighbor(this.m_rootNode),
          this.__getRightNeighbor(this.m_rootNode), this.m_endStemNode);
      final int[] jOnCycle = this.__jOnCycleRootNoChanged();
      final int[] jOnStem = this.__jOnStemNoRootChanged();

      if ((jOnCycle == null) && (jOnStem == null)) {
        break;
      }
      if ((jOnCycle != null)
          && ((jOnStem == null) || (jOnCycle[0] >= jOnStem[0]))) {
        this.__addEdge(jOnCycle[2], jOnCycle[3]);
        this.m_jNode = jOnCycle[2];
        int formerNode = 0;
        int laterNode = 0;
        if (jOnCycle[1] == 1) {
          formerNode = jOnCycle[3];
          laterNode = this.__getNeighbor(this.m_jNode, formerNode);
        } else
          if (jOnCycle[1] == 2) {
            laterNode = jOnCycle[3];
            formerNode = this.__getNeighbor(this.m_jNode, laterNode);
          }
        final int subRoot = this.__getLeftNeighbor(this.m_rootNode);
        final int subRootAno = this.__getRightNeighbor(this.m_rootNode);

        if (jOnCycle[1] == 1) {
          if (this.m_beginStemNode != this.m_rootNode) {
            // change the structure
            this.__setNeighborSame(this.m_endStemNode, -1, this.m_jNode);
            this.__setNeighborSame(this.m_jNode, formerNode,
                this.m_endStemNode);
            this.__setNeighborSame(formerNode, this.m_jNode, -1);
            this.__setNeighborSame(this.m_rootNode, subRoot,
                this.m_beginStemNode);
            // change the root node
            this.m_beginStemNode = subRoot;
            this.m_endStemNode = formerNode;
          } else {
            // change the structure
            this.__setNeighborSame(this.m_rootNode, subRoot, this.m_jNode);
            this.__setNeighborSame(this.m_jNode, formerNode,
                this.m_rootNode);
            this.__setNeighborSame(formerNode, this.m_jNode, -1);
            // change the root node
            this.m_beginStemNode = subRoot;
            this.m_endStemNode = formerNode;
          }
        } else
          if (jOnCycle[1] == 2) {
            if (this.m_beginStemNode != this.m_rootNode) {
              // change the structure
              this.__setNeighborSame(this.m_endStemNode, -1, this.m_jNode);
              this.__setNeighborSame(this.m_jNode, laterNode,
                  this.m_endStemNode);
              this.__setNeighborSame(laterNode, this.m_jNode, -1);
              this.__setNeighborSame(this.m_rootNode, subRootAno,
                  this.m_beginStemNode);
              // change the root node
              this.m_beginStemNode = subRootAno;
              this.m_endStemNode = laterNode;
            } else {
              // change the structure
              this.__setNeighborSame(this.m_rootNode, subRootAno,
                  this.m_jNode);
              this.__setNeighborSame(this.m_jNode, laterNode,
                  this.m_rootNode);
              this.__setNeighborSame(laterNode, this.m_jNode, -1);
              // change the root node
              this.m_beginStemNode = subRootAno;
              this.m_endStemNode = laterNode;
            }
          }
        final int trialAfter = this.__trialSolution(this.m_rootNode,
            this.__getLeftNeighbor(this.m_rootNode),
            this.__getRightNeighbor(this.m_rootNode), this.m_endStemNode);
        this.m_improvement += (jOnCycle[0] + trialAfter) - trialBefore;
        if (this.m_improvement > 0) {
          this.__getBetterTour();
          this.m_improvement = 0;
        }
      } else
        if ((jOnStem != null)
            && ((jOnCycle == null) || (jOnStem[0] >= jOnCycle[0]))) {
          this.__addEdge(jOnStem[1], jOnStem[2]);
          this.m_jNode = jOnStem[1];
          final int laterNode = jOnStem[2];
          final int formerNode = this.__getNeighbor(this.m_jNode,
              laterNode);
          // change the relation
          this.__setNeighborSame(this.m_endStemNode, -1, this.m_jNode);
          this.__setNeighborDifferent(this.m_jNode, formerNode,
              this.m_endStemNode);
          this.__setNeighborSame(laterNode, this.m_jNode, -1);
          // change the root node, begin stem node
          this.m_endStemNode = laterNode;
          final int trialAfter = this
              .__trialSolution(this.m_rootNode,
                  this.__getLeftNeighbor(this.m_rootNode),
                  this.__getRightNeighbor(this.m_rootNode),
                  this.m_endStemNode);
          this.m_improvement += (jOnStem[0] + trialAfter) - trialBefore;
          if (this.m_improvement > 0) {
            this.__getBetterTour();
            this.m_improvement = 0;
          }
        }
    }
  }

  /**
   * j node is on the cycle, root node is not changed
   *
   * @return jOnCycle[0]: best EK jOnCycle[1]: best type, 1 former node, 2
   *         later node jOnCycle[2]: j node also p node jOnCycle[3]: q
   *         node, former node or later node
   */
  private final int[] __jOnCycleRootNoChanged() {
    int bestEK = Integer.MIN_VALUE;
    // 0 is initialization, 1 presents former node is better, 2 later node
    int bestType = 0;
    int bestP = 0;
    int bestQ = 0;
    final int subRoot = this.__getLeftNeighbor(this.m_rootNode);
    final int subRootAno = this.__getRightNeighbor(this.m_rootNode);
    this.m_jNode = this.__getNeighbor(subRoot, this.m_rootNode);
    int formerNode = subRoot;
    int laterNode = this.__getNeighbor(this.m_jNode, formerNode);
    final int[] distance = new int[2];

    if ((this.m_jNode == subRootAno) || (subRoot == subRootAno)) {
      return null;
    }
    while (this.m_jNode != subRootAno) {
      if (!this.__inTabuList(this.m_jNode, formerNode)) {
        distance[0] = this.m_f.distance(this.m_jNode, formerNode)
            - this.m_f.distance(this.m_jNode, this.m_endStemNode);
        if (distance[0] > bestEK) {
          bestEK = distance[0];
          bestType = 1;
          bestP = this.m_jNode;
          bestQ = formerNode;
        }
      }
      if (!this.__inTabuList(this.m_jNode, laterNode)) {
        distance[1] = this.m_f.distance(this.m_jNode, laterNode)
            - this.m_f.distance(this.m_jNode, this.m_endStemNode);
        if (distance[1] > bestEK) {
          bestEK = distance[1];
          bestType = 2;
          bestP = this.m_jNode;
          bestQ = laterNode;
        }
      }
      // move j node to next node
      formerNode = this.m_jNode;
      this.m_jNode = laterNode;
      laterNode = this.__getNeighbor(this.m_jNode, formerNode);
    }
    if (bestType == 0) {
      return null;
    }
    final int[] jOnCycle = new int[4];
    jOnCycle[0] = bestEK;
    jOnCycle[1] = bestType;
    jOnCycle[2] = bestP;
    jOnCycle[3] = bestQ;
    return jOnCycle;
  }

  /**
   * j is on the stem, no root node is changed
   *
   * @return jOnStem[0]: bestEK jOnStem[1]: best p node, is also j node
   *         jOnStem[2]: best q node, later node
   */
  private final int[] __jOnStemNoRootChanged() {
    if (this.m_beginStemNode == this.m_endStemNode) {
      return null;
    }
    int bestEK = Integer.MIN_VALUE;
    int bestP = 0;
    int bestQ = 0;
    // initialization
    this.m_jNode = this.__getNeighbor(this.m_beginStemNode,
        this.m_rootNode);
    int formerNode = this.m_beginStemNode;
    int laterNode = this.__getNeighbor(this.m_jNode, formerNode);
    final int stopNode = this.__getNeighbor(this.m_endStemNode, -1);
    if ((this.m_jNode == stopNode) || (this.m_jNode == this.m_endStemNode)) {
      return null;
    }
    while (this.m_jNode != stopNode) {
      if (!this.__inTabuList(this.m_jNode, laterNode)) {
        final int distance = this.m_f.distance(this.m_jNode, laterNode)
            - this.m_f.distance(this.m_jNode, this.m_endStemNode);
        if (distance > bestEK) {
          bestEK = distance;
          bestP = this.m_jNode;
          bestQ = laterNode;
        }
      }
      // move j node to next node
      formerNode = this.m_jNode;
      this.m_jNode = laterNode;
      laterNode = this.__getNeighbor(this.m_jNode, formerNode);
    }
    if ((bestP == 0) || (bestQ == 0)) {
      return null;
    }
    final int[] jOnStem = new int[3];
    jOnStem[0] = bestEK;
    jOnStem[1] = bestP;
    jOnStem[2] = bestQ;
    return jOnStem;
  }

  /**
   * test one edge in the tabu edge or not
   *
   * @param i
   *          , one node of the edge
   * @param j
   *          , another node of the edge
   * @return true, in the tabu, false, not in the tabu
   */
  private final boolean __inTabuList(final int i, final int j) {
    if (i > j) {// i is always smaller than j
      return this.m_tabuEdge[j - 1][i - 1];
    }
    return this.m_tabuEdge[i - 1][j - 1];
  }

  /**
   * set the edge to be true, present the edge is deleted
   *
   * @param i
   *          , one node of the edge
   * @param j
   *          , another node of the edge
   */
  private final void __addEdge(final int i, final int j) {
    if (i > j) {// i is always smaller than j
      this.m_tabuEdge[j - 1][i - 1] = true;
    } else {
      this.m_tabuEdge[i - 1][j - 1] = true;
    }
  }

  /**
   * set all the edges to be false
   */
  private final void __initTabuList() {
    this.m_tabuEdge = null;
    this.m_tabuEdge = new boolean[this.m_n][this.m_n];
  }

  /**
   * The trial solution
   *
   * @param root
   *          the root
   * @param subRoot
   *          the sub-root
   * @param subRootAnother
   *          the other sub-root
   * @param endStem
   *          the end of the stem
   * @return the improvement of the trial solution, from stem and cycle to
   *         tour
   */
  private final int __trialSolution(final int root, final int subRoot,
      final int subRootAnother, final int endStem) {
    final int distanceSub = this.m_f.distance(root, subRoot)
        - this.m_f.distance(endStem, subRoot);
    final int distanceSubAnother = this.m_f.distance(root, subRootAnother)
        - this.m_f.distance(endStem, subRootAnother);
    if (distanceSub > distanceSubAnother) {
      return distanceSub;
    }
    return distanceSubAnother;
  }

  /**
   * Get the neighbor of nodeID, when another neighbor is "oneNeighbor"
   *
   * @param nodeID
   *          : the ID of nodes from 1 to n
   * @param oneNeighbor
   *          : one of the two neighbors
   * @return another neighbor when "oneNeighbor" is given
   */
  private final int __getNeighbor(final int nodeID, final int oneNeighbor) {
    final int one = this.__getLeftNeighbor(nodeID);
    final int two = this.__getRightNeighbor(nodeID);
    if (oneNeighbor == one) {
      return two;
    } else
      if (oneNeighbor == two) {
        return one;
      } else {
        final int[] e = new int[2];
        e[3] = 0;
        System.exit(-1);
        return 0;
      }
  }

  /**
   * Set the neighbor when know one, and set the one
   *
   * @param nodeID
   *          : The number of nodes
   * @param oldNeighbor
   *          : The neighbor is given
   * @param newNeighbor
   *          : The new neighbor's ID
   */
  private final void __setNeighborSame(final int nodeID,
      final int oldNeighbor, final int newNeighbor) {
    if (this.__getLeftNeighbor(nodeID) == oldNeighbor) {
      this.__setLeftNeighbor(nodeID, newNeighbor);
    } else
      if (this.__getRightNeighbor(nodeID) == oldNeighbor) {
        this.__setRightNeighbor(nodeID, newNeighbor);
      }
  }

  /**
   * Set the neighbor when know one, and set the one
   *
   * @param nodeID
   *          : The number of nodes
   * @param oldNeighbor
   *          : The neighbor is given
   * @param newNeighbor
   *          : The new neighbor's ID
   */
  private final void __setNeighborDifferent(final int nodeID,
      final int oldNeighbor, final int newNeighbor) {
    if (this.__getLeftNeighbor(nodeID) == oldNeighbor) {
      this.__setRightNeighbor(nodeID, newNeighbor);
    } else
      if (this.__getRightNeighbor(nodeID) == oldNeighbor) {
        this.__setLeftNeighbor(nodeID, newNeighbor);
      }
  }

  /**
   * Get the left neighbor node by node Id
   *
   * @param nodeId
   *          : node Id from 1 to n
   * @return the left neighbor's Id
   */
  private final int __getLeftNeighbor(final int nodeId) {
    return this.m_neighbors[(nodeId - 1) << 1];
  }

  /**
   * Get the right neighbor node by node Id
   *
   * @param nodeId
   *          : node Id from 1 to n
   * @return the right neighbor's Id
   */
  private final int __getRightNeighbor(final int nodeId) {
    return this.m_neighbors[1 + ((nodeId - 1) << 1)];
  }

  /**
   * Set the left neighbor node by node Id
   *
   * @param nodeId
   *          : node Id from 1 to n
   * @param neighbor
   *          : The node Id of the left neighbor
   */
  private final void __setLeftNeighbor(final int nodeId, final int neighbor) {
    this.m_neighbors[(nodeId - 1) << 1] = neighbor;
  }

  /**
   * Set the right neighbor node by node Id
   *
   * @param nodeId
   *          : node Id from 1 to n
   * @param neighbor
   *          : The node Id of the right neighbor
   */
  private final void __setRightNeighbor(final int nodeId,
      final int neighbor) {
    this.m_neighbors[1 + ((nodeId - 1) << 1)] = neighbor;
  }

  /**
   * Connect end stem node with sub root or sub root another, get the
   * better sub root by comparing, get the better tour
   *
   * @return the length of the better tour
   */
  private final long __getBetterTour() {
    int one = 0;// One of the sub root
    int another = 0;// Another sub root
    int better = 0;// Store the better of the node between "one" and
    // "another"
    one = this.__getLeftNeighbor(this.m_rootNode);
    another = this.__getRightNeighbor(this.m_rootNode);
    final int distanceOne = this.m_f.distance(this.m_endStemNode, one)
        - this.m_f.distance(this.m_rootNode, one);
    final int distanceAnother = this.m_f.distance(this.m_endStemNode,
        another) - this.m_f.distance(this.m_rootNode, another);
    if (distanceOne < distanceAnother) {
      better = one;
    } else {
      better = another;
    }
    int cursor = better;
    int former = this.m_rootNode;
    int count = 0;
    int tempNode = 0;
    // Store the cycle nodes from the better sub node to another sub root
    while (cursor != this.m_rootNode) {
      this.m_betterTour[count++] = cursor;
      tempNode = cursor;
      cursor = this.__getNeighbor(cursor, former);
      former = tempNode;
    }
    // Store the root node
    this.m_betterTour[count++] = this.m_rootNode;
    // Store the stem from begin stem to end stem node
    cursor = this.m_beginStemNode;
    former = this.m_rootNode;
    while (cursor != -1) {
      if (this.m_beginStemNode != this.m_rootNode) {
        this.m_betterTour[count++] = cursor;
      } else {
        cursor = -1;
      }
      // If begin stem node is the same with end stem node
      if (this.m_beginStemNode != this.m_endStemNode) {
        tempNode = cursor;
        cursor = this.__getNeighbor(cursor, former);
        former = tempNode;
      } else {
        cursor = -1;
      }
    }
    final long tourLength = this.m_f.evaluate(this.m_betterTour);
    if (tourLength < this.m_dst.tourLength) {
      this.m_dst.tourLength = tourLength;
      System.arraycopy(this.m_betterTour, 0, this.m_dst.solution, 0,
          this.m_n);
    }
    return tourLength;
  }

  /**
   * get the better solution for the current solution
   *
   * @return the better tour
   */
  private final int[] __getBetterSolution() {
    int one = 0;// One of the sub root
    int another = 0;// Another sub root
    int better = 0;// Store the better of the node between "one" and
    // "another"
    final int[] betterTour = new int[this.m_n];
    one = this.__getLeftNeighbor(this.m_rootNode);
    another = this.__getRightNeighbor(this.m_rootNode);
    final int distanceOne = this.m_f.distance(this.m_endStemNode, one)
        - this.m_f.distance(this.m_rootNode, one);
    final int distanceAnother = this.m_f.distance(this.m_endStemNode,
        another) - this.m_f.distance(this.m_rootNode, another);
    if (distanceOne < distanceAnother) {
      better = one;
    } else {
      better = another;
    }
    int cursor = better;
    int former = this.m_rootNode;
    int count = 0;
    int tempNode = 0;
    // Store the cycle nodes from the better sub node to another sub root
    while (cursor != this.m_rootNode) {
      betterTour[count++] = cursor;
      tempNode = cursor;
      cursor = this.__getNeighbor(cursor, former);
      former = tempNode;
    }
    // Store the root node
    betterTour[count++] = this.m_rootNode;
    // Store the stem from begin stem to end stem node
    cursor = this.m_beginStemNode;
    former = this.m_rootNode;
    while (cursor != -1) {
      if (this.m_beginStemNode != this.m_rootNode) {
        betterTour[count++] = cursor;
      } else {
        cursor = -1;
      }
      // If begin stem node is the same with end stem node
      if (this.m_beginStemNode != this.m_endStemNode) {
        tempNode = cursor;
        cursor = this.__getNeighbor(cursor, former);
        former = tempNode;
      } else {
        cursor = -1;
      }
    }
    return betterTour;
  }

  /**
   * Bootstrap function
   *
   * @param args
   *          the command line arguments
   */
  public static void main(final String args[]) {
    TSPAlgorithmRunner.benchmark(Instance.SYMMETRIC_INSTANCES,
        F_Sec.class, args);
  }
}
