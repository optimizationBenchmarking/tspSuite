package org.logisticPlanning.utils.io;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.ArrayList;

/**
 * This file class always represents canonical files.
 */
final class _CanonicalFile extends File {
  /** The serial version uid. */
  private static final long serialVersionUID = 1;

  /**
   * Create a canonical file by specifying a canonical path.
   * 
   * @param filename
   *          The canonical path.
   */
  _CanonicalFile(final String filename) {
    super(filename);
  }

  /**
   * Create a canonical file by specifying a canonical path.
   * 
   * @param parent
   *          the parent file
   * @param filename
   *          The canonical path.
   */
  private _CanonicalFile(final _CanonicalFile parent, final String filename) {
    super(parent, filename);
  }

  /** {@inheritDoc} */
  @Override
  public final _CanonicalFile getParentFile() {
    final String s;

    s = this.getParent();
    return (s != null) ? new _CanonicalFile(s) : null;
  }

  /** {@inheritDoc} */
  @Override
  public final _CanonicalFile getAbsoluteFile() {
    return this;
  }

  /** {@inheritDoc} */
  @Override
  public final _CanonicalFile getCanonicalFile() {
    return this;
  }

  /** {@inheritDoc} */
  @Override
  public final String getAbsolutePath() {
    return this.getPath();
  }

  /** {@inheritDoc} */
  @Override
  public final String getCanonicalPath() {
    return this.getPath();
  }

  /** {@inheritDoc} */
  @Override
  public final boolean isAbsolute() {
    return true;
  }

  /** {@inheritDoc} */
  @Override
  public final _CanonicalFile[] listFiles() {
    String[] ss;
    int i, n;
    _CanonicalFile[] fs;

    ss = this.list();
    if (ss == null) {
      return null;
    }
    n = ss.length;
    fs = new _CanonicalFile[n];
    for (i = 0; i < n; i++) {
      fs[i] = new _CanonicalFile(this, ss[i]);
    }
    return fs;
  }

  /** {@inheritDoc} */
  @Override
  public final _CanonicalFile[] listFiles(final FilenameFilter filter) {
    String[] ss;
    int i, n;
    _CanonicalFile[] fs;

    ss = this.list(filter);
    if (ss == null) {
      return null;
    }
    n = ss.length;
    fs = new _CanonicalFile[n];
    for (i = 0; i < n; i++) {
      fs[i] = new _CanonicalFile(this, ss[i]);
    }
    return fs;
  }

  /** {@inheritDoc} */
  @Override
  public final _CanonicalFile[] listFiles(final FileFilter filter) {
    String[] ss;
    ArrayList<_CanonicalFile> v;
    int i, n;
    _CanonicalFile f;

    if (filter == null) {
      return this.listFiles();
    }

    ss = this.list();
    if (ss == null) {
      return null;
    }

    v = new ArrayList<>();
    n = ss.length;

    for (i = 0; i < n; i++) {
      f = new _CanonicalFile(this, ss[i]);
      if (filter.accept(f)) {
        v.add(f);
      }
    }

    return v.toArray(new _CanonicalFile[v.size()]);
  }

  /** {@inheritDoc} */
  @Override
  public final String toString() {
    return this.getCanonicalPath();
  }

}
