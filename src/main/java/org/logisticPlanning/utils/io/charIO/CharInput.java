package org.logisticPlanning.utils.io.charIO;

import java.io.IOException;

/**
 * A very crude and simple character input interface that provides unified
 * methods to access different objects such as Strings or character arrays.
 * This object does not manage indexes at all, it is assumed that only
 * valid indexes are passed to its methods and that index calculation takes
 * place elsewhere.
 */
public abstract class CharInput {

  /** instantiate */
  protected CharInput() {
    super();
  }

  /**
   * get the character at index {@code index}
   *
   * @param index
   *          the index
   * @return the character
   */
  public abstract int get(final int index);

  /**
   * write a portion of the data to an output device
   *
   * @param out
   *          the output
   * @param start
   *          the start
   * @param end
   *          the end
   * @throws IOException
   *           if io fails
   */
  public abstract void write(final CharOutput out, final int start,
      final int end) throws IOException;

  /**
   * convert a portion to a string
   *
   * @param start
   *          the start
   * @param end
   *          the end
   * @return the string
   */
  public abstract String toString(final int start, final int end);

}
