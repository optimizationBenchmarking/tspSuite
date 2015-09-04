package test.junit.org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.ea.resizeStrategies;

import java.util.Random;

import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.general.ea.populationResize.RandomResize;
import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.ea.PermutationEA;

import test.junit.org.logisticPlanning.tsp.solving.algorithms.TSPAlgorithmSymmetricTest;

/**
 * tthe test of the permutation EA with randomly changing population size
 */
public class PermutationEAParentsSurviveRandomResizeTest extends
    TSPAlgorithmSymmetricTest {

  /** create */
  public PermutationEAParentsSurviveRandomResizeTest() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  protected PermutationEA createAlgorithm() {
    PermutationEA res;
    Random r;
    int mu, lambda;

    res = new PermutationEA();
    res.setParentsSurvive(true);

    r = new Random();
    mu = (1 + r.nextInt(512));
    lambda = (mu + 1 + r.nextInt(512));
    res.setMu(mu);
    res.setLambda(lambda);
    res.setCrossoverRate(r.nextDouble());
    res.setResizeStrategy(RandomResize.INSTANCE);

    return res;
  }
}
