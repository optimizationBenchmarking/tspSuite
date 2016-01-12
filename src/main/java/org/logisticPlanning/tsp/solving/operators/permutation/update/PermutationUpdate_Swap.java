package org.logisticPlanning.tsp.solving.operators.permutation.update;

import org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.ts._PairOfNode;
import java.util.ArrayList;

import org.logisticPlanning.tsp.benchmarking.dist.DistanceComputer;
import org.logisticPlanning.tsp.solving.utils.edge.Edge;

/**
 * <p>
 * This operation swaps two elements in a permutation&nbsp;[<a
 * href="#cite_LKMUB1999GAFTTSPARORAO" style="font-weight:bold">1</a>, <a
 * href="#cite_OSH1987ASOPCOOTTSP" style="font-weight:bold">2</a>, <a
 * href="#cite_M1996GADSEP" style="font-weight:bold">3</a>, <a
 * href="#cite_B1990TMTS" style="font-weight:bold">4</a>, <a
 * href="#cite_AAM1991HCOBSDEAPTAFTTSP" style="font-weight:bold">5</a>, <a
 * href="#cite_S1991SOUGA" style="font-weight:bold">6</a>]. This is maybe the
 * most common and simplest search operator that we can apply to a permutation.
 * </p>
 * <h2>References</h2>
 * <ol>
 * <li><div><span id="cite_LKMUB1999GAFTTSPARORAO" /><a href=
 * "http://cig.fi.upm.es/index.php?option=com_content&amp;view=article&amp;id=78&amp;Itemid=111"
 * >Pedro Larra&#241;aga</a>, <a href=
 * "http://www.tilburguniversity.edu/nl/webwijs/show/&amp;uid=c.m.h.kuijpers?uid=c.m.h.kuijpers"
 * >Cindy M. H. Kuijpers</a>, Roberto H. Murga, <a
 * href="http://www.sc.ehu.es/ccwbayes/members/inaki.htm">I&#241;aki Inza</a>,
 * and&nbsp;Sejla Dizdarevic: <span style="font-weight:bold">&ldquo;Genetic
 * Algorithms for the Travelling Salesman Problem: A Review of Representations
 * and Operators,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Journal of Artificial
 * Intelligence Research (JAIR)</span> 13(2):129&ndash;170, April&nbsp;1999;
 * published by El Segundo, CA, USA: AI Access Foundation, Inc. and&nbsp;Menlo
 * Park, CA, USA: AAAI Press. doi:&nbsp;<a
 * href="http://dx.doi.org/10.1023/A:1006529012972"
 * >10.1023/A:1006529012972</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/110769757">11076-9757</a>. <div>link: [<a
 * href=
 * "http://www.dca.fee.unicamp.br/~gomide/courses/EA072/artigos/Genetic_Algorithm_TSPR_eview_Larranaga_1999.pdf"
 * >1</a>]; CiteSeer<sup>x</sup><sub
 * style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.35.8882"
 * >10.1.1.35.8882</a></div></div></li>
 * <li><div><span id="cite_OSH1987ASOPCOOTTSP" />I. M. Oliver, D. J. Smith,
 * and&nbsp;<a href="https://en.wikipedia.org/wiki/John_Henry_Holland">John
 * Henry Holland</a>: <span style="font-weight:bold">&ldquo;A Study of
 * Permutation Crossover Operators on the Traveling Salesman
 * Problem,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the Second
 * International Conference on Genetic Algorithms and their Applications
 * (ICGA'87)</span>, July&nbsp;28&ndash;31, 1987, Cambridge, MA, USA:
 * Massachusetts Institute of Technology (MIT), pages 224&ndash;230, <a
 * href="http://www.biostat.pitt.edu/directory/bios/grefenstette.asp">John J.
 * Grefenstette</a>, editor, Mahwah, NJ, USA: Lawrence Erlbaum Associates, Inc.
 * (LEA). ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0805801588">0-8058-0158-8</a> and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780805801583">978-0-8058 -0158-3</a>;
 * OCLC:&nbsp;<a href="https://www.worldcat.org/oclc/17252562">17252562</a>;
 * Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=bvlQAAAAMAAJ">bvlQAAAAMAAJ </a></div></li>
 * <li><div><span id="cite_M1996GADSEP" /><a
 * href="http://cs.adelaide.edu.au/~zbyszek/">Zbigniew Michalewicz</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Genetic Algorithms +
 * Data Structures = Evolution Programs,&rdquo;</span> 1996, Berlin, Germany:
 * Springer-Verlag GmbH. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/3540606769">3-540-60676-9</a>, <a
 * href="https://www.worldcat.org/isbn/3540580905">3-540-58090-5</a>, <a href
 * ="https://www.worldcat.org/isbn/9783540606765">978-3-540-60676-5</a>,
 * and&nbsp;<a href="https://www.worldcat.org/isbn/9783642082337">978-3-642-
 * 08233-7</a>; Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=vlhLAobsK68C">vlhLAobsK68C</a>;
 * ASIN:&nbsp;<a href="http://www.amazon.com/dp/3540606769">3540606769</a></div>
 * </li>
 * <li><div><span id="cite_B1990TMTS" /><a
 * href="http://www.cs.mun.ca/~banzhaf/">Wolfgang Banzhaf</a>: <span
 * style="font-weight:bold">&ldquo;The &#8220;Molecular&#8221; Traveling
 * Salesman,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Biological Cybernetics</span>
 * 64(1):7&ndash;14, November&nbsp;1990; published by Berlin/Heidelberg:
 * Springer-Verlag. doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/BF00203625">10.1007/BF00203625</a>;
 * ISSN:&nbsp;<a href="https://www.worldcat.org/issn/03401200">0340-1200</a>
 * and&nbsp;<a href="https://www.worldcat.org/issn/14320770">1432-0770</a>.
 * <div>link: [<a
 * href="https://web.cs.mun.ca/~banzhaf/papers/MolTravelSalesman.pdf"
 * >1</a>]</div></div></li>
 * <li><div><span id="cite_AAM1991HCOBSDEAPTAFTTSP" />Balamurali Krishna Ambati,
 * Jayakrishna Ambati, and&nbsp;Mazen Moein Mokhtar: <span
 * style="font-weight:bold">&ldquo;Heuristic Combinatorial Optimization by
 * Simulated Darwinian Evolution: A Polynomial Time Algorithm for the Traveling
 * Salesman Problem,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Biological Cybernetics</span>
 * 65(1):31&ndash;35, May&nbsp;1991; published by Berlin/Heidelberg:
 * Springer-Verlag. doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/BF00197287">10.1007/BF00197287</a>;
 * ISSN:&nbsp;<a href="https://www.worldcat.org/issn/03401200">0340-1200</a>
 * and&nbsp;<a href="https://www.worldcat.org/issn/14320770">1432-0770</a></div>
 * </li>
 * <li><div><span id="cite_S1991SOUGA" />Gilbert Syswerda: <span
 * style="font-weight:bold">&ldquo;Schedule Optimization Using Genetic
 * Algorithms,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Handbook of Genetic
 * Algorithms</span>, pages 332&ndash;349, Lawrence Davis, editor, VNR Computer
 * Library, Stamford, CT, USA: Thomson Publishing Group, Inc. and&nbsp;New York,
 * NY, USA: Van Nostrand Reinhold Co.. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0442001738">0-442-00173-8</a> and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780442001735">978-0-442- 00173-5</a>;
 * LCCN:&nbsp;<a href="http://lccn.loc.gov/90012823">90012823</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/300431834">300431834</a>; Google Book
 * ID:&nbsp;<a
 * href="http://books.google.com/books?id=vTG5PAAACAAJ">vTG5PAAACAAJ</a>;
 * ASIN:&nbsp;<a href="http://www.amazon.com/dp/0442001738">0442001738</a>;
 * PPN:&nbsp;<a
 * href="http://gso.gbv.de/PPNSET?PPN=01945077X">01945077X</a></div></li>
 * </ol>
 */
public final class PermutationUpdate_Swap extends PermutationUpdateOperator {
	/** the serial version uid */
	private static final long serialVersionUID = 1L;

	/** the globally shared instance of this operator */
	public static final PermutationUpdate_Swap INSTANCE = new PermutationUpdate_Swap();

	/** instantiate */
	private PermutationUpdate_Swap() {
		super("swap"); //$NON-NLS-1$
	}

	/** {@inheritDoc} */
	@Override
	public final int delta(final int[] perm, final DistanceComputer f,
			final int a, final int b) {
		final int l, m1;
		final int A, B, C, D, E, F, AB, BC, DE, EF, AE, CE, BD, BF;

		l = perm.length;
		m1 = (l - 1);

		if (a == b) {
			return PermutationUpdateOperator.NO_EFFECT;
		}

		A = (perm[(a + m1) % l]);
		B = perm[a];
		C = perm[(a + 1) % l];
		D = (perm[(b + m1) % l]);
		E = perm[b];
		F = perm[(b + 1) % l];

		if (B == E) {
			return PermutationUpdateOperator.NO_EFFECT;
		}

		AB = f.distance(A, B);
		BC = f.distance(B, C);
		DE = f.distance(D, E);
		EF = f.distance(E, F);

		AE = f.distance(A, E);
		CE = f.distance(E, C);
		BD = f.distance(D, B);
		BF = f.distance(B, F);

		if (B == D) {
			return (BF + AE) - (AB + EF);
		}

		if (E == A) {
			return (BD + CE) - (DE + BC);
		}

		return (AE + CE + BD + BF) - (AB + BC + DE + EF);
	}

	/** {@inheritDoc} */
	@Override
	public final void update(final int[] perm, final int a, final int b) {
		final int tmp = perm[a];
		perm[a] = perm[b];
		perm[b] = tmp;
	}

	/** {@inheritDoc} */
	@Override
	public final ArrayList<_PairOfNode> inComingEdges(final int[] perm, final int a,
			final int b) {
		final int l, m1;
		final int A, B, C, D, E, F;
		ArrayList<_PairOfNode> inComing = new ArrayList<_PairOfNode>();
		
		l = perm.length;
		m1 = (l - 1);
		
		A = (perm[(a + m1) % l]);
		B = perm[a];
		C = perm[(a + 1) % l];
		D = (perm[(b + m1) % l]);
		E = perm[b];
		F = perm[(b + 1) % l];

		if(a==b || B == E) {
			return null;
		}

		if (B == D) {
			inComing.add(new _PairOfNode(B, F));
			inComing.add(new _PairOfNode(A, E));
		}

		if (E == A) {
			inComing.add(new _PairOfNode(B, D));
			inComing.add(new _PairOfNode(C, E));
		}

		else{
			inComing.add(new _PairOfNode(A, E));
			inComing.add(new _PairOfNode(C, E));
			inComing.add(new _PairOfNode(B, D));
			inComing.add(new _PairOfNode(B, F));
		}
		return inComing;
		
	}

	/** {@inheritDoc} */
	@Override
	public final ArrayList<_PairOfNode> outComingEdges(final int[] perm, final int a,
			final int b) {
		final int l, m1;
		final int A, B, C, D, E, F;
		ArrayList<_PairOfNode> outComing = new ArrayList<_PairOfNode>();
		
		l = perm.length;
		m1 = (l - 1);
		
		A = (perm[(a + m1) % l]);
		B = perm[a];
		C = perm[(a + 1) % l];
		D = (perm[(b + m1) % l]);
		E = perm[b];
		F = perm[(b + 1) % l];

		if(a==b || B == E) {
			return null;
		}

		if (B == D) {
			outComing.add(new _PairOfNode(E, F));
			outComing.add(new _PairOfNode(A, B));
		}

		if (E == A) {
			outComing.add(new _PairOfNode(B, C));
			outComing.add(new _PairOfNode(D, E));
		}

		else{
			outComing.add(new _PairOfNode(A, B));
			outComing.add(new _PairOfNode(D, E));
			outComing.add(new _PairOfNode(B, C));
			outComing.add(new _PairOfNode(E, F));
		}
		return outComing;
		
	}

}
