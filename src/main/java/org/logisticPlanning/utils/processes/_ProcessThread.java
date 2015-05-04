package org.logisticPlanning.utils.processes;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.logisticPlanning.utils.io.EEncoding;

/**
 * An internal thread class that listens on one stream of a process and
 * logs its output.
 */
final class _ProcessThread extends Thread {

  /** the owning process */
  private final AutoProcess m_owner;

  /** the logger */
  final Logger m_log;

  /** the log level */
  final Level m_level;

  /** the input stream */
  private final InputStream m_is;

  /**
   * Create the auto process.
   *
   * @param owner
   *          the owner
   * @param log
   *          the logger
   * @param level
   *          the log level
   * @param is
   *          the input stream
   */
  _ProcessThread(final AutoProcess owner, final Logger log,
      final Level level, final InputStream is) {
    super();

    this.m_owner = owner;
    this.m_log = log;
    this.m_level = ((level != null) ? level : Level.INFO);
    this.m_is = is;
  }

  /** run, but do not read any input - skip instead */
  private final void __runNoRead() {
    final InputStream is;
    final Object lock;
    byte[] skipBuff;
    int a;

    is = this.m_is;
    lock = this.m_owner.m_waitForMe;

    skipBuff = AutoProcess.s_skipBuffer;
    if (skipBuff == null) {
      AutoProcess.s_skipBuffer = skipBuff = new byte[4096];
    }

    do {
      try {
        a = is.available();
        if (a > 0) {
          // for some reason, "skip" does not work here.
          a = is.read(skipBuff, 0, Math.min(skipBuff.length, a));
          if (a > 0) {
            synchronized (lock) {
              this.m_owner.m_lastLifeSign = System.currentTimeMillis();
            }
          } else {
            if (a < 0) {
              return;
            }
          }
        } else {
          synchronized (lock) {
            lock.wait(10l);
          }
        }
      } catch (final Throwable t) {
        this.m_owner._logError("error during skipping.", t); //$NON-NLS-1$
      }
    } while (!(this.m_owner.m_terminated));
  }

  /** run, and read the input */
  private final void __runRead() {
    final Object lock;
    final _TextBuffer buf;
    int read;
    boolean hasText, alive;

    lock = this.m_owner.m_waitForMe;
    buf = new _TextBuffer(this.m_owner.m_id);

    try (InputStreamReader isr = new InputStreamReader(this.m_is,
        EEncoding.DEFAULT_TEXT_ENCODING.getJavaName())) {
      alive = true;

      outer: do {
        hasText = false;

        read = 0;
        inner: while (alive && isr.ready()) {
          // ok, there is data ready to be read

          try {
            read = buf._load(isr); // load the data into the buffer
          } catch (final OutOfMemoryError oome) {
            this.m_owner._logError("out of buffer memory.", oome); //$NON-NLS-1$
            break inner; // need to flush buffers
          } catch (final Throwable t) {
            this.m_owner._logError("error during reading.", t); //$NON-NLS-1$
          }

          // _load has completed successfully
          if (read > 0) {
            hasText = true;
            synchronized (lock) {
              this.m_owner.m_lastLifeSign = System.currentTimeMillis();
            }
            continue inner;
          }

          if (read < 0) {// stream was closed - we should never get
            // here
            alive = false; // anyway, although this is odd: let's
            // leave
            break inner;
          }
        }

        // if we get here, there may be some text in the buffer and the
        // main
        // process may or may not be alive
        if (hasText) {
          // ok, we have text: log it!
          hasText = false; // text will be flushed
          try {
            if (this.m_log.isLoggable(this.m_level)) {
              this.m_log.log(this.m_level, buf._text());
            }
          } finally {
            buf._flush();
          }
        } else {
          // ok there was no data: we should probably wait a bit
          try {// wait 30ms until new data is available
            synchronized (lock) {
              if (this.m_owner.m_terminated) {// we are dead
                alive = false; // so we are not alive
                break outer;
              }
              lock.wait(30l); // otherwise we wait a bit
            }
          } catch (final Throwable t) {
            this.m_owner._logError("error during waiting.", //$NON-NLS-1$
                t);
          }
        }

        if (this.m_owner.m_terminated) {// we are dead
          alive = false;
          break outer;
        }
      } while (alive);

    } catch (final Throwable tt) {
      this.m_owner._logError("odd error, maybe during reader creation.", //$NON-NLS-1$
          tt);
    }
  }

  /** {@inheritDoc} */
  @Override
  public final void run() {
    if (this.m_log != null) {
      this.__runRead();
    } else {
      this.__runNoRead();
    }
  }

}
