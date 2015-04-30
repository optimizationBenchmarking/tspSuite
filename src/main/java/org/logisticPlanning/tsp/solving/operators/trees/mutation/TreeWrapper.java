package org.logisticPlanning.tsp.solving.operators.trees.mutation;

import java.io.PrintStream;

import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;
import org.logisticPlanning.tsp.solving.Individual;
import org.logisticPlanning.tsp.solving.operators.UnaryOperator;
import org.logisticPlanning.tsp.solving.operators.trees.TreeOperationUtils;
import org.logisticPlanning.tsp.solving.operators.trees.TreePath;
import org.logisticPlanning.tsp.solving.searchSpaces.trees.Node;
import org.logisticPlanning.tsp.solving.searchSpaces.trees.NodeType;
import org.logisticPlanning.utils.config.Configurable;
import org.logisticPlanning.utils.math.random.Randomizer;

/**
 * <p>
 * An operator which wraps a tree node&nbsp;[<a href="#cite_WGOEB"
 * style="font-weight:bold">1</a>].
 * </p>
 * <h2>References</h2>
 * <ol>
 * <li><div><span id="cite_WGOEB" /><a
 * href="http://www.it-weise.de/">Thomas Weise</a> <span
 * style="color:gray">[&#27748;&#21355;&#24605;</span>]: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Global
 * Optimization Algorithms &#8210; Theory and Application,&rdquo;</span>
 * 2009, Germany: it-weise.de (self-published). <div>link: [<a
 * href="http://www.it-weise.de/projects/book.pdf">1</a>]</div></div></li>
 * </ol>
 * 
 * @param <NT>
 *          the node type
 */
public final class TreeWrapper<NT extends Node<NT>> extends
    UnaryOperator<NT> {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the internal path */
  private TreePath<NT> m_path;
  /** the maximum depth @serial serializable field */
  private final int m_maxDepth;

  /**
   * Create a new tree mutation operation
   * 
   * @param md
   *          the maximum tree depth
   */
  public TreeWrapper(final int md) {
    super("treeWrapper"); //$NON-NLS-1$
    this.m_maxDepth = md;
    this.m_path = new TreePath<>();
  }

  /** {@inheritDoc} */
  @Override
  public final void mutate(final Individual<NT> dest,
      final ObjectiveFunction f, final Individual<NT> g) {
    int trials, len, idx, i;
    NT sel, nu, par;
    TreePath<NT> p;
    NodeType<? extends NT, NT> x, y;
    final Randomizer r;

    r = f.getRandom();
    p = this.m_path;
    dest.clear();
    for (trials = 100; trials > 0; trials--) {

      p.randomPath(g.solution, r);
      len = p.size() - 1;

      nu = sel = p.get(len);

      if (len > 0) {
        len--;
        idx = p.getChildIndex(len);
        par = p.get(len);
        nu = TreeOperationUtils.createTree(par.getType()
            .getChildTypes(idx), (this.m_maxDepth - len) + 1, false, r);
        if (nu == null) {
          nu = sel;
        }
        if (nu != sel) {

          len = nu.size();
          if (len > 0) {
            idx = r.nextInt(len);
            x = nu.getType();
            y = sel.getType();
            for (i = len; (--i) >= 0;) {
              if (x.getChildTypes(idx).containsType(y)) {
                nu = nu.setChild(sel, idx);
                dest.solution = p.replaceEnd(nu);
                dest.producer = this;
                return;
              }
              idx = ((idx + 1) % len);
            }
          }
        }
      }

    }

    dest.assign(g);
  }

  /** {@inheritDoc} */
  @Override
  public final TreeWrapper<NT> clone() {
    TreeWrapper<NT> r;

    r = ((TreeWrapper<NT>) (super.clone()));
    r.m_path = new TreePath<>();

    return r;
  }

  /** {@inheritDoc} */
  @Override
  public void printConfiguration(final PrintStream ps) {
    super.printConfiguration(ps);

    Configurable.printKey(this.name() + "MaxDepth", ps); //$NON-NLS-1$
    ps.println(this.m_maxDepth);
  }
}
