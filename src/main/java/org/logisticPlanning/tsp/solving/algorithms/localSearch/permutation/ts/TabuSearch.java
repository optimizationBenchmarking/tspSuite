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
import java.util.Iterator;
import java.util.LinkedHashSet;

import org.logisticPlanning.tsp.benchmarking.instances.Instance;
import org.logisticPlanning.tsp.benchmarking.objective.LogPoint;
import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;
import org.logisticPlanning.tsp.solving.TSPAlgorithm;
import org.logisticPlanning.tsp.solving.TSPAlgorithmRunner;
import org.logisticPlanning.tsp.solving.TSPModule;
import org.logisticPlanning.tsp.solving.operators.permutation.creation.PermutationCreateUniform;
import org.logisticPlanning.tsp.solving.operators.permutation.update.PermutationUpdateOperator;
import org.logisticPlanning.tsp.solving.operators.permutation.update.PermutationUpdateOperators;
import org.logisticPlanning.utils.config.Configurable;
import org.logisticPlanning.utils.config.Configuration;
import org.logisticPlanning.utils.math.random.Randomizer;

/**
 * <p>
 * A simple tabu search, i.e., a greedy search, which in each step checks
 * which one of a set of available
 * {@link org.logisticPlanning.tsp.solving.operators.permutation.update.PermutationUpdateOperator
 * updating operations}&nbsp;[<a href="#cite_RN2002AI"
 * style="font-weight:bold">1</a>, <a href="#cite_JCS2003HC"
 * style="font-weight:bold">2</a>, <a href="#cite_DHS2000HC"
 * style="font-weight:bold">3</a>] would yield the best improvement and
 * then applies this operator.
 * </p>
 */
public class TabuSearch extends TSPAlgorithm {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the updating operators: {@value} */
  public static final String PARAM_UPDATING_OPERATORS = "updatingOperators"; //$NON-NLS-1$

  /** the update operations */
  private PermutationUpdateOperator[] m_ops;

  /** the length of tabu list: {@value} */
  public static final String PARAM_TABULIST_LENGTH = "tabuListLength"; //$NON-NLS-1$

  /** the length of tabu list */
  private int tabuListLength;

  /** the tabu list */
  private LinkedHashSet<PathHash> tabuList;

  /** the list */
  private int[] m_sort;

  /** instantiate */
  public TabuSearch() {
    super("TabuSearch");//$NON-NLS-1$
    this.m_ops = PermutationUpdateOperators.OPERATORS_AND_COMPLEMENT;
    this.tabuListLength = 10;
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
   *          the tabu list length
   */
  public void setTabuListLength(final int ptabuListLength) {
    this.tabuListLength = ptabuListLength;
  }

  /**
   * Perform the tabu search
   * 
   * @param args
   *          the command line arguments
   */
  public final static void main(final String[] args) {
    TSPAlgorithmRunner.benchmark(//
        Instance.SYMMETRIC_INSTANCES, TabuSearch.class,//
        args);
  }

  /** {@inheritDoc} */
  @Override
  public final void solve(final ObjectiveFunction f) {
    final PermutationUpdateOperator[] ops;
    PathHash ols, pathOfSol;
    // final int[] sol;
    final Randomizer r;
    final int n;
    final long regFEs;
    int d, a, b, j, listLength, oldHash;
    long value;
    Iterator<PathHash> xxxx;
    PermutationUpdateOperator best;
    LogPoint cp;

    /** the Neighborhood */

    /** the array used to sort the objective value of solutions */
    final int[] sort = this.m_sort;

    r = f.getRandom();

    cp = f.getCurrentLogPoint();
    ols = new PathHash(f.n());
    if (cp.getConsumedFEs() > 0) {
      pathOfSol = new PathHash(f.n());
      f.getCopyOfBest(pathOfSol.m_data);
      value = cp.getBestF();
    } else {
      pathOfSol = new PathHash(PermutationCreateUniform.create(f.n(), r));
      value = f.evaluate(pathOfSol.m_data);
    }

    // TODO: <added>
    // Add the starting solution to the hash set
    pathOfSol.updateHashCode();
    this.tabuList.add(pathOfSol);
    // TODO: </added>

    ops = this.m_ops.clone();// initialize ops
    regFEs = ops.length;
    n = f.n();

    while (!(f.shouldTerminate())) {

      a = r.nextInt(n);
      do {
        b = r.nextInt(n);
      } while (a == b);

      best = null;

      // TODO: we do not need to clear the list, as it will be overridden
      // anyway
      listLength = 0;
      for (final PermutationUpdateOperator o : this.m_ops) {
        d = o.delta(pathOfSol.m_data, f, a, b);
        // build and rank the neighborhood and PermutationUpdateOperator
        j = Arrays.binarySearch(sort, 0, listLength, d);
        if (j < 0) {
          j = ((-j) - 1);
        }

        System.arraycopy(sort, j, sort, j + 1, listLength - j);
        System.arraycopy(ops, j, ops, j + 1, listLength - j);
        sort[j] = d;
        ops[j] = o;

        listLength++;
      }

      // We now try to find whether the best improvement we had is in the
      // taboo list. If it is, we try the next best change.
      oldHash = pathOfSol.m_hash;
      for (j = 0; j < ops.length; j++) {
        // TODO: <added>
        if (sort[j] == PermutationUpdateOperator.NO_EFFECT) {
          // TODO: if there is no effect, we can stop here
          break;
        }
        // TODO: </added>
        best = ops[j];
        best.update(pathOfSol.m_data, a, b);
        pathOfSol.updateHashCode();
        // synchronized (lockObject) {
        if (this.tabuList.contains(pathOfSol)) {
          best.revertUpdate(pathOfSol.m_data, a, b);
          pathOfSol.m_hash = oldHash;
          continue;
        }

        value += sort[j];
        if (this.tabuList.size() >= this.tabuListLength) {
          xxxx = this.tabuList.iterator();
          if (xxxx.hasNext()) {
            xxxx.next();
            xxxx.remove();
          }
          System.arraycopy(pathOfSol.m_data, 0, ols.m_data, 0,
              ols.m_data.length);
          ols.m_hash = pathOfSol.m_hash;
          this.tabuList.add(ols);
        } else {
          this.tabuList.add(pathOfSol.clone());
        }
        break;
      }

      f.registerFEs(regFEs, pathOfSol.m_data, value);
    }
  }

  /** {@inheritDoc} */
  @Override
  public final TabuSearch clone() {
    final TabuSearch res;
    final PermutationUpdateOperator[] ops;
    int i;

    res = ((TabuSearch) (super.clone()));
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

    this.tabuListLength = config.getInt(TabuSearch.PARAM_TABULIST_LENGTH,
        0, 1000, this.tabuListLength);
  }

  /** {@inheritDoc} */
  @Override
  public void printConfiguration(final PrintStream ps) {
    super.printConfiguration(ps);

    Configurable.printKey(TabuSearch.PARAM_UPDATING_OPERATORS, ps);
    Configurable.printlnObject(this.m_ops, ps);
  }

  /** {@inheritDoc} */
  @Override
  public void beginRun(final ObjectiveFunction f) {
    super.beginRun(f);
    TSPModule.invokeBeginRun(f, this.m_ops);
    this.tabuList = new LinkedHashSet<>(this.tabuListLength);// TODO: added
    this.m_sort = new int[this.m_ops.length];
  }

  /** {@inheritDoc} */
  @Override
  public void endRun(final ObjectiveFunction f) {
    this.tabuList = null;// TODO: added
    this.m_sort = null;
    try {
      TSPModule.invokeEndRun(f, this.m_ops);
    } finally {
      super.endRun(f);
    }
  }
}
