package org.logisticPlanning.utils.document.impl.xhtml;

import java.io.IOException;
import java.util.EnumMap;

import org.logisticPlanning.utils.document.spec.EMathOp;
import org.logisticPlanning.utils.io.charIO.CharOutput;
import org.logisticPlanning.utils.text.EBraces;
import org.logisticPlanning.utils.text.transformations.XMLCharTransformer;

/**
 * the operation definition class
 */
abstract class _OpDef {

  /** the math-op table */
  static final char[] MO_TAB = { '<', 't', 'a', 'b', 'l', 'e', ' ', 'c',
      'l', 'a', 's', 's', '=', '"', 'm', 'a', 't', 'h', 'O', 'p', '"', '>' };
  /** the math-op tr */
  static final char[] MO_TR = { '<', 't', 'r', ' ', 'c', 'l', 'a', 's',
      's', '=', '"', 'm', 'a', 't', 'h', 'O', 'p', '"', '>' };
  /** the math-op td */
  static final char[] MO_TD = { '<', 't', 'd', ' ', 'c', 'l', 'a', 's',
      's', '=', '"', 'm', 'a', 't', 'h', 'O', 'p', '"', '>' };

  /** open a gap */
  static final char[] GAP_OPEN = new char[] { EBraces.PARENTHESES
      .getBegin() };
  /** close a gap */
  static final char[] GAP_CLOSE = new char[] { EBraces.PARENTHESES
      .getEnd() };
  /** comma */
  static final char[] COMMA = new char[] { ',' };

  /** the map with the operator data */
  static final EnumMap<EMathOp, _OpDef> MAP;

  static {
    final EnumMap<EMathOp, _OpDef> map;
    final char[] gapCO, gapCC, sum, prod;

    map = new EnumMap<>(EMathOp.class);

    gapCO = XMLCharTransformer.INSTANCE.transform(
        String.valueOf(EBraces.CURLY_BRACES.getBegin())).toCharArray();
    gapCC = XMLCharTransformer.INSTANCE.transform(
        String.valueOf(EBraces.CURLY_BRACES.getEnd())).toCharArray();

    map.put(EMathOp.ADD, //
        new _OpPosDef(null, new char[] { '+' }, null));

    map.put(EMathOp.SUB, //
        new _OpPosDef(null, new char[] { '-' }, null));

    map.put(EMathOp.MUL, //
        new _OpPosDef(null, new char[] { '*' }, null));

    map.put(EMathOp.DIV, _OpFrac.INST);

    map.put(EMathOp.DIV_INLINE, //
        new _OpPosDef(_OpDef.GAP_OPEN, new char[] { ')', '/', '(' },
            _OpDef.GAP_CLOSE));

    map.put(EMathOp.MOD, //
        new _OpPosDef(null, new char[] { 'm', 'o', 'd' }, null));

    map.put(EMathOp.POW, _OpPow.INST);

    map.put(EMathOp.LOG, // TODO
        new _OpPosDef(new char[] { 'l', 'o', 'g', '(', }, _OpDef.COMMA,
            _OpDef.GAP_CLOSE));

    map.put(EMathOp.LN, //
        new _OpPosDef(new char[] { 'l', 'n', }, null, null));

    map.put(EMathOp.LOG_10, //
        new _OpPosDef(new char[] { 'l', 'o', 'g', '<', 's', 'u', 'b', '>',
            '1', '0', '<', 's', 'u', 'b', '>' }, null, null));

    map.put(EMathOp.LOG_2, //
        new _OpPosDef(new char[] { 'l', 'o', 'g', '<', 's', 'u', 'b', '>',
            '2', '<', 's', 'u', 'b', '>' }, null, null));

    map.put(EMathOp.SQRT, _OpSqrt.INST);

    map.put(EMathOp.ROOT, // TODO
        new _OpPosDef(new char[] { 'r', 'o', 'o', 't', '(', },
            _OpDef.COMMA, _OpDef.GAP_CLOSE));

    map.put(EMathOp.ABS, _OpAbs.INST);

    map.put(EMathOp.MINUS, //
        new _OpPosDef(new char[] { '-' }, null, null));

    map.put(EMathOp.FACTORIAL, //
        new _OpPosDef(null, null, new char[] { '!' }));

    map.put(EMathOp.SIN, //
        new _OpPosDef(new char[] { 's', 'i', 'n' }, null, null));

    map.put(EMathOp.COS, //
        new _OpPosDef(new char[] { 'c', 'o', 's' }, null, null));

    map.put(EMathOp.TAN, //
        new _OpPosDef(new char[] { 't', 'a', 'n' }, null, null));

    map.put(EMathOp.AGGREGATE_SUM_FROM_TO, //
        new _OpSumFT((sum = XMLCharTransformer.INSTANCE
            .transform("\u2211").toCharArray()) //$NON-NLS-1$
        ));

    map.put(EMathOp.AGGREGATE_SUM_FOR_ALL, //
        new _OpSumFA(sum));

    map.put(
        EMathOp.AGGREGATE_PRODUCT_FROM_TO, //
        new _OpSumFT((prod = XMLCharTransformer.INSTANCE.transform(
            "\u220f").toCharArray()) //$NON-NLS-1$
        ));

    map.put(EMathOp.AGGREGATE_PRODUCT_FOR_ALL, //
        new _OpSumFA(prod));

    map.put(EMathOp.CMP_EQUALS, //
        new _OpPosDef(null, new char[] { '=' }, null));

    map.put(EMathOp.CMP_LESS_OR_EQUAL, //
        new _OpPosDef(null,//
            XMLCharTransformer.INSTANCE.transform("\u2264").toCharArray(), //$NON-NLS-1$
            null));

    map.put(EMathOp.CMP_LESS, //
        new _OpPosDef(null,//
            XMLCharTransformer.INSTANCE.transform("<").toCharArray(), //$NON-NLS-1$
            null));

    map.put(EMathOp.CMP_GREATER_OR_EQUAL, //
        new _OpPosDef(null,//
            XMLCharTransformer.INSTANCE.transform("\u2265").toCharArray(), //$NON-NLS-1$
            null));

    map.put(EMathOp.CMP_GREATER, //
        new _OpPosDef(null,//
            XMLCharTransformer.INSTANCE.transform(">").toCharArray(), //$NON-NLS-1$
            null));

    map.put(EMathOp.CMP_NOT_EQUAL, //
        new _OpPosDef(null,//
            XMLCharTransformer.INSTANCE.transform("\u2260").toCharArray(), //$NON-NLS-1$
            null));

    map.put(EMathOp.CMP_APPROX, //
        new _OpPosDef(null,//
            XMLCharTransformer.INSTANCE.transform("\u2248").toCharArray(), //$NON-NLS-1$
            null));

    map.put(EMathOp.CMP_APPROX, //
        new _OpPosDef(null,//
            XMLCharTransformer.INSTANCE.transform("\u22d1").toCharArray(), //$NON-NLS-1$
            null));

    map.put(EMathOp.LOGICAL_FOLLOWS, //
        new _OpPosDef(null,//
            XMLCharTransformer.INSTANCE.transform("\u21d2").toCharArray(), //$NON-NLS-1$
            null));

    map.put(EMathOp.LOGICAL_IFF, //
        new _OpPosDef(null,//
            XMLCharTransformer.INSTANCE.transform("\u21dd").toCharArray(), //$NON-NLS-1$
            null));

    map.put(EMathOp.LOGICAL_AND, //
        new _OpPosDef(null,//
            XMLCharTransformer.INSTANCE.transform("\u2227").toCharArray(), //$NON-NLS-1$
            null));

    map.put(EMathOp.LOGICAL_OR, //
        new _OpPosDef(null,//
            XMLCharTransformer.INSTANCE.transform("\u2228").toCharArray(), //$NON-NLS-1$
            null));

    map.put(EMathOp.LOGICAL_NOT, //
        new _OpPosDef(//
            XMLCharTransformer.INSTANCE.transform("\u00ac").toCharArray(), //$NON-NLS-1$
            null, null));

    map.put(EMathOp.TUPLE, new _OpPosDef(_OpDef.GAP_OPEN, _OpDef.COMMA,
        _OpDef.GAP_CLOSE));

    map.put(EMathOp.SET, new _OpPosDef(gapCO, _OpDef.COMMA, gapCC));

    map.put(EMathOp.SET_IN, //
        new _OpPosDef(null,//
            XMLCharTransformer.INSTANCE.transform("\u220a").toCharArray(), //$NON-NLS-1$
            null));

    map.put(EMathOp.SET_SUBSET, //
        new _OpPosDef(null,//
            XMLCharTransformer.INSTANCE.transform("\u2282").toCharArray(), //$NON-NLS-1$
            null));

    map.put(EMathOp.SET_SUBSET_EQ, //
        new _OpPosDef(null,//
            XMLCharTransformer.INSTANCE.transform("\u2286").toCharArray(), //$NON-NLS-1$
            null));

    map.put(EMathOp.ENCLOSING_PARENTHESES, //
        new _OpPosDef(_OpDef.GAP_OPEN, null, _OpDef.GAP_CLOSE));

    map.put(EMathOp.ENCLOSING_CURLY_BRACES, //
        new _OpPosDef(gapCO, null, gapCC));

    map.put(
        EMathOp.ENCLOSING_SQUARE_BRACKETS, //
        new _OpPosDef(//
            XMLCharTransformer.INSTANCE.transform(
                String.valueOf(EBraces.SQUARE_BRACKETS.getBegin()))
                .toCharArray(), null,//
            XMLCharTransformer.INSTANCE.transform(
                String.valueOf(EBraces.SQUARE_BRACKETS.getEnd()))
                .toCharArray()));

    map.put(
        EMathOp.ENCLOSING_ANGLE_BRACKETS, //
        new _OpPosDef(//
            XMLCharTransformer.INSTANCE.transform(
                String.valueOf(EBraces.ANGLE_BRACKETS.getBegin()))
                .toCharArray(), null,//
            XMLCharTransformer.INSTANCE.transform(
                String.valueOf(EBraces.CHEVRON.getEnd())).toCharArray()));

    map.put(EMathOp.ENCLOSING_INEQUALITY_BRACKETS, //
        new _OpPosDef(//
            XMLCharTransformer.INSTANCE.transform("\u300a").toCharArray(), //$NON-NLS-1$            
            null,//
            XMLCharTransformer.INSTANCE.transform("\u300b").toCharArray())); //$NON-NLS-1$

    map.put(EMathOp.FUNCTION_CALL, new _OpFuncCall());

    map.put(EMathOp.COMPLEX_BIG_O, //
        new _OpPosDef(//
            new char[] { 'O', '(' },//
            null, _OpDef.GAP_CLOSE));

    MAP = map;
  }

  /** create */
  _OpDef() {
    super();
  }

  /**
   * render the math operation
   * 
   * @param out
   *          the output
   * @param ops
   *          the operators
   * @throws IOException
   *           if i/o fails
   */
  abstract void render(final CharOutput out, final char[][] ops)
      throws IOException;
}
