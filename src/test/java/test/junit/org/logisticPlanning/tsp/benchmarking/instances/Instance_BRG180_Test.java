package test.junit.org.logisticPlanning.tsp.benchmarking.instances;

import org.logisticPlanning.tsp.benchmarking.instances.Instance;

/**
 * Test the TSP instance
 * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#BRG180}
 * from <a
 * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/">TSPLib
 * </a>. We test both, loaded distance computers which are raw (i.e.,
 * potentially based on coordinate lists), and those which use matrices
 * backing the results. <h2>References</h2>
 * <ol>
 * <li><div><span id="cite_PR1987OOA5CSTSPBBAC" />Manfred W. Padberg
 * and&nbsp;Giovanni Rinaldi: <span
 * style="font-weight:bold">&ldquo;Optimization of a 532-City Symmetric
 * Traveling Salesman Problem by Branch and Cut,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Operations Research
 * Letters</span> 6(1):1&ndash;7, March&nbsp;1987; published by Amsterdam,
 * The Netherlands: Elsevier Science Publishers B.V.. doi:&nbsp;<a
 * href="http://dx.doi.org/10.1016/0167-6377(87)90002-2"
 * >10.1016/0167-6377(87)90002-2</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/01676377">0167-6377</a></div></li>
 * </ol>
 */
public class Instance_BRG180_Test extends _InstanceTest {

  /**
   * test the instance
   * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance#BRG180}
   */
  public Instance_BRG180_Test() {
    super(Instance.BRG180);
  }
}
