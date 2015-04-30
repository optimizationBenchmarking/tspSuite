package test.junit;

import java.io.File;
import java.io.IOException;

import org.junit.Assert;

/**
 * a temp directory
 */
public final class TempDir implements AutoCloseable {

  /** the internal synchronizer */
  private static final Object SYNC = new Object();

  /** the directory */
  private File m_file;

  /** instantiate */
  public TempDir() {
    super();
  }

  /**
   * Get the temporary directory
   * 
   * @return the temporary directory
   * @throws IOException
   *           if something fails
   */
  public final File getDir() throws IOException {
    synchronized (TempDir.SYNC) {
      if (this.m_file == null) {

        this.m_file = File.createTempFile(// //
            "test" + //$NON-NLS-1$
                Integer.toHexString(//
                    (int) (System.currentTimeMillis() & 0xfffffffl)),//
            null);
        Assert.assertNotNull("Temp-File creation failed.", this.m_file);//$NON-NLS-1$

        this.m_file = this.m_file.getCanonicalFile();
        this.m_file.delete();

        Assert.assertTrue("Error when creating temporary directory.",//$NON-NLS-1$
            this.m_file.mkdirs());
        this.m_file.deleteOnExit();
      }
    }

    return this.m_file;
  }

  /** {@inheritDoc} */
  @Override
  public final void close() throws IOException {
    synchronized (TempDir.SYNC) {
      if (this.m_file != null) {
        try {
          TempDir.delete(this.m_file);
        } finally {
          this.m_file = null;
        }
      }
    }
  }

  /**
   * delete a file
   * 
   * @param f
   *          the file
   * @throws IOException
   *           the error
   */
  private static final void delete(final File f) throws IOException {
    final File[] fs;

    if (f == null) {
      return;
    }

    try {
      if (f.isDirectory()) {
        fs = f.listFiles();
        if (fs != null) {
          for (final File ff : fs) {
            TempDir.delete(ff);
          }
        }
      }
    } finally {
      f.delete();
    }
  }

}
