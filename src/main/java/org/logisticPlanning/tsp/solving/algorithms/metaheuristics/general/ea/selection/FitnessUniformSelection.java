package org.logisticPlanning.tsp.solving.algorithms.metaheuristics.general.ea.selection;

import java.util.Arrays;

import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;
import org.logisticPlanning.tsp.solving.Individual;
import org.logisticPlanning.tsp.solving.IndividualFitnessComparator;
import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.general.ea.SelectionAlgorithm;
import org.logisticPlanning.utils.math.random.Randomizer;

/**
 * <p>
 * The Fitness Uniform Selection Scheme (FUSS)&nbsp;[<a
 * href="#cite_H2006FUO" style="font-weight:bold">1</a>, <a
 * href="#cite_LHK2004TVFUS" style="font-weight:bold">2</a>, <a
 * href="#cite_H2002FUSTPGD" style="font-weight:bold">3</a>, <a
 * href="#cite_LHK2004TVFUS2" style="font-weight:bold">4</a>] first finds
 * the lowest and highest fitness in the population. For each slot in the
 * mating pool, it randomly chooses a fitness &quot;{@code fitness}&quot;
 * according to a uniform distribution between these two extremes. The
 * individual with a fitness closest to &quot; {@code fitness}&quot; is
 * then selected. The goal is to achieve an overall uniform spread of the
 * population over all fitness levels.
 * </p>
 * <h2>References</h2>
 * <ol>
 * <li><div><span id="cite_H2006FUO" /><a
 * href="http://www.hutter1.net/">Marcus Hutter</a> and&nbsp;<a
 * href="http://www.vetta.org/">Shane Legg</a>: <span
 * style="font-weight:bold">&ldquo;Fitness Uniform
 * Optimization,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">IEEE Transactions on
 * Evolutionary Computation (IEEE-EC)</span> 10(5):568&ndash;589,
 * October&nbsp;2006; published by Washington, DC, USA: IEEE Computer
 * Society. LCCN:&nbsp;<a href="http://lccn.loc.gov/97648327">97648327</a>;
 * doi:&nbsp;<a href
 * ="http://dx.doi.org/10.1109/TEVC.2005.863127">10.1109/TEVC
 * .2005.863127</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/1089778X">1089-778X</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/19410026">1941-0026</a>;
 * CODEN:&nbsp;<a href=
 * "http://www-library.desy.de/cgi-bin/spiface/find/coden/www?code=ITEVF5"
 * >ITEVF5</a>; INSPEC Accession Number:&nbsp;9101740; further information:
 * [<a href="http://www.ieee-cis.org/pubs/tec/">1</a>]. <div>links: [<a
 * href
 * ="http://www.vetta.org/documents/FitnessUniformOptimization.pdf">1</a>]
 * and&nbsp;[<a
 * href="http://www.idsia.ch/idsiareport/IDSIA-16-06.pdf">2</a>];
 * arxiv:&nbsp;<a
 * href="http://arxiv.org/abs/cs/0610126v1">cs/0610126v1</a>;
 * CiteSeer<sup>x</sup><sub style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href=
 * "http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.137.2999">
 * 10.1.1 .137.2999</a></div></div></li>
 * <li><div><span id="cite_LHK2004TVFUS" /><a
 * href="http://www.vetta.org/">Shane Legg</a>, <a
 * href="http://www.hutter1.net/">Marcus Hutter</a>, and&nbsp;Akshat Kumar:
 * <span style="font-weight:bold">&ldquo;Tournament versus Fitness Uniform
 * Selection,&rdquo;</span> <span
 * style="font-style:italic;font-family:cursive;">Technical Report</span>
 * Number&nbsp;IDSIA-04-04, March&nbsp;4, 2004; published by Manno-Lugano,
 * Switzerland: Dalle Molle Institute for Artificial Intelligence (IDSIA),
 * University of Lugano, Faculty of Informatics / University of Applied
 * Sciences of Southern Switzerland (SUPSI), Department of Innovative
 * Technologies <span style="color:gray">[Istituto Dalle Molle di Studi
 * sull'Intelligenza Artificiale</span>]. <div>link: [<a
 * href="http://www.idsia.ch/idsiareport/IDSIA-04-04.pdf"
 * >1</a>]</div></div></li>
 * <li><div><span id="cite_H2002FUSTPGD" /><a
 * href="http://www.hutter1.net/">Marcus Hutter</a>: <span
 * style="font-weight:bold">&ldquo;Fitness Uniform Selection to Preserve
 * Genetic Diversity,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the IEEE
 * Congress on Evolutionary Computation (CEC'02); 2002 IEEE World Congress
 * on Computation Intelligence (WCCI'02)</span>, 1-2, May&nbsp;12&ndash;17,
 * 2002, Honolulu, HI, USA: Hilton Hawaiian Village Hotel (Beach Resort
 * &amp; Spa), pages 783&ndash;788, <a
 * href="http://www.natural-selection.com/people_dfogel.html">David B.
 * Fogel</a>, Mohamed A. El-Sharkawi, <a
 * href="http://www.cs.bham.ac.uk/~xin/">Xin Yao</a> <span
 * style="color:gray">[&#23002;&#26032;</span>], Hitoshi Iba, Paul Marrow,
 * and&nbsp;Mark Shackleton, editors, Los Alamitos, CA, USA: IEEE Computer
 * Society Press and&nbsp;Piscataway, NJ, USA: IEEE Computer Society.
 * ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0780372824">0-7803-7282-4</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780780372825">978-0-7803
 * -7282-5</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1109/CEC.2002.1007025">10.1109/CEC.2002
 * .1007025</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/181357364">181357364</a>; Google
 * Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=-ptVAAAAMAAJ">-ptVAAAAMAAJ</a>;
 * INSPEC Accession Number:&nbsp;7336007. <div>links: [<a
 * href="http://www.hutter1.net/ai/pfuss.pdf">1</a>], [<a
 * href="http://www.hutter1.net/ai/pfuss.ps">2</a>], [<a
 * href="http://www.hutter1.net/ai/pfuss.tar">3</a>], and&nbsp;[<a
 * href="ftp://ftp.idsia.ch/pub/techrep/IDSIA-01-01.ps.gz">4</a>];
 * arxiv:&nbsp;<a href="http://arxiv.org/abs/cs/0103015">cs/0103015</a>;
 * CiteSeer<sup>x</sup><sub style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href=
 * "http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.106.9784">
 * 10.1.1 .106.9784</a></div></div></li>
 * <li><div><span id="cite_LHK2004TVFUS2" /><a
 * href="http://www.vetta.org/">Shane Legg</a>, <a
 * href="http://www.hutter1.net/">Marcus Hutter</a>, and&nbsp;Akshat Kumar:
 * <span style="font-weight:bold">&ldquo;Tournament versus Fitness Uniform
 * Selection,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the IEEE
 * Congress on Evolutionary Computation (CEC'04)</span>,
 * June&nbsp;20&ndash;23, 2004, Portland, OR, USA, pages 2144&ndash;2151,
 * Los Alamitos, CA, USA: IEEE Computer Society Press. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0780385152">0-7803-8515-2</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780780385153">978-0-7803
 * -8515-3</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1109/CEC.2004.1331162">10.1109/CEC.2004
 * .1331162</a>; Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=I8_5AAAACAAJ">I8_5AAAACAAJ</a>;
 * INSPEC Accession Number:&nbsp;8229180. <div>links: [<a
 * href="http://www.hutter1.net/ai/fussexp.pdf">1</a>], [<a
 * href="http://www.hutter1.net/ai/fussexp.ps">2</a>], and&nbsp;[<a
 * href="http://www.hutter1.net/ai/fussexp.zip">3</a>]; source code: [<a
 * href="http://www.hutter1.net/ai/fussdd.cpp">1</a>], [<a
 * href="http://www.hutter1.net/ai/fussdd.h">2</a>], [<a
 * href="http://www.hutter1.net/ai/fusstsp.cpp">3</a>], and&nbsp;[<a
 * href="http://www.hutter1.net/ai/fusstsp.h">4</a>]; arxiv:&nbsp;<a
 * href="http://arxiv.org/abs/cs/0403038v1">cs/0403038v1</a>;
 * CiteSeer<sup>x</sup><sub style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href=
 * "http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.71.2663">10.1
 * .1.71 .2663</a></div></div></li>
 * </ol>
 */
public final class FitnessUniformSelection extends SelectionAlgorithm {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the globally shared instance */
  public static final FitnessUniformSelection INSTANCE = new FitnessUniformSelection();

  /**
   * the Fitness Uniform Selection Schemes' constructor: private, use
   * {@link #INSTANCE}
   */
  private FitnessUniformSelection() {
    super("FUSS"); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  public final void select(final Individual<?>[] pop,
      final Individual<?>[] mate, final ObjectiveFunction f) {
    final double minSample, range;
    final Randomizer r;
    double fitness, midFitness, div, t;
    int i, j, low, high, mid;

    // sort the population: lowest fitness first, highest fitness last
    Arrays.sort(pop, IndividualFitnessComparator.INSTANCE);

    minSample = pop[0].f;
    range = (pop[pop.length - 1].f - minSample);
    r = f.getRandom();

    // for each slot in the mating pool
    for (i = mate.length; (--i) >= 0;) {
      // store individual with fitness closest to "fitness"
      fitness = ((r.nextDouble() * range) + minSample);

      // binary search
      low = 0;
      high = (pop.length - 1);
      finder: while (low <= high) {
        mid = ((low + high) >>> 1);
        midFitness = pop[mid].f;
        if (midFitness < fitness) {
          low = (mid + 1);
        } else {
          if (midFitness > fitness) {
            high = (mid - 1);
          } else {
            low = mid;
            break finder;
          }
        }
      }

      // ok, "low" now should hold an index of an individual with a
      // fitness
      // close to "fitness"
      high = low;

      // but there may be several individuals of the same fitness - let's
      // find
      // the one of them at the lowest index
      div = Math.abs(pop[low].f - fitness);
      findLowerLimit: for (j = low; (--j) >= 0;) {
        t = Math.abs(pop[j].f - fitness);
        if (t <= div) {// ok, the individual has the same distance as
          // low
          low = j; // update lower range end
          if (t < div) {// that should not happen...
            div = t; // check the "findUpperLimit" loop below for
            // the meaning
            high = j;
          }
        } else {
          break findLowerLimit;// individual is farther away, we can
          // stop
        }
      }

      // and now the one at the highest index
      findUpperLimit: for (j = high; (++j) < pop.length;) {
        t = Math.abs(pop[j].f - fitness);
        if (t <= div) {// individual has same distance as "low"
          high = j;
          if (t < div) {// this is possible: individual is actually
            // closer
            div = t;// so reset minimum distance
            low = j;// and also lower range limit
          }
        } else {
          break findUpperLimit;
        }
      }

      // by now, the range [low,high] contains the individuals closest to
      // "fitness"
      // so randomly pick one from that range
      mate[i] = pop[r.nextInt((high - low) + 1) + low];
    }
  }

  /** {@inheritDoc} */
  @Override
  public final FitnessUniformSelection clone() {
    return FitnessUniformSelection.INSTANCE;
  }

  //

  // default, automatic serialization replacement and resolve routines for
  // singletons
  //
  /**
   * Write replace: the instance this method is invoked on will be replaced
   * with the singleton instance {@link FitnessUniformSelection#INSTANCE
   * FitnessUniformSelection.INSTANCE} for serialization, i.e., when the
   * instance is written with
   * {@link java.io.ObjectOutputStream#writeObject(Object)}.
   *
   * @return the replacement instance (always
   *         {@link FitnessUniformSelection#INSTANCE
   *         FitnessUniformSelection.INSTANCE})
   */
  private final Object writeReplace() {
    return FitnessUniformSelection.INSTANCE;
  }

  /**
   * Read resolve: The instance this method is invoked on will be replaced
   * with the singleton instance {@link FitnessUniformSelection#INSTANCE
   * FitnessUniformSelection.INSTANCE} after serialization, i.e., when the
   * instance is read with {@link java.io.ObjectInputStream#readObject()}.
   *
   * @return the replacement instance (always
   *         {@link FitnessUniformSelection#INSTANCE
   *         FitnessUniformSelection.INSTANCE})
   */
  private final Object readResolve() {
    return FitnessUniformSelection.INSTANCE;
  }

}
