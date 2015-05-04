package org.logisticPlanning.tsp.solving.operators.permutation.recombination;

import java.util.Arrays;

import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;
import org.logisticPlanning.tsp.solving.Individual;
import org.logisticPlanning.tsp.solving.operators.BinaryOperator;
import org.logisticPlanning.utils.math.random.Randomizer;

/**
 * <h2>Heuristic Crossover</h2>
 * <p>
 * From&nbsp;[<a href="#cite_LKMUB1999GAFTTSPARORAO"
 * style="font-weight:bold">1</a>]: &quot;Grefenstette (1987b)&nbsp;[<a
 * href="#cite_G1987IPSKIGA" style="font-weight:bold">2</a>] developed a
 * class of heuristic crossover operators which emphasize edges. These
 * operators create an offspring in the following way:
 * </p>
 * <ol>
 * <li>They first select at random a city to be the current city of the
 * offspring.</li>
 * <li>Second, they consider the four (undirected) edges incident to the
 * current city. Over these edges a probability distribution is defined
 * based on their cost. The probability associated with an edge incident to
 * a previously visited city is equal to zero.</li>
 * <li>An edge is selected on this distribution. (If none of the parental
 * edges leads to an unvisited city a random edge is selected.)</li>
 * </ol>
 * <p>
 * The steps 3 and 4 are repeated until a complete tour has been
 * constructed.&quot;
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
 * <li><div><span id="cite_G1987IPSKIGA" /><a
 * href="http://www.biostat.pitt.edu/directory/bios/grefenstette.asp">John
 * J. Grefenstette</a>: <span style="font-weight:bold">&ldquo;Incorporating
 * Problem Specific Knowledge into Genetic Algorithms,&rdquo;</span> in
 * <span style="font-style:italic;font-family:cursive;">Genetic Algorithms
 * and Simulated Annealing</span>, pages 42&ndash;60, Lawrence Davis,
 * editor, Research Notes in Artificial Intelligence, London, UK: Pitman
 * and&nbsp;San Francisco, CA, USA: Morgan Kaufmann Publishers Inc..
 * ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0934613443">0934613443</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780934613446">978-0934613446</a>;
 * LCCN:&nbsp;<a href="http://lccn.loc.gov/87021357">87021357</a>;
 * OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/16355405">16355405</a>; Google Book
 * ID:&nbsp;<a
 * href="http://books.google.com/books?id=edfSSAAACAAJ">edfSSAAACAAJ
 * </a></div></li>
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

public final class PermutationHeuristicCrossover extends
BinaryOperator<int[]> {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * in case for using number too small. <br/>
   * TODO: Supervisor note: I do not think that this constant changes the
   * behavior of the algorithm in any way. Since you multiple
   * random.nextDouble() with the sum, any multiplier applied to the sum's
   * component should have no impact.
   */
  private static final double BIG_MULTIPLE_NUMBER = 10000.0;

  /**
   * m_set[i] = true means city i is already in offspring, which needs to
   * be ignored. m_set[i] = false means the opposite.
   */
  private boolean[] m_set;

  /**
   * m_seq1[i] = j means for parent1, city i is in position j it is same
   * with m_seq2, m_seq2[i] = j means for parent2, city i is in position j
   */
  private int[] m_seq1;
  /** @see #m_seq1 */
  private int[] m_seq2;

  /** create */
  public PermutationHeuristicCrossover() {
    super("PermutationHeuristicCrossover"); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  public final void recombine(final Individual<int[]> dest,
      final ObjectiveFunction f, final Individual<int[]> parent1,
      final Individual<int[]> parent2) {

    int[] res; /* contain offspring path */
    int[] parentPerm1, parentPerm2; /* contain parent path */
    int city, last; /* current city which is chosen from parent */
    int i = 0, idx = 0; /* loop index */
    final int n; /* total city number */
    Randomizer rand;

    /* initial m_set */
    boolean[] set;
    set = this.m_set;
    Arrays.fill(set, false);

    /* initial m_seq1, m_seq2 */
    int[] seq1;
    seq1 = this.m_seq1;
    Arrays.fill(seq1, 0);
    int[] seq2;
    seq2 = this.m_seq2;
    Arrays.fill(seq2, 0);

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

    /** record the position of each city in parent1 and parent2 */
    idx = 0;
    for (final int parent1i : parentPerm1) {
      seq1[parent1i] = idx;
      idx++;
    }
    idx = 0;
    for (final int parent2i : parentPerm2) {
      seq2[parent2i] = idx;
      idx++;
    }

    /** generate a random city for start */
    rand = f.getRandom();
    city = rand.nextInt(n) + 1;
    set[city] = true;
    dest.tourLength = 0;
    res[0] = city;

    /** generate offspring for each position */
    for (i = 1; i < n; i++) {
      last = city;
      city = this.findNextCity(city, parentPerm1, parentPerm2, seq1, seq2,
          f, set);
      dest.tourLength += f.distance(last, city);
      set[city] = true;
      res[i] = city;
    }

    dest.tourLength += f.distance(city, res[0]);
    f.registerFE(res, dest.tourLength);
  }

  /**
   * findNextCity: use probability model to generate offspring if the
   * current city is i, for nearby city j, the chosen probability is P(i,j)
   * = ( 1.0 / distance(i,j) ) /( sum of 1.0/distance(*,j)) . (In the code,
   * I replace 1.0 to BIG_MULTIPLE_NUMBER in case it is too small) To
   * implement this, we use nextDouble() to generate a random r(0 =< r <
   * 1). If r is between P(*<i,j) and P(i,j), city i-1 is chosen.
   *
   * @param incity
   *          TODO: describe what is this
   * @param parentPerm1
   *          TODO: describe what is this
   * @param parentPerm2
   *          TODO: describe what is this
   * @param seq1
   *          TODO: describe what is this
   * @param seq2
   *          TODO: describe what is this
   * @param f
   *          TODO: describe what is this
   * @param set
   *          TODO: describe what is this
   * @return TODO: describe what is this
   **/
  private int findNextCity(final int incity, final int[] parentPerm1,
      final int[] parentPerm2, final int[] seq1, final int[] seq2,
      final ObjectiveFunction f, final boolean[] set) {

    double totalDistance = 0.0, dis = 0.0;
    double iParent1LeftDistance = 0.0;
    double iParent1RightDistance = 0.0;
    double iParent2LeftDistance = 0.0;
    double iParent2RightDistance = 0.0;
    int iParent1Left = 0;
    int iParent1Right = 0;
    int iParent2Left = 0;
    int iParent2Right = 0;
    int idx1, idx2;
    final int n = f.n();
    int city = incity;

    Randomizer rand;
    rand = f.getRandom();

    /** idx1 is the position in parent 1 */
    idx1 = seq1[city];
    /** check for boundary */
    if (idx1 > 0) {
      if (set[parentPerm1[idx1 - 1]] == false) {
        iParent1Left = parentPerm1[idx1 - 1];
        /** */
        iParent1LeftDistance = PermutationHeuristicCrossover.BIG_MULTIPLE_NUMBER
            / f.distance(city, iParent1Left);
        totalDistance = iParent1LeftDistance;
      }
    }
    if (idx1 < (n - 1)) {
      if (set[parentPerm1[idx1 + 1]] == false) {
        iParent1Right = parentPerm1[idx1 + 1];
        iParent1RightDistance = totalDistance
            + (PermutationHeuristicCrossover.BIG_MULTIPLE_NUMBER / f
                .distance(city, iParent1Right));
        totalDistance = iParent1RightDistance;
      }
    }

    /** idx2 is the position in parent2 */
    idx2 = seq2[city];
    if (idx2 > 0) {
      if (set[parentPerm2[idx2 - 1]] == false) {
        iParent2Left = parentPerm2[idx2 - 1];
        iParent2LeftDistance = totalDistance
            + (PermutationHeuristicCrossover.BIG_MULTIPLE_NUMBER / f
                .distance(city, iParent2Left));
        totalDistance = iParent2LeftDistance;
      }
    }
    if (idx2 < (n - 1)) {
      if (set[parentPerm2[idx2 + 1]] == false) {
        iParent2Right = parentPerm2[idx2 + 1];
        iParent2RightDistance = totalDistance
            + (PermutationHeuristicCrossover.BIG_MULTIPLE_NUMBER / f
                .distance(city, iParent2Right));
        totalDistance = iParent2RightDistance;
      }
    }

    dis = rand.nextDouble() * totalDistance;

    if ((dis < iParent1LeftDistance) && (iParent1Left != 0)) {
      return iParent1Left;
    }
    if ((dis < iParent1RightDistance) && (iParent1Right != 0)) {
      return iParent1Right;
    }
    if ((dis < iParent2LeftDistance) && (iParent2Left != 0)) {
      return iParent2Left;
    }
    if ((dis < iParent2RightDistance) && (iParent2Right != 0)) {
      return iParent2Right;
    }
    /*
     * in case no available city around chosen city, randomly generate a
     * new city which is not in offspring
     */

    do {
      city = rand.nextInt(n) + 1;
    } while (set[city] == true);

    return city;

  }

  /** {@inheritDoc} */
  @Override
  public void beginRun(final ObjectiveFunction f) {
    super.beginRun(f);
    this.m_set = new boolean[f.n() + 1];
    this.m_seq1 = new int[f.n() + 1];
    this.m_seq2 = new int[f.n() + 1];
  }

  /** {@inheritDoc} */
  @Override
  public void endRun(final ObjectiveFunction f) {
    this.m_set = null;
    this.m_seq1 = null;
    this.m_seq2 = null;
    super.endRun(f);
  }

  /** {@inheritDoc} */
  @Override
  public PermutationHeuristicCrossover clone() {
    PermutationHeuristicCrossover r;

    r = ((PermutationHeuristicCrossover) (super.clone()));
    r.m_set = null;
    r.m_seq1 = null;
    r.m_seq2 = null;
    return r;
  }

}
