package org.logisticPlanning.utils.processes;

/**
 * a destroyer thread for processes that hang for too long
 */
final class _Destroyer extends Thread {

  /** the owner */
  private final AutoProcess m_owner;
  /** the maximum allowed hang time */
  private final long m_maxHang;

  /**
   * create the destroyer
   *
   * @param owner
   *          the owner
   * @param maxHang
   *          the maximum allowed hang time
   */
  _Destroyer(final AutoProcess owner, final long maxHang) {
    super();
    this.m_maxHang = maxHang;
    this.m_owner = owner;
  }

  /** {@inheritDoc} */
  @Override
  public final void run() {

    looper: for (;;) {

      synchronized (this.m_owner.m_waitForMe) {
        if (this.m_owner.m_terminated) {
          return;
        }

        if ((System.currentTimeMillis() - this.m_maxHang) > this.m_owner.m_lastLifeSign) {
          this.m_owner.m_process.destroy();
          break looper;
        }
        try {
          synchronized (this.m_owner.m_waitForMe) {
            this.m_owner.m_waitForMe.wait(this.m_maxHang);
          }
        } catch (final Throwable t) {
          this.m_owner._logError(//
              "error during waiting in destroyer thread.", t); //$NON-NLS-1$
        }
      }
    }

    this.m_owner._logError(//
        "process was killed due to being unresponsive for more than " + //$NON-NLS-1$
            this.m_maxHang + " ms.", null);//$NON-NLS-1$
  }
}
