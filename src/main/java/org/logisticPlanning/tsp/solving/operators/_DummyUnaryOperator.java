package org.logisticPlanning.tsp.solving.operators;

import org.logisticPlanning.utils.NamedObject;

/**
 * the dummy unary operator
 */
final class _DummyUnaryOperator extends UnaryOperator<Object> {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** create */
  _DummyUnaryOperator() {
    super("dummy1");//$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  public final NamedObject clone() {
    return this;
  }
}
