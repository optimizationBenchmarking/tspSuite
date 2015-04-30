package test.junit.org.logisticPlanning.tsp.benchmarking.instances;

import org.logisticPlanning.tsp.benchmarking.instances.Instance;

/**
 * Test the TSP instance
 * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#BURMA14}
 * from <a
 * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/">TSPLib
 * </a>. We test both, loaded distance computers which are raw (i.e.,
 * potentially based on coordinate lists), and those which use matrices
 * backing the results.
 */
public class Instance_BURMA14_Test extends _InstanceTest {

  /**
   * test the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#BURMA14}
   */
  public Instance_BURMA14_Test() {
    super(Instance.BURMA14);
  }
}
