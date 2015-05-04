package org.logisticPlanning.tsp.evaluation.data;

import java.io.IOException;
import java.text.NumberFormat;

import org.logisticPlanning.tsp.benchmarking.objective.DataPoint;
import org.logisticPlanning.tsp.benchmarking.objective.LogPoint;
import org.logisticPlanning.tsp.evaluation.data.conditions.AccessorIsObjective;
import org.logisticPlanning.tsp.evaluation.modules.impl.macros.Macros;
import org.logisticPlanning.utils.collections.conditions.CompoundCondition;
import org.logisticPlanning.utils.collections.conditions.EqualsCondition;
import org.logisticPlanning.utils.collections.conditions.NotCondition;
import org.logisticPlanning.utils.collections.conditions.NotNullCondition;
import org.logisticPlanning.utils.collections.lists.ArraySetView;
import org.logisticPlanning.utils.document.impl.utils.MacroMathConst;
import org.logisticPlanning.utils.document.spec.AbstractInlineElement;
import org.logisticPlanning.utils.document.spec.AbstractMathElement;
import org.logisticPlanning.utils.document.spec.AbstractTextComplex;
import org.logisticPlanning.utils.document.spec.AbstractTextPlain;
import org.logisticPlanning.utils.document.spec.EMathName;
import org.logisticPlanning.utils.document.spec.EMathOp;
import org.logisticPlanning.utils.document.spec.Element;
import org.logisticPlanning.utils.document.spec.Header;
import org.logisticPlanning.utils.document.spec.InlineMath;
import org.logisticPlanning.utils.document.spec.MacroDefinition;
import org.logisticPlanning.utils.document.spec.MathOp;
import org.logisticPlanning.utils.document.spec.MathOpParam;
import org.logisticPlanning.utils.math.functions.logic.LAnd;
import org.logisticPlanning.utils.utils.comparison.ComparisonUtils;

/**
 * The enumeration &quot;{@link Accessor}&quot; is a list of the dimensions
 * of the {@link org.logisticPlanning.tsp.benchmarking.objective.DataPoint
 * log points} collected by the
 * {@link org.logisticPlanning.tsp.benchmarking benchmarking environment}.
 * Data points have the following dimensions:
 * <ol>
 * <li>{@link #DE}: The amount of
 * {@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction#distance(int, int)
 * distance evaluations} consumed until the current log point was taken.</li>
 * <li>{@link #FE}: The amount of
 * {@link org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction#evaluate(int[])
 * function evaluations} consumed until the current log point was taken.</li>
 * <li>{@link #RUNTIME}: The
 * {@link org.logisticPlanning.tsp.benchmarking.objective.LogPoint#getConsumedRuntime()
 * absolute runtime} consumed in milliseconds until the current log point
 * was taken.</li>
 * <li>{@link #NORMALIZED_RUNTIME}: The normalized runtime consumed until
 * the current log point was taken.</li>
 * <li>{@link #F}: The absolute objective value (tour length) of the best
 * solution discovered until the current log point.</li>
 * <li>{@link #F_RELATIVE}: The relative objective value ((tour length -
 * optimal tour length)/optimal tour length) of the best solution
 * discovered until the current log point.</li>
 * </ol>
 */
public enum Accessor {

  /** the DE accessor */
  DE(LogPoint.DE_INDEX, "DE", null,//$NON-NLS-1$
      "distance evaluation", true) {//$NON-NLS-1$

    /** {@inheritDoc} */
    @Override
    public final double fromPoint(final DataPoint p) {
      return ((p == null) ? (Long.MAX_VALUE) : p.getConsumedDEs());
    }

    /** {@inheritDoc} */
    @Override
    public final long fromPointLong(final DataPoint p) {
      return ((p == null) ? (Long.MAX_VALUE) : p.getConsumedDEs());
    }

    /** {@inheritDoc} */
    @Override
    public final boolean canAccessAsLong() {
      return true;
    }

    /** {@inheritDoc} */
    @Override
    public final void define(final Header header) throws IOException {
      super.define(header);
      Macros.SCALE.define(header);
    }

    /** {@inheritDoc} */
    @Override
    public final long calculateScale(final int n) {
      return (((long) n) * ((long) n));
    }

    /** {@inheritDoc} */
    @Override
    public final String getScaleString() {
      return (Macros.SCALE.getPlaceholder() + '\u00b2');
    }

    /** {@inheritDoc} */
    @Override
    public final boolean isScaled() {
      return true;
    }

    /**
     * write the scale
     *
     * @param ame
     *          the mathe element
     * @throws IOException
     *           if io fails
     */
    private final void __writeScale(final AbstractMathElement ame)
        throws IOException {
      try (MathOp op = ame.mathOp(EMathOp.POW)) {
        try (MathOpParam p = op.mathOpParam()) {
          p.macroInvoke(Macros.SCALE);
        }
        try (MathOpParam p = op.mathOpParam()) {
          p.writeInt(2);
        }
      }
    }

    /** {@inheritDoc} */
    @Override
    public final void writeScale(final Element ame) throws IOException {
      if (ame instanceof AbstractMathElement) {
        this.__writeScale((AbstractMathElement) ame);
      } else {
        if (ame instanceof AbstractTextComplex) {
          try (InlineMath m = ((AbstractTextComplex) ame).inlineMath()) {
            this.__writeScale(m);
          }
        } else {
          super.writeScale(ame);
        }
      }
    }

    /** {@inheritDoc} */
    @Override
    public final void validateValue(final double value,
        final boolean isActualMeasurement) {
      if (value <= 0d) {
        throw new IllegalArgumentException(//
            "DE must be greater than zero, but is " + value); //$NON-NLS-1$
      }

      if (value != value) {
        throw new IllegalArgumentException("DE must not be NaN"); //$NON-NLS-1$
      }

      if (isActualMeasurement) {
        if (value >= Double.POSITIVE_INFINITY) {
          throw new IllegalArgumentException(//
              "DE must not be infinite, but is."); //$NON-NLS-1$
        }

        if (!(ComparisonUtils.isInteger(value))) {
          throw new IllegalArgumentException(//
              "DE must not be a fraction, but is " + value); //$NON-NLS-1$
        }
      }
    }

    /** {@inheritDoc} */
    @Override
    public final void writeValue(final double value,
        final AbstractTextComplex txt, final NumberFormat format)
            throws IOException {
      Accessor._writeValue(value, txt, format);
      txt.writeNoneBreakingSpace();
      this.writeShortName(txt, true);
    }
  },

  /** the FE accessor */
  FE(LogPoint.FE_INDEX, "FE", null,//$NON-NLS-1$
      "function evaluation", true) {//$NON-NLS-1$
    /** {@inheritDoc} */
    @Override
    public final double fromPoint(final DataPoint p) {
      return ((p == null) ? Double.POSITIVE_INFINITY : p.getConsumedFEs());
    }

    /** {@inheritDoc} */
    @Override
    public final boolean canAccessAsLong() {
      return true;
    }

    /** {@inheritDoc} */
    @Override
    public final long fromPointLong(final DataPoint p) {
      return ((p == null) ? Long.MAX_VALUE : p.getConsumedFEs());
    }

    /** {@inheritDoc} */
    @Override
    public final long calculateScale(final int n) {
      return n;
    }

    /** {@inheritDoc} */
    @Override
    public final boolean isScaled() {
      return true;
    }

    /** {@inheritDoc} */
    @Override
    public final void define(final Header header) throws IOException {
      super.define(header);
      Macros.SCALE.define(header);
    }

    /** {@inheritDoc} */
    @Override
    public final String getScaleString() {
      return Macros.SCALE.getPlaceholder();
    }

    /** {@inheritDoc} */
    @Override
    public final void writeScale(final Element ame) throws IOException {
      if (ame instanceof AbstractInlineElement) {
        ((AbstractInlineElement) ame).macroInvoke(Macros.SCALE);
      } else {
        super.writeScale(ame);
      }
    }

    /** {@inheritDoc} */
    @Override
    public final void validateValue(final double value,
        final boolean isActualMeasurement) {
      if (value <= 0d) {
        throw new IllegalArgumentException(//
            "FE must be greater than zero, but is " + value); //$NON-NLS-1$
      }

      if (value != value) {
        throw new IllegalArgumentException("FE must not be NaN"); //$NON-NLS-1$
      }

      if (isActualMeasurement) {
        if (value >= Double.POSITIVE_INFINITY) {
          throw new IllegalArgumentException(//
              "FE must not be infinite, but is."); //$NON-NLS-1$
        }

        if (!(ComparisonUtils.isInteger(value))) {
          throw new IllegalArgumentException(//
              "FE must not be a fraction, but is " + value); //$NON-NLS-1$
        }
      }
    }

    /** {@inheritDoc} */
    @Override
    public final void writeValue(final double value,
        final AbstractTextComplex txt, final NumberFormat format)
            throws IOException {
      Accessor._writeValue(value, txt, format);
      txt.writeNoneBreakingSpace();
      this.writeShortName(txt, true);
    }
  },

  /** the runtime accessor */
  RUNTIME(LogPoint.TIME_INDEX, "AT", null,//$NON-NLS-1$
      "absolute runtime", false) {//$NON-NLS-1$
    /** {@inheritDoc} */
    @Override
    public final double fromPoint(final DataPoint p) {
      return ((p == null) ? Double.POSITIVE_INFINITY : p
          .getConsumedRuntime());
    }

    /** {@inheritDoc} */
    @Override
    public final boolean canAccessAsLong() {
      return true;
    }

    /** {@inheritDoc} */
    @Override
    public final long fromPointLong(final DataPoint p) {
      return ((p == null) ? Long.MAX_VALUE : p.getConsumedRuntime());
    }

    /** {@inheritDoc} */
    @Override
    public final long calculateScale(final int n) {
      return (n);
    }

    /** {@inheritDoc} */
    @Override
    public final boolean isScaled() {
      return true;
    }

    /** {@inheritDoc} */
    @Override
    public final void define(final Header header) throws IOException {
      super.define(header);
      Macros.SCALE.define(header);
    }

    /** {@inheritDoc} */
    @Override
    public final String getScaleString() {
      return Macros.SCALE.getPlaceholder();
    }

    /** {@inheritDoc} */
    @Override
    public final void writeScale(final Element ame) throws IOException {
      if (ame instanceof AbstractInlineElement) {
        ((AbstractInlineElement) ame).macroInvoke(Macros.SCALE);
      } else {
        super.writeScale(ame);
      }
    }

    /** {@inheritDoc} */
    @Override
    public final void validateValue(final double value,
        final boolean isActualMeasurement) {
      if (value < 0d) {
        throw new IllegalArgumentException(//
            "Runtime must not be less than zero, but is " + value); //$NON-NLS-1$
      }

      if (value != value) {
        throw new IllegalArgumentException("Runtime must not be NaN"); //$NON-NLS-1$
      }

      if (isActualMeasurement) {
        if (value >= Double.POSITIVE_INFINITY) {
          throw new IllegalArgumentException(//
              "Runtime must not be infinite, but is."); //$NON-NLS-1$
        }

        if (!(ComparisonUtils.isInteger(value))) {
          throw new IllegalArgumentException(//
              "Runtime must not be a fraction, but is " + value); //$NON-NLS-1$
        }
      }
    }

    /** {@inheritDoc} */
    @Override
    public final boolean isTime() {
      return true;
    }

    /** {@inheritDoc} */
    @Override
    public final void writeValue(final double value,
        final AbstractTextComplex txt, final NumberFormat format)
            throws IOException {
      Accessor._writeValue(value, txt, format);
      txt.write("ms");//$NON-NLS-1$
    }
  },

  /** the objective value */
  F(LogPoint.F_INDEX, "f", "F",//$NON-NLS-1$ //$NON-NLS-2$
      "objective value", true) {//$NON-NLS-1$
    /** {@inheritDoc} */
    @Override
    public final double fromPoint(final DataPoint p) {
      return ((p == null) ? Double.POSITIVE_INFINITY : p.getBestF());
    }

    /** {@inheritDoc} */
    @Override
    public final boolean canAccessAsLong() {
      return true;
    }

    /** {@inheritDoc} */
    @Override
    public final long fromPointLong(final DataPoint p) {
      return ((p == null) ? Long.MAX_VALUE : p.getBestF());
    }

    /** {@inheritDoc} */
    @Override
    public final void writeScale(final Element ame) throws IOException {
      if (this.__write1Scale(ame)) {
        super.writeScale(ame);
      }
    }

    /** {@inheritDoc} */
    @Override
    public final boolean isObjective() {
      return true;
    }

    /** {@inheritDoc} */
    @Override
    public final void validateValue(final double value,
        final boolean isActualMeasurement) {
      if (value <= 0d) {
        throw new IllegalArgumentException(//
            "Objective value must be greater than zero, but is " + value); //$NON-NLS-1$
      }

      if (value != value) {
        throw new IllegalArgumentException(
            "Objective value must not be NaN"); //$NON-NLS-1$
      }

      if (isActualMeasurement) {
        if (!(ComparisonUtils.isInteger(value))) {
          throw new IllegalArgumentException(//
              "Objective value must not be a fraction, but is " + value); //$NON-NLS-1$
        }
      }
    }

    /** {@inheritDoc} */
    @Override
    public final void writeValue(final double value,
        final AbstractTextComplex txt, final NumberFormat format)
            throws IOException {

      try (InlineMath im = txt.inlineMath()) {
        try (MathOp mo = im.mathOp(EMathOp.CMP_EQUALS)) {
          try (MathOpParam p1 = mo.mathOpParam()) {
            this.writeShortName(p1);
          }
          try (MathOpParam p2 = mo.mathOpParam()) {
            Accessor._writeValue(value, p2, format);
          }
        }
      }
    }
  },

  /** the relative objective value */
  F_RELATIVE(DataPoint.RELATIVE_F_INDEX, "F", "FRel",//$NON-NLS-1$//$NON-NLS-2$
      "relative objective value", true) {//$NON-NLS-1$
    /** {@inheritDoc} */
    @Override
    public final double fromPoint(final DataPoint p) {
      return ((p == null) ? Double.POSITIVE_INFINITY : p.getRelBestF());
    }

    /** {@inheritDoc} */
    @Override
    public final void writeScale(final Element ame) throws IOException {
      if (this.__write1Scale(ame)) {
        super.writeScale(ame);
      }
    }

    /** {@inheritDoc} */
    @Override
    public final boolean isObjective() {
      return true;
    }

    /** {@inheritDoc} */
    @Override
    public final void validateValue(final double value,
        final boolean isActualMeasurement) {
      if (value != value) {
        throw new IllegalArgumentException(
            "Relative objective value must not be NaN"); //$NON-NLS-1$
      }
      if (value < 0d) {
        throw new IllegalArgumentException(//
            "Relative objective value not be less than zero, but is " + value); //$NON-NLS-1$
      }
    }

    /** {@inheritDoc} */
    @Override
    public final void writeValue(final double value,
        final AbstractTextComplex txt, final NumberFormat format)
            throws IOException {

      try (InlineMath im = txt.inlineMath()) {
        try (MathOp mo = im.mathOp(EMathOp.CMP_EQUALS)) {
          try (MathOpParam p1 = mo.mathOpParam()) {
            this.writeShortName(p1);
          }
          try (MathOpParam p2 = mo.mathOpParam()) {
            Accessor._writeValue(value, p2, format);
          }
        }
      }
    }
  },

  /** the normalized runtime accessor */
  NORMALIZED_RUNTIME(DataPoint.NORMALIZED_TIME_INDEX, "NT", null,//$NON-NLS-1$
      "normalized runtime", false) {//$NON-NLS-1$
    /** {@inheritDoc} */
    @Override
    public final double fromPoint(final DataPoint p) {
      return ((p != null) ? p.getConsumedNormalizedRuntime()
          : (Double.POSITIVE_INFINITY));
    }

    /** {@inheritDoc} */
    @Override
    public final void writeScale(final Element ame) throws IOException {
      if (this.__write1Scale(ame)) {
        super.writeScale(ame);
      }
    }

    /** {@inheritDoc} */
    @Override
    public final void validateValue(final double value,
        final boolean isActualMeasurement) {
      if (value < 0d) {
        throw new IllegalArgumentException(//
            "Normalized runtime must not be less than zero, but is " + value); //$NON-NLS-1$
      }
      if (value != value) {
        throw new IllegalArgumentException(
            "Normalized runtime must not be NaN"); //$NON-NLS-1$
      }
      if (isActualMeasurement) {
        if (value >= Double.POSITIVE_INFINITY) {
          throw new IllegalArgumentException(//
              "Normalized runtime must not be infinite, but is."); //$NON-NLS-1$
        }
      }
    }

    /** {@inheritDoc} */
    @Override
    public final boolean isTime() {
      return true;
    }

    /** {@inheritDoc} */
    @Override
    public final void writeValue(final double value,
        final AbstractTextComplex txt, final NumberFormat format)
            throws IOException {

      try (InlineMath im = txt.inlineMath()) {
        try (MathOp mo = im.mathOp(EMathOp.CMP_EQUALS)) {
          try (MathOpParam p1 = mo.mathOpParam()) {
            this.writeShortName(p1);
          }
          try (MathOpParam p2 = mo.mathOpParam()) {
            Accessor._writeValue(value, p2, format);
          }
        }
      }
    }
  },

  ;

  /** the total number of accessors */
  public static final ArraySetView<Accessor> ACCESSORS = //
      ArraySetView.makeArraySetView(Accessor.values(), false);

  /** the proper x-dimensions for progress diagrams */
  public static final ArraySetView<Accessor> TIME_MEASURES = //
      Accessor.ACCESSORS.select(//
          new CompoundCondition<>(LAnd.INSTANCE,//
              NotNullCondition.INSTANCE,//
              new NotCondition<>(AccessorIsObjective.INSTANCE)));

  /** the proper unbiased x-dimensions for progress diagrams */
  public static final ArraySetView<Accessor> UNBIASED_TIME_MEASURES = //
      Accessor.TIME_MEASURES.select(//
          new NotCondition<>(new EqualsCondition(Accessor.RUNTIME)));

  /** the proper x-dimensions for progress diagrams */
  public static final ArraySetView<Accessor> OBJECTIVE_MEASURES = //
      Accessor.ACCESSORS.select(//
          new CompoundCondition<>(LAnd.INSTANCE,//
              NotNullCondition.INSTANCE,//
              AccessorIsObjective.INSTANCE));

  /** the short name singular macro */
  private final MacroDefinition m_short;

  /** the long name */
  private final String m_long;
  /** the long name in plural */
  private final String m_longPlural;

  /** does this accessor have a plural form? */
  private final boolean m_hasPlural;

  /**
   * internal constructor
   *
   * @param dim
   *          the dimension
   * @param shrt
   *          the short name
   * @param lng
   *          the long name
   * @param macroName
   *          the macro name, or {@code null}
   * @param hasPlural
   *          does this accessor have a plural form for its names?
   */
  private Accessor(final int dim, final String shrt,
      final String macroName, final String lng, final boolean hasPlural) {//

    if (dim != this.ordinal()) {
      throw new IllegalArgumentException();
    }

    this.m_short = (new MacroMathConst(
        //
        ("measure" + ((macroName != null) ? macroName : shrt)), shrt, EMathName.SCALAR));//$NON-NLS-1$
    this.m_long = lng;
    this.m_longPlural = (hasPlural ? (lng + 's') : lng);
    this.m_hasPlural = true;
  }

  // calculation and data access routines

  /**
   * Get the {@code double} value from a given log point, or a value which
   * represents the worst possible result if the log point is {@code null}.
   * This worst possible result will usually be
   * {@link java.lang.Double#POSITIVE_INFINITY}.
   *
   * @param p
   *          the log point
   * @return the value, or an appropriate value (usually
   *         {@link java.lang.Double#POSITIVE_INFINITY}) if {@code p==null}
   */
  public double fromPoint(final DataPoint p) {
    throw new UnsupportedOperationException();
  }

  /**
   * Get the {@code long} value from a given log point, or a value which
   * represents the worst possible result if the log point is {@code null}.
   * This worst possible result will usually be
   * {@link java.lang.Double#POSITIVE_INFINITY}. Throws an
   * {@link java.lang.UnsupportedOperationException} if the dimension is
   * not represented as {@code long} value &ndash; i.e., if
   * <code>{@link #canAccessAsLong()}==false</code>.
   *
   * @param p
   *          the log point
   * @return the value, or an appropriate value (usually
   *         {@link java.lang.Long#MAX_VALUE}) if {@code p==null}
   * @throws java.lang.UnsupportedOperationException
   *           if the dimension is not represented as {@code long} value.
   */
  public long fromPointLong(final DataPoint p) {
    throw new UnsupportedOperationException();
  }

  /**
   * Can the values be accessed as {@code long}? This would can potentially
   * bring better precision, if possible, by using
   * {@link #fromPointLong(DataPoint)}
   *
   * @return {@code true} if {@link #fromPointLong(DataPoint)} can be used
   *         without throwing
   *         {@link java.lang.UnsupportedOperationException} s.
   */
  public boolean canAccessAsLong() {
    return false;
  }

  /**
   * calculate the scale (<code>n</code>, <code>n<sup>2</sup></code>, ...)
   *
   * @param n
   *          the problem size (number of nodes in the tsp)
   * @return the scale
   */
  public long calculateScale(final int n) {
    return 1l;
  }

  /**
   * Should we normally scale this measure?
   *
   * @return {@code true} if this measure is normally scaled, {@code false}
   *         otherwise
   */
  public boolean isScaled() {
    return false;
  }

  // plain string methods

  /**
   * A short scale string for use in figures
   *
   * @return the scale string
   */
  public String getScaleString() {
    return "1"; //$NON-NLS-1$
  }

  /**
   * get a short name to be used in file names, for example
   *
   * @return the short name
   */
  public final String getShortName() {
    return this.m_short.getPlaceholder();
  }

  /**
   * get the string to be used for a scaled value axis
   *
   * @return the string to be used for the scaled value axis
   */
  public final String getAxisString() {
    final String s;

    s = this.getShortName();
    if (this.isScaled()) {
      return (s + '/' + this.getScaleString());
    }
    return s;
  }

  // document methods

  /**
   * Define all necessary macros and stuff for this accessor
   *
   * @param header
   *          the header
   * @throws IOException
   *           if IO fails
   */
  public void define(final Header header) throws IOException {
    this.m_short.define(header);
  }

  /**
   * Write the short name of this accessor
   *
   * @param text
   *          the target text
   * @throws IOException
   *           if IO fails
   */
  public final void writeShortName(final AbstractInlineElement text)
      throws IOException {
    text.macroInvoke(this.m_short);
  }

  /**
   * Write the short name of this accessor
   *
   * @param text
   *          the target text
   * @param plural
   *          should we use the plural form?
   * @throws IOException
   *           if IO fails
   */
  public final void writeShortName(final AbstractTextPlain text,
      final boolean plural) throws IOException {
    text.macroInvoke(this.m_short);
    if (plural && (this.m_hasPlural)) {
      text.writeChar('s');
    }
  }

  /**
   * Write the long name of this accessor
   *
   * @param text
   *          the target text
   * @param plural
   *          should we use the plural form?
   * @throws IOException
   *           if IO fails
   */
  public final void writeLongName(final AbstractTextPlain text,
      final boolean plural) throws IOException {
    text.write(plural ? this.m_longPlural : this.m_long);
  }

  /**
   * the long name
   *
   * @param plural
   *          should we use the plural form?
   * @return the name
   */
  public final String getLongName(final boolean plural) {
    return (plural ? this.m_longPlural : this.m_long);
  }

  /**
   * Write the scale of this accessor
   *
   * @param ame
   *          the element to write to
   * @throws IOException
   *           if io fails
   */
  public void writeScale(final Element ame) throws IOException {
    throw new IllegalArgumentException(//
        "Accessor " + this + //$NON-NLS-1$
        " cannot write scale to element " + ame); //$NON-NLS-1$
  }

  /**
   * write the 1 scale
   *
   * @param ame
   *          the element
   * @return {@code true} on error, {@code false} on success
   * @throws IOException
   *           if io fails
   */
  final boolean __write1Scale(final Element ame) throws IOException {
    if (ame instanceof AbstractInlineElement) {
      ((AbstractInlineElement) ame).writeInt(1);
      return false;
    }
    return true;
  }

  /**
   * Does this accessor concern an objective value?
   *
   * @return {@code true} if it does, {@code false} otherwise
   */
  public boolean isObjective() {
    return false;
  }

  /**
   * Does this accessor represent a real time measure, i.e., is it
   * {@link #RUNTIME} or {@link #NORMALIZED_RUNTIME}?
   *
   * @return {@code true} if it does, {@code false} otherwise
   */
  public boolean isTime() {
    return false;
  }

  /** {@inheritDoc} */
  @Override
  public final String toString() {
    return ((((this.m_long + ' ') + '(') + this.getShortName()) + ')');
  }

  /**
   * validate a value for this dimension
   *
   * @param value
   *          the value
   * @param isActualMeasurement
   *          if this is {@code true}, some values like
   *          {@link java.lang.Double#POSITIVE_INFINITY} or fractions may
   *          not be permitted, but they may be OK in the opposite case,
   *          when the input is actually a statistic
   */
  public abstract void validateValue(final double value,
      final boolean isActualMeasurement);

  /**
   * Write a value
   *
   * @param value
   *          the value
   * @param txt
   *          the text
   * @param format
   *          the format for floats
   * @throws IOException
   *           if i/o fails
   */
  static final void _writeValue(final double value,
      final AbstractInlineElement txt, final NumberFormat format)
          throws IOException {
    long l;

    if (ComparisonUtils.isInteger(value)) {
      l = Math.round(value);
      if (format != null) {
        txt.writeLong(l, format);
      } else {
        txt.writeLong(l);
      }
    } else {
      if (format != null) {
        txt.writeDouble(value, format);
      } else {
        txt.writeDouble(value);
      }
    }
  }

  /**
   * Write a value
   *
   * @param value
   *          the value
   * @param txt
   *          the text
   * @param format
   *          the format
   * @throws IOException
   *           if i/o fails
   */
  public abstract void writeValue(final double value,
      final AbstractTextComplex txt, final NumberFormat format)
          throws IOException;

}
