package org.logisticPlanning.tsp.evaluation.modules.impl.descriptions.eval;

import java.io.IOException;

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
import org.logisticPlanning.utils.document.spec.EMathName;
import org.logisticPlanning.utils.document.spec.EMathOp;
import org.logisticPlanning.utils.document.spec.ESequenceType;
import org.logisticPlanning.utils.document.spec.Equation;
import org.logisticPlanning.utils.document.spec.EquationBody;
import org.logisticPlanning.utils.document.spec.Header;
import org.logisticPlanning.utils.document.spec.InlineMath;
import org.logisticPlanning.utils.document.spec.Label;
import org.logisticPlanning.utils.document.spec.MacroInvocation;
import org.logisticPlanning.utils.document.spec.MacroParameter;
import org.logisticPlanning.utils.document.spec.MathName;
import org.logisticPlanning.utils.document.spec.MathOp;
import org.logisticPlanning.utils.document.spec.MathOpParam;
import org.logisticPlanning.utils.document.spec.NormalText;
import org.logisticPlanning.utils.document.spec.SectionBody;
import org.logisticPlanning.utils.document.spec.SectionTitle;
import org.logisticPlanning.utils.document.spec.bib.BibAuthor;
import org.logisticPlanning.utils.document.spec.bib.BibAuthors;
import org.logisticPlanning.utils.document.spec.bib.BibDate;
import org.logisticPlanning.utils.document.spec.bib.BibInProceedings;
import org.logisticPlanning.utils.document.spec.bib.BibProceedings;
import org.logisticPlanning.utils.document.spec.bib.EBibMonth;

/**
 * A description of the Estimated Running Time (ERT)&nbsp;[<a
 * href="#cite_COCO2012CCCO" style="font-weight:bold">1</a>, <a
 * href="#cite_HAFR2012RPBBOBES" style="font-weight:bold">2</a>, <a
 * href="#cite_AH2005PEOAALSEA" style="font-weight:bold">3</a>], which
 * represents the estimated time that an algorithm needs to achieve a given
 * goal solution quality. As a quality measure, it is subject to
 * minimization, since we obviously want algorithms that find solutions
 * quickly. <h2>References</h2>
 * <ol>
 * <li><div><span id="cite_COCO2012CCCO" /><a
 * href="http://www.lri.fr/~hansen/">Nikolaus Hansen</a>, <a
 * href="http://www.lri.fr/~auger/">Anne Auger</a>, <a
 * href="https://tao.lri.fr/tiki-index.php?page=people">Raymond Ros</a>,
 * and&nbsp;Dimo Brockhoff: <span
 * style="font-style:italic;font-family:cursive;">&ldquo;COCO (COmparing
 * Continuous Optimisers),&rdquo;</span> (Website), October&nbsp;17, 2012,
 * Orsay, France: Universit&#233; Paris Sud, Institut National de Recherche
 * en Informatique et en Automatique (INRIA) Futurs, &#201;quipe TAO.
 * <div>link: [<a
 * href="http://coco.gforge.inria.fr/doku.php?id=start">1</a>]</div></div></li>
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
 * <li><div><span id="cite_AH2005PEOAALSEA" /><a
 * href="http://www.lri.fr/~auger/">Anne Auger</a> and&nbsp;<a
 * href="http://www.lri.fr/~hansen/">Nikolaus Hansen</a>: <span
 * style="font-weight:bold">&ldquo;Performance Evaluation of an Advanced
 * Local Search Evolutionary Algorithm,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the IEEE
 * Congress on Evolutionary Computation (CEC'05)</span>,
 * September&nbsp;2&ndash;5, 2005, Edinburgh, Scotland, UK, pages
 * 1777&ndash;1784, <a
 * href="http://www.macs.hw.ac.uk/staff-directory/david-wolfe-corne.htm"
 * >David Wolfe Corne</a>, <a
 * href="http://cs.adelaide.edu.au/~zbyszek/">Zbigniew Michalewicz</a>, <a
 * href="http://sc.snu.ac.kr/~rim/index.html">Robert Ian McKay</a>, <a
 * href="http://www.cs.vu.nl/~gusz/">&#193;goston E. Eiben</a> aka.
 * Gusz/Guszti, <a
 * href="http://www.natural-selection.com/people_dfogel.html">David B.
 * Fogel</a>, Carlos M. Fonseca, <a
 * href="https://www.ads.tuwien.ac.at/raidl/">G&#252;nther R. Raidl</a>, <a
 * href="http://vlab.ee.nus.edu.sg/~kctan/">Kay Chen Tan</a>, and&nbsp;Ali
 * M. S. Zalzala, editors, Piscataway, NJ, USA: IEEE Computer Society.
 * ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0780393635">0-7803-9363-5</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780780393639">978-0-7803
 * -9363-9</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1109/CEC.2005.1554903">10.1109/CEC.2005
 * .1554903</a>; OCLC:&nbsp;<a
 * href="https://www.worldcat.org/oclc/62773008">62773008</a>; Google Book
 * ID:&nbsp;<a
 * href="http://books.google.com/books?id=-QIVAAAACAAJ">-QIVAAAACAAJ</a>;
 * INSPEC Accession Number:&nbsp;8715465. <div>link: [<a
 * href="http://www.lri.fr/~hansen/cec2005localcmaes.pdf">1</a>];
 * CiteSeer<sup>x</sup><sub style="font-style:italic">&#946;</sub>:&nbsp;<a
 * href=
 * "http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.71.6248">10.1
 * .1.71 .6248</a></div></div></li>
 * <li><div><span id="cite_PROC1997CEC" /><span
 * style="font-style:italic;font-family:cursive;">&ldquo;Proceedings of the
 * IEEE International Conference on Evolutionary Computation
 * (CEC'97),&rdquo;</span> April&nbsp;13&ndash;16, 1997, <a
 * href="http://www.liacs.nl/~baeck/contact.htm">Thomas B&#228;ck</a>, <a
 * href="http://cs.adelaide.edu.au/~zbyszek/">Zbigniew Michalewicz</a>,
 * and&nbsp;<a href="http://www.cs.bham.ac.uk/~xin/">Xin Yao</a> <span
 * style="color:gray">[&#23002;&#26032;</span>], editors, Piscataway, NJ,
 * USA: IEEE Computer Society. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0780339495">0-7803-3949-5</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780780339491">978-0-7803
 * -3949-1</a>; Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=6JZVAAAAMAAJ">6JZVAAAAMAAJ</a>
 * and&nbsp;<a
 * href="http://books.google.com/books?id=so7XHQAACAAJ">so7XHQAACAAJ</a>;
 * INSPEC Accession Number:&nbsp;5577833</div></li>
 * <li><div><span id="cite_P1997DEVTFOT2I" />Kenneth V. Price: <span
 * style="font-weight:bold">&ldquo;Differential Evolution vs. the Functions
 * of the 2nd ICEO,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the IEEE
 * International Conference on Evolutionary Computation (CEC'97)</span>,
 * April&nbsp;13&ndash;16, 1997, Indianapolis, IN, USA, pages
 * 153&ndash;157, <a href="http://www.liacs.nl/~baeck/contact.htm">Thomas
 * B&#228;ck</a>, <a href="http://cs.adelaide.edu.au/~zbyszek/">Zbigniew
 * Michalewicz</a>, and&nbsp;<a href="http://www.cs.bham.ac.uk/~xin/">Xin
 * Yao</a> <span style="color:gray">[&#23002;&#26032;</span>], editors,
 * Piscataway, NJ, USA: IEEE Computer Society. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0780339495">0-7803-3949-5</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780780339491">978-0-7803
 * -3949-1</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1109/ICEC.1997.592287">10.1109/ICEC.1997
 * .592287</a>; Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=6JZVAAAAMAAJ">6JZVAAAAMAAJ</a>;
 * INSPEC Accession Number:&nbsp;5573043</div></li>
 * </ol>
 */
public final class DescERT extends DescriptionModule {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * the proceedings&nbsp;[<a href="#cite_PROC1997CEC"
   * style="font-weight:bold">4</a>]
   */
  public static final BibProceedings PROC1997CEC;

  /**
   * the in-proceedings&nbsp;[<a href="#cite_P1997DEVTFOT2I"
   * style="font-weight:bold">5</a>]
   */
  public static final BibInProceedings P1997DEVTFOT2I;

  static {
    PROC1997CEC = new BibProceedings(
        "Proceedings of the IEEE International Conference on Evolutionary Computation (CEC'97)",//$NON-NLS-1$
        new BibDate(1997, EBibMonth.APRIL, 13), //
        new BibDate(1997, EBibMonth.APRIL, 16), //
        new BibAuthors(//
            new BibAuthor("Thomas", "B\u00e4ck"),//$NON-NLS-1$//$NON-NLS-2$
            new BibAuthor("Zbigniew", "Michalewicz"),//$NON-NLS-1$//$NON-NLS-2$
            new BibAuthor("Xin", "Yao")//$NON-NLS-1$//$NON-NLS-2$
        ),//
        "Indianapolis, IN, USA",//$NON-NLS-1$
        "IEEE Computer Society", "Piscataway, NJ, USA",//$NON-NLS-1$//$NON-NLS-2$
        null, null, null, null);

    P1997DEVTFOT2I = new BibInProceedings(new BibAuthors(//
        new BibAuthor("Kenneth V.", "Price")),//$NON-NLS-1$//$NON-NLS-2$
        "Differential Evolution vs. the Functions of the 2nd ICEO",//$NON-NLS-1$
        DescERT.PROC1997CEC, "153", "157", null,//$NON-NLS-1$//$NON-NLS-2$
        null, "10.1109/ICEC.1997.592287");//$NON-NLS-1$
  }

  /**
   * the time measures
   *
   * @serial the internal reference to the time measures description
   */
  private final DescTimeMeasures m_time;

  /**
   * the relative objective values
   *
   * @serial the internal reference to the relative objective value
   *         description
   */
  private final DescRelativeObjectiveValues m_rel;

  /**
   * the quality measures
   *
   * @serial the internal reference to the quality measure description
   */
  private final DescQualityMeasures m_quality;

  /**
   * create!
   *
   * @param owner
   *          the macro's owner
   */
  DescERT(final Module owner) {
    super("ert", owner, false); //$NON-NLS-1$

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
    Macros.SCALE.define(header);
    Macros.F_OPTIMAL.define(header);
    Macros.F_THRESHOLD.define(header);
    Macros.F_THRESHOLD_RELATIVE.define(header);
    Macros.ERT.define(header);
    Macros.ERTi.define(header);
    Macros.ERTib.define(header);
  }

  /** {@inheritDoc} */
  @Override
  protected void writeSectionTitle(final SectionTitle title,
      final ExperimentSet data) throws IOException {
    title.write("Estimated Running Time"); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  protected void writeSectionBody(final SectionBody body,
      final ExperimentSet data) throws IOException {
    final Label equ;

    body.write("The estimated running time to success "); //$NON-NLS-1$
    body.macroInvoke(Macros.ERT);

    body.cite(ECitationMode.BY_ID_IN_SENTENCE,
        DescQualityMeasures.HAFR2012RPBBOBES,
        DescQualityMeasures.AH2005PEOAALSEA, DescERT.P1997DEVTFOT2I);
    body.write(//
    " denotes the amount of time (according to some measure) that passes in expectation until an optimization algorithm reaches a given target objective function value "); //$NON-NLS-1$
    body.macroInvoke(Macros.F_THRESHOLD);
    body.write(//
    " for the first time. In well-known benchmark suites such as COCO/BBOB"); //$NON-NLS-1$
    body.cite(ECitationMode.BY_ID_IN_SENTENCE,
        DescQualityMeasures.COCO2012CCCO,
        DescQualityMeasures.HAFR2012RPBBOBES,
        DescQualityMeasures.AH2005PEOAALSEA);

    body.write(//
    " it is a prominent performance indicator and usually measured in terms of the "); //$NON-NLS-1$
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

    try (Equation eq = body.equation(Label.AUTO_LABEL)) {
      try (EquationBody ebdy = eq.equationBody()) {
        try (MathOp equals = ebdy.mathOp(EMathOp.CMP_EQUALS)) {

          try (MathOpParam equals1 = equals.mathOpParam()) {
            try (MacroInvocation mi = body.macroInvocation(Macros.ERTib)) {
              try (MacroParameter mp = mi.macroParameter()) {
                mp.writeChar('T');
              }
              try (MacroParameter mp = mi.macroParameter()) {
                mp.macroInvoke(Macros.F_THRESHOLD);
              }
            }
          }

          try (MathOpParam equals2 = equals.mathOpParam()) {
            try (MathOp div = equals2.mathOp(EMathOp.DIV)) {

              try (MathOpParam div1 = div.mathOpParam()) {
                try (MathOp sfa = div1
                    .mathOp(EMathOp.AGGREGATE_SUM_FOR_ALL)) {

                  try (MathOpParam sfa2 = sfa.mathOpParam()) {
                    try (NormalText tn4 = sfa2.normalText()) {
                      tn4.write("runs"); //$NON-NLS-1$
                    }
                  }

                  try (MathOpParam sfa1 = sfa.mathOpParam()) {
                    try (NormalText tn1 = sfa1.normalText()) {
                      tn1.write("runtime measured in "); //$NON-NLS-1$
                    }
                    try (MathName mn = sfa1.mathName(EMathName.SCALAR)) {
                      mn.writeChar('T');
                    }
                    try (NormalText tn2 = sfa1.normalText()) {
                      tn2.write(" until "); //$NON-NLS-1$
                    }
                    sfa1.macroInvoke(Macros.F_THRESHOLD);
                    try (NormalText tn3 = sfa1.normalText()) {
                      tn3.write(" is reached"); //$NON-NLS-1$
                    }
                  }
                }
              }

              try (MathOpParam div2 = div.mathOpParam()) {
                try (NormalText tn5 = div2.normalText()) {
                  tn5.write("# runs that reach "); //$NON-NLS-1$
                  div2.macroInvoke(Macros.F_THRESHOLD);
                }
              }

            }
          }
        }
      }
      equ = eq.getLabel();
    }

    body.write("For a given time measure ");//$NON-NLS-1$

    try (InlineMath im = body.inlineMath()) {
      try (MathName mn = im.mathName(EMathName.SCALAR)) {
        mn.writeChar('T');
      }
    }

    body.write(" used, the ");//$NON-NLS-1$

    try (MacroInvocation mi = body.macroInvocation(Macros.ERTib)) {
      try (MacroParameter mp = mi.macroParameter()) {
        mp.writeChar('T');
      }
      try (MacroParameter mp = mi.macroParameter()) {
        mp.macroInvoke(Macros.F_THRESHOLD);
      }
    }

    body.write(" then avails to ");//$NON-NLS-1$
    body.reference(equ);
    body.write(", i.e., the sum of the runtime (according to time measure ");//$NON-NLS-1$

    try (InlineMath im = body.inlineMath()) {
      try (MathName mn = im.mathName(EMathName.SCALAR)) {
        mn.writeChar('T');
      }
    }

    body.write(") spent before reaching ");//$NON-NLS-1$
    body.macroInvoke(Macros.F_THRESHOLD);
    body.write(" over all runs, divided by the number of successful runs. If a run does never reach ");//$NON-NLS-1$
    body.macroInvoke(Macros.F_THRESHOLD);
    body.write(", then all the time spent for this run is added. The ");//$NON-NLS-1$
    body.macroInvoke(Macros.ERT);
    body.write(" becomes infinite (");//$NON-NLS-1$
    body.writeDouble(Double.POSITIVE_INFINITY);
    body.write(") if no run reaches ");//$NON-NLS-1$
    body.macroInvoke(Macros.F_THRESHOLD);
    body.write(". The ");//$NON-NLS-1$
    body.macroInvoke(Macros.ERT);
    body.write(//
    " is an example of a horizontal-cut performance measure as introduced in ");//$NON-NLS-1$
    body.reference(this.m_quality.getLabel(data));
    body.write(" and");//$NON-NLS-1$
    body.cite(ECitationMode.BY_ID_IN_SENTENCE,
        DescQualityMeasures.HAFR2012RPBBOBES);
    body.writeChar('.');
    body.writeLinebreak();

    body.write("In our evaluation, the objective value ");//$NON-NLS-1$
    body.macroInvoke(Macros.F_OPTIMAL);
    body.write(" of the known optimum is often used as threshold ");//$NON-NLS-1$
    body.macroInvoke(Macros.F_THRESHOLD);
    body.write(" and the ");//$NON-NLS-1$
    body.macroInvoke(Macros.ERT);
    body.write(//
    " then denotes the estimated runtime necessary to discover the best possible solution. However, thresholds very close to the global optimum also make sense, because often we are happy with a near-optimal solution, if we can get it fast.");//$NON-NLS-1$

    body.writeLinebreak();

    body.write(//
    "An optimization algorithm is good if it has a small expected runtime (regardless according to which measure) for a given goal objective value ");//$NON-NLS-1$
    body.macroInvoke(Macros.F_THRESHOLD);
    body.write(". If the ");//$NON-NLS-1$
    body.macroInvoke(Macros.ERT);
    body.write(" for small thresholds ");//$NON-NLS-1$
    body.macroInvoke(Macros.F_THRESHOLD);
    body.write("  is low, the algorithm is good.");//$NON-NLS-1$

    body.writeLinebreak();

    body.write("Since the unit of the ");//$NON-NLS-1$
    body.macroInvoke(Macros.ERT);
    body.write(//
    " is always a runtime measure, the same scaling mechanism as discussed in ");//$NON-NLS-1$
    body.reference(this.m_time.getLabel(data));
    body.write(//
    " apply, i.e., ");//$NON-NLS-1$

    body.writeSequence(new AccessorSequence(Accessor.TIME_MEASURES, true,
        false, //
        " can be scaled by ", body),//$NON-NLS-1$
        ESequenceType.AND, false);

    body.write(//
    ". This way, the results of experiments with benchmarks of different scale ");//$NON-NLS-1$
    body.macroInvoke(Macros.SCALE);
    body.write(//
    ", i.e., number of nodes/cities, can become more comparable.");//$NON-NLS-1$

    body.writeLinebreak();

    body.write("In order to make the ");//$NON-NLS-1$
    body.macroInvoke(Macros.ERT);
    body.write(//
    "s from different benchmark cases comparable, we will frequently use relative target objective value thresholds ");//$NON-NLS-1$
    body.macroInvoke(Macros.F_THRESHOLD_RELATIVE);
    body.write(//
    " instead of absolute ones (");//$NON-NLS-1$
    body.macroInvoke(Macros.F_THRESHOLD);
    body.write(") as discussed in ");//$NON-NLS-1$
    body.reference(this.m_rel.getLabel(data));
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
