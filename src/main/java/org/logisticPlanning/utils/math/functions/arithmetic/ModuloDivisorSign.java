package org.logisticPlanning.utils.math.functions.arithmetic;

import org.logisticPlanning.utils.math.functions.BinaryFunction;

/**
 * <p>
 * The modulo division function computing the <a
 * href="https://en.wikipedia.org/wiki/Modulo_operation">remainder</a> of a
 * division. This remainder will have the same sign as the divisor, as
 * devised in&nbsp;[<a href="#cite_K1997FA"
 * style="font-weight:bold">1</a>]. The result of this function is
 * {@code modulo(i,j)= i - j*floor(i/j)}.
 * </p>
 * <h2>References</h2>
 * <ol>
 * <li><div><span id="cite_K1997FA" />Donald Ervin Knuth: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Fundamental
 * Algorithms,&rdquo;</span> 1997, volume 1 of The Art of Computer
 * Programming (TAOCP), Boston, MA, USA: Addison-Wesley Longman Publishing
 * Co., Inc.. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0201896834">0-201-89683-4</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780201896831">978-0-201-
 * 89683-1</a>; Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=J_MySQAACAAJ">J_MySQAACAAJ</a>,
 * <a
 * href="http://books.google.com/books?id=B31GAAAAYAAJ">B31GAAAAYAAJ</a>,
 * and&nbsp;<a
 * href="http://books.google.com/books?id=5oJQAAAAMAAJ">5oJQAAAAMAAJ
 * </a></div></li>
 * </ol>
 */
public final class ModuloDivisorSign extends BinaryFunction {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the globally shared instance */
  public static final ModuloDivisorSign INSTANCE = new ModuloDivisorSign();

  /** instantiate */
  private ModuloDivisorSign() {
    super();
  }

  /**
   * Compute the modulo, i.e., the remainder of the division of {@code x0}
   * by {@code x1}. The result will have the same sign as {@code x1}.
   * 
   * @param x0
   *          the dividend
   * @param x1
   *          the divisor
   * @return the remainder of the division, i.e.,
   *         {@code modulo(x0, x1)= x0 - x1*floor(x0/x1)}
   */
  public static final int modulo(final int x0, final int x1) {
    final int var;

    if (((var = (x0 % x1)) != 0) && ((x0 ^ x1) < 0)) {
      return (var + x1);
    }

    return var;
  }

  /**
   * Compute the modulo, i.e., the remainder of the division of {@code x0}
   * by {@code x1}. The result will have the same sign as {@code x1}.
   * 
   * @param x0
   *          the dividend
   * @param x1
   *          the divisor
   * @return the remainder of the division, i.e.,
   *         {@code modulo(x0, x1)= x0 - x1*floor(x0/x1)}
   */
  public static final long modulo(final long x0, final long x1) {
    final long var;

    if (((var = (x0 % x1)) != 0) && ((x0 ^ x1) < 0)) {
      return (var + x1);
    }

    return var;
  }

  /**
   * Compute the modulo, i.e., the remainder of the division of {@code x0}
   * by {@code x1}. The result will have the same sign as {@code x1}.
   * 
   * @param x0
   *          the divident
   * @param x1
   *          the divisor
   * @return the remainder of the division, i.e.,
   *         {@code modulo(x0, x1)= x0 - x1*floor(x0/x1)}
   */
  public static final double modulo(final double x0, final double x1) {
    return (x0 - (x1 * Math.floor(x0 / x1)));
  }

  /** {@inheritDoc} */
  @Override
  public final byte compute(final byte x0, final byte x1) {
    return ((byte) (ModuloDivisorSign.modulo(x0, x1)));
  }

  /** {@inheritDoc} */
  @Override
  public final short compute(final short x0, final short x1) {
    return ((short) (ModuloDivisorSign.modulo(x0, x1)));
  }

  /** {@inheritDoc} */
  @Override
  public final int compute(final int x0, final int x1) {
    return (ModuloDivisorSign.modulo(x0, x1));
  }

  /** {@inheritDoc} */
  @Override
  public final long compute(final long x0, final long x1) {
    return (ModuloDivisorSign.modulo(x0, x1));
  }

  /** {@inheritDoc} */
  @Override
  public final double compute(final double x0, final double x1) {
    return (ModuloDivisorSign.modulo(x0, x1));
  }

  // default, automatic serialization replacement and resolve routines for
  // singletons
  //
  /**
   * Write replace: the instance this method is invoked on will be replaced
   * with the singleton instance {@link ModuloDivisorSign#INSTANCE
   * INSTANCE} for serialization, i.e., when the instance is written with
   * {@link java.io.ObjectOutputStream#writeObject(Object)}.
   * 
   * @return the replacement instance (always
   *         {@link ModuloDivisorSign#INSTANCE INSTANCE})
   */
  private final Object writeReplace() {
    return ModuloDivisorSign.INSTANCE;
  }

  /**
   * Read resolve: The instance this method is invoked on will be replaced
   * with the singleton instance {@link ModuloDivisorSign#INSTANCE
   * INSTANCE} after serialization, i.e., when the instance is read with
   * {@link java.io.ObjectInputStream#readObject()}.
   * 
   * @return the replacement instance (always
   *         {@link ModuloDivisorSign#INSTANCE INSTANCE})
   */
  private final Object readResolve() {
    return ModuloDivisorSign.INSTANCE;
  }
}
