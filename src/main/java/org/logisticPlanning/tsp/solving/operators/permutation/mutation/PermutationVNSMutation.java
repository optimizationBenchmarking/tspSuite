package org.logisticPlanning.tsp.solving.operators.permutation.mutation;

import org.logisticPlanning.tsp.solving.algorithms.localSearch.TSPLocalSearchBasedMutation;
import org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.vns.EImprovementSelectionPolicy;
import org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.vns.PermutationVNS;

/**
 * <p>
 * This class defines an unary search operation (mutation) that works like
 * {@link org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.vns.PermutationVNS}
 * and performs a Variable Neighborhood Search (<a
 * href="http://en.wikipedia.org/wiki/Variable_Neighborhood_Search"
 * >VNS</a>)&nbsp;[<a href="#cite_HM2001VNSPAA"
 * style="font-weight:bold">1</a>, <a href="#cite_HMP2008VNSMAA"
 * style="font-weight:bold">2</a>, <a href="#cite_HMP2010VNSMAA"
 * style="font-weight:bold">3</a>, <a href="#cite_HMBP2010VNS"
 * style="font-weight:bold">4</a>] algorithm embedded in a loop of
 * perturbation and neighborhood shuffling.
 * </p>
 * <p>
 * The VNS is defined over four neighborhoods (search operators) for the
 * path representation (permutation of cities in visiting order):
 * {@link org.logisticPlanning.tsp.solving.operators.permutation.update.PermutationUpdate_Swap
 * swap},
 * {@link org.logisticPlanning.tsp.solving.operators.permutation.update.PermutationUpdate_Rotate_Right
 * rotate right},
 * {@link org.logisticPlanning.tsp.solving.operators.permutation.update.PermutationUpdate_Rotate_Left
 * rotate left}, and
 * {@link org.logisticPlanning.tsp.solving.operators.permutation.update.PermutationUpdate_Reverse
 * reverse}. Each of these operations takes two integer values
 * {@code (i, j)} as parameter. The neighborhoods have proximately the same
 * sizes ({@code n²}) and are disjoint for most parameter tuples. This
 * means that it is not entirely clear which neighborhood we should start
 * with.
 * </p>
 * <p>
 * In the main loop of the search, the neighborhoods used in the VNS are
 * therefore shuffled. Then a fully deterministic local search is performed
 * (Variable Neighborhood Descend). After that concluded in a local
 * optimum, a random part of the solution is shuffled and the process is
 * repeated.
 * </p>
 * <h2>References</h2>
 * <ol>
 * <li><div><span id="cite_HM2001VNSPAA" /><a
 * href="http://www.hec.ca/profs/pierre.hansen.html">Pierre Hansen</a>
 * and&nbsp;<a href=
 * "http://www.brunel.ac.uk/siscm/mathematical-sciences/people-in-maths/academic-staff/drnenadmladenovic"
 * >Nenad Mladenovi&#263;</a>: <span
 * style="font-weight:bold">&ldquo;Variable Neighborhood Search: Principles
 * and Applications,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">European Journal of
 * Operational Research (EJOR)</span> 130(3):449&ndash;467, May&nbsp;1,
 * 2001; published by Amsterdam, The Netherlands: Elsevier Science
 * Publishers B.V. and&nbsp;Amsterdam, The Netherlands: North-Holland
 * Scientific Publishers Ltd.. doi:&nbsp;<a
 * href="http://dx.doi.org/10.1016/S0377-2217(00)00100-4">10.1016
 * /S0377-2217(00)00100-4</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/03772217">0377-2217</a></div></li>
 * <li><div><span id="cite_HMP2008VNSMAA" /><a
 * href="http://www.hec.ca/profs/pierre.hansen.html">Pierre Hansen</a>, <a
 * href=
 * "http://www.brunel.ac.uk/siscm/mathematical-sciences/people-in-maths/academic-staff/drnenadmladenovic"
 * >Nenad Mladenovi&#263;</a>, and&nbsp;<a
 * href="http://jamoreno.webs.ull.es/">Jos&#233; Andr&#233;s Moreno
 * P&#233;rez</a>: <span style="font-weight:bold">&ldquo;Variable
 * Neighbourhood Search: Methods and Applications,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">4OR</span>
 * 6(4):319&ndash;360, December&nbsp;1, 2008; published by Berlin, Germany:
 * Springer-Verlag GmbH. doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/s10288-008-0089-1">10.1007/s10288
 * -008-0089-1</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/16194500">1619-4500</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/16142411">1614-2411</a></div></li>
 * <li><div><span id="cite_HMP2010VNSMAA" /><a
 * href="http://www.hec.ca/profs/pierre.hansen.html">Pierre Hansen</a>, <a
 * href=
 * "http://www.brunel.ac.uk/siscm/mathematical-sciences/people-in-maths/academic-staff/drnenadmladenovic"
 * >Nenad Mladenovi&#263;</a>, and&nbsp;<a
 * href="http://jamoreno.webs.ull.es/">Jos&#233; Andr&#233;s Moreno
 * P&#233;rez</a>: <span style="font-weight:bold">&ldquo;Variable
 * Neighbourhood Search: Methods and Applications,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Annals of Operations
 * Research</span> 175(1):367&ndash;407, March&nbsp;1, 2010; published by
 * Dordrecht, Netherlands: Springer Netherlands and&nbsp;Amsterdam, The
 * Netherlands: J. C. Baltzer AG, Science Publishers. doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/s10479-009-0657-6"
 * >10.1007/s10479-009-0657-6</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/02545330">0254-5330</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/15729338">1572-9338</a></div></li>
 * <li><div><span id="cite_HMBP2010VNS" /><a
 * href="http://www.hec.ca/profs/pierre.hansen.html">Pierre Hansen</a>, <a
 * href=
 * "http://www.brunel.ac.uk/siscm/mathematical-sciences/people-in-maths/academic-staff/drnenadmladenovic"
 * >Nenad Mladenovi&#263;</a>, <a
 * href="http://www.hec.ca/en/profs/jack.brimberg.html">Jack Brimberg</a>,
 * and&nbsp;<a href="http://jamoreno.webs.ull.es/">Jos&#233; Andr&#233;s
 * Moreno P&#233;rez</a>: <span style="font-weight:bold">&ldquo;Variable
 * Neighborhood Search,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Handbook of
 * Metaheuristics</span>, chapter 61&ndash;86, pages 61&ndash;86, <a
 * href="http://www.crt.umontreal.ca/~michelg/">Michel Gendrau</a>
 * and&nbsp;<a href="http://www.iro.umontreal.ca/~potvin/">Jean-Yves
 * Potvin</a>, editors, volume 146 of International Series in Operations
 * Research &amp; Management Science, Norwell, MA, USA: Kluwer Academic
 * Publishers, Dordrecht, Netherlands: Springer Netherlands,
 * and&nbsp;Boston, MA, USA: Springer US. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/1461426901">1461426901</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9781441916655">978-1-4419
 * -1665-5</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/978-1-4419-1665-5_3">10.1007/978-
 * 1-4419-1665-5_3</a>; Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=xMTS5dyDhwMC">xMTS5dyDhwMC</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/08848289">0884-8289</a>;
 * ASIN:&nbsp;<a
 * href="http://www.amazon.com/dp/1461426901">1461426901</a></div></li>
 * </ol>
 */
public class PermutationVNSMutation extends
TSPLocalSearchBasedMutation<int[]> {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** create */
  public PermutationVNSMutation() {
    super(new PermutationVNS());
    this.setImprovementSelectionPolicy(EImprovementSelectionPolicy.DECIDE_RANDOMLY_PER_CALL);
  }

  /**
   * set the policy regarding whether the first improvement or the best
   * improvement should be used
   *
   * @param i
   *          the policy
   */
  public final void setImprovementSelectionPolicy(
      final EImprovementSelectionPolicy i) {
    ((PermutationVNS) (this.getLocalSearchAlgorithm()))
    .setImprovementSelectionPolicy(i);
  }
}
