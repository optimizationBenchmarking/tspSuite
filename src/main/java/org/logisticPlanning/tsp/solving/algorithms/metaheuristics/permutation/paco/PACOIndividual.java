package org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.paco;

import org.logisticPlanning.tsp.solving.Individual;

/**
 * 
 An individual record as used by pACO&nbsp;[<a href="#cite_G2004AAISAMCE"
 * style="font-weight:bold">1</a>, <a href="#cite_GM2002APBAFA"
 * style="font-weight:bold">2</a>, <a href="#cite_GM2002APBATDOP"
 * style="font-weight:bold">3</a>]. <h2>References</h2>
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
public final class PACOIndividual extends Individual<int[]> {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the birthday in terms of generations */
  public long m_birthday;

  /** create */
  PACOIndividual() {
    super();
  }

  /**
   * setup
   * 
   * @param data
   *          the data
   * @param length
   *          the value
   * @param birthday
   *          the birthday
   */
  final void setup(final int[] data, final long length, final long birthday) {
    final int l;

    this.tourLength = length;
    this.m_birthday = birthday;
    l = data.length;
    if ((this.solution != null) && (this.solution.length == l)) {
      System.arraycopy(data, 0, this.solution, 0, l);
    } else {
      this.solution = data.clone();
    }
  }

  /**
   * assign this individual record to the same values as stored in another
   * one
   * 
   * @param copy
   *          the record to copy
   */
  public final void assign(final PACOIndividual copy) {
    this.setup(copy.solution, copy.tourLength, copy.m_birthday);
  }

  /** {@inheritDoc} */
  @Override
  public final void assign(final Individual<int[]> copy) {
    if (copy instanceof PACOIndividual) {
      this.assign((PACOIndividual) copy);
    } else {
      super.assign(copy);
    }
  }

  /** clear */
  final void doclear() {
    this.tourLength = Individual.TOUR_LENGTH_NOT_SET;
    this.m_birthday = Long.MIN_VALUE;
  }

  /**
   * is this record empty?
   * 
   * @return {@code true} if this record is empty
   */
  public final boolean isEmpty() {
    return (this.m_birthday < 0l);
  }

  /** {@inheritDoc} */
  @Override
  public final void clear() {
    super.clear();
    this.doclear();
  }
}
