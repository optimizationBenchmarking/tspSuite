package org.logisticPlanning.utils.config;

/**
 * the class error
 */
final class _ClassError extends IllegalArgumentException {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** illegal state exception: class does not fit */
  private static final String WRONG_CLASS_1 = "Error: instance of class "; //$NON-NLS-1$
  /** illegal state exception: class does not fit */
  private static final String WRONG_CLASS_2 = " stored in configuration, but requested is an instance of "; //$NON-NLS-1$

  /**
   * the class error
   * 
   * @param is
   *          the real class
   * @param must
   *          the must class
   */
  _ClassError(final Class<?> is, final Class<?> must) {
    super(_ClassError.WRONG_CLASS_1 + is + _ClassError.WRONG_CLASS_2
        + must);
  }

  /**
   * the class error
   * 
   * @param is
   *          the real class
   * @param must
   *          the must class
   * @param cause
   *          the causing error
   */
  _ClassError(final Class<?> is, final Class<?> must, final Throwable cause) {
    super(_ClassError.WRONG_CLASS_1 + is + _ClassError.WRONG_CLASS_2
        + must, cause);
  }

  /**
   * the class error
   * 
   * @param isContainer
   *          the real container class
   * @param is
   *          the real class
   * @param mustContainer
   *          the must container class
   * @param must
   *          the must class
   */
  _ClassError(final Class<?> isContainer, final Class<?> is,
      final Class<?> mustContainer, final Class<?> must) {
    super(_ClassError.WRONG_CLASS_1 + isContainer + '[' + is + ']'
        + _ClassError.WRONG_CLASS_2 + mustContainer + '[' + must + ']');
  }
}
