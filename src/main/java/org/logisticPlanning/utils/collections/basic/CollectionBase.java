package org.logisticPlanning.utils.collections.basic;

import java.io.Serializable;

/**
 * The base class for all objects in our collection framework.
 */
public class CollectionBase implements Serializable, Cloneable {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** instantiate! */
  protected CollectionBase() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public Object clone() {
    try {
      return super.clone();
    } catch (final RuntimeException r) {
      throw r;
    } catch (final Throwable t) {
      throw new RuntimeException(t);
    }
  }

}
