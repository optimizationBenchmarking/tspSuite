package org.logisticPlanning.utils.math.statistics.aggregates;

import org.logisticPlanning.utils.math.data.point.Point;

/**
 * an aggregate
 */
public abstract class Aggregate extends Point {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** instantiate */
  protected Aggregate() {
    super();
  }

  /**
   * Visit a given {@code double}.
   *
   * @param value
   *          the value to visit
   */
  public abstract void visitDouble(final double value);

  /**
   * Visit a given {@code float}. By default, this method forwards to
   * {@link #visitDouble(double)}, but it may be overridden to do some
   * special computation for {@code float} values.
   *
   * @param value
   *          the value to visit
   */
  public void visitFloat(final float value) {
    this.visitDouble(value);
  }

  /**
   * Visit a given {@code long}. By default, this method forwards to
   * {@link #visitDouble(double)}, but it may be overridden to do some
   * special computation for {@code long} values.
   *
   * @param value
   *          the value to visit
   */
  public void visitLong(final long value) {
    this.visitDouble(value);
  }

  /**
   * Visit a given {@code int}. By default, this method forwards to
   * {@link #visitLong(long)}, but it may be overridden to do some special
   * computation for {@code int} values.
   *
   * @param value
   *          the value to visit
   */
  public void visitInt(final int value) {
    this.visitLong(value);
  }

  /**
   * Visit a given {@code short}. By default, this method forwards to
   * {@link #visitInt(int)}, but it may be overridden to do some special
   * computation for {@code short} values.
   *
   * @param value
   *          the value to visit
   */
  public void visitShort(final short value) {
    this.visitInt(value);
  }

  /**
   * Visit a given {@code byte}. By default, this method forwards to
   * {@link #visitShort(short)}, but it may be overridden to do some
   * special computation for {@code byte} values.
   *
   * @param value
   *          the value to visit
   */
  public void visitByte(final byte value) {
    this.visitShort(value);
  }

  /** reset all internal state information */
  public void reset() { //
  }

  /** reset the mean */
  @Override
  public String toString() {
    return this.getClass().getSimpleName();
  }
}
