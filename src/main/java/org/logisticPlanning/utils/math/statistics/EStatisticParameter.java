package org.logisticPlanning.utils.math.statistics;

import org.logisticPlanning.utils.collections.lists.ArraySetView;
import org.logisticPlanning.utils.utils.comparison.EComparison;

/**
 * An enumeration of statistic parameters. Based on this enumeration, we
 * can have some standard way to textify statistic information and, even
 * more important, it allows us to build a unified API to query statistic
 * data from objects. This API is defined in the interfaces
 * {@link org.logisticPlanning.utils.math.statistics.IStatisticData} (base
 * interface providing a list of supported parameters),
 * {@link org.logisticPlanning.utils.math.statistics.IStatisticPoint}
 * (interface for a single information/data point that may provide some
 * values of statistical data), and
 * {@link org.logisticPlanning.utils.math.statistics.IStatisticDataCollection}
 * (a collection of static information elements).
 */
public enum EStatisticParameter {
  /** the minimum */
  MINIMUM("min", //$NON-NLS-1$
      "minimum", //$NON-NLS-1$
      "minima", //$NON-NLS-1$
      true, false, false),

  /** the 5% percentile */
  PERCENTILE_05("q05", //$NON-NLS-1$
      "5% quantile", //$NON-NLS-1$
      "5% quantiles", //$NON-NLS-1$
      true, false, false),

  /** the 25% percentile */
  PERCENTILE_25("q25", //$NON-NLS-1$
      "25% quantile", //$NON-NLS-1$
      "25% quantiles", //$NON-NLS-1$
      true, false, false),

  /** the median */
  MEDIAN("med", //$NON-NLS-1$
      "median", //$NON-NLS-1$
      "medians", //$NON-NLS-1$
      true, false, false),

  /** the 75% percentile */
  PERCENTILE_75("q75", //$NON-NLS-1$
      "75% quantile", //$NON-NLS-1$
      "75% quantiles", //$NON-NLS-1$
      true, false, false),

  /** the 95% percentile */
  PERCENTILE_95("q95", //$NON-NLS-1$
      "95% quantile", //$NON-NLS-1$
      "95% quantiles", //$NON-NLS-1$
      true, false, false),

  /** the maximum */
  MAXIMUM("max", //$NON-NLS-1$
      "maximum", //$NON-NLS-1$
      "maxima", //$NON-NLS-1$
      true, false, false),

  /** the range */
  RANGE("range", //$NON-NLS-1$
      null, //
      "ranges", //$NON-NLS-1$
      false, true, false),

  /** the arithmetic mean */
  ARITHMETIC_MEAN("mean", //$NON-NLS-1$
      "arithmetic mean", //$NON-NLS-1$
      "arithmetic means", //$NON-NLS-1$
      true, false, false),

  /** the variance */
  VARIANCE("var", //$NON-NLS-1$
      "variance", //$NON-NLS-1$
      "variances", //$NON-NLS-1$
      false, true, false),

  /** the standard deviation */
  STANDARD_DEVIATION("stddev", //$NON-NLS-1$
      "standard deviation", //$NON-NLS-1$
      "standard deviations", //$NON-NLS-1$
      false, true, false),

  /** the coefficient of variation */
  COEFFICIENT_OF_VARIATION("coef.var", //$NON-NLS-1$
      "coefficient of variation", //$NON-NLS-1$
      "coefficients of variation", //$NON-NLS-1$
      false, true, false),

  /** the skewness */
  SKEWNESS("skew", //$NON-NLS-1$
      "skewness", //$NON-NLS-1$
      "skewnesses", //$NON-NLS-1$
      false, false, true) {

    /** {@inheritDoc} */
    @Override
    public final EComparison compare(final double val1, final double val2) {
      if ((val1 != val1) || (val2 != val2)) {
        return EComparison.NOT_EQUAL;
      }
      if (val1 == val2) {
        return EComparison.SAME;
      }
      if (Math.abs(val1) < Math.abs(val2)) {
        return EComparison.LESS;
      }
      return EComparison.GREATER;
    }

  },

  /** the kurtosis */
  KURTOSIS("kurtosis", //$NON-NLS-1$
      "kurtosis", //$NON-NLS-1$
      "kurtosises", //$NON-NLS-1$
      false, false, true) {

    /** {@inheritDoc} */
    @Override
    public final EComparison compare(final double val1, final double val2) {
      if ((val1 != val1) || (val2 != val2)) {
        return EComparison.NOT_EQUAL;
      }
      if (val1 == val2) {
        return EComparison.SAME;
      }
      if (Math.abs(val1) < Math.abs(val2)) {
        return EComparison.LESS;
      }
      return EComparison.GREATER;
    }

  },

  /** the count */
  COUNT("count", //$NON-NLS-1$
      "number of samples", //$NON-NLS-1$
      "numbers of samples", //$NON-NLS-1$
      false, false, false) {

    /** {@inheritDoc} */
    @Override
    public final EComparison compare(final double val1, final double val2) {
      if ((val1 != val1) || (val2 != val2)) {
        return EComparison.NOT_EQUAL;
      }
      if (val1 == val2) {
        return EComparison.SAME;
      }
      if (val1 < val2) {
        return EComparison.GREATER;
      }
      return EComparison.LESS;
    }
  },

  /** the sum */
  SUM("sum", //$NON-NLS-1$
      "sum of samples", //$NON-NLS-1$
      "sums of samples", //$NON-NLS-1$
      false, false, false);

  /** the short name */
  private final String m_short;

  /** the long name */
  private final String m_long;

  /** the long name in plural */
  private final String m_longPlural;

  /** is this a value parameter? */
  private final boolean m_isValue;

  /** is this a spread parameter? */
  private final boolean m_isSpread;

  /** is this a shape parameter? */
  private final boolean m_isShape;

  /**
   * create
   *
   * @param shrt
   *          the short name
   * @param lng
   *          the long name
   * @param lngP
   *          the long name in plural
   * @param value
   *          is this a value parameter?
   * @param spread
   *          is this a spread parameter?
   * @param shape
   *          is this a shape parameter?
   */
  private EStatisticParameter(final String shrt, final String lng,
      final String lngP, final boolean value, final boolean spread,
      final boolean shape) {
    this.m_short = shrt;
    this.m_long = ((lng == null) ? this.m_short : lng);
    this.m_longPlural = ((lngP == null) ? this.m_long : lngP);
    this.m_isValue = value;
    this.m_isSpread = spread;
    this.m_isShape = shape;
  }

  /**
   * Get the short name
   *
   * @return the short name
   */
  public final String getShortName() {
    return this.m_short;
  }

  /**
   * Get the long name
   *
   * @param plural
   *          should we return the plural form ({@code true}) or the
   *          singular form ({@code false})
   * @return the long name
   */
  public final String getLongName(final boolean plural) {
    return ((plural ? this.m_longPlural : this.m_long));
  }

  /**
   * Is this a value parameter, i.e., a parameter that represents actual
   * values from the distribution?
   *
   * @return {@code true} if this is a value parameter, i.e., a parameter
   *         that represents actual values from the distribution,
   *         {@code false} otherwise.
   */
  public final boolean isValue() {
    return this.m_isValue;
  }

  /**
   * Is this a spread parameter, i.e., a parameter that relates to the
   * range or spread of the values from this distribution
   *
   * @return {@code true} if this is a spread parameter, i.e., a parameter
   *         that relates to the range or spread of the values from this
   *         distribution, {@code false} otherwise.
   */
  public final boolean isSpread() {
    return this.m_isSpread;
  }

  /**
   * Is this a shape parameter, i.e., a parameter that relates to the shape
   * of this distribution
   *
   * @return {@code true} if this is a shape parameter, i.e., a parameter
   *         that relates to the shape of this distribution, {@code false}
   *         otherwise.
   */
  public final boolean isShape() {
    return this.m_isShape;
  }

  /**
   * create a list of statistic parameters
   *
   * @param list
   *          the parameters
   * @return the list
   */
  public static final ArraySetView<EStatisticParameter> makeList(
      final EStatisticParameter... list) {
    EStatisticParameter last;

    if ((list != null) && (list.length > 0)) {

      java.util.Arrays.sort(list);
      last = null;
      for (final EStatisticParameter p : list) {
        if (p == null) {
          throw new IllegalArgumentException("Null must not occur!"); //$NON-NLS-1$
        }
        if (p == last) {
          throw new IllegalArgumentException(p + " occurs two times!"); //$NON-NLS-1$
        }
        last = p;
      }
    }

    return ArraySetView.makeArraySetView(list, false);
  }

  /** {@inheritDoc} */
  @Override
  public final String toString() {
    return ((((this.m_long + ' ') + '(') + this.m_short) + ')');
  }

  /**
   * Compare two values of this parameter. The result is
   * {@link org.logisticPlanning.utils.utils.comparison.EComparison#LESS
   * LESS} if {@code val1} seems to be less or better than {@code val2},
   * {@link org.logisticPlanning.utils.utils.comparison.EComparison#GREATER
   * GREATER} if {@code val1} seems to be greater or worse than
   * {@code val2} and other constants are appropriately used
   *
   * @param val1
   *          the first value
   * @param val2
   *          the second value
   * @return the result
   */
  public EComparison compare(final double val1, final double val2) {
    if ((val1 != val1) || (val2 != val2)) {
      return EComparison.NOT_EQUAL;
    }
    if (val1 == val2) {
      return EComparison.SAME;
    }
    if (val1 < val2) {
      return EComparison.LESS;
    }
    return EComparison.GREATER;
  }
}
