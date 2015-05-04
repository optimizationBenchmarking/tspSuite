package org.logisticPlanning.tsp.solving.algorithms.exact.exhaustiveEnumeration;

import org.logisticPlanning.tsp.benchmarking.instances.Instance;
import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;
import org.logisticPlanning.tsp.solving.TSPAlgorithm;
import org.logisticPlanning.tsp.solving.TSPAlgorithmRunner;
import org.logisticPlanning.tsp.solving.operators.permutation.creation.PermutationCreateCanonical;

/**
 * <p>
 * This algorithm starts with a random permutation and tries to enumerates
 * all possible permutations one by one. If given enough time, it will
 * therefore always find the globally optimal solution. The implementation
 * here uses <a href=
 * "http://en.wikipedia.org/wiki/Steinhaus-Johnson-Trotter_algorithm#Even.27s_speedup"
 * >Even's Speedup</a>&nbsp;[<a href="#cite_E1973AC"
 * style="font-weight:bold">1</a>] of the <a href=
 * "http://en.wikipedia.org/wiki/Steinhaus-Johnson-Trotter_algorithm"
 * >Steinhaus-Johnson-Trotter Algorithm</a>&nbsp;[<a
 * href="#cite_J1963GOPBAT" style="font-weight:bold">2</a>, <a
 * href="#cite_S1977PGM" style="font-weight:bold">3</a>]. Even's Speedup
 * allows us to get the next permutation from the current permutation by
 * performing a single, adjacent swap (i.e., exchanging two neighboring
 * cities). This allows us to update the total tour length with only six
 * distance computations.
 * </p>
 * <p>
 * Due to the locality and orderedness of the changes, it can be expected
 * that this algorithm's performance strongly depends on the initial
 * solution and that it will not be good (not good at all&hellip;).
 * </p>
 * <h2>References</h2>
 * <ol>
 * <li><div><span id="cite_E1973AC" /><a
 * href="http://en.wikipedia.org/wiki/Shimon_Even">Shimon Even</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Algorithmic
 * Combinatorics,&rdquo;</span> 1973, New York, NY, USA: Macmillan
 * Publishers Co.. OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/589026">589026</a>; Google Book
 * ID:&nbsp;<a
 * href="http://books.google.com/books?id=AcE-AAAAIAAJ">AcE-AAAAIAAJ</a>;
 * ASIN:&nbsp;<a href="http://www.amazon.com/dp/B00393TCP4">B00393TCP4</a>
 * and&nbsp;<a
 * href="http://www.amazon.com/dp/B000NZSJ8M">B000NZSJ8M</a></div></li>
 * <li><div><span id="cite_J1963GOPBAT" /><a
 * href="http://en.wikipedia.org/wiki/Selmer_M._Johnson">Selmer Martin
 * Johnson</a>: <span style="font-weight:bold">&ldquo;Generation of
 * Permutations by Adjacent Transposition,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Mathematics of
 * Computation</span> 17(83):282&ndash;285, July&nbsp;1963; published by
 * Providence, RI, USA: American Mathematical Society (AMS). doi:&nbsp;<a
 * href="http://dx.doi.org/10.1090/S0025-5718-1963-0159764-2"
 * >10.1090/S0025-5718-1963-0159764-2</a>; JSTOR stable:&nbsp;<a
 * href="http://www.jstor.org/stable/2003846">2003846</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/00255718">0025-5718</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/10886842">1088-6842</a></div></li>
 * <li><div><span id="cite_S1977PGM" /><a
 * href="http://www.cs.princeton.edu/~rs/">Robert Sedgewick</a>: <span
 * style="font-weight:bold">&ldquo;Permutation Generation
 * Methods,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">ACM Computing Surveys
 * (CSUR)</span> 9(2):137&ndash;164, June&nbsp;1977; published by New York,
 * NY, USA: ACM Press. doi:&nbsp;<a
 * href="http://dx.doi.org/10.1145/356689.356692"
 * >10.1145/356689.356692</a>; ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/03600300">0360-0300</a>;
 * CODEN:&nbsp;<a href=
 * "http://www-library.desy.de/cgi-bin/spiface/find/coden/www?code=CMSVAN"
 * >CMSVAN</a></div></li>
 * </ol>
 */
public final class ExhaustivePermutationIteration extends TSPAlgorithm {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the internal data */
  private transient int[] m_data;

  /** the permutation */
  private transient int[] m_perm;

  /** the ranks */
  private transient int[] m_ranks;

  /** instantiate */
  public ExhaustivePermutationIteration() {
    super("Exhaustice Permutation Enumeration"); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  public ExhaustivePermutationIteration clone() {
    ExhaustivePermutationIteration c;

    c = ((ExhaustivePermutationIteration) (super.clone()));
    c.m_data = null;
    c.m_perm = null;
    c.m_ranks = null;

    return c;
  }

  /** {@inheritDoc} */
  @Override
  public void solve(final ObjectiveFunction f) {
    final int n, halfN, twoN;
    final int[] perm, data, ranks;
    int i, j, direction, chosenIndex, swapIndex, chosenValue, swapValue, indexBefore, indexAfter, elementBefore, elementAfter, first, second;
    long tourLength;

    n = f.n();
    halfN = (n >> 1);
    twoN = (n << 1); // BUG: This was a bug until 0.9.8, it was (halfN <<
    // 1);

    perm = this.m_perm;
    data = this.m_data;
    ranks = this.m_ranks;

    // ok, data structures are allocated, let's begin
    PermutationCreateCanonical.makeCanonical(ranks, n);
    PermutationCreateCanonical.makeCanonical(perm, n);
    f.getRandom().shuffle(perm, 0, n);

    // get the length of the initial permutation
    tourLength = f.evaluate(perm);

    // initialize: data holds pairs [(index, direction), (index,
    // direction)]
    for (j = i = 0; j < n; j++) {
      data[i++] = j;
      data[i++] = (-1);
    }
    data[1] = 0;

    while (!(f.shouldTerminate())) {

      choose: {
        // find largest element with direction != 0
        for (i = (twoN - 1); i > 0; i -= 2) {
          direction = data[i]; // directions are at odd indices
          if (direction != 0) {
            break choose;
          }
        }
        // no such element? quit
        return;
      }

      // swap the element at index (data[i-1]) with the element at index
      // (data[i-1]+direction)

      // indices are at even indices, i now points to index
      chosenIndex = data[--i];
      swapIndex = (chosenIndex + direction);
      chosenValue = ranks[chosenIndex];
      ranks[chosenIndex] = swapValue = ranks[swapIndex];
      ranks[swapIndex] = chosenValue;

      // Now we do the same swap in the actual solution, but this means
      // that
      // we also need the elements before and after in order to update the
      // distance appropriately.
      if (direction > 0) {
        // direction>0: chosenIndex < swapIndex
        indexBefore = (chosenIndex - 1);
        if (indexBefore < 0) {
          indexBefore = (n - 1);
        }
        indexAfter = (swapIndex + 1);
        if (indexAfter >= n) {
          indexAfter = 0;
        }

        elementBefore = perm[indexBefore];
        first = perm[chosenIndex];
        perm[chosenIndex] = second = perm[swapIndex];
        perm[swapIndex] = first;

        elementAfter = perm[indexAfter];
      } else {
        // direction<0: chosenIndex > swapIndex
        indexBefore = (swapIndex - 1);
        if (indexBefore < 0) {
          indexBefore = (n - 1);
        }
        indexAfter = (chosenIndex + 1);
        if (indexAfter >= n) {
          indexAfter = 0;
        }

        elementBefore = perm[indexBefore];
        first = perm[swapIndex];
        perm[swapIndex] = second = perm[chosenIndex];
        perm[chosenIndex] = first;

        elementAfter = perm[indexAfter];
      }

      // update tour length
      tourLength += (f.distance(elementBefore, second)//
          + f.distance(second, first)//
      + f.distance(first, elementAfter)//
          )
          - f.distance(elementBefore, first)//
          - f.distance(first, second)//
          - f.distance(second, elementAfter);
      f.registerFE(perm, tourLength);

      // update positions: data[i] is index of chosen element; now =
      // swapIndex
      data[i] = swapIndex;
      // index of swap element is at (swapValue-1)*2, now chosenIndex
      data[(swapValue - 1) << 1] = chosenIndex;

      // if we reached one end of the permutation or found a larger
      // element,
      // set direction to zero
      if ((swapIndex <= 0) || (swapIndex >= (n - 1))
          || (ranks[swapIndex + direction] > chosenValue)) {
        data[i + 1] = 0;// set direction to zero
      }

      // update the directions of all elements larger that the chosen one;
      for (i += 2; i < twoN;) {
        j = data[i++]; // index of that element
        // If the element is closer to the start, it should move to
        // higher
        // indices. Otherwise, it should move towards the start
        data[i++] = ((j < halfN) ? (+1) : (-1));
      }
    }
  }

  /**
   * Perform the exhaustive permutation enumeration heuristic
   *
   * @param args
   *          the command line arguments
   */
  public final static void main(final String[] args) {
    TSPAlgorithmRunner.benchmark(//
        Instance.ALL_INSTANCES,//
        ExhaustivePermutationIteration.class,//
        args);
  }

  /** {@inheritDoc} */
  @Override
  public void beginRun(final ObjectiveFunction f) {
    final int n, halfN;

    super.beginRun(f);

    n = f.n();
    halfN = (n >> 1);

    // allocate the data structures
    this.m_perm = new int[n];
    this.m_ranks = new int[n + halfN];
    this.m_data = new int[n << 1];
  }

  /** {@inheritDoc} */
  @Override
  public void endRun(final ObjectiveFunction f) {
    this.m_data = null;
    this.m_perm = null;
    this.m_ranks = null;
    super.endRun(f);
  }
}
