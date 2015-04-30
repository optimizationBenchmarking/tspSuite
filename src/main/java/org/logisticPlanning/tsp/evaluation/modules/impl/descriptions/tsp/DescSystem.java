package org.logisticPlanning.tsp.evaluation.modules.impl.descriptions.tsp;

import java.io.IOException;

import org.logisticPlanning.tsp.evaluation.data.Accessor;
import org.logisticPlanning.tsp.evaluation.data.ExperimentSet;
import org.logisticPlanning.tsp.evaluation.modules.impl.macros.Macros;
import org.logisticPlanning.tsp.evaluation.modules.spec.DescriptionModule;
import org.logisticPlanning.tsp.evaluation.modules.spec.Module;
import org.logisticPlanning.tsp.evaluation.modules.spec.RootModule;
import org.logisticPlanning.utils.document.spec.EMathOp;
import org.logisticPlanning.utils.document.spec.Header;
import org.logisticPlanning.utils.document.spec.InlineMath;
import org.logisticPlanning.utils.document.spec.Label;
import org.logisticPlanning.utils.document.spec.MathOp;
import org.logisticPlanning.utils.document.spec.MathOpParam;
import org.logisticPlanning.utils.document.spec.SectionBody;
import org.logisticPlanning.utils.document.spec.SectionTitle;

/**
 * A description of the benchmarking system.
 */
public final class DescSystem extends DescriptionModule {
  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the representations */
  private final DescRepresentations m_rep;

  /** the time measures */
  private final DescTimeMeasures m_tm;

  /** the benchmarks */
  private final DescBenchmarks m_bm;

  /**
   * create!
   * 
   * @param owner
   *          the owner
   */
  DescSystem(final Module owner) {
    super("BenchmarkingSystem", owner, true); //$NON-NLS-1$

    final RootModule root;

    root = this.getRoot();
    this.m_rep = root.findInstance(DescRepresentations.class);
    this.m_tm = root.findInstance(DescTimeMeasures.class);
    this.m_bm = root.findInstance(DescBenchmarks.class);
    this.addDependency(this.m_rep);
    this.addDependency(this.m_tm);
  }

  /** {@inheritDoc} */
  @Override
  protected void writeSectionTitle(final SectionTitle title,
      final ExperimentSet data) throws IOException {
    title.write("Benchmarking System"); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  protected void initialize(final Header header, final ExperimentSet data)
      throws IOException {//
    super.initialize(header, data);
    Macros.SCALE.define(header);
    Accessor.DE.define(header);
    Accessor.FE.define(header);
  }

  /** {@inheritDoc} */
  @Override
  protected void writeSectionBody(final SectionBody body,
      final ExperimentSet data) throws IOException {
    final Label lbl;

    body.write(//
    "The benchmarking system collects log points based on the four time measures"); //$NON-NLS-1$
    lbl = this.m_tm.getLabel(data);
    if (lbl != null) {
      body.write(" introduced in ");//$NON-NLS-1$    
      body.reference(lbl);
    }
    body.write(//
    " and provides two sources of information that an algorithm can use when solving a TSP: The algorithm can either query the distance between two nodes or the total length of a tour. For the latter, both the path and adjacency representation (see "); //$NON-NLS-1$
    body.reference(this.m_rep.getLabel(data));

    body.write(//
    ") are supported. Apart from that, no other information is available to the algorithm. This concerns, in particular, geometrical information."); //$NON-NLS-1$

    body.write(//
    "The system applies an algorithm to the TSPLib benchmark sets discussed in "); //$NON-NLS-1$
    body.reference(this.m_bm.getLabel(data));
    body.write(//
    ", be default granting a computational budged of at most one hour, "); //$NON-NLS-1$
    try (InlineMath im = body.inlineMath()) {
      try (MathOp mul = im.mathOp(EMathOp.MUL)) {
        try (MathOpParam p1 = mul.mathOpParam()) {
          p1.writeInt(100);
        }
        try (MathOpParam p2 = mul.mathOpParam()) {
          try (MathOp pow = p2.mathOp(EMathOp.POW)) {
            try (MathOpParam p3 = mul.mathOpParam()) {
              p3.macroInvoke(Macros.SCALE);
            }
            try (MathOpParam p4 = mul.mathOpParam()) {
              p4.writeInt(4);
            }
          }
        }
      }
    }
    Accessor.DE.writeShortName(body, true);

    body.write(//
    ", or "); //$NON-NLS-1$
    try (InlineMath im = body.inlineMath()) {
      try (MathOp mul = im.mathOp(EMathOp.MUL)) {
        try (MathOpParam p1 = mul.mathOpParam()) {
          p1.writeInt(100);
        }
        try (MathOpParam p2 = mul.mathOpParam()) {
          try (MathOp pow = p2.mathOp(EMathOp.POW)) {
            try (MathOpParam p3 = mul.mathOpParam()) {
              p3.macroInvoke(Macros.SCALE);
            }
            try (MathOpParam p4 = mul.mathOpParam()) {
              p4.writeInt(3);
            }
          }
        }
      }
    }
    Accessor.FE.writeShortName(body, true);

    body.write(//
    " \u2012 whichever ellapses earlier."); //$NON-NLS-1$

    super.writeSectionBody(body, data);
  }
}
