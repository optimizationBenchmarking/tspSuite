package org.logisticPlanning.utils.math.functions.logic;

/**
 * A class that provides some logic transformation routines.
 */
public final class NumericalLogic {

  /**
   * Transform a {@code boolean} value to a {@code type.type}.
   * 
   * @param b
   *          the {@code boolean}
   * @return the corresponding {@code type.type}
   */
  public static final byte boolean2byte(final boolean b) {
    return (b ? ((byte) (-1)) : ((byte) (0)));
  }

  /**
   * Transform a {@code boolean} value to a {@code type.type}.
   * 
   * @param b
   *          the {@code boolean}
   * @return the corresponding {@code type.type}
   */
  public static final short boolean2short(final boolean b) {
    return (b ? ((short) (-1)) : ((short) (0)));
  }

  /**
   * Transform a {@code boolean} value to a {@code type.type}.
   * 
   * @param b
   *          the {@code boolean}
   * @return the corresponding {@code type.type}
   */
  public static final int boolean2int(final boolean b) {
    return (b ? (-1) : (0));
  }

  /**
   * Transform a {@code boolean} value to a {@code type.type}.
   * 
   * @param b
   *          the {@code boolean}
   * @return the corresponding {@code type.type}
   */
  public static final long boolean2long(final boolean b) {
    return (b ? (-1l) : (0l));
  }

  /**
   * Transform a {@code boolean} value to a {@code type.type}.
   * 
   * @param b
   *          the {@code boolean}
   * @return the corresponding {@code type.type}
   */
  public static final float boolean2float(final boolean b) {
    return (b ? (-1f) : (0f));
  }

  /**
   * Transform a {@code boolean} value to a {@code type.type}.
   * 
   * @param b
   *          the {@code boolean}
   * @return the corresponding {@code type.type}
   */
  public static final double boolean2double(final boolean b) {
    return (b ? (-1d) : (0d));
  }

  /**
   * Transform a {@code type.type} value to a {@code boolean}.
   * 
   * @param v
   *          the {@code type.type}
   * @return the corresponding {@code boolean}
   */
  public static final boolean byte2boolean(final byte v) {
    return (v != ((byte) (0)));
  }

  /**
   * Transform a {@code type.type} value to a {@code boolean}.
   * 
   * @param v
   *          the {@code type.type}
   * @return the corresponding {@code boolean}
   */
  public static final boolean short2boolean(final short v) {
    return (v != ((short) (0)));
  }

  /**
   * Transform a {@code type.type} value to a {@code boolean}.
   * 
   * @param v
   *          the {@code type.type}
   * @return the corresponding {@code boolean}
   */
  public static final boolean int2boolean(final int v) {
    return (v != (0));
  }

  /**
   * Transform a {@code type.type} value to a {@code boolean}.
   * 
   * @param v
   *          the {@code type.type}
   * @return the corresponding {@code boolean}
   */
  public static final boolean long2boolean(final long v) {
    return (v != (0l));
  }

  /**
   * Transform a {@code type.type} value to a {@code boolean}.
   * 
   * @param v
   *          the {@code type.type}
   * @return the corresponding {@code boolean}
   */
  public static final boolean float2boolean(final float v) {
    return (!(v == (0f)));
  }

  /**
   * Transform a {@code type.type} value to a {@code boolean}.
   * 
   * @param v
   *          the {@code type.type}
   * @return the corresponding {@code boolean}
   */
  public static final boolean double2boolean(final double v) {
    return (!(v == (0d)));
  }
}
