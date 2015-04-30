package org.logisticPlanning.tsp.solving.operators.trees.recombination;

import java.io.PrintStream;

import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;
import org.logisticPlanning.tsp.solving.Individual;
import org.logisticPlanning.tsp.solving.operators.BinaryOperator;
import org.logisticPlanning.tsp.solving.operators.trees.TreePath;
import org.logisticPlanning.tsp.solving.searchSpaces.trees.Node;
import org.logisticPlanning.utils.config.Configurable;
import org.logisticPlanning.utils.math.random.Randomizer;

/**
 * <p>
 * A simple recombination operation for a given tree which implants a
 * randomly chosen subtree from one parent into the other parent, thereby
 * replacing a randomly picked node in the second parent&nbsp;[<a
 * href="#cite_KOZ1992GP" style="font-weight:bold">1</a>, <a
 * href="#cite_PLMP2008AFGTGP" style="font-weight:bold">2</a>, <a
 * href="#cite_WGOEB" style="font-weight:bold">3</a>].
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
public class SubTreeExchangeRecombination<NT extends Node<NT>> extends
    BinaryOperator<NT> {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the maximum depth @serial serializable field */
  private final int m_maxDepth;

  /** the first path */
  private TreePath<NT> m_pa1;

  /** the first path */
  private TreePath<NT> m_pa2;

  /**
   * Create a new tree recombination operation
   * 
   * @param md
   *          the maximum tree depth
   */
  public SubTreeExchangeRecombination(final int md) {
    super("subTreeExchangeRecombination"); //$NON-NLS-1$
    this.m_pa1 = new TreePath<>();
    this.m_pa2 = new TreePath<>();
    this.m_maxDepth = md;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void recombine(final Individual<NT> dest,
      final ObjectiveFunction f, final Individual<NT> parent1,
      final Individual<NT> parent2) {
    TreePath<NT> pt1, pt2;
    int trials, i;
    NT e1, e2;
    final Randomizer r;

    r = f.getRandom();
    dest.clear();
    pt1 = this.m_pa1;
    pt2 = this.m_pa2;

    outer: for (trials = 100; trials > 0; trials--) {
      pt1.randomPath(parent1.solution, r);
      pt2.randomPath(parent2.solution, r);

      i = pt1.size();
      if (i <= 1) {
        continue outer;
      }

      e2 = pt2.get(pt2.size() - 1);
      i -= 2;
      e1 = pt1.get(i);
      if (((i + e2.getHeight() + 1) < this.m_maxDepth)
          && e1.getType().getChildTypes(pt1.getChildIndex(i))
              .containsType(e2.getType())) {
        dest.solution = pt1.replaceEnd(e2);
        dest.producer = this;
        return;
      }
    }

    dest.assign(r.nextBoolean() ? parent1 : parent2);
  }

  /** {@inheritDoc} */
  @Override
  public final SubTreeExchangeRecombination<NT> clone() {
    SubTreeExchangeRecombination<NT> res;

    res = ((SubTreeExchangeRecombination<NT>) (super.clone()));
    res.m_pa1 = new TreePath<>();
    res.m_pa2 = new TreePath<>();

    return res;
  }

  /** {@inheritDoc} */
  @Override
  public void printConfiguration(final PrintStream ps) {
    super.printConfiguration(ps);

    Configurable.printKey(this.name() + "MaxDepth", ps); //$NON-NLS-1$
    ps.println(this.m_maxDepth);
  }
}
