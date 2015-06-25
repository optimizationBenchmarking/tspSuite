package org.logisticPlanning.utils.math.functions.logic;

import org.logisticPlanning.utils.math.functions.UnaryFunction;

/**
 * The logical not.
 */
public final class LNot extends UnaryFunction {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the globally shared instance */
  public static final LNot INSTANCE = new LNot();

  /** the forbidden constructor */
  private LNot() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public final byte compute(final byte x1) {
    return NumericalLogic.boolean2byte(!(NumericalLogic.byte2boolean(x1)));
  }

  /** {@inheritDoc} */
  @Override
  public final short compute(final short x1) {
    return NumericalLogic
        .boolean2short(!(NumericalLogic.short2boolean(x1)));
  }

  /** {@inheritDoc} */
  @Override
  public final int compute(final int x1) {
    return NumericalLogic.boolean2int(!(NumericalLogic.int2boolean(x1)));
  }

  /** {@inheritDoc} */
  @Override
  public final long compute(final long x1) {
    return NumericalLogic.boolean2long(!(NumericalLogic.long2boolean(x1)));
  }

  /** {@inheritDoc} */
  @Override
  public final float compute(final float x1) {
    return NumericalLogic
        .boolean2float(!(NumericalLogic.float2boolean(x1)));
  }

  /** {@inheritDoc} */
  @Override
  public final double compute(final double x1) {
    return NumericalLogic.boolean2double(!(NumericalLogic
        .double2boolean(x1)));
  }

  // default, automatic serialization replacement and resolve routines for
  // singletons
  //
  /**
   * Write replace: the instance this method is invoked on will be replaced
   * with the singleton instance {@link LNot#INSTANCE INSTANCE} for
   * serialization, i.e., when the instance is written with
   * {@link java.io.ObjectOutputStream#writeObject(Object)}.
   *
   * @return the replacement instance (always {@link LNot#INSTANCE
   *         INSTANCE})
   */
  private final Object writeReplace() {
    return LNot.INSTANCE;
  }

  /**
   * Read resolve: The instance this method is invoked on will be replaced
   * with the singleton instance {@link LNot#INSTANCE INSTANCE} after
   * serialization, i.e., when the instance is read with
   * {@link java.io.ObjectInputStream#readObject()}.
   *
   * @return the replacement instance (always {@link LNot#INSTANCE
   *         INSTANCE})
   */
  private final Object readResolve() {
    return LNot.INSTANCE;
  }
}
