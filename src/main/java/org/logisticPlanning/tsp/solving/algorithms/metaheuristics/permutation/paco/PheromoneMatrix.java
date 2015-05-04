package org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.paco;

import java.util.Arrays;

/**
 *
 <p>
 * An internal class to represent an asymmetric pheromone matrix for the
 * pACO&nbsp;[<a href="#cite_G2004AAISAMCE" style="font-weight:bold">1</a>,
 * <a href="#cite_GM2002APBAFA" style="font-weight:bold">2</a>, <a
 * href="#cite_GM2002APBATDOP" style="font-weight:bold">3</a>]. The idea is
 * that we want to not allocate a whole matrix in order to save memory,
 * since some problems may be really huge. We thus perform the following
 * trick: If the population-based ACO maintains {@code k} ants, this means
 * that, for each node, there may be at most {@code k} pheromone entries,
 * for the at most {@code k} following nodes. Hence, in total, we can
 * reduce the memory consumption to {@code O(k*n)} (instead of {@code n*n}
 * for a full matrix), where {@code k&lt;n} and small, usually 3 or
 * 5&nbsp;[<a href="#cite_G2004AAISAMCE" style="font-weight:bold">1</a>, <a
 * href="#cite_GM2002APBAFA" style="font-weight:bold">2</a>, <a
 * href="#cite_GM2002APBATDOP" style="font-weight:bold">3</a>].
 * </p>
 * <h2>References</h2>
 * <ol>
 * <li><div><span id="cite_G2004AAISAMCE" /><a
 * href="http://www.aifb.kit.edu/web/Michael_Guntsch/en">Michael
 * Guntsch</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Ant Algorithms in
 * Stochastic and Multi-Criteria Environments,&rdquo;</span> PhD Thesis,
 * January&nbsp;13, 2004, Karlsruhe, Germany: University of Karlsruhe
 * (Friedriciana), Department of Economics and Business Engineering
 * and&nbsp;Karlsruhe, Germany: University of Karlsruhe (Friedriciana),
 * Institute for Applied Computer Science and Formal Description Methods
 * (AIFB). Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=Lf1ztwAACAAJ">Lf1ztwAACAAJ</a>.
 * <div>links: [<a
 * href="http://www.lania.mx/~ccoello/EMOO/thesis_guntsch.pdf.gz">1</a>]
 * and&nbsp;[<a
 * href="http://digbib.ubka.uni-karlsruhe.de/volltexte/212004">2</a>];
 * urn:&nbsp;<a href=
 * "http://wm-urn.org/?urn=urn:nbn:de:swb:90-AAA2120045&amp;redirect=1"
 * >urn:nbn:de:swb:90-AAA2120045</a></div></div></li>
 * <li><div><span id="cite_GM2002APBAFA" /><a
 * href="http://www.aifb.kit.edu/web/Michael_Guntsch/en">Michael
 * Guntsch</a> and&nbsp;<a href=
 * "http://pacosy.informatik.uni-leipzig.de/15-0-Prof+Dr+Martin+Middendorf.html"
 * >Martin Middendorf</a>: <span style="font-weight:bold">&ldquo;A
 * Population Based Approach for ACO,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Applications of
 * Evolutionary Computing, Proceedings of EvoWorkshops 2002: EvoCOP,
 * EvoIASP, EvoSTIM/EvoPLAN (EvoWorkshops'02)</span>, April&nbsp;2&ndash;4,
 * 2002, Kinsale, Ireland, pages 72&ndash;81, <a
 * href="http://www.ce.unipr.it/people/cagnoni/">Stefano Cagnoni</a>, Jens
 * Gottlieb, <a
 * href="http://www.soc.napier.ac.uk/~emmah/Prof_Emma_Hart/Welcome.html"
 * >Emma Hart</a>, <a href=
 * "http://pacosy.informatik.uni-leipzig.de/15-0-Prof+Dr+Martin+Middendorf.html"
 * >Martin Middendorf</a>, and&nbsp;<a
 * href="https://www.ads.tuwien.ac.at/raidl/">G&#252;nther R. Raidl</a>,
 * editors, volume 2279 of Lecture Notes in Computer Science (LNCS),
 * Berlin, Germany: Springer-Verlag GmbH. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/3540434321">3-540-43432-1</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783540434320">978-3-540-
 * 43432-0</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/3-540-46004-7_8"
 * >10.1007/3-540-46004-7_8</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/03029743">0302-9743</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/16113349">1611-3349</a>.
 * <div>CiteSeer<sup>x</sup><sub
 * style="font-style:italic">&#946;</sub>:&nbsp;<a href
 * ="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.13.2514"
 * >10.1.1.13 .2514</a></div></div></li>
 * <li><div><span id="cite_GM2002APBATDOP" /><a
 * href="http://www.aifb.kit.edu/web/Michael_Guntsch/en">Michael
 * Guntsch</a> and&nbsp;<a href=
 * "http://pacosy.informatik.uni-leipzig.de/15-0-Prof+Dr+Martin+Middendorf.html"
 * >Martin Middendorf</a>: <span style="font-weight:bold">&ldquo;Applying
 * Population Based ACO to Dynamic Optimization Problems,&rdquo;</span> in
 * <span style="font-style:italic;font-family:cursive;">From Ant Colonies
 * to Artificial Ants &#8210; Proceedings of the Third International
 * Workshop on Ant Colony Optimization (ANTS'02)</span>,
 * September&nbsp;12&ndash;14, 2002, Brussels, Belgium, pages
 * 111&ndash;122, <a
 * href="https://en.wikipedia.org/wiki/Marco_Dorigo">Marco Dorigo</a>, <a
 * href="http://www.idsia.ch/~gianni/">Gianni A. Di Caro</a>,
 * and&nbsp;Michael Samples, editors, volume 2463/2002 of Lecture Notes in
 * Computer Science (LNCS), Berlin, Germany: Springer-Verlag GmbH.
 * ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/3540441468">3-540-44146-8</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783540441465">978-3-540-
 * 44146-5</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/3-540-45724-0_10">10.1007/3-540-45724
 * -0_10</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/03029743">0302-9743</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/16113349">1611-3349</a>.
 * <div>CiteSeer<sup>x</sup><sub
 * style="font-style:italic">&#946;</sub>:&nbsp;<a href
 * ="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.12.6580"
 * >10.1.1.12 .6580</a></div></div></li>
 * </ol>
 */
public final class PheromoneMatrix {

  /** the default matrix */
  private double m_default;

  /** the node id matrix */
  private int[] m_ids;

  /** the values */
  private double[] m_values;

  /** the number of ants */
  private int m_k;

  /** the maximum tau */
  private double m_tauAdd;

  /** the deletion threshold */
  private double m_delThreshold;

  /** instantiate a pheromone matrix */
  PheromoneMatrix() {
    super();
  }

  /**
   * instantiate the matrix
   *
   * @param n
   *          the number of nodes
   * @param k
   *          the number of ants
   * @param tauMax
   *          the maximum tau
   */
  final void init(final int n, final int k, final double tauMax) {
    int entries;

    this.m_k = k;

    // if there are k ants, there can be at most n*(k*2) entries
    // we use the (k+1)th value as a simple end mark to make updates easier
    entries = (n * (k + 1));

    if ((this.m_ids == null) || (this.m_ids.length < entries)) {
      this.m_ids = new int[entries];
      this.m_values = new double[entries];
    }

    this.m_default = (1d / (n - 1));
    this.m_tauAdd = ((tauMax - this.m_default) / k);
    this.m_delThreshold = (this.m_default + (0.5 * this.m_tauAdd));
    Arrays.fill(this.m_values, 0, entries, this.m_default);
    Arrays.fill(this.m_ids, 0, entries, Integer.MAX_VALUE);
  }

  /**
   * get the pheromone value for the edge from {@code a} to {@code b}
   *
   * @param a
   *          the first node index
   * @param b
   *          the second node index
   * @return the pheromone value
   */
  final double get(final int a, final int b) {
    int i, idx;

    i = ((a - 1) * (this.m_k + 1));
    idx = Arrays.binarySearch(this.m_ids, i, (i + this.m_k), b);
    if (idx < 0) {
      return this.m_default;
    }

    return this.m_values[idx];
  }

  /**
   * add a pheromone value for a given edge
   *
   * @param a
   *          the first node of the edge
   * @param b
   *          the second node of the edge
   * @param add
   *          the value to add
   */
  private final void add(final int a, final int b, final double add) {
    int i, idx, end;
    final double[] v;
    final int[] ids;

    i = ((this.m_k + 1) * (a - 1));
    end = (i + this.m_k);
    ids = this.m_ids;
    idx = Arrays.binarySearch(ids, i, end, b);
    v = this.m_values;
    if (idx >= 0) {
      v[idx] += add;
      return;
    }

    end += idx;
    idx = (-(idx + 1));
    System.arraycopy(v, idx, v, (idx + 1), end);
    v[idx] = (this.m_default + add);
    System.arraycopy(ids, idx, ids, (idx + 1), end);
    ids[idx] = b;
  }

  /**
   * delete a pheromone value for a given edge
   *
   * @param a
   *          the first node of the edge
   * @param b
   *          the second node of the edge
   * @param delete
   *          the value to subtract
   */
  private final void delete(final int a, final int b, final double delete) {
    int i, idx, end;
    final double[] v;
    final int[] ids;

    i = ((a - 1) * (this.m_k + 1));
    end = (i + this.m_k);
    ids = this.m_ids;
    idx = Arrays.binarySearch(ids, i, end, b);
    v = this.m_values;
    if (idx >= 0) {
      if ((v[idx] -= delete) <= this.m_delThreshold) {
        end -= (idx);
        System.arraycopy(v, (idx + 1), v, idx, end);
        System.arraycopy(ids, (idx + 1), ids, idx, end);
      }

      return;
    }
    throw new IllegalStateException();
  }

  /**
   * add a whole permutation
   *
   * @param perm
   *          the permutation
   */
  public final void add(final int[] perm) {
    int last;

    last = perm[perm.length - 1];
    for (final int cur : perm) {
      this.add(last, cur, this.m_tauAdd);
      last = cur;
    }
  }

  /**
   * delete a whole permutation
   *
   * @param perm
   *          the permutation
   */
  public final void delete(final int[] perm) {
    int last;

    last = perm[perm.length - 1];
    for (final int cur : perm) {
      this.delete(last, cur, this.m_tauAdd);
      last = cur;
    }
  }
}
