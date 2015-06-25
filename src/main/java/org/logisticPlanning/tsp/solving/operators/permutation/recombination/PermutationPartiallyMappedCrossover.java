package org.logisticPlanning.tsp.solving.operators.permutation.recombination;

import java.util.Arrays;

import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;
import org.logisticPlanning.tsp.solving.Individual;
import org.logisticPlanning.tsp.solving.operators.BinaryOperator;
import org.logisticPlanning.utils.math.random.Randomizer;

/**
 * <h2>Partially-Mapped Crossover</h2>
 * <p>
 * From&nbsp;[<a href="#cite_LKMUB1999GAFTTSPARORAO"
 * style="font-weight:bold">1</a>]: &quot;The partially-mapped crossover
 * operator was suggested by Goldberg and Lingle (1985)&nbsp;[<a
 * href="#cite_GLJ1985ALATT" style="font-weight:bold">2</a>]. It passes on
 * ordering and value information from the parent tours to the offspring
 * tours. A portion of one parent’s string is mapped onto a portion of the
 * other parent’s string and the remaining information is exchanged.&quot;
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
 * <li><div><span id="cite_GLJ1985ALATT" /><a
 * href="http://www.davidegoldberg.com/de-goldberg.html">David Edward
 * Goldberg</a> and&nbsp;Robert Lingle, Jr.: <span
 * style="font-weight:bold">&ldquo;Alleles, Loci and the TSP,&rdquo;</span>
 * in <span style="font-style:italic;font-family:cursive;">Proceedings of
 * the 1st International Conference on Genetic Algorithms and their
 * Applications (ICGA'85)</span>, June&nbsp;24&ndash;26, 1985, Pittsburgh,
 * PA, USA: Carnegy Mellon University (CMU), pages 154&ndash;159, <a
 * href="http://www.biostat.pitt.edu/directory/bios/grefenstette.asp">John
 * J. Grefenstette</a>, editor, Hillsdale, NJ, USA: Lawrence Erlbaum
 * Associates. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0805804269">0-8058-0426-9</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780805804263">978-0-8058
 * -0426-3</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/19702892">19702892</a></div></li>
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
public final class PermutationPartiallyMappedCrossover extends
    BinaryOperator<int[]> {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * m_set[i] = j means city i should be changed to city j in the offspring
   * m_set[i] = 0 means city i need not be changed
   */
  private int[] m_set;

  /** create */
  public PermutationPartiallyMappedCrossover() {
    super("PermutationPartiallyMappedCrossover"); //$NON-NLS-1$
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
    int[] set;
    set = this.m_set;
    Arrays.fill(set, 0);

    /* clear dest */
    n = f.n();
    dest.clearEvaluation();
    res = dest.solution;
    if ((res == null) || (res.length != n)) {
      dest.solution = res = new int[n];
    }
    dest.producer = this;

    /* generate two cuts */
    rand = f.getRandom();
    changeNum = rand.nextInt(n);
    firstCut = rand.nextInt(n - changeNum);

    parentPerm1 = parent1.solution;
    parentPerm2 = parent2.solution;

    /*
     * for the cities inside cut, put parent2 directly into res as the same
     * position in parent2, record the number in parent1 for further
     * changes
     */
    for (i = firstCut; i < (firstCut + changeNum); i++) {
      res[i] = parentPerm2[i];
      set[res[i]] = parentPerm1[i];
    }

    /*
     * for cities outside the cut, change it to a route that is legal using
     * mapping of set[i] = j
     */
    for (i = 0; i < firstCut; i++) {
      idx = parentPerm1[i];
      while (set[idx] != 0) {
        idx = set[idx];
      }
      res[i] = idx;
    }

    /* fulfill res */
    for (i = firstCut + changeNum; i < n; i++) {
      idx = parentPerm1[i];
      while (set[idx] != 0) {
        idx = set[idx];
      }
      res[i] = idx;
    }

    dest.tourLength = f.evaluate(res);
  }

  /** {@inheritDoc} */
  @Override
  public void beginRun(final ObjectiveFunction f) {
    super.beginRun(f);
    this.m_set = new int[f.n() + 1];
  }

  /** {@inheritDoc} */
  @Override
  public void endRun(final ObjectiveFunction f) {
    this.m_set = null;
    super.endRun(f);
  }

  /** {@inheritDoc} */
  @Override
  public PermutationPartiallyMappedCrossover clone() {
    PermutationPartiallyMappedCrossover r;

    r = ((PermutationPartiallyMappedCrossover) (super.clone()));
    r.m_set = null;
    return r;
  }
}
