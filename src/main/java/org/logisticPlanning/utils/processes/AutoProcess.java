package org.logisticPlanning.utils.processes;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class wraps the class {@link java.lang.Process} and can log the std
 * out and std err of a process to loggers. It will read the data from the
 * process as default-encoded strings.
 */
public final class AutoProcess {

  /**
   * the time after which a process that does not write any output is
   * automatically killed: {@value} ms, i.e., 30 minutes
   */
  public static final long DEFAULT_MAX_HANG = (1000l * 60l * 30l);

  /** the shared skip buffer */
  static byte[] s_skipBuffer = null;

  /** the process */
  final Process m_process;

  /** the output thread */
  private final _ProcessThread m_out;

  /** the error thread */
  final _ProcessThread m_error;

  /** the destroyer thread */
  private final _Destroyer m_destroy;

  /** the process id */
  final String m_id;

  /** did we terminate? */
  volatile boolean m_terminated;

  /** the last life sign of the process */
  volatile long m_lastLifeSign;

  /** the object to use for internal waiting */
  final Object m_waitForMe;

  /**
   * Create the auto process.
   * 
   * @param outLog
   *          the output logger
   * @param outLevel
   *          the output log level
   * @param errorLog
   *          the error logger
   * @param errorLevel
   *          the error level
   * @param process
   *          the process to wrap
   * @param maxHang
   *          the maximum amount of ms a process is allowed to hang (being
   *          without output) before being terminated, -1l for no limit
   */
  public AutoProcess(final Logger outLog, final Level outLevel,
      final Logger errorLog, final Level errorLevel, final long maxHang,
      final Process process) {
    super();

    if (process == null) {
      throw new IllegalArgumentException("Process must not be null."); //$NON-NLS-1$
    }

    this.m_process = process;
    this.m_out = new _ProcessThread(this, outLog, outLevel,
        process.getInputStream());
    this.m_error = new _ProcessThread(this, errorLog, errorLevel,
        process.getErrorStream());

    if ((this.m_error.m_log != null) || (this.m_out.m_log != null)) {
      this.m_waitForMe = this.m_id = //
      ((("Process " + process.hashCode()) + ':') + ' ');//$NON-NLS-1$
    } else {
      this.m_id = "[no log]";//$NON-NLS-1$
      this.m_waitForMe = new Object();
    }

    this.m_terminated = false;
    this.m_lastLifeSign = System.currentTimeMillis();

    this.m_out.start();
    this.m_error.start();

    if (maxHang > 0l) {
      this.m_destroy = new _Destroyer(this, maxHang);
      this.m_destroy.start();
    } else {
      this.m_destroy = null;
    }
  }

  /**
   * Create the auto process.
   * 
   * @param log
   *          the logger
   * @param outLevel
   *          the output log level
   * @param errorLevel
   *          the error level
   * @param maxHang
   *          the maximum amount of ms a process is allowed to hang (being
   *          without output) before being terminated, -1l for no limit
   * @param process
   *          the process to wrap
   */
  public AutoProcess(final Logger log, final Level outLevel,
      final Level errorLevel, final long maxHang, final Process process) {
    this(log, outLevel, log, errorLevel, maxHang, process);
  }

  /**
   * Create the auto process.
   * 
   * @param log
   *          the logger
   * @param outLevel
   *          the output log level
   * @param maxHang
   *          the maximum amount of ms a process is allowed to hang (being
   *          without output) before being terminated, -1l for no limit
   * @param process
   *          the process to wrap
   */
  public AutoProcess(final Logger log, final Level outLevel,
      final long maxHang, final Process process) {
    this(log, outLevel, Level.SEVERE, maxHang, process);
  }

  /**
   * Create the auto process.
   * 
   * @param log
   *          the logger
   * @param outLevel
   *          the output log level
   * @param process
   *          the process to wrap
   */
  public AutoProcess(final Logger log, final Level outLevel,
      final Process process) {
    this(log, outLevel, Level.SEVERE, AutoProcess.DEFAULT_MAX_HANG,
        process);
  }

  /**
   * log an error
   * 
   * @param description
   *          the description of the error to be logged
   * @param t
   *          the error
   */
  final void _logError(final String description, final Throwable t) {
    Logger l;
    String s;

    l = this.m_error.m_log;
    if ((l == null) || (!(l.isLoggable(Level.SEVERE)))) {
      l = this.m_out.m_log;
    }

    if ((l != null) && (l.isLoggable(Level.SEVERE))) {
      s = this.m_id + ((description != null) ? description : "Error!");//$NON-NLS-1$

      if (t != null) {
        l.log(Level.SEVERE, s, t);
      } else {
        l.log(Level.SEVERE, s);
      }
    }
  }

  /**
   * Wait until the process has terminated.
   * 
   * @return the return code of the process
   */
  public final int waitFor() {
    int retCode;

    retCode = (-1);

    try {
      try {

        do {
          try {
            retCode = this.m_process.waitFor();
            this.m_terminated = true;
          } catch (final InterruptedException itse) {
            this._logError(
                "has not yet finished, but an InterruptedException was caught.", //$NON-NLS-1$
                itse);
          }
          synchronized (this.m_waitForMe) {
            this.m_waitForMe.notifyAll();
          }
        } while (!(this.m_terminated));
      } finally {
        try {
          this.m_out.join();
        } catch (final InterruptedException itse) {
          this._logError("Error in stdout thread", //$NON-NLS-1$
              itse);
        } finally {
          try {
            this.m_error.join();
          } catch (final InterruptedException itse) {
            this._logError("Error in stderr thread", //$NON-NLS-1$
                itse);
          } finally {
            if (this.m_destroy != null) {
              try {
                this.m_destroy.join();
              } catch (final InterruptedException itse) {
                this._logError("Error in destroyer thread", //$NON-NLS-1$
                    itse);
              }
            }
          }
        }
      }
    } finally {
      AutoProcess.s_skipBuffer = null;
      if ((this.m_out.m_log != null)
          && (this.m_out.m_log.isLoggable(this.m_out.m_level))) {
        this.m_out.m_log.log(this.m_out.m_level, //
            this.m_id + " terminated with exit code " + retCode); //$NON-NLS-1$
      }
    }

    return retCode;
  }

}
