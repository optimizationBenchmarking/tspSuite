package org.logisticPlanning.utils.math.functions.logic;

/**
 * The logical or.
 */
public final class LOr extends BinaryBooleanFunction {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the globally shared instance */
  public static final LOr INSTANCE = new LOr();

  /** the forbidden constructor */
  private LOr() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public final boolean compute(final boolean x1, final boolean x2) {
    return (x1 || x2);
  }

  /** {@inheritDoc} */
  @Override
  public final int lazyResultForFirstParam(final boolean x1) {
    return (x1 ? 1 : 0);
  }

  //

  // default, automatic serialization replacement and resolve routines for
  // singletons
  //
  /**
   * Write replace: the instance this method is invoked on will be replaced
   * with the singleton instance {@link LOr#INSTANCE LOr.INSTANCE} for
   * serialization, i.e., when the instance is written with
   * {@link java.io.ObjectOutputStream#writeObject(Object)}.
   *
   * @return the replacement instance (always {@link LOr#INSTANCE
   *         LOr.INSTANCE})
   */
  private final Object writeReplace() {
    return LOr.INSTANCE;
  }

  /**
   * Read resolve: The instance this method is invoked on will be replaced
   * with the singleton instance {@link LOr#INSTANCE LOr.INSTANCE} after
   * serialization, i.e., when the instance is read with
   * {@link java.io.ObjectInputStream#readObject()}.
   *
   * @return the replacement instance (always {@link LOr#INSTANCE
   *         LOr.INSTANCE})
   */
  private final Object readResolve() {
    return LOr.INSTANCE;
  }

}
