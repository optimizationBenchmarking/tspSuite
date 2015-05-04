package org.logisticPlanning.tsp.solving.utils.edge;

import java.util.Comparator;

/**
 * A comparator that sorts
 * {@link org.logisticPlanning.tsp.solving.utils.edge.PriorityEdge edges}
 * according to their heuristic value and puts {@code null} values at the
 * end of an array.
 */
public final class PriorityEdgeComparator implements
Comparator<PriorityEdge> {

  /** the shared instance */
  public static final Comparator<PriorityEdge> INSTANCE = new PriorityEdgeComparator();

  /** create */
  private PriorityEdgeComparator() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public final int compare(final PriorityEdge o1, final PriorityEdge o2) {
    if (o1 == o2) {
      return 0;
    }
    if (o1 == null) {
      return 1;
    }
    if (o2 == null) {
      return (-1);
    }
    return Integer.compare(o1.h, o2.h);
  }

}
