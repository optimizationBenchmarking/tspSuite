package org.logisticPlanning.utils.math.functions.power;

import org.logisticPlanning.utils.math.functions.BinaryFunction;

/**
 * The pow function: raise {@code x1} to the power {@code x2}
 */
public final class Pow extends BinaryFunction {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the globally shared instance */
  public static final Pow INSTANCE = new Pow();

  /** instantiate */
  private Pow() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public final int compute(final int x1, final int x2) {
    int result, exp, base;

    if (x2 <= (1)) {
      if ((x2 <= (0)) && (x1 == (0))) {
        throw new java.lang.ArithmeticException("0 to a power <= 0"); //$NON-NLS-1$
      }
      if (x2 < (0)) {
        return (0);
      }
      if (x2 == (1)) {
        return x1;
      }
      return (1);
    }

    if (x1 <= (2)) {
      if (x1 == (0)) {
        return (0);
      }
      if (x1 == (1)) {
        return (1);
      }
      if (x1 == (2)) {
        result = ((1) << x2);
        if (result <= (0)) {
          return (java.lang.Integer.MAX_VALUE);
        }
        return result;
      }
    }

    result = (1);
    exp = x2;
    base = x1;

    for (;;) {
      if ((exp & (1)) != (0)) {
        if ((result *= base) <= (0)) {
          if ((x1 < (0)) && ((x2 & (1)) != (0))) {
            return (java.lang.Integer.MIN_VALUE);
          }
          return (java.lang.Integer.MAX_VALUE);
        }
      }

      if ((exp >>>= (1)) <= (0)) {
        return result;
      }
      base *= base;
    }
  }

  /** {@inheritDoc} */
  @Override
  public final long compute(final long x1, final long x2) {
    long result, exp, base;

    if (x2 <= (1l)) {
      if ((x2 <= (0l)) && (x1 == (0l))) {
        throw new java.lang.ArithmeticException("0 to a power <= 0"); //$NON-NLS-1$
      }
      if (x2 < (0l)) {
        return (0l);
      }
      if (x2 == (1l)) {
        return x1;
      }
      return (1l);
    }

    if (x1 <= (2l)) {
      if (x1 == (0l)) {
        return (0l);
      }
      if (x1 == (1l)) {
        return (1l);
      }
      if (x1 == (2l)) {
        result = ((1l) << x2);
        if (result <= (0l)) {
          return (java.lang.Long.MAX_VALUE);
        }
        return result;
      }
    }

    result = (1l);
    exp = x2;
    base = x1;

    for (;;) {
      if ((exp & (1l)) != (0l)) {
        if ((result *= base) <= (0l)) {
          if ((x1 < (0l)) && ((x2 & (1l)) != (0l))) {
            return (java.lang.Long.MIN_VALUE);
          }
          return (java.lang.Long.MAX_VALUE);
        }
      }

      if ((exp >>>= (1l)) <= (0l)) {
        return result;
      }
      base *= base;
    }
  }

  /** {@inheritDoc} */
  @Override
  public final float compute(final float x1, final float x2) {
    return ((float) (Math.pow(x1, x2)));
  }

  /** {@inheritDoc} */
  @Override
  public final double compute(final double x1, final double x2) {
    return (Math.pow(x1, x2));
  }

  /** {@inheritDoc} */
  @Override
  public final Log invertFor(final int index) {
    if (index == 1) {
      return Log.INSTANCE;
    }
    return null;
  }

  // default, automatic serialization replacement and resolve routines for
  // singletons
  //
  /**
   * Write replace: the instance this method is invoked on will be replaced
   * with the singleton instance {@link Pow#INSTANCE INSTANCE} for
   * serialization, i.e., when the instance is written with
   * {@link java.io.ObjectOutputStream#writeObject(Object)}.
   * 
   * @return the replacement instance (always {@link Pow#INSTANCE INSTANCE}
   *         )
   */
  private final Object writeReplace() {
    return Pow.INSTANCE;
  }

  /**
   * Read resolve: The instance this method is invoked on will be replaced
   * with the singleton instance {@link Pow#INSTANCE INSTANCE} after
   * serialization, i.e., when the instance is read with
   * {@link java.io.ObjectInputStream#readObject()}.
   * 
   * @return the replacement instance (always {@link Pow#INSTANCE INSTANCE}
   *         )
   */
  private final Object readResolve() {
    return Pow.INSTANCE;
  }
}
