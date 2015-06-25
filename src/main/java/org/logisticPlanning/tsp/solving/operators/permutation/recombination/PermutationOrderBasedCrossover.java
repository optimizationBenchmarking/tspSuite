package org.logisticPlanning.tsp.solving.operators.permutation.recombination;

import java.util.Arrays;

import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;
import org.logisticPlanning.tsp.solving.Individual;
import org.logisticPlanning.tsp.solving.operators.BinaryOperator;
import org.logisticPlanning.utils.math.random.Randomizer;

/**
 * <h2>Order-Based Crossover (OX2)</h2>
 * <p>
 * From&nbsp;[<a href="#cite_LKMUB1999GAFTTSPARORAO"
 * style="font-weight:bold">1</a>]: &quot;The order based crossover
 * operator (Syswerda 1991)&nbsp;[<a href="#cite_S1991SOUGA"
 * style="font-weight:bold">2</a>] selects at random several positions in a
 * parent tour, and the order of the cities in the selected positions of
 * this parent is imposed on the other parent.&quot;
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
 * <li><div><span id="cite_S1991SOUGA" />Gilbert Syswerda: <span
 * style="font-weight:bold">&ldquo;Schedule Optimization Using Genetic
 * Algorithms,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Handbook of Genetic
 * Algorithms</span>, pages 332&ndash;349, Lawrence Davis, editor, VNR
 * Computer Library, Stamford, CT, USA: Thomson Publishing Group, Inc.
 * and&nbsp;New York, NY, USA: Van Nostrand Reinhold Co.. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0442001738">0-442-00173-8</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780442001735">978-0-442-
 * 00173-5</a>; LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/90012823">90012823</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/300431834">300431834</a>; Google
 * Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=vTG5PAAACAAJ">vTG5PAAACAAJ</a>;
 * ASIN:&nbsp;<a href="http://www.amazon.com/dp/0442001738">0442001738</a>;
 * PPN:&nbsp;<a
 * href="http://gso.gbv.de/PPNSET?PPN=01945077X">01945077X</a></div></li>
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
public final class PermutationOrderBasedCrossover extends
    BinaryOperator<int[]> {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * m_set[i] = true means city i is already in offspring, which needs to
   * be ignored. m_set[i] = false means the opposite.
   */
  private boolean[] m_set;

  /** m_seq restores the cities chosen from specific positions */
  private int[] m_seq;

  /** create */
  public PermutationOrderBasedCrossover() {
    super("PermutationOrderBasedCrossover"); //$NON-NLS-1$
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
    int tmp;
    int randomCount = 0;

    /* m_set */
    boolean[] set;
    set = this.m_set;
    Arrays.fill(set, false);

    /* m_seq */
    int[] seq;
    seq = this.m_seq;
    Arrays.fill(seq, 0);

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

    /* randomly generate locations, flag it in set */
    rand = f.getRandom();
    randomCount = rand.nextInt(n);
    for (i = 0; i < randomCount; i++) {
      tmp = rand.nextInt(n);
      set[parentPerm2[tmp]] = true;
    }

    /* scanning parent2, record the sequence of the selected cities */
    idx = 0;
    for (final int parentPerm2i : parentPerm2) {
      if (set[parentPerm2i]) {
        seq[idx] = parentPerm2i;
        idx = idx + 1;
      }
    }

    /* scanning parent1, changing the sequence of the selected cities */
    idx = 0;
    for (i = 0; i < n; i++) {
      if (set[parentPerm1[i]] == true) {
        res[i] = seq[idx];
        idx = idx + 1;
      } else {
        res[i] = parentPerm1[i];
      }
    }

    dest.tourLength = f.evaluate(res);
  }

  /** {@inheritDoc} */
  @Override
  public void beginRun(final ObjectiveFunction f) {
    super.beginRun(f);
    this.m_set = new boolean[f.n() + 1];
    this.m_seq = new int[f.n() + 1];
  }

  /** {@inheritDoc} */
  @Override
  public void endRun(final ObjectiveFunction f) {
    this.m_set = null;
    this.m_seq = null;
    super.endRun(f);
  }

  /** {@inheritDoc} */
  @Override
  public PermutationOrderBasedCrossover clone() {
    PermutationOrderBasedCrossover r;

    r = ((PermutationOrderBasedCrossover) (super.clone()));
    r.m_set = null;
    r.m_seq = null;
    return r;
  }

}
