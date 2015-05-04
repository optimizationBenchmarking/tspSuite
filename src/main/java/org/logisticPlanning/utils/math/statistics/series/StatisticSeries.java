package org.logisticPlanning.utils.math.statistics.series;

import java.util.Arrays;

import org.logisticPlanning.utils.collections.lists.ArraySetView;
import org.logisticPlanning.utils.math.data.collection.ArrayDataCollectionView;
import org.logisticPlanning.utils.math.data.collection.IDataCollection;
import org.logisticPlanning.utils.math.statistics.EStatisticParameter;
import org.logisticPlanning.utils.math.statistics.IStatisticDataCollection;
import org.logisticPlanning.utils.math.statistics.aggregates.OrderStatisticInfo;
import org.logisticPlanning.utils.utils.EmptyUtils;
import org.logisticPlanning.utils.utils.comparison.EComparison;

/**
 * <p>
 * A statistic run is a run that represents the development of one
 * coordinate of a set of {@code n} data collections (let's call it
 * {@code y}) over another one (let's call it {@code x}). At each value for
 * {@code x}, there exist up to {@code n} values for {@code y}. A statistic
 * series represents statistics over these values, their median, mean, etc.
 * </p>
 * <p>
 * By default, the first {@code x} coordinate in the series is the one
 * where all {@code n} data collections have a value for (but you can also
 * start at the smallest {@code x}-coordinate over all collections). The
 * statistic series ends at the highest {@code x}-coordinate over all
 * collections. If a collection has no data there, the {@code y}-value of
 * its last point is used. This policy is generally applied: The {@code y}
 * -value of a collection for a given value {@code v} of {@code x} is
 * always the {@code y} value of the point with the largest {@code x}
 * -coordinate that is less or equal to {@code v} in the collection.
 * </p>
 * <p>
 * Statistic series are useful to compress and analyze data from multiple
 * time series. Say you measure the performance of an &quot;anytime
 * optimization algorithm&quot; over time. You can perform several runs,
 * and for each run you may collect multiple measure points with the best
 * solution quality discovered by the algorithm in the given run at
 * different points in time. You then may want to ask a question like:
 * &quot;What is the median solution quality after 100 seconds of
 * runtime?&quot; A statistic series answers exactly this question.
 * </p>
 */
public class StatisticSeries extends ArrayDataCollectionView implements
IStatisticDataCollection {

  /** the list of supported statistic parameters */
  private static final ArraySetView<EStatisticParameter> LIST = EStatisticParameter
      .makeList(EStatisticParameter.MINIMUM,
          EStatisticParameter.PERCENTILE_05,
          EStatisticParameter.PERCENTILE_25, EStatisticParameter.MEDIAN,
          EStatisticParameter.ARITHMETIC_MEAN,
          EStatisticParameter.PERCENTILE_75,
          EStatisticParameter.PERCENTILE_95, EStatisticParameter.MAXIMUM,
          EStatisticParameter.VARIANCE, EStatisticParameter.COUNT);

  /** the min-y-dimension */
  public static final int DIM_Y_MIN = StatisticSeries.LIST
      .indexOf(EStatisticParameter.MINIMUM);
  /** the 0.05-quantile of the y-dimension */
  public static final int DIM_Y_Q05 = StatisticSeries.LIST
      .indexOf(EStatisticParameter.PERCENTILE_05);
  /** the 0.25-quantile of the y-dimension */
  public static final int DIM_Y_Q25 = StatisticSeries.LIST
      .indexOf(EStatisticParameter.PERCENTILE_25);
  /** the median of the y-dimension */
  public static final int DIM_Y_MEDIAN = StatisticSeries.LIST
      .indexOf(EStatisticParameter.MEDIAN);
  /** the mean of the y-dimension */
  public static final int DIM_Y_MEAN = StatisticSeries.LIST
      .indexOf(EStatisticParameter.ARITHMETIC_MEAN);
  /** the 0.75-quantile of the y-dimension */
  public static final int DIM_Y_Q75 = StatisticSeries.LIST
      .indexOf(EStatisticParameter.PERCENTILE_75);
  /** the 0.95-quantile of the y-dimension */
  public static final int DIM_Y_Q95 = StatisticSeries.LIST
      .indexOf(EStatisticParameter.PERCENTILE_95);
  /** the maximum-y-dimension */
  public static final int DIM_Y_MAX = StatisticSeries.LIST
      .indexOf(EStatisticParameter.MAXIMUM);
  /** the y-variance dimension */
  public static final int DIM_Y_VARIANCE = StatisticSeries.LIST
      .indexOf(EStatisticParameter.VARIANCE);
  /** the number of elements in the point */
  public static final int DIM_COUNT = StatisticSeries.LIST
      .indexOf(EStatisticParameter.COUNT);
  /** the x-dimension */
  public static final int DIM_X = StatisticSeries.LIST.size();

  /** the dimension of the statistic series */
  public static final int STATISTIC_SERIES_DIM = (StatisticSeries.DIM_X + 1);

  /**
   * instantiate
   *
   * @param data
   *          the data
   */
  private StatisticSeries(final double[] data) {
    super(data, (data.length / StatisticSeries.STATISTIC_SERIES_DIM),
        StatisticSeries.STATISTIC_SERIES_DIM);
  }

  /**
   * Create the statistic series
   *
   * @param source
   *          the source array
   * @param dimX
   *          the indexing dimension - the values in this dimension must be
   *          monotonously increasing for each dimension
   * @param dimY
   *          the data value dimension over which the statistics will be
   *          computed
   */
  public StatisticSeries(final IDataCollection[] source, final int dimX,
      final int dimY) {
    this(source, dimX, dimY, -1);
  }

  /**
   * Create the statistic series
   *
   * @param source
   *          the source array
   * @param dimX
   *          the indexing dimension - the values in this dimension must be
   *          monotonously increasing for each dimension
   * @param dimY
   *          the data value dimension over which the statistics will be
   *          computed
   * @param approxMaxPoints
   *          the approximate number of points allowed along the x-axis,
   *          {@code -1} for unlimited
   */
  public StatisticSeries(final IDataCollection[] source, final int dimX,
      final int dimY, final int approxMaxPoints) {
    this(source, dimX, dimY, approxMaxPoints, true);
  }

  /**
   * Create the statistic series
   *
   * @param source
   *          the source array
   * @param dimX
   *          the indexing dimension - the values in this dimension must be
   *          monotonously increasing for each dimension
   * @param dimY
   *          the data value dimension over which the statistics will be
   *          computed
   * @param approxMaxPoints
   *          the approximate number of points allowed along the x-axis,
   *          {@code -1} for unlimited
   * @param beginWhenDataIsThereForAllCollections
   *          begin the series at the first point where data exists from
   *          all collections
   */
  public StatisticSeries(final IDataCollection[] source, final int dimX,
      final int dimY, final int approxMaxPoints,
      final boolean beginWhenDataIsThereForAllCollections) {
    this(StatisticSeries.makeSeries(source, dimX, dimY, approxMaxPoints,
        beginWhenDataIsThereForAllCollections));
  }

  /**
   * make a series from a set of data elements
   *
   * @param source
   *          the source array
   * @param dimX
   *          the indexing dimension - the values in this dimension must be
   *          monotonously increasing for each dimension
   * @param dimY
   *          the data value dimension over which the statistics will be
   *          computed
   * @param approxMaxPoints
   *          the approximate number of maximum points along the x-axis
   * @param beginWhenDataIsThereForAllCollections
   *          begin the series at the first point where data exists from
   *          all collections
   * @return the series
   */
  private static final double[] makeSeries(final IDataCollection[] source,
      final int dimX, final int dimY, final int approxMaxPoints,
      final boolean beginWhenDataIsThereForAllCollections) {
    final int[] indexes;
    final double[] data;
    final int sourceCount;
    final OrderStatisticInfo info;
    final double skip;
    boolean noNewPoint;
    double[] buffer;
    double lastX, curX, x, lastY, currentSkip;
    IDataCollection r;
    int i, j, e, size, capacity, index, dataSize, inc;

    if ((source == null) || ((sourceCount = source.length) <= 0)) {
      throw new IllegalArgumentException(//
          "There must be at least one data source."); //$NON-NLS-1$
    }

    // Let us get a proper initial capacity and the minimum distance of the
    // next point along the x-axis to the previous point. All x-coordinates
    // between the current point curX and (curX+minDistX) will be skipped
    initialize: {
      capacity = 0;

      for (final IDataCollection c : source) {
        capacity += c.size();
      }

      if (((approxMaxPoints > 0) && (approxMaxPoints < Integer.MAX_VALUE))) {
        if (capacity > approxMaxPoints) {
          x = (((double) capacity) / approxMaxPoints);
          if (x > 1d) {
            skip = x;
            break initialize;
          }
        }
      }

      skip = 0d;
    }

    // ok, done with initialization, let's go
    curX = Double.NaN;

    // create the backing data structure
    index = size = 0;
    buffer = new double[capacity * StatisticSeries.STATISTIC_SERIES_DIM];

    // the iteration data structure
    indexes = new int[sourceCount];
    data = new double[sourceCount];
    Arrays.fill(data, Double.NaN);
    info = new OrderStatisticInfo();

    currentSkip = Double.POSITIVE_INFINITY;// always store first point

    // build the point array
    outer: for (;;) {// create the points

      // get the next x-value which is larger than the current x value and
      // leades to a different y value
      lastX = curX;
      curX = Double.POSITIVE_INFINITY;
      noNewPoint = true;
      inc = 1;
      inner: for (i = sourceCount; (--i) >= 0;) {
        // for each data collection, find the next larger x value
        lastY = data[i];
        r = source[i];
        e = r.size();

        loopy: for (j = indexes[i]; j < e; j++) {
          x = r.get(j, dimX);
          if (x <= lastX) {// no advancement in x
            continue loopy;
          }
          if (r.get(j, dimY) == lastY) {// same y -> same point
            continue loopy;
          }
          noNewPoint = false;
          if (x < curX) {// ok, found new current x
            curX = x;
            inc = 1;
          } else {
            if (x <= curX) {
              inc++;
            }
          }
          continue inner;
        }
      }

      // we are at the end - no new point is generated
      if (noNewPoint) {
        break outer;
      }

      if (Math.abs(curX) <= 0d) {// check for -0d
        curX = 0d;
      }

      currentSkip += inc;
      if (currentSkip < skip) {
        inc = 0;
        continue outer;
      }

      currentSkip -= skip;
      if ((currentSkip >= Double.POSITIVE_INFINITY) || (currentSkip <= 0d)) {
        currentSkip = Math.max((inc - skip), 0d);
      }
      inc = 0;

      // new x-value found. so let's get the value of the y-coordinate
      // belonging to the point with the largest x value <= curX for each
      // source
      dataSize = 0;
      info.reset();
      for (i = sourceCount; (--i) >= 0;) {
        r = source[i];
        e = r.size();

        inner3: for (j = indexes[i]; j < e; j++) {
          x = r.get(j, dimX);
          if (x <= curX) {
            continue inner3;
          }
          break inner3;
        }

        j--;

        // maybe there is no point for the given x-coordinate, e.g.:
        // smallest
        // DE=45, but some runs may have the first DE at 100
        if (j >= 0) {
          indexes[i] = j;
          data[dataSize++] = lastY = r.get(j, dimY);
          info.visitDouble(lastY);
        }
      }

      // if beginWhenDataIsThereForAllCollections is true, we only begin
      // the
      // series when data is there for all source
      if (beginWhenDataIsThereForAllCollections
          && (info.getCount() < sourceCount)) {
        continue;
      }

      // now move
      if (size >= capacity) {
        capacity <<= 1;
        buffer = Arrays.copyOf(buffer,
            (capacity * StatisticSeries.STATISTIC_SERIES_DIM));
      }

      StatisticSeries.__store(buffer, index, curX, info, dataSize);

      size++;
      index += StatisticSeries.STATISTIC_SERIES_DIM;
    }

    skippedCurrent: {
      if (currentSkip > 0d) {
        // the last point has been skipped ... find it and store it

        curX = Double.NEGATIVE_INFINITY;
        info.reset();
        for (i = dataSize = sourceCount; (--i) >= 0;) {
          r = source[i];
          e = (r.size() - 1);
          x = r.get(e, dimX);
          if (x > curX) {
            curX = x;
          }
          data[i] = x = r.get(e, dimY);
          info.visitDouble(x);
        }

        // if beginWhenDataIsThereForAllCollections is true, we only
        // begin the
        // series when data is there for all source
        if (beginWhenDataIsThereForAllCollections
            && (info.getCount() < sourceCount)) {
          break skippedCurrent;
        }

        if ((index < StatisticSeries.STATISTIC_SERIES_DIM)
            || (buffer[(index + StatisticSeries.DIM_X)
                       - StatisticSeries.STATISTIC_SERIES_DIM] < curX)) {
          // now move
          if (size >= capacity) {
            capacity <<= 1;
            buffer = Arrays.copyOf(buffer,
                (capacity * StatisticSeries.STATISTIC_SERIES_DIM));
          }

          StatisticSeries.__store(buffer, index, curX, info, dataSize);

          size++;
          index += StatisticSeries.STATISTIC_SERIES_DIM;
        }
      }
    }

    return ((size > 0) ? ((size != capacity) ? (Arrays.copyOf(buffer,
        index)) : buffer) : EmptyUtils.EMPTY_DOUBLES);
  }

  /**
   * Store the data
   *
   * @param curX
   *          the current x
   * @param index
   *          the index
   * @param buffer
   *          the buffer
   * @param info
   *          the info
   * @param dataSize
   *          the data size
   */
  private static final void __store(final double[] buffer,
      final int index, final double curX, final OrderStatisticInfo info,
      final int dataSize) {
    // the data was loaded, so compute

    // store the data
    buffer[index + StatisticSeries.DIM_Y_MIN] = info.getMinimum();
    buffer[index + StatisticSeries.DIM_Y_Q05] = info.getQuantile(0.05d);
    buffer[index + StatisticSeries.DIM_Y_Q25] = info.getQuantile(0.25d);
    buffer[index + StatisticSeries.DIM_Y_MEDIAN] = info.getQuantile(0.5d);
    buffer[index + StatisticSeries.DIM_Y_Q75] = info.getQuantile(0.75d);
    buffer[index + StatisticSeries.DIM_Y_Q95] = info.getQuantile(0.95d);
    buffer[index + StatisticSeries.DIM_Y_MAX] = info.getMaximum();
    buffer[index + StatisticSeries.DIM_Y_MEAN] = info.getArithmeticMean();
    buffer[index + StatisticSeries.DIM_Y_VARIANCE] = info.getVariance();
    buffer[index + StatisticSeries.DIM_COUNT] = info.getCount();
    buffer[index + StatisticSeries.DIM_X] = curX;
  }

  /**
   * format and check the value
   *
   * @param x
   *          the value
   * @return the checked result
   */
  private static final double __format(final double x) {
    if (x != x) {
      throw new IllegalArgumentException(//
          "Cannot find value " + x); //$NON-NLS-1$
    }
    if (Math.abs(x) <= 0d) {
      return 0d;
    }
    return x;
  }

  /**
   * Get the index that belongs to the given {@code x} coordinate. The
   * returned value will be {@code -1} if {@code x} is smaller than the
   * smallest {@code x} coordinate of any point in this collection.
   * Otherwise, it will the index of the point with the largest {@code x}
   * -coordinate which is still less or equal than the passed in {@code x}
   * parameter.
   *
   * @param x
   *          the {@code x}-coordinate
   * @return the corresponding index
   */
  public final int indexOfX(final double x) {
    final double[] data;
    final double key;
    int low, high, mid;
    double midVal;

    key = StatisticSeries.__format(x);

    data = this.m_data;
    low = 0;
    high = (this.m_size - 1);

    while (low <= high) {
      mid = ((low + high) >>> 1);
      midVal = data[(mid * StatisticSeries.STATISTIC_SERIES_DIM)
                    + StatisticSeries.DIM_X];

      if (midVal < key) {
        low = (mid + 1);
      } else {
        if (midVal > key) {
          high = (mid - 1);
        } else {
          return mid;
        }
      }
    }

    return ((high < 0) ? (-1) : high);
  }

  /**
   * Find the index of the first point where the value at dimension
   * {@code dim} meets the comparison criterion {@code cmp} with the
   * provided value {@code value}.
   *
   * @param value
   *          the value
   * @param dim
   *          the dimension
   * @param cmp
   *          the comparison
   * @return the index, or {@code -1} if the comparison is never met
   */
  public final int firstIndexOf(final double value, final int dim,
      final EComparison cmp) {
    final double[] data;
    final double key;
    int i;

    if ((dim < 0) || (dim >= StatisticSeries.STATISTIC_SERIES_DIM)) {
      throw new IllegalArgumentException("illegal dimension " + dim); //$NON-NLS-1$
    }

    key = StatisticSeries.__format(value);
    data = this.m_data;

    for (i = dim; i < data.length; i += StatisticSeries.STATISTIC_SERIES_DIM) {
      if (cmp.compare(data[i], key)) {
        return (i / StatisticSeries.STATISTIC_SERIES_DIM);
      }
    }

    return (-1);
  }

  /**
   * Find the index of the first point where the value of the given
   * statistic parameter meets the comparison criterion {@code cmp} with
   * the provided value {@code value}.
   *
   * @param value
   *          the value
   * @param param
   *          the statistical parameter
   * @param cmp
   *          the comparison
   * @return the index, or {@code -1} if the comparison is never met
   */
  public final int firstIndexOf(final double value,
      final EStatisticParameter param, final EComparison cmp) {
    return this.firstIndexOf(value, StatisticSeries.LIST.indexOf(param),
        cmp);
  }

  /**
   * Find the index of the last point where the value at dimension
   * {@code dim} meets the comparison criterion {@code cmp} with the
   * provided value {@code value}.
   *
   * @param value
   *          the value
   * @param dim
   *          the dimension
   * @param cmp
   *          the comparison
   * @return the index, or {@code -1} if the comparison is never met
   */
  public final int lastIndexOf(final double value, final int dim,
      final EComparison cmp) {
    final double[] data;
    final double key;
    int i;

    if ((dim < 0) || (dim >= StatisticSeries.STATISTIC_SERIES_DIM)) {
      throw new IllegalArgumentException("illegal dimension " + dim); //$NON-NLS-1$
    }

    key = StatisticSeries.__format(value);
    data = this.m_data;

    for (i = (data.length + dim); (i -= StatisticSeries.STATISTIC_SERIES_DIM) >= 0;) {
      if (cmp.compare(data[i], key)) {
        return (i / StatisticSeries.STATISTIC_SERIES_DIM);
      }
    }

    return (-1);
  }

  /**
   * Find the index of the last point where the value of the given
   * statistic parameter meets the comparison criterion {@code cmp} with
   * the provided value {@code value}.
   *
   * @param value
   *          the value
   * @param param
   *          the statistical parameter
   * @param cmp
   *          the comparison
   * @return the index, or {@code -1} if the comparison is never met
   */
  public final int lastIndexOf(final double value,
      final EStatisticParameter param, final EComparison cmp) {
    return this.lastIndexOf(value, StatisticSeries.LIST.indexOf(param),
        cmp);
  }

  /** {@inheritDoc} */
  @Override
  public final ArraySetView<EStatisticParameter> getParameters() {
    return StatisticSeries.LIST;
  }

  /** {@inheritDoc} */
  @Override
  public final double getParameter(final int index,
      final EStatisticParameter param) {
    int i;
    i = StatisticSeries.LIST.indexOf(param);
    if (i >= 0) {
      return this.m_data[(StatisticSeries.STATISTIC_SERIES_DIM * index)
                         + i];
    }
    throw new UnsupportedOperationException(String.valueOf(param));
  }
}
