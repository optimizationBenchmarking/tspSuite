package org.logisticPlanning.tsp.evaluation.data.properties.litComp.results;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.logisticPlanning.tsp.benchmarking.instances.Instance;
import org.logisticPlanning.tsp.evaluation.data.Accessor;
import org.logisticPlanning.tsp.evaluation.data.properties.litComp.LiteratureResultPoint;
import org.logisticPlanning.tsp.evaluation.data.properties.litComp.LiteratureResultPointSet;
import org.logisticPlanning.tsp.evaluation.modules.impl.macros.Macros;
import org.logisticPlanning.utils.document.spec.AbstractTextComplex;
import org.logisticPlanning.utils.document.spec.ECitationMode;
import org.logisticPlanning.utils.document.spec.EMathName;
import org.logisticPlanning.utils.document.spec.EMathOp;
import org.logisticPlanning.utils.document.spec.Header;
import org.logisticPlanning.utils.document.spec.InlineMath;
import org.logisticPlanning.utils.document.spec.MathName;
import org.logisticPlanning.utils.document.spec.MathOp;
import org.logisticPlanning.utils.document.spec.MathOpParam;
import org.logisticPlanning.utils.document.spec.bib.BibAuthor;
import org.logisticPlanning.utils.document.spec.bib.BibAuthors;
import org.logisticPlanning.utils.document.spec.bib.BibDate;
import org.logisticPlanning.utils.document.spec.bib.BibInProceedings;
import org.logisticPlanning.utils.document.spec.bib.BibProceedings;
import org.logisticPlanning.utils.document.spec.bib.BibRecord;
import org.logisticPlanning.utils.document.spec.bib.EBibMonth;
import org.logisticPlanning.utils.math.statistics.EStatisticParameter;

/**
 * The results from&nbsp;[<a href="#cite_SH1997MMASALSFTTSP"
 * style="font-weight:bold">1</a>]. <h2>References</h2>
 * <ol>
 * <li><div><span id="cite_SH1997MMASALSFTTSP" /><a
 * href="http://iridia.ulb.ac.be/~stuetzle/">Thomas St&#252;tzle</a>
 * and&nbsp;<a href="http://www.cs.ubc.ca/~hoos/">Holger H. Hoos</a>: <span
 * style="font-weight:bold">&ldquo;MAX-MIN Ant System and Local Search for
 * the Traveling Salesman Problem,&rdquo;</span> in <span
 * style="font-style:italic;font-family:cursive;">Proceedings of the IEEE
 * International Conference on Evolutionary Computation (CEC'97)</span>,
 * April&nbsp;13&ndash;16, 1997, Indianapolis, IN, USA, pages
 * 309&ndash;314, <a href="http://www.liacs.nl/~baeck/contact.htm">Thomas
 * B&#228;ck</a>, <a href="http://cs.adelaide.edu.au/~zbyszek/">Zbigniew
 * Michalewicz</a>, and&nbsp;<a href="http://www.cs.bham.ac.uk/~xin/">Xin
 * Yao</a> <span style="color:gray">[&#23002;&#26032;</span>], editors,
 * Piscataway, NJ, USA: IEEE Computer Society. ISBN:&nbsp;<a
 * href="https://www.worldcat.org/isbn/0780339495">0-7803-3949-5</a>
 * and&nbsp;<a
 * href="https://www.worldcat.org/isbn/9780780339491">978-0-7803
 * -3949-1</a>; doi:&nbsp;<a
 * href="http://dx.doi.org/10.1109/ICEC.1997.592327">10.1109/ICEC.1997
 * .592327</a>; Google Book ID:&nbsp;<a
 * href="http://books.google.com/books?id=6JZVAAAAMAAJ">6JZVAAAAMAAJ</a>;
 * INSPEC Accession Number:&nbsp;5573070. <div>link: [<a
 * href="http://www.gta.ufrj.br/ensino/cpe717-2011/stutzle97-icec.pdf"
 * >1</a>]; CiteSeer<sup>x</sup><sub
 * style="font-style:italic">&#946;</sub>:&nbsp;<a href=
 * "http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.46.609"
 * >10.1.1.46 .609</a></div></div></li>
 * </ol>
 */
final class _SH1997MMASALSFTTSP extends LiteratureResultPointSet {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * the result point set for paper&nbsp;[<a
   * href="#cite_SH1997MMASALSFTTSP" style="font-weight:bold">1</a>]
   */
  static final LiteratureResultPointSet SH1997MMASALSFTTSP = new _SH1997MMASALSFTTSP();

  /**
   * get the bib record
   *
   * @return the bib record
   */
  private static final BibRecord __rec() {
    try {
      return new BibInProceedings(
          //
          new BibAuthors(//
              new BibAuthor("Thomas", "St\u00fctzle"),//$NON-NLS-1$//$NON-NLS-2$
              new BibAuthor("Holger H.", "Hoos")//$NON-NLS-1$//$NON-NLS-2$
          ),//
          "MAX-MIN Ant System and Local Search for the Traveling Salesman Problem",//$NON-NLS-1$
          new BibProceedings(//
              "CEC'97: Proceedings of the 5th International Conference on Parallel Problem Solving from Nature",//$NON-NLS-1$
              new BibDate(1997, EBibMonth.APRIL, 13),//
              new BibDate(1997, EBibMonth.APRIL, 16), //
              new BibAuthors(//
                  new BibAuthor("Thomas", "B\u00e4ck"),//$NON-NLS-1$//$NON-NLS-2$
                  new BibAuthor("Zbigniew", "Michalewicz"),//$NON-NLS-1$//$NON-NLS-2$
                  new BibAuthor("Xin", "Yao")//$NON-NLS-1$//$NON-NLS-2$
              ),//
              "Indianapolis, IN, USA",//$NON-NLS-1$
              "IEEE Computer Society",//$NON-NLS-1$
              "Piscataway, NJ, USA",//$NON-NLS-1$
              null, null,//
              null, null//
          ),//
          "309", "314",//$NON-NLS-1$//$NON-NLS-2$
          null,//
          new URI(
              "http://www.gta.ufrj.br/ensino/cpe717-2011/stutzle97-icec.pdf"),//$NON-NLS-1$
          "10.1109/ICEC.1997.592327"//$NON-NLS-1$
      );

    } catch (final URISyntaxException use) {
      throw new RuntimeException(use);
    }
  }

  /** create */
  _SH1997MMASALSFTTSP() {
    super("MAX-MIN Ant System with Local Search",//$NON-NLS-1$
        "MMAS-LS",//$NON-NLS-1$
        new BibRecord[] { _SH1997MMASALSFTTSP.__rec() },//
        new LiteratureResultPoint[] {//
        //
        // const=100
        //
            new LiteratureResultPoint(Instance.KROA100,//
                Accessor.FE, _SH1997MMASALSFTTSP.__steps(Instance.KROA100,
                    100l),//
                Accessor.F, 21427d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.D198,//
                Accessor.FE, _SH1997MMASALSFTTSP.__steps(Instance.D198,
                    100l),//
                Accessor.F, 15856d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.LIN318,//
                Accessor.FE, _SH1997MMASALSFTTSP.__steps(Instance.LIN318,
                    100l),//
                Accessor.F, 42426d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.PCB442,//
                Accessor.FE, _SH1997MMASALSFTTSP.__steps(Instance.PCB442,
                    100l),//
                Accessor.F, 51794d, EStatisticParameter.ARITHMETIC_MEAN),// //
            //
            new LiteratureResultPoint(Instance.ATT532,//
                Accessor.FE, _SH1997MMASALSFTTSP.__steps(Instance.ATT532,
                    100l),//
                Accessor.F, 28233d, EStatisticParameter.ARITHMETIC_MEAN),// //
            //
            new LiteratureResultPoint(Instance.RAT783,//
                Accessor.FE, _SH1997MMASALSFTTSP.__steps(Instance.RAT783,
                    100l),//
                Accessor.F, 9142d, EStatisticParameter.ARITHMETIC_MEAN),// //
            //
            new LiteratureResultPoint(Instance.P43,//
                Accessor.FE, _SH1997MMASALSFTTSP.__steps(Instance.P43,
                    100l),//
                Accessor.F, 5625d, EStatisticParameter.ARITHMETIC_MEAN),// //
            //
            new LiteratureResultPoint(Instance.RY48P,//
                Accessor.FE, _SH1997MMASALSFTTSP.__steps(Instance.RY48P,
                    100l),//
                Accessor.F, 14970d, EStatisticParameter.ARITHMETIC_MEAN),// //
            //
            new LiteratureResultPoint(Instance.FT70,//
                Accessor.FE, _SH1997MMASALSFTTSP.__steps(Instance.FT70,
                    100l),//
                Accessor.F, 39193d, EStatisticParameter.ARITHMETIC_MEAN),// //
            //
            new LiteratureResultPoint(Instance.KRO124P,//
                Accessor.FE, _SH1997MMASALSFTTSP.__steps(Instance.KRO124P,
                    100l),//
                Accessor.F, 39210d, EStatisticParameter.ARITHMETIC_MEAN),// //
            //
            new LiteratureResultPoint(Instance.FTV170,//
                Accessor.FE, _SH1997MMASALSFTTSP.__steps(Instance.FTV170,
                    100l),//
                Accessor.F, 2923d, EStatisticParameter.ARITHMETIC_MEAN),//

            //
            // const=500
            //
            new LiteratureResultPoint(Instance.KROA100,//
                Accessor.FE, _SH1997MMASALSFTTSP.__steps(Instance.KROA100,
                    500l),//
                Accessor.F, 21282d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.D198,//
                Accessor.FE, _SH1997MMASALSFTTSP.__steps(Instance.D198,
                    500l),//
                Accessor.F, 15817d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.LIN318,//
                Accessor.FE, _SH1997MMASALSFTTSP.__steps(Instance.LIN318,
                    500l),//
                Accessor.F, 42159d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.PCB442,//
                Accessor.FE, _SH1997MMASALSFTTSP.__steps(Instance.PCB442,
                    500l),//
                Accessor.F, 51265d, EStatisticParameter.ARITHMETIC_MEAN),// //
            //
            new LiteratureResultPoint(Instance.ATT532,//
                Accessor.FE, _SH1997MMASALSFTTSP.__steps(Instance.ATT532,
                    500l),//
                Accessor.F, 27973d, EStatisticParameter.ARITHMETIC_MEAN),// //
            //
            new LiteratureResultPoint(Instance.RAT783,//
                Accessor.FE, _SH1997MMASALSFTTSP.__steps(Instance.RAT783,
                    500l),//
                Accessor.F, 9056d, EStatisticParameter.ARITHMETIC_MEAN),// //
            //
            new LiteratureResultPoint(Instance.P43,//
                Accessor.FE, _SH1997MMASALSFTTSP.__steps(Instance.P43,
                    500l),//
                Accessor.F, 5622d, EStatisticParameter.ARITHMETIC_MEAN),// //
            //
            new LiteratureResultPoint(Instance.RY48P,//
                Accessor.FE, _SH1997MMASALSFTTSP.__steps(Instance.RY48P,
                    500l),//
                Accessor.F, 14694d, EStatisticParameter.ARITHMETIC_MEAN),// //
            //
            new LiteratureResultPoint(Instance.FT70,//
                Accessor.FE, _SH1997MMASALSFTTSP.__steps(Instance.FT70,
                    500l),//
                Accessor.F, 38775d, EStatisticParameter.ARITHMETIC_MEAN),// //
            //
            new LiteratureResultPoint(Instance.KRO124P,//
                Accessor.FE, _SH1997MMASALSFTTSP.__steps(Instance.KRO124P,
                    500l),//
                Accessor.F, 37391d, EStatisticParameter.ARITHMETIC_MEAN),// //
            //
            new LiteratureResultPoint(Instance.FTV170,//
                Accessor.FE, _SH1997MMASALSFTTSP.__steps(Instance.FTV170,
                    500l),//
                Accessor.F, 2828d, EStatisticParameter.ARITHMETIC_MEAN),//

            //
            // const=1000 or 2500
            //
            new LiteratureResultPoint(Instance.KROA100,//
                Accessor.FE, _SH1997MMASALSFTTSP.__steps(Instance.KROA100,
                    2500l),//
                Accessor.F, 21282d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.D198,//
                Accessor.FE, _SH1997MMASALSFTTSP.__steps(Instance.D198,
                    2500l),//
                Accessor.F, 15786d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.LIN318,//
                Accessor.FE, _SH1997MMASALSFTTSP.__steps(Instance.LIN318,
                    2500l),//
                Accessor.F, 42070d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.PCB442,//
                Accessor.FE, _SH1997MMASALSFTTSP.__steps(Instance.PCB442,
                    1000l),//
                Accessor.F, 51131d, EStatisticParameter.ARITHMETIC_MEAN),// //
            //
            new LiteratureResultPoint(Instance.ATT532,//
                Accessor.FE, _SH1997MMASALSFTTSP.__steps(Instance.ATT532,
                    1000l),//
                Accessor.F, 27871d, EStatisticParameter.ARITHMETIC_MEAN),// //
            //
            new LiteratureResultPoint(Instance.RAT783,//
                Accessor.FE, _SH1997MMASALSFTTSP.__steps(Instance.RAT783,
                    1000l),//
                Accessor.F, 8976d, EStatisticParameter.ARITHMETIC_MEAN),// //
            //
            new LiteratureResultPoint(Instance.P43,//
                Accessor.FE, _SH1997MMASALSFTTSP.__steps(Instance.P43,
                    2500l),//
                Accessor.F, 5620.6d, EStatisticParameter.ARITHMETIC_MEAN),// //
            //
            new LiteratureResultPoint(Instance.RY48P,//
                Accessor.FE, _SH1997MMASALSFTTSP.__steps(Instance.RY48P,
                    2500l),//
                Accessor.F, 14494d, EStatisticParameter.ARITHMETIC_MEAN),// //
            //
            new LiteratureResultPoint(Instance.FT70,//
                Accessor.FE, _SH1997MMASALSFTTSP.__steps(Instance.FT70,
                    2500l),//
                Accessor.F, 38707d, EStatisticParameter.ARITHMETIC_MEAN),// //
            //
            new LiteratureResultPoint(Instance.KRO124P,//
                Accessor.FE, _SH1997MMASALSFTTSP.__steps(Instance.KRO124P,
                    2500l),//
                Accessor.F, 36655d, EStatisticParameter.ARITHMETIC_MEAN),// //
            //
            new LiteratureResultPoint(Instance.FTV170,//
                Accessor.FE, _SH1997MMASALSFTTSP.__steps(Instance.FTV170,
                    2500l),//
                Accessor.F, 2790d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            //
            // runtime limits
            //
            new LiteratureResultPoint(Instance.EIL51,//
                Accessor.RUNTIME, 30e3d,//
                Accessor.F, 426.2d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.KROA100,//
                Accessor.RUNTIME, 100e3d,//
                Accessor.F, 21283d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.D198,//
                Accessor.RUNTIME, 200e3d,//
                Accessor.F, 15945d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.LIN318,//
                Accessor.RUNTIME, 500e3d,//
                Accessor.F, 42994d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            // ant system with different number of ants
            //
            new LiteratureResultPoint(Instance.RY48P,//
                Accessor.FE, 10000l,//
                Accessor.F, 14500d, EStatisticParameter.ARITHMETIC_MEAN),//
            //
            new LiteratureResultPoint(Instance.RY48P,//
                Accessor.FE, 333 * 30,//
                Accessor.F, 14422d, EStatisticParameter.MINIMUM),// //
            //
            new LiteratureResultPoint(Instance.RY48P,//
                Accessor.FE, 333 * 30,//
                Accessor.F, 14509d, EStatisticParameter.ARITHMETIC_MEAN),// //
            //
            new LiteratureResultPoint(Instance.RY48P,//
                Accessor.FE, 160 * 62,//
                Accessor.F, 14621d, EStatisticParameter.MINIMUM),// //
            //
            new LiteratureResultPoint(Instance.RY48P,//
                Accessor.FE, 160 * 62,//
                Accessor.F, 14743d, EStatisticParameter.ARITHMETIC_MEAN),// //
        });
  }

  /** {@inheritDoc} */
  @Override
  public final void writeDescription(final AbstractTextComplex dest)
      throws IOException {
    dest.write("In "); //$NON-NLS-1$
    this.cite(ECitationMode.BY_ID_IN_SENTENCE, dest);
    dest.write(", a set of variants of the "); //$NON-NLS-1$
    dest.write(this.getLongName());
    dest.write(" ("); //$NON-NLS-1$
    dest.write(this.getShortName());
    dest.write(") are introduced. In this ACO variant, each ant creates a solution to a TSP instance and some of these solutions undergo a subsequent local search. "); //$NON-NLS-1$
    this.cite(ECitationMode.BY_ID_AT_SENTENCE_START, dest);
    dest.write(" provide different ways to choose which one should undergo the local search: either all out of a set of 10 ants, the best out of a set of 10, and a version where "); //$NON-NLS-1$
    dest.macroInvoke(Macros.SCALE);
    dest.write(" ants are used and the best one is improved with local search. As local search, 2-opt is used for symmetric TSP instances and a 3-opt variant for asymmetric ones. Candidate sets are used in some configurations to decrease the runtime. The authors report results over 10 independent runs for several benchmark instances, both for fixed numbers of "); //$NON-NLS-1$
    Accessor.FE.writeLongName(dest, true);
    dest.write(" as well as for runtime limits (on Sparc 20 Workstations SS20/50). For the runs with "); //$NON-NLS-1$
    Accessor.FE.writeShortName(dest, false);
    dest.write(" limits, limits are chosen as "); //$NON-NLS-1$
    try (InlineMath im = dest.inlineMath()) {
      try (MathOp op1 = im.mathOp(EMathOp.MUL)) {
        try (MathOpParam p1 = op1.mathOpParam()) {
          try (MathName mn = p1.mathName(EMathName.SCALAR)) {
            mn.writeChar('k');
          }
        }
        try (MathOpParam p2 = op1.mathOpParam()) {
          try (MathOp op2 = p2.mathOp(EMathOp.MUL)) {
            try (MathOpParam p3 = op2.mathOpParam()) {
              p3.macroInvoke(Macros.SCALE);
            }
            try (MathOpParam p4 = op2.mathOpParam()) {
              try (MathName mn = p4.mathName(EMathName.SCALAR)) {
                mn.write("const");//$NON-NLS-1$
              }
            }
          }
        }
      }
    }
    dest.write(" steps of complexity "); //$NON-NLS-1$
    try (InlineMath im = dest.inlineMath()) {
      try (MathOp o = im.mathOp(EMathOp.COMPLEX_BIG_O)) {
        try (MathOpParam p1 = o.mathOpParam()) {
          try (MathOp o2 = im.mathOp(EMathOp.POW)) {
            try (MathOpParam p2 = o2.mathOpParam()) {
              p2.macroInvoke(Macros.SCALE);
            }
            try (MathOpParam p3 = o2.mathOpParam()) {
              p3.writeInt(2);
            }
          }
        }
      }
    }

    dest.write(", where "); //$NON-NLS-1$
    try (InlineMath im = dest.inlineMath()) {
      try (MathOp op1 = im.mathOp(EMathOp.CMP_EQUALS)) {
        try (MathOpParam p1 = op1.mathOpParam()) {
          try (MathName mn = p1.mathName(EMathName.SCALAR)) {
            mn.writeChar('k');
          }
        }
        try (MathOpParam p2 = op1.mathOpParam()) {
          p2.writeInt(1);
        }
      }
    }
    dest.write(" for symmetric and "); //$NON-NLS-1$
    try (InlineMath im = dest.inlineMath()) {
      try (MathOp op1 = im.mathOp(EMathOp.CMP_EQUALS)) {
        try (MathOpParam p1 = op1.mathOpParam()) {
          try (MathName mn = p1.mathName(EMathName.SCALAR)) {
            mn.writeChar('k');
          }
        }
        try (MathOpParam p2 = op1.mathOpParam()) {
          p2.writeInt(2);
        }
      }
    }

    dest.write(" for asymmetric instances, and "); //$NON-NLS-1$
    try (InlineMath im = dest.inlineMath()) {
      try (MathOp op1 = im.mathOp(EMathOp.SET_IN)) {
        try (MathOpParam p1 = op1.mathOpParam()) {
          try (MathName mn = p1.mathName(EMathName.SCALAR)) {
            mn.write("const");//$NON-NLS-1$
          }
        }
        try (MathOpParam p2 = op1.mathOpParam()) {

          try (MathOp op2 = p2.mathOp(EMathOp.SET)) {
            try (MathOpParam px = op2.mathOpParam()) {
              px.writeInt(1);
            }
            try (MathOpParam px = op2.mathOpParam()) {
              px.writeInt(50);
            }
            try (MathOpParam px = op2.mathOpParam()) {
              px.writeInt(100);
            }
            try (MathOpParam px = op2.mathOpParam()) {
              px.writeInt(500);
            }
            try (MathOpParam px = op2.mathOpParam()) {
              px.writeInt(1000);
            }
            try (MathOpParam px = op2.mathOpParam()) {
              px.writeInt(2500);
            }
          }

        }
      }
    }

    dest.write(". It is not entirely clear what a step is. Likely it contains one ant tour construction and one local search. Nevertheless, we make the conservative choice of assuming that one step consumes "); //$NON-NLS-1$
    dest.writeInt(1);
    Accessor.FE.writeShortName(dest, false);
    dest.write(". From all methods an results reported for a given benchmark instance and time step, we then choose the one where "); //$NON-NLS-1$
    dest.write(this.getShortName());
    dest.write(" performs best."); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  public final void initialize(final Header header) throws IOException {
    Accessor.FE.define(header);
    Macros.SCALE.define(header);
  }

  /**
   * calculate the number of steps
   *
   * @param inst
   *          the instance
   * @param cnst
   *          the const
   * @return the steps
   */
  private static final long __steps(final Instance inst, final long cnst) {
    final long n;

    n = (inst.n() * cnst);
    if (inst.symmetric()) {
      return n;
    }
    return (n * 2l);

  }
}
