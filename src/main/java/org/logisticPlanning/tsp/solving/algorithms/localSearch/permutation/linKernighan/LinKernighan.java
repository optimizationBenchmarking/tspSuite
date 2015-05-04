package org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.linKernighan;

import java.util.Arrays;

import org.logisticPlanning.tsp.benchmarking.instances.Instance;
import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;
import org.logisticPlanning.tsp.solving.Individual;
import org.logisticPlanning.tsp.solving.TSPAlgorithm;
import org.logisticPlanning.tsp.solving.TSPAlgorithmRunner;
import org.logisticPlanning.tsp.solving.algorithms.localSearch.TSPLocalSearchAlgorithm;

/**
 * The Lin-Kernighan heuristic. XXX: Write a description how this algorithm
 * works. You can describe it similarly to the paper, but do not just copy
 * from there.
 */
public class LinKernighan extends TSPLocalSearchAlgorithm<int[]> {

  /** serialVersionUID */
  private static final long serialVersionUID = 1L;

  /** for choose xi XXX: add description */
  private int[] m_tempX;

  /** for choose yi XXX: add description */
  private int[] m_tempY;

  /** save every xend choice XXX: add description */
  private int[] m_xend;

  /** save every yend choice XXX: add description */
  private int[] m_yend;

  /** a temporary tour */
  private int[] m_tempTour;

  /** edges of a tour which is being optimized */
  private EdgeSet m_edgeSet;

  /** egdes that are added */
  private EdgeSet m_edgeAdded;

  /** edges that are deleted */
  private EdgeSet m_edgeDeleted;

  /** recorded every node which has been reached in every exchange */
  private int[] m_t;

  /**
   * the main routine
   *
   * @param args
   *          the command line arguments
   */
  public final static void main(final String[] args) {
    TSPAlgorithmRunner.benchmark(Instance.SYMMETRIC_INSTANCES,
        LinKernighan.class, args);
  }

  /** create */
  public LinKernighan() {
    super("LinKernighan"); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  public TSPAlgorithm clone() {
    final LinKernighan x;

    x = ((LinKernighan) (super.clone()));
    x.m_t = null;
    x.m_tempX = null;// for chose x
    x.m_tempY = null;// for chose x
    x.m_xend = null;
    x.m_yend = null;
    x.m_edgeSet = null;
    x.m_edgeAdded = null;
    x.m_edgeDeleted = null;
    x.m_tempTour = null;
    return x;
  }

  /** {@inheritDoc} */
  @Override
  public void beginRun(final ObjectiveFunction f) {
    final int n;

    super.beginRun(f);

    n = f.n();

    this.m_t = new int[(2 * n) + n];
    this.m_tempX = new int[8];
    this.m_tempY = new int[8];
    this.m_tempTour = new int[n];
    this.m_xend = new int[n + 1];
    this.m_yend = new int[n + 1];
    this.m_edgeSet = new EdgeSet(4, n);
    this.m_edgeAdded = new EdgeSet(4, n);
    this.m_edgeDeleted = new EdgeSet(4, n);

  }

  /** {@inheritDoc} */
  @Override
  public void endRun(final ObjectiveFunction f) {
    this.m_t = null;
    this.m_tempX = null;// for chose x
    this.m_tempY = null;// for chose x
    this.m_xend = null;
    this.m_yend = null;
    this.m_edgeSet = null;
    this.m_edgeAdded = null;
    this.m_edgeDeleted = null;
    super.endRun(f);
  }

  /** {@inheritDoc} */
  @Override
  // protected void localSearch(final Individual<int[]> srcdst,
  // final ObjectiveFunction f) {
  // System.out.println("algo:begin-----------------------------------");
  // SolutionValidator.validatePath(srcdst.solution,
  // ((srcdst.tourLength==Individual.TOUR_LENGTH_NOT_SET)?-1:srcdst.tourLength),
  // f);
  //
  // this._localSearch(srcdst, f);
  // System.out.println("algo:end-----------------------------------");
  // SolutionValidator.validatePath(srcdst.solution,
  // ((srcdst.tourLength==Individual.TOUR_LENGTH_NOT_SET)?-1:srcdst.tourLength),
  // f);
  //
  // }
  public void localSearch(final Individual<int[]> srcdst,
      final ObjectiveFunction f) {

    // local variables -----------------------

    final int n = f.n(); // the number number of nodes

    long gain = 0;
    long gain1 = 0;
    long tourL, oldGain;
    int q = 1;

    // 1:same srcdst.solution ,another this.t[1].2:different
    // srcdst.solution,this.t[1] is 1(default)//backtracking has different
    // type
    int loopFlag = 2;
    int backType = 0;
    int backStep = 0;
    int countOfTempX;

    // The variable tempTour is an integer array of length n. We use it to
    // test
    // whether an edge set represents a tour. We may swap this value with
    // srcdst.solution.
    final int[] orig = srcdst.solution;
    int[] tempTour = this.m_tempTour;
    final EdgeSet tempEdgeSet = new EdgeSet(4, n);
    // we first load the solution into the set of edges
    this.m_edgeSet.fromPathRepresentation(srcdst.solution);
    tempEdgeSet.copyOf(this.m_edgeSet);
    if (f.shouldTerminate()) { // should we already quit?
      return; // yes -> let's return
    }

    choseT1: for (this.m_t[1] = 1; this.m_t[1] <= n; this.m_t[1]++) {

      if (f.shouldTerminate()) {
        break choseT1;
      }

      Arrays.fill(this.m_xend, 0, this.m_xend.length, 0);
      Arrays.fill(this.m_yend, 0, this.m_yend.length, 1);

      // test:
      this.m_edgeAdded.clear();
      this.m_edgeDeleted.clear();

      if (loopFlag == 2) {
        this.m_t[1] = 1;
      }

      q = 1;
      gain = 0;
      choseQ: for (; q <= n; q++) {
        // System.out.println("q:"+q);
        if (f.shouldTerminate()) {
          break choseT1;
        }

        if ((backType == 0) || (backType == 1)) {
          countOfTempX = this.m_edgeSet.getEdges(this.m_t[(2 * q) - 1],
              this.m_tempX);

          if (backType != 0) {
            backType = 0;
            this.m_xend[q] = this.m_xend[q] + 1;
            if (this.m_xend[q] >= (countOfTempX)) {
              // backtrack
              loopFlag = 1;
              if (q >= 2) {
                backStep = Math.min(2, (q - 1));
                backType = 2;

                LinKernighan.__beforeBacktrack(this.m_edgeDeleted,
                    this.m_edgeAdded, this.m_edgeSet, backType, this.m_t,
                    n, srcdst.solution, f, this.m_xend, this.m_yend,
                    backStep, q, tempEdgeSet);
                q = (backStep - 1);

                if (backStep == 2) {
                  gain = gain1;
                } else
                  if (backStep == 1) {
                    gain = gain1 = 0l;
                  }

                continue choseQ;
              }

              if (q == 1) {
                continue choseT1;
              }
            }

          }

          choseX: for (; this.m_xend[q] < (countOfTempX); this.m_xend[q]++) {
            if (f.shouldTerminate()) {
              break choseT1;
            }

            if (q < 2) {

              if (!this.m_edgeAdded.hasEdge(
                  this.m_tempX[2 * this.m_xend[q]],
                  this.m_tempX[(2 * this.m_xend[q]) + 1])) {
                if (this.m_edgeSet.deleteEdgeIfExists(
                    this.m_tempX[2 * this.m_xend[q]],
                    this.m_tempX[(2 * this.m_xend[q]) + 1])

                ) {
                  this.m_edgeDeleted.addEdge(
                      this.m_tempX[2 * this.m_xend[q]],
                      this.m_tempX[(2 * this.m_xend[q]) + 1]);

                  this.m_t[2 * q] = LinKernighan.__getNextNode(
                      this.m_t[(2 * q) - 1],
                      this.m_tempX[2 * this.m_xend[q]],
                      this.m_tempX[(2 * this.m_xend[q]) + 1]);
                  break choseX;
                }
                loopFlag = 2;
                continue choseT1;

              }
              if (this.m_xend[q] == ((countOfTempX) - 1)) {
                loopFlag = 1;
                continue choseT1;
              }
            }

            if (q >= 2) {
              if ((!this.m_edgeAdded.hasEdge(
                  this.m_tempX[2 * this.m_xend[q]],
                  this.m_tempX[(2 * this.m_xend[q]) + 1]))) {
                if (this.m_edgeSet.deleteEdgeIfExists(
                    this.m_tempX[2 * this.m_xend[q]],
                    this.m_tempX[(2 * this.m_xend[q]) + 1])) {
                  this.m_edgeDeleted.addEdge(
                      this.m_tempX[2 * this.m_xend[q]],
                      this.m_tempX[(2 * this.m_xend[q]) + 1]);

                  this.m_t[2 * q] = LinKernighan.__getNextNode(
                      this.m_t[(2 * q) - 1],
                      this.m_tempX[2 * this.m_xend[q]],
                      this.m_tempX[(2 * this.m_xend[q]) + 1]);

                  this.m_edgeSet.addEdge(this.m_t[1], this.m_t[2 * q]);

                  tourL = srcdst.tourLength
                      - (gain + LinKernighan.__g(this.m_t[1],
                          this.m_t[2 * q],
                          this.m_tempX[2 * this.m_xend[q]],
                          this.m_tempX[(2 * this.m_xend[q]) + 1], f));

                  if (tourL < srcdst.tourLength) {

                    if (this.m_edgeSet.toPathRepresentation(tempTour)) {

                      final int[] temp = srcdst.solution;

                      srcdst.solution = tempTour;

                      tempTour = temp;

                      // record edgeSet

                      tempEdgeSet.copyOf(this.m_edgeSet);

                      srcdst.tourLength = tourL;
                      // System.out.println(srcdst.tourLength);

                      f.registerFE(srcdst.solution, srcdst.tourLength);
                      if (f.shouldTerminate()) {
                        break choseT1;
                      }
                      loopFlag = 2;
                      this.m_t[1] = 0;
                      continue choseT1;
                    }// isATour

                    this.m_edgeSet.deleteEdgeIfExists(this.m_t[1],
                        this.m_t[2 * q]);

                    if (q == 2) {

                      break choseX;
                    }

                    this.m_edgeSet.addEdge(this.m_t[(2 * (q - 1)) + 1],
                        this.m_t[(2 * (q - 1)) + 2]);
                    this.m_edgeDeleted.deleteEdgeIfExists(
                        this.m_t[(2 * (q - 1)) + 1],
                        this.m_t[(2 * (q - 1)) + 2]);
                    this.m_t[2 * q] = 0;
                    if (this.m_xend[q] == ((countOfTempX) - 1)) {
                      // backtrack
                      loopFlag = 1;

                      if (q >= 2) {
                        // System.out.println("here!q>=2");
                        backStep = Math.min(2, (q - 1));
                        backType = 2;
                        LinKernighan.__beforeBacktrack(this.m_edgeDeleted,
                            this.m_edgeAdded, this.m_edgeSet, backType,
                            this.m_t, n, srcdst.solution, f, this.m_xend,
                            this.m_yend, backStep, q, tempEdgeSet);
                        q = (backStep - 1);

                        if (backStep == 1) {
                          gain = gain1 = 0;
                        } else
                          if (backStep == 2) {
                            gain = gain1;
                          }
                        continue choseQ;
                      }
                      // throw new Error("bad");
                      // continue choseT1;
                    }

                  } else {// shorter
                    this.m_edgeSet.deleteEdgeIfExists(this.m_t[1],
                        this.m_t[2 * q]);
                    break choseX;
                  }
                }
              } else {// is in yi?
                if (this.m_xend[q] == ((countOfTempX) - 1)) {
                  loopFlag = 1;

                  if (q >= 2) {
                    backStep = Math.min(2, (q - 1));
                    backType = 2;
                    LinKernighan.__beforeBacktrack(this.m_edgeDeleted,
                        this.m_edgeAdded, this.m_edgeSet, backType,
                        this.m_t, n, srcdst.solution, f, this.m_xend,
                        this.m_yend, backStep, q, tempEdgeSet);
                    q = (backStep - 1);

                    if (backStep == 2) {
                      gain = gain1;
                    } else
                      if (backStep == 1) {
                        gain = gain1 = 0;
                      }

                    continue choseQ;
                  }
                  // throw new Error("something!!");
                  // continue choseT1;

                }
                continue choseX;
              }// edgeinyi

            }// q>=2

          }// chosex
        }// backType

        if ((backType == 0) || (backType == 2)) {
          if (backType != 0) {
            backType = 0;
            this.m_yend[q] = this.m_yend[q] + 1;
            if (this.m_yend[q] >= (n + 1)) {
              loopFlag = 1;

              if ((q >= 1) && (q <= 2)) {
                backType = 1;
                backStep = q;

                // test: xi to t
                // this.m_edgeSet.addEdge(this.m_t[(q << 1) -
                // 1],
                // this.m_t[(q << 1)]);
                // this.m_edgeDeleted.deleteEdgeIfExists(this.m_t[(q
                // << 1)
                // - 1],
                // this.m_t[(q << 1)]);

                LinKernighan.__beforeBacktrack(this.m_edgeDeleted,
                    this.m_edgeAdded, this.m_edgeSet, backType, this.m_t,
                    n, srcdst.solution, f, this.m_xend, this.m_yend,
                    backStep, q, tempEdgeSet);
                q--;

                if (backStep == 2) {
                  gain = gain1;
                } else
                  if (backStep == 1) {
                    gain = gain1 = 0;
                  }
                continue choseQ;
              }
              // throw new Error("something");
              // continue choseT1;
            }
          }

          choseY: for (; this.m_yend[q] <= n; this.m_yend[q]++) {

            if (f.shouldTerminate()) {
              break choseT1; // Added to avoid time-outs
            }

            if (this.m_edgeSet.getEdges(this.m_yend[q], this.m_tempY) == 2) {
              if ((this.m_yend[q] != this.m_t[(2 * q) - 1])
                  && (this.m_yend[q] != this.m_t[2 * q])
                  && (this.m_yend[q] != LinKernighan.__getNextNode(
                      this.m_t[2 * q], this.m_tempY[0], this.m_tempY[1]))
                  && (this.m_yend[q] != LinKernighan.__getNextNode(
                      this.m_t[2 * q], this.m_tempY[2], this.m_tempY[3]))) {
                this.m_t[(2 * q) + 1] = this.m_yend[q];

                oldGain = gain;

                gain += LinKernighan.__g(this.m_t[2 * q],
                    this.m_t[(2 * q) + 1], this.m_t[(2 * q) - 1],
                    this.m_t[2 * q], f);

                if (!this.m_edgeDeleted.hasEdge(this.m_t[2 * q],
                    this.m_t[(2 * q) + 1]) && (gain > 0)) {
                  this.m_edgeSet.addEdge(this.m_t[2 * q],
                      this.m_t[(2 * q) + 1]);

                  this.m_edgeAdded.addEdge(this.m_t[2 * q],
                      this.m_t[(2 * q) + 1]);

                  if (q == 1) {
                    gain1 = gain;
                  }
                  //
                  break choseY;
                }// gain>0

                gain = oldGain;
                this.m_t[(2 * q) + 1] = 0;
              }
            }

            if (this.m_yend[q] == n) {
              // test:xi to t
              // this.m_edgeSet.addEdge(this.m_t[(2 * (q - 1)) +
              // 1],
              // this.m_t[(2 * (q - 1)) + 2]);
              // this.m_edgeDeleted.deleteEdgeIfExists(this.m_t[(2
              // * (q -
              // 1)) + 1],
              // this.m_t[(2 * (q - 1)) + 2]);

              loopFlag = 1;

              if (q > 2) {
                backType = 2;
                backStep = 2;

                LinKernighan.__beforeBacktrack(this.m_edgeDeleted,
                    this.m_edgeAdded, this.m_edgeSet, backType, this.m_t,
                    n, srcdst.solution, f, this.m_xend, this.m_yend,
                    backStep, q, tempEdgeSet);
                q = 1;
                if (backStep == 2) {
                  gain = gain1;
                }

                continue choseQ;
              }

              if ((q >= 1) && (q <= 2)) {
                backType = 1;
                backStep = q;
                LinKernighan.__beforeBacktrack(this.m_edgeDeleted,
                    this.m_edgeAdded, this.m_edgeSet, backType, this.m_t,
                    n, srcdst.solution, f, this.m_xend, this.m_yend,
                    backStep, q, tempEdgeSet);
                q--;

                if (backStep == 2) {
                  gain = gain1;
                } else
                  if (backStep == 1) {
                    gain = gain1 = 0;
                  }
                //

                continue choseQ;
              }
              // throw new Error("something happen");
              // continue choseT1;
            }
          }// choseY
        }// backtype
      }// choseq
    }// choseT1

    if (srcdst.solution != orig) {
      System.arraycopy(srcdst.solution, 0, orig, 0, orig.length);
      srcdst.solution = orig;
    }

  }

  // functions-----------------------------------------------------------------------------------------------------------

  /**
   * get next node XXX: add description
   *
   * @param n
   *          XXX: add description
   * @param a
   *          XXX: add description
   * @param b
   *          XXX: add description
   * @return XXX: add description
   */
  private static final int __getNextNode(final int n, final int a,
      final int b) {
    if (a == n) {
      return b;
    }
    if (b == n) {
      return a;
    }
    return 0;
  }

  /**
   * compute g XXX: add description
   *
   * @param yi1
   *          XXX: add description
   * @param yi2
   *          XXX: add description
   * @param xi1
   *          XXX: add description
   * @param xi2
   *          XXX: add description
   * @param f
   *          XXX: add description
   * @return XXX: add description
   */
  private static final long __g(final int yi1, final int yi2,
      final int xi1, final int xi2, final ObjectiveFunction f) {
    return f.distance(xi1, xi2) - f.distance(yi1, yi2);

  }

  /**
   * the function prepares before backtracking<br/>
   * type:1 q>2 y->y2 x->y2<br/>
   * type:2 q=2 y2-x2 type:4 x2->y1<br/>
   * type:3 q=1 y1->x1 type:5 x1->t1<br/>
   *
   * @param backType
   *          XXX: add description
   * @param t
   *          XXX: add description
   * @param n
   *          XXX: add description
   * @param sol
   *          XXX: add description
   * @param f
   *          XXX: add description
   * @param xend
   *          XXX: add description
   * @param yend
   *          XXX: add description
   * @param backStep
   *          XXX: add description
   * @param q
   *          XXX: add description
   * @param edgeSetAdded
   *          the added edges
   * @param edgeSetDeleted
   *          the deleted edges
   * @param edgeSet
   *          the edge set
   * @param tempEdgeSet
   *          the temporary edge set
   */
  private static final void __beforeBacktrack(
      final EdgeSet edgeSetDeleted, final EdgeSet edgeSetAdded,
      final EdgeSet edgeSet, final int backType, final int[] t,
      final int n, final int[] sol, final ObjectiveFunction f,
      final int[] xend, final int[] yend, final int backStep, final int q,
      final EdgeSet tempEdgeSet) {
    // swap two edgeSets
    // int[][] temp = edgeSet.data;
    // edgeSet.data = tempEdgeSet.data;
    // tempEdgeSet.data = temp;
    // tempEdgeSet.printEdgeSet();
    // edgeSet.printEdgeSet();

    edgeSet.copyOf(tempEdgeSet);
    // edgeSet.fromPathRepresentation(sol);
    // System.out.println("back:begin--------------------------------");
    // System.out.println(Arrays.toString(sol));
    edgeSetAdded.clear();
    edgeSetDeleted.clear();

    // get a new aled as just remove x2
    if ((backType == 2) && (backStep == 2)) {

      // x>2 and y>2->y2
      // test:
      edgeSet.deleteEdgeIfExists(t[1], t[2]);
      edgeSet.deleteEdgeIfExists(t[3], t[4]);

      edgeSet.addEdge(t[2], t[3]);

      // set edgeset added and deleted
      edgeSetAdded.addEdge(t[2], t[3]);
      edgeSetDeleted.addEdge(t[1], t[2]);
      edgeSetDeleted.addEdge(t[3], t[4]);

      // set xend and yend
      Arrays.fill(xend, 3, xend.length, 0);
      Arrays.fill(yend, 3, yend.length, 1);

    } else {
      if ((backType == 1) && (backStep == 2)) {
        // y2 -> x2
        edgeSet.deleteEdgeIfExists(t[1], t[2]);

        edgeSet.addEdge(t[2], t[3]);

        // set edgeset added and deleted
        edgeSetAdded.addEdge(t[2], t[3]);
        edgeSetDeleted.addEdge(t[1], t[2]);

        // set xend and yend
        Arrays.fill(xend, 3, xend.length, 0);
        Arrays.fill(yend, 2, yend.length, 1);
      } else {
        if ((backType == 2) && (backStep == 1)) {
          // x2 -> y1

          edgeSet.deleteEdgeIfExists(t[1], t[2]);

          // set edgesetdeleted
          edgeSetDeleted.addEdge(t[1], t[2]);

          // set xend and yend
          Arrays.fill(xend, 2, xend.length, 0);
          Arrays.fill(yend, 2, yend.length, 1);
        } else {
          if ((backType == 1) && (backStep == 1)) {
            // y1->x1

            // set xend and yend
            Arrays.fill(xend, 2, xend.length, 0);
            Arrays.fill(yend, 1, yend.length, 1);
          }

        }
      }
    }
  }

}
