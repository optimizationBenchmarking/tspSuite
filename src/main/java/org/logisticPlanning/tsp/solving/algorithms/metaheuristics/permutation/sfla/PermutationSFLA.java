// TSP Suite, version 0.9.8
// Copyright (c) 2012-2014 Thomas Weise, http://www.it-weise.de/
// License : The GNU Lesser General Public License, version 3.0
// Project : TSP Suite, Version: 0.9.8, Target Platform: Java 1.7
// File    : main.java.org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.ma.PermutationMA.java
// Website : http://www.logisticPlanning.org/tsp
// Packaged: 2014-04-26 18:05:48 GMT+0800
// 
// 
package org.logisticPlanning.tsp.solving.algorithms.metaheuristics.permutation.sfla;

import java.io.PrintStream;

import org.logisticPlanning.tsp.benchmarking.instances.Instance;
import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;
import org.logisticPlanning.tsp.solving.Individual;
import org.logisticPlanning.tsp.solving.TSPAlgorithmRunner;
import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.general.ea.EA;
import org.logisticPlanning.tsp.solving.gpm.GPM;
import org.logisticPlanning.tsp.solving.operators.MultiUnaryOperator;
import org.logisticPlanning.tsp.solving.operators.UnaryOperator;
import org.logisticPlanning.tsp.solving.operators.permutation.creation.PermutationCreateUniform;
import org.logisticPlanning.tsp.solving.operators.permutation.recombination.PermutationEdgeCrossover;
import org.logisticPlanning.tsp.solving.operators.permutation.update.PermutationUpdateOperators;
import org.logisticPlanning.utils.config.Configurable;
import org.logisticPlanning.utils.math.functions.UnaryFunction;
import org.logisticPlanning.utils.math.functions.power.Sqrt;

/**
 *   <p>  A Memetic Algorithm (MA)&nbsp;[<a href="#cite_M1989MA" style="font-weight:bold">1</a>, <a href="#cite_M2002MA" style="font-weight:bold">2</a>, <a href="#cite_MC2003AGITMA" style="font-weight:bold">3</a>, <a href="#cite_ES2003HWOTMA" style="font-weight:bold">4</a>, <a href="#cite_HKS2005RAIMA" style="font-weight:bold">5</a>, <a href="#cite_DM2004MA" style="font-weight:bold">6</a>, <a href="#cite_RS1994FMA" style="font-weight:bold">7</a>] that works  directly on permutations. This MA performs a hill climber-based local search  (for a number of steps related to the number of cities in a TSP) for each  new solution created by crossover. The EA part only uses selection and  crossover, the local search only uses mutation.  </p>  <h2>References</h2><ol><li><div><span id="cite_M1989MA" /><a href="http://www.newcastle.edu.au/staff/research-profile/Pablo_Moscato/">Pablo Moscato</a>: <span style="font-weight:bold">&ldquo;On Evolution, Search, Optimization, Genetic Algorithms and Martial Arts: Towards Memetic Algorithms,&rdquo;</span> <span style="font-style:italic;font-family:cursive;">Technical Report</span> Number&nbsp;C3P 826, 1989; published by Pasadena, CA, USA: California Institute of Technology (Caltech), Caltech Concurrent Computation Program (C3P). <div>links: [<a href="http://www.densis.fee.unicamp.br/~moscato/papers/bigone.ps">1</a>] and&nbsp;[<a href="http://www.each.usp.br/sarajane/SubPaginas/arquivos_aulas_IA/memetic.pdf">2</a>]; CiteSeer<sup>x</sup><sub style="font-style:italic">&#946;</sub>:&nbsp;<a href="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.27.9474">10.1.1.27.9474</a></div></div></li><li><div><span id="cite_M2002MA" /><a href="http://www.newcastle.edu.au/staff/research-profile/Pablo_Moscato/">Pablo Moscato</a>: <span style="font-weight:bold">&ldquo;Memetic Algorithms,&rdquo;</span> in <span style="font-style:italic;font-family:cursive;">Handbook of Applied Optimization</span>, chapter 157&ndash;167, pages 157&ndash;167, Panos M. Pardalos and&nbsp;Mauricio G.C. Resende, editors, New York, NY, USA: Oxford University Press, Inc.. ISBN:&nbsp;<a href="https://www.worldcat.org/isbn/0195125940">0-19-512594-0</a> and&nbsp;<a href="https://www.worldcat.org/isbn/9780195125948">978-0-19-512594-8</a>; OCLC:&nbsp;<a href="https://www.worldcat.org/oclc/45532495">45532495</a></div></li><li><div><span id="cite_MC2003AGITMA" /><a href="http://www.newcastle.edu.au/staff/research-profile/Pablo_Moscato/">Pablo Moscato</a> and&nbsp;<a href="http://www.lcc.uma.es/~ccottap/">Carlos Cotta</a>: <span style="font-weight:bold">&ldquo;A Gentle Introduction to Memetic Algorithms,&rdquo;</span> in <span style="font-style:italic;font-family:cursive;">Handbook of Metaheuristics</span>, chapter 105&ndash;144, pages 105&ndash;144, <a href="http://en.wikipedia.org/wiki/Fred_W._Glover">Fred W. Glover</a> and&nbsp;<a href="http://www.ucdenver.edu/academics/colleges/business/Faculty-Research/FacultyDirectory/Pages/Gary-Kochenberger.aspx">Gary A. Kochenberger</a>, editors, volume 57 of International Series in Operations Research &amp; Management Science, Norwell, MA, USA: Kluwer Academic Publishers, Dordrecht, Netherlands: Springer Netherlands, and&nbsp;Boston, MA, USA: Springer US. ISBN:&nbsp;<a href="https://www.worldcat.org/isbn/1402072635">1-4020-7263-5</a> and&nbsp;<a href="https://www.worldcat.org/isbn/9780306480560">978-0-306-48056-0</a>; doi:&nbsp;<a href="http://dx.doi.org/10.1007/0-306-48056-5_5">10.1007/0-306-48056-5_5</a>; Google Book ID:&nbsp;<a href="http://books.google.com/books?id=O_10T_KeqOgC">O_10T_KeqOgC</a>; ISSN:&nbsp;<a href="https://www.worldcat.org/issn/08848289">0884-8289</a>. <div>link: [<a href="http://www.lcc.uma.es/~ccottap/papers/handbook03memetic.pdf">1</a>]; CiteSeer<sup>x</sup><sub style="font-style:italic">&#946;</sub>:&nbsp;<a href="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.77.5300">10.1.1.77.5300</a></div></div></li><li><div><span id="cite_ES2003HWOTMA" /><a href="http://www.cs.vu.nl/~gusz/">&#193;goston E. Eiben</a> aka. Gusz/Guszti and&nbsp;<a href="http://www.cems.uwe.ac.uk/~jsmith/">James E. Smith</a>: <span style="font-weight:bold">&ldquo;Hybridisation with other Techniques: Memetic Algorithms,&rdquo;</span> in <span style="font-style:italic;font-family:cursive;">Introduction to Evolutionary Computing</span>, chapter 173&ndash;188, pages 173&ndash;188, Natural Computing Series, New York, NY, USA: Springer New York. ISBN:&nbsp;<a href="https://www.worldcat.org/isbn/3540401849">3540401849</a> and&nbsp;<a href="https://www.worldcat.org/isbn/9783540401841">978-3540401841</a>; Google Book ID:&nbsp;<a href="http://books.google.com/books?id=7IOE5VIpFpwC">7IOE5VIpFpwC</a>; ISSN:&nbsp;<a href="https://www.worldcat.org/issn/16197127">1619-7127</a></div></li><li><div><span id="cite_HKS2005RAIMA" /><span style="font-style:italic;font-family:cursive;">&ldquo;Recent Advances in Memetic Algorithms,&rdquo;</span> 2005, <a href="http://www.cs.sandia.gov/~wehart/Main/Home.html">William Eugene Hart</a>, <a href="http://www.cs.nott.ac.uk/~nxk/">Natalio Krasnogor</a>, and&nbsp;<a href="http://www.cems.uwe.ac.uk/~jsmith/">James E. Smith</a>, editors, volume 166/2005 of Studies in Fuzziness and Soft Computing, Berlin, Germany: Springer-Verlag GmbH. ISBN:&nbsp;<a href="https://www.worldcat.org/isbn/3540229043">3-540-22904-3</a> and&nbsp;<a href="https://www.worldcat.org/isbn/9783540229049">978-3-540-22904-9</a>; LCCN:&nbsp;<a href="http://lccn.loc.gov/2004111139">2004111139</a>; doi:&nbsp;<a href="http://dx.doi.org/10.1007/3-540-32363-5">10.1007/3-540-32363-5</a>; OCLC:&nbsp;<a href="https://www.worldcat.org/oclc/318297267">318297267</a> and&nbsp;<a href="https://www.worldcat.org/oclc/56697114">56697114</a>; Google Book ID:&nbsp;<a href="http://books.google.com/books?id=LYf7YW4DmkUC">LYf7YW4DmkUC</a>; ISSN:&nbsp;<a href="https://www.worldcat.org/issn/14349922">1434-9922</a> and&nbsp;<a href="https://www.worldcat.org/issn/18600808">1860-0808</a></div></li><li><div><span id="cite_DM2004MA" />Jason Digalakis and&nbsp;Konstantinos Margaritis: <span style="font-weight:bold">&ldquo;Performance Comparison of Memetic Algorithms,&rdquo;</span> in <span style="font-style:italic;font-family:cursive;">Journal of Applied Mathematics and Computation</span> 158:237&ndash;252, October&nbsp;2004; published by Essex, UK: Elsevier Science Publishers B.V.. doi:&nbsp;<a href="http://dx.doi.org/10.1016/j.amc.2003.08.115">10.1016/j.amc.2003.08.115</a>; ISSN:&nbsp;<a href="https://www.worldcat.org/issn/00963003">0096-3003</a>; CODEN:&nbsp;<a href="http://www-library.desy.de/cgi-bin/spiface/find/coden/www?code=AMHCBQ">AMHCBQ</a>. <div>links: [<a href="http://citeseer.ist.psu.edu/458892.html">1</a>], [<a href="http://www.complexity.org.au/ci/draft/draft/digala02/digala02s.pdf">2</a>], and&nbsp;[<a href="http://www.complexity.org.au/ci/vol10/digala02/digala02s.pdf">3</a>]; CiteSeer<sup>x</sup><sub style="font-style:italic">&#946;</sub>:&nbsp;<a href="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.21.5495">10.1.1.21.5495</a></div></div></li><li><div><span id="cite_RS1994FMA" /><a href="http://users.breathe.com/njr/">Nicholas J. Radcliffe</a> and&nbsp;<a href="http://www.linkedin.com/in/patricksurry">Patrick David Surry</a>: <span style="font-weight:bold">&ldquo;Formal Memetic Algorithms,&rdquo;</span> in <span style="font-style:italic;font-family:cursive;">Proceedings of the Workshop on Artificial Intelligence and Simulation of Behaviour, International Workshop on Evolutionary Computing, Selected Papers (AISB'94)</span>, April&nbsp;11&ndash;13, 1994, Leeds, UK, pages 1&ndash;16, Terence Claus Fogarty, editor, volume 865/1994 of Lecture Notes in Computer Science (LNCS), Berlin, Germany: Springer-Verlag GmbH and&nbsp;Chichester, West Sussex, UK: Society for the Study of Artificial Intelligence and the Simulation of Behaviour (SSAISB). ISBN:&nbsp;<a href="https://www.worldcat.org/isbn/3540584838">3-540-58483-8</a> and&nbsp;<a href="https://www.worldcat.org/isbn/9783540584834">978-3-540-58483-4</a>; doi:&nbsp;<a href="http://dx.doi.org/10.1007/3-540-58483-8_1">10.1007/3-540-58483-8_1</a>; ISSN:&nbsp;<a href="https://www.worldcat.org/issn/03029743">0302-9743</a> and&nbsp;<a href="https://www.worldcat.org/issn/16113349">1611-3349</a>; PPN:&nbsp;<a href="http://gso.gbv.de/PPNSET?PPN=59512478X">59512478X</a>. <div>CiteSeer<sup>x</sup><sub style="font-style:italic">&#946;</sub>:&nbsp;<a href="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.38.9885">10.1.1.38.9885</a></div></div></li></ol>
 <h2>Change Log</h2><dl> <dt style="font-weight:bold">0.9.8 / 2014-01-02</dt> <dd>The class hierarchy has been changed and the new class {@link org.logisticPlanning.tsp.solving.TSPModule} has been inserted into the parent hierarchy of this class. It provides the two methods, {@link org.logisticPlanning.tsp.solving.TSPModule#beginRun(org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction)} and {@link org.logisticPlanning.tsp.solving.TSPModule#endRun(org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction)} for targeted allocation or initialization and de-allocation or finalization of data structures.</dd> </dl> 
@author <em><a href="http://www.it-weise.de/">Thomas Weise</a></em>, Email:&nbsp;<a href="mailto:tweise@ustc.edu.cn">tweise@ustc.edu.cn</a>, Tel.:&nbsp;<a href="tel:+86 187 551 228 41">+86 187 551 228 41</a>; <a href="http://www.ustc.edu.cn/">University of Science and Technology of China (USTC)</a> [&#x4E2D;&#x56FD;&#x79D1;&#x5B66;&#x6280;&#x672F;&#x5927;&#x5B66;], (<a href="https://en.wikipedia.org/wiki/University_of_Science_and_Technology_of_China">wikipedia</a>); <a href="http://cs.ustc.edu.cn/">School of Computer Science and Technology (SCST)</a> [&#x8BA1;&#x7B97;&#x673A;&#x79D1;&#x5B66;&#x4E0E;&#x6280;&#x672F;&#x5B66;&#x9662;]; <a href="http://ubri.ustc.edu.cn/">USTC-Birmingham Joint Research Institute in Intelligent Computation and Its Applications (UBRI)</a>; West Campus [&#x897F;&#x533A;]; Crossroad of Huangshan Road and Feixi Road [&#x9EC4;&#x5C71;&#x8DEF;/&#x80A5;&#x897F;&#x8DEF;&#x5341;&#x5B57;&#x8DEF;&#x53E3;]; <a href="https://en.wikipedia.org/wiki/Hefei">Hefei</a> [&#x5408;&#x80A5;&#x5E02;] 230027; <a href="https://en.wikipedia.org/wiki/Anhui">Anhui</a> [&#x5B89;&#x5FBD;&#x7701;]; <a href="https://en.wikipedia.org/wiki/People%27s_Republic_of_China">China</a> [&#x4E2D;&#x56FD;]
@version version:0.9.8/file:2014-01-05T07:11:47_673+0800/build:2014-04-26T18:05:48_340+0800/platform:Java_1.7
 */
public class PermutationSFLA extends EA {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the maximum number of local search steps: {@value} */
  public static final String PARAM_MAX_STEPS = "maxLocalSearchSteps"; //$NON-NLS-1$

  /**
   * the maximum number of steps in the local search
   * 
   * @serial serializable field
   */
  private final UnaryFunction m_maxSteps;

  /** the temporary individual */
  private transient Individual<Object> m_temp;

  /** the number of steps to do */
  private transient int m_steps;

  /**
   * create the ea
   */
  public PermutationSFLA() {
    super("Permutation-based Shuffled Frog Leaping Algorithm");//$NON-NLS-1$

    this.setNullaryOperator(new PermutationCreateUniform());
    this.setUnaryOperator(new MultiUnaryOperator<>(
        PermutationUpdateOperators.UPDATE_OPERATORS));
    this.setBinaryOperator(new PermutationEdgeCrossover());

    this.setCrossoverRate(1d);

    this.m_maxSteps = Sqrt.INSTANCE;
    this.m_temp = new Individual<>();
  }

  /**
   * Perform the developmental updating EA
   * 
   * @param args
   *          the command line arguments
   */
  public final static void main(final String[] args) {
    TSPAlgorithmRunner.benchmark(Instance.SYMMETRIC_INSTANCES,
    		PermutationSFLA.class,//
        args);
  }

  /** {@inheritDoc} */
  @Override
  public final PermutationSFLA clone() {
	  PermutationSFLA cfg;

    cfg = ((PermutationSFLA) (super.clone()));

    // cfg.m_maxSteps = cfg.m_maxSteps.clone();
    cfg.m_temp = null;
    return cfg;
  }

  /** {@inheritDoc} */
  @Override
  public void printConfiguration(final PrintStream ps) {
    super.printConfiguration(ps);

    Configurable.printKey(PermutationSFLA.PARAM_MAX_STEPS, ps);
    Configurable.printlnObject(this.m_maxSteps, ps);
  }

  /** {@inheritDoc} */
  @Override
  public final void solve(final ObjectiveFunction f) {
    this.m_steps = ((int) (Math.ceil(this.m_maxSteps.compute(f.n())) + 0.5d));
    super.solve(f);
  }

  /** {@inheritDoc} */
  @Override
  public final void complete(final Individual<Object> ind,
      final ObjectiveFunction f, final GPM<Object> gpm) {
    final Individual<Object> temp;
    final UnaryOperator<Object> op1;
    int i;

    op1 = this.getUnaryOperator();
    temp = this.m_temp;
    super.complete(ind, f, gpm);
    if (f.shouldTerminate()) {
      return;
    }

    for (i = this.m_steps; (--i) >= 0;) {
      temp.clear();
      op1.mutate(temp, f, ind);
      super.complete(ind, f, gpm);
      if (f.shouldTerminate()) {
        return;
      }

      if (temp.tourLength < ind.tourLength) {
        ind.assign(temp);
      }
    }
  }

  /** {@inheritDoc} */
  @Override
  public void beginRun(final ObjectiveFunction f) {
    super.beginRun(f);
    this.m_temp = new Individual<>();
  }

  /** {@inheritDoc} */
  @Override
  public void endRun(final ObjectiveFunction f) {
    this.m_temp = null;
    super.endRun(f);
  }
}
// 
// 
// Information about the third-party software used is given in the file
// NOTICE.txt distributed with this framework. More information on this
// project is given at http://www.logisticPlanning.org/tsp. The license of
// this software and its source code (except the third party components) is
// the The GNU Lesser General Public License, version 3.0 (LGPL 3.0). A copy
// of the LGPL 3.0 is in file LICENSE.txt; and can also be found at
// http://www.opensource.org/licenses/LGPL-3.0.
// 
// Contact: Dr. Thomas Weise
// The USTC-Birmingham Joint Research Institute in Intelligent Computation and
// Its Applications (UBRI)
// School of Computer Science and Technology (SCST)
// University of Science and Technology of China (USTC)
// West Campus, Huangshan Road/Feixi Road, Hefei 230027, Anhui, China
// Email: tweise@gmx.de, tweise@ustc.edu.cn  Web: http://www.it-weise.de/
