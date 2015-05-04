package org.logisticPlanning.utils.math.statistics.aggregates;

import org.logisticPlanning.utils.collections.lists.ArraySetView;
import org.logisticPlanning.utils.math.statistics.EStatisticParameter;
import org.logisticPlanning.utils.math.statistics.IStatisticInfo;

/**
 * A class capable of gathering statistical information from a data source
 * in a single pass.
 */
public class StatisticInfo extends Aggregate implements IStatisticInfo {
  /** The serial version uid. */
  private static final long serialVersionUID = 1;

  /** the list of supported statistic parameters */
  static final ArraySetView<EStatisticParameter> LIST = EStatisticParameter
      .makeList(EStatisticParameter.MINIMUM,
          EStatisticParameter.ARITHMETIC_MEAN,
          EStatisticParameter.MAXIMUM, EStatisticParameter.RANGE,
          EStatisticParameter.COUNT, EStatisticParameter.VARIANCE,
          EStatisticParameter.STANDARD_DEVIATION,
          EStatisticParameter.COEFFICIENT_OF_VARIATION,
          EStatisticParameter.SKEWNESS, EStatisticParameter.KURTOSIS);

  /** the dimension of the basic statistic info record */
  public static final int STATISTIC_INFO_DIMENSION = StatisticInfo.LIST
      .size();

  /**
   * @serial The minimum value.
   * @see #getMinimum()
   */
  private double m_min;

  /**
   * @serial The maximum value.
   * @see #getMaximum()
   */
  private double m_max;

  // /** @serial the mean */
  // private double m_mean;
  // /** @serial M2 */
  // private double m_M2;
  // /** @serial M3 */
  // private double m_M3;
  // /** @serial M4 */
  // private double m_M4;

  /** @serial the mean */
  private StableSum m_mean;
  /** @serial M2 */
  private StableSum m_M2;
  /** @serial M3 */
  private StableSum m_M3;
  /** @serial M4 */
  private StableSum m_M4;

  /**
   * @serial The count of all values.
   * @see #getCount()
   */
  private long m_count;

  /** Create a new statistic info bag. */
  public StatisticInfo() {
    super();

    this.m_mean = new StableSum();
    this.m_M2 = new StableSum();
    this.m_M3 = new StableSum();
    this.m_M4 = new StableSum();
  }

  /** {@inheritDoc} */
  @Override
  public void reset() {
    this.m_count = 0L;
    this.m_max = Double.NaN;
    this.m_min = Double.NaN;

    this.m_mean.reset();
    this.m_M2.reset();
    this.m_M3.reset();
    this.m_M4.reset();
  }

  /** {@inheritDoc} */
  @Override
  public StatisticInfo clone() {
    StatisticInfo m;
    m = ((StatisticInfo) (super.clone()));
    m.m_M2 = ((StableSum) (m.m_M2.clone()));
    m.m_M3 = ((StableSum) (m.m_M3.clone()));
    m.m_M4 = ((StableSum) (m.m_M4.clone()));
    m.m_mean = ((StableSum) (m.m_mean.clone()));
    return m;
  }

  /**
   * Obtain the count of elements evaluated.
   *
   * @return The count of elements evaluated.
   */
  public final long getCount() {
    return this.m_count;
  }

  /** {@inheritDoc} */
  @Override
  public final double getMinimum() {
    return this.m_min;
  }

  /** {@inheritDoc} */
  @Override
  public final double getMaximum() {
    return this.m_max;
  }

  /** {@inheritDoc} */
  @Override
  public final double getArithmeticMean() {
    final double max, min, mean;
    final long l;

    l = this.m_count;
    if (l <= 0) {
      return Double.NaN;
    }

    min = this.m_min;
    max = this.m_max;
    if (max <= min) {
      return max;
    }
    if ((max >= Double.POSITIVE_INFINITY)
        && (min > Double.NEGATIVE_INFINITY)) {
      return Double.POSITIVE_INFINITY;
    }
    if ((min <= Double.NEGATIVE_INFINITY)
        && (max < Double.POSITIVE_INFINITY)) {
      return Double.NEGATIVE_INFINITY;
    }
    mean = this.m_mean.getResult();
    return Math.min(max, Math.max(min, mean));
  }

  /** {@inheritDoc} */
  @Override
  public final double getVariance() {
    final long l;
    final double min, max;

    l = this.m_count;
    if (l <= 1) {
      if (l <= 0) {
        return Double.NaN;
      }
      return 0.0d;
    }

    min = this.m_min;
    max = this.m_max;
    if (max <= min) {
      if ((min >= Double.POSITIVE_INFINITY)
          || (max <= Double.NEGATIVE_INFINITY)) {
        return Double.POSITIVE_INFINITY;
      }
      return 0d;
    }

    return (this.m_M2.getResult() / (this.m_count - 1l));
  }

  /**
   * Obtain the coefficient of variation.
   *
   * @return The coefficient of variation.
   */
  public final double getCoefficientOfVariation() {
    long l;

    l = this.m_count;

    if (l <= 1l) {
      if (l <= 0l) {
        return Double.NaN;
      }
      return 0.0d;
    }

    return (this.getStandardDeviation() / this.getArithmeticMean());
  }

  /** {@inheritDoc} */
  @Override
  public final double getStandardDeviation() {
    final double var;
    var = this.getVariance();
    if (var <= 0d) {
      return 0d;
    }
    return Math.sqrt(this.getVariance());
  }

  /**
   * Obtain the range of the values visited.
   *
   * @return The range of the values visited.
   */
  public final double getRange() {
    final double max, min, res;

    max = this.m_max;
    min = this.m_min;

    if (max <= min) {
      return 0d;
    }

    res = (max - min);
    if (res != res) {
      return Double.POSITIVE_INFINITY;
    }

    return Math.max(0.0d, res);
  }

  /** {@inheritDoc} */
  @Override
  public void visitDouble(final double value) {
    final long n, n1;
    final double delta, delta_n, delta_n2, mean, term1, M2, M3;// , M4;

    n1 = this.m_count;
    this.m_count = n = (n1 + 1l);
    // mean = this.m_mean;
    mean = this.m_mean.getResult();
    delta = (value - mean);
    delta_n = (delta / n);
    delta_n2 = (delta_n * delta_n);
    term1 = (delta * delta_n * n1);
    // this.m_mean = (mean + delta_n);
    this.m_mean.visitDouble(delta_n);

    if (n1 > 0l) {
      // M4 = this.m_M4;
      // M3 = this.m_M3;
      // M2 = this.m_M2;

      M3 = this.m_M3.getResult();
      M2 = this.m_M2.getResult();

      // this.m_M4 = ((M4 + (term1 * delta_n2 * (((n * n) - (3 * n)) + 3))
      // + (6
      // * delta_n2 * M2)) - (4 * delta_n * M3));
      this.m_M4
      .visitDouble(((term1 * delta_n2 * (((n * n) - (3 * n)) + 3)) + (6 * delta_n2 * M2))
          - (4 * delta_n * M3));

      // this.m_M3 = (M3 + (term1 * delta_n * (n - 2d))) - (3d * delta_n *
      // M2);
      this.m_M3.visitDouble(((term1 * delta_n * (n - 2d)))
          - (3d * delta_n * M2));

      // this.m_M2 = (M2 + term1);
      this.m_M2.visitDouble(term1);

      if (value > this.m_max) {
        this.m_max = value;
      }
      if (value < this.m_min) {
        this.m_min = value;
      }
    } else {
      this.m_max = value;
      this.m_min = value;
    }
  }

  /** {@inheritDoc} */
  @Override
  public final double getSkewness() {
    final long n;

    n = this.m_count;

    return ((Math.sqrt(n * (n - 1l)) / (n - 2l)) * (Math.sqrt(n) * (this.m_M3
        .getResult() / //
        (this.m_M2.getResult() * Math.sqrt(this.m_M2.getResult())))));
  }

  /** {@inheritDoc} */
  @Override
  public final double getKurtosis() {
    final long n, nm1;

    n = this.m_count;
    nm1 = (n - 1l);

    return ((((nm1 * (n + 1l) * n) * this.m_M4.getResult()) / (this.m_M2
        .getResult() * this.m_M2.getResult())) - (3l * (nm1 * nm1)))
        / ((n - 2l) * (n - 3l));
  }

  /** {@inheritDoc} */
  @Override
  public ArraySetView<EStatisticParameter> getParameters() {
    return StatisticInfo.LIST;
  }

  /** {@inheritDoc} */
  @Override
  public double getParameter(final EStatisticParameter param) {
    switch (param) {
      case MINIMUM: {
        return this.m_min;
      }
      case ARITHMETIC_MEAN: {
        return this.getArithmeticMean();
      }
      case MAXIMUM: {
        return this.m_max;
      }
      case RANGE: {
        return this.getRange();
      }
      case COUNT: {
        return this.m_count;
      }
      case VARIANCE: {
        return this.getVariance();
      }
      case STANDARD_DEVIATION: {
        return this.getStandardDeviation();
      }
      case COEFFICIENT_OF_VARIATION: {
        return this.getCoefficientOfVariation();
      }
      case SKEWNESS: {
        return this.getSkewness();
      }
      case KURTOSIS: {
        return this.getKurtosis();
      }
      default: {
        throw new UnsupportedOperationException(String.valueOf(param));
      }
    }
  }

  /** {@inheritDoc} */
  @Override
  public double get(final int dimension) {
    return this.getParameter(StatisticInfo.LIST.get(dimension));
  }

  /** {@inheritDoc} */
  @Override
  public int dimension() {
    return StatisticInfo.STATISTIC_INFO_DIMENSION;
  }
}
