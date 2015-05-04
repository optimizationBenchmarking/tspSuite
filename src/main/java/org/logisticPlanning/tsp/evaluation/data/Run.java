package org.logisticPlanning.tsp.evaluation.data;

import org.logisticPlanning.tsp.benchmarking.objective.DataPoint;
import org.logisticPlanning.utils.math.data.collection.IDataCollection;

/**
 * <p>
 * A run as loaded from a log file. A run represents one application of a
 * {@link org.logisticPlanning.tsp.solving.TSPAlgorithm TSP solver} to one
 * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance TSP
 * instance}.
 * </p>
 */
public final class Run extends _OwnedSet<DataPoint, RunSet> implements
IDataCollection {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * instantiate
   *
   * @param name
   *          the source file name
   * @param data
   *          the data points
   */
  public Run(final String name, final DataPoint[] data) {
    super(name, data);

    DataPoint old;
    old = null;
    for (final DataPoint p : data) {
      DataPoint.checkSequence(old, p);
      old = p;
    }

    if (old == null) {
      throw new IllegalArgumentException(//
          "There must be at least one log point in the run.");//$NON-NLS-1$
    }

    if (!(old.logReasonEnd())) {
      throw new IllegalArgumentException(//
          "The last log point in a run must have 'Algorithm End' as its reason."); //$NON-NLS-1$
    }
  }

  /**
   * Get the data point representing the information available at the given
   * objective function evaluation (see
   * <code>{@link org.logisticPlanning.tsp.benchmarking.objective.LogPoint#getConsumedFEs() LogPoint.getConsumedFEs()}</code>
   * )
   *
   * @param fe
   *          the objective function evaluation (see
   *          <code>{@link org.logisticPlanning.tsp.benchmarking.objective.LogPoint#getConsumedFEs() LogPoint.getConsumedFEs()}</code>
   *          )
   * @return the data point representing the information available at the
   *         given objective function evaluation (see
   *         <code>{@link org.logisticPlanning.tsp.benchmarking.objective.LogPoint#getConsumedFEs() LogPoint.getConsumedFEs()}</code>
   *         )
   */
  public final DataPoint findFE(final long fe) {
    final DataPoint[] ps;
    DataPoint midVal;
    int low, sh, high, mid, cmp;

    ps = this.m_data;
    low = 0;
    sh = high = (ps.length - 1);

    while (low <= high) {
      mid = ((low + high) >>> 1);
      midVal = ps[mid];
      cmp = java.lang.Long.compare(midVal.getConsumedFEs(), fe);

      if (cmp < 0) {
        low = (mid + 1);
      } else
        if (cmp > 0) {
          high = (mid - 1);
        } else {
          return midVal;
        }
    }

    if (low <= 0) {
      return null;
    }
    if (low > sh) {
      return ps[sh];
    }
    return ps[low - 1];
  }

  /**
   * Get the data point representing the information available at the given
   * objective function evaluation (see
   * <code>{@link org.logisticPlanning.tsp.benchmarking.objective.LogPoint#getConsumedFEs() LogPoint.getConsumedFEs()}</code>
   * )
   *
   * @param fe
   *          the objective function evaluation (see
   *          <code>{@link org.logisticPlanning.tsp.benchmarking.objective.LogPoint#getConsumedFEs() LogPoint.getConsumedFEs()}</code>
   *          )
   * @return the data point representing the information available at the
   *         given objective function evaluation (see
   *         <code>{@link org.logisticPlanning.tsp.benchmarking.objective.LogPoint#getConsumedFEs() LogPoint.getConsumedFEs()}</code>
   *         )
   */
  public final DataPoint findFE(final double fe) {
    final DataPoint[] ps;
    DataPoint midVal;
    int low, sh, high, mid, cmp;

    ps = this.m_data;
    low = 0;
    sh = high = (ps.length - 1);

    while (low <= high) {
      mid = ((low + high) >>> 1);
      midVal = ps[mid];
      cmp = java.lang.Double.compare(midVal.getConsumedFEs(), fe);

      if (cmp < 0) {
        low = (mid + 1);
      } else
        if (cmp > 0) {
          high = (mid - 1);
        } else {
          return midVal;
        }
    }

    if (low <= 0) {
      return null;
    }
    if (low > sh) {
      return ps[sh];
    }
    return ps[low - 1];
  }

  /**
   * Get the data point representing the information available at the given
   * distance function evaluation (see
   * <code>{@link org.logisticPlanning.tsp.benchmarking.objective.LogPoint#getConsumedDEs() LogPoint.getConsumedDEs()}</code>
   * )
   *
   * @param de
   *          the distance function evaluation (see
   *          <code>{@link org.logisticPlanning.tsp.benchmarking.objective.LogPoint#getConsumedDEs() LogPoint.getConsumedDEs()}</code>
   *          )
   * @return the data point representing the information available at the
   *         given distance function evaluation (see
   *         <code>{@link org.logisticPlanning.tsp.benchmarking.objective.LogPoint#getConsumedDEs() LogPoint.getConsumedDEs()}</code>
   *         )
   */
  public final DataPoint findDE(final long de) {
    final DataPoint[] ps;
    DataPoint midVal, next;
    int low, sh, high, mid, cmp, i;

    ps = this.m_data;
    low = 0;
    sh = high = (ps.length - 1);

    while (low <= high) {
      mid = ((low + high) >>> 1);
      midVal = ps[mid];
      cmp = java.lang.Long.compare(midVal.getConsumedDEs(), de);

      if (cmp < 0) {
        low = (mid + 1);
      } else
        if (cmp > 0) {
          high = (mid - 1);
        } else {
          for (i = mid; (++i) <= sh;) {
            next = ps[i];
            if ((java.lang.Long.compare(next.getConsumedDEs(), de)) != 0) {
              return midVal;
            }
            midVal = next;
          }
          return midVal;
        }
    }

    if (low <= 0) {
      return null;
    }
    if (low > sh) {
      return ps[sh];
    }
    return ps[low - 1];
  }

  /**
   * Get the data point representing the information available at the given
   * distance function evaluation (see
   * <code>{@link org.logisticPlanning.tsp.benchmarking.objective.LogPoint#getConsumedDEs() LogPoint.getConsumedDEs()}</code>
   * )
   *
   * @param de
   *          the distance function evaluation (see
   *          <code>{@link org.logisticPlanning.tsp.benchmarking.objective.LogPoint#getConsumedDEs() LogPoint.getConsumedDEs()}</code>
   *          )
   * @return the data point representing the information available at the
   *         given distance function evaluation (see
   *         <code>{@link org.logisticPlanning.tsp.benchmarking.objective.LogPoint#getConsumedDEs() LogPoint.getConsumedDEs()}</code>
   *         )
   */
  public final DataPoint findDE(final double de) {
    final DataPoint[] ps;
    DataPoint midVal, next;
    int low, sh, high, mid, cmp, i;

    ps = this.m_data;
    low = 0;
    sh = high = (ps.length - 1);

    while (low <= high) {
      mid = ((low + high) >>> 1);
      midVal = ps[mid];
      cmp = java.lang.Double.compare(midVal.getConsumedDEs(), de);

      if (cmp < 0) {
        low = (mid + 1);
      } else
        if (cmp > 0) {
          high = (mid - 1);
        } else {
          for (i = mid; (++i) <= sh;) {
            next = ps[i];
            if ((java.lang.Double.compare(next.getConsumedDEs(), de)) != 0) {
              return midVal;
            }
            midVal = next;
          }
          return midVal;
        }
    }

    if (low <= 0) {
      return null;
    }
    if (low > sh) {
      return ps[sh];
    }
    return ps[low - 1];
  }

  /**
   * Get the data point representing the information available at the given
   * runtime (see
   * <code>{@link org.logisticPlanning.tsp.benchmarking.objective.LogPoint#getConsumedRuntime() LogPoint.getConsumedRuntime()}</code>
   * )
   *
   * @param runtime
   *          the runtime (see
   *          <code>{@link org.logisticPlanning.tsp.benchmarking.objective.LogPoint#getConsumedRuntime() LogPoint.getConsumedRuntime()}</code>
   *          )
   * @return the data point representing the information available at the
   *         given runtime (see
   *         <code>{@link org.logisticPlanning.tsp.benchmarking.objective.LogPoint#getConsumedRuntime() LogPoint.getConsumedRuntime()}</code>
   *         )
   */
  public final DataPoint findRuntime(final long runtime) {
    final DataPoint[] ps;
    DataPoint midVal, next;
    int low, sh, high, mid, cmp, i;

    ps = this.m_data;
    low = 0;
    sh = high = (ps.length - 1);

    while (low <= high) {
      mid = ((low + high) >>> 1);
      midVal = ps[mid];
      cmp = java.lang.Long.compare(midVal.getConsumedRuntime(), runtime);

      if (cmp < 0) {
        low = (mid + 1);
      } else
        if (cmp > 0) {
          high = (mid - 1);
        } else {
          for (i = mid; (++i) <= sh;) {
            next = ps[i];
            if ((java.lang.Long
                .compare(next.getConsumedRuntime(), runtime)) != 0) {
              return midVal;
            }
            midVal = next;
          }
          return midVal;
        }
    }

    if (low <= 0) {
      return null;
    }
    if (low > sh) {
      return ps[sh];
    }
    return ps[low - 1];
  }

  /**
   * Get the data point representing the information available at the given
   * runtime (see
   * <code>{@link org.logisticPlanning.tsp.benchmarking.objective.LogPoint#getConsumedRuntime() LogPoint.getConsumedRuntime()}</code>
   * )
   *
   * @param runtime
   *          the runtime (see
   *          <code>{@link org.logisticPlanning.tsp.benchmarking.objective.LogPoint#getConsumedRuntime() LogPoint.getConsumedRuntime()}</code>
   *          )
   * @return the data point representing the information available at the
   *         given runtime (see
   *         <code>{@link org.logisticPlanning.tsp.benchmarking.objective.LogPoint#getConsumedRuntime() LogPoint.getConsumedRuntime()}</code>
   *         )
   */
  public final DataPoint findRuntime(final double runtime) {
    final DataPoint[] ps;
    DataPoint midVal, next;
    int low, sh, high, mid, cmp, i;

    ps = this.m_data;
    low = 0;
    sh = high = (ps.length - 1);

    while (low <= high) {
      mid = ((low + high) >>> 1);
      midVal = ps[mid];
      cmp = java.lang.Double.compare(midVal.getConsumedRuntime(), runtime);

      if (cmp < 0) {
        low = (mid + 1);
      } else
        if (cmp > 0) {
          high = (mid - 1);
        } else {
          for (i = mid; (++i) <= sh;) {
            next = ps[i];
            if ((java.lang.Double.compare(next.getConsumedRuntime(),
                runtime)) != 0) {
              return midVal;
            }
            midVal = next;
          }
          return midVal;
        }
    }

    if (low <= 0) {
      return null;
    }
    if (low > sh) {
      return ps[sh];
    }
    return ps[low - 1];
  }

  /**
   * Get the data point representing the information available at the given
   * normalized runtime (see
   * <code>{@link org.logisticPlanning.tsp.benchmarking.objective.DataPoint#getConsumedNormalizedRuntime() DataPoint.getConsumedNormalizedRuntime()}</code>
   * )
   *
   * @param nruntime
   *          the normalized runtime (see
   *          <code>{@link org.logisticPlanning.tsp.benchmarking.objective.DataPoint#getConsumedNormalizedRuntime() DataPoint.getConsumedNormalizedRuntime()}</code>
   *          )
   * @return the data point representing the information available at the
   *         given normalized runtime (see
   *         <code>{@link org.logisticPlanning.tsp.benchmarking.objective.DataPoint#getConsumedNormalizedRuntime() DataPoint.getConsumedNormalizedRuntime()}</code>
   *         )
   */
  public final DataPoint findNormalizedRuntime(final double nruntime) {
    final DataPoint[] ps;
    DataPoint midVal, next;
    int low, sh, high, mid, cmp, i;

    ps = this.m_data;
    low = 0;
    sh = high = (ps.length - 1);

    while (low <= high) {
      mid = ((low + high) >>> 1);
      midVal = ps[mid];
      cmp = java.lang.Double.compare(
          midVal.getConsumedNormalizedRuntime(), nruntime);

      if (cmp < 0) {
        low = (mid + 1);
      } else
        if (cmp > 0) {
          high = (mid - 1);
        } else {
          for (i = mid; (++i) <= sh;) {
            next = ps[i];
            if ((java.lang.Double.compare(
                next.getConsumedNormalizedRuntime(), nruntime)) != 0) {
              return midVal;
            }
            midVal = next;
          }
          return midVal;
        }
    }

    if (low <= 0) {
      return null;
    }
    if (low > sh) {
      return ps[sh];
    }
    return ps[low - 1];
  }

  /**
   * Get the data point representing the information available at the given
   * best known objective value (see
   * <code>{@link org.logisticPlanning.tsp.benchmarking.objective.LogPoint#getBestF() LogPointgetBestF()}</code>
   * )
   *
   * @param f
   *          the best known objective value (see
   *          <code>{@link org.logisticPlanning.tsp.benchmarking.objective.LogPoint#getBestF() LogPointgetBestF()}</code>
   *          )
   * @return the data point representing the information available at the
   *         given best known objective value (see
   *         <code>{@link org.logisticPlanning.tsp.benchmarking.objective.LogPoint#getBestF() LogPointgetBestF()}</code>
   *         )
   */
  public final DataPoint findBestF(final long f) {
    final DataPoint[] ps;
    DataPoint midVal;
    int low, sh, high, mid, cmp;

    ps = this.m_data;
    low = 0;
    sh = high = (ps.length - 1);

    while (low <= high) {
      mid = ((low + high) >>> 1);
      midVal = ps[mid];
      cmp = java.lang.Long.compare(midVal.getBestF(), f);

      if (cmp > 0) {
        low = (mid + 1);
      } else
        if (cmp < 0) {
          high = (mid - 1);
        } else {
          return midVal;
        }
    }

    if (low <= 0) {
      return ps[0];
    }
    if (low > sh) {
      return null;
    }
    return ps[low];
  }

  /**
   * Get the data point representing the information available at the given
   * best known objective value (see
   * <code>{@link org.logisticPlanning.tsp.benchmarking.objective.LogPoint#getBestF() LogPointgetBestF()}</code>
   * )
   *
   * @param f
   *          the best known objective value (see
   *          <code>{@link org.logisticPlanning.tsp.benchmarking.objective.LogPoint#getBestF() LogPointgetBestF()}</code>
   *          )
   * @return the data point representing the information available at the
   *         given best known objective value (see
   *         <code>{@link org.logisticPlanning.tsp.benchmarking.objective.LogPoint#getBestF() LogPointgetBestF()}</code>
   *         )
   */
  public final DataPoint findBestF(final double f) {
    final DataPoint[] ps;
    DataPoint midVal;
    int low, sh, high, mid, cmp;

    ps = this.m_data;
    low = 0;
    sh = high = (ps.length - 1);

    while (low <= high) {
      mid = ((low + high) >>> 1);
      midVal = ps[mid];
      cmp = java.lang.Double.compare(midVal.getBestF(), f);

      if (cmp > 0) {
        low = (mid + 1);
      } else
        if (cmp < 0) {
          high = (mid - 1);
        } else {
          return midVal;
        }
    }

    if (low <= 0) {
      return ps[0];
    }
    if (low > sh) {
      return null;
    }
    return ps[low];
  }

  /**
   * Get the data point representing the information available at the given
   * relativized best known objective value (see
   * <code>{@link org.logisticPlanning.tsp.benchmarking.objective.DataPoint#getRelBestF() DataPoint.getRelBestF()}</code>
   * )
   *
   * @param f
   *          the relativized best known objective value (see
   *          <code>{@link org.logisticPlanning.tsp.benchmarking.objective.DataPoint#getRelBestF() DataPoint.getRelBestF()}</code>
   *          )
   * @return the data point representing the information available at the
   *         given relativized best known objective value (see
   *         <code>{@link org.logisticPlanning.tsp.benchmarking.objective.DataPoint#getRelBestF() DataPoint.getRelBestF()}</code>
   *         )
   */
  public final DataPoint findRelBestF(final double f) {
    final DataPoint[] ps;
    DataPoint midVal;
    int low, sh, high, mid, cmp;

    ps = this.m_data;
    low = 0;
    sh = high = (ps.length - 1);

    while (low <= high) {
      mid = ((low + high) >>> 1);
      midVal = ps[mid];
      cmp = java.lang.Double.compare(midVal.getRelBestF(), f);

      if (cmp > 0) {
        low = (mid + 1);
      } else
        if (cmp < 0) {
          high = (mid - 1);
        } else {
          return midVal;
        }
    }

    if (low <= 0) {
      return ps[0];
    }
    if (low > sh) {
      return null;
    }
    return ps[low];
  }

  /** {@inheritDoc} */
  @Override
  public final int dimension() {
    return DataPoint.DATA_POINT_DIMENSION;
  }

  /** {@inheritDoc} */
  @Override
  public double get(final int point, final int dimension) {
    return this.m_data[point].get(dimension);
  }
}
