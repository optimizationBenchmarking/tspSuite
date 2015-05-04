package org.logisticPlanning.utils.utils.comparison;

import java.util.Comparator;

/**
 * A precise comparator extends the {@link java.util.Comparator} interface
 * by providing facilities to have <em>incomparable</em> objects. This is
 * achieved by providing the method {@link #preciseCompare(Object, Object)}
 * which returns an instance of
 * {@link org.logisticPlanning.utils.utils.comparison.EComparison}
 * indicating the comparison outcome.
 *
 * @param <T>
 *          the object type
 */
public class PreciseComparator<T> implements Comparator<T> {

  /** create */
  protected PreciseComparator() {
    super();
  }

  /**
   * Compare two guaranteed non-{@code null} objects {@code a} and
   * {@code b} with {@code a!=b}.
   *
   * @param a
   *          the first object
   * @param b
   *          the second object
   * @return the comparison result
   */
  protected EComparison doPreciseCompare(final T a, final T b) {
    return (a.equals(b) ? EComparison.EQUAL : EComparison.NOT_EQUAL);
  }

  /**
   * Compare two objects {@code a} and {@code b}. This method checks some
   * basic conditions (such as identical references or {@code null} and
   * then forwards to {@link #doPreciseCompare(Object, Object)}.
   *
   * @param a
   *          the first object
   * @param b
   *          the second object
   * @return the comparison result:
   *         <ol>
   *         <li>
   *         {@link org.logisticPlanning.utils.utils.comparison.EComparison#SAME
   *         SAME} if both values are {@code null},</li>
   *         <li>
   *         {@link org.logisticPlanning.utils.utils.comparison.EComparison#GREATER
   *         GREATER} if {@code a==null} but {@code b!=null},></li>
   *         <li>
   *         {@link org.logisticPlanning.utils.utils.comparison.EComparison#LESS
   *         LESS} if {@code a!=null} and {@code b==null},</li>
   *         <li>and the appropriate
   *         {@link org.logisticPlanning.utils.utils.comparison.EComparison
   *         EComparison constant} otherwise.</li>
   *         </ol>
   */
  public final EComparison preciseCompare(final T a, final T b) {
    if (a == b) {
      return EComparison.SAME;
    }
    if (a == null) {
      return EComparison.GREATER;
    }
    if (b == null) {
      return EComparison.LESS;
    }

    return this.doPreciseCompare(a, b);
  }

  /**
   * This method transforms the result from
   * {@link #preciseCompare(Object, Object)} to the integer range
   * {@code -1..1} . While the
   * {@link org.logisticPlanning.utils.utils.comparison.EComparison} allow
   * us to express that two objects are essentially not comparable, the
   * {@link java.util.Comparator} interface does not, as it only allows to
   * state that an object is &quot;less&quot; &quot;equal&quot;, or
   * &quot;greater&quot; than another one. Here, we translate the constants
   * indicating non-comparability
   * {@link org.logisticPlanning.utils.utils.comparison.EComparison#NOT_EQUAL}
   * and
   * {@link org.logisticPlanning.utils.utils.comparison.EComparison#NOT_SAME}
   * to {@code 0}.
   *
   * @param a
   *          the first object to compare
   * @param b
   *          the second object to compare
   * @return the transformed comparison result obtained from
   *         {@link #preciseCompare(Object, Object)}:
   *         <ol>
   *         <li>
   *         {@link org.logisticPlanning.utils.utils.comparison.EComparison#LESS}
   *         is translated to {@code -1}</li>
   *         <li>
   *         {@link org.logisticPlanning.utils.utils.comparison.EComparison#LESS_OR_SAME}
   *         is translated to {@code -1}</li>
   *         <li>
   *         {@link org.logisticPlanning.utils.utils.comparison.EComparison#LESS_OR_EQUAL}
   *         is translated to {@code -1}</li>
   *         <li>
   *         {@link org.logisticPlanning.utils.utils.comparison.EComparison#EQUAL}
   *         is translated to {@code 0}</li>
   *         <li>
   *         {@link org.logisticPlanning.utils.utils.comparison.EComparison#SAME}
   *         is translated to {@code 0}</li>
   *         <li>
   *         {@link org.logisticPlanning.utils.utils.comparison.EComparison#GREATER_OR_EQUAL}
   *         is translated to {@code 1}</li>
   *         <li>
   *         {@link org.logisticPlanning.utils.utils.comparison.EComparison#GREATER_OR_SAME}
   *         is translated to {@code 1}</li>
   *         <li>
   *         {@link org.logisticPlanning.utils.utils.comparison.EComparison#GREATER}
   *         is translated to {@code 1}</li>
   *         <li>
   *         {@link org.logisticPlanning.utils.utils.comparison.EComparison#NOT_SAME}
   *         is translated to {@code 0}</li>
   *         <li>
   *         {@link org.logisticPlanning.utils.utils.comparison.EComparison#NOT_EQUAL}
   *         is translated to {@code 0}</li>
   *         </ol>
   */
  @Override
  public final int compare(final T a, final T b) {
    return this.preciseCompare(a, b).m_cmp;
  }

}
