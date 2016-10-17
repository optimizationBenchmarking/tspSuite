package org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.linKernighan;

import org.logisticPlanning.tsp.benchmarking.instances.Instance;
import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;
import org.logisticPlanning.tsp.solving.Individual;
import org.logisticPlanning.tsp.solving.TSPAlgorithm;
import org.logisticPlanning.tsp.solving.TSPAlgorithmRunner;
import org.logisticPlanning.tsp.solving.algorithms.localSearch.TSPLocalSearchAlgorithm;
import org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.multiNeighborhoodSearch.MultiNeighborhoodSearch;

/** missing doc */
public class LKwithMNS_10 extends TSPLocalSearchAlgorithm<int[]> {
  /** missing doc */
  public LKwithMNS_10() {
    super("LKwithMNS"); //$NON-NLS-1$
    // TODO Auto-generated constructor stub
  }

  /** missing doc */
  private static final long serialVersionUID = 1L;

  /** missing doc */
  private LinKernighan_candiadate_10 LK;

  /** missing doc */
  private MultiNeighborhoodSearch MNS;

  /**
   * the main routine
   *
   * @param args
   *          the command line arguments
   */
  public final static void main(final String[] args) {
    TSPAlgorithmRunner.benchmark(Instance.SYMMETRIC_INSTANCES,
        LKwithMNS_10.class, args);
  }

  /** {@inheritDoc} */
  @Override
  public TSPAlgorithm clone() {
    // TODO Auto-generated method stub
    LKwithMNS_10 x;
    x = ((LKwithMNS_10) (super.clone()));
    // x.LK.clone();
    x.LK = null;
    // x.MNS.clone();
    x.MNS = null;
    return x;
  }

  /** {@inheritDoc} */
  @Override
  public void beginRun(final ObjectiveFunction f) {
    // TODO Auto-generated method stub
    super.beginRun(f);
    this.LK = new LinKernighan_candiadate_10();
    this.LK.beginRun(f);
    this.MNS = new MultiNeighborhoodSearch();
    this.MNS.beginRun(f);

  }

  /** {@inheritDoc} */
  @Override
  public void endRun(final ObjectiveFunction f) {
    // TODO Auto-generated method stub
    this.LK.endRun(f);
    this.LK = null;
    this.MNS.endRun(f);
    this.MNS = null;
    super.endRun(f);
  }

  /** {@inheritDoc} */
  @Override
  public void localSearch(final Individual<int[]> srcdst,
      final ObjectiveFunction f) {
    // TODO Auto-generated method stub
    long len, lenLK;

    lenLK = Long.MAX_VALUE;
    do {
      len = srcdst.tourLength;
      this.LK.localSearch(srcdst, f);
      if (f.shouldTerminate()) {
        return;
      }
      if (lenLK > srcdst.tourLength) {
        this.MNS.localSearch(srcdst, f);
      }
      lenLK = srcdst.tourLength;
    } while ((len > srcdst.tourLength) && (!f.shouldTerminate()));
  }

}
