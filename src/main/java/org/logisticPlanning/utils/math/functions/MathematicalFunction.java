package org.logisticPlanning.utils.math.functions;

import java.io.Serializable;

/**
 * <p>
 * The base class for mathematical functions. It is our goal to implement
 * many mathematical functions in a way that can
 * </p>
 * <ol>
 * <li>make use of inheritance and overriding in order to allow for a great
 * versatility and provide the chance to construct more complex functions
 * and operations</li>
 * <li>is very fast and efficient to access, i.e., has routines that can
 * efficiently inlined by a compiler</li>
 * </ol>
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
public abstract class MathematicalFunction implements Serializable {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiate the basic function class.
   */
  protected MathematicalFunction() {
    super();
  }

  /**
   * Get the arity of this function, i.e., the number of arguments it
   * accepts.
   *
   * @return the number of arguments that this function accepts
   */
  public abstract int arity();

  /**
   * Compute the result of this function when all parameters are
   * {@code double} valued.
   *
   * @param x
   *          the vector of parameters, which must contain (at least)
   *          {@link #arity()} {@code double} values
   * @return the result of this function, computed in {@code double}
   *         precision
   */
  public double compute(final double... x) {
    throw new UnsupportedOperationException();
  }

  /**
   * throws the illegal argument exception caused by a NaN return value for
   * an integer function.
   *
   * @throws IllegalArgumentException
   *           always
   */
  final void _throwIllegalNaN() {
    throw new IllegalArgumentException(//
        "The integer-based '" + this.toString() + //$NON-NLS-1$
        "' delegate to a real-valued calculation has returned NaN."); //$NON-NLS-1$
  }

  /**
   * throws the illegal argument exception caused by an illegal arity for
   * an integer function.
   *
   * @param length
   *          the dimension of the argument
   * @throws IllegalArgumentException
   *           always
   */
  final void _throwArity(final int length) {
    throw new IllegalArgumentException(//
        "The function '" + this.toString() + //$NON-NLS-1$
        "' has arity " + this.arity() + //$NON-NLS-1$
        " but was invoked with " + length + //$NON-NLS-1$
        " arguments."); //$NON-NLS-1$
  }

  /**
   * Compute the function value in the {@code byte} domain. This basic
   * function template delegates the computation to the {@code int} variant
   * of this function. The {@code int} result of that function is then
   * casted to {@code byte}.
   *
   * @param x
   *          the vector of parameters, which must contain (at least)
   *          {@link #arity()} {@code byte} values
   * @return the return value of this function, a byte
   * @throws IllegalArgumentException
   *           if a floating point delegate in the delegation chain returns
   *           {@code Double.NaN}.
   */
  public byte compute(final byte... x) {
    final int[] a;
    int i;
    final int r;

    i = x.length;
    if (i != this.arity()) {
      this._throwArity(i);
    }

    a = new int[i];
    for (; (--i) >= 0;) {
      a[i] = (x[i]);
    }

    r = this.compute(a);
    return ((byte) (r));
  }

  /**
   * Compute the function value in the {@code short} domain. This basic
   * function template delegates the computation to the {@code int} variant
   * of this function. The {@code int} result of that function is then
   * casted to {@code short}.
   *
   * @param x
   *          the vector of parameters, which must contain (at least)
   *          {@link #arity()} {@code short} values
   * @return the return value of this function, a short
   * @throws IllegalArgumentException
   *           if a floating point delegate in the delegation chain returns
   *           {@code Double.NaN}.
   */
  public short compute(final short... x) {
    final int[] a;
    int i;
    final int r;

    i = x.length;
    if (i != this.arity()) {
      this._throwArity(i);
    }

    a = new int[i];
    for (; (--i) >= 0;) {
      a[i] = (x[i]);
    }

    r = this.compute(a);
    return ((short) (r));
  }

  /**
   * Compute the function value in the {@code int} domain. This basic
   * function template delegates the computation to the {@code long}
   * variant of this function. The {@code long} result of that function is
   * then casted to {@code int}.
   *
   * @param x
   *          the vector of parameters, which must contain (at least)
   *          {@link #arity()} {@code int} values
   * @return the return value of this function, a int
   * @throws IllegalArgumentException
   *           if a floating point delegate in the delegation chain returns
   *           {@code Double.NaN}.
   */
  public int compute(final int... x) {
    final long[] a;
    int i;
    final long r;

    i = x.length;
    if (i != this.arity()) {
      this._throwArity(i);
    }

    a = new long[i];
    for (; (--i) >= 0;) {
      a[i] = (x[i]);
    }

    r = this.compute(a);
    return ((int) (r));
  }

  /**
   * Compute the function value in the {@code long} domain. This basic
   * function template delegates the computation to the {@code double}
   * variant of this function. The {@code double} result of that function
   * is then casted to {@code long}.
   *
   * @param x
   *          the vector of parameters, which must contain (at least)
   *          {@link #arity()} {@code long} values
   * @return the return value of this function, a long
   * @throws IllegalArgumentException
   *           if a floating point delegate in the delegation chain returns
   *           {@code Double.NaN}.
   */
  public long compute(final long... x) {
    final double[] a;
    int i;
    final double r;

    i = x.length;
    if (i != this.arity()) {
      this._throwArity(i);
    }

    a = new double[i];
    for (; (--i) >= 0;) {
      a[i] = (x[i]);
    }

    r = this.compute(a);
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
   * @param x
   *          the vector of parameters, which must contain (at least)
   *          {@link #arity()} {@code float} values
   * @return the return value of this function, a float
   */
  public float compute(final float... x) {
    final double[] a;
    int i;
    final double r;

    i = x.length;
    if (i != this.arity()) {
      this._throwArity(i);
    }

    a = new double[i];
    for (; (--i) >= 0;) {
      a[i] = (x[i]);
    }

    r = this.compute(a);
    return ((float) (r));
  }

  /**
   * <p>
   * Invert the function for the parameter at index {@code index}. If an
   * inverse function exists for the parameter at index {@code index}, the
   * result will a pointer to the object implementing said inverse
   * (otherwise it will be {@code null}).
   * </p>
   * <p>
   * If this function here is a n-ary function like
   * {@code f(x0, x1, x2, ...) = r}, then {@code f.invertFor(1)} should
   * return a function {@code g} such that
   * {@code g(x0, f(x0, x1, x2, ...), x1, x2, ...) = x1}, i.e., the result
   * of the original function takes the place of the {@code (index+1)}
   * <sup>th</sup> argument.
   * </p>
   * <p>
   * Notice that the existence of an inverse function for a given parameter
   * {@code index} does not mean that it is defined for all parameters. For
   * example, multiplication is an inverse for division, but a division by
   * zero may not be invertible.
   * </p>
   *
   * @param index
   *          the index of the parameter with
   *          <code>0&leq;index<{@link #arity()}</code>
   * @return the inverse function, if it exists, {@code null} otherwise
   */
  public MathematicalFunction invertFor(final int index) {
    return null;
  }

  /**
   * <p>
   * Get the derivative of the function for the parameter at index
   * {@code index}. If a derivative function exists for the parameter at
   * index {@code index}, the result will a pointer to the object
   * implementing said derivative (otherwise it will be {@code null}).
   * </p>
   * <p>
   * If this function here is an {@code n}-ary function, then the
   * derivative will either also be an {@code n}-ary function with the same
   * parameters as the original function, or it will be an {@code n-1}-ary
   * function where the parameter at index {@code index} is left out (if it
   * disappeared during the differentiation procedure).
   * </p>
   *
   * @param index
   *          the index of the parameter with
   *          <code>0&leq;index<{@link #arity()}</code>
   * @return the derivative function, if it exists, {@code null} otherwise
   */
  public MathematicalFunction derivativeFor(final int index) {
    return null;
  }

  /**
   * <p>
   * Get the integral of the function for the parameter at index
   * {@code index} . If an integral function exists for the parameter at
   * index {@code index} , the result will a pointer to the object
   * implementing said integral (otherwise it will be {@code null}). Of
   * course, all integrals could have an added constant {@code c}. We
   * assume that {@code c=0} here.
   * </p>
   *
   * @param index
   *          the index of the parameter with
   *          <code>0&leq;index<{@link #arity()}</code>
   * @return the integral function, if it exists, {@code null} otherwise
   */
  public MathematicalFunction integrateFor(final int index) {
    return null;
  }

  /** {@inheritDoc} */
  @Override
  public String toString() {
    return this.getClass().getSimpleName();
  }

  /**
   * This instance equals to any object of the same class. The reason is
   * that mathematical functions are usually singletons.
   *
   * @param o
   *          the object
   * @return {@code true} if {@code o} is an instance of the same class,
   *         {@code false} otherwise
   */
  @Override
  public boolean equals(final Object o) {
    return ((o == this) || ((o != null) && (this.getClass() == o
        .getClass())));
  }

  /**
   * The hash code here is set to the hash code of the class, since
   * mathematical functions are usually singletons.
   *
   * @return the hash code
   */
  @Override
  public int hashCode() {
    return this.getClass().hashCode();
  }
}
