package org.logisticPlanning.tsp.solving.operators.permutation.localOpt;

/** the internal class used to represent a path */
final class _PermutationPath {
  //
  // /** the node names */
  // private static final char[] HEX = { '0', '1', '2', '3', '4', '5', '6',
  // '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
  // 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
  // 'x', 'y', 'z' };

  /** the permutation represented by this path */
  final int[] m_permutation;

  /** the permutation id */
  final int m_id;

  /** the starting point of the first added edge */
  int m_add_1_1;
  /** the ending point of the first added edge */
  int m_add_1_2;
  /** the starting point of the second added edge */
  int m_add_2_1;
  /** the ending point of the second added edge */
  int m_add_2_2;

  /** the starting point of the first removed edge */
  int m_remove_1_1;
  /** the ending point of the first removed edge */
  int m_remove_1_2;
  /** the starting point of the second removed edge */
  int m_remove_2_1;
  /** the ending point of the second removed edge */
  int m_remove_2_2;

  /** the owning path */
  _PermutationPath m_owner;

  /** the child paths */
  _PermutationPath[] m_children;

  /**
   * create the path
   *
   * @param permutation
   *          the permutation
   * @param id
   *          the permutation id
   */
  _PermutationPath(final int[] permutation, final int id) {
    super();
    this.m_permutation = permutation;
    this.m_id = id;
  }

  // /** print this path */
  // final void _print() {
  // this.__print(0);
  // }

  // /**
  // * print an index
  // *
  // * @param index
  // * the index
  // */
  // private static final void __printIdx(final int index) {
  // if (index <= 0) {
  // System.out.print('S');
  // } else {
  // if (index >= 10000) {
  // System.out.print('E');
  // } else {
  // System.out.print(HEX[index]);
  // }
  // }
  // }
  //
  // /**
  // * print this path
  // *
  // * @param indent
  // * the indentation
  // */
  // private final void __print(final int indent) {
  // for (int i = indent; (--i) >= 0;) {
  // System.out.print('\t');
  // }
  // for (int i : this.m_permutation) {
  // __printIdx(i);
  // }
  // if (this.m_add_1_1 >= 0) {
  // System.out.print(' ');
  // System.out.print('(');
  // __printIdx(this.m_add_1_1);
  // __printIdx(this.m_add_1_2);
  // System.out.print('+');
  // __printIdx(this.m_add_2_1);
  // __printIdx(this.m_add_2_2);
  // System.out.print('-');
  // __printIdx(this.m_remove_1_1);
  // __printIdx(this.m_remove_1_2);
  // System.out.print('-');
  // __printIdx(this.m_remove_2_1);
  // __printIdx(this.m_remove_2_2);
  // System.out.print(')');
  // }
  // System.out.println();
  // System.out.flush();
  // if (this.m_children != null) {
  // for (_PermutationPath path : this.m_children) {
  // path.__print(indent + 1);
  // }
  // }
  // }

}
