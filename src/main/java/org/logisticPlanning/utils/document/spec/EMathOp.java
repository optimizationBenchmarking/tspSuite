package org.logisticPlanning.utils.document.spec;

/**
 * An enumeration of mathematical operators
 */
public enum EMathOp {

  // arithmetics

  /** the add operator */
  ADD("+", 2, Integer.MAX_VALUE), //$NON-NLS-1$

  /** the sub operator */
  SUB("-", 2, Integer.MAX_VALUE), //$NON-NLS-1$

  /** the mul operator */
  MUL("*", 2, Integer.MAX_VALUE), //$NON-NLS-1$

  /** the div operator */
  DIV("/", 2, 2), //$NON-NLS-1$

  /** the inline division operator */
  DIV_INLINE("/", 2, 2), //$NON-NLS-1$

  /** the mod operator */
  MOD("mod", 2, 2), //$NON-NLS-1$

  /** the pow operator */
  POW("^", 2, 2), //$NON-NLS-1$

  /** the log operator */
  LOG("log", 2, 2), //$NON-NLS-1$

  /** the ln operator */
  LN("ln", 1, 1), //$NON-NLS-1$

  /** the log_10 operator */
  LOG_10("lg", 1, 1), //$NON-NLS-1$

  /** the log_2operator */
  LOG_2("ld", 1, 1), //$NON-NLS-1$

  /** the sqrt operator */
  SQRT("sqrt", 1, 1), //$NON-NLS-1$

  /** the root operator */
  ROOT("root", 2, 2), //$NON-NLS-1$

  /** the abs operator */
  ABS("abs", 1, 1), //$NON-NLS-1$

  /** the minus operator */
  MINUS("minus", 1, 1), //$NON-NLS-1$

  /** the factorial operator */
  FACTORIAL("!", 1, 1), //$NON-NLS-1$

  /** the sinus */
  SIN("sin", 1, 1), //$NON-NLS-1$

  /** the cosinus */
  COS("cos", 1, 1), //$NON-NLS-1$

  /** the tangent */
  TAN("tan", 1, 1), //$NON-NLS-1$

  // aggregates

  /** the sum operator "from-to" */
  AGGREGATE_SUM_FROM_TO("sumFT", 3, 3), //$NON-NLS-1$

  /** the sum operator "for all" */
  AGGREGATE_SUM_FOR_ALL("sumFA", 2, 2), //$NON-NLS-1$

  /** the product operator "from-to" */
  AGGREGATE_PRODUCT_FROM_TO("prodFT", 3, 3), //$NON-NLS-1$

  /** the product operator "for-all" */
  AGGREGATE_PRODUCT_FOR_ALL("prodFA", 2, 2), //$NON-NLS-1$

  // comparison operators

  /** the equals operator */
  CMP_EQUALS("==", 2, 2), //$NON-NLS-1$

  /** the less-or-equal operator */
  CMP_LESS_OR_EQUAL("<=", 2, 2), //$NON-NLS-1$

  /** the less operator */
  CMP_LESS("<", 2, 2), //$NON-NLS-1$

  /** the greater-or-equal operator */
  CMP_GREATER_OR_EQUAL(">=", 2, 2), //$NON-NLS-1$
  /** the greater operator */
  CMP_GREATER(">", 2, 2), //$NON-NLS-1$

  /** the not equal operator */
  CMP_NOT_EQUAL("!=", 2, 2), //$NON-NLS-1$

  /** the approx operator */
  CMP_APPROX("approx", 2, 2), //$NON-NLS-1$

  /** the proportional operator */
  CMP_PROPORTIONAL("propTo", 2, 2), //$NON-NLS-1$

  // logical symbols

  /**
   * the double-left arrow signifying that one thing follows from another
   * one
   */
  LOGICAL_FOLLOWS("follows", 2, 2), //$NON-NLS-1$

  /** the double-left-right arrow signifying iff */
  LOGICAL_IFF("iff", 2, 2), //$NON-NLS-1$

  /** the logical and */
  LOGICAL_AND("land", 2, Integer.MAX_VALUE), //$NON-NLS-1$

  /** the logical or */
  LOGICAL_OR("lor", 2, Integer.MAX_VALUE), //$NON-NLS-1$

  /** the logical not */
  LOGICAL_NOT("lnot", 1, 1), //$NON-NLS-1$

  // tuple & set constructs

  /** the tuple */
  TUPLE("tuple", 1, Integer.MAX_VALUE), //$NON-NLS-1$

  /** the set */
  SET("set", 1, Integer.MAX_VALUE), //$NON-NLS-1$

  // set operations
  /** the set inclusion */
  SET_IN("in", 2, 2), //$NON-NLS-1$
  /** the set subset */
  SET_SUBSET("subset", 2, 2), //$NON-NLS-1$
  /** the set subset or equal */
  SET_SUBSET_EQ("subsetEq", 2, 2), //$NON-NLS-1$

  // Braces & Parentheses
  // see https://en.wikipedia.org/wiki/Bracket

  /** the parentheses */
  ENCLOSING_PARENTHESES("parentheses", 1, 1), //$NON-NLS-1$

  /** the curly braces */
  ENCLOSING_CURLY_BRACES("curlyBraces", 1, 1), //$NON-NLS-1$

  /** the square brackets */
  ENCLOSING_SQUARE_BRACKETS("squareBrackets", 1, 1), //$NON-NLS-1$

  /** the angle brackets */
  ENCLOSING_ANGLE_BRACKETS("angleBrackets", 1, 1), //$NON-NLS-1$
  /** the inequality brackets */
  ENCLOSING_INEQUALITY_BRACKETS("inequality Brackets", 1, 1), //$NON-NLS-1$

  /**
   * a function call in the form {@code f(x1,x2,...)}, where {@code f},
   * {@code x1}, etc. are parameters
   */
  FUNCTION_CALL("funcCall", 2, Integer.MAX_VALUE), //$NON-NLS-1$

  // Complexity

  /** the big-o */
  COMPLEX_BIG_O("bigO", 1, 1), //$NON-NLS-1$
  ;

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the operator string */
  final String m_op;

  /** the minimum arguments */
  final int m_minArgs;

  /** the maximum arguments */
  final int m_maxArgs;

  /**
   * Instantiate
   * 
   * @param op
   *          the operation
   * @param minArgs
   *          the minimum number of arguments
   * @param maxArgs
   *          the maximum number of arguments
   */
  EMathOp(final String op, final int minArgs, final int maxArgs) {
    this.m_op = op;
    this.m_minArgs = minArgs;
    this.m_maxArgs = maxArgs;
  }

  /** {@inheritDoc} */
  @Override
  public final String toString() {
    return this.m_op;
  }

  /**
   * Get the minimum number of arguments.
   * 
   * @return the minimum number of arguments
   */
  public final int getMinArgs() {
    return this.m_minArgs;
  }

  /**
   * Get the maximum number of arguments.
   * 
   * @return the maximum number of arguments
   */
  public final int getMaxArgs() {
    return this.m_maxArgs;
  }
}
