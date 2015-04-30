package org.logisticPlanning.tsp.solving.algorithms.metaheuristics.general.ea.fitness;

import java.io.PrintStream;
import java.util.Arrays;

import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;
import org.logisticPlanning.tsp.solving.Individual;
import org.logisticPlanning.tsp.solving.algorithms.metaheuristics.general.ea.FitnessAssignmentProcess;
import org.logisticPlanning.utils.config.Configurable;
import org.logisticPlanning.utils.config.Configuration;

/**
 * <p>
 * The Frequency Fitness Assignment (FFA)&nbsp;[<a
 * href="#cite_WWTWDY2014FFA" style="font-weight:bold">1</a>] process
 * manages a history table {@code H}. {@code H} has an entry for every
 * objective value (
 * {@link org.logisticPlanning.tsp.solving.Individual#tourLength tour
 * length} ever encountered. Which is increased by one when, well, the
 * objective value is encountered. The fitness belonging to an objective
 * value then is this number. The goal is to let the search explore many
 * different solutions.
 * </p>
 * <p>
 * This implementation has one change compared to&nbsp;[<a
 * href="#cite_WWTWDY2014FFA" style="font-weight:bold">1</a>]: The table
 * length has an upper boundary. Theoretically, in a TSP we may have almost
 * arbitrarily many solutions with different total distances. In order to
 * prevent a memory overflow, our history tables are limited. If more tour
 * lengths than allowed table entries occur, the last table entries will be
 * merged. Although this may create some evolutionary pressure towards
 * better solutions, a table size limit of a million or more entries will
 * keep this pressure low compared to the pressure created towards the
 * search for diverse solutions by the frequency fitness.
 * </p>
 * <h2>References</h2>
 * <ol>
 * <li><div><span id="cite_WWTWDY2014FFA" /><a
 * href="http://www.it-weise.de/">Thomas Weise</a> <span
 * style="color:gray">[&#27748;&#21355;&#24605;</span>], <a
 * href="mailto:mingxu@mail.ustc.edu.cn">Mingxu Wan</a> <span
 * style="color:gray">[&#19975;&#26126;&#32490;</span>], <a
 * href="http://staff.ustc.edu.cn/~ketang/">Ke Tang</a> <span
 * style="color:gray">[&#21776;&#29634;</span>], <a
 * href="http://mail.ustc.edu.cn/~wuyou308/">Pu Wang</a> <span
 * style="color:gray">[&#29579;&#29854;</span>], <a
 * href="http://www.marmakoide.org/">Alexandre Devert</a>, and&nbsp;<a
 * href="http://www.cs.bham.ac.uk/~xin/">Xin Yao</a> <span
 * style="color:gray">[&#23002;&#26032;</span>]: <span
 * style="font-weight:bold">&ldquo;Frequency Fitness
 * Assignment,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">IEEE Transactions on
 * Evolutionary Computation (IEEE-EC)</span> 18(2):226&ndash;243,
 * April&nbsp;2014; published by Washington, DC, USA: IEEE Computer
 * Society. LCCN:&nbsp;<a href="http://lccn.loc.gov/97648327">97648327</a>;
 * doi:&nbsp;<a href
 * ="http://dx.doi.org/10.1109/TEVC.2013.2251885">10.1109/
 * TEVC.2013.2251885</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/1089778X">1089-778X</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/19410026">1941-0026</a>;
 * CODEN:&nbsp;<a href=
 * "http://www-library.desy.de/cgi-bin/spiface/find/coden/www?code=ITEVF5"
 * >ITEVF5</a>; INSPEC Accession Number:&nbsp;14196623;
 * SCI/WOS:&nbsp;WOS:000334166600006; EI:&nbsp;20141517565962; further
 * information: [<a href="http://www.ieee-cis.org/pubs/tec/">1</a>].
 * <div>links: [<a
 * href="http://www.it-weise.de/documents/files/WWTWDY2014FFA.pdf">1</a>]
 * and&nbsp;[<a
 * href="http://www.cs.bham.ac.uk/~xin/papers/tevc2013FFA.pdf">2</a>
 * ]</div></div></li>
 * </ol>
 */
public final class FFA extends FitnessAssignmentProcess {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the maximum size of the FFA table: {@value} */
  public static final String PARAM_MAX_TABLE_LENGTH = "maxFFATableSize"; //$NON-NLS-1$

  /** the default maximum table length: {@value} */
  private static final int DEFAULT_MAX_TABLE_LENGTH = (1 << 20);
  /** the default maximum table length: {@value} */
  private static final int DEFAULT_INIT_TABLE_SIZE = (1 << 10);

  /**
   * the maximum length of the table; if there are more entries, the worse
   * values will be merged
   */
  private int m_maxSize;

  /** the objective values / tour lengths */
  private transient long[] m_tourLengths;

  /** the counters */
  private transient long[] m_count;

  /** the current table size */
  private transient int m_size;

  /** the constructor */
  public FFA() {
    super("FFA"); //$NON-NLS-1$

    this.m_tourLengths = new long[FFA.DEFAULT_INIT_TABLE_SIZE];
    this.m_count = new long[FFA.DEFAULT_INIT_TABLE_SIZE];
    this.m_maxSize = FFA.DEFAULT_MAX_TABLE_LENGTH;
  }

  /** {@inheritDoc} */
  @Override
  public final void assignFitness(final Individual<?>[] pop,
      final ObjectiveFunction f) {
    final int maxSize;
    int size, nsize, i, v;
    long[] table, otable, count, ocount;

    // high is the upper limit of the binary search
    size = this.m_size;
    table = this.m_tourLengths;
    count = this.m_count;
    maxSize = this.m_maxSize;

    // first round: update the table
    for (final Individual<?> ind : pop) {

      // find index of a given tour length (and whether it is already in
      // the
      // table)
      i = Arrays.binarySearch(table, 0, size, ind.tourLength);

      if (i < 0) {
        // if we get here, the tour length is not in the table
        i = (-(i + 1)); // calculate insertion point

        if (size < maxSize) {

          otable = table;
          ocount = count;

          // we did not yet reach the maximum table entry limit: we
          // can add a
          // new entry
          if (size >= table.length) {
            // but we need to allocate a larger array, best of size
            // "nsize"
            nsize = Math.min((size << 1), maxSize);

            table = new long[nsize]; // allocate lookup table
            if (i > 0) {// copy entries before new entry, if any
              System.arraycopy(otable, 0, table, 0, i - 1);
            }

            count = new long[nsize]; // allocate new counters
            if (i > 0) {// copy counters before new entry, if any
              System.arraycopy(ocount, 0, count, 0, i - 1);
            }
          }

          // shift lookup entries 1 to the right
          System.arraycopy(otable, i, table, i + 1, size - i);
          table[i] = ind.tourLength;// store new value

          // shift counters to the right
          System.arraycopy(ocount, i, count, i + 1, size - i);
          count[i] = 1;// store 1 as initial counter value

          size = (size + 1); // increase size
        } else {
          // darn, the frequency table has reached its maximum size
          nsize = (size - 1);

          findPos: {// we break findPos if the new tour length must
            // not be
            // stored

            if (i >= nsize) {
              // the new entry would either be after the end or
              // replaces the
              // current end
              count[nsize]++;// in any case, we increase the last
              // counter
              if (i >= size) {
                // if entry is after end, we don't need to store
                // it
                break findPos;
              }
            } else {
              // ok, the new entry is somewhere in our range, so
              // we need to
              // shift the other entries
              v = (i + 1);
              // first, all table entries move one step to the
              // right
              System.arraycopy(table, i, table, v, size - v);
              // the counter values of the last two entries are
              // added up
              count[nsize] += count[nsize - 1];
              // and then the other entries move one to the right,
              // but do not
              // override that value
              System.arraycopy(count, i, count, v, nsize - v);
              count[i] = 1;
            }

            // store the new table entry
            table[i] = ind.tourLength;
          }

        }
      } else {// ok, the tour length is in the table: increment its
        // counter
        count[i]++;
      }
    }

    // now set the fitness values
    for (final Individual<?> ind : pop) {
      i = Arrays.binarySearch(table, 0, size, ind.tourLength);
      if (i < 0) {
        ind.f = count[size - 1];
      } else {
        ind.f = count[i];
      }
    }

    this.m_tourLengths = table;
    this.m_count = count;
    this.m_size = size;
  }

  /** {@inheritDoc} */
  @Override
  public FFA clone() {
    FFA x;

    x = ((FFA) (super.clone()));
    x.m_tourLengths = new long[FFA.DEFAULT_INIT_TABLE_SIZE];
    x.m_count = new long[FFA.DEFAULT_INIT_TABLE_SIZE];
    x.m_size = 0;
    return x;
  }

  /** {@inheritDoc} */
  @Override
  public final void configure(final Configuration config) {
    super.configure(config);

    this.m_maxSize = config.getInt(FFA.PARAM_MAX_TABLE_LENGTH, 1, 8,
        Integer.MAX_VALUE);
  }

  /** {@inheritDoc} */
  @Override
  public final void printConfiguration(final PrintStream ps) {
    super.printConfiguration(ps);

    Configurable.printKey(FFA.PARAM_MAX_TABLE_LENGTH, ps);
    ps.println(this.m_maxSize);
  }

  /** {@inheritDoc} */
  @Override
  public final void printParameters(final PrintStream ps) {
    super.printParameters(ps);

    Configurable.printKey(FFA.PARAM_MAX_TABLE_LENGTH, ps);
    ps.println(//
    "the maximum number of entries in the FFA table"); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  public void beginRun(final ObjectiveFunction f) {
    this.m_size = 0;
    super.beginRun(f);
  }
}
