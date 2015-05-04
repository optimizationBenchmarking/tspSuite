package org.logisticPlanning.utils.io;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.security.AccessController;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.logisticPlanning.utils.text.TextUtils;

/**
 * The common files an application should know about.
 */
public final class FileUtils {

  /** The standard suffix for text file */
  public static final String TEXT_FILE_SUFFIX = "txt";//$NON-NLS-1$

  /** The current directory. */
  public static final File CURRENT_DIR = //
      FileUtils.canonicalize(new File("."));//$NON-NLS-1$

  /** The java home directory. */
  private static final File HOME_DIR = FileUtils.canonicalize(//
      System.getProperty("java.home")); //$NON-NLS-1$;

  /** The user directory. */
  private static final File USER_DIR = FileUtils.canonicalize(//
      System.getProperty("user.dir")); //$NON-NLS-1$;

  /** The temp directory. */
  private static final File TEMP_DIR;

  static {
    File f1, f2;

    f1 = FileUtils.canonicalize(//
        System.getProperty("java.io.tmpdir")); //$NON-NLS-1$;
    if (f1 == null) {
      try {
        f2 = File.createTempFile("test", "test");//$NON-NLS-1$;//$NON-NLS-2$;
        if (f2 != null) {
          try {
            f1 = FileUtils.canonicalize(f2.getParentFile());
          } finally {
            f2.delete();
          }
        }
      } catch (final Throwable t) {
        // ignored
      }
    }

    TEMP_DIR = f1;
  }

  /** The path list. */
  private static final File[] PATH = FileUtils.__getPath();

  /** extensions for executables */
  private static final String[] EXECUTABLE_EXTENSIONS = //
      FileUtils.__getExecutableExtensions();

  /**
   * Canonicalize a file
   *
   * @param f
   *          the file
   * @return the canonicalized version of the file
   */
  public static final File canonicalize(final File f) {

    if (f == null) {
      return null;
    }

    if (f instanceof _CanonicalFile) {
      return f;
    }

    try {
      return new _CanonicalFile(
          AccessController.doPrivileged(new _Canonicalizer(f)));
    } catch (final Throwable t) {
      // Changed from "return null"
      // Originally, we would return null here, to indicate that the file
      // cannot be canonicalized. Now we will try to canonicalize it in a
      // "best effort" approach.
      try {
        return f.getCanonicalFile();
      } catch (final Throwable t2) {
        try {
          return f.getAbsoluteFile();
        } catch (final Throwable t3) {
          return null;
        }
      }
    }
  }

  /**
   * Canonicalize a file
   *
   * @param s
   *          the file name
   * @return the canonicalized version of the file
   */
  public static final File canonicalize(final String s) {
    final String t;

    t = TextUtils.prepare(s);
    if (t != null) {
      return FileUtils.canonicalize(new File(t));
    }
    return FileUtils.CURRENT_DIR;
  }

  /**
   * Obtain the executable extensions
   *
   * @return the executable extensions
   */
  private static final String[] __getExecutableExtensions() {
    final ArrayList<String> l;
    String s;
    String[] ext;
    int i, j;

    l = new ArrayList<>();
    l.add(".exe"); //$NON-NLS-1$
    l.add(".bat"); //$NON-NLS-1$
    l.add(".com"); //$NON-NLS-1$
    l.add(".sh"); //$NON-NLS-1$

    s = TextUtils.prepare(System.getenv("PATHEXT")); //$NON-NLS-1$
    if (s == null) {
      s = TextUtils.prepare(System.getenv("pathext")); //$NON-NLS-1$
    }

    if (s != null) {
      ext = s.split(File.pathSeparator);
      if (ext != null) {
        j = ext.length;
        for (i = (ext.length - 1); i >= 0; i--) {
          s = TextUtils.prepare(ext[i]);
          if (s != null) {
            s = s.toLowerCase();
            if (!(l.contains(s))) {
              l.add(s);
            }
          }
        }
      }
    }

    j = l.size();
    ext = new String[j];

    for (i = 0, --j; j >= 0; j--, i++) {
      ext[i] = l.get(j);
    }

    return ext;
  }

  /**
   * Get the environment's path variable and add all the paths which could
   * potentially be interesting
   *
   * @return the environment's path variable
   */
  private static final _CanonicalFile[] __getPath() {
    String s;
    String[] x;
    int i;
    final int l;
    final HashSet<File> cf;
    File f;

    cf = new HashSet<>();

    find: {
      s = TextUtils.prepare(System.getenv("path")); //$NON-NLS-1$
      if (s == null) {
        s = TextUtils.prepare(System.getenv("$path")); //$NON-NLS-1$
        if (s == null) {
          s = TextUtils.prepare(System.getenv("%PATH%")); //$NON-NLS-1$
          if (s == null) {
            s = TextUtils.prepare(System.getenv("$PATH")); //$NON-NLS-1$
            if (s == null) {
              break find;
            }
          }
        }
      }

      x = s.split(File.pathSeparator);
      if ((x == null) || ((l = x.length) <= 0)) {
        return new _CanonicalFile[0];
      }

      for (i = 0; i < l; i++) {
        f = FileUtils.canonicalize(new File(x[i]));
        if ((f != null) && (f.exists())) {
          cf.add(f);
        }
      }
    }

    for (final String def : new String[] { "c:/program files", //$NON-NLS-1$
        "c:/program files (x86)", //$NON-NLS-1$
        "/usr/bin", //$NON-NLS-1$
        "/bin", //$NON-NLS-1$
    }) {
      f = FileUtils.canonicalize(def);
      if ((f != null) && (f.exists())) {
        cf.add(f);
      }
    }

    cf.add(FileUtils.HOME_DIR);
    cf.add(FileUtils.USER_DIR);
    cf.add(FileUtils.TEMP_DIR);

    return cf.toArray(new _CanonicalFile[cf.size()]);
  }

  /**
   * Delete the file or directory specified by <code>f</code>, recursively
   * if necessary
   *
   * @param f
   *          the file or directory to be deleted
   * @throws IOException
   *           if io fails
   */
  public static final void delete(final File f) throws IOException {
    File[] fs;

    if (f == null) {
      return;
    }
    if (!(f.exists())) {
      return;
    }

    if (f.isDirectory()) {
      fs = f.listFiles();
      if (fs != null) {
        for (final File d : fs) {
          FileUtils.delete(d);
        }
      }
    }

    try {
      f.delete();
    } catch (final Throwable t) {
      throw new IOException(t);
    }
  }

  /**
   * Copy a set of files recursively to a given directory
   *
   * @param srcFiles
   *          the source files (or directories)
   * @param destDir
   *          the destination directory
   * @throws IOException
   *           if something goes wrong
   */
  public static final void copy(final File[] srcFiles, final File destDir)
      throws IOException {

    if (srcFiles == null) {
      return;
    }
    if (destDir == null) {
      return;
    }

    for (final File f : srcFiles) {
      FileUtils.copy(f, destDir);
    }

  }

  /**
   * Copy a file or directory recursively
   *
   * @param src
   *          the source file
   * @param destDir
   *          the destination directory
   * @throws IOException
   *           if something goes wrong
   */
  public static final void copy(final File src, final File destDir)
      throws IOException {
    File f;

    if (src == null) {
      return;
    }
    if (destDir == null) {
      return;
    }

    if (src.exists()) {
      if (src.isDirectory()) {
        f = FileUtils.canonicalize(new File(destDir, src.getName()));
        f.mkdirs();

        FileUtils.copy(src.listFiles(), f);
      } else {
        Transcoder.copyBinaryFileToBinaryFile(src,
            FileUtils.canonicalize(new File(destDir, src.getName())));
      }
    }
  }

  /**
   * Move a set of files to a given directory
   *
   * @param src
   *          the source files
   * @param destDir
   *          the destination directory
   * @throws IOException
   *           if something goes wrong
   */
  public static final void move(final File[] src, final File destDir)
      throws IOException {
    final File dest;

    if ((src == null) || (src.length <= 0)) {
      return;
    }

    dest = FileUtils.canonicalize(destDir);
    if (dest == null) {
      return;
    }

    for (final File f : src) {
      FileUtils.move(FileUtils.canonicalize(f), dest);
    }
  }

  /**
   * Move a file or folder {@code src} to a given directory {@code destDir}
   * . If a file or directory with the same name as {@code src} already
   * exists in {@code destDir}, then this file or directoy is deleted
   * first.
   *
   * @param src
   *          the source file
   * @param destDir
   *          the destination directory
   * @throws IOException
   *           if something goes wrong
   */
  public static final void move(final File src, final File destDir)
      throws IOException {
    final File s, dd, d;

    if (src == null) {
      return;
    }

    s = FileUtils.canonicalize(src);
    if (s == null) {
      return;
    }

    checkDest: {
      if (destDir != null) {
        dd = FileUtils.canonicalize(destDir);
        if (dd != null) {
          d = FileUtils.canonicalize(new File(dd, s.getName()));
          if (d != null) {
            break checkDest;
          }
        }
      }

      throw new IOException("Dest directory '" + destDir + //$NON-NLS-1$
          "' is invalid.");//$NON-NLS-1$
    }

    if (d.equals(s)) {
      // source and destination are equal, no need to do anything
      return;
    }

    // try to use the modern method
    if (src.isFile()) {
      try {
        java.nio.file.Files.move(src.toPath(), d.toPath(),
            java.nio.file.StandardCopyOption.REPLACE_EXISTING);
      } catch (final IOException ioe) {
        // ignore here
      }
    }

    // First, we need to make sure that the destination file does not exist
    FileUtils.delete(d);

    try {
      if (s.renameTo(d)) {
        // if direct renaming works, we win: fast moving
        return;
      }
    } catch (final Throwable t) {
      throw new IOException(t);
    }

    // But maybe that does not work, maybe the destination is on an
    // entirely
    // different location or file system. Then we first copy the source
    // files/directories into the destination directory.
    FileUtils.copy(s, dd);
    // And then we delete the sources, and thereby also realize a move.
    FileUtils.delete(s);
  }

  /**
   * Check whether the given file name can be found in the path.
   *
   * @param name
   *          the file name
   * @return the corresponding file or <code>null</code> if the file was
   *         not found
   */
  public static final File findInPath(final String name) {
    File f;

    for (final File p : FileUtils.PATH) {
      f = new File(p, name);
      if (f.exists() && f.isFile() && (f.length() > 0l)) {
        return FileUtils.canonicalize(f);
      }
    }

    return null;
  }

  /**
   * Find an existing executable file in the given directory
   *
   * @param name
   *          the file name
   * @param dir
   *          the directory
   * @return the existing file or <code>null</code> if none was found
   */
  public static final File findExecutableInDir(final String name,
      final File dir) {
    File f, n;

    if (dir == null) {
      return null;
    }
    if (name == null) {
      return null;
    }

    f = FileUtils.canonicalize(dir);

    for (final String ext : FileUtils.EXECUTABLE_EXTENSIONS) {
      n = new File(f, name + ext);
      if (n.exists() && n.isFile()) {
        return FileUtils.canonicalize(n);
      }
    }

    return null;
  }

  /**
   * Check whether the given executable file name can be found in the path.
   *
   * @param name
   *          the file name
   * @return the corresponding file or <code>null</code> if the file was
   *         not found
   */
  public static final File findExecutableInPath(final String name) {
    File f;

    for (final String ext : FileUtils.EXECUTABLE_EXTENSIONS) {
      f = FileUtils.findInPath(name + ext);
      if (f != null) {
        return f;
      }
    }

    return FileUtils.findInPath(name);
  }

  /**
   * Find an existing file
   *
   * @param name
   *          the file name
   * @param dir
   *          the directory
   * @return the existing file or <code>null</code> if none was found
   */
  public static final File findFile(final String name, final File dir) {
    final File[] fs;
    File c;

    c = new File(dir, name);
    if (c.exists()) {
      return FileUtils.canonicalize(c);
    }

    fs = dir.listFiles();
    if (fs != null) {
      for (final File p : fs) {
        if (p.isDirectory()) {
          c = FileUtils.findFile(name, p);
          if (c != null) {
            return c;
          }
        }
      }
    }

    return null;
  }

  /**
   * Get the relative path of file in relation to {@code dir}
   *
   * @param dir
   *          the directory
   * @param file
   *          the file
   * @return the relative path
   */
  public static final String getRelativeName(final File dir,
      final File file) {
    File a, b;
    String xa, xb, xxa, xxb, res;
    java.nio.file.Path pa, pb, pr;
    final StringBuilder path;
    int equalEnds, nextA, nextB, dirLen;

    if (file == null) {
      return null;
    }

    if (dir == null) {
      try {
        return file.getCanonicalPath();
      } catch (final Throwable t) {
        try {
          return file.getAbsolutePath();
        } catch (final Throwable t2) {
          return file.getPath();
        }
      }
    }

    a = dir;
    try {
      a = FileUtils.canonicalize(a);
    } catch (final Throwable t) {
      //
    }
    try {
      xa = a.getCanonicalPath();
    } catch (final Throwable t) {
      try {
        xa = a.getAbsolutePath();
      } catch (final Throwable t2) {
        xa = a.getPath();
      }
    }

    b = file;
    try {
      b = FileUtils.canonicalize(b);
    } catch (final Throwable t) {
      //
    }
    try {
      xb = b.getCanonicalPath();
    } catch (final Throwable t) {
      try {
        xb = b.getAbsolutePath();
      } catch (final Throwable t2) {
        xb = b.getPath();
      }
    }

    if (File.separatorChar != '/') {
      xxa = xa.replace(File.separatorChar, '/');
      xxb = xb.replace(File.separatorChar, '/');
    } else {
      xxa = xa;
      xxb = xb;
    }

    pa = java.nio.file.Paths.get(xxa);
    if (pa != null) {
      pb = java.nio.file.Paths.get(xxb);
      pr = pa.relativize(pb);
      if (pr != null) {
        res = pr.toString();
        if (res != null) {
          if (File.separatorChar != '/') {
            return res.replace('/', File.separatorChar);
          }
          return res;
        }
      }
    }

    // fallback solution

    if (a.isFile()) {
      a = a.getParentFile();
      if (a == null) {
        return xb;
      }
      xa = a.getPath();
    }

    dirLen = xa.length();
    if (dirLen <= 0) {
      return xb;
    }

    if (xa.charAt(dirLen - 1) != File.separatorChar) {
      xa += File.separatorChar;
      dirLen++;
    }
    equalEnds = 0;
    for (;;) {
      nextA = xb.indexOf(File.separatorChar, equalEnds);
      nextB = xa.indexOf(File.separatorChar, equalEnds);

      if (nextA != nextB) {
        break;
      }
      if (xb.startsWith(xa.substring(0, nextB))) {
        equalEnds = (nextA + 1);
      } else {
        break;
      }
    }

    path = new StringBuilder();
    nextB = equalEnds;
    while ((nextB >= 0) && (nextB < dirLen)) {
      path.append('.');
      path.append('.');
      path.append(File.separatorChar);
      nextB = xa.indexOf(File.separatorChar, (nextB + 1));
    }
    path.append(xb.substring(equalEnds));
    return path.toString();
  }

  /**
   * Find all files in the directory
   *
   * @param dir
   *          the directory
   * @param ending
   *          the ending
   * @param l
   *          the list to attach all discovered files to
   */
  public static final void findAllFiles(final File dir,
      final String ending, final List<File> l) {
    final File[] f;
    final File c;
    String n;

    c = FileUtils.canonicalize(dir);
    if (c == null) {
      return;
    }

    f = c.listFiles();
    if (f == null) {
      return;
    }

    for (final File x : f) {
      if ((x == null) || (!(x.exists()))) {
        continue;
      }

      if (x.isFile()) {
        if (ending == null) {
          l.add(x);
        } else {
          n = x.getName();
          if ((n != null) && (n.endsWith(ending))) {
            l.add(x);
          }
        }
      } else {
        if (x.isDirectory()) {
          FileUtils.findAllFiles(x, ending, l);
        }
      }
    }
  }

  /**
   * Find the directory containing all files
   *
   * @param files
   *          the files
   * @return the file containing the parameters
   */
  public static final File getContainingPath(final File... files) {
    if (files == null) {
      return null;
    }
    return FileUtils.getContainingPath(files, 0, files.length);
  }

  /**
   * Find the directory containing all files
   *
   * @param files
   *          the files
   * @param start
   *          the starting index
   * @param count
   *          the number of files
   * @return the file containing the parameters
   */
  public static final File getContainingPath(final File[] files,
      final int start, final int count) {
    File cur;
    String pathS, curS;
    int i, pathL, curL;

    pathS = null;
    pathL = 0;

    outer: for (i = (start + count); (--i) >= start;) {
      cur = files[i];

      if (cur == null) {
        continue outer;
      }

      if (pathS == null) {
        pathS = FileUtils.canonicalize(cur).getPath();
        pathL = pathS.length();
        continue outer;
      }

      curS = FileUtils.canonicalize(cur).getPath();

      if (pathS.equals(curS)) {
        continue outer;
      }

      curL = curS.length();

      inner: for (;;) {
        while (curL > pathL) {
          curL = curS.lastIndexOf(File.separatorChar, curL - 1);
        }
        if (curL <= 0) {
          return null;
        }
        curS = curS.substring(0, curL);

        while (pathL > curL) {
          pathL = pathS.lastIndexOf(File.separatorChar, pathL - 1);
        }
        if (pathL <= 0) {
          return null;
        }
        pathS = pathS.substring(0, pathL);

        if (pathS.equals(curS)) {
          break inner;
        }

        pathL--;
      }
    }

    return ((pathS != null) ? FileUtils.canonicalize(new File(pathS))
        : null);
  }

  /**
   * get a file that points to a given uri
   *
   * @param base
   *          the base dir, or {@code null} to use current directory
   * @param uri
   *          the uri
   * @return the file
   */
  public static final File uriToFile(final File base, final URI uri) {
    String name;
    File file;
    try {
      file = new File(uri);
    } catch (final Throwable t) {
      name = uri.toString();
      if (File.separatorChar != '/') {
        name = name.replace('/', File.separatorChar);
      }
      file = new File((base != null) ? base : FileUtils.CURRENT_DIR, name);
    }
    return FileUtils.canonicalize(file);
  }

  /**
   * get a file that points to a given uri
   *
   * @param uri
   *          the uri
   * @return the file
   */
  public static final File uriToFile(final URI uri) {
    return FileUtils.uriToFile(FileUtils.CURRENT_DIR, uri);
  }
}
