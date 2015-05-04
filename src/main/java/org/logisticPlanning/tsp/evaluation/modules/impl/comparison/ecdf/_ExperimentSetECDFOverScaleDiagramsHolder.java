package org.logisticPlanning.tsp.evaluation.modules.impl.comparison.ecdf;

import java.io.IOException;

import org.logisticPlanning.tsp.evaluation.data.Accessor;
import org.logisticPlanning.tsp.evaluation.data.ExperimentSet;
import org.logisticPlanning.tsp.evaluation.data.properties.ecdf.RunSetECDFProperty;
import org.logisticPlanning.tsp.evaluation.data.properties.scale.AllScaleInstancesProperty;
import org.logisticPlanning.tsp.evaluation.modules.impl.macros.Macros;
import org.logisticPlanning.tsp.evaluation.modules.spec.ComparisonModule;
import org.logisticPlanning.tsp.evaluation.modules.spec.Module;
import org.logisticPlanning.utils.document.spec.Header;
import org.logisticPlanning.utils.document.spec.SectionTitle;

/**
 * <p>
 * This evaluator will print diagrams showing the Empirical (Cumulative)
 * Distribution Functions (ECDFs)&nbsp;[<a href="#cite_HAFR2012RPBBOBES"
 * style="font-weight:bold">1</a>, <a href="#cite_HS1998ELVAPAR"
 * style="font-weight:bold">2</a>] aggregated over benchmark sets that have
 * been grouped together in terms of their scale.
 * </p>
 * <h2>References</h2>
 * <ol>
 * <li><div><span id="cite_HAFR2012RPBBOBES" /><a
 * href="http://www.lri.fr/~hansen/">Nikolaus Hansen</a>, <a
 * href="http://www.lri.fr/~auger/">Anne Auger</a>, <a
 * href="http://www.researchgate.net/profile/Steffen_Finck/">Steffen
 * Finck</a>, and&nbsp;<a
 * href="https://tao.lri.fr/tiki-index.php?page=people">Raymond Ros</a>:
 * <span style="font-weight:bold">&ldquo;Real-Parameter Black-Box
 * Optimization Benchmarking: Experimental Setup,&rdquo;</span> <span
 * style="font-style:italic;font-family:cursive;">Technical Report</span>
 * March&nbsp;24, 2012; published by Orsay, France: Universit&#233; Paris
 * Sud, Institut National de Recherche en Informatique et en Automatique
 * (INRIA) Futurs, &#201;quipe TAO. <div>link: [<a href=
 * "http://coco.lri.fr/BBOB-downloads/download11.05/bbobdocexperiment.pdf"
 * >1</a>]</div></div></li>
 * <li><div><span id="cite_HS1998ELVAPAR" /><a
 * href="http://www.cs.ubc.ca/~hoos/">Holger H. Hoos</a> and&nbsp;<a
 * href="http://iridia.ulb.ac.be/~stuetzle/">Thomas St&#252;tzle</a>: <span
 * style="font-weight:bold">&ldquo;Evaluating Las Vegas Algorithms &#8210;
 * Pitfalls and Remedies,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the 14th
 * Conference on Uncertainty in Artificial Intelligence (UAI'98)</span>,
 * July&nbsp;24&ndash;26, 1998, Madison, WI, USA, pages 238&ndash;245,
 * Gregory F. Cooper and&nbsp;Serafin Moral, editors, San Francisco, CA,
 * USA: Morgan Kaufmann Publishers Inc.. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/155860555X">1-55860-555-X</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9781558605558">978-1-55860
 * -555-8</a>. <div>link: [<a href=
 * "http://www.intellektik.informatik.tu-darmstadt.de/TR/1998/98-02.ps.Z"
 * >1</a>]</div></div></li>
 * </ol>
 */
final class _ExperimentSetECDFOverScaleDiagramsHolder extends
ComparisonModule {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * create!
   *
   * @param owner
   *          the macro's owner
   */
  _ExperimentSetECDFOverScaleDiagramsHolder(final Module owner) {
    super(_ExperimentSetECDFBase._NAME_PFX + "OverScale",//$NON-NLS-1$
        owner, false);
  }

  /** {@inheritDoc} */
  @Override
  protected final boolean hasContent(final ExperimentSet data) {
    return false;
  }

  /** {@inheritDoc} */
  @Override
  protected final void initialize(final Header header,
      final ExperimentSet data) throws IOException {//
    super.initialize(header, data);
    Macros.ECDF.define(header);
  }

  /** {@inheritDoc} */
  @SuppressWarnings({ "unused", "unchecked" })
  @Override
  protected final void createChildModules() {
    AllScaleInstancesProperty<ExperimentSet>[] ch;

    ch = new AllScaleInstancesProperty[] {
        AllScaleInstancesProperty.SHARED_POWERS_OF_2,
        AllScaleInstancesProperty.SHARED_POWERS_OF_10 };

    for (final AllScaleInstancesProperty<ExperimentSet> scale : ch) {
      for (final Accessor axs : Accessor.TIME_MEASURES) {
        for (final double goal : _ExperimentSetECDFDiagrams.GOALS) {
          new _ExperimentSetECDFOverScaleDiagrams(this, //
              RunSetECDFProperty.getInstance(axs, goal),//
              scale,//
              ((scale.getBase() == 2) && (goal <= 0d) && //
                  Accessor.UNBIASED_TIME_MEASURES.contains(axs)
                  // ((axs == Accessor.DE) || //
                  // (axs == Accessor.FE) || //
                  // (axs == Accessor.NORMALIZED_RUNTIME))
                  ));
        }
      }
    }
  }

  /** {@inheritDoc} */
  @Override
  protected final void writeSectionTitle(final SectionTitle title,
      final ExperimentSet data) throws IOException {
    title.macroInvoke(Macros.ECDF);
    title
    .write(" over Problem Scale for Different Time Measures and Goals"); //$NON-NLS-1$
  }
}
