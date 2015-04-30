package org.logisticPlanning.utils.document.impl.xhtml;

import java.io.CharArrayWriter;
import java.io.Writer;
import java.util.Arrays;

import org.logisticPlanning.utils.document.spec.MacroDescriptor;
import org.logisticPlanning.utils.io.charIO.WriterCharOutput;

/**
 * the char writer buffer
 */
final class _Output {

  /** the temporary output stream */
  final WriterCharOutput m_out;

  /** the next one */
  final _Output m_next;

  /** the temp tag depth */
  int m_tagDepth;

  /** the data */
  private char[][] m_data;

  /** the number of data elements */
  private int m_size;

  /**
   * create
   * 
   * @param writer
   *          the destination writer
   * @param next
   *          the next
   */
  _Output(final _Output next, final Writer writer) {
    super();
    this.m_next = next;
    this.m_out = new WriterCharOutput(writer);
    this.m_tagDepth = 0;
  }

  /**
   * create
   * 
   * @param next
   *          the next
   */
  _Output(final _Output next) {
    this(next, new CharArrayWriter());
  }

  /**
   * get the characters
   * 
   * @return the characters
   */
  final char[] _getChars() {
    char[] ch;

    ch = ((CharArrayWriter) (this.m_out.getWriter())).toCharArray();
    if ((ch == null) || (ch.length <= 0)) {
      return null;
    }
    return ch;
  }

  /**
   * get the data at the given index
   * 
   * @param idx
   *          the index
   * @return the data
   */
  final char[] getData(final int idx) {
    if (idx >= this.m_size) {
      throw new IllegalStateException();
    }
    return this.m_data[idx];
  }

  /**
   * add a data element
   * 
   * @param data
   *          the data element
   */
  final void addData(final char[] data) {
    final int idx, idxp1;

    idx = this.m_size;
    idxp1 = (idx + 1);
    if (this.m_data == null) {
      this.m_data = new char[Math.max(MacroDescriptor.MAX_PARAMETERS,
          idxp1)][];
    } else {
      if (idx >= this.m_data.length) {
        this.m_data = Arrays.copyOf(this.m_data, idxp1);
      }
    }
    this.m_data[idx] = data;
    this.m_size = Math.max(this.m_size, idxp1);
  }

  /**
   * take the data
   * 
   * @return the data
   */
  final char[][] take() {
    final int s;
    char[][] x;

    x = this.m_data;
    if (x != null) {
      if ((s = this.m_size) > 0) {
        if (s < x.length) {
          x = new char[s][];
          System.arraycopy(this.m_data, 0, x, 0, s);
        }
      } else {
        x = null;
      }
      this.m_size = 0;
      this.m_data = null;
    }

    return x;
  }
}
