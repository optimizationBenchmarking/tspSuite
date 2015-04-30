package org.logisticPlanning.utils.math.functions.combinatoric;

import org.logisticPlanning.utils.math.functions.UnaryFunction;

/**
 * The harmonic number
 */
public final class HarmonicNumbers extends UnaryFunction {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the globally shared instance */
  public static final HarmonicNumbers INSTANCE = new HarmonicNumbers();

  /** instantiate */
  private HarmonicNumbers() {
    super();
  }

  /**
   * Recursively compute the harmonic numbers. The goal is to avoid
   * rounding errors by splitting the sum into intervalls in a
   * divide-and-conquer method.
   * 
   * @param start
   *          the start
   * @param end
   *          the end
   * @return The <code>n</code>'th harmonic number.
   */
  private static final double harmonic(final long start, final long end) {
    long i;

    if (start > end) {
      return 0d;
    }

    if (start == end) {
      return (1d / start);
    }

    i = ((start + end) >> 1);
    return (HarmonicNumbers.harmonic(start, i) + HarmonicNumbers.harmonic(
        i + 1l, end));
  }

  /**
   * Calculate the <code>n</code>'th harmonic number.
   * 
   * @param n
   *          The index of the harmonic number to be created.
   * @return The <code>n</code>'th harmonic number.
   */
  public static final double harmonic(final long n) {
    return HarmonicNumbers.harmonic(1, n);
  }

  /** {@inheritDoc} */
  @Override
  public final double compute(final double x1) {
    return HarmonicNumbers.harmonic(Math.round(x1));
  }

  //

  // default, automatic serialization replacement and resolve routines for
  // singletons
  //
  /**
   * Write replace: the instance this method is invoked on will be replaced
   * with the singleton instance {@link HarmonicNumbers#INSTANCE
   * HarmonicNumbers.INSTANCE} for serialization, i.e., when the instance
   * is written with {@link java.io.ObjectOutputStream#writeObject(Object)}
   * .
   * 
   * @return the replacement instance (always
   *         {@link HarmonicNumbers#INSTANCE HarmonicNumbers.INSTANCE})
   */
  private final Object writeReplace() {
    return HarmonicNumbers.INSTANCE;
  }

  /**
   * Read resolve: The instance this method is invoked on will be replaced
   * with the singleton instance {@link HarmonicNumbers#INSTANCE
   * HarmonicNumbers.INSTANCE} after serialization, i.e., when the instance
   * is read with {@link java.io.ObjectInputStream#readObject()}.
   * 
   * @return the replacement instance (always
   *         {@link HarmonicNumbers#INSTANCE HarmonicNumbers.INSTANCE})
   */
  private final Object readResolve() {
    return HarmonicNumbers.INSTANCE;
  }

}
