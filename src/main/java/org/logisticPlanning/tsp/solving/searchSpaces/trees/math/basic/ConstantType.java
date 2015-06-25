package org.logisticPlanning.tsp.solving.searchSpaces.trees.math.basic;

import java.io.PrintStream;
import java.util.Random;

import org.logisticPlanning.tsp.solving.searchSpaces.trees.Node;
import org.logisticPlanning.tsp.solving.searchSpaces.trees.NodeType;
import org.logisticPlanning.tsp.solving.searchSpaces.trees.math.Function;
import org.logisticPlanning.utils.config.Configurable;

/**
 * A constant type
 */
public final class ConstantType extends NodeType<Constant, Function> {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** Create a new constant type record */
  public ConstantType() {
    super("erc", null); //$NON-NLS-1$
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
  public final Constant instantiate(final Node<?>[] children,
      final Random r) {
    return new Constant(this, r.nextDouble());
  }

  /**
   * Instantiate a new constant
   *
   * @param v
   *          the value of the constant
   * @return the constant
   */
  public final Constant instantiate(final double v) {
    return new Constant(this, v);
  }

  /** {@inheritDoc} */
  @Override
  public void printConfiguration(final PrintStream ps) {
    super.printConfiguration(ps);

    Configurable.printKey("ercDistribution", ps); //$NON-NLS-1$
    ps.println("uniform in [0,1)");//$NON-NLS-1$
  }
}
