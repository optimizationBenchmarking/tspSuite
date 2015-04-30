package org.logisticPlanning.tsp.solving.algorithms.heuristics;

import java.io.PrintStream;

import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;
import org.logisticPlanning.tsp.solving.Individual;
import org.logisticPlanning.utils.config.Configurable;
import org.logisticPlanning.utils.config.Configuration;

/**
 * <p>
 * A TSP heuristic that may have a specific or random start node.
 * <p>
 */
public class TSPHeuristicWithStartNode extends TSPHeuristic {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * randomly choose a depot node, see {@link #setUseRandomDepot(boolean)},
   * {@link #isUsingRandomDepot()} in method
   * {@link #solve(ObjectiveFunction, Individual)} @serial serializable
   * field
   */
  private boolean m_randomDepot;

  /** the depot parameter name */
  private final String m_depotParamName;

  /**
   * instantiate
   * 
   * @param name
   *          the heuristics name
   */
  public TSPHeuristicWithStartNode(final String name) {
    super(name);

    String s, e;
    int end;

    s = this.getClass().getSimpleName();
    end = s.length();
    e = "Heuristic";//$NON-NLS-1$
    if (s.endsWith(e)) {
      end -= e.length();
    }

    this.m_depotParamName = Character.toLowerCase(s.charAt(0))
        + s.substring(1, end) + "RandomDepot"; //$NON-NLS-1$
  }

  /**
   * Set whether a random depot should be used or always node {@code 1} in
   * the method {@link #solve(ObjectiveFunction, Individual)}. This
   * {@link #solve(ObjectiveFunction, Individual) method} may be called
   * from another algorithm for initialization.
   * 
   * @param useRandomDepot
   *          {@code true} if random depots should be used, {@code false}
   *          if only node {@code 1} can be the depot
   */
  public final void setUseRandomDepot(final boolean useRandomDepot) {
    this.m_randomDepot = useRandomDepot;
  }

  /**
   * Do we use random depots (or always node {@code 1})?
   * 
   * @return {@code true} if random depots should be used, {@code false} if
   *         only node {@code 1} can be the depot
   */
  public final boolean isUsingRandomDepot() {
    return this.m_randomDepot;
  }

  /** {@inheritDoc} */
  @Override
  public void configure(final Configuration config) {
    super.configure(config);

    this.m_randomDepot = config.getBoolean(this.m_depotParamName,
        this.m_randomDepot);
  }

  /** {@inheritDoc} */
  @Override
  public void printConfiguration(final PrintStream ps) {
    super.printConfiguration(ps);

    Configurable.printKey(this.m_depotParamName, ps);
    ps.println(this.m_randomDepot);
  }

  /** {@inheritDoc} */
  @Override
  public void printParameters(final PrintStream ps) {
    super.printParameters(ps);

    Configurable.printKey(this.m_depotParamName, ps);
    ps.println("should we always use node 1 as depot (false) or randomly choose one (true)?"); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  public void solve(final ObjectiveFunction f, final Individual<int[]> dest) {
    this.solve(f, dest, //
        (this.m_randomDepot ? (f.getRandom().nextInt(f.n()) + 1) : 1));
  }

  /**
   * Solve a TSP by using a specific depot
   * 
   * @param f
   *          the objective function
   * @param dest
   *          the destination
   * @param depot
   *          the depot
   */
  public void solve(final ObjectiveFunction f,
      final Individual<int[]> dest, final int depot) {
    throw new UnsupportedOperationException();
  }
}
