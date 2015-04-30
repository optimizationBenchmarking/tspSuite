package org.logisticPlanning.utils.document.spec;

import java.io.IOException;

/**
 * a sequence
 */
public abstract class Sequence {

  /** the sequence */
  protected Sequence() {
    super();
  }

  /**
   * get the number of elements of the sequence
   * 
   * @return the number of elements of the sequence
   */
  public abstract int length();

  /**
   * write an element of that sequence
   * 
   * @param index
   *          the index
   * @throws IOException
   *           if io fails
   */
  public abstract void write(final int index) throws IOException;
}
