package org.logisticPlanning.tsp.solving.operators.permutation.recombination;

import java.util.Arrays;

import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;
import org.logisticPlanning.tsp.solving.Individual;
import org.logisticPlanning.tsp.solving.operators.BinaryOperator;
import org.logisticPlanning.utils.math.random.Randomizer;

/**
 * <h2>Maximal Preservative Crossover</h2>
 * <p>
 * From&nbsp;[<a href="#cite_LKMUB1999GAFTTSPARORAO"
 * style="font-weight:bold">1</a>]: &quot;The maximal preservative operator
 * was introduced by M&uuml;hlenbein et al. (1988)&nbsp;[<a
 * href="#cite_MGSK1988EAICO" style="font-weight:bold">2</a>]. It first
 * selects a random substring of the first parent whose length is greater
 * than or equal to {@value #MIN_CROSSOVER_LENGTH_LARGE_SCALE} (except for
 * very small problem instances), and smaller than or equal to the problem
 * size divided by 2.&quot;
 * </p>
 * <p>
 * Here, a small problem instance is considered one where the
 * {@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction#n()
 * scale} is less than {@value #MAXIMUM_SCALE_OF_SMALL_SCALE_INSTANCE},
 * which you can feel free to change the number as you need. In these
 * cases,the minimum length of the substring is
 * {@value #MIN_CROSSOVER_LENGTH_SMALL_SCALE} .
 * </p>
 * <h2>References</h2>
 * <ol>
 * <li><div><span id="cite_LKMUB1999GAFTTSPARORAO" /><a href=
 * "http://cig.fi.upm.es/index.php?option=com_content&amp;view=article&amp;id=78&amp;Itemid=111"
 * >Pedro Larra&#241;aga</a>, <a href=
 * "http://www.tilburguniversity.edu/nl/webwijs/show/&amp;uid=c.m.h.kuijpers?uid=c.m.h.kuijpers"
 * >Cindy M. H. Kuijpers</a>, Roberto H. Murga, <a
 * href="http://www.sc.ehu.es/ccwbayes/members/inaki.htm">I&#241;aki
 * Inza</a>, and&nbsp;Sejla Dizdarevic: <span
 * style="font-weight:bold">&ldquo;Genetic Algorithms for the Travelling
 * Salesman Problem: A Review of Representations and
 * Operators,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Journal of Artificial
 * Intelligence Research (JAIR)</span> 13(2):129&ndash;170,
 * April&nbsp;1999; published by El Segundo, CA, USA: AI Access Foundation,
 * Inc. and&nbsp;Menlo Park, CA, USA: AAAI Press. doi:&nbsp;<a
 * href="http://dx.doi.org/10.1023/A:1006529012972"
 * >10.1023/A:1006529012972</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/110769757">11076-9757</a>.
 * <div>link: [<a href=
 * "http://www.dca.fee.unicamp.br/~gomide/courses/EA072/artigos/Genetic_Algorithm_TSPR_eview_Larranaga_1999.pdf"
 * >1</a>]; CiteSeer<sup>x</sup><sub
 * style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.35.8882"
 * >10.1.1.35.8882</a></div></div></li>
 * <li><div><span id="cite_MGSK1988EAICO" /><a
 * href="http://www.muehlenbein.org/">Heinz M&#252;hlenbein</a>, Martina
 * Gorges-Schleuter, and&nbsp;Ottmar Kr&#228;mer: <span
 * style="font-weight:bold">&ldquo;Evolution Algorithms in Combinatorial
 * Optimization,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Parallel Computing
 * &#8210; Systems &amp; Applications</span> 7(1):65&ndash;85,
 * April&nbsp;1988; published by Essex, UK: Elsevier Science Publishers
 * B.V.. doi:&nbsp;<a href="http://dx.doi.org/10.1016/0167-8191(88)90098-1"
 * >10.1016/0167-8191(88)90098-1</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/01678191">0167-8191</a></div></li>
 * </ol>
 *
 * @author <ul>
 *         <li>
 *         <em><a href="mailto:ls503@mail.ustc.edu.cn">Shan Lin</a></em>
 *         [&#x6797;&#x6749;]</li>
 *         <li><em><a href="http://www.it-weise.de/">Thomas Weise</a></em>,
 *         Email:&nbsp;<a
 *         href="mailto:tweise@ustc.edu.cn">tweise@ustc.edu.cn</a> (role:
 *         supervisor of undergraduate project)</li>
 *         </ul>
 */
public final class PermutationMaximalPreservativeCrossover extends
    BinaryOperator<int[]> {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** for small instances, minimal crossover length */
  public static final int MIN_CROSSOVER_LENGTH_SMALL_SCALE = 2;

  /** for large instances, minimal crossover length */
  public static final int MIN_CROSSOVER_LENGTH_LARGE_SCALE = 10;

  /**
   * when city number is less than 30, we consider it as small instance，
   * you can feel free to change it as you need.
   */
  public static final int MAXIMUM_SCALE_OF_SMALL_SCALE_INSTANCE = 30;

  /** flag of which city is already in offspring */
  boolean[] m_set;

  /** create */
  public PermutationMaximalPreservativeCrossover() {
    super("PermutationMaximalPerservativeCrossover"); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  public final void recombine(final Individual<int[]> dest,
      final ObjectiveFunction f, final Individual<int[]> parent1,
      final Individual<int[]> parent2) {

    int[] res;
    int[] parentPerm1, parentPerm2; /* contain parent path */
    final Randomizer rand;
    final int n; /* total city number */
    /* maxChange = n/2, changeNum differs from different city numbers */
    int firstCut = 0, maxChange = 0, changeNum = 0, randRange = 0, last, cur;
    int i; /* loop index */

    boolean[] set;
    set = this.m_set;
    Arrays.fill(set, false);

    n = f.n();
    maxChange = n / 2;

    /* clear dest */
    dest.clearEvaluation();
    res = dest.solution;
    if ((res == null) || (res.length != n)) {
      dest.solution = res = new int[n];
    }
    dest.producer = this;

    parentPerm1 = parent1.solution;
    parentPerm2 = parent2.solution;

    rand = f.getRandom();

    /* for different n, min scales are different */
    if (n <= PermutationMaximalPreservativeCrossover.MAXIMUM_SCALE_OF_SMALL_SCALE_INSTANCE) {
      randRange = maxChange
          - PermutationMaximalPreservativeCrossover.MIN_CROSSOVER_LENGTH_SMALL_SCALE;
      changeNum = rand.nextInt(randRange)
          + PermutationMaximalPreservativeCrossover.MIN_CROSSOVER_LENGTH_SMALL_SCALE;
    } else
      if (n > PermutationMaximalPreservativeCrossover.MAXIMUM_SCALE_OF_SMALL_SCALE_INSTANCE) {
        randRange = maxChange
            - PermutationMaximalPreservativeCrossover.MIN_CROSSOVER_LENGTH_LARGE_SCALE;
        changeNum = rand.nextInt(randRange)
            + PermutationMaximalPreservativeCrossover.MIN_CROSSOVER_LENGTH_LARGE_SCALE;
      }
    firstCut = rand.nextInt(n - changeNum);

    dest.tourLength = 0l;
    res[0] = last = parentPerm1[firstCut];
    set[last] = true;
    /* put the subpath between two cut in parent1 directly to res */
    for (i = 1; i < changeNum; i++) {
      res[i] = cur = parentPerm1[i + firstCut];
      dest.tourLength += f.distance(last, cur);
      set[cur] = true;
      last = cur;
    }

    /* mark the others in parent2 the same as subpath */
    for (final int parentPerm2i : parentPerm2) {
      if (!set[parentPerm2i]) {
        res[changeNum] = cur = parentPerm2i;
        dest.tourLength += f.distance(last, cur);
        last = cur;
        if ((++changeNum) >= n) {
          break;
        }
      }
    }

    dest.tourLength += f.distance(last, res[0]);
    f.registerFE(res, dest.tourLength);
  }

  /** {@inheritDoc} */
  @Override
  public void beginRun(final ObjectiveFunction f) {
    super.beginRun(f);
    this.m_set = new boolean[f.n() + 1];
  }

  /** {@inheritDoc} */
  @Override
  public void endRun(final ObjectiveFunction f) {
    this.m_set = null;
    super.endRun(f);
  }

  /** {@inheritDoc} */
  @Override
  public PermutationMaximalPreservativeCrossover clone() {
    PermutationMaximalPreservativeCrossover r;

    r = ((PermutationMaximalPreservativeCrossover) (super.clone()));
    r.m_set = null;
    return r;
  }

}
