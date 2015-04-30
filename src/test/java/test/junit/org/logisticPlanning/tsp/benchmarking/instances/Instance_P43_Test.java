package test.junit.org.logisticPlanning.tsp.benchmarking.instances;

import org.logisticPlanning.tsp.benchmarking.instances.Instance;

/**
 * Test the TSP instance
 * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#P43}
 * from <a
 * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/">TSPLib
 * </a>. We test both, loaded distance computers which are raw (i.e.,
 * potentially based on coordinate lists), and those which use matrices
 * backing the results.
 */
public class Instance_P43_Test extends _InstanceTest {

  /**
   * test the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#P43}
   */
  public Instance_P43_Test() {
    super(Instance.P43);
  }
}
