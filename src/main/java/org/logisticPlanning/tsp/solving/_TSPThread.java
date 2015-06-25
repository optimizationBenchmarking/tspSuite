package org.logisticPlanning.tsp.solving;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.logisticPlanning.tsp.benchmarking.objective.Benchmark;
import org.logisticPlanning.tsp.benchmarking.objective.CreatorInfo;
import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;
import org.logisticPlanning.utils.utils.MemoryUtils;

/**
 * <p>
 * This class is an internal class. Please do not instantiate it, use it by
 * yourself, or otherwise meddle with it.
 * </p>
 * <p>
 * The worker thread executing the TSP solving algorithms.
 * </p>
 */
final class _TSPThread extends Thread {

  /** the source class */
  private static final String SOURCE_CLASS = _TSPThread.class
      .getCanonicalName();
  /** the source class */
  private static final String SOURCE_METHOD = "run"; //$NON-NLS-1$
  /** the constructor */
  private static final String SOURCE_CONSTRUCTOR = "<init>"; //$NON-NLS-1$

  /** the algorithm */
  private final TSPAlgorithm m_algo;

  /** the initialization algorithm */
  private final TSPAlgorithm m_init;

  /** the queue */
  private final _TSPQueue m_queue;

  /** the creator information */
  private final CreatorInfo m_creator;

  /** the logger */
  private final Logger m_log;

  /** the log string for no more jobs */
  private final String m_noMoreJobs;
  /** the log string for caught errors */
  private final String m_errorCaught;
  /** the log string for completed jobs */
  private final String m_jobCompleted;
  /** the log string for a severe error */
  private final String m_severe;

  /**
   * Create
   *
   * @param algo
   *          the algorithm
   * @param init
   *          the algorithm initializer
   * @param queue
   *          the queue
   * @param creator
   *          the creator info
   * @param log
   *          the logger
   */
  _TSPThread(final TSPAlgorithm algo, final TSPAlgorithm init,
      final _TSPQueue queue, final CreatorInfo creator, final Logger log) {
    super();
    this.m_algo = algo;
    this.m_init = init;
    this.m_queue = queue;
    this.m_creator = creator;
    this.m_log = log;

    if ((log != null) && (log.isLoggable(Level.INFO))) {
      log.logp(Level.INFO, _TSPThread.SOURCE_CLASS,
          _TSPThread.SOURCE_CONSTRUCTOR,
          ("Executor thread #" + this.getId() + //$NON-NLS-1$
          " created.")); //$NON-NLS-1$
    }

    if (log != null) {
      this.m_noMoreJobs = ("No more jobs available for thread #" + this.getId() + //$NON-NLS-1$
      " - thread quits."); //$NON-NLS-1$
      this.m_errorCaught = ("Error caught in thread #" + this.getId() + //$NON-NLS-1$
      " - storing it in objective function for logging. Benchmark instance was ");//$NON-NLS-1$
      this.m_jobCompleted = ("One job completed by thread #" + this.getId() + //$NON-NLS-1$
      " for benchmark ");//$NON-NLS-1$
      this.m_severe = ("Unexpected error caught in thread #" + this.getId());//$NON-NLS-1$
    } else {
      this.m_noMoreJobs = null;
      this.m_errorCaught = null;
      this.m_jobCompleted = null;
      this.m_severe = null;
    }
  }

  /** {@inheritDoc} */
  @Override
  public final void run() {
    final _TSPQueue q;
    final TSPAlgorithm init, algo;
    final Logger log;
    Benchmark bm, nbm;
    ObjectiveFunction f;

    q = this.m_queue;
    init = this.m_init;
    algo = this.m_algo;
    log = this.m_log;

    bm = null;
    f = null;
    try {

      if (init != null) {
        for (;;) {

          synchronized (q) {
            nbm = q.next();

            if (nbm == null) {
              if ((log != null) && (log.isLoggable(Level.INFO))) {
                log.logp(Level.INFO, _TSPThread.SOURCE_CLASS,
                    _TSPThread.SOURCE_METHOD, this.m_noMoreJobs);
              }
              return;
            }

            if ((nbm != bm) || (f == null)) {
              bm = nbm;
              f = bm.createObjective();
              f.setCreatorInfo(this.m_creator);
            }

            // initialization procedure is always executed: get
            // better runtime
            // estimate
            f.beginDeterministicInitialization(init);
          }

          try {
            try {
              init.call(f);
            } catch (final Throwable z) {
              try {
                f.setExceptionDuringInitalization(z);
                MemoryUtils.gc();
              } finally {
                if ((log != null) && log.isLoggable(Level.SEVERE)) {
                  log.logp(Level.SEVERE, _TSPThread.SOURCE_CLASS,
                      _TSPThread.SOURCE_METHOD,
                      (this.m_errorCaught + nbm.name()), z);
                }
              }
            }
          } finally {
            f.endDeterministicInitialization();
          }

          f.beginRun(algo);
          try {
            try {
              algo.call(f);
            } catch (final Throwable z) {
              try {
                f.setExceptionDuringRun(z);
                MemoryUtils.gc();
              } finally {
                if ((log != null) && log.isLoggable(Level.SEVERE)) {
                  log.logp(Level.SEVERE, _TSPThread.SOURCE_CLASS,
                      _TSPThread.SOURCE_METHOD,
                      (this.m_errorCaught + nbm.name()), z);
                }
              }
            }
          } finally {
            f.endRun();
          }

          if ((log != null) && log.isLoggable(Level.INFO)) {
            log.logp(Level.INFO, _TSPThread.SOURCE_CLASS,
                _TSPThread.SOURCE_METHOD,
                (this.m_jobCompleted + nbm.name()));
          }
        }
      }

      for (;;) {
        synchronized (q) {
          nbm = q.next();

          if (nbm == null) {
            if ((log != null) && (log.isLoggable(Level.INFO))) {
              log.logp(Level.INFO, _TSPThread.SOURCE_CLASS,
                  _TSPThread.SOURCE_METHOD, this.m_noMoreJobs);
            }
            return;
          }

          if ((nbm != bm) || (f == null)) {
            bm = nbm;
            f = bm.createObjective();
            f.setCreatorInfo(this.m_creator);
          }

          f.beginRun(algo);
        }

        try {
          try {
            algo.call(f);
          } catch (final Throwable z) {
            try {
              f.setExceptionDuringRun(z);
              MemoryUtils.gc();
            } finally {
              if ((log != null) && log.isLoggable(Level.SEVERE)) {
                log.logp(Level.SEVERE, _TSPThread.SOURCE_CLASS,
                    _TSPThread.SOURCE_METHOD,
                    (this.m_errorCaught + nbm.name()), z);
              }
            }
          }
        } finally {
          f.endRun();
        }

        if ((log != null) && log.isLoggable(Level.INFO)) {
          log.logp(Level.INFO, _TSPThread.SOURCE_CLASS,
              _TSPThread.SOURCE_METHOD, (this.m_jobCompleted + nbm.name()));
        }
      }
    } catch (final Throwable t) {
      try {
        MemoryUtils.gc();
      } finally {
        if ((log != null) && (log.isLoggable(Level.SEVERE))) {
          log.logp(Level.SEVERE, _TSPThread.SOURCE_CLASS,
              _TSPThread.SOURCE_METHOD, this.m_severe, t);
        } else {
          t.printStackTrace();
        }
      }
    }
  }
}
