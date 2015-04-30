package org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.hc;

import java.io.PrintStream;

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
import org.logisticPlanning.utils.math.random.Randomizer;

/**
 * <p>
 * A simple hill climber, i.e., a greedy search, which in each step checks
 * which one of a set of available
 * {@link org.logisticPlanning.tsp.solving.operators.permutation.update.PermutationUpdateOperator
 * updating operations}&nbsp;[<a href="#cite_RN2002AI"
 * style="font-weight:bold">1</a>, <a href="#cite_JCS2003HC"
 * style="font-weight:bold">2</a>, <a href="#cite_DHS2000HC"
 * style="font-weight:bold">3</a>] would yield the best improvement and
 * then applies this operator.
 * </p>
 * <h2>References</h2>
 * <ol>
 * <li><div><span id="cite_RN2002AI" />Stuart J. Russell and&nbsp;Peter
 * Norvig: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Artificial
 * Intelligence: A Modern Approach (AIMA),&rdquo;</span> December&nbsp;20,
 * 2002, Upper Saddle River, NJ, USA: Prentice Hall International Inc.
 * and&nbsp;Upper Saddle River, NJ, USA: Pearson Education. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0137903952">0-13-790395-2</a>, <a
 * href="https://www.worldcat.org/isbn/0130803022">0-13-080302-2</a>, <a
 * href="https://www.worldcat.org/isbn/8120323823">8120323823</a>, <a
 * href="https://www.worldcat.org/isbn/9780137903955"
 * >978-0-13-790395-5</a>, <a
 * href="https://www.worldcat.org/isbn/9780130803023"
 * >978-0-13-080302-3</a>, and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9788120323827">9788120323827</a>;
 * Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=5mfMAQAACAAJ">5mfMAQAACAAJ</a>,
 * <a
 * href="http://books.google.com/books?id=DvqIIwAACAAJ">DvqIIwAACAAJ</a>,
 * <a
 * href="http://books.google.com/books?id=5WfMAQAACAAJ">5WfMAQAACAAJ</a>,
 * and&nbsp;<a
 * href="http://books.google.com/books?id=0GldGwAACAAJ">0GldGwAACAAJ
 * </a></div></li>
 * <li><div><span id="cite_JCS2003HC" />James C. Spall: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Introduction to
 * Stochastic Search and Optimization,&rdquo;</span> June&nbsp;2003,
 * Estimation, Simulation, and Control &#8211; Wiley-Interscience Series in
 * Discrete Mathematics and Optimization, Chichester, West Sussex, UK:
 * Wiley Interscience. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0471330523">0-471-33052-3</a>, <a
 * href="https://www.worldcat.org/isbn/0471722138">0-471-72213-8</a>, <a
 * href
 * ="https://www.worldcat.org/isbn/9780471330523">978-0-471-33052-3</a>,
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780471722137">978-0-471-
 * 72213-7</a>; LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/2002038049">2002038049</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/51235522">51235522</a>, <a
 * href="https://www.worldcat.org/oclc/637018778">637018778</a>, <a
 * href="https://www.worldcat.org/oclc/474647590">474647590</a>,
 * and&nbsp;<a href="https://www.worldcat.org/oclc/50773216">50773216</a>;
 * Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=f66OIvvkKnAC"
 * >f66OIvvkKnAC</a></div></li>
 * <li><div><span id="cite_DHS2000HC" />Richard O. Duda, Peter Elliot Hart,
 * and&nbsp;David G. Stork: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Pattern
 * Classification,&rdquo;</span> November&nbsp;2000, Estimation,
 * Simulation, and Control &#8211; Wiley-Interscience Series in Discrete
 * Mathematics and Optimization, Chichester, West Sussex, UK: Wiley
 * Interscience. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0471056693">0-471-05669-3</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780471056690">978-0-471-
 * 05669-0</a>; LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/99029981">99029981</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/154744650">154744650</a>, <a
 * href="https://www.worldcat.org/oclc/474918353">474918353</a>,
 * and&nbsp;<a href="https://www.worldcat.org/oclc/41347061">41347061</a>;
 * Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=YoxQAAAAMAAJ">YoxQAAAAMAAJ</a>,
 * <a
 * href="http://books.google.com/books?id=hyQgQAAACAAJ">hyQgQAAACAAJ</a>,
 * and&nbsp;<a
 * href="http://books.google.com/books?id=o3I8PgAACAAJ">o3I8PgAACAAJ</a>;
 * PPN:&nbsp;<a
 * href="http://gso.gbv.de/PPNSET?PPN=303957670">303957670</a></div></li>
 * </ol>
 */
public class UpdatingPermutationHillClimber extends TSPAlgorithm {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the updating operators: {@value} */
  public static final String PARAM_UPDATING_OPERATORS = "updatingOperators"; //$NON-NLS-1$

  /** the update operations */
  private PermutationUpdateOperator[] m_ops;

  /** instantiate */
  public UpdatingPermutationHillClimber() {
    super("Permutation-Updating Hill Climber");//$NON-NLS-1$

    this.m_ops = PermutationUpdateOperators.OPERATORS_AND_COMPLEMENT;
  }

  /**
   * Perform the hill climbing
   * 
   * @param args
   *          the command line arguments
   */
  public final static void main(final String[] args) {
    TSPAlgorithmRunner.benchmark(
        //
        Instance.SYMMETRIC_INSTANCES,
        UpdatingPermutationHillClimber.class,//
        args);
  }

  /** {@inheritDoc} */
  @Override
  public final void solve(final ObjectiveFunction f) {
    final PermutationUpdateOperator[] ops;
    final int[] sol;
    final Randomizer r;
    final int n;
    final long regFEs;
    int d, delta, a, b;
    long value;
    PermutationUpdateOperator best;
    LogPoint cp;

    r = f.getRandom();

    cp = f.getCurrentLogPoint();
    if (cp.getConsumedFEs() > 0) {
      sol = new int[f.n()];
      f.getCopyOfBest(sol);
      value = cp.getBestF();
    } else {
      sol = PermutationCreateUniform.create(f.n(), r);
      value = f.evaluate(sol);
    }

    ops = this.m_ops;

    regFEs = ops.length;
    n = f.n();

    while (!(f.shouldTerminate())) {

      a = r.nextInt(n);
      do {
        b = r.nextInt(n);
      } while (a == b);

      best = null;
      delta = Integer.MAX_VALUE;
      for (final PermutationUpdateOperator o : ops) {
        d = o.delta(sol, f, a, b);
        if ((d != PermutationUpdateOperator.NO_EFFECT) && (d < delta)) {
          // only consider better deltas that can have an effect
          delta = d;
          best = o;
        }
      }

      if ((delta <= 0) && (best != null)) {
        best.update(sol, a, b);
        if (delta < 0) {
          value += delta;
          f.registerFEs(regFEs, sol, value);
          continue;
        }
      }

      f.registerFEs(regFEs, null, value + delta);
    }
  }

  /** {@inheritDoc} */
  @Override
  public final UpdatingPermutationHillClimber clone() {
    final UpdatingPermutationHillClimber res;
    final PermutationUpdateOperator[] ops;
    int i;

    res = ((UpdatingPermutationHillClimber) (super.clone()));
    res.m_ops = ops = res.m_ops.clone();
    for (i = ops.length; (--i) >= 0;) {
      ops[i] = ops[i].clone();
    }

    return res;
  }

  /** {@inheritDoc} */
  @Override
  public void printConfiguration(final PrintStream ps) {
    super.printConfiguration(ps);

    Configurable.printKey(
        UpdatingPermutationHillClimber.PARAM_UPDATING_OPERATORS, ps);
    Configurable.printlnObject(this.m_ops, ps);
  }

  /** {@inheritDoc} */
  @Override
  public void beginRun(final ObjectiveFunction f) {
    super.beginRun(f);
    TSPModule.invokeBeginRun(f, this.m_ops);
  }

  /** {@inheritDoc} */
  @Override
  public void endRun(final ObjectiveFunction f) {
    try {
      TSPModule.invokeEndRun(f, this.m_ops);
    } finally {
      super.endRun(f);
    }
  }
}
