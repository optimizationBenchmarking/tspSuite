package org.logisticPlanning.utils.document.spec;

/** the sequence type */
public enum ESequenceType {

  /** a simple, comma-separated sequence {@code "a, b, c"} */
  COMMA,

  /** a sequence of the type {@code "a, b, and c"} */
  AND,

  /** a sequence of the type {@code "a, b, or c"} */
  OR,

  /** a sequence of the type {@code "either a, b, or c"} */
  XOR,

  /** a sequence of the type {@code "neither a, b, nor c"} */
  NOT,

  /** a sequence of the {@code "a to c"} */
  FROM_TO;

}
