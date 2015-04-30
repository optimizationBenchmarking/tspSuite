package org.logisticPlanning.utils.io.charIO;

import java.io.IOException;

/**
 * A class that can be used to access a character output
 */
public abstract class CharOutput {

  /** instantiate */
  protected CharOutput() {
    super();
  }

  /**
   * write a single character
   * 
   * @param data
   *          the character
   * @throws IOException
   *           if io fails
   */
  public abstract void write(final int data) throws IOException;

  /**
   * write a portion of a given character array
   * 
   * @param data
   *          the character array
   * @param start
   *          the start index
   * @param end
   *          the end index
   * @throws IOException
   *           if io fails
   */
  public abstract void write(final char[] data, final int start,
      final int end) throws IOException;

  /**
   * write a portion of a given string
   * 
   * @param data
   *          the string
   * @param start
   *          the start index
   * @param end
   *          the end index
   * @throws IOException
   *           if io fails
   */
  public abstract void write(final String data, final int start,
      final int end) throws IOException;

  /**
   * write a portion of a given char sequence
   * 
   * @param data
   *          the char sequence
   * @param start
   *          the start index
   * @param end
   *          the end index
   * @throws IOException
   *           if io fails
   */
  public abstract void write(final CharSequence data, final int start,
      final int end) throws IOException;

}
