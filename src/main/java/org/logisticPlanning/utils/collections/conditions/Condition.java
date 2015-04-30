package org.logisticPlanning.utils.collections.conditions;

/**
 * A condition is a Boolean function which is either {@code true} or
 * {@code false}.
 * 
 * @param <T>
 *          the element type this condition applies to.
 */
public abstract class Condition<T> {

  /** create */
  protected Condition() {
    super();
  }

  /**
   * Check if the condition is met.
   * 
   * @param param
   *          the parameter
   * @return {@code true} if the condition is met by {@code param},
   *         {@code false} otherwise
   */
  public abstract boolean check(final T param);

}
