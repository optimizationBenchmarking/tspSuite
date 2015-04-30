package org.logisticPlanning.tsp.solving.searchSpaces.trees.math.basic;

import java.io.PrintStream;
import java.util.Random;

import org.logisticPlanning.tsp.solving.searchSpaces.trees.Node;
import org.logisticPlanning.tsp.solving.searchSpaces.trees.NodeType;
import org.logisticPlanning.tsp.solving.searchSpaces.trees.math.Function;
import org.logisticPlanning.utils.config.Configurable;

/**
 * A variable type
 */
public final class VariableType extends NodeType<Variable, Function> {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the variables */
  private final Variable[] m_vars;

  /**
   * Create a new variable type record
   * 
   * @param varc
   *          the variable count
   */
  public VariableType(final int varc) {
    super("variables", null); //$NON-NLS-1$

    int i;
    Variable[] v;

    this.m_vars = v = new Variable[varc];
    for (i = varc; (--i) >= 0;) {
      v[i] = new Variable(this, i);
    }
  }

  /** {@inheritDoc} */
  @Override
  public final Variable instantiate(final Node<?>[] children,
      final Random r) {
    return this.m_vars[r.nextInt(this.m_vars.length)];
  }

  /** {@inheritDoc} */
  @Override
  public void printConfiguration(final PrintStream ps) {
    super.printConfiguration(ps);

    Configurable.printKey("variableCount", ps); //$NON-NLS-1$
    ps.println(this.m_vars.length);
  }
}
