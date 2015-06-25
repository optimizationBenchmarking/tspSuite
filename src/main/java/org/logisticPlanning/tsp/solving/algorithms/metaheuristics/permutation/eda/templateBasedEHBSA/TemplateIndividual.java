package org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.eda.templateBasedEHBSA;

import org.logisticPlanning.tsp.solving.Individual;

/**
 *
 an individual record that also remembers whether it was part of the
 * model.
 *
 * @author <ul>
 *         <li>
 *         <em><a href="http://www2.hannan-u.ac.jp/~tsutsui/index-e.html">Shigeyoshi Tsutsui</a></em>
 *         [&#x7B52;&#x4E95; &#x8302;&#x7FA9;], Email:&nbsp;<a
 *         href="mailto:tsutsui@hannan-u.ac.jp">tsutsui@hannan-u.ac.jp</a></li>
 *         <li><em><a href="http://www.it-weise.de/">Thomas Weise</a></em>,
 *         Email:&nbsp;<a
 *         href="mailto:tweise@ustc.edu.cn">tweise@ustc.edu.cn</a> (role:
 *         adaption to benchmarking framework)</li>
 *         </ul>
 */
public final class TemplateIndividual extends Individual<int[]> {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * is this individual part of the model? @serial serializable field
   */
  public boolean isPartOfModel;

  /** instantiate */
  TemplateIndividual() {
    super();
  }
}
