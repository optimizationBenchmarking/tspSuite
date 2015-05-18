package test.junit.org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.simulatedAnnealing;

import org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.simulatedAnnealing.SimulatedAnnealing;

import test.junit.org.logisticPlanning.tsp.solving.algorithms.TSPAlgorithmSymmetricTest;


public class SimulatedAnnealingTest extends TSPAlgorithmSymmetricTest
{
	/** creat */
	public SimulatedAnnealingTest()
	{
		super();
	}

	 /** {@inheritDoc} */
	@Override
	protected SimulatedAnnealing createAlgorithm()
	{
		return new SimulatedAnnealing();
	}
}