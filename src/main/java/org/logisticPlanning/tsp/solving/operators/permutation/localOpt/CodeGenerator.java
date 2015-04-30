package org.logisticPlanning.tsp.solving.operators.permutation.localOpt;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Comparator;
import java.util.concurrent.Callable;

import org.logisticPlanning.tsp.benchmarking.objective.ObjectiveFunction;
import org.logisticPlanning.utils.utils.MemoryUtils;

/**
 * A code generator for local optimization operators.
 */
public final class CodeGenerator extends PrintWriter implements
    Callable<Void>, Comparator<int[]> {

  /** the node name before the start of the sequence */
  private static final String BEFORE_START = "beforeStart"; //$NON-NLS-1$
  /** the node name after the end of the sequence */
  private static final String AFTER_END = "afterEnd"; //$NON-NLS-1$
  /** the original node variable name */
  private static final String ORIG_NODE = "m_original_node_"; //$NON-NLS-1$
  /** the best node variable name */
  private static final String BEST_NODE = "m_best_node_"; //$NON-NLS-1$
  /** the best improvement */
  private static final String BEST_IMPROVEMENT = "m_best_delta"; //$NON-NLS-1$
  /** the distance variable name */
  private static final String DISTANCE = "m_dist_"; //$NON-NLS-1$
  /** the this prefix */
  private static final String THIS = "this."; //$NON-NLS-1$
  /** the current improvement */
  private static final String CURRENT_IMPROVEMENT = "current_delta"; //$NON-NLS-1$
  /** the parent delta */
  private static final String PARENT_DELTA = "parent_delta"; //$NON-NLS-1$
  /** the function name */
  private static final String FUNCTION_NAME = "__trace_"; //$NON-NLS-1$

  /** the sub-path length for which we generate code */
  private final int m_length;

  /** the class name */
  private final String m_className;

  /**
   * create
   * 
   * @param length
   *          the sub-path length for which we generate code
   * @param className
   *          the class name
   * @param dest
   *          the destination writer
   */
  public CodeGenerator(final int length, final String className,
      final Writer dest) {
    super(dest);
    this.m_length = length;
    this.m_className = className;
  }

  /**
   * Write the class header
   */
  private final void __classHeader() {

    this.print("package "); //$NON-NLS-1$
    this.print(LocalOptimizer.class.getPackage().getName());
    this.println(';');
    this.println();
    this.println();

    this.print("import ");//$NON-NLS-1$
    this.print(ObjectiveFunction.class.getCanonicalName());
    this.println(';');
    this.println();

    this.println("/** <p>");//$NON-NLS-1$
    this.print(" * An implementation of the class {@link ");//$NON-NLS-1$
    this.print(LocalOptimizer.class.getCanonicalName());
    this.print(//
    "} for finding optimal path sub-sequences of length ");//$NON-NLS-1$
    this.print(this.m_length);
    this.println('.');
    this.println(" * </p><p>");//$NON-NLS-1$
    this.println(//
    " * This code was automatically generated with the aim to be as efficient as possible.");//$NON-NLS-1$
    this.println(" * </p> */");//$NON-NLS-1$

    this.print("public final class ");//$NON-NLS-1$
    this.print(this.m_className);
    this.print(" extends ");//$NON-NLS-1$
    this.print(LocalOptimizer.class.getName());
    this.println(" {");//$NON-NLS-1$

    this.println();
    this.println("/** the serial version uid */ ");//$NON-NLS-1$
    this.println("private static final long serialVersionUID = 0L;");//$NON-NLS-1$
  }

  /** Write the class constructor */
  private final void __classConstructor() {
    this.println();
    this.println();
    this.print("/** The constructor of class {@link ");//$NON-NLS-1$
    this.print(this.m_className);
    this.println("} */");//$NON-NLS-1$
    this.print("public ");//$NON-NLS-1$
    this.print(this.m_className);
    this.print("() {");//$NON-NLS-1$
    this.println();
    this.print("super(");//$NON-NLS-1$
    this.print(this.m_length);
    this.print(");");//$NON-NLS-1$
    this.println();
    this.print("}"); //$NON-NLS-1$
  }

  /**
   * Begin the main method
   * 
   * @param root
   *          the root
   */
  private final void __mainMethod(final _PermutationPath root) {
    this.println();
    this.println();
    this.println("/** ");//$NON-NLS-1$
    this.print(" * This is the main entry point of class {@link ");//$NON-NLS-1$
    this.print(this.m_className);
    this.print('}');
    this.println('.');
    this.print(" * This method makes a sub-sequence of length ");//$NON-NLS-1$
    this.print(this.m_length);
    this.print(//
    " inside {@code path}, starting right after index {@code ");//$NON-NLS-1$
    this.print(CodeGenerator.BEFORE_START);
    this.println("}, optimal.");//$NON-NLS-1$
    this.println(//
    " * @param path the candidate solution in path representation");//$NON-NLS-1$
    this.print(" * @param ");//$NON-NLS-1$
    this.print(CodeGenerator.BEFORE_START);
    this.println(" the starting index");//$NON-NLS-1$
    this.println(" * @param dist the distance computer");//$NON-NLS-1$
    this.println(//
    " * @return the amount the length of the path has changed (negative if an improvement was found), or {@code 0L} if the path was not modified");//$NON-NLS-1$
    this.println(" */");//$NON-NLS-1$
    this.println("@Override");//$NON-NLS-1$
    this.print(//
    "public final long apply(final int[] path, final int ");//$NON-NLS-1$
    this.print(CodeGenerator.BEFORE_START);
    this.println(", final ObjectiveFunction dist) {");//$NON-NLS-1$

    this.__initializeMembers();

    this.println(//
    "// Trace all paths starting at the canonical permutation.");//$NON-NLS-1$
    // this.print(THIS);
    // this.print(FUNCTION_NAME);
    //    this.println("0();");//$NON-NLS-1$

    this.println();
    this.print("long ");//$NON-NLS-1$
    this.print(CodeGenerator.CURRENT_IMPROVEMENT);
    this.println(';');

    this.__makeTraceFunctionBody(root, null);

    this.__flushNodeCache();

    this.println();
    this.print("return ");//$NON-NLS-1$
    this.__bestImprovement();
    this.println("; //return the best improvement delta discovered");//$NON-NLS-1$

    this.print('}');
  }

  /**
   * call a function
   * 
   * @param path
   *          the function path
   */
  private final void __callTraceFunction(final _PermutationPath path) {
    if (path == null) {
      return;
    }
    if ((path.m_children == null) || (path.m_children.length <= 0)) {
      return;
    }

    this.print("// Trace the sub-set of permutations which efficiently can be reached from "); //$NON-NLS-1$
    this.__permutation(path.m_permutation);
    this.println(" by sequences of 2-opt moves.");//$NON-NLS-1$

    if (path.m_children.length > 1) {
      this.print(CodeGenerator.THIS);
      this.print(CodeGenerator.FUNCTION_NAME);
      this.print(path.m_id);
      this.print('(');
      if (path.m_id > 0) {
        this.print(CodeGenerator.CURRENT_IMPROVEMENT);
      }
      this.print(')');
      this.println(';');
    } else {
      this.__makeTraceFunctionBody(path, CodeGenerator.CURRENT_IMPROVEMENT);
    }
  }

  /**
   * make a function and all required sub-functions
   * 
   * @param path
   *          the function path
   */
  private final void __makeTraceFunction(final _PermutationPath path) {

    if (path == null) {
      return;
    }
    if ((path.m_children == null) || (path.m_children.length <= 1)) {
      return;
    }
    if (path.m_id <= 0) {
      return;
    }

    this.println();
    this.println();
    this.println("/** "); //$NON-NLS-1$
    this.print(//
    " * This function traces all permutations which can efficiently be reached from ");//$NON-NLS-1$
    this.__permutation(path.m_permutation);
    this.println('.');
    this.print(//
    " * &quot;Efficiently&quot; means that there is no shorter sequence of 2-opt moves starting at the canonical permutation which yields the child-permutations traced here than the path going over ");//$NON-NLS-1$
    this.__permutation(path.m_permutation);
    this.println('.');
    // if (isNotRoot) {
    this.print(" * @param ");//$NON-NLS-1$
    this.print(CodeGenerator.PARENT_DELTA);
    this.print(" the length change between the original tour and ");//$NON-NLS-1$
    this.__permutation(path.m_permutation);
    this.println();
    // }
    this.println(" */");//$NON-NLS-1$

    this.print("private final void ");//$NON-NLS-1$
    this.print(CodeGenerator.FUNCTION_NAME);
    this.print(path.m_id);
    this.print('(');
    // if (isNotRoot) {
    this.print("final long ");//$NON-NLS-1$
    this.print(CodeGenerator.PARENT_DELTA);
    // }
    this.println(") {");//$NON-NLS-1$

    this.println();
    this.print("long ");//$NON-NLS-1$
    this.print(CodeGenerator.CURRENT_IMPROVEMENT);
    this.println(';');

    this.__makeTraceFunctionBody(path, CodeGenerator.PARENT_DELTA);

    this.println('}');
  }

  /**
   * make a function and all required sub-functions
   * 
   * @param path
   *          the function path
   * @param currentDelta
   *          the current delta, or {@code null}
   */
  private final void __makeTraceFunctionBody(final _PermutationPath path,
      final String currentDelta) {
    if (path == null) {
      return;
    }

    for (final _PermutationPath child : path.m_children) {
      this.println();
      this.print("// Now processing permutation "); //$NON-NLS-1$
      this.__permutation(child.m_permutation);
      this.println('.');

      this.print(CodeGenerator.CURRENT_IMPROVEMENT);
      this.print(" = ("); //$NON-NLS-1$
      if (currentDelta != null) {
        this.print(currentDelta);
        this.print(" + ");//$NON-NLS-1$
      }

      this.__distance(child.m_add_1_1, child.m_add_1_2);
      this.print(" + ");//$NON-NLS-1$
      this.__distance(child.m_add_2_1, child.m_add_2_2);
      this.print(" - ");//$NON-NLS-1$
      this.__distance(child.m_remove_1_1, child.m_remove_1_2);
      this.print(" - ");//$NON-NLS-1$
      this.__distance(child.m_remove_2_1, child.m_remove_2_2);

      this.print(')');
      this.println(';');

      this.__checkBest(child.m_permutation);
      this.__callTraceFunction(child);

      this.print("// Finished processing permutation "); //$NON-NLS-1$
      this.__permutation(child.m_permutation);
      this.println('.');
    }
  }

  /** allocate the node cache */
  private final void __flushNodeCache() {
    int i;

    this.println();
    this.print("if (");//$NON-NLS-1$
    this.__bestImprovement();
    this.println(" < 0L) {");//$NON-NLS-1$
    this.print(//
    "// A new, improved node sequence has been discovered and cached in the ");//$NON-NLS-1$
    this.print(CodeGenerator.BEST_NODE);
    this.println(//
    "* member variables. We now write it back!");//$NON-NLS-1$

    this.print("i = "); //$NON-NLS-1$
    this.print(CodeGenerator.BEFORE_START);
    this.println(';');
    for (i = 1; i <= this.m_length; i++) {
      this.println("if ((++i) >= length) { i = 0; }");//$NON-NLS-1$   
      this.print("if (");//$NON-NLS-1$
      this.__bestNode(i);
      this.print(" > 0) { path[i] = ");//$NON-NLS-1$
      this.__bestNode(i);
      this.println("; }");//$NON-NLS-1$
    }

    this.println(//
    "// The improved node sequence has been flushed.");//$NON-NLS-1$

    this.println('}');
    this.println();
  }

  /** allocate the node cache */
  private final void __allocateNodeCache() {
    int i;

    this.println();
    this.println("// We cache all nodes related to the sub-sequence to be optimized."); //$NON-NLS-1$

    this.println("/** The node before the start index */"); //$NON-NLS-1$
    this.print("private int "); //$NON-NLS-1$
    this.print(CodeGenerator.ORIG_NODE);
    this.print(CodeGenerator.BEFORE_START);
    this.println(';');

    for (i = 1; i <= this.m_length; i++) {
      this.print("/** The node originally at index "); //$NON-NLS-1$
      this.print(i);
      this.println(" of the sub-sequence */"); //$NON-NLS-1$
      this.print("private int "); //$NON-NLS-1$
      this.print(CodeGenerator.ORIG_NODE);
      this.print(i);
      this.println(';');
      this.print("/** The best node found for index "); //$NON-NLS-1$
      this.print(i);
      this.print(//
      " of the sub-sequence, or empty/unused if no improvement was found, i.e., <code>{@link #"); //$NON-NLS-1$
      this.print(CodeGenerator.BEST_IMPROVEMENT);
      this.println("} &geq; 0</code> */"); //$NON-NLS-1$
      this.print("private int "); //$NON-NLS-1$
      this.print(CodeGenerator.BEST_NODE);
      this.print(i);
      this.println(';');
    }

    this.println("/** The node after the start index */"); //$NON-NLS-1$
    this.print("private int "); //$NON-NLS-1$
    this.print(CodeGenerator.ORIG_NODE);
    this.print(CodeGenerator.AFTER_END);
    this.println(';');

    this.println(//
    "// All nodes related to the sub-sequence to be optimized can now be cached."); //$NON-NLS-1$
    this.println();
  }

  /**
   * check the best permutation
   * 
   * @param perm
   *          the permutation
   */
  private final void __checkBest(final int[] perm) {
    int index;

    this.print("if ("); //$NON-NLS-1$
    this.print(CodeGenerator.CURRENT_IMPROVEMENT);
    this.print(" < "); //$NON-NLS-1$
    this.__bestImprovement();
    this.println(" ) {"); //$NON-NLS-1$
    this.print(//
    "// A new best permutation has been discovered: "); //$NON-NLS-1$
    this.__permutation(perm);
    this.println();
    this.__bestImprovement();
    this.print(" = "); //$NON-NLS-1$
    this.print(CodeGenerator.CURRENT_IMPROVEMENT);
    this.println(';');
    index = 1;
    for (final int i : perm) {
      this.__bestNode(index);
      this.print(" = "); //$NON-NLS-1$
      if (index != i) {
        this.__origNode(i);
        this.println(';');
      } else {
        this.println("(-1);");//$NON-NLS-1$
      }
      index++;
    }

    this.println('}');
  }

  /** allocate the distance matrix */
  private final void __allocateDistanceMatrix() {
    final int end;
    String s, e;
    int i, j;

    this.println();
    this.println(//
    "// We cache all distances related to the sub-sequence to be optimized."); //$NON-NLS-1$

    end = (this.m_length + 1);
    for (i = 0; i < end; i++) {
      s = ((i <= 0) ? CodeGenerator.BEFORE_START : Integer.toString(i));
      for (j = (i + 1); j <= end; j++) {
        if (i == 0) {
          if (j >= end) {
            continue;
          }
        }
        this.print("/** The distance from {@link #"); //$NON-NLS-1$
        this.print(CodeGenerator.ORIG_NODE);
        this.print(s);
        this.print("} to {@link #"); //$NON-NLS-1$
        e = ((j >= end) ? CodeGenerator.AFTER_END : Integer.toString(j));
        this.print(CodeGenerator.ORIG_NODE);
        this.print(e);
        this.println("} as {@code long}, since we always need {@code long} arithmetic anyway. */"); //$NON-NLS-1$
        this.print("private long "); //$NON-NLS-1$
        this.print(CodeGenerator.DISTANCE);
        this.print(s);
        this.print('_');
        this.print(e);
        this.println(';');
      }
    }

    this.println(//
    "// All distances related to the sub-sequence to be optimized can now be cached."); //$NON-NLS-1$
    this.println();
  }

  /** allocate the member variables */
  private final void __allocateBasicMembers() {
    this.println();
    //this.println("// Here we define the basic member variables."); //$NON-NLS-1$
    this.print(//
    "/** the best improvement found: if <code>{@link #"); //$NON-NLS-1$
    this.print(CodeGenerator.BEST_IMPROVEMENT);
    this.print(//
    "} &gt; 0</code>, then there is an improved sub-sequence stored in {@link #"); //$NON-NLS-1$
    this.print(CodeGenerator.BEST_NODE);
    this.print(//
    "1} &hellip; {@link #"); //$NON-NLS-1$
    this.print(CodeGenerator.BEST_NODE);
    this.print(this.m_length);
    this.println(//
    "}; otherwise these variables are not used */"); //$NON-NLS-1$    
    this.print("private long "); //$NON-NLS-1$
    this.print(CodeGenerator.BEST_IMPROVEMENT);
    this.println(';');

    //this.println("// The basic member variables have been defined."); //$NON-NLS-1$
    this.println();
  }

  /** allocate the member variables */
  private final void __allocateMembers() {
    this.__allocateBasicMembers();
    this.__allocateNodeCache();
    this.__allocateDistanceMatrix();
  }

  /** get the best improvement */
  private final void __bestImprovement() {
    this.print(CodeGenerator.THIS);
    this.print(CodeGenerator.BEST_IMPROVEMENT);
  }

  /** initialize the member variables */
  private final void __initializeBasicMembers() {
    this.println();
    //    this.println("// Here we initialize the basic member variables."); //$NON-NLS-1$
    this.__bestImprovement();
    this.println(" = 0L;"); //$NON-NLS-1$
    //    this.println("// The basic member variables have been initialized."); //$NON-NLS-1$
    this.println();
  }

  /**
   * access an original node
   * 
   * @param index
   *          the index
   */
  private final void __origNode(final int index) {
    this.print(CodeGenerator.THIS);
    this.print(CodeGenerator.ORIG_NODE);
    if (index <= 0) {
      this.print(CodeGenerator.BEFORE_START);
    } else {
      if (index <= this.m_length) {
        this.print(index);
      } else {
        this.print(CodeGenerator.AFTER_END);
      }
    }
  }

  /**
   * access a best node
   * 
   * @param index
   *          the index
   */
  private final void __bestNode(final int index) {
    this.print(CodeGenerator.THIS);
    this.print(CodeGenerator.BEST_NODE);
    this.print(index);
  }

  /** initialize the node cache variables */
  private final void __initializeNodeCache() {
    int i;

    this.println();
    this.println("// We cache all related nodes in member variables."); //$NON-NLS-1$
    this.print("i = "); //$NON-NLS-1$
    this.print(CodeGenerator.BEFORE_START);
    this.println("; // set the start index");//$NON-NLS-1$

    for (i = 0; i <= this.m_length; i++) {
      this.__origNode(i);
      this.println(" = path[i];");//$NON-NLS-1$
      this.println("if ((++i) >= length) { i = 0; }");//$NON-NLS-1$
    }
    this.__origNode(i);
    this.println(" = path[i];");//$NON-NLS-1$

    this.println("// Now all related nodes are cached in member variables."); //$NON-NLS-1$
    this.println();
  }

  /**
   * access the distance cache
   * 
   * @param a
   *          the first index
   * @param b
   *          the second index
   */
  private final void __distance(final int a, final int b) {
    int p, q;

    this.print(CodeGenerator.THIS);
    this.print(CodeGenerator.DISTANCE);
    if (a < b) {
      p = a;
      q = b;
    } else {
      p = b;
      q = a;
    }

    if (p <= 0) {
      this.print(CodeGenerator.BEFORE_START);
    } else {
      this.print(p);
    }

    this.print('_');

    if (q > this.m_length) {
      this.print(CodeGenerator.AFTER_END);
    } else {
      this.print(q);
    }
  }

  /** initialize the distance matrix */
  private final void __initializeDistanceMatrix() {
    final int end;
    int i, j;

    this.println();
    this.println("// We cache all distances related to the sub-sequence to be optimized."); //$NON-NLS-1$

    end = (this.m_length + 1);
    for (i = 0; i < end; i++) {
      for (j = (i + 1); j <= end; j++) {
        if (i == 0) {
          if (j >= end) {
            continue;
          }
        }
        this.__distance(i, j);
        this.print(" = dist.distance("); //$NON-NLS-1$
        this.__origNode(i);
        this.print(", ");//$NON-NLS-1$
        this.__origNode(j);
        this.print(')');
        this.println(';');
      }
    }

    this.println("// All distances related to the sub-sequence to be optimized are now cached."); //$NON-NLS-1$
    this.println();
  }

  /** initialize the member variables */
  private final void __initializeMembers() {
    this.println();
    this.println("final int length = path.length; // the number of elements in path");//$NON-NLS-1$
    this.println("int i; // a multi-purpose counter");//$NON-NLS-1$

    this.__initializeBasicMembers();
    this.__initializeNodeCache();
    this.__initializeDistanceMatrix();

    this.println();
  }

  /** Write the class footer */
  private final void __classFooter() {
    this.println();
    this.println();
    this.print('}');
    this.println();
  }

  /** clone */
  private final void __cloner() {
    this.println();
    this.println();
    this.println("/** {@inheritDoc} */"); //$NON-NLS-1$
    this.println("@Override"); //$NON-NLS-1$
    this.print("public final "); //$NON-NLS-1$
    this.print(this.m_className);
    this.println(" clone() {"); //$NON-NLS-1$
    this.print("return (("); //$NON-NLS-1$
    this.print(this.m_className);
    this.println(") (super.clone()));"); //$NON-NLS-1$
    this.println('}');
  }

  /** clone */
  private final void __subPathLength() {
    this.println();
    this.println();
    this.println("/** {@inheritDoc} */"); //$NON-NLS-1$
    this.println("@Override"); //$NON-NLS-1$
    this.println("public final int getSubPathLength() {"); //$NON-NLS-1$
    this.print("return "); //$NON-NLS-1$
    this.print(this.m_length);
    this.println(';');
    this.println('}');
  }

  /** {@inheritDoc} */
  @Override
  public Void call() throws IOException {
    _PermutationPath[] paths;

    this.__classHeader();

    this.__allocateMembers();

    this.__classConstructor();

    // this.__initializeMembers();

    paths = new _PermutationPathBuilder(this.m_length).call();

    this.__mainMethod(paths[0]);
    this.flush();
    MemoryUtils.gc();

    for (final _PermutationPath path : paths) {
      this.__makeTraceFunction(path);
    }

    this.flush();
    MemoryUtils.gc();

    this.__cloner();
    this.__subPathLength();

    this.__classFooter();
    return null;
  }

  /**
   * run the code generator
   * 
   * @param args
   *          the arguments
   * @throws IOException
   *           if i/o fails
   */
  public static final void main(final String[] args) throws IOException {
    File destDir;
    int start, end;
    String className;

    start = 2;
    end = 10;
    if (args.length > 0) {
      destDir = new File(args[0]);
      if (args.length > 1) {
        start = Math.max(2, Integer.parseInt(args[1]));
        if (args.length > 2) {
          end = Math.max(start, Integer.parseInt(args[2]));
        }
      }
    } else {
      destDir = new File("./generatedCode"); //$NON-NLS-1$
    }

    destDir.mkdirs();

    for (; start <= end; start++) {
      className = ("ExhaustivelyEnumeratingLocal" + start + //$NON-NLS-1$
      "Optimizer");//$NON-NLS-1$
      try (FileWriter fw = new FileWriter(new File(destDir, className
          + ".java"))) {//$NON-NLS-1$
        MemoryUtils.gc();
        try (CodeGenerator code = new CodeGenerator(start, className, fw)) {
          code.call();
          MemoryUtils.gc();
        }
        MemoryUtils.gc();
        MemoryUtils.gc();
      }
      MemoryUtils.gc();
      MemoryUtils.gc();
    }
  }

  /** {@inheritDoc} */
  @Override
  public final int compare(final int[] o1, final int[] o2) {
    int i, r;

    if (o1 == o2) {
      return 0;
    }
    for (i = 0; i < o1.length; i++) {
      r = Integer.compare(o1[i], o2[i]);
      if (r != 0) {
        return r;
      }
    }
    return 0;
  }

  /**
   * the permutation printer
   * 
   * @param perm
   *          the permutation to print
   */
  private final void __permutation(final int[] perm) {
    boolean has;

    this.print('(');
    has = false;
    for (final int i : perm) {
      if (has) {
        this.print(',');
      } else {
        has = true;
      }
      this.print(i);
    }
    this.print(')');
  }
}
