package org.logisticPlanning.tsp.solving.operators.permutation.creation;

import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;
import org.logisticPlanning.tsp.solving.Individual;
import org.logisticPlanning.utils.math.random.Randomizer;

/**
 * <p>
 * A permutation creator which generates uniformly <a
 * href="https://en.wikipedia.org/wiki/Random_permutation">randomly
 * distributed permutations</a>. For creating these permutations, we use
 * the <a href="https://en.wikipedia.org/wiki/Knuth_shuffle">Knuth
 * Shuffle</a> algorithm&nbsp;[<a href="#cite_FY1938STFBAAMR"
 * style="font-weight:bold">1</a>, <a href="#cite_D1964A235RP"
 * style="font-weight:bold">2</a>, <a href="#cite_K1969TAOCPSNA"
 * style="font-weight:bold">3</a>].
 * </p>
 * <p>
 * <strong>Warning: Due to a bug in
 * {@link org.logisticPlanning.utils.math.random.Randomizer#shuffle(int[])}
 * , the permutations created by this method were not actually uniformly
 * distributed. They were random, but not uniformly distributed. This bug
 * has been fixed in version 0.9.9. The bug actually is one of the common
 * problems listed <a href=
 * "http://en.wikipedia.org/wiki/Knuth_shuffle#Implementation_errors"
 * >here</a>: The swap index was always picked from the whole
 * range.</strong>
 * </p>
 * <h2>References</h2>
 * <ol>
 * <li><div><span id="cite_FY1938STFBAAMR" />Sir Ronald Aylmer Fisher
 * and&nbsp;Frank Yates: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Statistical Tables
 * for Biological, Agricultural and Medical Research,&rdquo;</span>
 * 1938&ndash;1948, London, UK: Oliver and Boyd and&nbsp;New York, NY, USA:
 * Macmillan Publishers Co.. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0582445256">0-582-44525-6</a>, <a
 * href="https://www.worldcat.org/isbn/0028447204">0-02-844720-4</a>, <a
 * href="https://www.worldcat.org/isbn/0050008722">0-05-000872-2</a>,
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780582445253">978-0582445253</a>;
 * LCCN:&nbsp;<a href="http://lccn.loc.gov/50013072">50013072</a>, <a
 * href="http://lccn.loc.gov/39000863">39000863</a>, <a
 * href="http://lccn.loc.gov/63022899">63022899</a>, <a
 * href="http://lccn.loc.gov/58021141">58021141</a>, <a
 * href="http://lccn.loc.gov/53030353">53030353</a>, <a
 * href="http://lccn.loc.gov/43012938">43012938</a>, <a
 * href="http://lccn.loc.gov/57004942">57004942</a>, and&nbsp;<a
 * href="http://lccn.loc.gov/53002395">53002395</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/14222135">14222135</a>;
 * ASIN:&nbsp;<a href="http://www.amazon.com/dp/B000UWVZG6">B000UWVZG6</a>;
 * PPN:&nbsp;<a
 * href="http://gso.gbv.de/PPNSET?PPN=022729143">022729143</a>, <a
 * href="http://gso.gbv.de/PPNSET?PPN=212944711">212944711</a>, <a
 * href="http://gso.gbv.de/PPNSET?PPN=197001637">197001637</a>, <a
 * href="http://gso.gbv.de/PPNSET?PPN=171985052">171985052</a>, <a
 * href="http://gso.gbv.de/PPNSET?PPN=548956669">548956669</a>, <a
 * href="http://gso.gbv.de/PPNSET?PPN=342017845">342017845</a>, <a
 * href="http://gso.gbv.de/PPNSET?PPN=321398483">321398483</a>, <a
 * href="http://gso.gbv.de/PPNSET?PPN=214623505">214623505</a>, <a
 * href="http://gso.gbv.de/PPNSET?PPN=307587746">307587746</a>, and&nbsp;<a
 * href="http://gso.gbv.de/PPNSET?PPN=025964712">025964712</a>. <div>link:
 * [<a href
 * ="http://digital.library.adelaide.edu.au/dspace/handle/2440/10701"
 * >1</a>]< /div></div></li>
 * <li><div><span id="cite_D1964A235RP" />Richard Durstenfeld: <span
 * style="font-weight:bold">&ldquo;Algorithm 235: Random
 * Permutation,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Communications of the ACM
 * (CACM)</span> 7(7):420, July&nbsp;1964; published by New York, NY, USA:
 * ACM Press. doi:&nbsp;<a
 * href="http://dx.doi.org/10.1145/364520.364540">10.1145
 * /364520.364540</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/00010782">0001-0782</a></div></li>
 * <li><div><span id="cite_K1969TAOCPSNA" />Donald Ervin Knuth: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Seminumerical
 * Algorithms,&rdquo;</span> 1969, volume 2 of The Art of Computer
 * Programming (TAOCP), Boston, MA, USA: Addison-Wesley Longman Publishing
 * Co., Inc.. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0201896842">0-201-89684-2</a>, <a
 * href="https://www.worldcat.org/isbn/8177583352">8177583352</a>, <a
 * href="https://www.worldcat.org/isbn/9780201896848"
 * >978-0-201-89684-8</a>, and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9788177583359">978-8177583359</a>;
 * OCLC:&nbsp;<a href="https://www.worldcat.org/oclc/85975465">85975465</a>
 * and&nbsp;<a href="https://www.worldcat.org/oclc/38207978">38207978</a>;
 * Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=OtLNKNVh1XoC">OtLNKNVh1XoC</a>;
 * ASIN:&nbsp;<a
 * href="http://www.amazon.com/dp/0201896842">0201896842</a></div></li>
 * </ol>
 */
public class PermutationCreateUniform extends PermutationCreateCanonical {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** instantiate */
  public PermutationCreateUniform() {
    super("uniformRandomPermutationCreator"); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  public void create(final Individual<int[]> dest,
      final ObjectiveFunction f) {
    super.create(dest, f);
    f.getRandom().shuffle(dest.solution, 0, dest.solution.length);
  }

  /**
   * Create a new random permutation of {@code n} elements
   * 
   * @param n
   *          instantiate a random permutation
   * @param r
   *          the randomizer
   * @return the new permutation
   */
  public static final int[] create(final int n, final Randomizer r) {
    int[] g;

    g = PermutationCreateCanonical.canonical(n);
    r.shuffle(g, 0, n);

    return g;
  }
}
