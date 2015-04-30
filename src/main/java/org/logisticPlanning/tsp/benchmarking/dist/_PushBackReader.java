package org.logisticPlanning.tsp.benchmarking.dist;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

/**
 * <p>
 * This class is an internal class. Please do not instantiate it, use it by
 * yourself, or otherwise meddle with it.
 * </p>
 * <p>
 * This class is a wrapper around {@link java.io.BufferedReader
 * BufferedReader} that allows us to push back already read data and to
 * read it again. This comes in handy when parsing a file and making
 * decisions about which backing store to use in memory while loading the
 * data and/or when deciding which distance measure to use. The loader
 * routines of the more specialized classes then can be presented with the
 * data that was already read.
 * </p>
 */
final class _PushBackReader extends BufferedReader {

  /** the pushed-back data */
  private final ArrayList<String> m_pb;

  /**
   * instantiate
   * 
   * @param sub
   *          the reader
   * @param pb
   *          the pushed-back data
   */
  _PushBackReader(final Reader sub, final ArrayList<String> pb) {
    super(sub);
    this.m_pb = pb;
  }

  /** {@inheritDoc} */
  @Override
  public final String readLine() throws IOException {
    if (!(this.m_pb.isEmpty())) {
      return this.m_pb.remove(0);
    }
    return super.readLine();
  }

  /**
   * wrap
   * 
   * @param l
   *          the list
   * @param br
   *          the reader
   * @return the new reader
   */
  static final BufferedReader wrap(final ArrayList<String> l,
      final BufferedReader br) {
    if ((l == null) || l.isEmpty()) {
      return br;
    }
    return new _PushBackReader(br, l);
  }

  /**
   * push back some string
   * 
   * @param l
   *          the list
   * @param s
   *          the string
   * @return the new list
   */
  static final ArrayList<String> pushBack(final ArrayList<String> l,
      final String s) {
    ArrayList<String> t;

    if (l != null) {
      t = l;
    } else {
      t = new ArrayList<>();
    }
    t.add(s);
    return t;
  }
}
