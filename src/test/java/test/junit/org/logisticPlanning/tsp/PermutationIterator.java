package test.junit.org.logisticPlanning.tsp;

import java.util.NoSuchElementException;

import org.logisticPlanning.tsp.solving.operators.permutation.creation.PermutationCreateCanonical;
import org.logisticPlanning.utils.collections.basic.BasicIterator;
import org.logisticPlanning.utils.math.random.Randomizer;

/**
 * <p>
 * An iterator for permutations of increasing size. The implementation here
 * uses <a href=
 * "http://en.wikipedia.org/wiki/Steinhaus-Johnson-Trotter_algorithm#Even.27s_speedup"
 * >Even's Speedup</a>&nbsp;[<a href="#cite_E1973AC"
 * style="font-weight:bold">1</a>] of the <a href=
 * "http://en.wikipedia.org/wiki/Steinhaus-Johnson-Trotter_algorithm"
 * >Steinhaus-Johnson-Trotter Algorithm</a>&nbsp;[<a
 * href="#cite_J1963GOPBAT" style="font-weight:bold">2</a>, <a
 * href="#cite_S1977PGM" style="font-weight:bold">3</a>]. Even's Speedup
 * allows us to get the next permutation from the current permutation by
 * performing a single, adjacent swap (i.e., exchanging two neighboring
 * cities).
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
public final class PermutationIterator extends BasicIterator<int[]> {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the internal data */
  private int[] m_data;

  /** the permutation */
  private int[] m_perm;

  /** the ranks */
  private int[] m_ranks;

  /** should we auto-increase the permutation size? */
  private final boolean m_autoIncrease;
  /**
   * the randomizer for shuffling the permutations, or {@code null} if none
   * needed
   */
  private final Randomizer m_r;

  /** do we have a next permutation? */
  private int m_hasNext;

  /**
   * instantiate
   * 
   * @param n
   *          the start n
   * @param autoIncrease
   *          if this is {@code true}, the permutation size will increase
   *          automatically
   * @param r
   *          the randomizer for shuffling the permutations, or
   *          {@code null} if none needed
   */
  public PermutationIterator(final int n, final boolean autoIncrease,
      final Randomizer r) {
    super();
    this.m_r = r;
    this.m_autoIncrease = autoIncrease;
    this.__setup(n);
  }

  /**
   * setup with a given {@code n}.
   * 
   * @param n
   *          size of the permutation
   */
  private final void __setup(final int n) {
    final int halfN;
    final int[] perm, ranks, data;
    int i, j;

    halfN = (n >> 1);

    // allocate the data structures

    this.m_perm = perm = new int[n];
    this.m_ranks = ranks = new int[i = (n + halfN)];
    this.m_data = data = new int[i << 1];
    if (this.m_r != null) {
      this.m_r.shuffle(perm, 0, n);
    }

    // ok, data structures are allocated, let's begin
    PermutationCreateCanonical.makeCanonical(ranks, n);
    PermutationCreateCanonical.makeCanonical(perm, n);

    // initialize: data holds pairs [(index, direction), (index,
    // direction)]
    for (j = i = 0; j < n; j++) {
      data[i++] = j;
      data[i++] = (-1);
    }
    data[1] = 0;

    this.m_hasNext = 0;
  }

  /** reset the iterator */
  public final void reset() {
    this.__setup(this.m_perm.length);
  }

  /** {@inheritDoc} */
  @Override
  public final boolean hasNext() {
    final int n, halfN, twoN;
    final int[] perm, data, ranks;
    int i, j, direction, chosenIndex, swapIndex, chosenValue, swapValue, indexBefore, indexAfter, first;

    switch (this.m_hasNext) {
      case 0: {
        return true;
      }
      case 1: {
        perm = this.m_perm;
        data = this.m_data;
        ranks = this.m_ranks;
        n = perm.length;
        halfN = (n >> 1);
        twoN = (n << 1);

        choose: {
          // find largest element with direction != 0
          for (i = (twoN - 1); i > 0; i -= 2) {
            direction = data[i]; // directions are at odd indices
            if (direction != 0) {
              break choose;
            }
          }
          // no such element? quit

          if (this.m_autoIncrease) {
            this.__setup(n + 1);
            return this.hasNext();
          }
          this.m_hasNext = 2;
          return false;
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
        // we also need the elements before and after in order to update
        // the
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

          first = perm[chosenIndex];
          perm[chosenIndex] = perm[swapIndex];
          perm[swapIndex] = first;
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

          first = perm[swapIndex];
          perm[swapIndex] = perm[chosenIndex];
          perm[chosenIndex] = first;
        }

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

        // update the directions of all elements larger that the chosen
        // one;
        for (i += 2; i < twoN;) {
          j = data[i++]; // index of that element
          // If the element is closer to the start, it should move to
          // higher
          // indices. Otherwise, it should move towards the start
          data[i++] = ((j < halfN) ? (+1) : (-1));
        }

        this.m_hasNext = 0;
        return true;
      }
      default: {
        return false;
      }
    }
  }

  /** {@inheritDoc} */
  @Override
  public final int[] next() {
    if (this.hasNext()) {
      this.m_hasNext = 1;
      return this.m_perm;
    }
    throw new NoSuchElementException();
  }
}
