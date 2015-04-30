package org.logisticPlanning.tsp.solving;

import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;
import org.logisticPlanning.utils.config.Configurable;

/**
 * A module of a {@link org.logisticPlanning.tsp.solving.TSPAlgorithm tsp
 * algorithm} which can be initialized and finalized. This is new as of
 * version 0.9.8 of TSP Suite with the goal to enable run-wise allocation
 * and de-allocation of data structures. This is a major break in the
 * algorithm behavior we had before.
 * 
 * 
 @author <em><a href="http://www.it-weise.de/">Thomas Weise</a></em>,
 *         Email:&nbsp;<a
 *         href="mailto:tweise@ustc.edu.cn">tweise@ustc.edu.cn</a>
 */
public class TSPModule extends Configurable {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * create
   * 
   * @param name
   *          the module's name
   */
  protected TSPModule(final String name) {
    super(name);
  }

  /**
   * This method must be called before each run of the optimization
   * algorithm using this module. If this object, in turn, holds references
   * to other modules, it must call their
   * {@link #beginRun(ObjectiveFunction) beginRun} methods in return.
   * 
   * @param f
   *          the objective function
   * @see #endRun(ObjectiveFunction)
   * @see org.logisticPlanning.tsp.solving.TSPAlgorithm#call(ObjectiveFunction)
   */
  public void beginRun(final ObjectiveFunction f) {
    //
  }

  /**
   * This method must be called when a run of of the optimization algorithm
   * using this module is completed. If this object, in turn, holds
   * references to other modules, it must call their
   * {@link #endRun(ObjectiveFunction) beginRun} methods in return.
   * 
   * @param f
   *          the objective function
   * @see #beginRun(ObjectiveFunction)
   * @see org.logisticPlanning.tsp.solving.TSPAlgorithm#call(ObjectiveFunction)
   */
  public void endRun(final ObjectiveFunction f) {
    //
  }

  /**
   * Invoke the {@link #beginRun(ObjectiveFunction)} method of a set of
   * modules. The two methods
   * {@link #invokeBeginRun(ObjectiveFunction, TSPModule[])} and
   * {@link #invokeEndRun(ObjectiveFunction, TSPModule[])} are
   * complementary and make sure that the modules are de-initialized in the
   * opposite order of initialization.
   * 
   * @param modules
   *          the modules
   * @param f
   *          the objective function
   * @see #beginRun(ObjectiveFunction)
   * @see #invokeEndRun(ObjectiveFunction, TSPModule[])
   */
  protected static final void invokeBeginRun(final ObjectiveFunction f,
      final TSPModule[] modules) {
    if (modules != null) {
      for (final TSPModule module : modules) {
        module.beginRun(f);
      }
    }
  }

  /**
   * Invoke the {@link #endRun(ObjectiveFunction)} method of a set of
   * modules.
   * 
   * @param modules
   *          the modules
   * @param f
   *          the objective function
   * @see #endRun(ObjectiveFunction)
   * @see #invokeBeginRun(ObjectiveFunction, TSPModule[])
   */
  protected static final void invokeEndRun(final ObjectiveFunction f,
      final TSPModule[] modules) {
    int i;
    Throwable t;

    t = null;
    if (modules != null) {
      for (i = modules.length; (--i) >= 0;) {
        try {
          modules[i].endRun(f);
        } catch (final Throwable z) {
          t = z;
        }
      }

      if (t != null) {
        throw new RuntimeException(t);
      }
    }
  }
}
