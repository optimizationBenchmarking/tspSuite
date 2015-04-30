package org.logisticPlanning.tsp.solving.operators;

import org.logisticPlanning.utils.NamedObject;

/**
 * the dummy binary operator
 */
final class _DummyBinaryOperator extends BinaryOperator<Object> {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** create */
  _DummyBinaryOperator() {
    super("dummy2");//$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  public final NamedObject clone() {
    return this;
  }
}
