package test.junit.org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.simulatedAnnealing;

import org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.simulatedAnnealing.ProblemDependentSA;

import test.junit.org.logisticPlanning.tsp.solving.algorithms.TSPAlgorithmSymmetricTest;

public class ProblemDependentSATest extends TSPAlgorithmSymmetricTest
{
	/** creat */
	public ProblemDependentSATest()
	{
		super();
	}

	 /** {@inheritDoc} */
	@Override
	protected ProblemDependentSA createAlgorithm()
	{
		return new ProblemDependentSA();
	}

}
