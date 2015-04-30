package org.logisticPlanning.tsp.benchmarking.objective;

/**
 * <p>
 * This class is an internal class. Please do not instantiate it, use it by
 * yourself, or otherwise meddle with it.
 * </p>
 * <p>
 * A {@link org.logisticPlanning.tsp.benchmarking.objective.LogPoint log
 * point} representing the current state of the optimization algorithm.
 * This log point is special by providing a {@link #getConsumedRuntime()}
 * function which uses {@link java.lang.System#currentTimeMillis()} to
 * compute the runtime that has passed and which additionally updates the
 * {@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction#shouldTerminate()
 * termination criterion} of the
 * {@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction
 * objective function}. Thus calling {@link #getConsumedRuntime()} of
 * {@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction#getCurrentLogPoint()
 * f.getCurrentLogPoint()} (where {@code f} is the objective function) is
 * costly and should only be performed when absolutely necessary, i.e.,
 * when real time is required.
 * </p>
 */
final class _CurrentLogPoint extends LogPoint {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the owning objective function */
  private transient ObjectiveFunction m_owner;

  /**
   * instantiate
   * 
   * @param owner
   *          the owning objective function
   */
  _CurrentLogPoint(final ObjectiveFunction owner) {
    super();
    this.m_owner = owner;
  }

  /**
   * <p>
   * Get an estimate of the amount of runtime in milliseconds consumed
   * until the current point in time.
   * </p>
   * <p>
   * Warning: Calling this function is very time consuming, as it will
   * update the state of the owning objective function instance and lead to
   * a system call!
   * </p>
   * 
   * @return the amount of milliseconds consumed.
   */
  @Override
  public final long getConsumedRuntime() {
    if (this.m_owner != null) {
      this.m_owner._updateConsumedTime();
    }
    return this.m_time;
  }

  /**
   * Find a replacement to be written into a stream. As this log point is
   * linked to an objective function, we instead store a copy of it. This
   * is not a good solution, as writing the same object multiple times may
   * result in odd behavior, but for now it is OK.
   * 
   * @return the replacement
   */
  private final Object writeReplace() {
    return this.clone();
  }

  /**
   * Find a resolved version to be read from a stream. As this log point is
   * linked to an objective function, we instead return a copy of it.
   * 
   * @return the replacement
   */
  private final Object readResolve() {
    return this.clone();
  }

  /** {@inheritDoc} */
  @Override
  public final LogPoint clone() {
    return new LogPoint(this);
  }
}
