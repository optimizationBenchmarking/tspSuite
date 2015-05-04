package org.logisticPlanning.utils.processes;

import java.io.IOException;
import java.io.Reader;

import org.logisticPlanning.utils.collections.lists.CharList;

/**
 * a text buffer
 */
final class _TextBuffer extends CharList {
  /** The serial version uid */
  private static final long serialVersionUID = 1L;

  /** the base length */
  private final int m_baseLen;

  /**
   * create
   *
   * @param id
   *          the id
   */
  _TextBuffer(final String id) {
    super(4096 + id.length());

    this.m_size = this.m_baseLen = id.length();
    id.getChars(0, this.m_baseLen, this.m_data, 0);
  }

  /**
   * load a number of chars
   *
   * @param r
   *          the reader
   * @return the number of bytes read
   * @throws IOException
   *           if I/O fails
   * @throws OutOfMemoryError
   *           if we are out of memory
   */
  final int _load(final Reader r) throws IOException, OutOfMemoryError {
    int size, read;
    char[] ch;

    size = this.m_size;
    read = 4096;

    ch = this.ensureFree(read, size);

    read = r.read(ch, size, read);
    this.modCount++;

    if (read > 0) {
      this.m_size += read;
    }
    return read;
  }

  /**
   * get the text loaded
   *
   * @return the text
   */
  final String _text() {
    return String.valueOf(this.m_data, 0, this.m_size);
  }

  /**
   * flush the buffer
   */
  final void _flush() {
    this.m_size = this.m_baseLen;
  }
}
