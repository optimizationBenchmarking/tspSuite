package org.logisticPlanning.utils.math.functions;

/**
 * <p>
 * The base class for binary functions, i.e., mathematical functions that
 * accept exactly 2 argument.
 * </p>
 * <h2>Inspiration</h2>
 * <p>
 * Some of the more complex mathematical routines in this package, e.g.,
 * derived from this class, may have been inspired by &ndash; or derived
 * from freely available open source code in the internet (although this
 * &quot;function object&quot; idea is by a unique &quot;invention&quot; by
 * myself). Unfortunately, this has been a quite some time ago for personal
 * use, so I (<a href="mailto:tweise@gmx.de">Thomas Weise</a>) do not
 * remember anymore from where the original stems.
 * </p>
 * <p>
 * Also, most of the code has undergone quite a few revisions,
 * refactorings, and modifications, so it often is quite different from the
 * potential originals.
 * <p>
 * <p>
 * I vaguely remember that some code may have been inspired by the <a
 * href="https://commons.apache.org/proper/commons-math/">The Apache
 * Commons Mathematics Library</a>&nbsp;[<a href="#cite_A2013CMTACML"
 * style="font-weight:bold">1</a>] which is currently is licensed under <a
 * href="https://www.apache.org/licenses/">Apache License 2.0</a> which is
 * GPL compatible &ndash; but again, I am not sure anymore, it's too long
 * ago.
 * </p>
 * <p>
 * If you know the original source of this code or find a very similar
 * package, please let me know so that I can properly cite it. If you feel
 * that this code here somehow infringes your copyright, please let me know
 * as well and I will cease putting it online or replace it with different
 * code.<br/>
 * Many thanks.<br/>
 * Thomas.
 * </p>
 * <h2>References</h2>
 * <ol>
 * <li><div><span id="cite_A2013CMTACML" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;Commons Math: The
 * Apache Commons Mathematics Library,&rdquo;</span> (Website),
 * April&nbsp;7, 2013, Forest Hill, MD, USA: Apache Software Foundation.
 * <div>link: [<a
 * href="https://commons.apache.org/proper/commons-math/">1</
 * a>]</div></div></li>
 * </ol>
 */
public class BinaryFunction extends MathematicalFunction {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** Instantiate the base class for binary functions */
  protected BinaryFunction() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public final int arity() {
    return 2;
  }

  /**
   * Delegate the function call with open parameter count to the
   * corresponding function with exactly 2 parameters.
   * 
   * @param x
   *          the parameter array
   * @return the return value of this function, a byte
   */
  @Override
  public final byte compute(final byte... x) {
    if (x.length != 2) {
      this._throwArity(x.length);
    }
    return this.compute(x[0], x[1]);
  }

  /**
   * Delegate the function call with open parameter count to the
   * corresponding function with exactly 2 parameters.
   * 
   * @param x
   *          the parameter array
   * @return the return value of this function, a short
   */
  @Override
  public final short compute(final short... x) {
    if (x.length != 2) {
      this._throwArity(x.length);
    }
    return this.compute(x[0], x[1]);
  }

  /**
   * Delegate the function call with open parameter count to the
   * corresponding function with exactly 2 parameters.
   * 
   * @param x
   *          the parameter array
   * @return the return value of this function, a int
   */
  @Override
  public final int compute(final int... x) {
    if (x.length != 2) {
      this._throwArity(x.length);
    }
    return this.compute(x[0], x[1]);
  }

  /**
   * Delegate the function call with open parameter count to the
   * corresponding function with exactly 2 parameters.
   * 
   * @param x
   *          the parameter array
   * @return the return value of this function, a long
   */
  @Override
  public final long compute(final long... x) {
    if (x.length != 2) {
      this._throwArity(x.length);
    }
    return this.compute(x[0], x[1]);
  }

  /**
   * Delegate the function call with open parameter count to the
   * corresponding function with exactly 2 parameters.
   * 
   * @param x
   *          the parameter array
   * @return the return value of this function, a float
   */
  @Override
  public final float compute(final float... x) {
    if (x.length != 2) {
      this._throwArity(x.length);
    }
    return this.compute(x[0], x[1]);
  }

  /**
   * Delegate the function call with open parameter count to the
   * corresponding function with exactly 2 parameters.
   * 
   * @param x
   *          the parameter array
   * @return the return value of this function, a double
   */
  @Override
  public final double compute(final double... x) {
    if (x.length != 2) {
      this._throwArity(x.length);
    }
    return this.compute(x[0], x[1]);
  }

  /**
   * Compute the function value in the {@code byte} domain. This basic
   * function template delegates the computation to the {@code int} variant
   * of this function. The {@code int} result of that function is then
   * casted to {@code byte}.
   * 
   * @param x0
   *          the 1st byte argument of the function
   * @param x1
   *          the 2nd byte argument of the function
   * @return the return value of this function, a {@code byte}
   */
  public byte compute(final byte x0, final byte x1) {
    final int r;

    r = this.compute(((int) (x0)), ((int) (x1)));

    return ((byte) (r));
  }

  /**
   * Compute the function value in the {@code short} domain. This basic
   * function template delegates the computation to the {@code int} variant
   * of this function. The {@code int} result of that function is then
   * casted to {@code short}.
   * 
   * @param x0
   *          the 1st short argument of the function
   * @param x1
   *          the 2nd short argument of the function
   * @return the return value of this function, a {@code short}
   */
  public short compute(final short x0, final short x1) {
    final int r;

    r = this.compute(((int) (x0)), ((int) (x1)));

    return ((short) (r));
  }

  /**
   * Compute the function value in the {@code int} domain. This basic
   * function template delegates the computation to the {@code long}
   * variant of this function. The {@code long} result of that function is
   * then casted to {@code int}.
   * 
   * @param x0
   *          the 1st int argument of the function
   * @param x1
   *          the 2nd int argument of the function
   * @return the return value of this function, a {@code int}
   */
  public int compute(final int x0, final int x1) {
    final long r;

    r = this.compute(((long) (x0)), ((long) (x1)));

    return ((int) (r));
  }

  /**
   * Compute the function value in the {@code long} domain. This basic
   * function template delegates the computation to the {@code double}
   * variant of this function. The {@code double} result of that function
   * is then casted to {@code long}.
   * 
   * @param x0
   *          the 1st long argument of the function
   * @param x1
   *          the 2nd long argument of the function
   * @return the return value of this function, a {@code long}
   */
  public long compute(final long x0, final long x1) {
    final double r;

    r = this.compute(((double) (x0)), ((double) (x1)));

    if (r <= (java.lang.Double.NEGATIVE_INFINITY)) {
      return (java.lang.Long.MIN_VALUE);
    }
    if (r >= (java.lang.Double.POSITIVE_INFINITY)) {
      return (java.lang.Long.MAX_VALUE);
    }
    if (java.lang.Double.isNaN(r)) {
      this._throwIllegalNaN();
    }
    return ((long) ((r)));
  }

  /**
   * Compute the function value in the {@code float} domain. This basic
   * function template delegates the computation to the {@code double}
   * variant of this function. The {@code double} result of that function
   * is then casted to {@code float}.
   * 
   * @param x0
   *          the 1st float argument of the function
   * @param x1
   *          the 2nd float argument of the function
   * @return the return value of this function, a {@code float}
   */
  public float compute(final float x0, final float x1) {
    final double r;

    r = this.compute(((double) (x0)), ((double) (x1)));

    return ((float) (r));
  }

  /**
   * Compute the function value in the {@code double} domain. Every
   * sub-class of this class must, at least, override this function.
   * 
   * @param x0
   *          the 1st {@code double} argument of the function
   * @param x1
   *          the 2nd {@code double} argument of the function
   * @return the return value of this function, a {@code double}
   */
  public double compute(final double x0, final double x1) {
    throw new UnsupportedOperationException();
  }

  /** {@inheritDoc} */
  @Override
  public BinaryFunction invertFor(final int index) {
    return null;
  }

  /** {@inheritDoc} */
  @Override
  public BinaryFunction integrateFor(final int index) {
    return null;
  }
}
