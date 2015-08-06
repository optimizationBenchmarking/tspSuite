// TSP Suite, version 0.9.8
// Copyright (c) 2012-2014 Thomas Weise, http://www.it-weise.de/
// License : The GNU Lesser General Public License, version 3.0
// Project : TSP Suite, Version: 0.9.8, Target Platform: Java 1.7
// File :
// main.java.org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.hc.UpdatingPermutationHillClimber.java
// Website : http://www.logisticPlanning.org/tsp
// Packaged: 2014-04-26 18:05:48 GMT+0800
//
//
package org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.ts;

import java.io.PrintStream;
import java.util.Arrays;

import org.logisticPlanning.tsp.benchmarking.instances.Instance;
import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;
import org.logisticPlanning.tsp.solving.Individual;
import org.logisticPlanning.tsp.solving.TSPAlgorithmRunner;
import org.logisticPlanning.tsp.solving.TSPModule;
import org.logisticPlanning.tsp.solving.algorithms.localSearch.TSPLocalSearchAlgorithm;
import org.logisticPlanning.tsp.solving.operators.permutation.update.PermutationUpdateOperator;
import org.logisticPlanning.tsp.solving.operators.permutation.update.PermutationUpdateOperators;
import org.logisticPlanning.tsp.solving.utils.RepresentationUtils;
import org.logisticPlanning.utils.config.Configurable;
import org.logisticPlanning.utils.config.Configuration;

/**
 * <p>
 * A simple tabu search, i.e., a local search, which in each step checks if the best solution
 * is already in a list called tabu list. If it is, then checks the second best solution until
 * one solution not in the tabu list found and accepts this solution. And also, add each solution
 * we get in each step into the tabu list.
 * {@link org.logisticPlanning.tsp.solving.operators.permutation.update.PermutationUpdateOperator
 * updating operations}&nbsp;[<a href="#cite_RN2002AI"
 * style="font-weight:bold">1</a>, <a href="#cite_JCS2003HC"
 * style="font-weight:bold">2</a>, <a href="#cite_DHS2000HC"
 * style="font-weight:bold">3</a>] would yield the best improvement and then
 * applies this operator.
 * </p>
 * @author <em>Dan Xu</em>, 
 * Email:&nbsp;<a href="mailto:dandy@mail.ustc.edu.cn">dandy@mail.ustc.edu.cn</a>, 
 * Tel.:&nbsp;<a href="tel:+86 139 650 406 13">+86 139 650 406 13</a>; 
 * <a href="http://www.ustc.edu.cn/">University of Science and Technology of China (USTC)</a> 
 * [&#x4E2D;&#x56FD;&#x79D1;&#x5B66;&#x6280;&#x672F;&#x5927;&#x5B66;],
(<a href="https://en.wikipedia.org/wiki/University_of_Science_and_Technology_of_China">wikipedia</a>); 
<a href="http://cs.ustc.edu.cn/">School of Computer Science and Technology (SCST)</a> 
[&#x8BA1;&#x7B97;&#x673A;&#x79D1;&#x5B66;&#x4E0E;&#x6280;&#x672F;&#x5B66;&#x9662;]; 
<a href="http://ubri.ustc.edu.cn/">USTC-Birmingham Joint Research Institute in Intelligent Computation and Its Applications (UBRI)</a>; 
West Campus [&#x897F;&#x533A;]; Crossroad of Huangshan Road and Feixi Road [&#x9EC4;&#x5C71;&#x8DEF;/&#x80A5;&#x897F;&#x8DEF;&#x5341;&#x5B57;&#x8DEF;&#x53E3;]; <a href="https://en.wikipedia.org/wiki/Hefei">Hefei</a> [&#x5408;&#x80A5;&#x5E02;] 230027; <a href="https://en.wikipedia.org/wiki/Anhui">Anhui</a> [&#x5B89;&#x5FBD;&#x7701;]; 
 * <a href="https://en.wikipedia.org/wiki/People%27s_Republic_of_China">China</a> [&#x4E2D;&#x56FD;]
 */
public class RestartTS extends TSPLocalSearchAlgorithm<int[]> {
	/** the serial version uid */
	private static final long serialVersionUID = 1L;

	/** the updating operators: {@value} */
	public static final String PARAM_UPDATING_OPERATORS = "updatingOperators"; //$NON-NLS-1$

	/** the update operations */
	private PermutationUpdateOperator[] m_ops;

	/** the length of tabu list: {@value} */
	public static final String PARAM_ABSOLUTE_LENGTH = "tabuListLength"; //$NON-NLS-1$

	private int absolute;
	
	/** the length of tabu list */
	private int tabuListLength;

	/** the length of tabu list: {@value} */
	public static final String PARAM_RATE = "rate"; //$NON-NLS-1$

	/** the length of tabu list */
	private int rate;
	/** the tabu list */
	private int[] tabuList;

	/** the list */
	private int[] m_sort;

	private int tabuLen;

	private int tabuStart;

	/** instantiate */
	public RestartTS() {
		super("TabuSearch");//$NON-NLS-1$
		this.m_ops = PermutationUpdateOperators.OPERATORS_AND_COMPLEMENT;
		// this.tabuListLength = 10;
	}

	/**
	 * get the tabu list length
	 * 
	 * @return the tabu list length
	 */
	public int getTabuListLength() {
		return this.tabuListLength;
	}

	/**
	 * set the tabu list length
	 * 
	 * @param ptabuListLength
	 *            the tabu list length
	 */
	public void setTabuListLength(final int ptabuListLength) {
		this.tabuListLength = ptabuListLength;
	}

	/**
	 * Perform the tabu search
	 * 
	 * @param args
	 *            the command line arguments
	 */
	public final static void main(final String[] args) {
		TSPAlgorithmRunner.benchmark(//
				Instance.SYMMETRIC_INSTANCES, RestartTS.class,//
				args);
	}

	/** {@inheritDoc} */
	@Override
	public void localSearch(final Individual<int[]> srcdst,
			final ObjectiveFunction f) {
		final PermutationUpdateOperator[] ops;
		final int n;
		int d, a, b, min, max, newmin, newmax, j, listLength;
		boolean foundImprovement;
		int[] sol = srcdst.solution;

		PermutationUpdateOperator best;

		/** the Neighborhood */

		/** the array used to sort the objective value of solutions */
		final int[] sort = this.m_sort;

		ops = this.m_ops.clone();// initialize ops
		n = f.n();

		addTabu(RepresentationUtils.pathHashCode(sol));
		foundImprovement = true;
		newmin = 0;
		newmax = n - 1;

		while (foundImprovement && (!(f.shouldTerminate()))) {

			foundImprovement = false;

			min = newmin - 1;
			max = newmax + 1;
			newmin = n;
			newmax = 0;

			for (a = 0; a < n - 2; a++) {
				for (b = a + 1; b < n - 1; b++) {
					if ((a > max) || (b < min)) {
						continue;
					}

					best = null;

					// TODO: we do not need to clear the list, as it will be
					// overridden
					// anyway
					listLength = 0;
					for (final PermutationUpdateOperator o : this.m_ops) {
						d = o.delta(sol, f, a, b);

						if (d < 0) {
							// build and rank the neighborhood and
							// PermutationUpdateOperator
							j = Arrays.binarySearch(sort, 0, listLength, d);
							if (j < 0) {
								j = ((-j) - 1);
							}

							System.arraycopy(sort, j, sort, j + 1, listLength
									- j);
							System.arraycopy(ops, j, ops, j + 1, listLength - j);
							sort[j] = d;
							ops[j] = o;

							listLength++;
						}
					}

					// We now try to find whether the best improvement we had is
					// in the
					// taboo list. If it is, we try the next best change.

					for (j = 0; j < listLength; j++) {
						// TODO: <added>
						best = ops[j];
						best.update(sol, a, b);

						if (!isTabu(RepresentationUtils.pathHashCode(sol))) {

							srcdst.tourLength += sort[j];
							foundImprovement = true;
							addTabu(RepresentationUtils.pathHashCode(sol));

							j++;
							newmin = Math.min(newmin, a);
							newmax = Math.max(newmax, b);
							break;
						}
						best.revertUpdate(sol, a, b);
					}

					if (j > 0) {
						f.registerFEs(j, sol, srcdst.tourLength);
						if (f.shouldTerminate()) {
							return;
						}
					}
					else f.registerFEs(j, null, srcdst.tourLength);
				}
			}
		}
	}
	
	private final boolean isTabu(final int val) {

		if (this.tabuLen < this.tabuList.length) {
			for (int l : this.tabuList) {
				if (l == val) {
					return true;
				}
				if (l == 0L) {
					return false;
				}
			}
			return false;
		}

		for (int l : this.tabuList) {
			if (l == val) {
				return true;
			}
		}
		return false;
	}

	private final void addTabu(final int val) {
		if (this.tabuLen < this.tabuList.length) {
			this.tabuList[this.tabuLen++] = val;
		} else {
			this.tabuList[this.tabuStart] = val;
			this.tabuStart = (this.tabuStart + 1) % this.tabuList.length;
		}
	}

	/** {@inheritDoc} */
	@Override
	public final RestartTS clone() {
		final RestartTS res;
		final PermutationUpdateOperator[] ops;
		int i;

		res = ((RestartTS) (super.clone()));
		res.m_ops = ops = res.m_ops.clone();
		for (i = ops.length; (--i) >= 0;) {
			ops[i] = ops[i].clone();
		}
		res.tabuList = null;// TODO: added

		return res;
	}

	/** {@inheritDoc} */
	@Override
	public void configure(final Configuration config) {
		super.configure(config);

		this.absolute = config.getInt(RestartTS.PARAM_ABSOLUTE_LENGTH, 0,
				20000, this.absolute);
		this.rate = config.getInt(RestartTS.PARAM_RATE, 0, 1000, this.rate);
	}

	/** {@inheritDoc} */
	@Override
	public void printConfiguration(final PrintStream ps) {
		super.printConfiguration(ps);

		Configurable.printKey(RestartTS.PARAM_ABSOLUTE_LENGTH, ps);
		Configurable.printlnObject(this.tabuListLength, ps);
		Configurable.printKey(RestartTS.PARAM_RATE, ps);
		Configurable.printlnObject(this.rate, ps);
		Configurable.printKey(RestartTS.PARAM_UPDATING_OPERATORS, ps);
		Configurable.printlnObject(this.m_ops, ps);
	}

	/** {@inheritDoc} */
	@Override
	public void beginRun(final ObjectiveFunction f) {
		super.beginRun(f);
		TSPModule.invokeBeginRun(f, this.m_ops);
//		if ((this.absolute + (int) (this.rate * Math.sqrt(f.n()))) < f.n()
//				* (f.n() - 1))
			this.tabuListLength = this.absolute
					+ (int) (this.rate * Math.sqrt(f.n()));
//		else
//			this.tabuListLength = f.n();
		this.tabuList = new int[this.tabuListLength];// TODO: added
		this.m_sort = new int[this.m_ops.length];
		this.tabuLen = 0;
		this.tabuStart = 0;
	}
	
	/** {@inheritDoc} */
	@Override
	public void endRun(final ObjectiveFunction f) {
		this.tabuList = null;// TODO: added
		this.m_sort = null;
		this.tabuLen = 0;
		this.tabuStart = 0;
		try {
			TSPModule.invokeEndRun(f, this.m_ops);
		} finally {
			super.endRun(f);
		}
	}
}