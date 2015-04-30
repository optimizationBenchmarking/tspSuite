package org.logisticPlanning.utils.io;

import java.io.File;
import java.security.PrivilegedAction;

/**
 * This small private class helps to canonicalize a file by tunneling this
 * operation through a <code>PrivilegedAction</code>.
 */
final class _Canonicalizer implements PrivilegedAction<String> {
  /** The file to canonicalize. */
  private final File m_file;

  /**
   * The constructor of the canonicalizer.
   * 
   * @param file
   *          The to be canonicalized.
   */
  _Canonicalizer(final File file) {
    super();
    this.m_file = file;
  }

  /** {@inheritDoc} */
  @Override
  public final String run() {
    try {
      return this.m_file.getCanonicalPath();
    } catch (final Throwable io) {
      try {
        return this.m_file.getAbsolutePath();
      } catch (final Throwable t2) {
        return this.m_file.getPath();
      }
    }
  }

}
