package org.logisticPlanning.utils.utils;

/**
 * A utilities class for computing hash codes
 */
public final class HashUtils {

  /**
   * Combine two hash codes. This operator returns a result which depends
   * on both hash codes {@code a} and {@code b}. This results depends on
   * the order of the parameters, i.e., it (usually) is different for
   * {@code (a, b)} and {@code (b, a)}.
   * 
   * @param a
   *          the first hash code
   * @param b
   *          the second hash code
   * @return the combined hash code
   */
  public static final int combineHashes(final int a, final int b) {
    return ((31 * a) + b);
  }

  /**
   * Compute the hash code of a byte.
   * 
   * @param b
   *          the data
   * @return the hash
   */
  public static final int hashCode(final byte b) {
    return HashUtils.hashCode((int) b);
  }

  /**
   * Compute the hash code of a {@code short}
   * 
   * @param b
   *          the data
   * @return the hash
   */
  public static final int hashCode(final short b) {
    return HashUtils.hashCode((int) b);
  }

  /**
   * Compute the hash code of an integer.
   * 
   * @param b
   *          the data
   * @return the hash
   */
  public static final int hashCode(final int b) {
    return b;
  }

  /**
   * Compute the hash code of a float.
   * 
   * @param b
   *          the data
   * @return the hash
   */
  public static final int hashCode(final float b) {
    return HashUtils.hashCode(Float.floatToIntBits(b));
  }

  /**
   * Compute the hash code of a long.
   * 
   * @param b
   *          the data
   * @return the hash
   */
  public static final int hashCode(final long b) {
    return HashUtils.combineHashes(//
        ((int) (b & 0xffffffff)), //
        ((int) (b >>> 32)));
  }

  /**
   * Compute the hash code of a double.
   * 
   * @param b
   *          the data
   * @return the hash
   */
  public static final int hashCode(final double b) {
    return HashUtils.hashCode(Double.doubleToLongBits(b));
  }

  /**
   * Compute the hash code of an object.
   * 
   * @param b
   *          the data
   * @return the hash
   */
  public static final int hashCode(final Object b) {
    return ((b == null) ? 0 : HashUtils.hashCode(b.hashCode()));
  }

  /**
   * Compute the hash code of a character.
   * 
   * @param b
   *          the data
   * @return the hash
   */
  public static final int hashCode(final char b) {
    return HashUtils.hashCode((int) b);
  }

  /**
   * Compute the hash code of a boolean value.
   * 
   * @param b
   *          the data
   * @return the hash
   */
  public static final int hashCode(final boolean b) {
    return (b ? 370248451 : 16785407);
  }
}
