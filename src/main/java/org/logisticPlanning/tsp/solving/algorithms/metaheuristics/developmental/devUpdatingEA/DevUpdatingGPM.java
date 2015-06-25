package org.logisticPlanning.tsp.solving.algorithms.metaheuristics.developmental.devUpdatingEA;

import java.util.Arrays;

import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;
import org.logisticPlanning.tsp.solving.Individual;
import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.developmental.devUpdatingEA.indexes.IndexIterator;
import org.logisticPlanning.tsp.solving.gpm.GPM;
import org.logisticPlanning.tsp.solving.operators.permutation.creation.PermutationCreateCanonical;
import org.logisticPlanning.tsp.solving.operators.permutation.update.PermutationUpdateOperator;
import org.logisticPlanning.tsp.solving.searchSpaces.trees.math.Function;
import org.logisticPlanning.utils.math.random.Randomizer;

/**
 * <p>
 * The developmental update GPM implemented similarly to the description
 * in&nbsp;[<a href="#cite_OWDC2013SADAFTSP"
 * style="font-weight:bold">1</a>] runs in an
 * {@link org.logisticPlanning.tsp.solving.algorithms.metaheuristics.general.ea.EA
 * EA}. In each generation of the EA, this GPM takes the best known
 * solution. Each individual is a
 * {@link org.logisticPlanning.tsp.solving.searchSpaces.trees.math.Function
 * mathematical function} represented as
 * {@link org.logisticPlanning.tsp.solving.searchSpaces.trees.Node tree
 * data structure}. This function decides what to do. In each iteration of
 * the GPM, two indices into the permutation (candidate solution) are
 * generated. Some
 * {@link org.logisticPlanning.tsp.solving.operators.permutation.update.PermutationUpdateOperator
 * update operators} may be applied to the permutation and we can predict
 * how good the produced result would be. Based on that prediction and
 * other parameters, such as the last time an operation was used, the
 * function computes a value. The operator for which the smallest value was
 * computed will then be applied in the iteration, if the computed value is
 * less than zero, that is. This does not mean that the best operator is
 * applied in each step &ndash; sometimes even the worst one might be
 * chosen. This allows the algorithm to escape from local optima. This way,
 * step-by-step, an existing solution will be modified and changed and,
 * hopefully, some good solutions are found on the way.
 * </p>
 * <h2>References</h2>
 * <ol>
 * <li><div><span id="cite_OWDC2013SADAFTSP" /><a
 * href="mailto:oyjmical@mail.ustc.edu.cn">Jin Ouyang</a> <span
 * style="color:gray">[&#27431;&#38451;&#26187;</span>], <a
 * href="http://www.it-weise.de/">Thomas Weise</a> <span
 * style="color:gray">[&#27748;&#21355;&#24605;</span>], <a
 * href="http://www.marmakoide.org/">Alexandre Devert</a>, and&nbsp;<a
 * href="http://www.swinburne.edu.my/iSECURES/index.php?do=rchiong">Raymond
 * Chiong</a>: <span style="font-weight:bold">&ldquo;SDGP: A Developmental
 * Approach for Traveling Salesman Problems,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the 2013
 * IEEE Symposium on Computational Intelligence in Production and Logistics
 * Systems (CIPLS'13)</span>, April&nbsp;15&ndash;19, 2013, Singapore:
 * Grand Copthorne Waterfront Hotel, pages 78&ndash;85, Los Alamitos, CA,
 * USA: IEEE Computer Society Press. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/9781467359054"
 * >978-1-4673-5905-4</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1109/CIPLS.2013.6595203">10.1109/CIPLS
 * .2013.6595203</a>; INSPEC Accession Number:&nbsp;13752116;
 * EI:&nbsp;20134116837899. <div>link: [<a
 * href="http://www.it-weise.de/documents/files/OWDC2013SADAFTSP.pdf"
 * >1</a>]</div></div></li>
 * </ol>
 *
 * @author <ul>
 *         <li>
 *         <em><a href="mailto:oyjmical@mail.ustc.edu.cn">Jin Ouyang</a></em>
 *         [&#x6B27;&#x9633;&#x664B;]</li>
 *         <li><em><a href="http://www.it-weise.de/">Thomas Weise</a></em>,
 *         Email:&nbsp;<a
 *         href="mailto:tweise@ustc.edu.cn">tweise@ustc.edu.cn</a> (role:
 *         adaption to benchmarking framework)</li>
 *         </ul>
 */
public final class DevUpdatingGPM extends GPM<Function> {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the algorithm @serial serializable field */
  DevUpdatingEA m_cfg;

  /** the update operators @serial serializable field */
  private PermutationUpdateOperator[] m_ops;

  /** when were the operations used last? */
  private transient long[] m_lastUsed;

  /** the deltas */
  private transient int[] m_deltas;

  /** the memory */
  private transient double[] m_mem;

  /** the number of steps to perform */
  private transient long m_steps;

  /** the baseline */
  private transient int[] m_base;
  /** the current solution */
  private transient int[] m_cur;

  /** the indices */
  private transient int[] m_ab;

  /** the base quality */
  private transient long m_baseQuality;

  /**
   * the developmentally updating gpm
   *
   * @param algorithm
   *          the algorithm
   */
  DevUpdatingGPM(final DevUpdatingEA algorithm) {
    super("developmental_update_gpm"); //$NON-NLS-1$

    this.m_cfg = algorithm;
    this.doInit();
  }

  /** initialize */
  private void doInit() {
    this.m_ab = new int[2];
    this.m_mem = new double[DevUpdatingEA.VAR_COUNT];
    this.m_lastUsed = null;
    this.m_deltas = null;
    this.m_base = null;
    this.m_cur = null;
  }

  /** {@inheritDoc} */
  @Override
  public void beginRun(final ObjectiveFunction f) {
    final int n;

    super.beforeGeneration(f);

    n = f.n();
    this.m_steps = Math.max(1l, this.m_cfg.m_devSteps.compute(n));
    this.m_ops = this.m_cfg.m_ops;

    if ((this.m_lastUsed == null)
        || (this.m_lastUsed.length != this.m_ops.length)) {
      this.m_lastUsed = new long[this.m_ops.length];
      this.m_deltas = new int[this.m_ops.length];
    }
    if ((this.m_base == null) || (this.m_base.length != n)) {
      this.m_base = new int[n];
      this.m_cur = new int[n];
    }

    if (f.getCurrentLogPoint().getConsumedFEs() <= 0l) {
      PermutationCreateCanonical.makeCanonical(this.m_base);
      // PermutationCreateUniform.randomize(this.m_base, f.getRandom());
      f.getRandom().shuffle(this.m_base, 0, n);
      f.evaluate(this.m_base);
    }
  }

  /** {@inheritDoc} */
  @Override
  public void beforeGeneration(final ObjectiveFunction f) {
    super.beforeGeneration(f);

    f.getCopyOfBest(this.m_base);
    this.m_baseQuality = f.getCurrentLogPoint().getBestF();
  }

  /** {@inheritDoc} */
  @Override
  public void gpm(final ObjectiveFunction f, final Individual<Function> p) {

    final int[] deltas, cur, ab;
    final long maxSteps;
    final double[] ctx;
    final long[] last;
    final int n;
    final IndexIterator it;
    final PermutationUpdateOperator[] ops;
    final Function g;
    final Randomizer r;

    int bestDeltaIdx, bestDelta, appliedOp, bestCompIdx;
    double scale, comp, bestComp;
    long done, move, bestDeltaRes, bestRes, curRes;
    int i, delta, a, b, memIdx, memIdx2;

    r = f.getRandom();

    last = this.m_lastUsed; // the array storing when the operations were
    Arrays.fill(last, 0l);// used the last time

    deltas = this.m_deltas;// the array to hold the deltas

    ctx = this.m_mem; // the memory for gp

    cur = this.m_cur; // the current candidate solution
    n = cur.length; // the problem dimension
    System.arraycopy(this.m_base, 0, cur, 0, n); // begin: set to baseline
    // solution

    curRes = bestRes = this.m_baseQuality;// the best/current result
    scale = (1d / bestRes);// the value to scale deltas with

    maxSteps = this.m_steps;// the maximum number of steps

    it = this.m_cfg.m_it;// the index generator
    it.start(n, r);
    ab = this.m_ab;

    ops = this.m_ops;// the permutation updating operators

    g = p.solution; // the genotype to be mapped

    done = 0l; // no move done so far

    mainLoop: for (move = 1; move <= maxSteps; move++) {// perform the
      // moves
      it.next(ab, n, r); // get the next index pair
      a = ab[0];
      b = ab[1];

      bestDelta = Integer.MAX_VALUE;// initialize the delta values
      bestDeltaIdx = 0;

      // compute all deltas (changes in length) for the available
      // operators
      // and get the operator with the best performance
      for (i = ops.length; (--i) >= 0;) {
        deltas[i] = delta = ops[i].delta(cur, f, a, b);
        if ((delta != PermutationUpdateOperator.NO_EFFECT)
            && (delta <= bestDelta)) {
          // only consider operations that can have an effect
          bestDeltaIdx = i;
          bestDelta = delta;
        }
      }

      if ((bestDelta == PermutationUpdateOperator.NO_EFFECT) || //
          (bestDelta >= Integer.MAX_VALUE)) {
        // not a single operation would have an effect: skip to next
        // iteration
        continue mainLoop;
      }

      // and compute what the outcome of applied the best operation would
      // be
      bestDeltaRes = (curRes + bestDelta);

      appliedOp = -1;
      if (bestDeltaRes < bestRes) {
        // oh, the result would be better than the current best solution
        // ok, then "realize", i.e., apply the operator
        appliedOp = bestDeltaIdx;
        ops[appliedOp].update(cur, a, b);
        bestRes = bestDeltaRes;

        f.registerFEs(ops.length, cur, bestRes);// register
      } else {
        f.registerFEs(ops.length, null, Long.MAX_VALUE);// register, but
        // ignore
      }

      if (f.shouldTerminate()) {
        break mainLoop;// ok, we should finish here
      }

      // now it is time to see what the genotype tells us
      bestComp = Double.POSITIVE_INFINITY;
      bestCompIdx = 0;

      // init: the values which are the same
      memIdx = 0;
      ctx[memIdx++] = ((move - 0.5d) / maxSteps); // the inverse move
      // index

      // ok, for each operator, do:
      checker: for (i = deltas.length; (--i) >= 0;) {

        delta = deltas[i];
        if (delta == PermutationUpdateOperator.NO_EFFECT) {
          // if the application of this operation would have no
          // effect, skip it
          continue checker;
        }

        memIdx2 = memIdx;
        ctx[memIdx2++] = (delta * scale); // set the scaled delta
        ctx[memIdx2++] = ((last[i] + 0.5d) / move); // set the last move
        // usage

        // compute output of genotype/function
        comp = g.compute(ctx);

        if (comp <= bestComp) {// remember best (smallest) result
          bestCompIdx = i;
          bestComp = comp;
        }
      }

      // only if smallest output is less than zero we will actually
      // realize
      if (bestComp >= 0d) {
        bestCompIdx = -1; // otherwise: do nothing
      }

      // is what we want to do different from what we did?
      if (bestCompIdx != appliedOp) {

        if (appliedOp >= 0) {// possibly undo first
          ops[appliedOp].revertUpdate(cur, a, b);
        }

        if (bestCompIdx >= 0) {// then possibly perform
          ops[bestCompIdx].update(cur, a, b);
        }
      }

      // ok, we decided to do a move by using the operator "bestCompIdx"
      if (bestCompIdx >= 0) {
        curRes += deltas[bestCompIdx];
        scale = (1d / curRes);
        last[bestCompIdx] = move;
        done++;
      }

    }

    p.tourLength = curRes;// best tour length encountered

    // set fitness: get large improvement + do many moves
    p.f = ((((double) curRes) / this.m_baseQuality) + (1d / ((0.5d + done))));
  }

  /** {@inheritDoc} */
  @Override
  public DevUpdatingGPM clone() {
    DevUpdatingGPM res;

    res = ((DevUpdatingGPM) (super.clone()));
    res.doInit();

    return res;
  }
}
