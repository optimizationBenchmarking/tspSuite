// TSP Suite, version 0.9.8
// Copyright (c) 2012-2014 Thomas Weise, http://www.it-weise.de/
// License : The GNU Lesser General Public License, version 3.0
// Project : TSP Suite, Version: 0.9.8, Target Platform: Java 1.7
// File    : main.java.org.logisticPlanning.tsp.solving.operators.permutation.mutation.PermutationTabuSearchMutation.java
// Website : http://www.logisticPlanning.org/tsp
// Packaged: 2014-04-26 18:05:48 GMT+0800
// 
// 
package org.logisticPlanning.tsp.solving.operators.permutation.mutation;

import org.logisticPlanning.tsp.solving.algorithms.localSearch.TSPLocalSearchBasedMutation;
import org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.ts.TabuSearchEx;
import org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.ts.TabuSearchObjective;
import org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.ts.TabuSearchSolution;

/**
 *   <p>  An mutator that performs a local search according to the  {@link org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.TabuSearch.TabuSearch  Multi-Neighborhood Search} and by default returns an individual which is  different from its parent. If the parent is already a local optimum, it will  be partially shuffled and the local search is applied to the shuffled  individual until it will result in a different solution.  </p>  <h2>Change Log</h2><dl> <dt style="font-weight:bold">0.9.8 / 2014-01-02</dt> <dd>The package {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics} has been split: Until now, it contained also Stochastic Local Search (SLS)&nbsp;[<a href="#cite_HS2005SLSFAA" style="font-weight:bold">1</a>] algorithms. These were now moved into their own package {@link org.logisticPlanning.tsp.solving.algorithms.localSearch}. This decision was made so that we can introduce a new parent class for all SLS algorithms, {@link org.logisticPlanning.tsp.solving.algorithms.localSearch.TSPLocalSearchAlgorithm}, which should come with provisions to be wrapped into a mutation operator. This operator can then, in turn, be used inside {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics metaheuristics} to form, e.g., {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.heuristicInitMA.PermutationHeuristicInitMA Memetic algorithms}.<br/>As a result, this class is now a sub-class of {@link org.logisticPlanning.tsp.solving.algorithms.localSearch.TSPLocalSearchBasedMutation} and has no separate code anymore.</dd> </dl>
@author <em><a href="http://www.it-weise.de/">Thomas Weise</a></em>, Email:&nbsp;<a href="mailto:tweise@ustc.edu.cn">tweise@ustc.edu.cn</a>, Tel.:&nbsp;<a href="tel:+86 187 551 228 41">+86 187 551 228 41</a>; <a href="http://www.ustc.edu.cn/">University of Science and Technology of China (USTC)</a> [&#x4E2D;&#x56FD;&#x79D1;&#x5B66;&#x6280;&#x672F;&#x5927;&#x5B66;], (<a href="https://en.wikipedia.org/wiki/University_of_Science_and_Technology_of_China">wikipedia</a>); <a href="http://cs.ustc.edu.cn/">School of Computer Science and Technology (SCST)</a> [&#x8BA1;&#x7B97;&#x673A;&#x79D1;&#x5B66;&#x4E0E;&#x6280;&#x672F;&#x5B66;&#x9662;]; <a href="http://ubri.ustc.edu.cn/">USTC-Birmingham Joint Research Institute in Intelligent Computation and Its Applications (UBRI)</a>; West Campus [&#x897F;&#x533A;]; Crossroad of Huangshan Road and Feixi Road [&#x9EC4;&#x5C71;&#x8DEF;/&#x80A5;&#x897F;&#x8DEF;&#x5341;&#x5B57;&#x8DEF;&#x53E3;]; <a href="https://en.wikipedia.org/wiki/Hefei">Hefei</a> [&#x5408;&#x80A5;&#x5E02;] 230027; <a href="https://en.wikipedia.org/wiki/Anhui">Anhui</a> [&#x5B89;&#x5FBD;&#x7701;]; <a href="https://en.wikipedia.org/wiki/People%27s_Republic_of_China">China</a> [&#x4E2D;&#x56FD;]
@version version:0.9.8/file:2014-01-06T06:30:38_907+0800/build:2014-04-26T18:05:48_340+0800/platform:Java_1.7
 */
public class PermutationTabuSearchMutation extends
    TSPLocalSearchBasedMutation<int[]> {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** create */
  public PermutationTabuSearchMutation() {
    super(new TabuSearchEx());
  }
}
// 
// 
// Information about the third-party software used is given in the file
// NOTICE.txt distributed with this framework. More information on this
// project is given at http://www.logisticPlanning.org/tsp. The license of
// this software and its source code (except the third party components) is
// the The GNU Lesser General Public License, version 3.0 (LGPL 3.0). A copy
// of the LGPL 3.0 is in file LICENSE.txt; and can also be found at
// http://www.opensource.org/licenses/LGPL-3.0.
// 
// Contact: Dr. Thomas Weise
// The USTC-Birmingham Joint Research Institute in Intelligent Computation and
// Its Applications (UBRI)
// School of Computer Science and Technology (SCST)
// University of Science and Technology of China (USTC)
// West Campus, Huangshan Road/Feixi Road, Hefei 230027, Anhui, China
// Email: tweise@gmx.de, tweise@ustc.edu.cn  Web: http://www.it-weise.de/
