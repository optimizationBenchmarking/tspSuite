package org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.linKernighan;

import org.logisticPlanning.tsp.benchmarking.instances.Instance;
import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;
import org.logisticPlanning.tsp.solving.Individual;
import org.logisticPlanning.tsp.solving.TSPAlgorithm;
import org.logisticPlanning.tsp.solving.TSPAlgorithmRunner;
import org.logisticPlanning.tsp.solving.algorithms.localSearch.TSPLocalSearchAlgorithm;
import org.logisticPlanning.tsp.solving.algorithms.localSearch.ejectionchain.P_Sec;

/** missing doc */
public class EJMwithLK_10 extends TSPLocalSearchAlgorithm<int[]> {
  /** missing doc */
  public EJMwithLK_10() {
    super("LKwithEJM"); //$NON-NLS-1$
    // TODO Auto-generated constructor stub
  }

  /** missing doc */
  private static final long serialVersionUID = 1L;

  /** missing doc */
  private LinKernighan_candiadate_10 LK;

  /** missing doc */
  private P_Sec psec;

  /**
   * the main routine
   *
   * @param args
   *          the command line arguments
   */
  public final static void main(final String[] args) {
    TSPAlgorithmRunner.benchmark(Instance.SYMMETRIC_INSTANCES,
        EJMwithLK_10.class, args);
  }

  /** {@inheritDoc} */
  @Override
  public TSPAlgorithm clone() {
    // TODO Auto-generated method stub
    EJMwithLK_10 x;
    x = ((EJMwithLK_10) (super.clone()));
    // x.LK.clone();
    x.psec = null;
    // x.MNS.clone();
    x.LK = null;
    return x;
  }

  /** {@inheritDoc} */
  @Override
  public void beginRun(final ObjectiveFunction f) {
    // TODO Auto-generated method stub
    super.beginRun(f);
    this.psec = new P_Sec();
    this.psec.beginRun(f);
    this.LK = new LinKernighan_candiadate_10();
    this.LK.beginRun(f);

  }

  /** {@inheritDoc} */
  @Override
  public void endRun(final ObjectiveFunction f) {
    // TODO Auto-generated method stub
    this.LK.endRun(f);
    this.LK = null;
    this.psec.endRun(f);
    this.psec = null;
    super.endRun(f);
  }

  /** {@inheritDoc} */
  @Override
  public void localSearch(final Individual<int[]> srcdst,
      final ObjectiveFunction f) {
    // TODO Auto-generated method stub
    long len;
    long lenPSEC;

    // lenLK = Long.MAX_VALUE;
    lenPSEC = Long.MAX_VALUE;
    do {
      len = srcdst.tourLength;
      // this.LK.localSearch(srcdst, f);
      this.psec.localSearch(srcdst, f);
      if (f.shouldTerminate()) {
        return;
      }
      if (lenPSEC > srcdst.tourLength) {
        // this.psec.localSearch(srcdst, f);
        this.LK.localSearch(srcdst, f);
      }
      lenPSEC = srcdst.tourLength;
    } while ((len > srcdst.tourLength) && (!f.shouldTerminate()));
  }
}
