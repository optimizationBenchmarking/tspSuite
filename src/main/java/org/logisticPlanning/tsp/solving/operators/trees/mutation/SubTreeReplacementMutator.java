package org.logisticPlanning.tsp.solving.operators.trees.mutation;

import java.io.PrintStream;

import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;
import org.logisticPlanning.tsp.solving.Individual;
import org.logisticPlanning.tsp.solving.operators.UnaryOperator;
import org.logisticPlanning.tsp.solving.operators.trees.TreeOperationUtils;
import org.logisticPlanning.tsp.solving.operators.trees.TreePath;
import org.logisticPlanning.tsp.solving.searchSpaces.trees.Node;
import org.logisticPlanning.utils.config.Configurable;
import org.logisticPlanning.utils.math.random.Randomizer;

/**
 * <p>
 * A simple mutation operation for a given tree which implants a randomly
 * created subtree into parent, thereby replacing a randomly picked node in
 * the parent&nbsp;[<a href="#cite_KOZ1992GP"
 * style="font-weight:bold">1</a>, <a href="#cite_PLMP2008AFGTGP"
 * style="font-weight:bold">2</a>, <a href="#cite_WGOEB"
 * style="font-weight:bold">3</a>].
 * </p>
 * <h2>References</h2>
 * <ol>
 * <li><div><span id="cite_KOZ1992GP" /><a
 * href="https://en.wikipedia.org/wiki/John_Koza">John R. Koza</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;Genetic
 * Programming: On the Programming of Computers by Means of Natural
 * Selection,&rdquo;</span> December&nbsp;1992, Bradford Books, Cambridge,
 * MA, USA: MIT Press. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0262111705">0-262-11170-5</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780262111706">978-0-262-
 * 11170-6</a>; LCCN:&nbsp;<a
 * href="http://lccn.loc.gov/92025785">92025785</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/245844858">245844858</a>, <a
 * href="https://www.worldcat.org/oclc/476715745">476715745</a>, <a
 * href="https://www.worldcat.org/oclc/312492070">312492070</a>, <a
 * href="https://www.worldcat.org/oclc/26263956">26263956</a>, <a
 * href="https://www.worldcat.org/oclc/610975642">610975642</a>,
 * and&nbsp;<a
 * href="https://www.worldcat.org/oclc/473279968">473279968</a>; Google
 * Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=Bhtxo60BV0EC">Bhtxo60BV0EC</a>
 * and&nbsp;<a
 * href="http://books.google.com/books?id=Wd8TAAAACAAJ">Wd8TAAAACAAJ</a>;
 * PPN:&nbsp;<a
 * href="http://gso.gbv.de/PPNSET?PPN=110450795">110450795</a>, <a
 * href="http://gso.gbv.de/PPNSET?PPN=152621075">152621075</a>, <a
 * href="http://gso.gbv.de/PPNSET?PPN=282081046">282081046</a>, <a
 * href="http://gso.gbv.de/PPNSET?PPN=191489522">191489522</a>, <a
 * href="http://gso.gbv.de/PPNSET?PPN=237675633">237675633</a>, <a
 * href="http://gso.gbv.de/PPNSET?PPN=246890320">246890320</a>, <a
 * href="http://gso.gbv.de/PPNSET?PPN=49354836X">49354836X</a>, and&nbsp;<a
 * href="http://gso.gbv.de/PPNSET?PPN=185924921">185924921</a></div></li>
 * <li><div><span id="cite_PLMP2008AFGTGP" /><a
 * href="http://cswww.essex.ac.uk/staff/poli/">Riccardo Poli</a>, <a
 * href="http://www0.cs.ucl.ac.uk/staff/W.Langdon/">William Benjamin
 * Langdon</a>, and&nbsp;<a
 * href="http://facultypages.morris.umn.edu/~mcphee/">Nicholas Freitag
 * McPhee</a>: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;A Field Guide to
 * Genetic Programming,&rdquo;</span> March&nbsp;2008, London, UK: Lulu
 * Enterprises UK Ltd. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/1409200736">1-4092-0073-6</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9781409200734">978-1-4092
 * -0073-4</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/225855345">225855345</a>; Google
 * Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=3PBrqNK5fFQC">3PBrqNK5fFQC</a>.
 * <div>links: [<a href=
 * "http://www.lulu.com/items/volume_63/2167000/2167025/2/print/book.pdf"
 * >1</a>] and&nbsp;[<a
 * href="http://www.gp-field-guide.org.uk/">2</a>]</div></div></li>
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
public final class SubTreeReplacementMutator<NT extends Node<NT>> extends
UnaryOperator<NT> {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;
  /** the internal path @serial serializable field */
  private TreePath<NT> m_path;

  /** the maximum depth @serial serializable field */
  private final int m_maxDepth;

  /**
   * Create a new tree mutation operation
   *
   * @param md
   *          the maximum tree depth
   */
  public SubTreeReplacementMutator(final int md) {
    super("subTreeReplacementMutation"); //$NON-NLS-1$
    this.m_maxDepth = md;
    this.m_path = new TreePath<>();
  }

  /** {@inheritDoc} */
  @Override
  public final void mutate(final Individual<NT> dest,
      final ObjectiveFunction f, final Individual<NT> g) {
    int trials, len, idx;
    NT sel, nu, par;
    TreePath<NT> p;
    final Randomizer r;

    dest.clear();
    r = f.getRandom();
    p = this.m_path;
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
      }

      if (nu != sel) {
        dest.solution = p.replaceEnd(nu);
        dest.producer = this;
        return;
      }
    }

    dest.assign(g);
  }

  /** {@inheritDoc} */
  @Override
  @SuppressWarnings("unchecked")
  public final SubTreeReplacementMutator<NT> clone() {
    SubTreeReplacementMutator<NT> r;

    r = ((SubTreeReplacementMutator<NT>) (super.clone()));
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
