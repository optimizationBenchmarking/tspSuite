package org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.linKernighan;

import java.util.Arrays;

import org.logisticPlanning.tsp.benchmarking.instances.Instance;
import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;
import org.logisticPlanning.tsp.solving.Individual;
import org.logisticPlanning.tsp.solving.TSPAlgorithm;
import org.logisticPlanning.tsp.solving.TSPAlgorithmRunner;
import org.logisticPlanning.tsp.solving.algorithms.localSearch.TSPLocalSearchAlgorithm;
import org.logisticPlanning.tsp.solving.utils.candidates.CandidateSet;
import org.logisticPlanning.utils.math.random.Randomizer;

/**
 * The Lin-Kernighan heuristic. You can describe it similarly to the paper,
 * but do not just copy from there. <@javaAuthorVersion/>
 */
public class LinKernighan_candiadate_20
    extends TSPLocalSearchAlgorithm<int[]> {

  /** serialVersionUID */
  private static final long serialVersionUID = 1L;

  /** for choose xi */
  private int[] m_tempX;

  /** for choose yi */
  private int[] m_tempY;

  /** save every xend choice */
  private int[] m_xend;

  /** save every yend choice */
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

  /** */
  private int[] m_yendCount;

  /** */
  private int[] m_xendCount;

  /** */
  private Randomizer r;
  /** */
  private int[] m_ycandidates1;
  /** */
  private int[] m_ycandidates2;
  /** */
  private int[] m_ycandidatesAfter2;
  /** */
  private int[] m_candidatesT;
  /** */
  private int[] m_xcandidates1;
  /** */
  private int[] m_xcandidates2;
  /** */
  private int[] m_xcandidatesAfter2;
  /** */
  private CandidateSet m_CandidatesOfY;
  /** */
  private int m_m;

  /** */
  private int[] m_CandidatesOfY2;

  /** */
  private int candidateSize;

  /**
   * the main routine
   *
   * @param args
   *          the command line arguments
   */
  public final static void main(final String[] args) {
    TSPAlgorithmRunner.benchmark(Instance.SYMMETRIC_INSTANCES,
        LinKernighan_candiadate_20.class, args);
  }

  /** create */
  public LinKernighan_candiadate_20() {
    super("LinKernighan"); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  public TSPAlgorithm clone() {
    final LinKernighan_candiadate_20 x;

    x = ((LinKernighan_candiadate_20) (super.clone()));
    x.m_t = null;
    x.m_tempX = null;// for chose x
    x.m_tempY = null;// for chose x
    x.m_xend = null;
    x.m_yend = null;
    x.m_edgeSet = null;
    x.m_edgeAdded = null;
    x.m_edgeDeleted = null;
    x.m_tempTour = null;
    x.m_yendCount = null;
    x.m_xendCount = null;
    x.r = null;
    x.m_ycandidates1 = null;
    x.m_ycandidates2 = null;
    x.m_ycandidatesAfter2 = null;
    x.m_candidatesT = null;
    x.m_xcandidates1 = null;
    x.m_xcandidates2 = null;
    x.m_xcandidatesAfter2 = null;
    x.m_CandidatesOfY = null;
    x.m_CandidatesOfY2 = null;
    x.m_t = null;
    this.m_m = 0;
    return x;
  }

  /** {@inheritDoc} */
  @Override
  public void beginRun(final ObjectiveFunction f) {
    final int n;

    super.beginRun(f);

    n = f.n();

    this.candidateSize = 20;
    if (this.candidateSize >= n) {

      this.m_m = n - 1;

    } else {

      this.m_m = this.candidateSize;
    }

    this.r = f.getRandom();
    this.m_t = new int[(2 * n) + n];
    this.m_tempX = new int[8];
    this.m_tempY = new int[8];
    this.m_tempTour = new int[n];
    this.m_xend = new int[n + 1];
    this.m_yend = new int[n + 1];
    this.m_edgeSet = new EdgeSet(4, n);
    this.m_edgeAdded = new EdgeSet(4, n);
    this.m_edgeDeleted = new EdgeSet(4, n);
    this.m_yendCount = new int[n + 1];
    this.m_xendCount = new int[n + 1];
    this.m_ycandidates1 = new int[n + 1];
    this.m_ycandidates2 = new int[n + 1];
    this.m_ycandidatesAfter2 = new int[n + 1];
    this.m_candidatesT = new int[n + 1];
    this.m_xcandidates1 = new int[4];
    this.m_xcandidates2 = new int[4];
    this.m_xcandidatesAfter2 = new int[4];

    // TODO change
    this.m_CandidatesOfY = CandidateSet.allocate(f, this.m_m, null);
    this.m_CandidatesOfY2 = new int[(this.m_m * n)];
    for (int i = 0; i < this.m_CandidatesOfY2.length; i++) {
      this.m_CandidatesOfY2[i] = (i % this.m_m) + 1;
      // switch(i%5){
      // case 0: this.m_CandidatesOfY2[i]=1;break;
      // case 1: this.m_CandidatesOfY2[i]=2;break;
      // case 2: this.m_CandidatesOfY2[i]=3;break;
      // case 3: this.m_CandidatesOfY2[i]=4;break;
      // case 4: this.m_CandidatesOfY2[i]=5;break;

      // }
    }
    // this.m_tCount = new int[n + 1];
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
    this.m_yendCount = null;
    this.r = null;
    this.m_ycandidates1 = null;
    this.m_ycandidates2 = null;
    this.m_ycandidatesAfter2 = null;
    this.m_candidatesT = null;
    this.m_xcandidates1 = null;
    this.m_xcandidates2 = null;
    this.m_xcandidatesAfter2 = null;
    this.m_xendCount = null;
    this.m_CandidatesOfY = null;
    this.m_CandidatesOfY2 = null;
    this.m_m = 0;
    super.endRun(f);
  }

  /** {@inheritDoc} */
  @Override
  public void localSearch(final Individual<int[]> srcdst,
      final ObjectiveFunction f) {

    // local variables -----------------------

    final int n = f.n(); // the number number of nodes

    long gain = 0;
    long gain1 = 0;
    long tourL, oldGain;
    int q = 1;
    int t1Count = 1;
    // 1:same srcdst.solution ,another this.t[1].2:different
    // srcdst.solution,this.t[1] is 1(default)//backtracking has different
    // type
    int backType = 0;
    int backStep = 0;
    int countOfTempX;

    for (int i = 0; i <= n; i++) {
      this.m_ycandidates1[i] = i;
      this.m_ycandidates2[i] = i;
      this.m_ycandidatesAfter2[i] = i;
      this.m_candidatesT[i] = i;
    }
    for (int i = 0; i <= 3; i++) {
      this.m_xcandidates1[i] = i;
      this.m_xcandidates2[i] = i;
      this.m_xcandidatesAfter2[i] = i;

    }
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

    // TODO randomizer
    choseT1: for (t1Count = 1; t1Count <= n; t1Count++) {
      int indexT1;
      indexT1 = this.r.nextInt(n - (t1Count - 1)) + 1;
      this.m_t[1] = this.m_candidatesT[indexT1];
      // swap
      this.m_candidatesT[indexT1] = this.m_candidatesT[n - (t1Count - 1)];
      this.m_candidatesT[n - (t1Count - 1)] = this.m_t[1];
      if (f.shouldTerminate()) {
        break choseT1;
      }

      Arrays.fill(this.m_xend, 0, this.m_xend.length, 0);
      Arrays.fill(this.m_yend, 0, this.m_yend.length, 1);
      Arrays.fill(this.m_xendCount, 0, this.m_xendCount.length, 0);
      Arrays.fill(this.m_yendCount, 0, this.m_yendCount.length, 1);

      Arrays.sort(this.m_xcandidates1);
      Arrays.sort(this.m_xcandidates2);
      // test:
      this.m_edgeAdded.clear();
      this.m_edgeDeleted.clear();

      q = 1;
      gain = 0;
      choseQ: for (; q <= n; q++) {
        if (f.shouldTerminate()) {
          break choseT1;
        }

        if ((backType == 0) || (backType == 1)) {
          countOfTempX = this.m_edgeSet.getEdges(this.m_t[(2 * q) - 1],
              this.m_tempX);

          // always sort m_xcandidatesAfter2;
          Arrays.sort(this.m_xcandidatesAfter2);
          if (backType != 0) {

            backType = 0;
            this.m_xend[q] = this.m_xend[q] + 1;
            if (this.m_xend[q] >= (countOfTempX)) {
              // backtrack
              if (q >= 2) {
                backStep = Math.min(2, (q - 1));
                backType = 2;

                LinKernighan_candiadate_20.__beforeBacktrack(
                    this.m_edgeDeleted, this.m_edgeAdded, this.m_edgeSet,
                    backType, this.m_t, n, srcdst.solution, f, this.m_xend,
                    this.m_yend, backStep, q, tempEdgeSet,
                    this.m_yendCount, this.m_xcandidates2,
                    this.m_xendCount);
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

          // TODO randomizer
          choseX: for (; this.m_xendCount[q] < (countOfTempX); this.m_xendCount[q]++) {
            int Xindex;
            Xindex = this.r.nextInt(countOfTempX - this.m_xendCount[q]);
            this.m_xend[q] = this.m_xcandidates1[Xindex];
            if (q == 1) {
              this.m_xend[q] = this.m_xcandidates1[Xindex];
              this.m_xcandidates1[Xindex] = this.m_xcandidates1[(countOfTempX
                  - (this.m_xendCount[q] + 1))];
              this.m_xcandidates1[(countOfTempX
                  - (this.m_xendCount[q] + 1))] = this.m_xend[q];
            } else
              if (q == 2) {
                this.m_xend[q] = this.m_xcandidates2[Xindex];
                this.m_xcandidates2[Xindex] = this.m_xcandidates2[(countOfTempX
                    - (this.m_xendCount[q] + 1))];
                this.m_xcandidates2[(countOfTempX
                    - (this.m_xendCount[q] + 1))] = this.m_xend[q];
              } else {
                this.m_xend[q] = this.m_xcandidatesAfter2[Xindex];
                this.m_xcandidatesAfter2[Xindex] = this.m_xcandidatesAfter2[(countOfTempX
                    - (this.m_xendCount[q] + 1))];
                this.m_xcandidatesAfter2[(countOfTempX
                    - (this.m_xendCount[q] + 1))] = this.m_xend[q];

              }
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

                  this.m_t[2 * q] = LinKernighan_candiadate_20
                      .__getNextNode(this.m_t[(2 * q) - 1],
                          this.m_tempX[2 * this.m_xend[q]],
                          this.m_tempX[(2 * this.m_xend[q]) + 1]);
                  break choseX;
                }
                t1Count = 1;
                continue choseT1;

              }
              if (this.m_xend[q] == ((countOfTempX) - 1)) {
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

                  this.m_t[2 * q] = LinKernighan_candiadate_20
                      .__getNextNode(this.m_t[(2 * q) - 1],
                          this.m_tempX[2 * this.m_xend[q]],
                          this.m_tempX[(2 * this.m_xend[q]) + 1]);

                  this.m_edgeSet.addEdge(this.m_t[1], this.m_t[2 * q]);

                  tourL = srcdst.tourLength
                      - (gain + LinKernighan_candiadate_20.__g(this.m_t[1],
                          this.m_t[2 * q],
                          this.m_tempX[2 * this.m_xend[q]],
                          this.m_tempX[(2 * this.m_xend[q]) + 1], f));

                  if (tourL < srcdst.tourLength) {

                    if (this.m_edgeSet.toPathRepresentation(tempTour)) {

                      final int[] temp = srcdst.solution;

                      srcdst.solution = tempTour;

                      tempTour = temp;

                      tempEdgeSet.copyOf(this.m_edgeSet);

                      srcdst.tourLength = tourL;
                      // System.out.println(srcdst.tourLength);
                      f.registerFE(srcdst.solution, srcdst.tourLength);
                      if (f.shouldTerminate()) {
                        break choseT1;
                      }
                      t1Count = 1;
                      continue choseT1;
                    } // isATour

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

                      if (q >= 2) {
                        backStep = Math.min(2, (q - 1));
                        backType = 2;
                        LinKernighan_candiadate_20.__beforeBacktrack(
                            this.m_edgeDeleted, this.m_edgeAdded,
                            this.m_edgeSet, backType, this.m_t, n,
                            srcdst.solution, f, this.m_xend, this.m_yend,
                            backStep, q, tempEdgeSet, this.m_yendCount,
                            this.m_xcandidates2, this.m_xendCount);
                        q = (backStep - 1);

                        if (backStep == 1) {
                          gain = gain1 = 0;
                        } else
                          if (backStep == 2) {
                            gain = gain1;
                          }
                        continue choseQ;
                      }
                    }

                  } else {// shorter
                    this.m_edgeSet.deleteEdgeIfExists(this.m_t[1],
                        this.m_t[2 * q]);
                    break choseX;
                  }
                }
              } else {// is in yi?
                if (this.m_xend[q] == ((countOfTempX) - 1)) {

                  if (q >= 2) {
                    backStep = Math.min(2, (q - 1));
                    backType = 2;
                    LinKernighan_candiadate_20.__beforeBacktrack(
                        this.m_edgeDeleted, this.m_edgeAdded,
                        this.m_edgeSet, backType, this.m_t, n,
                        srcdst.solution, f, this.m_xend, this.m_yend,
                        backStep, q, tempEdgeSet, this.m_yendCount,
                        this.m_xcandidates2, this.m_xendCount);
                    q = (backStep - 1);

                    if (backStep == 2) {
                      gain = gain1;
                    } else
                      if (backStep == 1) {
                        gain = gain1 = 0;
                      }

                    continue choseQ;
                  }

                }
                continue choseX;
              } // edgeinyi

            } // q>=2

          } // chosex
        } // backType

        if ((backType == 0) || (backType == 2)) {
          if (backType != 0) {
            backType = 0;
            this.m_yendCount[q] = this.m_yendCount[q] + 1;
            if (this.m_yendCount[q] >= (this.m_m + 1)) {

              if ((q >= 1) && (q <= 2)) {
                backType = 1;
                backStep = q;

                LinKernighan_candiadate_20.__beforeBacktrack(
                    this.m_edgeDeleted, this.m_edgeAdded, this.m_edgeSet,
                    backType, this.m_t, n, srcdst.solution, f, this.m_xend,
                    this.m_yend, backStep, q, tempEdgeSet,
                    this.m_yendCount, this.m_xcandidates2,
                    this.m_xendCount);
                q--;

                if (backStep == 2) {
                  gain = gain1;
                } else
                  if (backStep == 1) {
                    gain = gain1 = 0;
                  }
                continue choseQ;
              }
            }
          }

          // TODO randomizer
          choseY: for (; this.m_yendCount[q] <= this.m_m; this.m_yendCount[q]++) {
            // int Yindex;
            // Yindex = r.nextInt(n-(this.m_yendCount[q]-1))+1;
            // if(q==1){
            // this.m_yend[q]=this.m_ycandidates1[Yindex];
            // this.m_ycandidates1[Yindex] =
            // this.m_ycandidates1[(n-(this.m_yendCount[q]-1))];
            // this.m_ycandidates1[(n-(this.m_yendCount[q]-1))] =
            // this.m_yend[q];
            //
            // }else if(q==2){
            // this.m_yend[q]=this.m_ycandidates2[Yindex];
            // this.m_ycandidates2[Yindex] =
            // this.m_ycandidates2[(n-(this.m_yendCount[q]-1))];
            // this.m_ycandidates2[(n-(this.m_yendCount[q]-1))] =
            // this.m_yend[q];
            // }else{
            // this.m_yend[q]=this.m_ycandidatesAfter2[Yindex];
            // this.m_ycandidatesAfter2[Yindex] =
            // this.m_ycandidatesAfter2[(n-(this.m_yendCount[q]-1))];
            // this.m_ycandidatesAfter2[(n-(this.m_yendCount[q]-1))] =
            // this.m_yend[q];
            //
            // }

            // randomly chosing one from the m nearest neighbors from the
            // candidate set
            // int Yindex;
            // Yindex = r.nextInt(this.m_m-(this.m_yendCount[q]-1))+1;
            // this.m_yend[q] =
            // this.m_CandidatesOfY.getCandidate(this.m_t[2*q], Yindex);
            // this.m_CandidatesOfY.m_candidates[(((this.m_t[2*q]-1)*this.m_m)+(Yindex-1))]
            // =
            // this.m_CandidatesOfY.m_candidates[(((this.m_t[2*q]-1)*this.m_m)+(this.m_m-(this.m_yendCount[q]-1)-1))];
            // this.m_CandidatesOfY.m_candidates[(((this.m_t[2*q]-1)*this.m_m)+(this.m_m-(this.m_yendCount[q]-1)-1))]
            // =
            // this.m_yend[q];

            // int Yindex;
            // Yindex = r.nextInt(this.m_m-(this.m_yendCount[q]-1))+1;
            // this.m_yend[q] =
            // this.m_CandidatesOfY.getCandidate(this.m_t[2*q], Yindex);
            // this.m_CandidatesOfY.m_candidates[(((this.m_t[2*q]-1)*this.m_m)+(Yindex-1))]
            // =
            // this.m_CandidatesOfY.m_candidates[(((this.m_t[2*q]-1)*this.m_m)+(this.m_m-(this.m_yendCount[q]-1)-1))];
            // this.m_CandidatesOfY.m_candidates[(((this.m_t[2*q]-1)*this.m_m)+(this.m_m-(this.m_yendCount[q]-1)-1))]
            // =
            // this.m_yend[q];
            int Cindex;
            Cindex = this.r.nextInt(this.m_m - (this.m_yendCount[q] - 1));
            final int Yindex = this.m_CandidatesOfY2[((this.m_t[2 * q] - 1)
                * this.m_m) + Cindex];
            this.m_CandidatesOfY2[((this.m_t[2 * q] - 1) * this.m_m)
                + Cindex] = this.m_CandidatesOfY2[((this.m_t[2 * q] - 1)
                    * this.m_m) + (this.m_m - this.m_yendCount[q])];
            this.m_CandidatesOfY2[((this.m_t[2 * q] - 1) * this.m_m)
                + (this.m_m - this.m_yendCount[q])] = Yindex;

            this.m_yend[q] = this.m_CandidatesOfY
                .getCandidate(this.m_t[2 * q], Yindex);

            if (f.shouldTerminate()) {
              break choseT1; // Added to avoid time-outs
            }

            // TODO
            if (this.m_edgeSet.getEdges(this.m_yend[q],
                this.m_tempY) == 2) {
              if ((this.m_yend[q] != this.m_t[(2 * q) - 1])
                  && (this.m_yend[q] != this.m_t[2 * q])
                  && (this.m_yend[q] != LinKernighan_candiadate_20
                      .__getNextNode(this.m_t[2 * q], this.m_tempY[0],
                          this.m_tempY[1]))
                  && (this.m_yend[q] != LinKernighan_candiadate_20
                      .__getNextNode(this.m_t[2 * q], this.m_tempY[2],
                          this.m_tempY[3]))) {
                this.m_t[(2 * q) + 1] = this.m_yend[q];

                oldGain = gain;

                // System.out.println("q:"+ q);
                // System.out.println("this.m_t[2 * q]:"+this.m_t[2 * q]);
                gain += LinKernighan_candiadate_20.__g(this.m_t[2 * q],
                    this.m_t[(2 * q) + 1], this.m_t[(2 * q) - 1],
                    this.m_t[2 * q], f);
                // System.out.println(this.m_t[2 * q]);

                if (!this.m_edgeDeleted.hasEdge(this.m_t[2 * q],
                    this.m_t[(2 * q) + 1]) && (gain > 0)) {
                  this.m_edgeSet.addEdge(this.m_t[2 * q],
                      this.m_t[(2 * q) + 1]);

                  this.m_edgeAdded.addEdge(this.m_t[2 * q],
                      this.m_t[(2 * q) + 1]);

                  if (q == 1) {
                    gain1 = gain;
                  }
                  break choseY;
                } // gain>0

                gain = oldGain;
                this.m_t[(2 * q) + 1] = 0;
              }
            }

            if (this.m_yendCount[q] == this.m_m) {

              if (q > 2) {
                backType = 2;
                backStep = 2;

                LinKernighan_candiadate_20.__beforeBacktrack(
                    this.m_edgeDeleted, this.m_edgeAdded, this.m_edgeSet,
                    backType, this.m_t, n, srcdst.solution, f, this.m_xend,
                    this.m_yend, backStep, q, tempEdgeSet,
                    this.m_yendCount, this.m_xcandidates2,
                    this.m_xendCount);
                q = 1;
                if (backStep == 2) {
                  gain = gain1;
                }

                continue choseQ;
              }

              if ((q >= 1) && (q <= 2)) {
                backType = 1;
                backStep = q;
                LinKernighan_candiadate_20.__beforeBacktrack(
                    this.m_edgeDeleted, this.m_edgeAdded, this.m_edgeSet,
                    backType, this.m_t, n, srcdst.solution, f, this.m_xend,
                    this.m_yend, backStep, q, tempEdgeSet,
                    this.m_yendCount, this.m_xcandidates2,
                    this.m_xendCount);
                q--;

                if (backStep == 2) {
                  gain = gain1;
                } else
                  if (backStep == 1) {
                    gain = gain1 = 0;
                  }
                continue choseQ;
              }
            }
          } // choseY
        } // backtype
      } // choseq
    } // choseT1

    if (srcdst.solution != orig) {
      System.arraycopy(srcdst.solution, 0, orig, 0, orig.length);
      srcdst.solution = orig;
    }

  }

  // functions-----------------------------------------------------------------------------------------------------------
  /**
   * @param n
   * @param a
   * @param b
   * @return
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
   * @param yi1
   * @param yi2
   * @param xi1
   * @param xi2
   * @param f
   * @return
   */
  private static final long __g(final int yi1, final int yi2,
      final int xi1, final int xi2, final ObjectiveFunction f) {
    return f.distance(xi1, xi2) - f.distance(yi1, yi2);

  }

  /**
   * @param edgeSetDeleted
   * @param edgeSetAdded
   * @param edgeSet
   * @param backType
   * @param t
   * @param n
   * @param sol
   * @param f
   * @param xend
   * @param yend
   * @param backStep
   * @param q
   * @param tempEdgeSet
   * @param yendCount
   * @param xendcandidate2
   * @param xendCount
   */
  private static final void __beforeBacktrack(final EdgeSet edgeSetDeleted,
      final EdgeSet edgeSetAdded, final EdgeSet edgeSet,
      final int backType, final int[] t, final int n, final int[] sol,
      final ObjectiveFunction f, final int[] xend, final int[] yend,
      final int backStep, final int q, final EdgeSet tempEdgeSet,
      final int[] yendCount, final int[] xendcandidate2,
      final int[] xendCount) {

    edgeSet.copyOf(tempEdgeSet);
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

      Arrays.fill(yendCount, 3, yendCount.length, 1);
      Arrays.fill(xendCount, 3, xendCount.length, 0);

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

        Arrays.fill(yendCount, 2, yendCount.length, 1);
        Arrays.fill(xendCount, 3, xendCount.length, 0);
      } else {
        if ((backType == 2) && (backStep == 1)) {
          // x2 -> y1

          edgeSet.deleteEdgeIfExists(t[1], t[2]);

          // set edgesetdeleted
          edgeSetDeleted.addEdge(t[1], t[2]);

          // set xend and yend
          Arrays.fill(xend, 2, xend.length, 0);
          Arrays.fill(yend, 2, yend.length, 1);

          Arrays.fill(yendCount, 2, yendCount.length, 1);
          Arrays.fill(xendCount, 2, xendCount.length, 0);

          Arrays.sort(xendcandidate2);
        } else {
          if ((backType == 1) && (backStep == 1)) {
            // y1->x1

            // set xend and yend
            Arrays.fill(xend, 2, xend.length, 0);
            Arrays.fill(yend, 1, yend.length, 1);

            Arrays.fill(yendCount, 1, yendCount.length, 1);
            Arrays.fill(xendCount, 2, xendCount.length, 0);
            Arrays.sort(xendcandidate2);

          }

        }
      }
    }
  }

}