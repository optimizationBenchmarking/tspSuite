package org.logisticPlanning.tsp.solving;

import java.util.Comparator;

import org.logisticPlanning.utils.NamedObject;

/**
 * A comparator comparing individuals based on their
 * {@link org.logisticPlanning.tsp.solving.Individual#tourLength tour
 * length} .
 */
public final class IndividualTourLengthComparator extends NamedObject
    implements Comparator<Individual<?>> {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the globally shared comparator instance */
  public static final IndividualTourLengthComparator INSTANCE = new IndividualTourLengthComparator();

  /** instantiate */
  private IndividualTourLengthComparator() {
    super("tourLengthComparator"); //$NON-NLS-1$
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
    return Long.compare(o1.tourLength, o2.tourLength);
  }

  /** {@inheritDoc} */
  @Override
  public final IndividualTourLengthComparator clone() {
    return this;
  }

  //

  // default, automatic serialization replacement and resolve routines for
  // singletons
  //
  /**
   * Write replace: the instance this method is invoked on will be replaced
   * with the singleton instance
   * {@link IndividualTourLengthComparator#INSTANCE
   * IndividualTourLengthComparator.INSTANCE} for serialization, i.e., when
   * the instance is written with
   * {@link java.io.ObjectOutputStream#writeObject(Object)}.
   *
   * @return the replacement instance (always
   *         {@link IndividualTourLengthComparator#INSTANCE
   *         IndividualTourLengthComparator.INSTANCE})
   */
  private final Object writeReplace() {
    return IndividualTourLengthComparator.INSTANCE;
  }

  /**
   * Read resolve: The instance this method is invoked on will be replaced
   * with the singleton instance
   * {@link IndividualTourLengthComparator#INSTANCE
   * IndividualTourLengthComparator.INSTANCE} after serialization, i.e.,
   * when the instance is read with
   * {@link java.io.ObjectInputStream#readObject()}.
   *
   * @return the replacement instance (always
   *         {@link IndividualTourLengthComparator#INSTANCE
   *         IndividualTourLengthComparator.INSTANCE})
   */
  private final Object readResolve() {
    return IndividualTourLengthComparator.INSTANCE;
  }

}
