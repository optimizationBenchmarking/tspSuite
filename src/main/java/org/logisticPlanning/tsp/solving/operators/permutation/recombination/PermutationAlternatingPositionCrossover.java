package org.logisticPlanning.tsp.solving.operators.permutation.recombination;

import java.util.Arrays;

import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;
import org.logisticPlanning.tsp.solving.Individual;
import org.logisticPlanning.tsp.solving.operators.BinaryOperator;

/**
 * <h2>Alternating Position Crossover</h2>
 * <p>
 * From&nbsp;[<a href="#cite_LKMUB1999GAFTTSPARORAO"
 * style="font-weight:bold">1</a>]: &quot;The alternating position
 * crossover operator (Larra&ntilde;aga et al. 1996a)&nbsp;[<a
 * href="#cite_LKPM1997DBNTOTMGWGA" style="font-weight:bold">2</a>] simply
 * creates an offspring by selecting alternately the next element of the
 * first parent and the next element of the second parent, omitting the
 * elements already present in the offspring.&quot;
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
 * <li><div><span id="cite_LKPM1997DBNTOTMGWGA" /><a href=
 * "http://cig.fi.upm.es/index.php?option=com_content&amp;view=article&amp;id=78&amp;Itemid=111"
 * >Pedro Larra&#241;aga</a>, <a href=
 * "http://www.tilburguniversity.edu/nl/webwijs/show/&amp;uid=c.m.h.kuijpers?uid=c.m.h.kuijpers"
 * >Cindy M. H. Kuijpers</a>, Mikel Poza, and&nbsp;Roberto H. Murga: <span
 * style="font-weight:bold">&ldquo;Decomposing Bayesian Networks:
 * Triangulation of the Moral Graph with Genetic Algorithms,&rdquo;</span>
 * in <span style="font-style:italic;font-family:cursive;">Statistics and
 * Computing</span> 7(1):19&ndash;34, March&nbsp;1, 1997; published by
 * Dordrecht, Netherlands: Springer Netherlands and&nbsp;Golden, CO, USA:
 * Samizdat Press. doi:&nbsp;<a
 * href="http://dx.doi.org/10.1023/A:1018553211613"
 * >10.1023/A:1018553211613</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/09603174">0960-3174</a>.
 * <div>CiteSeer<sup>x</sup><sub
 * style="font-style:italic">&#946;</sub>:&nbsp;<a href
 * ="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.54.1276"
 * >10.1.1.54 .1276</a></div></div></li>
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
public final class PermutationAlternatingPositionCrossover extends
    BinaryOperator<int[]> {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * m_set[i] = true means city i is already in offspring, which needs to
   * be ignored. m_set[i] = false means the opposite.
   */
  private boolean[] m_set;

  /** create */
  public PermutationAlternatingPositionCrossover() {
    super("PermutationAlternatingPositionCrossover"); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  public final void recombine(final Individual<int[]> dest,
      final ObjectiveFunction f, final Individual<int[]> parent1,
      final Individual<int[]> parent2) {

    int[] res;
    int[] parentPerm1, parentPerm2; /* contain parent path */
    int i = 0, idx = 0, cur, last; /* loop index */
    final int n; /* total city number */

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

    parentPerm1 = parent1.solution;
    parentPerm2 = parent2.solution;

    /**
     * generate offspring from each parent in turns and omit the ones
     * already exist
     */
    dest.tourLength = 0l;
    i = 0;
    res[idx] = last = parentPerm1[i];
    set[res[idx]] = true;
    idx++;

    for (i = 0; (i < n) && (idx < n); i++) {
      if (set[cur = parentPerm1[i]] == false) {
        res[idx] = cur;
        set[res[idx]] = true;
        idx++;
        dest.tourLength += f.distance(last, cur);
        last = cur;
      }
      if (set[cur = parentPerm2[i]] == false) {
        res[idx] = cur;
        set[res[idx]] = true;
        idx++;
        dest.tourLength += f.distance(last, cur);
        last = cur;
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
  public PermutationAlternatingPositionCrossover clone() {
    PermutationAlternatingPositionCrossover r;

    r = ((PermutationAlternatingPositionCrossover) (super.clone()));
    r.m_set = null;
    return r;
  }

}
