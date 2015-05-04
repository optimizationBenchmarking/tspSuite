package org.logisticPlanning.tsp.solving.operators.permutation.recombination;

import java.util.Arrays;
import java.util.Random;

import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;
import org.logisticPlanning.tsp.solving.Individual;
import org.logisticPlanning.tsp.solving.operators.BinaryOperator;
import org.logisticPlanning.tsp.solving.utils.NodeManager;
import org.logisticPlanning.utils.math.random.Randomizer;

/**
 * <p>
 * The <a
 * href="https://en.wikipedia.org/wiki/Edge_recombination_operator">edge
 * recombination operator</a> is a crossover operator that tries to
 * preserve as many edges as possible from two parent permutations, as
 * published in&nbsp;[<a href="#cite_WSF1989SPATSTGERO"
 * style="font-weight:bold">1</a>, <a href="#cite_WSS1991TTSASSQSUGER"
 * style="font-weight:bold">2</a>, <a href="#cite_SMMWW1991ACOGSO"
 * style="font-weight:bold">3</a>, <a href="#cite_LKMUB1999GAFTTSPARORAO"
 * style="font-weight:bold">4</a>].
 * </p>
 * <h2>References</h2>
 * <ol>
 * <li><div><span id="cite_WSF1989SPATSTGERO" /><a
 * href="http://www.cs.colostate.edu/~whitley/">L. Darrell Whitley</a>,
 * Timothy Starkweather, and&nbsp;D'Ann Fuquay: <span
 * style="font-weight:bold">&ldquo;Scheduling Problems and Traveling
 * Salesman: The Genetic Edge Recombination Operator,&rdquo;</span> in
 * <span style="font-style:italic;font-family:cursive;">Proceedings of the
 * Third International Conference on Genetic Algorithms (ICGA'89)</span>,
 * June&nbsp;4&ndash;7, 1989, Fairfax, VA, USA: George Mason University
 * (GMU), pages 133&ndash;140, James David Schaffer, editor, San Francisco,
 * CA, USA: Morgan Kaufmann Publishers Inc.. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/1558600663">1-55860-066-3</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9781558600669">978-1-55860
 * -066-9</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/257885359">257885359</a>; Google
 * Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=BPBQAAAAMAAJ">BPBQAAAAMAAJ
 * </a></div></li>
 * <li><div><span id="cite_WSS1991TTSASSQSUGER" /><a
 * href="http://www.cs.colostate.edu/~whitley/">L. Darrell Whitley</a>,
 * Timothy Starkweather, and&nbsp;Daniel Shaner: <span
 * style="font-weight:bold">&ldquo;The Travelling Salesman and Sequence
 * Scheduling: Quality Solutions using Genetic Edge
 * Recombination,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Handbook of Genetic
 * Algorithms</span>, pages 350&ndash;372, Lawrence Davis, editor, VNR
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
 * </ol>
 */
public final class PermutationEdgeCrossover extends BinaryOperator<int[]> {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the edge matrix */
  private transient int[] m_edgeMatrix;

  /** the internal node manage */
  private transient NodeManager m_manager;

  /** instantiate the edge crossover */
  public PermutationEdgeCrossover() {
    super("PermutationEdgeCrossover"); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  public PermutationEdgeCrossover clone() {
    PermutationEdgeCrossover r;

    r = ((PermutationEdgeCrossover) (super.clone()));
    r.m_edgeMatrix = null;
    r.m_manager = null;
    return r;
  }

  /** {@inheritDoc} */
  @Override
  public final void recombine(final Individual<int[]> dest,
      final ObjectiveFunction f, final Individual<int[]> parent1,
      final Individual<int[]> parent2) {
    final int n;
    final int[] em, parentPerm1, parentPerm2;
    int[] res;
    int last, finished, node;
    final Randomizer r;
    final NodeManager manager;

    n = f.n();
    em = this.m_edgeMatrix;
    Arrays.fill(em, 0);

    parentPerm1 = parent1.solution;
    last = parentPerm1[n - 1];
    for (final int cur : parentPerm1) {
      PermutationEdgeCrossover.__addEdge(last, cur, em);
      PermutationEdgeCrossover.__addEdge(cur, last, em);
      last = cur;
    }

    parentPerm2 = parent2.solution;
    last = parentPerm2[n - 1];
    for (final int cur : parentPerm2) {
      PermutationEdgeCrossover.__addEdge(last, cur, em);
      PermutationEdgeCrossover.__addEdge(cur, last, em);
      last = cur;
    }

    manager = this.m_manager;
    manager.init(n);
    dest.clearEvaluation();
    res = dest.solution;
    if ((res == null) || (res.length != n)) {
      dest.solution = res = new int[n];
    }
    dest.producer = this;
    r = f.getRandom();

    finished = 0;
    last = node = (r.nextBoolean() ? parentPerm1 : parentPerm2)[0];
    // last = (node << 2);
    // Arrays.fill(em, last - 4, last, 0);

    dest.tourLength = 0l;
    for (;;) {
      if (node <= 0) {
        node = manager.deleteRandom(r);
      } else {
        manager.deleteByID(node);
      }
      res[finished++] = node;
      dest.tourLength += f.distance(last, node);
      last = node;
      if (finished >= n) {
        break;
      }
      node = PermutationEdgeCrossover.__popNode(node, em, r);
    }

    dest.tourLength += f.distance(last, res[0]);
    f.registerFE(res, dest.tourLength);
  }

  /**
   * allocate the edge matrix
   *
   * @param n1
   *          the first node
   * @param n2
   *          the second node
   * @param em
   *          the edge matrix
   */
  private static final void __addEdge(final int n1, final int n2,
      final int[] em) {
    int idx, add, read;

    add = n2;

    idx = ((n1 - 1) << 2);
    read = em[idx];
    if (read <= add) {
      if (read == add) {
        return;
      }
      em[idx] = add;
      if (read <= 0) {
        return;
      }
      add = read;
    }

    idx++;
    read = em[idx];
    if (read <= add) {
      if (read == add) {
        return;
      }
      em[idx] = add;
      if (read <= 0) {
        return;
      }
      add = read;
    }

    idx++;
    read = em[idx];
    if (read <= add) {
      if (read == add) {
        return;
      }
      em[idx] = add;
      if (read <= 0) {
        return;
      }
      add = read;
    }

    idx++;
    em[idx] = add;
  }

  /**
   * remove a node from the edge matrix
   *
   * @param n
   *          the node
   * @param em
   *          the edge matrix
   * @param r
   *          the randomizer
   * @return the next node chosen
   */
  private static final int __popNode(final int n, final int[] em,
      final Random r) {
    int localIdx, localStart, localEnd, curNode, curIdx, curEnd, last, cur, curEdges, minEdges, minCount;

    localEnd = (n << 2);
    localStart = (localEnd - 4);

    minEdges = 500;
    minCount = (-1);

    outer: for (localIdx = localEnd; (--localIdx) >= localStart;) {
      curNode = em[localIdx];

      if (curNode <= 0) {
        continue;
      }

      curIdx = (curNode << 2);
      curEnd = (curIdx - 4);
      curEdges = 4;
      last = 0;

      inner: for (; (--curIdx) >= curEnd;) {
        cur = em[curIdx];
        em[curIdx] = last;

        if (cur >= n) {
          break inner;
        }
        if (cur <= 0) {
          curEdges--;
        }
        last = cur;
      }

      if (curEdges <= minEdges) {
        if (curEdges < minEdges) {
          em[localEnd - 1] = curNode;
          minCount = 1;
          minEdges = curEdges;
          continue outer;
        }

        minCount++;
        em[localEnd - minCount] = curNode;
      }
    }

    if (minCount > 0) {
      return em[localEnd - 1 - r.nextInt(minCount)];
    }

    return (-1);
  }

  /** {@inheritDoc} */
  @Override
  public void beginRun(final ObjectiveFunction f) {
    super.beginRun(f);

    this.m_edgeMatrix = new int[f.n() << 2];
    this.m_manager = new NodeManager();
  }

  /** {@inheritDoc} */
  @Override
  public void endRun(final ObjectiveFunction f) {
    this.m_edgeMatrix = null;
    this.m_manager = null;
    super.endRun(f);
  }

  //
  // /**
  // * remove a node from the edge matrix
  // *
  // * @param n
  // * the node
  // * @param em
  // * the edge matrix
  // * @param r
  // * the randomizer
  // * @return the next node chosen
  // */
  // private static final int popNode(final int n, final int[] em, final
  // Random
  // r) {
  // int lidx, curNode, curIdx, curEnd, last, cur, curEdges, minEdges,
  // minCount;
  // int[] local;
  //
  // local = new int[4]; // this should be put on the stack if the JIT is
  // good
  // //but it turns out it is better to not allocate such an array
  // System.arraycopy(em, ((n - 1) << 2), local, 0, 4);
  //
  // minEdges = 500;
  // minCount = (-1);
  //
  // outer: for (lidx = 4; (--lidx) >= 0;) {
  // curNode = local[lidx];
  //
  // if (curNode <= 0) {
  // continue;
  // }
  //
  // curIdx = (curNode << 2);
  // curEnd = (curIdx - 4);
  // curEdges = 4;
  // last = 0;
  //
  // inner: for (; (--curIdx) >= curEnd;) {
  // cur = em[curIdx];
  // em[curIdx] = last;
  //
  // if (cur >= n) {
  // break inner;
  // }
  // if (cur <= 0) {
  // curEdges--;
  // }
  // last = cur;
  // }
  //
  // if (curEdges <= minEdges) {
  // if (curEdges < minEdges) {
  // local[3] = curNode;
  // minCount = 1;
  // minEdges = curEdges;
  // continue outer;
  // }
  //
  // minCount++;
  // local[4 - minCount] = curNode;
  // }
  // }
  //
  // if (minCount > 0) {
  // return local[3 - r.nextInt(minCount)];
  // }
  //
  // return (-1);
  // }
}
