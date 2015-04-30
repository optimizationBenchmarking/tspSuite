package org.logisticPlanning.tsp.solving.operators;

import org.logisticPlanning.utils.NamedObject;

/**
 * the dummy nullary operator
 */
final class _DummyNullaryOperator extends NullaryOperator<Object> {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** create */
  _DummyNullaryOperator() {
    super("dummy0");//$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  public final NamedObject clone() {
    return this;
  }
}
