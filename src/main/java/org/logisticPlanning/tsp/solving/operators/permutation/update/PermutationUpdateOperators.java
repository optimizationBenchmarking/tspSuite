package org.logisticPlanning.tsp.solving.operators.permutation.update;

/**
 * the permutation update operators
 */
public final class PermutationUpdateOperators {

  /** all unique permutation update operators */
  public static final PermutationUpdateOperator[] UPDATE_OPERATORS = new PermutationUpdateOperator[] {//
    PermutationUpdate_Swap.INSTANCE,//
    PermutationUpdate_Reverse.INSTANCE,//
    PermutationUpdate_Rotate_Right.INSTANCE,//
    PermutationUpdate_Rotate_Left.INSTANCE, };
  /** all permutation update operators including their complement operators */
  public static final PermutationUpdateOperator[] OPERATORS_AND_COMPLEMENT = new PermutationUpdateOperator[] {//
    PermutationUpdate_Swap.INSTANCE,//
    PermutationUpdate_Reverse.INSTANCE,//
    PermutationUpdate_Reverse.COMPLEMENT,//
    PermutationUpdate_Rotate_Right.INSTANCE,//
    PermutationUpdate_Rotate_Right.COMPLEMENT,//
    PermutationUpdate_Rotate_Left.INSTANCE,
    PermutationUpdate_Rotate_Left.COMPLEMENT, };

}
