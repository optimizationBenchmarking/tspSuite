package org.logisticPlanning.tsp.solving.algorithms.localSearch.ejectionchain;

import test.junit.org.logisticPlanning.tsp.solving.algorithms.TSPAlgorithmSymmetricTest;

public class F_SecTest  extends TSPAlgorithmSymmetricTest{
	/** create */
	public F_SecTest() {
		super();
	}
	
	/** {@inheritDoc} */
	@Override
	protected F_Sec createAlgorithm() {
		return new F_Sec();
	}
}
