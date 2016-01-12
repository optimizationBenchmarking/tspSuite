package org.logisticPlanning.tsp.solving.operators.permutation.update;

import java.util.ArrayList;

import org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer;
import org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.ts._PairOfNode;
import org.logisticPlanning.tsp.solving.utils.edge.Edge;

/**
 * <p>
 * This operator rotates a sub-sequence of the solution one step to the
 * right. This operator is equivalent to the insertion mutation operator
 * defined in&nbsp;[<a href="#cite_LKMUB1999GAFTTSPARORAO"
 * style="font-weight:bold">1</a>, <a href="#cite_F1988AEATTTSP"
 * style="font-weight:bold">2</a>, <a href="#cite_M1996GADSEP"
 * style="font-weight:bold">3</a>, <a href="#cite_S1991SOUGA"
 * style="font-weight:bold">4</a>].
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
 * <li><div><span id="cite_F1988AEATTTSP" /><a
 * href="http://www.natural-selection.com/people_dfogel.html">David B.
 * Fogel</a>: <span style="font-weight:bold">&ldquo;An Evolutionary
 * Approach to the Traveling Salesman Problem,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Biological
 * Cybernetics</span> 60(2):139&ndash;144, December&nbsp;1988; published by
 * Berlin/Heidelberg: Springer-Verlag. doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/BF00202901">10.1007/BF00202901</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/03401200">0340-1200</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/14320770">1432-0770</a>. <div>link:
 * [<a
 * href="http://users.on.net/~jivlain/papers/4%20Fogel.pdf">1</a>]</div>
 * </div></li>
 * <li><div><span id="cite_M1996GADSEP" /><a
 * href="http://cs.adelaide.edu.au/~zbyszek/">Zbigniew Michalewicz</a>:
 * <span style="font-style:italic;font-family:cursive;">&ldquo;Genetic
 * Algorithms + Data Structures = Evolution Programs,&rdquo;</span> 1996,
 * Berlin, Germany: Springer-Verlag GmbH. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/3540606769">3-540-60676-9</a>, <a
 * href="https://www.worldcat.org/isbn/3540580905">3-540-58090-5</a>, <a
 * href
 * ="https://www.worldcat.org/isbn/9783540606765">978-3-540-60676-5</a>,
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783642082337">978-3-642-
 * 08233-7</a>; Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=vlhLAobsK68C">vlhLAobsK68C</a>;
 * ASIN:&nbsp;<a
 * href="http://www.amazon.com/dp/3540606769">3540606769</a></div></li>
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
 */
public final class PermutationUpdate_Rotate_Right extends
    PermutationUpdateOperator {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the globally shared instance */
  public static final PermutationUpdate_Rotate_Right INSTANCE = new PermutationUpdate_Rotate_Right();

  /** the globally shared complementary function */
  public static final ComplementUpdateOperator COMPLEMENT = new ComplementUpdateOperator(
      PermutationUpdate_Rotate_Right.INSTANCE);

  /** instantiate */
  private PermutationUpdate_Rotate_Right() {
    super("rotateRight"); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  public final int delta(final int[] perm, final DistanceComputer f,
      final int a, final int b) {
    final int l, m1;
    final int B, E, A, F, D, DF, BE, AE, AB, EF, DE;

    l = perm.length;
    m1 = (l - 1);

    if ((((b - a) + l) % l) <= 1) {// changed == to <=
      return PermutationUpdate_Swap.INSTANCE.delta(perm, f, a, b);
    }
    if (a == ((b + 1) % l)) {
      return PermutationUpdateOperator.NO_EFFECT;
    }

    A = perm[(a + m1) % l];
    B = perm[a];

    D = perm[(b + m1) % l];
    E = perm[b];
    F = perm[(b + 1) % l];

    DF = f.distance(D, F);
    BE = f.distance(E, B);
    AE = f.distance(A, E);
    AB = f.distance(A, B);
    EF = f.distance(E, F);
    DE = f.distance(D, E);

    return (DF + BE + AE) - (AB + EF + DE);//
  }

  /** {@inheritDoc} */
	@Override
	public final ArrayList<_PairOfNode> inComingEdges(final int[] perm, final int a,
			final int b) {
		final int l, m1;
	    final int B, E, A, F, D;
	    ArrayList<_PairOfNode> inComing = new ArrayList<_PairOfNode>();

	    l = perm.length;
	    m1 = (l - 1);

	    if ((((b - a) + l) % l) <= 1) {// changed == to <=
	      return PermutationUpdate_Swap.INSTANCE.inComingEdges(perm, a, b);
	    }
	    if (a == ((b + 1) % l)) {
	      return null;
	    }

	    A = perm[(a + m1) % l];
	    B = perm[a];

	    D = perm[(b + m1) % l];
	    E = perm[b];
	    F = perm[(b + 1) % l];

	    inComing.add(new _PairOfNode(D, F));
	    inComing.add(new _PairOfNode(B, E));
	    inComing.add(new _PairOfNode(A, E));
	    
		return inComing;
		
	}

	/** {@inheritDoc} */
	@Override
	public final ArrayList<_PairOfNode> outComingEdges(final int[] perm, final int a,
			final int b) {
		final int l, m1;
	    final int B, E, A, F, D;
	    ArrayList<_PairOfNode> outComing = new ArrayList<_PairOfNode>();

	    l = perm.length;
	    m1 = (l - 1);

	    if ((((b - a) + l) % l) <= 1) {// changed == to <=
	      return PermutationUpdate_Swap.INSTANCE.outComingEdges(perm, a, b);
	    }
	    if (a == ((b + 1) % l)) {
	      return null;
	    }

	    A = perm[(a + m1) % l];
	    B = perm[a];

	    D = perm[(b + m1) % l];
	    E = perm[b];
	    F = perm[(b + 1) % l];

	    outComing.add(new _PairOfNode(E, F));
	    outComing.add(new _PairOfNode(D, E));
	    outComing.add(new _PairOfNode(A, B));
	    
		return outComing;
	}
  
  /** {@inheritDoc} */
  @Override
  public final void update(final int[] perm, final int a, final int b) {
    final int l, temp, m1;
    int i, j;

    l = perm.length;
    m1 = (l - 1);
    temp = perm[b];
    for (i = b; i != a; i = j) {
      j = ((i + m1) % l);
      perm[i] = perm[j];
    }
    perm[i] = temp;
  }

  /** {@inheritDoc} */
  @Override
  public final void revertUpdate(final int[] perm, final int a, final int b) {
    final int l, temp;
    int i, j;

    l = perm.length;
    temp = perm[a];
    for (i = a; i != b; i = j) {
      j = ((i + 1) % l);
      perm[i] = perm[j];
    }
    perm[i] = temp;
  }

}
