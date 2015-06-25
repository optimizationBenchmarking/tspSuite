package org.logisticPlanning.tsp.solving.operators.permutation.recombination;

import java.util.Arrays;

import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;
import org.logisticPlanning.tsp.solving.Individual;
import org.logisticPlanning.tsp.solving.operators.BinaryOperator;
import org.logisticPlanning.utils.math.random.Randomizer;

/**
 * <h2>Order Crossover (OX1)</h2>
 * <p>
 * From&nbsp;[<a href="#cite_LKMUB1999GAFTTSPARORAO"
 * style="font-weight:bold">1</a>]: &quot;The order crossover operator was
 * proposed by Davis (1985)&nbsp;[<a href="#cite_D1985AAATED"
 * style="font-weight:bold">2</a>]. The OX1 exploits a property of path
 * representation, the order of the city(not their positions) are
 * important. It constructs an offspring by choosing a subtour of one
 * parent {@code firstCut,changeNum} and preserving the relative order of
 * cities of other parent.(starting from firstCut+changeNum)&quot;
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
 * <li><div><span id="cite_D1985AAATED" />Lawrence Davis: <span
 * style="font-weight:bold">&ldquo;Applying Adaptive Algorithms to
 * Epistatic Domains,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the 9th
 * International Joint Conference on Artificial Intelligence
 * (IJCIA'85)</span>, 1, August&nbsp;1985, Los Angeles, CA, USA, pages
 * 162&ndash;164, Aravind K. Joshi, editor, San Francisco, CA, USA: Morgan
 * Kaufmann Publishers Inc.. <div>link: [<a
 * href="http://ijcai.org/Past%20Proceedings/IJCAI-85-VOL1/PDF/029.pdf"
 * >1</a>]</div></div></li>
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
public final class PermutationOrderCrossover extends BinaryOperator<int[]> {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * m_set[i] = true means city i is already in offspring, which needs to
   * be ignored. m_set[i] = false means the opposite.
   */
  private boolean[] m_set;

  /** create */
  public PermutationOrderCrossover() {
    super("PermutationOrderCrossover"); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  public final void recombine(final Individual<int[]> dest,
      final ObjectiveFunction f, final Individual<int[]> parent1,
      final Individual<int[]> parent2) {

    int[] res;
    int[] parentPerm1, parentPerm2; /* contain parent path */
    final Randomizer rand;
    int i, idx; /* loop index */
    final int n; /* total city number */
    int firstCut = 0, changeNum = 0;

    /* m_set */
    boolean[] set;
    set = this.m_set;
    Arrays.fill(set, false);

    /* clear dest */
    n = f.n();
    dest.clearEvaluation();
    res = dest.solution;
    if ((res == null) || (res.length != n)) {
      dest.solution = res = new int[n];
    }
    dest.producer = this;

    /* generate two cuts randomly */
    rand = f.getRandom();
    changeNum = rand.nextInt(n);
    firstCut = rand.nextInt(n - changeNum);

    parentPerm1 = parent1.solution;
    parentPerm2 = parent2.solution;

    /*
     * for the cities inside cut, put parent2 directly into res as the same
     * position in parent2, record the number in parent1 at the same time.
     */
    for (i = firstCut; i < (firstCut + changeNum); i++) {
      res[i] = parentPerm1[i];
      set[res[i]] = true;
    }

    /*
     * for cities outside the cut in parent1, starting from the second cut
     * point of one parent, the rest of the cities are copied in the order
     * in which they appear in the other parent, also starting from the
     * second cut point and omitting the cities that are already present.
     */
    idx = firstCut + changeNum;
    for (i = firstCut + changeNum; i < n; i++) {
      /* if it already exists in offspring, move to next */
      while (set[parentPerm2[idx]] == true) {
        idx = idx + 1;
        /* move to the beginning */
        if (idx == n) {
          idx = 0;
        }
      }
      res[i] = parentPerm2[idx];
      idx = idx + 1;
      if (idx == n) {
        idx = 0;
      }
    }

    /* fullfil the rest in res */
    for (i = 0; i < firstCut; i++) {
      while (set[parentPerm2[idx]] == true) {
        idx = idx + 1;
        if (idx == n) {
          idx = 0;
        }
      }
      res[i] = parentPerm2[idx];
      idx = idx + 1;
      if (idx == n) {
        idx = 0;
      }
    }

    dest.tourLength = f.evaluate(res);
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
  public PermutationOrderCrossover clone() {
    PermutationOrderCrossover r;

    r = ((PermutationOrderCrossover) (super.clone()));
    r.m_set = null;
    return r;
  }

}
