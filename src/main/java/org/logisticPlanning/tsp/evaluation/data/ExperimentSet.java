package org.logisticPlanning.tsp.evaluation.data;

/**
 * <p>
 * A set of experiments. This is the &quot;root data structure&quot; based
 * on which we compare and evaluate algorithms. Each experiment in this set
 * has been created by a different algorithm (configuration) and contains
 * different runs for different
 * {@link org.logisticPlanning.tsp.benchmarking.instances.Instance
 * benchmark instances}.
 * </p>
 */
public class ExperimentSet extends DataSet<Experiment> {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * instantiate
   *
   * @param name
   *          the experiment name
   * @param data
   *          the data of the run set
   */
  public ExperimentSet(final String name, final Experiment[] data) {
    super(name, data);
    DataSet._setup(data, this);
  }

  /**
   * Get the experiment for the given name
   *
   * @param name
   *          the name
   * @return the experiment belonging to that name, or {@code null} if no
   *         such set exists
   */
  public final Experiment forName(final String name) {
    final Experiment[] ps;
    Experiment midVal;
    int low, high, mid, cmp;

    ps = this.m_data;
    low = 0;
    high = (ps.length - 1);

    while (low <= high) {
      mid = ((low + high) >>> 1);
      midVal = ps[mid];
      cmp = midVal.name().compareTo(name);

      if (cmp < 0) {
        low = (mid + 1);
      } else
        if (cmp > 0) {
          high = (mid - 1);
        } else {
          return midVal;
        }
    }

    return null;
  }
}
