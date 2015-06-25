package org.logisticPlanning.tsp.solving.searchSpaces.trees.math.basic;

import java.io.PrintStream;
import java.util.Random;

import org.logisticPlanning.tsp.solving.searchSpaces.trees.Node;
import org.logisticPlanning.tsp.solving.searchSpaces.trees.NodeType;
import org.logisticPlanning.tsp.solving.searchSpaces.trees.NodeTypeSet;
import org.logisticPlanning.tsp.solving.searchSpaces.trees.math.Function;
import org.logisticPlanning.utils.config.Configurable;

/**
 * A write type
 */
public final class WriteType extends NodeType<Write, Function> {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the number of variables */
  private final int m_varC;

  /**
   * Create a new variable type record
   *
   * @param ch
   *          the types of the possible chTypes
   * @param varc
   *          the variable count
   */
  public WriteType(final int varc, final NodeTypeSet<Function>[] ch) {
    super("write_" + varc, null); //$NON-NLS-1$
    this.m_varC = Math.max(0, varc);
  }

  /**
   * Instantiate a node
   *
   * @param children
   *          a given set of children
   * @param r
   *          the randomizer
   * @return the new node
   */
  @Override
  public final Write instantiate(final Node<?>[] children, final Random r) {
    return new Write(children, this, r.nextInt(this.m_varC));
  }

  /** {@inheritDoc} */
  @Override
  public void printConfiguration(final PrintStream ps) {
    super.printConfiguration(ps);

    Configurable.printKey("writableVariableCount", ps); //$NON-NLS-1$
    ps.println(this.m_varC);
  }
}
