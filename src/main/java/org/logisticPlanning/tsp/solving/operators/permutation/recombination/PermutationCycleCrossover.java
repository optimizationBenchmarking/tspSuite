package org.logisticPlanning.tsp.solving.operators.permutation.recombination;

import java.util.Arrays;

import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;
import org.logisticPlanning.tsp.solving.Individual;
import org.logisticPlanning.tsp.solving.operators.BinaryOperator;
import org.logisticPlanning.tsp.solving.operators.permutation.creation.PermutationCreateCanonical;
import org.logisticPlanning.utils.math.random.Randomizer;

/**
 * <p>
 * The Cycle Crossover operator&nbsp;[<a href="#cite_OSH1987ASOPCOOTTSP"
 * style="font-weight:bold">1</a>, <a href="#cite_SMMWW1991ACOGSO"
 * style="font-weight:bold">2</a>, <a href="#cite_LKMUB1999GAFTTSPARORAO"
 * style="font-weight:bold">3</a>, <a href="#cite_B2003UAOSOPDWAOMATTSP"
 * style="font-weight:bold">4</a>] for permutations.
 * </p>
 * <h2>References</h2>
 * <ol>
 * <li><div><span id="cite_OSH1987ASOPCOOTTSP" />I. M. Oliver, D. J. Smith,
 * and&nbsp;<a href="https://en.wikipedia.org/wiki/John_Henry_Holland">John
 * Henry Holland</a>: <span style="font-weight:bold">&ldquo;A Study of
 * Permutation Crossover Operators on the Traveling Salesman
 * Problem,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the Second
 * International Conference on Genetic Algorithms and their Applications
 * (ICGA'87)</span>, July&nbsp;28&ndash;31, 1987, Cambridge, MA, USA:
 * Massachusetts Institute of Technology (MIT), pages 224&ndash;230, <a
 * href="http://www.biostat.pitt.edu/directory/bios/grefenstette.asp">John
 * J. Grefenstette</a>, editor, Mahwah, NJ, USA: Lawrence Erlbaum
 * Associates, Inc. (LEA). ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0805801588">0-8058-0158-8</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780805801583">978-0-8058
 * -0158-3</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/17252562">17252562</a>; Google Book
 * ID:&nbsp;<a
 * href="http://books.google.com/books?id=bvlQAAAAMAAJ">bvlQAAAAMAAJ
 * </a></div></li>
 * <li><div><span id="cite_SMMWW1991ACOGSO" />Timothy Starkweather, S.
 * McDaniel, Keith E. Mathias, <a
 * href="http://www.cs.colostate.edu/~whitley/">L. Darrell Whitley</a>,
 * and&nbsp;C. Whitley: <span style="font-weight:bold">&ldquo;A Comparison
 * of Genetic Sequencing Operators,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the Fourth
 * International Conference on Genetic Algorithms (ICGA'91)</span>,
 * July&nbsp;13&ndash;16, 1991, San Diego, CA, USA: University of
 * California (UCSD), pages 69&ndash;76, Richard K. Belew and&nbsp;Lashon
 * Bernard Booker, editors, San Francisco, CA, USA: Morgan Kaufmann
 * Publishers Inc.. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/1558602089">1-55860-208-9</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9781558602083">978-1-55860
 * -208-3</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/24132977">24132977</a>; Google Book
 * ID:&nbsp;<a
 * href="http://books.google.com/books?id=h9nYpwAACAAJ">h9nYpwAACAAJ</a>
 * and&nbsp;<a
 * href="http://books.google.com/books?id=YvBQAAAAMAAJ">YvBQAAAAMAAJ</a>.
 * <div>CiteSeer<sup>x</sup><sub
 * style="font-style:italic">&#946;</sub>:&nbsp;<a href
 * ="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.18.4329"
 * >10.1.1.18 .4329</a></div></div></li>
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
 * <li><div><span id="cite_B2003UAOSOPDWAOMATTSP" /><a
 * href="http://people.binf.ku.dk/wb/">Wouter Boomsma</a>: <span
 * style="font-weight:bold">&ldquo;Using Adaptive Operator Scheduling on
 * Problem Domains with an Operator Manifold: Applications to the
 * Travelling Salesman Problem,&rdquo;</span> 2:1274&ndash;1279,
 * December&nbsp;8&ndash;13, 2003; edited by <a
 * href="http://seit.unsw.adfa.edu.au/staff/sites/rsarker/">Ruhul Amin
 * Sarker</a>, <a
 * href="http://ai.cs.wayne.edu/ai/member%20webs/Reynolds/">Robert G.
 * Reynolds</a>, <a
 * href="http://seit.unsw.adfa.edu.au/staff/sites/abbass/">Hussein Aly
 * Abbass Amein</a>, <a href="http://vlab.ee.nus.edu.sg/~kctan/">Kay Chen
 * Tan</a>, <a href="http://sc.snu.ac.kr/~rim/index.html">Robert Ian
 * McKay</a>, Daryl Leslie Essam, and&nbsp;Tom Gedeon; published by
 * Piscataway, NJ, USA: IEEE Computer Society. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0780378040">0-7803-7804-0</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780780378049">978-0-7803
 * -7804-9</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1109/CEC.2003.1299815">10.1109/CEC.2003
 * .1299815</a>; Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=CyBpAAAACAAJ">CyBpAAAACAAJ</a>;
 * INSPEC Accession Number:&nbsp;8088168. <div>link: [<a
 * href="http://people.binf.ku.dk/wb/TSPpaper.pdf">1</a>]</div></div></li>
 * </ol>
 */
public final class PermutationCycleCrossover extends BinaryOperator<int[]> {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the temporary array */
  private transient boolean[] m_tempA;

  /** the temp b */
  private transient int[] m_tempB;

  /** the temp d */
  private transient int[] m_tempD;

  /** instantiate the class */
  public PermutationCycleCrossover() {
    super("PermutationCycleCrossover"); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  public final void recombine(final Individual<int[]> dest,
      final ObjectiveFunction f, final Individual<int[]> parent1,
      final Individual<int[]> parent2) {
    int j, rem, p1idx;
    int[] res;
    final int[] parentPerm1, parentPerm2;
    boolean[] temp;
    int[] idxs, p1Idxs;
    int v;
    final Randomizer rand;
    final int n;

    n = f.n();
    dest.clearEvaluation();

    res = dest.solution;
    if ((res == null) || (res.length != n)) {
      dest.solution = res = new int[n];
    }
    dest.producer = this;
    dest.tourLength = 0l;

    temp = this.m_tempA;
    p1Idxs = this.m_tempB;
    idxs = this.m_tempD;

    Arrays.fill(temp, 0, n, true);
    System.arraycopy(idxs, 0, p1Idxs, 0, n);

    rand = f.getRandom();

    parentPerm1 = parent1.solution;
    parentPerm2 = parent2.solution;

    p1idx = j = rand.nextInt(n);
    v = (parentPerm1[p1idx]);
    rem = n;
    outer: for (;;) {
      if (temp[p1idx]) {
        res[p1idx] = v;
        temp[p1idx] = false;
        v = parentPerm2[p1idx];
      } else {
        break;
      }
      p1Idxs[j] = p1Idxs[--rem];

      for (j = rem; (--j) >= 0;) {
        p1idx = p1Idxs[j];
        if (parentPerm1[p1idx] == v) {
          continue outer;
        }
      }
      break outer;
    }

    for (; (--rem) >= 0;) {
      p1idx = p1Idxs[rem];
      if (temp[p1idx]) {
        res[p1idx] = parentPerm2[p1idx];
      }
    }

    dest.tourLength = f.evaluate(dest.solution);
  }

  /** {@inheritDoc} */
  @Override
  public final PermutationCycleCrossover clone() {
    PermutationCycleCrossover res;

    res = ((PermutationCycleCrossover) (super.clone()));
    res.m_tempA = null;
    res.m_tempB = null;
    res.m_tempD = null;

    return res;
  }

  /** {@inheritDoc} */
  @Override
  public void beginRun(final ObjectiveFunction f) {
    final int n;

    super.beginRun(f);

    n = f.n();
    this.m_tempA = new boolean[n];
    this.m_tempB = new int[n];
    this.m_tempD = new int[n];
    PermutationCreateCanonical.makeCanonicalZero(this.m_tempD);

    // this.m_tempD = idxs = new int[n];
    // for (j = n; (--j) >= 0;) {
    // idxs[j] = j;
    // }
  }

  /** {@inheritDoc} */
  @Override
  public void endRun(final ObjectiveFunction f) {
    this.m_tempA = null;
    this.m_tempB = null;
    this.m_tempD = null;
    super.endRun(f);
  }
}
