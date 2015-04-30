package org.logisticPlanning.tsp.solving;

import java.util.Comparator;

import org.logisticPlanning.utils.NamedObject;
import org.logisticPlanning.utils.utils.comparison.ComparisonUtils;

/**
 * A comparator comparing individuals based on their
 * {@link org.logisticPlanning.tsp.solving.Individual#f fitness} .
 */
public final class IndividualFitnessComparator extends NamedObject
    implements Comparator<Individual<?>> {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the globally shared comparator instance */
  public static final IndividualFitnessComparator INSTANCE = new IndividualFitnessComparator();

  /** instantiate */
  private IndividualFitnessComparator() {
    super("fitnessComparator"); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  public final int compare(final Individual<?> o1, final Individual<?> o2) {
    if (o1 == o2) {
      return 0;
    }
    if (o1 == null) {
      return (1);
    }
    if (o2 == null) {
      return (-1);
    }
    return ComparisonUtils.compare(o1.f, o2.f);
  }

  /** {@inheritDoc} */
  @Override
  public final IndividualFitnessComparator clone() {
    return this;
  }

  //

  // default, automatic serialization replacement and resolve routines for
  // singletons
  //
  /**
   * Write replace: the instance this method is invoked on will be replaced
   * with the singleton instance
   * {@link IndividualFitnessComparator#INSTANCE
   * IndividualFitnessComparator.INSTANCE} for serialization, i.e., when
   * the instance is written with
   * {@link java.io.ObjectOutputStream#writeObject(Object)}.
   * 
   * @return the replacement instance (always
   *         {@link IndividualFitnessComparator#INSTANCE
   *         IndividualFitnessComparator.INSTANCE})
   */
  private final Object writeReplace() {
    return IndividualFitnessComparator.INSTANCE;
  }

  /**
   * Read resolve: The instance this method is invoked on will be replaced
   * with the singleton instance
   * {@link IndividualFitnessComparator#INSTANCE
   * IndividualFitnessComparator.INSTANCE} after serialization, i.e., when
   * the instance is read with
   * {@link java.io.ObjectInputStream#readObject()}.
   * 
   * @return the replacement instance (always
   *         {@link IndividualFitnessComparator#INSTANCE
   *         IndividualFitnessComparator.INSTANCE})
   */
  private final Object readResolve() {
    return IndividualFitnessComparator.INSTANCE;
  }

}
