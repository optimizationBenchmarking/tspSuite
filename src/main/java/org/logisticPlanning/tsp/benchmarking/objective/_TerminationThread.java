package org.logisticPlanning.tsp.benchmarking.objective;

/**
 * <p>
 * This class is an internal class. Please do not instantiate it, use it by
 * yourself, or otherwise meddle with it.
 * </p>
 * This internal {@link java.lang.Thread thread} takes care of end times.
 * We could also check whether a runtime limit is reached by calling
 * {@link java.lang.System#currentTimeMillis()}, but this would be costly
 * (system calls!) and waste time during optimization. Thus, instead, we
 * have this thread which {@link java.lang.Object#wait(long) sleeps} until
 * the time limit of an objective function is reached and then updates its
 * {@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction#shouldTerminate()
 * termination criterion}. As one thread is shared for all instances of
 * {@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction
 * ObjectiveFunction}, this method is very resource-friendly and saves
 * runtime.</p>
 */
final class _TerminationThread extends Thread {

  /** the synchronizer */
  private static final Object SYNC = new Object();

  /** the queue */
  private static volatile ObjectiveFunction s_queue = null;
  /** the instance */
  private static volatile _TerminationThread s_instance = null;

  /** create */
  private _TerminationThread() {
    super();

    this.setDaemon(true);
  }

  /**
   * enqueue the objective function
   *
   * @param f
   *          the function
   */
  static final void _enqueue(final ObjectiveFunction f) {
    final long t;
    ObjectiveFunction prev, next;

    if (f == null) {
      return;
    }
    t = f.m_endTime;
    if ((t >= Long.MAX_VALUE) || (t <= 0l)) {
      return;
    }

    synchronized (_TerminationThread.SYNC) {

      prev = null;
      next = _TerminationThread.s_queue;

      while (next != null) {
        if (next == f) {
          return;
        }
        if (next.m_endTime > f.m_endTime) {
          break;
        }
        prev = next;
        next = next.m_next;
      }

      f.m_next = next;
      if (prev != null) {
        prev.m_next = f;
        return;
      }

      _TerminationThread.s_queue = f;

      if (_TerminationThread.s_instance == null) {
        _TerminationThread.s_instance = new _TerminationThread();
        _TerminationThread.s_instance.start();
        return;
      }

      _TerminationThread.SYNC.notifyAll();
    }
  }

  /**
   * dequeue an objective function
   *
   * @param f
   *          the function
   */
  static final void _dequeue(final ObjectiveFunction f) {
    ObjectiveFunction cur, next;
    if (f == null) {
      return;
    }

    synchronized (_TerminationThread.SYNC) {
      cur = null;
      next = _TerminationThread.s_queue;

      while (next != null) {
        if (next == f) {
          if (cur == null) {
            _TerminationThread.s_queue = next.m_next;
            _TerminationThread.SYNC.notifyAll();
            return;
          }
          cur.m_next = next.m_next;
          return;
        }
        cur = next;
        next = next.m_next;
      }
    }
  }

  /** {@inheritDoc} */
  @Override
  public final void run() {
    long time;

    for (;;) {
      time = System.currentTimeMillis();

      synchronized (_TerminationThread.SYNC) {

        inner: for (;;) {
          if (_TerminationThread.s_queue == null) {
            _TerminationThread.s_instance = null;
            return;
          }

          if (_TerminationThread.s_queue.m_endTime <= time) {
            _TerminationThread.s_queue.m_terminate = true;
            _TerminationThread.s_queue = _TerminationThread.s_queue.m_next;
          } else {
            break inner;
          }
        }

        try {
          _TerminationThread.SYNC.wait(Math.max(0l,
              (_TerminationThread.s_queue.m_endTime - time)));
        } catch (final InterruptedException ie) {
          continue;
        }
      }
    }
  }
}
