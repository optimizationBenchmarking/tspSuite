package org.logisticPlanning.tsp.evaluation.modules.impl.descriptions.eval;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.logisticPlanning.tsp.evaluation.data.Accessor;
import org.logisticPlanning.tsp.evaluation.data.ExperimentSet;
import org.logisticPlanning.tsp.evaluation.modules.impl.AccessorSequence;
import org.logisticPlanning.tsp.evaluation.modules.impl.descriptions.tsp.DescRelativeObjectiveValues;
import org.logisticPlanning.tsp.evaluation.modules.impl.descriptions.tsp.DescTimeMeasures;
import org.logisticPlanning.tsp.evaluation.modules.impl.macros.Macros;
import org.logisticPlanning.tsp.evaluation.modules.spec.DescriptionModule;
import org.logisticPlanning.tsp.evaluation.modules.spec.Module;
import org.logisticPlanning.utils.collections.conditions.EqualsCondition;
import org.logisticPlanning.utils.collections.conditions.NotCondition;
import org.logisticPlanning.utils.document.spec.ECitationMode;
import org.logisticPlanning.utils.document.spec.ESequenceType;
import org.logisticPlanning.utils.document.spec.Header;
import org.logisticPlanning.utils.document.spec.SectionBody;
import org.logisticPlanning.utils.document.spec.SectionTitle;
import org.logisticPlanning.utils.document.spec.bib.BibAuthor;
import org.logisticPlanning.utils.document.spec.bib.BibAuthors;
import org.logisticPlanning.utils.document.spec.bib.BibDate;
import org.logisticPlanning.utils.document.spec.bib.BibInProceedings;
import org.logisticPlanning.utils.document.spec.bib.BibProceedings;
import org.logisticPlanning.utils.document.spec.bib.EBibMonth;

/**
 * A description of the Empirical (Cumulative) Distribution Functions
 * (ECDFs)&nbsp;[<a href="#cite_HAFR2012RPBBOBES"
 * style="font-weight:bold">1</a>, <a href="#cite_HS1998ELVAPAR"
 * style="font-weight:bold">2</a>, <a href="#cite_TH2004UAIAEEFSAFSAMS"
 * style="font-weight:bold">3</a>], which represents the fraction of runs
 * of an algorithm that have reached a given goal objective value (usually
 * the optimum) for a given point in time. The ECDF can become at most
 * {@code 1}, which is best and means that all runs have reached the goal
 * objective value. An algorithm is the better the earlier it arrives at
 * that point. <h2>References</h2>
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
 * <li><div><span id="cite_TH2004UAIAEEFSAFSAMS" /><a
 * href="https://cs.uwaterloo.ca/~dtompkin/">Dave Andrew Douglas
 * Tompkins</a> and&nbsp;<a href="http://www.cs.ubc.ca/~hoos/">Holger H.
 * Hoos</a>: <span style="font-weight:bold">&ldquo;UBCSAT: An
 * Implementation and Experimentation Environment for SLS Algorithms for
 * SAT and MAX-SAT,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Revised Selected Papers
 * from the Seventh International Conference on Theory and Applications of
 * Satisfiability Testing (SAT'04)</span>, May&nbsp;10&ndash;13, 2004,
 * Vancouver, BC, Canada, pages 306&ndash;320, <a
 * href="http://www.cs.ubc.ca/~hoos/">Holger H. Hoos</a> and&nbsp;<a
 * href="http://www.cs.sfu.ca/~mitchell/">David G. Mitchell</a>, editors,
 * volume 3542 of Lecture Notes in Computer Science (LNCS), Berlin,
 * Germany: Springer-Verlag GmbH. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/9783540278290"
 * >978-3-540-27829-0</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1007/11527695_24">10.1007/11527695_24</a>;
 * ISSN:&nbsp;<a
 * href="https://www.worldcat.org/issn/03029743">0302-9743</a> and&nbsp;<a
 * href="https://www.worldcat.org/issn/16113349">1611-3349</a>. <div>link:
 * [<a href="http://ubcsat.dtompkins.com/downloads/sat04proc-ubcsat.pdf"
 * >1</a>]</div></div></li>
 * </ol>
 */
public final class DescECDF extends DescriptionModule {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * the in-proceedings&nbsp;[<a href="#cite_TH2004UAIAEEFSAFSAMS"
   * style="font-weight:bold">3</a>]
   */
  public static final BibInProceedings TH2004UAIAEEFSAFSAMS;

  static {
    try {
      TH2004UAIAEEFSAFSAMS = new BibInProceedings(
          new BibAuthors(new BibAuthor("Dave Andrew Douglas", "Tompkins"),//$NON-NLS-1$ //$NON-NLS-2$
              DescTimeMeasures.HOLGER_H_HOOS),//
              "UBCSAT: An Implementation and Experimentation Environment for SLS Algorithms for SAT and MAX-SAT",//$NON-NLS-1$
              new BibProceedings(
                  "Revised Selected Papers from the Seventh International Conference on Theory and Applications of Satisfiability Testing (SAT'04)",//$NON-NLS-1$
                  new BibDate(2004, EBibMonth.MAY, 10), //
                  new BibDate(2004, EBibMonth.MAY, 13), //
                  new BibAuthors(//
                      DescTimeMeasures.HOLGER_H_HOOS,//
                      new BibAuthor("David G.", "Mitchell")//$NON-NLS-1$//$NON-NLS-2$
                      ),//
                      "Vancouver, BC, Canada",//$NON-NLS-1$
                      "Springer-Verlag GmbH", "Berlin, Germany",//$NON-NLS-1$//$NON-NLS-2$
                      "Lecture Notes in Computer Science (LNCS)", "3542",//$NON-NLS-1$//$NON-NLS-2$
                      null, "10.1007/11527695"),//$NON-NLS-1$
                      "306", "320", null,//$NON-NLS-1$//$NON-NLS-2$
                      new URI(
                          "http://ubcsat.dtompkins.com/downloads/sat04proc-ubcsat.pdf?attredirects=0"),//$NON-NLS-1$
          "10.1007/11527695_24");//$NON-NLS-1$

    } catch (final URISyntaxException use) {
      throw new RuntimeException(use);
    }
  }

  /** the time measures */
  private final DescTimeMeasures m_time;

  /** the relative objective values */
  private final DescRelativeObjectiveValues m_rel;

  /** the quality measures */
  private final DescQualityMeasures m_quality;

  /**
   * create!
   *
   * @param owner
   *          the macro's owner
   */
  DescECDF(final Module owner) {
    super("ecdf", owner, false); //$NON-NLS-1$

    this.m_time = this.getRoot().findInstance(DescTimeMeasures.class);
    this.addDependency(this.m_time);

    this.m_rel = this.getRoot().findInstance(
        DescRelativeObjectiveValues.class);
    this.addDependency(this.m_rel);

    this.m_quality = this.getRoot()
        .findInstance(DescQualityMeasures.class);
    this.addDependency(this.m_quality);
  }

  /** {@inheritDoc} */
  @Override
  protected void initialize(final Header header, final ExperimentSet data)
      throws IOException {//
    super.initialize(header, data);
    Macros.F_THRESHOLD_RELATIVE.define(header);
    Macros.ECDF.define(header);
  }

  /** {@inheritDoc} */
  @Override
  protected void writeSectionTitle(final SectionTitle title,
      final ExperimentSet data) throws IOException {
    title.write("Empirical Cumulative Distribution Function"); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  protected void writeSectionBody(final SectionBody body,
      final ExperimentSet data) throws IOException {

    body.write("The empirical cumulative distribution function "); //$NON-NLS-1$
    body.macroInvoke(Macros.ECDF);

    body.cite(ECitationMode.BY_ID_IN_SENTENCE,
        DescQualityMeasures.HAFR2012RPBBOBES,
        DescTimeMeasures.HS1998ELVAPAR, DescECDF.TH2004UAIAEEFSAFSAMS);
    body.write(//
        " is a function that returns the fraction of runs of an optimization algorithm that have reached a given target objective function value "); //$NON-NLS-1$
    body.macroInvoke(Macros.F_THRESHOLD);
    body.write(//
        " for a given point in time (defined according to some time measure, see "); //$NON-NLS-1$
    body.reference(this.m_time.getLabel(data));

    body.write("). The "); //$NON-NLS-1$
    body.macroInvoke(Macros.ECDF);
    body.write(" (at a given time) is always between"); //$NON-NLS-1$
    body.writeInt(0);
    body.write(" and "); //$NON-NLS-1$
    body.writeInt(1);
    body.write(", where "); //$NON-NLS-1$
    body.writeInt(1);
    body.write(//
        " is best, since it denotes that all runs have reached the target objective value "); //$NON-NLS-1$
    body.macroInvoke(Macros.F_THRESHOLD);
    body.write(". An algorithm is the better, the sooner its "); //$NON-NLS-1$
    body.macroInvoke(Macros.ECDF);
    body.write(" reaches high values."); //$NON-NLS-1$

    body.writeLinebreak();
    body.write("In order to be able to aggregate the "); //$NON-NLS-1$
    body.macroInvoke(Macros.ECDF);
    body.write(//
        "s over different scenarios, we use relative objective value goals instead of absolute ones (see "); //$NON-NLS-1$
    body.reference(this.m_rel.getLabel(data));
    body.write(//
        "). Aggregation is done by computing the arithmetic mean instead of counting successes of all runs. This avoids problems if there are more runs available for one benchmark instance than for another one."); //$NON-NLS-1$
    body.macroInvoke(Macros.ECDF);
    body.write(" reaches high values."); //$NON-NLS-1$

    body.writeLinebreak();
    body.write(//
        "In well-known benchmark suites such as COCO/BBOB"); //$NON-NLS-1$
    body.cite(ECitationMode.BY_ID_IN_SENTENCE,
        DescQualityMeasures.COCO2012CCCO,
        DescQualityMeasures.HAFR2012RPBBOBES);

    body.write(//
        " it is a prominent performance indicator and usually plotted over the consumed "); //$NON-NLS-1$
    Accessor.FE.writeLongName(body, true);
    body.write(" ("); //$NON-NLS-1$
    Accessor.FE.writeShortName(body, true);
    body.write(//
        "). In the context of this work, there are more time measures that we can use, such as "); //$NON-NLS-1$

    body.writeSequence(
        new AccessorSequence(Accessor.TIME_MEASURES
            .select(new NotCondition<>(new EqualsCondition(Accessor.FE))),
            true, true, true, body), ESequenceType.OR, false);

    body.write(//
        ", since we measure all these values in our log files as described in ");//$NON-NLS-1$

    body.reference(this.m_time.getLabel(data));

    body.writeChar('.');

    super.writeSectionBody(body, data);
  }

  /** {@inheritDoc} */
  @Override
  public int compareTo(final Module other) {
    if (other instanceof DescAUC) {
      return (-1);
    }
    return super.compareTo(other);
  }
}
