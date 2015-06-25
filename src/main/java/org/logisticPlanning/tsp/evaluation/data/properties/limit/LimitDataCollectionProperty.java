package org.logisticPlanning.tsp.evaluation.data.properties.limit;

import java.io.IOException;

import org.logisticPlanning.tsp.benchmarking.objective.DataPoint;
import org.logisticPlanning.tsp.benchmarking.objective.LogPoint;
import org.logisticPlanning.tsp.evaluation.data.Accessor;
import org.logisticPlanning.tsp.evaluation.data.EPropertyType;
import org.logisticPlanning.tsp.evaluation.data.Property;
import org.logisticPlanning.tsp.evaluation.data.RunSet;
import org.logisticPlanning.tsp.evaluation.modules.impl.macros.Macros;
import org.logisticPlanning.utils.document.spec.AbstractTextComplex;
import org.logisticPlanning.utils.document.spec.AbstractTextPlain;
import org.logisticPlanning.utils.document.spec.Document;
import org.logisticPlanning.utils.document.spec.EMathOp;
import org.logisticPlanning.utils.document.spec.Header;
import org.logisticPlanning.utils.document.spec.InlineMath;
import org.logisticPlanning.utils.document.spec.MathOp;
import org.logisticPlanning.utils.document.spec.MathOpParam;
import org.logisticPlanning.utils.math.data.collection.IDataCollection;
import org.logisticPlanning.utils.utils.HashUtils;

/**
 * A property that returns a data collection representing a state of a
 * given run set at a given limit.
 */
public final class LimitDataCollectionProperty extends
    Property<RunSet, IDataCollection> {

  /** the end of run */
  public static final LimitDataCollectionProperty END_OF_RUN = //
  new LimitDataCollectionProperty(EPropertyType.TEMPORARILY_STORED, (-3),
      Double.POSITIVE_INFINITY);
  /** the first of run */
  public static final LimitDataCollectionProperty FIRST_OF_RUN = //
  new LimitDataCollectionProperty(EPropertyType.TEMPORARILY_STORED, (-2),
      Double.NEGATIVE_INFINITY);
  /** the convergence point of run */
  public static final LimitDataCollectionProperty CONVERGENCE = //
  new LimitDataCollectionProperty(EPropertyType.TEMPORARILY_STORED, (-1),
      Double.NEGATIVE_INFINITY);
  /** the optimum of run */
  public static final LimitDataCollectionProperty OPTIMUM = //
  new LimitDataCollectionProperty(EPropertyType.TEMPORARILY_STORED, (-4),
      Double.NEGATIVE_INFINITY);

  /** the dimension */
  private final int m_dim;

  /** the value */
  private final double m_value;

  /** the hash code */
  private final int m_hc;

  /**
   * create the property
   *
   * @param type
   *          the type
   * @param dim
   *          the dimension
   * @param value
   *          the value
   */
  public LimitDataCollectionProperty(final EPropertyType type,
      final int dim, final double value) {
    super(type);

    if ((dim < (-4)) || (dim >= DataPoint.DATA_POINT_DIMENSION)) {
      LimitDataCollectionProperty.__dimError(dim);
    }

    this.m_dim = dim;
    this.m_value = value;

    this.m_hc = HashUtils.combineHashes(//
        HashUtils.combineHashes(//
            HashUtils.hashCode(this.getClass()),//
            HashUtils.hashCode(this.m_dim)),//
        HashUtils.hashCode(this.m_value));
  }

  /** {@inheritDoc} */
  @Override
  public String toString() {
    switch (this.m_dim) {
      case (-4): {
        return "OptimumReached";//$NON-NLS-1$
      }
      case (-3): {
        return "LastLogPoint";//$NON-NLS-1$
      }
      case (-2): {
        return "FirstLogPoint";//$NON-NLS-1$
      }
      case (-1): {
        return "ConvergenceLogPoint";//$NON-NLS-1$
      }
      case LogPoint.DE_INDEX:
      case LogPoint.FE_INDEX:
      case LogPoint.TIME_INDEX:
      case LogPoint.F_INDEX: {
        return (Accessor.ACCESSORS.get(this.m_dim).getShortName() + Math
            .round(this.m_dim));
      }

      case DataPoint.RELATIVE_F_INDEX:
      case DataPoint.NORMALIZED_TIME_INDEX: {
        return (Accessor.ACCESSORS.get(this.m_dim).getShortName() + this.m_dim);
      }

      default: {
        LimitDataCollectionProperty.__dimError(this.m_dim);
        return null;
      }
    }
  }

  /**
   * Write the short name
   *
   * @param out
   *          the output destination
   * @throws IOException
   *           if I/O fails
   */
  public final void writeShortName(final AbstractTextPlain out)
      throws IOException {
    switch (this.m_dim) {
      case (-4): {
        out.write("Arrival at Optimality");//$NON-NLS-1$
        return;
      }
      case (-3): {
        out.write("Last Log Point");//$NON-NLS-1$
        return;
      }
      case (-2): {
        out.write("First Log Point");//$NON-NLS-1$
        return;
      }
      case (-1): {
        out.write("Convergence Log Point");//$NON-NLS-1$
        return;
      }
      case LogPoint.DE_INDEX:
      case LogPoint.FE_INDEX:
      case LogPoint.TIME_INDEX: {
        if (out instanceof AbstractTextComplex) {
          try (InlineMath im = ((AbstractTextComplex) out).inlineMath()) {
            try (MathOp mo = im.mathOp(EMathOp.CMP_LESS_OR_EQUAL)) {
              try (MathOpParam p1 = mo.mathOpParam()) {
                Accessor.ACCESSORS.get(this.m_dim).writeShortName(p1);
              }
              try (MathOpParam p2 = mo.mathOpParam()) {
                p2.writeLong(Math.round(this.m_value));
              }
            }
          }
        } else {
          Accessor.ACCESSORS.get(this.m_dim).writeShortName(out);
          out.writeLong(Math.round(this.m_value));
        }
        return;
      }

      case LogPoint.F_INDEX: {
        if (out instanceof AbstractTextComplex) {
          try (InlineMath im = ((AbstractTextComplex) out).inlineMath()) {
            try (MathOp mo = im.mathOp(EMathOp.CMP_LESS_OR_EQUAL)) {
              try (MathOpParam p1 = mo.mathOpParam()) {
                p1.macroInvoke(Macros.F_BEST);
              }
              try (MathOpParam p2 = mo.mathOpParam()) {
                p2.writeLong(Math.round(this.m_value));
              }
            }
          }
        } else {
          out.macroInvoke(Macros.F_BEST);
          out.writeLong(Math.round(this.m_value));
        }
        return;
      }

      case DataPoint.RELATIVE_F_INDEX: {
        if (out instanceof AbstractTextComplex) {
          try (InlineMath im = ((AbstractTextComplex) out).inlineMath()) {
            try (MathOp mo = im.mathOp(EMathOp.CMP_LESS_OR_EQUAL)) {
              try (MathOpParam p1 = mo.mathOpParam()) {
                p1.macroInvoke(Macros.F_BEST_RELATIVE);
              }
              try (MathOpParam p2 = mo.mathOpParam()) {
                p2.writeDouble(this.m_value);
              }
            }
          }
        } else {
          out.macroInvoke(Macros.F_BEST_RELATIVE);
          out.writeDouble(this.m_value);
        }
        return;
      }

      case DataPoint.NORMALIZED_TIME_INDEX: {
        if (out instanceof AbstractTextComplex) {
          try (InlineMath im = ((AbstractTextComplex) out).inlineMath()) {
            try (MathOp mo = im.mathOp(EMathOp.CMP_LESS_OR_EQUAL)) {
              try (MathOpParam p1 = mo.mathOpParam()) {
                Accessor.NORMALIZED_RUNTIME.writeShortName(p1);
              }
              try (MathOpParam p2 = mo.mathOpParam()) {
                p2.writeDouble(this.m_value);
              }
            }
          }
        } else {
          Accessor.NORMALIZED_RUNTIME.writeShortName(out);
          out.writeLong(Math.round(this.m_value));
        }
        return;
      }

      default: {
        LimitDataCollectionProperty.__dimError(this.m_dim);
      }
    }
  }

  /** {@inheritDoc} */
  @SuppressWarnings("fallthrough")
  @Override
  public void initialize(final Header header) throws IOException {
    super.initialize(header);
    switch (this.m_dim) {
      case -4: {
        Macros.F_OPTIMAL.define(header);
      }
      case -1:
      case LogPoint.F_INDEX: {
        Macros.F_BEST.define(header);
        return;
      }
      case DataPoint.RELATIVE_F_INDEX: {
        Macros.F_BEST_RELATIVE.define(header);
        return;
      }
      default: {
        if (this.m_dim >= 0) {
          Accessor.ACCESSORS.get(this.m_dim).define(header);
        }
      }
    }
  }

  /**
   * the dimension error
   *
   * @param dim
   *          the dimension error
   */
  private static final void __dimError(final int dim) {
    throw new IllegalStateException("Illegal dimension " + dim); //$NON-NLS-1$
  }

  /**
   * Get a sentence fraction describing the limit used
   *
   * @return the in-text description
   */
  public final String getInTextDescription() {
    return "xxxxxxx xxxxxxx xxxxxxx xxxxxxx xxxxxxx xxxxxxx xxxxxxx xxxxxxx xxxxxxx xxxxxxx xxxxxxx xxxxxxx xxxxxxx xxxxxxx xxxxxxx xxxxxxx";//$NON-NLS-1$
    // TODO: do this right: copy text from method below
  }

  /**
   * Write a sentence fraction describing the limit used
   *
   * @param out
   *          the output destination
   * @throws IOException
   *           if I/O fails
   */
  public final void writeInTextDescription(final AbstractTextComplex out)
      throws IOException {
    switch (this.m_dim) {
      case (-4): {
        out.write("the log points denoting the moment in the runs when the global optimum was reached, i.e., the objective value ");//$NON-NLS-1$
        out.macroInvoke(Macros.F_BEST);
        out.write(" of the best solution discovered is the same as the objective value "); //$NON-NLS-1$
        out.macroInvoke(Macros.F_OPTIMAL);
        out.write(" of the known global optimum (shortest possible tour)"); //$NON-NLS-1$
        return;
      }
      case (-3): {
        out.write("the last recorded point in each run");//$NON-NLS-1$
        return;
      }
      case (-2): {
        out.write("the first recorded point in each run"); //$NON-NLS-1$
        return;
      }
      case (-1): {
        out.write("the points where the runs converged or were terminated, i.e., the last point in each run where the best objective value known "); //$NON-NLS-1$
        out.macroInvoke(Macros.F_BEST);
        out.write(" has improved"); //$NON-NLS-1$
        return;
      }
      case LogPoint.DE_INDEX: {
        out.write("the earliest log point of each run where at most "); //$NON-NLS-1$
        out.writeLong(Math.round(this.m_value));
        out.writeChar(' ');
        Accessor.DE.writeLongName(out, true);
        out.write(" ("); //$NON-NLS-1$
        Accessor.DE.writeShortName(out, true);
        out.write(") have been consumed"); //$NON-NLS-1$
        return;
      }
      case LogPoint.FE_INDEX: {
        out.write("the earliest log point of each run where at most "); //$NON-NLS-1$
        out.writeLong(Math.round(this.m_value));
        out.writeChar(' ');
        Accessor.FE.writeLongName(out, true);
        out.write(" ("); //$NON-NLS-1$
        Accessor.FE.writeShortName(out, true);
        out.write(") have been consumed"); //$NON-NLS-1$
        return;
      }

      case LogPoint.TIME_INDEX: {
        out.write("the earliest log point of each run where at most "); //$NON-NLS-1$
        out.writeLong(Math.round(this.m_value));
        out.write(" milliseconds of "); //$NON-NLS-1$
        Accessor.RUNTIME.writeLongName(out, true);
        out.write(" ("); //$NON-NLS-1$
        Accessor.RUNTIME.writeShortName(out, true);
        out.write(") have been consumed"); //$NON-NLS-1$
        return;
      }

      case LogPoint.F_INDEX: {
        out.write("the earliest log point of each run where the best known objective value "); //$NON-NLS-1$
        out.macroInvoke(Macros.F_BEST);
        out.write(" became less or equal to a threshold "); //$NON-NLS-1$
        try (InlineMath im = out.inlineMath()) {
          try (MathOp op = im.mathOp(EMathOp.CMP_EQUALS)) {
            try (MathOpParam p1 = op.mathOpParam()) {
              p1.macroInvoke(Macros.F_THRESHOLD);
            }
            try (MathOpParam p2 = op.mathOpParam()) {
              p2.writeLong(Math.round(this.m_value));
            }
          }
        }
        return;
      }

      case DataPoint.RELATIVE_F_INDEX: {
        out.write("the earliest log point of each run where the best known relative objective value "); //$NON-NLS-1$
        out.macroInvoke(Macros.F_BEST_RELATIVE);
        out.write(" became less or equal to "); //$NON-NLS-1$
        out.writeDouble(this.m_value);
        return;
      }

      case DataPoint.NORMALIZED_TIME_INDEX: {
        out.write("the earliest log point of each run where at most "); //$NON-NLS-1$
        out.writeDouble(this.m_value);
        out.write(" units of "); //$NON-NLS-1$
        Accessor.NORMALIZED_RUNTIME.writeLongName(out, true);
        out.write(" ("); //$NON-NLS-1$
        Accessor.NORMALIZED_RUNTIME.writeShortName(out, true);
        out.write(") have been consumed"); //$NON-NLS-1$
        return;
      }

      default: {
        LimitDataCollectionProperty.__dimError(this.m_dim);
      }
    }
  }

  /** {@inheritDoc} */
  @Override
  protected final IDataCollection compute(final RunSet dataset,
      final Document doc) {
    switch (this.m_dim) {
      case (-4): {
        return new _OptimumLimitDataCollection(dataset);
      }
      case (-3): {
        return new _EndOfRunLimitDataCollection(dataset);
      }
      case (-2): {
        return new _FirstOfRunLimitDataCollection(dataset);
      }
      case (-1): {
        return new _ConvergenceLimitDataCollection(dataset);
      }
      case LogPoint.DE_INDEX: {
        return new _DELimitDataCollection(dataset,
            Math.round(this.m_value));
      }
      case LogPoint.FE_INDEX: {
        return new _FELimitDataCollection(dataset,
            Math.round(this.m_value));
      }
      case LogPoint.TIME_INDEX: {
        return new _TimeLimitDataCollection(dataset,
            Math.round(this.m_value));
      }
      case LogPoint.F_INDEX: {
        return new _FLimitDataCollection(dataset, Math.round(this.m_value));
      }
      case DataPoint.RELATIVE_F_INDEX: {
        return new _RelativeFLimitDataCollection(dataset, this.m_value);
      }
      case DataPoint.NORMALIZED_TIME_INDEX: {
        return new _NormalizedTimeLimitDataCollection(dataset,
            this.m_value);
      }
      default: {
        LimitDataCollectionProperty.__dimError(this.m_dim);
        return null;
      }
    }
  }

  /**
   * create the property
   *
   * @param dim
   *          the dimension
   * @param value
   *          the value
   * @return the limit data collections property
   */
  public static final LimitDataCollectionProperty create(final int dim,
      final double value) {

    if (value <= 0d) {
      if ((dim != LogPoint.F_INDEX) && (dim != DataPoint.RELATIVE_F_INDEX)) {
        return LimitDataCollectionProperty.FIRST_OF_RUN;
      }
      return LimitDataCollectionProperty.OPTIMUM;
    }

    if (value >= Long.MAX_VALUE) {
      if ((dim == LogPoint.F_INDEX) || (dim == DataPoint.RELATIVE_F_INDEX)) {
        return LimitDataCollectionProperty.FIRST_OF_RUN;
      }
      return LimitDataCollectionProperty.END_OF_RUN;
    }

    return new LimitDataCollectionProperty(EPropertyType.NEVER_STORED,
        dim, value);
  }

  /** {@inheritDoc} */
  @Override
  public final int hashCode() {
    return this.m_hc;
  }

  /** {@inheritDoc} */
  @Override
  public final boolean equals(final Object o) {
    final LimitDataCollectionProperty p;
    if (o == this) {
      return true;
    }
    if (o instanceof LimitDataCollectionProperty) {
      p = ((LimitDataCollectionProperty) o);
      return ((p.m_dim == this.m_dim) && (p.m_value == this.m_value));
    }
    return false;
  }
}
