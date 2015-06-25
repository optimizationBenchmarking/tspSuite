package org.logisticPlanning.utils.math.statistics;

/**
 * <p>
 * A class for computing <a
 * href="https://en.wikipedia.org/wiki/Quantile">quantiles</a>&nbsp;[<a
 * href="#cite_BB2011USCAM" style="font-weight:bold">1</a>].
 * </p>
 * <p>
 * Generally, a {@code q}-quantile family divides a list sorted list of
 * data into {@code q} equally-sized consecutive chunks. The value of the
 * {@code k} <sup>th</sup> {@code q}-quantile is the value of the data
 * element exactly on the border between the {@code k}<sup>th</sup> and
 * {code (k+1)}<sup>th</sup> such chunk.
 * </p>
 * <p>
 * For example, the first ({@code k=1}) {@code 2}-quantile emerges if we
 * divide a data set into exactly two consecutive equally-sized chunks and
 * pick the value a the position where these two chunks touch. This is the
 * value exactly in the middle of the data set, i.e., the median.
 * </p>
 * <p>
 * The concept of the {@code k}<sup>th</sup> {@code q}-quantile can be
 * generalized by using real values {@code z} such that {@code z=k/q}. A
 * data set of length {@code count} then has about {@code z} values which
 * are smaller than its {@code z}-quantile and about {@code (1-z)} which
 * are larger.
 * </p>
 * <p>
 * This quantity can also be used to describe random variables: When
 * sampling a random variable, the probability to obtain a result larger
 * than its {@code z} -quantile (for arbitrary {@code z}) is at most
 * {@code (1-z)}. Vice versa, the probability to get a smaller value is at
 * most {@code z}.
 * </p>
 * <p>
 * Quantiles are very useful statistics, as they do not make any a-priory
 * assumption about the stochastic distribution of data and still allow us
 * to make meaningful statements about values. For instance, we know that
 * 50% of the data in a sample is between the {@code 0.25}-quantile and the
 * {@code 0.75} -quantile of the sample. It does not matter whether this
 * data is uniformly or normally distributed, the statement holds all the
 * same. The standard deviation allows us to make a statement like "About
 * 68% of the samples of a <emph>normally distributed</emph> process lie
 * between its mean &#xB1; standard deviation &ndash; but this holds only
 * for normally distributed processes.
 * </p>
 * <p>
 * When computing a {@code z}-quantile from a data sample with
 * {@code count}, care must be taken when {@code z*count} is no proper
 * integer. In this case, we use a linear interpolation in this class, as
 * can be seen in the code.
 * </p>
 * <p>
 * This implementation here is compatible with Apache Common's Math
 * package&nbsp;[<a href="#cite_A2013CMTACML"
 * style="font-weight:bold">2</a>].
 * </p>
 * <h2>References</h2>
 * <ol>
 * <li><div><span id="cite_BB2011USCAM" />Charles Henry Brase
 * and&nbsp;Corrinne Pellillo Brase: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Understandable
 * Statistics: Concepts and Methods,&rdquo;</span> 2011, Stamford, CT, USA:
 * Cengage Learning (Wadsworth Publishing Company). ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0840048386">0840048386</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780840048387">9780840048387</a>;
 * Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=m8rYUEWQx00C"
 * >m8rYUEWQx00C</a></div></li>
 * <li><div><span id="cite_A2013CMTACML" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;Commons Math: The
 * Apache Commons Mathematics Library,&rdquo;</span> (Website),
 * April&nbsp;7, 2013, Forest Hill, MD, USA: Apache Software Foundation.
 * <div>link: [<a
 * href="https://commons.apache.org/proper/commons-math/">1</
 * a>]</div></div></li>
 * </ol>
 *
 *
 @author <em><a href="http://www.it-weise.de/">Thomas Weise</a></em>,
 *         Email:&nbsp;<a
 *         href="mailto:tweise@ustc.edu.cn">tweise@ustc.edu.cn</a>
 */
public final class Quantile {

  /** error if no data is found */
  private static final String NO_DATA_ERROR = "Number of data elements must be greater than zero."; //$NON-NLS-1$

  /**
   * Get the given quantile from a sorted list of {@code byte} values. The
   * value {@code V} of a given quantile {@code quantile} is such that an
   * approximate fraction of {@code quantile*count} elements in the list is
   * smaller then {@code V} and approximately {@code (1-quantile)*count}
   * elements are larger than {@code Q}.
   *
   * @param sortedList
   *          the sorted list of {@code byte} values
   * @param start
   *          the start index
   * @param count
   *          the number of elements to consider
   * @param quantile
   *          the quantile-defining value {@code z}, in {@code [0,1]},
   *          {@code 0.5} is the median
   * @return the quantile
   */
  public static final double getQuantile(final byte[] sortedList,
      final int start, final int count, final double quantile) {
    final double low, pos, v;
    final byte upper, lower;
    final int intpos;

    if (count <= 0) {
      throw new IllegalArgumentException(Quantile.NO_DATA_ERROR);
    }
    if (count <= 1) {
      return sortedList[start];
    }

    if (quantile <= 0d) {
      return sortedList[start];
    }
    if (quantile >= 1d) {
      return sortedList[(start + count) - 1];
    }

    pos = (quantile * (count + 1));
    if (pos < 1d) {
      return sortedList[start];
    }
    if (pos >= count) {
      return sortedList[(start + count) - 1];
    }

    low = Math.floor(pos);
    intpos = (((int) low) + start);
    lower = sortedList[intpos - 1];

    v = (pos - low);
    if (v <= 0d) {
      return lower;
    }

    upper = sortedList[intpos];
    if (upper <= lower) {
      return lower;
    }
    return Math.max(lower,
        Math.min(upper, (lower + (v * (upper - lower)))));
  }

  /**
   * Get the given quantile from a sorted list of {@code short} values. The
   * value {@code V} of a given quantile {@code quantile} is such that an
   * approximate fraction of {@code quantile*count} elements in the list is
   * smaller then {@code V} and approximately {@code (1-quantile)*count}
   * elements are larger than {@code Q}.
   *
   * @param sortedList
   *          the sorted list of {@code short} values
   * @param start
   *          the start index
   * @param count
   *          the number of elements to consider
   * @param quantile
   *          the quantile-defining value {@code z}, in {@code [0,1]},
   *          {@code 0.5} is the median
   * @return the quantile
   */
  public static final double getQuantile(final short[] sortedList,
      final int start, final int count, final double quantile) {
    final double low, pos, v;
    final short upper, lower;
    final int intpos;

    if (count <= 0) {
      throw new IllegalArgumentException(Quantile.NO_DATA_ERROR);
    }
    if (count <= 1) {
      return sortedList[start];
    }

    if (quantile <= 0d) {
      return sortedList[start];
    }
    if (quantile >= 1d) {
      return sortedList[(start + count) - 1];
    }

    pos = (quantile * (count + 1));
    if (pos < 1d) {
      return sortedList[start];
    }
    if (pos >= count) {
      return sortedList[(start + count) - 1];
    }

    low = Math.floor(pos);
    intpos = (((int) low) + start);
    lower = sortedList[intpos - 1];

    v = (pos - low);
    if (v <= 0d) {
      return lower;
    }

    upper = sortedList[intpos];
    if (upper <= lower) {
      return lower;
    }
    return Math.max(lower,
        Math.min(upper, (lower + (v * (upper - lower)))));
  }

  /**
   * Get the given quantile from a sorted list of {@code int} values. The
   * value {@code V} of a given quantile {@code quantile} is such that an
   * approximate fraction of {@code quantile*count} elements in the list is
   * smaller then {@code V} and approximately {@code (1-quantile)*count}
   * elements are larger than {@code Q}.
   *
   * @param sortedList
   *          the sorted list of {@code int} values
   * @param start
   *          the start index
   * @param count
   *          the number of elements to consider
   * @param quantile
   *          the quantile-defining value {@code z}, in {@code [0,1]},
   *          {@code 0.5} is the median
   * @return the quantile
   */
  public static final double getQuantile(final int[] sortedList,
      final int start, final int count, final double quantile) {
    final double low, pos, v;
    final int upper, lower;
    final int intpos;

    if (count <= 0) {
      throw new IllegalArgumentException(Quantile.NO_DATA_ERROR);
    }
    if (count <= 1) {
      return sortedList[start];
    }

    if (quantile <= 0d) {
      return sortedList[start];
    }
    if (quantile >= 1d) {
      return sortedList[(start + count) - 1];
    }

    pos = (quantile * (count + 1));
    if (pos < 1d) {
      return sortedList[start];
    }
    if (pos >= count) {
      return sortedList[(start + count) - 1];
    }

    low = Math.floor(pos);
    intpos = (((int) low) + start);
    lower = sortedList[intpos - 1];

    v = (pos - low);
    if (v <= 0d) {
      return lower;
    }

    upper = sortedList[intpos];
    if (upper <= lower) {
      return lower;
    }
    return Math.max(lower,
        Math.min(upper, (lower + (v * (upper - lower)))));
  }

  /**
   * Get the given quantile from a sorted list of {@code long} values. The
   * value {@code V} of a given quantile {@code quantile} is such that an
   * approximate fraction of {@code quantile*count} elements in the list is
   * smaller then {@code V} and approximately {@code (1-quantile)*count}
   * elements are larger than {@code Q}.
   *
   * @param sortedList
   *          the sorted list of {@code long} values
   * @param start
   *          the start index
   * @param count
   *          the number of elements to consider
   * @param quantile
   *          the quantile-defining value {@code z}, in {@code [0,1]},
   *          {@code 0.5} is the median
   * @return the quantile
   */
  public static final double getQuantile(final long[] sortedList,
      final int start, final int count, final double quantile) {
    final double low, pos, v;
    final long upper, lower;
    final int intpos;

    if (count <= 0) {
      throw new IllegalArgumentException(Quantile.NO_DATA_ERROR);
    }
    if (count <= 1) {
      return sortedList[start];
    }

    if (quantile <= 0d) {
      return sortedList[start];
    }
    if (quantile >= 1d) {
      return sortedList[(start + count) - 1];
    }

    pos = (quantile * (count + 1));
    if (pos < 1d) {
      return sortedList[start];
    }
    if (pos >= count) {
      return sortedList[(start + count) - 1];
    }

    low = Math.floor(pos);
    intpos = (((int) low) + start);
    lower = sortedList[intpos - 1];

    v = (pos - low);
    if (v <= 0d) {
      return lower;
    }

    upper = sortedList[intpos];
    if (upper <= lower) {
      return lower;
    }
    return Math.max(lower,
        Math.min(upper, (lower + (v * (upper - lower)))));
  }

  /**
   * Get the given quantile from a sorted list of {@code float} values. The
   * value {@code V} of a given quantile {@code quantile} is such that an
   * approximate fraction of {@code quantile*count} elements in the list is
   * smaller then {@code V} and approximately {@code (1-quantile)*count}
   * elements are larger than {@code Q}.
   *
   * @param sortedList
   *          the sorted list of {@code float} values
   * @param start
   *          the start index
   * @param count
   *          the number of elements to consider
   * @param quantile
   *          the quantile-defining value {@code z}, in {@code [0,1]},
   *          {@code 0.5} is the median
   * @return the quantile
   */
  public static final double getQuantile(final float[] sortedList,
      final int start, final int count, final double quantile) {
    final double low, pos, v;
    final float upper, lower;
    final int intpos;

    if (count <= 0) {
      throw new IllegalArgumentException(Quantile.NO_DATA_ERROR);
    }
    if (count <= 1) {
      return sortedList[start];
    }

    if (quantile <= 0d) {
      return sortedList[start];
    }
    if (quantile >= 1d) {
      return sortedList[(start + count) - 1];
    }

    pos = (quantile * (count + 1));
    if (pos < 1d) {
      return sortedList[start];
    }
    if (pos >= count) {
      return sortedList[(start + count) - 1];
    }

    low = Math.floor(pos);
    intpos = (((int) low) + start);
    lower = sortedList[intpos - 1];

    v = (pos - low);
    if (v <= 0d) {
      return lower;
    }

    upper = sortedList[intpos];
    if (upper <= lower) {
      return lower;
    }
    return Math.max(lower,
        Math.min(upper, (lower + (v * (upper - lower)))));
  }

  /**
   * Get the given quantile from a sorted list of {@code double} values.
   * The value {@code V} of a given quantile {@code quantile} is such that
   * an approximate fraction of {@code quantile*count} elements in the list
   * is smaller then {@code V} and approximately {@code (1-quantile)*count}
   * elements are larger than {@code Q}.
   *
   * @param sortedList
   *          the sorted list of {@code double} values
   * @param start
   *          the start index
   * @param count
   *          the number of elements to consider
   * @param quantile
   *          the quantile-defining value {@code z}, in {@code [0,1]},
   *          {@code 0.5} is the median
   * @return the quantile
   */
  public static final double getQuantile(final double[] sortedList,
      final int start, final int count, final double quantile) {
    final double low, pos, v;
    final double upper, lower;
    final int intpos;

    if (count <= 0) {
      throw new IllegalArgumentException(Quantile.NO_DATA_ERROR);
    }
    if (count <= 1) {
      return sortedList[start];
    }

    if (quantile <= 0d) {
      return sortedList[start];
    }
    if (quantile >= 1d) {
      return sortedList[(start + count) - 1];
    }

    pos = (quantile * (count + 1));
    if (pos < 1d) {
      return sortedList[start];
    }
    if (pos >= count) {
      return sortedList[(start + count) - 1];
    }

    low = Math.floor(pos);
    intpos = (((int) low) + start);
    lower = sortedList[intpos - 1];

    v = (pos - low);
    if (v <= 0d) {
      return lower;
    }

    upper = sortedList[intpos];
    if (upper <= lower) {
      return lower;
    }
    return Math.max(lower,
        Math.min(upper, (lower + (v * (upper - lower)))));
  }

}
